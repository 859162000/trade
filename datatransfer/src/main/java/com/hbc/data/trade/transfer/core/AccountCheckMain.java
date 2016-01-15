package com.hbc.data.trade.transfer.core;

import java.io.File;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hbc.data.trade.transfer.service.account.AccountCheckService;
import com.hbc.data.trade.transfer.service.account.BankCardCheckService;
import com.hbc.data.trade.transfer.service.account.DrawCheckService;
import com.hbc.data.trade.transfer.service.hfinal.FOrderService;
import com.hbc.data.trade.transfer.util.ConfigLoader;
import com.hbc.data.trade.transfer.util.LogBackInit;

public class AccountCheckMain {
	private final static Logger logger = LoggerFactory.getLogger(AccountCheckMain.class);

	public static void main(String[] args) throws InterruptedException {
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
		AccountCheckService accountCheckService = applicationContext.getBean(AccountCheckService.class);

		BankCardCheckService bankCardCheckService = applicationContext.getBean(BankCardCheckService.class);

		DrawCheckService drawCheckService = applicationContext.getBean(DrawCheckService.class);

		Date start = new Date();

		accountCheckService.checkAccount();

		logger.info("");
		logger.info("..............................");
		logger.info("");

		accountCheckService.checkAccountLog();

		logger.info("");
		logger.info("..............................");
		logger.info("");

		accountCheckService.checkAgentLogAccount();

		logger.info("");
		logger.info("..............................");
		logger.info("");
		accountCheckService.checkGuideLogAccount();

		logger.info("");
		logger.info("..............................");
		logger.info("");

		bankCardCheckService.checkCount();

		logger.info("");
		logger.info("..............................");
		logger.info("");

		drawCheckService.checkWithdrawal();

		Date end = new Date();
		logger.info(" acount data check cost: " + (end.getTime() - start.getTime()));
	}
}
