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

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hbc.api.trade.bdata.common.rsp.ReturnResult;
import com.hbc.api.trade.order.controller.query.MISOrderLogController;
import com.hbc.api.trade.order.enums.TradeReturnCodeEnum;
import com.hbc.api.trade.order.enums.bean.LogType;
import com.hbc.api.trade.order.exception.TradeException;
import com.hbc.api.trade.order.mapping.gen.bean.OrderLog;
import com.hbc.api.trade.order.mapping.genx.xbean.OrderLogParamBean;
import com.hbc.api.trade.order.service.OrderLogService;

/**
 * @author Jongly Ran
 */
@RestController
@RequestMapping("trade")
public class GDSOrderLogController {
	private final static Logger logger = LoggerFactory.getLogger(MISOrderLogController.class);
	
	@Autowired
	private OrderLogService orderLogService;

	@RequestMapping(value = "v1.0/ca/order/log", 
			method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public ReturnResult getLeaveWords(String orderNo) {
		if(StringUtils.isBlank(orderNo)) {
			TradeException tradeException = new TradeException(TradeReturnCodeEnum.ORDER_PARAM_FAILED, "orderNo");
			logger.error(tradeException.getMessage());
			throw tradeException;
		}
		List<OrderLog> orderLogList = orderLogService.getLeaveMsgs(orderNo);
		ReturnResult returnResult = new ReturnResult();
		returnResult.setData(orderLogList);
		return returnResult;
	}
	
	@RequestMapping(value = "v1.0/ca/order/log/submit", 
			method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public ReturnResult insertLeaveWords(OrderLogParamBean orderLogParam) {
		validateInputsForInsertLeaveWords(orderLogParam);
		orderLogParam.setLogType(LogType.ADD_COMMENTS_MANUALLY.value);
		orderLogService.insertOrderLog(orderLogParam);
		return new ReturnResult();
	}

	/**
	 * @param orderLogParam
	 */
	private void validateInputsForInsertLeaveWords(OrderLogParamBean orderLogParam) {
		if(StringUtils.isBlank(orderLogParam.getOrderNo())) {
			TradeException tradeException = new TradeException(TradeReturnCodeEnum.ORDER_PARAM_FAILED, "orderNo");
			logger.error(tradeException.getMessage());
			throw tradeException;
		}
		if(StringUtils.isBlank(orderLogParam.getContent())) {
			TradeException tradeException = new TradeException(TradeReturnCodeEnum.ORDER_PARAM_FAILED, "content");
			logger.error(tradeException.getMessage());
			throw tradeException;
		}
		if(StringUtils.isBlank(orderLogParam.getOpUserId())) {
			TradeException tradeException = new TradeException(TradeReturnCodeEnum.ORDER_PARAM_FAILED, "OpUserId");
			logger.error(tradeException.getMessage());
			throw tradeException;
		}
		if(StringUtils.isBlank(orderLogParam.getOpUserName())) {
			TradeException tradeException = new TradeException(TradeReturnCodeEnum.ORDER_PARAM_FAILED, "OpUserName");
			logger.error(tradeException.getMessage());
			throw tradeException;
		}
	}
}
