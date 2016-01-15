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

import com.hbc.api.trade.bdata.mapper.guide.gen.bean.GuideBean;
import com.hbc.api.trade.order.mapping.gen.bean.OrderBean;
import com.hbc.api.trade.ota.mapping.gen.bean.TradeThirdOrder;

/**
 * @author Jongly Ran
 */
public class CallbackBean {
	private TradeThirdOrder tradeThirdOrder;
	private GuideBean guideBean;
	private OrderBean orderBean;
	
	public CallbackBean() {
		
	}
	
	public CallbackBean(TradeThirdOrder tradeThirdOrder, GuideBean guideBean, OrderBean orderBean) {
		this.tradeThirdOrder = tradeThirdOrder;
		this.guideBean = guideBean;
		this.orderBean = orderBean;
	}
	
	/**
	 * @return the tradeThirdOrder
	 */
	public TradeThirdOrder getTradeThirdOrder() {
		return tradeThirdOrder;
	}
	/**
	 * @param tradeThirdOrder the tradeThirdOrder to set
	 */
	public void setTradeThirdOrder(TradeThirdOrder tradeThirdOrder) {
		this.tradeThirdOrder = tradeThirdOrder;
	}
	/**
	 * @return the guideBean
	 */
	public GuideBean getGuideBean() {
		return guideBean;
	}
	/**
	 * @param guideBean the guideBean to set
	 */
	public void setGuideBean(GuideBean guideBean) {
		this.guideBean = guideBean;
	}
	/**
	 * @return the orderBean
	 */
	public OrderBean getOrderBean() {
		return orderBean;
	}
	/**
	 * @param orderBean the orderBean to set
	 */
	public void setOrderBean(OrderBean orderBean) {
		this.orderBean = orderBean;
	}
}
