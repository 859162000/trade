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

import com.hbc.api.trade.order.enums.order.OrderStatus;
import com.hbc.api.trade.order.mapping.gen.bean.OrderBean;

/**
 * @author Jongly Ran
 */
public class AbstractOTATransferOrderService extends AbstractTransferOrderService {

	/* (non-Javadoc)
	 * @see com.hbc.api.trade.order.service.transfer.AbstractTransferOrderService#appendParams(com.hbc.api.trade.order.mapping.gen.bean.OrderBean, java.lang.Object)
	 */
	@Override
	protected <T> void appendParams(OrderBean orderBean, T param) {
		super.appendParams(orderBean, param);
		orderBean.setOrderStatus(OrderStatus.PAYSUCCESS.value);
		orderBean.setOrderStatusName(OrderStatus.PAYSUCCESS.name);
	}

}
