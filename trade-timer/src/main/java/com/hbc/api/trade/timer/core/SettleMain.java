/**
 * @Author lukangle
 * @2015年11月22日@下午4:19:41
 */
package com.hbc.api.trade.timer.core;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hbc.api.trade.bdata.common.util.TConfigLoader;
import com.hbc.api.trade.timer.thread.SettleThread;
import com.hbc.api.trade.timer.util.LogBackInit;

public class SettleMain {

	/**
	 * @param args
	 */
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
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(springFileXml);
		SettleThread settleThread = applicationContext.getBean(SettleThread.class);
		
		ExecutorService executor = Executors.newFixedThreadPool(2);
		executor.execute(settleThread);

		try {
			Thread.currentThread().join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
