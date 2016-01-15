package com.hbc.api.gateway.controller.req;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

public class AliMobilePayReqUrlParam {
	@NotBlank private String appId;//客户端号 	String
	@NotBlank private String appEnv;
	@NotBlank private String payNo;
	@NotNull private Double priceChannel;
	public Double getPriceChannel() {
		return priceChannel;
	}
	public void setPriceChannel(Double priceChannel) {
		this.priceChannel = priceChannel;
	}
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getAppEnv() {
		return appEnv;
	}
	public void setAppEnv(String appEnv) {
		this.appEnv = appEnv;
	}
	public String getPayNo() {
		return payNo;
	}
	public void setPayNo(String payNo) {
		this.payNo = payNo;
	}
}
