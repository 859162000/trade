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
package com.hbc.api.trade.third.vo;

/**
 * @author Jongly Ran
 */
public class MISAppraisement extends Appraisement {
	private String orderNo;				// 订单号
	private String guideName;			// 导游姓名
	private String orderType;			// 服务类型    如：日租
	private String serviceCityName;		
	private String serviceCountryName;	// 服务城市    如：曼谷(泰国)
	private String serviceCompleteTime;	// 服务完成时间 如：2015-09-28 09:56:49(当地时间)
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
	 * @return the guideName
	 */
	public String getGuideName() {
		return guideName;
	}
	/**
	 * @param guideName the guideName to set
	 */
	public void setGuideName(String guideName) {
		this.guideName = guideName;
	}
	/**
	 * @return the orderType
	 */
	public String getOrderType() {
		return orderType;
	}
	/**
	 * @param orderType the orderType to set
	 */
	public void setOrderType(String orderType) {
		this.orderType = orderType;
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
	 * @return the serviceCountryName
	 */
	public String getServiceCountryName() {
		return serviceCountryName;
	}
	/**
	 * @param serviceCountryName the serviceCountryName to set
	 */
	public void setServiceCountryName(String serviceCountryName) {
		this.serviceCountryName = serviceCountryName;
	}
	/**
	 * @return the serviceCompleteTime
	 */
	public String getServiceCompleteTime() {
		return serviceCompleteTime;
	}
	/**
	 * @param serviceCompleteTime the serviceCompleteTime to set
	 */
	public void setServiceCompleteTime(String serviceCompleteTime) {
		this.serviceCompleteTime = serviceCompleteTime;
	}
}
