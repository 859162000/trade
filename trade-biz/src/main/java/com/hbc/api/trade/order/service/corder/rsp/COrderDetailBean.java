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
package com.hbc.api.trade.order.service.corder.rsp;

import java.util.List;

import com.hbc.api.trade.order.service.domain.CommendationOrder;
import com.hbc.api.trade.order.service.rsp.Coupon;
import com.hbc.api.trade.order.service.rsp.GuideInfo;
import com.hbc.api.trade.order.service.rsp.PassCity;
import com.hbc.api.trade.order.service.rsp.PriceInfo;
import com.hbc.api.trade.third.vo.Appraisement;

/**
 * @author Jongly Ran
 */
public class COrderDetailBean extends CommendationOrder{
    private String 	orderNo;			// 订单号
    private Integer orderStatus;		// 订单状态
    private Integer orderType;			// 订单类型
    private String 	serviceTime;		// 服务开始时间
    private String 	serviceRecTime;		// 服务时间时分秒
    private String 	serviceEndTime;		// 服务结束时间（日租）
    
    private Integer serviceCityId;		// 服务城市ID
    private String 	serviceCityName;	// 服务城市
    private String 	serviceEndCityname;	// 服务结束城市
    private String 	startAddress;		// 开始地址
    private String 	destAddress;		// 结束地址
    private String 	startAddressDetail;	// 开始地址详情
    private String 	destAddressDetail;	// 结束地址详情
    private String 	serviceAreaCode;	// 服务酒店区号，例如：86，中国
    private String 	serviceAddressTel;	// 服务酒店或者区域电话号码
    private String 	startAddressPoi;	// 出发地经纬度

    private Integer adultNum;			// 成人数
    private Integer childNum;			// 儿童数
    private String 	userName;			// 联系人
    private String 	userAreaCode1;		// 区号1
    private String 	userMobile1;		// 手机1
    private String 	userAreaCode2;		// 区号2
    private String 	userMobile2;		// 手机2
    private String 	userAreaCode3;		// 区号3
    private String 	userMobile3;		// 手机3
    
    private Integer carTypeId;			// 经济 舒适 豪华 奢华
    private Integer carSeatNum;			// 车座数
    private String 	carDesc;			// 车况描述，如：经济5座
    private Integer serviceLocalDays;	// 日租市内天数
    private Integer serviceNonlocalDays;// 日租市外天数
    private Integer totalDays;			// 日租总天数
    
    private Double	overPrice;			// 增项费用
    private Integer	alreadyPay;			// 增项费用 是否已支付

	private Boolean canChat;			// 是否能聊天
    private String	imToken;			// 聊天token
    private Integer	imCount;			// 未读消息数
    
    private String	payDeadTime;		// 支付终结时间，订单未支付时有值
    private Boolean	alreadyTimeout;		// 支付时间已超时
    private String 	userRemark;			// 用户备注

    private String 	flightNo;			// 航班
    private String 	flightAirportCode;	// 机场ID
	private String	flightAirportBuiding;
	private String	flightDestCode; 	// 降落机场三字码
	private String	flightDestName; 	// 降落机场名称
	private String	flightDestBuilding;	// 降落机场航站楼
    private String 	flightBrandSign;	// 接机牌
    private Integer	isArrivalVisa;		// 是否为落地签证；0-否；1-是 
    
    private Boolean cancelable; 		// 是否能取消订单,必选
    private String 	cancelText; 		// 不可取消的文案提示,选填
    private Integer additionIsRead;		// 增项费用是否已读
	private Boolean refundable;			// 是否能退款.
	private String  cancelTip;			// 文案
    
    private GuideInfo guideInfo;		// 导游信息	
    private PriceInfo priceInfo;		// 价格信息
    private Appraisement appraisement;	// 客户评价车导
    private Coupon	  coupon;			// 优惠信息
    private List<PassCity> passCities;	// 途径城市
    private String childSeat;			//  规则：年龄范围-数量。例如：1-1,2-3 附：1:婴儿座椅(0-1岁) 2:幼儿座椅(1-4岁) 3:学童座椅(4-7岁) 4:儿童座椅(7-12岁)]
    
	/**
	 * @return the cancelTip
	 */
	public String getCancelTip() {
		return cancelTip;
	}
	/**
	 * @param cancelTip the cancelTip to set
	 */
	public void setCancelTip(String cancelTip) {
		this.cancelTip = cancelTip;
	}
	/**
	 * @return the flightAirportBuiding
	 */
	public String getFlightAirportBuiding() {
		return flightAirportBuiding;
	}
	/**
	 * @param flightAirportBuiding the flightAirportBuiding to set
	 */
	public void setFlightAirportBuiding(String flightAirportBuiding) {
		this.flightAirportBuiding = flightAirportBuiding;
	}
	/**
	 * @return the flightDestCode
	 */
	public String getFlightDestCode() {
		return flightDestCode;
	}
	/**
	 * @param flightDestCode the flightDestCode to set
	 */
	public void setFlightDestCode(String flightDestCode) {
		this.flightDestCode = flightDestCode;
	}
	/**
	 * @return the flightDestName
	 */
	public String getFlightDestName() {
		return flightDestName;
	}
	/**
	 * @param flightDestName the flightDestName to set
	 */
	public void setFlightDestName(String flightDestName) {
		this.flightDestName = flightDestName;
	}
	/**
	 * @return the flightDestBuilding
	 */
	public String getFlightDestBuilding() {
		return flightDestBuilding;
	}
	/**
	 * @param flightDestBuilding the flightDestBuilding to set
	 */
	public void setFlightDestBuilding(String flightDestBuilding) {
		this.flightDestBuilding = flightDestBuilding;
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
	 * @return the alreadyTimeout
	 */
	public Boolean getAlreadyTimeout() {
		return alreadyTimeout;
	}
	/**
	 * @param alreadyTimeout the alreadyTimeout to set
	 */
	public void setAlreadyTimeout(Boolean alreadyTimeout) {
		this.alreadyTimeout = alreadyTimeout;
	}
	/**
	 * @return the appraisement
	 */
	public Appraisement getAppraisement() {
		return appraisement;
	}
	/**
	 * @param appraisement the appraisement to set
	 */
	public void setAppraisement(Appraisement appraisement) {
		this.appraisement = appraisement;
	}
	/**
	 * @return the refundable
	 */
	public Boolean getRefundable() {
		return refundable;
	}
	/**
	 * @param refundable the refundable to set
	 */
	public void setRefundable(Boolean refundable) {
		this.refundable = refundable;
	}
	
	/**
	 * @return the additionIsRead
	 */
	public Integer getAdditionIsRead() {
		return additionIsRead;
	}
	/**
	 * @param additionIsRead the additionIsRead to set
	 */
	public void setAdditionIsRead(Integer additionIsRead) {
		this.additionIsRead = additionIsRead;
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
	 * @return the totalDays
	 */
	public Integer getTotalDays() {
		return totalDays;
	}
	/**
	 * @param totalDays the totalDays to set
	 */
	public void setTotalDays(Integer totalDays) {
		this.totalDays = totalDays;
	}
	/**
	 * @return the cancelable
	 */
	public Boolean getCancelable() {
		return cancelable;
	}
	/**
	 * @param cancelable the cancelable to set
	 */
	public void setCancelable(Boolean cancelable) {
		this.cancelable = cancelable;
	}
	/**
	 * @return the cancelText
	 */
	public String getCancelText() {
		return cancelText;
	}
	/**
	 * @param cancelText the cancelText to set
	 */
	public void setCancelText(String cancelText) {
		this.cancelText = cancelText;
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
	 * @return 0: 签证未确定; 1: 国内签证; 2: 落地签证. See VisaType enum
	 */
	public Integer getIsArrivalVisa() {
		return isArrivalVisa;
	}
	/**
	 * @param isArrivalVisa 0: 签证未确定; 1: 国内签证; 2: 落地签证. See VisaType enum
	 */
	public void setIsArrivalVisa(Integer isArrivalVisa) {
		this.isArrivalVisa = isArrivalVisa;
	}
	/**
	 * @return 目的地区号
	 */
	public String getServiceAreaCode() {
		return serviceAreaCode;
	}
	/**
	 * @param serviceAreaCode 目的地区号
	 */
	public void setServiceAreaCode(String serviceAreaCode) {
		this.serviceAreaCode = serviceAreaCode;
	}
	/**
	 * @return 目的地酒店或者区域电话号码
	 */
	public String getServiceAddressTel() {
		return serviceAddressTel;
	}
	/**
	 * @param serviceAddressTel 目的地酒店或者区域电话号码
	 */
	public void setServiceAddressTel(String serviceAddressTel) {
		this.serviceAddressTel = serviceAddressTel;
	}
	/**
	 * @return the flightBrandSign
	 */
	public String getFlightBrandSign() {
		return flightBrandSign;
	}
	/**
	 * @param flightBrandSign the flightBrandSign to set
	 */
	public void setFlightBrandSign(String flightBrandSign) {
		this.flightBrandSign = flightBrandSign;
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
	 * @return the passCities
	 */
	public List<PassCity> getPassCities() {
		return passCities;
	}
	/**
	 * @param passCities the passCities to set
	 */
	public void setPassCities(List<PassCity> passCities) {
		this.passCities = passCities;
	}
	/**
	 * @return the orderNo
	 */
	public String getOrderNo() {
		return orderNo;
	}
	/**
	 * @param orderNo the orderNo to set
	 */
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	/**
	 * @return the orderStatus
	 */
	public Integer getOrderStatus() {
		return orderStatus;
	}
	/**
	 * @param orderStatus the orderStatus to set
	 */
	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
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
	 * @return the serviceEndTime
	 */
	public String getServiceEndTime() {
		return serviceEndTime;
	}
	/**
	 * @param serviceEndTime the serviceEndTime to set
	 */
	public void setServiceEndTime(String serviceEndTime) {
		this.serviceEndTime = serviceEndTime;
	}
	/**
	 * @return the serviceCityName
	 */
	public String getServiceCityName() {
		return serviceCityName;
	}
	/**
	 * @param serviceCityName the serviceCityName to set
	 */
	public void setServiceCityName(String serviceCityName) {
		this.serviceCityName = serviceCityName;
	}
	/**
	 * @return the serviceEndCityname
	 */
	public String getServiceEndCityname() {
		return serviceEndCityname;
	}
	/**
	 * @param serviceEndCityname the serviceEndCityname to set
	 */
	public void setServiceEndCityname(String serviceEndCityname) {
		this.serviceEndCityname = serviceEndCityname;
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
	 * @return the carSeatNum
	 */
	public Integer getCarSeatNum() {
		return carSeatNum;
	}
	/**
	 * @param carSeatNum the carSeatNum to set
	 */
	public void setCarSeatNum(Integer carSeatNum) {
		this.carSeatNum = carSeatNum;
	}
	/**
	 * @return the carDesc
	 */
	public String getCarDesc() {
		return carDesc;
	}
	/**
	 * @param carDesc the carDesc to set
	 */
	public void setCarDesc(String carDesc) {
		this.carDesc = carDesc;
	}
	/**
	 * @return the serviceLocalDays
	 */
	public Integer getServiceLocalDays() {
		return serviceLocalDays;
	}
	/**
	 * @param serviceLocalDays the serviceLocalDays to set
	 */
	public void setServiceLocalDays(Integer serviceLocalDays) {
		this.serviceLocalDays = serviceLocalDays;
	}
	/**
	 * @return the serviceNonlocalDays
	 */
	public Integer getServiceNonlocalDays() {
		return serviceNonlocalDays;
	}
	/**
	 * @param serviceNonlocalDays the serviceNonlocalDays to set
	 */
	public void setServiceNonlocalDays(Integer serviceNonlocalDays) {
		this.serviceNonlocalDays = serviceNonlocalDays;
	}
	/**
	 * @return the serviceRecTime
	 */
	public String getServiceRecTime() {
		return serviceRecTime;
	}
	/**
	 * @param serviceRecTime the serviceRecTime to set
	 */
	public void setServiceRecTime(String serviceRecTime) {
		this.serviceRecTime = serviceRecTime;
	}
	/**
	 * @return 增项费用
	 */
	public Double getOverPrice() {
		return overPrice;
	}
	/**
	 * @param overPrice 增项费用
	 */
	public void setOverPrice(Double overPrice) {
		this.overPrice = overPrice;
	}
	/**
	 * @return 增项费用 是否已支付
	 */
	public Integer getAlreadyPay() {
		return alreadyPay;
	}
	/**
	 * @param alreadyPay 增项费用 是否已支付
	 */
	public void setAlreadyPay(Integer alreadyPay) {
		this.alreadyPay = alreadyPay;
	}
	/**
	 * @return the canChat
	 */
	public Boolean getCanChat() {
		return canChat;
	}
	/**
	 * @param canChat the canChat to set
	 */
	public void setCanChat(Boolean canChat) {
		this.canChat = canChat;
	}
	/**
	 * @return 聊天token
	 */
	public String getImToken() {
		return imToken;
	}
	/**
	 * @param imToken 聊天token
	 */
	public void setImToken(String imToken) {
		this.imToken = imToken;
	}
	/**
	 * @return the imCount
	 */
	public Integer getImCount() {
		return imCount;
	}
	/**
	 * @param imCount the imCount to set
	 */
	public void setImCount(Integer imCount) {
		this.imCount = imCount;
	}
	/**
	 * @return the guideInfo
	 */
	public GuideInfo getGuideInfo() {
		return guideInfo;
	}
	/**
	 * @param guideInfo the guideInfo to set
	 */
	public void setGuideInfo(GuideInfo guideInfo) {
		this.guideInfo = guideInfo;
	}
	/**
	 * @return the priceInfo
	 */
	public PriceInfo getPriceInfo() {
		return priceInfo;
	}
	/**
	 * @param priceInfo the priceInfo to set
	 */
	public void setPriceInfo(PriceInfo priceInfo) {
		this.priceInfo = priceInfo;
	}
	/**
	 * @return the coupon
	 */
	public Coupon getCoupon() {
		return coupon;
	}
	/**
	 * @param coupon the coupon to set
	 */
	public void setCoupon(Coupon coupon) {
		this.coupon = coupon;
	}
	public String getPayDeadTime() {
		return payDeadTime;
	}
	public void setPayDeadTime(String payDeadTime) {
		this.payDeadTime = payDeadTime;
	}
	
}
