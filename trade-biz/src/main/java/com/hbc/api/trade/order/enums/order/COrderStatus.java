/**
 * @Author lukangle
 * @2015年10月7日@上午10:59:51
 */
package com.hbc.api.trade.order.enums.order;

import com.hbc.api.trade.order.enums.TradeReturnCodeEnum;
import com.hbc.api.trade.order.exception.TradeException;

public enum COrderStatus {
	
	/** 1: 未付款*/
	INITSTATE(1,"未付款"), 

	/** 2: 已付款*/
	PAYSUCCESS(2,"已付款"), 
	
	/** 3: 已接单*/
	AGREE(3,"已接单"), 

	/** 4: 已到达*/
	ARRIVED(4,"已到达"), 

	/** 5: 服务中*/
	SERVICING(5,"服务中"), 

	/** 6: 未评价*/
	NOT_EVALUATED(6,"未评价"),
	
	/** 7: 已完成*/
	COMPLETE(7,"已完成"), 

	/** 8: 已取消*/
	CANCELLED(8,"已取消"), 
	
	/** 9: 已退款*/
	REFUNDED(9,"已退款"),

	/** 10: 客诉处理中*/
	COMPLAINT(10,"客诉处理中");
	


	public Integer value;
	public String name;
	COrderStatus(int value,String name){
		this.value = value;
		this.name = name;
	}
	
	public static COrderStatus getStatus(Integer value){
		COrderStatus[] otypes = COrderStatus.values();
		for(COrderStatus orderStatus : otypes){
			if(orderStatus.value.equals(value)){
				return orderStatus;
			}
		}
		
		throw new TradeException(TradeReturnCodeEnum.ORDER_STATUS_TYPE,value);
	}
}
