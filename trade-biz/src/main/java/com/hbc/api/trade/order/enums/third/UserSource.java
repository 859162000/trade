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
package com.hbc.api.trade.order.enums.third;

/**
 * 来源。1：andriod，2：IOS；3：微信；4：PC；5：GDS；6：去哪儿API；7：携程API；8：去啊API；9：其他;10:MIS
 * <p>DB Table: user_info</p>
 * @author Jongly Ran
 */
public enum UserSource {
	/** 1：andriod. */
	ANDRIOD(1, "andriod"),
	
	/** 2：IOS. */
	IOS(2, "IOS"),
	
	/** 3：微信. */
	WX(3, "微信"),
	
	/** 4：PC. */
	PC(4, "PC"),
	
	/** 5：GDS. */
	GDS(5, "GDS"),

	QUNAR(1948212164, "QUNA渠道"),
	
	CTRIP(1918029805, "携程渠道"),
	
	QUA(1909280330, "去啊渠道"),
	
//	QUNAR(17, "QUNA渠道"),
//	
//	CTRIP(20, "携程渠道"),
//	
//	QUA(19, "去啊渠道"),
	
	/** 9：其他 */
	OTHER(9, "其他"),
	
	/** 10：MIS */
	MIS(10, "MIS"),
	;
	
	/** The value. */
	private final Integer code;

	/** The desc. */
	private final String message;/**
	
	 * Instantiates a new return status enum.
	 * 
	 * @param value
	 *            the value
	 * @param desc
	 *            the desc
	 */
	private UserSource(Integer code, String message) {
		this.code = code;
		this.message = message;
	}

	/**
	 * Gets the value.
	 * 
	 * @return the value
	 */
	public Integer getCode() {
		return code;
	}

	/**
	 * Gets the desc.
	 * 
	 * @return the desc
	 */
	public String getMessage() {
		return message;
	}
}
