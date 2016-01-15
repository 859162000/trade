/**
 * @Author lukangle
 * @2015年11月28日@下午2:13:26
 */
package com.hbc.api.trade.order.service.gorder.enums;

import com.hbc.api.trade.order.enums.TradeReturnCodeEnum;
import com.hbc.api.trade.order.exception.TradeException;

public enum SearchType {
	TOBEDONE(1, "待完成"), SUCCESSCOMPLETE(2, "已完成"),CANCLED(3, "已取消"),
	;


	public Integer value;
	public String desc;

	private SearchType(Integer value, String desc) {
		this.value = value;
		this.desc = desc;
	}

	public Integer getCode() {
		return value;
	}

	public String getDesc() {
		return desc;
	}
	
	public static SearchType getType(Integer value){
		SearchType[] carClassEnums = SearchType.values();
		for(SearchType carClassEnum : carClassEnums){
			if(carClassEnum.value.equals(value)){
				return carClassEnum;
			}
		}
		throw new TradeException(TradeReturnCodeEnum.ORDER_PARAM_FAILED, "searchType");
	}
}
