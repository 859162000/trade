/**
 * @Author lukangle
 * @2015年10月23日@下午7:26:40
 */
package com.hbc.api.trade.timer.core;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hbc.api.trade.bdata.common.util.TConfigLoader;
import com.hbc.api.trade.timer.service.pay.AliPayRedisCusmer;
import com.hbc.api.trade.timer.service.pay.AliRefundRedisCusmer;
import com.hbc.api.trade.timer.util.LogBackInit;

public class RedisQueueStarter {

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
		AliPayRedisCusmer aliPayRedisCusmer = applicationContext.getBean(AliPayRedisCusmer.class);
		AliRefundRedisCusmer aliRefundRedisCusmer = applicationContext.getBean(AliRefundRedisCusmer.class);
		ExecutorService executor = Executors.newFixedThreadPool(2);
		executor.execute(new Runnable(){
			@Override
			public void run() {
				aliPayRedisCusmer.startToCustomer();
			}
		});
		
		executor.execute(new Runnable(){
			@Override
			public void run() {
				aliRefundRedisCusmer.startToCustomer();
			}
		});
		
		try {
			Thread.currentThread().join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
