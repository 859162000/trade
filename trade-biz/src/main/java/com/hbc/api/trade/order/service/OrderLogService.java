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
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hbc.api.trade.order.enums.TradeReturnCodeEnum;
import com.hbc.api.trade.order.enums.bean.LogType;
import com.hbc.api.trade.order.exception.TradeException;
import com.hbc.api.trade.order.mapping.gen.OrderLogMapper;
import com.hbc.api.trade.order.mapping.gen.bean.OrderBean;
import com.hbc.api.trade.order.mapping.gen.bean.OrderLog;
import com.hbc.api.trade.order.mapping.gen.bean.OrderLogExample;
import com.hbc.api.trade.order.mapping.gen.bean.OrderLogExample.Criteria;
import com.hbc.api.trade.order.mapping.genx.xbean.OrderLogParamBean;

/**
 * @author Jongly Ran
 */
@Service
public class OrderLogService {
	private final static Logger logger = LoggerFactory.getLogger(OrderLogService.class);
	
	@Autowired
	private OrderLogMapper orderLogMapper;
	@Autowired
	private OrderQueryService orderQueryService;

	public List<OrderLog> getMisOrderLog(String orderNo) {
		OrderLogExample example = new OrderLogExample();
		Criteria criteria = example.createCriteria();
		criteria.andOrderNoEqualTo(orderNo);
		criteria.andLogTypeNotEqualTo(LogType.ADD_COMMENTS_MANUALLY.value);
		List<OrderLog> orderLogList = orderLogMapper.selectByExample(example);
		OrderBean orderBean  = orderQueryService.getOrderByNo(orderNo);
		for(OrderLog orderLog:orderLogList){
			if (StringUtils.isNoneBlank(orderLog.getContent()) && StringUtils.isNoneBlank(orderBean.getGuideName())) {
				String content = StringUtils.replace(orderLog.getContent(), "您", orderBean.getGuideName());
				orderLog.setContent(content);
			}
		}
		return orderLogList;
	}
	
	public List<OrderLog> getLeaveMsgs(String orderNo) {
		OrderLogExample example = new OrderLogExample();
		Criteria criteria = example.createCriteria();
		criteria.andOrderNoEqualTo(orderNo);
		criteria.andLogTypeEqualTo(LogType.ADD_COMMENTS_MANUALLY.value);
		return orderLogMapper.selectByExample(example);
	}
	
	public void insertOrderLog(OrderLogParamBean orderLogParamBean) {
		OrderLog record = new OrderLog();
		record.setComment(orderLogParamBean.getComment());
		record.setContent(orderLogParamBean.getContent());
		Date date = new Date();
		record.setCreateTime(date);
		record.setGuideId(orderLogParamBean.getGuideId());
		record.setGuideName(orderLogParamBean.getGuideName());
		record.setLogType(orderLogParamBean.getLogType());
		if(orderLogParamBean.getOpType() != null) {
			record.setOpType(orderLogParamBean.getOpType());
		}
		record.setOpUserId(orderLogParamBean.getOpUserId());
		record.setOpUserName(orderLogParamBean.getOpUserName());
		record.setOrderNo(orderLogParamBean.getOrderNo());
		record.setUpdateTime(date);
		try {
			orderLogMapper.insert(record);
		} catch(Exception e) {
			logger.error("写入trade_order_log失败", e);
			throw new TradeException(TradeReturnCodeEnum.FAILED_FOR_INSERT, "日志记录");
		}
	}
}
