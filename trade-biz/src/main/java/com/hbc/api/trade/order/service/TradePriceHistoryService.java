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

import com.hbc.api.trade.bdata.common.enums.Sysuser;
import com.hbc.api.trade.bdata.common.util.BeanUtilsEnhance;
import com.hbc.api.trade.bdata.common.util.DoubleUtil;
import com.hbc.api.trade.order.enums.TradeReturnCodeEnum;
import com.hbc.api.trade.order.enums.order.PriceHistoryOpType;
import com.hbc.api.trade.order.enums.order.PriceHistoryPriceType;
import com.hbc.api.trade.order.exception.TradeException;
import com.hbc.api.trade.order.mapping.gen.TradePriceHistoryMapper;
import com.hbc.api.trade.order.mapping.gen.bean.TradePriceHistory;
import com.hbc.api.trade.order.mapping.gen.bean.TradePriceHistoryExample;
import com.hbc.api.trade.order.mapping.genx.xbean.TradePriceHistoryParam;

/**
 * @author Jongly Ran
 */
@Service
public class TradePriceHistoryService {
	private final static Logger logger = LoggerFactory.getLogger(TradePriceHistoryService.class);

	@Autowired private TradePriceHistoryMapper tradePriceHistoryMapper;
	
	public void recordPriceHistory(TradePriceHistoryParam priceHistoryBeanParam) {
		PriceHistoryPriceType.getType(priceHistoryBeanParam.getPriceType()); // validate price type
		TradePriceHistory historyBean = new TradePriceHistory();
		BeanUtilsEnhance.copyProperties(historyBean, priceHistoryBeanParam);
		Date currentTime = new Date();
		historyBean.setCreateTime(currentTime);
		historyBean.setUpdateTime(currentTime);
		try {
			tradePriceHistoryMapper.insert(historyBean);
		} catch(Exception e) {
			logger.error("记录更新订单价格历史记录失败", e);
			throw new TradeException(TradeReturnCodeEnum.FAILED_FOR_INSERT, "记录更新订单价格失败，");
		}
	}
	/**
	 * 
	 * @param orderNo 订单号
	 * @param sprice 导游价 原价
	 * @param tprice 导游价 变化后的价格
	 */
	public void deliverGuidePriceChange(String orderNo,Double sprice,Double tprice,PriceHistoryOpType priceHistoryOpType){
		TradePriceHistory historyBean = new TradePriceHistory();
		Date currentTime = new Date();
		historyBean.setCreateTime(currentTime);
		historyBean.setUpdateTime(currentTime);
		historyBean.setOpComment("pk 改价");
		historyBean.setOpType(priceHistoryOpType.value);
		historyBean.setOpUid(Sysuser.SYSUSER.id);
		historyBean.setOpUname(Sysuser.SYSUSER.name);
		historyBean.setOrderNo(orderNo);
		historyBean.setPriceType(PriceHistoryPriceType.GUIDE_PRICE_EDIT.value);
		
		TradePriceHistoryExample tradePriceHistoryExample = new TradePriceHistoryExample();
		TradePriceHistoryExample.Criteria criteria = tradePriceHistoryExample.createCriteria();
		criteria.andOrderNoEqualTo(orderNo);
		criteria.andPriceTypeEqualTo(PriceHistoryPriceType.GUIDE_PRICE_EDIT.value);
		int pnum = tradePriceHistoryMapper.countByExample(tradePriceHistoryExample);
		historyBean.setPriceSeq(pnum);
		
		historyBean.setSourcePrice(sprice);
		historyBean.setTargetPrice(tprice);
		try {
			tradePriceHistoryMapper.insert(historyBean);
		} catch(Exception e) {
			logger.error("记录更新订单价格历史记录失败", e);
			throw new TradeException(TradeReturnCodeEnum.FAILED_FOR_INSERT, "记录更新订单价格失败，");
		}
	}
	/**
	 * 计算价格变动百分比
	 * @param opType 价格改动类型： 1.首单奖励 2.串单 3.二次发单  4.管理员价格 5. A级导游降价
	 * @return
	 */
	public String getPercentForPriceChanges(PriceHistoryOpType priceHistoryOpTye, String orderNo) {
		TradePriceHistoryExample example = new TradePriceHistoryExample();
		example.createCriteria().andOpTypeEqualTo(priceHistoryOpTye.value).andOrderNoEqualTo(orderNo);
		example.setOrderByClause("create_time desc limit 1");
		List<TradePriceHistory> priceHistoryList = tradePriceHistoryMapper.selectByExample(example );
		if(priceHistoryList != null && priceHistoryList.size() == 1) {
			TradePriceHistory priceHistoryBean = priceHistoryList.get(0);
			return DoubleUtil.percent(priceHistoryBean.getTargetPrice(), priceHistoryBean.getSourcePrice(), 0);
		}
		return "0%";
	}

	public Integer getMaxTradePriceByOrderNumber(String orderNumber)
	{
		TradePriceHistoryExample example = new TradePriceHistoryExample();
		example.createCriteria().andOrderNoEqualTo(orderNumber);
		List<TradePriceHistory> priceHistoryList = tradePriceHistoryMapper.selectByExample(example);
		return priceHistoryList == null ? 0 : priceHistoryList.size();
	}
}
