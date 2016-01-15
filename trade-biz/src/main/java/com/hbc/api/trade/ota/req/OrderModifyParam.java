package com.hbc.api.trade.ota.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 
 * @author Jongly Ran
 */
public class OrderModifyParam {

	/*  
	 * Qua API. 
	 * Standard Query Parameters 
	 */
	@NotBlank private String thirdTradeNo ;
	@NotBlank private String sign ;
	
	private String orderNo ;
	private String userName ;
	private String userAreaCode1 ;
	private String userMobile1 ;
	private String userAreaCode2 ;
	private String userMobile2 ;
	private String userAreaCode3 ;
	private String userMobile3 ;
	private String userRemark ;
	private Integer isArrivalVisa ;
	private Integer servicePartner ;
	
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
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserAreaCode1() {
		return userAreaCode1;
	}
	public void setUserAreaCode1(String userAreaCode1) {
		this.userAreaCode1 = userAreaCode1;
	}
	public String getUserMobile1() {
		return userMobile1;
	}
	public void setUserMobile1(String userMobile1) {
		this.userMobile1 = userMobile1;
	}
	public String getUserAreaCode2() {
		return userAreaCode2;
	}
	public void setUserAreaCode2(String userAreaCode2) {
		this.userAreaCode2 = userAreaCode2;
	}
	public String getUserMobile2() {
		return userMobile2;
	}
	public void setUserMobile2(String userMobile2) {
		this.userMobile2 = userMobile2;
	}
	public String getUserAreaCode3() {
		return userAreaCode3;
	}
	public void setUserAreaCode3(String userAreaCode3) {
		this.userAreaCode3 = userAreaCode3;
	}
	public String getUserMobile3() {
		return userMobile3;
	}
	public void setUserMobile3(String userMobile3) {
		this.userMobile3 = userMobile3;
	}
	public String getUserRemark() {
		return userRemark;
	}
	public void setUserRemark(String userRemark) {
		this.userRemark = userRemark;
	}
	public Integer getIsArrivalVisa() {
		return isArrivalVisa;
	}
	public void setIsArrivalVisa(Integer isArrivalVisa) {
		this.isArrivalVisa = isArrivalVisa;
	}

}
