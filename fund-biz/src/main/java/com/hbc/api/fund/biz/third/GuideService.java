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
package com.hbc.api.fund.biz.third;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hbc.api.fund.biz.enums.FundReturnCodeEnum;
import com.hbc.api.fund.biz.exception.FundException;
import com.hbc.api.trade.bdata.common.DownloadConfigService;
import com.hbc.api.trade.bdata.mapper.guide.gen.GuideBeanMapper;
import com.hbc.api.trade.bdata.mapper.guide.gen.bean.GuideBean;

/**
 * @author Jongly Ran
 */
@Service
public class GuideService {
	private final static Logger logger = LoggerFactory.getLogger(GuideService.class);
	
	@Autowired private GuideBeanMapper guideBeanMapper;
	@Autowired private DownloadConfigService downloadConfirmService;
	
	public GuideBean getGuide(String guideId) {
		GuideBean guideBean = guideBeanMapper.selectByPrimaryKey(guideId);
		if(guideBean == null) {
			logger.error("导游信息不存在，guideId=" + guideId);
			throw new FundException(FundReturnCodeEnum.ERR_NOT_FUND, "导游");
		}
		guideBean.setAvatar(downloadConfirmService.getFullPath(guideBean.getAvatar()) );
		return guideBean;
	}
}
