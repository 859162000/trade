/**
 * @Author lukangle
 * @2015年11月7日@下午6:13:04
 */
package com.hbc.api.trade.order.enums;

import com.hbc.api.trade.order.exception.TradeException;
import com.hbc.api.trade.order.service.deliver.conf.TradeCitySolrConf;

public enum CarClassTypeEnum {

	Car1(1, 1, 5), Car2(2, 1, 7), Car3(3, 1, 9), Car4(4, 1, 12), 
	Car5(5, 2, 5), Car6(6, 2, 7), Car7(7, 2, 9), Car8(8, 2, 12), 
	Car9(9, 3, 5), Car10(10, 3, 7), Car11(11, 3, 9), Car12(12, 3, 11), 
	Car13(13, 4, 5), Car14(14, 4, 7), Car15(15, 4, 9), Car16(16, 4, 11), ;
	public Integer thirdCarType;
	public Integer carTypeId;
	public Integer carSeatNum;

	CarClassTypeEnum(Integer thirdCarType, Integer carTypeId, Integer carSeatNum) {
		this.thirdCarType = thirdCarType;
		this.carTypeId = carTypeId;
		this.carSeatNum = carSeatNum;
	}

	public static CarClassTypeEnum getCarCls(Integer thirdCarType) {
		CarClassTypeEnum[] otypes = CarClassTypeEnum.values();
		for (CarClassTypeEnum carClassTypeEnum : otypes) {
			if (carClassTypeEnum.thirdCarType .equals(thirdCarType) ) {
				return carClassTypeEnum;
			}
		}
		return null;
	}

	public static CarClassTypeEnum getCarCls(Integer carType, Integer carNum) {
		CarClassTypeEnum[] otypes = CarClassTypeEnum.values();
		for (CarClassTypeEnum carClassTypeEnum : otypes) {
			if (carClassTypeEnum.carTypeId .equals(carType )&& carClassTypeEnum.carSeatNum.equals(carNum)) {
				return carClassTypeEnum;
			}
		}
		return null;
	}

	public static String getConfUrgent(CarClassTypeEnum carClassTypeEnum, TradeCitySolrConf tradeCitySolrConf) {
		switch (carClassTypeEnum) {
//		case Car1:
//			return tradeCitySolrConf.getUrgent_1_5();
//		case Car2:
//			return tradeCitySolrConf.getUrgent_1_7();
//		case Car3:
//			return tradeCitySolrConf.getUrgent_1_9();
//		case Car4:
//			return tradeCitySolrConf.getUrgent_1_12();
//		case Car5:
//			return tradeCitySolrConf.getUrgent_2_5();
//		case Car6:
//			return tradeCitySolrConf.getUrgent_2_7();
//		case Car7:
//			return tradeCitySolrConf.getUrgent_2_9();
//		case Car8:
//			return tradeCitySolrConf.getUrgent_2_12();
//		case Car9:
//			return tradeCitySolrConf.getUrgent_3_5();
//		case Car10:
//			return tradeCitySolrConf.getUrgent_3_7();
//		case Car11:
//			return tradeCitySolrConf.getUrgent_3_9();
//		case Car12:
//			return tradeCitySolrConf.getUrgent_3_12();
//		case Car13:
//			return tradeCitySolrConf.getUrgent_4_5();
//		case Car14:
//			return tradeCitySolrConf.getUrgent_4_7();
//		case Car15:
//			return tradeCitySolrConf.getUrgent_4_9();
//		case Car16:
//			return tradeCitySolrConf.getUrgent_4_12();
		default:
			throw new TradeException(TradeReturnCodeEnum.TRADE_SOLR_URGENT);
		}
	}

}
