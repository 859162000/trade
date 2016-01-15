
package com.hbc.api.trade.order.enums.order;

import com.hbc.api.trade.order.enums.TradeReturnCodeEnum;
import com.hbc.api.trade.order.exception.TradeException;
/**
 * 
 * @author Jongly Ran
 */
public enum KafkaOperation {
	ADD(1, "新增"), 
	EDIT(2,"修改");
	
	public int value;
	public String name;
	KafkaOperation(int value,String name){
		this.value = value;
		this.name = name;
	}
	
	public static KafkaOperation getType(Integer value){
		KafkaOperation[] orderKafkaOpts = KafkaOperation.values();
		for(KafkaOperation orderKafkaOpt : orderKafkaOpts){
			if(orderKafkaOpt.value==value){
				return orderKafkaOpt;
			}
		}
		
		throw new TradeException(TradeReturnCodeEnum.ORDER_TYPE,value);
	}
}
