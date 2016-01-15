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
public class PriceInfo {

	private Double orderPrice = 0.0d; // 总价
	private Double shouldPay = 0.0d; // 应付
	private Double actualPay = 0.0d; // 实付
	private Double checkInPrice; // 帮办理登机手续
	private Double refundPrice = 0.0d; // 退款金额
	private Double refundablePrice= 0.0d; // 可退款金额
	private Double cancelFee = 0.0d; //退改费 (实付 - 可退款金额)

	/**
	 * @return the refundPrice
	 */
	public Double getRefundPrice() {
		return refundPrice;
	}

	/**
	 * @param refundPrice
	 *            the refundPrice to set
	 */
	public void setRefundPrice(Double refundPrice) {
		this.refundPrice = refundPrice;
	}

	/**
	 * @return the refundablePrice
	 */
	public Double getRefundablePrice() {
		return refundablePrice;
	}

	/**
	 * @param refundablePrice
	 *            the refundablePrice to set
	 */
	public void setRefundablePrice(Double refundablePrice) {
		this.refundablePrice = refundablePrice;
	}

	/**
	 * @return the checkInPrice
	 */
	public Double getCheckInPrice() {
		return checkInPrice;
	}

	/**
	 * @param checkInPrice
	 *            the checkInPrice to set
	 */
	public void setCheckInPrice(Double checkInPrice) {
		this.checkInPrice = checkInPrice;
	}

	/**
	 * @return 总价
	 */
	public Double getOrderPrice() {
		return orderPrice;
	}

	/**
	 * @param orderPrice
	 *            总价
	 */
	public void setOrderPrice(Double orderPrice) {
		this.orderPrice = orderPrice;
	}

	/**
	 * @return 应付
	 */
	public Double getShouldPay() {
		return shouldPay;
	}

	/**
	 * @param shouldPay
	 *            应付
	 */
	public void setShouldPay(Double shouldPay) {
		this.shouldPay = shouldPay;
	}

	/**
	 * @return 实付
	 */
	public Double getActualPay() {
		return actualPay;
	}

	/**
	 * @param actualPay
	 *            实付
	 */
	public void setActualPay(Double actualPay) {
		this.actualPay = actualPay;
	}

	public Double getCancelFee() {
		return cancelFee;
	}

	public void setCancelFee(Double cancelFee) {
		this.cancelFee = cancelFee;
	}

	@Override
	public String toString() {
		return "PriceInfo [orderPrice=" + orderPrice + ", shouldPay=" + shouldPay + ", actualPay=" + actualPay + ", checkInPrice=" + checkInPrice + ", refundPrice=" + refundPrice + ", refundablePrice=" + refundablePrice + ", cancelFee=" + cancelFee
				+ "]";
	}

}
