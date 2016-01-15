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
package com.hbc.api.trade.order.enums.order;

import com.hbc.api.trade.order.enums.TradeReturnCodeEnum;
import com.hbc.api.trade.order.exception.TradeException;

/**
 * 对应trade_order
 * @author Jongly Ran
 */
public enum FlightIsCustom {
	NORMAL(0, "正常"), CUSTOM(1, "自定义");
	

	public Integer value;
	public String name;
	
	FlightIsCustom(int value,String name){
		this.value = value;
		this.name = name;
	}
	
	public static FlightIsCustom getItem(Integer value) {
		if(value != null) {
			for(FlightIsCustom one : FlightIsCustom.values()) {
				if(one.ordinal() == value.intValue()) {
					return one;
				}
			}
		}
		throw new TradeException(TradeReturnCodeEnum.ORDER_PARAM_FAILED, "是否自定义航班号");
	}
}
