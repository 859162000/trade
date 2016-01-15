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
public class QuaCommonParam {
	private String method; 			//	String	是	API接口名称。
	private String app_key; 		//	String	是	TOP分配给应用的AppKey。
	private String session; 		//	String	否	用户登录授权成功后，TOP颁发给应用的授权信息，详细介绍请点击这里。当此API的标签上注明：“需要授权”，则此参数必传；“不需要授权”，则此参数不需要传；“可选授权”，则此参数为可选。
	private String timestamp; 		//	String	是	时间戳，格式为yyyy-MM-dd HH:mm:ss，时区为GMT+8，例如：2015-01-01 12:00:00。淘宝API服务端允许客户端请求最大时间误差为10分钟。
	private String format; 			//	String	否	响应格式。默认为xml格式，可选值：xml，json。
	private String v; 				//	String	是	API协议版本，可选值：2.0。
	private String partner_id; 		//	String	否	合作伙伴身份标识。
	private String target_app_key; 	//	String	否	被调用的目标AppKey，仅当被调用的API为第三方ISV提供时有效。
	private Boolean simplify; 		//	Boolean	否	是否采用精简JSON返回格式，仅当format=json时有效，默认值为：false。
	private String sign_method;	 	//	String	是	签名的摘要算法，可选值为：hmac，md5。
	private String sign; 			//	String	是	API输入参数签名结果，签名算法介绍请点击这里。
	/**
	 * @return the method
	 */
	public String getMethod() {
		return method;
	}
	/**
	 * @param method the method to set
	 */
	public void setMethod(String method) {
		this.method = method;
	}
	/**
	 * @return the app_key
	 */
	public String getApp_key() {
		return app_key;
	}
	/**
	 * @param app_key the app_key to set
	 */
	public void setApp_key(String app_key) {
		this.app_key = app_key;
	}
	/**
	 * @return the session
	 */
	public String getSession() {
		return session;
	}
	/**
	 * @param session the session to set
	 */
	public void setSession(String session) {
		this.session = session;
	}
	/**
	 * @return the timestamp
	 */
	public String getTimestamp() {
		return timestamp;
	}
	/**
	 * @param timestamp the timestamp to set
	 */
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	/**
	 * @return the format
	 */
	public String getFormat() {
		return format;
	}
	/**
	 * @param format the format to set
	 */
	public void setFormat(String format) {
		this.format = format;
	}
	/**
	 * @return the v
	 */
	public String getV() {
		return v;
	}
	/**
	 * @param v the v to set
	 */
	public void setV(String v) {
		this.v = v;
	}
	/**
	 * @return the partner_id
	 */
	public String getPartner_id() {
		return partner_id;
	}
	/**
	 * @param partner_id the partner_id to set
	 */
	public void setPartner_id(String partner_id) {
		this.partner_id = partner_id;
	}
	/**
	 * @return the target_app_key
	 */
	public String getTarget_app_key() {
		return target_app_key;
	}
	/**
	 * @param target_app_key the target_app_key to set
	 */
	public void setTarget_app_key(String target_app_key) {
		this.target_app_key = target_app_key;
	}
	/**
	 * @return the simplify
	 */
	public Boolean getSimplify() {
		return simplify;
	}
	/**
	 * @param simplify the simplify to set
	 */
	public void setSimplify(Boolean simplify) {
		this.simplify = simplify;
	}
	/**
	 * @return the sign_method
	 */
	public String getSign_method() {
		return sign_method;
	}
	/**
	 * @param sign_method the sign_method to set
	 */
	public void setSign_method(String sign_method) {
		this.sign_method = sign_method;
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
}
