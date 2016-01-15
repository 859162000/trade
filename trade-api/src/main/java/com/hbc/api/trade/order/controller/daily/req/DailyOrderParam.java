package com.hbc.api.trade.order.controller.daily.req;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.hbc.api.trade.order.controller.commendation.GDSOrderParam;


public class DailyOrderParam extends GDSOrderParam{

	@Min(value=0, message="订单价必须不小于0")
	private Double 	priceChannel;		//	渠道价
	private Double 	priceTicket;		//	票面价
	@NotBlank private String priceMark;	//	询价系统返回ID
	
	@Min(value=0, message="成人座位数不能小于0")
	private Integer 	adultNum;//	成人座位数
	
	@Min(value=0, message="小孩座位数不能小于0")
	private Integer 	childNum;//	小孩座位数
	
	private String	childSeat;			//	每个小孩的年龄以逗号分开 1-1,2-1,3-2 ｛1类型的小孩坐1个｝
	private String serviceAddressTel;	//	目的地酒店或者区域电话号码
	private String serviceAreaCode;		//	目的地区号
	
	@NotNull private Integer serviceCityId;		//	服务地城市ID
	@NotNull private Integer serviceEndCityid;	//	服务终止城市ID
	@NotBlank private String serviceDate;		//	服务时间[2015-10-03]
	@NotBlank private String serviceEndDate;	//	服务终止时间[2015-10-03]
	@NotBlank private String startAddressPoi;	//	出发地poi
	
	@NotNull private String serviceRecTime;			//  服务时间[20:02]
	private Integer serviceLocalDays;		//	市内服务天数
	private Integer serviceNonlocalDays;	//	市外服务天数
	@NotNull private Integer totalDays;				// 日租总天数
	
	// TODO 冉孟萍，确认校验方式
	private String startAddress;			//	出发地（冗余）机场名+航站楼
	private String startAddressDetail;		//	出发地详情
	
	private String destAddress	;			//目的地address;
	private String destAddressDetail;		//	目的地address详情
	private String destAddressPoi;			//	目的地poi
	private Double	distance;				//	服务距离
	private String journeyComment;			//	行程说明[说明1，说明2，说明3]
	@NotBlank private String servicePassCitys;//途经城市[1,3,4,5] 城市ID
				
	@NotNull private Integer carTypeId;		//	1 经济 2舒适 3豪华 4奢华
	@Min(value=0, message="车座数不能小于0")
	@NotNull private Integer carSeatNum	;	//	车座数
	private String	carDesc	;				//	现代圣达菲,起亚K5,雪佛兰迈锐宝
	private String userId;		//	客户ID;
	@NotBlank private String userName	;	//	客人名称
	@NotBlank private String	userAreaCode1;
	@NotBlank private String	userMobile1;
	private String	userAreaCode2;
	private String	userMobile2;
	private String	userAreaCode3;
	private String	userMobile3;
	private String userEmail;	//	客人email
	private String userRemark;	//	客人备注
	@NotNull private int urgentFlag;
	@NotNull private Integer orderChannel; // call: AgentChannelEnum
	
	public Double getPriceTicket() {
		return priceTicket;
	}
	public void setPriceTicket(Double priceTicket) {
		this.priceTicket = priceTicket;
	}
	/**
	 * @return the totalDays
	 */
	public Integer getTotalDays() {
		return totalDays;
	}
	/**
	 * @param totalDays the totalDays to set
	 */
	public void setTotalDays(Integer totalDays) {
		this.totalDays = totalDays;
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
	public Integer getServiceEndCityid() {
		return serviceEndCityid;
	}
	public void setServiceEndCityid(Integer serviceEndCityid) {
		this.serviceEndCityid = serviceEndCityid;
	}
	public String getServiceDate() {
		return serviceDate;
	}
	public void setServiceDate(String serviceDate) {
		this.serviceDate = serviceDate;
	}
	public String getServiceEndDate() {
		return serviceEndDate;
	}
	public void setServiceEndDate(String serviceEndDate) {
		this.serviceEndDate = serviceEndDate;
	}
	public String getServiceRecTime() {
		return serviceRecTime;
	}
	public void setServiceRecTime(String serviceRecTime) {
		this.serviceRecTime = serviceRecTime;
	}
	public Integer getServiceLocalDays() {
		return serviceLocalDays;
	}
	public void setServiceLocalDays(Integer serviceLocalDays) {
		this.serviceLocalDays = serviceLocalDays;
	}
	public Integer getServiceNonlocalDays() {
		return serviceNonlocalDays;
	}
	public void setServiceNonlocalDays(Integer serviceNonlocalDays) {
		this.serviceNonlocalDays = serviceNonlocalDays;
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
	public Double getDistance() {
		return distance;
	}
	public void setDistance(Double distance) {
		this.distance = distance;
	}
	public String getJourneyComment() {
		return journeyComment;
	}
	public void setJourneyComment(String journeyComment) {
		this.journeyComment = journeyComment;
	}
	public String getServicePassCitys() {
		return servicePassCitys;
	}
	public void setServicePassCitys(String servicePassCitys) {
		this.servicePassCitys = servicePassCitys;
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
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUserRemark() {
		return userRemark;
	}
	public void setUserRemark(String userRemark) {
		this.userRemark = userRemark;
	}


}
