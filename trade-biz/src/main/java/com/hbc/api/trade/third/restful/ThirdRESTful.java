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
package com.hbc.api.trade.third.restful;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author Jongly Ran
 */
@Component
public class ThirdRESTful {
	
	@Value("${trade.order.gds.create.obtain.user}")
	public String TRADE_ORDER_GDS_CREATE_OBTAIN_USER;
	
	@Value("${trade.order.appraise.guide}")
	public String TRADE_ORDER_APPRAISE_GUIDE;
	
	@Value("${trade.order.appraise.guide.history}")
	public String TRADE_ORDER_APPRAISE_GUIDE_HISTORY;
	
	@Value("${trade.order.appraise.user.comment.list}")
	public String TRADE_ORDER_APPRAISE_USER_COMMENT_LIST;
	
	@Value("${trade.order.obtain.im.statistics.customer}")
	public String TRADE_ORDER_OBTAIN_IM_STATISTICS_CUSTOMER;

	@Value("${trade.order.obtain.im.statistics.cunreadlist}")
	public String TRADE_ORDER_OBTAIN_IM_STATISTICS_CUNREADLIST;
	
	@Value("${trade.order.obtain.im.statistics.guide}")
	public String TRADE_ORDER_OBTAIN_IM_STATISTICS_GUIDE;

	@Value("${trade.order.obtain.im.statistics.gunreadlist}")
	public String TRADE_ORDER_OBTAIN_IM_STATISTICS_GUNREADLIST;
	
	@Value("${trade.order.flight.register}")
	public String TRADE_ORDER_FLIGHT_REGISTER;
	
	@Value("${trade.order.ucenter.obtain.account.no}")
	public String TRADE_ORDER_UCENTER_OBTAIN_ACCOUNT_NO;
	
	@Value("${trade.order.communication.imtoken}")
	public String TRADE_ORDER_COMMUNICATION_IMTOKEN;

	@Value("${trade.order.communication.push}")
	public String TRADE_ORDER_COMMUNICATION_PUSH;
	
	@Value("${trade.order.marketing.coupons}")
	public String TRADE_ORDER_MARKETING_COUPONS;
	
	@Value("${trade.order.payment.discount}")
	public String TRADE_ORDER_PAYMENT_DISCOUNT;
}
