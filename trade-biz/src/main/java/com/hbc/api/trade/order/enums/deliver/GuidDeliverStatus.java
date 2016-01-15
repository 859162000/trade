/**
 * @Author lukangle
 * @2015年10月26日@下午7:45:28
 */
package com.hbc.api.trade.order.enums.deliver;

import com.hbc.api.trade.order.enums.TradeReturnCodeEnum;
import com.hbc.api.trade.order.exception.TradeException;

/**
 * 对应Trade_deliver_guide表 
 */
public enum GuidDeliverStatus {
	nosend(1, "未发单"), //预处理状态，前端过滤掉该状态
	sendpush(2,"已发送给导游 消息已经PUSH"),
	success(3,"接单成功"),
	successsend(4,"接单成功并且消息已经发送"),
	pkfailed(5,"pk失败"),
	resend(6,"取消重发"),
	pkfailedPush(7,"pk失败并且消息已经发送"),;
	
	public Integer value;
	public String name;
	GuidDeliverStatus(Integer value,String name){
		this.value = value;
		this.name = name;
	}
	
	public static GuidDeliverStatus getType(Integer value){
		GuidDeliverStatus[] deliverStatus = GuidDeliverStatus.values();
		for(GuidDeliverStatus deliverStatu : deliverStatus){
			if(deliverStatu.value.equals(value)){
				return deliverStatu;
			}
		}
		
		throw new TradeException(TradeReturnCodeEnum.ORDER_DELIVER_STATUSEXP,value);
	}
}
