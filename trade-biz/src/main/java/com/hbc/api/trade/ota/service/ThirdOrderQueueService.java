package com.hbc.api.trade.ota.service;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.hbc.api.trade.bdata.common.HttpClientService;
import com.hbc.api.trade.bdata.common.redis.RedisDao;
import com.hbc.api.trade.order.enums.TradeReturnCodeEnum;
import com.hbc.api.trade.order.exception.TradeException;
import com.hbc.api.trade.order.mapping.gen.bean.OrderBean;
import com.hbc.api.trade.order.service.OrderQueryService;
import com.hbc.api.trade.ota.enums.ThirdOrderStatus;
import com.hbc.api.trade.ota.mapping.gen.TradeThirdOrderMapper;
import com.hbc.api.trade.ota.mapping.gen.bean.TradeThirdOrder;

@Component
public class ThirdOrderQueueService {

	private final static Logger log = LoggerFactory.getLogger(ThirdOrderQueueService.class);
	private String confirmKey = "tradeThirdOrderConfirmQueue" ;
	private String completeKey = "tradeThirdOrderCompleteQueue" ;
	
	@Autowired RedisDao redisDao;
	@Autowired OrderQueryService orderQueryService;
	@Autowired HttpClientService httpClientService ;
	@Autowired TradeThirdOrderMapper tradeThirdOrderMapper;

	public void addThirdConfirmQueue(String orderNo, String url, int callbackCount) {
		if (url == "") {
			throw new TradeException(TradeReturnCodeEnum.ORDER_PARAM_FAILED, "url参数不能为空");
		}
		TradeThirdOrder tradeThirdOrder = tradeThirdOrderMapper.selectByPrimaryKey(orderNo);
		if (tradeThirdOrder == null) {
			throw new TradeException(TradeReturnCodeEnum.ORDER_NOTEXIST, "订单不存在, thirdId:" + orderNo);
		}
		if (tradeThirdOrder.getOrderStatus() != ThirdOrderStatus.GUIDE_CONFIRM.value) {
			throw new TradeException(TradeReturnCodeEnum.ORDER_STATUS_TYPE, "订单状态错误 orderStatus:" + tradeThirdOrder.getOrderStatus() + " " + ThirdOrderStatus.GUIDE_CONFIRM.value );
		}
		try {
			JSONObject jsonObject = new JSONObject() ;
			jsonObject.put("orderNo", tradeThirdOrder.getOrderNo());
			jsonObject.put("url", url);
			jsonObject.put("orderStatus", tradeThirdOrder.getOrderStatus());
			jsonObject.put("callbackCount", callbackCount);
			redisDao.lpush(this.confirmKey, jsonObject.toJSONString());
		} catch (TradeException e) {
			log.error(e.getMessage());
			throw new TradeException(TradeReturnCodeEnum.TRADE_THIRD_QUEUE_FAIL);
		}
	}
	
	public void execThirdConfirmQueue() {
		String queueData = redisDao.blpop(this.confirmKey);
		try {
			if (queueData != null) {
				JSONObject postJson = new JSONObject() ;
				JSONObject queueJson = JSON.parseObject(queueData);
				TradeThirdOrder tradeThirdOrder = tradeThirdOrderMapper.selectByPrimaryKey(queueJson.getString("orderNo"));
				OrderBean orderBean = orderQueryService.getOrderByNo(tradeThirdOrder.getOrderNo()) ;
				if (tradeThirdOrder == null || orderBean == null) {
					throw new TradeException(TradeReturnCodeEnum.ORDER_NOTEXIST);
				}
				postJson.put("servicePartner", tradeThirdOrder.getThirdPartner());
				postJson.put("thirdTradeNo", tradeThirdOrder.getThirdTradeNo());
				postJson.put("orderNo", tradeThirdOrder.getOrderNo());
				postJson.put("guideConfirmTime", tradeThirdOrder.getGuideConfirmTime());
				postJson.put("guideName", orderBean.getGuideName());
				postJson.put("guideAreaCode", orderBean.getGuideAreaCode());
				postJson.put("guideMobile", orderBean.getGuideMobile());
				postJson.put("guideCarName", orderBean.getCarName());
				postJson.put("guideCarDesc", orderBean.getCarDesc());
				String postString = postJson.toJSONString();
				String resString = "";
				JSONObject resObj = new JSONObject() ;
				try {
					resString = httpClientService.sendReqPost(queueJson.getString("url"), postString) ;
					resObj = JSON.parseObject(resString);
				} catch (IOException | JSONException e) {
					log.error(e.getMessage());
					resObj.put("status", 9999);
				}
				int callbackCount = queueJson.getIntValue("callbackCount") + 1 ;
				if (resObj.getIntValue("status") != 200 && callbackCount <= 3) {
					this.addThirdConfirmQueue(tradeThirdOrder.getOrderNo(), queueJson.getString("url"), callbackCount);
				}
			}
		} catch (TradeException e) {
			log.error(e.getMessage());
			redisDao.rpush(this.confirmKey + "Error", queueData);
			throw new TradeException(TradeReturnCodeEnum.TRADE_THIRD_REQUEST_FAIL, e);
		}
	}

	public void addThirdCompleteQueue(String orderNo, String url, String completeTime, int callbackCount) {
		if (url == "") {
			throw new TradeException(TradeReturnCodeEnum.ORDER_PARAM_FAILED, "url参数不能为空");
		}
		TradeThirdOrder tradeThirdOrder = tradeThirdOrderMapper.selectByPrimaryKey(orderNo);
		if (tradeThirdOrder == null) {
			throw new TradeException(TradeReturnCodeEnum.ORDER_NOTEXIST, "订单不存在, orderNo:" + orderNo);
		}
		if (tradeThirdOrder.getOrderStatus() != ThirdOrderStatus.ORDER_COMPLETE.value) {
			throw new TradeException(TradeReturnCodeEnum.ORDER_STATUS_TYPE, "订单状态错误 orderStatus:" + tradeThirdOrder.getOrderStatus() + " " + ThirdOrderStatus.GUIDE_CONFIRM.value );
		}
		try {
			JSONObject jsonObject = new JSONObject() ;
			jsonObject.put("url", url);
			jsonObject.put("orderNo", tradeThirdOrder.getOrderNo());
			jsonObject.put("orderStatus", tradeThirdOrder.getOrderStatus());
			jsonObject.put("completeTime", completeTime);
			jsonObject.put("callbackCount", callbackCount);
			redisDao.lpush(this.completeKey, jsonObject.toJSONString());
		} catch (TradeException e) {
			log.error(e.getMessage());
			throw new TradeException(TradeReturnCodeEnum.TRADE_THIRD_QUEUE_FAIL);
		}
	}
	
	public void execThirdCompleteQueue() {
		String queueData = redisDao.blpop(this.completeKey);
		try {
			if (queueData != null) {
				JSONObject postJson =  new JSONObject() ;
				JSONObject queueJson = JSON.parseObject(queueData);
				TradeThirdOrder tradeThirdOrder = tradeThirdOrderMapper.selectByPrimaryKey(queueJson.getString("orderNo"));
				OrderBean orderBean = orderQueryService.getOrderByNo(tradeThirdOrder.getOrderNo()) ;
				if (tradeThirdOrder == null || orderBean == null) {
					throw new TradeException(TradeReturnCodeEnum.ORDER_NOTEXIST, "订单不存在, orderNo:" + queueJson.getString("orderNo"));
				}
				postJson.put("servicePartner", tradeThirdOrder.getThirdPartner());
				postJson.put("thirdTradeNo", tradeThirdOrder.getThirdTradeNo());
				postJson.put("orderNo", tradeThirdOrder.getOrderNo());
				postJson.put("serviceCompleteTime", queueJson.getString("completeTime"));
				String resString = "";
				JSONObject resObj = new JSONObject() ;
				try {
					resString = httpClientService.sendReqPost(queueJson.getString("url"), postJson.toJSONString()) ;
					resObj = JSON.parseObject(resString);
				} catch (IOException | JSONException e) {
					log.error(e.getMessage());
					resObj.put("status", 9999);
				}
				int callbackCount = queueJson.getIntValue("callbackCount") + 1 ;
				if (resObj.getIntValue("status") != 200 && callbackCount <= 3) {
					this.addThirdCompleteQueue(tradeThirdOrder.getOrderNo(), queueJson.getString("url"), queueJson.getString("completeTime"), callbackCount);
				}
			}
		} catch (TradeException e) {
			log.error(e.getMessage());
			redisDao.rpush(this.completeKey + "Error", queueData);
			throw new TradeException(TradeReturnCodeEnum.TRADE_THIRD_REQUEST_FAIL, e);
		}
		
	}

}
