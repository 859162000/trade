package com.hbc.data.trade.transfer.service.account;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.hbc.data.trade.transfer.BaseTest;

public class AccountServiceTest extends BaseTest {

	@Autowired
	private AccountService accountService;

	@Test
	public void agentAccountTrarnsferTest() {
		accountService.agentAccountTransfer();
	}

	@Test
	public void guideAccountTransferTest() {
		accountService.guideAccountTransfer();
	}

	@Test
	public void sysAccountTransferTest() {
		accountService.sysAccountTransfer();
	}

	@Test
	public void agentAccountLogTransferTest() throws Exception {
		accountService.agentAccountLogTransfer();
	}

	@Test
	public void guideAccountLogTransferTest() throws Exception {
		accountService.guideAccountLogTransfer();
	}

	@Test
	public void sysAccountLogTransferTest() throws Exception {
		accountService.sysAccountLogTransfer();
	}

	@Test
	public void guideDrawTransferTest() {
		accountService.guideDrawTransfer();
	}

	@Test
	public void bankCardTransferTest() {
		accountService.bankCardTransfer();
	}

}
