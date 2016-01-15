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
public class QunarPriceDetailTypes {
	public static enum PriceType {
		FORMAL(1), SOLID(2), LOWEST(3);
		
		private int value = 0;

	    private PriceType(int value) {
	        this.value = value;
	    }
	    
	    public int value() {
	    	return this.value;
	    }
	} 

	private Integer carType; 				//	车型	Integer	参见3.3
	private Integer priceType; 				//	价格类型	Integer	参见3.4
	private Double price; 					//	此车型的总价格	Decimal	106.00
	public QunarPriceDetailTypesItems detail; 	//	此车型的详细价格列表	JsonObject
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
	 * @return the priceType
	 */
	public Integer getPriceType() {
		return priceType;
	}
	/**
	 * @param priceType the priceType to set
	 */
	public void setPriceType(Integer priceType) {
		this.priceType = priceType;
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
	
	public static void main(String[] args) {
		System.out.println(PriceType.SOLID.ordinal());
	}
}
