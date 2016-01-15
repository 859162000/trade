package com.hbc.data.trade.transfer.service.account;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.hbc.data.trade.transfer.BaseTest;

public class AccountCheckServiceTest extends BaseTest {

	@Autowired
	private AccountCheckService accountCheckService;

	@Autowired
	private DrawCheckService drawCheckService;

	@Autowired
	private BankCardCheckService bankCardCheckService;

	@Test
	public void checkAccountTest() {
		accountCheckService.checkAccount();
	}

	@Test
	public void checkAccountLogTest() {
		accountCheckService.checkAccountLog();
	}

	@Test
	public void checkWithdrawalTest() {
		drawCheckService.checkWithdrawal();
	}

	@Test
	public void bankCardCheckServiceTest() {
		bankCardCheckService.checkCount();
	}

	@Test
	public void checkAgentAccountTest() {
		accountCheckService.checkAgentAccount();
	}

	@Test
	public void checkGuideAccountTest() {
		accountCheckService.checkGuideAccount();
	}

	@Test
	public void checkAgentLogAccountTest() {
		accountCheckService.checkAgentLogAccount();
	}

	@Test
	public void checkAccountLogCountByGuideIdTest() {
		accountCheckService.checkGuideLogAccount();
	}
}
