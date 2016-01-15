/*
 * Copyright (c) 2015-2016, CCLX.COM. All rights reserved.
 * WANDA GROUP PROPRIETARY/CONFIDENTIAL. 
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is private property; you can't redistribute it and/or modify it
 * under the terms of the LICENSE you obtained from
 *
 *    http://www.cclx.com/
 * 
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. 
 *
 * Author: Jongly Ran
 * Revision: 1.0
 */
package com.hbc.api.trade.order.service.rsp;

/**
 * @author Jongly Ran
 */
public class GuideInfo {
	private String guideId; // 车导信息id
	private String guideName; // 车导名字
	private String guideAvatar; // 车导头像
	private String guideCar; // 车导的车况描述 （车型 车号）
	private String guideTel; // 导游电话
	private Float guideStarLevel; // 导游星级,guide_grade.sys_assessment

	private String ugrContactName;
	private String ugrContactAreaCode;
	private String ugrContactMobile;

	/* MIS订单详情用 */
	private Double priceGuide; // 导游价
	private String guideNo; // 导游号
	private Integer guideLevel; // 导游级别， A、B、C、D、E

	public String getGuideId() {
		return guideId;
	}

	public void setGuideId(String guideId) {
		this.guideId = guideId;
	}

	public String getGuideName() {
		return guideName;
	}

	public void setGuideName(String guideName) {
		this.guideName = guideName;
	}

	public String getGuideAvatar() {
		return guideAvatar;
	}

	public void setGuideAvatar(String guideAvatar) {
		this.guideAvatar = guideAvatar;
	}

	public String getGuideCar() {
		return guideCar;
	}

	public void setGuideCar(String guideCar) {
		this.guideCar = guideCar;
	}

	public String getGuideTel() {
		return guideTel;
	}

	public void setGuideTel(String guideTel) {
		this.guideTel = guideTel;
	}

	public Float getGuideStarLevel() {
		return guideStarLevel;
	}

	public void setGuideStarLevel(Float guideStarLevel) {
		this.guideStarLevel = guideStarLevel;
	}

	public String getUgrContactName() {
		return ugrContactName;
	}

	public void setUgrContactName(String ugrContactName) {
		this.ugrContactName = ugrContactName;
	}

	public String getUgrContactAreaCode() {
		return ugrContactAreaCode;
	}

	public void setUgrContactAreaCode(String ugrContactAreaCode) {
		this.ugrContactAreaCode = ugrContactAreaCode;
	}

	public String getUgrContactMobile() {
		return ugrContactMobile;
	}

	public void setUgrContactMobile(String ugrContactMobile) {
		this.ugrContactMobile = ugrContactMobile;
	}

	public Double getPriceGuide() {
		return priceGuide;
	}

	public void setPriceGuide(Double priceGuide) {
		this.priceGuide = priceGuide;
	}

	public String getGuideNo() {
		return guideNo;
	}

	public void setGuideNo(String guideNo) {
		this.guideNo = guideNo;
	}

	public Integer getGuideLevel() {
		return guideLevel;
	}

	public void setGuideLevel(Integer guideLevel) {
		this.guideLevel = guideLevel;
	}

	@Override
	public String toString() {
		return "GuideInfo [guideId=" + guideId + ", guideName=" + guideName + ", guideAvatar=" + guideAvatar + ", guideCar=" + guideCar + ", guideTel=" + guideTel + ", guideStarLevel=" + guideStarLevel + ", ugrContactName=" + ugrContactName
				+ ", ugrContactAreaCode=" + ugrContactAreaCode + ", ugrContactMobile=" + ugrContactMobile + ", priceGuide=" + priceGuide + ", guideNo=" + guideNo + ", guideLevel=" + guideLevel + "]";
	}
}
