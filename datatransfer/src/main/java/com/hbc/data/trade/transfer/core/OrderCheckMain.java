/**
 * @Author lukangle
 * @2015年12月20日@上午11:14:05
 */
package com.hbc.data.trade.transfer.core;

import java.io.File;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hbc.data.trade.transfer.mapping.hbcfinal.gen.bean.FinalOrderBean;
import com.hbc.data.trade.transfer.service.MoveDataCheckService;
import com.hbc.data.trade.transfer.service.hfinal.FOrderService;
import com.hbc.data.trade.transfer.thread.OrderCheckThread;
import com.hbc.data.trade.transfer.util.ConfigLoader;
import com.hbc.data.trade.transfer.util.LogBackInit;

public class OrderCheckMain {
	public static void main(String[] args) throws InterruptedException {
		String baseConfigDir = "";
		if (args != null && args.length > 0) {
			baseConfigDir = args[0];
		} else {
			baseConfigDir = Class.class.getClass().getResource("/").getPath();
		}

		LogBackInit.initLogBack(baseConfigDir + File.separator + "/logback.xml");

		Logger log = LoggerFactory.getLogger(FOrderService.class);
		log.info("baseConfigDir:" + baseConfigDir );
		ConfigLoader.loadProperties(baseConfigDir );
		ExecutorService executor = Executors.newFixedThreadPool(ConfigLoader.getInt("move.rnum", 20));
		String springFileXml = "classpath:conf/transfer.xml";
		log.info(springFileXml);
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(springFileXml);
		FOrderService fOrderService = applicationContext.getBean(FOrderService.class);

		MoveDataCheckService moveDataCheckService=  applicationContext.getBean(MoveDataCheckService.class);
		
		int countNum = fOrderService.countCompleteFinalOrders();
		
		int fnum = 1000;
		int tnum = (countNum/fnum)+1;
		long curtime = System.currentTimeMillis();
		
		for(int i=0;i<tnum;i++){
			List<FinalOrderBean> finalOrderBeans = fOrderService.getCompleteFinalOrderBean(fnum,i*fnum);
			
			OrderCheckThread orderThread = new OrderCheckThread(finalOrderBeans,moveDataCheckService);
			log.info("提交线程 "+i);
			executor.submit(orderThread);
			
//			moveDataCheckService.startToCheck(finalOrderBeans);
		}
		
		executor.shutdown();
		executor.awaitTermination(100, TimeUnit.HOURS);
		
		log.info("check["+countNum+"]条 订单耗时["+((System.currentTimeMillis()-curtime)/(1000*60))+"]分钟");
		
		System.exit(0);
		
	}
}
