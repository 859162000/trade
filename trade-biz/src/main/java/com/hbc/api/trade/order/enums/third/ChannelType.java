/**
 * @Author lukangle
 * @2015年12月3日@下午9:10:20
 */
package com.hbc.api.trade.order.enums.third;


public enum ChannelType {
	//1OTA,2电商，3旅航，4其他,5直客

	OTA(1, "OTA"), 
	AirTravel(3,"旅航"),
	Straight(5,"直客"),
	;

	public Integer value;
	public String name;
	ChannelType(int value,String name){
		this.value = value;
		this.name = name;
	}
	
	public static ChannelType getType(Integer value){
		ChannelType[] carTypeEnums = ChannelType.values();
		for(ChannelType carTypeEnum : carTypeEnums){
			if(carTypeEnum.value.equals(value)){
				return carTypeEnum;
			}
		}
		return null;
	}
}
