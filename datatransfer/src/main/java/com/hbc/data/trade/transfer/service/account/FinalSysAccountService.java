package com.hbc.data.trade.transfer.service.account;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hbc.data.trade.transfer.mapping.hbcfund.gen.FinalSysAccountLogMapper;
import com.hbc.data.trade.transfer.mapping.hbcfund.gen.FinalSysAccountMapper;
import com.hbc.data.trade.transfer.mapping.hbcfund.gen.bean.FinalSysAccount;
import com.hbc.data.trade.transfer.mapping.hbcfund.gen.bean.FinalSysAccountLog;
import com.hbc.data.trade.transfer.mapping.hbcfund.gen.bean.FinalSysAccountLogCriteria;
import com.hbc.data.trade.transfer.util.Page;

@Service
public class FinalSysAccountService {

	@Autowired
	private FinalSysAccountMapper finalSysAccountMapper;

	@Autowired
	private FinalSysAccountLogMapper finalSysAccountLogMapper;

	private final int limit = 100;

	public FinalSysAccount getFinalSysAccount(int sysAccountId) {
		return finalSysAccountMapper.selectByPrimaryKey(sysAccountId);
	}

	public List<FinalSysAccountLog> pageQueryBySysAccountLogId(String sysAccountLogId) {
		FinalSysAccountLogCriteria example = new FinalSysAccountLogCriteria();
		FinalSysAccountLogCriteria.Criteria criteria = example.createCriteria();

		criteria.andSysaccountlogidGreaterThan(sysAccountLogId);

		Page page = new Page(0, limit);
		example.setPage(page);

		example.setOrderByClause("sysaccountlogid asc");

		return finalSysAccountLogMapper.selectByExample(example);
	}

	public int countSysAccountLog() {
		FinalSysAccountLogCriteria example = new FinalSysAccountLogCriteria();

		return finalSysAccountLogMapper.countByExample(example);
	}

	public int countSysAccountLogByType(int type) {
		FinalSysAccountLogCriteria example = new FinalSysAccountLogCriteria();
		FinalSysAccountLogCriteria.Criteria criteria = example.createCriteria();

		criteria.andBiztypeEqualTo(type);

		return finalSysAccountLogMapper.countByExample(example);
	}

}
