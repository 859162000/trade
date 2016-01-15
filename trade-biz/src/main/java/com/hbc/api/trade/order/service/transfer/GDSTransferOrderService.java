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
package com.hbc.api.trade.order.service.transfer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hbc.api.trade.order.enums.order.OrderSource;
import com.hbc.api.trade.order.mapping.gen.bean.OrderBean;
import com.hbc.api.trade.third.restful.UCenterService;

/**
 * GDS 送机
 * @author Jongly Ran
 */
@Service
public class GDSTransferOrderService extends AbstractTransferOrderService {

	@Autowired private UCenterService 		ucenterService;
	
	/* (non-Javadoc)
	 * @see com.hbc.api.trade.order.service.transfer.AbstractTransferOrderService#appendParams(com.hbc.api.trade.order.mapping.gen.bean.OrderBean, java.lang.Object)
	 */
	@Override
	protected <T> void appendParams(OrderBean orderBean, T param) {
		super.appendParams(orderBean, param);
		orderBean.setOrderSource(OrderSource.GDS.value);
		orderBean.setUserId(ucenterService.obtainOrGenerateUserIdForGDS(orderBean.getUserAreaCode1(), orderBean.getUserMobile1(), orderBean.getAgentId(), orderBean.getAgentName()));
	}

}
