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
package com.hbc.api.trade.timer.service.ota.resp;

/**
 * Success: {"data":"2015-07-10 11:41:43","bstatus":{"code":0,"code":""}}
 * Failed: {"bstatus":{"code":1018,"des":"签名错误拒绝服务"}}
 * @author Jongly Ran
 */
public class QunarResult {
	private String data;
	private QunarResultEntity bstatus;
	
	/**
	 * @return the data
	 */
	public String getData() {
		return data;
	}
	/**
	 * @param data the data to set
	 */
	public void setData(String data) {
		this.data = data;
	}
	/**
	 * @return the bstatus
	 */
	public QunarResultEntity getBstatus() {
		return bstatus;
	}
	/**
	 * @param bstatus the bstatus to set
	 */
	public void setBstatus(QunarResultEntity bstatus) {
		this.bstatus = bstatus;
	}

}
