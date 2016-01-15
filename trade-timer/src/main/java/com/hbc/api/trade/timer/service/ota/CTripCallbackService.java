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
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.hbc.api.trade.bdata.common.HttpClientService;
import com.hbc.api.trade.bdata.common.TradeConstant;
import com.hbc.api.trade.bdata.common.sign.CTripMD5;
import com.hbc.api.trade.bdata.common.sign.DESEncrypt;
import com.hbc.api.trade.bdata.common.util.DoubleUtil;
import com.hbc.api.trade.bdata.common.util.TimeConverter;
import com.hbc.api.trade.bdata.mapper.guide.gen.bean.GuideBean;
import com.hbc.api.trade.order.enums.TradeReturnCodeEnum;
import com.hbc.api.trade.order.enums.order.OrderType;
import com.hbc.api.trade.order.exception.TradeException;
import com.hbc.api.trade.order.mapping.gen.TradeMoveInfoMapper;
import com.hbc.api.trade.order.mapping.gen.bean.OrderBean;
import com.hbc.api.trade.order.mapping.gen.bean.TradeMoveInfo;
import com.hbc.api.trade.order.mapping.gen.bean.TradeMoveInfoExample;
import com.hbc.api.trade.ota.enums.ThirdOrderStatus;
import com.hbc.api.trade.ota.mapping.gen.TradeThirdOrderMapper;
import com.hbc.api.trade.ota.mapping.gen.bean.TradeThirdOrder;
import com.hbc.api.trade.ota.resp.ctrip.CTripResult;
import com.hbc.api.trade.timer.service.ota.req.CTripConfirmOrder;
import com.hbc.api.trade.timer.service.ota.req.CTripPushDriverInfo;
import com.hbc.api.trade.timer.service.ota.req.CTripPushOrderComplete;
import com.hbc.api.trade.timer.service.ota.req.CallbackBean;

/**
 * 携程回调函数
 * @author Jongly Ran
 */
@Service
public class CTripCallbackService implements OTACallbackService {
	
	private final Logger logger = LoggerFactory.getLogger(CTripCallbackService.class);
	
	@Autowired
	private HttpClientService httpClientService;
	
	@Value("${trade.ota.ctrip.callback.vendor.id}")
	private String TRADE_OTA_CTRIP_CALLBACK_VENDOR_ID;
	
	@Value("${trade.ota.ctrip.callback.host}")
	private String TRADE_OTA_CTRIP_CALLBACK_HOST;
	
	@Value("${trade.ota.ctrip.callback.des.encrypt.key}")
	private String TRADE_OTA_CTRIP_CALLBACK_DES_ENCRYPT_KEY;
	
	@Autowired private TradeThirdOrderMapper tradeThirdOrderMapper;
	
	
	private class CallBackEntity {
		private String auth;
		private String body;
	}
	
    private CallBackEntity generateSign(String preEncrypt, String timespan) {
    	try {
    		DESEncrypt desEngine = new DESEncrypt(TRADE_OTA_CTRIP_CALLBACK_DES_ENCRYPT_KEY);
			String postEncrypt = desEngine.encrypt(preEncrypt);
	    	String originString = TRADE_OTA_CTRIP_CALLBACK_VENDOR_ID + timespan + TRADE_OTA_CTRIP_CALLBACK_DES_ENCRYPT_KEY + postEncrypt.length();
		    String lowerCase = CTripMD5.getInstance().toMD5(originString).toLowerCase();
		    logger.info("DES前：" + preEncrypt + ", DES后：" + postEncrypt + ", MD5前：" + originString + "，MD5后：" + lowerCase);
		    
		    CallBackEntity entity = new CallBackEntity();
		    entity.auth = lowerCase;
		    entity.body = postEncrypt;
			return entity;
		} catch (Exception e) {
			logger.error("生成sign出错:" + preEncrypt + ", timespan=" +timespan, e);
			throw new TradeException(TradeReturnCodeEnum.GENERATE_SIGN_ERR);
		}
    }
    
	/* (non-Javadoc)
	 * @see com.hbc.api.trade.ota.callback.OTACallBackService#confrimOrder(com.hbc.api.trade.ota.callback.req.CTripConfirmOrder)
	 */
	@Override
	@Transactional
	public void confrimOrder(CallbackBean callbackBean) {
		for(int i = 0; i < 3; i++) {
			OrderBean orderBean = callbackBean.getOrderBean();
			CTripConfirmOrder ctripConfirmOrder = new CTripConfirmOrder();
			ctripConfirmOrder.ConnectTel= TradeConstant.DRIVER_DEFAULT_AREACODE + TradeConstant.DRIVER_DEFAULT_PHONE;;
			ctripConfirmOrder.CtripOrderID=orderBean.getThirdTradeNo();
			ctripConfirmOrder.VendorOrderID=getOrderNo(orderBean.getOrderNo());
			String thirdTradeNo = orderBean.getThirdTradeNo();
			ctripConfirmOrder.CtripOrderID=thirdTradeNo;
			if(orderBean.getOrderType().equals(OrderType.PICKUPORDER.value)) {
				String airportCode = orderBean.getFlightDestCode();
				if(airportCode != null) {
					if("BKK".equals(airportCode)){
						ctripConfirmOrder.CarUseGuideCN = "素万那普国际机场3号门,寻找举\"皇包车\"标识接机牌(黄色)的工作人员";
			        }else if("DMK".equals(airportCode)){
			        	ctripConfirmOrder.CarUseGuideCN = "廊曼机场3号门,寻找举\"皇包车\"标识接机牌(黄色)的工作人员";
			        }else if("HKT".equals(airportCode)){
			        	ctripConfirmOrder.CarUseGuideCN = "普吉国际机场出口左转TMB旁，taxi接机牌候客区,寻找举\"皇包车\"标识接机牌(黄色)的工作人员";
			        }else if("CNX".equals(airportCode)){
			        	ctripConfirmOrder.CarUseGuideCN = "清迈国际机场8号门,寻找举\"皇包车\"标识接机牌(黄色)的工作人员";
			        }
				}
				
			}
			
			try {
				String timestamp = TimeConverter.formatDate(new Date(), TimeConverter.INT_TIMESTAMP);
				CallBackEntity entity = generateSign(JSON.toJSONString(ctripConfirmOrder), timestamp);
				String callbackUrl = TRADE_OTA_CTRIP_CALLBACK_HOST + "/vendormessagebus/OCH-"+TRADE_OTA_CTRIP_CALLBACK_VENDOR_ID+"/order/confirm/"+thirdTradeNo+"/"+timestamp+"/" + entity.auth;
				String responseText = httpClientService.postJSON(callbackUrl, entity.body);
				logger.info("【携程回调】确认订单"+ orderBean.getOrderNo() + "返回值：" + responseText);

				CTripResult result = JSON.parseObject(responseText, CTripResult.class);
				if("OK".equals(result.MsgCode)) {
					logger.info("【携程回调】确认订单成功"+ orderBean.getOrderNo());
					TradeThirdOrder tradeDThirdOrderTemp = tradeThirdOrderMapper.selectByPrimaryKey(orderBean.getOrderNo());
					TradeThirdOrder tradeDThirdOrder = new TradeThirdOrder();
					tradeDThirdOrder.setOrderNo(orderBean.getOrderNo());
					tradeDThirdOrder.setOrderConfirmTime(new Date());
					tradeDThirdOrder.setOrderStatus(ThirdOrderStatus.ORDER_CONFIRMED.value);
					if(ThirdOrderStatus.PAYSUCCESS.value == tradeDThirdOrderTemp.getOrderStatus()  && 
							tradeThirdOrderMapper.updateByPrimaryKeySelective(tradeDThirdOrder) != 1 ){
						logger.error("【携程回调】确认订单,更新三方表状态失败，orderNo="+orderBean.getOrderNo());
						throw new TradeException(TradeReturnCodeEnum.THIRD_ORDER_UPDATE_FAILED, orderBean.getOrderNo());
					}
					logger.info("【携程回调】确认订单,更新三方表状态成功，orderNo="+orderBean.getOrderNo());
					return;
				}
				logger.error("回调携程API以确认订单时失败，,orderNo=" + orderBean.getOrderNo() + ",API返回值为：" + JSON.toJSONString(result));
				TimeUnit.SECONDS.sleep(3);
			} catch (IOException | InterruptedException e) {
				logger.error("回调携程API以推送确认订单时失败,orderNo=" + orderBean.getOrderNo(), e);
			}
		}
		throw new TradeException(TradeReturnCodeEnum.CTRIP_CALLBACK_FAILED);
	}
	
	private String getOrderNo(String orderNo){
		if(orderNo.length()==12 || orderNo.length()==18){
			return orderNo;
		}
		return orderNo.substring(1, orderNo.length());
	}
	@Autowired
	TradeMoveInfoMapper tradeMoveInfoMapper;
	/* (non-Javadoc)
	 * @see com.hbc.api.trade.ota.callback.OTACallBackService#pushDriverInfo(com.hbc.api.trade.ota.callback.req.CTripPushDriverInfo)
	 */
	@Override
	@Transactional
	public void pushDriverInfo(CallbackBean callbackBean ) {
		for(int i = 0; i < 3; i++) {
			TradeThirdOrder tradeThirdOrder = callbackBean.getTradeThirdOrder();
			GuideBean guideBean = callbackBean.getGuideBean();
			CTripPushDriverInfo driverAgree = new CTripPushDriverInfo();
			driverAgree.DriverName=guideBean.getGuideName();
			String areaCode = guideBean.getAreaCode() == null ? "" : "+" + guideBean.getAreaCode();
			driverAgree.DriverPhone= areaCode + guideBean.getMobile(); // orderBean.getGuideAreaCode() + orderBean.getGuideMobile();
			driverAgree.CtripOrderID=tradeThirdOrder.getThirdTradeNo();
			driverAgree.VendorOrderID=getOrderNo(tradeThirdOrder.getOrderNo());
			
			//兼容老订单
			OrderBean orderBean = callbackBean.getOrderBean();
			Date date = TimeConverter.toDate("2015-12-28", TimeConverter.END_WITH_DATE);
			if(orderBean.getGoodNo()!=null && orderBean.getGoodNo().equals("8") && orderBean.getCreateTime().before(date)){
				logger.info("老订单 开始适配老订单 携程规则"+orderBean.getOrderNo());
				
				tradeThirdOrder.setThirdTradeNo(orderBean.getThirdTradeNo());
				String orderNo = orderBean.getOrderNo();
				TradeMoveInfoExample tradeMoveInfoExample = new TradeMoveInfoExample();
				tradeMoveInfoExample.createCriteria().andOrderSnEqualTo(orderNo);
				List<TradeMoveInfo> mlist =	tradeMoveInfoMapper.selectByExample(tradeMoveInfoExample);
				
				if(mlist.size()>=1){
					logger.info("老订单 开始适配老订单 携程规则  修改原有订单ID 为："+mlist.get(0).getOrderId());
					
					driverAgree.CtripOrderID=orderBean.getThirdTradeNo();
					driverAgree.VendorOrderID=mlist.get(0).getOrderId();
					
				}else{
					logger.error("move info 表中无该订单号："+orderNo);
				}
			}
			
			try {
				String timestamp = TimeConverter.formatDate(new Date(), TimeConverter.INT_TIMESTAMP);
				CallBackEntity entity = generateSign(JSON.toJSONString(driverAgree), timestamp);
				String callbackUrl = TRADE_OTA_CTRIP_CALLBACK_HOST + "/vendormessagebus/OCH-"+TRADE_OTA_CTRIP_CALLBACK_VENDOR_ID+"/driver/push/"+driverAgree.CtripOrderID+"/"+timestamp+"/" + entity.auth;
				logger.info("【携程回调】push导游"+tradeThirdOrder.getOrderNo()+"Req：" + callbackUrl);
				String responseText = httpClientService.postJSON(callbackUrl, entity.body);
				logger.info("【携程回调】push导游"+tradeThirdOrder.getOrderNo()+"返回值：" + responseText);
				CTripResult result = JSON.parseObject(responseText, CTripResult.class);
				if("OK".equals(result.MsgCode)) {
					logger.info("【携程回调】回调成功"+tradeThirdOrder.getOrderNo()+"");

					TradeThirdOrder tradeDThirdOrderTemp = tradeThirdOrderMapper.selectByPrimaryKey(tradeThirdOrder.getOrderNo());
					TradeThirdOrder tradeDThirdOrder = new TradeThirdOrder();
					tradeDThirdOrder.setOrderNo(tradeThirdOrder.getOrderNo());
					tradeDThirdOrder.setGuideConfirmTime(new Date());
					tradeDThirdOrder.setOrderStatus(ThirdOrderStatus.GUIDE_CONFIRM.value);
					if(ThirdOrderStatus.ORDER_CONFIRMED.value != tradeDThirdOrderTemp.getOrderStatus() ){
						logger.error(ThirdOrderStatus.GUIDE_CONFIRM.name+ ":第三方订单状态异常 原始状态为：【"+tradeDThirdOrderTemp.getOrderStatus()+"】");
					}
					int optnum = tradeThirdOrderMapper.updateByPrimaryKeySelective(tradeDThirdOrder) ;
					if(optnum!=1){
						throw new TradeException(TradeReturnCodeEnum.THIRD_ORDER_UPDATE_FAILED, tradeThirdOrder.getOrderNo());
					}else{
						logger.info("更新订单状态以及确认导游时间 "+tradeThirdOrder.getOrderNo()+" 成功");
					}
					logger.info("【携程回调】更新三方表状态成功"+tradeThirdOrder.getOrderNo()+"");
					return;
				}
				logger.error("回调携程API以推送车导信息时失败,orderNo=" + orderBean.getOrderNo() + ",API返回值为：" + JSON.toJSONString(result));
				TimeUnit.SECONDS.sleep(3);
			} catch (IOException | InterruptedException e) {
				logger.error("回调携程API以推送车导信息时失败,orderNo=" + orderBean.getOrderNo(), e);
			}
		}
		throw new TradeException(TradeReturnCodeEnum.CTRIP_CALLBACK_FAILED);
	}
	
	/* (non-Javadoc)
	 * @see com.hbc.api.trade.ota.callback.OTACallBackService#pushWhenOrderCompleted(com.hbc.api.trade.ota.callback.req.CTripPushOrderComplete)
	 */
	@Override
	@Transactional
	public void pushWhenOrderCompleted(CallbackBean callbackBean ) {
		OrderBean orderBean = callbackBean.getOrderBean();
		for(int i = 0; i < 3; i++) {
			TradeThirdOrder tradeThirdOrder = callbackBean.getTradeThirdOrder();
			CTripPushOrderComplete pushOrderComplete  = new CTripPushOrderComplete();
			pushOrderComplete.CtripOrderID=tradeThirdOrder.getThirdTradeNo();
			pushOrderComplete.VendorOrderID=getOrderNo(tradeThirdOrder.getOrderNo());
			double distance = orderBean.getDistance() == null ? 0.0 : orderBean.getDistance();
			double unitOfDistance = 1000d;
			pushOrderComplete.DistanceLength= String.valueOf(DoubleUtil.multiplicationDouble(distance, unitOfDistance).intValue());
			int timeLength = orderBean.getExpectedCompTime() == null ? 0 : orderBean.getExpectedCompTime();
			int unitOfTimeLength = 60;
			pushOrderComplete.TimeLength = timeLength * unitOfTimeLength;
			try {
				String timestamp = TimeConverter.formatDate(new Date(), TimeConverter.INT_TIMESTAMP);
				CallBackEntity entity = generateSign(JSON.toJSONString(pushOrderComplete), timestamp);
				String callbackUrl = TRADE_OTA_CTRIP_CALLBACK_HOST + "/vendormessagebus/OCH-"+TRADE_OTA_CTRIP_CALLBACK_VENDOR_ID+"/order/complete/"+tradeThirdOrder.getThirdTradeNo()+"/"+timestamp+"/" + entity.auth;
				logger.info("【携程回调】完成订单"+ tradeThirdOrder.getOrderNo() +"Req：" + callbackUrl);
				String responseText = httpClientService.postJSON(callbackUrl, entity.body);
				logger.info("【携程回调】完成订单返回值：" + responseText);
				CTripResult result = JSON.parseObject(responseText, CTripResult.class);
				if("OK".equals(result.MsgCode)) {
					logger.info("【携程回调】"+ tradeThirdOrder.getOrderNo() +"回调成功");
					TradeThirdOrder tradeDThirdOrderTemp = tradeThirdOrderMapper.selectByPrimaryKey(tradeThirdOrder.getOrderNo());
					TradeThirdOrder tradeDThirdOrder = new TradeThirdOrder();
					tradeDThirdOrder.setOrderNo(tradeThirdOrder.getOrderNo());
					tradeDThirdOrder.setUpdateTime(new Date());
					tradeDThirdOrder.setOrderStatus(ThirdOrderStatus.ORDER_COMPLETE.value);
					if(ThirdOrderStatus.ORDER_CONFIRMED.value == tradeDThirdOrderTemp.getOrderStatus()  && 
							tradeThirdOrderMapper.updateByPrimaryKeySelective(tradeDThirdOrder) != 1 ){
						logger.error("【携程回调】完成订单,更新三方表状态失败，orderNo=" +tradeThirdOrder.getOrderNo());
						throw new TradeException(TradeReturnCodeEnum.THIRD_ORDER_UPDATE_FAILED, tradeThirdOrder.getOrderNo());
					}
					logger.info("【携程回调】完成订单,更新三方表状态成功，orderNo="+tradeThirdOrder.getOrderNo());
					return;
				}
				logger.error("【携程回调】完成订单失败,orderNo=" + orderBean.getOrderNo() + ",API返回值为：" + responseText);
				TimeUnit.SECONDS.sleep(3);
			} catch (IOException | InterruptedException e) {
				logger.error("回调携程API以推送订单完成时失败" + tradeThirdOrder.getOrderNo(), e);
			}
		}

		logger.info("携程订单 结算 携程已经失败  钱直接打给导游 等待以后沟通【"+orderBean.getOrderNo()+"】 【"+orderBean.getThirdTradeNo()+"】");
	}
}
