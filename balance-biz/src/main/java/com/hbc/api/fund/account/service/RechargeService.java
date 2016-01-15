/**
 * @Author lukangle
 * @2016年1月4日@下午4:04:06
 */
package com.hbc.api.fund.account.service;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.hbc.api.fund.account.enums.AccountChangeType;
import com.hbc.api.fund.account.enums.AccountStatus;
import com.hbc.api.fund.account.enums.BalanceReturnCodeEnum;
import com.hbc.api.fund.account.enums.BizStatus;
import com.hbc.api.fund.account.enums.BizType;
import com.hbc.api.fund.account.exceptions.BalanceException;
import com.hbc.api.fund.account.mapping.gen.FundAccountLogMapper;
import com.hbc.api.fund.account.mapping.gen.FundAccountMapper;
import com.hbc.api.fund.account.mapping.gen.bean.FundAccount;
import com.hbc.api.fund.account.mapping.gen.bean.FundAccountLog;
import com.hbc.api.fund.account.mapping.genx.WtFundAccountMapper;
import com.hbc.api.trade.bdata.common.IDGenerotor;
import com.hbc.api.trade.bdata.common.enums.Sysuser;
import com.hbc.api.trade.bdata.common.util.DoubleUtil;
@Component
public class RechargeService {
	private final static Logger logger = LoggerFactory.getLogger(FundAccountService.class);

	@Autowired
	private FundAccountLogMapper fundAccountLogMapper;
	@Autowired
	private FundAccountMapper fundAccountMapper;
	@Autowired
	private WtFundAccountMapper wtFundAccountMapper;
	
	/**
	 * 导游处罚
	 * 
	 * @param accountNo
	 * @param amount
	 * @param bizType
	 * @param accountChangeType
	 * @param orderNo
	 * @param optId
	 * @param optName
	 * @param remark
	 */
	@Transactional
	public void punished(String accountNo, double amount, BizType bizType, AccountChangeType accountChangeType,String orderNo, String optId, String optName,String remark) {
		logger.info("用户 [" + optId + "]处罚[" + accountNo + "] " + amount + " 元");
		FundAccount fundAccount = wtFundAccountMapper.forupdateByAccountNo(accountNo);
		AccountStatus accountStatus = AccountStatus.getType(fundAccount.getAccountStatus());
		if (AccountStatus.NOMAL.equals(accountStatus)) {
			double famount = DoubleUtil.subtractionDouble(fundAccount.getAmount(), amount);
//			if (famount < 0) {
//				throw new BalanceException(BalanceReturnCodeEnum.ACCOUNT_MONEY_NOTE, accountNo);
//			}
			Date curtime = new Date();
			FundAccountLog fundAccountLog = new FundAccountLog();
			fundAccountLog.setAccountNo(accountNo);
			fundAccountLog.setAmountCg(famount);
			fundAccountLog.setChangeType(accountChangeType.value);
			fundAccountLog.setAmountCur(fundAccount.getAmount());
			fundAccountLog.setChangAmount(-amount);
			fundAccountLog.setCreateTime(curtime);
			fundAccountLog.setRemark1("支付  " + amount);
			fundAccountLog.setLogNo("R" + IDGenerotor.generateAccountLogNo());
			fundAccountLog.setBizType(bizType.value);
			fundAccountLog.setOpUserId(optId);
			fundAccountLog.setOpUserName(optName);
			fundAccountLog.setBizTime(curtime);
			fundAccountLog.setBizStatus(BizStatus.SUCCESS.value);
			fundAccountLog.setUpdateTime(curtime);
			fundAccountLog.setUserId(fundAccount.getUserId());
			fundAccountLog.setOrderNo(orderNo);
			fundAccountLog.setRemark1(remark);
			fundAccountLogMapper.insert(fundAccountLog);
			fundAccount.setAmount(famount);
			int optnum = wtFundAccountMapper.updateAmount(fundAccount);

			if (optnum != 1) {
				throw new BalanceException(BalanceReturnCodeEnum.ACCOUNT_SYSTEM_ERROR, accountNo);
			}
		} else {
			throw new BalanceException(BalanceReturnCodeEnum.ACCOUNT_FROZEN, accountNo);
		}
	}
	
	/**
	 * 提款 必须标识操作用户
	 * 
	 * @param accountNo
	 * @param amount
	 * @param optid
	 * @param optname
	 */
	@Transactional
	public void recharge(String accountNo, double amount, BizType bizType, String optid, String optname, String remark2, String targetAccountNo) {
		logger.info("用户 [" + optid + "]提款资金账户[" + accountNo + "] " + amount + " 元");
		FundAccount fundAccount = wtFundAccountMapper.forupdateByAccountNo(accountNo);
		AccountStatus accountStatus = AccountStatus.getType(fundAccount.getAccountStatus());
		if (AccountStatus.NOMAL.equals(accountStatus)) {
			double famount = DoubleUtil.subtractionDouble(fundAccount.getAmount(), amount);
			if (famount < 0) {
				throw new BalanceException(BalanceReturnCodeEnum.ACCOUNT_MONEY_NOTE, accountNo);
			}
			Date curtime = new Date();
			FundAccountLog fundAccountLog = new FundAccountLog();
			fundAccountLog.setAccountNo(accountNo);
			fundAccountLog.setTargetAccountNo(targetAccountNo);
			fundAccountLog.setAmountCg(famount);
			fundAccountLog.setChangeType(AccountChangeType.System.value);
			fundAccountLog.setAmountCur(fundAccount.getAmount());
			fundAccountLog.setChangAmount(-amount);
			fundAccountLog.setCreateTime(curtime);
			fundAccountLog.setRemark1("支付  " + amount);
			fundAccountLog.setRemark2(remark2);
			fundAccountLog.setLogNo("R" + IDGenerotor.generateAccountLogNo());
			fundAccountLog.setBizType(bizType.value);
			fundAccountLog.setOpUserId(optid);
			fundAccountLog.setOpUserName(optname);
			fundAccountLog.setBizTime(curtime);
			fundAccountLog.setBizStatus(BizStatus.SUCCESS.value);
			fundAccountLog.setUpdateTime(curtime);
			fundAccountLog.setUserId(fundAccount.getUserId());
			fundAccountLogMapper.insert(fundAccountLog);
			fundAccount.setAmount(famount);
			int optnum = wtFundAccountMapper.updateAmount(fundAccount);

			if (optnum != 1) {
				throw new BalanceException(BalanceReturnCodeEnum.ACCOUNT_SYSTEM_ERROR, accountNo);
			}
		} else {
			throw new BalanceException(BalanceReturnCodeEnum.ACCOUNT_FROZEN, accountNo);
		}
	}

	/**
	 * 取款
	 * 
	 * @param accountNo
	 * @param amount
	 */
	@Transactional
	public void recharge(String accountNo, double amount) {
		logger.info("消费资金账户[" + accountNo + "] " + amount + " 元");
		FundAccount fundAccount = wtFundAccountMapper.forupdateByAccountNo(accountNo);
		AccountStatus accountStatus = AccountStatus.getType(fundAccount.getAccountStatus());

		if (AccountStatus.NOMAL.equals(accountStatus)) {
			double famount = DoubleUtil.subtractionDouble(fundAccount.getAmount(), amount);
			if (famount < 0) {
				throw new BalanceException(BalanceReturnCodeEnum.ACCOUNT_MONEY_NOTE, accountNo);
			}
			Date curtime = new Date();
			FundAccountLog fundAccountLog = new FundAccountLog();
			fundAccountLog.setAccountNo(accountNo);
			fundAccountLog.setAmountCg(famount);
			fundAccountLog.setChangeType(AccountChangeType.System.value);
			fundAccountLog.setAmountCur(fundAccount.getAmount());
			fundAccountLog.setChangAmount(-amount);
			fundAccountLog.setCreateTime(curtime);
			fundAccountLog.setRemark1("支付  " + amount);
			fundAccountLog.setBizType(BizType.RECHARAGE.value);
			fundAccountLog.setOpUserId(Sysuser.SYSUSER.id);
			fundAccountLog.setOpUserName(Sysuser.SYSUSER.name);
			fundAccountLog.setBizTime(curtime);
			fundAccountLog.setLogNo("R" + IDGenerotor.generateAccountLogNo());
			fundAccountLog.setBizStatus(BizStatus.SUCCESS.value);
			fundAccountLog.setUpdateTime(curtime);
			fundAccountLog.setUserId(fundAccount.getUserId());
			fundAccountLogMapper.insert(fundAccountLog);
			fundAccount.setAmount(famount);
			int optnum = wtFundAccountMapper.updateAmount(fundAccount);

			if (optnum != 1) {
				throw new BalanceException(BalanceReturnCodeEnum.ACCOUNT_SYSTEM_ERROR, accountNo);
			}
		} else {
			throw new BalanceException(BalanceReturnCodeEnum.ACCOUNT_FROZEN, accountNo);
		}
	}

	@Transactional
	public void recharge(String accountNo, double amount, BizType bizType, String orderNo) {
		logger.info("消费资金账户[" + accountNo + "] " + amount + " 元");
		FundAccount fundAccount = wtFundAccountMapper.forupdateByAccountNo(accountNo);
		AccountStatus accountStatus = AccountStatus.getType(fundAccount.getAccountStatus());
		if (AccountStatus.NOMAL.equals(accountStatus)) {
			double famount = DoubleUtil.subtractionDouble(fundAccount.getAmount(), amount);
			if (famount < 0) {
				throw new BalanceException(BalanceReturnCodeEnum.ACCOUNT_MONEY_NOTE, accountNo);
			}
			Date curtime = new Date();
			FundAccountLog fundAccountLog = new FundAccountLog();
			fundAccountLog.setAccountNo(accountNo);
			fundAccountLog.setAmountCg(famount);
			fundAccountLog.setChangeType(AccountChangeType.System.value);
			fundAccountLog.setAmountCur(fundAccount.getAmount());
			fundAccountLog.setChangAmount(-amount);
			fundAccountLog.setCreateTime(curtime);
			fundAccountLog.setRemark1("支付  " + amount);
			fundAccountLog.setBizType(bizType.value);
			fundAccountLog.setOpUserId(Sysuser.SYSUSER.id);
			fundAccountLog.setOpUserName(Sysuser.SYSUSER.name);
			fundAccountLog.setBizTime(curtime);
			fundAccountLog.setOrderNo(orderNo);
			fundAccountLog.setLogNo("R" + IDGenerotor.generateAccountLogNo());
			fundAccountLog.setBizStatus(BizStatus.SUCCESS.value);
			fundAccountLog.setUpdateTime(curtime);
			fundAccountLog.setUserId(fundAccount.getUserId());
			fundAccountLogMapper.insert(fundAccountLog);
			fundAccount.setAmount(famount);
			int optnum = wtFundAccountMapper.updateAmount(fundAccount);
			if (optnum != 1) {
				throw new BalanceException(BalanceReturnCodeEnum.ACCOUNT_SYSTEM_ERROR, accountNo);
			}
		} else {
			throw new BalanceException(BalanceReturnCodeEnum.ACCOUNT_FROZEN, accountNo);
		}
	}
}
