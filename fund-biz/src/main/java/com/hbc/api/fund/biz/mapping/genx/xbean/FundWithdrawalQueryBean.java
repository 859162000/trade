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

import java.util.List;

/**
 * @author Jongly Ran
 */
public class FundWithdrawalQueryBean extends AbstractParameter{
	private String guideId;
	private Integer drawStatus;
	private List<Integer> drawStatusList;
	private String	finAccount;
	
	/**
	 * @return the finAccount
	 */
	public String getFinAccount() {
		return finAccount;
	}
	/**
	 * @param finAccount the finAccount to set
	 */
	public void setFinAccount(String finAccount) {
		this.finAccount = finAccount;
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
	/**
	 * @return the drawStatus
	 */
	public Integer getDrawStatus() {
		return drawStatus;
	}
	/**
	 * @param drawStatus the drawStatus to set
	 */
	public void setDrawStatus(Integer drawStatus) {
		this.drawStatus = drawStatus;
	}
	/**
	 * @return the drawStatusList
	 */
	public List<Integer> getDrawStatusList() {
		return drawStatusList;
	}
	/**
	 * @param drawStatusList the drawStatusList to set
	 */
	public void setDrawStatusList(List<Integer> drawStatusList) {
		this.drawStatusList = drawStatusList;
	}
}
