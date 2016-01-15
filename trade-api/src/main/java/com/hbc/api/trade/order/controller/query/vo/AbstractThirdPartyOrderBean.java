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
package com.hbc.api.trade.order.controller.query.vo;

/**
 * @author Jongly Ran
 */
public abstract class AbstractThirdPartyOrderBean {
    private String 	orderNo; 			// 订单号	
    private String 	createTime; 		// 下单时间
    private Integer orderSource;		// 渠道，订单来源1 C端 2GDS 3 携程 4 去哪
    private String 	serviceTime; 		// 服务开始时间,精确到秒 
    private String 	serviceCityName; 	// 包车开始城市
    private String 	serviceCountryName; // 服务地国籍
    private Integer orderStatus; 		// 订单状态
    private String  userId;				// 客人ID
    private String 	userName;			// 客人姓名
    private String 	userAreaCode;		// 客人电话国家码区号
    private String 	userMobile;			// 客人电话
    private Integer orderType;			// 订单类型。1-接机；2-送机；3-包车；4-次租；5-拼车
    private Double 	priceChannel;		// 订单价格
	/**
	 * @return the userAreaCode
	 */
	public String getUserAreaCode() {
		return userAreaCode;
	}
	/**
	 * @param userAreaCode the userAreaCode to set
	 */
	public void setUserAreaCode(String userAreaCode) {
		this.userAreaCode = userAreaCode;
	}
	/**
	 * @return the userMobile
	 */
	public String getUserMobile() {
		return userMobile;
	}
	/**
	 * @param userMobile the userMobile to set
	 */
	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}
	/**
	 * @return the orderSource
	 */
	public Integer getOrderSource() {
		return orderSource;
	}
	/**
	 * @param orderSource the orderSource to set
	 */
	public void setOrderSource(Integer orderSource) {
		this.orderSource = orderSource;
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
	 * @return 订单类型。1-接机；2-送机；3-包车；4-次租；5-拼车
	 */
	public Integer getOrderType() {
		return orderType;
	}
	/**
	 * @param orderType 订单类型。1-接机；2-送机；3-包车；4-次租；5-拼车
	 */
	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}
	/**
	 * @return the orderStatus
	 */
	public Integer getOrderStatus() {
		return orderStatus;
	}
	/**
	 * @param orderStatus the orderStatus to set
	 */
	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}
	/**
	 * @return 服务开始时间,精确到秒
	 */
	public String getServiceTime() {
		return serviceTime;
	}
	/**
	 * @param serviceTime 服务开始时间,精确到秒
	 */
	public void setServiceTime(String serviceTime) {
		this.serviceTime = serviceTime;
	}
	/**
	 * @return the serviceCityName
	 */
	public String getServiceCityName() {
		return serviceCityName;
	}
	/**
	 * @param serviceCityName the serviceCityName to set
	 */
	public void setServiceCityName(String serviceCityName) {
		this.serviceCityName = serviceCityName;
	}
	/**
	 * @return 服务地国籍
	 */
	public String getServiceCountryName() {
		return serviceCountryName;
	}
	/**
	 * @param serviceCountryName 服务地国籍
	 */
	public void setServiceCountryName(String serviceCountryName) {
		this.serviceCountryName = serviceCountryName;
	}
	/**
	 * @return 订单时间
	 */
	public String getCreateTime() {
		return createTime;
	}
	/**
	 * @param createTime 订单时间
	 */
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	/**
	 * @return the priceChannel
	 */
	public Double getPriceChannel() {
		return priceChannel;
	}
	/**
	 * @param priceChannel the priceChannel to set
	 */
	public void setPriceChannel(Double priceChannel) {
		this.priceChannel = priceChannel;
	}
	
	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
}
