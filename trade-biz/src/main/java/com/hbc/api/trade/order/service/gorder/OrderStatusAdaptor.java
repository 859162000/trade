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
package com.hbc.api.trade.order.service.gorder;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hbc.api.trade.order.enums.TradeReturnCodeEnum;
import com.hbc.api.trade.order.enums.deliver.DemandDeal;
import com.hbc.api.trade.order.enums.deliver.TradeDeliverStatus;
import com.hbc.api.trade.order.enums.order.COrderStatus;
import com.hbc.api.trade.order.enums.order.GOrderStatus;
import com.hbc.api.trade.order.enums.order.OrderStatus;
import com.hbc.api.trade.order.enums.order.UserCommentStatus;
import com.hbc.api.trade.order.exception.TradeException;
import com.hbc.api.trade.order.mapping.gen.bean.OrderBean;
import com.hbc.api.trade.order.mapping.gen.bean.TradeDeliverGuide;

/**
 * 适配C端和G端状态机，适配GDS、MIS、Agency查询条件中的订单状态
 * @author Jongly Ran
 */
public class OrderStatusAdaptor {
	private final static Logger logger = LoggerFactory.getLogger(OrderStatusAdaptor.class);
	
	public static COrderStatus toCOrderStatus(OrderStatus orderStatus, TradeDeliverStatus deliverStatus, Integer userCommentStatus) {
		switch(orderStatus) {
		case INITSTATE:
			return COrderStatus.INITSTATE;
		case PAYSUCCESS:
			return deliverStatus.equals(TradeDeliverStatus.pkend) ? COrderStatus.AGREE : COrderStatus.PAYSUCCESS;
		case GUIDE_ARRIVED:
			return COrderStatus.ARRIVED;
		case PICK_CUSTOMER:
		case STROKE_END:
		case DISPUTING:
			return COrderStatus.SERVICING;
		case CONFIRMED_COST:// 费用确认
		case SETTLEMENT: 	// 结算完成
			return userCommentStatus != null && userCommentStatus.equals(UserCommentStatus.SCORED.value) ?  COrderStatus.COMPLETE : COrderStatus.NOT_EVALUATED; 		
		case CANCLE_CLOSE_NOPAY:
		case PAY_OUTTIME_CLOSE:
			return COrderStatus.CANCELLED;
		case CANCEL_SERVICEED:
		case CANCLE_CLOSE_PAY_SERVICE:
		case CANCEL_CLOSE:
		case CANCELING: 	// 取消中，基于已付款
		case CANCEL_NOSERVICE:
			return COrderStatus.REFUNDED;
		default:
			throw new TradeException(TradeReturnCodeEnum.ORDER_STATUS_TYPE);
		}
	}
	
	public static GOrderStatus toNewGOrderStatus(OrderBean orderBean, TradeDeliverGuide tradeDeliverGuide) {
		if(tradeDeliverGuide==null){
			throw new TradeException(TradeReturnCodeEnum.ORDER_GUIDE_EXP,orderBean.getOrderNo());
		}
		DemandDeal demandDeal = DemandDeal.getType(tradeDeliverGuide.getDemandDeal());
		if(DemandDeal.INITIAL.equals(demandDeal)){
			return GOrderStatus.INITSTATE;
		}else if(tradeDeliverGuide.getGuideId().equals(orderBean.getGuideId()) ){
			return GOrderStatus.GET;
		}else{
			return GOrderStatus.AGREE;
		}
	}
	
	public static GOrderStatus toGOrderStatus(OrderBean orderBean,TradeDeliverGuide tradeDeliverGuide,String guideId) {
		OrderStatus orderStatus = OrderStatus.getStatus(orderBean.getOrderStatus());
		switch(orderStatus) {
		case PAYSUCCESS:
			if(guideId.equals(orderBean.getGuideId()) ){
				return GOrderStatus.GET;
			}
			DemandDeal demandDeal = DemandDeal.INITIAL;
			if(tradeDeliverGuide!=null){
				demandDeal = DemandDeal.getType(tradeDeliverGuide.getDemandDeal());
			}
			if(DemandDeal.INITIAL.equals(demandDeal)){
				return GOrderStatus.INITSTATE;
			}else{
				return GOrderStatus.AGREE;
			}
		case GUIDE_ARRIVED:
			return GOrderStatus.ARRIVED;
		case PICK_CUSTOMER :
			return GOrderStatus.PICKED_UP;
		case STROKE_END:
			return GOrderStatus.STROKE_END;
		case CONFIRMED_COST:
		case SETTLEMENT:
			Integer userCommentStatus = orderBean.getUserCommentStatus();
			return userCommentStatus  != null && userCommentStatus.equals(UserCommentStatus.SCORED.value) ?  GOrderStatus.COMPLETE : GOrderStatus.NOT_EVALUATED; 		
		case CANCEL_CLOSE:
		case CANCEL_NOSERVICE:
		case CANCEL_SERVICEED:
		case CANCELING:
		case CANCLE_CLOSE_PAY_SERVICE:
		case CANCLE_CLOSE_NOPAY:
		case PAY_OUTTIME_CLOSE:
			return GOrderStatus.CANCELLED;
		case DISPUTING:
			return GOrderStatus.COMPLAINT;
		default:
			throw new TradeException(TradeReturnCodeEnum.ORDER_STATUS_TYPE,orderBean.getOrderNo());
		}
	}
	
	
	public static List<Integer> getCOrderStatusQueryType(Integer searchType) {
		List<Integer> orderStatusList = null;
		if(searchType != null) {
			orderStatusList = new ArrayList<Integer>();
			if(searchType==1){ // 进行中
				orderStatusList.add(OrderStatus.INITSTATE.value);
				orderStatusList.add(OrderStatus.PAYSUCCESS.value);
				orderStatusList.add(OrderStatus.GUIDE_ARRIVED.value);
				orderStatusList.add(OrderStatus.PICK_CUSTOMER.value);
				orderStatusList.add(OrderStatus.STROKE_END.value);
				orderStatusList.add(OrderStatus.DISPUTING.value);
				orderStatusList.add(OrderStatus.CANCEL_NOSERVICE.value);
				orderStatusList.add(OrderStatus.CANCEL_SERVICEED.value);
			}else if(searchType==2){ // 完成
				orderStatusList.add(OrderStatus.CONFIRMED_COST.value);
				orderStatusList.add(OrderStatus.SETTLEMENT.value);
			}else if(searchType==3){ // 取消
				orderStatusList.add(OrderStatus.CANCEL_CLOSE.value);
				orderStatusList.add(OrderStatus.CANCELING.value);
				orderStatusList.add(OrderStatus.CANCLE_CLOSE_NOPAY.value);
				orderStatusList.add(OrderStatus.CANCLE_CLOSE_PAY_SERVICE.value);
				orderStatusList.add(OrderStatus.PAY_OUTTIME_CLOSE.value);
			}else{
				logger.error("orderShowType only can be [1 2 3]");
				throw new TradeException(TradeReturnCodeEnum.ORDER_PARAM_FAILED,"查询订单状态");
			}
		}
		return orderStatusList;
	}
	
	/**
	 * GAPP查询条件中的订单状态
	 * @param searchType 1:待完成,2:已完成,3:被取消
	 * @return
	 */
	public static List<Integer> getGOrderStatusQueryType(Integer searchType) {
		List<Integer> orderStatusList = null;
		if(searchType != null) {
			orderStatusList = new ArrayList<Integer>();
			switch(searchType) {
			case 1: // 待完成
				orderStatusList.add(OrderStatus.PAYSUCCESS.value);
				orderStatusList.add(OrderStatus.GUIDE_ARRIVED.value);
				orderStatusList.add(OrderStatus.PICK_CUSTOMER.value);
				orderStatusList.add(OrderStatus.STROKE_END.value);
				orderStatusList.add(OrderStatus.DISPUTING.value);
				orderStatusList.add(OrderStatus.CANCEL_NOSERVICE.value);
				orderStatusList.add(OrderStatus.CANCEL_SERVICEED.value);
				break;
			case 2: // 已完成
				orderStatusList.add(OrderStatus.CONFIRMED_COST.value);
				orderStatusList.add(OrderStatus.SETTLEMENT.value);
				break;
			case 3: // 被取消
				orderStatusList.add(OrderStatus.CANCEL_CLOSE.value);
				orderStatusList.add(OrderStatus.CANCELING.value);
				orderStatusList.add(OrderStatus.CANCLE_CLOSE_NOPAY.value);
				orderStatusList.add(OrderStatus.CANCLE_CLOSE_PAY_SERVICE.value);
				orderStatusList.add(OrderStatus.PAY_OUTTIME_CLOSE.value);
				break;
			default:
				logger.error("GAPP查询条件searchType只能是[1:待完成,2:已完成,3:被取消]");
				throw new TradeException(TradeReturnCodeEnum.ORDER_PARAM_FAILED, "查询订单状态");
			}
		}
		return orderStatusList;
	}
	
	/**
	 * GDS查询条件中的订单状态
	 * @param searchType 1:等待支付,2:等待服务,3:服务中,4:已完成,5:已取消,6:全部
	 * @return
	 */
	public static List<Integer> getGDSOrderStatusQueryType(Integer searchType) {
		List<Integer> orderStatusList = null;
		if(searchType != null) {
			orderStatusList = new ArrayList<Integer>();
			switch(searchType) {
			case 1: // 等待支付
				orderStatusList.add(OrderStatus.INITSTATE.value);
				break;
			case 2: // 等待服务
				orderStatusList.add(OrderStatus.PAYSUCCESS.value);
				break;
			case 3: // 服务中
				orderStatusList.add(OrderStatus.GUIDE_ARRIVED.value);
				orderStatusList.add(OrderStatus.PICK_CUSTOMER.value);
				orderStatusList.add(OrderStatus.STROKE_END.value);
				orderStatusList.add(OrderStatus.DISPUTING.value);
				break;
			case 4: // 已完成
				orderStatusList.add(OrderStatus.CONFIRMED_COST.value);
				orderStatusList.add(OrderStatus.SETTLEMENT.value);
				break;
			case 5: // 已取消
				orderStatusList.add(OrderStatus.CANCEL_CLOSE.value);
				orderStatusList.add(OrderStatus.CANCELING.value);
				orderStatusList.add(OrderStatus.CANCLE_CLOSE_NOPAY.value);
				orderStatusList.add(OrderStatus.CANCLE_CLOSE_PAY_SERVICE.value);
				orderStatusList.add(OrderStatus.CANCEL_NOSERVICE.value);
				orderStatusList.add(OrderStatus.CANCEL_SERVICEED.value);
				orderStatusList.add(OrderStatus.PAY_OUTTIME_CLOSE.value);
				break;
			case 6: // 全部
				orderStatusList = null;
				break;
			default: 
				logger.error("GDS查询条件searchType只能是[1:等待支付,2:等待服务,3:服务中,4:已完成,5:已取消,6:全部]");
				throw new TradeException(TradeReturnCodeEnum.ORDER_PARAM_FAILED, "查询订单状态");
			}
		}
		return orderStatusList;
	}
	
	/**
	 * 地接社查询条件中的订单状态
	 * @param searchType 2:服务未开始,3:服务中,4:已完成,5:已取消,6:全部
	 * @return
	 */
	public static List<Integer> getAgencyOrderStatusQueryType(Integer searchType) {
		List<Integer> orderStatusList = null;
		if(searchType != null) {
			orderStatusList = new ArrayList<Integer>();
			switch(searchType) {
			case 2: // 服务未开始
				orderStatusList.add(OrderStatus.PAYSUCCESS.value);
				break;
			case 3: // 服务中
				orderStatusList.add(OrderStatus.GUIDE_ARRIVED.value);
				orderStatusList.add(OrderStatus.PICK_CUSTOMER.value);
				orderStatusList.add(OrderStatus.STROKE_END.value);
				orderStatusList.add(OrderStatus.DISPUTING.value);
				break;
			case 4: // 已完成
				orderStatusList.add(OrderStatus.CONFIRMED_COST.value);
				orderStatusList.add(OrderStatus.SETTLEMENT.value);
				break;
			case 5: // 已取消
				orderStatusList.add(OrderStatus.CANCEL_CLOSE.value);
				orderStatusList.add(OrderStatus.CANCELING.value);
				orderStatusList.add(OrderStatus.CANCLE_CLOSE_NOPAY.value);
				orderStatusList.add(OrderStatus.CANCLE_CLOSE_PAY_SERVICE.value);
				orderStatusList.add(OrderStatus.CANCEL_NOSERVICE.value);
				orderStatusList.add(OrderStatus.CANCEL_SERVICEED.value);
				orderStatusList.add(OrderStatus.PAY_OUTTIME_CLOSE.value);
				break;
			case 6: // 全部
				orderStatusList = null;
				break;
			default: 
				logger.error("地接社查询条件searchType只能是[2:服务未开始,3:服务中,4:已完成,5:已取消,6:全部]");
				throw new TradeException(TradeReturnCodeEnum.ORDER_PARAM_FAILED, "查询订单状态");
			}
		}
		return orderStatusList;
	}
	
	/**
	 * MIS查询条件中的订单状态
	 * @param searchType 1:急待处理,2:等待支付,3:发包中,4:导游已确认,5:服务中,6:已完成,7:已取消,8:纠纷中,9:全部
	 * @return 
	 */
	public static List<Integer> getMISOrderStatusQueryType(Integer searchType) {
		List<Integer> orderStatusList = null;
		if(searchType != null) {
			orderStatusList = new ArrayList<Integer>();
			switch(searchType) {
			case 1: // 急待处理
				orderStatusList = null;
				break;
			case 2: // 等待支付
				orderStatusList.add(OrderStatus.INITSTATE.value);
				orderStatusList.add(OrderStatus.PAY_OUTTIME_CLOSE.value);
				break;
			case 3: // 发包中
				orderStatusList.add(OrderStatus.PAYSUCCESS.value); //BUGFIX (http://bug.hbc.tech/mantis/view.php?id=838)
				break;
			case 4: // 导游已确认
				orderStatusList.add(OrderStatus.PAYSUCCESS.value);
				break;
			case 5: // 服务中
				orderStatusList.add(OrderStatus.GUIDE_ARRIVED.value);
				orderStatusList.add(OrderStatus.PICK_CUSTOMER.value);
				break;
			case 6: // 已完成
				orderStatusList.add(OrderStatus.STROKE_END.value);
				orderStatusList.add(OrderStatus.CONFIRMED_COST.value);
				orderStatusList.add(OrderStatus.SETTLEMENT.value);
				orderStatusList.add(OrderStatus.CANCEL_SERVICEED.value);
				orderStatusList.add(OrderStatus.CANCLE_CLOSE_PAY_SERVICE.value);
				orderStatusList.add(OrderStatus.CANCEL_NOSERVICE.value);
				orderStatusList.add(OrderStatus.CANCEL_CLOSE.value);
				orderStatusList.add(OrderStatus.CANCELING.value);

				break;
			case 7: // 已取消
				orderStatusList.add(OrderStatus.CANCLE_CLOSE_NOPAY.value);
				break;
			case 8: // 纠纷中
				orderStatusList.add(OrderStatus.DISPUTING.value);
				break;
			case 9: // 全部
				orderStatusList = null;
				break;
			default: 
				logger.error("MIS查询条件searchType只能是[1:急待处理,2:等待支付,3:发包中,4:导游已确认,5:服务中,6:已完成,7:已取消,8:全部]");
				throw new TradeException(TradeReturnCodeEnum.ORDER_PARAM_FAILED, "查询订单状态");
			}
		}
		return orderStatusList;
	}
}
