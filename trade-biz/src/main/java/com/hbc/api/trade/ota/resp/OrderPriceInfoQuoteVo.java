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
package com.hbc.api.trade.ota.resp;

/**
 * @author Jongly Ran
 */
public class OrderPriceInfoQuoteVo {

	private String models; // 车型名称
	private Integer carType; // 车级别
	private Integer seatCategory; // 座位类别
	private String carDesc; // 车型描述
	
	private String pricemark; // 价格标记
	private Double price; // 价格
	private Integer urgentFlag; // 是否急单 1是，0非
	
	private Double checkInPrice; // 送机代办登机手续费

	/**
	 * @return the models
	 */
	public String getModels() {
		return models;
	}

	/**
	 * @param models the models to set
	 */
	public void setModels(String models) {
		this.models = models;
	}

	/**
	 * @return the carType
	 */
	public Integer getCarType() {
		return carType;
	}

	/**
	 * @param carType the carType to set
	 */
	public void setCarType(Integer carType) {
		this.carType = carType;
	}

	/**
	 * @return the seatCategory
	 */
	public Integer getSeatCategory() {
		return seatCategory;
	}

	/**
	 * @param seatCategory the seatCategory to set
	 */
	public void setSeatCategory(Integer seatCategory) {
		this.seatCategory = seatCategory;
	}

	/**
	 * @return the carDesc
	 */
	public String getCarDesc() {
		return carDesc;
	}

	/**
	 * @param carDesc the carDesc to set
	 */
	public void setCarDesc(String carDesc) {
		this.carDesc = carDesc;
	}

	/**
	 * @return the pricemark
	 */
	public String getPricemark() {
		return pricemark;
	}

	/**
	 * @param pricemark the pricemark to set
	 */
	public void setPricemark(String pricemark) {
		this.pricemark = pricemark;
	}

	/**
	 * @return the price
	 */
	public Double getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(Double price) {
		this.price = price;
	}

	/**
	 * @return the urgentFlag
	 */
	public Integer getUrgentFlag() {
		return urgentFlag;
	}

	/**
	 * @param urgentFlag the urgentFlag to set
	 */
	public void setUrgentFlag(Integer urgentFlag) {
		this.urgentFlag = urgentFlag;
	}

	/**
	 * @return the checkInPrice
	 */
	public Double getCheckInPrice() {
		return checkInPrice;
	}

	/**
	 * @param checkInPrice the checkInPrice to set
	 */
	public void setCheckInPrice(Double checkInPrice) {
		this.checkInPrice = checkInPrice;
	}
}
