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
package com.hbc.api.trade.ota.resp;

/**
 * @author Jongly Ran
 */
public class OrderCancelResult {
	private String orderNo;			// HBC 订单号
	private String thirdTradeNo;	// 三方订单号
	private Double refundable;		// 可退金额
	private Double cancelFee;		// 退款时应扣除已消费的费用
	
	/**
	 * @return the orderNo
	 */
	public String getOrderNo() {
		return orderNo;
	}
	/**
	 * @param orderNo the orderNo to set
	 */
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	/**
	 * @return the thirdTradeNo
	 */
	public String getThirdTradeNo() {
		return thirdTradeNo;
	}
	/**
	 * @param thirdTradeNo the thirdTradeNo to set
	 */
	public void setThirdTradeNo(String thirdTradeNo) {
		this.thirdTradeNo = thirdTradeNo;
	}
	/**
	 * @return the refundable
	 */
	public Double getRefundable() {
		return refundable;
	}
	/**
	 * @param refundable the refundable to set
	 */
	public void setRefundable(Double refundable) {
		this.refundable = refundable;
	}
	/**
	 * @return the cancelFee
	 */
	public Double getCancelFee() {
		return cancelFee;
	}
	/**
	 * @param cancelFee the cancelFee to set
	 */
	public void setCancelFee(Double cancelFee) {
		this.cancelFee = cancelFee;
	}
}	
