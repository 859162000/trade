/**
 * @Author lukangle
 * @2015年11月9日@下午8:12:20
 */
package com.hbc.api.trade.order.service.deliver.conf;

public enum IsChildSupport {
	/** 1: 接机 */
	SUPPORT(1, "支持"),

	/** 3: 日租 */
	NOTSUPPORT(2, "日租"),

	;
	public int value;
	public String name;

	IsChildSupport(int value, String name) {
		this.value = value;
		this.name = name;
	}

	public static IsChildSupport getType(int value) {
		IsChildSupport[] otypes = IsChildSupport.values();
		for (IsChildSupport orderType : otypes) {
			if (orderType.value == value) {
				return orderType;
			}
		}

		throw null;
	}
}
