package com.hbc.data.trade.transfer.core;

import java.io.File;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hbc.data.trade.transfer.service.account.AccountService;
import com.hbc.data.trade.transfer.service.hfinal.FOrderService;
import com.hbc.data.trade.transfer.thread.AccountThread;
import com.hbc.data.trade.transfer.thread.AgentAccountLogThrad;
import com.hbc.data.trade.transfer.thread.BankCardThread;
import com.hbc.data.trade.transfer.thread.DrawThread;
import com.hbc.data.trade.transfer.thread.GuideAccountLogThrad;
import com.hbc.data.trade.transfer.thread.SystemAccountLogThread;
import com.hbc.data.trade.transfer.util.ConfigLoader;
import com.hbc.data.trade.transfer.util.LogBackInit;

public class AccountMain {

	private final static Logger logger = LoggerFactory.getLogger(AccountMain.class);

	public static void main(String[] args) {
		String baseConfigDir = "";
		if (args != null && args.length > 0) {
			baseConfigDir = args[0];
		} else {
			baseConfigDir = Class.class.getClass().getResource("/").getPath();
		}

		LogBackInit.initLogBack(baseConfigDir + File.separator + "/logback.xml");

		Logger log = LoggerFactory.getLogger(FOrderService.class);
		log.info("baseConfigDir:" + baseConfigDir);
		ConfigLoader.loadProperties(baseConfigDir);

		ExecutorService executor = Executors.newFixedThreadPool(ConfigLoader.getInt("move.rnum", 10));
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(
				"classpath:conf/transfer.xml", "classpath:conf/tradedatasource.xml");
		AccountService accountService = applicationContext.getBean(AccountService.class);

		Date start = new Date();

		AccountThread accountThread = new AccountThread(accountService);
		executor.execute(accountThread);

		AgentAccountLogThrad agentAccountLogThrad = new AgentAccountLogThrad(accountService);
		executor.execute(agentAccountLogThrad);

		// accountService.agentAccountLogTransfer();

		GuideAccountLogThrad guideAccountLogThrad = new GuideAccountLogThrad(accountService);
		executor.execute(guideAccountLogThrad);
		// accountService.guideAccountLogTransfer();

		SystemAccountLogThread systemAccountLogThread = new SystemAccountLogThread(accountService);
		executor.execute(systemAccountLogThread);
		// accountService.sysAccountLogTransfer();

		DrawThread drawThread = new DrawThread(accountService);
		executor.execute(drawThread);

		BankCardThread bankCardThread = new BankCardThread(accountService);
		executor.execute(bankCardThread);

		Date end = new Date();
		logger.info(" acount data transfer cost: " + (end.getTime() - start.getTime()));
	}
}
