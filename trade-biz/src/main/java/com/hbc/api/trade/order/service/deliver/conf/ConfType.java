/**
 * @Author lukangle
 * @2015年11月9日@下午8:06:58
 */
package com.hbc.api.trade.order.service.deliver.conf;

/**
 * 1 配置类型 1接送机
 */
public enum ConfType {
	/** 1: 接机 */
	PICKUPORDER(1, "接送次"),

	/** 3: 日租 */
	DAILY(2, "日租"),

	;
	public int value;
	public String name;

	ConfType(int value, String name) {
		this.value = value;
		this.name = name;
	}

	public static ConfType getType(int value) {
		ConfType[] otypes = ConfType.values();
		for (ConfType orderType : otypes) {
			if (orderType.value == value) {
				return orderType;
			}
		}

		throw null;
	}

}
