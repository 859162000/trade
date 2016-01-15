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
package com.hbc.api.trade.ota.qua.req;

/**
 * 一张优惠券 <br/>
 * 2015-12-31
 * @author Jongly Ran
 * @version v1.0.2 
 */
public class PromotionInfo {
	//DEMO: {\"couponValue\":1500,\"id\":\"1004028_1004002_20151223160957870355582721c5b6NC\",\"name\":\"皇包车用车券测试\"}
	
	private Double couponValue;
	private String id;
	private String name;
	/**
	 * @return the couponValue
	 */
	public Double getCouponValue() {
		return couponValue;
	}
	/**
	 * @param couponValue the couponValue to set
	 */
	public void setCouponValue(Double couponValue) {
		this.couponValue = couponValue;
	}
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
}
