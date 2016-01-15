/**
 * @Author lukangle
 * @2015年10月24日@下午11:01:35
 */
package com.hbc.api.trade.order.enums.deliver;

import com.hbc.api.trade.order.enums.TradeReturnCodeEnum;
import com.hbc.api.trade.order.exception.TradeException;

/**
 * 对应trade_deliver表
 */
public enum TradeDeliverStatus {
	predeliver(0, "未发单"), 
	delivered(1,"已发送导游"),
	resenddelivered(5,"取消重发2次发单"),
	pkend(2,"接单成功 pk成功"),
	pushSended(3,"接单成功并且消息已经发送"),
	deliverfailed(4,"pk失败"),
	;
	
	public Integer value;
	public String name;
	TradeDeliverStatus(Integer value,String name){
		this.value = value;
		this.name = name;
	}
	
	public static TradeDeliverStatus getType(Integer value){
		TradeDeliverStatus[] deliverStatus = TradeDeliverStatus.values();
		for(TradeDeliverStatus deliverStatu : deliverStatus){
			if(deliverStatu.value.equals(value)){
				return deliverStatu;
			}
		}
		
		throw new TradeException(TradeReturnCodeEnum.ORDER_DELIVER_STATUSEXP,value);
	}
}
