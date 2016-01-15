/**
 * @Author lukangle
 * @2015年10月16日@上午11:35:20
 */
package com.hbc.api.trade.pay.controller.req;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

public class AliWebPayReqUrlParam {
	@NotBlank private String orderNo;
	@NotNull private Double actualPrice;
	private String coupId;
	@NotBlank private String userId;
	
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
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
