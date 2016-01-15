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
import com.hbc.api.trade.ota.req.OrderCancelParam;

/**
 * @author Jongly Ran
 */
public class QunarOrderCancelParam extends QunarBaseParam{
	@NotBlank 
	private String qOrderId;	// 	是	去哪儿订单号	String	“q100001”
	private Integer cancelType;	// 	是	取消的原因	Short	1：系统取消 2：用户取消
	private String cancelTime;	//	是	取消时间	String	”2014-09-01 23:00:00”
	private Long cancelTimeMs;	//	是	取消时间(毫秒)	Long	1399887903000
	
	/**
	 * @return
	 */
	public OrderCancelParam toStandardOrderDetailBean() {
		OrderCancelParam param = new OrderCancelParam();
    	param.setServicePartner(AgentChannelEnum.QUNAR_CHANNEL.value);
    	param.setSign(getSign());
    	param.setThirdTradeNo(qOrderId);
    	param.setReason(cancelType == null || cancelType.equals(2) ? "用户取消" : "系统取消");
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
	 * @return the cancelType
	 */
	public Integer getCancelType() {
		return cancelType;
	}
	/**
	 * @param cancelType the cancelType to set
	 */
	public void setCancelType(Integer cancelType) {
		this.cancelType = cancelType;
	}
	/**
	 * @return the cancelTime
	 */
	public String getCancelTime() {
		return cancelTime;
	}
	/**
	 * @param cancelTime the cancelTime to set
	 */
	public void setCancelTime(String cancelTime) {
		this.cancelTime = cancelTime;
	}
	/**
	 * @return the cancelTimeMs
	 */
	public Long getCancelTimeMs() {
		return cancelTimeMs;
	}
	/**
	 * @param cancelTimeMs the cancelTimeMs to set
	 */
	public void setCancelTimeMs(Long cancelTimeMs) {
		this.cancelTimeMs = cancelTimeMs;
	}

}
