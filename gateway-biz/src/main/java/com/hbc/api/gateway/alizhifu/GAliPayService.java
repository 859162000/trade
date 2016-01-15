/**
 * @Author lukangle
 * @2015年10月16日@上午11:33:20
 */
package com.hbc.api.gateway.alizhifu;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import scala.collection.mutable.StringBuilder;

import com.hbc.api.gateway.alizhifu.config.AlipayConfig;
import com.hbc.api.gateway.alizhifu.sign.AliPayMD5;
import com.hbc.api.gateway.alizhifu.sign.RSA;
import com.hbc.api.gateway.alizhifu.util.AlipayCallBackValidator;
import com.hbc.api.gateway.enums.GatewayReturnCodeEnum;
import com.hbc.api.gateway.exception.GatewayException;

/**
 * 阿里 直接到账 支付 支付接口
 */
@Component
public class GAliPayService {

	private static String _input_charset = "UTF-8";
	private final static Logger log = LoggerFactory.getLogger(GAliPayService.class);

	@Autowired
	AlipayConfig alipayConfig;

	//	@Autowired
	//	AlipayCore alipayCore;

	@Autowired
	private AlipayCallBackValidator validator;

	public String getWebPayUrl(String orderNo, double payMoney) {
		String notify_url = alipayConfig.payNotifyUrl;
		String seller_id = alipayConfig.partner;
		String securityCode = alipayConfig.securityCode;
		String subject = "皇包车订单支付";
		String service = "create_direct_pay_by_user";
		String partner = alipayConfig.partner;

		String payment_type = alipayConfig.paymentType;
		String total_fee = String.valueOf(payMoney);
		String it_b_pay = alipayConfig.itBPay;

		Map<String, String> hmap = new HashMap<String, String>();
		hmap.put("_input_charset", "utf-8");
		hmap.put("notify_url", notify_url);
		hmap.put("out_trade_no", orderNo);
		hmap.put("payment_type", payment_type);
		hmap.put("total_fee", total_fee);
		hmap.put("seller_email", alipayConfig.sellerEmail);
		hmap.put("subject", subject);
		hmap.put("service", service);
		hmap.put("partner", partner);
		hmap.put("seller_id", seller_id);
		hmap.put("it_b_pay", it_b_pay);
		//		hmap.put("return_url","http://stage.pay.huangbaoche.com/return/r1/l1/p201510161014381353760000000stage/p1/m1444961678/1d4f29adfb836127087cdcc7f19ac197");
		Set<String> keysetEncode = hmap.keySet();

		hmap = validator.parmFilter(hmap);
		String sortReqStr = validator.createLinkString(hmap);
		//String sign = MD5.getMD5ofStr(sortReqStr+securityCode).toLowerCase();
		String sign = AliPayMD5.sign(sortReqStr, securityCode, AliPayMD5.DEFAULT_ENCODING);

		try {
			for (String key : keysetEncode)
				hmap.put(key, URLEncoder.encode(hmap.get(key), AliPayMD5.DEFAULT_ENCODING));
		} catch (UnsupportedEncodingException e) {
			log.error(e.getMessage());
			throw new GatewayException(GatewayReturnCodeEnum.ERR_ALIPAY_URL);
		}

		sortReqStr = validator.createLinkString(hmap);

		//String rurl = alipayConfig.webPayUrl+"?"+sortReqStr+"&sign="+sign+"&sign_type=MD5";

		String webUrl = new StringBuilder().append(alipayConfig.webPayUrl).append("?").append(sortReqStr).append("&sign=").append(sign).append("&sign_type=MD5").toString();
		log.info(orderNo + " get payurl is [" + webUrl + "]");
		return webUrl;
	}

	public String getMobilePayUrl(String orderNo, double payMoney, String appid, String appenv) {
		String notify_url = alipayConfig.payNotifyUrl;
		String seller_id = alipayConfig.partner;
		String subject = "皇包车订单支付";
		String service = "mobile.securitypay.pay";
		String partner = alipayConfig.partner;

		String payment_type = alipayConfig.paymentType;
		String total_fee = String.valueOf(payMoney);
		String it_b_pay = alipayConfig.itBPay;

		Map<String, String> hmap = new HashMap<String, String>();

		hmap.put("_input_charset", _input_charset);
		hmap.put("notify_url", notify_url);
		hmap.put("out_trade_no", orderNo);
		hmap.put("payment_type", payment_type);
		hmap.put("total_fee", total_fee);
		hmap.put("seller_email", alipayConfig.sellerEmail);
		hmap.put("subject", subject);
		hmap.put("service", service);
		hmap.put("partner", partner);
		hmap.put("seller_id", seller_id);
		hmap.put("body", subject);
		hmap.put("it_b_pay", it_b_pay);
		hmap.put("appenv", appenv);
		hmap.put("app_id", appid);

		Set<String> keysetEncode = hmap.keySet();

		hmap = validator.parmFilter(hmap);
		//		String sign = MD5.getMD5ofStr(sortReqStr+securityCode).toLowerCase();

		//		for(String key:keysetEncode){
		//			hmap.put(key, URLEncoder.encode(hmap.get(key),"utf-8"));
		//		}
		StringBuffer spayurl = new StringBuffer();
		for (String keystr : keysetEncode) {
			spayurl = spayurl.append(keystr + "=\"" + hmap.get(keystr) + "\"&");
		}
		String dspayurl = spayurl.substring(0, spayurl.length() - 1);

		String sign = RSA.sign(dspayurl, alipayConfig.privateKey, "UTF-8");

		try {
			sign = URLEncoder.encode(sign, "utf-8");
		} catch (UnsupportedEncodingException e) {
			log.error(e.getMessage());
			throw new GatewayException(GatewayReturnCodeEnum.ERR_ALIPAY_SIGN);
		}
		String rurl = dspayurl + "&sign=\"" + sign + "\"&sign_type=\"RSA\"";
		log.info(orderNo + " get payurl is [" + rurl + "]");
		return rurl;
	}

	//	public String testMobilePayUrl(AliMobilePayReqUrlParam aliMobilePayReqUrlParam) throws UnsupportedEncodingException{
	//		String notify_url = AlipayConfig.notify_url;
	//		
	//		String orderNo = aliMobilePayReqUrlParam.getOrderNo();
	//		OrderBean orderBean = orderQueryService.getOrderByNo(orderNo);
	//		
	//		if(orderBean==null){
	//			throw new TradeException(TradeReturnCodeEnum.ORDER_NOTEXIST, orderNo);
	//		}
	////		if(orderBean.getPriceSale().equals(aliMobilePayReqUrlParam.getPriceSale()) && aliMobilePayReqUrlParam.getPriceSale()>0){
	//		if(true){
	//			String seller_id=alipayConfig.getPartner();
	//			String subject = "皇包车订单支付";
	//			String service = "mobile.securitypay.pay";
	//			String partner =alipayConfig.getPartner();
	//			
	//			String payment_type = AlipayConfig.payment_type;
	//			String total_fee = String.valueOf(orderBean.getPriceSale());
	//			String it_b_pay = AlipayConfig.it_b_pay;
	//			
	//			Map<String,String> hmap = new HashMap<String,String>();
	//			
	//			hmap.put("_input_charset", _input_charset);		
	//			hmap.put("notify_url", "http://pay.huangbaoche.com/notify/direct/1/1");		
	//			hmap.put("out_trade_no", "20150912235215702293233819693571");		
	//			hmap.put("payment_type", payment_type);
	//			hmap.put("total_fee", "0.01");
	//			hmap.put("seller_email", AlipayConfig.seller_email);
	//			hmap.put("subject", subject);
	//			hmap.put("service", service);
	//			hmap.put("partner",partner);
	//			hmap.put("seller_id",partner);
	//			hmap.put("body",subject);
	//			hmap.put("it_b_pay",it_b_pay);
	//			hmap.put("appenv",aliMobilePayReqUrlParam.getAppEnv());
	//			hmap.put("app_id",aliMobilePayReqUrlParam.getAppId());
	//			
	//			Set<String> keysetEncode = hmap.keySet();
	//		
	//			hmap = AlipayCore.paraFilter(hmap);
	////			String sign = MD5.getMD5ofStr(sortReqStr+securityCode).toLowerCase();
	//			
	//			
	////			for(String key:keysetEncode){
	////				hmap.put(key, URLEncoder.encode(hmap.get(key),"utf-8"));
	////			}
	//			String orderInfo = getOrderInfo(subject,subject,"0.01",orderBean.getOrderNo(),AlipayConfig.notify_url);
	//			String sign = sign(orderInfo);
	//			try {
	//				// 仅需对sign 做URL编码
	//				sign = URLEncoder.encode(sign, "UTF-8");
	//			} catch (UnsupportedEncodingException e) {
	//				e.printStackTrace();
	//			}
	//
	//			// 完整的符合支付宝参数规范的订单信息
	//			final String payInfo = orderInfo + "&sign=\"" + sign + "\"&"
	//					+ getSignType();
	//			log.info(payInfo);
	//			
	//			StringBuffer spayurl = new StringBuffer();
	//			for(String keystr:keysetEncode){
	//				spayurl = spayurl.append(keystr+"=\""+hmap.get(keystr)+"\"&");
	//			}
	//			String dspayurl = spayurl.substring(0, spayurl.length()-1)+"&return_url=\"m.alipay.com\"";
	//			String sign2 = RSA.sign(dspayurl, AlipayConfig.private_key, "UTF-8");
	//			sign2 = URLEncoder.encode(sign(dspayurl), "UTF-8");
	//			
	////			String rurl = payInfo;
	//			String rurl = dspayurl+"&sign=\""+sign2+"\"&sign_type=\"RSA\"";
	//			log.info(rurl);
	//			log.info(JSON.toJSON(aliMobilePayReqUrlParam)+" get payurl is ["+rurl+"]");
	//			return rurl;
	//		}else{
	//			throw new PayException(PayReturnCodeEnum.PAY_PRICESALE_CHANGED,orderBean.getPriceSale());
	//		}
	//	}
	//	
	//	public String getOrderInfo(String subject, String body, String price,String orderNo,String notify_url) {
	//
	//		// 签约合作者身份ID
	//		String orderInfo = "partner=" + "\"" +alipayConfig.getPartner() + "\"";
	//
	//		// 签约卖家支付宝账号
	//		orderInfo += "&seller_id=" + "\"" +alipayConfig.getPartner() + "\"";
	//
	//		// 商户网站唯一订单号
	//		orderInfo += "&out_trade_no=" + "\"" + orderNo + "\"";
	//
	//		// 商品名称
	//		orderInfo += "&subject=" + "\"" + subject + "\"";
	//
	//		// 商品详情
	//		orderInfo += "&body=" + "\"" + body + "\"";
	//
	//		// 商品金额
	//		orderInfo += "&total_fee=" + "\"" + price + "\"";
	//
	//		// 服务器异步通知页面路径
	//		orderInfo += "&notify_url=" + "\"" +notify_url
	//				+ "\"";
	//
	//		// 服务接口名称， 固定值
	//		orderInfo += "&service=\"mobile.securitypay.pay\"";
	//
	//		// 支付类型， 固定值
	//		orderInfo += "&payment_type=\"1\"";
	//
	//		// 参数编码， 固定值
	//		orderInfo += "&_input_charset=\"utf-8\"";
	//
	//		// 设置未付款交易的超时时间
	//		// 默认30分钟，一旦超时，该笔交易就会自动被关闭。
	//		// 取值范围：1m～15d。
	//		// m-分钟，h-小时，d-天，1c-当天（无论交易何时创建，都在0点关闭）。
	//		// 该参数数值不接受小数点，如1.5h，可转换为90m。
	//		orderInfo += "&it_b_pay=\""+AlipayConfig.it_b_pay+"\"";
	//
	//		// extern_token为经过快登授权获取到的alipay_open_id,带上此参数用户将使用授权的账户进行支付
	//		// orderInfo += "&extern_token=" + "\"" + extern_token + "\"";
	//
	//		// 支付宝处理完请求后，当前页面跳转到商户指定页面的路径，可空
	////		orderInfo += "&return_url=\"m.alipay.com\"";
	//
	//		// 调用银行卡支付，需配置此参数，参与签名， 固定值 （需要签约《无线银行卡快捷支付》才能使用）
	//		// orderInfo += "&paymethod=\"expressGateway\"";
	//
	//		return orderInfo;
	//	}
	//	
	//	public String sign(String content) {
	//		return SignUtils.sign(content, AlipayConfig.private_key);
	//	}
	//
	//	/**
	//	 * get the sign type we use. 获取签名方式
	//	 * 
	//	 */
	//	public String getSignType() {
	//		return "sign_type=\"RSA\"";
	//	}
}
