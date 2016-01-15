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
public class QuaPushDriverInfo {
	private String orderId; 		//	是	String		阿里旅行生成的订单号
	private String providerId; 		//	是	String		服务商标识,由阿里旅行分配
	private String thirdOrderId; 	//	否	String		供应商生成的唯一订单号
	private String confirmTime; 	//	是	String		2015-12-12 12:12:12	司机应答时间（北京时间）
	private String driverName; 		//	是	String		司机姓名
	private String driverTel; 		//	是	String		司机联系电话
	private String driverCarName; 	//	否	String		例如: 奔驰C200
	private String driverCarDesc; 	//	否	String		司机车辆颜色等信息：如红色,候车地点 
	private String driverCarNo; 	//	否	String		司机车牌号
	private String confirmType; 	//	是	Integer		0为应答,1为改派
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
	 * @return the confirmTime
	 */
	public String getConfirmTime() {
		return confirmTime;
	}
	/**
	 * @param confirmTime the confirmTime to set
	 */
	public void setConfirmTime(String confirmTime) {
		this.confirmTime = confirmTime;
	}
	/**
	 * @return the driverName
	 */
	public String getDriverName() {
		return driverName;
	}
	/**
	 * @param driverName the driverName to set
	 */
	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}
	/**
	 * @return the driverTel
	 */
	public String getDriverTel() {
		return driverTel;
	}
	/**
	 * @param driverTel the driverTel to set
	 */
	public void setDriverTel(String driverTel) {
		this.driverTel = driverTel;
	}
	/**
	 * @return the driverCarName
	 */
	public String getDriverCarName() {
		return driverCarName;
	}
	/**
	 * @param driverCarName the driverCarName to set
	 */
	public void setDriverCarName(String driverCarName) {
		this.driverCarName = driverCarName;
	}
	/**
	 * @return the driverCarDesc
	 */
	public String getDriverCarDesc() {
		return driverCarDesc;
	}
	/**
	 * @param driverCarDesc the driverCarDesc to set
	 */
	public void setDriverCarDesc(String driverCarDesc) {
		this.driverCarDesc = driverCarDesc;
	}
	/**
	 * @return the driverCarNo
	 */
	public String getDriverCarNo() {
		return driverCarNo;
	}
	/**
	 * @param driverCarNo the driverCarNo to set
	 */
	public void setDriverCarNo(String driverCarNo) {
		this.driverCarNo = driverCarNo;
	}
	/**
	 * @return the confirmType
	 */
	public String getConfirmType() {
		return confirmType;
	}
	/**
	 * @param confirmType the confirmType to set
	 */
	public void setConfirmType(String confirmType) {
		this.confirmType = confirmType;
	}

}
