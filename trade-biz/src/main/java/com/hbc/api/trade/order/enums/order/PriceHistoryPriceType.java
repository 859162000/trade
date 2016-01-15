/**
 * @Author lukangle
 * @2015年12月5日@下午5:37:30
 */
package com.hbc.api.trade.order.enums.order;


public enum PriceHistoryPriceType {
	/** 1: 接机 */
    GUIDE_PRICE_EDIT(1,"导游价修改"),
    CHANNEL_PRICE_EDIT(2,"渠道价修改"),
    ;

	public Integer value;
	public String name;

	PriceHistoryPriceType(int value, String name) {
		this.value = value;
		this.name = name;
	}

	public static PriceHistoryPriceType getType(Integer value) {
		PriceHistoryPriceType[] otypes = PriceHistoryPriceType.values();
		for (PriceHistoryPriceType orderType : otypes) {
			if (orderType.value .equals( value)) {
				return orderType;
			}
		}
		return null;
	}

}
