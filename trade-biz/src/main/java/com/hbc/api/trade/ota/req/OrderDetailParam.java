package com.hbc.api.trade.ota.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 
 * @author Jongly Ran
 */
public class OrderDetailParam{

	/*  
	 * Qua API. 
	 * Standard Query Parameters 
	 */
	@NotBlank private String thirdTradeNo ;
	@NotBlank private String sign ;
	
	private Integer servicePartner ;
	private String orderNo ;

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
	public Integer getServicePartner() {
		return servicePartner;
	}
	public void setServicePartner(Integer servicePartner) {
		this.servicePartner = servicePartner;
	}
	public String getThirdTradeNo() {
		return thirdTradeNo;
	}
	public void setThirdTradeNo(String thirdTradeNo) {
		this.thirdTradeNo = thirdTradeNo;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

}
