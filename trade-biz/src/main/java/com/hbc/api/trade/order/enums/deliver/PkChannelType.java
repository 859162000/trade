/**
 * @Author lukangle
 * @2015年11月11日@上午11:03:16
 */
package com.hbc.api.trade.order.enums.deliver;


public enum PkChannelType {
	//PK模式：1服务优先，2收入优先，3响应优先
	ServicePriory(1, "1服务优先"),
	IncomePriory(2, "收入优先"),
	RspPriory(3, "响应优先"), 
	;
	
	public Integer value;
	public String name;
	PkChannelType(Integer value,String name){
		this.value = value;
		this.name = name;
	}
	
	public static PkChannelType getType(Integer value){
		PkChannelType[] deliverTypes = PkChannelType.values();
		for(PkChannelType deliverType : deliverTypes){
			if(deliverType.value.equals(value)){
				return deliverType;
			}
		}
		return null;
	}
}
