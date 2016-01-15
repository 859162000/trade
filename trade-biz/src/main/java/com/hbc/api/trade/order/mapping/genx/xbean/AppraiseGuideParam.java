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
package com.hbc.api.trade.order.mapping.genx.xbean;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

/**
 * @author Jongly Ran
 */
public class AppraiseGuideParam {
	@NotBlank(message="订单编号不能为空")
	private String orderNo; 					// 订单号
	
	@NotBlank(message="评价人ID不能为空")
	private String fromUid; 					// 评价人ID
	
	private String fromUname; 					// 评价人Name
	
	@NotBlank(message="导游ID不能为空")
	private String guideId; 					// 导游ID
	
	private String guideName; 					// 导游Name

	@NotNull(message="景点介绍评价星级不能为空")
	private Float sceneryNarrate; 				// 景点介绍评价星级（1-5星）
	
	@NotNull(message="服务态度评价星级不能为空")
	private Float serviceAttitude; 				// 服务态度评价星级（1-5星）
	
	@NotNull(message="线路熟悉评价星级不能为空")
	private Float routeFamiliar; 				// 路线熟悉评价星级（1-5星）
	
	private String content; 					// 评价内容
	private Integer orderType; 					// 订单类型，1接机2送机3日租4次租5精品路线
	private Integer commentType; 				// 类型。1.-客人评价导游；2-运营评价导游
	
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
	 * @return the content
	 */
	public String getContent() {
		return content;
	}
	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * @return the fromUid
	 */
	public String getFromUid() {
		return fromUid;
	}
	/**
	 * @param fromUid the fromUid to set
	 */
	public void setFromUid(String fromUid) {
		this.fromUid = fromUid;
	}
	/**
	 * @return the fromUname
	 */
	public String getFromUname() {
		return fromUname;
	}
	/**
	 * @param fromUname the fromUname to set
	 */
	public void setFromUname(String fromUname) {
		this.fromUname = fromUname;
	}
	/**
	 * @return the guideId
	 */
	public String getGuideId() {
		return guideId;
	}
	/**
	 * @param guideId the guideId to set
	 */
	public void setGuideId(String guideId) {
		this.guideId = guideId;
	}
	/**
	 * @return the guideName
	 */
	public String getGuideName() {
		return guideName;
	}
	/**
	 * @param guideName the guideName to set
	 */
	public void setGuideName(String guideName) {
		this.guideName = guideName;
	}
	/**
	 * @return the sceneryNarrate
	 */
	public Float getSceneryNarrate() {
		return sceneryNarrate;
	}
	/**
	 * @param sceneryNarrate the sceneryNarrate to set
	 */
	public void setSceneryNarrate(Float sceneryNarrate) {
		this.sceneryNarrate = sceneryNarrate;
	}
	/**
	 * @return the serviceAttitude
	 */
	public Float getServiceAttitude() {
		return serviceAttitude;
	}
	/**
	 * @param serviceAttitude the serviceAttitude to set
	 */
	public void setServiceAttitude(Float serviceAttitude) {
		this.serviceAttitude = serviceAttitude;
	}
	/**
	 * @return the routeFamiliar
	 */
	public Float getRouteFamiliar() {
		return routeFamiliar;
	}
	/**
	 * @param routeFamiliar the routeFamiliar to set
	 */
	public void setRouteFamiliar(Float routeFamiliar) {
		this.routeFamiliar = routeFamiliar;
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
	 * @return the commentType
	 */
	public Integer getCommentType() {
		return commentType;
	}
	/**
	 * @param commentType the commentType to set
	 */
	public void setCommentType(Integer commentType) {
		this.commentType = commentType;
	}
}
