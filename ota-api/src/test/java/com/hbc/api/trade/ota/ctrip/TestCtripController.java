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
package com.hbc.api.trade.ota.ctrip;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.alibaba.fastjson.JSON;
import com.hbc.api.trade.bdata.common.HttpClientService;
import com.hbc.api.trade.bdata.common.sign.CTripMD5;
import com.hbc.api.trade.bdata.common.sign.SignValidator;
import com.hbc.api.trade.ota.ctrip.req.CTripCalculatePriceParam;
import com.hbc.api.trade.ota.ctrip.req.CTripOrderSubmitParam;
import com.hbc.api.trade.ota.resp.ctrip.CTripCalculatePriceDetail;
import com.hbc.api.trade.ota.resp.ctrip.CTripCalculatePriceResult;
import com.hbc.api.trade.ota.resp.ctrip.CTripCreateOrderResult;
import com.hbc.api.trade.ota.service.SignService;
import com.hbc.api.trade.ota.service.SignService.CHARACTER;
import com.hbc.api.trade.ota.service.SignService.POSITION;

/**
 * @author Jongly Ran
 */

@RunWith(SpringJUnit4ClassRunner.class)  
@WebAppConfiguration  
// @TransactionConfiguration(defaultRollback = true)  
// @Transactional  
@ContextConfiguration(locations = {"classpath:conf/spring-mvc.xml"})  
public class TestCtripController {
	private HttpClientService http = new HttpClientService();
	private final static String securityKey =  "191c4934f5cd468a0c7e7cc1173a8cda"; // "ad373bfc6afd45e825d3d160ac041f19"; //;
	
    @Autowired private SignService 				signService;
    private Properties properties;
    
    {
    	InputStream resourceAsStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("com/hbc/api/trade/ota/ctrip/ctrip.url.properties");
		properties = new Properties();
		try {
			properties.load(resourceAsStream);
		} catch (IOException e) {
			e.printStackTrace();
			fail();
		}
    }
    
    
    private String generateSign(Object arg, String noncestr, Long timestamp) {
    	Map<String, String> params = SignValidator.buildQueryString(arg);
    	String postFix = "&noncestr=" + noncestr + "&timestamp=" + timestamp + securityKey;
	    return signService.createSign(params, postFix, CTripMD5.getInstance(),
	    		POSITION.END, CHARACTER.LOWERCASE);
    }
	

    private Double price;
    private String pricemark;
    private Integer carType;
    
	@Test
	public void calculatePrice() {
		String jsonParam = properties.getProperty("calculate.price.others");
		CTripCalculatePriceParam inputs = JSON.parseObject(jsonParam, CTripCalculatePriceParam.class);
		String nocestr = inputs.noncestr;
		Long timestamp = inputs.timestamp;
		inputs.noncestr = (null);
		inputs.timestamp = (null);
		inputs.sign = generateSign(inputs, nocestr, timestamp);
		jsonParam = JSON.toJSONString(inputs);
		try {
			String calculateResponse = http.postJSON("http://openapi.test.hbc.tech/apictrip/calculateprice", jsonParam);
			CTripCalculatePriceResult resultBean = JSON.parseObject(calculateResponse, CTripCalculatePriceResult.class);
			assertNotNull(resultBean);
			pricemark = resultBean.PriceMark;
			List<CTripCalculatePriceDetail> QueryResultList= resultBean.QueryResultList;
			assertNotNull(QueryResultList);
			boolean first = true;
			for(CTripCalculatePriceDetail detail : QueryResultList) {
				if(first) {
					price = detail.Price;
					carType = detail.VehicleType;
					first = false;
				}
				if(detail.VehicleType == 17) {
					fail();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void calculatePriceWithTaxi() {
		String jsonParam = properties.getProperty("calculate.price.mangu");
		CTripCalculatePriceParam inputs = JSON.parseObject(jsonParam, CTripCalculatePriceParam.class);
		String nocestr = inputs.noncestr;
		Long timestamp = inputs.timestamp;
		inputs.noncestr = (null);
		inputs.timestamp = (null);
		inputs.sign = generateSign(inputs, nocestr, timestamp);
		jsonParam = JSON.toJSONString(inputs);
		try {
			String calculateResponse = http.postJSON("http://openapi.test.hbc.tech/apictrip/calculateprice", jsonParam);
			CTripCalculatePriceResult resultBean = JSON.parseObject(calculateResponse, CTripCalculatePriceResult.class);
			assertNotNull(resultBean);
			List<CTripCalculatePriceDetail> QueryResultList= resultBean.QueryResultList;
			assertNotNull(QueryResultList);
			boolean hasCars = false;
			for(CTripCalculatePriceDetail detail : QueryResultList) {
				if(detail.VehicleType == 17) {
					hasCars = true;
				}
			}
			
			if(!hasCars) {
				fail();
			}
		} catch (IOException e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void orderSubmit () {
		calculatePrice();
		String jsonParam = properties.getProperty("order.submit");
		CTripOrderSubmitParam inputs = JSON.parseObject(jsonParam, CTripOrderSubmitParam.class);
		inputs.PriceMark = pricemark;
		inputs.VehicleType = carType;
		inputs.TotalPrice = price.toString();
		String thirdOrderNo = String.valueOf(System.currentTimeMillis());
		inputs.CtripPurchaseOrderID = thirdOrderNo;
		String nocestr = inputs.noncestr;
		Long timestamp = inputs.timestamp;
		inputs.noncestr=(null);
		inputs.timestamp=(null);
		inputs.sign = generateSign(inputs, nocestr, timestamp);
		jsonParam = JSON.toJSONString(inputs);

		try {
			String submitResponse = http.postJSON("http://openapi.test.hbc.tech/apictrip/ordersubmit", jsonParam);
			assertNotNull(submitResponse);
			CTripCreateOrderResult result = JSON.parseObject(submitResponse, CTripCreateOrderResult.class);
			assertNotNull(result);
			assertEquals(result.VendorOrderId, thirdOrderNo);
		} catch (IOException e) {
			e.printStackTrace();
			fail();
		}
	}
	
}
