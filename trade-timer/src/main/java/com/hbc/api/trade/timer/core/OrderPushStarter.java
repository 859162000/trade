package com.hbc.api.trade.timer.core;

import java.io.File;
import java.util.concurrent.ScheduledThreadPoolExecutor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hbc.api.trade.bdata.common.util.TConfigLoader;
import com.hbc.api.trade.timer.service.deliver.order.push.OrderPushSender;
import com.hbc.api.trade.timer.util.LogBackInit;

public class OrderPushStarter {
	public static void main(String[] args) {
		String baseConfigDir = "";
		if (args != null && args.length > 0) {
			baseConfigDir = args[0];
		} else {
			baseConfigDir = Class.class.getClass().getResource("/").getPath();
		}
		
		LogBackInit.initLogBack(baseConfigDir + File.separator + "/logback.xml");

		Logger log = LoggerFactory.getLogger(OrderPushStarter.class);
		log.info("baseConfigDir:" + baseConfigDir + File.separator + "cfiles");
		TConfigLoader.loadProperties(baseConfigDir + File.separator + "cfiles");
		String springFileXml = "classpath:conf/trade-timer.xml";
		log.info(springFileXml);
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(springFileXml);
		OrderPushSender orderPushSender = applicationContext.getBean(OrderPushSender.class);
		
		ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(1);
		executor.execute(new Runnable() {
			@Override
			public void run() {
				try {
					while(true){
						orderPushSender.startToOrderPush();
						
					}
				} catch (Exception e) {
					log.error("Schedule refresh model menu cache error. message: " + e.getMessage(), e);
				}
			}
		});
		
		
	}
}
