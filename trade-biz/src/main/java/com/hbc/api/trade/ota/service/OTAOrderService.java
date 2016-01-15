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
package com.hbc.api.trade.ota.service;

import java.util.List;

import com.hbc.api.trade.order.mapping.gen.bean.OrderBean;
import com.hbc.api.trade.ota.mapping.gen.bean.TradeThirdOrder;
import com.hbc.api.trade.ota.po.CityPo;
import com.hbc.api.trade.ota.req.CalculatePriceParam;
import com.hbc.api.trade.ota.req.OrderCancelParam;
import com.hbc.api.trade.ota.req.OrderDetailParam;
import com.hbc.api.trade.ota.req.OrderModifyParam;
import com.hbc.api.trade.ota.req.OrderSubmitParam;
import com.hbc.api.trade.ota.resp.OrderCancelResult;
import com.hbc.api.trade.ota.resp.OrderPriceInfo;

/**
 * @author Jongly Ran
 */
public interface OTAOrderService {

	OrderPriceInfo getPrice(CalculatePriceParam param);

	TradeThirdOrder getThirdOrderBythirdTradeNo(Integer partner, String thirdTradeNo);

	OrderBean getOrderDetail(OrderDetailParam param);

	OrderBean createOrder(OrderSubmitParam param);

	OrderCancelResult cancelThirdOrder(OrderCancelParam param);

	List<CityPo> getServiceCitys();

	void updateOrder(OrderModifyParam param);
}