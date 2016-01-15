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

import com.hbc.api.trade.bdata.common.util.TimeConverter;
import com.hbc.api.trade.order.enums.order.OrderType;
import com.hbc.api.trade.order.enums.ota.AgentChannelEnum;
import com.hbc.api.trade.order.mapping.gen.bean.OrderBean;
import com.hbc.api.trade.ota.mapping.gen.bean.TradeThirdOrder;
import com.hbc.api.trade.ota.req.OrderSubmitParam;
import com.hbc.api.trade.ota.resp.qunar.QunarOrderSubmitDetail;
import com.hbc.api.trade.ota.resp.qunar.QunarServiceInfo;

/**
 * @author Jongly Ran
 */
public class QunarOrderSubmitParam  extends QunarBaseParam{
	@NotNull private String qOrderId; 		//	是	去哪儿订单号	String	q100001 (供应商通过这个参数来判断是否是重复订单
	private String flightNo; 				//	否	航班号	String	CA5810/K3328
	@NotNull private String airportCode; 	//	否	服务机场代码，接机和送机都用这个	String	PVG
    @NotNull private Integer  serviceType;  // 	是	服务类型	Integer	1:城市内用车 2:飞机场接机 3:飞机场送机 4:火车站接车 5:火车站送车
	private Integer serviceTypeRefine; 		//	否	包车或出租车	Integer	1:出租车 2:包车
	
	@NotNull private Integer carType; 		//	是	车的类型	Short	参见4.3
	private String currency; 				//	否	货币种类	String	“CNY”
	@NotNull private Double totalPrice; 	//	是	总价	Decimal	总价= 计算的价格+ 其他服务价格
	private String distance; 		//	是	出发地和目的地之间的距离	Integer	37000
	private Integer estimatedTime; //	是	估算的到大时间（分钟）	Integer	56
	private Integer orderType; 				//	是	订单类型	Integer	0：立即叫车 1：预约叫车
	private String country; 				// 	是	用车国家	String	“台湾”
	private String city; 					// 	是	用车城市	String	“台北”
	private String fromAddrName; 	//  是	出发地	String	“台北桃园机场”
	
	private String fromAddrNameEn; 			//	否	出发地英文	String	
	@NotNull private String fromLong; 		//	是	出发地经度	String	101.74803287322996
	@NotNull private String fromLat; 		//	是	出发地纬度	String	2.746386823121271
	private String toAddrName; 	// 	是	目的地	String	“香格里拉酒店”
	private String toAddrNameEn; 			//	否	目的地英文	String	
	@NotNull private String toLong; 		//	是	目的地经度	String	101.69177235615736
	@NotNull private String toLat; 			//	是	目的地纬度	String	3.1420125400764917
	
	private QunarPassengerInfo passengerInfo; 
	private QunarServiceInfo serviceInfo; 	// 	是		Object	
	private String orderTime; 		//	是	订单执行日期	Long	1399187803
	private QunarOrderSubmitDetail detail; 	//	是	选择的车的价格详情	JsonObject	
	private String appointment; 	//	是	乘车时间	String	“2014-09-01 15:15:00”
	private Long orderTimeMs	; 			//	是	乘车时间（毫秒）	Long	1399887903000
	private String createTime; 				//	是	订单创建时间	String	“2014-09-01 10:15:00”
	private Long createTimeMs; 				//  是	订单创建时间（毫秒）	Long	1399180003
	private String  pricemark ; 			// 
	private Integer urgentFlag;				// 是否急单，来自查价
	private Integer luggageNum;				// 行李数
	private Double offlineCash;				// 线下结算
	private String fromAddrDetail;		
	private String toAddrDetail;

	/* 2015-12-16 新增 */
	private String 		bookingAgentMobileCode; // 代订人手机号码区号
	private String 		bookingAgentMobile; 	// 代订人手机号码 
	
	/* 2016-01-08 新增 */
	private String 		remark;	// 备注
	
	/* 老版本加的 */
	private String flightStartTime;			// 起飞时间
	private String depFlightTime;			// 降落时间
	private String depCityName;				// 降落城市
	private String depAirportThreeCode;		

	public OrderSubmitParam toStandardSubmitParam(Integer carTypeId) {
		OrderBean orderBean = new OrderBean();
		orderBean.setAgentId(AgentChannelEnum.QUNAR_CHANNEL.value+"");
		orderBean.setAgentName(AgentChannelEnum.QUNAR_CHANNEL.desc);
		orderBean.setAgentOpid(AgentChannelEnum.QUNAR_CHANNEL.value+"");
		orderBean.setAgentOpname(AgentChannelEnum.QUNAR_CHANNEL.desc);
		orderBean.setOrderChannel(AgentChannelEnum.QUNAR_CHANNEL.value);
		orderBean.setServiceTime(TimeConverter.toDate(orderTime));
		orderBean.setUserAreaCode3(bookingAgentMobileCode);
		orderBean.setUserMobile3(bookingAgentMobile);
		orderBean.setUserRemark(remark);
		orderBean.setExpectedCompTime(estimatedTime);
		orderBean.setThirdTradeNo(qOrderId);
		orderBean.setStartAddress(fromAddrName);
		orderBean.setStartAddressPoi(fromLat + "," + fromLong);
		orderBean.setDestAddress(toAddrName);
		orderBean.setDestAddressPoi(toLat + "," + toLong);
		Double distanceOfQunar = Double.valueOf(distance);
		Double hbcUnitOfDistance = 1000d;
		orderBean.setDistance(distanceOfQunar/hbcUnitOfDistance);
		orderBean.setStartAddressDetail( (fromAddrDetail == null || fromAddrDetail.length() ==0) ? fromAddrName : fromAddrDetail);
		orderBean.setDestAddressDetail( (toAddrDetail == null  || toAddrDetail.length() ==0) ? toAddrName : toAddrDetail);
		if(serviceType == 2) {
			orderBean.setOrderType(OrderType.PICKUPORDER.value);
			orderBean.setFlightFlyTime(depFlightTime == null ? null : TimeConverter.toDate(depFlightTime));
			orderBean.setFlightDestCode(airportCode);
		} else {
			orderBean.setOrderType(OrderType.TRANSFER.value);
			orderBean.setFlightFlyTime(flightStartTime == null ? null : TimeConverter.toDate(flightStartTime));
			orderBean.setFlightAirportCode(airportCode);
		}
		orderBean.setFlightNo(flightNo);
		orderBean.setUrgentFlag(urgentFlag == null ? 0 : urgentFlag);
		orderBean.setCarTypeId(carType);
		orderBean.setPriceMark(pricemark);

		orderBean.setPriceChannel(totalPrice);
		
		TradeThirdOrder thirdOrderBean = new TradeThirdOrder();
		thirdOrderBean.setThirdPartner(AgentChannelEnum.QUNAR_CHANNEL.value);
		thirdOrderBean.setPrice(totalPrice);
		thirdOrderBean.setPriceMark(pricemark);
		thirdOrderBean.setThirdTradeNo(qOrderId);
		thirdOrderBean.setThirdCarType(carType);
		
		OrderSubmitParam submitParam = new OrderSubmitParam();
		submitParam.setOrderBean(orderBean);
		submitParam.setThirdOrderBean(thirdOrderBean);
		submitParam.setSign(getSign());
		return submitParam;
	}

	/**
	 * @return the toAddrDetail
	 */
	public String getToAddrDetail() {
		return toAddrDetail;
	}

	/**
	 * @param toAddrDetail the toAddrDetail to set
	 */
	public void setToAddrDetail(String toAddrDetail) {
		this.toAddrDetail = toAddrDetail;
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
	 * @return the offlineCash
	 */
	public Double getOfflineCash() {
		return offlineCash;
	}

	/**
	 * @param offlineCash the offlineCash to set
	 */
	public void setOfflineCash(Double offlineCash) {
		this.offlineCash = offlineCash;
	}

	/**
	 * @return the flightStartTime
	 */
	public String getFlightStartTime() {
		return flightStartTime;
	}

	/**
	 * @param flightStartTime the flightStartTime to set
	 */
	public void setFlightStartTime(String flightStartTime) {
		this.flightStartTime = flightStartTime;
	}

	/**
	 * @return the depFlightTime
	 */
	public String getDepFlightTime() {
		return depFlightTime;
	}

	/**
	 * @param depFlightTime the depFlightTime to set
	 */
	public void setDepFlightTime(String depFlightTime) {
		this.depFlightTime = depFlightTime;
	}

	/**
	 * @return the depCityName
	 */
	public String getDepCityName() {
		return depCityName;
	}

	/**
	 * @param depCityName the depCityName to set
	 */
	public void setDepCityName(String depCityName) {
		this.depCityName = depCityName;
	}

	/**
	 * @return the depAirportThreeCode
	 */
	public String getDepAirportThreeCode() {
		return depAirportThreeCode;
	}

	/**
	 * @param depAirportThreeCode the depAirportThreeCode to set
	 */
	public void setDepAirportThreeCode(String depAirportThreeCode) {
		this.depAirportThreeCode = depAirportThreeCode;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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
	 * @return the appointment
	 */
	public String getAppointment() {
		return appointment;
	}

	/**
	 * @param appointment the appointment to set
	 */
	public void setAppointment(String appointment) {
		this.appointment = appointment;
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
	public Integer getEstimatedTime() {
		return estimatedTime;
	}

	/**
	 * @param estimatedTime the estimatedTime to set
	 */
	public void setEstimatedTime(Integer estimatedTime) {
		this.estimatedTime = estimatedTime;
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
	 * @return the fromLong
	 */
	public String getFromLong() {
		return fromLong;
	}

	/**
	 * @param fromLong the fromLong to set
	 */
	public void setFromLong(String fromLong) {
		this.fromLong = fromLong;
	}

	/**
	 * @return the fromLat
	 */
	public String getFromLat() {
		return fromLat;
	}

	/**
	 * @param fromLat the fromLat to set
	 */
	public void setFromLat(String fromLat) {
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
	 * @return the toAddrNameEn
	 */
	public String getToAddrNameEn() {
		return toAddrNameEn;
	}

	/**
	 * @param toAddrNameEn the toAddrNameEn to set
	 */
	public void setToAddrNameEn(String toAddrNameEn) {
		this.toAddrNameEn = toAddrNameEn;
	}

	/**
	 * @return the toLong
	 */
	public String getToLong() {
		return toLong;
	}

	/**
	 * @param toLong the toLong to set
	 */
	public void setToLong(String toLong) {
		this.toLong = toLong;
	}

	/**
	 * @return the toLat
	 */
	public String getToLat() {
		return toLat;
	}

	/**
	 * @param toLat the toLat to set
	 */
	public void setToLat(String toLat) {
		this.toLat = toLat;
	}

	/**
	 * @return the passengerInfo
	 */
	public QunarPassengerInfo getPassengerInfo() {
		return passengerInfo;
	}

	/**
	 * @param passengerInfo the passengerInfo to set
	 */
	public void setPassengerInfo(QunarPassengerInfo passengerInfo) {
		this.passengerInfo = passengerInfo;
	}

	/**
	 * @return the serviceInfo
	 */
	public QunarServiceInfo getServiceInfo() {
		return serviceInfo;
	}

	/**
	 * @param serviceInfo the serviceInfo to set
	 */
	public void setServiceInfo(QunarServiceInfo serviceInfo) {
		this.serviceInfo = serviceInfo;
	}


	/**
	 * @return the detail
	 */
	public QunarOrderSubmitDetail getDetail() {
		return detail;
	}

	/**
	 * @param detail the detail to set
	 */
	public void setDetail(QunarOrderSubmitDetail detail) {
		this.detail = detail;
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
	public Long getCreateTimeMs() {
		return createTimeMs;
	}

	/**
	 * @param createTimeMs the createTimeMs to set
	 */
	public void setCreateTimeMs(Long createTimeMs) {
		this.createTimeMs = createTimeMs;
	}

	/**
	 * @return the pricemark
	 */
	public String getPricemark() {
		return pricemark;
	}

	/**
	 * @param priceMark the priceMark to set
	 */
	public void setPricemark(String pricemark) {
		this.pricemark = pricemark;
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
	 * @return the bookingAgentMobileCode
	 */
	public String getBookingAgentMobileCode() {
		return bookingAgentMobileCode;
	}

	/**
	 * @param bookingAgentMobileCode the bookingAgentMobileCode to set
	 */
	public void setBookingAgentMobileCode(String bookingAgentMobileCode) {
		this.bookingAgentMobileCode = bookingAgentMobileCode;
	}

	/**
	 * @return the bookingAgentMobile
	 */
	public String getBookingAgentMobile() {
		return bookingAgentMobile;
	}

	/**
	 * @param bookingAgentMobile the bookingAgentMobile to set
	 */
	public void setBookingAgentMobile(String bookingAgentMobile) {
		this.bookingAgentMobile = bookingAgentMobile;
	}
}
