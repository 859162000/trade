/**
 * @Author lukangle
 * @2015年11月9日@下午8:12:33
 */
package com.hbc.api.trade.order.service.deliver.conf;

public enum OpenSer {
	/** 1: 开通*/
	OPEN(1, "开通"),

	/** 2: 不开通 */
	NOTOPEN(2, "不开通"),

	;
	public int value;
	public String name;

	OpenSer(int value, String name) {
		this.value = value;
		this.name = name;
	}

	public static OpenSer getType(int value) {
		OpenSer[] otypes = OpenSer.values();
		for (OpenSer orderType : otypes) {
			if (orderType.value == value) {
				return orderType;
			}
		}

		throw null;
	}

}
