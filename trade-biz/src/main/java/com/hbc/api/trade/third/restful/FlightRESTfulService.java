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

import java.io.IOException;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hbc.api.trade.bdata.common.HttpClientService;
import com.hbc.api.trade.bdata.common.rsp.ReturnResult;
import com.hbc.api.trade.bdata.common.util.TimeConverter;
import com.hbc.api.trade.order.enums.TradeReturnCodeEnum;
import com.hbc.api.trade.order.enums.bean.OperationType;
import com.hbc.api.trade.order.enums.order.OrderLogType;
import com.hbc.api.trade.order.exception.TradeException;
import com.hbc.api.trade.order.mapping.genx.xbean.OrderLogParamBean;
import com.hbc.api.trade.order.service.OrderLogService;
import com.hbc.api.trade.order.service.OrderService;

/**
 * @author Jongly Ran
 */
@Service
public class FlightRESTfulService {
	private final static Logger logger = LoggerFactory.getLogger(FlightRESTfulService.class);
	
	@Autowired private ThirdRESTful			thirdRESTful;
	@Autowired private HttpClientService	httpClientService;
	@Autowired private OrderLogService		orderLogService;
	@Autowired private OrderService			orderService;

	/**
	 * 
	 * 注册航班号
	 * @param flightNo 航班号
	 * @param flightCode 目的地三字码
	 * @param flightDestCode 出发地三字码
	 * @param flightDate  航班日期（yyyy-mm-dd格式，例2014-09-01）
	 * @param opUserId 操作员ID
	 * @param opUserName 操作员Name
	 * @param orderNo 订单编号
	 * @return
	 */
	@Transactional
	public ReturnResult registerFlightNo(String flightNo, String flightCode, String flightDestCode, String flightDate,
			String opUserId, String opUserName, String orderNo) {
		StringBuilder params = new StringBuilder();
		params.append("?fnum=").append(flightNo).append("&dep=").append(flightCode).append("&arr=")
			.append(flightDestCode).append("&date=").append(flightDate);
		
		String responseText = null;
		try {
			String url = thirdRESTful.TRADE_ORDER_FLIGHT_REGISTER + params.toString();
			logger.info("请求地址：" + url);
			responseText = httpClientService.sendReqPost(url);
		} catch (IOException e) {
			logger.error("调用航班服务器失败", e);
			throw new TradeException(TradeReturnCodeEnum.RESTFULL_ERROR_FLIGHT);
		}
		
		logger.info(responseText);
		ReturnResult result = JSON.parseObject(responseText, ReturnResult.class);
		if(result.getStatus() == 200) {
			JSONObject data = (JSONObject) result.getData();
			orderService.registerFlight(data.getString("flightId"), orderNo);
			
			OrderLogParamBean orderLogParamBean = new OrderLogParamBean();
			orderLogParamBean.setContent(OrderLogType.REGISTER_FLIGHT_NO_CONTENT(TimeConverter.formatDate(new Date())) );
			orderLogParamBean.setLogType(OrderLogType.REGISTER_FLIGHT_NO.type);
			orderLogParamBean.setOpType(OperationType.CUSTOMER_SERVICE.value);
			orderLogParamBean.setOpUserId(opUserId);
			orderLogParamBean.setOpUserName(opUserName);
			orderLogParamBean.setOrderNo(orderNo);
			orderLogService.insertOrderLog(orderLogParamBean );
		}
		return result;
	}
}
