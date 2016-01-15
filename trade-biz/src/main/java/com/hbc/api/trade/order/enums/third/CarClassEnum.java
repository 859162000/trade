package com.hbc.api.trade.order.enums.third;

import com.hbc.api.trade.order.enums.TradeReturnCodeEnum;
import com.hbc.api.trade.order.exception.TradeException;


/**
 * @author colin
 *
 */
public enum CarClassEnum {
	CARCLASS_FIVE(5, "5座车系"), CARCLASS_SEVEN(7, "7座车系"), CARCLASS_NINE(9, "9座车系"), CARCLASS_TWELVE(12, "12座车系");


	public Integer value;
	public String desc;

	private CarClassEnum(Integer value, String desc) {
		this.value = value;
		this.desc = desc;
	}

	public Integer getCode() {
		return value;
	}

	public String getDesc() {
		return desc;
	}
	
	public static CarClassEnum getType(Integer value){
		CarClassEnum[] carClassEnums = CarClassEnum.values();
		for(CarClassEnum carClassEnum : carClassEnums){
			if(carClassEnum.value.equals(value)){
				return carClassEnum;
			}
		}
		
		throw new TradeException(TradeReturnCodeEnum.ORDER_CARCLASS_NOSUPPORT,value);
		
	}
}
