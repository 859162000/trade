/**
 * @Author lukangle
 * @2015年10月7日@下午5:13:41
 */
package com.hbc.api.trade.order.controller.opt.req;

import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotBlank;

public class CEditOrderParam {

    /* --------- 公用 ------------ */
	@NotBlank 
	private String  orderNo	;			// 目的地酒店或者区域电话号码
	private Integer orderType	;		// 1-接机；2-送机；3-日租；4-次租

	@Min(value=0, message="成人座位数不能小于0")
	private Integer 	adultNum;//	成人座位数
	
	@Min(value=0, message="小孩座位数不能小于0")
	private Integer 	childNum;//	小孩座位数
	private String childSeat;
	private String 	serviceAddressTel;	// 目的地酒店或者区域电话号码
	private String 	serviceAreaCode;	// 目的地区号
	private String 	userAreaCode1;
	private String 	userMobile1;
	
	private String	userAreaCode2;
	private String	userMobile2;
	private String	userAreaCode3;
	private String	userMobile3;
	private String userEmail;	//	客人email
	private String 	userRemark	;		// 客人备注
	private String 	userName;			// 客人名称
	private Integer isArrivalVisa;

    /* --------- 次租 ------------ */
	// 暂无额外字段需要更新
	
    /* --------- 日租 ------------ */
	
	private String serviceDate	;		// 服务时间[2015-10-03 20:02:34]必须为开始时间当天 只容许修改点数 日租
	private String serviceRecTime;		// 服务时间的时分秒
	private String servicePassCitys; 	// 途径城市
    private String startAddress;

    /* --------- 送机、接机 ------------ */
    
	private String	flightBrandSign;  	// 接机
	private String	flightNo;
	private String	flightAirportCode;
	private String	flightAirportName;
	private String  flightFlyTimeL;
	private String  flightArriveTimeL;
	private String	flightAirportBuiding;

	
	public String getChildSeat() {
		return childSeat;
	}
	public void setChildSeat(String childSeat) {
		this.childSeat = childSeat;
	}
	/**
	 * @return the userEmail
	 */
	public String getUserEmail() {
		return userEmail;
	}
	/**
	 * @param userEmail the userEmail to set
	 */
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	/**
	 * @return the servicePassCitys
	 */
	public String getServicePassCitys() {
		return servicePassCitys;
	}
	/**
	 * @param servicePassCitys the servicePassCitys to set
	 */
	public void setServicePassCitys(String servicePassCitys) {
		this.servicePassCitys = servicePassCitys;
	}
	/**
	 * @return the startAddress
	 */
	public String getStartAddress() {
		return startAddress;
	}
	/**
	 * @param startAddress the startAddress to set
	 */
	public void setStartAddress(String startAddress) {
		this.startAddress = startAddress;
	}
	/**
	 * @return the serviceDate
	 */
	public String getServiceDate() {
		return serviceDate;
	}
	/**
	 * @param serviceDate the serviceDate to set
	 */
	public void setServiceDate(String serviceDate) {
		this.serviceDate = serviceDate;
	}
	/**
	 * @return the serviceRecTime
	 */
	public String getServiceRecTime() {
		return serviceRecTime;
	}
	/**
	 * @param serviceRecTime the serviceRecTime to set
	 */
	public void setServiceRecTime(String serviceRecTime) {
		this.serviceRecTime = serviceRecTime;
	}
	/**
	 * @return the flightBrandSign
	 */
	public String getFlightBrandSign() {
		return flightBrandSign;
	}
	/**
	 * @param flightBrandSign the flightBrandSign to set
	 */
	public void setFlightBrandSign(String flightBrandSign) {
		this.flightBrandSign = flightBrandSign;
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
	public String getFlightAirportName() {
		return flightAirportName;
	}
	public void setFlightAirportName(String flightAirportName) {
		this.flightAirportName = flightAirportName;
	}
	public String getFlightFlyTimeL() {
		return flightFlyTimeL;
	}
	public void setFlightFlyTimeL(String flightFlyTimeL) {
		this.flightFlyTimeL = flightFlyTimeL;
	}
	public String getFlightArriveTimeL() {
		return flightArriveTimeL;
	}
	public void setFlightArriveTimeL(String flightArriveTimeL) {
		this.flightArriveTimeL = flightArriveTimeL;
	}
	public String getFlightAirportBuiding() {
		return flightAirportBuiding;
	}
	public void setFlightAirportBuiding(String flightAirportBuiding) {
		this.flightAirportBuiding = flightAirportBuiding;
	}
	public Integer getAdultNum() {
		return adultNum;
	}
	public void setAdultNum(Integer adultNum) {
		this.adultNum = adultNum;
	}
	public String getUserAreaCode1() {
		return userAreaCode1;
	}
	public void setUserAreaCode1(String userAreaCode1) {
		this.userAreaCode1 = userAreaCode1;
	}
	public String getUserAreaCode2() {
		return userAreaCode2;
	}
	public void setUserAreaCode2(String userAreaCode2) {
		this.userAreaCode2 = userAreaCode2;
	}
	public String getUserAreaCode3() {
		return userAreaCode3;
	}
	public void setUserAreaCode3(String userAreaCode3) {
		this.userAreaCode3 = userAreaCode3;
	}
	public Integer getOrderType() {
		return orderType;
	}
	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}
	public Integer getChildNum() {
		return childNum;
	}
	public void setChildNum(Integer childNum) {
		this.childNum = childNum;
	}
	public String getServiceAddressTel() {
		return serviceAddressTel;
	}
	public void setServiceAddressTel(String serviceAddressTel) {
		this.serviceAddressTel = serviceAddressTel;
	}
	public String getServiceAreaCode() {
		return serviceAreaCode;
	}
	public void setServiceAreaCode(String serviceAreaCode) {
		this.serviceAreaCode = serviceAreaCode;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserMobile1() {
		return userMobile1;
	}
	public void setUserMobile1(String userMobile1) {
		this.userMobile1 = userMobile1;
	}
	public String getUserMobile2() {
		return userMobile2;
	}
	public void setUserMobile2(String userMobile2) {
		this.userMobile2 = userMobile2;
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
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	/**
	 * @return 0: 签证未确定; 1: 国内签证; 2: 落地签证. See VisaType enum.
	 */
	public Integer getIsArrivalVisa() {
		return isArrivalVisa;
	}
	/**
	 * @param isArrivalVisa 0: 签证未确定; 1: 国内签证; 2: 落地签证. See VisaType enum
	 */
	public void setIsArrivalVisa(Integer isArrivalVisa) {
		this.isArrivalVisa = isArrivalVisa;
	}
	
}
