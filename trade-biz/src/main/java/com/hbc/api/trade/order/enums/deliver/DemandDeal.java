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
package com.hbc.api.trade.order.enums.deliver;

import com.hbc.api.trade.order.enums.TradeReturnCodeEnum;
import com.hbc.api.trade.order.exception.TradeException;

/**
 * 表态是否愿意接单：0-未表态（默认）1-愿意接 2 不愿意接
 * @author Jongly Ran
 */
public enum DemandDeal {
	/** 0:未表态 */
	INITIAL(0, "未表态"),
	
	/** 1:愿意接 */
	AGREE(1, "愿意接"),
	
	/** 2:不愿意接 */
	DISAGREE(2, "不愿意接");
	
	public Integer value;
	public String name;
	DemandDeal(Integer value,String name){
		this.value = value;
		this.name = name;
	}
	
	public static DemandDeal getType(Integer value){
		DemandDeal[] deliverTypes = DemandDeal.values();
		for(DemandDeal deliverType : deliverTypes){
			if(deliverType.value.equals(value)){
				return deliverType;
			}
		}
		
		throw new TradeException(TradeReturnCodeEnum.DEMAND_DEAL_TYPE,value);
	}
}
