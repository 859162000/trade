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
 * 下面的常量表示仅用于CTrip
 * @author Jongly Ran
 */
public class CTripCalculatePriceDetail {
	public Integer VehicleType; 			// 车型ID
	public Boolean IsSupportPickup;			// 是否提供接机牌服务
	
	/** 0.0 */
	public Double  PickupFee = 0.0;			// 接机牌费用

	public Boolean IsSupportChildSeat;		// 是否提供儿童座椅服务
	
	/** 0.0 */
	public Double  ChildSeatFee	 = 0.0;		// 儿童座椅费用
	public Boolean IsMustChildSeat = false;	// 有婴儿儿童的时候，儿童座椅是否必须使用
	public Double  Price;					// 价格（按人数分）
}
