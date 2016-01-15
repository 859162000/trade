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
package com.hbc.api.trade.ota.qunar.req;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.hbc.api.trade.order.enums.order.OrderType;
import com.hbc.api.trade.order.enums.ota.AgentChannelEnum;
import com.hbc.api.trade.ota.req.CalculatePriceParam;

/**
 * @author Jongly Ran
 */
public class QunarCalculatePriceParam extends QunarBaseParam{
    
    private String country;  			//  是	乘车所在国家	String	“台北”
    private String city;  				//  是	乘车所在城市	String	“台湾”
    
    @NotNull
    private Integer  serviceType;  		// 	是	服务类型	Integer	1:城市内用车 2:飞机场接机 3:飞机场送机 4:火车站接车 5:火车站送车
    private Integer serviceTypeRefine;  	// 	否	包车或出租车	Integer	1:出租车 2:包车
    private String carType;  			// 	否	车型Mapping ID 	String	在去哪儿数据库中存储，详细需要和去哪儿的开发人员讨论 eg:1
    private Integer  staffNum;  		//  否	乘客数	Integer	4
    private Integer luggageNum;  		// 	否	行李数	Integer	2
    private String flightNo;  			// 	否	航班号	String	CA5810/K3328
    
    @NotBlank
    private String airportCode;  		// 	否	飞机场代码	String	PVG
    private String fromAddrName	;  		//  是	出发地	String	“台北桃园机场 ”
    private String fromAddrNameEn;  	// 	否	出发地英文	String	
    
    @NotBlank
    private Double fromLong;  			// 	是	出发地经度	Double	101.74803287322996

    @NotBlank
    private Double fromLat;  			// 	是	出发地纬度	Double	2.746386823121271
    private String toAddrName;  		//  是	目的地	String	“香格里拉酒店”
    private String toAddrNameEn;  		// 	否	目的地	String	

    @NotBlank
    private Double toLong;  			// 	是	目的地经度	Double	101.69177235615736

    @NotBlank
    private Double toLat;  				// 	是	目的地纬度	Double	3.1420125400764917
    private String distance;  			// 	是	起始和终止之间的距离（米）	Integer	37000
    private String  estimatedTime;  	// 	是	预计耗时(参考)，分钟	Integer	30
    private String childrenChair;  		// 	是	儿童座椅数	Integer	1
    private String isNeedAirportPickupCard;  	// 	是	举牌服务	Integer	1 需要 2 不需要

    @NotBlank
    private String orderTime;  			// 	是	乘车时间	String	“2014-09-01 17:10:00”
    private Long orderTimeMs;  			// 	是	乘车时间（毫秒）	Long	1399887903000

	/**
	 * @return the priceQueryBean
	 */
	public CalculatePriceParam toStandardCalculatePriceParam() {
		CalculatePriceParam priceQueryBean = new CalculatePriceParam();
		priceQueryBean.setFlightAirportCode(airportCode);
		priceQueryBean.setServicePartner(AgentChannelEnum.QUNAR_CHANNEL.value);
		priceQueryBean.setServiceTime(orderTime);
		priceQueryBean.setStartAddressPoi(fromLat + "," + fromLong);
		priceQueryBean.setDestAddressPoi(toLat + "," + toLong);
		priceQueryBean.setCarTypeId(carType);
		priceQueryBean.setSign(getSign());
		Double distanceOfQunar = Double.valueOf(distance);
		Double hbcUnitOfDistance = 1000d;
		priceQueryBean.setDistance(distanceOfQunar/hbcUnitOfDistance);
		priceQueryBean.setEstimatedTime(estimatedTime);
		priceQueryBean.setOrderType(serviceType == 2 ? OrderType.PICKUPORDER.value : OrderType.TRANSFER.value);
		return priceQueryBean;
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
	 * @return the fromAddrNameEn
	 */
	public String getFromAddrNameEn() {
		return fromAddrNameEn;
	}


	/**
	 * @param fromAddrNameEn the fromAddrNameEn to set
	 */
	public void setFromAddrNameEn(String fromAddrNameEn) {
		this.fromAddrNameEn = fromAddrNameEn;
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
	 * @return the toAddrNameEn
	 */
	public String getToAddrNameEn() {
		return toAddrNameEn;
	}


	/**
	 * @return the carType
	 */
	public String getCarType() {
		return carType;
	}


	/**
	 * @param toAddrNameEn the toAddrNameEn to set
	 */
	public void setToAddrNameEn(String toAddrNameEn) {
		this.toAddrNameEn = toAddrNameEn;
	}


	/**
	 * @return the distance
	 */
	public String getDistance() {
		return distance;
	}


	/**
	 * @param distance the distance to set
	 */
	public void setDistance(String distance) {
		this.distance = distance;
	}


	/**
	 * @return the estimatedTime
	 */
	public String getEstimatedTime() {
		return estimatedTime;
	}


	/**
	 * @param estimatedTime the estimatedTime to set
	 */
	public void setEstimatedTime(String estimatedTime) {
		this.estimatedTime = estimatedTime;
	}


	/**
	 * @return the childrenChair
	 */
	public String getChildrenChair() {
		return childrenChair;
	}


	/**
	 * @param childrenChair the childrenChair to set
	 */
	public void setChildrenChair(String childrenChair) {
		this.childrenChair = childrenChair;
	}


	/**
	 * @return the isNeedAirportPickupCard
	 */
	public String getIsNeedAirportPickupCard() {
		return isNeedAirportPickupCard;
	}


	/**
	 * @param isNeedAirportPickupCard the isNeedAirportPickupCard to set
	 */
	public void setIsNeedAirportPickupCard(String isNeedAirportPickupCard) {
		this.isNeedAirportPickupCard = isNeedAirportPickupCard;
	}


	/**
	 * @param carType the carType to set
	 */
	public void setCarType(String carType) {
		this.carType = carType;
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
	 * @return the orderTimeMs
	 */
	public Long getOrderTimeMs() {
		return orderTimeMs;
	}


	/**
	 * @param orderTimeMs the orderTimeMs to set
	 */
	public void setOrderTimeMs(Long orderTimeMs) {
		this.orderTimeMs = orderTimeMs;
	}
}
