/**
 * @Author lukangle
 * @2015年11月21日@下午6:07:32
 */
package com.hbc.api.fund.account.service;

import java.sql.Timestamp;

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
public class FundFrozenAccountService {
	@Autowired
	FundAccountLogMapper fundAccountLogMapper;
	@Autowired
	FundAccountMapper fundAccountMapper;
	@Autowired
	FundAccountService fundAccountService;
	@Autowired
	WtFundAccountMapper wtFundAccountMapper;
	@Transactional
	public void payFrozen(String accountNo,double amount,BizType bizType,String orderNo){
		FundAccount fundAccount = fundAccountService.getFundAccount(accountNo);
		AccountStatus accountStatus = AccountStatus.getType(fundAccount.getAccountStatus());
		if(AccountStatus.NOMAL.equals(accountStatus)){ 
			Timestamp curtime = new Timestamp(System.currentTimeMillis());
			FundAccountLog fundAccountLog = new FundAccountLog();
			fundAccountLog.setAccountNo(accountNo);
			fundAccountLog.setAmountCg(DoubleUtil.addDouble(fundAccount.getAmount(), amount));
			fundAccountLog.setChangeType(AccountChangeType.System.value);
			fundAccountLog.setAmountCur(fundAccount.getAmount());
			fundAccountLog.setChangAmount(-amount);
			fundAccountLog.setCreateTime(curtime);
			fundAccountLog.setRemark1(bizType.name +amount);
			fundAccountLog.setBizType(bizType.value);
			fundAccountLog.setOpUserId(Sysuser.SYSUSER.id);
			fundAccountLog.setOpUserName(Sysuser.SYSUSER.name);
			fundAccountLog.setOrderNo(orderNo);
			fundAccountLog.setLogNo("C"+IDGenerotor.generateAccountLogNo());
			fundAccountLog.setBizTime(curtime);
			fundAccountLog.setBizStatus(BizStatus.SUCCESS.value);
			fundAccountLog.setUpdateTime(curtime);
			fundAccountLog.setUserId(fundAccount.getUserId());
			fundAccountLogMapper.insert(fundAccountLog);
			
			double famount = DoubleUtil.addDouble(fundAccount.getFrozenAmount(), amount);
			fundAccount.setFrozenAmount(famount);
			
			wtFundAccountMapper.updateFrozenAmount(fundAccount);
		}else{
			throw new BalanceException(BalanceReturnCodeEnum.ACCOUNT_FROZEN,accountNo);
		}
	}
	
	@Transactional
	public void rechargeFrozen(String accountNo,double amount,BizType bizType,String orderNo){
		FundAccount fundAccount = fundAccountService.getFundAccount(accountNo);
		AccountStatus accountStatus = AccountStatus.getType(fundAccount.getAccountStatus());
		if(AccountStatus.NOMAL.equals(accountStatus)){ 
			Timestamp curtime = new Timestamp(System.currentTimeMillis());
			FundAccountLog fundAccountLog = new FundAccountLog();
			fundAccountLog.setAccountNo(accountNo);
			fundAccountLog.setAmountCg(fundAccount.getAmount()+amount);
			fundAccountLog.setChangeType(AccountChangeType.System.value);
			fundAccountLog.setAmountCur(fundAccount.getAmount());
			fundAccountLog.setChangAmount(amount);
			fundAccountLog.setCreateTime(curtime);
			fundAccountLog.setRemark1(bizType.name +amount);
			fundAccountLog.setBizType(bizType.value);
			fundAccountLog.setOpUserId(Sysuser.SYSUSER.id);
			fundAccountLog.setOpUserName(Sysuser.SYSUSER.name);
			fundAccountLog.setOrderNo(orderNo);
			fundAccountLog.setLogNo("C"+IDGenerotor.generateAccountLogNo());
			fundAccountLog.setBizTime(curtime);
			fundAccountLog.setBizStatus(BizStatus.SUCCESS.value);
			fundAccountLog.setUpdateTime(curtime);
			fundAccountLog.setUserId(fundAccount.getUserId());
			fundAccountLogMapper.insert(fundAccountLog);
			
			
			double famount = DoubleUtil.subtractionDouble(fundAccount.getFrozenAmount(), amount);
			if(famount<0){
				throw new BalanceException(BalanceReturnCodeEnum.ACCOUNT_MONEY_NOTE,accountNo);
			}
			fundAccount.setFrozenAmount(famount);
			wtFundAccountMapper.updateFrozenAmount(fundAccount);
		}else{
			throw new BalanceException(BalanceReturnCodeEnum.ACCOUNT_FROZEN,accountNo);
		}
	}
}
