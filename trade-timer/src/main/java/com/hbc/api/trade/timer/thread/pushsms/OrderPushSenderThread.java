/**
 * @Author lukangle
 * @2015年12月4日@下午8:36:22
 */
package com.hbc.api.trade.timer.thread.pushsms;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hbc.api.trade.timer.service.deliver.order.push.OrderPushSender;
@Component
public class OrderPushSenderThread implements Runnable {
	private final static Logger log = LoggerFactory.getLogger(OrderPushSenderThread.class);
	@Autowired
	OrderPushSender orderPushSender ;
	@Override
	public void run() {
		log.info("push消息启动.......");
		while (true) {
			try {
				orderPushSender.startToOrderPush();
				TimeUnit.SECONDS.sleep(1);
			} catch (Exception e) {
				log.error("", e);
			}
		}
	}

}