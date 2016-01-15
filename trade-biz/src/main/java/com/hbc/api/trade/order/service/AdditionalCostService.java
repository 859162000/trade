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
import com.hbc.api.trade.bdata.common.util.TimeConverter;
import com.hbc.api.trade.order.enums.TradeReturnCodeEnum;
import com.hbc.api.trade.order.enums.bean.OperationType;
import com.hbc.api.trade.order.enums.order.AdditionalCostType;
import com.hbc.api.trade.order.enums.order.AdditionalISReadType;
import com.hbc.api.trade.order.enums.order.OrderLogType;
import com.hbc.api.trade.order.exception.TradeException;
import com.hbc.api.trade.order.mapping.gen.TradeAdditionalCostMapper;
import com.hbc.api.trade.order.mapping.gen.TradeAdditionalDetailMapper;
import com.hbc.api.trade.order.mapping.gen.bean.TradeAdditionalCost;
import com.hbc.api.trade.order.mapping.gen.bean.TradeAdditionalCostExample;
import com.hbc.api.trade.order.mapping.gen.bean.TradeAdditionalDetail;
import com.hbc.api.trade.order.mapping.gen.bean.TradeAdditionalDetailExample;
import com.hbc.api.trade.order.mapping.genx.TradeAdditionalCostMapperEnhance;
import com.hbc.api.trade.order.mapping.genx.xbean.AdditionalCostDetail;
import com.hbc.api.trade.order.mapping.genx.xbean.GOrderAdditionalCostParamBean;
import com.hbc.api.trade.order.mapping.genx.xbean.MISAdditionalCostQueryBean;
import com.hbc.api.trade.order.mapping.genx.xbean.OrderLogParamBean;

/**
 * @author Jongly Ran
 */
@Service
public class AdditionalCostService {
	private final static Logger log = LoggerFactory.getLogger(AdditionalCostService.class);

	@Autowired
	private TradeAdditionalDetailMapper additionalCostDetail;
	@Autowired
	private TradeAdditionalCostMapper additionalCost;
	@Autowired
	private TradeAdditionalCostMapperEnhance additionalCostEnhance;
	@Autowired
	private OrderLogService orderLogService;

	/**
	 * 
	 * @param orderNo
	 * @return Object[] ，存储格式为：[TradeAdditionalCost, List &lt;
	 *         TradeAdditionalDetail &gt; ]
	 */
	public Object[] getAdditionalCostWithDetail(String orderNo) {
		TradeAdditionalCost costBean = getAdditionalCost(orderNo);
		List<TradeAdditionalDetail> costDetailList = null;
		if (costBean != null) {
			TradeAdditionalDetailExample costDetailExample = new TradeAdditionalDetailExample();
			costDetailExample.createCriteria().andAdditionalIdEqualTo(costBean.getAdditionalNo());
			costDetailList = additionalCostDetail.selectByExample(costDetailExample);
		}

		Object[] results = new Object[2];
		results[0] = costBean;
		results[1] = costDetailList;
		return results;
	}

	/**
	 * 
	 * @param orderNo
	 * @return null, 如果没数据
	 */
	public List<TradeAdditionalDetail> getAdditionalCostDetails(String orderNo) {
		TradeAdditionalCost costBean = getAdditionalCost(orderNo);
		List<TradeAdditionalDetail> costDetailList = null;
		if (costBean != null) {
			TradeAdditionalDetailExample costDetailExample = new TradeAdditionalDetailExample();
			costDetailExample.createCriteria().andAdditionalIdEqualTo(costBean.getAdditionalNo());
			costDetailExample.setOrderByClause("daily_date");
			costDetailList = additionalCostDetail.selectByExample(costDetailExample);
		}
		return costDetailList;
	}

	/**
	 * 
	 * @param orderNo
	 * @return null, 如果没查询到数据
	 */
	public TradeAdditionalCost getAdditionalCost(String orderNo) {
		TradeAdditionalCostExample costExample = new TradeAdditionalCostExample();
		costExample.createCriteria().andOrderNoEqualTo(orderNo);
		List<TradeAdditionalCost> costList = additionalCost.selectByExample(costExample);
		if (costList != null && costList.size() > 0) {
			if (costList.size() == 1)
				return costList.get(0);
			log.error("增项费用总计与orderNo应该是一对一的，但订单[" + orderNo + "]在Trade_Additional_Cost有多个记录。");
			throw new TradeException(TradeReturnCodeEnum.ADDITIONAL_COST_OUT_OF_SIZE);
		}
		return null;
	}

	public boolean setAdditionalCostRead(String orderNo)
	{
		TradeAdditionalCostExample costExample = new TradeAdditionalCostExample();
		costExample.createCriteria().andOrderNoEqualTo(orderNo);
		TradeAdditionalCost cost = new TradeAdditionalCost();
		cost.setAdditionIsRead(1);
		if (additionalCost.updateByExampleSelective(cost, costExample) > 0)
		{
			log.debug("order:{} additional cost information is read...", orderNo);
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}

	public List<TradeAdditionalCost> getAdditionalCostList(MISAdditionalCostQueryBean queryBean) {
		return additionalCostEnhance.getTradeAdditionalCostList(queryBean);
	}

	// TODO 冉孟萍，增项费用c, G
	@Transactional
	public void saveCostAndCostDetail(GOrderAdditionalCostParamBean additionalCostParam, String guideId, String guideName) {
		String costDetailId = IDGenerotor.generateAdditionalCostId();
		Date currentTime = new Date();
		Double overDistancePriceTotal = 0.0, overDayPriceTotal = 0.0, overPayPriceTotal /* 垫付费 */= 0.0, overTimePriceTotal = 0.0;
		Double overWaitTimeSum = 0.0;
		for (AdditionalCostDetail paramBean : additionalCostParam.getAdditionalCostList()) {
			log.info("增项费用明细：" + JSON.toJSONString(paramBean));

			// 计算各项费用总计
			if (paramBean.getOverDayPrice() != null) {
				overDayPriceTotal += paramBean.getOverDayPrice();
			}
			if (paramBean.getOverDistancePrice() != null) {
				overDistancePriceTotal += paramBean.getOverDistancePrice();
			}
			if (paramBean.getOverTimePrice() != null) {
				overTimePriceTotal += paramBean.getOverTimePrice();
			}
			if (paramBean.getOverWaitTimeCost() != null) {
				overWaitTimeSum += paramBean.getOverWaitTimeCost();
			}
			if (paramBean.getOtherFee1() != null) {
				overPayPriceTotal += paramBean.getOtherFee1();
			}
			// 存储每次各项费用明细
			saveAdditionalCostDetail(costDetailId, currentTime, paramBean);
		}

		// 存储各项费用总计
		saveAdditionalCost(additionalCostParam, costDetailId, currentTime, overDistancePriceTotal, overDayPriceTotal, overPayPriceTotal, overTimePriceTotal, overWaitTimeSum);

		OrderLogParamBean orderLogParamBean = new OrderLogParamBean();
		orderLogParamBean.setOrderNo(additionalCostParam.getOrderNo());
		orderLogParamBean.setContent(OrderLogType.CHANGE_ORDER_CONTENT(guideName, TimeConverter.formatDate(currentTime)));
		orderLogParamBean.setGuideId(guideId);
		orderLogParamBean.setGuideName(guideName);
		orderLogParamBean.setLogType(OrderLogType.CHANGE_ORDER.type);
		orderLogParamBean.setOpType(OperationType.GUIDE.value);
		orderLogParamBean.setOpUserId(guideId);
		orderLogParamBean.setOpUserName(guideName);
		orderLogService.insertOrderLog(orderLogParamBean);
	}

	/**
	 * @param additionalCostParam
	 * @param costDetailId
	 * @param currentTime
	 * @param overDistancePriceTotal
	 * @param overDayPriceTotal
	 * @param overPayPriceTotal
	 * @param overTimePriceTotal
	 * @param overWaitTimeSum
	 */
	private void saveAdditionalCost(GOrderAdditionalCostParamBean additionalCostParam, String costDetailId, Date currentTime, Double overDistancePriceTotal, Double overDayPriceTotal, Double overPayPriceTotal, Double overTimePriceTotal,
			Double overWaitTimeSum) {
		TradeAdditionalCost additionalCostBean = new TradeAdditionalCost();
		additionalCostBean.setAdditionalNo(costDetailId);
		additionalCostBean.setApplyPrice(additionalCostParam.getApplyPrice());
		additionalCostBean.setOrderNo(additionalCostParam.getOrderNo());
		additionalCostBean.setCreateTime(currentTime);
		additionalCostBean.setUpdateTime(currentTime);
		additionalCostBean.setAdditionStatus(AdditionalCostType.INITIAL.value);
		additionalCostBean.setOverDayCost(overDayPriceTotal);
		additionalCostBean.setOverDistanceCost(overDistancePriceTotal);
		additionalCostBean.setOverPayCost(overPayPriceTotal);
		additionalCostBean.setOverTimeCost(overTimePriceTotal);
		additionalCostBean.setOverWaitTimeCost(overWaitTimeSum);
		additionalCostBean.setAdditionIsRead(AdditionalISReadType.UNREAD.value);
		try {
			log.info("增项费用明细：" + JSON.toJSONString(additionalCostBean));
			additionalCost.insert(additionalCostBean);
		} catch (Exception e) {
			log.error("保存增项费用总计到trade_additional_cost表失败", e);
			throw new TradeException(TradeReturnCodeEnum.FAILED_FOR_INSERT, "增项费用");
		}
	}

	/**
	 * @param costDetailId
	 * @param currentTime
	 * @param paramBean
	 */
	private void saveAdditionalCostDetail(String costDetailId, Date currentTime, AdditionalCostDetail paramBean) {
		TradeAdditionalDetail additionalDetailBean = new TradeAdditionalDetail();
		additionalDetailBean.setAdditionalId(costDetailId);
		additionalDetailBean.setApplyfee(paramBean.getApplyfee());
		additionalDetailBean.setCreateTime(currentTime);
		additionalDetailBean.setDailyDate(TimeConverter.toDate(paramBean.getDailyDate(), TimeConverter.END_WITH_DATE));
		additionalDetailBean.setDetailId(IDGenerotor.generateAdditionalCostDetailId());
		additionalDetailBean.setOtherFee1(paramBean.getOtherFee1());
		additionalDetailBean.setOverDay(paramBean.getOverDay());
		additionalDetailBean.setOverDayPrice(paramBean.getOverDayPrice());
		additionalDetailBean.setOverDistance(paramBean.getOverDistance());
		additionalDetailBean.setOverDistancePrice(paramBean.getOverDistancePrice());
		additionalDetailBean.setOverTime(paramBean.getOverTime());
		additionalDetailBean.setOverTimePrice(paramBean.getOverTimePrice());
		additionalDetailBean.setUnitDayPrice(paramBean.getUnitDayPrice());
		additionalDetailBean.setUnitDistancePrice(paramBean.getUnitDistancePrice());
		additionalDetailBean.setUnitTimePrice(paramBean.getUnitTimePrice());
		additionalDetailBean.setUpdateTime(currentTime);
		try {
			int result = additionalCostDetail.insert(additionalDetailBean);
			log.info("增项费用明细入库[" + result + "]条数据additionalDetailBean： " + additionalDetailBean);

		} catch (Exception e) {
			log.error("保存增项费用明细到trade_additional_detail表失败", e);
			throw new TradeException(TradeReturnCodeEnum.FAILED_FOR_INSERT, "增项费用明细");
		}
	}
}
