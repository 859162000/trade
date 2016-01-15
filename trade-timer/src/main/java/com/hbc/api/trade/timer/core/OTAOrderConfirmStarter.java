/**
 * @Author lukangle
 * @2015年12月16日@下午4:08:25
 */
package com.hbc.api.trade.timer.core;

import java.io.File;
import java.util.concurrent.ScheduledThreadPoolExecutor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hbc.api.trade.bdata.common.util.TConfigLoader;
import com.hbc.api.trade.timer.thread.ota.OTAGuidConfirmThread;
import com.hbc.api.trade.timer.thread.ota.OTAOrderConfirmThread;
import com.hbc.api.trade.timer.util.LogBackInit;

public class OTAOrderConfirmStarter {
	public static void main(String[] args) {
		String baseConfigDir = "";
		if (args != null && args.length > 0) {
			baseConfigDir = args[0];
		} else {
			baseConfigDir = Class.class.getClass().getResource("/").getPath();
		}
		
		LogBackInit.initLogBack(baseConfigDir + File.separator + "/logback.xml");

		Logger log = LoggerFactory.getLogger(RedisQueueStarter.class);
		log.info("baseConfigDir:" + baseConfigDir + File.separator + "cfiles");
		TConfigLoader.loadProperties(baseConfigDir + File.separator + "cfiles");
		String springFileXml = "classpath:conf/trade-timer.xml";
		log.info(springFileXml);
		@SuppressWarnings("resource")
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(springFileXml);
		OTAOrderConfirmThread orderConfirmThread = applicationContext.getBean(OTAOrderConfirmThread.class);
		OTAGuidConfirmThread pkThread = applicationContext.getBean(OTAGuidConfirmThread.class);
		ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(2);
		executor.execute(orderConfirmThread);
		executor.execute(pkThread);
	}
}
