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

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.hbc.api.fund.account.mapping.gen.bean.FundAccount;
import com.hbc.api.fund.account.service.FundAccountService;
import com.hbc.api.fund.biz.mapping.gen.bean.FundBankCard;
import com.hbc.api.fund.biz.mapping.genx.xbean.FundBankCardBindBean;
import com.hbc.api.fund.biz.mapping.genx.xbean.FundBankCardQueryBean;
import com.hbc.api.fund.biz.service.FundBankCardService;
import com.hbc.api.fund.biz.validator.FundValidator;
import com.hbc.api.trade.bdata.common.rsp.ReturnResult;
import com.hbc.api.trade.order.controller.validator.OrderValidator;
import com.hbc.api.trade.order.service.FundLogService;
import com.hbc.api.trade.sec.TradeAccountContext;

/**
 * @author Jongly Ran
 */
@RestController
@RequestMapping("fund")
public class GFundAccountController {

	private static final Logger logger = LoggerFactory.getLogger(GFundAccountController.class);

	@Autowired
	private FundAccountService fundAccountService;

	@Autowired
	private TradeAccountContext tradeAccountContext;

	@Autowired
	private FundBankCardService bankCardService;

	@Autowired
	private FundLogService fundLogService;

	@RequestMapping(value = "v1.0/g/account/balances", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public ReturnResult getAccountBalances(HttpServletRequest request) {
		FundAccount fundAccountBean = fundAccountService.getFundAccount(tradeAccountContext.getFundId());
		Map<String, Double> data = new HashMap<>(2);
		Double totalAmount = fundLogService.getFutureAmount(fundAccountBean.getAccountNo());
		data.put("totalAmount", totalAmount);
		double useableAmount = fundAccountBean.getAmount();
		//data.put("useableAmount", useableAmount < 0.0 ? 0.0 : useableAmount);
		data.put("useableAmount", useableAmount);
		ReturnResult returnResult = new ReturnResult();
		returnResult.setData(data);
		logger.info("账户余额查询结果->{}", JSON.toJSONString(returnResult));
		return returnResult;
	}

	@RequestMapping(value = "v1.0/g/account/bank/card/detail", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public ReturnResult findBankCardDetail(String bankNo, HttpServletRequest request) {
		FundBankCard resultData = bankCardService.selectFundBankCard(bankNo);
		ReturnResult returnResult = new ReturnResult();
		returnResult.setData(resultData);
		return returnResult;
	}

	@RequestMapping(value = "v1.0/g/account/bank/list", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public ReturnResult findBankCardList(@Valid FundBankCardQueryBean queryBean, BindingResult result, HttpServletRequest request) {
		String userId = tradeAccountContext.getUserId();
		OrderValidator.validateParamString(userId, "导游ID");
		queryBean.setGuideId(userId);
		FundValidator.validateLimitAndOffset(queryBean.getLimit(), queryBean.getOffset());
		int totalSize = bankCardService.selectFundBankCardListTotalSize(queryBean);
		List<FundBankCard> bankCardList = bankCardService.selectFundBankCardList(queryBean);
		ReturnResult returnResult = new ReturnResult();
		List<FundBankCard> targetList = new LinkedList<>();
		if (bankCardList != null && bankCardList.size() > 0) {
			for (FundBankCard fundBankCard : bankCardList) {
				FundBankCard bankCard = new FundBankCard();
				bankCard.setAccount(fundBankCard.getAccount());
				bankCard.setAccountHolderName(fundBankCard.getAccountHolderName());
				bankCard.setType(fundBankCard.getType());
				bankCard.setBankNo(fundBankCard.getBankNo());
				bankCard.setBank(fundBankCard.getBank());
				bankCard.setCityName(fundBankCard.getCityName());
				targetList.add(bankCard);
			}
		}
		Map<String, Object> resultData = new HashMap<>(2);
		resultData.put("totalSize", totalSize);
		resultData.put("resultBean", targetList);
		returnResult.setData(resultData);
		return returnResult;
	}

	@RequestMapping(value = "v1.0/g/account/bank/bind", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public ReturnResult bindBankCard(FundBankCardBindBean bankCard, HttpServletRequest request) {
		String guideId = tradeAccountContext.getUserId();
		OrderValidator.validateParamString(guideId, "导游ID");
		bankCard.setGuideId(guideId);
		bankCardService.bindNewBankCard(bankCard);
		return new ReturnResult();
	}

	@RequestMapping(value = "v1.0/g/account/bank/unbind", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public ReturnResult unbindBankCard(String bankNo, HttpServletRequest request) {
		String guideId = tradeAccountContext.getUserId();
		OrderValidator.validateParamString(guideId, "导游ID");
		bankCardService.unbindBankCard(bankNo, guideId);
		return new ReturnResult();
	}

}
