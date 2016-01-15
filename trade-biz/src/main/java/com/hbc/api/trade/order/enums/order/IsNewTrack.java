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
 * trade_order
 * @author Jongly Ran
 */
public enum IsNewTrack {
	/** 0: 无 */
	NO(0,"无"),

	/** 1: 有 */
	YES(1,"有");
	
	public Integer value;
	
	public String name;
	
	IsNewTrack(Integer value,String name){
		this.value = value;
		this.name = name;
	}
	
	public static IsNewTrack getType(Integer type) {
		for( IsNewTrack visaType : values()) {
			if(visaType.value.equals(type))	return visaType;
		}
		throw new TradeException(TradeReturnCodeEnum.ORDER_PARAM_FAILED, "IsNewTrack");
	}
	
	/**
	 * 如果不存在，抛出TradeException
	 */
	public static void isValidate(Integer type) {
		getType(type);
	}
}
