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
 * @author Jongly Ran
 */
public class CTripResult{
	public static enum MsgCodeEnum { OK, ERROR, ERROR_PRICEMARK }
	
	/**
	 * OK:成功
	 * ERROR_PRICEMARK:价格标示符号已经使用，不可以重复使用。
	 * ERROR:其他错误（正常情况下，不应该出现。
	 */
	public String MsgCode = MsgCodeEnum.OK.toString();	
	
	public String Message = "成功";	
	
	public CTripResult(){}

	public CTripResult(MsgCodeEnum msgCodeEnum, String Message) {
		this.MsgCode = msgCodeEnum.toString();
		this.Message = Message;
	}
}
