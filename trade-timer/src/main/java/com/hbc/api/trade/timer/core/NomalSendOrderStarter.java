/**
 * @Author lukangle
 * @2015年10月24日@上午11:59:26
 */
package com.hbc.api.trade.timer.core;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hbc.api.trade.bdata.common.util.TConfigLoader;
import com.hbc.api.trade.timer.service.deliver.order.nomal.NomalOrderDeliverSender;
import com.hbc.api.trade.timer.util.LogBackInit;

public class NomalSendOrderStarter {
	/**
	 * 普通发单 发单预处理
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

		Logger log = LoggerFactory.getLogger(NomalSendOrderStarter.class);
		log.info("baseConfigDir:" + baseConfigDir + File.separator + "cfiles");
		TConfigLoader.loadProperties(baseConfigDir + File.separator + "cfiles");
		String springFileXml = "classpath:conf/trade-timer.xml";
		log.info(springFileXml);
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(springFileXml);
		NomalOrderDeliverSender nomalOrderDeliverSender = applicationContext.getBean(NomalOrderDeliverSender.class);
//		PkDeliverService pkDeliverService = applicationContext.getBean(PkDeliverService.class);
		ExecutorService executor = Executors.newFixedThreadPool(3);
		executor.execute(new Runnable(){
			@Override
			public void run() {
				while(true){
					try{
						nomalOrderDeliverSender.preSendOrder();
					}catch(Exception e){
						log.error("", e);
					}
					try {
						TimeUnit.SECONDS.sleep(1);
					} catch (InterruptedException e) {
						log.error("", e);
					}
				}
			}
		});
		
		executor.execute(new Runnable(){
			@Override
			public void run() {
				while(true){
					try{
						nomalOrderDeliverSender.nomalSend();
					}catch(Exception e){
						log.error("", e);
					}
					try {
						TimeUnit.SECONDS.sleep(1);
					} catch (InterruptedException e) {
						log.error("", e);
					}
				}
			}
		});
		
	}

}
