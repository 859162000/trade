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

import com.hbc.api.trade.order.service.domain.CommendationOrder;

/**
 * @author Jongly Ran
 */
public class GOrderBean extends CommendationOrder {
    /* -------公用--------- */
	
    private String 	orderNo; 			// 订单号	
    private Integer orderType;			// 订单类型。1-接机；2-送机；3-包车；4-次租；5-拼车
    private Integer orderStatus; 		// 订单状态。主流程状态：参考com/hbc/api/trade/order/enums/bean/OrderStatus.java
    private Integer deliverStatus; 		// 订单状态。0 初始态 1 发单中 2已接单
    private Date 	serviceTime; 		// 服务开始时间,精确到秒 
    private Date 	serviceEndTime; 	// 服务结束时间,精确到秒 
    private Integer serviceTimeDown; 	// 距离服务时间多少秒
    
    private String 	startAddress; 		// 出发地
    private String 	destAddress; 		// 到达地
    private String 	serviceCityName; 	// 包车开始城市
    private String 	serviceEndCityname; // 包车结束城市
    private Integer	journeyNum; 		// 新行程动态数量
    private Integer serialFlag = 0;			// 0 正常订单 1表示串单 2表示被串单
    private Integer urgentFlag = 0;			// 0 普通订单 1急单

    private Boolean canChat = false;	// 是否能聊天
    private Integer	imCount = 0;		// 未读消息数
    
    /* -- 待分配订单列表（泰国地接社）-- */
    // 暂无额外字段
    
    /* -------取消订单--------- */

    private Date	cancelTime;		// 订单取消时间，精确到秒
    
    /* -------已错过订单--------- */
    
    private Date 	guideDealTime;		// 其他导游抢单时间，即订单被分配到导游的时间，精确到秒
    
    /* -------新增订单--------- */

    private Integer totalDays;			// 日租总天数
    private Integer	customNum; 			// 拼单客人数	（成人+儿童）
    private Double 	priceGuide;			// 订单价格
    private Double	distance; 			// 预估路程
    private Integer	isRead; 			// 是否已读。0-未读；1-已读
    private Date 	deliverEndTime; 	// 距离派单结束的时间.
    
    /* -------完成订单--------- */
    
    private Integer guideCommentStatus;	// 导游评论状态 评论状态 0 未评价 1 已评价
    private Integer userCommentStatus;	// 用户评论状态 评论状态 0 未评价 1 已评价
    private Date	completeTime;		// 订单完成时间
	private String  userCommentTime;	// 用户评价时间（精确到分，约定客户端处理）
	private String  guideCommentTime;	// 导游评价时间（精确到分，约定客户端处理）
	private String  systemCommentTime;	// 系统评价时间（精确到分，约定客户端处理）

    /* -------发单--------- */
    private String 	allocateGid; 		// 导游订单可见关联Id
    
	public String getUserCommentTime() {
		return userCommentTime;
	}
	public void setUserCommentTime(String userCommentTime) {
		this.userCommentTime = userCommentTime;
	}
	public String getGuideCommentTime() {
		return guideCommentTime;
	}
	public void setGuideCommentTime(String guideCommentTime) {
		this.guideCommentTime = guideCommentTime;
	}
	public String getSystemCommentTime() {
		return systemCommentTime;
	}
	public void setSystemCommentTime(String systemCommentTime) {
		this.systemCommentTime = systemCommentTime;
	}
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
	public Double getPriceGuide() {
		return priceGuide;
	}
	public void setPriceGuide(Double priceGuide) {
		this.priceGuide = priceGuide;
	}
	/**
	 * @return the allocateGid
	 */
	public String getAllocateGid() {
		return allocateGid;
	}
	/**
	 * @param allocateGid the allocateGid to set
	 */
	public void setAllocateGid(String allocateGid) {
		this.allocateGid = allocateGid;
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
	 * @return 导游评论状态 评论状态 0 未评价 1 已评价
	 */
	public Integer getGuideCommentStatus() {
		return guideCommentStatus;
	}
	/**
	 * @param guideCommentStatus 导游评论状态 评论状态 0 未评价 1 已评价
	 */
	public void setGuideCommentStatus(Integer guideCommentStatus) {
		this.guideCommentStatus = guideCommentStatus;
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
	 * @return  订单状态。分支状态：0 初始态 1 发单中 2 已发单 3 pk中 4 需要报警
	 */
	public Integer getDeliverStatus() {
		return deliverStatus;
	}
	/**
	 * @param deliverStatus  订单状态。分支状态：0 初始态 1 发单中 2 已发单 3 pk中 4 需要报警
	 */
	public void setDeliverStatus(Integer deliverStatus) {
		this.deliverStatus = deliverStatus;
	}
	/**
	 * @return 其他导游抢单时间，即订单被分配到导游的时间，精确到秒
	 */
	public Date getGuideDealTime() {
		return guideDealTime;
	}
	/**
	 * @param guideDealTime 其他导游抢单时间，即订单被分配到导游的时间，精确到秒
	 */
	public void setGuideDealTime(Date guideDealTime) {
		this.guideDealTime = guideDealTime;
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
	 * @return 订单类型。1-接机；2-送机；3-包车；4-次租；5-拼车
	 */
	public Integer getOrderType() {
		return orderType;
	}
	/**
	 * @param orderType 订单类型。1-接机；2-送机；3-包车；4-次租；5-拼车
	 */
	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}
	/**
	 * @return 订单状态。主流程状态：参考 {@link com.hbc.api.trade.order.enums.bean.OrderStatus} 
	 */
	public Integer getOrderStatus() {
		return orderStatus;
	}
	/**
	 * @param orderStatus 订单状态。主流程状态：参考. {@link com.hbc.api.trade.order.enums.bean.OrderStatus}
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
	 * @return 服务结束时间,精确到秒
	 */
	public Date getServiceEndTime() {
		return serviceEndTime;
	}
	/**
	 * @param serviceEndTime 服务结束时间,精确到秒
	 */
	public void setServiceEndTime(Date serviceEndTime) {
		this.serviceEndTime = serviceEndTime;
	}
	/**
	 * @return 出发地
	 */
	public String getStartAddress() {
		return startAddress;
	}
	/**
	 * @param startAddress 出发地
	 */
	public void setStartAddress(String startAddress) {
		this.startAddress = startAddress;
	}
	/**
	 * @return 到达地
	 */
	public String getDestAddress() {
		return destAddress;
	}
	/**
	 * @param destAddress 到达地
	 */
	public void setDestAddress(String destAddress) {
		this.destAddress = destAddress;
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
	 * @return 新行程动态数量
	 */
	public Integer getJourneyNum() {
		return journeyNum;
	}
	/**
	 * @param journeyNum 新行程动态数量
	 */
	public void setJourneyNum(Integer journeyNum) {
		this.journeyNum = journeyNum;
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
	 * @return the customNum
	 */
	public Integer getCustomNum() {
		return customNum;
	}
	/**
	 * @param customNum the customNum to set
	 */
	public void setCustomNum(Integer customNum) {
		this.customNum = customNum;
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
	 * @return 是否已读。0-未读；1-已读
	 */
	public Integer getIsRead() {
		return isRead;
	}
	/**
	 * @param isRead 是否已读。0-未读；1-已读
	 */
	public void setIsRead(Integer isRead) {
		this.isRead = isRead;
	}
	/**
	 * @return the deliverEndTime
	 */
	public Date getDeliverEndTime() {
		return deliverEndTime;
	}
	/**
	 * @param deliverEndTime the deliverEndTime to set
	 */
	public void setDeliverEndTime(Date deliverEndTime) {
		this.deliverEndTime = deliverEndTime;
	}
}
