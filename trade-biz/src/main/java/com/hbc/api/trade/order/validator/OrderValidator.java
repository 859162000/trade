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
package com.hbc.api.trade.order.validator;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hbc.api.trade.bdata.common.TradeConstant;
import com.hbc.api.trade.bdata.common.util.ParameterValidator;
import com.hbc.api.trade.order.enums.TradeReturnCodeEnum;
import com.hbc.api.trade.order.exception.TradeException;
import com.hbc.api.trade.order.mapping.gen.bean.OrderBean;

/**
 * @author Jongly Ran
 */
public class OrderValidator extends ParameterValidator{
	private final static Logger log = LoggerFactory.getLogger(OrderValidator.class);

	/**
	 * 验证格式是否为：人数-年龄类型,人数2-年龄类型2,[人数3-年龄类型3,...]，FALSE则抛出TradeException
	 * @param orderBean
	 */
	public final static void  validateChildSeat(OrderBean orderBean) {
		if(StringUtils.isNotBlank(orderBean.getChildSeat())) {
			for(String childSeatString : orderBean.getChildSeat().split(TradeConstant.SPLITER_COMMA)) {
				if(childSeatString != null && childSeatString.split(TradeConstant.SPLITER_LINE).length != 2) {
					log.error("儿童座椅数格式不正确：应该是 人数-年龄类型,人数2-年龄类型2,[人数3-年龄类型3,...]" + orderBean.getChildSeat());
					throw new TradeException(TradeReturnCodeEnum.ORDER_PARAM_FAILED, "儿童座椅数");
				}
			}
		}
		
	}
	
	/**
	 * 验证途径城市格式是否为cityId-stayDay,cityId2-stayDay2,[cityId3-stayDa3,...]，FALSE则抛出TradeException
	 * @param orderBean
	 */
	public final static void validatePassCity(OrderBean orderBean) {
		if(StringUtils.isNotBlank(orderBean.getServicePassCity())) {
			for(String passCity : orderBean.getServicePassCity().split(TradeConstant.SPLITER_COMMA)) {
				if(passCity != null && passCity.split(TradeConstant.SPLITER_LINE).length != 2) {
					log.error("途径城市格式不正确：应该是 cityId-stayDay,cityId2-stayDay2,[cityId3-stayDa3,...]" + orderBean.getServicePassCity());
					throw new TradeException(TradeReturnCodeEnum.ORDER_PARAM_FAILED, "途径城市");
				}
			}
		}
	}

	/**
	 * @param orderBean
	 */
	public static void validateHeadCount(OrderBean orderBean) {
		if (orderBean.getChildNum() == null || orderBean.getChildNum() < 0) {
			log.error("儿童数不能为负数");
			throw new TradeException(TradeReturnCodeEnum.ORDER_PARAM_FAILED, "儿童数不能为负数|");
		}

		if(orderBean.getAdultNum() == null || orderBean.getAdultNum() < 0) {
			log.error("儿童数不能为负数，手机号：" +orderBean.getUserAreaCode1() + "-" + orderBean.getUserMobile1() + "，联系人：" + orderBean.getUserName());
			throw new TradeException(TradeReturnCodeEnum.ORDER_PARAM_FAILED, "儿童数不能为负数|");
		}

		if (orderBean.getCarSeatNum() == null || orderBean.getCarSeatNum() <= 0) {
			log.error("必须要有车辆座位数");
			throw new TradeException(TradeReturnCodeEnum.ORDER_PARAM_FAILED, "必须要有车辆座位数|");
		}

		Integer customerNum = orderBean.getAdultNum() + orderBean.getChildNum();
		
		if (customerNum <= 0)
		{
			log.error("总乘客人数不能为零");
			throw new TradeException(TradeReturnCodeEnum.ORDER_PARAM_FAILED, "总乘客人数不能为零|");
		}

		Integer useableCarSeatNum = orderBean.getCarSeatNum() - 1;
		if(customerNum > useableCarSeatNum) {
			log.error("乘客人数超额，车座共" + useableCarSeatNum + "人，本次订单成人数：" + orderBean.getAdultNum() + "，儿童数：" + orderBean.getChildNum());
			throw new TradeException(TradeReturnCodeEnum.ORDER_HEAD_COUNT_ERR);
		}
	}
}
