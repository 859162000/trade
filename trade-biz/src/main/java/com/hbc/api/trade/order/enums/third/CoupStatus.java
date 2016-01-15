/**
 * @Author lukangle
 * @2015年11月9日@上午10:08:54
 */
package com.hbc.api.trade.order.enums.third;


public enum CoupStatus {
	STATUS_INIT(0, "初始化"), STATUS_BINDED(1, "已绑定"), STATUS_USED(2, "已使用"), STATUS_INVALID(-1, "已废弃"), STATUS_LOCKED(98,
			"已锁定");
	public Integer value;
	public String name;
	CoupStatus(int value,String name){
		this.value = value;
		this.name = name;
	}
	
	public static CoupStatus getType(Integer value){
		CoupStatus[] carTypeEnums = CoupStatus.values();
		for(CoupStatus coupStatus : carTypeEnums){
			if(coupStatus.value.equals(value)){
				return coupStatus;
			}
		}
		return null;
	}
}
