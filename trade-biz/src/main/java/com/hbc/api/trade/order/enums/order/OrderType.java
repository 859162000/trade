/**
 * @Author lukangle
 * @2015年10月6日@下午7:50:53
 */
package com.hbc.api.trade.order.enums.order;

import com.hbc.api.trade.order.enums.TradeReturnCodeEnum;
import com.hbc.api.trade.order.exception.TradeException;
import com.hbc.api.trade.order.mapping.gen.bean.OrderBean;

/**
 * 订单类型。1-接机；2-送机；3-日租；4-次租
 */
public enum OrderType {
	/** 1: 接机 */
	PICKUPORDER(1, "接机"),

	/** 2: 送机 */
	TRANSFER(2, "送机"),

	/** 3: 日租 */
	DAILY(3, "日租"),

	/** 4: 次租 */
	PERUSE(4, "次租"),

	/** 5: 精品线路 */
	COMMENDATION(5, "精品线路");

	public Integer value;
	public String name;

	OrderType(int value, String name) {
		this.value = value;
		this.name = name;
	}

	public static OrderType getType(Integer value) {
		OrderType[] otypes = OrderType.values();
		for (OrderType orderType : otypes) {
			if (orderType.value .equals( value)) {
				return orderType;
			}
		}

		throw new TradeException(TradeReturnCodeEnum.ORDER_TYPE);
	}
	
	public static OrderType getTypeByGoodeType(OrderBean orderBean) {
		OrderType orderType = getType(orderBean.getOrderType());
		if(OrderType.COMMENDATION.equals(orderType)){
			GoodType goodType = GoodType.getType(orderBean.getOrderGoodsType());
			if(GoodType.PERUSE.equals(goodType)){
				return PERUSE;
			}else if(GoodType.PICKUPORDER.equals(goodType)){
				return PICKUPORDER;
			}else if(GoodType.TRANSFER.equals(goodType)){
				return TRANSFER;
			}else{
				return DAILY;
			}
		}else{
			return orderType;
		}
	}
	

	/**
	 * 如果不存在，抛出TradeException
	 */
	public static void isValidate(Integer orderType) {
		getType(orderType);
	}

}
