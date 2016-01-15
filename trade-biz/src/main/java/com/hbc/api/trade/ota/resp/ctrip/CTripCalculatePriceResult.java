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
package com.hbc.api.trade.ota.resp.ctrip;

import java.util.List;

/**
 * @author Jongly Ran
 */
public class CTripCalculatePriceResult extends CTripResult{
	public String 	PriceMark;
	
	/**
	 * @since v2.0
	 */
	public Integer 	UrgentFlag; // TODO 第二版
	
	/**
	 * @since v2.0
	 */
	public Double	Distance; 	// TODO 第二版
	
	/**
	 * @since v2.0
	 */
	public Integer	EstTime; 	// TODO 第二版
	
	public List<CTripCalculatePriceDetail> QueryResultList;
	
	public CTripCalculatePriceResult() {}
	
	/**
	 * @param msgCodeEnum
	 * @param Message
	 */
	public CTripCalculatePriceResult(MsgCodeEnum msgCodeEnum, String Message) {
		super(msgCodeEnum, Message);
	}
}
