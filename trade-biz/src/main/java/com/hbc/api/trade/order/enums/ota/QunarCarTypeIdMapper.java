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
package com.hbc.api.trade.order.enums.ota;

/**
 * @author Jongly Ran
 */
public enum QunarCarTypeIdMapper {
	
	xx(1,2,2),
	;
	
	public Integer thirdCarTypeId;
	public Integer carTypeId;
	public Integer carSeatNum;

	QunarCarTypeIdMapper(Integer thirdCarTypeId, Integer carTypeId, Integer carSeatNum) {
		this.thirdCarTypeId = thirdCarTypeId;
		this.carTypeId = carTypeId;
		this.carSeatNum = carSeatNum;
	}
}
