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
 * 次租
 * @author Jongly Ran
 */
public class SinglePriceMarkStratety extends AbstractPriceMarkStratety {

	/* (non-Javadoc)
	 * @see com.hbc.api.trade.third.pricemark.AbstractPriceMarkStratety#buildRESTfulURL(com.hbc.api.trade.order.mapping.gen.bean.OrderBean)
	 * 
	 * Demo:  http://api.dev.hbc.tech/price/v1.0/p/pricemarkauth?serviceType=4&pricemark=f0c5e257-37c0-43c9-8d22-7c0df84524331447938754277
	 * &price=2&carType=2&seatCategory=5&startLocation=37.460191,126.440696&endLocation=37.574102,127.020007&channelId=25
	 * &serviceDate=2015-11-1219:30:22&cityId=204
	 */
	@Override
	protected StringBuilder buildRESTfulURL(OrderBean orderBean) {
		StringBuilder queryString = super.buildRESTfulURL(orderBean);
		queryString.append("&cityId=").append(orderBean.getServiceCityId());
		queryString.append("&serviceDate=").append(TimeConverter.formatDate(orderBean.getServiceTime()));
		return queryString;
	}

}
