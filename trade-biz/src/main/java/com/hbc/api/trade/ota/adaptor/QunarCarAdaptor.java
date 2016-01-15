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
public class QunarCarAdaptor extends CarTypeAdaptor {
	private static final QunarCarAdaptor instance = new QunarCarAdaptor();
	protected static Map<Integer, String> CAR_MAP = new HashMap<Integer, String>();
	
	private QunarCarAdaptor(){}
	
	public static QunarCarAdaptor getInstance() {
		return instance;
	}
	/**
	 *  1 => '1,1',
        3 => '2,1',
        4 => '3,1',
        5 => '2,2',
        107 => '2,3',
        132 => '2,4',
        103 => '1,2',
        119 => '4,1',
        dd 121 => '4,3',
        dd 122 => '1,4',
        125 => '3,2',
        dd 126 => '1,3'
        
        
 经济5座		经济	1	丰田卡罗拉/大众速腾	出租车3人2行李，非出租车3人3行李
舒适5座		舒适	3	大众帕萨特/丰田凯美瑞/本田雅阁	3人3行李
豪华5座		豪华	4	奔驰E系/宝马5系/奥迪A6	3人3行李
奢华5座		奢华	119	奔驰S系/宝马7系/奥迪A8	4人3行李
经济7座		经济7座	103	丰田innova/丰田wish	4人4行李
商务7座		商务7座	5	丰田埃尔法/Sienna/奔驰Viano	6人4行李
豪华7座		豪华7座	125	奔驰Vito/雪铁龙Jumper	6人4行李
亚洲经济9/舒适9座/欧洲豪华9座		小巴	107	丰田海狮/大众T5	7人6行李  ?   1 9  
舒适12座/豪华12/奢华12		豪华小巴	132	丰田海狮	9人6行李   
无		商务小巴	114	丰田海狮	12人8行李  ?       不对应
无		中巴	118	丰田考斯特、三菱	18人12行李 ?


		1
		3
		4
		119
		103
		5
		125
		107 1 9
		132
		
		
		114 不对应
		118 不对应
		

	 */
	static {
									// HBC     						Qunar
		
		CAR_MAP.put(1, "1-5"); 		// 经济5座						经济
		CAR_MAP.put(3, "2-5"); 		// 舒适5座						舒适
		CAR_MAP.put(4, "3-5"); 		// 豪华5座						豪华
		CAR_MAP.put(5, "2-7"); 		// 商务7座						商务7座
		//CAR_MAP.put(6, "2-12");		// 舒适12座/豪华12/奢华12			豪华小巴  (适配最便宜的)
		CAR_MAP.put(107, "2-9"); 	// 亚洲经济9/舒适9座/欧洲豪华9座		小巴		(适配最便宜的)
		//CAR_MAP.put(14, "2-12");	// 舒适12座/豪华12/奢华12			豪华小巴  (适配最便宜的)
		CAR_MAP.put(132, "2-12"); 	// 经济7座						经济7座
		CAR_MAP.put(103 , "1-7");
		//CAR_MAP.put(114, "2-12");	// 舒适12座/豪华12/奢华12			商务小巴  (适配最便宜的)
		CAR_MAP.put(119, "4-5"); 	// 奢华5座		
		CAR_MAP.put(121, "4-9"); 	// 奢华5座		奢华
		CAR_MAP.put(122, "1-12");	// 舒适12座/豪华12/奢华12			豪华小巴  (适配最便宜的)
		CAR_MAP.put(125, "3-7"); 	// 豪华7座						豪华7座
		CAR_MAP.put(126, "1-9"); 	// 亚洲经济9/舒适9座/欧洲豪华9座		小巴		(适配最便宜的)
									// 无							中巴
	}
	
	/*
	| 去哪车型 | 去哪车型描述 | 皇包车车型 |
	+--------------+--------------------+-----------------+
	|            1 | 经济型          |               1 |
	|            4 | 豪华型          |               4 |
	|            5 | 商务7座         |               5 |
	|          125 | 豪华7座         |             125 |
	|          103 | 经济7座         |             103 |
	|            3 | 舒适型          |               3 |
	|          132 | 豪华小巴       |               6 |
	|          132 | 豪华小巴       |             122 |
	|          119 | 奢华型          |             119 |
	|          114 | 商务小巴       |             114 |
	|          107 | 小巴             |             126 |
	|          132 | 豪华小巴       |              14 |
	|          107 | 小巴             |              10 |
	+--------------+--------------------+-----------------+
	
	*/
	@Override
	public Map<Integer, String> carTypeMap() {
		return CAR_MAP;
	}
}
