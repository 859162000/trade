/**
 * @Author lukangle
 * @2015年11月26日@下午5:32:02
 */
package com.hbc.api.trade.order.service.mis.rsp;

import java.util.Date;

public class MisDeliverGuideVo {
	 /**
     *  最近一次登录时间
     *  所属表字段为guide.latest_login_time
     */
    private Date latestLoginTime;
    
    /**
     *  导游id
     *  所属表字段为guide.guide_id
     */
    private String guideId;

    /**
     *  导游业务编号
     *  所属表字段为guide.guide_no
     */
    private String guideNo;

    /**
     *  导游名称
     *  所属表字段为guide.guide_name
     */
    private String guideName;
    /**
     *  导游评级。0-未评级;1-A;2-B;3-C;4-D;5-E
     *  所属表字段为guide.guide_level
     */
    private Integer guideLevel;
    private String guideLevelName;
    
    
    private String carId;
    
    private String  carName;
    private Integer    carType;
    private String carTypeName;
    private Integer    carClass;
    private String carClassName;
    
    private String carModel;
    private double priceGuide;
    
    private Date deliverTime;//发包时间
    private Date firstSeeTime;//首次查看时间
    private Date acptime;//接单时间
    
    private String refusReason;
    
    private int demandDeal;//DemandDeal
    private String demandDealName;
	public Date getLatestLoginTime() {
		return latestLoginTime;
	}
	public void setLatestLoginTime(Date latestLoginTime) {
		this.latestLoginTime = latestLoginTime;
	}
	public String getGuideId() {
		return guideId;
	}
	public void setGuideId(String guideId) {
		this.guideId = guideId;
	}
	public String getGuideNo() {
		return guideNo;
	}
	public void setGuideNo(String guideNo) {
		this.guideNo = guideNo;
	}
	public String getGuideName() {
		return guideName;
	}
	public void setGuideName(String guideName) {
		this.guideName = guideName;
	}
	public Integer getGuideLevel() {
		return guideLevel;
	}
	public void setGuideLevel(Integer guideLevel) {
		this.guideLevel = guideLevel;
	}
	public String getGuideLevelName() {
		return guideLevelName;
	}
	public void setGuideLevelName(String guideLevelName) {
		this.guideLevelName = guideLevelName;
	}
	public String getCarId() {
		return carId;
	}
	public void setCarId(String carId) {
		this.carId = carId;
	}
	public String getCarName() {
		return carName;
	}
	public void setCarName(String carName) {
		this.carName = carName;
	}
	public Integer getCarType() {
		return carType;
	}
	public void setCarType(Integer carType) {
		this.carType = carType;
	}
	public Integer getCarClass() {
		return carClass;
	}
	public void setCarClass(Integer carClass) {
		this.carClass = carClass;
	}
	public String getCarTypeName() {
		return carTypeName;
	}
	public void setCarTypeName(String carTypeName) {
		this.carTypeName = carTypeName;
	}
	public String getCarClassName() {
		return carClassName;
	}
	public void setCarClassName(String carClassName) {
		this.carClassName = carClassName;
	}
	public String getCarModel() {
		return carModel;
	}
	public void setCarModel(String carModel) {
		this.carModel = carModel;
	}
	public double getPriceGuide() {
		return priceGuide;
	}
	public void setPriceGuide(double priceGuide) {
		this.priceGuide = priceGuide;
	}
	public Date getDeliverTime() {
		return deliverTime;
	}
	public void setDeliverTime(Date deliverTime) {
		this.deliverTime = deliverTime;
	}
	public Date getFirstSeeTime() {
		return firstSeeTime;
	}
	public void setFirstSeeTime(Date firstSeeTime) {
		this.firstSeeTime = firstSeeTime;
	}
	public Date getAcptime() {
		return acptime;
	}
	public void setAcptime(Date acptime) {
		this.acptime = acptime;
	}
	public String getRefusReason() {
		return refusReason;
	}
	public void setRefusReason(String refusReason) {
		this.refusReason = refusReason;
	}
	public int getDemandDeal() {
		return demandDeal;
	}
	public void setDemandDeal(int demandDeal) {
		this.demandDeal = demandDeal;
	}
	public String getDemandDealName() {
		return demandDealName;
	}
	public void setDemandDealName(String demandDealName) {
		this.demandDealName = demandDealName;
	}
    
}
