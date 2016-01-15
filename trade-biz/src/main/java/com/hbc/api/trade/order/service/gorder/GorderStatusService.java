/**
 * @Author lukangle
 * @2015年11月28日@下午2:08:35
 */
package com.hbc.api.trade.order.service.gorder;

import java.util.ArrayList;
import java.util.List;

import com.hbc.api.trade.order.enums.order.OrderStatus;

public class GorderStatusService {
	/**
	 * 主订单未完成的状态
	 * @param searchType
	 * @return
	 */
	public static List<Integer> unfinishedOrders() {
		List<Integer> orderStatusList = new ArrayList<Integer>();
		orderStatusList.add(OrderStatus.PAYSUCCESS.value);
		orderStatusList.add(OrderStatus.GUIDE_ARRIVED.value);
		orderStatusList.add(OrderStatus.PICK_CUSTOMER.value);
		orderStatusList.add(OrderStatus.STROKE_END.value);
		orderStatusList.add(OrderStatus.DISPUTING.value);
		return orderStatusList;
	}
	/**
	 * 2:已完成
	 * @param searchType
	 * @return
	 */
	public static List<Integer> finishedOrders() {
		List<Integer> orderStatusList = new ArrayList<Integer>();
		orderStatusList.add(OrderStatus.CONFIRMED_COST.value);
		orderStatusList.add(OrderStatus.SETTLEMENT.value);
		return orderStatusList;
	}
	/**
	 * 被取消
	 * @return
	 */
	public static List<Integer> cancelledOrders() {
		List<Integer> orderStatusList = new ArrayList<Integer>();
		orderStatusList.add(OrderStatus.CANCEL_CLOSE.value);
		orderStatusList.add(OrderStatus.CANCELING.value);
		orderStatusList.add(OrderStatus.CANCLE_CLOSE_NOPAY.value);
		orderStatusList.add(OrderStatus.CANCLE_CLOSE_PAY_SERVICE.value);
		orderStatusList.add(OrderStatus.CANCEL_NOSERVICE.value);
		orderStatusList.add(OrderStatus.CANCEL_SERVICEED.value);
		orderStatusList.add(OrderStatus.PAY_OUTTIME_CLOSE.value);
		return orderStatusList;
	}
}
