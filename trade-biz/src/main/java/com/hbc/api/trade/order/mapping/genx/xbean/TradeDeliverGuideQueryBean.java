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
public class TradeDeliverGuideQueryBean extends AbstractParameter {
	private String guideId;
	private String guideNo;
	private String guideName;
	private String orderNo; // 查导游列表时用
	private Integer demandDeal;
	private Integer deliverStatus;
	private Integer deliverType;
	private List<Integer> deliverStatusList;
	private String searchInfo;

	private Integer tradeDeliverStatus;
	private Integer tradeDeliverGuideStatus;

	public String getGuideId() {
		return guideId;
	}

	public void setGuideId(String guideId) {
		this.guideId = guideId;
	}

	public String getGuideNo() {
		return guideNo;
	}

	public void setGuideNo(String guideNo) {
		this.guideNo = guideNo;
	}

	public String getGuideName() {
		return guideName;
	}

	public void setGuideName(String guideName) {
		this.guideName = guideName;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public Integer getDemandDeal() {
		return demandDeal;
	}

	public void setDemandDeal(Integer demandDeal) {
		this.demandDeal = demandDeal;
	}

	public Integer getDeliverStatus() {
		return deliverStatus;
	}

	public void setDeliverStatus(Integer deliverStatus) {
		this.deliverStatus = deliverStatus;
	}

	public Integer getDeliverType() {
		return deliverType;
	}

	public void setDeliverType(Integer deliverType) {
		this.deliverType = deliverType;
	}

	public List<Integer> getDeliverStatusList() {
		return deliverStatusList;
	}

	public void setDeliverStatusList(List<Integer> deliverStatusList) {
		this.deliverStatusList = deliverStatusList;
	}

	public String getSearchInfo() {
		return searchInfo;
	}

	public void setSearchInfo(String searchInfo) {
		this.searchInfo = searchInfo;
	}

	public Integer getTradeDeliverStatus() {
		return tradeDeliverStatus;
	}

	public void setTradeDeliverStatus(Integer tradeDeliverStatus) {
		this.tradeDeliverStatus = tradeDeliverStatus;
	}

	public Integer getTradeDeliverGuideStatus() {
		return tradeDeliverGuideStatus;
	}

	public void setTradeDeliverGuideStatus(Integer tradeDeliverGuideStatus) {
		this.tradeDeliverGuideStatus = tradeDeliverGuideStatus;
	}

	@Override
	public String toString() {
		return "TradeDeliverGuideQueryBean [guideId=" + guideId + ", guideNo=" + guideNo + ", guideName=" + guideName + ", orderNo=" + orderNo + ", demandDeal=" + demandDeal + ", deliverStatus=" + deliverStatus + ", deliverType=" + deliverType
				+ ", deliverStatusList=" + deliverStatusList + ", searchInfo=" + searchInfo + ", tradeDeliverStatus=" + tradeDeliverStatus + ", tradeDeliverGuideStatus=" + tradeDeliverGuideStatus + "]";
	}

}
