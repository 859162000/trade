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
import com.hbc.api.trade.bdata.common.rsp.ReturnResult;

/**
 * @author Jongly Ran
 */
@Service
public class PriceRESTfulService {
	private final static Logger log = LoggerFactory.getLogger(PriceRESTfulService.class);

	@Autowired private HttpClientService httpClientService;
	@Autowired private ThirdRESTful thirdRESTful;

	public JSONObject getPaymentDiscount(String orderNo) {

		String requrl = thirdRESTful.TRADE_ORDER_PAYMENT_DISCOUNT + "?orderNo=" + orderNo;
		log.info("查支付明细中包含的所有折扣情况，URL：" + requrl );
		try {
			String responseText = httpClientService.sendReq(requrl);
			log.info(responseText);
			ReturnResult result = JSON.parseObject(responseText, ReturnResult.class); // not null
			if(result.getStatus() == 200) {
				return (JSONObject) result.getData(); // not null
			} 
			log.error("获取["+orderNo+"]IMToken失败，为了不影响主流程，容错。result: " + JSON.toJSONString(result));
		} catch (Exception e) {
			log.error("查支付明细中包含的所有折扣情况遇到异常", e);
		}
		return null;
	}
}
