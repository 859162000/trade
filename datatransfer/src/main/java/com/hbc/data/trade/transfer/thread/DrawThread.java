package com.hbc.data.trade.transfer.thread;

import com.hbc.data.trade.transfer.service.account.AccountService;

public class DrawThread implements Runnable {

	private AccountService accountService;

	public DrawThread(AccountService accountService) {
		this.accountService = accountService;
	}

	@Override
	public void run() {
		accountService.guideDrawTransfer();
	}

}
