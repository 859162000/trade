/**
 * @Author lukangle
 * @2015年11月18日@下午1:52:48
 */
package com.hbc.api.fund.account.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hbc.api.fund.account.mapping.gen.FundAccountLogMapper;
import com.hbc.api.fund.account.mapping.gen.bean.FundAccount;
import com.hbc.api.fund.account.mapping.gen.bean.FundAccountLog;
import com.hbc.api.fund.account.mapping.gen.bean.FundAccountLogExample;
import com.hbc.api.fund.account.mapping.genx.WtFundAccountLogMapper;
import com.hbc.api.fund.account.parm.FundAccountLogParam;
import com.hbc.api.fund.account.parm.QAccountLogParam;
import com.hbc.api.trade.bdata.common.Page;

@Component
public class FundAccountLogService {

	@Autowired
	private FundAccountLogMapper fundAccountLogMapper;

	@Autowired
	private WtFundAccountLogMapper wtFundAccountLogMapper;

	@Autowired
	private FundAccountService fundAccountService;

	public List<FundAccountLog> getAccountLogs(FundAccountLogParam param) {
		FundAccountLogExample fundAccountLogExample = new FundAccountLogExample();
		Page page = new Page(param.getOffset(), param.getLimit());
		fundAccountLogExample.setPage(page);
		FundAccountLogExample.Criteria criteria = fundAccountLogExample.createCriteria();
		criteria.andAccountNoEqualTo(param.getAccountNo());
		criteria.andBizStatusGreaterThan(Integer.valueOf(0));
		fundAccountLogExample.setOrderByClause(param.getIsTradeTimeDown() ? "create_time desc" : "create_time asc");
		return fundAccountLogMapper.selectByExample(fundAccountLogExample);
	}

	public int countAccountLogs(FundAccountLogParam param) {
		FundAccountLogExample fundAccountLogExample = new FundAccountLogExample();
		Page page = new Page(param.getOffset(), param.getLimit());
		fundAccountLogExample.setPage(page);
		FundAccountLogExample.Criteria criteria = fundAccountLogExample.createCriteria();
		criteria.andBizStatusGreaterThan(Integer.valueOf(0));
		criteria.andAccountNoEqualTo(param.getAccountNo());
		return fundAccountLogMapper.countByExample(fundAccountLogExample);
	}

	public int countAccountLogs() {
		FundAccountLogExample fundAccountLogExample = new FundAccountLogExample();
		FundAccountLogExample.Criteria criteria = fundAccountLogExample.createCriteria();
		criteria.andLogNoIsNotNull();
		criteria.andBizStatusGreaterThan(Integer.valueOf(0));
		return fundAccountLogMapper.countByExample(fundAccountLogExample);
	}

	public int countAccountAllLogs(String accountNo) {
		FundAccountLogExample fundAccountLogExample = new FundAccountLogExample();
		FundAccountLogExample.Criteria criteria = fundAccountLogExample.createCriteria();
		criteria.andLogNoIsNotNull();
		criteria.andBizStatusGreaterThan(Integer.valueOf(0));
		return fundAccountLogMapper.countByExample(fundAccountLogExample);
	}

	public List<FundAccountLog> getAccountAllLogs(QAccountLogParam accountLogParam) {
		return wtFundAccountLogMapper.selectOrderLogs(accountLogParam);
	}

	public int getAccountAllLogsTotalSize(QAccountLogParam accountLogParam) {
		return wtFundAccountLogMapper.selectOrderLogsTotalSize(accountLogParam);
	}

	public FundAccount getPayAccountInfo(FundAccountLogParam param) {
		return fundAccountService.getFundAccount(param.getAccountNo());
	}

	public List<FundAccountLog> getAccountAllLogsByType(String accountNo, List<Integer> list, Integer limit, Integer offset) {
		FundAccountLogExample fundAccountLogExample = new FundAccountLogExample();
		FundAccountLogExample.Criteria criteria = fundAccountLogExample.createCriteria();
		criteria.andAccountNoEqualTo(accountNo);
		criteria.andLogNoIsNotNull();
		criteria.andBizStatusGreaterThan(Integer.valueOf(0));
		criteria.andBizTypeIn(list);
		Page page = new Page(offset, limit);
		fundAccountLogExample.setPage(page);
		fundAccountLogExample.setOrderByClause("create_time desc");
		return fundAccountLogMapper.selectByExample(fundAccountLogExample);
	}

	public Integer getAccountAllLogsSizeByType(String accountNo, List<Integer> list) {
		FundAccountLogExample fundAccountLogExample = new FundAccountLogExample();
		FundAccountLogExample.Criteria criteria = fundAccountLogExample.createCriteria();
		criteria.andAccountNoEqualTo(accountNo);
		criteria.andLogNoIsNotNull();
		criteria.andBizStatusGreaterThan(Integer.valueOf(0));
		criteria.andBizTypeIn(list);
		return fundAccountLogMapper.countByExample(fundAccountLogExample);
	}
}
