package com.hbc.data.trade.transfer.service.account;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hbc.data.trade.transfer.mapping.hbcbasedata.gen.FinalGuideMapper;
import com.hbc.data.trade.transfer.mapping.hbcbasedata.gen.bean.FinalGuide;
import com.hbc.data.trade.transfer.mapping.hbcfinal.gen.FinalGuideaccountdetailMapper;
import com.hbc.data.trade.transfer.mapping.hbcfinal.gen.bean.FinalGuideaccountdetail;
import com.hbc.data.trade.transfer.mapping.hbcfinal.gen.bean.FinalGuideaccountdetailCriteria;
import com.hbc.data.trade.transfer.mapping.hbcfund.gen.FinalGuideAccountMapper;
import com.hbc.data.trade.transfer.mapping.hbcfund.gen.bean.FinalGuideAccount;
import com.hbc.data.trade.transfer.mapping.hbcfund.gen.bean.FinalGuideAccountCriteria;
import com.hbc.data.trade.transfer.util.Page;

@Component
public class FinalGuideAccountService {

	@Autowired
	private FinalGuideMapper finalGuideMapper;
	@Autowired
	private FinalGuideAccountMapper finalGuideAccountMapper;
	@Autowired
	private FinalGuideaccountdetailMapper finalGuideaccountdetailMapper;

	private final int limit = 100;

	public FinalGuide getFinalGuide(int guideId) {
		return finalGuideMapper.selectByPrimaryKey(guideId);
	}

	public List<FinalGuideAccount> pageQueryByGuideId(int startGuideId) {
		FinalGuideAccountCriteria example = new FinalGuideAccountCriteria();
		FinalGuideAccountCriteria.Criteria criteria = example.createCriteria();

		criteria.andGuideidGreaterThan(startGuideId);

		Page page = new Page(0, limit);
		example.setPage(page);

		example.setOrderByClause("guideid asc");

		return finalGuideAccountMapper.selectByExample(example);
	}

	public List<FinalGuideaccountdetail> pageQueryByGuideLogId(String startGuideLogId) {
		FinalGuideaccountdetailCriteria example = new FinalGuideaccountdetailCriteria();
		FinalGuideaccountdetailCriteria.Criteria criteria = example.createCriteria();

		criteria.andGuideaccountdetailidGreaterThan(startGuideLogId);

		Page page = new Page(0, limit);
		example.setPage(page);

		example.setOrderByClause("guideAccountDetailId asc");

		return finalGuideaccountdetailMapper.selectByExample(example);
	}

	public List<FinalGuideaccountdetail> findGuideLogs(int guideId) {
		FinalGuideaccountdetailCriteria example = new FinalGuideaccountdetailCriteria();
		FinalGuideaccountdetailCriteria.Criteria criteria = example.createCriteria();

		criteria.andGuideidEqualTo(guideId);

		example.setOrderByClause("created_at asc");

		return finalGuideaccountdetailMapper.selectByExample(example);
	}

	public int countGuideAccount() {
		FinalGuideAccountCriteria example = new FinalGuideAccountCriteria();

		return finalGuideAccountMapper.countByExample(example);
	}

	public int countGuideAccountByAmountAndTotalAmount(int amount, int totalAmount) {
		FinalGuideAccountCriteria example = new FinalGuideAccountCriteria();
		FinalGuideAccountCriteria.Criteria criteria = example.createCriteria();

		criteria.andActivepriceEqualTo(amount);
		criteria.andTotalEqualTo(totalAmount);

		return finalGuideAccountMapper.countByExample(example);
	}

	public int countGuideAccountLog() {
		FinalGuideaccountdetailCriteria example = new FinalGuideaccountdetailCriteria();

		return finalGuideaccountdetailMapper.countByExample(example);
	}

	public int countGuideAccountLogByStatus(int status) {
		FinalGuideaccountdetailCriteria example = new FinalGuideaccountdetailCriteria();
		FinalGuideaccountdetailCriteria.Criteria criteria = example.createCriteria();
		criteria.andBizstatusEqualTo(status);

		return finalGuideaccountdetailMapper.countByExample(example);
	}

	public int countGuideAccountLogByGuideId(int guideId) {
		FinalGuideaccountdetailCriteria example = new FinalGuideaccountdetailCriteria();
		FinalGuideaccountdetailCriteria.Criteria criteria = example.createCriteria();
		criteria.andGuideidEqualTo(guideId);

		return finalGuideaccountdetailMapper.countByExample(example);
	}

	public int countGuideAccountLogByType(int type) {
		FinalGuideaccountdetailCriteria example = new FinalGuideaccountdetailCriteria();
		FinalGuideaccountdetailCriteria.Criteria criteria = example.createCriteria();
		criteria.andBiztypeEqualTo(type);

		return finalGuideaccountdetailMapper.countByExample(example);
	}

}
