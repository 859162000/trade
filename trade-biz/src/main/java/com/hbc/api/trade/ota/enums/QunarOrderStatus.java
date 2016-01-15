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
package com.hbc.api.trade.ota.enums;

import com.hbc.api.trade.order.enums.TradeReturnCodeEnum;
import com.hbc.api.trade.order.exception.TradeException;

/**
 * @author Jongly Ran
 */
public enum QunarOrderStatus {
	/** 下单成功未被响应 */
	NO_RESPONSE(1,"下单成功未被响应"),

	/** 下单成功已被响应 */
	RESPONSED(2,"下单成功已被响应"),

	/** 用户取消 */
	CANCELLED_BY_USER(3,"用户取消"),

	/** 订单完成 */
	COMPLETE(4,"订单完成"),

	/** 司机取消 */
	CANCELLED_BY_DRIVER(5,"司机取消"),

	/** 客服取消 */
	CANCELLED_BY_SERVICE(6,"客服取消"),

	/** 去哪儿系统取消 */
	CANCELLED_BY_QUNAR(7,"去哪儿系统取消"),

	/** 价格最终确认 */
	DETERMINE_PRICE(8,"价格最终确认"),

	/** 乘客未出现 */
	NO_COMING(9,"乘客未出现"),

	/** 供应商取消 */
	CANCELLED_BY_SUPPLIER(10,"供应商取消");
	
	public int value;
	public String name;

	QunarOrderStatus(int value,String name){
		this.value = value;
		this.name = name;
	}
	
	public static QunarOrderStatus getStatus(int value) {
		QunarOrderStatus[] otypes = QunarOrderStatus.values();
		for (QunarOrderStatus thirdOrderStatus : otypes) {
			if (thirdOrderStatus.value==value) {
				return thirdOrderStatus;
			}
		}
		throw new TradeException(TradeReturnCodeEnum.ORDER_STATUS_TYPE, value);
	}
}
