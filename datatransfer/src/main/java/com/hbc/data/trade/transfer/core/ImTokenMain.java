/**
 * @Author lukangle
 * @2015年12月22日@下午9:29:38
 */
package com.hbc.data.trade.transfer.core;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hbc.api.trade.bdata.common.util.TConfigLoader;
import com.hbc.api.trade.timer.core.RedisQueueStarter;
import com.hbc.data.trade.transfer.service.CheckRefundService;

public class ImTokenMain {
	public static void main(String[] args) throws InterruptedException {
		String baseConfigDir = "";
		if (args != null && args.length > 0) {
			baseConfigDir = args[0];
		} else {
			baseConfigDir = Class.class.getClass().getResource("/").getPath();
		}
		
		Logger log = LoggerFactory.getLogger(RedisQueueStarter.class);
		
		log.info("info info  info  ");
		log.error("error error  error  ");
		
		log.info("baseConfigDir:" + baseConfigDir + File.separator + "cfiles");
		TConfigLoader.loadProperties(baseConfigDir + File.separator + "cfiles");
		log.info("baseConfigDir:" + baseConfigDir );
		
		TConfigLoader.loadProperties(baseConfigDir + File.separator);
		String springFileXml = "classpath:conf/transfer.xml";
		log.info(springFileXml);
		
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(springFileXml);
		//1450953464
		
//		FOrderService fOrderService = applicationContext.getBean(FOrderService.class);
//
//		ImServiceMove imServiceMove=  applicationContext.getBean(ImServiceMove.class);
//		
//		
//		DPayService dpayService =  applicationContext.getBean(DPayService.class);
		
//		Csed csed=  applicationContext.getBean(Csed.class);
//		
//		log.info("check 开始");
//		csed.getAllXiechenOrder();
		
		CheckRefundService paymentCheck=  applicationContext.getBean(CheckRefundService.class);
		
		log.info("check 开始");
		paymentCheck.check();
//		startToSycQuna
//		for(OrderBean orderBean : olist){
//			log.info(orderBean.getOrderNo()+"   开始做账。。。。。。。。。。。。。。。。。。。。。。。。。。。。");
//			thridCarVal.update(orderBean);
//			log.info(orderBean.getOrderNo()+"   做账结束。。。。。。。。。。。。。。。。。。。。。。。。。。。。");
//		}
//		int fnum = 400;
//		ExecutorService	executor2 = Executors.newFixedThreadPool(ConfigLoader.getInt("move.rnum", 20));
//		
//		int countNum2 = imServiceMove.countAllFinalOrders();
//		long curtime = System.currentTimeMillis();
//		int tnum2 = (countNum2/fnum)+1;
//		
//		for(int i=0;i<tnum2;i++){
//			List<OrderBean> finalOrderBeans = imServiceMove.getAllFinalOrderBean(fnum,i*fnum);
//			
//			ImThread imThread = new ImThread(finalOrderBeans,imServiceMove);
//			log.info("提交线程 "+i);
//			executor2.submit(imThread);
//		}
//		
//		executor2.shutdown();
//		executor2.awaitTermination(100, TimeUnit.HOURS);
//		
//		
//		
//		log.info("check["+countNum2+"]条 订单耗时["+((System.currentTimeMillis()-curtime)/(1000*60))+"]分钟");
		
		System.exit(0);
		
	}
}
