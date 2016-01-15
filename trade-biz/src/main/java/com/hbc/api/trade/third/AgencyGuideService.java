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
package com.hbc.api.trade.third;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hbc.api.trade.bdata.mapper.guide.gen.AgencyGuideMapper;
import com.hbc.api.trade.bdata.mapper.guide.gen.bean.AgencyGuide;
import com.hbc.api.trade.bdata.mapper.guide.gen.bean.AgencyGuideExample;
import com.hbc.api.trade.order.enums.TradeReturnCodeEnum;
import com.hbc.api.trade.order.exception.TradeException;

/**
 * @author Jongly Ran
 */

@Service
public class AgencyGuideService {

	@Autowired private AgencyGuideMapper 	agencyGuideMapper;
	
	public List<AgencyGuide> selectAgencyGuideList(Integer agencyId) {
		AgencyGuideExample example = new AgencyGuideExample();
		example.createCriteria().andAgencyIdEqualTo(agencyId).andStatusEqualTo(1);
		return agencyGuideMapper.selectByExample(example );
	}
	
	public List<String> selectGuideIdList(Integer agencyId) {
		List<AgencyGuide> agencyGuideList = selectAgencyGuideList(agencyId);
		if(agencyGuideList != null && agencyGuideList.size() > 0) {
			List<String> guideIdList = new LinkedList<>();
			for(AgencyGuide bean : agencyGuideList) {
				if(bean.getGuideId() != null) {
					guideIdList.add(bean.getGuideId().toString());
				}
			}
			return guideIdList;
		}
		throw new TradeException(TradeReturnCodeEnum.ORDER_PARAM_FAILED, "获取导游id列表");
	}
}
