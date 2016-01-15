package com.hbc.data.trade.transfer.service.account;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hbc.data.trade.transfer.mapping.hbcfund.gen.FinalGuidefinanceMapper;
import com.hbc.data.trade.transfer.mapping.hbcfund.gen.bean.FinalGuidefinance;
import com.hbc.data.trade.transfer.mapping.hbcfund.gen.bean.FinalGuidefinanceCriteria;
import com.hbc.data.trade.transfer.util.Page;

@Component
public class FinalGuidefinanceService {

	@Autowired
	private FinalGuidefinanceMapper finalGuidefinanceMapper;

	private final int limit = 100;

	public List<FinalGuidefinance> pageQueryGuidefinance(int startId) {
		FinalGuidefinanceCriteria example = new FinalGuidefinanceCriteria();
		FinalGuidefinanceCriteria.Criteria criteria = example.createCriteria();
		criteria.andGuidefinanceidGreaterThan(startId);

		Page page = new Page(0, limit);
		example.setPage(page);

		example.setOrderByClause("guidefinanceid asc");

		return finalGuidefinanceMapper.selectByExample(example);
	}
}
