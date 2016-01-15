package com.hbc.api.trade.ota.resp;

import java.sql.Timestamp;

public class OrderDetailResult {

	private int servicePartner ;
	private int orderStatus ;
	private String orderNo ;
	private String thirdTradeNo ;
	private String guideName ;
	private String guideAreaCode ;
	private String guideMobile ;
	private String guideCarName ;
	private String guideCarDesc ;
	private Timestamp guideConfirmTime ;

	public int getServicePartner() {
		return servicePartner;
	}
	public void setServicePartner(int servicePartner) {
		this.servicePartner = servicePartner;
	}
	public int getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(int orderStatus) {
		this.orderStatus = orderStatus;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getThirdTradeNo() {
		return thirdTradeNo;
	}
	public void setThirdTradeNo(String thirdTradeNo) {
		this.thirdTradeNo = thirdTradeNo;
	}
	public String getGuideName() {
		return guideName;
	}
	public void setGuideName(String guideName) {
		this.guideName = guideName;
	}
	public String getGuideAreaCode() {
		return guideAreaCode;
	}
	public void setGuideAreaCode(String guideAreaCode) {
		this.guideAreaCode = guideAreaCode;
	}
	public String getGuideMobile() {
		return guideMobile;
	}
	public void setGuideMobile(String guideMobile) {
		this.guideMobile = guideMobile;
	}
	public String getGuideCarName() {
		return guideCarName;
	}
	public void setGuideCarName(String guideCarName) {
		this.guideCarName = guideCarName;
	}
	public String getGuideCarDesc() {
		return guideCarDesc;
	}
	public void setGuideCarDesc(String guideCarDesc) {
		this.guideCarDesc = guideCarDesc;
	}
	public Timestamp getGuideConfirmTime() {
		return guideConfirmTime;
	}
	public void setGuideConfirmTime(Timestamp guideConfirmTime) {
		this.guideConfirmTime = guideConfirmTime;
	}
}
