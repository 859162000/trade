package com.hbc.api.trade.ota.qua;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.hbc.api.trade.bdata.common.rsp.ReturnResult;
import com.hbc.api.trade.bdata.common.sign.QuaMD5;
import com.hbc.api.trade.bdata.common.sign.SignValidator;
import com.hbc.api.trade.bdata.common.util.DoubleUtil;
import com.hbc.api.trade.order.mapping.gen.bean.OrderBean;
import com.hbc.api.trade.ota.mapping.gen.bean.TradeThirdOrder;
import com.hbc.api.trade.ota.qua.req.PromotionInfo;
import com.hbc.api.trade.ota.qua.req.PromotionInfos;
import com.hbc.api.trade.ota.qua.req.QuaOrderSubmitParam;
import com.hbc.api.trade.ota.req.CalculatePriceParam;
import com.hbc.api.trade.ota.req.OrderCancelParam;
import com.hbc.api.trade.ota.req.OrderDetailParam;
import com.hbc.api.trade.ota.req.OrderModifyParam;
import com.hbc.api.trade.ota.req.OrderSubmitParam;
import com.hbc.api.trade.ota.resp.OrderDetailResult;
import com.hbc.api.trade.ota.resp.qua.QuaOrderPriceInfo;
import com.hbc.api.trade.ota.service.QuaOrderService;
import com.hbc.api.trade.ota.service.SignService;
import com.hbc.api.trade.ota.service.SignService.CHARACTER;
import com.hbc.api.trade.ota.service.SignService.POSITION;

/**
 * 阿里-去啊 API
 * Standard API
 * @author Jongly Ran
 */
@RestController
@RequestMapping(value = "ota/v1.0")
public class QuaController {

	private Logger log = LoggerFactory.getLogger(QuaController.class);
	private static final String securityKey = "90d5497397a15f126b0f0acb8ed67da2";
	
    @Autowired private QuaOrderService 	quaOrderService ;

    @Autowired private SignService 				signService ;
    
    private void validateSign(HttpServletRequest request) {
    	Object[] params = SignValidator.buildQueryString(request);
		@SuppressWarnings("unchecked")
		Map<String, String> queryString = (Map<String, String>) params[0];	
		String	sign = params[1].toString();
	    signService.validateSign(queryString, sign, securityKey, QuaMD5.getInstance(), POSITION.END, CHARACTER.LOWERCASE);
    }
	
	@RequestMapping(value = "calculatePrice", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public ReturnResult calculatePrice(@Valid CalculatePriceParam param, BindingResult result, HttpServletRequest request) {
		log.info("去啊查价RequestParam:" + JSON.toJSONString(param));
		validateSign(request);
		QuaOrderPriceInfo jsonData = quaOrderService.getPrice(param);
		ReturnResult returnResult = new ReturnResult();
		returnResult.setData(jsonData);
		log.info("去啊查价返回:" + JSON.toJSONString(returnResult));
		return returnResult;
	}

    @RequestMapping(value="orderSubmit", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public ReturnResult orderSubmit(@Valid QuaOrderSubmitParam param, BindingResult result, HttpServletRequest request) {
    	log.info("去啊下单RequestParam:" + JSON.toJSONString(param));
		validateSign(request);
		OrderSubmitParam standardSubmitParam = param.toStandardSubmitParam();
		
		// 处理优惠券 v1.0.2 2015-12-31
		TradeThirdOrder thirdOrderBean = standardSubmitParam.getThirdOrderBean();
    	String promotionInfo = param.getPromotionInfo();
    	if(promotionInfo != null) {
    		log.info("去啊订单["+thirdOrderBean.getThirdTradeNo()+"]优惠券：" + promotionInfo);
    		PromotionInfos promotionInfos = JSON.parseObject("{\"promotionInfo\":"+promotionInfo+"}", PromotionInfos.class);
    		if(promotionInfos != null) {
    			List<PromotionInfo> promotionList = promotionInfos.getPromotionInfo();
    			if(promotionList != null) {
    				double couponAmount = 0.0;
    				for(PromotionInfo coupon : promotionList) {
    					double one = ( coupon == null ? 0.0 : coupon.getCouponValue() );
    					couponAmount = DoubleUtil.addDouble(one, couponAmount);
    				}
    				thirdOrderBean.setCouponAmount(couponAmount/100d);
    				OrderBean orderBean = standardSubmitParam.getOrderBean();
    				orderBean.setPriceChannel(DoubleUtil.addDouble(orderBean.getPriceChannel(), thirdOrderBean.getCouponAmount()));
					standardSubmitParam.setOrderBean(orderBean );
    				standardSubmitParam.setThirdOrderBean(thirdOrderBean);
    			}
    		}
    	}
    	
		OrderBean orderBean = quaOrderService.createOrder(standardSubmitParam);
        ReturnResult returnResult = new ReturnResult();
        Map<String, String> data = new HashMap<>(1);
        data.put("orderNo", orderBean.getOrderNo());
        returnResult.setData(data);
    	log.info("去啊下单返回：" + JSON.toJSONString(returnResult));
		return returnResult;
    }
    
    @RequestMapping(value="orderCancel", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public ReturnResult orderCancel(@Valid OrderCancelParam param, BindingResult result, HttpServletRequest request) {
    	log.info("去啊取消订单RequestParam:" + JSON.toJSONString(param));
		validateSign(request);
        quaOrderService.cancelThirdOrder(param);
        ReturnResult returnResult = new ReturnResult();
    	log.info("去啊取消下单返回：" + JSON.toJSONString(returnResult));
		return returnResult ;
    }

	// 暂时无用处
	@Deprecated
    @RequestMapping(value="orderDetail", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public ReturnResult getOrderDetail(@Valid OrderDetailParam param, BindingResult result, HttpServletRequest request) {
    	log.info("去啊查详情RequestParam:" + JSON.toJSONString(param));
		validateSign(request);
		OrderDetailResult orderDetail = quaOrderService.getOrderDetail(param);
		ReturnResult returnResult = new ReturnResult();
        returnResult.setData(orderDetail);
        return returnResult ;
    }

	// 暂时无用处
	@Deprecated
    @RequestMapping(value="orderModify", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public ReturnResult orderModify(@Valid OrderModifyParam param, BindingResult result, HttpServletRequest request) {
    	log.info("去啊修改订单RequestParam:" + JSON.toJSONString(param));
		validateSign(request);
    	quaOrderService.updateOrder(param);
        return new ReturnResult() ;
    }
}
