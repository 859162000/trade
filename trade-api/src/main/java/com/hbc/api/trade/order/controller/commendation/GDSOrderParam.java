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
package com.hbc.api.trade.order.controller.commendation;

/**
 * GDS下单参数
 * @author Jongly Ran
 */
public class GDSOrderParam extends CommendationOrderParam {
    private String agentId;				// gds代理商ID
    private String agentName;			// gds 登录用户代理商名称
    private String agentOpid;			// gds操作员ID
    private String agentOpname;			// gds 操作员名称

	/**
     * 仅保留CAPP下单参数
     */
    public void clearGDSParam() {
    	clearCommendationParam();
    	agentId = null;
    	agentName = null;
    	agentOpid = null;
    	agentOpname = null;
    }
    
	/**
	 * @return the agentId
	 */
	public String getAgentId() {
		return agentId;
	}
	/**
	 * @param agentId the agentId to set
	 */
	public void setAgentId(String agentId) {
		this.agentId = agentId;
	}
	/**
	 * @return the agentName
	 */
	public String getAgentName() {
		return agentName;
	}
	/**
	 * @param agentName the agentName to set
	 */
	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}
	/**
	 * @return the agentOpid
	 */
	public String getAgentOpid() {
		return agentOpid;
	}
	/**
	 * @param agentOpid the agentOpid to set
	 */
	public void setAgentOpid(String agentOpid) {
		this.agentOpid = agentOpid;
	}
	/**
	 * @return the agentOpname
	 */
	public String getAgentOpname() {
		return agentOpname;
	}
	/**
	 * @param agentOpname the agentOpname to set
	 */
	public void setAgentOpname(String agentOpname) {
		this.agentOpname = agentOpname;
	}
    
}
