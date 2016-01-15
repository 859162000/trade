/**
 * @Author lukangle
 * @2015年12月11日@下午11:16:32
 */
package com.hbc.api.trade.timer.thread.pay;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hbc.api.trade.order.mapping.gen.bean.OrderBean;
import com.hbc.api.trade.timer.service.pay.PayTimeoutService;
@Component
public class PayTimeoutThread extends Thread {
	private final static Logger log = LoggerFactory.getLogger(PayTimeoutThread.class);
	@Autowired
	PayTimeoutService payTimeoutService;
	@Override
	public void run() {
		log.info("支付超时 线程启动 .......");
		while (true) {
			try {
				List<OrderBean> orderBeans = payTimeoutService.getAllNoPayOrders();
				
				for(OrderBean orderBean :orderBeans ){
					payTimeoutService.payOutCancle(orderBean);
				}
				TimeUnit.MINUTES.sleep(5);
			} catch (Exception e) {
				log.error("", e);
			}
		}
	}
}
