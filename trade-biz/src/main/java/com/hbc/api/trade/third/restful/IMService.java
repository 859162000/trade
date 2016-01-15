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

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hbc.api.trade.bdata.common.HttpClientService;
import com.hbc.api.trade.bdata.common.rsp.ReturnResult;

/**
 * 订单及时聊天数量统计
 * 
 * @author Jongly Ran
 */
@Service
public class IMService {
	private final static Logger logger = LoggerFactory.getLogger(IMService.class);

	@Autowired
	private HttpClientService httpClientService;
	@Autowired
	private ThirdRESTful thirdRESTful;

	public String obtainIMToken(String orderNo) {
		try {
			Assert.notNull(orderNo, "订单编号不能为空！");
			String responseText = httpClientService.sendReq(thirdRESTful.TRADE_ORDER_COMMUNICATION_IMTOKEN + "?apply_type=2&apply_id=" + orderNo);
			logger.info("获取订单[" + orderNo + "]评价导游信息：" + responseText);
			ReturnResult result = JSON.parseObject(responseText, ReturnResult.class); // not null
			if (result != null && result.getStatus() == 200) {
				JSONObject json = (JSONObject) result.getData(); // not null
				return json.getString("token");
			} else {
				logger.error("获取[" + orderNo + "]IMToken失败，为了不影响主流程，容错。result: " + JSON.toJSONString(result));
			}
		} catch (Exception e) {
			logger.error("获取[" + orderNo + "]IMToken失败，为了不影响主流程，容错。", e);
			handException(e);
		}
		return null;
	}

	public ReturnResult imCustomerUnreadTotalSize(String userId) {
		if (StringUtils.isBlank(userId))
			return new ReturnResult();
		try {
			String responseText = httpClientService.sendReqPost(thirdRESTful.TRADE_ORDER_OBTAIN_IM_STATISTICS_CUSTOMER + "?userId=" + userId);
			ReturnResult result = JSON.parseObject(responseText, ReturnResult.class);
			int status = result.getStatus();
			if (status == 200) {
				return result;
			}
			logger.error("通过RESTful获取是否能聊天及其数量时返回状态为" + status);
		} catch (IOException e) {
			logger.error("通过RESTful获取是否能聊天及其数量时失败", e);
		}
		return new ReturnResult();
	}

	public ReturnResult imGuideUnreadTotalSize(String guideId) {
		if (StringUtils.isBlank(guideId) || guideId.equals("0"))
			return new ReturnResult();
		try {
			String responseText = httpClientService.sendReqPost(thirdRESTful.TRADE_ORDER_OBTAIN_IM_STATISTICS_GUIDE + "?guideId=" + guideId);
			ReturnResult result = JSON.parseObject(responseText, ReturnResult.class);
			int status = result.getStatus();
			if (status == 200) {
				return result;
			}
			logger.error("通过RESTful获取是否能聊天及其数量时返回状态为" + status);
		} catch (IOException e) {
			logger.error("通过RESTful获取是否能聊天及其数量时失败", e);
		}
		return new ReturnResult();
	}

	public ReturnResult imCUnreadSizeList(String userId) {
		if (StringUtils.isBlank(userId))
			return new ReturnResult();
		try {
			String responseText = httpClientService.sendReqPost(thirdRESTful.TRADE_ORDER_OBTAIN_IM_STATISTICS_CUNREADLIST + "?userId=" + userId);
			ReturnResult result = JSON.parseObject(responseText, ReturnResult.class);
			int status = result.getStatus();
			if (status == 200) {
				return result;
			}
			logger.error("通过RESTful获取是否能聊天及其数量时返回状态为" + status);
		} catch (IOException e) {
			logger.error("通过RESTful获取是否能聊天及其数量时失败", e);
		}
		return new ReturnResult();
	}

	public ReturnResult imGUnreadSizeList(String guideId) {
		if (StringUtils.isBlank(guideId) || guideId.equals("0"))
			return new ReturnResult();
		try {
			String responseText = httpClientService.sendReqPost(thirdRESTful.TRADE_ORDER_OBTAIN_IM_STATISTICS_GUNREADLIST + "?guideId=" + guideId);
			logger.info("IMService responseText:" + responseText);
			ReturnResult result = JSON.parseObject(responseText, ReturnResult.class);
			int status = result.getStatus();
			if (status == 200) {
				return result;
			}
			logger.error("通过RESTful获取是否能聊天及其数量时返回状态为" + status);
		} catch (IOException e) {
			logger.error("通过RESTful获取是否能聊天及其数量时失败", e);
		}
		return new ReturnResult();
	}

	class IMBean {
		private String name;
		private String value;

		/**
		 * @return the name
		 */
		public String getName() {
			return name;
		}

		/**
		 * @param name
		 *            the name to set
		 */
		public void setName(String name) {
			this.name = name;
		}

		/**
		 * @return the value
		 */
		public String getValue() {
			return value;
		}

		/**
		 * @param value
		 *            the value to set
		 */
		public void setValue(String value) {
			this.value = value;
		}
	}

	private void handException(Throwable t) {
		if (t instanceof java.lang.RuntimeException) {
			throw (java.lang.RuntimeException) t;
		} else {
			throw new RuntimeException(t);
		}
	}

}
