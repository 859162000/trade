package com.hbc.data.trade.transfer.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hbc.data.trade.transfer.service.account.AccountService;

public class AccountThread implements Runnable {

	private final static Logger logger = LoggerFactory.getLogger(AccountThread.class);

	private AccountService accountService;

	public AccountThread(AccountService accountService) {
		this.accountService = accountService;
	}

	@Override
	public void run() {

		logger.info("--------------AccountThread start--------------");

		accountService.agentAccountTransfer();

		accountService.guideAccountTransfer();

		accountService.sysAccountTransfer();

		logger.info("--------------AccountThread end--------------");
	}
}
