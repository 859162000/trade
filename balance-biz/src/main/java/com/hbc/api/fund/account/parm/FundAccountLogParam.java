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
package com.hbc.api.fund.account.parm;

/**
 * @author Jongly Ran
 */
public class FundAccountLogParam extends Pagination{
	private String accountNo; 				// 资金账户
	private String guideId; 				// 导游ID
	private Boolean isTradeTimeDown = false; // 交易时间逆序

	/**
	 * @return the isTradeTimeDown
	 */
	public Boolean getIsTradeTimeDown() {
		return isTradeTimeDown;
	}

	/**
	 * @param isTradeTimeDown the isTradeTimeDown to set
	 */
	public void setIsTradeTimeDown(Boolean isTradeTimeDown) {
		this.isTradeTimeDown = isTradeTimeDown;
	}

	/**
	 * @return the guideId
	 */
	public String getGuideId() {
		return guideId;
	}

	/**
	 * @param guideId the guideId to set
	 */
	public void setGuideId(String guideId) {
		this.guideId = guideId;
	}

	/**
	 * @return the accountNo
	 */
	public String getAccountNo() {
		return accountNo;
	}

	/**
	 * @param accountNo the accountNo to set
	 */
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
}
