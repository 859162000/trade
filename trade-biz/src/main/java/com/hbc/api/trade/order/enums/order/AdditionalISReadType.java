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

/**
 * @author Jongly Ran
 */
public enum AdditionalISReadType {
	/** 0: 未读 */
	UNREAD(0, "未读"), 
	
	/** 1: 已读 */
	READ(1,"已读"); 	
	
	public int value;
	public String name;
	
	AdditionalISReadType(int value,String name){
		this.value = value;
		this.name = name;
	}
}
