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
 * 封装复杂查询条件
 * 
 * @author Jongly Ran
 */
public class MISOrderQueryBean extends AbstractParameter {
	private String serviceTimeBegin; // 服务时间，开始
	private String serviceTimeEnd; // 服务时间，结束
	private String orderStartDate; // 下单时间，开始
	private String orderEndDate; // 下单时间，结束

	private String orderNo; // 订单号
	private String thirdTradeNo;
	private Integer orderSource; // 销售渠道
	private Integer orderChannel; // (去哪儿、携程等) QUNAR(1948212164, "QUNA渠道"),CTRIP(1918029805, "携程渠道"),QUA(1909280330, "去啊渠道")
	private Integer orderType; // 订单类别，1-接机；2-送机；3-日租；4-次租

	private Integer deliverType; // 0：普通订单；1：急单
	private Integer deliverStatus; // 0 初始态 1 发单中 2 已发单 3 pk中 4 需要报警
	private Boolean hasClickedAllButton;// 是否点击了“全部”按钮
	private Boolean hasMoreThanOneHour; // 超过1小时未接单，true：超过；
	private Boolean hasNotRegistered; // 航班未注册，deliverStatus = 4， flightRegisterId is null。 true：未注册；
	private String payTime; // 超时条件：当前时间 - 1h > 支付时间，TRUE为超时；

	private List<Integer> orderStatusList; // 多个订单状态集合
	private Integer searchType; // 1:急待处理,2:等待支付,3:发包中,4:导游已确认,5:服务中,6:已完成,7:已取消,8:全部
	private Integer urgentFlag; // 是否急单，0否，1是

	private String userId; // 客人ID
	private String userName; // 客人姓名
	private String userMobile; // 客人电话
	private String guideId; // 导游ID
	private String guideNo; // 导游编号
	private String guideName; // 导游姓名
	private String serviceCityId;
	private String serviceCityName; // 服务城市

	private Integer evaluateStatus; // 评价状态
	private Boolean hasAdditionalCost; // 是否有增项费用
	private List<String> orderNoList; // 多个订单号

	/**
	 * @return the orderChannel
	 */
	public Integer getOrderChannel() {
		return orderChannel;
	}

	/**
	 * @param orderChannel
	 *            the orderChannel to set
	 */
	public void setOrderChannel(Integer orderChannel) {
		this.orderChannel = orderChannel;
	}

	public String getServiceTimeBegin() {
		return serviceTimeBegin;
	}

	public void setServiceTimeBegin(String serviceTimeBegin) {
		this.serviceTimeBegin = serviceTimeBegin;
	}

	public String getServiceTimeEnd() {
		return serviceTimeEnd;
	}

	public void setServiceTimeEnd(String serviceTimeEnd) {
		this.serviceTimeEnd = serviceTimeEnd;
	}

	public String getOrderStartDate() {
		return orderStartDate;
	}

	public void setOrderStartDate(String orderStartDate) {
		this.orderStartDate = orderStartDate;
	}

	public String getOrderEndDate() {
		return orderEndDate;
	}

	public void setOrderEndDate(String orderEndDate) {
		this.orderEndDate = orderEndDate;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getThirdTradeNo() {
		return thirdTradeNo;
	}

	public void setThirdTradeNo(String thirdTradeNo) {
		this.thirdTradeNo = thirdTradeNo;
	}

	public Integer getOrderSource() {
		return orderSource;
	}

	public void setOrderSource(Integer orderSource) {
		this.orderSource = orderSource;
	}

	public Integer getOrderType() {
		return orderType;
	}

	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}

	public Integer getDeliverType() {
		return deliverType;
	}

	public void setDeliverType(Integer deliverType) {
		this.deliverType = deliverType;
	}

	public Integer getDeliverStatus() {
		return deliverStatus;
	}

	public void setDeliverStatus(Integer deliverStatus) {
		this.deliverStatus = deliverStatus;
	}

	public Boolean getHasClickedAllButton() {
		return hasClickedAllButton;
	}

	public void setHasClickedAllButton(Boolean hasClickedAllButton) {
		this.hasClickedAllButton = hasClickedAllButton;
	}

	public Boolean getHasMoreThanOneHour() {
		return hasMoreThanOneHour;
	}

	public void setHasMoreThanOneHour(Boolean hasMoreThanOneHour) {
		this.hasMoreThanOneHour = hasMoreThanOneHour;
	}

	public Boolean getHasNotRegistered() {
		return hasNotRegistered;
	}

	public void setHasNotRegistered(Boolean hasNotRegistered) {
		this.hasNotRegistered = hasNotRegistered;
	}

	public String getPayTime() {
		return payTime;
	}

	public void setPayTime(String payTime) {
		this.payTime = payTime;
	}

	public List<Integer> getOrderStatusList() {
		return orderStatusList;
	}

	public void setOrderStatusList(List<Integer> orderStatusList) {
		this.orderStatusList = orderStatusList;
	}

	public Integer getSearchType() {
		return searchType;
	}

	public void setSearchType(Integer searchType) {
		this.searchType = searchType;
	}

	public Integer getUrgentFlag() {
		return urgentFlag;
	}

	public void setUrgentFlag(Integer urgentFlag) {
		this.urgentFlag = urgentFlag;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserMobile() {
		return userMobile;
	}

	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}

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

	public String getServiceCityId() {
		return serviceCityId;
	}

	public void setServiceCityId(String serviceCityId) {
		this.serviceCityId = serviceCityId;
	}

	public String getServiceCityName() {
		return serviceCityName;
	}

	public void setServiceCityName(String serviceCityName) {
		this.serviceCityName = serviceCityName;
	}

	public Integer getEvaluateStatus() {
		return evaluateStatus;
	}

	public void setEvaluateStatus(Integer evaluateStatus) {
		this.evaluateStatus = evaluateStatus;
	}

	public Boolean getHasAdditionalCost() {
		return hasAdditionalCost;
	}

	public void setHasAdditionalCost(Boolean hasAdditionalCost) {
		this.hasAdditionalCost = hasAdditionalCost;
	}

	public List<String> getOrderNoList() {
		return orderNoList;
	}

	public void setOrderNoList(List<String> orderNoList) {
		this.orderNoList = orderNoList;
	}

	@Override
	public String toString() {
		return "MISOrderQueryBean [serviceTimeBegin=" + serviceTimeBegin + ", serviceTimeEnd=" + serviceTimeEnd + ", orderStartDate=" + orderStartDate + ", orderEndDate=" + orderEndDate + ", orderNo=" + orderNo + ", thirdTradeNo=" + thirdTradeNo
				+ ", orderSource=" + orderSource + ", orderChannel=" + orderChannel + ", orderType=" + orderType + ", deliverType=" + deliverType + ", deliverStatus=" + deliverStatus + ", hasClickedAllButton=" + hasClickedAllButton
				+ ", hasMoreThanOneHour=" + hasMoreThanOneHour + ", hasNotRegistered=" + hasNotRegistered + ", payTime=" + payTime + ", orderStatusList=" + orderStatusList + ", searchType=" + searchType + ", urgentFlag=" + urgentFlag + ", userId="
				+ userId + ", userName=" + userName + ", userMobile=" + userMobile + ", guideId=" + guideId + ", guideNo=" + guideNo + ", guideName=" + guideName + ", serviceCityId=" + serviceCityId + ", serviceCityName=" + serviceCityName
				+ ", evaluateStatus=" + evaluateStatus + ", hasAdditionalCost=" + hasAdditionalCost + ", orderNoList=" + orderNoList + "]";
	}

}
