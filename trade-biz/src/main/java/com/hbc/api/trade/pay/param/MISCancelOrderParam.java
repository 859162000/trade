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
package com.hbc.api.trade.pay.param;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

/**
 * @author Jongly Ran
 */
public class MISCancelOrderParam {
	@NotBlank(message="订单编号不能为空")
	private String orderNo;
	
	@NotNull(message="系统退还金额不能为空")
	private Double systemPrice; 	// 系统退还（订单价-导游收益-退还客人）

	@NotNull(message="退还客人金额不能为空")
	private Double backToCustomer; 	// 退还客人（渠道）

	@NotNull(message="导游收益不能为空")
	private Double guideIncome; 	// 导游收益

	@NotNull(message="处理记录不能为空")
	private String content; 		// 处理记录（仅客服可见）
	
	private String opUserId;
	private String opUserName;
	
	public String getOpUserId() {
		return opUserId;
	}
	public void setOpUserId(String opUserId) {
		this.opUserId = opUserId;
	}
	public String getOpUserName() {
		return opUserName;
	}
	public void setOpUserName(String opUserName) {
		this.opUserName = opUserName;
	}
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
	 * @return the systemPrice
	 */
	public Double getSystemPrice() {
		return systemPrice;
	}
	/**
	 * @param systemPrice the systemPrice to set
	 */
	public void setSystemPrice(Double systemPrice) {
		this.systemPrice = systemPrice;
	}
	/**
	 * @return the backToCustomer
	 */
	public Double getBackToCustomer() {
		return backToCustomer;
	}
	/**
	 * @param backToCustomer the backToCustomer to set
	 */
	public void setBackToCustomer(Double backToCustomer) {
		this.backToCustomer = backToCustomer;
	}
	/**
	 * @return the guideIncome
	 */
	public Double getGuideIncome() {
		return guideIncome;
	}
	/**
	 * @param guideIncome the guideIncome to set
	 */
	public void setGuideIncome(Double guideIncome) {
		this.guideIncome = guideIncome;
	}
	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}
	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}
}
