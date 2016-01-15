/**
 * @Author lukangle
 * @2015年10月7日@下午5:08:59
 */
package com.hbc.api.trade.coupon.enums;

public enum CouponKafkaOperation {
	/** 1: 添加 */
	ADD(1, "添加"), 

	/** 2: 修改 */
	UPDATE(2,"修改");
	
	public int value;
	public String name;
	
	CouponKafkaOperation(int value,String name){
		this.value = value;
		this.name = name;
	}
}
