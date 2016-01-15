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
package com.hbc.api.trade.ota.service;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.hbc.api.trade.bdata.common.TradeConstant;
import com.hbc.api.trade.bdata.common.exception.ReturnCode;
import com.hbc.api.trade.bdata.common.util.BeanUtilsEnhance;
import com.hbc.api.trade.bdata.common.util.DoubleUtil;
import com.hbc.api.trade.bdata.common.util.TimeConverter;
import com.hbc.api.trade.order.enums.order.OrderStatus;
import com.hbc.api.trade.order.enums.order.OrderType;
import com.hbc.api.trade.order.exception.TradeException;
import com.hbc.api.trade.order.mapping.gen.OrderBeanMapper;
import com.hbc.api.trade.order.mapping.gen.bean.OrderBean;
import com.hbc.api.trade.order.mapping.gen.bean.OrderBeanExample;
import com.hbc.api.trade.ota.adaptor.CarTypeAdaptor;
import com.hbc.api.trade.ota.adaptor.CarTypeContext;
import com.hbc.api.trade.ota.adaptor.QunarCarAdaptor;
import com.hbc.api.trade.ota.enums.QunarErrorCode;
import com.hbc.api.trade.ota.enums.QunarOrderStatus;
import com.hbc.api.trade.ota.mapping.gen.bean.TradeThirdOrder;
import com.hbc.api.trade.ota.po.CityPo;
import com.hbc.api.trade.ota.req.CalculatePriceParam;
import com.hbc.api.trade.ota.req.OrderCancelParam;
import com.hbc.api.trade.ota.req.OrderDetailParam;
import com.hbc.api.trade.ota.req.OrderModifyParam;
import com.hbc.api.trade.ota.req.OrderSubmitParam;
import com.hbc.api.trade.ota.resp.OrderPriceInfo;
import com.hbc.api.trade.ota.resp.OrderPriceInfoQuoteVo;
import com.hbc.api.trade.ota.resp.qunar.QuanrOrderListResultDetail;
import com.hbc.api.trade.ota.resp.qunar.QunarCalculatePriceResult;
import com.hbc.api.trade.ota.resp.qunar.QunarDriverInfo;
import com.hbc.api.trade.ota.resp.qunar.QunarOrderDetailResult;
import com.hbc.api.trade.ota.resp.qunar.QunarOrderListResult;
import com.hbc.api.trade.ota.resp.qunar.QunarOrderSubmitResult;
import com.hbc.api.trade.ota.resp.qunar.QunarPriceDetail;
import com.hbc.api.trade.ota.resp.qunar.QunarPriceDetailTypes;
import com.hbc.api.trade.ota.resp.qunar.QunarPriceDetailTypes.PriceType;
import com.hbc.api.trade.ota.resp.qunar.QunarPriceDetailTypesItems;
import com.hbc.api.trade.ota.resp.qunar.QunarResult;

/**
 * @author Jongly Ran
 */
@Service
public class QunarOrderService {
	private static Logger logger = LoggerFactory.getLogger(QunarOrderService.class);
	
	@Resource(name="standardOrderService") 
	private OTAOrderService otaOrderService;
	@Autowired private OrderBeanMapper orderBeanMapper;
	
	public void cancelThirdOrder(OrderCancelParam param) {
		otaOrderService.cancelThirdOrder(param);
	}
	
	public QunarOrderSubmitResult createOrder(OrderSubmitParam param) {
		OrderBean orderBean = param.getOrderBean();
		if(orderBean.getPriceMark() == null) {
			orderBean.setPriceMark("0");
		}

		CarTypeAdaptor carTypeAdaptor = CarTypeContext.getInstance().parseThirdCarType(orderBean.getCarTypeId(), QunarCarAdaptor.getInstance());
		orderBean.setCarTypeId(carTypeAdaptor.getCarType());
		orderBean.setCarSeatNum(carTypeAdaptor.getSeatCategory());
		orderBean.setCarDesc(carTypeAdaptor.getHbcCarDesc());
		param.setOrderBean(orderBean);
		TradeThirdOrder thirdOrderBean = param.getThirdOrderBean();
		OrderBean orderBeanBack = otaOrderService.createOrder(param);
		QunarOrderSubmitResult result = new QunarOrderSubmitResult();
		result.setqOrderId(thirdOrderBean.getThirdTradeNo());
		result.setvOrderId(orderBeanBack.getOrderNo());
		OrderType orderType = OrderType.getType(orderBeanBack.getOrderType());
		switch(orderType) {
		case PICKUPORDER:
			result.setAirportCode(orderBeanBack.getFlightDestCode());
			break;
		case TRANSFER:
			result.setAirportCode(orderBeanBack.getFlightAirportCode());
			break;
		default:
		}
		result.setCreateTime(TimeConverter.formatDate(orderBeanBack.getCreateTime()));
		result.setCreateTimeMs(orderBeanBack.getCreateTime().getTime()+"");
		result.setFlightNo(orderBeanBack.getFlightNo());
		result.setFromAddrDetail(orderBeanBack.getStartAddressDetail());
		result.setOrderTime(TimeConverter.formatDate(orderBeanBack.getServiceTime()));
		result.setOrderTimeMs(orderBeanBack.getServiceTime().getTime()+"");
		result.setDistance(DoubleUtil.multiplicationDouble(orderBeanBack.getDistance(), 1000d).intValue());
		
		logger.info("Qunar下单结果：" + JSON.toJSONString(result));
		return result;
	}
	
	public QunarOrderDetailResult getOrderDetail(OrderDetailParam param) {
	    OrderBean orderBean = otaOrderService.getOrderDetail(param);
		QunarOrderDetailResult result = new QunarOrderDetailResult();
		result.setqOrderId(orderBean.getThirdTradeNo());
		result.setCity(orderBean.getServiceCityName());
		result.setCountry(orderBean.getServiceCountryName());
		result.setCreateTime(TimeConverter.formatDate(orderBean.getCreateTime()));

		result.setDistance(DoubleUtil.multiplicationDouble(orderBean.getDistance(), 1000d).intValue());
		QunarDriverInfo driverInfo = new QunarDriverInfo();
		driverInfo.setName(TradeConstant.DRIVER_DEFAULT_NAME);
		driverInfo.setPhone(TradeConstant.DRIVER_DEFAULT_AREACODE + TradeConstant.DRIVER_DEFAULT_PHONE);
		driverInfo.setStatus(1); // 在路上
		result.driverInfo=(driverInfo);
		result.setFromAddrName(orderBean.getStartAddress());
		String[] startAddressPoi = orderBean.getStartAddressPoi().split(TradeConstant.SPLITER_COMMA);
		result.setFromLat(Double.valueOf(startAddressPoi[0]));
		result.setFromLong(Double.valueOf(startAddressPoi[1]));
		
		int carType = orderBean.getCarTypeId();
		int seatCategory = orderBean.getCarSeatNum();
		List<Integer> thirdCarTypeList = CarTypeContext.getInstance().toThirdCarType(carType, seatCategory, QunarCarAdaptor.getInstance());
		result.setMotorCycleType(thirdCarTypeList.get(0)); // 车型一定不能为空，否则报异常
		
		result.setOrderTime(TimeConverter.formatDate(orderBean.getServiceTime()));
		result.setOrderType(1); // 预约订单
		result.setPrice(orderBean.getPriceChannel());
		result.setqOrderId(orderBean.getThirdTradeNo());
		result.setServiceType(orderBean.getOrderType().intValue() == 1 ? 2 : 3); // 2接机、3送机
		result.setServiceTypeRefine(2); // 2 包车
		result.setStatus(getQuarStatus(orderBean.getOrderStatus()));
		result.setToAddrName(orderBean.getDestAddress());
		String[] endAddressPoi = orderBean.getDestAddressPoi().split(TradeConstant.SPLITER_COMMA);
		result.setToLat(Double.valueOf(endAddressPoi[0]));
		result.setToLong(Double.valueOf(endAddressPoi[1]));
		logger.info("Qunar详情结果：" + JSON.toJSONString(result));
		return result;
	}
	

	public QunarCalculatePriceResult getPrice(CalculatePriceParam param) {

		// 转换为查价车型
		String carTypeList = CarTypeContext.getInstance().buildCarTypeForPricing(param.getCarTypeId(), QunarCarAdaptor.getInstance());
		param.setCarTypeId(carTypeList);
		OrderPriceInfo data = otaOrderService.getPrice(param);
		if(data == null) {
			logger.error("无汽车列表为空");
			throw new TradeException(new ReturnCode() {
				@Override public String getMessage() { return QunarErrorCode.NOT_SUPPORT.value(); }
				@Override public int getCode() { return QunarErrorCode.NOT_SUPPORT.code(); }
			});
		}
		logger.info("查价报价系统返回：" + JSON.toJSONString(data));
		String priceMark = "0";
		boolean hasNotPriceMark = true;
		double lowPrice = 0.0;

		QunarPriceDetail qunarPriceInfo = new QunarPriceDetail();
		List<OrderPriceInfoQuoteVo> cars = data.getCars();
		if(cars != null && cars.size() > 0) {
			List<QunarPriceDetailTypes> qunarCarTypes = new LinkedList<>();
			for(int i = 0, j = cars.size(); i < j; i++) {
				QunarPriceDetailTypes qunarCarType = new QunarPriceDetailTypes();
				OrderPriceInfoQuoteVo quoteVo = cars.get(i);
				double price = quoteVo.getPrice();
				qunarCarType.setPrice(price);
				qunarCarType.setPriceType(PriceType.FORMAL.value());

				// 计算最低价
				if(i == 0) { lowPrice = price; }
				if(price < lowPrice) { lowPrice = price; }
				
				// 一次计算
				if(hasNotPriceMark) {
					priceMark = quoteVo.getPricemark(); // 2015-12-19： v1.0接送次都能保证所有priceMark是同一个值
					hasNotPriceMark = false;
				}
				QunarPriceDetailTypesItems detail = new QunarPriceDetailTypesItems();
				detail.setBaseDist(DoubleUtil.multiplicationDouble(data.getDistance(), 1000d).intValue());
				detail.setBasePrice(price);
				detail.setBaseTime(data.getEstTime());
				detail.setChildrenChairCost(0.0);
				detail.setFreeDriverCost(0.0);
				detail.setMeetCost(0.0);
				detail.setNightServiceCost(0.0);
				detail.setOtherCost(0.0);
				detail.setOverDistCost(0.0);
				detail.setOverTimeCost(0.0);
				detail.setPickupCardCost(0.0);
				detail.setPricecodecription("");
				detail.setShortPreBookCost(0.0);
				detail.setTip(0.0);
				detail.setVatCost(0.0);
				qunarCarType.detail = detail ;
				
				// 适配车型
				int carType = quoteVo.getCarType();
				int seatCategory = quoteVo.getSeatCategory();
				List<Integer> thirdCarTypeList = CarTypeContext.getInstance().toThirdCarType(carType, seatCategory, QunarCarAdaptor.getInstance());
				if(thirdCarTypeList != null && thirdCarTypeList.size() > 0) {
					if(thirdCarTypeList.size() == 1) {
						qunarCarType.setCarType(thirdCarTypeList.get(0));
						qunarCarTypes.add(qunarCarType);
					} else {
						// 我们的一个车型对应携程多个车型
						for(Integer thirdCarType : thirdCarTypeList) {
							qunarCarType.setCarType(thirdCarType);
							QunarPriceDetailTypes result = new QunarPriceDetailTypes();
							BeanUtilsEnhance.copyProperties(result, qunarCarType);
							qunarCarTypes.add(result);
						}
					}
				}
			}
			qunarPriceInfo.types=(qunarCarTypes);
		} else {
			logger.error("无汽车列表为空");
			throw new TradeException(new ReturnCode() {
				@Override public String getMessage() { return QunarErrorCode.NOT_SUPPORT.value(); }
				@Override public int getCode() { return QunarErrorCode.NOT_SUPPORT.code(); }
			});
		}
		qunarPriceInfo.setLowPrice(lowPrice);

		QunarCalculatePriceResult priceResult = new QunarCalculatePriceResult();
		if(param.getEstimatedTime() == null) {
			priceResult.setEstimatedTime(data.getEstTime());
		}else {
			priceResult.setEstimatedTime(Integer.parseInt(param.getEstimatedTime()));
		}
		if(param.getDistance()==null){
			priceResult.setDistance(DoubleUtil.multiplicationDouble(data.getDistance(), 1000d).intValue());
		}else{
			priceResult.setDistance(DoubleUtil.multiplicationDouble(param.getDistance(), 1000d).intValue());
		}
		priceResult.price=(qunarPriceInfo );
		priceResult.setCurrency("CNY");
		priceResult.setPricemark(priceMark);
		logger.info("Qunar查价详情结果：" + JSON.toJSONString(priceResult));
		return priceResult;
	}
	
	public List<CityPo> getServiceCitys() {
		return otaOrderService.getServiceCitys();
	}
	
	public void updateOrder(OrderModifyParam param) {
		otaOrderService.updateOrder(param);
	}
	
	public QunarResult orderList(List<String> thirdOrderNoList, String searchBeginTime) {
		
		OrderBeanExample example = new OrderBeanExample();
		example.createCriteria().andThirdTradeNoIn(thirdOrderNoList);
		List<OrderBean> orderBeanList = orderBeanMapper.selectByExample(example);
		logger.info("输入的订单号：" + JSON.toJSONString(thirdOrderNoList));
		QunarOrderListResult resultBean = new QunarOrderListResult();
		List<QuanrOrderListResultDetail> orderStatusList = new LinkedList<>();
		if(orderBeanList != null && orderBeanList.size() > 0) {
			for(OrderBean bean : orderBeanList) {
				QuanrOrderListResultDetail vo = new QuanrOrderListResultDetail();
				vo.setqOrderId(bean.getThirdTradeNo());
				vo.setSupplierOrderId(bean.getOrderNo());
				vo.setStatus(getQuarStatus(bean.getOrderStatus()));
				orderStatusList.add(vo);
			}
		}

		List<QuanrOrderListResultDetail> result = new LinkedList<>();
		for(String thirdOrderNo : thirdOrderNoList) {
			
			boolean isContain = false;
			for(QuanrOrderListResultDetail detail : orderStatusList) {
				if(detail.getqOrderId().equals(thirdOrderNo)){
					result.add(detail);
					isContain = true;
					break;
				} 
			}
			
			if(!isContain){
				QuanrOrderListResultDetail vo = new QuanrOrderListResultDetail();
				vo.setqOrderId(thirdOrderNo);
				vo.setSupplierOrderId("");
				vo.setStatus(QunarErrorCode.ORDER_NOT_FOUND.code());
				result.add(vo);
			}
		}
		logger.info("输入的订单号：" + JSON.toJSONString(result));
		resultBean.orderStatusList=result;
		return resultBean;
	}

	/**
	 *
	 * @param orderStatus
	 * @return
	 */
	private Integer getQuarStatus(Integer orderStatus) {
		
		
		OrderStatus status = OrderStatus.getStatus(orderStatus);
		switch(status) {
		case CANCEL_CLOSE:
		case CANCLE_CLOSE_NOPAY:
		case CANCEL_SERVICEED:
		case CANCELING:
		case CANCLE_CLOSE_PAY_SERVICE:
		case CANCEL_NOSERVICE:
			return QunarOrderStatus.CANCELLED_BY_USER.value;
		case SETTLEMENT:
			return QunarOrderStatus.COMPLETE.value;
		case CONFIRMED_COST:
			return QunarOrderStatus.DETERMINE_PRICE.value;
		case PAYSUCCESS:
		case GUIDE_ARRIVED:
		case PICK_CUSTOMER:
		case STROKE_END:
		case DISPUTING:
		default:
			return QunarOrderStatus.RESPONSED.value;
		}
	}
	
	public List<Integer> toOrderStatusList(Integer status) {
		List<Integer> orderStatusList = new LinkedList<>();
		QunarOrderStatus qunarOrderStatus = QunarOrderStatus.getStatus(status);
		switch(qunarOrderStatus) {
		case CANCELLED_BY_USER:
			orderStatusList.add(OrderStatus.CANCEL_CLOSE.value);
			orderStatusList.add(OrderStatus.CANCLE_CLOSE_NOPAY.value);
			orderStatusList.add(OrderStatus.CANCEL_SERVICEED.value);
			orderStatusList.add(OrderStatus.CANCLE_CLOSE_PAY_SERVICE.value);
			orderStatusList.add(OrderStatus.CANCELING.value);
			orderStatusList.add(OrderStatus.CANCEL_NOSERVICE.value);
			return orderStatusList;
		case COMPLETE:
			orderStatusList.add(OrderStatus.SETTLEMENT.value);
			return orderStatusList;
		case DETERMINE_PRICE:
			orderStatusList.add(OrderStatus.CONFIRMED_COST.value);
			return orderStatusList;
		case RESPONSED:
			orderStatusList.add(OrderStatus.PAYSUCCESS.value);
			orderStatusList.add(OrderStatus.GUIDE_ARRIVED.value);
			orderStatusList.add(OrderStatus.PICK_CUSTOMER.value);
			orderStatusList.add(OrderStatus.STROKE_END.value);
			orderStatusList.add(OrderStatus.DISPUTING.value);
			return orderStatusList;
		case CANCELLED_BY_DRIVER:
		case CANCELLED_BY_SERVICE:
		case CANCELLED_BY_QUNAR:
		case CANCELLED_BY_SUPPLIER:
		case NO_COMING:
		case NO_RESPONSE:
		default :
			return null;
		}
	}
	
	
}
