/*
 * Copyright (c) 2015-2016, CCLX.COM. All rights reserved.
 * WANDA GROUP PROPRIETARY/CONFIDENTIAL. 
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is private property; you can't redistribute it and/or modify it
 * under the terms of the LICENSE you obtained from
 *
 *    http://www.cclx.com/
 * 
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. 
 *
 * Author: Jongly Ran
 * Revision: 1.0
 */
package com.hbc.api.trade.order;

import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.hbc.api.trade.TradeFinalStr;
import com.hbc.api.trade.order.enums.order.GoodType;
import com.hbc.api.trade.order.enums.order.OrderSource;
import com.hbc.api.trade.order.enums.order.OrderStatus;
import com.hbc.api.trade.order.enums.order.OrderType;
import com.hbc.api.trade.order.enums.order.UserCommentStatus;
import com.hbc.api.trade.order.mapping.gen.bean.OrderBean;

/**
 * @author Jongly Ran
 */
public class OrderPal {
	public static List<Integer> getNotSettlementOrderStatus() {
		List<Integer> orderStatusList = new LinkedList<>();
		orderStatusList.add(OrderStatus.PAYSUCCESS.value);
		orderStatusList.add(OrderStatus.GUIDE_ARRIVED.value);
		orderStatusList.add(OrderStatus.PICK_CUSTOMER.value);
		orderStatusList.add(OrderStatus.STROKE_END.value);
		orderStatusList.add(OrderStatus.CANCELING.value);
		orderStatusList.add(OrderStatus.DISPUTING.value);
		orderStatusList.add(OrderStatus.CONFIRMED_COST.value);
		return orderStatusList;
	}
	
	/**
	 * 能否退款
	 * @param orderStatus
	 * @return
	 */
	public static boolean refundable(Integer orderStatus) {
		switch(OrderStatus.getStatus(orderStatus)) {
		case PAYSUCCESS:
		case GUIDE_ARRIVED:
		case PICK_CUSTOMER:
		case STROKE_END:
			return true;
		default:
			return false;
		}
	}
	
	/**
	 * 是否加载导游信息
	 * @param orderStatus
	 * @param guideId
	 * @return
	 */
	public static boolean hasGuideInfo(Integer orderStatus, String guideId) {
		switch(OrderStatus.getStatus(orderStatus)) {
			case PAYSUCCESS:
				return StringUtils.isNotBlank(guideId) && !guideId.equals("0");
			case INITSTATE:
			case PAY_OUTTIME_CLOSE:
				return false;
			default:
				return true;
		}
	}
	
	/**
	 * 是否加载付款信息
	 * @param orderStatus
	 * @return
	 */
	public static boolean hasPaymentInfo(Integer orderStatus) {
		switch(OrderStatus.getStatus(orderStatus)) {
			case INITSTATE:
			case PAY_OUTTIME_CLOSE:
			case CANCLE_CLOSE_NOPAY :
				return false;
			default:
				return true;
		}
	}
	
	/**
	 * 是否可取消订单
	 * 
	 * @param orderStatus
	 * @return
	 */
	public static boolean cancelable(Integer orderStatus) {
		switch(OrderStatus.getStatus(orderStatus)) {
			case INITSTATE:
			case PAYSUCCESS:
			case GUIDE_ARRIVED:
			case PICK_CUSTOMER:
			case STROKE_END:
				// 客诉处理中被禁用，客诉处理完后由系统自动处理为未服务关闭和已服务退款
				return true;
			default:
				return false;
		}
	}
	
	/**
	 * 返回不可取消订单的文本提示
	 * @param orderStatus
	 * @return
	 */
	public static String cancelText(Integer orderStatus) {
		OrderStatus status = OrderStatus.getStatus(orderStatus);
		switch(status) {
			case INITSTATE:
			case PAYSUCCESS:
			case GUIDE_ARRIVED:
			case PICK_CUSTOMER:
			case STROKE_END:
				// 客诉处理中被禁用，客诉处理完后由系统自动处理为未服务关闭和已服务退款
				return null;
			default:
				return "订单当前状态为["+status.name+"]，仅在未支付、支付成功、导游已到达、接到客人和行程结束状态可以取消订单";
		}
	}
	
	/**
	 * 是否能聊天
	 * @param imtoken
	 * @return
	 */
	public static boolean chatable(OrderBean orderBean) {
		Integer orderStatus = orderBean.getOrderStatus();
		String guideId = orderBean.getGuideId();
		OrderStatus status = OrderStatus.getStatus(orderStatus);
		switch(status) {
			case PAYSUCCESS:
				return guideId != null && !guideId.equals(TradeFinalStr.defaultGuideId);
			case INITSTATE:
			case PAY_OUTTIME_CLOSE:
				return false;
			default:
				return true;
		}
	}
	
	/**
	 * 订单是否使用 yyyy-mm-dd格式日期
	 * 
	 * @param orderTypeValue 目前日租和精品线路需要
	 * @return 
	 */
	public static boolean usingShortDate(Integer orderTypeValue, Integer goodsType) {
		OrderType orderType = OrderType.getType(orderTypeValue);
		boolean isShortDate = false;
		if(orderType.equals(OrderType.COMMENDATION) && goodsType != null) {
			GoodType goodType = GoodType.getType(goodsType);
			switch(goodType) {
			case BigLongDistance:
			case CityVehiclesCar:
			case LessLongDistance:
			case QUALITY:
				isShortDate = true;
				break;
			default:
				isShortDate = false;
			}
		}
		return (orderType.equals(OrderType.DAILY) || isShortDate);
	}
	
	/**
	 * 获取订单号头部
	 * 
	 * @return
	 */
	public static String getOrderNoPrefix(OrderBean orderBean) {
		OrderType orderType = OrderType.getType(orderBean.getOrderType());
		switch (orderType) {
		case PICKUPORDER:
			return "J";
		case TRANSFER:
			return "S";
		case PERUSE:
			return "C";
		case DAILY:
			return "R";
		case COMMENDATION:
			GoodType  goodsType = GoodType.getType(orderBean.getOrderGoodsType());
			switch(goodsType) {
			case CityVehiclesCar:
				return "LN";
			case LessLongDistance:
				return "LX";
			case BigLongDistance:
				return "LD";
			case QUALITY :
				return "LL";
			case PERUSE:
				return "LC";
			case TRANSFER:
				return "LS";
			case PICKUPORDER:
				return "LJ";
			}
		}
		return "M";
	}
	
	public static boolean appraisableByUser(OrderBean orderBean) {
		OrderStatus status = OrderStatus.getStatus(orderBean.getOrderStatus());
		Boolean appraisable = null;
		switch(status) {
			case CONFIRMED_COST:// 费用确认
			case SETTLEMENT: 	// 结算完成
				appraisable = true;
				break;
			default:
				appraisable = false;
		}
		boolean hasNotBeenAppraised = UserCommentStatus.getType(orderBean.getUserCommentStatus()).equals(UserCommentStatus.UNSCORED);
		return appraisable && hasNotBeenAppraised;
	}

	/**
	 * @param order
	 * @return
	 */
	public static double getOrderPrice(OrderBean order) {
		if(OrderSource.GDS.value.equals(order.getOrderSource()) ) {
			//[BUG-FIX] GDS 下单应该去票面价
			Double checkInPrice = order.getCheckInPrice();
			return checkInPrice != null ? order.getPriceTicket() - checkInPrice : order.getPriceTicket(); // 下单时已校验，能保证 > 0
		} else {
			Double checkInPrice = order.getCheckInPrice();
			Double priceChannel = order.getPriceChannel();
			return checkInPrice != null ? priceChannel - checkInPrice : priceChannel; // 下单时已校验，能保证 > 0
		}
	}
}
