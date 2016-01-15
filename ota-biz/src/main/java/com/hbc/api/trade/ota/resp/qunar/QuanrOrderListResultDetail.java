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
package com.hbc.api.trade.ota.resp.qunar;

/**
 * @author Jongly Ran
 */
public class QuanrOrderListResultDetail {
	private String  qOrderId;		// 去哪儿订单号
	private Integer status;			// 状态
	private String  supplierOrderId;// HBC订单号

	/**
	 * @return the status
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * @return the supplierOrderId
	 */
	public String getSupplierOrderId() {
		return supplierOrderId;
	}

	/**
	 * @param supplierOrderId the supplierOrderId to set
	 */
	public void setSupplierOrderId(String supplierOrderId) {
		this.supplierOrderId = supplierOrderId;
	}

	/**
	 * @return the qOrderId
	 */
	public String getqOrderId() {
		return qOrderId;
	}

	/**
	 * @param qOrderId the qOrderId to set
	 */
	public void setqOrderId(String qOrderId) {
		this.qOrderId = qOrderId;
	}
}
