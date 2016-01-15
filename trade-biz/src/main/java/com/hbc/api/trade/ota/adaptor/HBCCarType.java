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
package com.hbc.api.trade.ota.adaptor;

import java.util.HashMap;
import java.util.Map;

import com.hbc.api.trade.bdata.common.TradeConstant;

/**
 * 
 * @author Jongly Ran
 */
public class HBCCarType{
	private static final HBCCarType instance = new HBCCarType();
	protected static Map<String, String> CAR_MAP = new HashMap<String, String>();
	
	private HBCCarType(){}
	
	public static HBCCarType getInstance() {
		return instance;
	}
	
	static {
		CAR_MAP.put("1-5", "经济5座");	
		CAR_MAP.put("1-7", "经济7座");	
		CAR_MAP.put("1-9", "经济9座");	
		CAR_MAP.put("1-12", "经济12座");	
		
		CAR_MAP.put("2-5", "舒适5座");	
		CAR_MAP.put("2-7", "舒适7座");	
		CAR_MAP.put("2-9", "舒适9座");	
		CAR_MAP.put("2-12", "舒适12座");	
		CAR_MAP.put("2-14", "舒适14座"); // qua 特有
		
		CAR_MAP.put("3-5", "豪华5座");	
		CAR_MAP.put("3-7", "豪华7座");	
		CAR_MAP.put("3-9", "豪华9座");	
		CAR_MAP.put("3-12", "豪华12座");
		
		CAR_MAP.put("4-5", "奢华5座");	
		CAR_MAP.put("4-7", "奢华7座");
		CAR_MAP.put("4-9", "奢华9座");	
		CAR_MAP.put("4-12", "奢华12座");
		
	}

	public String carDesc(Integer carType, Integer carSeatNum) {
		return CAR_MAP.get(carType+TradeConstant.SPLITER_BOTTOM_LINE+carSeatNum);
	}
	
}
