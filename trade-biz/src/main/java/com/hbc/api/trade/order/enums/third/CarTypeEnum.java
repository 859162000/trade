package com.hbc.api.trade.order.enums.third;

import com.hbc.api.trade.order.enums.TradeReturnCodeEnum;
import com.hbc.api.trade.order.exception.TradeException;


/**
 * @author colin
 *
 */
public enum CarTypeEnum {
	CARTYPE_ECONOMIC(1, "经济型"), CARTYPE_COMFOTABLE(2, "舒适型"), CARTYPE_LUXURY(3, "豪华型"), CARTYPE_EXTRA_LUXURY(4, "奢华型");

	public Integer value;
	public String name;
	CarTypeEnum(int value,String name){
		this.value = value;
		this.name = name;
	}
	
	public static CarTypeEnum getType(Integer value){
		CarTypeEnum[] carTypeEnums = CarTypeEnum.values();
		for(CarTypeEnum carTypeEnum : carTypeEnums){
			if(carTypeEnum.value.equals(value)){
				return carTypeEnum;
			}
		}
		throw new TradeException(TradeReturnCodeEnum.ORDER_CARTYPE_NOSUPPORT,value);
	}
}
