/**
 * @Author lukangle
 * @2015年10月17日@上午10:12:41
 */
package com.hbc.api.trade.pay.controller.req;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

public class AliMobilePayReqUrlParam {
	@NotBlank private String appId;//客户端号 	String
	@NotBlank private String appEnv;
	@NotBlank private String orderNo;
	@NotNull private Double actualPrice;
	private String coupId;
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
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public Double getActualPrice() {
		return actualPrice;
	}
	public void setActualPrice(Double actualPrice) {
		this.actualPrice = actualPrice;
	}
	public String getCoupId() {
		return coupId;
	}
	public void setCoupId(String coupId) {
		this.coupId = coupId;
	}
	
}
