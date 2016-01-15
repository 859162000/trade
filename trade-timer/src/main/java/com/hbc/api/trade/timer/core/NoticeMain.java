/**
 * @Author lukangle
 * @2015年12月2日@下午10:10:21
 */
package com.hbc.api.trade.timer.core;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hbc.api.trade.bdata.common.util.TConfigLoader;
import com.hbc.api.trade.timer.thread.LeaverTrackerThread;
import com.hbc.api.trade.timer.util.LogBackInit;

public class NoticeMain {
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
		LeaverTrackerThread leaverTrackerThread = applicationContext.getBean(LeaverTrackerThread.class);
		ExecutorService executor = Executors.newFixedThreadPool(3);
		executor.execute(leaverTrackerThread);
		
	}
}
