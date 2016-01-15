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
public class QunarServiceInfo implements SubJSONParam{
	private Integer childrenChair;				//	儿童座椅的数量	Short	2
	private Integer childrenAge;				//	儿童年龄组	Integer	2
	private Integer  isNeedAirportPickupCard;	//  是否需要举牌服务	Short	1 
	private Integer luggageNum;					//	行李个数	Integer	2
	private Integer staffNum;					//	乘客数量	Integer	2
	private String  airportPickupCardMsg;		//	接机牌填入信息	String	“张三-北京-中国-Qunar”
	/**
	 * @return the childrenChair
	 */
	public Integer getChildrenChair() {
		return childrenChair;
	}
	/**
	 * @param childrenChair the childrenChair to set
	 */
	public void setChildrenChair(Integer childrenChair) {
		this.childrenChair = childrenChair;
	}
	/**
	 * @return the childrenAge
	 */
	public Integer getChildrenAge() {
		return childrenAge;
	}
	/**
	 * @param childrenAge the childrenAge to set
	 */
	public void setChildrenAge(Integer childrenAge) {
		this.childrenAge = childrenAge;
	}
	/**
	 * @return the isNeedAirportPickupCard
	 */
	public Integer getIsNeedAirportPickupCard() {
		return isNeedAirportPickupCard;
	}
	/**
	 * @param isNeedAirportPickupCard the isNeedAirportPickupCard to set
	 */
	public void setIsNeedAirportPickupCard(Integer isNeedAirportPickupCard) {
		this.isNeedAirportPickupCard = isNeedAirportPickupCard;
	}
	/**
	 * @return the luggageNum
	 */
	public Integer getLuggageNum() {
		return luggageNum;
	}
	/**
	 * @param luggageNum the luggageNum to set
	 */
	public void setLuggageNum(Integer luggageNum) {
		this.luggageNum = luggageNum;
	}
	/**
	 * @return the staffNum
	 */
	public Integer getStaffNum() {
		return staffNum;
	}
	/**
	 * @param staffNum the staffNum to set
	 */
	public void setStaffNum(Integer staffNum) {
		this.staffNum = staffNum;
	}
	/**
	 * @return the airportPickupCardMsg
	 */
	public String getAirportPickupCardMsg() {
		return airportPickupCardMsg;
	}
	/**
	 * @param airportPickupCardMsg the airportPickupCardMsg to set
	 */
	public void setAirportPickupCardMsg(String airportPickupCardMsg) {
		this.airportPickupCardMsg = airportPickupCardMsg;
	}
}
