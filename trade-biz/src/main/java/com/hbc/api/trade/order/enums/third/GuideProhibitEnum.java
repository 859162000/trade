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
 * 
 *  
 */
package com.hbc.api.trade.order.enums.third;


/**
 * @author colin
 *
 */
public enum GuideProhibitEnum {
	PROHIBIT_TYPE_PUSH(1, "订单推送"), PROHIBIT_TYPE_RECEIVE(2, "接单"), PROHIBIT_TYPE_WITHDRAW(3, "提现"), PROHIBIT_TYPE_LOGIN(
			4, "登录");

	public Integer value;
	public String name;
	GuideProhibitEnum(int value,String name){
		this.value = value;
		this.name = name;
	}
	
	public static GuideProhibitEnum getType(Integer value){
		GuideProhibitEnum[] carTypeEnums = GuideProhibitEnum.values();
		for(GuideProhibitEnum carTypeEnum : carTypeEnums){
			if(carTypeEnum.value.equals(value)){
				return carTypeEnum;
			}
		}
		return null;
	}
}
