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

import java.util.List;

import com.hbc.api.trade.order.mapping.gen.bean.OrderBean;
import com.hbc.api.trade.order.service.rsp.GuideInfo;
import com.hbc.api.trade.order.service.rsp.OrderTrack;

/**
 * @author Jongly Ran
 */
public class MISOrderDetail extends OrderBean {
	private String 				payGatewayName;	// 支付方式，如支付宝、微信等
	private List<OrderTrack> 	orderTracks;	// 订单动态
	private GuideInfo			guideInfo;		// 导游信息
	
	/**
	 * @return the guideInfo
	 */
	public GuideInfo getGuideInfo() {
		return guideInfo;
	}
	/**
	 * @param guideInfo the guideInfo to set
	 */
	public void setGuideInfo(GuideInfo guideInfo) {
		this.guideInfo = guideInfo;
	}
	/**
	 * @return the payGatewayName
	 */
	public String getPayGatewayName() {
		return payGatewayName;
	}
	/**
	 * @param payGatewayName the payGatewayName to set
	 */
	public void setPayGatewayName(String payGatewayName) {
		this.payGatewayName = payGatewayName;
	}
	/**
	 * @return the orderTracks
	 */
	public List<OrderTrack> getOrderTracks() {
		return orderTracks;
	}
	/**
	 * @param orderTracks the orderTracks to set
	 */
	public void setOrderTracks(List<OrderTrack> orderTracks) {
		this.orderTracks = orderTracks;
	}
}
