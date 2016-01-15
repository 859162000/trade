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
package com.hbc.api.trade.ota.qua.req;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.hbc.api.trade.bdata.common.util.TimeConverter;
import com.hbc.api.trade.order.enums.order.OrderType;
import com.hbc.api.trade.order.enums.ota.AgentChannelEnum;
import com.hbc.api.trade.order.mapping.gen.bean.OrderBean;
import com.hbc.api.trade.ota.mapping.gen.bean.TradeThirdOrder;
import com.hbc.api.trade.ota.req.OrderSubmitParam;

/**
 * @author Jongly Ran
 */
public class QuaOrderSubmitParam {

	@NotNull private Integer orderType ;
	@NotNull private Integer carTypeId ;
	@NotNull private String  price;
	@NotNull private String  serviceTime ;
	@NotNull private Integer adultNum ;
	@NotNull private Integer childNum ;
	@NotNull private String  userAreaCode1 ;
	@NotNull private String  userMobile1 ;
	@NotNull private Integer expectedCompTime; // 接送机预计完成时间
    
	@NotBlank private String thirdTradeNo  ;
	@NotBlank private String startAddress ;
	@NotBlank private String startAddressDetail ;
	@NotBlank private String startAddressPoi ;
	@NotBlank private String destAddress ;
	@NotBlank private String destAddressDetail ;
	@NotBlank private String destAddressPoi ;
	@NotBlank private String userName ;
	@NotBlank private String sign ;
	@NotBlank private String flightAirportCode ; 
	@NotBlank private String priceMark ;
    @NotNull  private Integer servicePartner ;
	
	private Integer serviceCityId ;
    private String  agentName;			// 登录用户代理商名称
    private String  agentOpname;		// 操作员名称
    private Integer orderChannel;		// 渠道ID
    private String  promotionInfo;		// 优惠券信息
    
    /**
     * 2015-12-30废弃，用couponAmount代替
     */
    @Deprecated
	private String saleCode ; 			// 优惠券代码
    
    /**
     * 2015-12-30废弃，用couponAmount代替
     */
    @Deprecated
	private String salePrice ; 			// 优惠券金额
	
    /* 下列参数可不传递，为了兼容老版本留下 */
	private String serviceAreaCode ;
	private String serviceAddressTel ;
	private Integer serviceCountryId ;
	private String flightFlyTime ;
	private String flightNo ;
	private String distance ;
	private String childSeat ;
	private Integer luggageNum ;
	private String userAreaCode2 ;
	private String userMobile2 ;
	private String userAreaCode3 ;
	private String userMobile3 ;
	private String userRemark ;
	private Integer isArrivalVisa ;
	public Integer 	urgentFlag;					// 是否急单，来自查价
	

	public OrderSubmitParam toStandardSubmitParam() {
		OrderBean orderBean = new OrderBean();
		AgentChannelEnum agentChannel = AgentChannelEnum.getType(servicePartner);
		orderBean.setAgentId(agentChannel.value+"");
		orderBean.setAgentName(agentChannel.desc);
		orderBean.setAgentOpid(agentChannel.value+"");
		orderBean.setAgentOpname(agentChannel.desc);
		orderBean.setOrderChannel(agentChannel.value);
		orderBean.setAdultNum(getAdultNum());
		orderBean.setChildNum(childNum);
		orderBean.setServiceTime(TimeConverter.toDate(serviceTime) );
		orderBean.setUserAreaCode1(getUserAreaCode1());
		orderBean.setUserMobile1(getUserMobile1());
		orderBean.setExpectedCompTime(getExpectedCompTime());
		orderBean.setThirdTradeNo(getThirdTradeNo());
		orderBean.setStartAddress(getStartAddress());
		orderBean.setStartAddressDetail(getStartAddressDetail());
		orderBean.setStartAddressPoi(getStartAddressPoi());
		orderBean.setDestAddress(getDestAddress());
		orderBean.setDestAddressDetail(getDestAddressDetail());
		orderBean.setDestAddressPoi(getDestAddressPoi());
		orderBean.setUserName(getUserName());
		orderBean.setPriceMark(getPriceMark());
		orderBean.setPriceChannel(Double.valueOf(price));

		if(orderType == 1) {
			orderBean.setFlightDestCode(getFlightAirportCode());
			orderBean.setOrderType(OrderType.PICKUPORDER.value);
		} else {
			orderBean.setFlightAirportCode(getFlightAirportCode());
			orderBean.setOrderType(OrderType.TRANSFER.value);
		}
		orderBean.setUrgentFlag(urgentFlag);
		orderBean.setCarTypeId(carTypeId);

		TradeThirdOrder thirdOrderBean = new TradeThirdOrder();
		thirdOrderBean.setThirdPartner(agentChannel.value);
		thirdOrderBean.setPrice(Double.valueOf(price));
		thirdOrderBean.setPriceMark(priceMark);
		thirdOrderBean.setThirdTradeNo(thirdTradeNo);
		thirdOrderBean.setThirdCarType(carTypeId);
		
		OrderSubmitParam submitParam = new OrderSubmitParam();
		submitParam.setOrderBean(orderBean);
		submitParam.setThirdOrderBean(thirdOrderBean);
		submitParam.setSign(getSign());
		return submitParam;
	}
	
	
	/**
	 * @return the promotionInfo
	 */
	public String getPromotionInfo() {
		return promotionInfo;
	}

	/**
	 * @param promotionInfo the promotionInfo to set
	 */
	public void setPromotionInfo(String promotionInfo) {
		this.promotionInfo = promotionInfo;
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
	 * @return the serviceCityId
	 */
	public Integer getServiceCityId() {
		return serviceCityId;
	}
	/**
	 * @param serviceCityId the serviceCityId to set
	 */
	public void setServiceCityId(Integer serviceCityId) {
		this.serviceCityId = serviceCityId;
	}
	/**
	 * @return the carTypeId
	 */
	public Integer getCarTypeId() {
		return carTypeId;
	}
	/**
	 * @param carTypeId the carTypeId to set
	 */
	public void setCarTypeId(Integer carTypeId) {
		this.carTypeId = carTypeId;
	}
	/**
	 * @return the price
	 */
	public String getPrice() {
		return price;
	}
	/**
	 * @param price the price to set
	 */
	public void setPrice(String price) {
		this.price = price;
	}
	/**
	 * @return the serviceTime
	 */
	public String getServiceTime() {
		return serviceTime;
	}
	/**
	 * @param serviceTime the serviceTime to set
	 */
	public void setServiceTime(String serviceTime) {
		this.serviceTime = serviceTime;
	}
	/**
	 * @return the adultNum
	 */
	public Integer getAdultNum() {
		return adultNum;
	}
	/**
	 * @param adultNum the adultNum to set
	 */
	public void setAdultNum(Integer adultNum) {
		this.adultNum = adultNum;
	}
	/**
	 * @return the childNum
	 */
	public Integer getChildNum() {
		return childNum;
	}
	/**
	 * @param childNum the childNum to set
	 */
	public void setChildNum(Integer childNum) {
		this.childNum = childNum;
	}
	/**
	 * @return the userAreaCode1
	 */
	public String getUserAreaCode1() {
		return userAreaCode1;
	}
	/**
	 * @param userAreaCode1 the userAreaCode1 to set
	 */
	public void setUserAreaCode1(String userAreaCode1) {
		this.userAreaCode1 = userAreaCode1;
	}
	/**
	 * @return the userMobile1
	 */
	public String getUserMobile1() {
		return userMobile1;
	}
	/**
	 * @param userMobile1 the userMobile1 to set
	 */
	public void setUserMobile1(String userMobile1) {
		this.userMobile1 = userMobile1;
	}
	/**
	 * @return the expectedCompTime
	 */
	public Integer getExpectedCompTime() {
		return expectedCompTime;
	}
	/**
	 * @param expectedCompTime the expectedCompTime to set
	 */
	public void setExpectedCompTime(Integer expectedCompTime) {
		this.expectedCompTime = expectedCompTime;
	}
	/**
	 * @return the thirdTradeNo
	 */
	public String getThirdTradeNo() {
		return thirdTradeNo;
	}
	/**
	 * @param thirdTradeNo the thirdTradeNo to set
	 */
	public void setThirdTradeNo(String thirdTradeNo) {
		this.thirdTradeNo = thirdTradeNo;
	}
	/**
	 * @return the startAddress
	 */
	public String getStartAddress() {
		return startAddress;
	}
	/**
	 * @param startAddress the startAddress to set
	 */
	public void setStartAddress(String startAddress) {
		this.startAddress = startAddress;
	}
	/**
	 * @return the startAddressDetail
	 */
	public String getStartAddressDetail() {
		return startAddressDetail;
	}
	/**
	 * @param startAddressDetail the startAddressDetail to set
	 */
	public void setStartAddressDetail(String startAddressDetail) {
		this.startAddressDetail = startAddressDetail;
	}
	/**
	 * @return the startAddressPoi
	 */
	public String getStartAddressPoi() {
		return startAddressPoi;
	}
	/**
	 * @param startAddressPoi the startAddressPoi to set
	 */
	public void setStartAddressPoi(String startAddressPoi) {
		this.startAddressPoi = startAddressPoi;
	}
	/**
	 * @return the destAddress
	 */
	public String getDestAddress() {
		return destAddress;
	}
	/**
	 * @param destAddress the destAddress to set
	 */
	public void setDestAddress(String destAddress) {
		this.destAddress = destAddress;
	}
	/**
	 * @return the destAddressDetail
	 */
	public String getDestAddressDetail() {
		return destAddressDetail;
	}
	/**
	 * @param destAddressDetail the destAddressDetail to set
	 */
	public void setDestAddressDetail(String destAddressDetail) {
		this.destAddressDetail = destAddressDetail;
	}
	/**
	 * @return the destAddressPoi
	 */
	public String getDestAddressPoi() {
		return destAddressPoi;
	}
	/**
	 * @param destAddressPoi the destAddressPoi to set
	 */
	public void setDestAddressPoi(String destAddressPoi) {
		this.destAddressPoi = destAddressPoi;
	}
	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * @return the sign
	 */
	public String getSign() {
		return sign;
	}
	/**
	 * @param sign the sign to set
	 */
	public void setSign(String sign) {
		this.sign = sign;
	}
	/**
	 * @return the flightAirportCode
	 */
	public String getFlightAirportCode() {
		return flightAirportCode;
	}
	/**
	 * @param flightAirportCode the flightAirportCode to set
	 */
	public void setFlightAirportCode(String flightAirportCode) {
		this.flightAirportCode = flightAirportCode;
	}
	/**
	 * @return the priceMark
	 */
	public String getPriceMark() {
		return priceMark;
	}
	/**
	 * @param priceMark the priceMark to set
	 */
	public void setPriceMark(String priceMark) {
		this.priceMark = priceMark;
	}
	/**
	 * @return the agentName
	 */
	public String getAgentName() {
		return agentName;
	}
	/**
	 * @param agentName the agentName to set
	 */
	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}
	/**
	 * @return the agentOpname
	 */
	public String getAgentOpname() {
		return agentOpname;
	}
	/**
	 * @param agentOpname the agentOpname to set
	 */
	public void setAgentOpname(String agentOpname) {
		this.agentOpname = agentOpname;
	}
	/**
	 * @return the orderChannel
	 */
	public Integer getOrderChannel() {
		return orderChannel;
	}
	/**
	 * @param orderChannel the orderChannel to set
	 */
	public void setOrderChannel(Integer orderChannel) {
		this.orderChannel = orderChannel;
	}
	/**
	 * @return the servicePartner
	 */
	public Integer getServicePartner() {
		return servicePartner;
	}
	/**
	 * @param servicePartner the servicePartner to set
	 */
	public void setServicePartner(Integer servicePartner) {
		this.servicePartner = servicePartner;
	}
	/**
	 * @return the serviceAreaCode
	 */
	public String getServiceAreaCode() {
		return serviceAreaCode;
	}
	/**
	 * @param serviceAreaCode the serviceAreaCode to set
	 */
	public void setServiceAreaCode(String serviceAreaCode) {
		this.serviceAreaCode = serviceAreaCode;
	}
	/**
	 * @return the serviceAddressTel
	 */
	public String getServiceAddressTel() {
		return serviceAddressTel;
	}
	/**
	 * @param serviceAddressTel the serviceAddressTel to set
	 */
	public void setServiceAddressTel(String serviceAddressTel) {
		this.serviceAddressTel = serviceAddressTel;
	}
	/**
	 * @return the serviceCountryId
	 */
	public Integer getServiceCountryId() {
		return serviceCountryId;
	}
	/**
	 * @param serviceCountryId the serviceCountryId to set
	 */
	public void setServiceCountryId(Integer serviceCountryId) {
		this.serviceCountryId = serviceCountryId;
	}
	/**
	 * @return the flightFlyTime
	 */
	public String getFlightFlyTime() {
		return flightFlyTime;
	}
	/**
	 * @param flightFlyTime the flightFlyTime to set
	 */
	public void setFlightFlyTime(String flightFlyTime) {
		this.flightFlyTime = flightFlyTime;
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
	 * @return the childSeat
	 */
	public String getChildSeat() {
		return childSeat;
	}
	/**
	 * @param childSeat the childSeat to set
	 */
	public void setChildSeat(String childSeat) {
		this.childSeat = childSeat;
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
	 * @return the userAreaCode2
	 */
	public String getUserAreaCode2() {
		return userAreaCode2;
	}
	/**
	 * @param userAreaCode2 the userAreaCode2 to set
	 */
	public void setUserAreaCode2(String userAreaCode2) {
		this.userAreaCode2 = userAreaCode2;
	}
	/**
	 * @return the userMobile2
	 */
	public String getUserMobile2() {
		return userMobile2;
	}
	/**
	 * @param userMobile2 the userMobile2 to set
	 */
	public void setUserMobile2(String userMobile2) {
		this.userMobile2 = userMobile2;
	}
	/**
	 * @return the userAreaCode3
	 */
	public String getUserAreaCode3() {
		return userAreaCode3;
	}
	/**
	 * @param userAreaCode3 the userAreaCode3 to set
	 */
	public void setUserAreaCode3(String userAreaCode3) {
		this.userAreaCode3 = userAreaCode3;
	}
	/**
	 * @return the userMobile3
	 */
	public String getUserMobile3() {
		return userMobile3;
	}
	/**
	 * @param userMobile3 the userMobile3 to set
	 */
	public void setUserMobile3(String userMobile3) {
		this.userMobile3 = userMobile3;
	}
	/**
	 * @return the userRemark
	 */
	public String getUserRemark() {
		return userRemark;
	}
	/**
	 * @param userRemark the userRemark to set
	 */
	public void setUserRemark(String userRemark) {
		this.userRemark = userRemark;
	}
	/**
	 * @return the isArrivalVisa
	 */
	public Integer getIsArrivalVisa() {
		return isArrivalVisa;
	}
	/**
	 * @param isArrivalVisa the isArrivalVisa to set
	 */
	public void setIsArrivalVisa(Integer isArrivalVisa) {
		this.isArrivalVisa = isArrivalVisa;
	}
	/**
	 * @return the saleCode
	 */
	public String getSaleCode() {
		return saleCode;
	}
	/**
	 * @param saleCode the saleCode to set
	 */
	public void setSaleCode(String saleCode) {
		this.saleCode = saleCode;
	}
	/**
	 * @return the salePrice
	 */
	public String getSalePrice() {
		return salePrice;
	}
	/**
	 * @param salePrice the salePrice to set
	 */
	public void setSalePrice(String salePrice) {
		this.salePrice = salePrice;
	}
}
