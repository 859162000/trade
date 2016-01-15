/**
 * @Author lukangle
 * @2015年12月4日@下午8:41:43
 */
package com.hbc.api.trade.timer.thread.pay;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hbc.api.trade.timer.service.pay.AliPayRedisCusmer;
@Component
public class AliPayThread extends Thread {
	private final static Logger log = LoggerFactory.getLogger(AliPayThread.class);
	@Autowired
	AliPayRedisCusmer aliPayRedisCusmer;
	@Override
	public void run() {
		log.info("启动支付 redis消费线程 .......");
		while (true) {
			try {
				aliPayRedisCusmer.startToCustomer();
				TimeUnit.SECONDS.sleep(1);
			} catch (Exception e) {
				log.error("", e);
			}
		}
	}
}
