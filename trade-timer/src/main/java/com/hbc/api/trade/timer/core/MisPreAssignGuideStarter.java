/**
 * @Author lukangle
 * @2015年11月10日@下午4:59:04
 */
package com.hbc.api.trade.timer.core;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hbc.api.trade.bdata.common.util.TConfigLoader;
import com.hbc.api.trade.timer.thread.PreGuideThread;
import com.hbc.api.trade.timer.util.LogBackInit;

/**
 * MIS预设导游  支付成功填充 定时任务
 */
public class MisPreAssignGuideStarter {
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
		TConfigLoader.loadProperties(baseConfigDir);
		String springFileXml = "classpath:conf/trade-timer.xml";
		log.info(springFileXml);
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(springFileXml);
		PreGuideThread preGuideThread = applicationContext.getBean(PreGuideThread.class);
		
		preGuideThread.start();
	}
}
