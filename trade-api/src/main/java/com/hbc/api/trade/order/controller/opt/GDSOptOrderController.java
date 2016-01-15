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
package com.hbc.api.trade.order.controller.opt;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hbc.api.trade.bdata.common.rsp.ReturnResult;
import com.hbc.api.trade.bdata.common.util.BeanUtilsEnhance;
import com.hbc.api.trade.order.controller.opt.req.GDSOrderParam;
import com.hbc.api.trade.order.controller.validator.OrderValidator;
import com.hbc.api.trade.order.enums.TradeReturnCodeEnum;
import com.hbc.api.trade.order.enums.order.OrderType;
import com.hbc.api.trade.order.enums.order.VisaType;
import com.hbc.api.trade.order.exception.TradeException;
import com.hbc.api.trade.order.mapping.gen.bean.OrderBean;
import com.hbc.api.trade.order.service.GDSOptOrderService;
import com.hbc.api.trade.pay.service.RefundService;

/**
 * @author Jongly Ran
 */


@RequestMapping("trade")
@RestController
public class GDSOptOrderController {
	
	@Autowired private GDSOptOrderService 	gdsOptOrderService;
	@Autowired private RefundService 		refundService;

	@RequestMapping(value = "v1.0/ca/order/edit", 
			method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public ReturnResult editOrder(GDSOrderParam orderParam, HttpServletRequest request) {
		OrderType orderType = OrderType.getType(orderParam.getOrderType());
		if (orderType.equals(OrderType.PICKUPORDER) && orderParam.getIsArrivalVisa() != null) {
			VisaType.isValidate(orderParam.getIsArrivalVisa());
		}
		OrderBean orderBean = new OrderBean();
		BeanUtilsEnhance.copyProperties(orderBean, orderParam);

//		orderBean.setServicePassCity(orderParam.getServicePassCitys());
		switch (orderType) {
		case PICKUPORDER:
			gdsOptOrderService.updatePickUpOrder(orderBean);
			break;
		case TRANSFER:
			gdsOptOrderService.updateTransferOrder(orderBean);
			break;
		case DAILY:
		case COMMENDATION:
			gdsOptOrderService.updateDailyOrder(orderBean, orderParam.getServiceRecTime());
			break;
		case PERUSE:
			gdsOptOrderService.updateSingleOrder(orderBean);
			break;
		default:
			throw new TradeException(TradeReturnCodeEnum.ORDER_PARAM_FAILED, "订单类别错误");
		}
		return new ReturnResult();
	}


	@RequestMapping(value = "v1.0/ca/order/cancel", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public ReturnResult cancelOrder(String orderNo, String reason, HttpServletRequest request) {

		OrderValidator.validateOrderNo(orderNo);
		refundService.refundOrder(orderNo, reason);
		return new ReturnResult();
	}
	
	
	@RequestMapping(value = "v1.0/ca/order/remove", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public ReturnResult removeOrder(String orderNo, HttpServletRequest request) {
		
		OrderValidator.validateOrderNo(orderNo);
		return new ReturnResult();
	}
}
