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

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.hbc.api.fund.account.enums.BalanceReturnCodeEnum;
import com.hbc.api.fund.biz.constraints.FundBizConstraints;
import com.hbc.api.fund.biz.mapping.gen.bean.FundWithdrawal;
import com.hbc.api.fund.biz.mapping.genx.xbean.FundTransferQueryBean;
import com.hbc.api.fund.biz.mapping.genx.xbean.FundWithDrawQueryBean;
import com.hbc.api.fund.biz.mapping.genx.xbean.FundWithdrawDenyQueryBean;
import com.hbc.api.fund.biz.mapping.genx.xbean.WithdrawListResult;
import com.hbc.api.fund.biz.service.MISFundWithdrawService;
import com.hbc.api.fund.biz.validator.FundValidator;
import com.hbc.api.gateway.alizhifu.req.TransInfo;
import com.hbc.api.gateway.enums.GatewayReturnCodeEnum;
import com.hbc.api.gateway.exception.GatewayException;
import com.hbc.api.trade.bdata.common.rsp.ReturnResult;
import com.hbc.api.trade.bdata.mapper.guide.gen.bean.GuideBean;
import com.hbc.api.trade.bdata.mapper.guide.gen.bean.GuideProhibit;
import com.hbc.api.trade.fund.req.GDSBossFundwithdrawQueryParam;
import com.hbc.api.trade.fund.req.MisFundWithdrawQueryParam;
import com.hbc.api.trade.order.controller.validator.FundwithdrawAccessValidator;
import com.hbc.api.trade.order.controller.validator.OrderValidator;
import com.hbc.api.trade.order.enums.TradeReturnCodeEnum;
import com.hbc.api.trade.order.enums.third.GuideProhibitEnum;
import com.hbc.api.trade.order.exception.TradeException;
import com.hbc.api.trade.third.LControllerService;

/**
 * @author Jongly Ran
 */
@RestController
@RequestMapping("fund")
public class MISFundWithdrawController {

	private static final Logger logger = LoggerFactory.getLogger(MISFundWithdrawController.class);

	@Autowired
	private MISFundWithdrawService misFundWithdrawService;

	@Autowired
	private FundwithdrawAccessValidator fundwithdrawAccessValidator;

	@Autowired
	private LControllerService lControllerService;

	@RequestMapping(value = "v1.0/e/account/withdraw/list", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public ReturnResult withdrawList(FundWithDrawQueryBean queryBean, HttpServletRequest request) {
		FundValidator.validateLimitAndOffset(queryBean.getLimit(), queryBean.getOffset());
		FundValidator.validateFundWithDrawQueryBean(queryBean);
		WithdrawListResult fundWithdrawResult = misFundWithdrawService.withdrawList(queryBean);
		ReturnResult returnResult = new ReturnResult();
		returnResult.setData(fundWithdrawResult.getFundWithdrawList(), fundWithdrawResult.getTotalSize());
		return returnResult;
	}

	@RequestMapping(value = "v1.0/e/account/withdraw/detail", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public ReturnResult accountDetail(String drawNo, HttpServletRequest request) {
		FundValidator.validateParamString(drawNo, "drawNo");
		FundWithdrawal fundWithdrawal = misFundWithdrawService.getFundWithdrawalDetail(drawNo);
		ReturnResult returnResult = new ReturnResult();
		returnResult.setData(fundWithdrawal);
		return returnResult;
	}

	@RequestMapping(value = "v1.0/e/account/withdraw/auto/confirm", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public ReturnResult agree(String transNo, String payeeAccount, String payeeName, Double transAmount, Double actualAmount, String guideId, String transTime, String actualAccount, String actualRemark, String optId, String optName) {
		FundValidator.validateParamString(transNo, "transNo");
		FundValidator.validateParamString(payeeName, "payeeName");
		FundValidator.validateParamString(payeeAccount, "payeeAccount");
		FundValidator.validateParamNumber(transAmount, "提现金额");
		FundValidator.validateParamString(optId, "操作人ID");
		FundValidator.validateParamString(optName, "操作人姓名");
		FundValidator.validateParamString(actualAccount, "我方支付账号");
		FundValidator.validateParamString(actualRemark, "备注");

		List<TransInfo> batchTransferList = new ArrayList<TransInfo>(1);
		batchTransferList.add(new TransInfo(transNo, payeeAccount, payeeName, guideId, transAmount, actualAmount, actualAccount, actualRemark, "提现操作"));
		return batch_agree(batchTransferList, actualAmount, transTime, optId, optName);
	}

	@RequestMapping(value = "v1.0/e/account/withdraw/auto/json", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public ReturnResult batch_agree_json(@RequestBody MisFundWithdrawQueryParam queryParm) {
		return batch_agree(queryParm.getBatchTransferList(), queryParm.getTotalAmount(), queryParm.getTransTime(), queryParm.getOptId(), queryParm.getOptName());
	}

	@RequestMapping(value = "v1.0/e/boss/withdraw/json", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public ReturnResult boss_auto_transfer(@RequestBody(required = true) GDSBossFundwithdrawQueryParam queryParm) {
		logger.info("BOSS-提现申请->{}", JSON.toJSONString(queryParm));

		OrderValidator.validateParamString(queryParm.getTargetBossGuideId(), "TargetBossGuideId");
		OrderValidator.validateParamString(queryParm.getTargetAccountNo(), "TargetAccountNo");
		OrderValidator.validateParamString(queryParm.getTargetBankNo(), "TargetBankNo");
		OrderValidator.validateParamString(queryParm.getOptId(), "OptId");
		OrderValidator.validateParamString(queryParm.getOptName(), "OptName");

		String guideId = queryParm.getTargetBossGuideId();
		String finAccount = queryParm.getTargetAccountNo();

		List<GuideProhibit> guideProhibits = lControllerService.getGuideProhibit(guideId, GuideProhibitEnum.PROHIBIT_TYPE_WITHDRAW);
		if (guideProhibits.size() > 0) {
			logger.error("BOSS-提现申请|导游被禁用提现|" + guideId);
			throw new TradeException(TradeReturnCodeEnum.GUIDE_FORBIT_WITHDRAW);
		}

		fundwithdrawAccessValidator.validateMISFundwithdrawRight(finAccount);
		GuideBean guideBean = lControllerService.getGuidByGuideId(guideId);

		String drawNo = misFundWithdrawService.bossTransfer(queryParm.getBossTransferGuideInfos(), queryParm.getTotalAmount(), guideId, guideBean.getGuideNo(),
				finAccount, queryParm.getTargetBankNo(), queryParm.getOptId(), queryParm.getOptName());
		ReturnResult resturnResult = new ReturnResult();
		resturnResult.setData(drawNo);
		return resturnResult;
	}

	@RequestMapping(value = "v1.0/e/account/withdraw/auto/batch", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public ReturnResult batch_agree(List<TransInfo> batchTransferList, @RequestParam(required = true) Double totalAmount, String transTime, @RequestParam(required = true) String optId, @RequestParam(required = true) String optName) {
		if (batchTransferList == null || batchTransferList.size() <= 0 || batchTransferList.size() > 1000
				|| totalAmount < 0 || StringUtils.isBlank(transTime) || StringUtils.isBlank(optId) || StringUtils.isBlank(optName)) {
			throw new GatewayException(GatewayReturnCodeEnum.ERR_TRANS_LIST_PARM);
		}

		Iterator<TransInfo> transferInfoIterator = batchTransferList.iterator();
		while (transferInfoIterator.hasNext()) {
			TransInfo transInfo = (TransInfo) transferInfoIterator.next();
			accessWithdrawStatus(transInfo.getGuideId(), transInfo.getTransNo());
		}

		Date transdate = null;
		try {
			transdate = DateUtils.parseDate(transTime, FundBizConstraints.CCLX_DATE_PATTERN_SHORT);
		} catch (ParseException e) {
			logger.error("{}", ExceptionUtils.getStackTrace(e));
			throw new GatewayException(GatewayReturnCodeEnum.ERR_TRANS_PARM_DATE_ILLEGAL);
		}

		String url = misFundWithdrawService.tryToTransfer(batchTransferList, totalAmount, transdate, optId, optName, FundBizConstraints.CCLX_DATE_PATTERN_SHORT);
		logger.info("Ali-Withdraw-URL->{}", url);
		ReturnResult resturnResult = new ReturnResult();
		resturnResult.setData(url);
		return resturnResult;
	}

	@RequestMapping(value = "v1.0/e/account/withdraw/deny", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public ReturnResult withdraw_deny(FundWithdrawDenyQueryBean queryBean) {
		FundValidator.validateFundWithdrawDenyQueryBean(queryBean);
		boolean success = misFundWithdrawService.finalDenyWithdraw(queryBean);
		ReturnResult returnResult = new ReturnResult();
		returnResult.setMessage("OK");
		if (!success) {
			returnResult.setStatus(BalanceReturnCodeEnum.WITHDRAW_CANCELED_FAILED.getCode());
			returnResult.setMessage(BalanceReturnCodeEnum.WITHDRAW_CANCELED_FAILED.getMessage());
		}
		return returnResult;
	}

	@RequestMapping(value = "v1.0/e/account/withdraw/transfer", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public ReturnResult withdraw_transfer(FundTransferQueryBean queryBean) {
		FundValidator.validateParamString(queryBean.getGuideId(), "导游ID");
		accessWithdrawStatus(queryBean.getGuideId(), queryBean.getDrawNo());

		boolean success = misFundWithdrawService.tryToTransfer(queryBean);
		ReturnResult returnResult = new ReturnResult();
		returnResult.setMessage("OK");
		if (!success) {
			returnResult.setStatus(BalanceReturnCodeEnum.WITHDRAW_NONE_AUTO_TRANSFER_FAILED.getCode());
			returnResult.setMessage(BalanceReturnCodeEnum.WITHDRAW_NONE_AUTO_TRANSFER_FAILED.getMessage());
		}
		return returnResult;
	}

	private void accessWithdrawStatus(String guideId, String drawNo)
	{
		if (StringUtils.isBlank(guideId)) {
			logger.error("MIS-导游提现申请|导游ID为空,流水号:{}", drawNo);
			throw new GatewayException(GatewayReturnCodeEnum.ERR_TRANS_PARM_ILLEGAL, drawNo);
		}

		List<GuideProhibit> guideProhibits = lControllerService.getGuideProhibit(guideId, GuideProhibitEnum.PROHIBIT_TYPE_WITHDRAW);
		if (guideProhibits.size() > 0) {
			logger.error("MIS-导游提现申请|导游被禁用提现|ID:{} 流水号:{}", guideId, drawNo);
			throw new GatewayException(GatewayReturnCodeEnum.ERR_TRANS_GUIDE_FROZEN, drawNo);
		}
	}
}
