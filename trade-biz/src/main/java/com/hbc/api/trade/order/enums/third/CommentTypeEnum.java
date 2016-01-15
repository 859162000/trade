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
package com.hbc.api.trade.order.enums.third;

import com.hbc.api.trade.order.enums.TradeReturnCodeEnum;
import com.hbc.api.trade.order.exception.TradeException;

/**
 * 1.-客人评价导游；2-运营评价导游
 * @author Jongly Ran
 */
public enum CommentTypeEnum {
	FROM_CUSTOMER(1, "客人评价导游"), FROM_CUSTOMER_SERVICE(2, "运营评价导游");

	public Integer value;
	public String name;
	CommentTypeEnum(int value,String name){
		this.value = value;
		this.name = name;
	}
	
	public static CommentTypeEnum getType(Integer value){
		CommentTypeEnum[] carTypeEnums = CommentTypeEnum.values();
		for(CommentTypeEnum commentTypeEnum : carTypeEnums){
			if(commentTypeEnum.value.equals(value)){
				return commentTypeEnum;
			}
		}
		throw new TradeException(TradeReturnCodeEnum.ORDER_CARTYPE_NOSUPPORT,value);
	}
}
