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

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hbc.api.fund.biz.mapping.gen.bean.FundBankCard;
import com.hbc.api.fund.biz.mapping.gen.bean.FundWithdrawal;
import com.hbc.api.fund.biz.mapping.genx.xbean.FundBankCardBindBean;
import com.hbc.api.fund.biz.mapping.genx.xbean.FundBankCardQueryBean;
import com.hbc.api.fund.biz.mapping.genx.xbean.FundWithdrawalQueryBean;
import com.hbc.api.fund.biz.service.FundBankCardService;
import com.hbc.api.fund.biz.service.GFundWithdrawService;
import com.hbc.api.fund.biz.validator.FundValidator;
import com.hbc.api.trade.bdata.common.rsp.ReturnResult;

/**
 * gBoss
 * @author Jongly Ran
 */
@RequestMapping("fund")
@RestController
public class AgencyBankCardController {

	@Autowired private FundBankCardService bankCardService;
	@Autowired private GFundWithdrawService withdrawService;

	@RequestMapping(value = "v1.0/gb/account/bank/list", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public ReturnResult findBankCardList(@Valid FundBankCardQueryBean queryBean, BindingResult result, HttpServletRequest request) {
		FundValidator.validateLimitAndOffset(queryBean.getLimit(), queryBean.getOffset());
		List<FundBankCard> bankCardList = bankCardService.selectFundBankCardList(queryBean);
		Integer totalSize = bankCardService.selectFundBankCardListTotalSize(queryBean);
		ReturnResult returnResult = new ReturnResult();
		returnResult.setData(bankCardList, totalSize);
		return returnResult;
	}
	
	@RequestMapping(value = "v1.0/gb/account/bank/bind", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public ReturnResult bindBankCard(@Validated FundBankCardBindBean bankCard, BindingResult result, HttpServletRequest request) {
		bankCardService.bindNewBankCard(bankCard);
		return new ReturnResult();
	}
	
	@RequestMapping(value = "v1.0/gb/account/bank/remove", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public ReturnResult removeBankCard(String bankNo, HttpServletRequest request) {
		FundValidator.validateParamString(bankNo, "银行卡关联帐号");
		bankCardService.deleteFundBankCard(bankNo);
		return new ReturnResult();
	}
	
	@RequestMapping(value = "v1.0/gb/account/history/list", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public ReturnResult accountHistory(String accountNo, HttpServletRequest request) {
		FundValidator.validateParamString(accountNo, "银行卡关联帐号");
		FundWithdrawalQueryBean queryBean = new FundWithdrawalQueryBean();
		queryBean.setFinAccount(accountNo);
		List<FundWithdrawal> resultBean = withdrawService.getFundWithdrawalList(queryBean );
		Integer totalSize = withdrawService.getFundWithdrawalListTotalSize(queryBean);
		ReturnResult returnResult = new ReturnResult();
		returnResult.setData(resultBean, totalSize);
		return returnResult;
	}
	
	
}
