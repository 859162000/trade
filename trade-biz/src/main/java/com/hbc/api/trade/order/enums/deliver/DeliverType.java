/**
 * @Author lukangle
 * @2015年10月24日@下午11:03:26
 */
package com.hbc.api.trade.order.enums.deliver;

import com.hbc.api.trade.order.enums.TradeReturnCodeEnum;
import com.hbc.api.trade.order.exception.TradeException;

public enum DeliverType {
	/**
	 * 类型：1-正常发单；2-立即发单；3-增量补发；4-取消重发 5 指派导游 6 支付前预指派导游
	 */
	Ordinary(1, "普通发单"),
	Immediately(2, "立即发单"),
	Incremental_Send(3, "增量补发"), 
	Cancle_Send(4,"取消重发"), 
	assign_GUIDE(5,"指派导游"),
	PRE_ASSIGN_GUIDE(6,"支付前指派导游"),
	;
	
	public Integer value;
	public String name;
	DeliverType(Integer value,String name){
		this.value = value;
		this.name = name;
	}
	
	public static DeliverType getType(Integer value){
		DeliverType[] deliverTypes = DeliverType.values();
		for(DeliverType deliverType : deliverTypes){
			if(deliverType.value.equals(value)){
				return deliverType;
			}
		}
		
		throw new TradeException(TradeReturnCodeEnum.ORDER_DELIVER_STATUSEXP,value);
	}
}
