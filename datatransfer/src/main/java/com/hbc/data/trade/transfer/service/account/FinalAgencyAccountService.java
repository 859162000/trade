package com.hbc.data.trade.transfer.service.account;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hbc.data.trade.transfer.mapping.hbcbasedata.gen.FinalAgencyMapper;
import com.hbc.data.trade.transfer.mapping.hbcbasedata.gen.bean.FinalAgency;
import com.hbc.data.trade.transfer.mapping.hbcfund.gen.FinalAgencyAccountMapper;
import com.hbc.data.trade.transfer.mapping.hbcfund.gen.FinalAgencyAccountlogMapper;
import com.hbc.data.trade.transfer.mapping.hbcfund.gen.bean.FinalAgencyAccount;
import com.hbc.data.trade.transfer.mapping.hbcfund.gen.bean.FinalAgencyAccountCriteria;
import com.hbc.data.trade.transfer.mapping.hbcfund.gen.bean.FinalAgencyAccountlog;
import com.hbc.data.trade.transfer.mapping.hbcfund.gen.bean.FinalAgencyAccountlogCriteria;
import com.hbc.data.trade.transfer.util.Page;

@Component
public class FinalAgencyAccountService {

	@Autowired
	private FinalAgencyMapper finalAgencyMapper;

	@Autowired
	private FinalAgencyAccountMapper finalAgencyAccountMapper;

	@Autowired
	private FinalAgencyAccountlogMapper finalAgencyAccountlogMapper;

	private final int limit = 100;

	public FinalAgency getByIAgencyId(int agencyId) {
		return finalAgencyMapper.selectByPrimaryKey(agencyId);
	}

	public List<FinalAgencyAccount> pageQueryByAgencyId(int startAgencyId) {
		FinalAgencyAccountCriteria example = new FinalAgencyAccountCriteria();
		FinalAgencyAccountCriteria.Criteria criteria = example.createCriteria();
		criteria.andAgencyidGreaterThan(startAgencyId);
		Page page = new Page(0, limit);
		example.setPage(page);

		return finalAgencyAccountMapper.selectByExample(example);
	}

	public List<FinalAgencyAccountlog> pageQueryByAgencyLogId(String startAgencyLogId) {
		FinalAgencyAccountlogCriteria example = new FinalAgencyAccountlogCriteria();
		FinalAgencyAccountlogCriteria.Criteria criteria = example.createCriteria();
		criteria.andAgencyaccountlogidGreaterThan(startAgencyLogId);

		Page page = new Page(0, limit);
		example.setPage(page);

		return finalAgencyAccountlogMapper.selectByExample(example);
	}
}
