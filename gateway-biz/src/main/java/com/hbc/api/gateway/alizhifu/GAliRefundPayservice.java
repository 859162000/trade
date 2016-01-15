/**
 * @Author lukangle
 * @2015年10月17日@上午11:46:31
 */
package com.hbc.api.gateway.alizhifu;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hbc.api.gateway.alizhifu.config.AlipayConfig;
import com.hbc.api.gateway.alizhifu.sign.MD5;
import com.hbc.api.gateway.alizhifu.util.AlipayCore;
import com.hbc.api.gateway.enums.GatewayReturnCodeEnum;
import com.hbc.api.gateway.exception.GatewayException;
import com.hbc.api.gateway.util.TimeConverter;
import com.hbc.api.trade.bdata.common.HttpClientService;

@Component
public class GAliRefundPayservice {
	private Logger log = LoggerFactory.getLogger(GAliRefundPayservice.class);
	@Autowired
	AlipayConfig alipayConfig;
	@Autowired
	AlipayCore alipayCore;
	@Autowired
	protected HttpClientService httpClientService;

	
	/**
	 * 向支付宝退款
	 * @param tradePayment
	 * @param orderBean
	 * @param refundReason
	 * @return
	 * @throws Exception
	 */
	public String refundToAli(String batchNo,int batchNum,String refundDetail) {
		String reqUrl = buildRefundUrl(batchNo,batchNum,refundDetail);
		
		String rspstr = httpClientService.sendReqNoEncode(reqUrl);
		Document dom = Jsoup.parse(rspstr);
		Elements  eles = dom.getElementsByTag("alipay");
		for(Element element:eles){
			String isSuccess = element.getElementsByTag("is_success").get(0).text();
			if("f".equalsIgnoreCase(isSuccess.toLowerCase())){
				log.error(batchNo+" 退款失败");
				throw new GatewayException(GatewayReturnCodeEnum.GATEWAY_REFUND_FAILED, rspstr);
			}
		}
		return rspstr;
	}
	
	private String service ="refund_fastpay_by_platform_nopwd";//	接口名称 	String 	接口名称。 	不可空 	refund_fastpay_by_platform_pwd
	
	private String partner;//合作者身份ID 	String(16) 	签约的支付宝账号对应的支付宝唯一用户号。以2088开头的16位纯数字组成。 	不可空 	2088101008267254
	private String inputCharset ;//参数编码字符集 	String 	商户网站使用的编码格式，如utf-8、gbk、gb2312等。 	不可空 	GBK
	private String refundNotifyUrl;//服务器异步通知页面路径 	String(200) 	支付宝服务器主动通知商户网站里指定的页面http路径。 	可空 	http://api.test.alipay.net/atinterface/receive_notify.htm
	private String sellerEmail 	;//卖家支付宝账号 	String 	如果卖家Id已填，则此字段可为空。 	不可空 	Jier1105@alitest.com
	private String sellerUserId ;//	卖家用户ID 	String 	卖家支付宝账号对应的支付宝唯一用户号。以2088开头的纯16位数字。登录时，seller_email和seller_user_id两者必填一个。如果两者都填，以seller_user_id为准。 	不可空 	2088101008267254
	private String refundDate 	;//退款请求时间 	String 	退款请求的当前时间。格式为：yyyy-MM-dd hh:mm:ss。 	不可空 	2011-01-12 11:21:00
	
	private String  securityCode ;
	public void initParm(){
		service ="refund_fastpay_by_platform_nopwd";//	接口名称 	String 	接口名称。 	不可空 	refund_fastpay_by_platform_pwd
		partner =alipayConfig.partner;//合作者身份ID 	String(16) 	签约的支付宝账号对应的支付宝唯一用户号。以2088开头的16位纯数字组成。 	不可空 	2088101008267254
		inputCharset =alipayConfig.payInputCharset	;//参数编码字符集 	String 	商户网站使用的编码格式，如utf-8、gbk、gb2312等。 	不可空 	GBK
		refundNotifyUrl =alipayConfig.refundNotifyUrl	;//服务器异步通知页面路径 	String(200) 	支付宝服务器主动通知商户网站里指定的页面http路径。 	可空 	http://api.test.alipay.net/atinterface/receive_notify.htm
		sellerEmail 	=alipayConfig.sellerEmail;//卖家支付宝账号 	String 	如果卖家Id已填，则此字段可为空。 	不可空 	Jier1105@alitest.com
		sellerUserId =alipayConfig.partner;//	卖家用户ID 	String 	卖家支付宝账号对应的支付宝唯一用户号。以2088开头的纯16位数字。登录时，seller_email和seller_user_id两者必填一个。如果两者都填，以seller_user_id为准。 	不可空 	2088101008267254
		 securityCode = alipayConfig.securityCode;
	}
	/**
	 * 
	 * @param batchNo3End 前三位
	 * 每进行一次即时到账批量退款，都需要提供一个批次号，通过该批次号可以查询这一批次的退款交易记录，对于每一个合作伙伴，传递的每一个批次号都必须保证唯一性。格式为：退款日期（8位）+流水号（3～24位）。不可重复，且退款日期必须是当天日期。流水号可以接受数字或英文字符，建议使用数字，但不可接受“000”。 	不可空 	201101120001
	 * @param batchNum 即参数detail_data的值中，“#”字符出现的数量加1，最大支持1000笔（即“#”字符出现的最大数量为999个）。 	不可空 	1
	 * @param detailData 退款请求的明细数据。格式详情参见“4.3 单笔数据集参数说明”。 	不可空 	2011011201037066^5.00^协商退款
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public String buildRefundUrl(String batchNo,int batchNum,String refundDetail) {
		initParm();
		Map<String,String> hmap = new HashMap<String,String>();
		Date date = new Date(System.currentTimeMillis());
		
		refundDate = TimeConverter.formatDate(date);
		hmap.put("_input_charset", inputCharset);		
		hmap.put("service",  service);	//		接口名称 	String 	接口名称。 	不可空 	refund_fastpay_by_platform_pwd
		hmap.put("partner", partner);	// 	合作者身份ID 	String(16) 	签约的支付宝账号对应的支付宝唯一用户号。以2088开头的16位纯数字组成。 	不可空 	2088101008267254
		hmap.put("notify_url",  refundNotifyUrl);	//	服务器异步通知页面路径 	String(200) 	支付宝服务器主动通知商户网站里指定的页面http路径。 	可空 	http://api.test.alipay.net/atinterface/receive_notify.htm
		hmap.put("seller_email",  sellerEmail);	//	卖家支付宝账号 	String 	如果卖家Id已填，则此字段可为空。 	不可空 	Jier1105@alitest.com
		hmap.put("seller_user_id", sellerUserId);	// 	卖家用户ID 	String 	卖家支付宝账号对应的支付宝唯一用户号。以2088开头的纯16位数字。登录时，seller_email和seller_user_id两者必填一个。如果两者都填，以seller_user_id为准。 	不可空 	2088101008267254
		hmap.put("refund_date",  refundDate);	//	退款请求时间 	String 	退款请求的当前时间。格式为：yyyy-MM-dd hh:mm:ss。 	不可空 	2011-01-12 11:21:00
		hmap.put("batch_no", batchNo);	// 	退款批次号 	String 	每进行一次即时到账批量退款，都需要提供一个批次号，通过该批次号可以查询这一批次的退款交易记录，对于每一个合作伙伴，传递的每一个批次号都必须保证唯一性。格式为：退款日期（8位）+流水号（3～24位）。不可重复，且退款日期必须是当天日期。流水号可以接受数字或英文字符，建议使用数字，但不可接受“000”。 	不可空 	201101120001
		hmap.put("batch_num", batchNum+"");	//	总笔数 	String 	即参数detail_data的值中，“#”字符出现的数量加1，最大支持1000笔（即“#”字符出现的最大数量为999个）。 	不可空 	1
		hmap.put("detail_data",	 refundDetail);	//单笔数据集 	String 	退款请求的明细数据。格式详情参见“4.3 单笔数据集参数说明”。 	不可空 	2011011201037066^5.00^协商退款
		Set<String> keysetEncode = hmap.keySet();
	
		hmap = alipayCore.paraFilter(hmap);
		String sortReqStr = alipayCore.createLinkString(hmap);
		String sign = MD5.getMD5ofStr(sortReqStr+securityCode).toLowerCase();
		
		
		try {
			for(String key:keysetEncode)
				hmap.put(key, URLEncoder.encode(hmap.get(key),inputCharset));
		} catch (UnsupportedEncodingException e) {
			log.error("URLEncoder.encode处理AliPay URL参数发生异常:"+e.getMessage());
			throw new GatewayException(GatewayReturnCodeEnum.GATEWAY_REFUND_FAILED);
		}
		sortReqStr = alipayCore.createLinkString(hmap);
		String rurl = alipayConfig.webPayUrl+"?"+sortReqStr+"&sign="+sign+"&sign_type=MD5";
		return rurl;
	}
}
