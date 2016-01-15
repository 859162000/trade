package com.hbc.data.trade.transfer.service.account;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hbc.data.trade.transfer.mapping.hbcbasedata.gen.FinalAgentMapper;
import com.hbc.data.trade.transfer.mapping.hbcbasedata.gen.bean.FinalAgent;
import com.hbc.data.trade.transfer.mapping.hbcfund.gen.FinalAgentAccountMapper;
import com.hbc.data.trade.transfer.mapping.hbcfund.gen.FinalAgentaccountLogMapper;
import com.hbc.data.trade.transfer.mapping.hbcfund.gen.bean.FinalAgentAccount;
import com.hbc.data.trade.transfer.mapping.hbcfund.gen.bean.FinalAgentAccountCriteria;
import com.hbc.data.trade.transfer.mapping.hbcfund.gen.bean.FinalAgentaccountLog;
import com.hbc.data.trade.transfer.mapping.hbcfund.gen.bean.FinalAgentaccountLogCriteria;
import com.hbc.data.trade.transfer.util.Page;

@Component
public class FinalAgentAccountService {

	@Autowired
	private FinalAgentMapper finalAgentMapper;

	@Autowired
	private FinalAgentAccountMapper finalAgentAccountMapper;

	@Autowired
	private FinalAgentaccountLogMapper finalAgentaccountLogMapper;

	private final int limit = 100;

	public FinalAgent getFinalAgent(int agentId) {
		return finalAgentMapper.selectByPrimaryKey(agentId);
	}

	public List<FinalAgentAccount> pageQueryByAgentId(int startAgentId) {
		FinalAgentAccountCriteria example = new FinalAgentAccountCriteria();
		FinalAgentAccountCriteria.Criteria criteria = example.createCriteria();

		criteria.andAgentidGreaterThan(startAgentId);
		Page page = new Page(0, limit);
		example.setPage(page);

		example.setOrderByClause("agentid asc");

		return finalAgentAccountMapper.selectByExample(example);
	}

	public List<FinalAgentaccountLog> pageQueryByAgentLogId(String startAgentLogId) {
		FinalAgentaccountLogCriteria example = new FinalAgentaccountLogCriteria();
		FinalAgentaccountLogCriteria.Criteria criteria = example.createCriteria();

		criteria.andAgentaccountlogidGreaterThan(startAgentLogId);

		Page page = new Page(0, limit);
		example.setPage(page);

		example.setOrderByClause("agentAccountLogId asc");

		return finalAgentaccountLogMapper.selectByExample(example);
	}

	public List<FinalAgentaccountLog> findAgentAccountLogs(int agentId) {
		FinalAgentaccountLogCriteria example = new FinalAgentaccountLogCriteria();
		FinalAgentaccountLogCriteria.Criteria criteria = example.createCriteria();
		criteria.andAgentidEqualTo(agentId);

		example.setOrderByClause("created_at asc");

		return finalAgentaccountLogMapper.selectByExample(example);
	}

	public FinalAgentaccountLog getAgentAccountLog(int agentId, String orderId, String comment) {
		FinalAgentaccountLogCriteria example = new FinalAgentaccountLogCriteria();
		FinalAgentaccountLogCriteria.Criteria criteria = example.createCriteria();
		criteria.andAgentidEqualTo(agentId);
		criteria.andOrderidEqualTo(orderId);
		criteria.andCommentEqualTo(comment);

		example.setOrderByClause("created_at asc");

		List<FinalAgentaccountLog> finalAgentaccountLogs = finalAgentaccountLogMapper.selectByExample(example);
		if (finalAgentaccountLogs.size() > 0) {
			return finalAgentaccountLogs.get(0);
		}
		return null;
	}

	public int countAgentAccount() {
		FinalAgentAccountCriteria example = new FinalAgentAccountCriteria();
		return finalAgentAccountMapper.countByExample(example);
	}

	public int countAgentAccountByAmountAndTotalAmount(int amount, int totalAmount) {
		FinalAgentAccountCriteria example = new FinalAgentAccountCriteria();

		FinalAgentAccountCriteria.Criteria criteria = example.createCriteria();
		criteria.andCurrentpriceEqualTo(amount);
		criteria.andTotalEqualTo(totalAmount);

		return finalAgentAccountMapper.countByExample(example);
	}

	public int countAgentAccountLog() {
		FinalAgentaccountLogCriteria example = new FinalAgentaccountLogCriteria();
		return finalAgentaccountLogMapper.countByExample(example);
	}

	public int countAgentAccountLogByStatus(int status) {
		FinalAgentaccountLogCriteria example = new FinalAgentaccountLogCriteria();
		FinalAgentaccountLogCriteria.Criteria criteria = example.createCriteria();
		criteria.andBizstatusEqualTo(status);

		return finalAgentaccountLogMapper.countByExample(example);
	}

	public int countAgentAccountLogByAccountNo(int agentId) {
		FinalAgentaccountLogCriteria example = new FinalAgentaccountLogCriteria();
		FinalAgentaccountLogCriteria.Criteria criteria = example.createCriteria();
		criteria.andAgentidEqualTo(agentId);

		return finalAgentaccountLogMapper.countByExample(example);
	}

	public int countAgentAccountLogByType(int type) {
		FinalAgentaccountLogCriteria example = new FinalAgentaccountLogCriteria();
		FinalAgentaccountLogCriteria.Criteria criteria = example.createCriteria();
		criteria.andBiztypeEqualTo(type);

		return finalAgentaccountLogMapper.countByExample(example);
	}
}
