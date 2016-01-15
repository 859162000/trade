/**
 * @Author lukangle
 * @2015年11月21日@下午3:00:13
 */
package com.hbc.api.trade.timer.thread;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hbc.api.trade.order.mapping.gen.bean.OrderBean;
import com.hbc.api.trade.timer.service.settle.OrderSettleService;
@Component
public class SettleThread implements Runnable{
	Logger log = LoggerFactory.getLogger(SettleThread.class);
	@Autowired
	OrderSettleService orderSettleService;
	@Override
	public void run() {
		log.info("结算线程启动.........");
		while(true){
			List<OrderBean> orders = orderSettleService.getAllSettleOrders();
			for(OrderBean orderBean : orders){
				try{
					orderSettleService.settleAcount(orderBean);
				}catch(Exception e){
					log.error("", e);
				}
			}
			
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				log.error("", e);
			}
		}
	}

}
