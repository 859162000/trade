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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hbc.api.trade.bdata.mapper.basedata.gen.UserFundAccountMapper;
import com.hbc.api.trade.bdata.mapper.basedata.gen.bean.UserFundAccount;
import com.hbc.api.trade.bdata.mapper.basedata.gen.bean.UserFundAccountExample;
import com.hbc.api.trade.order.enums.TradeReturnCodeEnum;
import com.hbc.api.trade.order.exception.TradeException;

/**
 * @author Jongly Ran
 */
@Service
public class UserFundAccountService {
	private final static Logger log = LoggerFactory.getLogger(UserFundAccountService.class);

	@Autowired private UserFundAccountMapper userFundAccountMapper;
	
	public String getUserAccount(String userId) {
		UserFundAccountExample example = new UserFundAccountExample();
		example.createCriteria().andUserIdEqualTo(userId).andIsDefaultEqualTo(1).andStatusEqualTo(1);
		List<UserFundAccount> list = userFundAccountMapper.selectByExample(example );
		if(list == null || list.size() != 1) {
			log.error("用户资金账户不存在，userId：" + userId);
			throw new TradeException(TradeReturnCodeEnum.USER_FUND_ACCOUNT_NOT_FOUND);
		}
		String accountNo = list.get(0).getAccountNo();
		if(accountNo == null) {
			log.error("用户资金账户不存在，userId：" + userId);
			throw new TradeException(TradeReturnCodeEnum.USER_FUND_ACCOUNT_NOT_FOUND);
		}
		return accountNo;
	}
}
