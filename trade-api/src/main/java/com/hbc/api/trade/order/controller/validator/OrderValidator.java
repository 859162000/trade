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
package com.hbc.api.trade.order.controller.validator;

import org.apache.commons.lang3.StringUtils;

import com.hbc.api.trade.bdata.common.util.ParameterValidator;
import com.hbc.api.trade.order.enums.TradeReturnCodeEnum;
import com.hbc.api.trade.order.exception.TradeException;

/**
 * @author Jongly Ran
 */
public class OrderValidator extends ParameterValidator {
	
	public static void validateOrderNo(String orderNo) {
		if(StringUtils.isBlank(orderNo)) {
			logger.error("orderNo值为 null或含换行符等，此时orderNo=" + orderNo);
			throw new TradeException(TradeReturnCodeEnum.ORDER_PARAM_FAILED, "orderNo");
		}
	}
	
	public static void validateGuideId(String guideId) {
		if(StringUtils.isBlank(guideId)) {
			logger.error("guideId值为 null或含换行符等，此时guideId=" + guideId);
			throw new TradeException(TradeReturnCodeEnum.ORDER_PARAM_FAILED, "guideId");
		}
	}
	
	public static void validateDeliverGuide(String 	allocateGid, String orderNo, String guideId) {
		validateGuideId(guideId);
		validateOrderNo(orderNo);
		if(StringUtils.isBlank(allocateGid)) {
			logger.error("allocateGid(对应数据库：allocat_gno)值为 null或含换行符等，此时allocateGid=" + allocateGid);
			throw new TradeException(TradeReturnCodeEnum.ORDER_PARAM_FAILED, "allocateGid");
		}
	}
	
	public static void validateDeliverGuide(String 	allocateGid, String orderNo, String refuseReason, String guideId) {
		validateDeliverGuide(allocateGid, orderNo, guideId);
		if(StringUtils.isEmpty(refuseReason)) {
			logger.error("refuseReason值为 null，此时refuseReason=" + refuseReason);
			throw new TradeException(TradeReturnCodeEnum.ORDER_PARAM_FAILED, "refuseReason");
		}
	}
}
