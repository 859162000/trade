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
package com.hbc.api.trade.order.controller.query.vo;

/**
 * 返回增项费用明细，所有字段用String，且带上单位，APP仅显示。
 * <ul>注意：
 * <li>当产生超天数时，不计超公里和超时</li>
 * <li>当不超天数时，超公里和超时不累加，哪个总费用高取哪个</li>
 * </ul>
 * @author Jongly Ran
 */
public class AdditionalCostDetailBean {
	private String dailyDate; 			// 包车日期
	private String overTime; 			// 超时 包车以小时计数，接机以分钟计数
	private String overTimePrice; 		// 超时费用
	private String overDistance; 		// 超公里数
	
	private String overDistancePrice; 	// 超里程费用
	private String overDay; 			// 超天数
	private String overDayPrice; 		// 超天数费用
	private String prePaymentPrice; 	// 垫付费用
    private String overWaitTimeCost;	// 超等待时间（仅限接送机，次租）  
    private String applyfee;			// 小计
	
	/**
	 * @return the applyfee
	 */
	public String getApplyfee() {
		return applyfee;
	}
	/**
	 * @param applyfee the applyfee to set
	 */
	public void setApplyfee(String applyfee) {
		this.applyfee = applyfee;
	}
	/**
	 * @return the overWaitTimeCost
	 */
	public String getOverWaitTimeCost() {
		return overWaitTimeCost;
	}
	/**
	 * @param overWaitTimeCost the overWaitTimeCost to set
	 */
	public void setOverWaitTimeCost(String overWaitTimeCost) {
		this.overWaitTimeCost = overWaitTimeCost;
	}
	/**
	 * @return 包车日期
	 */
	public String getDailyDate() {
		return dailyDate;
	}
	/**
	 * @param dailyDate 包车日期
	 */
	public void setDailyDate(String dailyDate) {
		this.dailyDate = dailyDate;
	}
	/**
	 * @return 超时 包车以小时计数，接机以分钟计数
	 */
	public String getOverTime() {
		return overTime;
	}
	/**
	 * @param overTime 超时 包车以小时计数，接机以分钟计数
	 */
	public void setOverTime(String overTime) {
		this.overTime = overTime;
	}
	/**
	 * @return 超时费用
	 */
	public String getOverTimePrice() {
		return overTimePrice;
	}
	/**
	 * @param overTimePrice 超时费用
	 */
	public void setOverTimePrice(String overTimePrice) {
		this.overTimePrice = overTimePrice;
	}
	/**
	 * @return 超公里数
	 */
	public String getOverDistance() {
		return overDistance;
	}
	/**
	 * @param overDistance 超公里数
	 */
	public void setOverDistance(String overDistance) {
		this.overDistance = overDistance;
	}
	/**
	 * @return 超里程费用
	 */
	public String getOverDistancePrice() {
		return overDistancePrice;
	}
	/**
	 * @param overDistancePrice 超里程费用
	 */
	public void setOverDistancePrice(String overDistancePrice) {
		this.overDistancePrice = overDistancePrice;
	}
	/**
	 * @return 超天数
	 */
	public String getOverDay() {
		return overDay;
	}
	/**
	 * @param overDay 超天数
	 */
	public void setOverDay(String overDay) {
		this.overDay = overDay;
	}
	/**
	 * @return 超天数费用
	 */
	public String getOverDayPrice() {
		return overDayPrice;
	}
	/**
	 * @param overDayPrice 超天数费用
	 */
	public void setOverDayPrice(String overDayPrice) {
		this.overDayPrice = overDayPrice;
	}
	/**
	 * @return 垫付费用
	 */
	public String getPrePaymentPrice() {
		return prePaymentPrice;
	}
	/**
	 * @param prePaymentPrice 垫付费用
	 */
	public void setPrePaymentPrice(String prePaymentPrice) {
		this.prePaymentPrice = prePaymentPrice;
	}
}
