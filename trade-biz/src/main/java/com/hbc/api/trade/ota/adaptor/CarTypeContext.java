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
package com.hbc.api.trade.ota.adaptor;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hbc.api.trade.bdata.common.TradeConstant;
import com.hbc.api.trade.order.enums.TradeReturnCodeEnum;
import com.hbc.api.trade.order.exception.TradeException;

/**
 * @author Jongly Ran
 */
public class CarTypeContext {
	private final static Logger logger = LoggerFactory.getLogger(CarTypeContext.class);
	
	private final static CarTypeContext instance = new CarTypeContext();
	
	private CarTypeContext(){}
	
	public static CarTypeContext getInstance() {
		return instance;
	}
	
	/**
	 * 为查价构造车型
	 * @param carTypeString 车型字符串，多个用半角逗号（,）隔开。如：1,2,3
	 * @param carTypeAdaptor 车型适配器
	 * @return 用半角逗号和下划线隔开。格式：车型1_车座数1,车型2_车座数2, ... , 车型n_车座数n
	 */
	public String buildCarTypeForPricing(String carTypeString, CarTypeAdaptor carTypeAdaptor) {
		if(carTypeString == null) return null;
		
		StringBuilder carTypes = new StringBuilder();
		String[] all = carTypeString.split(TradeConstant.SPLITER_COMMA);
		for(String each : all) {
			String value = carTypeAdaptor.carTypeMap().get(Integer.valueOf(each));
			carTypes.append(TradeConstant.SPLITER_COMMA).append(value);
		}
		if(carTypes.length() > 2) {
			return carTypes.toString().substring(1);
		}
		return null;
	}

	/**
	 * 解析车型字符串，获取车型和车座数
	 * @param carTypeId 对外适配的车型ID
	 * @param carTypeAdaptor 车型适配器
	 * @return
	 */
	public CarTypeAdaptor parseThirdCarType(Integer carTypeId, CarTypeAdaptor carTypeAdaptor) {
		try {
			String carType = carTypeAdaptor.carTypeMap().get(carTypeId);
			if(carType == null || carType.split(TradeConstant.SPLITER_LINE).length != 2) {
				throw new Exception("车型格式不正确，应该是：车型_车座数，如 1-5（经济5座）, 2-7（舒适7座）等");
			}
			String[] carTypes = carType.split(TradeConstant.SPLITER_LINE);
			int hbcCarType = Integer.parseInt(carTypes[0]);
			int hbcCarSeatNum = Integer.parseInt(carTypes[1]);
			carTypeAdaptor.setCarType(hbcCarType);
			carTypeAdaptor.setSeatCategory(hbcCarSeatNum);
			carTypeAdaptor.setHbcCarDesc(HBCCarType.getInstance().carDesc(hbcCarType, hbcCarSeatNum));
			return carTypeAdaptor;
		} catch(Exception e) {
			logger.error("车型异常", e);
			throw new TradeException(TradeReturnCodeEnum.ORDER_PARAM_FAILED, "carType");
		}
	}
	
	/**
	 * 把HBC的车型转换为第三方的适配车型
	 * 
	 * @param carType HBC的车型
	 * @param seatCategory HBC的车型对应车座数
	 * @param carTypeAdaptor 车型适配器
	 * @return
	 */
	public List<Integer> toThirdCarType(Integer carType, Integer seatCategory, CarTypeAdaptor carTypeAdaptor) {
		String carTypeString = carType + TradeConstant.SPLITER_LINE + seatCategory;
		List<Integer> thirdCarTypeList = new LinkedList<>();
		Map<Integer, String> carTypePool = carTypeAdaptor.carTypeMap();
		for(Integer key : carTypePool.keySet()) {
			String tempCarTypeString = carTypePool.get(key);
			if(carTypeString.equals(tempCarTypeString)) {
				thirdCarTypeList.add(key);
			}
		}
		return thirdCarTypeList;
	}
}
