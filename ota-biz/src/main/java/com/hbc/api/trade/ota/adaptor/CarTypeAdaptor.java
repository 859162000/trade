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
package com.hbc.api.trade.ota.adaptor;

import java.util.Map;

import com.hbc.api.trade.bdata.common.TradeConstant;

/**
 * @author Jongly Ran
 */
public abstract class CarTypeAdaptor {


	private Integer seatCategory;
	private Integer carType;

	private String  hbcCarDesc;				// 
	@SuppressWarnings("unused")
	private String  carTypeForPriceVerify; 	// 
	
	private Integer carTypeUsedByAPI;		// 
	private String  thirdCarDesc;			// 
	
	public abstract Map<Integer, String> carTypeMap() ;
	
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
	 * @return the hbcCarDesc
	 */
	public String getHbcCarDesc() {
		return hbcCarDesc;
	}

	/**
	 * @param hbcCarDesc the hbcCarDesc to set
	 */
	public void setHbcCarDesc(String hbcCarDesc) {
		this.hbcCarDesc = hbcCarDesc;
	}

	/**
	 * @return the carTypeForPriceVerify
	 */
	public String getCarTypeForPriceVerify() {
		return  carType + TradeConstant.SPLITER_BOTTOM_LINE + seatCategory;
	}

	/**
	 * @return the carTypeUsedByAPI
	 */
	public Integer getCarTypeUsedByAPI() {
		return carTypeUsedByAPI;
	}

	/**
	 * @param carTypeUsedByAPI the carTypeUsedByAPI to set
	 */
	public void setCarTypeUsedByAPI(Integer carTypeUsedByAPI) {
		this.carTypeUsedByAPI = carTypeUsedByAPI;
	}

	/**
	 * @return the thirdCarDesc
	 */
	public String getThirdCarDesc() {
		return thirdCarDesc;
	}

	/**
	 * @param thirdCarDesc the thirdCarDesc to set
	 */
	public void setThirdCarDesc(String thirdCarDesc) {
		this.thirdCarDesc = thirdCarDesc;
	}
}
