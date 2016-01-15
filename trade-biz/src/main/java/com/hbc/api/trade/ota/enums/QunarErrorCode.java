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
 * TODO 目前未适配去哪的错误信息，全部以参数错误返回，我们有log支持，后期可考虑完全适配。
 * @author Jongly Ran
 */
public enum QunarErrorCode {
	
	SUCCESS(0,"成功"),
	SERVER_ERR(2,"服务器错误"),
	ERR_PARAM(3,"参数错误"),
	ERR_SERVICE_TIME(4,"预定时间早于当前时间"),
	CANNOT_CREATE_THE_SAME_ORDER(5,"不能重复下单"),
	NOT_SUPPORT(7,"城市不支持"),
	ORDER_NOT_FOUND(11,"订单不存在"),
	NOT_RESPONSED(10,"订单未被响应，不能完成"),
	ALREADY_DONE(22,"订单已完成，不能重复完成"),
	ALREADY_CANCELLED(24,"订单已经取消，不能重复取消"),
	CANNOT_BE_CANCELLED(25,"当前订单不能被取消"),
	ERR_SQL(26,"数据库错误"),
	ERR_SIGN(27,"sign验证错误"),
	ERR_PRICE(28,"金额不匹配"),
	;
	
	/*
	0	成功
	1	系统参数vendorkey错误
	2	服务器错误
	3	参数错误
	4	预定时间早于当前时间
	5	不能重复下单
	6	不能跨城市下单
	7	城市不支持
	8	此服务不可使用
	9	订单不存在
	10	订单未被响应，不能完成
	11	订单已响应或已完成，不能重复响应
	12	订单已完成，不能取消
	20	订单已被响应，不能重复响应
	21	当前订单不能被响应
	22	订单已完成，不能重复完成
	23	当前订单不能完成
	24	订单已经取消，不能重复取消
	25	当前订单不能被取消
	26	数据库错误
	27	sign验证错误
	28	金额不匹配
	29	地点更新成功
*/
	
	private Integer code = 0;
	private String value;
	
	private QunarErrorCode(Integer code, String value) {
		this.code = code;
		this.value = value;
	}
	
	public static boolean hasContains(Integer code) {
		for(QunarErrorCode qunarError : QunarErrorCode.values()) {
			if(qunarError.code.equals(code)) {
				return true;
			}
		}
		return false;
	}
	
	public Integer code() {
		return this.code;
	}
	
	public String value() {
		return this.value;
	}
	
}
