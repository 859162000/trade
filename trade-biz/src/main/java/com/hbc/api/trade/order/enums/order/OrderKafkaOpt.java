/**
 * @Author lukangle
 * @2015年10月7日@下午5:08:59
 */
package com.hbc.api.trade.order.enums.order;

import com.hbc.api.trade.order.enums.TradeReturnCodeEnum;
import com.hbc.api.trade.order.exception.TradeException;

public enum OrderKafkaOpt {
	add(1, "新增"), 
	edit(2,"修改"), 
	statechange(3,"状态变更");
	
	public Integer value;
	public String name;
	OrderKafkaOpt(Integer value,String name){
		this.value = value;
		this.name = name;
	}
	
	public static OrderKafkaOpt getType(Integer value){
		OrderKafkaOpt[] orderKafkaOpts = OrderKafkaOpt.values();
		for(OrderKafkaOpt orderKafkaOpt : orderKafkaOpts){
			if(orderKafkaOpt.value.equals(value)){
				return orderKafkaOpt;
			}
		}
		
		throw new TradeException(TradeReturnCodeEnum.ORDER_TYPE,value);
	}
}
