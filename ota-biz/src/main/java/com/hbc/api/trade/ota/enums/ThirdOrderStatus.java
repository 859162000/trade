package com.hbc.api.trade.ota.enums;

import com.hbc.api.trade.order.enums.TradeReturnCodeEnum;
import com.hbc.api.trade.order.exception.TradeException;

public enum ThirdOrderStatus {

	PAYSUCCESS(1001,"下单成功"), 
	ORDER_CONFIRMED(1003,"订单已确认"), 
	GUIDE_CONFIRM(1002,"导游已接单"), 
	ORDER_CANCEL(4002,"订单取消"),
	ORDER_COMPLETE(6001,"交易完成"),
	ORDER_COMPLETE_EXP(9001,"交易完成 携程调度失败"); // 跟乐乐确认，是否可以删除
	
	public int value;
	public String name;

	ThirdOrderStatus(int value,String name){
		this.value = value;
		this.name = name;
	}
	
	public static ThirdOrderStatus getStatus(int value) {
		ThirdOrderStatus[] otypes = ThirdOrderStatus.values();
		for (ThirdOrderStatus thirdOrderStatus : otypes) {
			if (thirdOrderStatus.value==value) {
				return thirdOrderStatus;
			}
		}
		throw new TradeException(TradeReturnCodeEnum.ORDER_STATUS_TYPE, value);
	}
}