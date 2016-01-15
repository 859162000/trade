/**
 * @Author lukangle
 * @2015年10月7日@上午10:59:51
 */
package com.hbc.api.trade.order.enums.order;

import com.hbc.api.trade.order.enums.TradeReturnCodeEnum;
import com.hbc.api.trade.order.exception.TradeException;

/**
 * 如果更新，请更新主状态对CAPP，GAPP，GDS、MIS、Agency的适配工具： OrderStatusAdaptor
 */
public enum OrderStatus {

	/* 支付前 */
	/** 1001: 未付款*/
	INITSTATE(1001,"未付款"), 
	
	/** 1010: 已取消未付款*/
	CANCLE_CLOSE_NOPAY(1010,"已取消未付款"),

	/** 1020: 付款超时关闭*/
	PAY_OUTTIME_CLOSE(1020,"付款超时关闭"), // 对应app取消

	/* 支付后 */
	
	/** 2001: 付款成功*/
	PAYSUCCESS(2001,"付款成功"), 

	/** 2030: 导游到达*/
	GUIDE_ARRIVED(2030,"导游到达"), 

	/** 2050: 接到客人*/
	PICK_CUSTOMER(2050,"接到客人"), 

	/** 2070: 行程结束*/
	STROKE_END(2070,"行程结束"), 
	
	/** 2080: 确认费用（target：导游）*/
	CONFIRMED_COST(2080,"确认费用"),
	
	/** 2100: 结算完成（target：导游）*/
	SETTLEMENT(2100,"结算完成"), // 后台服务处理

	/** 3001: 客诉退款（服务已开始）*/
	CANCEL_SERVICEED(3001,"客诉退款（服务已开始）"), 
	
	/** 3002: 已取消已服务*/
	CANCLE_CLOSE_PAY_SERVICE(3002,"已取消已服务"),
	
	/** 3003: 客诉退款（服务未开始）*/
	CANCEL_NOSERVICE(3003,"客诉退款（服务未开始）"),
	
	/** 3004: 客诉处理中*/
	DISPUTING(3004,"客诉处理中"), 
	
	/** 3005: 取消退款*/
	CANCEL_CLOSE(3005,"取消退款"),  

	/** 3007: 已取消已付款*/
	CANCELING(3007,"已取消已付款"),
	;
	
	public Integer value;
	public String name;
	OrderStatus(int value,String name){
		this.value = value;
		this.name = name;
	}
	
	public static OrderStatus getStatus(Integer value){
		OrderStatus[] otypes = OrderStatus.values();
		for(OrderStatus orderStatus : otypes){
			if(orderStatus.value.equals(value)){
				return orderStatus;
			}
		}
		
		throw new TradeException(TradeReturnCodeEnum.ORDER_STATUS_TYPE,value);
	}
}
