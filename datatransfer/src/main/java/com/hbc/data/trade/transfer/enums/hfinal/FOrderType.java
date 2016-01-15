package com.hbc.data.trade.transfer.enums.hfinal;

import com.hbc.api.trade.order.enums.TradeReturnCodeEnum;
import com.hbc.api.trade.order.exception.TradeException;

public enum FOrderType {
	/**订单类型。1-接机；2-送机；3-日租；4-次租**/
	/** 1: 接机 */
	PICKUPORDER(1, "接机"),

	/** 2: 送机 */
	TRANSFER(2, "送机"),

	/** 3: 日租 */
	DAILY(3, "日租"),

	/** 4: 次租 */
	PERUSE(4, "次租"),

	;
	public Integer value;
	public String name;

	FOrderType(int value, String name) {
		this.value = value;
		this.name = name;
	}

	public static FOrderType getType(Integer value) {
		FOrderType[] otypes = FOrderType.values();
		for (FOrderType	 orderType : otypes) {
			if (orderType.value .equals( value)) {
				return orderType;
			}
		}

		throw new TradeException(TradeReturnCodeEnum.ORDER_TYPE);
	}
}
