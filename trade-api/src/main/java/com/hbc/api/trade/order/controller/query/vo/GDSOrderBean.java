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

import java.util.Date;

import com.hbc.api.trade.order.enums.order.OrderStatus;

/**
 * @author Jongly Ran
 */
public class GDSOrderBean extends AbstractThirdPartyOrderBean{
	private String 	userRequirement; // 客人需求(car_name)
	private Date	guideAssignTime; // 接单时间
	
	/**
	 * @return the guideAssignTime
	 */
	public Date getGuideAssignTime() {
		return guideAssignTime;
	}

	/**
	 * @param guideAssignTime the guideAssignTime to set
	 */
	public void setGuideAssignTime(Date guideAssignTime) {
		this.guideAssignTime = guideAssignTime;
	}

	/**
	 * 支付状态
	 * @return the hasPayed
	 */
	public Boolean getHasPayed() {
		return this.getOrderStatus().equals(OrderStatus.PAYSUCCESS.value) ;
	}

	/**
	 * @return the userRequirement
	 */
	public String getUserRequirement() {
		return userRequirement;
	}

	/**
	 * @param userRequirement the userRequirement to set
	 */
	public void setUserRequirement(String userRequirement) {
		this.userRequirement = userRequirement;
	}

}
