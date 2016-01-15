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
package com.hbc.api.trade.third.pricemark;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hbc.api.trade.bdata.common.HttpClientService;
import com.hbc.api.trade.bdata.common.exception.ReturnCode;
import com.hbc.api.trade.order.enums.TradeReturnCodeEnum;
import com.hbc.api.trade.order.enums.order.OrderSource;
import com.hbc.api.trade.order.enums.order.OrderType;
import com.hbc.api.trade.order.enums.third.ServiceType;
import com.hbc.api.trade.order.exception.TradeException;
import com.hbc.api.trade.order.mapping.gen.bean.OrderBean;

/**
 * PriceMark 验核策略
 * @author Jongly Ran
 */
public abstract class AbstractPriceMarkStratety {
	protected final static Logger log = LoggerFactory.getLogger(AbstractPriceMarkStratety.class);
	
	@Value("${trade.priceMarkUrl}")
	private String priceMarkUrl;
	
	@Autowired private HttpClientService httpClientService;

	public void verify(OrderBean orderBean) {
		StringBuilder queryString = buildRESTfulURL(orderBean);
		String url = queryString != null ? ( priceMarkUrl + "?" + queryString.toString() ) : priceMarkUrl;
		log.debug("PriceMark校验：URL=" +url);
		try {
			String responseText = httpClientService.sendReq(url);
			log.debug("PriceMark校验：responseText=" +responseText);
			JSONObject jobject = JSON.parseObject(responseText);

			Integer status = jobject.getInteger("status");
			log.info(url + " [" + responseText + "] ");
			if (200 == status) {
				JSONObject data = (JSONObject) jobject.get("data");
				if(data.getDouble("sysPrice") == null || data.getDouble("guidePrice") == null || data.getDouble("ticketPrice") == null ){
					throw new TradeException(TradeReturnCodeEnum.PRICE_MARK_NOEXT, responseText);
				}
				orderBean.setPriceBase(data.getDouble("sysPrice"));
				orderBean.setPriceGuide(data.getDouble("guidePrice"));
				orderBean.setPriceGuideBase(data.getDouble("guidePrice"));
				orderBean.setUrgentHour(data.getInteger("urgentHour"));
				
				if(orderBean.getPriceTicket()==null || orderBean.getPriceTicket()<=0d || OrderSource.GDS.value.equals(orderBean.getOrderSource())){
					
				}else{
					orderBean.setPriceTicket(data.getDouble("ticketPrice"));
				}

			} else {
				throw new TradeException(new ReturnCode() {
					@Override public String getMessage() { return jobject.getString("message"); }
					@Override public int getCode() { return status; }
				});
			}
		} catch (IOException e) {
			throw new TradeException(TradeReturnCodeEnum.PRICE_MARK_NOEXT, e.getMessage());
		}
	}
	
	/**
	 * 已封装公共参数，可在各应用中append参数。已提供的参数有：serviceType，pricemark，price，carType，seatCategory，startLocation，endLocation，channelId
	 * @param orderBean
	 * @return
	 */
	protected StringBuilder buildRESTfulURL(OrderBean orderBean) {
		OrderType orderType = OrderType.getType(orderBean.getOrderType());
		StringBuilder queryString = new StringBuilder();
		queryString.append("serviceType=").append(ServiceType.getType(orderType).value);
		queryString.append("&pricemark=").append(orderBean.getPriceMark());
		queryString.append("&price=").append(orderBean.getPriceChannel());
		queryString.append("&carType=").append(orderBean.getCarTypeId());
		queryString.append("&seatCategory=").append(orderBean.getCarSeatNum());
		queryString.append("&startLocation=").append(orderBean.getStartAddressPoi());
		queryString.append("&endLocation=").append(orderBean.getDestAddressPoi());
		queryString.append("&channelId=").append(orderBean.getOrderChannel());
		return queryString;
	}
}
