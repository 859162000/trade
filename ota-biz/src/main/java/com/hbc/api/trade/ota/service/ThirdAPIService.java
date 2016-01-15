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

import java.util.Map;
import java.util.Set;

import org.apache.commons.httpclient.URIException;
import org.apache.commons.httpclient.util.URIUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.hbc.api.trade.bdata.common.HttpClientService;
import com.hbc.api.trade.bdata.exception.TradeSDKException;
import com.hbc.api.trade.order.enums.TradeReturnCodeEnum;
import com.hbc.api.trade.order.enums.order.OrderType;
import com.hbc.api.trade.order.exception.TradeException;
import com.hbc.api.trade.ota.resp.OrderPriceInfo;
import com.hbc.api.trade.ota.resp.ThirdCalculatePriceResult;

/**
 * @author Jongly Ran
 */
@Service
public class ThirdAPIService {
private final static Logger log = LoggerFactory.getLogger(ThirdOrderService.class);
	
	@Autowired private HttpClientService httpClientService;

	@Value("${trade.price.calprice.pickup}")
	String caculatePriceForPickup;
	
	@Value("${trade.price.calprice.transfer}")
	String caculatePriceForTransfer;
	

	/**
	 * @param reqData
	 * @return
	 * @since v1.0.1
	 */
	public OrderPriceInfo getPriceFromAPI(String reqData, OrderType orderType){
		String rspstr;
		String calpriceUrl = OrderType.PICKUPORDER.equals(orderType) ? caculatePriceForPickup : caculatePriceForTransfer;
		String reqUrl = reqData != null ? (calpriceUrl  + "?" + reqData) : calpriceUrl ;
		log.info("查价request: " + reqUrl);
		try {
			rspstr = httpClientService.sendReqNoEncode(reqUrl) ;
			ThirdCalculatePriceResult result = JSON.parseObject(rspstr, ThirdCalculatePriceResult.class);
			if(result.getStatus() == 200) {
				return (OrderPriceInfo) result.getData();
			}
			log.info("查价ResponseText:" + rspstr);
		} catch (TradeSDKException e) {
			log.error("查价报错", e);
		}
		throw new TradeException(TradeReturnCodeEnum.TRADE_PRICE_ERROR) ;
	}
	
	/**
	 * 
	 * @param params
	 * @return
	 * @since v1.0.1
	 */
	public OrderPriceInfo getPriceFromAPI(Map<String, String> params, OrderType orderType){
		if(params == null || params.isEmpty())  return null;
		
		Set<String> keySet = params.keySet();
		StringBuilder parstr = new StringBuilder();
		for (String keyStr : keySet) {
			String value = params.get(keyStr);
			if (value != null) {
				parstr.append("&").append(keyStr).append("=").append(value);
			}
		}
		try {
			return getPriceFromAPI(URIUtil.encodeQuery(parstr.toString().substring(1)), orderType);
		} catch (URIException e) {
			log.error("URL转码异常", e);
			throw new TradeException(TradeReturnCodeEnum.ORDER_PARAM_FAILED, "与第三方通讯") ;
		}
	}
}
