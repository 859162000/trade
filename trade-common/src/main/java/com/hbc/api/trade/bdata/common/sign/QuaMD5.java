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
package com.hbc.api.trade.bdata.common.sign;

/**
 * @author Jongly Ran
 */
public class QuaMD5 implements MD5Adaptor{
	private static final QuaMD5 instance = new QuaMD5();
	
	private QuaMD5(){}
	
	public static QuaMD5 getInstance() {
		return instance;
	}
	
	/* (non-Javadoc)
	 * @see com.hbc.api.trade.bdata.common.sign.MD5Adaptor#toMD5(java.lang.String)
	 */
	@Override
	public String toMD5(String originString) {
		return StandardMD5.toMD5(originString);
	}
}
