package com.hbc.api.trade.order.enums.deliver;

import com.hbc.api.trade.order.enums.TradeReturnCodeEnum;
import com.hbc.api.trade.order.exception.TradeException;

public enum OrderSerialFlag {
	
	//0 正常订单 1表示串单 2表示被串单
	/** 0:正常订单 */
	NORMAL(0, "正常订单"),
	
	/** 1:串单 */
	SERIAL(1, "串单"),
	/** 2:被串单 */
	SERIALED(2, "被串单");
	
	public Integer value;
	public String name;
	
	OrderSerialFlag(Integer value,String name){
		this.value = value;
		this.name = name;
	}
	
	public static OrderSerialFlag getType(Integer value){
		OrderSerialFlag[] orderSerialFlags = OrderSerialFlag.values();
		for(OrderSerialFlag orderSerialFlag : orderSerialFlags){
			if(orderSerialFlag.value.equals(value)){
				return orderSerialFlag;
			}
		}
		return OrderSerialFlag.NORMAL;
	}
}
