package com.hbc.api.trade.timer.core;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hbc.api.trade.bdata.common.util.TConfigLoader;
import com.hbc.api.trade.timer.service.deliver.order.Increment.IncrementOrderSender;
import com.hbc.api.trade.timer.util.LogBackInit;

public class IncrementalSendStarter {
	public static void main(String[] args) {
		String baseConfigDir = "";
		if (args != null && args.length > 0) {
			baseConfigDir = args[0];
		} else {
			baseConfigDir = Class.class.getClass().getResource("/").getPath();
		}

		LogBackInit.initLogBack(baseConfigDir + File.separator + "/logback.xml");

		Logger log = LoggerFactory.getLogger(IncrementalSendStarter.class);
		log.info("baseConfigDir:" + baseConfigDir + File.separator + "cfiles");
		TConfigLoader.loadProperties(baseConfigDir + File.separator + "cfiles");
		String springFileXml = "classpath:conf/trade-timer.xml";
		log.info(springFileXml);
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(springFileXml);
		IncrementOrderSender incrementOrderSender = applicationContext.getBean(IncrementOrderSender.class);
		
		
		while (true) {
			try {				
				String pushUrl = TConfigLoader.getProperty("pushurl");
				incrementOrderSender.startToIncrement();
				// reDeliverService.startToReDeliver();
			} catch (Exception e) {
				log.error("", e);
			}
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				log.error("", e);
			}
		}
	}
}
