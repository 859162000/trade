/**
 * @Author lukangle
 * @2015年12月4日@下午8:28:21
 */
package com.hbc.api.trade.timer.thread.deliver;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hbc.api.trade.timer.service.deliver.order.nomal.NomalOrderDeliverSender;
@Component
public class NomalOrderDeliverSenderThread extends Thread {
	private final static Logger log = LoggerFactory.getLogger(NomalOrderDeliverSenderThread.class);
	@Autowired
	NomalOrderDeliverSender nomalOrderDeliverSender;
	@Override
	public void run() {
		log.info("发单预处理 以及 发送服务启动 程序启动.......");
		while (true) {
			try {
				nomalOrderDeliverSender.preSendOrder();
				nomalOrderDeliverSender.nomalSend();
				TimeUnit.SECONDS.sleep(1);
			} catch (Exception e) {
				log.error("", e);
			}
		}
	}

}
