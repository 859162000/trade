/**
 * @Author lukangle
 * @2015年12月4日@下午8:39:54
 */
package com.hbc.api.trade.timer.thread.pay;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hbc.api.trade.timer.service.pay.AliRefundRedisCusmer;
@Component
public class AliRefundThread extends Thread {
	private final static Logger log = LoggerFactory.getLogger(AliRefundThread.class);
	@Autowired
	AliRefundRedisCusmer aliRefundRedisCusmer;
	@Override
	public void run() {
		log.info("启动退款 redis消费线程 .......");
		while (true) {
			try {
				aliRefundRedisCusmer.startToCustomer();
				TimeUnit.SECONDS.sleep(1);
			} catch (Exception e) {
				log.error("", e);
			}
		}
	}
}
