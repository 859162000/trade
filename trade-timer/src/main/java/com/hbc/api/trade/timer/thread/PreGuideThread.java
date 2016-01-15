/**
 * @Author lukangle
 * @2015年11月20日@上午11:07:00
 */
package com.hbc.api.trade.timer.thread;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hbc.api.trade.order.mapping.gen.bean.OrderBean;
import com.hbc.api.trade.timer.service.deliver.order.preguide.PreAssginGuideService;
@Component
public class PreGuideThread extends Thread{
	private final static Logger log = LoggerFactory.getLogger(PreAssginGuideService.class);
	@Autowired
	PreAssginGuideService preAssginGuideService;
	@Override
	public void run() {
		try {
			log.info("启动MIS预指派线程 .......");
			while(true){
				startToSendGuide();
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					log.error("", e);
				}
			}
		} catch (Exception e) {
			log.error("Schedule refresh model menu cache error. message: " + e.getMessage(), e);
		}
	}
	public void startToSendGuide(){
		List<OrderBean> preAssignOrders = preAssginGuideService.preAssginGuide();
		for(OrderBean orderBean : preAssignOrders){
			try{
				preAssginGuideService.assginPreToGuide(orderBean);
			}catch(Exception e){
				log.error("", e);
			}
		}
	}
}
