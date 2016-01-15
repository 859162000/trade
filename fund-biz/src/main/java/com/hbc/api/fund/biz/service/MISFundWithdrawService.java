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

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.hbc.api.fund.account.enums.BizType;
import com.hbc.api.fund.account.mapping.gen.bean.FundAccount;
import com.hbc.api.fund.account.service.FundAccountService;
import com.hbc.api.fund.account.service.RechargeService;
import com.hbc.api.fund.biz.enums.FundBankCardType;
import com.hbc.api.fund.biz.enums.FundDrawPaymentStatus;
import com.hbc.api.fund.biz.enums.FundDrawStatus;
import com.hbc.api.fund.biz.enums.FundProcessStatus;
import com.hbc.api.fund.biz.enums.FundReturnCodeEnum;
import com.hbc.api.fund.biz.exception.FundException;
import com.hbc.api.fund.biz.mapping.gen.bean.FundWithdrawPayment;
import com.hbc.api.fund.biz.mapping.gen.bean.FundWithdrawPaymentExample;
import com.hbc.api.fund.biz.mapping.gen.bean.FundWithdrawal;
import com.hbc.api.fund.biz.mapping.gen.bean.FundWithdrawalExample;
import com.hbc.api.fund.biz.mapping.genx.xbean.BossTransferGuideInfo;
import com.hbc.api.fund.biz.mapping.genx.xbean.FundTransferQueryBean;
import com.hbc.api.fund.biz.mapping.genx.xbean.FundWithDrawQueryBean;
import com.hbc.api.fund.biz.mapping.genx.xbean.FundWithdrawApplyBean;
import com.hbc.api.fund.biz.mapping.genx.xbean.WithdrawListResult;
import com.hbc.api.gateway.alizhifu.GAliTransPayService;
import com.hbc.api.gateway.alizhifu.config.AlipayConfig;
import com.hbc.api.gateway.alizhifu.req.TransInfo;
import com.hbc.api.trade.bdata.common.IDGenerotor;
import com.hbc.api.trade.bdata.common.util.DoubleUtil;

/**
 * 转帐使用GAliTransPayService
 * 
 * @author Jongly Ran
 */
@Service
public class MISFundWithdrawService extends FundWithdrawService {

	private final static Logger logger = LoggerFactory.getLogger(MISFundWithdrawService.class);

	@Autowired
	private GAliTransPayService gAliTransPayService;

	@Autowired
	private AlipayConfig alipayConfig;

	@Autowired
	protected FundAccountService fundAccountService;

	@Autowired
	protected RechargeService rechargeService;

	@Transactional
	public String tryToTransfer(List<TransInfo> batchTransferList, Double totalAmount, Date transTime, String optId, String optName, String pattern) {
		if (batchTransferList == null || batchTransferList.size() <= 0) {
			return "";
		}

		logger.info("导游提现|自动提现申请->{} 总金额->{}", batchTransferList, totalAmount);
		String batchNo = IDGenerotor.generateFundWithDrawNo();
		Iterator<TransInfo> transInfoIterator = batchTransferList.iterator();
		while (transInfoIterator.hasNext()) {
			TransInfo transInfo = transInfoIterator.next();
			// TODO 重复提现申请校验
			FundWithdrawPaymentExample example = new FundWithdrawPaymentExample();
			example.createCriteria().andDrawNoEqualTo(transInfo.getTransNo());
			List<FundWithdrawPayment> payments = fundWithdrawPaymentMapper.selectByExample(example);
			if (payments != null && payments.size() > 0) {
				Iterator<FundWithdrawPayment> paymentsIterator = payments.iterator();
				while (paymentsIterator.hasNext()) {
					FundWithdrawPayment fundWithdrawPayment = paymentsIterator.next();
					// 支付状态 0：初始 | 1：已提交 | 2：支付成功 | 3：支付失败
					Integer transStatus = fundWithdrawPayment.getTransferStatus();
					if (transStatus == FundDrawPaymentStatus.PAYMENT_SUCCESS.value) {
						throw new FundException(FundReturnCodeEnum.ERR_UPDATE, "流水号[" + fundWithdrawPayment.getDrawNo() + "] 重复提现.");
					}
				}
			}

			FundWithdrawPayment payment = new FundWithdrawPayment();
			String paymentId = IDGenerotor.generatePaymentId();
			Date currentDate = new Date();
			payment.setPaymentId(paymentId);
			payment.setBatchNo(batchNo);
			payment.setDrawNo(transInfo.getTransNo());
			payment.setCreateTime(currentDate);
			payment.setGuideId(transInfo.getGuideId() == null ? "" : transInfo.getGuideId());
			payment.setOperatorId(optId);
			payment.setOperatorName(optName);
			payment.setPayeeAccount(transInfo.getPayeeAccount());
			payment.setPayeeName(transInfo.getPayeeName());
			payment.setPayerAccount(alipayConfig.sellerEmail);
			payment.setPaymentMethod(FundBankCardType.ALIPAY.value);
			payment.setActualAmount(transInfo.getActualAmount());
			payment.setTransferAmount(transInfo.getTransAmount());
			payment.setTransferDatetime(transTime);
			payment.setTransferStatus(FundDrawPaymentStatus.PAYMENT_INIT.value);
			final String remark = "[" + currentDate + "] - 操作员[" + optId + "(" + optName + ")" + "] 提交自动转账(实际总金额):[" + transInfo.getActualAmount() + "]元到:[" + transInfo.getPayeeAccount() + "],批次号:["
					+ batchNo + "],流水号:[" + transInfo.getTransNo() + "]";
			payment.setRemark(remark);
			logger.info("导游提现|申请提现记录-> {}", payment);
			fundWithdrawPaymentMapper.insert(payment);

			FundWithdrawal fundWithdrawal = fundWithdrawMapper.selectByPrimaryKey(transInfo.getTransNo());
			if (fundWithdrawal == null) {
				logger.error("流水号 {} 不存在于导游提现列表中。", transInfo.getTransNo());
				throw new FundException(FundReturnCodeEnum.ERR_UPDATE, "流水号不存在");
			}

			Integer oldDrawStatus = fundWithdrawal.getDrawStatus();
			if (oldDrawStatus == FundDrawStatus.AUTO_WITHDRAW_APPLIED.value) {
				logger.error("导游提现|状态更新异常");
				throw new FundException(FundReturnCodeEnum.ERR_UPDATE, "状态更新异常");
			}

			FundWithdrawalExample criteria = new FundWithdrawalExample();
			criteria.createCriteria().andDrawNoEqualTo(transInfo.getTransNo());
			fundWithdrawal.setDrawStatus(FundDrawStatus.AUTO_WITHDRAW_APPLIED.value);
			fundWithdrawal.setUpdateTime(currentDate);
			fundWithdrawal.setAccount(transInfo.getActualAccount());
			fundWithdrawal.setDrawComment(StringUtils.isNoneBlank(fundWithdrawal.getDrawComment()) ? fundWithdrawal.getDrawComment() : "" + "->" + transInfo.getActualRemark() + "更新BY:" + optId + " "
					+ optName);
			logger.info("导游提现|更新提现记录-> {} | 状态变化: {} -> {}", fundWithdrawal, FundDrawStatus.getStatus(oldDrawStatus), FundDrawStatus.getStatus(fundWithdrawal.getDrawStatus()));
			fundWithdrawMapper.updateByExampleSelective(fundWithdrawal, criteria);
		}

		String aliDate = DateFormatUtils.format(transTime, pattern);
		String returnUrl = gAliTransPayService.getFundTransferUrl(batchNo, batchTransferList, totalAmount, aliDate);
		return returnUrl;
	}

	public WithdrawListResult withdrawList(FundWithDrawQueryBean queryBean) {
		WithdrawListResult result = new WithdrawListResult();
		List<FundWithdrawal> retList = super.getFundWithdrawListByProcessStatus(queryBean);
		Integer totalSize = super.getFundWithdrawListTotalSizeByProcessStatus(queryBean);
		result.setFundWithdrawList(retList);
		result.setTotalSize(totalSize);
		return result;
	}

	public void disagree(String drawNo) {
		if (fundWithdrawMapperEnhance.disagree(drawNo) == 0) {
			logger.error("驳回导游提交提现申请时，更新数据失败。参数：drawNo=" + drawNo);
			throw new FundException(FundReturnCodeEnum.ERR_UPDATE, "驳回导游提交提现申请时，更新数据失败");
		}
	}

	public double getFundWithdrawalTotalAmount(String guideId) {
		return fundWithdrawMapperEnhance.getFundWithdrawalTotalAmount(guideId);
	}

	@Transactional
	public boolean tryToTransfer(FundTransferQueryBean queryBean) {
		String drawNo = queryBean.getDrawNo();
		FundWithdrawal fundWithdrawal = fundWithdrawMapper.selectByPrimaryKey(drawNo);

		if (fundWithdrawal == null) {
			throw new FundException(FundReturnCodeEnum.ERR_NOT_FUND, "该提现申请");
		}

		Integer oldDrawStatus = fundWithdrawal.getDrawStatus();
		Integer oldProcessStatus = fundWithdrawal.getProcessStatus();

		if (oldDrawStatus == FundDrawStatus.HAVE_TRANSFERED.value) {
			logger.error("支付宝提现回调|状态更新异常");
			throw new FundException(FundReturnCodeEnum.ERR_UPDATE, "状态更新异常");
		}

		if (oldProcessStatus == FundProcessStatus.PROCESSED.value) {
			logger.error("支付宝提现回调|状态更新异常");
			throw new FundException(FundReturnCodeEnum.ERR_UPDATE, "状态更新异常");
		}

		fundWithdrawal.setPrice(queryBean.getTransferAmount());
		fundWithdrawal.setActualPrice(queryBean.getActualAmount());
		fundWithdrawal.setAdminId(queryBean.getOptId());
		fundWithdrawal.setAdminName(queryBean.getOptName());
		fundWithdrawal.setDrawStatus(FundDrawStatus.HAVE_TRANSFERED.value);
		fundWithdrawal.setProcessStatus(FundProcessStatus.PROCESSED.value);
		fundWithdrawal.setUpdateTime(new Date());
		String payAccount = queryBean.getPayAccount();

		if (StringUtils.isNoneBlank(payAccount)) {
			fundWithdrawal.setAccount(payAccount); // OPTIONAL
		}
		fundWithdrawal.setDrawComment(StringUtils.isNoneBlank(fundWithdrawal.getDrawComment()) ? fundWithdrawal.getDrawComment() : "" + "->" + "导游提现确认BY:" + queryBean.getOptId() + " "
				+ queryBean.getOptName());
		logger.info("导游提现|非自动提现确认->{} | 状态变化 DrawStatus:{} -> {} | ProcessStatus:{} -> {}", fundWithdrawal, FundDrawStatus.getStatus(oldDrawStatus),
				FundDrawStatus.getStatus(fundWithdrawal.getDrawStatus()), FundProcessStatus.getStatus(oldProcessStatus), FundProcessStatus.getStatus(fundWithdrawal.getProcessStatus()));
		int effectNumUpdateWithdraw = fundWithdrawMapper.updateByPrimaryKeySelective(fundWithdrawal);
		int effectNumUpdateAccount = 0;
		if (effectNumUpdateWithdraw > 0) {
			effectNumUpdateAccount = fundAccountService.confirmTransferToUpdateAccount(fundWithdrawal.getFinAccount(), queryBean.getActualAmount(),
					queryBean.getGuideId() == null ? "" : queryBean.getGuideId(), fundWithdrawal.getDrawNo());
		}
		return (effectNumUpdateWithdraw > 0 && effectNumUpdateAccount > 0);
	}

	@Transactional
	public String bossTransfer(List<BossTransferGuideInfo> bossTransferGuideInfos, Double totalAmount, String targetBossGuideId, String targetBossGuideNo, String targetAccountNo, String targetBankNo, String optId, String optName)
	{
		//1.计算地接社导游列表总金额，进行校验
		validBossTransferGuideInfo(bossTransferGuideInfos, totalAmount, targetBossGuideId, targetBossGuideNo, targetAccountNo, targetBankNo, optId, optName);

		//2.每个导游资金账户扣款 
		//3.每个导游资金账户扣款后记录流水
		Iterator<BossTransferGuideInfo> bossTransferGuideInfosIterator = bossTransferGuideInfos.iterator();
		while (bossTransferGuideInfosIterator.hasNext()) {
			BossTransferGuideInfo bossTransferGuideInfo = (BossTransferGuideInfo) bossTransferGuideInfosIterator.next();
			String accountNo = bossTransferGuideInfo.getAccountNo();
			Double amount = bossTransferGuideInfo.getAmount();
			String remark = new StringBuilder().append("地接社老板提现->").append(accountNo).append(" 扣金额->").append(amount).append(" 目标账号->").append(targetAccountNo).toString();
			rechargeService.recharge(accountNo, amount, BizType.BOSS_RECHARAGE, optId, optName, remark, targetAccountNo);
		}

		//4.为老板资金账户总额打款
		String remark = new StringBuilder().append("地接社老板提现->").append(targetAccountNo).append(" 老板入账金额->").append(totalAmount).toString();
		fundAccountService.pay(targetAccountNo, totalAmount, BizType.BOSS_RECHARAGE, optId, optName, remark);

		//5.老板资金账户提交提现申请
		FundWithdrawApplyBean fundWithdrawApplyBean = new FundWithdrawApplyBean();
		fundWithdrawApplyBean.setPrice(totalAmount);
		fundWithdrawApplyBean.setGuideId(targetBossGuideId);
		fundWithdrawApplyBean.setGuideNo(targetBossGuideNo);

		fundWithdrawApplyBean.setFinBankNo(targetBankNo);
		fundWithdrawApplyBean.setFinAccount(targetAccountNo);

		FundAccount fundAccountBean = fundAccountService.getFundAccount(targetAccountNo);
		double useableAmount = fundAccountBean.getAmount();
		double expectsToGet = fundWithdrawApplyBean.getPrice();
		if (useableAmount < expectsToGet) {
			logger.error("BOSS-提现申请|提现金额不能超过可提现余额， 参数:{}", JSON.toJSONString(fundWithdrawApplyBean));
			throw new FundException(FundReturnCodeEnum.ERR_PARAM, "提现金额");
		}

		logger.info("BOSS-提现申请-APPLY->{}", JSON.toJSONString(fundWithdrawApplyBean));
		return applyWithdraw(fundWithdrawApplyBean, optId, optName);
	}

	/**
	 * 
	 * @param bossTransferGuideInfos
	 * @param totalAmount
	 * @param targetBossGuideId
	 * @param targetBossGuideNo
	 * @param targetAccountNo
	 * @param targetBankNo
	 * @param optId
	 * @param optName
	 */
	private void validBossTransferGuideInfo(List<BossTransferGuideInfo> bossTransferGuideInfos, Double totalAmount, String targetBossGuideId, String targetBossGuideNo, String targetAccountNo, String targetBankNo, String optId, String optName)
	{
		if (bossTransferGuideInfos == null || bossTransferGuideInfos.size() <= 0 || totalAmount.compareTo(0.0d) <= 0
				|| StringUtils.isBlank(targetBossGuideId) || StringUtils.isBlank(targetAccountNo) || StringUtils.isBlank(targetBossGuideNo)
				|| StringUtils.isBlank(optId) || StringUtils.isBlank(optName) || StringUtils.isBlank(targetBankNo)) {
			throw new FundException(FundReturnCodeEnum.ERR_INSERT, "参数异常");
		}

		Double allTotalAmountInlist = 0.0d;
		Iterator<BossTransferGuideInfo> bossTransferGuideInfosIterator = bossTransferGuideInfos.iterator();
		while (bossTransferGuideInfosIterator.hasNext()) {
			BossTransferGuideInfo bossTransferGuideInfo = (BossTransferGuideInfo) bossTransferGuideInfosIterator.next();
			allTotalAmountInlist = DoubleUtil.addDouble(allTotalAmountInlist, bossTransferGuideInfo.getAmount());
		}

		logger.info("BOSS-导游提现申请|列表总额:{} |申请额:{}", allTotalAmountInlist, totalAmount);
		if (!allTotalAmountInlist.equals(totalAmount)) {
			throw new FundException(FundReturnCodeEnum.ERR_INSERT, "金额不匹配");
		}
	}
}
