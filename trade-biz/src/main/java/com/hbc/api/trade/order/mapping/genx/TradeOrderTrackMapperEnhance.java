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

import com.hbc.api.trade.order.mapping.gen.bean.TradeOrderTrack;

/**
 * @author Jongly Ran
 */
public interface TradeOrderTrackMapperEnhance {
	
	@Select({"<script>",
		"select * from trade_order_track where order_no in ",
		"<foreach item='item' index='index' collection='orderNoList' open='(' separator=',' close=')'> #{item} </foreach>",
		"</script>"})
	List<TradeOrderTrack> selectOrderTracks(@Param(value = "orderNoList") List<String> orderNoList);
	
	@Update("update trade_order_track set is_read=1 where track_id=#{trackId}")
	int updateToBeRead(@Param(value="trackId") String trackId);
}
