package com.hbc.api.trade.ota.req ;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

/**
 * @author Jongly Ran
 */
public class CalculatePriceParam {

	/*  
	 * Common. 
	 */
    @NotBlank private String sign;
    
	/*  
	 * Qua API. 
	 * Standard Query Parameters 
	 */
	@NotBlank private String flightAirportCode 	;
	@NotBlank private String serviceTime 		;
	@NotBlank private String startAddressPoi 	;
	@NotBlank private String destAddressPoi 	;
	@NotNull  private Integer orderType 		; // 1 接机，2送机
    
	
	private Integer servicePartner 	;	// 渠道合作id(固定值)

	private Double 	distance ;			// 去哪儿用 	是	起始和终止之间的距离（米）	Integer	37000
    private String  estimatedTime;  	// 去哪儿用 	是	预计耗时(参考)，分钟	Integer	30
    private String carTypeId ;
	
	/* 
	 * 以下是StandardAPI老版本用的，现在非必须
	 * v1.0.2
	 * Jongly Ran
	 * 2015-11-29
	 */
	private Integer serviceCountryId ;
	private Integer serviceCityId ;
	private String 	startAddress ;
	private String 	startAddressDetail ;
	private String 	destAddress ;
	private String 	destAddressDetail ;
	private Integer serviceAreaCode ;
	private Integer serviceAddressTel ;
	private String 	flightFlyTime ;
	private String 	flightNo ;
	private Integer adultNum ;
	private Integer childNum ;

	/**
	 * @return the estimatedTime
	 */
	public String getEstimatedTime() {
		return estimatedTime;
	}
	/**
	 * @param estimatedTime the estimatedTime to set
	 */
	public void setEstimatedTime(String estimatedTime) {
		this.estimatedTime = estimatedTime;
	}
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
	public Integer getOrderType() {
		return orderType;
	}
	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}
	public Integer getServiceCountryId() {
		return serviceCountryId;
	}
	public void setServiceCountryId(Integer serviceCountryId) {
		this.serviceCountryId = serviceCountryId;
	}
	public Integer getServiceCityId() {
		return serviceCityId;
	}
	public void setServiceCityId(Integer serviceCityId) {
		this.serviceCityId = serviceCityId;
	}
	public String getCarTypeId() {
		return carTypeId;
	}
	public void setCarTypeId(String carTypeId) {
		this.carTypeId = carTypeId;
	}
	public String getStartAddress() {
		return startAddress;
	}
	public void setStartAddress(String startAddress) {
		this.startAddress = startAddress;
	}
	public String getStartAddressDetail() {
		return startAddressDetail;
	}
	public void setStartAddressDetail(String startAddressDetail) {
		this.startAddressDetail = startAddressDetail;
	}
	public String getStartAddressPoi() {
		return startAddressPoi;
	}
	public void setStartAddressPoi(String startAddressPoi) {
		this.startAddressPoi = startAddressPoi;
	}
	public String getDestAddress() {
		return destAddress;
	}
	public void setDestAddress(String destAddress) {
		this.destAddress = destAddress;
	}
	public String getDestAddressDetail() {
		return destAddressDetail;
	}
	public void setDestAddressDetail(String destAddressDetail) {
		this.destAddressDetail = destAddressDetail;
	}
	public String getDestAddressPoi() {
		return destAddressPoi;
	}
	public void setDestAddressPoi(String destAddressPoi) {
		this.destAddressPoi = destAddressPoi;
	}
	public Integer getServiceAreaCode() {
		return serviceAreaCode;
	}
	public void setServiceAreaCode(Integer serviceAreaCode) {
		this.serviceAreaCode = serviceAreaCode;
	}
	public Integer getServiceAddressTel() {
		return serviceAddressTel;
	}
	public void setServiceAddressTel(Integer serviceAddressTel) {
		this.serviceAddressTel = serviceAddressTel;
	}
	public String getServiceTime() {
		return serviceTime;
	}
	public void setServiceTime(String serviceTime) {
		this.serviceTime = serviceTime;
	}
	public String getFlightFlyTime() {
		return flightFlyTime;
	}
	public void setFlightFlyTime(String flightFlyTime) {
		this.flightFlyTime = flightFlyTime;
	}
	public String getFlightNo() {
		return flightNo;
	}
	public void setFlightNo(String flightNo) {
		this.flightNo = flightNo;
	}
	public String getFlightAirportCode() {
		return flightAirportCode;
	}
	public void setFlightAirportCode(String flightAirportCode) {
		this.flightAirportCode = flightAirportCode;
	}
	public Double getDistance() {
		return distance;
	}
	public void setDistance(Double distance) {
		this.distance = distance;
	}
	public Integer getAdultNum() {
		return adultNum;
	}
	public void setAdultNum(Integer adultNum) {
		this.adultNum = adultNum;
	}
	public Integer getChildNum() {
		return childNum;
	}
	public void setChildNum(Integer childNum) {
		this.childNum = childNum;
	}

}