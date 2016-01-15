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

import java.util.Date;

/**
 * @author Jongly Ran
 */
public class TradeDeliverGuideParamBean{
	private String 	guideId;
	private Integer demandDeal;
	private String 	orderNo;
	private String 	allocateGid; 	/*发单ID*/
	private String 	refuseReason; 	/*拒绝理由*/
	
	private Date  	acceptTime;
	private Date  	updateTime;
	private Integer isRead;
	
	private Date firstTime;
	
	public Date getFirstTime() {
		return firstTime;
	}

	public void setFirstTime(Date firstTime) {
		this.firstTime = firstTime;
	}

	/**
	 * @return the acceptTime
	 */
	public Date getAcceptTime() {
		return acceptTime;
	}

	/**
	 * @param acceptTime the acceptTime to set
	 */
	public void setAcceptTime(Date acceptTime) {
		this.acceptTime = acceptTime;
	}

	/**
	 * @return the updateTime
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * @param updateTime the updateTime to set
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * @return the isRead
	 */
	public Integer getIsRead() {
		return isRead;
	}

	/**
	 * @param isRead the isRead to set
	 */
	public void setIsRead(Integer isRead) {
		this.isRead = isRead;
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
	 * @return the allocateGid
	 */
	public String getAllocateGid() {
		return allocateGid;
	}

	/**
	 * @param allocateGid the allocateGid to set
	 */
	public void setAllocateGid(String allocateGid) {
		this.allocateGid = allocateGid;
	}

	/**
	 * @return the refuseReason
	 */
	public String getRefuseReason() {
		return refuseReason;
	}

	/**
	 * @param refuseReason the refuseReason to set
	 */
	public void setRefuseReason(String refuseReason) {
		this.refuseReason = refuseReason;
	}

	/**
	 * @return the demandDeal
	 */
	public Integer getDemandDeal() {
		return demandDeal;
	}

	/**
	 * @param demandDeal the demandDeal to set
	 */
	public void setDemandDeal(Integer demandDeal) {
		this.demandDeal = demandDeal;
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
}
