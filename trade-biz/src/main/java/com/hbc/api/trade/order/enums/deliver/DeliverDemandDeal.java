/**
 * @Author lukangle
 * @2015年10月26日@下午5:52:28
 */
package com.hbc.api.trade.order.enums.deliver;

import com.hbc.api.trade.order.enums.TradeReturnCodeEnum;
import com.hbc.api.trade.order.exception.TradeException;
/**
 * 0-未表态（默认）1-愿意接  2 不愿意接
 */
public enum DeliverDemandDeal {
	init(0, "未表态（默认）"), 
	accept(1,"愿意接 "), 
	noaccept(2,"不愿意接"),
	;
	
	public Integer value;
	public String name;
	DeliverDemandDeal(int value,String name){
		this.value = value;
		this.name = name;
	}
	
	public static DeliverDemandDeal getType(Integer value){
		DeliverDemandDeal[] deliverDemandDeals = DeliverDemandDeal.values();
		for(DeliverDemandDeal deliverDemandDeal : deliverDemandDeals){
			if(deliverDemandDeal.value.equals(value)){
				return deliverDemandDeal;
			}
		}
		
		throw new TradeException(TradeReturnCodeEnum.DELIVER_DeliverDemandDealExp,value);
	}
}
