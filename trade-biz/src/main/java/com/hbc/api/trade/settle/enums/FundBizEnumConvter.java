/**
 * @Author lukangle
 * @2015年11月19日@下午5:56:37
 */
package com.hbc.api.trade.settle.enums;

import com.hbc.api.fund.account.enums.BizType;
import com.hbc.api.trade.order.enums.order.OrderType;

public class FundBizEnumConvter {
	public static BizType getFundBizType(OrderType orderType) {
		switch (orderType) {
		case PICKUPORDER:
			return BizType.PICKUP;
		case TRANSFER:
			return BizType.TRANSFER;
		case DAILY:
			return BizType.DAILY;
		case PERUSE:
			return BizType.SINGLE;
		case COMMENDATION:
			return BizType.COMMENDATION;
		default:
			return null;
		}
	}
	
	/**
	 * 退款时获取 资金业务类型
	 * @param orderType
	 * @return
	 */
	public static BizType getRefundBizType(OrderType orderType) {
		switch (orderType) {
		case PICKUPORDER:
			return BizType.CANCAL_PICKUP;
		case TRANSFER:
			return BizType.CANCAL_TRANSFER;
		case DAILY:
			return BizType.CANCAL_DAILY;
		case PERUSE:
			return BizType.CANCLE_SINGLE;
		case COMMENDATION:
			return BizType.CANCAL_COMMNENDATION;
		default:
			return null;
		}
	}
}
