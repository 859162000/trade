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
public class QunarOrderSubmitResult extends QunarResult{
	private String qOrderId;				//	去哪儿订单号	String	“q100005”
	private String vOrderId;				//	供应商订单号	String	“v100002”
	private Integer serviceType;			//	服务类型	Integer	1
	private Integer serviceTypeRefine;		//	出租车或包车	Integer	2
	private Integer carType;				//	车辆类型	Integer	2
	private String currency;				//	货币种类	String	“CNY”
	private Double totalPrice;				//	总价格	Decimal	106.00
	private String country;					// 	乘车国家	String	“台湾”
	private String city;					// 	乘车城市	String	“台北”
	private String fromAddrName;			//	出发地	String	“台北桃园机场”
	private Double fromLong;				//	出发地经度	Double	101.74803287322996
	private Double fromLat;					//	出发地纬度	Double	2.746386823121271
	private String toAddrName;				// 	目的地	String	“香格里拉酒店”
	private Double toLong;					//	目的地经度	Double	101.69177235615736
	private Double toLat;					//	目的地纬度	Double	3.1420125400764917
	private String meetingPoint;			//	接机地点	String	“行李提取出口处”
	private Integer distance;				//	出发地到目的地的距离	Integer	37000
	private Integer estimatedTime;			//	预计路上花费时间（单位：分钟）	Integer	56
	public QunarServiceInfo serviceInfo;	//  		Object	
	public QunarOrderSubmitDetail detail;	//	
	
	private String airportCode;
	private String createTime;
	private String createTimeMs;
	private String flightNo;
	private String fromAddrDetail;
	private String orderTimeMs;
	private String orderTime;
	private Integer luggageNum;				// 行李数
	
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
	 * @return the airportCode
	 */
	public String getAirportCode() {
		return airportCode;
	}
	/**
	 * @param airportCode the airportCode to set
	 */
	public void setAirportCode(String airportCode) {
		this.airportCode = airportCode;
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
	/**
	 * @return the createTimeMs
	 */
	public String getCreateTimeMs() {
		return createTimeMs;
	}
	/**
	 * @param createTimeMs the createTimeMs to set
	 */
	public void setCreateTimeMs(String createTimeMs) {
		this.createTimeMs = createTimeMs;
	}
	/**
	 * @return the flightNo
	 */
	public String getFlightNo() {
		return flightNo;
	}
	/**
	 * @param flightNo the flightNo to set
	 */
	public void setFlightNo(String flightNo) {
		this.flightNo = flightNo;
	}
	/**
	 * @return the fromAddrDetail
	 */
	public String getFromAddrDetail() {
		return fromAddrDetail;
	}
	/**
	 * @param fromAddrDetail the fromAddrDetail to set
	 */
	public void setFromAddrDetail(String fromAddrDetail) {
		this.fromAddrDetail = fromAddrDetail;
	}
	/**
	 * @return the orderTimeMs
	 */
	public String getOrderTimeMs() {
		return orderTimeMs;
	}
	/**
	 * @param orderTimeMs the orderTimeMs to set
	 */
	public void setOrderTimeMs(String orderTimeMs) {
		this.orderTimeMs = orderTimeMs;
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
	 * @return the vOrderId
	 */
	public String getvOrderId() {
		return vOrderId;
	}
	/**
	 * @param vOrderId the vOrderId to set
	 */
	public void setvOrderId(String vOrderId) {
		this.vOrderId = vOrderId;
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
	 * @return the totalPrice
	 */
	public Double getTotalPrice() {
		return totalPrice;
	}
	/**
	 * @param totalPrice the totalPrice to set
	 */
	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
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
	 * @return the meetingPoint
	 */
	public String getMeetingPoint() {
		return meetingPoint;
	}
	/**
	 * @param meetingPoint the meetingPoint to set
	 */
	public void setMeetingPoint(String meetingPoint) {
		this.meetingPoint = meetingPoint;
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
	 * @return the estimatedTime
	 */
	public Integer getEstimatedTime() {
		return estimatedTime;
	}
	/**
	 * @param estimatedTime the estimatedTime to set
	 */
	public void setEstimatedTime(Integer estimatedTime) {
		this.estimatedTime = estimatedTime;
	}
	
}
