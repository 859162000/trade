package com.hbc.api.trade.order.enums.ota;

import com.hbc.api.trade.order.enums.TradeReturnCodeEnum;
import com.hbc.api.trade.order.enums.third.UserSource;
import com.hbc.api.trade.order.exception.TradeException;


public enum AgentChannelEnum{
	QUNAR_CHANNEL(UserSource.QUNAR.getCode(), "QUNA渠道"),
	
	CTRIP_CHANNEL(UserSource.CTRIP.getCode(), "携程渠道"),
	
	QUA_CHANNEL(UserSource.QUA.getCode(), "去啊渠道"),
	
	C(18,"c"),
	;


	public Integer value;
	public String desc;

	private AgentChannelEnum(Integer value, String desc) {
		this.value = value;
		this.desc = desc;
	}

	public Integer getCode() {
		return value;
	}

	public String getDesc() {
		return desc;
	}
	
	public static AgentChannelEnum getType(Integer value){
		AgentChannelEnum[] carClassEnums = AgentChannelEnum.values();
		for(AgentChannelEnum carClassEnum : carClassEnums){
			if(carClassEnum.value.equals(value)){
				return carClassEnum;
			}
		}
		throw new TradeException(TradeReturnCodeEnum.ORDER_CHANEL_NOEXIST,value);
	}
	
}
