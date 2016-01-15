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

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.hbc.api.trade.bdata.common.TradeConstant;
import com.hbc.api.trade.bdata.common.sign.CTripMD5;
import com.hbc.api.trade.bdata.common.sign.SignValidator;
import com.hbc.api.trade.order.enums.TradeReturnCodeEnum;
import com.hbc.api.trade.order.exception.TradeException;
import com.hbc.api.trade.ota.common.ChannelSet;
import com.hbc.api.trade.ota.ctrip.req.CTripCalculatePriceParam;
import com.hbc.api.trade.ota.ctrip.req.CTripOrderCancelParam;
import com.hbc.api.trade.ota.ctrip.req.CTripOrderSubmitParam;
import com.hbc.api.trade.ota.req.CalculatePriceParam;
import com.hbc.api.trade.ota.req.OrderCancelParam;
import com.hbc.api.trade.ota.req.OrderSubmitParam;
import com.hbc.api.trade.ota.resp.ctrip.CTripCalculatePriceResult;
import com.hbc.api.trade.ota.resp.ctrip.CTripCancelOrderResult;
import com.hbc.api.trade.ota.resp.ctrip.CTripCreateOrderResult;
import com.hbc.api.trade.ota.resp.ctrip.CTripResult;
import com.hbc.api.trade.ota.service.CTripOrderService;
import com.hbc.api.trade.ota.service.SignService;
import com.hbc.api.trade.ota.service.SignService.CHARACTER;
import com.hbc.api.trade.ota.service.SignService.POSITION;
import com.hbc.api.trade.ota.validator.Channel;

/**
 * 
 * @author Jongly Ran
 */
@RestController
@RequestMapping(value = "apictrip")
// TODO 第二版 @RequestMapping(value = "ota")
@Channel(ChannelSet.CTRIP)
public class CtripController {
	private Logger log = LoggerFactory.getLogger(CtripController.class);
	
	@Value("${trade.ota.ctrip.callback.security.key}")
	private String securityKey;

    @Autowired private CTripOrderService 		ctripOrderService ;
    @Autowired private SignService 				signService ;
    
    private void validateSign(Object arg, String sign, String noncestr, Long timestamp) {
    	Map<String, String> params = SignValidator.buildQueryString(arg);
    	String postFix = "&noncestr=" + noncestr + "&timestamp=" + timestamp + securityKey;
	    signService.validateSign(params, sign, postFix, CTripMD5.getInstance(), POSITION.END, CHARACTER.LOWERCASE);
    }
	
	@RequestMapping(value = "calculateprice", method = RequestMethod.POST)
	public CTripResult calculatePrice(HttpServletRequest request) {
		String jsonParam = null;
		try {
			jsonParam = IOUtils.toString(request.getInputStream(), TradeConstant.UTF8);
		} catch (IOException e) {
			log.error("携程查价参数错误", e);
			throw new TradeException(TradeReturnCodeEnum.ORDER_PARAM_FAILED, "");
		}
		CTripCalculatePriceParam inputs = JSON.parseObject(jsonParam, CTripCalculatePriceParam.class);
    	log.info("携程查价RequestParam:" + jsonParam + ",转化为对象后JSON：" + JSON.toJSONString(inputs));
		String nocestr = inputs.noncestr;
		Long timestamp = inputs.timestamp;
		inputs.noncestr = (null);
		inputs.timestamp = (null);
		validateSign(inputs, inputs.sign, nocestr, timestamp);
		CalculatePriceParam param = inputs.toStandardCalculatePriceParam();
		CTripCalculatePriceResult resultBean = ctripOrderService.getPrice(param);
		log.info("携程查价Response: " + JSON.toJSONString(resultBean));
		resultBean.MsgCode = "OK";
		return resultBean;
	}

    @RequestMapping(value="ordersubmit", method = RequestMethod.POST)
    public CTripResult orderSubmit(HttpServletRequest request) {
		String jsonParam = null;
		try {
			jsonParam = IOUtils.toString(request.getInputStream(), TradeConstant.UTF8);
		} catch (IOException e) {
			log.error("携程下单参数错误", e);
			throw new TradeException(TradeReturnCodeEnum.ORDER_PARAM_FAILED, "");
		}
    	CTripOrderSubmitParam inputs = JSON.parseObject(jsonParam, CTripOrderSubmitParam.class);
    	log.info("携程下单RequestParam:" + jsonParam + ",转化为对象后JSON：" + JSON.toJSONString(inputs));
		String nocestr = inputs.noncestr;
		Long timestamp = inputs.timestamp;
		inputs.noncestr=(null);
		inputs.timestamp=(null);
    	OrderSubmitParam param = inputs.toStandardSubmitParam();
    	validateSign(inputs, inputs.sign, nocestr, timestamp);
		String hbcOrderNo=ctripOrderService.createOrder(param);
		String toCtipOrderNo = hbcOrderNo.substring(1); // 仅支持数字
		CTripCreateOrderResult resultBean = new CTripCreateOrderResult(toCtipOrderNo);
		log.info("携程下单Response: " + JSON.toJSONString(resultBean));
		resultBean.MsgCode = "OK";
		return resultBean;
    }
    

    @RequestMapping(value="ordercancel", method = RequestMethod.POST)
    public CTripResult orderCancel(HttpServletRequest request) {
		String jsonParam = null;
		try {
			jsonParam = IOUtils.toString(request.getInputStream(), TradeConstant.UTF8);
		} catch (IOException e) {
			log.error("携程查价参数错误", e);
			throw new TradeException(TradeReturnCodeEnum.ORDER_PARAM_FAILED, "");
		}
    	CTripOrderCancelParam inputs = JSON.parseObject(jsonParam, CTripOrderCancelParam.class);
    	log.info("携程取消订单RequestParam:" + jsonParam + ",转化为对象后JSON：" + JSON.toJSONString(inputs));
		String nocestr = inputs.noncestr;
		Long timestamp = inputs.timestamp;
		inputs.noncestr=(null);
		inputs.timestamp=(null);
		validateSign(inputs, inputs.sign, nocestr, timestamp);
		OrderCancelParam param = inputs.toStandardOrderDetailBean();
        CTripCancelOrderResult resultBean = ctripOrderService.cancelThirdOrder(param);
		log.info("携程取消订单Response: " + JSON.toJSONString(resultBean));
		resultBean.MsgCode = "OK";
		return resultBean;
    }
    
}
