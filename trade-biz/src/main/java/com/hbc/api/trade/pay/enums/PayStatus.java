/**
 * @Author lukangle
 * @2015年10月17日@下午6:06:35
 */
package com.hbc.api.trade.pay.enums;


public enum PayStatus {
	INITED(0,"初始态"), 
	SUCCESS(1,"成功"), 
	FAILED(2,"失败")
	;
	
	public int value;
	public String name;
	PayStatus(int value,String name){
		this.value = value;
		this.name = name;
	}
	
	public static PayStatus getType(int value){
		PayStatus[] payStatus = PayStatus.values();
		for(PayStatus payStatu : payStatus){
			if(payStatu.value==value){
				return payStatu;
			}
		}
		return null;
	}
}
