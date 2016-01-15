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
package com.hbc.api.trade.third.vo;

/**
 * 客户评价车导
 * @author Jongly Ran
 */
public class Appraisement {
	private String  orderNo;
    private String	content;			// 评价信息
	private Double 	sceneryNarrate; 	// 景点介绍评价星级（1-5星）
	private Double 	serviceAttitude; 	// 服务态度评价星级（1-5星）
	private Double 	routeFamiliar; 		// 路线熟悉评价星级（1-5星）
	private Double  totalScore;			// 总星级
	private String  userCommentTime;	// 用户评价时间（精确到分，约定客户端处理）
	private String  guideCommentTime;	// 导游评价时间（精确到分，约定客户端处理）
	private String  systemCommentTime;	// 系统评价时间（精确到分，约定客户端处理）
	
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
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
	 * @return the totalScore
	 */
	public Double getTotalScore() {
		return totalScore;
	}
	/**
	 * @param totalScore the totalScore to set
	 */
	public void setTotalScore(Double totalScore) {
		this.totalScore = totalScore;
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
	 * @return the sceneryNarrate
	 */
	public Double getSceneryNarrate() {
		return sceneryNarrate;
	}
	/**
	 * @param sceneryNarrate the sceneryNarrate to set
	 */
	public void setSceneryNarrate(Double sceneryNarrate) {
		this.sceneryNarrate = sceneryNarrate;
	}
	/**
	 * @return the serviceAttitude
	 */
	public Double getServiceAttitude() {
		return serviceAttitude;
	}
	/**
	 * @param serviceAttitude the serviceAttitude to set
	 */
	public void setServiceAttitude(Double serviceAttitude) {
		this.serviceAttitude = serviceAttitude;
	}
	/**
	 * @return the routeFamiliar
	 */
	public Double getRouteFamiliar() {
		return routeFamiliar;
	}
	/**
	 * @param routeFamiliar the routeFamiliar to set
	 */
	public void setRouteFamiliar(Double routeFamiliar) {
		this.routeFamiliar = routeFamiliar;
	}
}
