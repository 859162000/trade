package com.hbc.api.trade.timer.core;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hbc.api.trade.bdata.common.util.TConfigLoader;
import com.hbc.api.trade.order.enums.deliver.DeliverType;
import com.hbc.api.trade.order.enums.deliver.TradeDeliverStatus;
import com.hbc.api.trade.order.mapping.gen.bean.TradeOrderDeliver;
import com.hbc.api.trade.order.service.deliver.TradeDeliverService;
import com.hbc.api.trade.timer.service.deliver.order.redeliver.ReDeliverService;
import com.hbc.api.trade.timer.util.LogBackInit;

public class ReDeliverStarter {

	public static void main(String[] args) {
		String baseConfigDir = "";
		if (args != null && args.length > 0) {
			baseConfigDir = args[0];
		} else {
			baseConfigDir = Class.class.getClass().getResource("/").getPath();
		}

		LogBackInit.initLogBack(baseConfigDir + File.separator + "/logback.xml");

		Logger log = LoggerFactory.getLogger(ReDeliverStarter.class);
		log.info("baseConfigDir:" + baseConfigDir + File.separator + "cfiles");
		TConfigLoader.loadProperties(baseConfigDir + File.separator + "cfiles");
		String springFileXml = "classpath:conf/trade-timer.xml";
		log.info(springFileXml);
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(springFileXml);
		ReDeliverService reDeliverService = applicationContext.getBean(ReDeliverService.class);
		TradeDeliverService tradeDeliverService =applicationContext.getBean(TradeDeliverService.class);
		while (true) {
			try {
				int urgentHour = TConfigLoader.getInt("urgenthour", 24);
				int span = TConfigLoader.getInt("redelivertime", 10);
				String pushUrl = TConfigLoader.getProperty("pushurl");
//				reDeliverService.startToReDeliver(span, urgentHour, pushUrl);
				List<Integer> deliverStatuss = new ArrayList<Integer>();
				deliverStatuss.add(TradeDeliverStatus.delivered.value);
				deliverStatuss.add(TradeDeliverStatus.predeliver.value);

				List<TradeOrderDeliver> tradeDelivers = tradeDeliverService.getTradeDelivers(deliverStatuss, DeliverType.Cancle_Send);
				for(TradeOrderDeliver tradeOrderDeliver : tradeDelivers){
					reDeliverService.startToRedeliver(tradeOrderDeliver,span,urgentHour,pushUrl);
				}
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
