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
package com.hbc.api.fund.biz.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import scala.collection.mutable.StringBuilder;

import com.alibaba.fastjson.JSON;
import com.hbc.api.fund.account.enums.BalanceReturnCodeEnum;
import com.hbc.api.fund.account.exceptions.BalanceException;
import com.hbc.api.fund.account.service.FundAccountService;
import com.hbc.api.fund.biz.enums.FundDrawStatus;
import com.hbc.api.fund.biz.enums.FundProcessStatus;
import com.hbc.api.fund.biz.enums.FundReturnCodeEnum;
import com.hbc.api.fund.biz.exception.FundException;
import com.hbc.api.fund.biz.mapping.gen.FundWithdrawPaymentMapper;
import com.hbc.api.fund.biz.mapping.gen.FundWithdrawalMapper;
import com.hbc.api.fund.biz.mapping.gen.bean.FundBankCard;
import com.hbc.api.fund.biz.mapping.gen.bean.FundWithdrawal;
import com.hbc.api.fund.biz.mapping.gen.bean.FundWithdrawalExample;
import com.hbc.api.fund.biz.mapping.genx.FundWithdrawalMapperEnhance;
import com.hbc.api.fund.biz.mapping.genx.xbean.FundWithDrawQueryBean;
import com.hbc.api.fund.biz.mapping.genx.xbean.FundWithdrawApplyBean;
import com.hbc.api.fund.biz.mapping.genx.xbean.FundWithdrawDenyQueryBean;
import com.hbc.api.fund.biz.mapping.genx.xbean.FundWithdrawalQueryBean;
import com.hbc.api.trade.bdata.common.IDGenerotor;
import com.hbc.api.trade.bdata.common.Page;

/**
 * @author Jongly Ran
 */
@Service
public class FundWithdrawService {

	private static final Logger logger = LoggerFactory.getLogger(FundWithdrawService.class);

	@Autowired
	protected FundWithdrawalMapper fundWithdrawMapper;

	@Autowired
	protected FundBankCardService fundBankCardService;

	@Autowired
	protected FundAccountService fundAccountService;

	@Autowired
	protected FundWithdrawPaymentMapper fundWithdrawPaymentMapper;

	@Autowired
	protected FundWithdrawalMapperEnhance fundWithdrawMapperEnhance;

	final String SPILT_MARK = "|";

	final String SPILT_MARK_INNER = "^";

	public List<FundWithdrawal> getLatestFundwithdraw(String accountNo) {
		return fundWithdrawMapperEnhance.getLatestFundWithdraw(accountNo);
	}

	public List<FundWithdrawal> getFundWithdrawalList(FundWithdrawalQueryBean queryBean) {
		return fundWithdrawMapperEnhance.selectFundWithdrawalList(queryBean);
	}

	public int getFundWithdrawalListTotalSize(FundWithdrawalQueryBean queryBean) {
		return fundWithdrawMapperEnhance.selectFundWithdrawalListTotalSize(queryBean);
	}

	public FundWithdrawal getFundWithdrawalDetail(String drawNo) {
		return fundWithdrawMapper.selectByPrimaryKey(drawNo);
	}

	public List<FundWithdrawal> getFundWithdrawListByProcessStatus(FundWithDrawQueryBean queryBean) {
		FundWithdrawalExample criteria = new FundWithdrawalExample();
		FundWithdrawalExample.Criteria cc = criteria.createCriteria();
		Integer processStatus = queryBean.getProcessStatus();
		cc.andProcessStatusEqualTo(processStatus);

		if (StringUtils.isNoneBlank(queryBean.getGuideNo())) {
			cc.andGuideNoEqualTo(queryBean.getGuideNo());
		}

		if (StringUtils.isNoneBlank(queryBean.getGuideName())) {
			cc.andGuideNameLike(new StringBuilder().append("%").append(queryBean.getGuideName()).append("%").toString());
		}

		if (queryBean.getProcessStatus() == FundWithDrawQueryBean.AUTO_PROC) {
			if (queryBean.getDrawStatus() == null) {
				List<Integer> in = new ArrayList<Integer>();
				in.add(FundDrawStatus.APPLY.value);
				in.add(FundDrawStatus.HAVE_TRANSFERED.value);
				in.add(FundDrawStatus.AUTO_WITHDRAW_FAILED.value);
				in.add(FundDrawStatus.AUTO_WITHDRAW_APPLIED.value);
				cc.andDrawStatusIn(in);
			} else {
				cc.andDrawStatusEqualTo(queryBean.getDrawStatus());
			}
		}

		Page page = new Page(queryBean.getOffset(), queryBean.getLimit());
		criteria.setPage(page);

		//待处理提现列表和自动提现列表，排序都应该按申请时间的正序排列，已处理的提现申请，按处理时间的倒叙排列
		if (processStatus.equals(FundProcessStatus.INIT.value) || processStatus.equals(FundProcessStatus.AUTO_PROCESS.value)) {
			criteria.setOrderByClause("apply_time asc");
		} else if (processStatus.equals(FundProcessStatus.PROCESSED.value)) {
			criteria.setOrderByClause("update_time desc");
		}

		return fundWithdrawMapper.selectByExample(criteria);
	}

	public Integer getFundWithdrawListTotalSizeByProcessStatus(FundWithDrawQueryBean queryBean) {
		FundWithdrawalExample criteria = new FundWithdrawalExample();
		FundWithdrawalExample.Criteria cc = criteria.createCriteria();

		cc.andProcessStatusEqualTo(queryBean.getProcessStatus());

		if (StringUtils.isNoneBlank(queryBean.getGuideNo())) {
			cc.andGuideNoEqualTo(queryBean.getGuideNo());
		}

		if (StringUtils.isNoneBlank(queryBean.getGuideName())) {
			cc.andGuideNameLike(new StringBuilder().append("%").append(queryBean.getGuideName()).append("%").toString());
		}

		if (queryBean.getProcessStatus() == FundWithDrawQueryBean.AUTO_PROC) {
			if (queryBean.getDrawStatus() == null) {
				List<Integer> in = new ArrayList<Integer>();
				in.add(FundDrawStatus.APPLY.value);
				in.add(FundDrawStatus.HAVE_TRANSFERED.value);
				in.add(FundDrawStatus.AUTO_WITHDRAW_FAILED.value);
				in.add(FundDrawStatus.AUTO_WITHDRAW_APPLIED.value);
				cc.andDrawStatusIn(in);
			} else {
				cc.andDrawStatusEqualTo(queryBean.getDrawStatus());
			}
		}
		return fundWithdrawMapper.countByExample(criteria);
	}

	@Transactional
	public String applyWithdraw(FundWithdrawApplyBean fundWithdrawApplyBean, String optId, String optName) {
		logger.info("导游提现申请|提现查银行卡参数为：" + JSON.toJSONString(fundWithdrawApplyBean));
		FundBankCard fundBankCard = fundBankCardService.selectFundBankCard(fundWithdrawApplyBean.getFinBankNo());
		logger.info("导游提现申请|提现查银行卡信息：" + JSON.toJSONString(fundBankCard));
		if (fundBankCard == null) {
			logger.error("导游提交提现申请时，银行卡不存在。参数：" + JSON.toJSONString(fundWithdrawApplyBean));
			throw new FundException(FundReturnCodeEnum.ERR_PARAM, "银行卡账户");
		}

		fundWithdrawApplyBean.setGuideAgencyId(fundBankCard.getGuideAgencyId());
		fundWithdrawApplyBean.setDrawNo(IDGenerotor.generateAccountNo());
		fundWithdrawApplyBean.setDrawStatus(FundDrawStatus.APPLY.value);
		fundWithdrawApplyBean.setFinBank(fundBankCard.getBank());
		fundWithdrawApplyBean.setFinCurrency(fundBankCard.getCurrency());
		fundWithdrawApplyBean.setFinName(fundBankCard.getAccountHolderName());
		fundWithdrawApplyBean.setGuideName(fundBankCard.getGuideName());
		fundWithdrawApplyBean.setGuideId(fundBankCard.getGuideId());
		fundWithdrawApplyBean.setGuideAgencyName(fundBankCard.getGuideAgencyName());

		//fundWithdrawApplyBean.setTargetAccount(fundBankCard.getAccount());
		fundWithdrawApplyBean.setFinBankNo(fundBankCard.getAccount());

		Integer fintype = fundBankCard.getType();
		fundWithdrawApplyBean.setFinType(fintype);

		if (fintype == 2) { //2-支付宝账号
			fundWithdrawApplyBean.setIsAuto(1);
			fundWithdrawApplyBean.setProcessStatus(FundProcessStatus.AUTO_PROCESS.value);
		} else {
			fundWithdrawApplyBean.setProcessStatus(FundProcessStatus.INIT.value);
			fundWithdrawApplyBean.setIsAuto(0);
		}

		Date currentTime = new Date();
		fundWithdrawApplyBean.setApplyTime(currentTime);
		fundWithdrawApplyBean.setCreateTime(currentTime);
		fundWithdrawApplyBean.setUpdateTime(currentTime);
		logger.info("导游提现申请|导游申请提现->{}", JSON.toJSONString(fundWithdrawApplyBean));
		try {
			fundAccountService.generalWithdrawToUpdateAccount(fundWithdrawApplyBean.getPrice(), fundWithdrawApplyBean.getDrawNo(), fundWithdrawApplyBean.getFinAccount(), optId, optName);
			fundWithdrawMapper.insert(fundWithdrawApplyBean);
			return fundWithdrawApplyBean.getDrawNo();
		} catch (Exception e) {
			logger.error("导游提现申请|导游提交提现申请时，数据入库失败。参数：" + JSON.toJSONString(fundWithdrawApplyBean), e);
			throw new FundException(FundReturnCodeEnum.ERR_INSERT, "导游提交提现申请失败");
		}
	}

	@Transactional
	public Boolean finalDenyWithdraw(FundWithdrawDenyQueryBean queryBean) {
		FundWithdrawal fundWithdraw = fundWithdrawMapperEnhance.getFundWithdrawForUpdate(queryBean.getDrawNo());
		if (fundWithdraw == null || fundWithdraw.getProcessStatus() == FundProcessStatus.PROCESSED.value) {
			throw new BalanceException(BalanceReturnCodeEnum.WITHDRAW_DENY_DUPLICATE_SUBMITED, queryBean.getDrawNo());
		}

		Integer oldDrawStatus = fundWithdraw.getDrawStatus();
		Integer oldProcessStatus = fundWithdraw.getProcessStatus();

		if (oldDrawStatus == FundDrawStatus.DELETE_TO_HIDDEN.value) {
			logger.error("支付宝提现回调|状态更新异常");
			throw new FundException(FundReturnCodeEnum.ERR_UPDATE, "状态更新异常");
		}

		if (oldProcessStatus == FundProcessStatus.PROCESSED.value) {
			logger.error("支付宝提现回调|状态更新异常");
			throw new FundException(FundReturnCodeEnum.ERR_UPDATE, "状态更新异常");
		}

		FundWithdrawalExample criteria = new FundWithdrawalExample();
		criteria.createCriteria().andDrawNoEqualTo(queryBean.getDrawNo());
		fundWithdraw.setUpdateTime(new Date());
		fundWithdraw.setDrawComment(queryBean.getReason());
		fundWithdraw.setDrawStatus(FundDrawStatus.DELETE_TO_HIDDEN.value);
		fundWithdraw.setProcessStatus(FundProcessStatus.PROCESSED.value);
		fundWithdraw.setActualPrice(0d);
		fundWithdraw.setDrawComment(StringUtils.isNoneBlank(fundWithdraw.getDrawComment()) ? fundWithdraw.getDrawComment() : "" + "-> 更新BY:" + queryBean.getOptId() + "|" + queryBean.getOptName());

		logger.info("导游提现|导游提现拒绝->{} | 状态变化 DrawStatus:{} -> {} | ProcessStatus:{} -> {}", fundWithdraw, FundDrawStatus.getStatus(oldDrawStatus),
				FundDrawStatus.getStatus(fundWithdraw.getDrawStatus()), FundProcessStatus.getStatus(oldProcessStatus), FundProcessStatus.getStatus(fundWithdraw.getProcessStatus()));
		int updateWithdrawNum = fundWithdrawMapper.updateByExampleSelective(fundWithdraw, criteria);
		return (updateWithdrawNum > 0 && this.denyTransferToUpdateAccount(queryBean.getAccountNo(), queryBean.getAppliedAmount(), queryBean.getGuideId()) > 0);
	}

	private Integer denyTransferToUpdateAccount(String accountNo, Double appliedAmount, String guideId) {
		return fundAccountService.denyTransferToUpdateAccount(accountNo, appliedAmount, guideId);
	}
}
