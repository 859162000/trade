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

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.ConvertUtilsBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.hbc.api.trade.bdata.common.exception.ParamValidateException;
import com.hbc.api.trade.bdata.common.exception.enums.CommonReturnCodeEnum;

/**
 * @author Jongly Ran
 */
public class BeanUtilsEnhance {
	private final static Logger log = LoggerFactory.getLogger(BeanUtilsEnhance.class);
	
	/**
	 * 对象属性拷贝，把orig里面的属性的值复制到dest对应的属性里。<p/>
	 * 支持类型转换，因为数据库短数字类型对应到Java的byte, short等，如果dest对象使用了integer及更大类型时，如果不强制转换会报错。
	 * @param dest
	 * @param orig
	 */
	public static void copyProperties(Object dest, Object orig) {
		ConvertUtilsBean convertUtil = new ConvertUtilsBean();
		convertUtil.register(false, true, 10);
		BeanUtilsBean beanUtils = new BeanUtilsBean(convertUtil);
		try {
			beanUtils.copyProperties(dest, orig);
		} catch (IllegalAccessException | InvocationTargetException e) {
			log.error("对象属性复制错误，from:" + JSON.toJSONString(orig) + ", to: " + JSON.toJSONString(dest), e);
			throw new ParamValidateException(CommonReturnCodeEnum.PARAM_ERROR);
		}
	}
}
