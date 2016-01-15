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

import com.alibaba.fastjson.JSON;
import com.hbc.api.trade.bdata.common.util.TimeConverter;
import com.hbc.api.trade.order.enums.TradeReturnCodeEnum;
import com.hbc.api.trade.order.enums.bean.OperationType;
import com.hbc.api.trade.order.enums.order.OrderLogType;
import com.hbc.api.trade.order.enums.order.OrderSource;
import com.hbc.api.trade.order.enums.order.OrderStatus;
import com.hbc.api.trade.order.enums.order.SystemCommentStatus;
import com.hbc.api.trade.order.enums.third.CommentTypeEnum;
import com.hbc.api.trade.order.exception.TradeException;
import com.hbc.api.trade.order.mapping.gen.bean.OrderBean;
import com.hbc.api.trade.order.mapping.genx.xbean.AppraiseGuideParam;
import com.hbc.api.trade.order.mapping.genx.xbean.OrderLogParamBean;
import com.hbc.api.trade.order.mapping.genx.xbean.TradePriceHistoryParam;
import com.hbc.api.trade.third.restful.GuideRESTfulService;

/**
 * @author Jongly Ran
 */
@Service
public class MISOptOrderService extends OrderService{
	@Autowired private UpdateOrderService		updateOrderService;
	@Autowired private OrderQueryService		orderQueryService;
	@Autowired private TradePriceHistoryService tradePriceHistoryService;
	@Autowired private OrderLogService			orderLogService;
	@Autowired private GuideRESTfulService		guideRESTfulService;
	private static final Logger logger = LoggerFactory.getLogger(MISOptOrderService.class);
	/* (non-Javadoc)
	 * @see com.hbc.api.trade.order.service.OrderService#addOrder(com.hbc.api.trade.order.mapping.gen.bean.OrderBean)
	 */
	@Override
	public String addOrder(OrderBean orderBean) {
		orderBean.setOrderSource(OrderSource.OTA.value);
		return super.addOrder(orderBean);
	}

	@Transactional
	public void updateDailyOrder(OrderBean orderBean,String serviceRecTime) {
		updateOrderService.updateDailyOrder(orderBean, serviceRecTime);
	}
	
	@Transactional
	public void updatePickUpOrder(OrderBean orderBean) {
		updateOrderService.updatePickUpOrder(orderBean);
	}
	
	
	@Transactional
	public void updateSingleOrder(OrderBean orderBean) {
		updateOrderService.updateSingleOrder(orderBean);
	}
	
	@Transactional
	public void updateTransferOrder(OrderBean orderBean) {
		updateOrderService.updateTransferOrder(orderBean);
	}
	
	@Transactional
	public void updateGuidePrice(TradePriceHistoryParam priceHistoryBeanParam) {
		OrderBean tempBean = updateOrderBeanMapper.forupdateByOrderNo(priceHistoryBeanParam.getOrderNo());
		boolean hasNotPay = OrderStatus.getStatus(tempBean.getOrderStatus()).equals(OrderStatus.INITSTATE);
		//boolean hasAlreadyPreAssignedGuide = StringUtils.isNotBlank(tempBean.getGuidePreId());
		if (hasNotPay) {
			if(updateOrderBeanMapper.updateGuidePrice(priceHistoryBeanParam.getOrderNo(), priceHistoryBeanParam.getTargetPrice(), tempBean.getPriceGuide() ) == 0 ) {
				log.error("修改导游价失败，参数：" + JSON.toJSONString(priceHistoryBeanParam));
				throw new TradeException(TradeReturnCodeEnum.FAILED_FOR_UPDATE, "修改导游价");
			}
			tradePriceHistoryService.recordPriceHistory(priceHistoryBeanParam);
			
			// log
			OrderLogParamBean orderLogParamBean = new OrderLogParamBean();
			orderLogParamBean.setContent(OrderLogType.UPDATE_PRICE_ALREADY_PAYED_CONTENT("MIS", priceHistoryBeanParam.getOpUname(),
					TimeConverter.formatDate(new Date()), priceHistoryBeanParam.getTargetPrice(), priceHistoryBeanParam.getSourcePrice()));
			orderLogParamBean.setLogType(OrderLogType.UPDATE_PRICE_ALREADY_PAYED.type);
			orderLogParamBean.setOpType(OperationType.CUSTOMER_SERVICE.value);
			orderLogParamBean.setOpUserId(priceHistoryBeanParam.getOpUid());
			orderLogParamBean.setOpUserName(priceHistoryBeanParam.getOpUname());
			orderLogParamBean.setOrderNo(priceHistoryBeanParam.getOrderNo());
			orderLogService.insertOrderLog(orderLogParamBean );
		}
	}
	@Autowired
	OrderTrackService orderTrackService;
	@Transactional
	public void dispute(String orderNo,String opUserName,String opUserId){
		OrderBean orderBean = orderQueryService.getOrderByNo(orderNo);
		OrderStatus ostatus = OrderStatus.getStatus(orderBean.getOrderStatus());
		if (ostatus == OrderStatus.DISPUTING) {
			throw new TradeException(TradeReturnCodeEnum.ORDER_ALREADY_DISPUTED, orderNo);
		}

		if (ostatus.equals(OrderStatus.PAYSUCCESS) || ostatus.equals(OrderStatus.GUIDE_ARRIVED) || ostatus.equals(OrderStatus.PICK_CUSTOMER) || ostatus.equals(OrderStatus.STROKE_END)) {
			updateOrderStatus(orderNo, ostatus, OrderStatus.DISPUTING);
			OrderLogParamBean orderLogParamBean = new OrderLogParamBean();
			orderLogParamBean.setContent(OrderLogType.DISPUTE_CONTENT(opUserName, TimeConverter.formatDate(new Date())));
			orderLogParamBean.setOpUserId(opUserId);
			orderLogParamBean.setLogType(OrderLogType.DISPUTE.type);
			orderLogParamBean.setOpType(OperationType.CUSTOMER_SERVICE.value);
			orderLogParamBean.setOpUserId(opUserId);
			orderLogParamBean.setOpUserName(opUserName);
			orderLogParamBean.setOrderNo(orderNo);
			orderLogService.insertOrderLog(orderLogParamBean);
			orderTrackService.orderFrozed(orderNo);
		}
		logger.error("订单：" + orderNo + "状态不对，但此时为" + ostatus.value + "(" + ostatus.name + ")");
	}
	@Transactional
	public void appraiseGuide(AppraiseGuideParam param) {
		OrderBean orderBean = orderQueryService.getOrderByNo(param.getOrderNo());
		OrderStatus orderStatus = OrderStatus.getStatus(orderBean.getOrderStatus());
		Boolean appraisable = null;
		switch(orderStatus) {
			case CONFIRMED_COST:// 费用确认
			case SETTLEMENT: 	// 结算完成
				appraisable = true;
				break;
			default:
				appraisable = false;
		}
		
		boolean hasNotBeenAppraised = SystemCommentStatus.getType(orderBean.getSystemCommentStatus()).equals(SystemCommentStatus.UNSCORED);
		if(appraisable && hasNotBeenAppraised ) {
			updateOrderBeanMapper.updateToAlreadySystemAppraised(orderBean.getOrderNo());
			param.setCommentType(CommentTypeEnum.FROM_CUSTOMER_SERVICE.value);
			guideRESTfulService.saveAppraiseContent(param, orderBean.getOrderType());
		} else {
			log.error("订单当前状态不能被评价, :" + JSON.toJSONString(orderBean));
			throw new TradeException(TradeReturnCodeEnum.ORDER_CANNOT_BE_APPRAISED);
		}
	}
}
