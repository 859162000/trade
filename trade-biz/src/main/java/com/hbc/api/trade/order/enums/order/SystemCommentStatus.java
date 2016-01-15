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
 * 系统评论状态  0 未评论 1已评论
 * @author Jongly Ran
 */
public enum SystemCommentStatus {
	/** 0: 未评论 */
	UNSCORED(0,"未评论"),

	/** 1: 已评论 */
	SCORED(1,"已评论");
	
	public Integer value;
	
	public String name;
	
	SystemCommentStatus(int value,String name){
		this.value = value;
		this.name = name;
	}
	
	public static SystemCommentStatus getType(Integer type) {
		if(type != null) {
			for( SystemCommentStatus userCommentStatus : values()) {
				if(userCommentStatus.value .equals( type))	return userCommentStatus;
			}
		}
		throw new TradeException(TradeReturnCodeEnum.ORDER_PARAM_FAILED, "用户评论状态");
	}
	
	/**
	 * 如果不存在，抛出TradeException
	 */
	public static void isValidate(int type) {
		getType(type);
	}
}
