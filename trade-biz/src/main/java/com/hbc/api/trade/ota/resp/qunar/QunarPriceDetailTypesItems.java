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
public class QunarPriceDetailTypesItems {
	private Double 	basePrice; 			//	基本价格	Decimal	60
	private Integer baseTime; 			//	基本时间（单位：分钟）	Integer	90
	private Double 	overTimeCost; 		//	超时（例如：每六十分钟30刀）	Decimal	10.00
	private Integer baseDist;			//	基本里程（米）	Integer	30000
	private Double 	overDistCost; 		//	超出公里数（例如：每公里3刀）	Decimal	21.00
	private Double 	freeDriverCost; 	//	如果乘客想去一个偏远地区，需要加的司机回程的空驶费	Decimal	5.00
	private Double 	nightServiceCost; 	//	夜间费用（例如：23:00-06:00）	Decimal	15:00
	private Double 	vatCost; 			//	Tax	Decimal	5
	private Double  shortPreBookCost; 	//	临时订车	Decimal	20
	private Double 	meetCost; 			//	接机费	Decimal	5
	private Double 	otherCost; 			//	其他费用	Decimal	10.00
	private Double 	tip; 				//	小费	Decimal	5.00
	private Double 	childrenChairCost; 	//	儿童座椅费用	Decimal	20.00
	private Double 	pickupCardCost; 	//	举牌接送服务费用	Decimal	20.00
	private String 	pricecodecription; 	//	价格描述	String	The price include the fare of freeway
	/**
	 * @return the basePrice
	 */
	public Double getBasePrice() {
		return basePrice;
	}
	/**
	 * @param basePrice the basePrice to set
	 */
	public void setBasePrice(Double basePrice) {
		this.basePrice = basePrice;
	}
	/**
	 * @return the baseTime
	 */
	public Integer getBaseTime() {
		return baseTime;
	}
	/**
	 * @param baseTime the baseTime to set
	 */
	public void setBaseTime(Integer baseTime) {
		this.baseTime = baseTime;
	}
	/**
	 * @return the overTimeCost
	 */
	public Double getOverTimeCost() {
		return overTimeCost;
	}
	/**
	 * @param overTimeCost the overTimeCost to set
	 */
	public void setOverTimeCost(Double overTimeCost) {
		this.overTimeCost = overTimeCost;
	}
	/**
	 * @return the baseDist
	 */
	public Integer getBaseDist() {
		return baseDist;
	}
	/**
	 * @param baseDist the baseDist to set
	 */
	public void setBaseDist(Integer baseDist) {
		this.baseDist = baseDist;
	}
	/**
	 * @return the overDistCost
	 */
	public Double getOverDistCost() {
		return overDistCost;
	}
	/**
	 * @param overDistCost the overDistCost to set
	 */
	public void setOverDistCost(Double overDistCost) {
		this.overDistCost = overDistCost;
	}
	/**
	 * @return the freeDriverCost
	 */
	public Double getFreeDriverCost() {
		return freeDriverCost;
	}
	/**
	 * @param freeDriverCost the freeDriverCost to set
	 */
	public void setFreeDriverCost(Double freeDriverCost) {
		this.freeDriverCost = freeDriverCost;
	}
	/**
	 * @return the nightServiceCost
	 */
	public Double getNightServiceCost() {
		return nightServiceCost;
	}
	/**
	 * @param nightServiceCost the nightServiceCost to set
	 */
	public void setNightServiceCost(Double nightServiceCost) {
		this.nightServiceCost = nightServiceCost;
	}
	/**
	 * @return the vatCost
	 */
	public Double getVatCost() {
		return vatCost;
	}
	/**
	 * @param vatCost the vatCost to set
	 */
	public void setVatCost(Double vatCost) {
		this.vatCost = vatCost;
	}
	/**
	 * @return the shortPreBookCost
	 */
	public Double getShortPreBookCost() {
		return shortPreBookCost;
	}
	/**
	 * @param shortPreBookCost the shortPreBookCost to set
	 */
	public void setShortPreBookCost(Double shortPreBookCost) {
		this.shortPreBookCost = shortPreBookCost;
	}
	/**
	 * @return the meetCost
	 */
	public Double getMeetCost() {
		return meetCost;
	}
	/**
	 * @param meetCost the meetCost to set
	 */
	public void setMeetCost(Double meetCost) {
		this.meetCost = meetCost;
	}
	/**
	 * @return the otherCost
	 */
	public Double getOtherCost() {
		return otherCost;
	}
	/**
	 * @param otherCost the otherCost to set
	 */
	public void setOtherCost(Double otherCost) {
		this.otherCost = otherCost;
	}
	/**
	 * @return the tip
	 */
	public Double getTip() {
		return tip;
	}
	/**
	 * @param tip the tip to set
	 */
	public void setTip(Double tip) {
		this.tip = tip;
	}
	/**
	 * @return the childrenChairCost
	 */
	public Double getChildrenChairCost() {
		return childrenChairCost;
	}
	/**
	 * @param childrenChairCost the childrenChairCost to set
	 */
	public void setChildrenChairCost(Double childrenChairCost) {
		this.childrenChairCost = childrenChairCost;
	}
	/**
	 * @return the pickupCardCost
	 */
	public Double getPickupCardCost() {
		return pickupCardCost;
	}
	/**
	 * @param pickupCardCost the pickupCardCost to set
	 */
	public void setPickupCardCost(Double pickupCardCost) {
		this.pickupCardCost = pickupCardCost;
	}
	/**
	 * @return the pricecodecription
	 */
	public String getPricecodecription() {
		return pricecodecription;
	}
	/**
	 * @param pricecodecription the pricecodecription to set
	 */
	public void setPricecodecription(String pricecodecription) {
		this.pricecodecription = pricecodecription;
	}

}
