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

/**
 * 每个接口ErrorCode都不一样，目前未适配错误信息，将来可以考虑
 * 
 * @author Jongly Ran
 */
public enum CTripErrorCode {
	OK("成功"), 
	
	ERROR("其他错误信息"),
	
	ERROR_PRICEMARK("价格标示符号已经使用，不可以重复使用。"),
	
	ERROR_CANNOT("过了用车时间不可以取消"),

	ERROR_TIMEOUT("超时不接受信息。"),
	ERROR_CANCELED("订单已经取消"),
	ERROR_STATUSERR("订单已经完成，不可以修改信息。"),
	;
	
	public String value;
	
	private CTripErrorCode(String value) {
		this.value = value;
	}
}
