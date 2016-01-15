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
package com.hbc.api.trade.ota.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * @author Jongly Ran
 */
public class OrderCancelParam {
	@NotBlank private String thirdTradeNo;		// 三方订单号	
	private Integer servicePartner ;
	private String reason;						// 退款原因
	private Double ruserPrice;					// 退款金额
	
	@NotBlank private String sign ;

	/**
	 * @return the ruserPrice
	 */
	public Double getRuserPrice() {
		return ruserPrice;
	}

	/**
	 * @param ruserPrice the ruserPrice to set
	 */
	public void setRuserPrice(Double ruserPrice) {
		this.ruserPrice = ruserPrice;
	}

	/**
	 * @return the reason
	 */
	public String getReason() {
		return reason;
	}

	/**
	 * @param reason the reason to set
	 */
	public void setReason(String reason) {
		this.reason = reason;
	}

	/**
	 * @return the thirdTradeNo
	 */
	public String getThirdTradeNo() {
		return thirdTradeNo;
	}

	/**
	 * @param thirdTradeNo the thirdTradeNo to set
	 */
	public void setThirdTradeNo(String thirdTradeNo) {
		this.thirdTradeNo = thirdTradeNo;
	}

	/**
	 * @return the servicePartner
	 */
	public Integer getServicePartner() {
		return servicePartner;
	}

	/**
	 * @param servicePartner the servicePartner to set
	 */
	public void setServicePartner(Integer servicePartner) {
		this.servicePartner = servicePartner;
	}

	/**
	 * @return the sign
	 */
	public String getSign() {
		return sign;
	}

	/**
	 * @param sign the sign to set
	 */
	public void setSign(String sign) {
		this.sign = sign;
	}
}
