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
import com.hbc.api.trade.ota.req.OrderDetailParam;

/**
 * @author Jongly Ran
 */
public class QunarOrderDetailParam extends QunarBaseParam{
	@NotBlank private String qOrderId;	// 去哪儿订单号
	private String queryTime;
	private Long  queryTimeMs;

    public OrderDetailParam toStandardOrderDetailBean() {
    	OrderDetailParam param = new OrderDetailParam();
    	param.setServicePartner(AgentChannelEnum.QUNAR_CHANNEL.value);
    	param.setSign(getSign());
    	param.setThirdTradeNo(qOrderId);
    	return param;
    }

	/**
	 * @return the queryTime
	 */
	public String getQueryTime() {
		return queryTime;
	}

	/**
	 * @param queryTime the queryTime to set
	 */
	public void setQueryTime(String queryTime) {
		this.queryTime = queryTime;
	}

	/**
	 * @return the queryTimeMs
	 */
	public Long getQueryTimeMs() {
		return queryTimeMs;
	}

	/**
	 * @param queryTimeMs the queryTimeMs to set
	 */
	public void setQueryTimeMs(Long queryTimeMs) {
		this.queryTimeMs = queryTimeMs;
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
