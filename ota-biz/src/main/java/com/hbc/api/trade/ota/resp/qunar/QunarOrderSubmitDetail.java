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

import com.hbc.api.trade.bdata.common.sign.SubJSONParam;

/**
 * 下单请求与返回共用
 * @author Jongly Ran
 */
public class QunarOrderSubmitDetail implements SubJSONParam {
	private Double basePrice = 0.0; 		//	是	基本价格   	Decimal	60
	private Integer baseTime = 0; 			//	是	基本时间 (分钟)	Integer	90
	private Double overTimeCost = 0.0; 		//	是	超出时间计费 (eg.每60分钟30美金)	Decimal	10.00
	private Double baseDist = 0.0; 			//	是	基础距离(米)	Integer	30000
	private Double overDistCost = 0.0; 		//	是	超出距离收费(eg.每公里3美金)	Decimal	21.0
	private Double freeDriverCost = 0.0; 	//	是	空驶费	Decimal	5.00
	private Double nightServiceCost = 0.0; 	//	是	夜间费	Decimal	15.00
	private Double vatCost = 0.0; 			//	是	 税	Decimal	5
	private Double  shortPreBookCost = 0.0; //	是	临时订车	Decimal	20
	private Double meetCost = 0.0	; 		//	是	接机费	Decimal	5
	private Double otherCost = 0.0; 		//	是	其他	Decimal	10.00
	private Double tip = 0.0; 				//	是	司机小费	Decimal	5.00
	private Double childrenChairCost = 0.0; //	是	儿童座椅费	Decimal	20.00
	private Double pickupCardCost = 0.0; 	//	是	接机牌费	Decimal	20.00
	private String pricecodecription; 		//	是	价格描述	String	包含接机费
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
	public Double getBaseDist() {
		return baseDist;
	}
	/**
	 * @param baseDist the baseDist to set
	 */
	public void setBaseDist(Double baseDist) {
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
