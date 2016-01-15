package com.hbc.data.trade.transfer.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hbc.data.trade.transfer.service.account.AccountService;

public class AgentAccountLogThrad implements Runnable {

	private final static Logger logger = LoggerFactory.getLogger(AgentAccountLogThrad.class);

	private AccountService accountService;

	public AgentAccountLogThrad(AccountService accountService) {
		this.accountService = accountService;
	}

	public void run() {

		logger.info("--------------AgentAccountLogThrad start--------------");

		try {
			accountService.agentAccountLogTransfer();
		} catch (Exception e) {
			logger.error(e.toString());
		}

		logger.info("--------------AgentAccountLogThrad end--------------");
	}
}
