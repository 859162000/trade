/**
 * @Author lukangle
 * @2015年12月31日@下午4:18:40
 */
package com.hbc.api.fund.biz.service;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.hbc.api.fund.account.enums.BalanceReturnCodeEnum;
import com.hbc.api.fund.account.exceptions.BalanceException;
import com.hbc.api.fund.account.mapping.gen.bean.FundAccount;
import com.hbc.api.fund.account.service.FundAccountService;
import com.hbc.api.fund.biz.enums.FundDrawPaymentStatus;
import com.hbc.api.fund.biz.enums.FundDrawStatus;
import com.hbc.api.fund.biz.enums.FundProcessStatus;
import com.hbc.api.fund.biz.enums.FundReturnCodeEnum;
import com.hbc.api.fund.biz.exception.FundException;
import com.hbc.api.fund.biz.mapping.gen.FundWithdrawPaymentMapper;
import com.hbc.api.fund.biz.mapping.gen.FundWithdrawalMapper;
import com.hbc.api.fund.biz.mapping.gen.bean.FundWithdrawPayment;
import com.hbc.api.fund.biz.mapping.gen.bean.FundWithdrawPaymentExample;
import com.hbc.api.fund.biz.mapping.gen.bean.FundWithdrawal;
import com.hbc.api.gateway.alizhifu.req.AliWithDrawNotifyParm;

@Component
public class AliFundWithdrawService {
	private static final Logger logger = LoggerFactory.getLogger(AliFundWithdrawService.class);

	final String SPILT_MARK = "|";

	final String SPILT_MARK_INNER = "^";
	@Autowired
	protected FundWithdrawalMapper fundWithdrawMapper;
	@Autowired
	protected FundWithdrawPaymentMapper fundWithdrawPaymentMapper;
	@Autowired
	protected FundAccountService fundAccountService;

	public void handleAliWithdrawCallback(AliWithDrawNotifyParm payParam) {
		logger.info("支付宝提现回调|返回信息-> {}", JSON.toJSONString(payParam));
		String batchNo = payParam.getBatch_no();
		String successDetails = payParam.getSuccess_details();
		String failedDetails = payParam.getFail_details();

		if (StringUtils.isNoneBlank(successDetails)) {
			handleWithdrawCallback(batchNo, successDetails, Boolean.TRUE);
		}

		if (StringUtils.isNoneBlank(failedDetails)) {
			handleWithdrawCallback(batchNo, failedDetails, Boolean.FALSE);
		}
	}

	@Transactional
	private void handleWithdrawCallback(String batchNo, String details, boolean status) {
		if (details == null || batchNo == null) {
			throw new IllegalArgumentException("参数异常");
		}

		String[] atoms = null;
		if (StringUtils.contains(details, SPILT_MARK)) {
			atoms = StringUtils.split(details, SPILT_MARK);
		} else {
			atoms = new String[] { details };
		}

		logger.info("支付宝提现回调|返回信息-> 批次号:{} |结果信息: {}", batchNo, details);
		// 流水号^收款方账号^收款账号姓名^付款金额^成功标识(S)^成功原因^支付宝内部流水号^完成时间。 每条记录以“|”间隔。
		// 流水号^收款方账号^收款账号姓名^付款金额^失败标识(F)^失败原因^支付宝内部流水号^完成时间。 每条记录以“|”间隔。
		for (int i = 0; i < atoms.length; i++) {
			String[] cells = StringUtils.split(atoms[i], SPILT_MARK_INNER);
			if (cells != null) { // //////////////////////////////////////////流水号
									// ////收款方账号 //付款金额
				String drawNo = cells[0];
				String payeeAccount = cells[1];
				String payeeName = cells[2];
				String payeeAmount = cells[3];
				String flag = cells[4];
				String message = cells[5];
				logger.info("支付宝提现回调|FOR-更新提现，资金账户信息-> batchNo:{} | trawNo:{} | payeeAccount:{} | payeeName:{} | payeeAmount:{} | flag:{} | reason:{} | status:{}", batchNo, drawNo, payeeAccount,
						payeeName, payeeAmount, flag, message, status);
				updateFundwithdrawPaymentStatus(batchNo, drawNo, payeeAccount, Double.valueOf(payeeAmount), status, flag, message);
				FundWithdrawal fundWithdraw = updateFundwithdrawStatus(batchNo, drawNo, Double.valueOf(payeeAmount), status);
				updateFundAccountStatus(fundWithdraw.getFinAccount(), batchNo, drawNo, Double.valueOf(payeeAmount), fundWithdraw.getGuideId(), status);
			} else {
				logger.error("支付宝提现回调|返回信息格式异常,返回结果:{}", details);
				throw new FundException(FundReturnCodeEnum.ERR_PARAM, "支付宝返回");
			}
		}
	}

	@Transactional
	private void updateFundwithdrawPaymentStatus(String batchNo, String drawNo, String payeeAccount, Double amount, boolean status, String flag, String reason) {
		FundWithdrawPaymentExample example = new FundWithdrawPaymentExample();
		example.createCriteria().andBatchNoEqualTo(batchNo).andDrawNoEqualTo(drawNo).andPayeeAccountEqualTo(payeeAccount);
		List<FundWithdrawPayment> paymentList = fundWithdrawPaymentMapper.selectByExample(example); // FundWithdrawPayment
																									// 表通过批次号和DRAWNO以及金额来查询更新。
		if (paymentList != null && paymentList.size() > 0) {
			logger.info("支付宝提现回调|更新FundwithdrawPayment 查询返回{}条记录", paymentList.size());
			Iterator<FundWithdrawPayment> paymentListiIterator = paymentList.iterator();
			while (paymentListiIterator.hasNext()) {
				FundWithdrawPayment fundWithdrawPayment = paymentListiIterator.next();
				Integer oldTransferStatus = fundWithdrawPayment.getTransferStatus();
				if (oldTransferStatus == FundDrawPaymentStatus.PAYMENT_SUCCESS.value) {
					logger.error("支付宝提现回调|状态更新异常 FundDrawPayment 状态已经为支付成功！");
					// throw new FundException(FundReturnCodeEnum.ERR_UPDATE,
					// "状态更新异常"); //FIXME
				}

				fundWithdrawPayment.setTransferStatus(status ? FundDrawPaymentStatus.PAYMENT_SUCCESS.value : FundDrawPaymentStatus.PAYMENT_FAILED.value);
				fundWithdrawPayment.setUpdateTime(new Date());
				fundWithdrawPayment.setActualAmount(status ? amount : 0d);
				String oldRemark = fundWithdrawPayment.getRemark();
				fundWithdrawPayment.setRemark(oldRemark + " |-> 回调更新信息:" + flag + "|" + reason);
				int optNum = fundWithdrawPaymentMapper.updateByPrimaryKeySelective(fundWithdrawPayment);
				if (optNum > 0) {
					logger.info("支付宝提现回调|更新FundwithdrawPayment信息:{} ,生效条数:{} |状态变化 TransferStatus (0:初始 1:已提交 2:支付成功 3:支付失败): {} -> {}", fundWithdrawPayment, optNum, oldTransferStatus,
							fundWithdrawPayment.getTransferStatus());
				} else {
					logger.error("支付宝提现回调|数据更新异常|更新FundwithdrawPayment信息:{} ,生效条数:{}", fundWithdrawPayment, optNum);

				}
			}
		} else {
			logger.error("支付宝提现回调|无自动提现支付信息-> batchNo:{} | drawNo:{} | payeeAccount:{} | amount:{} | status:{}", batchNo, drawNo, payeeAccount, amount, status);
		}
	}

	@Transactional
	private void updateFundAccountStatus(String accountNo, String batchNo, String drawNo, Double amount, String guideId, boolean status) {
		FundAccount fundAccount = fundAccountService.getFundAccount(accountNo);
		if (fundAccount == null) {
			logger.info("支付宝提现回调|资金帐号[" + accountNo + "]资金账户不存在");
			throw new BalanceException(BalanceReturnCodeEnum.ACCOUNT_NOT_EXSIT, "资金");
		}
		int optNum = fundAccountService.aliWithdrawCallbackToUpdateAccount(accountNo, amount, drawNo, status);
		if (optNum > 0) {
			logger.info("支付宝提现回调|更新FundAccount accountNo:{} | drawNo:{} | guideId:{} | amount:{} | status:{} | 生效条数:{}", accountNo, drawNo, guideId, amount, status, optNum);
		} else {
			logger.error("支付宝提现回调|数据更新异常|更新FundAccount accountNo:{} | drawNo:{} | guideId:{} | amount:{} | status:{} | 生效条数:{}", accountNo, drawNo, guideId, amount, status, optNum);
		}
	}

	@Transactional
	private FundWithdrawal updateFundwithdrawStatus(String batchNo, String drawNo, Double actualPrice, boolean status) {
		FundWithdrawal fundWithdrawal = fundWithdrawMapper.selectByPrimaryKey(drawNo);
		if (fundWithdrawal == null) {
			logger.error("支付宝提现回调|更新异常，未查询到流水号为" + drawNo + "的流水");
			throw new FundException(FundReturnCodeEnum.ERR_UPDATE, "更新异常，未查询到流水号为" + drawNo + "的流水");
		}

		Integer oldProcessStatus = fundWithdrawal.getProcessStatus();
		Integer drawStatus = fundWithdrawal.getDrawStatus();

		if (drawStatus == FundDrawStatus.HAVE_TRANSFERED.value) {
			logger.error("支付宝提现回调|状态更新异常");
			throw new FundException(FundReturnCodeEnum.ERR_UPDATE, "状态更新异常");
		}

		// 不需要判断处理状态,SINCE 二次回调

		fundWithdrawal.setUpdateTime(new Date());
		fundWithdrawal.setProcessStatus(FundProcessStatus.PROCESSED.value);
		fundWithdrawal.setDrawStatus(status ? FundDrawStatus.HAVE_TRANSFERED.value : FundDrawStatus.AUTO_WITHDRAW_FAILED.value);

		fundWithdrawal.setActualPrice(status ? actualPrice : 0d);
		int optNum = fundWithdrawMapper.updateByPrimaryKeySelective(fundWithdrawal);
		if (optNum > 0) {
			logger.info("支付宝提现回调|更新FundWithdraw 数据:{} | 生效条数:{} | 状态变化  DrawStatus: {}->{} | ProcessStatus: {} -> {}", fundWithdrawal, optNum, FundDrawStatus.getStatus(drawStatus),
					FundDrawStatus.getStatus(fundWithdrawal.getDrawStatus()), FundProcessStatus.getStatus(oldProcessStatus), FundProcessStatus.getStatus(fundWithdrawal.getDrawStatus()));
		} else {
			logger.error("支付宝提现回调|数据更新异常|更新FundWithdraw 状态:{} | 生效条数:{}", fundWithdrawal, optNum);
			throw new FundException(FundReturnCodeEnum.ERR_UPDATE, "更新 fundwithdraw数据异常");
		}
		return fundWithdrawal;
	}
}
