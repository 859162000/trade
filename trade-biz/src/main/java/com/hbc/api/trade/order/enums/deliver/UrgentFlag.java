/**
 * @Author lukangle
 * @2015年11月6日@下午7:54:17
 */
package com.hbc.api.trade.order.enums.deliver;



public enum UrgentFlag {
	nomal(0, "普通订单"), 
	urgent(1, "紧急订单"), 
	;
	
	public Integer value;
	public String name;
	UrgentFlag(Integer value,String name){
		this.value = value;
		this.name = name;
	}
	
	public static UrgentFlag getType(Integer value){
		UrgentFlag[] urgentFlags = UrgentFlag.values();
		for(UrgentFlag urgentFlag : urgentFlags){
			if(urgentFlag.value.equals(value)){
				return urgentFlag;
			}
		}
		return UrgentFlag.nomal;
	}
}
