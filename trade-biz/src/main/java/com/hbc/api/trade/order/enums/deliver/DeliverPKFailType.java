package com.hbc.api.trade.order.enums.deliver;

import com.hbc.api.trade.order.enums.TradeReturnCodeEnum;
import com.hbc.api.trade.order.exception.TradeException;

public enum DeliverPKFailType {
	
//	PK 失败的原因：1-禁止接单；2-行程冲突；3-评分；4-抢单速度；5-RP;6-已串单
	forbidpick(1, "禁止接单"), //预处理状态，前端过滤掉该状态
	conflict(2,"行程冲突"),
	grade(3,"评分"),
	accepttime(4,"抢单速度"),
	rp(5,"RP"),
	serial(6,"已串单"),
	price(7,"价格");
	
	public Integer value;
	public String name;
	DeliverPKFailType(int value,String name){
		this.value = value;
		this.name = name;
	}
	
	public static DeliverPKFailType getType(Integer value){
		DeliverPKFailType[] deliverPKFailTypes = DeliverPKFailType.values();
		for(DeliverPKFailType deliverPKFailType : deliverPKFailTypes){
			if(deliverPKFailType.value.equals(value)){
				return deliverPKFailType;
			}
		}
		
		throw new TradeException(TradeReturnCodeEnum.ORDER_DELIVER_STATUSEXP,value);
	}
}
