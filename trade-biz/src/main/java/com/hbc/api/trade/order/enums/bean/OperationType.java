package com.hbc.api.trade.order.enums.bean;

import com.hbc.api.trade.order.enums.TradeReturnCodeEnum;
import com.hbc.api.trade.order.exception.TradeException;

/**
 * 日志分类。1-代理商行为；2-用户行为；3-导游行为；4-客服行为；5-系统行为；6-地接社行为
 * @author Jongly Ran
 */
public enum OperationType {
	/** 1: 代理商行为*/
	ASSISTENT(1,"代理商行为"), 

	/** 2: 用户行为*/
	CUSTOMER(2,"用户行为"), 

	/** 3: 导游行为*/
    GUIDE(3,"导游行为"), 

	/** 4: 客服行为*/
	CUSTOMER_SERVICE(4,"客服行为"), 

	/** 5: 系统行为*/
	SYSTEM(5,"系统行为"), 

	/** 6:地接社行为*/
	LOCAL_AGENCY(6,"地接社行为");


	public int value;
	public String name;
	OperationType(int value,String name){
		this.value = value;
		this.name = name;
	}
	
	public static OperationType getStatus(Integer value){
		OperationType[] otypes = OperationType.values();
		for(OperationType orderStatus : otypes){
			if(orderStatus.value==value){
				return orderStatus;
			}
		}
		
		throw new TradeException(TradeReturnCodeEnum.OPERATION_TYPE,value);
	}
}
