package com.hbc.api.trade.order.enums.alarm;

import com.hbc.api.trade.order.enums.TradeReturnCodeEnum;
import com.hbc.api.trade.order.exception.TradeException;

public enum AlarmType {
/*1-服务到时间，导游无操作 2-无人接单;3-临行前提醒；*/
	GUIDENOOPREATION(1,"PUSH 服务到时间，导游无操作"),
	NOGUIDEPICK(2,"PUSH 无人接单"),
	LEAVING(3,"PUSH 临行前24提醒"),
	leavePushOrder(4,"C PUSH  24提醒"),
	
	pushLeaving(3022,"G PUSH  24提醒"),
	PUSH_LOG_PAYSUCCESS(3001,"PUSH 支付成功"),
	pushFlightArrive(3002,"航班起飞PUSH"),
	pushFlightDelay(3003,"航班延误PUSH"),
	
	pushFlightCancle(3004,"航班取消PUSH"),
	pushFlightALTERNATEArrive(3005,"航班备降PUSH"),
	pushFlightStatusBack(3006,"航班返回"),
	pushNewOrder(3007,"新订单"),
	pushPkSuccess(3008,"PK成功"),
	pushPkFail(3009,"PK失败"),
	pushRewardServce(3010,"奖金"),
	pushEditOrder(3011,"订单修改"),
	
	canclePushOrder(5000,"C端订单取消"),
	cGuideConfirm(5001,"C端订单导游确认"),
 ADDITIONAL_FEE_CONFIRM(5002, "增项费用提醒"),
	
	SMSLOGKEAVING(2001,"临行前24提醒 sms"),
	SMSPAYSUCCESS(2002,"支付成功提醒"),
	guideSMSConfirm(2003,"导游确定SMS提醒"),
	gleaverStartSMS(2004,"导游临行前提醒"),
	cancleSMSConfirm(2005,"取消订单SMS通知C"),
	editOrderSMS(2006,"订单修改SMS通知C"),
	reGuideSMS(2007,"导游重新指派"),
	
	cancleGOrder(8007,"g端取消"),
	;
	public Integer value;
	public String name;
	AlarmType(int value,String name){
		this.value = value;
		this.name = name;
	}
	
	public static AlarmType getStatus(Integer value){
		AlarmType[] alarmTypes = AlarmType.values();
		for(AlarmType alarmType : alarmTypes){
			if(alarmType.value.equals(value)){
				return alarmType;
			}
		}
		
		throw new TradeException(TradeReturnCodeEnum.ORDER_STATUS_QUERY_TYPE,value);
	}
}
