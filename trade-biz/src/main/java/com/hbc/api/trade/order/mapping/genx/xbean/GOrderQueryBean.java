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
public class GOrderQueryBean extends AbstractParameter{
	private String 			guideId;
	private List<Integer> 	orderStatus;
	private Integer 		deliverStatus;
	
	/*
	 * GDS查询条件searchType只能是[1:等待支付,2:等待服务,3:服务中,4:已完成,5:已取消,6:全部]
	 * GAPP查询条件searchType只能是[1:待完成,2:已完成,3:被取消]
	 * 地接社查询条件searchType只能是[2:服务未开始,3:服务中,4:已完成,5:已取消,6:全部]
	 */
	private Integer 		searchType; 
	private String			orderNo;
	
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
	 * @return the searchType
	 */
	public Integer getSearchType() {
		return searchType;
	}
	/**
	 * @param searchType the searchType to set
	 */
	public void setSearchType(Integer searchType) {
		this.searchType = searchType;
	}
	
	/**
	 * @return the guideId
	 */
	public String getGuideId() {
		return guideId;
	}
	/**
	 * @param guideId the guideId to set
	 */
	public void setGuideId(String guideId) {
		this.guideId = guideId;
	}
	/**
	 * @return the orderStatus
	 */
	public List<Integer> getOrderStatus() {
		return orderStatus;
	}
	/**
	 * @param orderStatus the orderStatus to set
	 */
	public void setOrderStatus(List<Integer> orderStatus) {
		this.orderStatus = orderStatus;
	}
	/**
	 * @return the deliverStatus
	 */
	public Integer getDeliverStatus() {
		return deliverStatus;
	}
	/**
	 * @param deliverStatus the deliverStatus to set
	 */
	public void setDeliverStatus(Integer deliverStatus) {
		this.deliverStatus = deliverStatus;
	}
	
}
