/**
 * @Author lukangle
 * @2015年10月7日@下午2:58:07
 */
package com.hbc.api.trade.order.service.corder.rsp;

import com.hbc.api.trade.order.service.rsp.GuideInfo;
import com.hbc.api.trade.order.service.rsp.PriceInfo;

public class COrderBean {
	private String 	orderNo;			// 订单号
    private Integer orderStatus;		// 订单状态
    private Integer orderType;			// 订单类型
    private String 	serviceTime;		// 服务开始时间
    private String 	serviceEndTime;		// 服务结束时间（日租）
    
    private String 	serviceCityName;	// 服务城市
    private String 	serviceEndCityname;	// 服务结束城市
    private String 	startAddress;		// 开始地址
    private String 	destAddress;		// 结束地址
    private String 	flightNo;			// 航班
    private String 	lineSubject;		// 精品线路（线路包车）主题（标题）
    
    private Integer carTypeId;			// 经济 舒适 豪华 奢华
    private Integer carSeatNum;			// 车座数
    private String 	carDesc;			// 描述，如：经济5座
    private Integer	serviceLocalDays;	// 日租市内天数
    private Integer	serviceNonlocalDays;// 日租市外天数
    private Integer totalDays;			// 日租总天数
    
    private Boolean canChat;			// 是否能聊天
    private String 	imToken;			// 聊天token
    private Integer	imCount;			// 未读消息数
    private Double	overPrice;			// 增项费用（待定）
    private Integer	alreadyPay;			// 已支付增项费用（待定）
    
    private GuideInfo guideInfo;		// 导游信息
    private PriceInfo priceInfo;		// 价格信息

	/**
	 * @return the lineSubject
	 */
	public String getLineSubject() {
		return lineSubject;
	}

	/**
	 * @param lineSubject the lineSubject to set
	 */
	public void setLineSubject(String lineSubject) {
		this.lineSubject = lineSubject;
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
	 * @return the imCount
	 */
	public Integer getImCount() {
		return imCount;
	}

	/**
	 * @param imCount the imCount to set
	 */
	public void setImCount(Integer imCount) {
		this.imCount = imCount;
	}

	/**
	 * @return the priceInfo
	 */
	public PriceInfo getPriceInfo() {
		return priceInfo;
	}

	/**
	 * @param priceInfo the priceInfo to set
	 */
	public void setPriceInfo(PriceInfo priceInfo) {
		this.priceInfo = priceInfo;
	}
	/**
	 * @return 是否能聊天
	 */
	public Boolean getCanChat() {
		return canChat;
	}
	/**
	 * @param canChat 是否能聊天
	 */
	public void setCanChat(Boolean canChat) {
		this.canChat = canChat;
	}
	/**
	 * @return 聊天token
	 */
	public String getImToken() {
		return imToken;
	}
	/**
	 * @param imToken 聊天token
	 */
	public void setImToken(String imToken) {
		this.imToken = imToken;
	}

	/**
	 * @return the orderNo
	 */
	public String getOrderNo() {
		return orderNo;
	}

	/**
	 * @param orderNo the orderNo to set
	 */
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	/**
	 * @return 订单状态。To see {@link com.hbc.api.trade.order.enums.order.OrderStatus}
	 */
	public Integer getOrderStatus() {
		return orderStatus;
	}

	/**
	 * @param orderStatus 订单状态。To see {@link com.hbc.api.trade.order.enums.order.OrderStatus}
	 */
	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}

	/**
	 * @return 订单类型。1-接机；2-送机；3-日租；4-次租
	 */
	public Integer getOrderType() {
		return orderType;
	}

	/**
	 * @param orderType 订单类型。1-接机；2-送机；3-日租；4-次租
	 */
	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}

	/**
	 * @return the serviceTime
	 */
	public String getServiceTime() {
		return serviceTime;
	}

	/**
	 * @param serviceTime the serviceTime to set
	 */
	public void setServiceTime(String serviceTime) {
		this.serviceTime = serviceTime;
	}

	/**
	 * @return 日租结束时间
	 */
	public String getServiceEndTime() {
		return serviceEndTime;
	}

	/**
	 * @param serviceEndTime 日租结束时间
	 */
	public void setServiceEndTime(String serviceEndTime) {
		this.serviceEndTime = serviceEndTime;
	}

	/**
	 * @return the serviceCityName
	 */
	public String getServiceCityName() {
		return serviceCityName;
	}

	/**
	 * @param serviceCityName the serviceCityName to set
	 */
	public void setServiceCityName(String serviceCityName) {
		this.serviceCityName = serviceCityName;
	}

	/**
	 * @return the serviceEndCityname
	 */
	public String getServiceEndCityname() {
		return serviceEndCityname;
	}

	/**
	 * @param serviceEndCityname the serviceEndCityname to set
	 */
	public void setServiceEndCityname(String serviceEndCityname) {
		this.serviceEndCityname = serviceEndCityname;
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
	 * @return the destAddress
	 */
	public String getDestAddress() {
		return destAddress;
	}

	/**
	 * @param destAddress the destAddress to set
	 */
	public void setDestAddress(String destAddress) {
		this.destAddress = destAddress;
	}

	/**
	 * @return the flightNo
	 */
	public String getFlightNo() {
		return flightNo;
	}

	/**
	 * @param flightNo the flightNo to set
	 */
	public void setFlightNo(String flightNo) {
		this.flightNo = flightNo;
	}

	/**
	 * @return the carTypeId
	 */
	public Integer getCarTypeId() {
		return carTypeId;
	}

	/**
	 * @param carTypeId the carTypeId to set
	 */
	public void setCarTypeId(Integer carTypeId) {
		this.carTypeId = carTypeId;
	}


	public Integer getCarSeatNum() {
		return carSeatNum;
	}

	public void setCarSeatNum(Integer carSeatNum) {
		this.carSeatNum = carSeatNum;
	}

	/**
	 * @return the carDesc
	 */
	public String getCarDesc() {
		return carDesc;
	}

	/**
	 * @param carDesc the carDesc to set
	 */
	public void setCarDesc(String carDesc) {
		this.carDesc = carDesc;
	}

	/**
	 * @return 日租市内天数
	 */
	public Integer getServiceLocalDays() {
		return serviceLocalDays;
	}

	/**
	 * @param serviceLocalDays 日租市内天数
	 */
	public void setServiceLocalDays(Integer serviceLocalDays) {
		this.serviceLocalDays = serviceLocalDays;
	}

	/**
	 * @return 日租市外天数
	 */
	public Integer getServiceNonlocalDays() {
		return serviceNonlocalDays;
	}

	/**
	 * @param serviceNonlocalDays 日租市外天数
	 */
	public void setServiceNonlocalDays(Integer serviceNonlocalDays) {
		this.serviceNonlocalDays = serviceNonlocalDays;
	}

	/**
	 * @return 增项费用
	 */
	public Double getOverPrice() {
		return overPrice;
	}

	/**
	 * @param overPrice 增项费用
	 */
	public void setOverPrice(Double overPrice) {
		this.overPrice = overPrice;
	}

	/**
	 * @return 增项费用是否已支付，0：未支付；1：已支付。
	 */
	public Integer getAlreadyPay() {
		return alreadyPay;
	}

	/**
	 * @param alreadyPay  增项费用是否已支付，0：未支付；1：已支付。
	 */
	public void setAlreadyPay(Integer alreadyPay) {
		this.alreadyPay = alreadyPay;
	}

	/**
	 * @return 订单对应的导游基础信息
	 */
	public GuideInfo getGuideInfo() {
		return guideInfo;
	}

	/**
	 * @param guideBasicInfo 订单对应的导游基础信息
	 */
	public void setGuideInfo(GuideInfo guideInfo) {
		this.guideInfo = guideInfo;
	}
}
