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
package com.hbc.api.trade.ota.resp.qua;

import java.util.List;

import com.hbc.api.trade.ota.resp.OrderPriceInfoQuoteVo;

/**
 * @author Jongly Ran
 */
public class QuaOrderPriceInfo {

	private List<OrderPriceInfoQuoteVo> pricelist;
	private Double distance; 
	private String pricemark; // 价格标记（用于三方适配，HBC标准是在子JSON）
	private Integer expectedCompTime; // 适配三方
	
	/**
	 * @return the pricelist
	 */
	public List<OrderPriceInfoQuoteVo> getPricelist() {
		return pricelist;
	}
	/**
	 * @param pricelist the pricelist to set
	 */
	public void setPricelist(List<OrderPriceInfoQuoteVo> pricelist) {
		this.pricelist = pricelist;
	}
	/**
	 * @return the distance
	 */
	public Double getDistance() {
		return distance;
	}
	/**
	 * @param distance the distance to set
	 */
	public void setDistance(Double distance) {
		this.distance = distance;
	}
	/**
	 * @return the pricemark
	 */
	public String getPricemark() {
		return pricemark;
	}
	/**
	 * @param pricemark the pricemark to set
	 */
	public void setPricemark(String pricemark) {
		this.pricemark = pricemark;
	}
	/**
	 * @return the expectedCompTime
	 */
	public Integer getExpectedCompTime() {
		return expectedCompTime;
	}
	/**
	 * @param expectedCompTime the expectedCompTime to set
	 */
	public void setExpectedCompTime(Integer expectedCompTime) {
		this.expectedCompTime = expectedCompTime;
	}
}
