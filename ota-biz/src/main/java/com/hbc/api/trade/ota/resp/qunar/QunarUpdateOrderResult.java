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
package com.hbc.api.trade.ota.resp.qunar;

import org.hibernate.validator.constraints.NotBlank;

/**
 * @author Jongly Ran
 */
public class QunarUpdateOrderResult extends QunarResult{

	@NotBlank private String qOrderId;		// 去哪儿订单号
	
	/**
	 * 
	 */
	public QunarUpdateOrderResult() {
		super();
	}
	
	public QunarUpdateOrderResult(String qOrderId) {
		this.qOrderId = qOrderId;
	}

	/**
	 * @param code
	 * @param errorMsg
	 */
	public QunarUpdateOrderResult(int code, String errorMsg) {
		super(code, errorMsg);
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
	
}
