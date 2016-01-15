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
package com.hbc.api.trade.ota.qunar.req;

import com.hbc.api.trade.bdata.common.sign.SubJSONParam;

/**
 * @author Jongly Ran
 */
public class QunarPassengerInfo implements SubJSONParam{
	private String  name;			//  是	乘客姓名	String	“张三”
	private String  phone;		//	是	乘客电话号码	String	“88879887"”
	private String  mobileCode;	//	否	国际区号	String	
	private String  mobileCodeOuter;		//	否	境外手机号国际区号	String	
	private String  phoneOuter;				//	否	境外手机号	String	
	private String  cardNo;					//	否	身份证号	String	
	private String  gender;					//	否	性别	String	F:女 	M:男
	private Integer age;					//	否	年龄	Int	
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}
	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	/**
	 * @return the mobileCode
	 */
	public String getMobileCode() {
		return mobileCode;
	}
	/**
	 * @param mobileCode the mobileCode to set
	 */
	public void setMobileCode(String mobileCode) {
		this.mobileCode = mobileCode;
	}
	/**
	 * @return the mobileCodeOuter
	 */
	public String getMobileCodeOuter() {
		return mobileCodeOuter;
	}
	/**
	 * @param mobileCodeOuter the mobileCodeOuter to set
	 */
	public void setMobileCodeOuter(String mobileCodeOuter) {
		this.mobileCodeOuter = mobileCodeOuter;
	}
	/**
	 * @return the phoneOuter
	 */
	public String getPhoneOuter() {
		return phoneOuter;
	}
	/**
	 * @param phoneOuter the phoneOuter to set
	 */
	public void setPhoneOuter(String phoneOuter) {
		this.phoneOuter = phoneOuter;
	}
	/**
	 * @return the cardNo
	 */
	public String getCardNo() {
		return cardNo;
	}
	/**
	 * @param cardNo the cardNo to set
	 */
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}
	/**
	 * @param gender the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}
	/**
	 * @return the age
	 */
	public Integer getAge() {
		return age;
	}
	/**
	 * @param age the age to set
	 */
	public void setAge(Integer age) {
		this.age = age;
	}

}
