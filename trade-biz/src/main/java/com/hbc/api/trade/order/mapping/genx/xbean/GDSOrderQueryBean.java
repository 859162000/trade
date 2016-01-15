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
package com.hbc.api.trade.order.mapping.genx.xbean;

/**
 * @author Jongly Ran
 */
public class GDSOrderQueryBean extends GOrderQueryBean {

	private String agentId;
	private String agentOpname;
	private String serviceCityName;
	private String userName;
	private Integer orderType; 
	private String agentOpid;
	
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
	 * @return the orderType
	 */
	public Integer getOrderType() {
		return orderType;
	}
	/**
	 * @param orderType the orderType to set
	 */
	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}
	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
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
	/**
	 * @return the serviceCityName
	 */
	public String getServiceCityName() {
		return serviceCityName;
	}
	/**
	 * @param serviceCityName the serviceCityName to set
	 */
	public void setServiceCityName(String serviceCityName) {
		this.serviceCityName = serviceCityName;
	}
}
