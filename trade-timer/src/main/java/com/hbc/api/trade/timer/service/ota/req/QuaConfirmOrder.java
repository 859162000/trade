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
package com.hbc.api.trade.timer.service.ota.req;

/**
 * 使用SDK 
 * @since 2015-12-20
 * @author Jongly Ran
 */
@Deprecated
public class QuaConfirmOrder {
	private String orderId; 		//	是	String		阿里旅行生成的订单号
	private String providerId; 		//	是	String		服务商标识,由阿里旅行分配
	private String thirdOrderId; 	//	否	String		供应商生成的唯一订单号
	private Integer confirmType; 	//	是	Integer		0为接受订单 1为无法接单
	private String message; 		//	否	String		无法接单原因
	
	/**
	 * @return the orderId
	 */
	public String getOrderId() {
		return orderId;
	}
	/**
	 * @param orderId the orderId to set
	 */
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	/**
	 * @return the providerId
	 */
	public String getProviderId() {
		return providerId;
	}
	/**
	 * @param providerId the providerId to set
	 */
	public void setProviderId(String providerId) {
		this.providerId = providerId;
	}
	/**
	 * @return the thirdOrderId
	 */
	public String getThirdOrderId() {
		return thirdOrderId;
	}
	/**
	 * @param thirdOrderId the thirdOrderId to set
	 */
	public void setThirdOrderId(String thirdOrderId) {
		this.thirdOrderId = thirdOrderId;
	}
	/**
	 * @return the confirmType
	 */
	public Integer getConfirmType() {
		return confirmType;
	}
	/**
	 * @param confirmType the confirmType to set
	 */
	public void setConfirmType(Integer confirmType) {
		this.confirmType = confirmType;
	}
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

}
