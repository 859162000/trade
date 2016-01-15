/**
 * @Author lukangle
 * @2015骞�11鏈�19鏃涓嬪崍7:43:39
 */
package com.hbc.api.trade.order.enums.order;


public enum RefundConfType {
	/** 1: 鎺ラ�佹 */
	SPT(1, "接送次"),

	/** 2: 鏃ョ 绮惧搧璺嚎 */
	DC(2, "日租"),

	;
	public Integer value;
	public String name;

	RefundConfType(int value, String name) {
		this.value = value;
		this.name = name;
	}

	public static RefundConfType getType(Integer value) {
		RefundConfType[] otypes = RefundConfType.values();
		for (RefundConfType orderType : otypes) {
			if (orderType.value .equals(value)) {
				return orderType;
			}
		}
		return null;
	}

}
