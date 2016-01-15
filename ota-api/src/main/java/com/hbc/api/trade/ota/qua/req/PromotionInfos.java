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
package com.hbc.api.trade.ota.qua.req;

import java.util.List;

/**
 * 多张优惠券<br/>
 * 2015-12-31
 * @author Jongly Ran
 * @version v1.0.2
 */
public class PromotionInfos {
	private List<PromotionInfo> promotionInfo;

	/**
	 * @return the promotionInfo
	 */
	public List<PromotionInfo> getPromotionInfo() {
		return promotionInfo;
	}

	/**
	 * @param promotionInfo the promotionInfo to set
	 */
	public void setPromotionInfo(List<PromotionInfo> promotionInfo) {
		this.promotionInfo = promotionInfo;
	}
}
