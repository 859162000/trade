/**
 * @Author lukangle
 * @2015年11月13日@下午1:49:29
 */
package com.hbc.api.trade.order.enums.order;

import com.hbc.api.trade.order.mapping.gen.bean.OrderBean;


/**
 * 1接 2 送 3 市内包车 4次 5 固定线路产品 6小长途 7大长途
 */
public enum GoodType {
	/** 1: 接机 */
	PICKUPORDER(1, "接机"),
	
	/** 2: 送机 */
	TRANSFER(2, "送机"),
	
	/** 3: 市内包车(由日租拆分出来) */
	CityVehiclesCar(3, "市内包车"),
	
	/** 4: 次租 */
	PERUSE(4, "次租"), 
	
	/** 5: 精品线路(由日租拆分出来) */
	QUALITY(5, "精品线路"), 

	/** 6: 小长途 (由日租拆分出来)*/
	LessLongDistance(6, "小长途"), 
	
	/** 7: 大长途 (由日租拆分出来)*/
	BigLongDistance(7, "大长途"), 
	;
	

	public Integer value;
	public String name;

	GoodType(int value, String name) {
		this.value = value;
		this.name = name;
	}

	public static GoodType getType(Integer value) {
		if(value != null) {
			GoodType[] orderKafkaOpts = GoodType.values();
			for (GoodType orderKafkaOpt : orderKafkaOpts) {
				if (orderKafkaOpt.value .equals( value)) {
					return orderKafkaOpt;
				}
			}
		}
		return null;
	}
	
	public static GoodType getGoodTypeByOrder(OrderBean orderBean) {
		OrderType orderType = OrderType.getTypeByGoodeType(orderBean);
		GoodType goodType = GoodType.getType(orderBean.getOrderGoodsType());

		if (OrderType.COMMENDATION.equals(orderType)) {
			return goodType;
		} else {
			// 不是固定线路 日租 按照 固有逻辑处理
			if (OrderType.DAILY.equals(orderType)) {
				int days = orderBean.getTotalDays();
				if ((!orderBean.getServiceCityId().equals(orderBean.getServiceEndCityid()) && days > 3)) {
					return GoodType.BigLongDistance;
				} else if (orderBean.getServiceCityId().equals(orderBean.getServiceEndCityid())) {
					return GoodType.CityVehiclesCar;
				} else {
					return GoodType.LessLongDistance;
				}
			} else if (OrderType.PERUSE.equals(orderType)) {
				return GoodType.PERUSE;
			} else if (OrderType.PICKUPORDER.equals(orderType)) {
				return GoodType.PICKUPORDER;
			} else if (OrderType.TRANSFER.equals(orderType)) {
				return GoodType.TRANSFER;
			} else {
				return GoodType.QUALITY;
			}
		}
	}
}
