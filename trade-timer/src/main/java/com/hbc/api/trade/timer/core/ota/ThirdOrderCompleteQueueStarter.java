/**
 * @brief 订单完成通知渠道队列任务
 * @author Ivan Huang <ivanhuang@huangbaoche.com>
 * @since 2015-11-12
 * @copyright Copyright (c) www.huangbaoche.com
 */

package com.hbc.api.trade.timer.core.ota;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hbc.api.trade.bdata.common.util.TConfigLoader;
import com.hbc.api.trade.order.exception.TradeException;
import com.hbc.api.trade.ota.service.ThirdOrderQueueService;
import com.hbc.api.trade.timer.util.LogBackInit;

public class ThirdOrderCompleteQueueStarter {

	public static void main(String[] args) {
		String baseConfigDir = "";
		if (args != null && args.length > 0) {
			baseConfigDir = args[0];
		} else {
			baseConfigDir = Class.class.getClass().getResource("/").getPath();
		}
		
		LogBackInit.initLogBack(baseConfigDir + File.separator + "/logback.xml");

		Logger log = LoggerFactory.getLogger(ThirdOrderCompleteQueueStarter.class);
		log.info("baseConfigDir:" + baseConfigDir + File.separator + "conf");
		TConfigLoader.loadProperties(baseConfigDir + File.separator + "conf");
		String springFileXml = "classpath:conf/trade-timer.xml";
		log.info(springFileXml);
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(springFileXml);
		ThirdOrderQueueService thirdOrderQueueService = applicationContext.getBean(ThirdOrderQueueService.class);
		while (true) {
			try {
				thirdOrderQueueService.execThirdCompleteQueue() ;
			} catch (TradeException e) {
				log.error("", e);
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
