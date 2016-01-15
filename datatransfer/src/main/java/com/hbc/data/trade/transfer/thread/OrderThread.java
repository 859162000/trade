/**
 * @Author lukangle
 * @2015年12月17日@下午10:58:13
 */
package com.hbc.data.trade.transfer.thread;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hbc.api.trade.order.mapping.gen.bean.OrderBean;
import com.hbc.data.trade.transfer.mapping.hbcfinal.gen.bean.FinalOrderBean;
import com.hbc.data.trade.transfer.service.hfinal.FOrderService;
import com.hbc.data.trade.transfer.service.trade.DOrderService;

public class OrderThread implements Runnable{
	private final static Logger log = LoggerFactory.getLogger(OrderThread.class);
	List<FinalOrderBean> finalOrderBeans;
	FOrderService fOrderService;
	DOrderService ffOrderService;
	public OrderThread(List<FinalOrderBean> finalOrderBeans,FOrderService fOrderService,DOrderService ffOrderService){
		this.finalOrderBeans = finalOrderBeans;
		this.fOrderService = fOrderService;
		this.ffOrderService = ffOrderService;
	}
	@Override
	public void run() {
		long curtime = System.currentTimeMillis();
		for(FinalOrderBean finalOrderBean:finalOrderBeans){
			try{
				OrderBean orderBean = ffOrderService.moveOrder(finalOrderBean);
				if(orderBean!=null && orderBean.getOrderNo()!=null){
					log.info(orderBean.getOrderNo()+"迁移成功");
				}
			}catch(Exception e){
				log.error("订单 ["+finalOrderBean.getOrdersn()+"] 迁移错误",e);
			}
		}
		log.info("迁移["+finalOrderBeans.size()+"]条 订单耗时["+((System.currentTimeMillis()-curtime)/(1000*60))+"]分钟");
	}
}
