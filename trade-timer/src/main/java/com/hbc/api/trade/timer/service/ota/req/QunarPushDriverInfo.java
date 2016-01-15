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
 * @author Jongly Ran
 */
public class QunarPushDriverInfo extends QunarCallbackParam{
	private String driverName; 				//	是	司机姓名	String	
	private String driverMobile; 			//	是	司机电话	String	
	private Integer driverLanguage; 		//  是	司机语言:0本地 1英文 2中文	Integer	
	private String licensePlate; 			//	是	车牌	String	
	private String pickingPosition; 		//	是	接机位置	String	
	private String pickingPositionDetail; 	//	是	接送机司机具体位置	String	
	private String driverMobileCode; 		//	是	司机手机国际区号'	String	
	
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
	 * @return the driverMobile
	 */
	public String getDriverMobile() {
		return driverMobile;
	}
	/**
	 * @param driverMobile the driverMobile to set
	 */
	public void setDriverMobile(String driverMobile) {
		this.driverMobile = driverMobile;
	}
	/**
	 * @return the driverLanguage
	 */
	public Integer getDriverLanguage() {
		return driverLanguage;
	}
	/**
	 * @param driverLanguage 司机语言:0本地 1英文 2中文
	 */
	public void setDriverLanguage(Integer driverLanguage) {
		this.driverLanguage = driverLanguage;
	}
	/**
	 * @return the licensePlate
	 */
	public String getLicensePlate() {
		return licensePlate;
	}
	/**
	 * @param licensePlate the licensePlate to set
	 */
	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}
	/**
	 * @return the pickingPosition
	 */
	public String getPickingPosition() {
		return pickingPosition;
	}
	/**
	 * @param pickingPosition the pickingPosition to set
	 */
	public void setPickingPosition(String pickingPosition) {
		this.pickingPosition = pickingPosition;
	}
	/**
	 * @return the pickingPositionDetail
	 */
	public String getPickingPositionDetail() {
		return pickingPositionDetail;
	}
	/**
	 * @param pickingPositionDetail the pickingPositionDetail to set
	 */
	public void setPickingPositionDetail(String pickingPositionDetail) {
		this.pickingPositionDetail = pickingPositionDetail;
	}
	/**
	 * @return the driverMobileCode
	 */
	public String getDriverMobileCode() {
		return driverMobileCode;
	}
	/**
	 * @param driverMobileCode the driverMobileCode to set
	 */
	public void setDriverMobileCode(String driverMobileCode) {
		this.driverMobileCode = driverMobileCode;
	}
}
