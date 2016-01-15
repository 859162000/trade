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
public class QunarConfirmOrder extends QunarCallbackParam {
	private String motorCadeName; 			//	是	车队名字	String	
	private String motorCadeContact; 		//	是	车队联系方式	String	
	private String motorcadeContactCode; 	//	是	车队联系国际区号	String	

	/**
	 * @return 车队名字
	 */
	public String getMotorCadeName() {
		return motorCadeName;
	}
	/**
	 * @param motorCadeName 车队名字
	 */
	public void setMotorCadeName(String motorCadeName) {
		this.motorCadeName = motorCadeName;
	}
	/**
	 * @return the motorCadeContact
	 */
	public String getMotorCadeContact() {
		return motorCadeContact;
	}
	/**
	 * @param motorCadeContact 车队联系方式
	 */
	public void setMotorCadeContact(String motorCadeContact) {
		this.motorCadeContact = motorCadeContact;
	}
	/**
	 * @return the motorcadeContactCode
	 */
	public String getMotorcadeContactCode() {
		return motorcadeContactCode;
	}
	/**
	 * @param motorcadeContactCode 车队联系方式区号
	 */
	public void setMotorcadeContactCode(String motorcadeContactCode) {
		this.motorcadeContactCode = motorcadeContactCode;
	}
}
