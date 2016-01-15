/**
 * @Author lukangle
 * @2015年10月22日@上午9:39:52
 */
package com.hbc.api.trade.pay.enums;

public enum RefundStatus {
	SUCCESS(1,"成功"), 
	FAILED(2,"失败"),
	UNKNOWN(3,"未知");
	public int value;
	public String name;
	RefundStatus(int value,String name){
		this.value = value;
		this.name = name;
	}
	
	public static RefundStatus getType(int value){
		RefundStatus[] refundStatus = RefundStatus.values();
		for(RefundStatus refundStatu : refundStatus){
			if(refundStatu.value==value){
				return refundStatu;
			}
		}
		return null;
	}
}
