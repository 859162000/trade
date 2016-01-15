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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hbc.api.trade.bdata.common.HttpClientService;
import com.hbc.api.trade.bdata.common.exception.ReturnCode;
import com.hbc.api.trade.bdata.common.rsp.ReturnResult;
import com.hbc.api.trade.order.enums.TradeReturnCodeEnum;
import com.hbc.api.trade.order.enums.third.UserSource;
import com.hbc.api.trade.order.exception.TradeException;

/**
 * @author Jongly Ran
 */
@Service
public class UCenterService {
	protected final static Logger log = LoggerFactory.getLogger(UCenterService.class);
	
	@Autowired private ThirdRESTful			thirdRESTful;
	@Autowired private HttpClientService	httpClientService;

	public static class Entity {
		private String userId;
		private String accountNo;
		/**
		 * @return the userId
		 */
		public String getUserId() {
			return userId;
		}
		/**
		 * @param userId the userId to set
		 */
		private void setUserId(String userId) {
			this.userId = userId;
		}
		/**
		 * @return the accountNo
		 */
		public String getAccountNo() {
			return accountNo;
		}
		/**
		 * @param accountNo the accountNo to set
		 */
		private void setAccountNo(String accountNo) {
			this.accountNo = accountNo;
		}
		
	}
	
	public String obtainOrGenerateUserIdForGDS(String areaCode, String mobile, String agentId, String agentName) {
		return this.obtainOrGenerateUserId(areaCode, mobile, agentId, agentName, UserSource.GDS.getCode());
	}

	public String  obtainOrGenerateUserId(String areaCode, String mobile, String agentId, String agentName, Integer source) {
		return obtainUserFundAccount(areaCode, mobile, agentId, agentName, source).getUserId(); 
	}

	/**
	 * @param areaCode
	 * @param mobile
	 * @param agentId
	 * @param agentName
	 * @param source
	 * @return
	 */
	public Entity obtainUserFundAccount(String areaCode, String mobile, String agentId, String agentName, Integer source) {
		String responseText = null;
		try {
			String requrl = thirdRESTful.TRADE_ORDER_GDS_CREATE_OBTAIN_USER + "?areaCode="+areaCode+"&mobile="+mobile+"&agentId="+agentId+
					"&agentName="+agentName+"&source="+source;
			log.info("通过手机号和区号去查userId，如果未注册则注册后返回，URL：" + requrl);
			responseText = httpClientService.sendReqPost(requrl);
		} catch (Exception e) {
			log.error("ucenter服务错误，无法获取或创建userId。参数：mobile：" + mobile + ";areaCode:"+areaCode, e);
			throw new TradeException(TradeReturnCodeEnum.ORDER_PARAM_FAILED);
		}
		
		log.info("通过RESTful获取userId：" + responseText);
		final ReturnResult result = JSON.parseObject(responseText, ReturnResult.class); // not null
		if(result.getStatus() == 200) {
			JSONObject jsonObject =  (JSONObject) result.getData(); // not null
			Entity entity = new Entity();
			entity.setUserId(jsonObject.getString("userId"));
			entity.setAccountNo(jsonObject.getString("accountNo"));
			return entity;
		}
		throw new TradeException(new ReturnCode() {
			@Override public String getMessage() { return result.getMessage(); }
			@Override public int getCode() { return result.getStatus(); }
		});
	}
}
