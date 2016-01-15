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

/**
 * @author Jongly Ran
 */
public class OrderLogParamBean {
	private String	orderNo;
	private String	guideId;
	private String	guideName;
	private Integer	logType; 	// 日志类型
	private Integer	opType;		// 日志分类。1-代理商行为；2-用户行为；3-导游行为；4-客服行为；5-系统行为；6-地接社行为
	private String	content;	// 日志文本内容。根据type不同，文本内容不同。系统自动填写
	private String	comment;	// 客服或代理商主动填写的备注信息
	private String	value;		// 对应操作记录的值。
	private String	opUserId;	// 操作人ID
	private String	opUserName;	// 操作人姓名
	
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
	 * @return the logType
	 * 日志类型。<br/>
		1-提交订单；<br/>
		2-补充订单资料（包括部分内容修改）；<br/>
		3-付款；<br/>
		4-已确定导游（导游已接单）；<br/>
		5-导游已到达预定地点；<br/>
		6-导游已接到客人（开始出发）；<br/>
		7-已完成服务；<br/>
		8-导游评价客人；<br/>
		9-客人评价导游；<br/>
		10-确认费用；<br/>
		11-结算完成；<br/>
		98-运营确认分发；<br/><br/>
		
		101-客人（代理）取消订单；<br/>
		102-导游（运营）取消订单；<br/>
		103-手动添加备注信息；<br/>
		104-运营认领订单；<br/>
		105-运营确认退款（存储具体金额）；<br/>
		106-系统自动取消订单（60分钟未支付）；<br/>
		107-系统自动结算（60分钟）<br/><br/>
		
		201-运营增加订单奖金；<br/>
		202-运营派单；<br/>
		203-运营处理投诉；<br/>
		204-运营重置某个导游状态；<br/>
		205-运营处理（首次或修改）异动申请；<br/>
		206-运营增量补发<br/>
		207-运营取消（改派）导游<br/>
		208-运营修改订单金额<br/>
		209-运营修改订单评分<br/>
		210-运营执行注册航班操作<br/>
		211-运营取消重发<br/>
		212-运营帮助导游提交异动申请<br/>
		213-运营处罚导游<br/>
		214-运营活动奖励<br/><br/>
		
		601-地接社老板改派导游<br/><br/>
		
		
		301-导游提交异动申请；<br/><br/>
		
		200-系统AppPush通知；<br/>
		300-系统短信通知<br/><br/>
		
		400-用户支付异动费用<br/>
	 */
	public Integer getLogType() {
		return logType;
	}
	/**
	 * @param logType 
	 * 		1-提交订单；<br/>
		2-补充订单资料（包括部分内容修改）；<br/>
		3-付款；<br/>
		4-已确定导游（导游已接单）；<br/>
		5-导游已到达预定地点；<br/>
		6-导游已接到客人（开始出发）；<br/>
		7-已完成服务；<br/>
		8-导游评价客人；<br/>
		9-客人评价导游；<br/>
		10-确认费用；<br/>
		11-结算完成；<br/>
		98-运营确认分发；<br/><br/>
		
		101-客人（代理）取消订单；<br/>
		102-导游（运营）取消订单；<br/>
		103-手动添加备注信息；<br/>
		104-运营认领订单；<br/>
		105-运营确认退款（存储具体金额）；<br/>
		106-系统自动取消订单（60分钟未支付）；<br/>
		107-系统自动结算（60分钟）<br/><br/>
		
		201-运营增加订单奖金；<br/>
		202-运营派单；<br/>
		203-运营处理投诉；<br/>
		204-运营重置某个导游状态；<br/>
		205-运营处理（首次或修改）异动申请；<br/>
		206-运营增量补发<br/>
		207-运营取消（改派）导游<br/>
		208-运营修改订单金额<br/>
		209-运营修改订单评分<br/>
		210-运营执行注册航班操作<br/>
		211-运营取消重发<br/>
		212-运营帮助导游提交异动申请<br/>
		213-运营处罚导游<br/>
		214-运营活动奖励<br/><br/>
		601-地接社老板改派导游<br/><br/>
		301-导游提交异动申请；<br/><br/>
		
		200-系统AppPush通知；<br/>
		300-系统短信通知<br/><br/>
		
		400-用户支付异动费用<br/>
	 */
	public void setLogType(Integer logType) {
		this.logType = logType;
	}
	/**
	 * @return 日志分类。1-代理商行为；2-用户行为；3-导游行为；4-客服行为；5-系统行为；6-地接社行为
	 */
	public Integer getOpType() {
		return opType;
	}
	/**
	 * @param opType 日志分类。1-代理商行为；2-用户行为；3-导游行为；4-客服行为；5-系统行为；6-地接社行为
	 */
	public void setOpType(Integer opType) {
		this.opType = opType;
	}
	/**
	 * @return 日志文本内容。根据type不同，文本内容不同。系统自动填写
	 */
	public String getContent() {
		return content;
	}
	/**
	 * @param content 日志文本内容。根据type不同，文本内容不同。系统自动填写
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * @return 客服或代理商主动填写的备注信息
	 */
	public String getComment() {
		return comment;
	}
	/**
	 * @param comment 客服或代理商主动填写的备注信息
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}
	/**
	 * @return 对应操作记录的值。
	 */
	public String getValue() {
		return value;
	}
	/**
	 * @param value 对应操作记录的值。
	 */
	public void setValue(String value) {
		this.value = value;
	}
	/**
	 * @return the opUserId
	 */
	public String getOpUserId() {
		return opUserId;
	}
	/**
	 * @param opUserId the opUserId to set
	 */
	public void setOpUserId(String opUserId) {
		this.opUserId = opUserId;
	}
	/**
	 * @return the opUserName
	 */
	public String getOpUserName() {
		return opUserName;
	}
	/**
	 * @param opUserName the opUserName to set
	 */
	public void setOpUserName(String opUserName) {
		this.opUserName = opUserName;
	}
}
