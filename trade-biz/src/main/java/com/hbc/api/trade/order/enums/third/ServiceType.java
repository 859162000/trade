/**
 * Copyrights  2015  HuangBaoChe
 *
 * All rights reserved.
 *
 * Created on 2015年10月1日 下午7:47:29
 * 
 * @author HanZhaozhan
 *
 */
package com.hbc.api.trade.order.enums.third;

import com.hbc.api.trade.order.enums.order.OrderType;

public enum ServiceType {

	JSC(0, "接机/送机/次租"), AIRPORT_PICKUP(1, "接机"), AIRPORT_TRANSFER(2, "送机"), DAILY_RENT(3, "日租"), SINGLE_RENT(4, "次租"), COMMENDATION(5, "精品线路");
	;

	public Integer value;
	public String name;

	private ServiceType(Integer value, String name) {
		this.value = value;
		this.name = name;
	}

	public static ServiceType getType(Integer value) {
		ServiceType[] guideStatus = ServiceType.values();
		for (ServiceType guideStatu : guideStatus) {
			if (guideStatu.value.equals(value)) {
				return guideStatu;
			}
		}
		return null;
	}

	public static ServiceType getType(OrderType orderType) {
		if (OrderType.COMMENDATION.equals(orderType)) {
			return COMMENDATION;
		} else if (OrderType.PERUSE.equals(orderType)) {
			return SINGLE_RENT;
		} else if (OrderType.DAILY.equals(orderType)) {
			return DAILY_RENT;
		} else if (OrderType.PICKUPORDER.equals(orderType)) {
			return AIRPORT_PICKUP;
		} else if (OrderType.TRANSFER.equals(orderType)) {
			return AIRPORT_TRANSFER;
		}else{
			return null;
		}
	}
}
