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
import org.springframework.stereotype.Service;

import com.hbc.api.trade.bdata.mapper.basedata.gen.CityBeanMapper;
import com.hbc.api.trade.bdata.mapper.basedata.gen.bean.CityBean;
import com.hbc.api.trade.bdata.mapper.basedata.gen.bean.CityBeanExample;

/**
 * @author Jongly Ran
 */
@Service
public class CityQueryService {
	@Autowired CityBeanMapper cityMapper;
	
	public List<CityBean> getCities(List<Integer> cityIdList) {
		CityBeanExample example = new CityBeanExample();
		if(cityIdList != null && cityIdList.size() > 0) {
			example.createCriteria().andCityIdIn(cityIdList);
		}
		return cityMapper.selectByExample(example );
	}
}
