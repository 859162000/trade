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
package com.hbc.api.trade.order.controller.query.vo;

/**
 * @author Jongly Ran
 */
public class MISCancelOrderDetailBean {
	private Double priceChannel; 	// 渠道价
	private Double orderPrice; 		// 订单价
	private Double actualPay; 		// 实付
	private Double couponPrice;		// 优惠券
	private Double guidePrice;		// 导游价
	/**
	 * @return the priceChannel
	 */
	public Double getPriceChannel() {
		return priceChannel;
	}
	/**
	 * @param priceChannel the priceChannel to set
	 */
	public void setPriceChannel(Double priceChannel) {
		this.priceChannel = priceChannel;
	}
	/**
	 * @return the orderPrice
	 */
	public Double getOrderPrice() {
		return orderPrice;
	}
	/**
	 * @param orderPrice the orderPrice to set
	 */
	public void setOrderPrice(Double orderPrice) {
		this.orderPrice = orderPrice;
	}
	/**
	 * @return the actualPay
	 */
	public Double getActualPay() {
		return actualPay;
	}
	/**
	 * @param actualPay the actualPay to set
	 */
	public void setActualPay(Double actualPay) {
		this.actualPay = actualPay;
	}
	/**
	 * @return the couponPrice
	 */
	public Double getCouponPrice() {
		return couponPrice;
	}
	/**
	 * @param couponPrice the couponPrice to set
	 */
	public void setCouponPrice(Double couponPrice) {
		this.couponPrice = couponPrice;
	}
	/**
	 * @return the guidePrice
	 */
	public Double getGuidePrice() {
		return guidePrice;
	}
	/**
	 * @param guidePrice the guidePrice to set
	 */
	public void setGuidePrice(Double guidePrice) {
		this.guidePrice = guidePrice;
	}
}
