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
package com.hbc.api.trade.ota.resp.qunar;

/**
 * @author Jongly Ran
 */
public class QunarCalculatePriceResult extends QunarResult {
	private Integer distance; 			// 起始地点之间的距离	Integer	37000
	private Integer estimatedTime; 		//	估算的到大时间（分钟）	Integer	56
	private String  currency; 			//	货币种类	String	“CNY”
	public QunarPriceDetail price; 	//	每种车型的价格	JsonArray	
	
	private String  pricemark;			// TODO 冉孟萍，与去哪儿对
	
	public QunarCalculatePriceResult() {
		super();
	}

	public QunarCalculatePriceResult(int code, String errorMsg) {
		super(code, errorMsg);
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
	 * @return the distance
	 */
	public Integer getDistance() {
		return distance;
	}

	/**
	 * @param distance the distance to set
	 */
	public void setDistance(Integer distance) {
		this.distance = distance;
	}

	/**
	 * @return the estimatedTime
	 */
	public Integer getEstimatedTime() {
		return estimatedTime;
	}

	/**
	 * @param estimatedTime the estimatedTime to set
	 */
	public void setEstimatedTime(Integer estimatedTime) {
		this.estimatedTime = estimatedTime;
	}

	/**
	 * @return the currency
	 */
	public String getCurrency() {
		return currency;
	}

	/**
	 * @param currency the currency to set
	 */
	public void setCurrency(String currency) {
		this.currency = currency;
	}
}
