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
package com.hbc.api.trade.ota.qunar.req;

import java.util.List;

/**
 * @author Jongly Ran
 */
public class QunarOrderListParam extends QunarBaseParam{
	List<QuanrOrderListDetail> orderInfo;
	private String queryTime; 			//	是	查询起始时间	String	“2014-09-01 15:15:00”
	private Long queryTimeMs; 			//	是	查询起始时间（毫秒）	Long	1399887903000

	/**
	 * @return the orderInfo
	 */
	public List<QuanrOrderListDetail> getOrderInfo() {
		return orderInfo;
	}
	/**
	 * @param orderInfo the orderInfo to set
	 */
	public void setOrderInfo(List<QuanrOrderListDetail> orderInfo) {
		this.orderInfo = orderInfo;
	}
	/**
	 * @return the queryTime
	 */
	public String getQueryTime() {
		return queryTime;
	}
	/**
	 * @param queryTime the queryTime to set
	 */
	public void setQueryTime(String queryTime) {
		this.queryTime = queryTime;
	}
	/**
	 * @return the queryTimeMs
	 */
	public Long getQueryTimeMs() {
		return queryTimeMs;
	}
	/**
	 * @param queryTimeMs the queryTimeMs to set
	 */
	public void setQueryTimeMs(Long queryTimeMs) {
		this.queryTimeMs = queryTimeMs;
	}

}
