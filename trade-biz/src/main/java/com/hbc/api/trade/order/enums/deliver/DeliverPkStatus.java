/**
 * @Author lukangle
 * @2015年11月13日@上午10:36:08
 */
package com.hbc.api.trade.order.enums.deliver;


public enum DeliverPkStatus {
	INIT(0,"初始态"),
	PKING(1,"PK中"),
	/** 3:PK失败 */
	PK_FAILED(2, "PK失败"),
	/** 4:取消重发 */
	PK_SUCCESS(3, "PK成功"),
	;
	
	public Integer value;
	public String name;
	
	DeliverPkStatus(Integer value,String name){
		this.value = value;
		this.name = name;
	}
	
	public static DeliverPkStatus getType(Integer value){
		DeliverPkStatus[] deliverTypes = DeliverPkStatus.values();
		for(DeliverPkStatus deliverType : deliverTypes){
			if(deliverType.value.equals(value)){
				return deliverType;
			}
		}
		
		return null;
	}
}
