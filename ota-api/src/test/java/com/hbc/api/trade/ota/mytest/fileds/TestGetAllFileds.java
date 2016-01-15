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
package com.hbc.api.trade.ota.mytest.fileds;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;

/**
 * @author Jongly Ran
 */
public class TestGetAllFileds {
	
	public static void main(String[] args) {
		ParentC common = new ParentC();
		buildQueryString(common);
	}
	
	public static Map<String, String> buildQueryString(Object arg) {
		Map<String, String> params = new HashMap<>() ;
		try {
			doBindParam(arg, params);
			System.out.println("size:" + params.size() +", 参数：" + JSON.toJSONString(params));
			return params;
		} catch ( IllegalArgumentException | SecurityException | IllegalAccessException  e) {
			System.out.println("通过反射获取去哪儿参数失败");
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * @param arg
	 * @param params
	 * @throws IllegalAccessException
	 */
	private static void doBindParam(Object arg, Map<String, String> params) throws IllegalAccessException {
		Class<? extends Object> currentClass = arg.getClass();
		for(; currentClass != Object.class; currentClass = currentClass.getSuperclass()) {
			Field[] fields = currentClass.getDeclaredFields();
			for(Field field : fields) {
				if(field == null)  continue;
				field.setAccessible(true);
				Object value = field.get(arg);
				if(value != null) {
					if(value instanceof String || value instanceof Number || value instanceof Character || value instanceof Boolean) {
						String name = field.getName();
						params.put(name, value.toString());
					} else if(value instanceof SubJSONParam){
						doBindParam(value, params);
					}
				}
			}
		}
	}
}
