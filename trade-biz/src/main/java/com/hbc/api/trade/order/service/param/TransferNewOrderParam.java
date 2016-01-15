package com.hbc.api.trade.order.service.param;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 送机下单参数
 * 由于规范晚于开发，因此会出现Date类型时间对应两种规范，其中C端用L后缀，其他用s后缀。<p/>
 * 如Date serviceTime对应C端String serviceTimeL，对应GDS等其他端String serviceTimes
 * @author Jongly Ran
 */
public class TransferNewOrderParam {

	@Min(value=0, message="订单价必须不小于0")
	private Double 	priceChannel;					//	C端价格
	
	@Min(value=0, message="送机登机手续必须不小于0")
	private Double checkInPrice; 					//  帮办理登机手续

	@Min(value=0, message="成人座位数不能小于0")
	private Integer 	adultNum;					//	成人座位数
	
	@Min(value=0, message="小孩座位数不能小于0")
	private Integer 	childNum;					//	小孩座位数

	@Min(value=0, message="车座数不能小于0")
	private Integer 	carSeatNum;					//	车座数
	
	@Min(value=0, message="预计服务完成时间不能小于0")
	private Integer expectedCompTime;				//  预计服务完成时间 接送次 必填

	@NotBlank(message="priceMark不能为空")
	private String	priceMark;						//	询价系统返回ID
	
	@NotBlank(message="目的地区号不能为空")
	private String	serviceAreaCode;				//	目的地区号
	
	@NotNull(message="服务地城市ID不能为空") 
	private Integer 	serviceCityId;				//	服务地城市ID
	
	@NotBlank(message="服务时间不能为空") 
	private String	serviceTimeL;					//	服务时间[2015-10-03 20:02:34]
	
	@NotBlank(message="出发地不能为空") 
	private String	startAddress;					//	出发地（冗余）机场名+航站楼
	
	@NotBlank(message="出发地详细地址不能为空")
	private String	startAddressDetail;				//	出发地详情
	
	@NotBlank(message="出发地经纬度不能为空") 
	private String	startAddressPoi;				//	出发地poi
	
	@NotBlank(message="目的地不能为空")
	private String	destAddress;					//	目的地address
	
	@NotBlank(message="目的地经纬度不能为空")
	private String	destAddressPoi;					//	目的地经纬度
	
	@NotNull(message="服务距离不能为空") 
	private Double		distance;					//	服务距离
	
	@NotNull(message="车型不能为空") 
	private Integer 	carTypeId;					//	1 经济 2舒适 3豪华 4奢华
	
	@NotBlank(message="汽车描述不能为空") 
	private String	carDesc;
	
	@NotBlank(message="客户手机区号不能为空") 
	private String	userAreaCode1;
	
	@NotBlank(message="客户手机不能为空") 
	private String	userMobile1;
	
	@NotBlank(message="起飞机场三字码不能为空") 
	private String	flightAirportCode;
	
	@NotBlank(message="起飞机场名不能为空") 
	private String	flightAirportName;
	
	@NotNull(message="是否急单不能为空") 
	private Integer 	urgentFlag;		//急单标识
	
	@NotNull(message="订单渠道不能为空")  
	private Integer orderChannel; 		// call: AgentChannelEnum
	
	private String	userAreaCode2;
	private String	userMobile2;
	private String	userAreaCode3;
	private String	userMobile3;
	private String 	userEmail;			//	客人email
	private String	userRemark;
	
	private String	userId;
	private String	userName;
	private String	childSeat;			//	每个小孩的年龄以逗号分开 1-1,2-1,3-2 ｛1类型的小孩坐1个｝
	private String	serviceAddressTel;	//	目的地酒店或者区域电话号码
	private String 	destAddressDetail;	//	目的地address详情

	private String	flightNo;
	private String 	flightFlyTimeL;
	private String 	flightArriveTimeL;
	
	private String 	flightFlyTimes;		
	private String 	flightArriveTimes;
	private String 	serviceTimes;		
    private String 	serviceEndTimes;	
	
	private Integer	flightIsCustom;
	private String	flightAirportBuiding;
	private String	flightDestCode; 	// 降落机场三字码
	private String	flightDestName; 	// 降落机场名称
	private String	flightDestBuilding;	// 降落机场航站楼
	private Integer orderType;
	
	/**
	 * @return the orderType
	 */
	public Integer getOrderType() {
		return orderType;
	}
	/**
	 * @param orderType the orderType to set
	 */
	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}
	/**
	 * @return the flightFlyTimes
	 */
	public String getFlightFlyTimes() {
		return flightFlyTimes;
	}
	/**
	 * @param flightFlyTimes the flightFlyTimes to set
	 */
	public void setFlightFlyTimes(String flightFlyTimes) {
		this.flightFlyTimes = flightFlyTimes;
	}
	/**
	 * @return the flightArriveTimes
	 */
	public String getFlightArriveTimes() {
		return flightArriveTimes;
	}
	/**
	 * @param flightArriveTimes the flightArriveTimes to set
	 */
	public void setFlightArriveTimes(String flightArriveTimes) {
		this.flightArriveTimes = flightArriveTimes;
	}
	/**
	 * @return the serviceTimes
	 */
	public String getServiceTimes() {
		return serviceTimes;
	}
	/**
	 * @param serviceTimes the serviceTimes to set
	 */
	public void setServiceTimes(String serviceTimes) {
		this.serviceTimes = serviceTimes;
	}
	/**
	 * @return the serviceEndTimes
	 */
	public String getServiceEndTimes() {
		return serviceEndTimes;
	}
	/**
	 * @param serviceEndTimes the serviceEndTimes to set
	 */
	public void setServiceEndTimes(String serviceEndTimes) {
		this.serviceEndTimes = serviceEndTimes;
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
	public void setUrgentFlag(Integer urgentFlag) {
		this.urgentFlag = urgentFlag;
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
	/**
	 * @return the checkInPrice
	 */
	public Double getCheckInPrice() {
		return checkInPrice;
	}
	/**
	 * @param checkInPrice the checkInPrice to set
	 */
	public void setCheckInPrice(Double checkInPrice) {
		this.checkInPrice = checkInPrice;
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
	/**
	 * @return the priceChannel
	 */
	public Double getPriceChannel() {
		return priceChannel;
	}
	/**
	 * @param priceChannel the priceChannel to set
	 */
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
	public Integer getCarSeatNum() {
		return carSeatNum;
	}
	public void setCarSeatNum(Integer carSeatNum) {
		this.carSeatNum = carSeatNum;
	}
	public String getCarDesc() {
		return carDesc;
	}
	public void setCarDesc(String carDesc) {
		this.carDesc = carDesc;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
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
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}

}
