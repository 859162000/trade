/**
 * @Author lukangle
 * @2015年12月3日@下午9:30:51
 */
package com.hbc.api.trade.timer.core;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hbc.api.trade.bdata.common.util.TConfigLoader;
import com.hbc.api.trade.order.mapping.gen.bean.OrderBean;
import com.hbc.api.trade.order.service.OrderQueryService;
import com.hbc.api.trade.third.sms.SMSService;
import com.hbc.api.trade.timer.util.LogBackInit;

public class SmsMain {
	public static void main(String[] args) {
		String baseConfigDir = "";
		if (args != null && args.length > 0) {
			baseConfigDir = args[0];
		} else {
			baseConfigDir = Class.class.getClass().getResource("/").getPath();
		}
		
		LogBackInit.initLogBack(baseConfigDir + File.separator + "/logback.xml");

		Logger log = LoggerFactory.getLogger(NomalSendOrderStarter.class);
		log.info("baseConfigDir:" + baseConfigDir + File.separator + "cfiles");
		TConfigLoader.loadProperties(baseConfigDir + File.separator + "cfiles");
		String springFileXml = "classpath:conf/trade-timer.xml";
		log.info(springFileXml);
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(springFileXml);
		SMSService smsservice = applicationContext.getBean(SMSService.class);
		OrderQueryService orderQueryService = applicationContext.getBean(OrderQueryService.class);
		
		OrderBean orderBean = orderQueryService.getOrderByNo("J120976233860");
		smsservice.cancleSMSConfirm(orderBean);
	}
}
