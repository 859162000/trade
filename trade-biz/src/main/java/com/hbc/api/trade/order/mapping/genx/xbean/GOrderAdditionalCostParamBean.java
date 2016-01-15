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
package com.hbc.api.trade.order.mapping.genx.xbean;

import java.util.List;

/**
 * @author Jongly Ran
 */
public class GOrderAdditionalCostParamBean {

	private String orderNo; 				// 订单号
	private Double applyPrice; 				// 导游申请的费用总金额
	private List<AdditionalCostDetail> additionalCostList;
	
	/**
	 * @return 导游申请的费用总金额
	 */
	public Double getApplyPrice() {
		return applyPrice;
	}
	/**
	 * @param applyPrice 导游申请的费用总金额
	 */
	public void setApplyPrice(Double applyPrice) {
		this.applyPrice = applyPrice;
	}
	/**
	 * @return the additionalCostList
	 */
	public List<AdditionalCostDetail> getAdditionalCostList() {
		return additionalCostList;
	}
	/**
	 * @param additionalCostList the additionalCostList to set
	 */
	public void setAdditionalCostList(List<AdditionalCostDetail> additionalCostList) {
		this.additionalCostList = additionalCostList;
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
}
