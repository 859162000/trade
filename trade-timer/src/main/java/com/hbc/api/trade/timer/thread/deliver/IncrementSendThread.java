/**
 * @Author lukangle
 * @2015年12月4日@下午8:34:04
 */
package com.hbc.api.trade.timer.thread.deliver;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hbc.api.trade.timer.service.deliver.order.Increment.IncrementOrderSender;
@Component
public class IncrementSendThread  extends Thread {
	private final static Logger log = LoggerFactory.getLogger(IncrementSendThread.class);
	@Autowired
	IncrementOrderSender incrementOrderSender;
	@Override
	public void run() {
		log.info("增量补发服务启动.......");
		while (true) {
			try {
				incrementOrderSender.startToIncrement();
				TimeUnit.SECONDS.sleep(1);
			} catch (Exception e) {
				log.error("", e);
			}
		}
	}

}