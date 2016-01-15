/**
 * @Author lukangle
 * @2015年12月25日@上午6:18:33
 */
package com.hbc.data.trade.transfer.service;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.hbc.api.fund.account.enums.AccountStatus;
import com.hbc.api.fund.account.enums.AccountType;
import com.hbc.api.fund.account.mapping.gen.FundAccountMapper;
import com.hbc.api.fund.account.mapping.gen.bean.FundAccount;
import com.hbc.api.fund.account.service.FundAccountService;
import com.hbc.api.trade.bdata.mapper.guide.gen.GuideBeanMapper;
import com.hbc.api.trade.bdata.mapper.guide.gen.bean.GuideBean;
import com.hbc.api.trade.bdata.mapper.guide.gen.bean.GuideBeanExample;

@Component
public class FoundAccountInsert {
	@Autowired
	FundAccountService fundAccountService;
	Logger log = Logger.getLogger(FoundAccountInsert.class);
	@Autowired
	FundAccountMapper fundAccountMapper;
	public void extAcc(String guideId,String accountNO){
		FundAccount fundAccount = fundAccountMapper.selectByPrimaryKey(accountNO);
		
		if(fundAccount==null){
			fundAccount = new FundAccount();
			fundAccount.setAccountNo(accountNO);
			fundAccount.setUserId(guideId);
			fundAccount.setAccountStatus(AccountStatus.NOMAL.value);
			fundAccount.setAccountType(AccountType.G_ACCOUNT.value);
			fundAccount.setCreateTime(new Date());
			fundAccountMapper.insert(fundAccount);
			log.info("插入 资金账户【"+JSON.toJSONString(fundAccount)+"】成功");
		}
	}
	@Autowired
	GuideBeanMapper guideBeanMapper;
	public void start(){
		List<GuideBean> glist = guideBeanMapper.selectByExample(new GuideBeanExample());
		for(GuideBean guideBean:glist){
			String aid = guideBean.getFundAccountId();
			extAcc(guideBean.getGuideId(),aid);
		}
	}
}
