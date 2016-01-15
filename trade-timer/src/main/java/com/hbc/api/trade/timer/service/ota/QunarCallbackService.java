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

import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.hbc.api.trade.bdata.common.HttpClientService;
import com.hbc.api.trade.bdata.common.TradeConstant;
import com.hbc.api.trade.bdata.common.sign.QunarMD5;
import com.hbc.api.trade.bdata.common.sign.SignValidator;
import com.hbc.api.trade.order.enums.TradeReturnCodeEnum;
import com.hbc.api.trade.order.exception.TradeException;
import com.hbc.api.trade.order.mapping.gen.bean.OrderBean;
import com.hbc.api.trade.ota.enums.ThirdOrderStatus;
import com.hbc.api.trade.ota.mapping.gen.TradeThirdOrderMapper;
import com.hbc.api.trade.ota.mapping.gen.bean.TradeThirdOrder;
import com.hbc.api.trade.ota.service.SignService;
import com.hbc.api.trade.ota.service.SignService.CHARACTER;
import com.hbc.api.trade.ota.service.SignService.POSITION;
import com.hbc.api.trade.timer.service.ota.req.CallbackBean;
import com.hbc.api.trade.timer.service.ota.req.QunarConfirmOrder;
import com.hbc.api.trade.timer.service.ota.req.QunarPushDriverInfo;
import com.hbc.api.trade.timer.service.ota.req.QunarPushOrderComplete;
import com.hbc.api.trade.timer.service.ota.resp.QunarResult;

/**
 * @author Jongly Ran
 */
@Service
public class QunarCallbackService implements OTACallbackService {
	private final static Logger logger = LoggerFactory.getLogger(QunarCallbackService.class);

	//@Value("${trade.ota.qunar.callback.host}")
	private String TRADE_OTA_QUNAR_CALLBACK_HOST="http://intercar.qunar.com/vendor/";
	
	@Value("${trade.ota.qunar.callback.security.key}")
	private String TRADE_OTA_QUNAR_CALLBACK_SECURITY_KEY;
	
	private final static String VENDOR_KEY = "QunHugboGa21";
	private final static String CHANNEL = "hugbogaapi";
	private final static String VERSION = "1.0";
	
	@Autowired private SignService 			 signService ;
	@Autowired private TradeThirdOrderMapper tradeThirdOrderMapper;
	@Autowired private HttpClientService 	 httpClientService;
    
    private String generateSign(Object arg) {
    	Map<String, String> params = SignValidator.buildQueryString(arg);
    	logger.info("【去哪儿回调sign】生成前：" + JSON.toJSONString(params));
	    String sign = signService.createSign(params, TRADE_OTA_QUNAR_CALLBACK_SECURITY_KEY, QunarMD5.getInstance(),
	    		POSITION.BOTH, CHARACTER.UPPERCASE);
	    logger.info("【去哪儿回调sign】生成后：" + sign);
	    return sign;
    }
	
	/* (non-Javadoc)
	 * @see com.hbc.api.trade.ota.callback.OTACallBackService#confrimOrder(com.hbc.api.trade.ota.callback.req.CallbackBean)
	 */
	@Override
	public void confrimOrder(CallbackBean callbackBean) {
		for(int i = 0; i < 3; i++) {
			OrderBean orderBean = callbackBean.getOrderBean();
			QunarConfirmOrder confirmOrderBean = new QunarConfirmOrder();
			confirmOrderBean.setChannel(CHANNEL);
			confirmOrderBean.setOrderId(orderBean.getThirdTradeNo());
			confirmOrderBean.setTimestamp(System.currentTimeMillis());
			confirmOrderBean.setVendorkey(VENDOR_KEY);
			confirmOrderBean.setVersion(VERSION);
			confirmOrderBean.setTtmComfirmType(1);
			confirmOrderBean.setMotorCadeContact(TradeConstant.DRIVER_DEFAULT_PHONE);
			confirmOrderBean.setMotorcadeContactCode(TradeConstant.DRIVER_DEFAULT_AREACODE.substring(1));
			confirmOrderBean.setMotorCadeName(TradeConstant.DRIVER_DEFAULT_NAME);
			confirmOrderBean.setSign(generateSign(confirmOrderBean));
			String jsonBody = JSON.toJSONString(confirmOrderBean);
			try{
				String callbackUrl = TRADE_OTA_QUNAR_CALLBACK_HOST + "car/motorcade/confirm";
				String responseText = httpClientService.postJSON(callbackUrl, jsonBody);
				logger.info("【去哪儿回调】确认订单"+ orderBean.getOrderNo() + ", callbackUrl:" + callbackUrl + ", 返回值：" + responseText);
				QunarResult result = JSON.parseObject(responseText, QunarResult.class);
				if(result.getBstatus().getCode() == 0) {
					logger.info("【去哪儿回调】确认订单成功, orderNo="+ orderBean.getOrderNo());
					TradeThirdOrder tradeDThirdOrderTemp = tradeThirdOrderMapper.selectByPrimaryKey(orderBean.getOrderNo());
					TradeThirdOrder tradeDThirdOrder = new TradeThirdOrder();
					tradeDThirdOrder.setOrderNo(orderBean.getOrderNo());
					tradeDThirdOrder.setOrderConfirmTime(new Date());
					tradeDThirdOrder.setOrderStatus(ThirdOrderStatus.ORDER_CONFIRMED.value);
					if(ThirdOrderStatus.PAYSUCCESS.value == tradeDThirdOrderTemp.getOrderStatus()  && 
							tradeThirdOrderMapper.updateByPrimaryKeySelective(tradeDThirdOrder) != 1 ){
						logger.error("【去哪儿回调】确认订单,更新三方订单表失败:" + orderBean.getOrderNo());
						throw new TradeException(TradeReturnCodeEnum.THIRD_ORDER_UPDATE_FAILED, orderBean.getOrderNo());
					}
					logger.info("【去哪儿回调】确认订单,更新三方表状态成功"+orderBean.getOrderNo());
					return;
				}
				logger.error("【去哪儿回调】确认订单失败,orderNo=" + orderBean.getOrderNo() + ",API返回值为：" + JSON.toJSONString(result));
				TimeUnit.SECONDS.sleep(3);
			} catch (IOException | InterruptedException | JSONException e) {
				logger.error("【去哪儿回调】确认订单失败, orderNo=" + orderBean.getOrderNo(), e);
			}
		}
		throw new TradeException(TradeReturnCodeEnum.QUNAR_CALLBACK_FAILED);
	}

	/* (non-Javadoc)
	 * @see com.hbc.api.trade.ota.callback.OTACallBackService#pushDriverInfo(com.hbc.api.trade.ota.callback.req.CallbackBean)
	 */
	@Override
	public void pushDriverInfo(CallbackBean callbackBean) {
		for(int i = 0; i < 3; i++) {
			OrderBean orderBean = callbackBean.getOrderBean();
			QunarPushDriverInfo pushDriverInfo = new QunarPushDriverInfo();
			pushDriverInfo.setChannel(CHANNEL);
			pushDriverInfo.setOrderId(orderBean.getThirdTradeNo());
			pushDriverInfo.setTimestamp(System.currentTimeMillis());
			pushDriverInfo.setVendorkey(VENDOR_KEY);
			pushDriverInfo.setVersion(VERSION);
			pushDriverInfo.setTtmComfirmType(2);
			pushDriverInfo.setDriverName(TradeConstant.DRIVER_DEFAULT_NAME);	
			pushDriverInfo.setDriverMobile(TradeConstant.DRIVER_DEFAULT_PHONE);	
			pushDriverInfo.setDriverLanguage(2);
			pushDriverInfo.setLicensePlate("无");
			pushDriverInfo.setPickingPosition(orderBean.getStartAddress());	
			String startAddressDetail = orderBean.getStartAddressDetail();
			pushDriverInfo.setPickingPositionDetail(startAddressDetail == null ? orderBean.getStartAddress() : startAddressDetail);	
			pushDriverInfo.setDriverMobileCode(TradeConstant.DRIVER_DEFAULT_AREACODE.substring(1));	
			pushDriverInfo.setSign(generateSign(pushDriverInfo));
			String jsonBody = JSON.toJSONString(pushDriverInfo);
			try{
				String callbackUrl = TRADE_OTA_QUNAR_CALLBACK_HOST + "car/driver/confirm";
				String responseText = httpClientService.postJSON(callbackUrl, jsonBody);
				String orderNo = orderBean.getOrderNo();
				logger.info("【去哪儿回调】指派司机"+ orderNo  + ", callbackUrl:" + callbackUrl + ", 返回值：" + responseText);
				QunarResult result = JSON.parseObject(responseText, QunarResult.class);
				if(result.getBstatus().getCode() == 0) {
					logger.info("【去哪儿回调】指派司机成功, orderNo="+ orderNo);
					TradeThirdOrder tradeDThirdOrderTemp = tradeThirdOrderMapper.selectByPrimaryKey(orderNo);
					TradeThirdOrder tradeDThirdOrder = new TradeThirdOrder();
					tradeDThirdOrder.setOrderNo(orderNo);
					tradeDThirdOrder.setGuideConfirmTime(new Date());
					tradeDThirdOrder.setOrderStatus(ThirdOrderStatus.GUIDE_CONFIRM.value);
					if(ThirdOrderStatus.ORDER_CONFIRMED.value != tradeDThirdOrderTemp.getOrderStatus() &&
							tradeThirdOrderMapper.updateByPrimaryKeySelective(tradeDThirdOrder) != 1){
						logger.info("【去哪儿回调】指派司机,更新三方表状态失败, orderNo="+orderNo);
						throw new TradeException(TradeReturnCodeEnum.THIRD_ORDER_UPDATE_FAILED, orderNo);
					}
					logger.info("【去哪儿回调】指派司机,更新三方表状态成功, orderNo="+orderNo);
					return;
				}
				logger.error("【去哪儿回调】指派司机失败,orderNo=" + orderBean.getOrderNo() + ",API返回值为：" + JSON.toJSONString(result));
				TimeUnit.SECONDS.sleep(3);
			} catch (IOException | InterruptedException | JSONException e) {
				logger.error("【去哪儿回调】指派司机失败, orderNo="+orderBean.getOrderNo(), e);
			}
		}
		throw new TradeException(TradeReturnCodeEnum.QUNAR_CALLBACK_FAILED);
	}

	/* (non-Javadoc)
	 * @see com.hbc.api.trade.ota.callback.OTACallBackService#pushWhenOrderCompleted(com.hbc.api.trade.ota.callback.req.CallbackBean)
	 */
	@Override
	public void pushWhenOrderCompleted(CallbackBean callbackBean) {
		OrderBean orderBean = callbackBean.getOrderBean();
		for(int i = 0; i < 3; i++) {
			QunarPushOrderComplete orderCompletedBean = new QunarPushOrderComplete();
			orderCompletedBean.setChannel(CHANNEL);
			orderCompletedBean.setOrderId(orderBean.getThirdTradeNo());
			orderCompletedBean.setTimestamp(System.currentTimeMillis());
			orderCompletedBean.setVendorkey(VENDOR_KEY);
			orderCompletedBean.setVersion(VERSION);
			orderCompletedBean.setSign(generateSign(orderCompletedBean));
			String jsonBody = JSON.toJSONString(orderCompletedBean);
			try{
				String callbackUrl = TRADE_OTA_QUNAR_CALLBACK_HOST + "completion/confirm";
				String responseText = httpClientService.postJSON(callbackUrl, jsonBody);
				String orderNo = orderBean.getOrderNo();
				logger.info("【去哪儿回调】完成订单"+ orderNo  + ", callbackUrl:" + callbackUrl + ", 返回值：" + responseText);
				QunarResult result = JSON.parseObject(responseText, QunarResult.class);
				if(result.getBstatus().getCode() == 0) {
					logger.info("【去哪儿回调】完成订单成功, orderNo="+ orderNo);
					TradeThirdOrder tradeDThirdOrderTemp = tradeThirdOrderMapper.selectByPrimaryKey(orderNo);
					TradeThirdOrder tradeDThirdOrder = new TradeThirdOrder();
					tradeDThirdOrder.setOrderNo(orderNo);
					tradeDThirdOrder.setUpdateTime(new Date());
					tradeDThirdOrder.setOrderStatus(ThirdOrderStatus.ORDER_COMPLETE.value);
					if(ThirdOrderStatus.ORDER_CONFIRMED.value == tradeDThirdOrderTemp.getOrderStatus()  && 
							tradeThirdOrderMapper.updateByPrimaryKeySelective(tradeDThirdOrder) != 1 ){
						logger.error("【去哪儿回调】完成订单，更新三方表状态失败"+orderNo);
						throw new TradeException(TradeReturnCodeEnum.THIRD_ORDER_UPDATE_FAILED, orderNo);
					}
					logger.info("【去哪儿回调】完成订单，更新三方表状态成功"+orderNo);
					return;
				}
				logger.error("【去哪儿回调】完成订单失败,orderNo=" + orderBean.getOrderNo() + ",API返回值为：" + JSON.toJSONString(result));
				TimeUnit.SECONDS.sleep(3);
			} catch (IOException | InterruptedException | JSONException e) {
				logger.error("【去哪儿回调】完成订单失败,orderNo=" + orderBean.getOrderNo(), e);
			}
		}
		logger.info("【去哪儿回调】完成订单失败,结算失败，钱直接打给导游 等待以后沟通【"+orderBean.getOrderNo()+"】 【"+orderBean.getThirdTradeNo()+"】");
	}

}
