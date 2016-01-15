package com.hbc.api.trade.order.enums.alarm;

import com.hbc.api.trade.order.enums.TradeReturnCodeEnum;
import com.hbc.api.trade.order.exception.TradeException;

public enum AlarmStatus {

	/*0-初始化（默认）；1-成功；2：失败*/
	INIT(0,"初始化"),
	SUCCESS(1,"成功"),
	FAIL(2,"失败");
	
	public Integer value;
	public String name;
	AlarmStatus(int value,String name){
		this.value = value;
		this.name = name;
	}
	
	public static AlarmStatus getStatus(Integer value){
		AlarmStatus[] alarmStatuss = AlarmStatus.values();
		for(AlarmStatus alarmStatus : alarmStatuss){
			if(alarmStatus.value.equals(value)){
				return alarmStatus;
			}
		}
		
		throw new TradeException(TradeReturnCodeEnum.ORDER_STATUS_QUERY_TYPE,value);
	}
}
