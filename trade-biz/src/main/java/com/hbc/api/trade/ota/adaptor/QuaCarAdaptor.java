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

/**
 * @author Jongly Ran
 */
public class QuaCarAdaptor extends CarTypeAdaptor {
	private static final QuaCarAdaptor instance = new QuaCarAdaptor();
	protected static Map<Integer, String> CAR_MAP = new HashMap<Integer, String>();
	
	private QuaCarAdaptor(){}
	
	public static QuaCarAdaptor getInstance() {
		return instance;
	}
	
	static {
								// HBC      Qua
		
		CAR_MAP.put(2, "1-5"); 	// 经济5座 	经济5座
		CAR_MAP.put(3, "2-5"); 	// 舒适5座	舒适5座
		CAR_MAP.put(4, "3-5"); 	// 豪华5座	豪华5座
		CAR_MAP.put(5, "4-5"); 	// 奢华5座	奢华5座
		
		CAR_MAP.put(6, "1-7"); 	// 经济7座	经济7座
		CAR_MAP.put(7, "2-7"); 	// 舒适7座	舒适7座
		CAR_MAP.put(8, "3-7"); 	// 豪华7座	豪华7座
		CAR_MAP.put(9, "4-7"); 	// 奢华7座	奢华7座
		
		CAR_MAP.put(10, "1-9"); // 经济9座	经济9座
		CAR_MAP.put(11, "2-9"); // 舒适9座	舒适9座
		CAR_MAP.put(12, "3-9"); // 豪华9座	豪华9座
		
		CAR_MAP.put(13, "2-12"); // 舒适12座 商务10座 
		CAR_MAP.put(14, "1-12"); // 经济12座	经济12座
		CAR_MAP.put(15, "2-12"); // 舒适12座	舒适12座
		CAR_MAP.put(16, "3-12"); // 豪华12座	豪华12座
		
		CAR_MAP.put(17, "2-14"); // 舒适14座	中巴10-15座
	}
	
	@Override
	public Map<Integer, String> carTypeMap() {
		return CAR_MAP;
	}
}
