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
package com.hbc.api.trade.order.service.corder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hbc.api.trade.bdata.common.rsp.ListServiceRsp;
import com.hbc.api.trade.order.enums.TradeReturnCodeEnum;
import com.hbc.api.trade.order.exception.TradeException;
import com.hbc.api.trade.order.mapping.gen.bean.OrderBean;
import com.hbc.api.trade.order.service.OrderQueryService;
import com.hbc.api.trade.order.service.corder.rsp.COrderBean;
import com.hbc.api.trade.order.service.corder.rsp.COrderDetailBean;
import com.hbc.api.trade.order.service.gorder.OrderStatusAdaptor;
import com.hbc.api.trade.order.service.gorder.enums.SearchType;

/**
 * @author Jongly Ran
 */
@Service
public class COrderQueryService {
	private final static Logger logger = LoggerFactory.getLogger(COrderService.class);
	
	@Autowired private COrderService			cOrderService;
	@Autowired private OrderQueryService		orderQueryService;
	
	public COrderDetailBean getOrderDetailBean(String userId, String orderNo) {
		OrderBean orderBean = getOrderBean(orderNo);
		return cOrderService.convertToCOrderDetailBean(orderBean);
	}
	
	public OrderBean getOrderBean(String orderNo) {
		if(StringUtils.isBlank(orderNo)) {
			TradeException tradeException = new TradeException(TradeReturnCodeEnum.ORDER_PARAM_FAILED,  "orderNo");
			logger.error(tradeException.getMessage());
			throw tradeException;
		}
		OrderBean orderBean = orderQueryService.getOrderByNo(orderNo);
		if(orderBean == null) {
			TradeException tradeException = new TradeException(TradeReturnCodeEnum.ORDER_ID_NOT_FOUND);
			logger.error(tradeException.getMessage());
			throw tradeException;
		}
		return orderBean;
	}

	/**
	 * @param userId
	 * @param searchType
	 * @param limit
	 * @param offset
	 * @return
	 */
	public Map<String, Object> getOrders(String userId, Integer searchType, Integer limit, Integer offset) {
		List<Integer> orderStatus = OrderStatusAdaptor.getCOrderStatusQueryType(searchType);
		SearchType searchTypeEnum = SearchType.getType(searchType);
		ListServiceRsp<OrderBean> listServiceRsp = orderQueryService.getOrdersByUserIdStatus(userId,orderStatus , true,searchTypeEnum,limit, offset);
		
		List<COrderBean> resultBean = cOrderService.convert(listServiceRsp.getDatalist(), userId);
		Map<String, Object> result = new HashMap<>(2);
		result.put("totalSize", listServiceRsp.getTsize());
		result.put("resultBean", resultBean);
		return result;
	}
}
