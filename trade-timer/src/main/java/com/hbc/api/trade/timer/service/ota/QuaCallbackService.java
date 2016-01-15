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
package com.hbc.api.trade.timer.service.ota;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.hbc.api.trade.bdata.common.TradeConstant;
import com.hbc.api.trade.bdata.common.redis.RedisDao;
import com.hbc.api.trade.bdata.common.util.TimeConverter;
import com.hbc.api.trade.order.enums.TradeReturnCodeEnum;
import com.hbc.api.trade.order.exception.TradeException;
import com.hbc.api.trade.order.mapping.gen.bean.OrderBean;
import com.hbc.api.trade.ota.enums.ThirdOrderStatus;
import com.hbc.api.trade.ota.mapping.gen.TradeThirdOrderMapper;
import com.hbc.api.trade.ota.mapping.gen.bean.TradeThirdOrder;
import com.hbc.api.trade.timer.service.ota.req.CallbackBean;
import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlitripCarOrderAcceptRequest;
import com.taobao.api.request.AlitripCarOrderAcceptRequest.OrderAccept;
import com.taobao.api.request.AlitripCarOrderCompleteRequest;
import com.taobao.api.request.AlitripCarOrderCompleteRequest.OrderComplete;
import com.taobao.api.request.AlitripCarOrderConfirmRequest;
import com.taobao.api.request.AlitripCarOrderConfirmRequest.OrderConfirm;
import com.taobao.api.response.AlitripCarOrderAcceptResponse;
import com.taobao.api.response.AlitripCarOrderAcceptResponse.ApiResult;
import com.taobao.api.response.AlitripCarOrderCompleteResponse;
import com.taobao.api.response.AlitripCarOrderConfirmResponse;

/**
 * @author Jongly Ran
 */
@Service
public class QuaCallbackService implements OTACallbackService {
	private final static Logger logger = LoggerFactory.getLogger(QuaCallbackService.class);
	
	@Autowired RedisDao redisDao;
	
	@Value("${trade.ota.qua.callback.host}")
	private String TRADE_OTA_QUA_CALLBACK_HOST;
	
	@Value("${trade.ota.qua.callback.app.provider.id}")
	private String TRADE_OTA_QUA_CALLBACK_PROVIDER_ID;
	
	@Value("${trade.ota.qua.callback.app.key}")
	private String TRADE_OTA_QUA_CALLBACK_APP_KEY;
	
	@Value("${trade.ota.qua.callback.app.secret}")
	private String TRADE_OTA_QUA_CALLBACK_APP_SECRET;
	
	@Autowired private TradeThirdOrderMapper tradeThirdOrderMapper;
	
	/* (non-Javadoc)
	 * @see com.hbc.api.trade.ota.callback.OTACallbackService#confrimOrder(com.hbc.api.trade.ota.callback.req.CallbackBean)
	 */
	@Override
	@Transactional
	public void confrimOrder(CallbackBean callbackBean) {
		for(int i = 0; i < 3; i++) {
			OrderBean orderBean = callbackBean.getOrderBean();
			OrderAccept paramOrderAccept = new OrderAccept();
			paramOrderAccept.setOrderId(orderBean.getThirdTradeNo());
			paramOrderAccept.setProviderId(TRADE_OTA_QUA_CALLBACK_PROVIDER_ID);
			paramOrderAccept.setThirdOrderId(orderBean.getOrderNo());
			paramOrderAccept.setConfirmType(0l); // 0 接单，1拒绝
	        AlitripCarOrderAcceptRequest req = new AlitripCarOrderAcceptRequest();
			req.setParamOrderAccept(paramOrderAccept);
			try {
				TaobaoClient client = new DefaultTaobaoClient(TRADE_OTA_QUA_CALLBACK_HOST, TRADE_OTA_QUA_CALLBACK_APP_KEY, TRADE_OTA_QUA_CALLBACK_APP_SECRET);
				AlitripCarOrderAcceptResponse rsp = client.execute(req);
				ApiResult result = rsp.getResult();
				String responseText = JSON.toJSONString(rsp);
				logger.info("【阿里去啊】确认订单response：" + responseText);
		        Long code = result.getCode(); // 唯一一处与其他接口不同
		        if(code != null && code.intValue() == 0) { 
		        	logger.info("【阿里去啊】回调成功");
		    		TradeThirdOrder tradeDThirdOrderTemp = tradeThirdOrderMapper.selectByPrimaryKey(orderBean.getOrderNo());
		    		TradeThirdOrder tradeDThirdOrder = new TradeThirdOrder();
		    		tradeDThirdOrder.setOrderNo(orderBean.getOrderNo());
		    		tradeDThirdOrder.setOrderConfirmTime(new Date());
		    		tradeDThirdOrder.setOrderStatus(ThirdOrderStatus.ORDER_CONFIRMED.value);
		    		if(ThirdOrderStatus.PAYSUCCESS.value == tradeDThirdOrderTemp.getOrderStatus() 
		    				&& tradeThirdOrderMapper.updateByPrimaryKeySelective(tradeDThirdOrder) != 1 ){
		    			logger.error(ThirdOrderStatus.ORDER_CONFIRMED.name+ ":更新三方订单表失败");
		    			throw new TradeException(TradeReturnCodeEnum.THIRD_ORDER_UPDATE_FAILED, orderBean.getOrderNo());
		    		}
					logger.info("【阿里去啊】更新三方表状态成功"+orderBean.getOrderNo()+"");
		        	return;  
		        } 
		        logger.error("【阿里去啊】回调确认订单发生异常,orderNo=" + orderBean.getOrderNo() + ",返回对象JSON：", responseText);
				TimeUnit.SECONDS.sleep(3);
			} catch (ApiException | InterruptedException e) {
				logger.error("【阿里去啊】回调确认订单发生异常,orderNo=" + orderBean.getOrderNo(), e);
			}
		}
		throw new TradeException(TradeReturnCodeEnum.QUA_CALLBACK_FAILED);
	}

	/* (non-Javadoc)
	 * @see com.hbc.api.trade.ota.callback.OTACallbackService#pushDriverInfo(com.hbc.api.trade.ota.callback.req.CallbackBean)
	 */
	@Override
	@Transactional
	public void pushDriverInfo(CallbackBean callbackBean) {
		for(int i = 0; i < 3; i++) {
			OrderBean orderBean = callbackBean.getOrderBean();
			OrderConfirm paramOrderConfirm = new OrderConfirm();
			paramOrderConfirm.setOrderId(orderBean.getThirdTradeNo());
			paramOrderConfirm.setProviderId(TRADE_OTA_QUA_CALLBACK_PROVIDER_ID);
			paramOrderConfirm.setThirdOrderId(orderBean.getOrderNo());
			paramOrderConfirm.setConfirmTime(TimeConverter.formatDate(new Date()));
			paramOrderConfirm.setDriverName(TradeConstant.DRIVER_DEFAULT_NAME);
			paramOrderConfirm.setDriverTel(TradeConstant.DRIVER_DEFAULT_AREACODE + TradeConstant.SPLITER_LINE + TradeConstant.DRIVER_DEFAULT_PHONE);
			paramOrderConfirm.setDriverCarName(orderBean.getCarName());
			paramOrderConfirm.setDriverCarDesc(orderBean.getCarDesc());
			paramOrderConfirm.setConfirmType(0l); // 0 接单，1拒绝
			AlitripCarOrderConfirmRequest req = new AlitripCarOrderConfirmRequest();
			req.setParamOrderConfirm(paramOrderConfirm);
			try {
				TaobaoClient client = new DefaultTaobaoClient(TRADE_OTA_QUA_CALLBACK_HOST, TRADE_OTA_QUA_CALLBACK_APP_KEY, TRADE_OTA_QUA_CALLBACK_APP_SECRET);
				AlitripCarOrderConfirmResponse rsp = client.execute(req);
				String responseText = JSON.toJSONString(rsp);
				logger.info("【阿里去啊】push 司机信息response：" + responseText);
		        Long code = rsp.getMessageCode(); 
		        if(code != null && code.intValue() == 0) { 
		        	logger.info("更新主订单表成功");
		    		TradeThirdOrder tradeDThirdOrderTemp = tradeThirdOrderMapper.selectByPrimaryKey(orderBean.getOrderNo());
		    		TradeThirdOrder tradeDThirdOrder = new TradeThirdOrder();
		    		tradeDThirdOrder.setOrderNo(orderBean.getOrderNo());
		    		tradeDThirdOrder.setGuideConfirmTime(new Date());
		    		tradeDThirdOrder.setOrderStatus(ThirdOrderStatus.GUIDE_CONFIRM.value);
		    		if(ThirdOrderStatus.ORDER_CONFIRMED.value == tradeDThirdOrderTemp.getOrderStatus() 
		    				&& tradeThirdOrderMapper.updateByPrimaryKeySelective(tradeDThirdOrder) != 1 ){
		    			logger.error(ThirdOrderStatus.GUIDE_CONFIRM.name+ ":更新三方订单表失败");
		    			throw new TradeException(TradeReturnCodeEnum.THIRD_ORDER_UPDATE_FAILED, orderBean.getOrderNo());
		    		}
					logger.info("【阿里去啊】更新三方表状态成功"+orderBean.getOrderNo()+"");
		        	return;  
		        } 
		        logger.error("【阿里去啊】push 司机信息发生异常,orderNo=" + orderBean.getOrderNo() + ",去啊返回对象JSON：", responseText);
		        TimeUnit.SECONDS.sleep(3);
			} catch (ApiException | InterruptedException e) {
				logger.error("【阿里去啊】回调push 司机信息发生异常,orderNo=" + orderBean.getOrderNo(), e);
			}
		}
		throw new TradeException(TradeReturnCodeEnum.QUA_CALLBACK_FAILED);
	}

	/* (non-Javadoc)
	 * @see com.hbc.api.trade.ota.callback.OTACallbackService#pushWhenOrderCompleted(com.hbc.api.trade.ota.callback.req.CallbackBean)
	 */
	@Override
	@Transactional
	public void pushWhenOrderCompleted(CallbackBean callbackBean) {
		OrderBean orderBean = callbackBean.getOrderBean();
		for(int i = 0; i < 3; i++) {
	        OrderComplete paramOrderComplete = new OrderComplete();
			paramOrderComplete.setOrderId(orderBean.getThirdTradeNo());
			paramOrderComplete.setProviderId(TRADE_OTA_QUA_CALLBACK_PROVIDER_ID);
			paramOrderComplete.setThirdOrderId(orderBean.getOrderNo());
			Date completeTime = orderBean.getCompleteTime();
			if(completeTime == null) {
				completeTime = new Date();
			}
			paramOrderComplete.setCompleteTime(TimeConverter.formatDate(completeTime));
			AlitripCarOrderCompleteRequest paramTaobaoRequest = new AlitripCarOrderCompleteRequest();
			paramTaobaoRequest.setParamOrderComplete(paramOrderComplete);
			try {
				TaobaoClient client = new DefaultTaobaoClient(TRADE_OTA_QUA_CALLBACK_HOST, TRADE_OTA_QUA_CALLBACK_APP_KEY, TRADE_OTA_QUA_CALLBACK_APP_SECRET);
				AlitripCarOrderCompleteResponse rsp = client.execute(paramTaobaoRequest);
				String responseText = JSON.toJSONString(rsp);
				logger.info("【阿里去啊】push订单完成response：" + responseText);
		        Long code = rsp.getMessageCode(); 
		        if(code != null && code.intValue() == 0) {
		        	logger.info("【阿里去啊】回调push订单完成成功");
		    		TradeThirdOrder tradeDThirdOrderTemp = tradeThirdOrderMapper.selectByPrimaryKey(orderBean.getOrderNo());
		    		TradeThirdOrder tradeDThirdOrder = new TradeThirdOrder();
		    		tradeDThirdOrder.setOrderNo(orderBean.getOrderNo());
		    		tradeDThirdOrder.setUpdateTime(new Date());
		    		tradeDThirdOrder.setOrderStatus(ThirdOrderStatus.ORDER_COMPLETE.value);
		    		if(ThirdOrderStatus.GUIDE_CONFIRM.value == tradeDThirdOrderTemp.getOrderStatus() 
		    				&& tradeThirdOrderMapper.updateByPrimaryKeySelective(tradeDThirdOrder) != 1 ){
		    			logger.error(ThirdOrderStatus.ORDER_COMPLETE.name+ ":更新三方订单表失败");
		    			throw new TradeException(TradeReturnCodeEnum.THIRD_ORDER_UPDATE_FAILED, orderBean.getOrderNo());
		    		}
					logger.info("【阿里去啊】更新三方表状态成功"+orderBean.getOrderNo()+"");
		        	return;  
		        } 
		        logger.error("【阿里去啊】push订单完成发生异常,orderNo=" + orderBean.getOrderNo() + ",去啊返回对象JSON：", responseText);
				TimeUnit.SECONDS.sleep(3);
			} catch (ApiException | InterruptedException e) {
				logger.error("【阿里去啊】ppush订单完成发生异常,orderNo=" + orderBean.getOrderNo(), e);
			}
		}
		logger.info("【阿里去啊】完成订单失败,结算失败，钱直接打给导游 等待以后沟通【"+orderBean.getOrderNo()+"】 【"+orderBean.getThirdTradeNo()+"】");
	}
}
