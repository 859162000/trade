/**
 * @Author lukangle
 * @2015年11月14日@下午8:31:11
 */
package com.hbc.api.trade.timer.core;

import java.io.File;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hbc.api.trade.bdata.common.util.TConfigLoader;
import com.hbc.api.trade.bdata.common.util.TimeConverter;
import com.hbc.api.trade.order.service.OrderServiceTime;
import com.hbc.api.trade.timer.util.LogBackInit;

public class Timetest {

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

		LogBackInit.initLogBack(baseConfigDir + File.separator + "logback.xml");

		Logger log = LoggerFactory.getLogger(RedisQueueStarter.class);
		log.info("baseConfigDir:" + baseConfigDir + File.separator + "conf");
		TConfigLoader.loadProperties(baseConfigDir + File.separator + "conf");
		String springFileXml = "classpath:conf/trade-timer.xml";
		log.info(springFileXml);
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(springFileXml);
		OrderServiceTime orderServiceTime = applicationContext.getBean(OrderServiceTime.class);
		Date date = orderServiceTime.getServiceCityCurTime(217);
		
		System.out.println(TimeConverter.formatDate(date));
	}

}
