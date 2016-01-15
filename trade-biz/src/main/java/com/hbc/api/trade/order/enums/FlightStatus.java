/**
 * @Author lukangle
 * @2015年12月3日@下午5:54:25
 */
package com.hbc.api.trade.order.enums;


public enum FlightStatus {
	STATUS_PLAN("计划"),
	STATUS_TAKE_OFF("起飞"),
	STATUS_ARRIVE("到达"),
	STATUS_DELAY("延误"),
	STATUS_CANCEL("取消"),
	STATUS_ALTERNATE_ARRIVE("备降"),
	STATUS_BACK("返航"),
	;
	
	public String name;

	private FlightStatus(String name){
		this.name = name;
	}
	
	public static FlightStatus getType(String value){
		FlightStatus[] carClassEnums = FlightStatus.values();
		for(FlightStatus carClassEnum : carClassEnums){
			if(carClassEnum.name.equals(value)){
				return carClassEnum;
			}
		}
		
		return null;
	}
}
