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
package com.hbc.api.trade.order.service.rsp;

/**
 * @author Jongly Ran
 */
public class Coupon {
	private String  couponId;		// 优惠券ID
	private Double  actualPrice;	// 实际支付金额，与主订单一致
	private String  priceInfo;		// 折扣比例。couponType=1时有值
	/**
	 * @return the couponId
	 */
	public String getCouponId() {
		return couponId;
	}
	/**
	 * @param couponId the couponId to set
	 */
	public void setCouponId(String couponId) {
		this.couponId = couponId;
	}
	/**
	 * @return the actualPrice
	 */
	public Double getActualPrice() {
		return actualPrice;
	}
	/**
	 * @param actualPrice the actualPrice to set
	 */
	public void setActualPrice(Double actualPrice) {
		this.actualPrice = actualPrice;
	}
	/**
	 * @return the priceInfo
	 */
	public String getPriceInfo() {
		return priceInfo;
	}
	/**
	 * @param priceInfo the priceInfo to set
	 */
	public void setPriceInfo(String priceInfo) {
		this.priceInfo = priceInfo;
	}
	
}
