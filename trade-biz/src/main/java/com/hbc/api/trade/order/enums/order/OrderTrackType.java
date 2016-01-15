
package com.hbc.api.trade.order.enums.order;

import com.hbc.api.trade.order.enums.TradeReturnCodeEnum;
import com.hbc.api.trade.order.exception.TradeException;

/**
 * 
 * 行程动态类型。1-接下订单；2-航班延误；3-航班起飞；4-航班落地；5-已到达接车地点；6-已接到客人；
 * 7-已完成服务；8-导游评价客人；9-客人评价导游；99-订单被取消；-1-客人取消订单；98-订单被修改；
 * 10-服务费用结算完成，11-改派订单，12-订单奖金,13-航班取消，14-航班备降，15-航班返航,
 * 16-您确认了服务费用，并提交了增项费用申请
 * 
 * @author Jongly Ran
 */
public enum OrderTrackType {
	/** 1: 接下订单 */
	OBTAIN_ORDER(1, "接下订单"), 
	
	/** 5: 已到达接车地点 */
	ARRIVED(5, "已到达接车地点"), 
	
	/** 6: 已接到客人 */
	PICKEDUP(6,"已接到客人"), 
	
	/** 7: 已完成服务 */
	COMPLETED(7,"已完成服务"),
	
	/** 8: 导游评价客人 */
	APPRAISE_BY_GUIDE(8,"导游评价客人"), 	
	
	/** 9: 客人评价导游 */
	APPRAISE_BY_CUSTOMER(9,"客人评价导游"), 	
	
	/** 10: 服务费用结算完成 */
	SETTLEMENT_COMPLETED(10,"服务费用结算完成"), 	
	
	OPRDER_FROZE(11, "客服冻结订单"),

	/** 99: 订单被取消 */
	CANCELLED(99,"订单被取消"), 	
	
	/** 98: 订单被修改 */
	UPDATED(98,"订单被修改"),
	
	/** 16: 已确认费用 */
	COMFIRMED_COST(16,"已确认费用"),
	
	FLIGHT_FLY(100,"飞机起飞"),
	
	FLIGHT_ARRIVE(101,"飞机到达"),
	
	FLIGHT_DELAY(102,"航班延误"),
	FLIGHT_CANCLE(103,"航班取消"),
	
	FLIGHT_TERNATEARRIVE(104,"航班备降"),
	
	FLIGHT_STATUSBACK(105,"航班返回"),
	;
	public Integer value;
	public String name;
	
	OrderTrackType(int value,String name){
		this.value = value;
		this.name = name;
	}
	
	public static OrderTrackType getType(Integer value){
		OrderTrackType[] otypes = OrderTrackType.values();
		for(OrderTrackType orderType : otypes){
			if(orderType.value.equals(value)){
				return orderType;
			}
		}
		
		throw new TradeException(TradeReturnCodeEnum.ORDER_TRACK_TYPE);
	}

	/**
	 * 如果不存在，抛出TradeException
	 */
	public static void isValidate(Integer orderType) {
		getType(orderType);
	}
}
