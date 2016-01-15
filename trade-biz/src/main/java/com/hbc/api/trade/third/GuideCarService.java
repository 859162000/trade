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

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.hbc.api.trade.bdata.common.cache.CacheFinal;
import com.hbc.api.trade.bdata.mapper.guide.gen.GuideCarMapper;
import com.hbc.api.trade.bdata.mapper.guide.gen.bean.GuideCar;
import com.hbc.api.trade.bdata.mapper.guide.gen.bean.GuideCarExample;

/**
 * @author Jongly Ran
 */
@Service
public class GuideCarService {
	@Autowired private GuideCarMapper guideCarMapper;
	@Cacheable(value=CacheFinal.confCache,key="#root.targetClass+#root.methodName"+"+#root.args")
	public List<GuideCar> getGuideCars(String guideId) {
		GuideCarExample example = new GuideCarExample();
		example.createCriteria().andGuideIdEqualTo(guideId);
		return guideCarMapper.selectByExample(example);
	}
	@Cacheable(value=CacheFinal.confCache,key="#root.targetClass+#root.methodName"+"+#root.args")
	public GuideCar getGuideCar(String guideId) {
		GuideCarExample example = new GuideCarExample();
		example.createCriteria().andGuideIdEqualTo(guideId);
		List<GuideCar> gcars = guideCarMapper.selectByExample(example);
		if(gcars.size()>=1){
			return gcars.get(0);
		}else{
			return null;
		}
	}
	
}
