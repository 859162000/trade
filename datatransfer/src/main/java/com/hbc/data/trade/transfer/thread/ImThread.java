/**
 * @Author lukangle
 * @2015年12月25日@上午12:43:02
 */
package com.hbc.data.trade.transfer.thread;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hbc.api.trade.order.mapping.gen.bean.OrderBean;
import com.hbc.data.trade.transfer.service.hfinal.FOrderService;
import com.hbc.data.trade.transfer.service.trade.DOrderService;
import com.hbc.data.trade.transfer.service.trade.ImServiceMove;

public class ImThread implements Runnable{
	private final static Logger log = LoggerFactory.getLogger(OrderThread.class);
	List<OrderBean> orderList;
	ImServiceMove imServiceMove;
	public ImThread(List<OrderBean> finalOrderBeans,ImServiceMove imServiceMove){
		this.orderList = finalOrderBeans;
		this.imServiceMove = imServiceMove;
	}
	@Override
	public void run() {
		long curtime = System.currentTimeMillis();
		for(OrderBean orderBean:orderList){
			try{
				imServiceMove.setImOrder(orderBean.getOrderNo());
			}catch(Exception e){
				log.error("订单 迁移错误",e);
			}
		}
		log.info("迁移["+orderList.size()+"]条 订单耗时["+((System.currentTimeMillis()-curtime)/(1000*60))+"]分钟");
	}
}
