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
package com.hbc.api.trade.bdata.common.util;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hbc.api.trade.bdata.common.exception.ParamValidateException;
import com.hbc.api.trade.bdata.common.exception.enums.CommonReturnCodeEnum;

/**
 * @author Jongly Ran
 */
public class ParameterValidator {
	protected final static Logger logger = LoggerFactory.getLogger(ParameterValidator.class);
	
	public static void validateLimitAndOffset(Integer limit, Integer offset) {
		if(limit == null || limit <= 0) {
			logger.error("参数limit必须大于0，此时为" + limit);
			throw new ParamValidateException(CommonReturnCodeEnum.PARAM_ERROR_WITHARG, "limit");
		}
		if(offset == null || offset < 0) {
			logger.error("参数offset必须不小于0，此时为" + limit);
			throw new ParamValidateException(CommonReturnCodeEnum.PARAM_ERROR_WITHARG, "offset");
		}
	}
	
	/**
	 * @param paramValue 需要校验的参数
	 * @param paramNameTip 对应参数名的对应国籍的翻译
	 */
	public static void validateParamString(String paramValue, String paramNameTip) {
		if(StringUtils.isBlank(paramValue)) {
			throw new ParamValidateException(CommonReturnCodeEnum.PARAM_ERROR_WITHARG, paramNameTip);
		}
	}
	
	/**
	 * @param paramValue 需要校验的参数
	 * @param paramNameTip 对应参数名的对应国籍的翻译
	 */
	public static void validateParamNumber(Number paramValue, String paramNameTip) {
		if(paramValue == null) {
			throw new ParamValidateException(CommonReturnCodeEnum.PARAM_ERROR_WITHARG, paramNameTip);
		}
	}
	
	/**
	 * @param paramValue 需要校验的参数
	 * @param paramNameTip 对应参数名的对应国籍的翻译
	 */
	public static void validateParamNumberGreaterThan0(Double paramValue, String paramNameTip) {
		if (paramValue == null || Double.compare(paramValue.doubleValue(), 0) <= 0) {
			throw new ParamValidateException(CommonReturnCodeEnum.PARAM_ERROR_WITHARG, paramNameTip);
		}
	}
	
	/**
	 * @param paramValue 需要校验的参数
	 * @param paramNameTip 对应参数名的对应国籍的翻译
	 */
	public static void validateParamNumberGreaterThan0(Short paramValue, String paramNameTip) {
		if(paramValue == null || paramValue.shortValue() < 0) {
			throw new ParamValidateException(CommonReturnCodeEnum.PARAM_ERROR_WITHARG, paramNameTip);
		}
	}
	
	/**
	 * @param paramValue 需要校验的参数
	 * @param paramNameTip 对应参数名的对应国籍的翻译
	 */
	public static void validateParamNumberGreaterThan0(Integer paramValue, String paramNameTip) {
		if(paramValue != null && paramValue.intValue() < 0) {
			throw new ParamValidateException(CommonReturnCodeEnum.PARAM_ERROR_WITHARG, paramNameTip);
		}
	}
	
	/**
	 * @param paramValue 需要校验的参数
	 * @param paramNameTip 对应参数名的对应国籍的翻译
	 */
	public static void validateParamNumberGreaterThan0(Byte paramValue, String paramNameTip) {
		if(paramValue == null || paramValue.byteValue() < 0) {
			throw new ParamValidateException(CommonReturnCodeEnum.PARAM_ERROR_WITHARG, paramNameTip);
		}
	}
	
	/**
	 * @param paramValue 需要校验的参数
	 * @param paramNameTip 对应参数名的对应国籍的翻译
	 */
	public static void validateParamDate(Date paramValue, String paramNameTip) {
		if(paramValue == null) {
			throw new ParamValidateException(CommonReturnCodeEnum.PARAM_ERROR_WITHARG, paramNameTip);
		}
	}
}
