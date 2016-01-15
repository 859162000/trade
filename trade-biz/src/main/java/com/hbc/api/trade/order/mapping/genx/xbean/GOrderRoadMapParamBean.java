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

/**
 * @author Jongly Ran
 */
public class GOrderRoadMapParamBean {
	
	private String orderNo; 	// 订单ID
	private String gpsPoints; 	// 坐标点集合：x1,y1:x2,y2
	private String startTime; 	// 开始记录GPS时间
	private String endTime; 	// 结束记录GPS时间
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
	 * @return 坐标点集合：x1,y1:x2,y2
	 */
	public String getGpsPoints() {
		return gpsPoints;
	}
	/**
	 * @param gpsPoints 坐标点集合：x1,y1:x2,y2
	 */
	public void setGpsPoints(String gpsPoints) {
		this.gpsPoints = gpsPoints;
	}
	/**
	 * @return 开始记录GPS时间
	 */
	public String getStartTime() {
		return startTime;
	}
	/**
	 * @param startTime 开始记录GPS时间
	 */
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	/**
	 * @return 结束记录GPS时间
	 */
	public String getEndTime() {
		return endTime;
	}
	/**
	 * @param endTime 结束记录GPS时间
	 */
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
}
