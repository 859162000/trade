package com.hbc.data.trade.transfer.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hbc.data.trade.transfer.service.account.AccountService;

public class BankCardThread implements Runnable {

	private final static Logger logger = LoggerFactory.getLogger(BankCardThread.class);

	private AccountService accountService;

	public BankCardThread(AccountService accountService) {
		this.accountService = accountService;
	}

	@Override
	public void run() {

		logger.info(".........................BankCardThread start ......................");

		accountService.bankCardTransfer();

		logger.info(".........................BankCardThread end ......................");
	}
}
