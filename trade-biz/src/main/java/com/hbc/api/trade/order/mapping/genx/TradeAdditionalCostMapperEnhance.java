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
package com.hbc.api.trade.order.mapping.genx;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.hbc.api.trade.order.mapping.gen.bean.TradeAdditionalCost;
import com.hbc.api.trade.order.mapping.genx.xbean.MISAdditionalCostQueryBean;

/**
 * @author Jongly Ran
 */
public interface TradeAdditionalCostMapperEnhance {
	
	@Select("select * from trade_additional_cost order by create_time desc limit #{offset}, #{limit} ")
	public List<TradeAdditionalCost> getTradeAdditionalCostList(MISAdditionalCostQueryBean queryBean);
	
	@Update("update trade_additional_cost set addition_is_read=#{additionIsRead} where order_no=#{orderNo}")
	public int updateAdditionalIsReadStatus(@Param("orderNo") String orderNo, @Param("additionIsRead") Integer additionIsRead);
}
