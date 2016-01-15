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
package com.hbc.api.trade.timer.service.ota.req;

/**
 * @author Jongly Ran
 */
public class QunarCallbackParam {
	private String orderId; 			// 去哪orderId	String	“gjc14110992025879304”
    private String sign;				// API输入参数签名结果 TODO v2.0后必须
    private String vendorkey;
    private String version;				// API版本号	String	1.0
	private String channel;				// 渠道合作id(固定值) TODO v2.0 后必须, 对应我们servicePartner
	private Long   timestamp;			// 时间戳 :1436500063741,
	private Integer ttmComfirmType; 	//	是	对应确认步骤的状态	Integer	车辆确认 0 未确认 1 车辆确认 2 司机确认',-1 完成订单
	
	/**
	 * @return 车辆确认 0 未确认 1 车辆确认 2 司机确认',-1 完成订单
	 */
	public Integer getTtmComfirmType() {
		return ttmComfirmType;
	}
	/**
	 * @param ttmComfirmType 车辆确认 0 未确认 1 车辆确认 2 司机确认',-1 完成订单
	 */
	public void setTtmComfirmType(Integer ttmComfirmType) {
		this.ttmComfirmType = ttmComfirmType;
	}
	/**
	 * @return the orderId
	 */
	public String getOrderId() {
		return orderId;
	}
	/**
	 * @param orderId the orderId to set
	 */
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	/**
	 * @return the timestamp
	 */
	public Long getTimestamp() {
		return timestamp;
	}
	/**
	 * @param timestamp the timestamp to set
	 */
	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
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
	 * @return the vendorkey
	 */
	public String getVendorkey() {
		return vendorkey;
	}
	/**
	 * @param vendorkey the vendorkey to set
	 */
	public void setVendorkey(String vendorkey) {
		this.vendorkey = vendorkey;
	}
	/**
	 * @return the version
	 */
	public String getVersion() {
		return version;
	}
	/**
	 * @param version the version to set
	 */
	public void setVersion(String version) {
		this.version = version;
	}
	/**
	 * @return the channel
	 */
	public String getChannel() {
		return channel;
	}
	/**
	 * @param channel the channel to set
	 */
	public void setChannel(String channel) {
		this.channel = channel;
	}
}
