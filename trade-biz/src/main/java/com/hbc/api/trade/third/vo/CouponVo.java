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
 * Author: colin.han
 * Revision: 1.0
 * 
 *  
 */
package com.hbc.api.trade.third.vo;

import java.sql.Date;

/**
 * @author colin
 *
 */
public class CouponVo {

	private String couponId;
	private String couponBatchId;
	private String couponBatchName;
	private String couponPackageId;
	private String couponCode;
	private String orderNo;
	private Integer couponPrice;
	private Integer couponDiscount;
	private String password;
	private Integer status;
	private Integer bindUserType;
	private String bindUserId;
	private Date bindDateTime;
	private String useUserId;
	private Date useDateTime;
	private Integer couponType;
	private Integer transferCount;
	private Integer fromUserType;
	private String fromUserId;
	private String vendorId;
	private Integer vendorType;
	private Date updateTime;
	private Date createTime;

	private String orderRuleRemark;
	private String content;
	private String endTime;
	private String startTime;
	private String priceInfo;
	private Double actualPrice;

	private String applyType;
	private String applyCar;
	private String applyArea;

	/**
	 * @return the couponId
	 */
	public String getCouponId() {
		return couponId;
	}

	/**
	 * @param couponId
	 *            the couponId to set
	 */
	public void setCouponId(String couponId) {
		this.couponId = couponId;
	}

	/**
	 * @return the couponBatchId
	 */
	public String getCouponBatchId() {
		return couponBatchId;
	}

	/**
	 * @param couponBatchId
	 *            the couponBatchId to set
	 */
	public void setCouponBatchId(String couponBatchId) {
		this.couponBatchId = couponBatchId;
	}

	/**
	 * @return the couponPackageId
	 */
	public String getCouponPackageId() {
		return couponPackageId;
	}

	/**
	 * @param couponPackageId
	 *            the couponPackageId to set
	 */
	public void setCouponPackageId(String couponPackageId) {
		this.couponPackageId = couponPackageId;
	}

	/**
	 * @return the couponCode
	 */
	public String getCouponCode() {
		return couponCode;
	}

	/**
	 * @param couponCode
	 *            the couponCode to set
	 */
	public void setCouponCode(String couponCode) {
		this.couponCode = couponCode;
	}

	/**
	 * @return the couponPrice
	 */
	public Integer getCouponPrice() {
		return couponPrice;
	}

	/**
	 * @param couponPrice
	 *            the couponPrice to set
	 */
	public void setCouponPrice(Integer couponPrice) {
		this.couponPrice = couponPrice;
	}

	/**
	 * @return the couponDiscount
	 */
	public Integer getCouponDiscount() {
		return couponDiscount;
	}

	/**
	 * @param couponDiscount
	 *            the couponDiscount to set
	 */
	public void setCouponDiscount(Integer couponDiscount) {
		this.couponDiscount = couponDiscount;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the status
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * @return the bindUserType
	 */
	public Integer getBindUserType() {
		return bindUserType;
	}

	/**
	 * @param bindUserType
	 *            the bindUserType to set
	 */
	public void setBindUserType(Integer bindUserType) {
		this.bindUserType = bindUserType;
	}

	/**
	 * @return the bindUserId
	 */
	public String getBindUserId() {
		return bindUserId;
	}

	/**
	 * @param bindUserId
	 *            the bindUserId to set
	 */
	public void setBindUserId(String bindUserId) {
		this.bindUserId = bindUserId;
	}

	/**
	 * @return the bindDateTime
	 */
	public Date getBindDateTime() {
		return bindDateTime;
	}

	/**
	 * @param bindDateTime
	 *            the bindDateTime to set
	 */
	public void setBindDateTime(Date bindDateTime) {
		this.bindDateTime = bindDateTime;
	}

	/**
	 * @return the useUserId
	 */
	public String getUseUserId() {
		return useUserId;
	}

	/**
	 * @param useUserId
	 *            the useUserId to set
	 */
	public void setUseUserId(String useUserId) {
		this.useUserId = useUserId;
	}

	/**
	 * @return the useDateTime
	 */
	public Date getUseDateTime() {
		return useDateTime;
	}

	/**
	 * @param useDateTime
	 *            the useDateTime to set
	 */
	public void setUseDateTime(Date useDateTime) {
		this.useDateTime = useDateTime;
	}

	/**
	 * @return the couponType
	 */
	public Integer getCouponType() {
		return couponType;
	}

	/**
	 * @param couponType
	 *            the couponType to set
	 */
	public void setCouponType(Integer couponType) {
		this.couponType = couponType;
	}

	/**
	 * @return the transferCount
	 */
	public Integer getTransferCount() {
		return transferCount;
	}

	/**
	 * @param transferCount
	 *            the transferCount to set
	 */
	public void setTransferCount(Integer transferCount) {
		this.transferCount = transferCount;
	}

	/**
	 * @return the fromUserType
	 */
	public Integer getFromUserType() {
		return fromUserType;
	}

	/**
	 * @param fromUserType
	 *            the fromUserType to set
	 */
	public void setFromUserType(Integer fromUserType) {
		this.fromUserType = fromUserType;
	}

	/**
	 * @return the fromUserId
	 */
	public String getFromUserId() {
		return fromUserId;
	}

	/**
	 * @param fromUserId
	 *            the fromUserId to set
	 */
	public void setFromUserId(String fromUserId) {
		this.fromUserId = fromUserId;
	}

	/**
	 * @return the vendorId
	 */
	public String getVendorId() {
		return vendorId;
	}

	/**
	 * @param vendorId
	 *            the vendorId to set
	 */
	public void setVendorId(String vendorId) {
		this.vendorId = vendorId;
	}

	/**
	 * @return the vendorType
	 */
	public Integer getVendorType() {
		return vendorType;
	}

	/**
	 * @param vendorType
	 *            the vendorType to set
	 */
	public void setVendorType(Integer vendorType) {
		this.vendorType = vendorType;
	}

	/**
	 * @return the updateTime
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * @param updateTime
	 *            the updateTime to set
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * @return the createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * @param createTime
	 *            the createTime to set
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getOrderRuleRemark() {
		return orderRuleRemark;
	}

	public void setOrderRuleRemark(String orderRuleRemark) {
		this.orderRuleRemark = orderRuleRemark;
	}

	public String getCouponBatchName() {
		return couponBatchName;
	}

	public void setCouponBatchName(String couponBatchName) {
		this.couponBatchName = couponBatchName;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getPriceInfo() {
		return priceInfo;
	}

	public void setPriceInfo(String priceInfo) {
		this.priceInfo = priceInfo;
	}

	public Double getActualPrice() {
		return actualPrice;
	}

	public void setActualPrice(Double actualPrice) {
		this.actualPrice = actualPrice;
	}

	public String getApplyType() {
		return applyType;
	}

	public void setApplyType(String applyType) {
		this.applyType = applyType;
	}

	public String getApplyArea() {
		return applyArea;
	}

	public void setApplyArea(String applyArea) {
		this.applyArea = applyArea;
	}

	public String getApplyCar() {
		return applyCar;
	}

	public void setApplyCar(String applyCar) {
		this.applyCar = applyCar;
	}
}
