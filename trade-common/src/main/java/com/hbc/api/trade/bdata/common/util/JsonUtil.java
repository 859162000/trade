package com.hbc.api.trade.bdata.common.util;

import java.util.Set;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;

public class JsonUtil {

	/**
	 * 包含/排除指定对象中的字段并转换成JSON字符串
	 * 
	 * @param obj
	 *           要转换的对象
	 * @param clz
	 *           需要过滤的类
	 * @param includeFields
	 *           包含字段
	 * @param excludeFields
	 * 			  排除字段
	 * @param features
	 * 			序列号特性
	 * 
	 * @return
	 */
	public static String toJSONString(Object obj, Class<?> clz, final String[] includeFields,
			final String[] excludeFields, SerializerFeature... features) {

		SimplePropertyPreFilter propertyFilter = new SimplePropertyPreFilter(clz, new String[] {});

		if (includeFields != null && includeFields.length > 0) {
			Set<String> includes = propertyFilter.getIncludes();
			for (String property : includeFields) {
				includes.add(property);
			}
		}
		if (excludeFields != null && excludeFields.length > 0) {
			Set<String> excludes = propertyFilter.getExcludes();
			for (String property : excludeFields) {
				excludes.add(property);
			}
		}

		return JSON.toJSONString(obj, propertyFilter, features);
	}

}
