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
package com.hbc.api.trade.bdata.common.sign;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;

/**
 * @author Jongly Ran
 */
public class SignValidator {
	
	private final static Logger logger = LoggerFactory.getLogger(SignValidator.class);
	
	/**
	 * @param arg
	 * @return 第一个值：String sign ；第二个值：Map<String, String> params
	 */
	public static Map<String, String> buildQueryString(Object arg) {
		return buildQueryString(arg, false);
	}
			
	/**
	 * @param arg
	 * @param isFormatNumber 如果遇到浮点类型末尾是0则去掉
	 * @return 第一个值：String sign ；第二个值：Map<String, String> params
	 */
	public static Map<String, String> buildQueryString(Object arg, boolean isFormatNumber) {
		Map<String, String> params = new HashMap<>() ;
		try {
			doBindParam(arg, params, isFormatNumber);
			logger.info("请求参数列表[requestBody] " + JSON.toJSONString(params));
			return params;
		} catch ( IllegalArgumentException | SecurityException | IllegalAccessException  e) {
			logger.error("通过反射获取去哪儿参数失败", e);
			return null;
		}
	}

	/**
	 * @param arg
	 * @param params
	 * @param isFormatNumber 如果遇到浮点类型末尾是0则去掉
	 * @throws IllegalAccessException
	 */
	private static void doBindParam(Object arg, Map<String, String> params, boolean isFormatNumber) throws IllegalAccessException {
		Class<? extends Object> currentClass = arg.getClass();
		for(; currentClass != Object.class; currentClass = currentClass.getSuperclass()) {
			Field[] fields = currentClass.getDeclaredFields();
			for(Field field : fields) {
				if(field == null)  continue;
				field.setAccessible(true);
				Object value = field.get(arg);
				if(value != null) {
					if(value instanceof String || value instanceof Character || value instanceof Boolean) {
						String name = field.getName();
						params.put(name, value.toString());
					} else if(value instanceof Number) {
						String name = field.getName();
						if(isFormatNumber && (value instanceof Double || value instanceof Float)) {
							String s = value.toString();
							if(s.indexOf(".") > 0){
						          s = s.replaceAll("0+?$", "");//去掉后面无用的零
						          s = s.replaceAll("[.]$", "");//如小数点后面全是零则去掉小数点
						     }
							params.put(name, s);
						} else {
							params.put(name, value.toString());
						}
					} else if(value instanceof SubJSONParam){
						doBindParam(value, params, isFormatNumber);
					}
				}
			}
		}
	}
	
	/**
	 * 
	 * @param request
	 * @return 第一个值：Map<String, String> params；第二个值：String sign ；
	 */
	public static Object[] buildQueryString(HttpServletRequest request) {
        Map<String, String> params = new HashMap<>() ;
		StringBuilder queryString = new StringBuilder();
        String sign = "";
		Set<String> keySet = request.getParameterMap().keySet();
        boolean next = true;
        for (String k : keySet) {
            String[] values = request.getParameterValues(k);
            if(values != null && values.length == 1) {
            	params.put(k, values[0]) ;
				queryString.append("&").append(k).append("=").append(values[0]);
            }
            
            if(next && k.equals("sign")) {
            	sign = values[0];
            	next = false;
            }
        }

		logger.info("请求参数列表[queryString] " + queryString);
		logger.info("请求参数列表[requestBody] " + JSON.toJSONString(params));
        Object[] results = new Object[2];
        results[0] = params;
        results[1] = sign;
        return results;
	}
}
