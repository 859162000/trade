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
package com.hbc.api.trade.order.controller.opt.req;

import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotBlank;

/**
 * @author Jongly Ran
 */
public class GDSEditOrderParam {

    /* --------- 公用 ------------ */
	@NotBlank 
	private String  orderNo	;			// 目的地酒店或者区域电话号码
	private Integer orderType	;		// 1-接机；2-送机；3-日租；4-次租

	@Min(value=0, message="成人座位数不能小于0")
	private Integer 	adultNum;//	成人座位数
	
	@Min(value=0, message="小孩座位数不能小于0")
	private Integer 	childNum;//	小孩座位数
	private String childSeat;
	private String 	serviceAddressTel;	// 目的地酒店或者区域电话号码
	private String 	serviceAreaCode;	// 目的地区号
	private String 	userAreaCode1;
	private String 	userMobile1;
	
	private String	userAreaCode2;
	private String	userMobile2;
	private String	userAreaCode3;
	private String	userMobile3;
	private String userEmail;	//	客人email
	private String 	userRemark	;		// 客人备注
	private String 	userName;			// 客人名称
	private Integer isArrivalVisa;

    /* --------- 次租 ------------ */
	// 暂无额外字段需要更新
	
    /* --------- 日租 ------------ */
	
	private String serviceDate	;		// 服务时间[2015-10-03 20:02:34]必须为开始时间当天 只容许修改点数 日租
	private String serviceRecTime;		// 服务时间的时分秒
	private String serviceEndDate;		// 服务终止时间[2015-10-03]
	private String servicePassCitys; 	// 途径城市
    private String startAddress;
    /**
     *  日租 订单行程描述，线路包车（精品线路）备注
     *  所属表字段为`trade_order`.journey_comment
     */
    private String journeyComment;

    /* --------- 送机、接机 ------------ */

	private String	flightBrandSign;  	// 接机
	private String	flightNo;
	private String	flightAirportCode;
	private String	flightAirportName;
	private String  flightFlyTimeL;
	private String  flightArriveTimeL;
	private String	flightAirportBuiding;
    
	
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
	 * @return the userEmail
	 */
	public String getUserEmail() {
		return userEmail;
	}

	/**
	 * @param userEmail the userEmail to set
	 */
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
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
	 * @return the servicePassCitys
	 */
	public String getServicePassCitys() {
		return servicePassCitys;
	}

	/**
	 * @param servicePassCitys the servicePassCitys to set
	 */
	public void setServicePassCitys(String servicePassCitys) {
		this.servicePassCitys = servicePassCitys;
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
	 * @return the journeyComment
	 */
	public String getJourneyComment() {
		return journeyComment;
	}

	/**
	 * @param journeyComment the journeyComment to set
	 */
	public void setJourneyComment(String journeyComment) {
		this.journeyComment = journeyComment;
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
	 * @return the flightAirportName
	 */
	public String getFlightAirportName() {
		return flightAirportName;
	}

	/**
	 * @param flightAirportName the flightAirportName to set
	 */
	public void setFlightAirportName(String flightAirportName) {
		this.flightAirportName = flightAirportName;
	}

	/**
	 * @return the flightFlyTimeL
	 */
	public String getFlightFlyTimeL() {
		return flightFlyTimeL;
	}

	/**
	 * @param flightFlyTimeL the flightFlyTimeL to set
	 */
	public void setFlightFlyTimeL(String flightFlyTimeL) {
		this.flightFlyTimeL = flightFlyTimeL;
	}

	/**
	 * @return the flightArriveTimeL
	 */
	public String getFlightArriveTimeL() {
		return flightArriveTimeL;
	}

	/**
	 * @param flightArriveTimeL the flightArriveTimeL to set
	 */
	public void setFlightArriveTimeL(String flightArriveTimeL) {
		this.flightArriveTimeL = flightArriveTimeL;
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
	 * @return the serviceEndDate
	 */
	public String getServiceEndDate() {
		return serviceEndDate;
	}

	/**
	 * @param serviceEndDate the serviceEndDate to set
	 */
	public void setServiceEndDate(String serviceEndDate) {
		this.serviceEndDate = serviceEndDate;
	}

	/**
	 * @return the serviceDate
	 */
	public String getServiceDate() {
		return serviceDate;
	}

	/**
	 * @param serviceDate the serviceDate to set
	 */
	public void setServiceDate(String serviceDate) {
		this.serviceDate = serviceDate;
	}

	/**
     *  服务时间，服务开始时间  日租默认8点
     *  所属表字段为trade_order.service_time
     */
    private String serviceTimes;
    /**
     *  日租结束时间
     *  所属表字段为trade_order.service_end_time
     */
    private String serviceEndTimes;
    /**
     *  导游被指定时间
     *  所属表字段为trade_order.guide_assign_time
     */
    private String guideAssignTimes;
    /**
     *  航班计划起飞时间
     *  所属表字段为trade_order.flight_fly_time
     */
    private String flightFlyTimes;

    /**
     *  航班计划到达时间
     *  所属表字段为trade_order.flight_arrive_time
     */
    private String flightArriveTimes;
    /**
     *  订单取消时间
     *  所属表字段为trade_order.cancel_time
     */
    private String cancelTimes;

    /**
     *  订单完成时间
     *  所属表字段为trade_order.complete_time
     */
    private String completeTimes;

    /**
     *  下单时间
     *  所属表字段为trade_order.create_time
     */
    private String createTimes;
    

    /**
     *  
     *  所属表字段为trade_order.update_time
     */
    private String updateTimes;
    
    

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
	 * @return the serviceTimes
	 */
	public String getServiceTimes() {
		return serviceTimes;
	}

	/**
	 * @return the serviceEndTimes
	 */
	public String getServiceEndTimes() {
		return serviceEndTimes;
	}

	/**
	 * @param serviceEndTimes the serviceEndTimes to set
	 */
	public void setServiceEndTimes(String serviceEndTimes) {
		this.serviceEndTimes = serviceEndTimes;
	}

	/**
	 * @return the guideAssignTimes
	 */
	public String getGuideAssignTimes() {
		return guideAssignTimes;
	}

	/**
	 * @param guideAssignTimes the guideAssignTimes to set
	 */
	public void setGuideAssignTimes(String guideAssignTimes) {
		this.guideAssignTimes = guideAssignTimes;
	}

	/**
	 * @return the flightFlyTimes
	 */
	public String getFlightFlyTimes() {
		return flightFlyTimes;
	}

	/**
	 * @param flightFlyTimes the flightFlyTimes to set
	 */
	public void setFlightFlyTimes(String flightFlyTimes) {
		this.flightFlyTimes = flightFlyTimes;
	}

	/**
	 * @return the flightArriveTimes
	 */
	public String getFlightArriveTimes() {
		return flightArriveTimes;
	}

	/**
	 * @param flightArriveTimes the flightArriveTimes to set
	 */
	public void setFlightArriveTimes(String flightArriveTimes) {
		this.flightArriveTimes = flightArriveTimes;
	}

	/**
	 * @return the cancelTimes
	 */
	public String getCancelTimes() {
		return cancelTimes;
	}

	/**
	 * @param cancelTimes the cancelTimes to set
	 */
	public void setCancelTimes(String cancelTimes) {
		this.cancelTimes = cancelTimes;
	}

	/**
	 * @return the completeTimes
	 */
	public String getCompleteTimes() {
		return completeTimes;
	}

	/**
	 * @param completeTimes the completeTimes to set
	 */
	public void setCompleteTimes(String completeTimes) {
		this.completeTimes = completeTimes;
	}

	/**
	 * @return the createTimes
	 */
	public String getCreateTimes() {
		return createTimes;
	}

	/**
	 * @param createTimes the createTimes to set
	 */
	public void setCreateTimes(String createTimes) {
		this.createTimes = createTimes;
	}

	/**
	 * @return the updateTimes
	 */
	public String getUpdateTimes() {
		return updateTimes;
	}

	/**
	 * @param updateTimes the updateTimes to set
	 */
	public void setUpdateTimes(String updateTimes) {
		this.updateTimes = updateTimes;
	}

	/**
	 * @param serviceTimes the serviceTimes to set
	 */
	public void setServiceTimes(String serviceTimes) {
		this.serviceTimes = serviceTimes;
	}

}
