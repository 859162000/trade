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
package com.hbc.api.trade.order.service.transfer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hbc.api.trade.bdata.common.util.TimeConverter;
import com.hbc.api.trade.order.enums.TradeReturnCodeEnum;
import com.hbc.api.trade.order.enums.order.OrderType;
import com.hbc.api.trade.order.exception.TradeException;
import com.hbc.api.trade.order.mapping.gen.bean.OrderBean;
import com.hbc.api.trade.order.service.AbstractOrderService;
import com.hbc.api.trade.order.service.param.TransferNewOrderParam;

/**
 * @author Jongly Ran
 */
public abstract class AbstractTransferOrderService extends AbstractOrderService {
	protected final static Logger log = LoggerFactory.getLogger(AbstractTransferOrderService.class);


	/* (non-Javadoc)
	 * @see com.hbc.api.trade.order.service.transfer.AbstractOrderService#appendParams(com.hbc.api.trade.order.mapping.gen.bean.OrderBean, java.lang.Object)
	 */
	@Override
	protected <T> void appendParams(OrderBean orderBean, T param) {
		TransferNewOrderParam transferNewOrderParam = (TransferNewOrderParam) param;
		orderBean.setOrderType(OrderType.TRANSFER.value);
		orderBean.setFlightFlyTime(TimeConverter.toDate(transferNewOrderParam.getFlightFlyTimes()));
		orderBean.setFlightArriveTime(TimeConverter.toDate(transferNewOrderParam.getFlightArriveTimes()));
		orderBean.setServiceTime(TimeConverter.toDate(transferNewOrderParam.getServiceTimes()));
	}


	/* (non-Javadoc)
	 * @see com.hbc.api.trade.order.service.transfer.AbstractOrderService#validateCreateNewOrder(com.hbc.api.trade.order.mapping.gen.bean.OrderBean)
	 */
	@Override
	protected void validateCreateNewOrder(OrderBean orderBean) {
		super.validateCreateNewOrder(orderBean);
		if(orderBean.getCheckInPrice() != null && orderBean.getPriceChannel() < orderBean.getCheckInPrice()) {
			log.error("送机时，订单价不能小于checkIn价");
			throw new TradeException(TradeReturnCodeEnum.ORDER_PARAM_FAILED, "订单价");
		}
	}
}
