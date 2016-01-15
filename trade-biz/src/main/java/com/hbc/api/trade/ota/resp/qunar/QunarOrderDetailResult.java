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
public class QunarOrderDetailResult extends QunarResult {
	private String qOrderId ; 			//	去哪儿订单号	String	“q100001”
	private Integer status ; 			//	订单状态	Integer	参见4.1
	private Double price ; 				//	价格	decimal	25.60
	private String currency ; 			//	货币种类	String	“CNY”
	private Integer orderType ; 		//	订单类型	Integer	0：即时订单 1：预约订单
	private Integer serviceType ; 		//	服务类型	Integer	1:城市内用车 2:飞机场接机 3:飞机场送机 4:火车站接车 5:火车站送车
	private Integer serviceTypeRefine ; //	出租车或包车	Integer	2
	private Integer distance	 ; 		//  出发地和目的地之间的距离	Integer	100(m)
	private Integer motorCycleType ; 	//	车的类型	Short	参见3.3
	
	private String arrivalTime ; 		//	估算的到达时间	String	”2014-09-01 23:00:00”
	private String country ; 			//	用车国家	String	“台湾”
	private String city  ; 				//	用车城市	String	“台北”
	private String fromAddrName ; 		//	出发地	String	“台北桃园机场”
	private Double fromLong ; 			//	出发地经度	Double	101.74803287322996
	private Double fromLat ; 			//	出发地纬度	Double	2.746386823121271
	private String toAddrName ; 		// 	目的地	String	“香格里拉酒店”
	private Double toLong ; 			//	目的地经度	Double	101.69177235615736
	private Double toLat ; 				//	目的地纬度	Double	3.1420125400764917
	private String orderTime ; 			//	订单执行日期	String	”2014-09-01 23:00:00”
	private String createTime ; 		//	订单创建时间	String	”2014-09-01 20:00:00”
	public QunarDriverInfo driverInfo;	//	司机信息	object	

	public QunarOrderDetailResult() {
		super();
	}

	public QunarOrderDetailResult(int code, String errorMsg) {
		super(code, errorMsg);
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
	 * @return the currency
	 */
	public String getCurrency() {
		return currency;
	}

	/**
	 * @param currency the currency to set
	 */
	public void setCurrency(String currency) {
		this.currency = currency;
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
	 * @return the serviceType
	 */
	public Integer getServiceType() {
		return serviceType;
	}

	/**
	 * @param serviceType the serviceType to set
	 */
	public void setServiceType(Integer serviceType) {
		this.serviceType = serviceType;
	}

	/**
	 * @return the serviceTypeRefine
	 */
	public Integer getServiceTypeRefine() {
		return serviceTypeRefine;
	}

	/**
	 * @param serviceTypeRefine the serviceTypeRefine to set
	 */
	public void setServiceTypeRefine(Integer serviceTypeRefine) {
		this.serviceTypeRefine = serviceTypeRefine;
	}

	/**
	 * @return the distance
	 */
	public Integer getDistance() {
		return distance;
	}

	/**
	 * @param distance the distance to set
	 */
	public void setDistance(Integer distance) {
		this.distance = distance;
	}

	/**
	 * @return the motorCycleType
	 */
	public Integer getMotorCycleType() {
		return motorCycleType;
	}

	/**
	 * @param motorCycleType the motorCycleType to set
	 */
	public void setMotorCycleType(Integer motorCycleType) {
		this.motorCycleType = motorCycleType;
	}

	/**
	 * @return the arrivalTime
	 */
	public String getArrivalTime() {
		return arrivalTime;
	}

	/**
	 * @param arrivalTime the arrivalTime to set
	 */
	public void setArrivalTime(String arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the fromAddrName
	 */
	public String getFromAddrName() {
		return fromAddrName;
	}

	/**
	 * @param fromAddrName the fromAddrName to set
	 */
	public void setFromAddrName(String fromAddrName) {
		this.fromAddrName = fromAddrName;
	}

	/**
	 * @return the fromLong
	 */
	public Double getFromLong() {
		return fromLong;
	}

	/**
	 * @param fromLong the fromLong to set
	 */
	public void setFromLong(Double fromLong) {
		this.fromLong = fromLong;
	}

	/**
	 * @return the fromLat
	 */
	public Double getFromLat() {
		return fromLat;
	}

	/**
	 * @param fromLat the fromLat to set
	 */
	public void setFromLat(Double fromLat) {
		this.fromLat = fromLat;
	}

	/**
	 * @return the toAddrName
	 */
	public String getToAddrName() {
		return toAddrName;
	}

	/**
	 * @param toAddrName the toAddrName to set
	 */
	public void setToAddrName(String toAddrName) {
		this.toAddrName = toAddrName;
	}

	/**
	 * @return the toLong
	 */
	public Double getToLong() {
		return toLong;
	}

	/**
	 * @param toLong the toLong to set
	 */
	public void setToLong(Double toLong) {
		this.toLong = toLong;
	}

	/**
	 * @return the toLat
	 */
	public Double getToLat() {
		return toLat;
	}

	/**
	 * @param toLat the toLat to set
	 */
	public void setToLat(Double toLat) {
		this.toLat = toLat;
	}

	/**
	 * @return the orderTime
	 */
	public String getOrderTime() {
		return orderTime;
	}

	/**
	 * @param orderTime the orderTime to set
	 */
	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}

	/**
	 * @return the createTime
	 */
	public String getCreateTime() {
		return createTime;
	}

	/**
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

}
