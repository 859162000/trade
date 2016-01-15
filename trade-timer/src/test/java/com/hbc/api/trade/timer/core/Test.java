/**
 * @Author lukangle
 * @2015年11月12日@下午3:37:57
 */
package com.hbc.api.trade.timer.core;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alibaba.fastjson.JSON;
import com.hbc.api.trade.bdata.common.HttpClientService;
import com.hbc.api.trade.bdata.common.util.TConfigLoader;
import com.hbc.api.trade.third.PushParam;
import com.hbc.api.trade.timer.util.LogBackInit;

public class Test {
	@Autowired
	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException {
		String baseConfigDir = "";
		if (args != null && args.length > 0) {
			baseConfigDir = args[0];
		} else {
			baseConfigDir = Class.class.getClass().getResource("/").getPath();
		}

		LogBackInit.initLogBack(baseConfigDir + File.separator + "/logback.xml");

		Logger log = LoggerFactory.getLogger(RedisQueueStarter.class);
		log.info("baseConfigDir:" + baseConfigDir + File.separator + "conf");
		TConfigLoader.loadProperties(baseConfigDir + File.separator + "conf");
		String springFileXml = "classpath:conf/trade-timer.xml";
		log.info(springFileXml);
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(springFileXml);
		HttpClientService httpClientService = applicationContext.getBean(HttpClientService.class);

		String fsd = httpClientService
				.sendReqPost("http://openapi.hbc.tech/gateway/v1/alipay/notify?discount=0.00&payment_type=1&subject=皇包车订单支付&trade_no=2015101921001004310055720026&buyer_email=773830444@qq.com&gmt_create=2015-10-19 16:29:50&notify_type=trade_status_sync&quantity=1&out_trade_no=C1001441019664447263&seller_id=2088611681636031&notify_time=2015-10-19 16:29:58&trade_status=TRADE_SUCCESS&is_total_fee_adjust=N&total_fee=0.01&gmt_payment=2015-10-19 16:29:58&seller_email=kateliu@cclx.com&price=0.01&buyer_id=2088012588780319&notify_id=3bf7c23a6454414d98f0295f47bcb31ie4&use_coupon=N&sign_type=MD5&sign=30ce9d9ca8d9c4046be18affbf446731");
		String url = "http://api.d.hbc.tech/communication/v1.0/order/push/send";
		PushParam pushParam = new PushParam();
		pushParam.setPush_type(1);
		pushParam.setPush_objects(new String[] { "设备号", "两万" });
		pushParam.setTitle("这里设置标题");
		pushParam.setTemplate_id(55);
		pushParam.setParams(new String[] { "天猫", "街机", "151115200813965775" });
		Map<String, String> map = new HashMap<>();
		map.put("orderId", "151101185702497185");
		map.put("orderType", "1");
		map.put("type", "2");
		pushParam.setExtras(map);

		String json = JSON.toJSONString(pushParam);
		fsd = httpClientService.sendReqPost(url, json);

		System.out.println(fsd);
	}

}
