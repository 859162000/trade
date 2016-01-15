package com.hbc.api.trade.order.enums.third;

public enum NoticeType {
	NEW_ORDER(1,"新订单"),
	PK_SUCCESS(2,"PK成功"),
	PK_FAIL(3,"PK失败"),
	LEAVING(4,"临行前提醒"),
	BOOS_ASSIGN(5,"地接社老板指派导游"),
	OPERATOR_ASSIGN(6,"运营指派导游"),
	CLIENT_CANCEL(7,"客人取消订单通知导游"),
	CLIENT_MODIFY(8,"客人修改订单"),
	FLIGHT_STATUS(9,"航班状态改变"),
	GUIDE_NO_SERVICE(10,"导游未开始服务"),
	CONFIRM_FEE(11,"确认费用"),
	ADDITIONAL_PASS(12,"增项费用处理完毕"),
	ORDER_REWARD(13,"订单奖金"),
	ORDER_SERIAL(14,"串单新订单");
	public Integer value;
	public String name;
	NoticeType(int value,String name){
		this.value = value;
		this.name = name;
	}
	
	public static NoticeType getType(Integer value){
		NoticeType[] pushTypes = NoticeType.values();
		for(NoticeType pushType : pushTypes){
			if(pushType.value.equals(value)){
				return pushType;
			}
		}
		return null;
	}
}
