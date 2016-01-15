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
package com.hbc.api.trade.order.service.rsp;

/**
 * @author Jongly Ran
 */
public class ChildSeat {
	private String seatSum;		// 儿童座椅数
	private String seatType;	// 儿童座椅类型。1:婴儿座椅(0-1岁) 2:幼儿座椅(1-4岁) 3:学童座椅(4-7岁) 4:儿童座椅(7-12岁)
	
	/**
	 * @return 儿童座椅数
	 */
	public String getSeatSum() {
		return seatSum;
	}
	/**
	 * @param seatSum 儿童座椅数
	 */
	public void setSeatSum(String seatSum) {
		this.seatSum = seatSum;
	}
	/**
	 * @return 儿童座椅类型。1:婴儿座椅(0-1岁) 2:幼儿座椅(1-4岁) 3:学童座椅(4-7岁) 4:儿童座椅(7-12岁)
	 */
	public String getSeatType() {
		return seatType;
	}
	/**
	 * @param seatType 儿童座椅类型。1:婴儿座椅(0-1岁) 2:幼儿座椅(1-4岁) 3:学童座椅(4-7岁) 4:儿童座椅(7-12岁)
	 */
	public void setSeatType(String seatType) {
		this.seatType = seatType;
	}
}
