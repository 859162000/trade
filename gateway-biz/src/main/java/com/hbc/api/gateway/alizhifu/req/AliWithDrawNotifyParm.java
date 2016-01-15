/*
 * COPYRIGHT (C) 2015-2016,LUOSHUAI. ALL RIGHTS RESERVED.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files
 * (the “Software”), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, 
 * publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, 
 * subject to the following conditions: 
 *
 *   a).The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software!
 *   b).Any individual or entity would be granted by LUOSHUAI before using this Software!
 *  
 * Please contact through email luoshuai@live.com if you need additional informations OR have any questions.
 *
 * THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT
 * LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. 
 * IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, 
 * WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE 
 * SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 * Author: Luoshuai 
 * Revision: 1.0
 * 
 *  
 */
package com.hbc.api.gateway.alizhifu.req;

import java.io.Serializable;

/**
 * @author LuoShuai
 *
 */
public class AliWithDrawNotifyParm implements Serializable {

	private static final long serialVersionUID = -8432068495739066546L;

	private String notify_time;//	通知时间 	Date 	通知发送的时间。格式为：yyyy-MM-dd HH:mm:ss。 	不可空 	2009-08-12 11:08:32
	private String notify_type;//	通知类型 	String 	通知的类型。 	不可空 	batch_refund_notify
	private String notify_id;//通知校验ID 	String 	通知校验ID。 	不可空 	70fec0c2730b27528665af4517c27b95
	private String sign_type;//	签名方式 	String 	DSA、RSA、MD5三个值可选，必须大写。 	不可空 	MD5
	private String sign;//签名 	String 	请参见安全接入与验证。 	不可空 	b7baf9af3c91b37bef4261849aa76281
	private String batch_no;//	退款批次号 	String 	原请求退款批次号。 	不可空 	20060702001
	private String pay_user_id; //付款账号ID  付款的支付宝账号对应的支付宝唯一用户号。 以2088开头的16位纯数字组成。 
	private String pay_user_name; //付款账号姓名 
	private String pay_account_no; //付款账号 
	private String success_details; //转账成功的详细信息 
	private String fail_details; //转账失败的详细信息 

	public String getNotify_time() {
		return notify_time;
	}

	public void setNotify_time(String notify_time) {
		this.notify_time = notify_time;
	}

	public String getNotify_type() {
		return notify_type;
	}

	public void setNotify_type(String notify_type) {
		this.notify_type = notify_type;
	}

	public String getNotify_id() {
		return notify_id;
	}

	public void setNotify_id(String notify_id) {
		this.notify_id = notify_id;
	}

	public String getSign_type() {
		return sign_type;
	}

	public void setSign_type(String sign_type) {
		this.sign_type = sign_type;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getBatch_no() {
		return batch_no;
	}

	public void setBatch_no(String batch_no) {
		this.batch_no = batch_no;
	}

	public String getPay_user_id() {
		return pay_user_id;
	}

	public void setPay_user_id(String pay_user_id) {
		this.pay_user_id = pay_user_id;
	}

	public String getPay_user_name() {
		return pay_user_name;
	}

	public void setPay_user_name(String pay_user_name) {
		this.pay_user_name = pay_user_name;
	}

	public String getPay_account_no() {
		return pay_account_no;
	}

	public void setPay_account_no(String pay_account_no) {
		this.pay_account_no = pay_account_no;
	}

	public String getSuccess_details() {
		return success_details;
	}

	public void setSuccess_details(String success_details) {
		this.success_details = success_details;
	}

	public String getFail_details() {
		return fail_details;
	}

	public void setFail_details(String fail_details) {
		this.fail_details = fail_details;
	}

	@Override
	public String toString() {
		return "AliRefundNotifyParam [notify_time=" + notify_time + ", notify_type=" + notify_type + ", notify_id=" + notify_id + ", sign_type=" + sign_type + ", sign=" + sign + ", batch_no=" + batch_no + ", pay_user_id=" + pay_user_id
				+ ", pay_user_name=" + pay_user_name + ", pay_account_no=" + pay_account_no + ", success_details=" + success_details + ", fail_details=" + fail_details + "]";
	}

}
