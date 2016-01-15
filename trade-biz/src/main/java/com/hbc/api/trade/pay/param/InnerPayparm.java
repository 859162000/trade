package com.hbc.api.trade.pay.param;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

public class InnerPayparm {
	@NotBlank(message="资金账户不能为空")
	private String accountNo;
	@NotNull(message="付款金额不能为空")
	private Double priceChannel;
	@NotBlank(message="订单号不能为空")
	private String orderNo;
	@NotBlank(message="用户ID不能为空")
	private String userId;
	@NotBlank(message="用户名不能为空")
	private String userName;
	
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	public Double getPriceChannel() {
		return priceChannel;
	}
	public void setPriceChannel(Double priceChannel) {
		this.priceChannel = priceChannel;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	
}
