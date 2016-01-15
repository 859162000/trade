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
package com.hbc.api.trade.fund.req;

import java.io.Serializable;

/**
 * @author LuoShuai
 *
 */
public class GFundWithdrawHistory implements Serializable {

	private static final long serialVersionUID = -99476826234833049L;

	private String accountLogNo; //流水号
	private String bizComment; //交易状态提示
	private String content; //账单名称
	private String createTime; //交易时间
	private String orderNo; //订单号
	private String price; //交易价格

	//	private String acountNo; //资金账号
	//	private String historyNo; //流水号
	//
	//	private String finAccountNo; //提现账户
	//	private String finAccountName; //银行户主姓名
	//	private String finBankNo; //银行账号
	//	private String finBankName;//银行名
	//	private String userName; //客人姓名
	//
	//	private String transactionDateDesc; //交易时间
	//	private Integer transactionStatus; //交易状态
	//	private String transactionStatusName; //交易状态中文
	//	private Double transactionAmount; //交易金额
	//
	//	private Integer serviceType; //服务类型枚举
	//	private String serviceTypeName; //服务类型
	//	private Integer serviceStatus; //服务状态
	//	private String serviceStatusName; //服务状态中文
	//
	//	//包车
	//	private String serviceLocation; //地点 城市
	//	private String serviceDailyDateDesc; //包车天数 //2015.2月10日-15日
	//	private String serviceDailyDesc; //服务描述 //城内4天跨城市1天
	//
	//	private String serviceTransDateDesc; //接送机时间 /2014.12.29日 周五 14:30 
	//	private String serviceStartAddress; //起点 首都机场
	//	private String serviceEndAddress; //终点 建外SOHO

	public String getAccountLogNo() {
		return accountLogNo;
	}

	public void setAccountLogNo(String accountLogNo) {
		this.accountLogNo = accountLogNo;
	}

	public String getBizComment() {
		return bizComment;
	}

	public void setBizComment(String bizComment) {
		this.bizComment = bizComment;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "GFundWithdrawHistory [accountLogNo=" + accountLogNo + ", bizComment=" + bizComment + ", content=" + content + ", createTime=" + createTime + ", orderNo=" + orderNo + ", price=" + price + "]";
	}

}
