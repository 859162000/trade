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
package com.hbc.api.trade.order.service;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hbc.api.trade.bdata.common.util.TimeConverter;
import com.hbc.api.trade.order.enums.TradeReturnCodeEnum;
import com.hbc.api.trade.order.enums.bean.OperationType;
import com.hbc.api.trade.order.enums.order.OrderKafkaOpt;
import com.hbc.api.trade.order.enums.order.OrderLogType;
import com.hbc.api.trade.order.enums.order.OrderStatus;
import com.hbc.api.trade.order.enums.order.OrderType;
import com.hbc.api.trade.order.exception.TradeException;
import com.hbc.api.trade.order.mapping.gen.bean.OrderBean;
import com.hbc.api.trade.order.mapping.genx.UpdateOrderBeanMapper;
import com.hbc.api.trade.order.mapping.genx.xbean.GOrderAdditionalCostParamBean;
import com.hbc.api.trade.order.mapping.genx.xbean.OrderLogParamBean;
import com.hbc.api.trade.order.service.kafka.TradeKafkaMsgSender;
import com.hbc.api.trade.third.push.CPushService;

/**
 * @author Jongly Ran
 */
@Service
public class GOptOrderService {
	@Autowired
	private AdditionalCostService additionalCostService;
	@Autowired
	private OrderTrackService orderTrackService;
	@Autowired
	private OrderLogService orderLogService;
	@Autowired
	private UpdateOrderBeanMapper updateOrderBeanMapper;
	@Autowired
	private TradeKafkaMsgSender tradeKafkaMsgSender;

	@Autowired
	private CPushService cpushService;

	private final static Logger log = LoggerFactory.getLogger(GOptOrderService.class);

	@Transactional
	public void guideArrive(String orderNo, String guideId, String guideName) {
		orderTrackService.guideArrived(orderNo);
		this.updateOrderStatus(orderNo, OrderStatus.GUIDE_ARRIVED);

		OrderLogParamBean orderLogParamBean = new OrderLogParamBean();
		orderLogParamBean.setContent(OrderLogType.ORDER_STATUS_CONTENT(guideName, TimeConverter.formatDate(new Date()), OrderStatus.GUIDE_ARRIVED));
		orderLogParamBean.setGuideId(guideId);
		orderLogParamBean.setGuideName(guideName);
		orderLogParamBean.setLogType(OrderLogType.GUIDE_ARRIVE.type);
		orderLogParamBean.setOpType(OperationType.GUIDE.value);
		orderLogParamBean.setOpUserId(guideId);
		orderLogParamBean.setOpUserName(guideName);
		orderLogParamBean.setOrderNo(orderNo);
		orderLogService.insertOrderLog(orderLogParamBean);
	}
	
	/**
	 * @param orderNo
	 */
	public void receiveGuest(String orderNo) {
		orderTrackService.pickedupCustomer(orderNo);
		this.updateOrderStatus(orderNo, OrderStatus.PICK_CUSTOMER);
	}

	@Transactional
	public void confirmcost(OrderBean orderBean, GOrderAdditionalCostParamBean additionalCostParam, String guideId, String guideName) {

		if (additionalCostParam.getAdditionalCostList() != null) {
			additionalCostService.saveCostAndCostDetail(additionalCostParam, guideId, guideName);
			double additionalFee = additionalCostParam.getApplyPrice();
			if (orderBean.getOrderType() != OrderType.COMMENDATION.value && additionalFee != 0)
			{
				cpushService.additionalFeeConfirm(orderBean, additionalFee);
			}
		}

		this.updateOrderStatus(additionalCostParam.getOrderNo(), OrderStatus.CONFIRMED_COST);
		orderTrackService.confirmedCost(orderBean.getOrderNo());

		// DB log
		OrderLogParamBean orderLogParamBean = new OrderLogParamBean();
		orderLogParamBean.setContent(OrderLogType.ORDER_STATUS_CONTENT(guideName, TimeConverter.formatDate(new Date()), OrderStatus.CONFIRMED_COST));
		orderLogParamBean.setGuideId(guideId);
		orderLogParamBean.setGuideName(guideName);
		orderLogParamBean.setLogType(OrderLogType.CONFIRMED_COST.type);
		orderLogParamBean.setOpType(OperationType.GUIDE.value);
		orderLogParamBean.setOpUserId(guideId);
		orderLogParamBean.setOpUserName(guideName);
		orderLogParamBean.setOrderNo(additionalCostParam.getOrderNo());
		orderLogService.insertOrderLog(orderLogParamBean);
	}
	
	@Transactional
	public void complete(String orderNo, String guideId, String guideName) {
		updateOrderStatus(orderNo, OrderStatus.STROKE_END);
		orderTrackService.complete(orderNo);

		OrderLogParamBean orderLogParamBean = new OrderLogParamBean();
		orderLogParamBean.setContent(OrderLogType.ORDER_STATUS_CONTENT(guideName, TimeConverter.formatDate(new Date()), OrderStatus.STROKE_END));
		orderLogParamBean.setGuideId(guideId);
		orderLogParamBean.setGuideName(guideName);
		orderLogParamBean.setLogType(OrderLogType.GUIDE_COMPLETE.type);
		orderLogParamBean.setOpType(OperationType.GUIDE.value);
		orderLogParamBean.setOpUserId(guideId);
		orderLogParamBean.setOpUserName(guideName);
		orderLogParamBean.setOrderNo(orderNo);
		orderLogService.insertOrderLog(orderLogParamBean);
	}

	@Transactional
	public void updateOrderStatus(String orderNo, OrderStatus orderStatus) {
		log.info(orderNo + " start to update orderstatus to [" + orderStatus.name + "]");
		OrderBean orderBean = updateOrderBeanMapper.forupdateByOrderNo(orderNo);
		if (orderBean == null) {
			log.error("订单不存在，订单号：" + orderNo);
			throw new TradeException(TradeReturnCodeEnum.ORDER_NOTEXIST, orderNo);
		}
		if (orderBean.getOrderStatus().equals(orderStatus.value)) {
			log.error("DB 订单状态 已经是 [" + OrderStatus.getStatus(orderBean.getOrderStatus()).name + "] 订单状态异常，订单号：" + orderNo);
			throw new TradeException(TradeReturnCodeEnum.ORDER_NONEED_UPDATE);
		}
		int ors = updateOrderBeanMapper.updateOrderStatus(orderBean.getOrderNo(), orderBean.getOrderStatus(), orderStatus.value, orderStatus.name);
		if (ors > 0) {
			log.info("订单更新成功，where条件：orderNo：" + orderNo + ", oldOrderStatus: " + orderBean.getOrderStatus() + ", newOrderStatus:" + orderStatus.value + ",newOrderStatusValue:" + orderStatus.name);
			tradeKafkaMsgSender.sendToKafka(orderBean, OrderKafkaOpt.statechange);
		} else {
			log.error("订单未更新，where条件：orderNo：" + orderNo + ", oldOrderStatus: " + orderBean.getOrderStatus() + ", newOrderStatus:" + orderStatus.value + ",newOrderStatusValue:" + orderStatus.name);
			throw new TradeException(TradeReturnCodeEnum.ORDER_UPDATE_FAILED);
		}
		log.info(orderNo + " end to update orderstatus to [" + orderStatus.name + "]");
	}
}
