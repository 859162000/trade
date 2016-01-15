/**
 * @Author lukangle
 * @2015年10月17日@上午11:34:57
 */
package com.hbc.api.trade.pay.controller.req;

import org.hibernate.validator.constraints.NotBlank;

public class AliRefundPayParam {
	@NotBlank private String orderNo;
	@NotBlank private String reason;
	
	
	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
}
