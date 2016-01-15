/**
 * @Author lukangle
 * @2015年10月6日@下午7:50:53
 */
package com.hbc.api.trade.order.enums.order;

import com.hbc.api.trade.order.enums.TradeReturnCodeEnum;
import com.hbc.api.trade.order.exception.TradeException;

/**
 * 订单来源1 C端 2 GDS 当为GDS时，取agent_name 3 携程 4 去哪
 */
public enum OrderSource {

	/** 1: C端 */
	C(1, "C端"),

	/** 2: GDS 当为GDS时，取agent_name */
	GDS(2, "GDS"),

	/** 3: OTA */
	OTA(3, "OTA"),

	CTRIP(3, "携程渠道"),

	ALITRIP(4, "去啊渠道"),

	QUNAR(5, "去哪渠道"),

	;

	public Integer value;
	public String name;

	OrderSource(int value, String name) {
		this.value = value;
		this.name = name;
	}

	public static OrderSource getType(Integer value) {
		OrderSource[] otypes = OrderSource.values();
		for (OrderSource orderType : otypes) {
			if (orderType.value.equals(value)) {
				return orderType;
			}
		}

		throw new TradeException(TradeReturnCodeEnum.ORDER_SOURCE_TYPE);
	}

	/**
	 * 如果不存在，抛出TradeException
	 */
	public static void isValidate(Integer orderType) {
		getType(orderType);
	}
}
