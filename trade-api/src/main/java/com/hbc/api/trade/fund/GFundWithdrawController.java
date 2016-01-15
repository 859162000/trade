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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.hbc.api.fund.account.enums.BizType;
import com.hbc.api.fund.account.mapping.gen.bean.FundAccount;
import com.hbc.api.fund.account.mapping.gen.bean.FundAccountLog;
import com.hbc.api.fund.account.parm.FundAccountLogParam;
import com.hbc.api.fund.account.service.FundAccountLogService;
import com.hbc.api.fund.account.service.FundAccountService;
import com.hbc.api.fund.biz.enums.FundReturnCodeEnum;
import com.hbc.api.fund.biz.exception.FundException;
import com.hbc.api.fund.biz.mapping.genx.xbean.FundWithdrawApplyBean;
import com.hbc.api.fund.biz.service.GFundWithdrawService;
import com.hbc.api.fund.biz.validator.FundValidator;
import com.hbc.api.trade.bdata.common.rsp.ReturnResult;
import com.hbc.api.trade.bdata.common.util.DoubleUtil;
import com.hbc.api.trade.bdata.mapper.guide.gen.bean.GuideBean;
import com.hbc.api.trade.bdata.mapper.guide.gen.bean.GuideProhibit;
import com.hbc.api.trade.fund.req.GFundWithdrawHistory;
import com.hbc.api.trade.order.controller.validator.FundwithdrawAccessValidator;
import com.hbc.api.trade.order.controller.validator.OrderValidator;
import com.hbc.api.trade.order.enums.TradeReturnCodeEnum;
import com.hbc.api.trade.order.enums.third.GuideProhibitEnum;
import com.hbc.api.trade.order.exception.TradeException;
import com.hbc.api.trade.order.service.FundLogService;
import com.hbc.api.trade.order.service.OrderQueryService;
import com.hbc.api.trade.sec.TradeAccountContext;
import com.hbc.api.trade.third.LControllerService;

import edu.emory.mathcs.backport.java.util.Arrays;

/**
 * @author Jongly Ran
 */
@RestController
@RequestMapping("fund")
public class GFundWithdrawController {

	private final static Logger logger = LoggerFactory.getLogger(GFundWithdrawController.class);

	private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

	@Autowired
	private GFundWithdrawService gFundWithdrawService;

	@Autowired
	private TradeAccountContext tradeAccountContext;

	@Autowired
	private FundAccountService fundAccountService;

	@Autowired
	private LControllerService lControllerService;

	@Autowired
	private FundAccountLogService fundAccountLogService;

	@Autowired
	private OrderQueryService orderQueryService;

	@Autowired
	private FundLogService fundLogService;

	@Autowired
	private FundwithdrawAccessValidator fundwithdrawAccessValidator;

	@RequestMapping(value = "v1.0/g/account/withdraw/history", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public ReturnResult withdraw_history(FundAccountLogParam param)
	{
		int totalHistoryCount = 0;
		//final String pattern = "市内{0}天，跨城市{1}天";
		List<GFundWithdrawHistory> histories = new ArrayList<GFundWithdrawHistory>();
		FundValidator.validateParamString(param.getAccountNo(), "AccountNo");

		@SuppressWarnings("unchecked")
		List<Integer> bizTypeList = Arrays.asList(new Integer[] {
				BizType.RECHARAGE.value,
				BizType.BOSS_RECHARAGE.value,

				BizType.REWARD_BY_YINGDAOYOU.value,
				BizType.INVITEREWORD.value,

				BizType.REWARD_BY_MIS.value,
				BizType.PUNISH_BY_MIS.value,

				BizType.WITHDRAW_APPLY.value,
				BizType.WITHDRAW_DENY.value,

				BizType.PICKUP.value,
				BizType.TRANSFER.value,
				BizType.DAILY.value,
				BizType.SINGLE.value,

				BizType.CANCAL_TRANSFER.value,
				BizType.CANCAL_DAILY.value,
				BizType.CANCLE_SINGLE.value,
				BizType.CANCAL_COMMNENDATION.value });

		List<FundAccountLog> historyAccountLogs = fundAccountLogService.getAccountAllLogsByType(param.getAccountNo(), bizTypeList, param.getLimit(), param.getOffset());
		if (historyAccountLogs != null && historyAccountLogs.size() > 0) {
			totalHistoryCount = fundAccountLogService.getAccountAllLogsSizeByType(param.getAccountNo(), bizTypeList);
			Iterator<FundAccountLog> fundAccountLogIterator = historyAccountLogs.iterator();
			while (fundAccountLogIterator.hasNext()) {
				String orderNo;
				FundAccountLog fundAccountLog = fundAccountLogIterator.next();
				GFundWithdrawHistory history = new GFundWithdrawHistory();

				if (fundAccountLog.getBizType() != null) {
					history.setContent(BizType.getType(fundAccountLog.getBizType()).name);
				}

				if (StringUtils.isNoneBlank(orderNo = fundAccountLog.getOrderNo())) {
					history.setOrderNo(orderNo);
				}

				history.setAccountLogNo(fundAccountLog.getLogNo());
				history.setBizComment(fundAccountLog.getBizStatus() == 1 ? "交易成功" : "交易失败");
				history.setCreateTime(DateFormatUtils.format(fundAccountLog.getCreateTime(), DATE_FORMAT));
				history.setPrice(String.valueOf(fundAccountLog.getChangAmount()));
				histories.add(history);
			}
		}
		ReturnResult returnResult = new ReturnResult();
		returnResult.setData(histories, totalHistoryCount);
		return returnResult;
	}

	@RequestMapping(value = "v1.0/g/account/withdraw/apply", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public ReturnResult apply(FundWithdrawApplyBean fundWithdrawApplyBean, HttpServletRequest request) {
		logger.info("G-导游提现申请|输入->{}", JSON.toJSONString(fundWithdrawApplyBean)); //{"finAccount":"guide-100014070","finBankNo":"100007198","price":1177}
		FundValidator.validateParamNumberGreaterThan0(fundWithdrawApplyBean.getPrice(), "提现金额必须大于0");
		FundValidator.validateParamString(fundWithdrawApplyBean.getFinBankNo(), "finBankNo");
		FundValidator.validateParamString(fundWithdrawApplyBean.getFinAccount(), "finAccount");

		String guideId = tradeAccountContext.getUserId();
		String accountNo = tradeAccountContext.getFundId();

		OrderValidator.validateParamString(guideId, "导游ID");

		List<GuideProhibit> guideProhibits = lControllerService.getGuideProhibit(guideId, GuideProhibitEnum.PROHIBIT_TYPE_WITHDRAW);
		if (guideProhibits.size() > 0) {
			logger.error("G-导游提现申请|导游被禁用提现|" + guideId);
			throw new TradeException(TradeReturnCodeEnum.GUIDE_FORBIT_WITHDRAW);
		}

		fundwithdrawAccessValidator.validateGAPFundwithdrawRight(fundWithdrawApplyBean.getFinAccount());

		GuideBean appliedGuide = lControllerService.getGuidByGuideId(guideId);
		FundAccount fundAccountBean = fundAccountService.getFundAccount(accountNo);
		double useableAmount = fundAccountBean.getAmount();
		double expectsToGet = fundWithdrawApplyBean.getPrice();
		logger.info("G-导游提现申请|useableAmount:{} | expectsToGet:{}", useableAmount, expectsToGet);
		if (useableAmount < expectsToGet) {
			logger.error("G-导游提现申请|提现金额不能超过可提现余额， 参数:{}", JSON.toJSONString(fundWithdrawApplyBean));
			throw new FundException(FundReturnCodeEnum.ERR_PARAM, "提现金额");
		}

		fundWithdrawApplyBean.setGuideId(guideId);
		fundWithdrawApplyBean.setGuideNo(appliedGuide.getGuideNo());
		fundWithdrawApplyBean.setFinAccount(accountNo);
		Double totalAmount = fundLogService.getFutureAmount(fundAccountBean.getAccountNo());

		logger.info("G-导游提现申请|导游 [{} {} {}] 申请提现->{}", appliedGuide.getGuideId(), appliedGuide.getGuideNo(), appliedGuide.getGuideName(), JSON.toJSONString(fundWithdrawApplyBean));
		String drawNo = gFundWithdrawService.applyWithdraw(fundWithdrawApplyBean, guideId, appliedGuide.getGuideName());
		ReturnResult returnResult = new ReturnResult();
		Map<String, String> data = new HashMap<>();
		data.put("drawNo", drawNo);
		data.put("totalAmount", String.valueOf(DoubleUtil.subtractionDouble(totalAmount, expectsToGet)));
		data.put("useableAmount", String.valueOf(useableAmount - expectsToGet));
		returnResult.setData(data);
		logger.info("G-导游提现申请|drawNo:{} totalAmount:{} expectsToGet:{} result:{}", drawNo, totalAmount, expectsToGet, JSON.toJSONString(returnResult));
		return returnResult;
	}
}
