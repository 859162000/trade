package com.hbc.api.trade.third.rsp;

import java.util.Date;

public class GuideRsp {
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
     *  原model，当前身份（账号类型）。1-兼职(学生)；2-兼职(工作)；3-专职(个体）；4-专职（地接社）
     *  所属表字段为guide.job_type
     */
    private Integer jobType;
    private String jobTypeName;
    /**
     *  导游评级。0-未评级;1-A;2-B;3-C;4-D;5-E
     *  所属表字段为guide.guide_level
     */
    private Integer guideLevel;
    private String guideLevelName;
    
    private double priceGuide;
    
    private String carId;
    
    private String  carName;
    private Integer    carType;
    private String catTypeName;
    private Integer    carClass;
    private String catClassName;
	public Date getLatestLoginTime() {
		return latestLoginTime;
	}
	public void setLatestLoginTime(Date latestLoginTime) {
		this.latestLoginTime = latestLoginTime;
	}
	public String getGuideLevelName() {
		return guideLevelName;
	}
	public void setGuideLevelName(String guideLevelName) {
		this.guideLevelName = guideLevelName;
	}
	public String getGuideId() {
		return guideId;
	}
	public void setGuideId(String guideId) {
		this.guideId = guideId;
	}
	public String getJobTypeName() {
		return jobTypeName;
	}
	public void setJobTypeName(String jobTypeName) {
		this.jobTypeName = jobTypeName;
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
	public Integer getJobType() {
		return jobType;
	}
	public void setJobType(Integer jobType) {
		this.jobType = jobType;
	}
	public Integer getGuideLevel() {
		return guideLevel;
	}
	public void setGuideLevel(Integer guideLevel) {
		this.guideLevel = guideLevel;
	}
	public double getPriceGuide() {
		return priceGuide;
	}
	public void setPriceGuide(double priceGuide) {
		this.priceGuide = priceGuide;
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
	public String getCatTypeName() {
		return catTypeName;
	}
	public void setCatTypeName(String catTypeName) {
		this.catTypeName = catTypeName;
	}
	public Integer getCarClass() {
		return carClass;
	}
	public void setCarClass(Integer carClass) {
		this.carClass = carClass;
	}
	public String getCatClassName() {
		return catClassName;
	}
	public void setCatClassName(String catClassName) {
		this.catClassName = catClassName;
	}
    
}
