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
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hbc.api.trade.bdata.common.rsp.ReturnResult;
import com.hbc.api.trade.bdata.common.util.BeanUtilsEnhance;
import com.hbc.api.trade.order.controller.query.vo.MISOrderDetail;
import com.hbc.api.trade.order.controller.validator.OrderValidator;
import com.hbc.api.trade.order.mapping.gen.bean.OrderBean;
import com.hbc.api.trade.order.mapping.gen.bean.TradeOrderTrack;
import com.hbc.api.trade.order.mapping.genx.xbean.GDSOrderQueryBean;
import com.hbc.api.trade.order.mapping.genx.xbean.PaymentDetail;
import com.hbc.api.trade.order.service.GDSOrderQueryService;
import com.hbc.api.trade.order.service.OrderQueryService;
import com.hbc.api.trade.order.service.OrderTrackService;
import com.hbc.api.trade.order.service.gorder.OrderStatusAdaptor;
import com.hbc.api.trade.order.service.rsp.OrderTrack;

/**
 * @author Jongly Ran
 */
@RestController
@RequestMapping("trade")
public class GDSOrderQueryController {

	@Autowired
	private OrderQueryService orderQueryService;
	@Autowired
	private GDSOrderQueryService gdsOrderQueryService;

	@RequestMapping(value = "v1.0/ca/order/list/by/opuserid",
			method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public ReturnResult getOrdersByOpUserId(GDSOrderQueryBean queryBean) {
		OrderValidator.validateParamString(queryBean.getAgentOpid(), "代理商操作员ID");
		List<OrderBean> orderBeans = gdsOrderQueryService.selectOrderForGDS(queryBean);
		Integer totalSize = gdsOrderQueryService.selectOrderForGDSTotalSize(queryBean);
		ReturnResult returnResult = new ReturnResult();
		Map<String, Object> result = new HashMap<>(3);
		result.put("totalSize", totalSize);
		result.put("resultBean", orderBeans);
		returnResult.setData(result);
		return returnResult;
	}

	@RequestMapping(value = "v1.0/ca/order/list",
			method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public ReturnResult getOrders(GDSOrderQueryBean queryBean) {
		OrderValidator.validateParamString(queryBean.getAgentId(), "代理商ID");
		OrderValidator.validateLimitAndOffset(queryBean.getLimit(), queryBean.getOffset());
		queryBean.setOrderStatus(OrderStatusAdaptor.getGDSOrderStatusQueryType(queryBean.getSearchType()));
		List<OrderBean> orderBeans = gdsOrderQueryService.selectOrderForGDS(queryBean);
		Integer totalSize = gdsOrderQueryService.selectOrderForGDSTotalSize(queryBean);
		Integer doneSize = gdsOrderQueryService.getDoneSize(queryBean); //BUGFIX(http://bug.hbc.tech/mantis/view.php?id=905)
		Integer initialSize = gdsOrderQueryService.getInitalSize(queryBean);//BUGFIX(http://bug.hbc.tech/mantis/view.php?id=905)
		ReturnResult returnResult = new ReturnResult();
		Map<String, Object> result = new HashMap<>(3);
		result.put("totalSize", totalSize);
		result.put("resultBean", orderBeans);
		result.put("doneSize", doneSize);
		result.put("initialSize", initialSize);
		returnResult.setData(result);
		return returnResult;
	}

	@RequestMapping(value = "v1.0/ca/order/detail", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public ReturnResult getOrder(String orderNo) {
		ReturnResult returnResult = new ReturnResult();
		OrderBean orderDetail = orderQueryService.getOrderByNo(orderNo);
		MISOrderDetail misOrderDetail = new MISOrderDetail();
		BeanUtilsEnhance.copyProperties(misOrderDetail, orderDetail);
		setOrderTrack(orderNo, misOrderDetail);
		
		returnResult.setData(misOrderDetail);
		return returnResult;
	}
	@Autowired
	OrderTrackService orderTrackService;
	private void setOrderTrack(String orderNo, MISOrderDetail misOrderDetail) {
		List<TradeOrderTrack> orderTracks = orderTrackService.getOrderTrackRecords(orderNo);
		if (orderTracks != null && orderTracks.size() > 0) {
			List<OrderTrack> orderTrackList = new LinkedList<>();
			for (TradeOrderTrack orderTrackParam : orderTracks) {
				OrderTrack orderTrackBean = new OrderTrack();
				orderTrackBean.setCreateTime(orderTrackParam.getCreateTime());
				orderTrackBean.setTrackDesc(orderTrackParam.getTrackDesc());
				orderTrackList.add(orderTrackBean);
			}
			misOrderDetail.setOrderTracks(orderTrackList);
		}
	}
	
	@RequestMapping(value = "v1.0/ca/order/payment/detail", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public ReturnResult getOrderPaymentDetail(@RequestParam(required = true) String orderNo, HttpServletRequest request) {
		PaymentDetail paymentDetail = orderQueryService.getOrderPaymentDetail(orderNo);
		ReturnResult returnResult = new ReturnResult();
		returnResult.setData(paymentDetail);
		return returnResult;
	}

}
