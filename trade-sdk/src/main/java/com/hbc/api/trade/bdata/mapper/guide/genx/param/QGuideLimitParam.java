/**
 * @Author lukangle
 * @2015年11月20日@下午3:40:02
 */
package com.hbc.api.trade.bdata.mapper.guide.genx.param;

import java.util.List;

public class QGuideLimitParam {
	private Integer carType;
	private Integer carClass;
	private 	Integer guideLevel;
	private List<String> guideIds;
	private 	List<Integer> signStatus;
	private Integer limit;
	private 	Integer offset;
	private 	Integer cityId ;
	private 	Integer cropType;
	private String guideNo;
	private String guideName;
	
	
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
	public List<Integer> getSignStatus() {
		return signStatus;
	}
	public void setSignStatus(List<Integer> signStatus) {
		if(signStatus!=null && signStatus.size()>0){
			this.signStatus = signStatus;
		}
	}
	public Integer getCropType() {
		return cropType;
	}
	public void setCropType(Integer cropType) {
		this.cropType = cropType;
	}
	public Integer getCityId() {
		return cityId;
	}
	public void setCityId(Integer cityId) {
		this.cityId = cityId;
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
	public Integer getGuideLevel() {
		return guideLevel;
	}
	public void setGuideLevel(Integer guideLevel) {
		this.guideLevel = guideLevel;
	}
	public List<String> getGuideIds() {
		return guideIds;
	}
	public void setGuideIds(List<String> guideIds) {
		if(guideIds!=null && guideIds.size()>0){
			this.guideIds = guideIds;
		}
	}
	public Integer getLimit() {
		return limit;
	}
	public void setLimit(Integer limit) {
		this.limit = limit;
	}
	public Integer getOffset() {
		return offset;
	}
	public void setOffset(Integer offset) {
		this.offset = offset;
	}
	
}
