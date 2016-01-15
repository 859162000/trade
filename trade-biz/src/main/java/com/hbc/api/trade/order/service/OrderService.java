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
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.hbc.api.trade.TradeFinalStr;
import com.hbc.api.trade.bdata.common.IDGenerotor;
import com.hbc.api.trade.bdata.common.util.TimeConverter;
import com.hbc.api.trade.bdata.mapper.basedata.gen.bean.CityBean;
import com.hbc.api.trade.bdata.mapper.basedata.gen.bean.ContinentBean;
import com.hbc.api.trade.bdata.mapper.basedata.gen.bean.CountryBean;
import com.hbc.api.trade.bdata.mapper.coup.gen.bean.CouponBean;
import com.hbc.api.trade.order.OrderPal;
import com.hbc.api.trade.order.enums.TradeReturnCodeEnum;
import com.hbc.api.trade.order.enums.bean.OperationType;
import com.hbc.api.trade.order.enums.deliver.DeliverType;
import com.hbc.api.trade.order.enums.deliver.IsReadable;
import com.hbc.api.trade.order.enums.deliver.OrderDeliverStatus;
import com.hbc.api.trade.order.enums.deliver.OrderSerialFlag;
import com.hbc.api.trade.order.enums.deliver.UrgentFlag;
import com.hbc.api.trade.order.enums.order.CarGroupFlag;
import com.hbc.api.trade.order.enums.order.FlightIsCustom;
import com.hbc.api.trade.order.enums.order.GoodType;
import com.hbc.api.trade.order.enums.order.GuideCommentStatus;
import com.hbc.api.trade.order.enums.order.OrderKafkaOpt;
import com.hbc.api.trade.order.enums.order.OrderLogType;
import com.hbc.api.trade.order.enums.order.OrderSource;
import com.hbc.api.trade.order.enums.order.OrderStatus;
import com.hbc.api.trade.order.enums.order.OrderType;
import com.hbc.api.trade.order.enums.order.SystemCommentStatus;
import com.hbc.api.trade.order.enums.order.UserCommentStatus;
import com.hbc.api.trade.order.enums.order.VisaType;
import com.hbc.api.trade.order.enums.ota.AgentChannelEnum;
import com.hbc.api.trade.order.exception.TradeException;
import com.hbc.api.trade.order.mapping.gen.OrderBeanMapper;
import com.hbc.api.trade.order.mapping.gen.bean.OrderBean;
import com.hbc.api.trade.order.mapping.gen.bean.OrderBeanExample;
import com.hbc.api.trade.order.mapping.genx.UpdateOrderBeanMapper;
import com.hbc.api.trade.order.mapping.genx.xbean.OrderLogParamBean;
import com.hbc.api.trade.order.service.alarm.OrderAlarmService;
import com.hbc.api.trade.order.service.kafka.TradeKafkaMsgSender;
import com.hbc.api.trade.order.validator.OrderValidator;
import com.hbc.api.trade.pay.mapping.gen.bean.TradePayment;
import com.hbc.api.trade.settle.enums.AccountEnums;
import com.hbc.api.trade.third.CouponServiceParty;
import com.hbc.api.trade.third.LControllerService;
import com.hbc.api.trade.third.UserFundAccountService;
import com.hbc.api.trade.third.restful.IMService;
import com.hbc.api.trade.third.restful.UCenterService;
import com.hbc.api.trade.third.restful.UCenterService.Entity;

/**
 * @author Jongly Ran
 */
@Service
public class OrderService {
	protected final static Logger log = LoggerFactory.getLogger(OrderService.class);

	@Autowired
	protected OrderBeanMapper orderBeanMapper;
	@Autowired
	protected LControllerService controllerService;
	@Autowired
	protected OrderLogService orderLogService;
	@Autowired
	protected OrderServiceTime orderServiceTime;

	@Autowired
	protected UpdateOrderBeanMapper updateOrderBeanMapper;
	@Autowired
	protected TradeKafkaMsgSender tradeKafkaMsgSender;
	@Autowired
	protected CouponServiceParty couponServiceParty;
	@Autowired
	protected UserFundAccountService userFundAccountService;
	@Autowired
	private IMService imService;
	@Autowired
	PriceMarkService priceMarkService;

	@Autowired
	private UCenterService ucenterService;

	@Transactional
	public void updateOrderStatus(String orderNo, OrderStatus sourceOrderStatus, OrderStatus orderStatus) {
		log.info(orderNo + " start to update orderstatus to [" + orderStatus.name + "]");
		OrderBean orderBean = updateOrderBeanMapper.forupdateByOrderNo(orderNo);
		if (orderBean == null) {
			log.error("【更新订单】订单不存在，订单号：" + orderNo);
			throw new TradeException(TradeReturnCodeEnum.ORDER_NOTEXIST, orderNo);
		}
		if (orderBean.getOrderStatus().equals(orderStatus.value)) {
			log.error("【更新订单】DB 订单状态 已经是 [" + OrderStatus.getStatus(orderBean.getOrderStatus()).name + "] 订单状态异常，订单号：" + orderNo);
			throw new TradeException(TradeReturnCodeEnum.ORDER_NONEED_UPDATE);
		}
		int ors = updateOrderBeanMapper.updateOrderStatus(orderBean.getOrderNo(), orderBean.getOrderStatus(), orderStatus.value, orderStatus.name);
		if (ors > 0) {
			log.info("【更新订单】订单更新成功，where条件：orderNo：" + orderNo + ", oldOrderStatus: " + orderBean.getOrderStatus() + ", newOrderStatus:" + orderStatus.value + ",newOrderStatusValue:" + orderStatus.name);
			tradeKafkaMsgSender.sendToKafka(orderBean, OrderKafkaOpt.statechange);
		} else {
			log.error("【更新订单】订单未更新，where条件：orderNo：" + orderNo + ", oldOrderStatus: " + orderBean.getOrderStatus() + ", newOrderStatus:" + orderStatus.value + ",newOrderStatusValue:" + orderStatus.name);
			throw new TradeException(TradeReturnCodeEnum.ORDER_UPDATE_FAILED);
		}
		log.info("【更新订单】" + orderNo + " end to update orderstatus to [" + orderStatus.name + "]");
	}

	@Transactional
	public void updateOrderCancleTime(String orderNo) {
		OrderBean orderBean = new OrderBean();
		orderBean.setOrderNo(orderNo);
		orderBean.setCancelTime(new Date());
		orderBeanMapper.updateByPrimaryKeySelective(orderBean);
	}
	
	@Transactional
	public void resetSerOrder(OrderBean orderBean) {
		//serialFlag
		if(OrderSerialFlag.SERIAL.value.equals(orderBean.getSerialFlag())){
			OrderBean record = new OrderBean();
			record.setOrderNo(orderBean.getOrderNo());
			record.setSerialFlag(OrderSerialFlag.NORMAL.value);
			record.setSerialOrderNo("");
			orderBeanMapper.updateByPrimaryKeySelective(record);
			log.info("重置 串单标识	"+orderBean.getOrderNo()+"	成功");
			if(orderBean.getSerialOrderNo()!=null && orderBean.getSerialOrderNo().length()>0){
				String serNo = orderBean.getSerialOrderNo();
				
				OrderBean sdrecord = new OrderBean();
				sdrecord.setOrderNo(serNo);
				sdrecord.setSerialFlag(OrderSerialFlag.NORMAL.value);
				sdrecord.setSerialOrderNo("");
				orderBeanMapper.updateByPrimaryKeySelective(sdrecord);
				
				log.info("重置 被串订单	"+serNo+"	成功");
			}
		}
	}
	
	
	
	

	@Transactional
	public String addOrder(OrderBean orderBean) {
		OrderValidator.validateChildSeat(orderBean);
		OrderValidator.validatePassCity(orderBean);
		CityBean cityBean = controllerService.getCityById(orderBean.getServiceCityId());
		orderServiceTime.isValidServiceTime(orderBean, cityBean);
		// OrderValidator.validateHeadCount(orderBean);
		return addOrderProccess(orderBean);
	}

	@Transactional
	public String addOrderProccess(OrderBean orderBean) {
		CityBean cityBean = buildCityInfo(orderBean);
		buildServiceTime(orderBean, cityBean);
		buildCountryInfo(orderBean, cityBean);

		String orderNo = OrderPal.getOrderNoPrefix(orderBean) + IDGenerotor.generateOrderNo();
		if (OrderSource.OTA.value.equals(orderBean.getOrderSource())) {
			if (StringUtils.isBlank(orderBean.getUserId())) {
				Entity entity = ucenterService.obtainUserFundAccount(orderBean.getUserAreaCode1(), orderBean.getUserMobile1(), orderBean.getAgentId(), orderBean.getAgentName(), orderBean.getOrderChannel());
				orderBean.setUserId(entity.getUserId());
			}

			Integer orderChannel = orderBean.getOrderChannel();
			if (AgentChannelEnum.CTRIP_CHANNEL.value.equals(orderChannel)) {
				orderBean.setUserAccount(AccountEnums.XIECHEN_ACCOUNT.value);
			} else if (AgentChannelEnum.QUNAR_CHANNEL.value.equals(orderChannel)) {
				orderBean.setUserAccount(AccountEnums.QUNA_ACCOUNT.value);
			} else if (AgentChannelEnum.QUA_CHANNEL.value.equals(orderChannel)) {
				orderBean.setUserAccount(AccountEnums.QUA_ACCOUNT.value);
			} else {
				log.error("渠道资金账户不支持，orderChannel=" + orderChannel);
				throw new TradeException(TradeReturnCodeEnum.CHANNEL_FUND_ACCOUNT_NOT_SUPPORT);
			}
		} else {
			orderBean.setImToken(imService.obtainIMToken(orderNo));
			if (StringUtils.isBlank(orderBean.getUserId())) {
				Entity entity = ucenterService.obtainUserFundAccount(orderBean.getUserAreaCode1(), orderBean.getUserMobile1(), orderBean.getAgentId(), orderBean.getAgentName(), orderBean.getOrderChannel());
				orderBean.setUserId(entity.getUserId());
				orderBean.setUserAccount(entity.getAccountNo());
			} else {
				orderBean.setUserAccount(userFundAccountService.getUserAccount(orderBean.getUserId()));
			}
		}
		orderBean.setOrderNo(orderNo);
		buildStaticOrderBody(orderBean);
		if (OrderSource.OTA.value.equals(orderBean.getOrderSource())) {
			try {
				priceMarkService.verify(orderBean);
			} catch (Exception e) {
				orderBean.setPriceBase(orderBean.getPriceChannel());
				orderBean.setPriceGuide(orderBean.getPriceChannel());
				orderBean.setPriceGuideBase(orderBean.getPriceChannel());
				orderBean.setPriceTicket(orderBean.getPriceChannel());
				log.error("下单查价异常，直接兼容：", e);
			}
		} else {
			OrderBean orderCurBean = getCurSubOrder(orderBean);
			if(orderCurBean!=null){
				return orderCurBean.getOrderNo();
			}
			priceMarkService.verify(orderBean);
		}
		log.info("【下单】入库数据：" + JSON.toJSONString(orderBean));
		try {
			orderBeanMapper.insert(orderBean);
			tradeKafkaMsgSender.sendToKafka(orderBean, OrderKafkaOpt.add);
			saveLog(orderBean, orderNo);
			insertAlarm(orderBean);
		} catch (Exception e) {
			log.error("【下单】失败", e);
			throw new TradeException(TradeReturnCodeEnum.FAILED_FOR_INSERT, "下单");
		}
		log.info("【下单】成功, orderNO=" + orderNo);
		return orderNo;
	}

	/**
	 * 在非OTA情况下，需要查询最近一次的 当前时间2秒内的订单，若有则直接返回给前端
	 * 
	 * @return
	 */
	public OrderBean getCurSubOrder(OrderBean orderBean) {
		OrderBeanExample orderBeanExample = new OrderBeanExample();
		OrderBeanExample.Criteria criteria = orderBeanExample.createCriteria();
		criteria.andUserIdEqualTo(orderBean.getUserId());
		Date date = new Date(System.currentTimeMillis() - 3 * 1000);
		criteria.andCreateTimeGreaterThan(date);
		criteria.andCarSeatNumEqualTo(orderBean.getCarSeatNum());
		criteria.andCarTypeIdEqualTo(orderBean.getCarTypeId());
		criteria.andOrderTypeEqualTo(orderBean.getOrderType());
		criteria.andServiceCityIdEqualTo(orderBean.getServiceCityId());
		List<OrderBean> olist = orderBeanMapper.selectByExample(orderBeanExample);
		
		if(olist.size()>0){
			return olist.get(0);
		}else{
			return null;
		}
	}

	/**
	 * @param orderBean
	 * @param orderNo
	 */
	private void buildStaticOrderBody(OrderBean orderBean) {
		orderBean.setPriceGuideBase(orderBean.getPriceGuide());
		orderBean.setOrderStatus(OrderStatus.INITSTATE.value);
		orderBean.setOrderStatusName(OrderStatus.INITSTATE.name);
		Date currentTime = new Date();
		orderBean.setCreateTime(currentTime);
		orderBean.setUpdateTime(currentTime);
		orderBean.setGuideId(TradeFinalStr.defaultGuideId);
		orderBean.setDeliverStatus(OrderDeliverStatus.init.value);
		orderBean.setDeliverType(DeliverType.Ordinary.value);
		orderBean.setPriceGuide(orderBean.getPriceChannel());
		orderBean.setUserCommentStatus(UserCommentStatus.UNSCORED.value);
		orderBean.setGuideCommentStatus(GuideCommentStatus.UNSCORED.value);
		orderBean.setSystemCommentStatus(SystemCommentStatus.UNSCORED.value);
		orderBean.setFlightIsCustom(FlightIsCustom.NORMAL.value);
		orderBean.setSerialFlag(orderBean.getSerialFlag() == null ? OrderSerialFlag.NORMAL.value : orderBean.getSerialFlag());
		orderBean.setIsArrivalVisa(orderBean.getIsArrivalVisa() == null ? VisaType.UNDEFINED.value : orderBean.getIsArrivalVisa());
		orderBean.setCargroupFlag(orderBean.getCargroupFlag() == null ? CarGroupFlag.NORMAL.value : orderBean.getCargroupFlag());
		orderBean.setCheckInPrice(orderBean.getCheckInPrice() == null ? 0d : orderBean.getCheckInPrice());
		orderBean.setIsReadable(IsReadable.VISIABLE.value);
		if (orderBean.getUrgentFlag() == null) {
			orderBean.setUrgentFlag(UrgentFlag.nomal.value);
		}

		// BUGFIX
		if (StringUtils.isNoneBlank(orderBean.getStartAddress()) && orderBean.getStartAddress().equals("null")) {
			orderBean.setStartAddress(null);
		}
		if (StringUtils.isNoneBlank(orderBean.getStartAddressDetail()) && orderBean.getStartAddressDetail().equals("null")) {
			orderBean.setStartAddressDetail(null);
		}
	}

	/**
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
		OrderType orderType = OrderType.getType(orderBean.getOrderType());
		log.info("orderType:" + orderType);
		boolean hasExpectedCompTime = false;
		switch (orderType) {
		case PICKUPORDER:
		case TRANSFER:
		case PERUSE:
			hasExpectedCompTime = true;
			log.info("接送次：hasExpectedCompTime:" + hasExpectedCompTime);
			break;
		case COMMENDATION:
			log.info("精品线路：hasExpectedCompTime:" + hasExpectedCompTime);
			GoodType goodsType = GoodType.getType(orderBean.getOrderGoodsType());
			switch (goodsType) {
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

		orderBean.setServiceCityName(cityBean.getCityName());
		orderBean.setServiceCityEnname(cityBean.getEnName());
		orderBean.setServiceCitySpell(cityBean.getSpell());
		return cityBean;
	}

	public void updateCoupInfos(OrderBean orderBean, String coupId, TradePayment tradePayment) {
		CouponBean couponBean = couponServiceParty.getCoup(coupId);
		orderBean.setCoupId(coupId);
		orderBean.setCoupPriceInfo(tradePayment.getCouponInfo());
		orderBean.setCoupType(couponBean.getCouponType());
		updateOrderBeanMapper.addCoupOrder(orderBean);
	}

	/**
	 * 更新串单标示
	 * 
	 * @param serialFlag
	 * @param serialOrderNo
	 * @param orderNo
	 */
	public void updateOrderSerialFlag(int serialFlag, String serialOrderNo, String orderNo) {

		updateOrderBeanMapper.updateOrderSerialFlag(serialFlag, serialOrderNo, orderNo);
	}

	@Autowired
	OrderAlarmService orderAlarmService;

	private void insertAlarm(OrderBean orderBean) {
		orderAlarmService.insertBeforeLeave(orderBean);
		orderAlarmService.insertNoService(orderBean);
	}

	/**
	 * @param registerId
	 */
	public void registerFlight(String registerId, String orderNo) {
		OrderBean target = new OrderBean();
		target.setFlightRegisterId(registerId);
		OrderBeanExample conditions = new OrderBeanExample();
		conditions.createCriteria().andOrderNoEqualTo(orderNo).andFlightIsCustomEqualTo(FlightIsCustom.CUSTOM.value).andFlightRegisterIdIsNull();
		if (orderBeanMapper.updateByExampleSelective(target, conditions) == 0) {
			log.error("注册航班号失败，订单：" + orderNo);
			throw new TradeException(TradeReturnCodeEnum.FAILED_FOR_UPDATE, "注册航班号");
		}
	}

	public void updateOrder(OrderBean record) {
		if (orderBeanMapper.updateByPrimaryKeySelective(record) == 0) {
			log.error("注册航班号失败，订单：" + record.getOrderNo());
			throw new TradeException(TradeReturnCodeEnum.FAILED_FOR_UPDATE, "更新订单" + JSON.toJSONString(record));
		}
	}
}
