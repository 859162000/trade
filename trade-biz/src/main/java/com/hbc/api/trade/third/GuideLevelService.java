/**
 * @Author lukangle
 * @2015年11月12日@下午7:57:06
 */
package com.hbc.api.trade.third;

import org.springframework.stereotype.Component;

import com.hbc.api.trade.order.enums.order.GoodType;
import com.hbc.api.trade.order.enums.order.OrderType;
import com.hbc.api.trade.order.enums.third.GuideCropTypeEnum;
import com.hbc.api.trade.order.enums.third.GuideLevelEnums;
import com.hbc.api.trade.order.mapping.gen.bean.OrderBean;

@Component
public class GuideLevelService {
	public GuideLevelEnums getGuideLevByOrder(OrderBean orderBean) {
		OrderType orderType = OrderType.getType(orderBean.getOrderType());
		GoodType goodType = GoodType.getType(orderBean.getOrderGoodsType());
		
		if (OrderType.COMMENDATION.equals(orderType)) {
			if (GoodType.BigLongDistance.equals(goodType)) {
				return GuideLevelEnums.A;
			} else if (GoodType.CityVehiclesCar.equals(goodType)) {
				return GuideLevelEnums.C;
			} else if (GoodType.LessLongDistance.equals(goodType)) {
				return GuideLevelEnums.B;
			} else if (GoodType.QUALITY.equals(goodType)) {
				return GuideLevelEnums.D;
			}else if (GoodType.PICKUPORDER.equals(goodType)) {
				return GuideLevelEnums.E;
			} else if (GoodType.TRANSFER.equals(goodType)) {
				return GuideLevelEnums.E;
			} else if (GoodType.PERUSE.equals(goodType)) {
				return GuideLevelEnums.E;
			}else{
				return GuideLevelEnums.E;
			}
		} else {
			if (OrderType.DAILY.equals(orderType)) {
				int days = orderBean.getTotalDays();
				
				if ((!orderBean.getServiceCityId().equals(orderBean.getServiceEndCityid()) && days > 3)) {
					return GuideLevelEnums.A;
				} else if (orderBean.getServiceCityId().equals(orderBean.getServiceEndCityid())) {
					return GuideLevelEnums.C;
				} else {
					return GuideLevelEnums.B;
				}
				
			} else if (OrderType.PERUSE.equals(orderType) || OrderType.PICKUPORDER.equals(orderType) || OrderType.TRANSFER.equals(orderType)) {
				return GuideLevelEnums.E;
			} else {
				return GuideLevelEnums.D;
			}
		}
	}

	/**
	 * 根据订单类型 获取 对应的导游服务类型
	 * 
	 * @param orderBean
	 * @return
	 */
	public GuideCropTypeEnum getGuideCropLevelByOrder(OrderBean orderBean) {
		OrderType orderType = OrderType.getTypeByGoodeType(orderBean);
		GoodType goodType = GoodType.getType(orderBean.getOrderGoodsType());

		if (OrderType.COMMENDATION.equals(orderType)) {
			if (GoodType.BigLongDistance.equals(goodType)) {
				return GuideCropTypeEnum.CARCLASS_BAO_OUT3;
			} else if (GoodType.CityVehiclesCar.equals(goodType)) {
				return GuideCropTypeEnum.CROPTYPE_BAO_INCITY;
			} else if (GoodType.LessLongDistance.equals(goodType)) {
				return GuideCropTypeEnum.CROPTYPE_BAO_IN3;
			} else if (GoodType.QUALITY.equals(goodType)) {
				return GuideCropTypeEnum.CROPTYPE_ROUTE;
			}else if (GoodType.PICKUPORDER.equals(goodType)) {
				return GuideCropTypeEnum.CROPTYPE_RECEIVE;
			} else if (GoodType.TRANSFER.equals(goodType)) {
				return GuideCropTypeEnum.CROPTYPE_SEND;
			} else if (GoodType.PERUSE.equals(goodType)) {
				return GuideCropTypeEnum.CROPTYPE_BYTIME;
			}else{
				return GuideCropTypeEnum.CROPTYPE_ROUTE;
			}
		} else {
			// 不是固定线路 日租 按照 固有逻辑处理
			if (OrderType.DAILY.equals(orderType)) {
				int days = orderBean.getTotalDays();
				if ((!orderBean.getServiceCityId().equals(orderBean.getServiceEndCityid()) && days > 3)) {
					return GuideCropTypeEnum.CARCLASS_BAO_OUT3;
				} else if (orderBean.getServiceCityId().equals(orderBean.getServiceEndCityid())) {
					return GuideCropTypeEnum.CROPTYPE_BAO_INCITY;
				} else {
					return GuideCropTypeEnum.CROPTYPE_BAO_IN3;
				}
			} else if (OrderType.PERUSE.equals(orderType)) {
				return GuideCropTypeEnum.CROPTYPE_BYTIME;
			} else if (OrderType.PICKUPORDER.equals(orderType)) {
				return GuideCropTypeEnum.CROPTYPE_RECEIVE;
			} else if (OrderType.TRANSFER.equals(orderType)) {
				return GuideCropTypeEnum.CROPTYPE_SEND;
			} else {
				return GuideCropTypeEnum.CROPTYPE_ROUTE;
			}
		}
	}
}
