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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hbc.api.trade.order.enums.order.OrderSource;
import com.hbc.api.trade.order.mapping.gen.bean.OrderBean;
import com.hbc.api.trade.third.restful.UCenterService;

/**
 * @author Jongly Ran
 */
@Service
public class GDSOptOrderService extends OrderService {
	@Autowired private UCenterService 		ucenterService;
	@Autowired private UpdateOrderService	updateOrderService;
	
	/* (non-Javadoc)
	 * @see com.hbc.api.trade.order.service.OrderService#addOrder(com.hbc.api.trade.order.mapping.gen.bean.OrderBean)
	 */
	@Override
	@Transactional
	public String addOrder(OrderBean orderBean) {
		orderBean.setOrderSource(OrderSource.GDS.value);
		orderBean.setUserId(ucenterService.obtainOrGenerateUserIdForGDS(orderBean.getUserAreaCode1(), orderBean.getUserMobile1(), orderBean.getAgentId(), orderBean.getAgentName()));
		return super.addOrder(orderBean);
	}

	@Transactional
	public void updateDailyOrder(OrderBean orderBean,String serviceRecTime) {
		updateOrderService.updateDailyOrder(orderBean, serviceRecTime);
	}
	
	@Transactional
	public void updatePickUpOrder(OrderBean orderBean) {
		updateOrderService.updatePickUpOrder(orderBean);
	}
	
	
	@Transactional
	public void updateSingleOrder(OrderBean orderBean) {
		updateOrderService.updateSingleOrder(orderBean);
	}
	
	@Transactional
	public void updateTransferOrder(OrderBean orderBean) {
		updateOrderService.updateTransferOrder(orderBean);
	}
}
