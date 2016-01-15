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
package com.hbc.api.trade.fund;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hbc.api.fund.account.mapping.gen.bean.FundAccount;
import com.hbc.api.fund.account.service.FundAccountService;
import com.hbc.api.trade.bdata.common.rsp.ReturnResult;

/**
 * @author Jongly Ran
 */
@RestController
@RequestMapping("fund")
public class GDSFundAccountController {

	@Autowired private FundAccountService fundAccountService;
	
	@RequestMapping(value = "v1.0/ca/account/detail", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public ReturnResult findAccount(@RequestParam(required=true) String accountNo, BindingResult bindResult, HttpServletRequest request) {
		FundAccount result = fundAccountService.getFundAccount(accountNo);
		ReturnResult returnResult = new ReturnResult();
		returnResult.setData(result);
		return returnResult;
	}
}
