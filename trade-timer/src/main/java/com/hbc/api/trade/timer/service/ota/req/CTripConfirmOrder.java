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
package com.hbc.api.trade.timer.service.ota.req;

/**
 * CTrip确认订单
 * @author Jongly Ran
 */
public class CTripConfirmOrder {
	public String 	CtripOrderID ;			//  携程订单编号(采购单) NotNull
	public String	VendorOrderID ;			// 	供应商订单编号 NotNull
	public String	ConnectTel ;			// 	调度电话或司机电话 NotNull
	public String	ConnectTelCode ;		// 	调度电话或司机电话的前缀区号（国家区号）
	public String	ConnectTel2	 ;			//  备用电话
	public String	ConnectTel2Code ;		// 	备用电话的前缀区号（国家区号）
	public String	CarUseGuideCN ;			// 	用车指示信息（中文）
	public String	CarUseGuideEN ;			// 	用车指示信息（英文）
}
