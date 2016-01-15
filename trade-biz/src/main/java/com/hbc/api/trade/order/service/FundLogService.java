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

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hbc.api.fund.account.mapping.gen.bean.FundAccount;
import com.hbc.api.fund.account.mapping.gen.bean.FundAccountLog;
import com.hbc.api.fund.account.parm.FundAccountLogParam;
import com.hbc.api.fund.account.service.FundAccountLogService;
import com.hbc.api.fund.account.service.FundAccountService;
import com.hbc.api.trade.bdata.common.util.DoubleUtil;
import com.hbc.api.trade.order.enums.order.OrderStatus;
import com.hbc.api.trade.order.mapping.gen.OrderBeanMapper;
import com.hbc.api.trade.order.mapping.gen.bean.OrderBean;
import com.hbc.api.trade.order.mapping.gen.bean.OrderBeanExample;

/**
 * @author Jongly Ran
 */
@Service
public class FundLogService {
	@Autowired private FundAccountLogService 	fundAccountLogService;
	@Autowired private OrderBeanMapper 			orderBeanMapper;
	@Autowired
	private FundAccountService fundAccountService;
	public Map<String, Object> getFundAccountLogForGDS(FundAccountLogParam param) {
		FundAccount fundAccountBean = fundAccountLogService.getPayAccountInfo(param);
		Double useableAmount 	= fundAccountBean.getAmount() == null ? 0.0 : fundAccountBean.getAmount();				// min 0.0
		Double allAmount		= useableAmount;
		Double frozenAmount 	= fundAccountBean.getFrozenAmount() == null ? 0.0 : fundAccountBean.getFrozenAmount();  // min 0.0
		Double unuseableAmount	= frozenAmount;
		Double totalAmount		= fundAccountBean.getTotalAmount() == null ? 0.0 : fundAccountBean.getTotalAmount();			
		
		// 账户流水
		int totalSize = fundAccountLogService.countAccountLogs(param);
		List<FundAccountLog> accountLogs = fundAccountLogService.getAccountLogs(param);
				
		Map<String, Object> data = new LinkedHashMap<>(6);
		data.put("totalWithdraw"	, totalAmount);
		data.put("totalAmount"		, allAmount);
		data.put("unuseableAmount"	, unuseableAmount);
		data.put("useableAmount"	, useableAmount);
		data.put("totalSize"		, totalSize);
		data.put("resultBean"		, accountLogs);
		return data;
	}
	
	public Double getFutureAmount(String accountNo){
		FundAccount fundAccount = fundAccountService.getFundAccount(accountNo);
		Double useableAmount 	= fundAccount.getAmount() == null ? 0.0 : fundAccount.getAmount();				// min 0.0
		Double allAmount		= useableAmount;
		OrderBeanExample example = new OrderBeanExample();
		List<Integer> orderStatusList = new LinkedList<>();
		orderStatusList.add(OrderStatus.PAYSUCCESS.value);
		orderStatusList.add(OrderStatus.GUIDE_ARRIVED.value);
		orderStatusList.add(OrderStatus.PICK_CUSTOMER.value);
		orderStatusList.add(OrderStatus.STROKE_END.value);
		orderStatusList.add(OrderStatus.CONFIRMED_COST.value);
		example.createCriteria().andGuideAccountNoEqualTo(accountNo).andOrderStatusIn(orderStatusList );
		List<OrderBean> servicingOrders = orderBeanMapper.selectByExample(example);
		if(servicingOrders != null && servicingOrders.size() > 0 ) {
			Double totalComingMoney = 0.0;
			for(OrderBean servicingOrder : servicingOrders) {
				totalComingMoney = DoubleUtil.addDouble(totalComingMoney, servicingOrder.getPriceGuide());
			}
			allAmount =  DoubleUtil.addDouble(useableAmount, totalComingMoney);;
		}
		return allAmount;
	}

	public Map<String, Object> getFundAccountLogForMIS(FundAccountLogParam param) {
		FundAccount fundAccountBean = fundAccountLogService.getPayAccountInfo(param);
		Double useableAmount 	= fundAccountBean.getAmount() == null ? 0.0 : fundAccountBean.getAmount();				// min 0.0
		Double allAmount		= useableAmount;
		//Double frozenAmount 	= fundAccountBean.getFrozenAmount() == null ? 0.0 : fundAccountBean.getFrozenAmount();  // min 0.0
		Double unuseableAmount = 0d;
		Double totalAmount		= fundAccountBean.getTotalAmount() == null ? 0.0 : fundAccountBean.getTotalAmount();			
		
		OrderBeanExample example = new OrderBeanExample();
		List<Integer> orderStatusList = new LinkedList<>();
		orderStatusList.add(OrderStatus.PAYSUCCESS.value);
		orderStatusList.add(OrderStatus.GUIDE_ARRIVED.value);
		orderStatusList.add(OrderStatus.PICK_CUSTOMER.value);
		orderStatusList.add(OrderStatus.STROKE_END.value);
		orderStatusList.add(OrderStatus.CONFIRMED_COST.value);
		example.createCriteria().andGuideIdEqualTo(param.getGuideId()).andOrderStatusIn(orderStatusList );
		List<OrderBean> servicingOrders = orderBeanMapper.selectByExample(example);

		if(servicingOrders != null && servicingOrders.size() > 0 ) {
			Double totalComingMoney = 0.0;
			for(OrderBean servicingOrder : servicingOrders) {
				totalComingMoney += servicingOrder.getPriceGuide();
			}
			allAmount =  DoubleUtil.addDouble(useableAmount, totalComingMoney);;
			unuseableAmount = totalComingMoney;
		}
		
		// 账户流水
		int totalSize = fundAccountLogService.countAccountLogs(param);
		List<FundAccountLog> accountLogs = fundAccountLogService.getAccountLogs(param);
		
		Map<String, Object> data = new LinkedHashMap<>(6);
		data.put("totalWithdraw", totalAmount); //总收入
		data.put("totalAmount", allAmount); //账户余额  //可提现+在途
		data.put("unuseableAmount", unuseableAmount);//不可提现 在途金额
		data.put("useableAmount", useableAmount); //可提现
		data.put("totalSize", totalSize);
		data.put("resultBean", accountLogs);
		return data;
	}
}
