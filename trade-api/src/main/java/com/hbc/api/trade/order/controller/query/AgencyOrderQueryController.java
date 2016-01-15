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
package com.hbc.api.trade.order.controller.query;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hbc.api.trade.bdata.common.rsp.ReturnResult;
import com.hbc.api.trade.order.controller.validator.OrderValidator;
import com.hbc.api.trade.order.enums.order.AgencyOrderStatus;
import com.hbc.api.trade.order.mapping.gen.bean.OrderBean;
import com.hbc.api.trade.order.mapping.genx.xbean.AgencyOrderQueryBean;
import com.hbc.api.trade.order.service.AgencyOrderQueryService;
import com.hbc.api.trade.order.service.OrderQueryService;
import com.hbc.api.trade.order.service.gorder.OrderStatusAdaptor;

/**
 * 地接社查询接口
 * @author Jongly Ran
 */
@RestController
@RequestMapping("trade")
public class AgencyOrderQueryController {

	@Autowired private OrderQueryService 		orderQueryService;
	@Autowired private AgencyOrderQueryService 	agencyOrderQueryService;

	@RequestMapping(value = "v1.0/gb/order/list", 
			method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public ReturnResult getOrders(AgencyOrderQueryBean  queryBean, HttpServletRequest request) {
		OrderValidator.validateLimitAndOffset(queryBean.getLimit(), queryBean.getOffset());
		queryBean.setOrderStatus(OrderStatusAdaptor.getAgencyOrderStatusQueryType(queryBean.getSearchType()));
		ReturnResult returnResult = new ReturnResult();
		returnResult.setData(agencyOrderQueryService.selectOrderForAgency(queryBean));
		return returnResult;
	}
	
	@RequestMapping(value = "v1.0/gb/order/detail", 
			method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public ReturnResult getOrder(@RequestParam(required=true) String orderNo, HttpServletRequest request) {
		ReturnResult returnResult = new ReturnResult();
		returnResult.setData(orderQueryService.getOrderByNo(orderNo));
		return returnResult;
	}
	
	@RequestMapping(value="v1.0/gb/order/servicing/size")
	public ReturnResult getServicingOrderTotalSize(@RequestParam(required=true) String agencyId, HttpServletRequest request) {
		AgencyOrderQueryBean queryBean = new AgencyOrderQueryBean();
		queryBean.setAgencyId(agencyId);
		queryBean.setOrderStatus(OrderStatusAdaptor.getAgencyOrderStatusQueryType(AgencyOrderStatus.SERVICING.value));
		int totalSize = agencyOrderQueryService.selectOrderForAgencyTotalSize(queryBean);
		ReturnResult result = new ReturnResult();
		Map<String, Integer> resultMap = new HashMap<>(1);
		resultMap.put("totalSize", totalSize);
		result.setData(resultMap);
		return result;
	}

	@RequestMapping(value = "v1.0/gb/order/user", 
			method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public ReturnResult getUserInfo(@RequestParam(required=true) String orderNo, HttpServletRequest request) {
		OrderBean orderBean = orderQueryService.getOrderByNo(orderNo); // 已处理null
		ReturnResult returnResult = new ReturnResult();
		Map<String, String> resultBean = new HashMap<>(2);
		resultBean.put("userId", orderBean.getUserId());
		resultBean.put("userName", orderBean.getUserName());
		returnResult.setData(resultBean);
		return returnResult;
	}
}
