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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.hbc.api.trade.bdata.mapper.basedata.gen.bean.AirportBean;
import com.hbc.api.trade.order.enums.deliver.UrgentFlag;
import com.hbc.api.trade.order.mapping.gen.bean.OrderBean;
import com.hbc.api.trade.ota.adaptor.CTripCarAdaptor;
import com.hbc.api.trade.ota.adaptor.CarTypeAdaptor;
import com.hbc.api.trade.ota.adaptor.CarTypeContext;
import com.hbc.api.trade.ota.po.CityPo;
import com.hbc.api.trade.ota.req.CalculatePriceParam;
import com.hbc.api.trade.ota.req.OrderCancelParam;
import com.hbc.api.trade.ota.req.OrderSubmitParam;
import com.hbc.api.trade.ota.resp.OrderCancelResult;
import com.hbc.api.trade.ota.resp.OrderPriceInfo;
import com.hbc.api.trade.ota.resp.OrderPriceInfoQuoteVo;
import com.hbc.api.trade.ota.resp.ctrip.CTripCalculatePriceDetail;
import com.hbc.api.trade.ota.resp.ctrip.CTripCalculatePriceResult;
import com.hbc.api.trade.ota.resp.ctrip.CTripCancelOrderResult;
import com.hbc.api.trade.third.LControllerService;

/**
 * @author Jongly Ran
 */
@Service
public class CTripOrderService {
	private final static Logger logger = LoggerFactory.getLogger(CTripOrderService.class);
	
	@Autowired
	@Qualifier("standardOrderService")
	private OTAOrderService otaOrderService;
	
	public CTripCancelOrderResult cancelThirdOrder(OrderCancelParam param) {
		OrderCancelResult cancelBean = otaOrderService.cancelThirdOrder(param);
		CTripCancelOrderResult result = new CTripCancelOrderResult();
		result.CancelFee = cancelBean.getCancelFee();
		result.MsgCode = "OK";
		return result;
	}
	
	public String createOrder(OrderSubmitParam param) {
		OrderBean orderBean = param.getOrderBean();
		
		if(orderBean.getAdultNum() == null) {
			orderBean.setAdultNum(1);// TODO ctrip 怎么传递？
		}
		
		CarTypeAdaptor carTypeAdaptor = CarTypeContext.getInstance().parseThirdCarType(orderBean.getCarTypeId(), CTripCarAdaptor.getInstance());
		orderBean.setCarTypeId(carTypeAdaptor.getCarType());
		orderBean.setCarSeatNum(carTypeAdaptor.getSeatCategory());
		orderBean.setCarDesc(carTypeAdaptor.getHbcCarDesc());
		param.setOrderBean(orderBean);
		
		return otaOrderService.createOrder(param).getOrderNo();
	}
	@Autowired
	LControllerService lcontrollerService;
	public CTripCalculatePriceResult getPrice(CalculatePriceParam param) {

		CTripCalculatePriceResult result = new CTripCalculatePriceResult();
		param.setCarTypeId(CarTypeContext.getInstance().buildCarTypeForPricing(param.getCarTypeId(), CTripCarAdaptor.getInstance()));
		OrderPriceInfo data = otaOrderService.getPrice(param);
		if(data == null) {
			result.MsgCode="ERROR";
			result.Message="无报价信息";
			return result;
		}
		
		String priceMark = "0";						 // default
		Integer urgentFlag = UrgentFlag.nomal.value; // default
		List<OrderPriceInfoQuoteVo> cars = data.getCars();
		if(cars != null && cars.size() > 0)  {
			boolean hasNotPriceMark = true;
			Boolean supportBanner = data.getSupportBanner();
			Boolean supportChildseat = data.getSupportChildseat();
			
			// 拿齐所有在适配器里的车型
			List<CTripCalculatePriceDetail> queryResultList = new LinkedList<>();
			CTripCalculatePriceDetail taxi = null;
			for (int i = 0; i < cars.size(); i++) {
				OrderPriceInfoQuoteVo quoteVo = cars.get(i);
				CTripCalculatePriceDetail detail = new CTripCalculatePriceDetail();
				detail.IsSupportChildSeat = supportChildseat;
				detail.IsSupportPickup = supportBanner;
				detail.Price = quoteVo.getPrice();
				if(hasNotPriceMark) {
					urgentFlag= quoteVo.getUrgentFlag(); // 2015-12-19： v1.0同一个值
					priceMark = quoteVo.getPricemark();  // 2015-12-19： v1.0接送次都能保证所有priceMark是同一个值
					hasNotPriceMark =false;
				}
				
				// 适配车型
				int seatCategory = quoteVo.getSeatCategory();
				int carType = quoteVo.getCarType();
				List<Integer> carTypeList = CarTypeContext.getInstance().toThirdCarType(carType, seatCategory, CTripCarAdaptor.getInstance());
				if(carTypeList != null && carTypeList.size() > 0) {
					// 如果是携程出租车车型（17）直接忽略
					if(carTypeList.get(0) == 17) {
						continue;
					}
					detail.VehicleType = carTypeList.get(0);
					// 如果是经济5座（1_5）的车型，记录到出租车(携程出租车 17）
					if(carType == 1 && seatCategory == 5) {
						taxi = new CTripCalculatePriceDetail();
						taxi.IsSupportChildSeat = supportChildseat;
						taxi.IsSupportPickup = supportBanner;
						taxi.Price = detail.Price;
						taxi.VehicleType = 17; // 携程出租车
					}
					queryResultList.add(detail);
				}
			}
			
			// 如果城市是曼谷（cityId=230）需要增加出租车报价，价格按照经济5座处理
			AirportBean airportBean = lcontrollerService.getAirportByCode(param.getFlightAirportCode());
			if (taxi != null && new Integer(230).equals(airportBean.getCityId())) {
				queryResultList.add(taxi );
			}
			result.QueryResultList = queryResultList;
		} else {
			result.MsgCode="ERROR";
			result.Message="无报价信息";
			return result;
		}
		result.Distance = data.getDistance(); // TODO 第二版
		result.EstTime = data.getEstTime();	  // TODO 第二版
		result.PriceMark = priceMark;		  // TODO 第二版
		result.UrgentFlag = urgentFlag;		  // TODO 第二版
		logger.info("查价Service层返回：" + JSON.toJSONString(result));
		return result;
	}
	
	public List<CityPo> getServiceCitys() {
		return otaOrderService.getServiceCitys();
	}
}
