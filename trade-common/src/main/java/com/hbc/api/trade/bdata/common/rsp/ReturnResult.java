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
package com.hbc.api.trade.bdata.common.rsp;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Jongly Ran
 */
public class ReturnResult implements Serializable {

	private static final long serialVersionUID = 7031165881573003049L;

	private Object 	data;
	private String 	message; 		// 状态描述信息
	private Integer status = 200; 	// 响应状态码 默认200代表成功
	
	
	public <T> void  setData(List<T> resultBean, Integer totalSize) {
		Map<String, Object> rmap = new HashMap<String, Object>();
		rmap.put("totalSize", totalSize);
		rmap.put("resultBean", resultBean);
		this.data = rmap;
	}
	
	public void setData(String jkey,Object dvalue) {
		Map<String, Object> rmap = new HashMap<String, Object>();
		rmap.put(jkey, dvalue);
		this.data = rmap;
	}
	/**
	 * @return the data
	 */
	public Object getData() {
		return data;
	}
	/**
	 * @param data the data to set
	 */
	public void setData(Object data) {
		this.data = data;
	}
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	/**
	 * @return the status
	 */
	public Integer getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
}
