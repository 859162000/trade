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

/**
 * FastJSON会把首字母变小写，指定JSON注解不生效，先用public类型
 * @author Jongly Ran
 */
public class CTripCreateOrderResult extends CTripResult{
	
	public String VendorOrderId; // 供应商订单编号
	
	public CTripCreateOrderResult(){}
	
	public CTripCreateOrderResult(String VendorOrderId) {
		this.VendorOrderId = VendorOrderId;
	}
	
	/**
	 * @param msgCodeEnum
	 * @param Message
	 */
	public CTripCreateOrderResult(MsgCodeEnum msgCodeEnum, String Message) {
		super(msgCodeEnum, Message);
	}
}
