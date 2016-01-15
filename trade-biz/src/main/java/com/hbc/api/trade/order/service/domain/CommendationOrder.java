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
package com.hbc.api.trade.order.service.domain;

/**
 * @author Jongly Ran
 */
public class CommendationOrder {

	private String 	lineSubject;	// 精品线路（线路包车）主题（标题）
	private String 	lineDescription;// 精品线路（线路包车）描述
    private Integer orderGoodsType; // 1接 2 送 4次 5 大长途 6小长途 7市内包车 8固定线路产品 
    private String  goodNo;			// 商品ID
    
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
	 * @return the lineDescription
	 */
	public String getLineDescription() {
		return lineDescription;
	}
	/**
	 * @param lineDescription the lineDescription to set
	 */
	public void setLineDescription(String lineDescription) {
		this.lineDescription = lineDescription;
	}
	/**
	 * @return the orderGoodsType
	 */
	public Integer getOrderGoodsType() {
		return orderGoodsType;
	}
	/**
	 * @param orderGoodsType the orderGoodsType to set
	 */
	public void setOrderGoodsType(Integer orderGoodsType) {
		this.orderGoodsType = orderGoodsType;
	}
	/**
	 * @return the goodNo
	 */
	public String getGoodNo() {
		return goodNo;
	}
	/**
	 * @param goodNo the goodNo to set
	 */
	public void setGoodNo(String goodNo) {
		this.goodNo = goodNo;
	}
}
