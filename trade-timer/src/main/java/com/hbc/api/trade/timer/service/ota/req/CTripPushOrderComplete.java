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
 * 订单完成推送消息给CTrip
 * @author Jongly Ran
 */
public class CTripPushOrderComplete {
	public String CtripOrderID ;			//  携程订单编号(采购单) NotNull
	public String VendorOrderID ;			// 	供应商订单编号 NotNull
	public String DriverID; 				//	最长25位	司机编号（唯一）
	public String DistanceLength; 			//	单位米	实际行程总公里数
	public Integer TimeLength; 			//	单位秒	实际使用时长
}
