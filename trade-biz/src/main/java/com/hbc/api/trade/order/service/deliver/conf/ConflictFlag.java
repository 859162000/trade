/**
 * @Author lukangle
 * @2015年11月10日@下午2:04:17
 */
package com.hbc.api.trade.order.service.deliver.conf;

public enum ConflictFlag {
	/** 1: 接机 */
	OPEN(1, "开启"),

	/** 3: 日租 */
	CLOSED(2, "关闭"),

	;
	public int value;
	public String name;

	ConflictFlag(int value, String name) {
		this.value = value;
		this.name = name;
	}

	public static ConflictFlag getType(int value) {
		ConflictFlag[] otypes = ConflictFlag.values();
		for (ConflictFlag orderType : otypes) {
			if (orderType.value == value) {
				return orderType;
			}
		}

		throw null;
	}
}
