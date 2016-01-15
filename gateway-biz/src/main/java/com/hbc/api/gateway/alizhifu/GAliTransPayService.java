/**
 * @brief 支付宝提现Service
 * @author Ivan Huang <ivanhuang@huangbaoche.com> 
 * @since 2015-11-20
 */
package com.hbc.api.gateway.alizhifu;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import scala.collection.mutable.StringBuilder;

import com.hbc.api.gateway.alizhifu.config.AlipayConfig;
import com.hbc.api.gateway.alizhifu.req.TransInfo;
import com.hbc.api.gateway.alizhifu.sign.AliPayMD5;
import com.hbc.api.gateway.alizhifu.util.AlipayCore;
import com.hbc.api.gateway.enums.GatewayReturnCodeEnum;
import com.hbc.api.gateway.exception.GatewayException;

@Component
public class GAliTransPayService {

	private final static Logger log = LoggerFactory.getLogger(GAliTransPayService.class);

	@Autowired
	private AlipayConfig alipayConfig;

	@Autowired
	private AlipayCore alipayCore;

	public String getFundTransferUrl(String batchNo, List<TransInfo> transferList, Double totalAmount, String transTime) {
		if (transferList != null && transferList.size() > 0) {
			String transferDetail = TransferSummary.getTransferDetails(transferList, totalAmount);
			Map<String, String> hmap = new HashMap<String, String>();
			hmap.put("service", "batch_trans_notify"); //接口名称
			hmap.put("partner", alipayConfig.partner); //合作身份者ID 
			hmap.put("_input_charset", alipayConfig.payInputCharset);
			hmap.put("notify_url", alipayConfig.transferNotifyUrl); //服务器异步通知页面路径 
			hmap.put("account_name", alipayConfig.sellerPersonname); //付款账号名 
			hmap.put("detail_data", transferDetail); //付款详细数据 
			hmap.put("batch_no", batchNo); //批量付款批次号  批量付款批次号。11～32位的数字或字母或数字与字母的组合，且区分大小写。注意：批量付款批次号用作业务幂等性控制的依据，一旦提交受理，请勿直接更改批次号再次上传。 
			hmap.put("batch_num", String.valueOf(transferList.size())); //付款总笔数 
			hmap.put("batch_fee", String.valueOf(totalAmount)); //付款总金额 
			hmap.put("email", alipayConfig.sellerEmail); //付款账号
			hmap.put("pay_date", transTime); //支付日期 

			//hmap.put("buyer_account_name",) ; //付款账号别名 同email参数，可以使用该参数名代替email输入参数；优先级大于email。 
			//hmap.put("extend_param ",) ; //业务扩展参数  用于商户的特定业务信息的传递，只有商户与支付宝约定了传递此参数且约定了参数含义，此参数才有效。 参数格式：参数名1^参数值1|参数名2^参数值2|…… 多条数据用“|”间隔

			Set<String> keysetEncode = hmap.keySet();
			hmap = alipayCore.paraFilter(hmap);
			String sortReqStr = alipayCore.createLinkString(hmap);
			String sign = AliPayMD5.sign(sortReqStr, alipayConfig.securityCode);
			try {
				for (String key : keysetEncode) {
					hmap.put(key, URLEncoder.encode(hmap.get(key), alipayConfig.payInputCharset));
				}
			} catch (UnsupportedEncodingException e) {
				log.error(e.getMessage());
				throw new GatewayException(GatewayReturnCodeEnum.ERR_ALIPAY_URL);
			}

			sortReqStr = alipayCore.createLinkString(hmap);
			String finalUrl = new StringBuilder().append(alipayConfig.webPayUrl).append("?").append(sortReqStr).append("&sign=").append(sign).append("&sign_type=MD5").toString();
			log.info("批号：" + batchNo + " AliPay URL-> [" + finalUrl + "]");
			return finalUrl;
		} else {
			return "";
		}
	}
}
