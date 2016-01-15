/**
 * @Author lukangle
 * @2015年12月2日@下午7:49:24
 */
package com.hbc.api.trade.order.enums.alarm;


public enum AlarmFlag {
	/*0-初始化（默认）；1-成功；2：失败*/
	NOSEND(1,"未发送"),
	SENDED(2,"已发送");
	
	public Integer value;
	public String name;
	AlarmFlag(int value,String name){
		this.value = value;
		this.name = name;
	}
	
	public static AlarmFlag getStatus(Integer value){
		AlarmFlag[] alarmStatuss = AlarmFlag.values();
		for(AlarmFlag alarmStatus : alarmStatuss){
			if(alarmStatus.value.equals(value)){
				return alarmStatus;
			}
		}
		return null;
	}
}
