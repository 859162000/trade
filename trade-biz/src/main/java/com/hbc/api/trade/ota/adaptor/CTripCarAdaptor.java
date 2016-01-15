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
 * 
 * @author Jongly Ran
 */
public class CTripCarAdaptor extends CarTypeAdaptor{
	private static final CTripCarAdaptor instance = new CTripCarAdaptor();
	protected static Map<Integer, String> CAR_MAP = new HashMap<Integer, String>();
	
	private CTripCarAdaptor(){}
	
	public static CTripCarAdaptor getInstance() {
		return instance;
	}
	
	static {
								// HBC		Ctrip
		/**
		 * 携程车型组	携程车型ID	皇包车车型组	皇包车车型ID
		          经济4座  	1	经济5座	1-5
		         经济6座	    2	经济7座	1-7
		   8座小巴	    3	经济9座	1-9
		   10座中巴	4	经济12座	1-12
		          舒适4座	    5	舒适5座	2-5
		          舒适6座	    6	舒适7座	2-7
			8座小巴	7	舒适9座	2-9
			10座中巴	8	舒适12座	2-12
			豪华4座	9	豪华5座	3-5
			豪华6座	10	豪华7座	3-7
			8座小巴	11	豪华9座	3-9
			10座中巴	12	豪华12座	3-12
			豪华4座	13	奢华5座	4-5
			豪华6座	14	豪华7座	4-7
			8座中巴	15	豪华9座	4-9
			10座中巴	16	豪华12座	4-12
			
             经济5对应携程 经济4+出租车
		 */
		CAR_MAP.put(1, "1-5");	
		CAR_MAP.put(2, "1-7");	
		CAR_MAP.put(3, "1-9");	
		CAR_MAP.put(4, "1-12");	
		CAR_MAP.put(5, "2-5");	
		CAR_MAP.put(6, "2-7");	
		CAR_MAP.put(7, "2-9");	
		CAR_MAP.put(8, "2-12");	
		CAR_MAP.put(9, "3-5");	
		CAR_MAP.put(10, "3-7");	
		CAR_MAP.put(11, "3-9");	
		CAR_MAP.put(12, "3-12");
		CAR_MAP.put(13, "4-5");	
		CAR_MAP.put(14, "4-7");
		CAR_MAP.put(15, "4-9");	
		CAR_MAP.put(16, "4-12");
		CAR_MAP.put(17, "1-5"); // 曼谷出租车
		
	}

	/* (non-Javadoc)
	 * @see com.hbc.api.trade.ota.adaptor.CarTypeAdaptor#carTypeMap()
	 */
	@Override
	public Map<Integer, String> carTypeMap() {
		return CAR_MAP;
	}
}
