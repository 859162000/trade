/**
 * @Author lukangle
 * @2015年11月3日@下午4:29:37
 */
package com.hbc.api.fund.account.service;

import java.sql.Timestamp;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import scala.collection.mutable.StringBuilder;

import com.hbc.api.fund.account.enums.AccountChangeType;
import com.hbc.api.fund.account.enums.AccountStatus;
import com.hbc.api.fund.account.enums.AccountType;
import com.hbc.api.fund.account.enums.BalanceReturnCodeEnum;
import com.hbc.api.fund.account.enums.BizStatus;
import com.hbc.api.fund.account.enums.BizType;
import com.hbc.api.fund.account.exceptions.BalanceException;
import com.hbc.api.fund.account.mapping.gen.FundAccountLogMapper;
import com.hbc.api.fund.account.mapping.gen.FundAccountMapper;
import com.hbc.api.fund.account.mapping.gen.bean.FundAccount;
import com.hbc.api.fund.account.mapping.gen.bean.FundAccountExample;
import com.hbc.api.fund.account.mapping.gen.bean.FundAccountLog;
import com.hbc.api.fund.account.mapping.genx.WtFundAccountMapper;
import com.hbc.api.fund.account.parm.PayParm;
import com.hbc.api.trade.bdata.common.IDGenerotor;
import com.hbc.api.trade.bdata.common.enums.Sysuser;
import com.hbc.api.trade.bdata.common.util.DoubleUtil;

@Component
public class FundAccountService {
	private final static Logger logger = LoggerFactory.getLogger(FundAccountService.class);

	@Autowired
	private FundAccountLogMapper fundAccountLogMapper;
	@Autowired
	private FundAccountMapper fundAccountMapper;
	@Autowired
	private WtFundAccountMapper wtFundAccountMapper;

	public Boolean enable(String accountNo, String optId, String optName) {
		logger.info(accountNo + " 被用户" + optId + " " + optName + "启用");
		return accountStatus(accountNo, optId, optName, AccountStatus.NOMAL.value) > 0;
	}

	public Boolean disable(String accountNo, String optId, String optName) {
		logger.info(accountNo + " 被用户" + optId + " " + optName + "禁用");
		return accountStatus(accountNo, optId, optName, AccountStatus.Frozen.value) > 0;
	}

	private int accountStatus(String accountNo, String optId, String optName, Integer status) {
		FundAccount fundAccount = fundAccountMapper.selectByPrimaryKey(accountNo);
		if (fundAccount == null) {
			logger.info("资金帐号[" + accountNo + "]资金账户不存在");
			throw new BalanceException(BalanceReturnCodeEnum.ACCOUNT_NOT_EXSIT, "资金");
		}

		FundAccountExample criteria = new FundAccountExample();
		criteria.createCriteria().andAccountNoEqualTo(accountNo);
		FundAccount account = new FundAccount();
		account.setAccountStatus(status);
		int num = fundAccountMapper.updateByExampleSelective(account, criteria);

		Date curtime = new Date();
		FundAccountLog fundAccountLog = new FundAccountLog();
		fundAccountLog.setAccountNo(accountNo);
		fundAccountLog.setChangeType(AccountChangeType.System.value);
		fundAccountLog.setCreateTime(curtime);
		fundAccountLog.setLogNo("R" + IDGenerotor.generateAccountLogNo());
		fundAccountLog.setBizType(BizType.ACCOUNT_OPERATION.value);
		fundAccountLog.setOpUserId(optId);
		fundAccountLog.setOpUserName(optName);
		fundAccountLog.setBizStatus(num > 0 ? BizStatus.SUCCESS.value : BizStatus.FAILED.value);
		fundAccountLog.setBizTime(curtime);
		fundAccountLog.setUpdateTime(curtime);
		fundAccountLog.setUserId(fundAccount.getUserId());
		fundAccountLog.setRemark1("资金账户操作  " + status);
		int optnum = fundAccountLogMapper.insert(fundAccountLog);
		if (optnum != 1) {
			throw new BalanceException(BalanceReturnCodeEnum.ACCOUNT_SYSTEM_ERROR, accountNo);
		}
		return num;
	}

	public FundAccount getFundAccount(String accountNo) {
		FundAccount fundAccount = fundAccountMapper.selectByPrimaryKey(accountNo);
		if (fundAccount == null) {
			logger.info("资金帐号[" + accountNo + "]资金账户不存在");
			throw new BalanceException(BalanceReturnCodeEnum.ACCOUNT_NOT_EXSIT, "资金");
		}
		if (fundAccount.getFrozenAmount() == null) {
			fundAccount.setFrozenAmount(0.0);
		}
		if (fundAccount.getAmount() == null) {
			fundAccount.setAmount(0.0);
		}
		return fundAccount;
	}

	public String addAccount(String areacode, String mobile, String accountName, String userId, AccountType accountType) {
		String accountNo = "FN" + IDGenerotor.generateAccountNo();
		logger.info("用户 [" + userId + "]添加资金账户 " + accountNo + " ");
		FundAccount fundAccount = new FundAccount();
		fundAccount.setAccountName(accountName);
		fundAccount.setAccountNo(accountNo);
		fundAccount.setAccountStatus(AccountStatus.NOMAL.value);
		fundAccount.setAccountType(accountType.value);
		fundAccount.setCreateTime(new Date());
		fundAccount.setUserId(userId);
		fundAccountMapper.insert(fundAccount);
		return accountNo;
	}

	/**
	 * 用在用户注册时自动创建资金账户。
	 * 
	 * @param accountNo
	 *            此处为了事务一致，从外围传递
	 * @param areacode
	 * @param mobile
	 * @param accountName
	 * @param userId
	 * @param accountType
	 * @return
	 */
	public String addAccount(String accountNo, String areacode, String mobile, String accountName, String userId, AccountType accountType) {
		logger.info("用户 [" + userId + "]添加资金账户 " + accountNo + " ");
		FundAccount fundAccount = new FundAccount();
		fundAccount.setAccountName(accountName);
		fundAccount.setAccountNo(accountNo);
		fundAccount.setAccountStatus(AccountStatus.NOMAL.value);
		fundAccount.setAccountType(accountType.value);
		fundAccount.setCreateTime(new Date());
		fundAccount.setUserId(userId);
		fundAccountMapper.insert(fundAccount);
		return accountNo;
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
	public void recharge(String accountNo, double amount, String optid, String optname) {
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
			fundAccountLog.setAmountCg(famount);
			fundAccountLog.setChangeType(AccountChangeType.System.value);
			fundAccountLog.setAmountCur(fundAccount.getAmount());
			fundAccountLog.setChangAmount(-amount);
			fundAccountLog.setCreateTime(curtime);
			fundAccountLog.setRemark1("支付  " + amount);
			fundAccountLog.setLogNo("R" + IDGenerotor.generateAccountLogNo());
			fundAccountLog.setBizType(BizType.RECHARAGE.value);
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

	/**
	 * 企业支付充值
	 * 
	 * @param accountId
	 * @param amount
	 */
	@Transactional
	public void pay(PayParm payParm) {
		FundAccount fundAccount = wtFundAccountMapper.forupdateByAccountNo(payParm.getAccountNo());
		logger.info("充值资金账户[" + payParm.getAccountNo() + "] " + payParm.getAmount() + " 元");
		AccountStatus accountStatus = AccountStatus.getType(fundAccount.getAccountStatus());
		if (AccountStatus.NOMAL.equals(accountStatus)) {
			Date curtime = new Date();
			FundAccountLog fundAccountLog = new FundAccountLog();
			fundAccountLog.setAccountNo(fundAccount.getAccountNo());
			fundAccountLog.setAmountCg(DoubleUtil.addDouble(fundAccount.getAmount(), payParm.getAmount()));
			fundAccountLog.setChangeType(AccountChangeType.COMPAY.value);
			fundAccountLog.setAmountCur(fundAccount.getAmount());
			fundAccountLog.setChangAmount(payParm.getAmount());
			fundAccountLog.setCreateTime(curtime);
			fundAccountLog.setRemark1("充值 " + payParm.getAmount());
			fundAccountLog.setBizType(BizType.getType(payParm.getBizType()).value);
			fundAccountLog.setOpUserId(payParm.getOptid());
			fundAccountLog.setOpUserName(payParm.getOptnam());
			fundAccountLog.setComment(payParm.getComment());
			fundAccountLog.setRemark1(payParm.getRemark());
			fundAccountLog.setUserId(payParm.getUserId());
			fundAccountLog.setUserName(payParm.getUserName());
			fundAccountLog.setBizStatus(BizStatus.SUCCESS.value);
			fundAccountLog.setBizTime(curtime);
			fundAccountLog.setLogNo("C" + IDGenerotor.generateAccountLogNo());
			fundAccountLog.setBizStatus(BizStatus.SUCCESS.value);
			fundAccountLog.setUpdateTime(curtime);
			fundAccountLogMapper.insert(fundAccountLog);

			fundAccount.setAmount(DoubleUtil.addDouble(fundAccount.getAmount(), payParm.getAmount()));
			fundAccount.setTotalAmount(DoubleUtil.addDouble(fundAccount.getTotalAmount(), payParm.getAmount()));
			int optnum = wtFundAccountMapper.updateAmount(fundAccount);

			if (optnum != 1) {
				throw new BalanceException(BalanceReturnCodeEnum.ACCOUNT_SYSTEM_ERROR, payParm.getAccountNo());
			}
		} else {
			throw new BalanceException(BalanceReturnCodeEnum.ACCOUNT_FROZEN, fundAccount.getAccountNo());
		}

	}

	@Transactional
	public void pay(String accountNo, double amount) {
		FundAccount fundAccount = wtFundAccountMapper.forupdateByAccountNo(accountNo);
		AccountStatus accountStatus = AccountStatus.getType(fundAccount.getAccountStatus());

		logger.info("充值资金账户[" + accountNo + "] " + amount + " 元");
		if (AccountStatus.NOMAL.equals(accountStatus)) {
			Timestamp curtime = new Timestamp(System.currentTimeMillis());
			FundAccountLog fundAccountLog = new FundAccountLog();
			fundAccountLog.setAccountNo(accountNo);
			fundAccountLog.setAmountCg(DoubleUtil.addDouble(fundAccount.getAmount(), amount));
			fundAccountLog.setChangeType(AccountChangeType.System.value);
			fundAccountLog.setAmountCur(fundAccount.getAmount());
			fundAccountLog.setChangAmount(amount);
			fundAccountLog.setCreateTime(curtime);
			fundAccountLog.setRemark1("充值 " + amount);
			fundAccountLog.setBizType(BizType.PAY.value);
			fundAccountLog.setOpUserId(Sysuser.SYSUSER.id);
			fundAccountLog.setOpUserName(Sysuser.SYSUSER.name);
			fundAccountLog.setLogNo("C" + IDGenerotor.generateAccountLogNo());
			fundAccountLog.setBizTime(curtime);
			fundAccountLog.setBizStatus(BizStatus.SUCCESS.value);
			fundAccountLog.setUpdateTime(curtime);
			fundAccountLog.setUserId(fundAccount.getUserId());
			fundAccountLogMapper.insert(fundAccountLog);

			fundAccount.setAmount(DoubleUtil.addDouble(fundAccount.getAmount(), amount));
			fundAccount.setTotalAmount(DoubleUtil.addDouble(fundAccount.getTotalAmount(), amount));
			int optnum = wtFundAccountMapper.updateAmount(fundAccount);
			if (optnum != 1) {
				throw new BalanceException(BalanceReturnCodeEnum.ACCOUNT_SYSTEM_ERROR, accountNo);
			}
		} else {
			throw new BalanceException(BalanceReturnCodeEnum.ACCOUNT_FROZEN, accountNo);
		}

	}

	/**
	 * 包含操作人 以及
	 * 
	 * @param accountNo
	 * @param amount
	 * @param bizType
	 * @param orderNo
	 * @param optId
	 * @param optName
	 */
	@Transactional
	public void pay(String accountNo, double amount, BizType bizType, AccountChangeType accountChangeType, String orderNo, String optId, String optName, String remark) {
		logger.info("充值资金账户[" + accountNo + "] " + amount + " 元");
		FundAccount fundAccount = wtFundAccountMapper.forupdateByAccountNo(accountNo);
		if (fundAccount == null) {
			logger.error("导游资金账户不存在，参数：accountNo=" + accountNo + ", withdrawOrderNo=" + orderNo);
			throw new BalanceException(BalanceReturnCodeEnum.ACCOUNT_NOT_EXSIT, accountNo);
		}
		AccountStatus accountStatus = AccountStatus.getType(fundAccount.getAccountStatus());
		if (AccountStatus.NOMAL.equals(accountStatus)) {
			Timestamp curtime = new Timestamp(System.currentTimeMillis());
			FundAccountLog fundAccountLog = new FundAccountLog();
			fundAccountLog.setAccountNo(accountNo);
			fundAccountLog.setAmountCg(DoubleUtil.addDouble(fundAccount.getAmount(), amount));
			fundAccountLog.setChangeType(accountChangeType.value);
			fundAccountLog.setAmountCur(fundAccount.getAmount());
			fundAccountLog.setChangAmount(amount);
			fundAccountLog.setCreateTime(curtime);
			fundAccountLog.setRemark1(bizType.name + amount);
			fundAccountLog.setBizType(bizType.value);
			fundAccountLog.setOpUserId(optId);
			fundAccountLog.setOpUserName(optName);
			fundAccountLog.setOrderNo(orderNo);
			fundAccountLog.setLogNo("C" + IDGenerotor.generateAccountLogNo());
			fundAccountLog.setBizTime(curtime);
			fundAccountLog.setBizStatus(BizStatus.SUCCESS.value);
			fundAccountLog.setUpdateTime(curtime);
			fundAccountLog.setUserId(fundAccount.getUserId());
			fundAccountLog.setRemark1(remark);
			fundAccountLogMapper.insert(fundAccountLog);

			fundAccount.setAmount(DoubleUtil.addDouble(fundAccount.getAmount(), amount));
			fundAccount.setTotalAmount(DoubleUtil.addDouble(fundAccount.getTotalAmount(), amount));
			int optnum = wtFundAccountMapper.updateAmount(fundAccount);
			if (optnum != 1) {
				throw new BalanceException(BalanceReturnCodeEnum.ACCOUNT_SYSTEM_ERROR, accountNo);
			}
		} else {
			throw new BalanceException(BalanceReturnCodeEnum.ACCOUNT_FROZEN, accountNo);
		}
	}

	@Transactional
	public void pay(String accountNo, double amount, BizType bizType, String orderNo) {
		logger.info("充值资金账户[" + accountNo + "] " + amount + " 元");
		FundAccount fundAccount = wtFundAccountMapper.forupdateByAccountNo(accountNo);
		if (fundAccount == null) {
			logger.error("导游资金账户不存在，参数：accountNo=" + accountNo + ", withdrawOrderNo=" + orderNo);
			throw new BalanceException(BalanceReturnCodeEnum.ACCOUNT_NOT_EXSIT, accountNo);
		}
		AccountStatus accountStatus = AccountStatus.getType(fundAccount.getAccountStatus());
		if (AccountStatus.NOMAL.equals(accountStatus)) {
			Timestamp curtime = new Timestamp(System.currentTimeMillis());
			FundAccountLog fundAccountLog = new FundAccountLog();
			fundAccountLog.setAccountNo(accountNo);
			fundAccountLog.setAmountCg(DoubleUtil.addDouble(fundAccount.getAmount(), amount));
			fundAccountLog.setChangeType(AccountChangeType.System.value);
			fundAccountLog.setAmountCur(fundAccount.getAmount());
			fundAccountLog.setChangAmount(amount);
			fundAccountLog.setCreateTime(curtime);
			fundAccountLog.setRemark1(bizType.name + amount);
			fundAccountLog.setBizType(bizType.value);
			fundAccountLog.setOpUserId(Sysuser.SYSUSER.id);
			fundAccountLog.setOpUserName(Sysuser.SYSUSER.name);
			fundAccountLog.setOrderNo(orderNo);
			fundAccountLog.setLogNo("C" + IDGenerotor.generateAccountLogNo());
			fundAccountLog.setBizTime(curtime);
			fundAccountLog.setBizStatus(BizStatus.SUCCESS.value);
			fundAccountLog.setUpdateTime(curtime);
			fundAccountLog.setUserId(fundAccount.getUserId());
			fundAccountLogMapper.insert(fundAccountLog);

			fundAccount.setAmount(DoubleUtil.addDouble(fundAccount.getAmount(), amount));
			fundAccount.setTotalAmount(DoubleUtil.addDouble(fundAccount.getTotalAmount(), amount));
			int optnum = wtFundAccountMapper.updateAmount(fundAccount);
			if (optnum != 1) {
				throw new BalanceException(BalanceReturnCodeEnum.ACCOUNT_SYSTEM_ERROR, accountNo);
			}
		} else {
			throw new BalanceException(BalanceReturnCodeEnum.ACCOUNT_FROZEN, accountNo);
		}
	}

	@Transactional
	public void pay(String accountNo, double amount, BizType bizType, String optId, String optName) {
		pay(accountNo, amount, bizType, optId, optName, "");
	}

	@Transactional
	public void pay(String accountNo, double amount, BizType bizType, String optId, String optName, String remark2) {
		logger.info("充值资金账户[" + accountNo + "] " + amount + " 元");
		FundAccount fundAccount = wtFundAccountMapper.forupdateByAccountNo(accountNo);
		if (fundAccount == null) {
			throw new BalanceException(BalanceReturnCodeEnum.ACCOUNT_NOT_EXSIT, accountNo);
		}
		AccountStatus accountStatus = AccountStatus.getType(fundAccount.getAccountStatus());
		if (AccountStatus.NOMAL.equals(accountStatus)) {
			Timestamp curtime = new Timestamp(System.currentTimeMillis());
			FundAccountLog fundAccountLog = new FundAccountLog();
			fundAccountLog.setAccountNo(accountNo);
			fundAccountLog.setAmountCg(DoubleUtil.addDouble(fundAccount.getAmount(), amount));
			fundAccountLog.setChangeType(AccountChangeType.System.value);
			fundAccountLog.setAmountCur(fundAccount.getAmount());
			fundAccountLog.setChangAmount(amount);
			fundAccountLog.setCreateTime(curtime);
			fundAccountLog.setRemark1(bizType.name + amount);
			fundAccountLog.setRemark2(remark2);
			fundAccountLog.setBizType(bizType.value);
			fundAccountLog.setOpUserId(optId);
			fundAccountLog.setOpUserName(optName);
			fundAccountLog.setLogNo("C" + IDGenerotor.generateAccountLogNo());
			fundAccountLog.setBizTime(curtime);
			fundAccountLog.setBizStatus(BizStatus.SUCCESS.value);
			fundAccountLog.setUpdateTime(curtime);
			fundAccountLog.setUserId(fundAccount.getUserId());
			fundAccountLogMapper.insert(fundAccountLog);

			fundAccount.setAmount(DoubleUtil.addDouble(fundAccount.getAmount(), amount));
			fundAccount.setTotalAmount(DoubleUtil.addDouble(fundAccount.getTotalAmount(), amount));
			int optnum = wtFundAccountMapper.updateAmount(fundAccount);
			if (optnum != 1) {
				throw new BalanceException(BalanceReturnCodeEnum.ACCOUNT_SYSTEM_ERROR, accountNo);
			}
		} else {
			throw new BalanceException(BalanceReturnCodeEnum.ACCOUNT_FROZEN, accountNo);
		}
	}

	/**
	 * @param applyAmount
	 *            导游申请提现金额
	 * @param orderNo
	 *            订单号
	 * @param guideId
	 *            导游编号
	 * @param accountNo
	 *            资金账号
	 * @param bizType
	 *            业务类型
	 */
	public void generalWithdrawToUpdateAccount(Double applyAmount, String drawNo, String accountNo, String optId, String optName) {
		logger.info("提现资金账户[" + accountNo + "] " + applyAmount + " 元");
		validateAmountGreaterThan0(applyAmount);
		FundAccount fundAccount = wtFundAccountMapper.forupdateByAccountNo(accountNo);
		if (fundAccount == null) {
			logger.error("导游资金账户不存在，参数：accountNo=" + accountNo + ", withdrawOrderNo=" + drawNo);
			throw new BalanceException(BalanceReturnCodeEnum.ACCOUNT_NOT_EXSIT, accountNo);
		}
		AccountStatus accountStatus = AccountStatus.getType(fundAccount.getAccountStatus());
		if (AccountStatus.NOMAL.equals(accountStatus)) {
			if (DoubleUtil.subtractionDouble(fundAccount.getAmount(), applyAmount) < 0) {
				throw new BalanceException(BalanceReturnCodeEnum.WITHDRAW_FAILED, accountNo);
			}
			Timestamp curtime = new Timestamp(System.currentTimeMillis());
			FundAccountLog fundAccountLog = new FundAccountLog();
			fundAccountLog.setAccountNo(accountNo);
			fundAccountLog.setAmountCg(DoubleUtil.addDouble(fundAccount.getFrozenAmount(), applyAmount));
			fundAccountLog.setChangeType(AccountChangeType.System.value);
			fundAccountLog.setAmountCur(fundAccount.getAmount());
			fundAccountLog.setChangAmount(-applyAmount);
			fundAccountLog.setCreateTime(curtime);

			fundAccountLog.setRemark1(new StringBuilder().append("导游申请提现|").append(applyAmount).toString());
			fundAccountLog.setRemark2("导游申请提现|" + accountNo + "|申请金额:" + applyAmount);

			fundAccountLog.setBizType(BizType.WITHDRAW_APPLY.value);
			fundAccountLog.setOpUserId(optId);
			fundAccountLog.setOpUserName(optName);
			fundAccountLog.setLogNo("C" + IDGenerotor.generateAccountLogNo());
			fundAccountLog.setBizTime(curtime);
			fundAccountLog.setBizStatus(BizStatus.SUCCESS.value);
			fundAccountLog.setUpdateTime(curtime);
			fundAccountLog.setUserId(fundAccount.getUserId());
			fundAccountLog.setPayNo(drawNo); //FIXME 暂时用此字段填写提现流水号。用于G端导游提现流水查询银行卡号信息
			logger.info("插入资金账户日志->{}", fundAccountLog);
			fundAccountLogMapper.insert(fundAccountLog);

			fundAccount.setAmount(DoubleUtil.subtractionDouble(fundAccount.getAmount(), applyAmount));
			fundAccount.setFrozenAmount(DoubleUtil.addDouble(fundAccount.getFrozenAmount(), applyAmount));
			logger.info("开始更新资金账号信息->{} | {}", accountNo, fundAccount);
			int updateAmountNum = wtFundAccountMapper.updateAmount(fundAccount);
			int frozenAmountNum = wtFundAccountMapper.updateFrozenAmount(fundAccount);
			if (updateAmountNum != 1 && frozenAmountNum != 1) {
				throw new BalanceException(BalanceReturnCodeEnum.ACCOUNT_SYSTEM_ERROR, accountNo);
			}
		} else {
			throw new BalanceException(BalanceReturnCodeEnum.ACCOUNT_FROZEN, accountNo);
		}
	}

	@Transactional
	public Integer denyTransferToUpdateAccount(String accountNo, Double appliedAmount, String guideId) {
		logger.info("导游提现被拒->accountNo:{} | apply:{}", accountNo, appliedAmount);
		validateAmountGreaterThan0(appliedAmount);
		FundAccount fundAccount = wtFundAccountMapper.forupdateByAccountNo(accountNo);
		if (fundAccount == null) {
			logger.error("导游资金账户不存在，参数：accountNo=" + accountNo);
			throw new BalanceException(BalanceReturnCodeEnum.ACCOUNT_NOT_EXSIT, accountNo);
		}

		AccountStatus accountStatus = AccountStatus.getType(fundAccount.getAccountStatus());
		if (accountStatus == null) {
			logger.error("导游资金账户状态异常，参数：accountNo=" + accountNo);
			throw new BalanceException(BalanceReturnCodeEnum.ACCOUNT_ABNORMAL, accountNo);
		}

		double frozenAmount = fundAccount.getFrozenAmount() == null ? 0d : fundAccount.getFrozenAmount();
		if (AccountStatus.NOMAL.equals(accountStatus)) {
			if (frozenAmount < appliedAmount) {
				logger.error("冻结金额小于申请操作金额  {} | {}", frozenAmount, appliedAmount);
				throw new BalanceException(BalanceReturnCodeEnum.WITHDRAW_DENY_FROZEN_AMOUNT_ABNORMAL, accountNo);
			}
			Timestamp curtime = new Timestamp(System.currentTimeMillis());
			FundAccountLog fundAccountLog = new FundAccountLog();
			fundAccountLog.setAccountNo(accountNo);
			fundAccountLog.setAmountCg(DoubleUtil.addDouble(fundAccount.getAmount(), appliedAmount));
			fundAccountLog.setChangeType(AccountChangeType.System.value);
			fundAccountLog.setAmountCur(fundAccount.getAmount());
			fundAccountLog.setChangAmount(appliedAmount);
			fundAccountLog.setCreateTime(curtime);

			fundAccountLog.setRemark1(new StringBuilder().append("提现申请被退回|").append("金额:").append(appliedAmount).toString());
			fundAccountLog.setRemark2(new StringBuilder().append("提现申请被退回|").append(accountNo).append("申请:").append(appliedAmount).append("|冻结:").append(frozenAmount).toString());

			fundAccountLog.setBizType(BizType.WITHDRAW_DENY.value);
			fundAccountLog.setOpUserId(guideId);
			fundAccountLog.setLogNo("C" + IDGenerotor.generateAccountLogNo());
			fundAccountLog.setBizTime(curtime);
			fundAccountLog.setBizStatus(BizStatus.SUCCESS.value);
			fundAccountLog.setUpdateTime(curtime);
			fundAccountLog.setUserId(fundAccount.getUserId());
			logger.info("插入资金账户日志->{}", fundAccountLog);
			fundAccountLogMapper.insert(fundAccountLog);

			fundAccount.setAmount(DoubleUtil.addDouble(fundAccount.getAmount(), appliedAmount));
			fundAccount.setFrozenAmount(DoubleUtil.subtractionDouble(frozenAmount, appliedAmount));
			logger.info("开始更新资金账号信息->{} | {}", accountNo, fundAccount);
			int optnum = wtFundAccountMapper.updateAmount(fundAccount);
			int frozenAmountNum = wtFundAccountMapper.updateFrozenAmount(fundAccount);

			if (optnum != 1 && frozenAmountNum != 1) {
				throw new BalanceException(BalanceReturnCodeEnum.ACCOUNT_SYSTEM_ERROR, accountNo);
			}
			return optnum;

		} else {
			throw new BalanceException(BalanceReturnCodeEnum.ACCOUNT_FROZEN, accountNo);
		}
	}

	@Transactional
	public int aliWithdrawCallbackToUpdateAccount(String accountNo, Double amount, String drawNo, boolean status) {
		validateAmountGreaterThan0(amount);
		FundAccount fundAccount = wtFundAccountMapper.forupdateByAccountNo(accountNo);
		if (fundAccount == null) {
			logger.error("支付宝提现回调|导游资金账户不存在，参数：accountNo=" + accountNo);
			throw new BalanceException(BalanceReturnCodeEnum.ACCOUNT_NOT_EXSIT, accountNo);
		}

		Double oldAmount = fundAccount.getAmount();
		Double oldFrozenAmount = fundAccount.getFrozenAmount();
		Double oldTotalAmount = fundAccount.getTotalAmount();

		AccountStatus accountStatus = AccountStatus.getType(fundAccount.getAccountStatus());
		double frozenAmount = fundAccount.getFrozenAmount() == null ? 0d : fundAccount.getFrozenAmount();
		int optnum = 0;
		if (AccountStatus.NOMAL.equals(accountStatus)) {
			if (frozenAmount < amount) {
				logger.error("支付宝提现回调|冻结金额小于申请操作金额  {} | {}", frozenAmount, amount);
				throw new BalanceException(BalanceReturnCodeEnum.WITHDRAW_DENY_FROZEN_AMOUNT_ABNORMAL, accountNo);
			}
			Timestamp curtime = new Timestamp(System.currentTimeMillis());
			FundAccountLog fundAccountLog = new FundAccountLog();
			fundAccountLog.setAccountNo(accountNo);
			fundAccountLog.setAmountCg(fundAccount.getAmount()); //变化后的金额
			fundAccountLog.setChangeType(AccountChangeType.System.value);
			fundAccountLog.setAmountCur(fundAccount.getAmount()); //当前金额
			fundAccountLog.setChangAmount(amount);
			fundAccountLog.setCreateTime(curtime);

			fundAccountLog.setRemark1(new StringBuilder().append(status ? "提现成功" : "提现失败").append("|金额:").append(amount).toString());
			fundAccountLog.setRemark2(new StringBuilder().append("提现支付宝回调|").append(accountNo).append("|金额:").append(amount).toString());

			fundAccountLog.setBizType(BizType.RECHARAGE.value);
			fundAccountLog.setLogNo(new StringBuilder().append(status ? "S" : "F").append(IDGenerotor.generateAccountLogNo()).toString());
			fundAccountLog.setBizTime(curtime);
			fundAccountLog.setBizStatus(status ? BizStatus.SUCCESS.value : BizStatus.FAILED.value);
			fundAccountLog.setUpdateTime(curtime);
			fundAccountLog.setUserId(fundAccount.getUserId());
			fundAccountLog.setPayNo(drawNo); //FIXME 暂时用此字段填写提现流水号。用于G端导游提现流水查询银行卡号信息

			logger.info("支付宝提现回调|插入资金账户日志->{}", fundAccountLog);
			fundAccountLogMapper.insert(fundAccountLog);

			if (status) {

				fundAccount.setFrozenAmount(DoubleUtil.subtractionDouble(frozenAmount, amount));

				logger.info("支付宝提现回调|开始更新资金账号信息->{} | {} | 资金账户变化 Amount: {} -> {} | FrozenAmount: {} -> {} | TotalAmount: {} -> {} | ChangeAmount:{}",
						accountNo, fundAccount, oldAmount, fundAccount.getAmount(), oldFrozenAmount, fundAccount.getFrozenAmount(), oldTotalAmount, fundAccount.getTotalAmount(), amount);
				optnum = wtFundAccountMapper.updateAmount(fundAccount);
				int frozenAmountNum = wtFundAccountMapper.updateFrozenAmount(fundAccount);
				if (optnum != 1 && frozenAmountNum != 1) {
					throw new BalanceException(BalanceReturnCodeEnum.ACCOUNT_SYSTEM_ERROR, accountNo);
				}
				return optnum;
			} else {
				fundAccount.setFrozenAmount(DoubleUtil.subtractionDouble(frozenAmount, amount));
				logger.info("支付宝提现回调|开始更新资金账号信息->{} | {} | 资金账户变化 Amount: {} -> {} | FrozenAmount: {} -> {} | TotalAmount: {} -> {} | ChangeAmount:{}",
						accountNo, fundAccount, oldAmount, fundAccount.getAmount(), oldFrozenAmount, fundAccount.getFrozenAmount(), oldTotalAmount, fundAccount.getTotalAmount(), amount);

				fundAccount.setAmount(DoubleUtil.addDouble(fundAccount.getAmount(), amount));
				fundAccount.setFrozenAmount(DoubleUtil.subtractionDouble(frozenAmount, amount));

				optnum = wtFundAccountMapper.updateAmount(fundAccount);
				int frozenAmountNum = wtFundAccountMapper.updateFrozenAmount(fundAccount);
				if (optnum != 1 && frozenAmountNum != 1) {
					throw new BalanceException(BalanceReturnCodeEnum.ACCOUNT_SYSTEM_ERROR, accountNo);
				}
				return optnum;
			}
		} else {
			throw new BalanceException(BalanceReturnCodeEnum.ACCOUNT_FROZEN, accountNo);
		}
	}

	/**
	 * MIS 支付宝以外的提现 确认
	 * 
	 * @param accountNo
	 * @param actualAmount
	 * @param guideId
	 * @param drawNo
	 * @return
	 */
	@Transactional
	public Integer confirmTransferToUpdateAccount(String accountNo, Double actualAmount, String guideId, String drawNo) {
		validateAmountGreaterThan0(actualAmount);
		FundAccount fundAccount = wtFundAccountMapper.forupdateByAccountNo(accountNo);
		if (fundAccount == null) {
			logger.error("导游资金账户不存在，参数：accountNo=" + accountNo);
			throw new BalanceException(BalanceReturnCodeEnum.ACCOUNT_NOT_EXSIT, accountNo);
		}

		AccountStatus accountStatus = AccountStatus.getType(fundAccount.getAccountStatus());
		double frozenAmount = fundAccount.getFrozenAmount() == null ? 0d : fundAccount.getFrozenAmount();
		if (AccountStatus.NOMAL.equals(accountStatus)) {
			if (frozenAmount < actualAmount) {
				logger.error("冻结金额小于申请操作金额  {} | {}", frozenAmount, actualAmount);
				throw new BalanceException(BalanceReturnCodeEnum.WITHDRAW_CONFIRM_FROZEN_AMOUNT_ABNORMAL, accountNo);
			}
			Timestamp curtime = new Timestamp(System.currentTimeMillis());
			FundAccountLog fundAccountLog = new FundAccountLog();
			fundAccountLog.setAccountNo(accountNo);
			fundAccountLog.setAmountCg(DoubleUtil.subtractionDouble(fundAccount.getAmount(), actualAmount));
			fundAccountLog.setChangeType(AccountChangeType.System.value);
			fundAccountLog.setAmountCur(fundAccount.getAmount());
			fundAccountLog.setChangAmount(actualAmount);
			fundAccountLog.setCreateTime(curtime);

			fundAccountLog.setRemark1(new StringBuilder().append("待处理转账确认|").append("金额:").append(actualAmount).toString());
			fundAccountLog.setRemark2(new StringBuilder().append("待处理转账确认|").append(accountNo).append("|金额:").append(actualAmount).toString());

			fundAccountLog.setBizType(BizType.RECHARAGE.value);
			fundAccountLog.setOpUserId(guideId);
			fundAccountLog.setLogNo("W" + IDGenerotor.generateAccountLogNo());
			fundAccountLog.setBizTime(curtime);
			fundAccountLog.setBizStatus(BizStatus.SUCCESS.value);
			fundAccountLog.setUpdateTime(curtime);
			fundAccountLog.setPayNo(drawNo); //FIXME 暂时用此字段填写提现流水号。用于G端导游提现流水查询银行卡号信息
			fundAccountLog.setUserId(fundAccount.getUserId());
			logger.info("插入资金账户日志->{}", fundAccountLog);
			fundAccountLogMapper.insert(fundAccountLog);
			logger.info("开始更新资金账号信息->{} | {}", accountNo, fundAccount);
			fundAccount.setFrozenAmount(DoubleUtil.subtractionDouble(frozenAmount, actualAmount));
			int frozenAmountNum = wtFundAccountMapper.updateFrozenAmount(fundAccount);

			if (frozenAmountNum != 1 && frozenAmountNum != 1) {
				throw new BalanceException(BalanceReturnCodeEnum.ACCOUNT_SYSTEM_ERROR, accountNo);
			}
			return frozenAmountNum;
		} else {
			throw new BalanceException(BalanceReturnCodeEnum.ACCOUNT_FROZEN, accountNo);
		}
	}

	private void validateAmountGreaterThan0(Double amount) {
		if (amount.compareTo(0.0d) <= 0) {
			throw new BalanceException(BalanceReturnCodeEnum.ACCOUNT_ILLEGAL_AMOUNT_SUBMITED);
		}
	}
}
