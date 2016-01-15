/**
 * @Author lukangle
 * @2015年12月20日@上午11:10:43
 */
package com.hbc.data.trade.transfer.thread;

import java.util.List;

import org.springframework.stereotype.Component;

import com.hbc.data.trade.transfer.mapping.hbcfinal.gen.bean.FinalOrderBean;
import com.hbc.data.trade.transfer.service.MoveDataCheckService;

public class OrderCheckThread implements Runnable{
	List<FinalOrderBean> finalOrderBeans;
	MoveDataCheckService moveDataCheckService;
	public OrderCheckThread(List<FinalOrderBean> finalOrderBeans,MoveDataCheckService moveDataCheckService){
		this.finalOrderBeans  = finalOrderBeans;
		this.moveDataCheckService = moveDataCheckService;
	}
	@Override
	public void run() {
		moveDataCheckService.startToCheck(finalOrderBeans);
	}

}
