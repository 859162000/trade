package com.hbc.api.trade.order.controller.pickup.req;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.hbc.api.trade.order.controller.commendation.GDSOrderParam;


public class PickUpOrderParam extends GDSOrderParam{

	@Min(value=0)
	private Double 	priceChannel;					//	C端价格
	private Double 	priceTicket;		//	票面价
	@NotBlank private String	priceMark;			//	询价系统返回ID
	
	@Min(value=0, message="成人座位数不能小于0")
	private Integer 	adultNum;					//	成人座位数
	
	@Min(value=0, message="小孩座位数不能小于0")
	private Integer 	childNum;					//	小孩座位数
	
	@Min(value=0, message="车座数必须不小于0")
	private Byte 	carSeatNum;						//	车座数
	@NotBlank private String	flightDestCode; 	// 降落机场三字码
	@NotBlank private String	flightDestName; 	// 降落机场名称
	@NotBlank private String	flightNo;
	@NotBlank private String 	flightArriveTimeL;
	@NotNull private Integer 	serviceCityId;		//	服务地城市ID
	@NotBlank private String	serviceTimeL;		//	服务时间[2015-10-03 20:02:34]
	@NotBlank private String	startAddressPoi;	//	出发地poi 
	@NotBlank private String	destAddress;		//	目的地address
	@NotBlank private String 	destAddressDetail;	//	目的地address详情
	@NotBlank private String	destAddressPoi;		//	目的地经纬度
	@NotNull private Double		distance;			//	服务距离
	@NotNull private Integer 	carTypeId;			//	1 经济 2舒适 3豪华 4奢华
	@NotBlank private String	carDesc;
	@NotBlank private String	userAreaCode1;
	@NotBlank private String	userMobile1;
	@NotNull private Integer	isArrivalVisa;
	@NotNull  private Integer 	expectedCompTime;	// 预计服务完成时间 接送次 必填
	@NotNull private Integer 	urgentFlag;			// 急单标识
	@NotBlank private String	userName;			// 联系人名
	@NotNull private Integer 	orderChannel;		// call: AgentChannelEnum

	private String 	startAddress; 
	private String 	serviceRecTime;
	private String	userId;
	private String	userAreaCode2;
	private String	userMobile2;
	private String	userAreaCode3;
	private String	userMobile3;
	private String 	userEmail;			//	客人email
	private String	userRemark;
	
	private String	childSeat;			//	每个小孩的年龄以逗号分开 1-1,2-1,3-2 ｛1类型的小孩坐1个｝
	private String	serviceAddressTel;	//	目的地酒店或者区域电话号码
	private String	serviceAreaCode;	//	目的地区号
	private String	flightAirportCode; 	// 起飞机场三字码
	private String 	flightFlyTimeL;					
	private String	flightAirportName;
	private Integer	flightIsCustom;		// 0正常，1自定义，需要MIS注册航班
	private String	flightAirportBuiding;
	private String	flightDestBuilding;	// 降落机场航站楼
	private String 	flightBrandSign;	// 接机牌 存在机场禁止举接机牌，因此不是必填  
	
	public Double getPriceTicket() {
		return priceTicket;
	}
	public void setPriceTicket(Double priceTicket) {
		this.priceTicket = priceTicket;
	}
	public void setUrgentFlag(Integer urgentFlag) {
		this.urgentFlag = urgentFlag;
	}
	/**
	 * @return the flightDestName
	 */
	public String getFlightDestName() {
		return flightDestName;
	}
	/**
	 * @param flightDestName the flightDestName to set
	 */
	public void setFlightDestName(String flightDestName) {
		this.flightDestName = flightDestName;
	}
	/**
	 * @return the flightDestBuilding
	 */
	public String getFlightDestBuilding() {
		return flightDestBuilding;
	}
	/**
	 * @param flightDestBuilding the flightDestBuilding to set
	 */
	public void setFlightDestBuilding(String flightDestBuilding) {
		this.flightDestBuilding = flightDestBuilding;
	}
	/**
	 * @return the flightDestCode
	 */
	public String getFlightDestCode() {
		return flightDestCode;
	}
	/**
	 * @param flightDestCode the flightDestCode to set
	 */
	public void setFlightDestCode(String flightDestCode) {
		this.flightDestCode = flightDestCode;
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
	 * @return the orderChannel
	 */
	public Integer getOrderChannel() {
		return orderChannel;
	}
	/**
	 * @param orderChannel the orderChannel to set
	 */
	public void setOrderChannel(Integer orderChannel) {
		this.orderChannel = orderChannel;
	}
	public int getUrgentFlag() {
		return urgentFlag;
	}
	public void setUrgentFlag(int urgentFlag) {
		this.urgentFlag = urgentFlag;
	}
	public Integer getExpectedCompTime() {
		return expectedCompTime;
	}

	public void setExpectedCompTime(Integer expectedCompTime) {
		this.expectedCompTime = expectedCompTime;
	}

	public Double getPriceChannel() {
		return priceChannel;
	}

	public void setPriceChannel(Double priceChannel) {
		this.priceChannel = priceChannel;
	}

	public String getPriceMark() {
		return priceMark;
	}

	public void setPriceMark(String priceMark) {
		this.priceMark = priceMark;
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

	public String getChildSeat() {
		return childSeat;
	}

	public void setChildSeat(String childSeat) {
		this.childSeat = childSeat;
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

	public Integer getServiceCityId() {
		return serviceCityId;
	}

	public void setServiceCityId(Integer serviceCityId) {
		this.serviceCityId = serviceCityId;
	}

	public String getServiceTimeL() {
		return serviceTimeL;
	}

	public void setServiceTimeL(String serviceTimeL) {
		this.serviceTimeL = serviceTimeL;
	}

	public String getStartAddressPoi() {
		return startAddressPoi;
	}

	public void setStartAddressPoi(String startAddressPoi) {
		this.startAddressPoi = startAddressPoi;
	}

	public String getStartAddress() {
		return startAddress;
	}

	public void setStartAddress(String startAddress) {
		this.startAddress = startAddress;
	}

	public String getServiceRecTime() {
		return serviceRecTime;
	}

	public void setServiceRecTime(String serviceRecTime) {
		this.serviceRecTime = serviceRecTime;
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

	public Double getDistance() {
		return distance;
	}

	public void setDistance(Double distance) {
		this.distance = distance;
	}

	public Integer getCarTypeId() {
		return carTypeId;
	}

	public void setCarTypeId(Integer carTypeId) {
		this.carTypeId = carTypeId;
	}

	public Byte getCarSeatNum() {
		return carSeatNum;
	}

	public void setCarSeatNum(Byte carSeatNum) {
		this.carSeatNum = carSeatNum;
	}

	public String getCarDesc() {
		return carDesc;
	}

	public void setCarDesc(String carDesc) {
		this.carDesc = carDesc;
	}

	public String getUserAreaCode1() {
		return userAreaCode1;
	}

	public void setUserAreaCode1(String userAreaCode1) {
		this.userAreaCode1 = userAreaCode1;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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

	public Integer getFlightIsCustom() {
		return flightIsCustom;
	}

	public void setFlightIsCustom(Integer flightIsCustom) {
		this.flightIsCustom = flightIsCustom;
	}

	public String getFlightAirportBuiding() {
		return flightAirportBuiding;
	}

	public void setFlightAirportBuiding(String flightAirportBuiding) {
		this.flightAirportBuiding = flightAirportBuiding;
	}

	public Integer getIsArrivalVisa() {
		return isArrivalVisa;
	}

	public void setIsArrivalVisa(Integer isArrivalVisa) {
		this.isArrivalVisa = isArrivalVisa;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getFlightBrandSign() {
		return flightBrandSign;
	}

	public void setFlightBrandSign(String flightBrandSign) {
		this.flightBrandSign = flightBrandSign;
	}
	
	

}
