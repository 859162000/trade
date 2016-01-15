package com.hbc.api.trade.order.enums.conf;

import com.hbc.api.trade.order.enums.TradeReturnCodeEnum;
import com.hbc.api.trade.order.exception.TradeException;

public enum CitySerialOpen {
	//0 关闭 1启用 
		/** 0:关闭*/
		CLOSE(0, "关闭"),
		
		/** 1:启用 */
		OPEN(1, "启用");
		
		public int value;
		public String name;
		
		CitySerialOpen(int value,String name){
			this.value = value;
			this.name = name;
		}
		
		public static CitySerialOpen getType(Integer value){
			CitySerialOpen[] citySerialOpens = CitySerialOpen.values();
			for(CitySerialOpen citySerialOpen : citySerialOpens){
				if(citySerialOpen.value==value){
					return citySerialOpen;
				}
			}
			
			throw new TradeException(TradeReturnCodeEnum.IS_READABLE_TYPE,value);
		}
}
