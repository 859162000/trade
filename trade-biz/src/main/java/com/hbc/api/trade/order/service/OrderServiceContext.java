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

/**
 * @author Jongly Ran
 */
public class OrderServiceContext {
	private AbstractOrderService abstractOrderService;
	
	public OrderServiceContext(AbstractOrderService abstractOrderService) {
		this.abstractOrderService = abstractOrderService;
	}
	
	public <T> void createOrder(T param) {
		abstractOrderService.createNewOrder(param);
	}
	
	public <T> void updateOrder(T param) {
		abstractOrderService.updateOrder(param);
	}
}
