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
 * 精品线路
 * @author Jongly Ran
 */
public class CommendationPriceMarkStratety extends AbstractPriceMarkStratety {

	/* (non-Javadoc)
	 * @see com.hbc.api.trade.third.pricemark.AbstractPriceMarkStratety#buildRESTfulURL(com.hbc.api.trade.order.mapping.gen.bean.OrderBean)
	 */
	@Override
	protected StringBuilder buildRESTfulURL(OrderBean orderBean) {
		StringBuilder queryString = super.buildRESTfulURL(orderBean);
		queryString.append("&serviceDate=").append(TimeConverter.formatDate(orderBean.getServiceTime()));
		queryString.append("&startCityId=").append(orderBean.getServiceCityId());
		queryString.append("&endCityId=").append(orderBean.getServiceEndCityid());
		queryString.append("&stayCities=").append(orderBean.getServicePassCity());
		queryString.append("&goodsNo=").append(orderBean.getGoodNo());
		queryString.append("&goodsType=").append(orderBean.getOrderGoodsType());
		return queryString;
	}

}
