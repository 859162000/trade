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
package com.hbc.api.trade.order.service;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.hbc.api.trade.bdata.common.IDGenerotor;
import com.hbc.api.trade.order.enums.FlightStatus;
import com.hbc.api.trade.order.enums.TradeReturnCodeEnum;
import com.hbc.api.trade.order.enums.order.OrderTrackType;
import com.hbc.api.trade.order.exception.TradeException;
import com.hbc.api.trade.order.mapping.gen.TradeOrderTrackMapper;
import com.hbc.api.trade.order.mapping.gen.bean.TradeOrderTrack;
import com.hbc.api.trade.order.mapping.gen.bean.TradeOrderTrackExample;
import com.hbc.api.trade.order.mapping.genx.TradeOrderTrackMapperEnhance;

/**
 * @author Jongly Ran
 */
@Service
public class OrderTrackService {
	private final static Logger log = LoggerFactory.getLogger(OrderTrackService.class);
	
	@Autowired private TradeOrderTrackMapper orderTrackMapper;
	@Autowired private TradeOrderTrackMapperEnhance orderTrackMapperEnhance;
	
	public List<TradeOrderTrack> getOrderTrackRecords(String orderNo) {
		TradeOrderTrackExample example = new TradeOrderTrackExample();
		example.createCriteria().andOrderNoEqualTo(orderNo);
		example.setOrderByClause("create_time desc");
		return orderTrackMapper.selectByExample(example);
	}
	
	/**
	 * @param orderNoList
	 * @return null，如果orderNoList is null or its size is 0
	 */
	public List<TradeOrderTrack> getOrderTrackRecords(List<String> orderNoList) {
		if(orderNoList == null || orderNoList.size() == 0) return null;
		return orderTrackMapperEnhance.selectOrderTracks(orderNoList);
	}

	@Transactional
	public void updateToRead(String trackId) {
		if(orderTrackMapperEnhance.updateToBeRead(trackId) == 0) {
			log.error("更新trade_order_track is_read字段失败，此时track_id=" + trackId);
			throw new TradeException(TradeReturnCodeEnum.FAILED_FOR_UPDATE, "行程记录");
		}
		TradeOrderTrack orderTrack = new TradeOrderTrack();
		orderTrack.setIsRead(1);
		orderTrack.setTrackId(trackId);
	}
	
	@Transactional
	private void insertNewTrack(String orderNo, OrderTrackType trackTYpe) {
		TradeOrderTrack trackBean = new TradeOrderTrack();
		trackBean .setIsRead(0);
		trackBean.setOrderNo(orderNo);
		Date currentTime = new Date();
		trackBean.setCreateTime(currentTime );
		trackBean.setStatus(1);
		trackBean.setTrackType(trackTYpe.value);
		trackBean.setTrackContent(trackTYpe.name);
		trackBean.setTrackDesc(trackTYpe.name);
		trackBean.setTrackId(IDGenerotor.generateOrderTrackId());
		trackBean.setUpdateTime(currentTime);
		try {
			orderTrackMapper.insert(trackBean);
		} catch(Exception e) {
			log.error("表trade_order_track插入数据失败：" + JSON.toJSONString(trackBean),e);
			throw new TradeException(TradeReturnCodeEnum.FAILED_FOR_INSERT, "行程记录");
		}
	}
	
	/**
	 * 接到订单
	 * @param orderNo
	 */
	public void obtainedOrder(String orderNo) {
		insertNewTrack(orderNo, OrderTrackType.OBTAIN_ORDER);
	}

	/**
	 * 导游已到达
	 * @param orderNo
	 */
	public void guideArrived(String orderNo) {
		insertNewTrack(orderNo, OrderTrackType.ARRIVED);
	}

	/**
	 * 接到客户
	 * @param orderNo
	 */
	public void pickedupCustomer(String orderNo) {
		insertNewTrack(orderNo, OrderTrackType.PICKEDUP);
	}
	
	/**
	 * 行程结束
	 * @param orderNo
	 */
	public void complete(String orderNo) {
		insertNewTrack(orderNo, OrderTrackType.COMPLETED);
	}

	/**
	 * 确认费用
	 * @param orderNo
	 */
	public void confirmedCost(String orderNo) {
		insertNewTrack(orderNo, OrderTrackType.COMFIRMED_COST);
	}

	/**
	 * 结算完成
	 * @param orderNo
	 */
	public void settlementCompleted(String orderNo) {
		insertNewTrack(orderNo, OrderTrackType.SETTLEMENT_COMPLETED);
	}
	/**
	 * 飞机起飞
	 * @param orderNo
	 */
	public void flightFly(String orderNo) {
		insertNewTrack(orderNo, OrderTrackType.FLIGHT_FLY);
	}
	/**
	 * 飞机到达
	 * @param orderNo
	 */
	public void flightArrive(String orderNo) {
		insertNewTrack(orderNo, OrderTrackType.FLIGHT_ARRIVE);
	}
	
	public void  flightDelay(String orderNo) {
		insertNewTrack(orderNo, OrderTrackType.FLIGHT_DELAY);
	}
	
	public void  flightCancle(String orderNo) {
		insertNewTrack(orderNo, OrderTrackType.FLIGHT_CANCLE);
	}
	
	public void  flightALTERNATEArrive(String orderNo) {
		insertNewTrack(orderNo, OrderTrackType.FLIGHT_TERNATEARRIVE);
	}
	
	public void  flightStatusBack(String orderNo) {
		insertNewTrack(orderNo, OrderTrackType.FLIGHT_STATUSBACK);
	}
	
	
	/**
	 * 订单已取消
	 * @param orderNo
	 */
	public void cancelled(String orderNo) {
		insertNewTrack(orderNo, OrderTrackType.CANCELLED);
	}
	
	/**
	 * 订单已修改
	 * @param orderNo
	 */
	public void updated(String orderNo) {
		insertNewTrack(orderNo, OrderTrackType.UPDATED);
	}
	
	/**
	 * 用户评价导游
	 * @param orderNo
	 */
	public void appraisedByCustomer(String orderNo) {
		insertNewTrack(orderNo, OrderTrackType.APPRAISE_BY_CUSTOMER);
	}
	
	/**
	 * 导游评价用户
	 * @param orderNo
	 */
	public void appraisedByGuide(String orderNo) {
		insertNewTrack(orderNo, OrderTrackType.APPRAISE_BY_GUIDE);
	}
	
	/**
	 * 客服冻结订单
	 * 
	 * @param orderNo
	 */
	public void orderFrozed(String orderNo)
	{
		insertNewTrack(orderNo, OrderTrackType.OPRDER_FROZE);
	}

}
