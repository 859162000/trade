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
package com.hbc.api.trade.order.service.gorder.rsp;

import java.util.Date;
import java.util.List;

import com.hbc.api.trade.order.mapping.gen.bean.TradeAdditionalDetail;
import com.hbc.api.trade.order.service.domain.CommendationOrder;
import com.hbc.api.trade.order.service.rsp.ChildSeat;
import com.hbc.api.trade.order.service.rsp.Coupon;
import com.hbc.api.trade.order.service.rsp.OrderTrack;
import com.hbc.api.trade.order.service.rsp.PassCity;
import com.hbc.api.trade.order.service.rsp.PriceInfo;
import com.hbc.api.trade.order.service.rsp.UserMobile;
import com.hbc.api.trade.third.vo.Appraisement;

/**
 * @author Jongly Ran
 */
public class GOrderDetailBean extends CommendationOrder{

    private String 	allocatGno;			// 主键
    private String 	orderNo; 			// 订单号	
    private Double 	priceGuide;			// 订单价格
    private Integer orderType;			// 订单类型。1-接机；2-送机；3-包车；4-次租；5-拼车
    private Integer orderStatus; 		// 订单状态。主流程状态：参考com/hbc/api/trade/order/enums/bean/OrderStatus.java
    private Integer deliverStatus; 		// 订单状态。分支状态：0 初始态 1 发单中 2 已发单 3 pk中 4 需要报警
    private Date 	serviceTime; 		// 服务开始时间,精确到秒 
    private Date 	serviceEndTime; 	// 服务结束时间,包车结束日期,精确到秒 
    private Integer serviceTimeDown; 	// 距离服务时间多少秒
    
    private String 	startAddress; 		// 出发地
    private String 	startAddressDetail; // 出发地详细地址
    private String 	serviceAreaCode;	// 服务酒店 区号类似与 86中国
    private String 	serviceAddressTel;	// 目的地酒店或者区域电话号码
    private String 	startAddressPoi; 	// 出发地经纬度
    private String 	destAddress; 		// 到达地
    private String 	destAddressDetail; 	// 到达地详细地址
    private String 	destAddressPoi; 	// 到达地经纬度
    
    private Double	distance; 			// 预估路程
    private Integer	expectedCompTime; 	// 预估从接到客户至到达目的地总耗时
    private String 	serviceCityId; 		// 包车开始城市
    private String 	serviceCityName; 	// 包车开始城市
    private String 	serviceEndCityname; // 包车结束城市
    private String 	serviceCountryId;   // 国家id
    
    private String 	userName;			// 客人名称
    private String 	userRemark;			// 乘客留言，备注 【2015-12-04 追加接机时的签证状态】
    private String 	userAvatar;			// 头像
    
    private String 	flightNo;			// 航班号
    private String  flightBrandSign;	// 接机牌姓名
	private String	flightAirportBuiding;
	private String	flightDestCode; 	// 降落机场三字码
	private String	flightDestName; 	// 降落机场名称
	private String	flightDestBuilding;	// 降落机场航站楼
    
    private Integer adultNum;			// 成人数量
    private Integer childNum;			// 儿童数量
    private Date 	userGetOnDate;		// 客户上车时间，从行程记录里查
    private Integer serialFlag;			// 0 正常订单 1表示串单 2表示被串单
    private Integer urgentFlag;			// 0 普通订单 1急单
    
    private Integer guideCommentStatus;	// 导游评论状态 评论状态 0 未评价 1 已评价
    private Integer userCommentStatus;	// 用户评论状态 评论状态 0 未评价 1 已评价
    private Date	cancelTime;			// 订单取消时间
    private Date	completeTime;		// 订单完成时间
    private String	carTypeAndSeatInfo;	// 车型（车类型<1-经济 2-舒适 3-豪华 4-奢华>+座位）
    private Integer isRead;				// 是否已读
    private Integer totalDays;			// 日租总天数

    private Appraisement appraisement;	// 客户评价车导
    private String serialOrderTip;		// 您好，此订单与您已接的${serviceTime}前往${destAddress}${orderType}订单顺路，优先派送给你。
	private Double checkInPrice=0.0; 	// 帮办理登机手续
    private Boolean hasNewOrderStatus;	// 是否有新订单动态
    private Boolean isMissed;			// 是否已错过， true 已错过

    private Double	overPrice;			// 增项费用
    private Integer	alreadyPay;			// 增项费用 是否已支付
    private PriceInfo priceInfo;		// 价格信息
    private Coupon	  coupon;			// 优惠信息
    private List<TradeAdditionalDetail> additionalCostDetails;
    
    private List<ChildSeat>  childSeats;	// 儿童座椅数
    private List<UserMobile> userMobiles; 	// 用户手机号
    private List<OrderTrack> orderTracks;	// 订单动态
    private List<PassCity> 	 passCities;	// 途径城市
    
	/**
	 * @return the serviceTimeDown
	 */
	public Integer getServiceTimeDown() {
		return serviceTimeDown;
	}
	/**
	 * @param serviceTimeDown the serviceTimeDown to set
	 */
	public void setServiceTimeDown(Integer serviceTimeDown) {
		this.serviceTimeDown = serviceTimeDown;
	}
	/**
	 * @return the serviceCountryId
	 */
	public String getServiceCountryId() {
		return serviceCountryId;
	}
	/**
	 * @param serviceCountryId the serviceCountryId to set
	 */
	public void setServiceCountryId(String serviceCountryId) {
		this.serviceCountryId = serviceCountryId;
	}
	/**
	 * @return the userCommentStatus
	 */
	public Integer getUserCommentStatus() {
		return userCommentStatus;
	}
	/**
	 * @param userCommentStatus the userCommentStatus to set
	 */
	public void setUserCommentStatus(Integer userCommentStatus) {
		this.userCommentStatus = userCommentStatus;
	}
	/**
	 * @return the userAvatar
	 */
	public String getUserAvatar() {
		return userAvatar;
	}
	/**
	 * @param userAvatar the userAvatar to set
	 */
	public void setUserAvatar(String userAvatar) {
		this.userAvatar = userAvatar;
	}
	/**
	 * @return the isMissed
	 */
	public Boolean getIsMissed() {
		return isMissed;
	}
	/**
	 * @param isMissed the isMissed to set
	 */
	public void setIsMissed(Boolean isMissed) {
		this.isMissed = isMissed;
	}
	/**
	 * @return the hasNewOrderStatus
	 */
	public Boolean getHasNewOrderStatus() {
		return hasNewOrderStatus;
	}
	/**
	 * @param hasNewOrderStatus the hasNewOrderStatus to set
	 */
	public void setHasNewOrderStatus(Boolean hasNewOrderStatus) {
		this.hasNewOrderStatus = hasNewOrderStatus;
	}
	/**
	 * @return the priceGuide
	 */
	public Double getPriceGuide() {
		return priceGuide;
	}
	/**
	 * @param priceGuide the priceGuide to set
	 */
	public void setPriceGuide(Double priceGuide) {
		this.priceGuide = priceGuide;
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
	 * @return the serviceCityId
	 */
	public String getServiceCityId() {
		return serviceCityId;
	}
	/**
	 * @param serviceCityId the serviceCityId to set
	 */
	public void setServiceCityId(String serviceCityId) {
		this.serviceCityId = serviceCityId;
	}
	/**
	 * @return the additionalCostDetails
	 */
	public List<TradeAdditionalDetail> getAdditionalCostDetails() {
		return additionalCostDetails;
	}
	/**
	 * @param additionalCostDetails the additionalCostDetails to set
	 */
	public void setAdditionalCostDetails(List<TradeAdditionalDetail> additionalCostDetails) {
		this.additionalCostDetails = additionalCostDetails;
	}
	/**
	 * @return the overPrice
	 */
	public Double getOverPrice() {
		return overPrice;
	}
	/**
	 * @param overPrice the overPrice to set
	 */
	public void setOverPrice(Double overPrice) {
		this.overPrice = overPrice;
	}
	/**
	 * @return the alreadyPay
	 */
	public Integer getAlreadyPay() {
		return alreadyPay;
	}
	/**
	 * @param alreadyPay the alreadyPay to set
	 */
	public void setAlreadyPay(Integer alreadyPay) {
		this.alreadyPay = alreadyPay;
	}
	/**
	 * @return the allocatGno
	 */
	public String getAllocatGno() {
		return allocatGno;
	}
	/**
	 * @param allocatGno the allocatGno to set
	 */
	public void setAllocatGno(String allocatGno) {
		this.allocatGno = allocatGno;
	}
	/**
	 * @return the checkInPrice
	 */
	public Double getCheckInPrice() {
		return checkInPrice;
	}
	/**
	 * @param checkInPrice the checkInPrice to set
	 */
	public void setCheckInPrice(Double checkInPrice) {
		this.checkInPrice = checkInPrice;
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
	 * @return the serialOrderTip
	 */
	public String getSerialOrderTip() {
		return serialOrderTip;
	}
	/**
	 * @param serialOrderTip the serialOrderTip to set
	 */
	public void setSerialOrderTip(String serialOrderTip) {
		this.serialOrderTip = serialOrderTip;
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
	 * @return the isRead
	 */
	public Integer getIsRead() {
		return isRead;
	}
	/**
	 * @param isRead the isRead to set
	 */
	public void setIsRead(Integer isRead) {
		this.isRead = isRead;
	}
	/**
	 * @return the carTypeAndSeatInfo
	 */
	public String getCarTypeAndSeatInfo() {
		return carTypeAndSeatInfo;
	}
	/**
	 * @param carTypeAndSeatInfo the carTypeAndSeatInfo to set
	 */
	public void setCarTypeAndSeatInfo(String carTypeAndSeatInfo) {
		this.carTypeAndSeatInfo = carTypeAndSeatInfo;
	}
	/**
	 * @return the guideCommentStatus
	 */
	public Integer getGuideCommentStatus() {
		return guideCommentStatus;
	}
	/**
	 * @param guideCommentStatus the guideCommentStatus to set
	 */
	public void setGuideCommentStatus(Integer guideCommentStatus) {
		this.guideCommentStatus = guideCommentStatus;
	}
	/**
	 * @return the cancelTime
	 */
	public Date getCancelTime() {
		return cancelTime;
	}
	/**
	 * @param cancelTime the cancelTime to set
	 */
	public void setCancelTime(Date cancelTime) {
		this.cancelTime = cancelTime;
	}
	/**
	 * @return the completeTime
	 */
	public Date getCompleteTime() {
		return completeTime;
	}
	/**
	 * @param completeTime the completeTime to set
	 */
	public void setCompleteTime(Date completeTime) {
		this.completeTime = completeTime;
	}
	/**
	 * @return the orderTracks
	 */
	public List<OrderTrack> getOrderTracks() {
		return orderTracks;
	}
	/**
	 * @param orderTracks the orderTracks to set
	 */
	public void setOrderTracks(List<OrderTrack> orderTracks) {
		this.orderTracks = orderTracks;
	}
	/**
	 * @return the serialFlag
	 */
	public Integer getSerialFlag() {
		return serialFlag;
	}
	/**
	 * @param serialFlag the serialFlag to set
	 */
	public void setSerialFlag(Integer serialFlag) {
		this.serialFlag = serialFlag;
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
	 * @return 订单状态。分支状态：0 初始态 1 发单中 2 已发单 3 pk中
	 */
	public Integer getDeliverStatus() {
		return deliverStatus;
	}
	/**
	 * @param deliverStatus 订单状态。分支状态：0 初始态 1 发单中 2 已发单 3 pk中
	 */
	public void setDeliverStatus(Integer deliverStatus) {
		this.deliverStatus = deliverStatus;
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
	 * @return 订单状态。主流程状态：参考com/hbc/api/trade/order/enums/bean/OrderStatus.java
	 */
	public Integer getOrderStatus() {
		return orderStatus;
	}
	/**
	 * @param orderStatus 订单状态。主流程状态：参考com/hbc/api/trade/order/enums/bean/OrderStatus.java
	 */
	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}
	/**
	 * @return 服务开始时间,精确到秒 
	 */
	public Date getServiceTime() {
		return serviceTime;
	}
	/**
	 * @param serviceTime 服务开始时间,精确到秒 
	 */
	public void setServiceTime(Date serviceTime) {
		this.serviceTime = serviceTime;
	}
	/**
	 * @return 服务结束时间,包车结束日期,精确到秒
	 */
	public Date getServiceEndTime() {
		return serviceEndTime;
	}
	/**
	 * @param serviceEndTime 服务结束时间,包车结束日期,精确到秒
	 */
	public void setServiceEndTime(Date serviceEndTime) {
		this.serviceEndTime = serviceEndTime;
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
	 * @return 出发地经纬度
	 */
	public String getStartAddressPoi() {
		return startAddressPoi;
	}
	/**
	 * @param startAddressPoi 出发地经纬度
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
	 * @return 到达地经纬度
	 */
	public String getDestAddressPoi() {
		return destAddressPoi;
	}
	/**
	 * @param destAddressPoi 到达地经纬度
	 */
	public void setDestAddressPoi(String destAddressPoi) {
		this.destAddressPoi = destAddressPoi;
	}
	/**
	 * @return the distance
	 */
	public Double getDistance() {
		return distance;
	}
	/**
	 * @param distance the distance to set
	 */
	public void setDistance(Double distance) {
		this.distance = distance;
	}
	/**
	 * @return 包车开始城市
	 */
	public String getServiceCityName() {
		return serviceCityName;
	}
	/**
	 * @param serviceCityName 包车开始城市
	 */
	public void setServiceCityName(String serviceCityName) {
		this.serviceCityName = serviceCityName;
	}
	/**
	 * @return 包车结束城市
	 */
	public String getServiceEndCityname() {
		return serviceEndCityname;
	}
	/**
	 * @param serviceEndCityname 包车结束城市
	 */
	public void setServiceEndCityname(String serviceEndCityname) {
		this.serviceEndCityname = serviceEndCityname;
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
	 * @return 乘客留言，备注 【2015-12-04 追加接机时的签证状态】
	 */
	public String getUserRemark() {
		return userRemark;
	}
	/**
	 * @param userRemark 乘客留言，备注 【2015-12-04 追加接机时的签证状态】
	 */
	public void setUserRemark(String userRemark) {
		this.userRemark = userRemark;
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
	 * @return 客户上车时间，从行程记录里查
	 */
	public Date getUserGetOnDate() {
		return userGetOnDate;
	}
	/**
	 * @param userGetOnDate 客户上车时间，从行程记录里查
	 */
	public void setUserGetOnDate(Date userGetOnDate) {
		this.userGetOnDate = userGetOnDate;
	}
	/**
	 * @return 儿童座椅数
	 */
	public List<ChildSeat> getChildSeats() {
		return childSeats;
	}
	/**
	 * @param childSeats 儿童座椅数
	 */
	public void setChildSeats(List<ChildSeat> childSeats) {
		this.childSeats = childSeats;
	}
	/**
	 * @return the userMobiles
	 */
	public List<UserMobile> getUserMobiles() {
		return userMobiles;
	}
	/**
	 * @param userMobiles the userMobiles to set
	 */
	public void setUserMobiles(List<UserMobile> userMobiles) {
		this.userMobiles = userMobiles;
	}
}
