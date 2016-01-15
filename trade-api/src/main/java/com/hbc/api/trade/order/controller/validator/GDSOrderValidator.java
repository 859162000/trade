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
package com.hbc.api.trade.order.controller.validator;

import com.hbc.api.trade.order.controller.opt.req.GDSOrderParam;
import com.hbc.api.trade.order.enums.TradeReturnCodeEnum;
import com.hbc.api.trade.order.enums.order.OrderType;
import com.hbc.api.trade.order.exception.TradeException;

/**
 * @author Jongly Ran
 */
public class GDSOrderValidator {

	/**
	 * @param orderBean
	 */
	public static void validatedCreateOrder(GDSOrderParam orderBean) {
		OrderValidator.validateParamString(orderBean.getPriceMark(), "价格系统唯一标识");
		OrderValidator.validateParamNumber(orderBean.getOrderChannel(), "订单渠道");
		OrderValidator.validateParamNumberGreaterThan0(orderBean.getPriceChannel(), "订单单价");
		OrderValidator.validateParamNumberGreaterThan0(orderBean.getAdultNum(), "成人座位数");
		OrderValidator.validateParamNumberGreaterThan0(orderBean.getChildNum(), "儿童座位数");
		OrderValidator.validateParamNumberGreaterThan0(orderBean.getCarSeatNum(), "座位数");
		OrderValidator.validateParamNumber(orderBean.getOrderType(), "订单类别");
		OrderValidator.validateParamString(orderBean.getDestAddress(), "目的地");
		OrderValidator.validateParamString(orderBean.getDestAddressDetail(), "目的地详细地址");
		OrderValidator.validateParamString(orderBean.getServiceTimes(), "服务时间");
		OrderValidator.validateParamString(orderBean.getAgentId(), "代理商ID");
		OrderValidator.validateParamString(orderBean.getAgentName(), "代理商名称");
		OrderValidator.validateParamString(orderBean.getAgentOpid(), "操作员ID");
		OrderValidator.validateParamString(orderBean.getAgentOpname(), "操作员姓名");
		
		switch(OrderType.getType(orderBean.getOrderType())) {
		case PICKUPORDER:
			OrderValidator.validateParamString(orderBean.getFlightDestCode(), "降落机场");
			OrderValidator.validateParamString(orderBean.getFlightNo(), "航班号");
			OrderValidator.validateParamNumber(orderBean.getExpectedCompTime(), "接机预计完成时间");
			break;
		case TRANSFER:
			OrderValidator.validateParamString(orderBean.getFlightAirportCode(), "起飞机场");
			OrderValidator.validateParamNumberGreaterThan0(orderBean.getCheckInPrice(), "帮办理登机手续费");
			if(orderBean.getPriceChannel() < orderBean.getCheckInPrice()) {
				throw new TradeException(TradeReturnCodeEnum.ORDER_PARAM_FAILED, "订单单价");
			}
			OrderValidator.validateParamNumber(orderBean.getExpectedCompTime(), "送机预计完成时间");
			break;
		case PERUSE:
			OrderValidator.validateParamNumber(orderBean.getDistance(), "服务距离");
			OrderValidator.validateParamString(orderBean.getStartAddress(), "出发地");
			OrderValidator.validateParamString(orderBean.getDestAddress(), "目的地");
			OrderValidator.validateParamNumber(orderBean.getExpectedCompTime(), "次租预计完成时间");
			break;
		case DAILY:
			OrderValidator.validateParamString(orderBean.getStartAddress(), "出发地");
			OrderValidator.validateParamString(orderBean.getDestAddress(), "目的地");
			OrderValidator.validateParamNumberGreaterThan0(orderBean.getTotalDays(), "总天数");
			break;
		case COMMENDATION:
			OrderValidator.validateParamString(orderBean.getGoodNo(), "商品ID");
			OrderValidator.validateParamNumberGreaterThan0(orderBean.getOrderGoodsType(), "商品类别");
			break;
		}
	}
}
