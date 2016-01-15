/**
 * @Author lukangle
 * @2015年12月28日@下午9:08:48
 */
package com.hbc.data.trade.transfer.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hbc.api.trade.order.enums.order.OrderStatus;
import com.hbc.api.trade.order.mapping.gen.OrderBeanMapper;
import com.hbc.api.trade.order.mapping.gen.bean.OrderBean;
import com.hbc.api.trade.order.mapping.gen.bean.OrderBeanExample;

@Component
public class OrderUpdateService {
	@Autowired
	OrderBeanMapper orderBeanMapper;

	public void update() {
		List<String> ilist = new ArrayList<>();
		ilist.add("1636034141");
		ilist.add("1636223915");
		ilist.add("1636182358");
		ilist.add("1636186745");
		ilist.add("1636178593");
		ilist.add("1636176202");
		ilist.add("1636156570");
		ilist.add("1636212143");
		ilist.add("1636243002");
		ilist.add("1636208257");
		OrderBeanExample orderBeanExample = new OrderBeanExample();
		orderBeanExample.createCriteria().andThirdTradeNoIn(ilist).andOrderStatusEqualTo(OrderStatus.CANCELING.value);
		List<OrderBean> olist =  orderBeanMapper.selectByExample(orderBeanExample);
		
		for(OrderBean orderBean :olist ){
			OrderBean record = new OrderBean();
			record.setOrderNo(orderBean.getOrderNo());
			record.setOrderStatus(OrderStatus.PAYSUCCESS.value);
			orderBeanMapper.updateByPrimaryKeySelective(record);
			
			System.out.println(orderBean.getOrderNo()+"已经由 【"+orderBean.getOrderStatus()+"】变更成【"+OrderStatus.PAYSUCCESS.value+"】");
		}
	}
}
