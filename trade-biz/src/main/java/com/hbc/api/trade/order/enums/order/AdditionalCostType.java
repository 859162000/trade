/**
 * @Author lukangle
 * @2015年10月6日@下午7:50:53
 */
package com.hbc.api.trade.order.enums.order;

import com.hbc.api.trade.order.enums.TradeReturnCodeEnum;
import com.hbc.api.trade.order.exception.TradeException;

/**
 * 增向费用 状态 1 初始态 2为导游已确认付款
 */
public enum AdditionalCostType {
	/** 1: 初始态 */
	INITIAL(1, "初始态"), 
	
	/** 2: 导游已确认付款 */
	CONFIRMED(2,"导游已确认付款"); 	
	
	public Integer value;
	public String name;
	
	AdditionalCostType(int value,String name){
		this.value = value;
		this.name = name;
	}
	
	public static AdditionalCostType getType(Integer value){
		AdditionalCostType[] otypes = AdditionalCostType.values();
		for(AdditionalCostType orderType : otypes){
			if(orderType.value.equals(value)){
				return orderType;
			}
		}
		
		throw new TradeException(TradeReturnCodeEnum.ADDITIONAL_COST_TYPE);
	}

	/**
	 * 如果不存在，抛出TradeException
	 */
	public static void isValidate(Integer orderType) {
		getType(orderType);
	}
}
