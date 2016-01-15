package com.hbc.api.trade.order.enums.order;

import com.hbc.api.trade.order.enums.TradeReturnCodeEnum;
import com.hbc.api.trade.order.exception.TradeException;

/**
 * GGDS 查询条件中的订单状态
 * @author Jongly Ran
 */
public enum GDSOrderStatus {
	
	/** 1: 等待支付*/
	INITSTATE(1,"等待支付"), 

	/** 2: 等待服务*/
	NOT_START(2,"等待服务"), 
	
	/** 3: 服务中*/
	SERVICING(3,"服务中"), 
	
	/** 4: 已完成*/
	COMPLETED(4,"已完成"), 

	/** 5: 已取消*/
	CANCELLED(5,"已取消"), 

	/** 6: 全部*/
	ALL(6,"全部");


	public Integer value;
	public String name;
	GDSOrderStatus(int value,String name){
		this.value = value;
		this.name = name;
	}
	
	public static GDSOrderStatus getStatus(Integer value){
		GDSOrderStatus[] otypes = GDSOrderStatus.values();
		for(GDSOrderStatus orderStatus : otypes){
			if(orderStatus.value.equals(value)){
				return orderStatus;
			}
		}
		
		throw new TradeException(TradeReturnCodeEnum.ORDER_STATUS_QUERY_TYPE,value);
	}
}
