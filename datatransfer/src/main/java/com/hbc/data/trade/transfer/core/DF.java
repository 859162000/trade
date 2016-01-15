/**
 * @Author lukangle
 * @2015年12月25日@下午6:54:52
 */
package com.hbc.data.trade.transfer.core;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hbc.api.trade.bdata.common.TradeConstant;
import com.hbc.api.trade.ota.adaptor.CarTypeContext;
import com.hbc.api.trade.ota.adaptor.QuaCarAdaptor;
import com.hbc.data.trade.transfer.service.TestRedisSend;
import com.hbc.data.trade.transfer.service.hfinal.FOrderService;
import com.hbc.data.trade.transfer.util.ConfigLoader;
import com.hbc.data.trade.transfer.util.LogBackInit;

public class DF {
	public static void main(String[] args) throws InterruptedException {
		String baseConfigDir = "";
		if (args != null && args.length > 0) {
			baseConfigDir = args[0];
		} else {
			baseConfigDir = Class.class.getClass().getResource("/").getPath();
		}

		StringBuilder carTypes = new StringBuilder();
		String[] all = "10".split(TradeConstant.SPLITER_COMMA);
		for(String each : all) {
			carTypes.append(TradeConstant.SPLITER_COMMA).append("1_5");
		}
		if(carTypes.length() > 2) {
			System.out.println(carTypes.substring(1));
		}
		
		LogBackInit.initLogBack(baseConfigDir + File.separator + "/logback.xml");

		Logger log = LoggerFactory.getLogger(FOrderService.class);
		log.info("baseConfigDir:" + baseConfigDir );
		ConfigLoader.loadProperties(baseConfigDir );
		
		ExecutorService executor = Executors.newFixedThreadPool(ConfigLoader.getInt("move.rnum", 20));
		String springFileXml = "classpath:conf/transfer.xml";
		log.info(springFileXml);
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(springFileXml);
		
		TestRedisSend testRedisSend =  applicationContext.getBean(TestRedisSend.class);
		
		
		testRedisSend.start(args[1],args[2],args[3]);
		
		System.exit(0);
	}

	
}
