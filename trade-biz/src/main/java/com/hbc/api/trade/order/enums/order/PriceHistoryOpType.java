/**
 * @Author lukangle
 * @2015年10月6日@下午7:50:53
 */
package com.hbc.api.trade.order.enums.order;

import com.hbc.api.trade.order.enums.TradeReturnCodeEnum;
import com.hbc.api.trade.order.exception.TradeException;

/**
 * 订单类型。1-接机；2-送机；3-日租；4-次租
 * trade_price_history optype
 */
public enum PriceHistoryOpType {
	/** 1: 接机 */
    SER_ORDER(1,"串单"),
    MIS_EDIT_GUIDEPRICE(2,"管理员修改导游价"),
    DELIVER_GUIDE_LEVEL(3,"发单导游级别降价"),
    REWARD(4,"发单导游级别降价"),
    ;

	public Integer value;
	public String name;

	PriceHistoryOpType(int value, String name) {
		this.value = value;
		this.name = name;
	}

	public static PriceHistoryOpType getType(Integer value) {
		PriceHistoryOpType[] otypes = PriceHistoryOpType.values();
		for (PriceHistoryOpType orderType : otypes) {
			if (orderType.value .equals( value)) {
				return orderType;
			}
		}

		throw new TradeException(TradeReturnCodeEnum.ORDER_TYPE);
	}


	/**
	 * 如果不存在，抛出TradeException
	 */
	public static void isValidate(Integer orderType) {
		getType(orderType);
	}

}
