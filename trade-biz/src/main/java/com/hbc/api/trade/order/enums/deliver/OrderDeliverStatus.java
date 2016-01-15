/**
 * @Author lukangle
 * @2015年11月6日@下午6:18:36
 */
package com.hbc.api.trade.order.enums.deliver;

import com.hbc.api.trade.order.enums.TradeReturnCodeEnum;
import com.hbc.api.trade.order.exception.TradeException;

/**
 * 对应order主表
 */
public enum OrderDeliverStatus {
	init(0, "初始态"), 
	deliving(1, "发单中"), 
	deliversuccess(2,"确认导游"), 
	deliverfailed(3,"确认失败"),
	predelive(4,"预发单"),
	;
	
	public Integer value;
	public String name;
	OrderDeliverStatus(Integer value,String name){
		this.value = value;
		this.name = name;
	}
	
	public static OrderDeliverStatus getType(Integer value){
		OrderDeliverStatus[] orderDeliverStatus = OrderDeliverStatus.values();
		for(OrderDeliverStatus orderDeliverStatu : orderDeliverStatus){
			if(orderDeliverStatu.value.equals(value)){
				return orderDeliverStatu;
			}
		}
		throw new TradeException(TradeReturnCodeEnum.ORDER_DELIVER_TYPE,value);
	}
}
