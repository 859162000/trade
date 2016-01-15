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
package com.hbc.api.trade.third.pricemark;

import com.hbc.api.trade.bdata.common.util.TimeConverter;
import com.hbc.api.trade.order.mapping.gen.bean.OrderBean;

/**
 * @author Jongly Ran
 */
public class TransferPriceMarkStratety extends AbstractPriceMarkStratety {

	/* (non-Javadoc)
	 * @see com.hbc.api.trade.third.pricemark.AbstractPriceMarkStratety#buildRESTfulURL(com.hbc.api.trade.order.mapping.gen.bean.OrderBean)
	 * 
	 * Demo: http://api.dev.hbc.tech/price/v1.0/p/pricemarkauth?serviceType=2&pricemark=b9bfafb1-bd35-4217-8869-2965ff05d9e81447937078853
	 * &price=1164&carType=1&seatCategory=5&startLocation=37.460191,126.440696&endLocation=37.574102,127.020007&airportCode=ICN
	 * &channelId=25&serviceDate=2015-11-12 19:30:22&assitCheckIn=1&checkinPrice=54
	 */
	@Override
	protected StringBuilder buildRESTfulURL(OrderBean orderBean) {
		
		Double checkInPrice = orderBean.getCheckInPrice();
		int ischeckIn = 0;
		if (checkInPrice != null && checkInPrice > 0) {
			ischeckIn = 1;
		} else {
			checkInPrice = 0d;
		}
		StringBuilder queryString = super.buildRESTfulURL(orderBean);
		queryString.append("&airportCode=").append(orderBean.getFlightAirportCode());
		queryString.append("&assitCheckIn=").append(ischeckIn);
		queryString.append("&checkinPrice=").append(checkInPrice);
		queryString.append("&serviceDate=").append(TimeConverter.formatDate(orderBean.getServiceTime()));
		return queryString;
	}

}
