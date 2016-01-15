package com.hbc.api.trade.order.enums.alarm;

import com.hbc.api.trade.order.enums.TradeReturnCodeEnum;
import com.hbc.api.trade.order.exception.TradeException;

public enum AlarmTargetType {

	GUIDE(1,"导游"),
	USER(2,"用户"),
	OPERATE(3,"运营");	
	
	public Integer value;
	public String name;
	AlarmTargetType(Integer value,String name){
		this.value = value;
		this.name = name;
	}
	
	public static AlarmTargetType getStatus(Integer value){
		AlarmTargetType[] alarmTargetTypes = AlarmTargetType.values();
		for(AlarmTargetType alarmTargetType : alarmTargetTypes){
			if(alarmTargetType.value.equals(value)){
				return alarmTargetType;
			}
		}
		
		throw new TradeException(TradeReturnCodeEnum.ORDER_STATUS_QUERY_TYPE,value);
	}
}
