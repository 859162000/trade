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
 * CTrip司机应答，推送司机信息
 * @author Jongly Ran
 */
public class CTripPushDriverInfo {
	public String CtripOrderID ;			//  携程订单编号(采购单) NotNull
	public String VendorOrderID ;			// 	供应商订单编号 NotNull
	public String DriverID; 				//	最长25位	司机编号（唯一）
	public String DriverName; 				//	司机姓名(司机姓名,司机手机, 司机车牌号码其中一个必须)
	public String AreaCode; 				//	国家区号
	public String DriverPhone; 				//	司机手机(司机姓名,司机手机, 司机车牌号码其中一个必须)
	public String VehicleNumber; 			//	最长20位	司机车牌号码(司机姓名,司机手机, 司机车牌号码其中一个必须)
	public String VehicleTypeBrand; 		//	最长30位	具体车辆品牌，例如：奔驰C200
	public String Remark; 					//	车辆备注
	
}
