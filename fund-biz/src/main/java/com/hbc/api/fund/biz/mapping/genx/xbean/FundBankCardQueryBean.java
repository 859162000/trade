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
package com.hbc.api.fund.biz.mapping.genx.xbean;

/**
 * @author Jongly Ran
 */
public class FundBankCardQueryBean extends AbstractParameter{
	private String guideId;
	
	/*  地接社使用除上述参数外的参数*/
	private String guideAgencyId;

	/**
	 * @return the guideAgencyId
	 */
	public String getGuideAgencyId() {
		return guideAgencyId;
	}

	/**
	 * @param guideAgencyId the guideAgencyId to set
	 */
	public void setGuideAgencyId(String guideAgencyId) {
		this.guideAgencyId = guideAgencyId;
	}

	/**
	 * @return the guideId
	 */
	public String getGuideId() {
		return guideId;
	}

	/**
	 * @param guideId the guideId to set
	 */
	public void setGuideId(String guideId) {
		this.guideId = guideId;
	}
}
