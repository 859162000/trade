/**
 * @Author lukangle
 * @2015年12月23日@下午11:45:24
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

import com.hbc.api.trade.order.mapping.gen.bean.OrderBean;
import com.hbc.api.trade.third.restful.IMService;
import com.hbc.data.trade.transfer.mapping.hbcfinal.gen.bean.FinalOrderBean;
import com.hbc.data.trade.transfer.service.hfinal.FOrderService;
import com.hbc.data.trade.transfer.service.trade.DOrderService;
import com.hbc.data.trade.transfer.service.trade.DPayService;
import com.hbc.data.trade.transfer.thread.OrderThread;
import com.hbc.data.trade.transfer.util.ConfigLoader;
import com.hbc.data.trade.transfer.util.LogBackInit;

public class PriceTest {
	private final static Logger logger = LoggerFactory.getLogger(AccountCheckMain.class);
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

		DPayService dpayService =  applicationContext.getBean(DPayService.class);
		
		DOrderService ffOrderService=  applicationContext.getBean(DOrderService.class);
		
		int countNum = fOrderService.countCompleteFinalOrders();
		
		int fnum = 1000;
		int tnum = (countNum/fnum)+1;
		long curtime = System.currentTimeMillis();
		
		List<FinalOrderBean> finalOrderBeans = fOrderService.getCompleteTMPFinalOrderBean(110,0);
		for(FinalOrderBean finalOrderBean : finalOrderBeans){
			ffOrderService.moveOrder(finalOrderBean);
		}
		
		
		log.info("迁移["+countNum+"]条 订单耗时["+((System.currentTimeMillis()-curtime)/(1000*60))+"]分钟");
		System.exit(0);
	}
}
