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

import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.hbc.api.trade.bdata.common.util.BeanUtilsEnhance;
import com.hbc.api.trade.order.enums.TradeReturnCodeEnum;
import com.hbc.api.trade.order.exception.TradeException;
import com.hbc.api.trade.order.mapping.gen.bean.OrderBean;
import com.hbc.api.trade.order.validator.OrderValidator;
import com.hbc.api.trade.ota.common.ChannelSet;
import com.hbc.api.trade.ota.qunar.req.QuanrOrderListDetail;
import com.hbc.api.trade.ota.qunar.req.QunarCalculatePriceParam;
import com.hbc.api.trade.ota.qunar.req.QunarOrderCancelParam;
import com.hbc.api.trade.ota.qunar.req.QunarOrderDetailParam;
import com.hbc.api.trade.ota.qunar.req.QunarOrderListParam;
import com.hbc.api.trade.ota.qunar.req.QunarOrderSubmitParam;
import com.hbc.api.trade.ota.qunar.req.QunarOrderUpdateParam;
import com.hbc.api.trade.ota.qunar.req.QunarPassengerInfo;
import com.hbc.api.trade.ota.req.CalculatePriceParam;
import com.hbc.api.trade.ota.req.OrderCancelParam;
import com.hbc.api.trade.ota.req.OrderDetailParam;
import com.hbc.api.trade.ota.req.OrderModifyParam;
import com.hbc.api.trade.ota.req.OrderSubmitParam;
import com.hbc.api.trade.ota.resp.qunar.QunarCalculatePriceResult;
import com.hbc.api.trade.ota.resp.qunar.QunarCancelOrderResult;
import com.hbc.api.trade.ota.resp.qunar.QunarOrderDetailResult;
import com.hbc.api.trade.ota.resp.qunar.QunarOrderSubmitDetail;
import com.hbc.api.trade.ota.resp.qunar.QunarOrderSubmitResult;
import com.hbc.api.trade.ota.resp.qunar.QunarResult;
import com.hbc.api.trade.ota.resp.qunar.QunarServiceInfo;
import com.hbc.api.trade.ota.service.QunarOrderService;
import com.hbc.api.trade.ota.validator.Channel;

/**
 * 已确认如果返回值多于他们需要的，不会报错
 * <p>v1.0 去哪儿给我们的securityKey = 191c4934f5cd468a0c7e7cc1173a8cda</p>
 * <p>被v2.0代替</p>
 * @author Jongly Ran
 */
@Deprecated
@RestController
@RequestMapping(value = "v1.0/api")
@Channel(ChannelSet.QUNAR)
public class QunarController {
	private Logger log = LoggerFactory.getLogger(QunarController.class);
	
    @Autowired private QunarOrderService 		qunarOrderService ;

	@RequestMapping(value = "calculateprice", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public QunarResult calculatePrice(@RequestBody QunarCalculatePriceParam inputs, HttpServletRequest request) {
		log.info("去哪儿查价RequestParam:" + JSON.toJSONString(inputs));
		OrderValidator.validateParamString(inputs.getAirportCode(), "airportCode");
		OrderValidator.validateParamString(inputs.getOrderTime(), "orderTime");
		OrderValidator.validateParamNumber(inputs.getFromLong(), "fromLong");
		OrderValidator.validateParamNumber(inputs.getFromLat(), "fromLat");
		OrderValidator.validateParamNumber(inputs.getToLong(), "toLong");
		OrderValidator.validateParamNumber(inputs.getToLat(), "toLat");
		OrderValidator.validateParamNumber(inputs.getServiceType(), "serviceType");
		CalculatePriceParam param = inputs.toStandardCalculatePriceParam();
		QunarCalculatePriceResult resultBean = qunarOrderService.getPrice(param);
		log.info("去哪儿查价返回:" + JSON.toJSONString(resultBean));
		return resultBean;
	}

    @RequestMapping(value="ordersubmit", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public QunarResult orderSubmit(@RequestBody QunarOrderSubmitParam inputs, HttpServletRequest request) {
    	log.info("去哪儿下单RequestParam:" + JSON.toJSONString(inputs));
		validateSubmitOrder(inputs);
		
		QunarServiceInfo serviceInfo = inputs.getServiceInfo();
		QunarPassengerInfo passengerInfo = inputs.getPassengerInfo();
    	OrderSubmitParam param = inputs.toStandardSubmitParam(inputs.getCarType());
    	OrderBean orderBean = param.getOrderBean();
    	// staffNum是总人数，childrenChair是车上的儿童座椅数，显然staffNum不一定等于成人数。兼容老版本这么写。
		orderBean.setAdultNum(serviceInfo.getStaffNum());
		orderBean.setChildNum(serviceInfo.getChildrenChair()); 
		orderBean.setUserAreaCode1(passengerInfo.getMobileCode());
		orderBean.setUserMobile1(passengerInfo.getPhone());
		orderBean.setUserAreaCode2(passengerInfo.getMobileCodeOuter());
		orderBean.setUserMobile2(passengerInfo.getPhoneOuter());
		orderBean.setUserName(passengerInfo.getName());
		param.setOrderBean(orderBean);
		
        QunarOrderSubmitResult resultBean = qunarOrderService.createOrder(param);
        resultBean.setvOrderId(resultBean.getvOrderId().substring(1)); // 仅支持数字
        String qOrderId = resultBean.getqOrderId();
        BeanUtilsEnhance.copyProperties(resultBean, inputs);
        QunarOrderSubmitDetail detail = new QunarOrderSubmitDetail();
        detail.setBasePrice(Double.valueOf(inputs.getTotalPrice()));
		resultBean.detail=(detail );
        resultBean.setqOrderId(qOrderId);
        resultBean.setLuggageNum(inputs.getLuggageNum());
		log.info("去哪儿下单返回:" + JSON.toJSONString(resultBean));
		return resultBean;
    }

	/**
	 * @param inputs
	 */
	private void validateSubmitOrder(QunarOrderSubmitParam inputs) {
		OrderValidator.validateParamNumber(inputs.getServiceType(), "serviceType");
		OrderValidator.validateParamNumber(inputs.getCarType(), "carType");
		OrderValidator.validateParamString(inputs.getDistance(), "distance");
		OrderValidator.validateParamNumber(inputs.getEstimatedTime(), "estimatedTime");
		OrderValidator.validateParamNumber(inputs.getTotalPrice(), "totalPrice");
		OrderValidator.validateParamString(inputs.getFromLong(), "fromLong");
		OrderValidator.validateParamString(inputs.getFromLat(), "fromLat");
		OrderValidator.validateParamString(inputs.getToLong(), "toLong");
		OrderValidator.validateParamString(inputs.getToLat(), "toLat");
		
		OrderValidator.validateParamString(inputs.getqOrderId(), "qOrderId");
		OrderValidator.validateParamString(inputs.getAirportCode(), "airportCode");
		OrderValidator.validateParamString(inputs.getOrderTime(), "orderTime");
		OrderValidator.validateParamString(inputs.getFromAddrName(), "fromAddrName");
		OrderValidator.validateParamString(inputs.getToAddrName(), "toAddrName");
		
		QunarServiceInfo serviceInfo = inputs.getServiceInfo();
		QunarPassengerInfo passengerInfo = inputs.getPassengerInfo();
		if(serviceInfo == null || passengerInfo == null ) {
			log.error("去哪儿下单参数错误：serviceInfo=" + JSON.toJSONString(serviceInfo) + ",passengerInfo=" + JSON.toJSONString(passengerInfo));
			throw new TradeException(TradeReturnCodeEnum.ORDER_PARAM_FAILED, "serviceInfo || passengerInfo");
		}
		OrderValidator.validateParamNumber(serviceInfo.getStaffNum(), "serviceInfo.staffNum");
		OrderValidator.validateParamString(passengerInfo.getName(), "passengerInfo.name");
		OrderValidator.validateParamString(passengerInfo.getPhone(), "passengerInfo.phone");
		OrderValidator.validateParamString(passengerInfo.getMobileCode(), "passengerInfo.mobileCode");
		QunarOrderSubmitDetail priceDetail = inputs.getDetail();
		log.info("去哪儿下单订单价格明细：" + JSON.toJSONString(priceDetail));
	}
    
	/**
	 * v2.0被替代，采用回调函数
	 * @param inputs
	 * @param request
	 * @return
	 */
	@Deprecated
    @RequestMapping(value="orderstatus", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public QunarResult orderList(@RequestBody QunarOrderListParam inputs, HttpServletRequest request) {
    	log.info("去哪儿查状态列表RequestParam:" + JSON.toJSONString(inputs));
    	List<QuanrOrderListDetail> orderInfo = inputs.getOrderInfo();
    	if(orderInfo == null) {
	    	log.error("三方订单号不能为空");
			throw new TradeException(TradeReturnCodeEnum.ORDER_PARAM_FAILED, "三方订单列表");
    	}

    	List<String> thirdNoList = new LinkedList<>();
    	for(QuanrOrderListDetail info : orderInfo) {
    		if(info.getqOrderId() != null) {
    			thirdNoList.add(info.getqOrderId());
    		}
    	}
		QunarResult resultBean = qunarOrderService.orderList(thirdNoList , inputs.getQueryTime());
		log.info("去哪儿查状态列表返回:" + JSON.toJSONString(resultBean));
		return resultBean;
    }

    @RequestMapping(value="orderdetail", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public QunarResult getOrderDetail(@RequestBody QunarOrderDetailParam inputs, HttpServletRequest request) {
		OrderValidator.validateParamString(inputs.getqOrderId(), "qOrderId");
		OrderValidator.validateParamString(inputs.getSign(), "sign");
    	log.info("去哪儿查详情RequestParam:" + JSON.toJSONString(inputs));
    	OrderDetailParam param = inputs.toStandardOrderDetailBean();
        QunarOrderDetailResult resultBean = qunarOrderService.getOrderDetail(param);
		log.info("去哪儿查详情返回:" + JSON.toJSONString(resultBean));
		return resultBean ;
    }
    
    @RequestMapping(value="ordercancel", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public QunarResult orderCancel(@RequestBody QunarOrderCancelParam inputs, HttpServletRequest request) {
    	log.info("去哪儿取消订单RequestParam:" + JSON.toJSONString(inputs));
		OrderValidator.validateParamString(inputs.getqOrderId(), "qOrderId");
    	OrderCancelParam param = inputs.toStandardOrderDetailBean();
        qunarOrderService.cancelThirdOrder(param);
        QunarCancelOrderResult resultBean = new QunarCancelOrderResult(inputs.getqOrderId());
		log.info("去哪儿取消订单返回:" + JSON.toJSONString(resultBean));
		return resultBean ;
    }
    
    @RequestMapping(value="orderupdate", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public QunarResult orderModify(@RequestBody QunarOrderUpdateParam inputs, HttpServletRequest request) {
    	log.info("去哪儿修改订单RequestParam:" + JSON.toJSONString(inputs));
		OrderValidator.validateParamString(inputs.getqOrderId(), "qOrderId");
    	OrderModifyParam param = inputs.toStandardOrderModifyParam();
    	qunarOrderService.updateOrder(param);
        QunarCancelOrderResult qunarCancelOrderResult = new QunarCancelOrderResult(inputs.getqOrderId());
		log.info("去哪儿修改订单返回:" + JSON.toJSONString(qunarCancelOrderResult));
		return qunarCancelOrderResult ;
    }
}
