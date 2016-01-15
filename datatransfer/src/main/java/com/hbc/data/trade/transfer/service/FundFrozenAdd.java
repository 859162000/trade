/**
 * @Author lukangle
 * @2015年12月25日@下午2:55:52
 */
package com.hbc.data.trade.transfer.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.hbc.api.fund.account.enums.AccountChangeType;
import com.hbc.api.fund.account.enums.BalanceReturnCodeEnum;
import com.hbc.api.fund.account.enums.BizStatus;
import com.hbc.api.fund.account.enums.BizType;
import com.hbc.api.fund.account.exceptions.BalanceException;
import com.hbc.api.fund.account.mapping.gen.FundAccountLogMapper;
import com.hbc.api.fund.account.mapping.gen.FundAccountMapper;
import com.hbc.api.fund.account.mapping.gen.bean.FundAccount;
import com.hbc.api.fund.account.mapping.gen.bean.FundAccountLog;
import com.hbc.api.fund.account.service.FundAccountService;
import com.hbc.api.fund.biz.mapping.gen.FundWithdrawalMapper;
import com.hbc.api.fund.biz.mapping.gen.bean.FundWithdrawal;
import com.hbc.api.fund.biz.mapping.gen.bean.FundWithdrawalExample;
import com.hbc.api.trade.bdata.common.IDGenerotor;
import com.hbc.api.trade.bdata.common.enums.Sysuser;
import com.hbc.api.trade.bdata.common.util.DoubleUtil;
import com.hbc.api.trade.bdata.common.util.TimeConverter;

@Component
public class FundFrozenAdd {
	@Autowired
	FundWithdrawalMapper fundWithdrawalMapper;
	@Autowired
	FundAccountService fundAccountService;
	@Autowired
	FundAccountMapper fundAccountMapper;
	@Autowired
	FundAccountLogMapper fundAccountLogMapper;
	Logger log = Logger.getLogger(FundFrozenAdd.class);
	@Transactional
	public void start(){
		FundWithdrawalExample fundWithdrawalExample = new FundWithdrawalExample();
		List list = new ArrayList<>();
		list.add(2);
		Date date = TimeConverter.toDate("2015-12-25", TimeConverter.END_WITH_DATE);
		fundWithdrawalExample.createCriteria().andDrawStatusIn(list).andCreateTimeGreaterThan(date);
		List<FundWithdrawal> wlist = fundWithdrawalMapper.selectByExample(fundWithdrawalExample);
		
		Set<String> glist = new HashSet<String>();
		for(FundWithdrawal fundWithdrawal:wlist){
			glist.add(fundWithdrawal.getGuideId());
		}
		
		Map<String,List<FundWithdrawal>> lmap = new HashMap<>();
		for(String guideId:glist){
			List<FundWithdrawal> flist = lmap.get(guideId);
			if(flist==null){
				flist = new ArrayList<>();
			}
			for(FundWithdrawal fundWithdrawal:wlist){
				if(fundWithdrawal.getGuideId().equals(guideId)){
					flist.add(fundWithdrawal);
				}
			}
			lmap.put(guideId, flist);
		}
		
		for(String guideId:glist){
			List<FundWithdrawal> flist = lmap.get(guideId);
			Double frozenAmout = 0.0d;
			String fundAccountNo =   null;
			for(FundWithdrawal fundWithdrawal:flist){
				frozenAmout = DoubleUtil.addDouble(frozenAmout, fundWithdrawal.getPrice());
				if(fundAccountNo!=null && !fundAccountNo.equals(fundWithdrawal.getFinAccount())){
					System.out.println(guideId+" 上一笔【"+fundAccountNo+"】 这一笔 【】  红冲错误  "+JSON.toJSONString(fundWithdrawal));
					return ;
				}
				fundAccountNo = fundWithdrawal.getFinAccount();
			}
			
			try{
				
				System.out.println(fundAccountNo+"@@@");
				FundAccount fundAccount = fundAccountService.getFundAccount(fundAccountNo);
				
				if(fundAccount.getFrozenAmount()==null || fundAccount.getFrozenAmount()>=frozenAmout){
					System.out.println("红冲开始 ："+fundAccountNo+" 充值 【"+frozenAmout+"】金额将由【"+fundAccount.getAmount()+"】变成【"+DoubleUtil.addDouble(frozenAmout, fundAccount.getAmount())+"】 冻结金额 将由【"+fundAccount.getFrozenAmount()+"】 变成【"+DoubleUtil.subtractionDouble(fundAccount.getFrozenAmount(), frozenAmout)+"】");
					FundAccount record = new FundAccount();
					record.setAccountNo(fundAccount.getAccountNo());
					record.setFrozenAmount(DoubleUtil.subtractionDouble(fundAccount.getFrozenAmount(), frozenAmout));
					record.setAmount(DoubleUtil.addDouble(frozenAmout, fundAccount.getAmount()));
					int optnum = fundAccountMapper.updateByPrimaryKeySelective(record);
					
					if(optnum!=1){
						throw new BalanceException(BalanceReturnCodeEnum.ACCOUNT_NOT_EXSIT, "资金");
					}
					Date curtime = new Date();
					FundAccountLog fundAccountLog = new FundAccountLog();
					fundAccountLog.setAccountNo(fundAccountNo);
					fundAccountLog.setAmountCg(frozenAmout);
					fundAccountLog.setChangeType(AccountChangeType.System.value);
					fundAccountLog.setAmountCur(fundAccount.getAmount());
					fundAccountLog.setChangAmount(frozenAmout);
					fundAccountLog.setCreateTime(curtime);
					fundAccountLog.setRemark1("冻结金额   红冲  " +frozenAmout);
					fundAccountLog.setBizType(BizType.BIZ_HONGCHONG.value);
					fundAccountLog.setOpUserId(Sysuser.SYSUSER.id);
					fundAccountLog.setOpUserName(Sysuser.SYSUSER.name);
					fundAccountLog.setBizTime(curtime);
					fundAccountLog.setLogNo("R" + IDGenerotor.generateAccountLogNo());
					fundAccountLog.setBizStatus(BizStatus.SUCCESS.value);
					fundAccountLog.setUpdateTime(curtime);
					fundAccountLog.setUserId(fundAccount.getUserId());
					fundAccountLog.setComment(BizType.BIZ_HONGCHONG.name);
					optnum = fundAccountLogMapper.insert(fundAccountLog);
					
					if(optnum!=1){
						throw new BalanceException(BalanceReturnCodeEnum.ACCOUNT_NOT_EXSIT, "资金");
					}
					System.out.println(fundAccountNo+"红冲成功"+frozenAmout);
				}else{
					System.out.println(fundAccountNo+"红冲掠过");
				}
			}catch(Exception e){
				log.error("", e);
			}
		}
		log.info("红冲列表:"+JSON.toJSONString(wlist));
	}
}
