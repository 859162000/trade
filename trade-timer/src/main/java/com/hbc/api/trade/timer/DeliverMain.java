/**
 * @Author lukangle
 * @2015年11月16日@下午3:29:21
 */
package com.hbc.api.trade.timer;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hbc.api.trade.bdata.common.util.TConfigLoader;
import com.hbc.api.trade.timer.core.RedisQueueStarter;
import com.hbc.api.trade.timer.kafka.KafkaConsumer;
import com.hbc.api.trade.timer.thread.LeaverTrackerThread;
import com.hbc.api.trade.timer.thread.PreGuideThread;
import com.hbc.api.trade.timer.thread.SettleThread;
import com.hbc.api.trade.timer.thread.deliver.IncrementSendThread;
import com.hbc.api.trade.timer.thread.deliver.NomalOrderDeliverSenderThread;
import com.hbc.api.trade.timer.thread.deliver.PkThread;
import com.hbc.api.trade.timer.thread.deliver.ReDeliverThread;
import com.hbc.api.trade.timer.thread.ota.OTAGuidConfirmThread;
import com.hbc.api.trade.timer.thread.ota.OTAOrderConfirmThread;
import com.hbc.api.trade.timer.thread.pay.AliPayThread;
import com.hbc.api.trade.timer.thread.pay.AliRefundThread;
import com.hbc.api.trade.timer.thread.pay.AliWithdrawThread;
import com.hbc.api.trade.timer.thread.pay.PayTimeoutThread;
import com.hbc.api.trade.timer.thread.pushsms.OrderPushSenderThread;

public class DeliverMain {

	/**
	 * @param args
	 */
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		String baseConfigDir = "";
		if (args != null && args.length > 0) {
			baseConfigDir = args[0];
		} else {
			baseConfigDir = Class.class.getClass().getResource("/").getPath();
		}
		
//		LogBackInit.initLogBack(baseConfigDir + File.separator + "/logback.xml");

		Logger log = LoggerFactory.getLogger(RedisQueueStarter.class);
		log.info("baseConfigDir:" + baseConfigDir + File.separator + "cfiles");
		TConfigLoader.loadProperties(baseConfigDir + File.separator + "cfiles");
		String springFileXml = "classpath:conf/trade-timer.xml";
		log.info(springFileXml);
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(springFileXml);
		
		ExecutorService executor = Executors.newFixedThreadPool(20);
		/**支付**/
		AliPayThread aliPayThread = applicationContext.getBean(AliPayThread.class);
		executor.execute(aliPayThread);
		/**支付超时**/
		PayTimeoutThread payTimeoutThread = applicationContext.getBean(PayTimeoutThread.class);
		executor.execute(payTimeoutThread);
		/**退款**/
		AliRefundThread aliRefundThread = applicationContext.getBean(AliRefundThread.class);
		executor.execute(aliRefundThread);

		//提现
		AliWithdrawThread aliWithdrawThread = applicationContext.getBean(AliWithdrawThread.class);
		executor.execute(aliWithdrawThread);

		/**MIS指派**/
		PreGuideThread preGuideThread = applicationContext.getBean(PreGuideThread.class);
		executor.execute(preGuideThread);
		
		/**结算线程启动**/
		SettleThread settleThread = applicationContext.getBean(SettleThread.class);
		executor.execute(settleThread);
		
		/***普通发单，  急单会立即发送**/
		NomalOrderDeliverSenderThread nomalOrderDeliverSenderThread = applicationContext.getBean(NomalOrderDeliverSenderThread.class);	
		executor.execute(nomalOrderDeliverSenderThread);
		/**重发**/
		ReDeliverThread reDeliverThread = applicationContext.getBean(ReDeliverThread.class);
		executor.execute(reDeliverThread);
		
		/**增量补发**/
		IncrementSendThread  incrementSendThread = applicationContext.getBean(IncrementSendThread.class);
		executor.execute(incrementSendThread);
		
		/**pk服务启动**/
		PkThread pkThread = applicationContext.getBean(PkThread.class);
		executor.execute(pkThread);
		
		/**push消息启动**/
		OrderPushSenderThread orderPushSenderThread = applicationContext.getBean(OrderPushSenderThread.class);
		executor.execute(orderPushSenderThread);
		
		/**航班kafka push启动**/
		KafkaConsumer flightPushCusmer = (KafkaConsumer)applicationContext.getBean("flightKafka");
		executor.execute(flightPushCusmer);
		/**提醒**/
		LeaverTrackerThread leaverTrackerThread = applicationContext.getBean(LeaverTrackerThread.class);
		executor.execute(leaverTrackerThread);
		
		/**OTA 订单确认**/
		OTAOrderConfirmThread orderConfirmThread = applicationContext.getBean(OTAOrderConfirmThread.class);
		executor.execute(orderConfirmThread);
		
		/**OTA 订单导游确认**/
		OTAGuidConfirmThread guidConfirmThread = applicationContext.getBean(OTAGuidConfirmThread.class);
		executor.execute(guidConfirmThread);
		
		try {
			Thread.currentThread().join();
		} catch (InterruptedException e) {
			log.error("", e);
		}
	}

}
