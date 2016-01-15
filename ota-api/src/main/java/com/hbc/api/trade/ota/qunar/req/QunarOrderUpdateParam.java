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

import org.hibernate.validator.constraints.NotBlank;

import com.hbc.api.trade.order.enums.ota.AgentChannelEnum;
import com.hbc.api.trade.ota.req.OrderModifyParam;

/**
 * @author Jongly Ran
 */
public class QunarOrderUpdateParam  extends QunarBaseParam{
	@NotBlank private String qOrderId; 	//	去哪儿订单号	String	“q100001”
	
	private String name;	 		//	乘客姓名	String	“John”
	private String phone;	 		//	乘客联系方式	String	"88879887"
	private String mobileCode;		//	联系方式国际区号	String	
	private String mobileCodeOuter;	//	境外联系电话国际区号	String	
	private String phoneOuter;		//	境外联系电话	String	
	private String cardNo;			//	乘客身份证号码或护照号码	String	"130703198209011214"
	private String updateTime;		//	更新时间	String	”2014-09-01 23:00:00”
	private String updateTimeMs;	//	更新时间(毫秒)	Long	1399887903000

	/* 2016-01-08 新增 */
	private String 		remark;		// 备注

	public OrderModifyParam toStandardOrderModifyParam() {
		OrderModifyParam param = new OrderModifyParam();
		param.setSign(getSign());
		param.setThirdTradeNo(qOrderId);
		param.setUserAreaCode1(mobileCode);
		param.setUserMobile1(phone);
		param.setUserAreaCode2(mobileCodeOuter);
		param.setUserMobile2(phoneOuter);
		param.setUserName(name);
		param.setServicePartner(AgentChannelEnum.QUNAR_CHANNEL.value);
		param.setUserRemark(remark);
		return param;
	}


	/**
	 * @return the qOrderId
	 */
	public String getqOrderId() {
		return qOrderId;
	}

	/**
	 * @param qOrderId the qOrderId to set
	 */
	public void setqOrderId(String qOrderId) {
		this.qOrderId = qOrderId;
	}

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
	 * @return the updateTime
	 */
	public String getUpdateTime() {
		return updateTime;
	}

	/**
	 * @param updateTime the updateTime to set
	 */
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * @return the updateTimeMs
	 */
	public String getUpdateTimeMs() {
		return updateTimeMs;
	}

	/**
	 * @param updateTimeMs the updateTimeMs to set
	 */
	public void setUpdateTimeMs(String updateTimeMs) {
		this.updateTimeMs = updateTimeMs;
	}
}
