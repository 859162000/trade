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
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.hbc.api.trade.bdata.common.util.BeanUtilsEnhance;
import com.hbc.api.trade.order.mapping.gen.bean.OrderBean;
import com.hbc.api.trade.ota.adaptor.CarTypeAdaptor;
import com.hbc.api.trade.ota.adaptor.CarTypeContext;
import com.hbc.api.trade.ota.adaptor.QuaCarAdaptor;
import com.hbc.api.trade.ota.po.CityPo;
import com.hbc.api.trade.ota.req.CalculatePriceParam;
import com.hbc.api.trade.ota.req.OrderCancelParam;
import com.hbc.api.trade.ota.req.OrderDetailParam;
import com.hbc.api.trade.ota.req.OrderModifyParam;
import com.hbc.api.trade.ota.req.OrderSubmitParam;
import com.hbc.api.trade.ota.resp.OrderDetailResult;
import com.hbc.api.trade.ota.resp.OrderPriceInfo;
import com.hbc.api.trade.ota.resp.OrderPriceInfoQuoteVo;
import com.hbc.api.trade.ota.resp.qua.QuaOrderPriceInfo;

/**
 * @author Jongly Ran
 */
@Service
public class QuaOrderService {
	private final static Logger logger = LoggerFactory.getLogger(QuaOrderService.class);

	@Resource(name="standardOrderService") 
	private OTAOrderService otaOrderService;
	
	public void cancelThirdOrder(OrderCancelParam param) {
		otaOrderService.cancelThirdOrder(param);
	}
	
	public OrderBean createOrder(OrderSubmitParam param) {
		OrderBean orderBean = param.getOrderBean();
		CarTypeAdaptor carTypeAdaptor = CarTypeContext.getInstance().parseThirdCarType(orderBean.getCarTypeId(), QuaCarAdaptor.getInstance());
		orderBean.setCarTypeId(carTypeAdaptor.getCarType());
		orderBean.setCarSeatNum(carTypeAdaptor.getSeatCategory());
		orderBean.setCarDesc(carTypeAdaptor.getHbcCarDesc());
		param.setOrderBean(orderBean);
		logger.info("去哪下单：" + JSON.toJSONString(param));
		return otaOrderService.createOrder(param);
	}
	
	public OrderDetailResult getOrderDetail(OrderDetailParam param) {
		OrderBean orderBean = otaOrderService.getOrderDetail(param);
		OrderDetailResult  orderDetail = new OrderDetailResult();
        orderDetail.setServicePartner(param.getServicePartner());
        orderDetail.setOrderStatus(orderBean.getOrderStatus());
        orderDetail.setOrderNo(orderBean.getOrderNo());
        orderDetail.setThirdTradeNo(orderBean.getThirdTradeNo());
        orderDetail.setGuideAreaCode(orderBean.getGuideAreaCode());
        orderDetail.setGuideMobile(orderBean.getGuideMobile());
        orderDetail.setGuideName(orderBean.getGuideName());
        orderDetail.setGuideCarName(orderBean.getCarName());
        orderDetail.setGuideCarDesc(orderBean.getCarDesc());
        return orderDetail;
	}
	
	public QuaOrderPriceInfo getPrice(CalculatePriceParam param) {
		String carTypeList = CarTypeContext.getInstance().buildCarTypeForPricing(param.getCarTypeId(), QuaCarAdaptor.getInstance());
		param.setCarTypeId(carTypeList);
		OrderPriceInfo standardPriceInfo = otaOrderService.getPrice(param);
		if(standardPriceInfo == null) {
			return new QuaOrderPriceInfo();
		}
		QuaOrderPriceInfo quaOrderPriceInfo = new QuaOrderPriceInfo();
		quaOrderPriceInfo.setDistance(standardPriceInfo.getDistance());
		quaOrderPriceInfo.setExpectedCompTime(standardPriceInfo.getEstTime());
		boolean hasNextPriceMark = true;
		String priceMark = "0";
		List<OrderPriceInfoQuoteVo> cars = standardPriceInfo.getCars();
		if(cars != null && cars.size() > 0 ) {
			List<OrderPriceInfoQuoteVo> targetCars = new LinkedList<>();
			for(OrderPriceInfoQuoteVo quoteVo : cars) {
				OrderPriceInfoQuoteVo targetQuoteVo = new OrderPriceInfoQuoteVo();
				BeanUtilsEnhance.copyProperties(targetQuoteVo, quoteVo);
				
				// 适配车型
				Integer carType = quoteVo.getCarType();
				Integer seatCategory = quoteVo.getSeatCategory();
				List<Integer> quaCarTypeList = CarTypeContext.getInstance().toThirdCarType(carType, seatCategory, QuaCarAdaptor.getInstance());
				
				
				if(quaCarTypeList != null && quaCarTypeList.size() > 0) {
					if(quaCarTypeList.size() == 1) {
						targetQuoteVo.setCarType(quaCarTypeList.get(0));
						targetCars.add(targetQuoteVo);
					} else {
						// 我们的一个车型对应携程多个车型
						for(Integer thirdCarType : quaCarTypeList) {
							targetQuoteVo.setCarType(thirdCarType);
							OrderPriceInfoQuoteVo result = new OrderPriceInfoQuoteVo();
							BeanUtilsEnhance.copyProperties(result, targetQuoteVo);
							targetCars.add(result);
						}
					}
				} 
				
				if(hasNextPriceMark) {
					priceMark = targetQuoteVo.getPricemark();  // 2015-12-19： v1.0接送次都能保证所有priceMark是同一个值
					hasNextPriceMark = false;
				}
			}
			quaOrderPriceInfo.setPricelist(targetCars);
 			
		}
		quaOrderPriceInfo.setPricemark(priceMark);
		logger.info("查价service层返回：" + JSON.toJSONString(quaOrderPriceInfo));
		return quaOrderPriceInfo;
	}
	
	public List<CityPo> getServiceCitys() {
		return otaOrderService.getServiceCitys();
	}
	
	public void updateOrder(OrderModifyParam param) {
		otaOrderService.updateOrder(param);
	}
}
