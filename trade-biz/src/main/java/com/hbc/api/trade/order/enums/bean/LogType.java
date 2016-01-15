/**
 * @Author lukangle
 * @2015年10月7日@上午10:59:51
 */
package com.hbc.api.trade.order.enums.bean;

import com.hbc.api.trade.order.enums.TradeReturnCodeEnum;
import com.hbc.api.trade.order.exception.TradeException;

public enum LogType {
	/** 1：提交订单*/
	SUBMIT_ORDER(1,"提交订单"), 

	/** 2：补充订单资料（包括部分内容修改）*/
	SUPPLEMENT(2,"补充订单资料（包括部分内容修改）"), 

	/** 3: 付款*/
    PAYMENT(3,"付款"), 

	/** 4: 已确定导游（导游已接单）*/
	CONFIRMED_BY_GUIDE(4,"已确定导游（导游已接单）"), 

	/** 5: 导游已到达预定地点*/
	ARRIVED_APPOINTED_PLACE(5,"导游已到达预定地点"), 

	/** 6: 导游已接到客人（开始出发）*/
	PICKED_UP(6,"导游已接到客人（开始出发）"), 

	/** 7: 已完成服务*/
	COMPLETED_SERVICE(7,"已完成服务"), 

	/** 8: 导游评价客人*/
	GUIDE_EVALUATES_CUSTOMER(8,"导游评价客人"), 

	/** 9: 客人评价导游*/
	CUSTOMER_EVALUATES_GUIDE(9,"客人评价导游"), 
	
	/** 10: 确认费用*/
	CONFIRMS_COST(10,"确认费用"),

	/** 11: 结算完成*/
	PAYMENT_DONE(11,"结算完成"), 

	/** 98: 运营确认分发*/
	OPERATING_CONFIRMS_ASSIGN(98,"运营确认分发"), 

	/** 101: 客人（代理）取消订单*/
	CANCELLED_BY_CUSTOMER(101,"客人（代理）取消订单"),
	
	/** 102: 导游（运营）取消订单*/
	CANCELLED_BY_GUIDE_OR_OPERATING(102,"导游（运营）取消订单"),

	/** 103: 手动添加备注信息*/
	ADD_COMMENTS_MANUALLY(103,"手动添加备注信息"),
	
	/** 104: 运营认领订单*/
	OPERATING_CONFIRMED_ORDER(104,"运营认领订单"),
	
	/** 105: 运营确认退款（存储具体金额）*/
	OPERATING_CONFIRMED_REFUNDED(105,"运营确认退款（存储具体金额）"),
	
	/** 106: 系统自动取消订单（60分钟未支付）*/
	CANCELLED_BY_SYSTEM(106,"系统自动取消订单（60分钟未支付）"),
	
	/** 107: 系统自动结算（60分钟）*/
	PAYED_BY_SYSTEM(107,"系统自动结算（60分钟）"),
	
	/** 201: 运营增加订单奖金*/
	OPERATING_FORTIFY_BONUS(201,"运营增加订单奖金"),
	
	/** 202: 运营派单*/
	OPERATING_ASSIGNS_ORDER(202,"运营派单"),
	
	/** 203: 运营处理投诉*/
	OPERATING_HANDLERS_COMPLAINT(203,"运营处理投诉"),
	
	/** 204: 运营重置某个导游状态*/
	OPERATING_CHANGES_GUIDE_STATUS(204,"运营重置某个导游状态"),
	
	/** 205: 运营处理（首次或修改）异动申请*/
	OPERATING_WORKS_APPLY(205,"运营处理（首次或修改）异动申请"),
	
	/** 206: 运营增量补发*/
	ADDITIONAL_COST_BY_OPERATING(206,"运营增量补发"),
	
	/** 207: 运营取消（改派）导游*/
	OPERATING_CANCELS_OR_CHANGS_GUIDE(207,"运营取消（改派）导游"),
	
	/** 208: 运营修改订单金额*/
	OPERATING_UPDATES_AMOUNT(208,"运营修改订单金额"),
	
	/** 209: 运营修改订单评分*/
	OPERATING_UPDATES_ORDER_SCORE(209,"运营修改订单评分"),
	
	/** 210: 运营执行注册航班操作*/
	REGISTERED_FLIGHT_BY_OPERATING(210,"运营执行注册航班操作"),
	
	/** 211: 运营取消重发*/
	OPERATING_REVOKES_RESEND(211,"运营取消重发"),
	
	/** 212: 运营帮助导游提交异动申请*/
	OPERATING_HELP_GUIDE_SUBMIT_APPLY(212,"运营帮助导游提交异动申请"),
	
	/** 213: 运营处罚导游*/
	OPERATING_PUNISHES_GUIDE(213,"运营处罚导游"),
	
	/** 214: 运营活动奖励*/
	OPERATING_ACTIVITY_AWARD(214,"运营活动奖励"),
	
	/** 601: 地接社老板改派导游*/
	LOCAL_AGENCY_REASSIGN_GUIDE(601,"地接社老板改派导游"),
	
	/** 301: 导游提交异动申请*/
	GUIDE_SUBMIT_APPLY(301,"导游提交异动申请"),
	
	/** 200: 系统AppPush通知*/
	SYSTEM_APP_PUSHES_NOTICE(200,"系统AppPush通知"),
	
	/** 300: 系统短信通知*/
	SYSTEM_SHORT_MESSAGE_NOTICE(300,"系统短信通知"),
	
	/** 400: 用户支付异动费用*/
	CUSTOMER_PAYS_FOR_CHANGING(400,"用户支付异动费用");

	public int 		value;
	public String 	name;
	
	LogType(int value,String name){
		this.value = value;
		this.name = name;
	}
	
	public static LogType getStatus(Integer value){
		LogType[] otypes = LogType.values();
		for(LogType orderStatus : otypes){
			if(orderStatus.value==value){
				return orderStatus;
			}
		}
		
		throw new TradeException(TradeReturnCodeEnum.ORDER_TYPE,value);
	}
}
