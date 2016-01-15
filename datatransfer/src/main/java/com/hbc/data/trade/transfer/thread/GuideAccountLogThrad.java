package com.hbc.data.trade.transfer.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hbc.data.trade.transfer.service.account.AccountService;

public class GuideAccountLogThrad implements Runnable {

	private final static Logger logger = LoggerFactory.getLogger(GuideAccountLogThrad.class);

	private AccountService accountService;

	public GuideAccountLogThrad(AccountService accountService) {
		this.accountService = accountService;
	}

	public void run() {
		logger.info("--------------GuideAccountLogThrad start--------------");

		try {
			accountService.guideAccountLogTransfer();
		} catch (Exception e) {
			logger.error(e.toString());
		}

		logger.info("--------------GuideAccountLogThrad end--------------");
	}
}
