/**
 * @Author lukangle
 * @2015年10月7日@上午10:59:51
 */
package com.hbc.api.trade.order.enums.order;

import com.hbc.api.trade.order.enums.TradeReturnCodeEnum;
import com.hbc.api.trade.order.exception.TradeException;

public enum GOrderStatus {
	
	/** 1: 待接订单*/
	INITSTATE(1,"待接订单"), 

	/** 2: 已表态，包含同意接单但被别人抢走、拒绝接单*/
	AGREE(2,"已表态"), 
	/** 3: 导已接订单*/
	GET(3,"已接订单"), 

	/** 4: 已到达*/
	ARRIVED(4,"已到达"), 

	/** 5: 接到客人*/
	PICKED_UP(5,"接到客人"), 

	/** 6: 服务完成*/
	STROKE_END(6,"服务完成"), 

	/** 7: 未评价*/
	NOT_EVALUATED(7,"未评价"), 
	
	/** 8: 已完成*/
	COMPLETE(8,"已完成"),

	/** 9: 已取消*/
	CANCELLED(9,"已取消"), 

	/** 10: 客诉处理中*/
	COMPLAINT(10,"客诉处理中");
	
	public Integer value;
	public String name;
	GOrderStatus(Integer value,String name){
		this.value = value;
		this.name = name;
	}
	
	public static GOrderStatus getStatus(Integer value){
		GOrderStatus[] otypes = GOrderStatus.values();
		for(GOrderStatus orderStatus : otypes){
			if(orderStatus.value.equals(value)){
				return orderStatus;
			}
		}
		
		throw new TradeException(TradeReturnCodeEnum.ORDER_STATUS_TYPE,value);
	}
}
