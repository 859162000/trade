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

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.hbc.api.trade.TradeFinalStr;
import com.hbc.api.trade.bdata.common.IDGenerotor;
import com.hbc.api.trade.bdata.common.util.BeanUtilsEnhance;
import com.hbc.api.trade.bdata.common.util.TimeConverter;
import com.hbc.api.trade.bdata.mapper.basedata.gen.bean.CityBean;
import com.hbc.api.trade.bdata.mapper.basedata.gen.bean.ContinentBean;
import com.hbc.api.trade.bdata.mapper.basedata.gen.bean.CountryBean;
import com.hbc.api.trade.order.OrderPal;
import com.hbc.api.trade.order.enums.TradeReturnCodeEnum;
import com.hbc.api.trade.order.enums.bean.OperationType;
import com.hbc.api.trade.order.enums.deliver.DeliverType;
import com.hbc.api.trade.order.enums.deliver.OrderDeliverStatus;
import com.hbc.api.trade.order.enums.deliver.OrderSerialFlag;
import com.hbc.api.trade.order.enums.deliver.UrgentFlag;
import com.hbc.api.trade.order.enums.order.GoodType;
import com.hbc.api.trade.order.enums.order.GuideCommentStatus;
import com.hbc.api.trade.order.enums.order.OrderKafkaOpt;
import com.hbc.api.trade.order.enums.order.OrderLogType;
import com.hbc.api.trade.order.enums.order.OrderStatus;
import com.hbc.api.trade.order.enums.order.OrderType;
import com.hbc.api.trade.order.enums.order.SystemCommentStatus;
import com.hbc.api.trade.order.enums.order.UserCommentStatus;
import com.hbc.api.trade.order.enums.third.CarClassEnum;
import com.hbc.api.trade.order.exception.TradeException;
import com.hbc.api.trade.order.mapping.gen.OrderBeanMapper;
import com.hbc.api.trade.order.mapping.gen.bean.OrderBean;
import com.hbc.api.trade.order.mapping.genx.UpdateOrderBeanMapper;
import com.hbc.api.trade.order.mapping.genx.xbean.OrderLogParamBean;
import com.hbc.api.trade.order.service.alarm.OrderAlarmService;
import com.hbc.api.trade.order.service.kafka.TradeKafkaMsgSender;
import com.hbc.api.trade.order.validator.OrderValidator;
import com.hbc.api.trade.third.LControllerService;
import com.hbc.api.trade.third.restful.IMService;

/**
 * @author Jongly Ran
 */
public abstract class AbstractOrderService  {
	protected final static Logger log = LoggerFactory.getLogger(AbstractOrderService.class);

	@Autowired protected OrderBeanMapper 		orderBeanMapper;
	@Autowired protected UpdateOrderBeanMapper 	updateOrderBeanMapper;
	@Autowired protected TradeKafkaMsgSender 	tradeKafkaMsgSender;
	@Autowired protected IMService 				imService;

	@Autowired private LControllerService 		controllerService;
	@Autowired private OrderServiceTime 		orderServiceTime;
	@Autowired private PriceMarkService 		priceMarkService;
	@Autowired private OrderAlarmService 		orderAlarmService;
	@Autowired private OrderLogService 			orderLogService;
	

	public <T> void updateOrder(T param) {
	}
	
	@Transactional
	public <T> String createNewOrder(T param) {
		OrderBean orderBean = new OrderBean();
		BeanUtilsEnhance.copyProperties(orderBean, param);
		commonParams(orderBean, param);
		appendParams(orderBean, param);
		validateCreateNewOrder(orderBean);
		String orderNo = orderBean.getOrderNo();
		try {
			log.info("下单数据：" + JSON.toJSONString(orderBean));
			doInsertBefore(orderBean);
			orderBeanMapper.insert(orderBean);
			tradeKafkaMsgSender.sendToKafka(orderBean, OrderKafkaOpt.add);
			saveLog(orderBean, orderNo);
			insertAlarm(orderBean);
			doInsertAfter(orderBean);
			return orderNo;
		} catch (Exception e) {
			log.error("下单异常", e);
			throw new TradeException(TradeReturnCodeEnum.ORDER_ADD_LOST);
		}
	}

	/**
	 * 准备公共参数
	 * @param <T>
	 * @param orderBean
	 * @param param
	 */
	private <T> void commonParams(OrderBean orderBean, T param) {
		CityBean cityBean = buildCityInfo(orderBean);
		buildServiceTime(orderBean, cityBean);
		buildCountryInfo(orderBean, cityBean);
		
		String orderNo = OrderPal.getOrderNoPrefix(orderBean) + IDGenerotor.generateOrderNo();
		orderBean.setOrderNo(orderNo);
		orderBean.setImToken(imService.obtainIMToken(orderNo));
		orderBean.setOrderStatus(OrderStatus.INITSTATE.value);
		orderBean.setOrderStatusName(OrderStatus.INITSTATE.name);
		Date currentTime = new Date();
		orderBean.setCreateTime(currentTime);
		orderBean.setUpdateTime(currentTime);
		orderBean.setGuideId(TradeFinalStr.defaultGuideId);
		orderBean.setDeliverStatus(OrderDeliverStatus.init.value);
		orderBean.setDeliverType(DeliverType.Ordinary.value);
		orderBean.setSerialFlag(OrderSerialFlag.NORMAL.value);
		orderBean.setPriceGuide(orderBean.getPriceChannel());
		orderBean.setUserCommentStatus(UserCommentStatus.UNSCORED.value);
		orderBean.setGuideCommentStatus(GuideCommentStatus.UNSCORED.value);
		orderBean.setSystemCommentStatus(SystemCommentStatus.UNSCORED.value);
		orderBean.setPriceGuideBase(orderBean.getPriceGuide());
		orderBean.setUrgentFlag(UrgentFlag.getType(orderBean.getUrgentFlag()).value);
		orderBean.setCarSeatNum(CarClassEnum.getType(orderBean.getCarSeatNum()).value);
	}
	

	/**
	 * 追加除公共参数外的参数，如果有校验，请先处理
	 * @param <T>
	 * @param orderBean
	 * @param param
	 */
	protected <T> void appendParams(OrderBean orderBean, T param){
		
	};
	

	/**
	 * 插入订单主表后的操作，如增加参数请使用appendParam
	 * @param orderBean
	 */
	@Transactional
	protected void doInsertAfter(OrderBean orderBean) {
		
	}

	/**
	 * 插入订单主表前的操作
	 * @param orderBean
	 */
	@Transactional
	protected void doInsertBefore(OrderBean orderBean) {
		
	}

	/**
	 * 业务开始前校验当前业务所需参数是否合法
	 * @param orderBean
	 * @param param
	 */
	protected void validateCreateNewOrder(OrderBean orderBean) {
		OrderValidator.validateChildSeat(orderBean);
		OrderValidator.validatePassCity(orderBean);
		String priceMark = orderBean.getPriceMark();
		if (priceMark == null || priceMark.length() < 2) {
			throw new TradeException(TradeReturnCodeEnum.ORDER_PRICE_FAILED);
		}
		priceMarkService.verify(orderBean);
	}
	
	/**
	 * @param orderBean
	 * @param cityBean
	 */
	private void buildCountryInfo(OrderBean orderBean, CityBean cityBean) {
		CountryBean country = controllerService.getCountryById(cityBean.getCountryId());
		if (country == null) {
			log.error("国家不存在：countryId" + cityBean.getCountryId());
			throw new TradeException(TradeReturnCodeEnum.ORDER_COUNTRY_EXIST, "国家不存在");
		}
		orderBean.setServiceCountryName(country.getCountryName());
		orderBean.setServiceCountryId(country.getCountryId());

		ContinentBean continentBean = controllerService.geContinentById(country.getContinentId());
		if (continentBean == null) {
			log.error("大洲编码不存在：continentId" + country.getContinentId());
			throw new TradeException(TradeReturnCodeEnum.ORDER_Continent_EXIST, "大洲编码");
		}
		orderBean.setServiceContinentName(continentBean.getContinentName());
		orderBean.setServiceContinentId(continentBean.getContinentId());
	}

	/**
	 * @param orderBean
	 * @param orderType
	 * @param cityBean
	 */
	private void buildServiceTime(OrderBean orderBean, CityBean cityBean) {
		orderServiceTime.isValidServiceTime(orderBean, cityBean);
		OrderType orderType = OrderType.getType(orderBean.getOrderType());
		log.info("orderType:" + orderType);
		boolean hasExpectedCompTime = false;
		switch(orderType) {
		case PICKUPORDER:
		case TRANSFER:
		case PERUSE:
			hasExpectedCompTime = true;
			log.info("接送次：hasExpectedCompTime:" + hasExpectedCompTime);
			break;
		case COMMENDATION:
			log.info("精品线路：hasExpectedCompTime:" + hasExpectedCompTime);
			GoodType  goodsType = GoodType.getType(orderBean.getOrderGoodsType());
			switch(goodsType) {
			case PERUSE:
			case PICKUPORDER:
			case TRANSFER:
				hasExpectedCompTime = true;
				break;
			default:
				hasExpectedCompTime = false;
			}
			break;
		default:
			hasExpectedCompTime = false;
		}
		
		if (hasExpectedCompTime) {
			// 服务截至日期
			Date serviceDate = orderBean.getServiceTime();
			if (orderBean.getExpectedCompTime() == null) {
				orderBean.setExpectedCompTime(0);
			}
			Date endTime = new Date(serviceDate.getTime() + 60 * 1000 * orderBean.getExpectedCompTime());
			orderBean.setServiceEndTime(endTime);
		}
	}

	/**
	 * @param orderBean
	 * @param orderType
	 * @return
	 */
	private CityBean buildCityInfo(OrderBean orderBean) {
		CityBean cityBean = controllerService.getCityById(orderBean.getServiceCityId());
		if (cityBean == null) {
			throw new TradeException(TradeReturnCodeEnum.ORDER_PARAM_FAILED, "服务城市ID");
		}
		orderBean.setServiceCityName(cityBean.getCityName());
		orderBean.setServiceCityEnname(cityBean.getEnName());
		orderBean.setServiceCitySpell(cityBean.getSpell());
		if (OrderType.DAILY.value.equals(orderBean.getOrderType())) {
			CityBean cityPo = controllerService.getCityById(orderBean.getServiceEndCityid());
			if (cityPo == null) {
				log.error("日租终止城市ID不存在：serverEndCityId" + orderBean.getServiceEndCityid());
				throw new TradeException(TradeReturnCodeEnum.ORDER_CITY_EXIST, "日租终止城市");
			}
			orderBean.setServiceEndCityname(cityPo.getCityName());
		}
		return cityBean;
	}

	/**
	 * 保存Log
	 * @param orderBean
	 * @param orderType
	 * @param orderNo
	 */
	private void saveLog(OrderBean orderBean, String orderNo) {
		OrderLogParamBean orderLogParamBean = new OrderLogParamBean();
		boolean isAgentOrder = StringUtils.isBlank(orderBean.getAgentOpid());
		String opUserId = isAgentOrder ? orderBean.getUserId() : orderBean.getAgentOpid();
		String opUserName = isAgentOrder ? orderBean.getUserName() : orderBean.getAgentOpname();
		OrderType orderType = OrderType.getType(orderBean.getOrderType());
		orderLogParamBean.setContent(OrderLogType.SUBMIT_ORDER_CONTENT(orderBean.getAgentName(), opUserName, TimeConverter.formatDate(new Date()), orderType));
		orderLogParamBean.setLogType(OrderLogType.SUBMIT_ORDER.type);
		orderLogParamBean.setGuideId(opUserId);
		orderLogParamBean.setGuideName(opUserName);
		orderLogParamBean.setOpType(isAgentOrder ? OperationType.ASSISTENT.value : OperationType.CUSTOMER.value);
		orderLogParamBean.setOpUserId(opUserId);
		orderLogParamBean.setOpUserName(opUserName);
		orderLogParamBean.setOrderNo(orderNo);
		orderLogService.insertOrderLog(orderLogParamBean);
	}
	

	/**
	 * 消息推送
	 * @param orderBean
	 */
	private void insertAlarm(OrderBean orderBean){
		orderAlarmService.insertBeforeLeave(orderBean);
	}
}
