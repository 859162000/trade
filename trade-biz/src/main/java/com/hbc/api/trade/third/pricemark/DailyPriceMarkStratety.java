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
public class DailyPriceMarkStratety extends AbstractPriceMarkStratety {

	/* (non-Javadoc)
	 * @see com.hbc.api.trade.third.pricemark.AbstractPriceMarkStratety#buildRESTfulURL(com.hbc.api.trade.order.mapping.gen.bean.OrderBean)
	 * 
	 * Demo: http://api.dev.hbc.tech/price/v1.0/p/pricemarkauth?serviceType=3&pricemark=34c80e93-d839-41a5-ad66-523aecb3702e1447939033115
	 * &price=3&endCityId=43&startCityId=204&channelId=25&startLocation=37.460191,126.440696&endLocation=37.574102,127.020007
	 * &startDate=2015-11-12 19:30:22&endDate=2015-11-14 00:00:00&stayCities=204-1,43-2&carType=1&seatCategory=9
	 */
	@Override
	protected StringBuilder buildRESTfulURL(OrderBean orderBean) {
		StringBuilder queryString = super.buildRESTfulURL(orderBean);
		queryString.append("&startDate=").append(TimeConverter.formatDate(orderBean.getServiceTime()));
		queryString.append("&startCityId=").append(orderBean.getServiceCityId());
		queryString.append("&endCityId=").append(orderBean.getServiceEndCityid());
		queryString.append("&stayCities=").append(orderBean.getServicePassCity());
		queryString.append("&endDate=").append(TimeConverter.formatDates(orderBean.getServiceEndTime())+" 00:00:00");
		return queryString;
	}

}
