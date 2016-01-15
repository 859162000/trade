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
package com.hbc.api.trade.ota.qunar;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.alibaba.fastjson.JSON;
import com.hbc.api.trade.bdata.common.HttpClientService;
import com.hbc.api.trade.bdata.common.sign.QunarMD5;
import com.hbc.api.trade.bdata.common.sign.SignValidator;
import com.hbc.api.trade.ota.qunar.req.QunarCalculatePriceParam;
import com.hbc.api.trade.ota.qunar.req.QunarOrderCancelParam;
import com.hbc.api.trade.ota.qunar.req.QunarOrderDetailParam;
import com.hbc.api.trade.ota.qunar.req.QunarOrderSubmitParam;
import com.hbc.api.trade.ota.qunar.req.QunarOrderUpdateParam;
import com.hbc.api.trade.ota.resp.qunar.QunarCalculatePriceResult;
import com.hbc.api.trade.ota.resp.qunar.QunarCancelOrderResult;
import com.hbc.api.trade.ota.resp.qunar.QunarOrderDetailResult;
import com.hbc.api.trade.ota.resp.qunar.QunarOrderSubmitDetail;
import com.hbc.api.trade.ota.resp.qunar.QunarOrderSubmitResult;
import com.hbc.api.trade.ota.resp.qunar.QunarPriceDetail;
import com.hbc.api.trade.ota.resp.qunar.QunarPriceDetailTypes;
import com.hbc.api.trade.ota.resp.qunar.QunarUpdateOrderResult;
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
public class TestQunarController {
	private HttpClientService http = new HttpClientService();

	@Value("${trade.ota.qunar.callback.security.key}")
	private String TRADE_OTA_QUNAR_CALLBACK_SECURITY_KEY;
	
    @Autowired private SignService 				signService;
    private Properties properties;
    
    {
    	InputStream resourceAsStream = Thread.currentThread().getContextClassLoader()
    			.getResourceAsStream("com/hbc/api/trade/ota/qunar/qunar.url.properties");
		properties = new Properties();
		try {
			properties.load(resourceAsStream);
		} catch (IOException e) {
			e.printStackTrace();
			fail();
		}
    }

    private String generateSign(Object arg) {
    	Map<String, String> params = SignValidator.buildQueryString(arg, true);
	    return signService.createSign(params, TRADE_OTA_QUNAR_CALLBACK_SECURITY_KEY, QunarMD5.getInstance(),
	    		POSITION.BOTH, CHARACTER.UPPERCASE);
    }
    
    private Double price;
    private String pricemark;
    private Integer carType;
    
    @Test
    @Ignore
	public void calculatePrice() {
		String jsonParam = properties.getProperty("calculate.price.others");
		QunarCalculatePriceParam inputs = JSON.parseObject(jsonParam, QunarCalculatePriceParam.class);
		inputs.setSign(generateSign(inputs));
		jsonParam = JSON.toJSONString(inputs);
		try {
			String calculateResponse = http.postJSON("http://openapi.test.hbc.tech/ota/qunar/v2.0/calculatePrice", jsonParam);
			QunarCalculatePriceResult resultBean = JSON.parseObject(calculateResponse, QunarCalculatePriceResult.class);
			assertNotNull(resultBean);
			QunarPriceDetail priceDetail = resultBean.price;
			assertNotNull(priceDetail);
			List<QunarPriceDetailTypes> types = priceDetail.types;
			assertNotNull(types);
			QunarPriceDetailTypes type = types.get(0);
			price = type.getPrice();
			assertNotNull(price);
			pricemark = resultBean.getPricemark();
			assertNotNull(pricemark);
			carType = type.getCarType();
			assertNotNull(carType);
		} catch (IOException e) {
			e.printStackTrace();
			fail();
		}
	}
    
    @Test
   	public void orderSubmit() {
    	calculatePrice(); 
   		String jsonParam = properties.getProperty("order.submit");
   		QunarOrderSubmitParam inputs = JSON.parseObject(jsonParam, QunarOrderSubmitParam.class);
		assertNotNull(inputs);
   		String thirdOrderNo = String.valueOf(System.currentTimeMillis());
		inputs.setqOrderId(thirdOrderNo);
		inputs.setTotalPrice(price);
		inputs.setPricemark(pricemark);
		inputs.setCarType(carType);
		QunarOrderSubmitDetail detail = inputs.getDetail();
		assertNotNull(detail);
		detail.setBasePrice(price);
		inputs.setDetail(detail);
		inputs.setSign(generateSign(inputs));
		jsonParam = JSON.toJSONString(inputs);
   		
   		try {
   			String calculateResponse = http.postJSON("http://openapi.test.hbc.tech/ota/qunar/v2.0/orderSubmit", jsonParam);
   			QunarOrderSubmitResult resultBean = JSON.parseObject(calculateResponse, QunarOrderSubmitResult.class);
   	
   			assertNotNull(resultBean);
   			assertEquals(resultBean.getErrorCode(), new Integer(0));
   			assertEquals(thirdOrderNo, resultBean.getqOrderId());
   		} catch (IOException e) {
   			e.printStackTrace();
   			fail();
   		}
   	}
    
    @Test
    @Ignore
	public void orderDetail() {
		String jsonParam = properties.getProperty("order.detail");
		QunarOrderDetailParam inputs = JSON.parseObject(jsonParam, QunarOrderDetailParam.class);
		String qOrderId = "gjc14526856784130248";
		inputs.setqOrderId(qOrderId);
		inputs.setSign(generateSign(inputs));
		jsonParam = JSON.toJSONString(inputs);
		try {
			String calculateResponse = http.postJSON("http://openapi.test.hbc.tech/ota/qunar/v2.0/orderDetail", jsonParam);
			QunarOrderDetailResult resultBean = JSON.parseObject(calculateResponse, QunarOrderDetailResult.class);
			assertNotNull(resultBean);
			assertEquals(resultBean.getqOrderId(), qOrderId);
		} catch (IOException e) {
			e.printStackTrace();
			fail();
		}
	}
    

    @Test
    @Ignore
	public void orderModify() {
		String jsonParam = properties.getProperty("order.modify");
		QunarOrderUpdateParam inputs = JSON.parseObject(jsonParam, QunarOrderUpdateParam.class);
		String qOrderId = "gjc14526767750486714";
		inputs.setqOrderId(qOrderId);
		inputs.setSign(generateSign(inputs));
		jsonParam = JSON.toJSONString(inputs);
		try {
			String calculateResponse = http.postJSON("http://openapi.test.hbc.tech/ota/qunar/v2.0/orderModify", jsonParam);
			QunarUpdateOrderResult resultBean = JSON.parseObject(calculateResponse, QunarUpdateOrderResult.class);
			assertNotNull(resultBean);
			assertEquals(resultBean.getqOrderId(), qOrderId);
		} catch (IOException e) {
			e.printStackTrace();
			fail();
		}
	}
    
    @Test
    @Ignore
    public void orderCancel() {
    	String jsonParam = properties.getProperty("order.cancel");
    	QunarOrderCancelParam inputs = JSON.parseObject(jsonParam, QunarOrderCancelParam.class);
    	String qOrderId = "gjc14526856784130248";
    	inputs.setqOrderId(qOrderId);
    	inputs.setSign(generateSign(inputs));
    	jsonParam = JSON.toJSONString(inputs);
    	try {
    		String calculateResponse = http.postJSON("http://openapi.test.hbc.tech/ota/qunar/v2.0/orderCancel", jsonParam);
    		QunarCancelOrderResult resultBean = JSON.parseObject(calculateResponse, QunarCancelOrderResult.class);
    		assertNotNull(resultBean);
    		assertEquals(resultBean.getqOrderId(), qOrderId);
    	} catch (IOException e) {
    		e.printStackTrace();
    		fail();
    	}
    }
}
