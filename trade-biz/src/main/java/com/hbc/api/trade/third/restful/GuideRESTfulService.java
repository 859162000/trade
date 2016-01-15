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
package com.hbc.api.trade.third.restful;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hbc.api.trade.bdata.common.HttpClientService;
import com.hbc.api.trade.bdata.common.rsp.ReturnResult;
import com.hbc.api.trade.order.enums.TradeReturnCodeEnum;
import com.hbc.api.trade.order.enums.order.OrderType;
import com.hbc.api.trade.order.enums.order.UserCommentStatus;
import com.hbc.api.trade.order.exception.TradeException;
import com.hbc.api.trade.order.mapping.genx.xbean.AppraiseGuideParam;
import com.hbc.api.trade.order.service.OrderTrackService;
import com.hbc.api.trade.third.vo.Appraisement;

/**
 * @author Jongly Ran
 */
@Service
public class GuideRESTfulService {
	private final static Logger logger = LoggerFactory.getLogger(GuideRESTfulService.class);
	
	@Autowired private ThirdRESTful			thirdRESTful;
	@Autowired private HttpClientService 	httpClientService;
	@Autowired private OrderTrackService 	orderTrackService;
	
	/**
	 * 获取评价内容
	 * @param orderBean
	 * @param target
	 */
	public Appraisement obtainAppraiseContent(String orderNo, Integer userCommentStatus) {
		if(UserCommentStatus.UNSCORED.value.equals(userCommentStatus)) return null;
		
		try {
			String responseText = httpClientService.sendReq(thirdRESTful.TRADE_ORDER_APPRAISE_GUIDE_HISTORY+"?orderNo="+ orderNo);
			logger.info("获取评价导游信息：" + responseText);
			ReturnResult result = JSON.parseObject(responseText, ReturnResult.class); // not null
			if(result.getStatus() == 200) {
				JSONObject json = (JSONObject) result.getData(); // not null
				Appraisement appraisement = new Appraisement();
				String content = json.getString("content");
				appraisement.setContent(content != null && content.replaceAll("null", "").trim().length() > 0 ? content : null);
				appraisement.setSceneryNarrate(json.getDouble("sceneryNarrate"));
				appraisement.setServiceAttitude(json.getDouble("serviceAttitude"));
				appraisement.setRouteFamiliar(json.getDouble("routeFamiliar"));
				appraisement.setTotalScore(json.getDouble("totalScore"));
				return appraisement;
			} else {
				logger.error("获取导游评价异常，为了不影响主流程，仅log下来. Error message:" + result.getMessage() + ", status:" + result.getStatus());
			}
		} catch (Exception e) {
			logger.error("获取导游评价异常，为了不影响主流程，仅log下来", e);
		}
		return null;
	}
	
	/**
	 * 获取评价内容
	 * @param orderBean
	 * @param target
	 */
	public List<Appraisement> obtainAppraisementList(List<String> orderNoList) {
		if(orderNoList == null || orderNoList.isEmpty()) return null;
		StringBuilder orderNos = new StringBuilder();
		for(String orderNo : orderNoList) {
			orderNos.append(",").append(orderNo);
		}
		try {
			String responseText = httpClientService.sendReq(thirdRESTful.TRADE_ORDER_APPRAISE_USER_COMMENT_LIST+"?orderNos="+ orderNos.substring(1));
			logger.info("获取评价导游信息：" + responseText);
			ReturnResult result = JSON.parseObject(responseText, ReturnResult.class); // not null
			if(result.getStatus() == 200) {
				JSONArray datas = (JSONArray) result.getData(); // not null
				if(datas != null && datas.size() > 0) {
					List<Appraisement> dataResult = new LinkedList<>();
					for(int i = 0, j = datas.size(); i < j; i++) {
						JSONObject jsonObject = (JSONObject) datas.get(i);
						Appraisement bean = new Appraisement();
						bean.setOrderNo(jsonObject.getString("orderNo"));
						bean.setUserCommentTime(jsonObject.getString("userCommentTime"));
						dataResult.add(bean);
					}
					return dataResult;
				}
			} else {
				logger.error("获取导游评价异常，为了不影响主流程，仅log下来. Error message:" + result.getMessage() + ", status:" + result.getStatus());
			}
		} catch (Exception e) {
			logger.error("获取导游评价异常，为了不影响主流程，仅log下来", e);
		}
		return null;
	}
	
	/**
	 * 保持评价
	 * 
	 * @param param
	 * @param orderType
	 * @param userId
	 * @param userName
	 * @param guideId
	 * @param guideName 
	 */
	public void saveAppraiseContent(AppraiseGuideParam param, Integer orderType) {
		Map<String,String> reqmap = new HashMap<String,String>();
		reqmap.put("orderNo", param.getOrderNo());
		reqmap.put("orderType", OrderType.getType(orderType).value+"");
		reqmap.put("content", param.getContent());
		reqmap.put("sceneryNarrate", param.getSceneryNarrate()+"");
        reqmap.put("serviceAttitude", param.getServiceAttitude()+"");
        reqmap.put("routeFamiliar", param.getRouteFamiliar()+"");
		reqmap.put("fromUid", param.getFromUid());
		reqmap.put("fromUname", param.getFromUname());
		reqmap.put("guideId", param.getGuideId());
		reqmap.put("guideName", param.getGuideName());
		reqmap.put("commentType", param.getCommentType()+"");
		
		ReturnResult returnResult = null;
		try {
			String rspMsg = httpClientService.sendPostMapReq(thirdRESTful.TRADE_ORDER_APPRAISE_GUIDE, reqmap);
			logger.info(rspMsg);
			returnResult = JSON.parseObject(rspMsg, ReturnResult.class);
		} catch (Exception e) {
			logger.error("评价导游失败，参数：" + JSON.toJSONString(param), e);
			throw new TradeException(TradeReturnCodeEnum.FAILED_FOR_INSERT, "评价导游");
		}
		
		if(returnResult.getStatus() != 200) { // returnResult不可能是null，否则RuntimeException
			logger.error("评价导游失败，导游端返回："  + JSON.toJSONString(returnResult) + "\n参数：" + JSON.toJSONString(param) );
			throw new TradeException(TradeReturnCodeEnum.FAILED_FOR_INSERT, returnResult.getMessage());
		}
		orderTrackService.appraisedByCustomer( param.getOrderNo());
	}
}
