/*
 * Copyright (c) 2015-2016, CCLX.COM. All rights reserved.
 * WANDA GROUP PROPRIETARY/CONFIDENTIAL. 
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is private property; you can't redistribute it and/or modify it
 * under the terms of the LICENSE you obtained from
 *
 *    http://www.cclx.com/
 * 
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. 
 *
 * Author: Jongly Ran
 * Revision: 1.0
 */
package com.hbc.api.trade.order.service;

import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hbc.api.trade.bdata.common.util.TimeConverter;
import com.hbc.api.trade.order.enums.order.OrderStatus;
import com.hbc.api.trade.order.mapping.gen.bean.TradeAdditionalCost;
import com.hbc.api.trade.order.mapping.genx.OrderBeanMapperEnhance;
import com.hbc.api.trade.order.mapping.genx.xbean.MISAdditionalCostQueryBean;
import com.hbc.api.trade.order.mapping.genx.xbean.MISOrderQueryBean;
import com.hbc.api.trade.order.mapping.genx.xbean.MISOrderResultBean;

/**
 * @author Jongly Ran
 */
@Service
public class MISOrderQueryService {
	@Autowired private OrderBeanMapperEnhance orderBeanMapperEnhance;
	@Autowired private AdditionalCostService  additionalCostService;
	
	/**
	 * 查询一小时未接单的数据
	 * @param orderBeanParam
	 */
	private void conditionForMoreThanOneHour(MISOrderQueryBean orderBeanParam) {
		if(orderBeanParam.getHasMoreThanOneHour() != null && orderBeanParam.getHasMoreThanOneHour()) {
			List<Integer> orderStatusList = new LinkedList<>();
			orderStatusList.add(OrderStatus.PAYSUCCESS.value);
			orderBeanParam.setOrderStatusList(orderStatusList);
			orderBeanParam.setGuideId("0");
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new Date());
			calendar.add(Calendar.HOUR, -1);
			orderBeanParam.setPayTime(TimeConverter.formatDate(calendar.getTime()));
		}
	}

	/**
	 * 查询航班未注册的数据
	 * @param orderBeanParam
	 * @return
	 */
	private Boolean hasNotRegistered(MISOrderQueryBean orderBeanParam) {
		return orderBeanParam.getHasNotRegistered() != null && !orderBeanParam.getHasNotRegistered() ? true : null;
	}
	
	public List<MISOrderResultBean> queryOrders(MISOrderQueryBean orderBeanParam) {
		conditionForMoreThanOneHour(orderBeanParam);
		orderBeanParam.setHasNotRegistered(hasNotRegistered(orderBeanParam));
		return orderBeanMapperEnhance.selectOrderForMIS(orderBeanParam);
	}

	public int queryOrdersTotalSize(MISOrderQueryBean orderBeanParam){
		conditionForMoreThanOneHour(orderBeanParam);
		orderBeanParam.setHasNotRegistered(hasNotRegistered(orderBeanParam));
		return orderBeanMapperEnhance.selectOrderForMISTotalSize(orderBeanParam);
	}
	
	/**
	 * 查询有增项费用的订单
	 * @param orderBeanParam
	 * @return
	 */
	public List<MISOrderResultBean> queryAdditionalCostOrders(MISOrderQueryBean orderBeanParam) {
		conditionForAdditionalCost(orderBeanParam);
		return orderBeanMapperEnhance.selectOrderForMIS(orderBeanParam);
	}
	
	public int queryAdditionalCostOrdersTotalSize(MISOrderQueryBean orderBeanParam) {
		conditionForAdditionalCost(orderBeanParam);
		return orderBeanMapperEnhance.selectOrderForMISTotalSize(orderBeanParam);
	}
	
	public List<MISOrderResultBean> queryOrdersByUserId(MISOrderQueryBean orderBeanParam) {
		return orderBeanMapperEnhance.selectOrderForMISByUserId(orderBeanParam);
	}
	
	public int queryOrdersByUserIdTotalSize(MISOrderQueryBean orderBeanParam) {
		return orderBeanMapperEnhance.selectOrderForMISByUserIdTotalSize(orderBeanParam);
	}
	
	/**
	 * @param orderBeanParam
	 */
	private void conditionForAdditionalCost(MISOrderQueryBean orderBeanParam) {
		/* ------- 查询有增项费用的订单 ------- */
		MISAdditionalCostQueryBean queryBean = new MISAdditionalCostQueryBean();
		queryBean.setLimit(orderBeanParam.getLimit());
		queryBean.setOffset(orderBeanParam.getOffset());
		List<TradeAdditionalCost> additionCostList = additionalCostService.getAdditionalCostList(queryBean );
		List<String> orderNoList = null;
		if(additionCostList != null && additionCostList.size() > 0) {
			orderNoList = new LinkedList<>();
			for(TradeAdditionalCost cost : additionCostList) {
				orderNoList.add(cost.getOrderNo());
			}
			orderBeanParam.setOrderNoList(orderNoList);
		}
	}
	
	public List<MISOrderResultBean> queryAlreayAgrredOrder(MISOrderQueryBean orderBeanParam) {
		return orderBeanMapperEnhance.queryAlreayAgrredOrder(orderBeanParam);
	}
	
	public int queryAlreayAgrredOrderTotalSize(MISOrderQueryBean orderBeanParam) {
		return orderBeanMapperEnhance.queryAlreayAgrredOrderTotalSize(orderBeanParam);
	}

	public List<MISOrderResultBean> getUnbalancedOrders(MISOrderQueryBean orderBeanParam) {
		return orderBeanMapperEnhance.getUnbalancedOrders(orderBeanParam);
	}

	public MISOrderResultBean queryOrderByOrderNo(MISOrderQueryBean orderBeanParam) {
		return orderBeanMapperEnhance.selectOrderForMISByOrderNo(orderBeanParam);
	}
}
