package com.hbc.data.trade.transfer.service.account;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.hbc.api.fund.account.enums.AccountType;
import com.hbc.api.fund.account.enums.BizStatus;
import com.hbc.api.fund.account.enums.BizType;
import com.hbc.api.fund.account.mapping.gen.FundAccountLogMapper;
import com.hbc.api.fund.account.mapping.gen.FundAccountMapper;
import com.hbc.api.fund.account.mapping.gen.bean.FundAccountExample;
import com.hbc.api.fund.account.mapping.gen.bean.FundAccountLogExample;
import com.hbc.data.trade.transfer.mapping.hbcbasedata.gen.bean.FinalAgent;
import com.hbc.data.trade.transfer.mapping.hbcbasedata.gen.bean.FinalGuide;
import com.hbc.data.trade.transfer.mapping.hbcfinal.gen.bean.FinalGuideaccountdetail;
import com.hbc.data.trade.transfer.mapping.hbcfund.gen.bean.FinalAgentAccount;
import com.hbc.data.trade.transfer.mapping.hbcfund.gen.bean.FinalAgentaccountLog;
import com.hbc.data.trade.transfer.mapping.hbcfund.gen.bean.FinalGuideAccount;
import com.hbc.datamove.base.util.AccountIdUtil;

@Service
public class AccountCheckService {

	private final static Logger logger = LoggerFactory.getLogger(AccountCheckService.class);

	private int width = 5000;

	@Autowired
	private FundAccountMapper fundAccountMapper;
	@Autowired
	private FundAccountLogMapper fundAccountLogMapper;

	@Autowired
	private FinalAgentAccountService finalAgentAccountService;
	@Autowired
	private FinalGuideAccountService finalGuideAccountService;
	@Autowired
	private FinalSysAccountService finalSysAccountService;

	public void checkAccount() {
		checkTotalAccountCount();

		logger.info("");
		logger.info("");

		checkAccountCountByAmount(20000d, 20000d);

		logger.info("");
		logger.info("");

		checkAccountCountByAmount(1d, 1d);

		logger.info("");
		logger.info("");
	}

	public void checkAccountLog() {

		checkTotalAccountLogCount();

		logger.info("");
		logger.info("");

		checkAccountLogCountByStatus(BizStatus.SUCCESS.value);

		logger.info("");

		checkAccountLogCountByStatus(BizStatus.FAILED.value);

		logger.info("");
		logger.info("");

		checkAccountLogCountByType(BizType.ACCOUNT_OPERATION.value);

		logger.info("");

		checkAccountLogCountByType(BizType.BUCAHNG_PICKUP.value);

		logger.info("");

		checkAccountLogCountByType(BizType.CANCAL_COMMNENDATION.value);

		logger.info("");

		checkAccountLogCountByType(BizType.CANCAL_DAILY.value);

		logger.info("");

		checkAccountLogCountByType(BizType.CANCAL_PICKUP.value);

		logger.info("");

		checkAccountLogCountByType(BizType.CANCAL_TRANSFER.value);

		logger.info("");

		checkAccountLogCountByType(BizType.CANCLE_SINGLE.value);

		logger.info("");

		checkAccountLogCountByType(BizType.CHUFA.value);

		logger.info("");

		checkAccountLogCountByType(BizType.COMMENDATION.value);

		logger.info("");

		checkAccountLogCountByType(BizType.DAILY.value);

		logger.info("");
		logger.info("");

		checkAccountLogCountByType(BizType.GAIPAI_PICKUP.value);

		logger.info("");
		logger.info("");

		checkAccountLogCountByType(BizType.PAY.value);

		logger.info("");
		logger.info("");
	}

	public void checkAgentLogAccount() {

		logger.info("");
		logger.info("");

		checkAccountLogCountByAgentId(231);
		checkAccountLogCountByAgentId(2604);
		checkAccountLogCountByAgentId(2624);
		checkAccountLogCountByAgentId(100014542);
		checkAccountLogCountByAgentId(100015089);

		checkAccountLogCountByAgentId(2605);
		checkAccountLogCountByAgentId(100000055);
		checkAccountLogCountByAgentId(100003775);
		checkAccountLogCountByAgentId(100011624);
		checkAccountLogCountByAgentId(100014622);

		logger.info("");
		logger.info("");
	}

	public void checkGuideLogAccount() {
		logger.info("");
		logger.info("");

		checkAccountLogCountByGuideId(231);
		checkAccountLogCountByGuideId(2604);
		checkAccountLogCountByGuideId(2624);
		checkAccountLogCountByGuideId(100014542);
		checkAccountLogCountByGuideId(100015089);

		checkAccountLogCountByGuideId(2605);
		checkAccountLogCountByGuideId(100003775);
		checkAccountLogCountByGuideId(100011624);
		checkAccountLogCountByGuideId(100014586);
		checkAccountLogCountByGuideId(100014622);

		logger.info("");
		logger.info("");

	}

	public void checkAgentAccount() {

		List<InvalidAccount> invalidAccounts = new ArrayList<InvalidAccount>();

		List<InvalidAccount> invalidTotalAccounts = new ArrayList<InvalidAccount>();

		List<InvalidAccount> invalidActiceAccounts = new ArrayList<InvalidAccount>();

		int startIndex = 0;
		List<FinalAgentAccount> finalAgentAccounts = finalAgentAccountService.pageQueryByAgentId(startIndex);

		while (!CollectionUtils.isEmpty(finalAgentAccounts)) {
			logger.info("agentAccountTransfer startIndex: " + startIndex);

			for (FinalAgentAccount finalAgentAccount : finalAgentAccounts) {
				int agentId = finalAgentAccount.getAgentid();

				List<FinalAgentaccountLog> agentAccountLogs = finalAgentAccountService.findAgentAccountLogs(agentId);

				FinalAgent finalAgent = finalAgentAccountService.getFinalAgent(finalAgentAccount.getAgentid());

				int totalAccount = 0;
				int activeAccount = 0;
				for (FinalAgentaccountLog finalAgentaccountLog : agentAccountLogs) {
					int price = finalAgentaccountLog.getPrice();
					int bizType = finalAgentaccountLog.getBiztype();

					if (bizType == BizType.PAY.value || bizType == BizType.RT.value) {
						totalAccount += price;
					}

					String comment = finalAgentaccountLog.getComment();
					if (null != comment) {
						int index = comment.indexOf("支付平台支付");
						if (index >= 0) {
							continue;
						}
					}

					if (price > 0 && bizType != BizType.PAY.value && bizType != BizType.RT.value) {
						FinalAgentaccountLog temp = finalAgentAccountService.getAgentAccountLog(agentId,
								finalAgentaccountLog.getOrderid(),
								"支付平台支付");
						if (null != temp) {
							continue;
						}
					}

					activeAccount += price;
				}

				if (finalAgentAccount.getTotal() != totalAccount) {
					InvalidAccount invalidAccount = new InvalidAccount();

					invalidAccount.setId(agentId + "");
					invalidAccount.setType("agent 充值总额 (total value)");
					invalidAccount.setName(finalAgent.getAgentname());
					invalidAccount.setDbValue(finalAgentAccount.getTotal() + "");
					invalidAccount.setSumValue(totalAccount + "");

					invalidAccounts.add(invalidAccount);
					invalidTotalAccounts.add(invalidAccount);
				}

				if (finalAgentAccount.getCurrentprice() != activeAccount) {
					InvalidAccount invalidAccount = new InvalidAccount();

					invalidAccount.setId(agentId + "");
					invalidAccount.setType("agent 可用余额(current value)");
					invalidAccount.setName(finalAgent.getAgentname());
					invalidAccount.setDbValue(finalAgentAccount.getCurrentprice() + "");
					invalidAccount.setSumValue(activeAccount + "");

					invalidAccounts.add(invalidAccount);

					invalidActiceAccounts.add(invalidAccount);
				}
				startIndex = finalAgentAccount.getAgentid();
			}
			finalAgentAccounts = finalAgentAccountService.pageQueryByAgentId(startIndex);
		}

		System.out.println("----------------------------------------");
		System.out.println();

		HSSFWorkbook workBook = new HSSFWorkbook();

		Sheet guideTotalSheet = workBook.createSheet("代理商总收入");

		guideTotalSheet.setColumnWidth(0, width);
		guideTotalSheet.setColumnWidth(1, width);
		guideTotalSheet.setColumnWidth(2, width);
		guideTotalSheet.setColumnWidth(3, width);

		Row row = guideTotalSheet.createRow(0);

		Cell idCell = row.createCell(0);
		idCell.setCellValue("代理商ID");

		Cell nameCell = row.createCell(1);
		nameCell.setCellValue("名称");

		Cell totalCell = row.createCell(2);
		totalCell.setCellValue("账号代理商充值总值");

		Cell sumCell = row.createCell(3);
		sumCell.setCellValue("累加代理商充值总值");

		int index = 1;

		System.out.println("供应商充值错误的账号数量：" + invalidTotalAccounts.size());

		for (InvalidAccount invalidAccount : invalidTotalAccounts) {
			Row row2 = guideTotalSheet.createRow(index++);

			Cell idCell2 = row2.createCell(0);
			idCell2.setCellValue(invalidAccount.getId());

			Cell typeCell2 = row2.createCell(1);
			typeCell2.setCellValue(invalidAccount.getName());

			Cell totalCell2 = row2.createCell(2);
			totalCell2.setCellValue(invalidAccount.getDbValue());

			Cell sumCell2 = row2.createCell(3);
			sumCell2.setCellValue(invalidAccount.getSumValue());

			System.out.println(invalidAccount.toString());
		}

		System.out.println();
		System.out.println("----------------------------------------");

		Sheet guideActiceSheet = workBook.createSheet("代理商余额");

		guideActiceSheet.setColumnWidth(0, width);
		guideActiceSheet.setColumnWidth(1, width);
		guideActiceSheet.setColumnWidth(2, width);
		guideActiceSheet.setColumnWidth(3, width);

		Row activeRow = guideActiceSheet.createRow(0);

		Cell activeIdCell = activeRow.createCell(0);
		activeIdCell.setCellValue("导游ID");

		Cell activeNameCell = activeRow.createCell(1);
		activeNameCell.setCellValue("名称");

		Cell activeAccountCell = activeRow.createCell(2);
		activeAccountCell.setCellValue("账号余额");

		Cell sumActiveCell = activeRow.createCell(3);
		sumActiveCell.setCellValue("累加余额");

		index = 1;

		System.out.println("代理商余额错误的账号数量：" + invalidTotalAccounts.size());

		for (InvalidAccount invalidAccount : invalidActiceAccounts) {
			Row activeRow2 = guideActiceSheet.createRow(index++);

			Cell activeIdCell2 = activeRow2.createCell(0);
			activeIdCell2.setCellValue(invalidAccount.getId());

			Cell activeNameCell2 = activeRow2.createCell(1);
			activeNameCell2.setCellValue(invalidAccount.getName());

			Cell activeAccountCell2 = activeRow2.createCell(2);
			activeAccountCell2.setCellValue(invalidAccount.getDbValue());

			Cell sumActiveCell2 = activeRow2.createCell(3);
			sumActiveCell2.setCellValue(invalidAccount.getSumValue());

			System.out.println(invalidAccount.toString());
		}

		System.out.println();
		System.out.println("----------------------------------------");

		try {
			// 输出成XLS文件
			File file = new File("d:/AgentAccount.xls");

			if (file.exists()) {
				file.delete();
			}

			file.createNewFile();

			FileOutputStream fos = new FileOutputStream(file);

			// 写入数据，并关闭文件
			workBook.write(fos);
			fos.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void checkGuideAccount() {
		List<InvalidAccount> invalidAccounts = new ArrayList<InvalidAccount>();

		List<InvalidAccount> invalidTotalAccounts = new ArrayList<InvalidAccount>();

		List<InvalidAccount> invalidActiceAccounts = new ArrayList<InvalidAccount>();

		int startIndex = 0;
		int totalCount = 0;
		List<FinalGuideAccount> finalGuideAccounts = finalGuideAccountService.pageQueryByGuideId(startIndex);

		while (!CollectionUtils.isEmpty(finalGuideAccounts)) {

			logger.info("guideAccountTransfer startIndex: " + startIndex);

			for (FinalGuideAccount finalGuideAccount : finalGuideAccounts) {

				FinalGuide finalGuide = finalGuideAccountService.getFinalGuide(finalGuideAccount.getGuideid());
				if (null == finalGuide) {
					logger.warn("invalid guide id: " + finalGuideAccount.getGuideid());
					continue;
				}

				List<FinalGuideaccountdetail> finalGuideaccountdetails = finalGuideAccountService
						.findGuideLogs(finalGuideAccount.getGuideid());

				int totalAccount = 0;
				int activeAccount = 0;

				for (FinalGuideaccountdetail finalGuideaccountdetail : finalGuideaccountdetails) {
					int price = finalGuideaccountdetail.getPrice();
					if (price > 0) {
						totalAccount += price;
					}
					activeAccount += price;
				}

				if (finalGuideAccount.getTotal() != totalAccount) {
					InvalidAccount invalidAccount = new InvalidAccount();

					invalidAccount.setId(finalGuideAccount.getGuideid() + "");
					invalidAccount.setType("导游总收入 (total value)");
					invalidAccount.setName(finalGuide.getName());
					invalidAccount.setDbValue(finalGuideAccount.getTotal() + "");
					invalidAccount.setSumValue(totalAccount + "");

					invalidAccounts.add(invalidAccount);

					invalidTotalAccounts.add(invalidAccount);
				}

				if (finalGuideAccount.getCurrentprice() != activeAccount) {
					InvalidAccount invalidAccount = new InvalidAccount();

					invalidAccount.setId(finalGuideAccount.getGuideid() + "");
					invalidAccount.setType("导游可用余额(current value)");
					invalidAccount.setName(finalGuide.getName());
					invalidAccount.setDbValue(finalGuideAccount.getCurrentprice() + "");
					invalidAccount.setSumValue(activeAccount + "");

					invalidAccounts.add(invalidAccount);

					invalidActiceAccounts.add(invalidAccount);
				}

				startIndex = finalGuideAccount.getGuideid();
			}

			totalCount += finalGuideAccounts.size();

			finalGuideAccounts = finalGuideAccountService.pageQueryByGuideId(startIndex);
		}

		logger.info("guideAccountTransfer final startIndex: " + startIndex);
		logger.info("guideAccountTransfer totalCount: " + totalCount);

		System.out.println("----------------------------------------");
		System.out.println();

		System.out.println("导游总收入错误的账号数量：" + invalidTotalAccounts.size());

		HSSFWorkbook workBook = new HSSFWorkbook();

		Sheet guideTotalSheet = workBook.createSheet("导游总收入");

		guideTotalSheet.setColumnWidth(0, width);
		guideTotalSheet.setColumnWidth(1, width);
		guideTotalSheet.setColumnWidth(2, width);
		guideTotalSheet.setColumnWidth(3, width);

		Row row = guideTotalSheet.createRow(0);

		Cell idCell = row.createCell(0);
		idCell.setCellValue("导游ID");

		Cell nameCell = row.createCell(1);
		nameCell.setCellValue("名称");

		Cell totalCell = row.createCell(2);
		totalCell.setCellValue("账号总收入");

		Cell sumCell = row.createCell(3);
		sumCell.setCellValue("累加总收入");

		int index = 1;
		for (InvalidAccount invalidAccount : invalidTotalAccounts) {

			Row row2 = guideTotalSheet.createRow(index++);

			Cell idCell2 = row2.createCell(0);
			idCell2.setCellValue(invalidAccount.getId());

			Cell typeCell2 = row2.createCell(1);
			typeCell2.setCellValue(invalidAccount.getName());

			Cell totalCell2 = row2.createCell(2);
			totalCell2.setCellValue(invalidAccount.getDbValue());

			Cell sumCell2 = row2.createCell(3);
			sumCell2.setCellValue(invalidAccount.getSumValue());

			System.out.println(invalidAccount.toString());
		}

		System.out.println();
		System.out.println("----------------------------------------");

		System.out.println("----------------------------------------");
		System.out.println();

		System.out.println("导游可用收入错误的账号数量：" + invalidActiceAccounts.size());

		Sheet guideActiceSheet = workBook.createSheet("导游可用余额");

		guideActiceSheet.setColumnWidth(0, width);
		guideActiceSheet.setColumnWidth(1, width);
		guideActiceSheet.setColumnWidth(2, width);
		guideActiceSheet.setColumnWidth(3, width);

		Row activeRow = guideActiceSheet.createRow(0);

		Cell activeIdCell = activeRow.createCell(0);
		activeIdCell.setCellValue("导游ID");

		Cell activeNameCell = activeRow.createCell(1);
		activeNameCell.setCellValue("名称");

		Cell activeAccountCell = activeRow.createCell(2);
		activeAccountCell.setCellValue("账号可用余额");

		Cell sumActiveCell = activeRow.createCell(3);
		sumActiveCell.setCellValue("累加可用余额");

		index = 1;

		for (InvalidAccount invalidAccount : invalidActiceAccounts) {
			Row activeRow2 = guideActiceSheet.createRow(index++);

			Cell activeIdCell2 = activeRow2.createCell(0);
			activeIdCell2.setCellValue(invalidAccount.getId());

			Cell activeNameCell2 = activeRow2.createCell(1);
			activeNameCell2.setCellValue(invalidAccount.getName());

			Cell activeAccountCell2 = activeRow2.createCell(2);
			activeAccountCell2.setCellValue(invalidAccount.getDbValue());

			Cell sumActiveCell2 = activeRow2.createCell(3);
			sumActiveCell2.setCellValue(invalidAccount.getSumValue());

			System.out.println(invalidAccount.toString());
		}

		System.out.println();
		System.out.println("----------------------------------------");

		try {
			// 输出成XLS文件
			File file = new File("d:/GuideAccount.xls");

			if (file.exists()) {
				file.delete();
			}

			file.createNewFile();

			FileOutputStream fos = new FileOutputStream(file);

			// 写入数据，并关闭文件
			workBook.write(fos);
			fos.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	// 总数据量验证 1 导游账户 2C端账户 3为黄包车 4为代理商账户
	private void checkTotalAccountCount() {
		// 代理商数量验证
		FundAccountExample fundAccountExample = new FundAccountExample();
		FundAccountExample.Criteria criteria = fundAccountExample.createCriteria();
		criteria.andAccountTypeEqualTo(AccountType.AGENT_ACCOUNT.value);
		int javaAgentAccountCount = fundAccountMapper.countByExample(fundAccountExample);

		int phpAgentAccountCount = finalAgentAccountService.countAgentAccount();

		// Assert.assertEquals(javaAgentAccountCount, phpAgentAccountCount);
		if (javaAgentAccountCount != phpAgentAccountCount) {
			logger.warn(" .... fail: checkTotalAccountCount . 代理商账号总数量验证失败, javaAgentAccountCount: "
					+ javaAgentAccountCount + ", phpAgentAccountCount: " + phpAgentAccountCount);
		} else {
			logger.info(" ### success: checkTotalAccountCount . 代理商账号总数量验证成功, javaAgentAccountCount: "
					+ javaAgentAccountCount + ", phpAgentAccountCount: " + phpAgentAccountCount);
		}

		fundAccountExample = new FundAccountExample();
		criteria = fundAccountExample.createCriteria();
		criteria.andAccountTypeEqualTo(AccountType.G_ACCOUNT.value);
		int javaGuideAccountCount = fundAccountMapper.countByExample(fundAccountExample);

		int phpGuideAccountCount = finalGuideAccountService.countGuideAccount();

		// Assert.assertEquals(javaGuideAccountCount, phpGuideAccountCount);

		if (javaGuideAccountCount != phpGuideAccountCount) {
			logger.warn(" .... fail: checkTotalAccountCount . 导游账号总数量证失败, javaGuideAccountCount: "
					+ javaGuideAccountCount + ", phpGuideAccountCount: " + phpGuideAccountCount);
		} else {
			logger.info(" ### success: checkTotalAccountCount . 导游账号总数量证成功, javaGuideAccountCount: "
					+ javaGuideAccountCount + ", phpGuideAccountCount: " + phpGuideAccountCount);
		}
	}

	// 总数据量验证 1 导游账户 2C端账户 3为黄包车 4为代理商账户
	private void checkAccountCountByAmount(Double amount, Double totalAmount) {
		// 代理商数量验证
		FundAccountExample fundAccountExample = new FundAccountExample();
		FundAccountExample.Criteria criteria = fundAccountExample.createCriteria();
		criteria.andAccountTypeEqualTo(AccountType.AGENT_ACCOUNT.value);
		criteria.andAmountEqualTo(amount);
		criteria.andTotalAmountEqualTo(totalAmount);

		int javaAgentAccountCount = fundAccountMapper.countByExample(fundAccountExample);

		int phpAgentAccountCount = finalAgentAccountService.countAgentAccountByAmountAndTotalAmount(amount.intValue(),
				totalAmount.intValue());

		// Assert.assertEquals(javaAgentAccountCount, phpAgentAccountCount);

		if (javaAgentAccountCount != phpAgentAccountCount) {
			logger.warn(" .... fail: checkAccountCountByAmount . 代理商账号根据金额验证失败, javaAgentAccountCount: "
					+ javaAgentAccountCount + ", phpAgentAccountCount: " + phpAgentAccountCount + ", amount: " + amount
					+ ", totalAmount:" + totalAmount);
		} else {
			logger.info(" ### success: checkAccountCountByAmount . 代理商账号根据金额验证成功, javaAgentAccountCount: "
					+ javaAgentAccountCount + ", phpAgentAccountCount: " + phpAgentAccountCount + ", amount: " + amount
					+ ", totalAmount:" + totalAmount);
		}

		fundAccountExample = new FundAccountExample();
		criteria = fundAccountExample.createCriteria();
		criteria.andAccountTypeEqualTo(AccountType.G_ACCOUNT.value);
		criteria.andAmountEqualTo(amount);
		criteria.andTotalAmountEqualTo(totalAmount);

		int javaGuideAccountCount = fundAccountMapper.countByExample(fundAccountExample);

		int phpGuideAccountCount = finalGuideAccountService.countGuideAccountByAmountAndTotalAmount(amount.intValue(),
				totalAmount.intValue());

		// Assert.assertEquals(javaGuideAccountCount, phpGuideAccountCount);

		if (javaGuideAccountCount != phpGuideAccountCount) {
			logger.warn(" .... fail: checkAccountCountByAmount . 导游账号根据金额验证失败, javaGuideAccountCount: "
					+ javaGuideAccountCount + ", phpGuideAccountCount: " + phpGuideAccountCount + ", amount: "
					+ amount
					+ ", totalAmount:" + totalAmount);
		} else {
			logger.info(" ### success: checkAccountCountByAmount . 导游账号根据金额验证成功, javaGuideAccountCount: "
					+ javaGuideAccountCount + ", phpGuideAccountCount: " + phpGuideAccountCount + ", amount: " + amount
					+ ", totalAmount:" + totalAmount);
		}
	}

	private void checkTotalAccountLogCount() {
		FundAccountLogExample example = new FundAccountLogExample();
		FundAccountLogExample.Criteria criteria = example.createCriteria();

		criteria.andLogNoLike("agent-%");

		int javaAgentAccountLogCount = fundAccountLogMapper.countByExample(example);

		int phpAgentAccountLogCount = finalAgentAccountService.countAgentAccountLog();

		// Assert.assertEquals(javaAgentAccountLogCount,
		// phpAgentAccountLogCount);

		if (javaAgentAccountLogCount != phpAgentAccountLogCount) {
			logger.warn(" .... fail: checkTotalAccountLogCount . 代理商账号流水总数量证失败, javaAgentAccountLogCount: "
					+ javaAgentAccountLogCount + ", phpAgentAccountLogCount: " + phpAgentAccountLogCount);
		} else {
			logger.info(" ### success: checkTotalAccountLogCount . 代理商账号流水总数量证成功, javaAgentAccountLogCount: "
					+ javaAgentAccountLogCount + ", phpAgentAccountLogCount: " + phpAgentAccountLogCount);
		}

		example = new FundAccountLogExample();
		criteria = example.createCriteria();
		criteria.andLogNoLike("guide-%");

		int javaGuideAccountLogCount = fundAccountLogMapper.countByExample(example);

		int phpGuideAccountLogCount = finalGuideAccountService.countGuideAccountLog();

		// Assert.assertEquals(javaGuideAccountLogCount,
		// phpGuideAccountLogCount);

		if (javaGuideAccountLogCount != phpGuideAccountLogCount) {
			logger.warn(" .... fail: checkTotalAccountLogCount . 导游账号流水总数量证失败, javaGuideAccountLogCount: "
					+ javaGuideAccountLogCount + ", phpGuideAccountLogCount: " + phpGuideAccountLogCount);
		} else {
			logger.info(" ### success: checkTotalAccountLogCount . 导游账号流水总数量证成功, javaGuideAccountLogCount: "
					+ javaGuideAccountLogCount + ", phpGuideAccountLogCount: " + phpGuideAccountLogCount);
		}

		example = new FundAccountLogExample();
		criteria = example.createCriteria();
		criteria.andLogNoLike("system-%");

		int javaSysAccountLogCount = fundAccountLogMapper.countByExample(example);

		int phpSysAccountLogCount = finalSysAccountService.countSysAccountLog();

		// Assert.assertEquals(javaSysAccountLogCount, phpSysAccountLogCount);

		if (javaSysAccountLogCount != phpSysAccountLogCount) {
			logger.warn(" .... fail: checkTotalAccountLogCount . 系统账号流水总数量证失败, javaSysAccountLogCount: "
					+ javaSysAccountLogCount + ", phpSysAccountLogCount: " + phpSysAccountLogCount);
		} else {
			logger.info(" ### success: checkTotalAccountLogCount . 系统账号流水总数量证成功, javaSysAccountLogCount: "
					+ javaSysAccountLogCount + ", phpSysAccountLogCount: " + phpSysAccountLogCount);
		}
	}

	private void checkAccountLogCountByStatus(int status) {
		FundAccountLogExample example = new FundAccountLogExample();
		FundAccountLogExample.Criteria criteria = example.createCriteria();

		criteria.andLogNoLike("agent-%");
		criteria.andBizStatusEqualTo(status);

		int javaAgentAccountLogCount = fundAccountLogMapper.countByExample(example);

		int phpAgentAccountLogCount = finalAgentAccountService.countAgentAccountLogByStatus(status);

		// Assert.assertEquals(javaAgentAccountLogCount,
		// phpAgentAccountLogCount);

		if (javaAgentAccountLogCount != phpAgentAccountLogCount) {
			logger.warn(" .... fail: checkAccountLogCountByStatus . 代理商账号流水状态数量证失败, javaAgentAccountLogCount: "
					+ javaAgentAccountLogCount + ", phpAgentAccountLogCount: " + phpAgentAccountLogCount + ", status: "
					+ status);
		} else {
			logger.warn(" ### success: checkAccountLogCountByStatus . 代理商账号流水状态数量证成功, javaAgentAccountLogCount: "
					+ javaAgentAccountLogCount + ", phpAgentAccountLogCount: " + phpAgentAccountLogCount + ", status: "
					+ status);
		}

		example = new FundAccountLogExample();
		criteria = example.createCriteria();
		criteria.andLogNoLike("guide-%");
		criteria.andBizStatusEqualTo(status);

		int javaGuideAccountLogCount = fundAccountLogMapper.countByExample(example);

		int phpGuideAccountLogCount = finalGuideAccountService.countGuideAccountLogByStatus(status);

		// Assert.assertEquals(javaGuideAccountLogCount,
		// phpGuideAccountLogCount);

		if (javaGuideAccountLogCount != phpGuideAccountLogCount) {
			logger.warn(" .... fail: checkAccountLogCountByStatus . 导游账号流水状态数量证失败, javaGuideAccountLogCount: "
					+ javaGuideAccountLogCount + ", phpGuideAccountLogCount: " + phpGuideAccountLogCount + ", status: "
					+ status);
		} else {
			logger.warn(" ### success: checkAccountLogCountByStatus . 导游账号流水状态数量证成功, javaGuideAccountLogCount: "
					+ javaGuideAccountLogCount + ", phpGuideAccountLogCount: " + phpGuideAccountLogCount + ", status: "
					+ status);
		}
	}

	private void checkAccountLogCountByAgentId(int agentId) {
		FundAccountLogExample example = new FundAccountLogExample();
		FundAccountLogExample.Criteria criteria = example.createCriteria();

		criteria.andLogNoLike("agent-%");
		criteria.andAccountNoEqualTo(AccountIdUtil.getAgentAccountNo(agentId));

		int javaAgentAccountLogCount = fundAccountLogMapper.countByExample(example);

		int phpAgentAccountLogCount = finalAgentAccountService.countAgentAccountLogByAccountNo(agentId);

		if (javaAgentAccountLogCount != phpAgentAccountLogCount) {
			logger.warn(" .... fail: checkAccountLogCountByAgentId . 代理商账号流水状态数量证失败, javaAgentAccountLogCount: "
					+ javaAgentAccountLogCount + ", phpAgentAccountLogCount: " + phpAgentAccountLogCount + ", agentId: "
					+ agentId);
		} else {
			logger.warn(" ### success: checkAccountLogCountByAgentId . 代理商账号流水状态数量证成功, javaAgentAccountLogCount: "
					+ javaAgentAccountLogCount + ", phpAgentAccountLogCount: " + phpAgentAccountLogCount + ", agentId: "
					+ agentId);
		}
	}

	private void checkAccountLogCountByGuideId(int guideId) {
		FundAccountLogExample example = new FundAccountLogExample();
		FundAccountLogExample.Criteria criteria = example.createCriteria();

		criteria.andLogNoLike("guide-%");
		criteria.andAccountNoEqualTo(AccountIdUtil.getGuideAccountNo(guideId));

		int javaGuideAccountLogCount = fundAccountLogMapper.countByExample(example);

		int phpGuideAccountLogCount = finalGuideAccountService.countGuideAccountLogByGuideId(guideId);

		if (javaGuideAccountLogCount != phpGuideAccountLogCount) {
			logger.warn(" .... fail: checkAccountLogCountByGuideId . 导游账号流水状态数量证失败, javaGuideAccountLogCount: "
					+ javaGuideAccountLogCount + ", phpGuideAccountLogCount: " + phpGuideAccountLogCount + ", guideId: "
					+ guideId);
		} else {
			logger.warn(" ### success: checkAccountLogCountByGuideId . 导游账号流水状态数量证成功, javaGuideAccountLogCount: "
					+ javaGuideAccountLogCount + ", phpGuideAccountLogCount: " + phpGuideAccountLogCount + ", guideId: "
					+ guideId);
		}
	}

	private void checkAccountLogCountByType(int type) {
		FundAccountLogExample example = new FundAccountLogExample();
		FundAccountLogExample.Criteria criteria = example.createCriteria();

		criteria.andLogNoLike("agent-%");
		criteria.andBizTypeEqualTo(type);

		int javaAgentAccountLogCount = fundAccountLogMapper.countByExample(example);

		int phpAgentAccountLogCount = finalAgentAccountService.countAgentAccountLogByType(type);

		// Assert.assertEquals(javaAgentAccountLogCount,
		// phpAgentAccountLogCount);

		if (javaAgentAccountLogCount != phpAgentAccountLogCount) {
			logger.warn(" .... fail: checkAccountLogCountByStatus . 代理商账号流水类型数量证失败, javaAgentAccountLogCount: "
					+ javaAgentAccountLogCount + ", phpGuideAccountLogCount: " + phpAgentAccountLogCount + ", type: "
					+ type);
		} else {
			logger.warn(" ### success: checkAccountLogCountByStatus . 代理商账号流水类型数量证成功, javaAgentAccountLogCount: "
					+ javaAgentAccountLogCount + ", phpGuideAccountLogCount: " + phpAgentAccountLogCount + ", type: "
					+ type);
		}

		example = new FundAccountLogExample();
		criteria = example.createCriteria();
		criteria.andLogNoLike("guide-%");
		criteria.andBizTypeEqualTo(type);

		int javaGuideAccountLogCount = fundAccountLogMapper.countByExample(example);

		int phpGuideAccountLogCount = finalGuideAccountService.countGuideAccountLogByType(type);

		// Assert.assertEquals(javaGuideAccountLogCount,
		// phpGuideAccountLogCount);

		if (javaGuideAccountLogCount != phpGuideAccountLogCount) {
			logger.warn(" .... fail: checkTotalAccountLogCount . 导游账号流水类型数量证失败, javaGuideAccountLogCount: "
					+ javaGuideAccountLogCount + ", phpGuideAccountLogCount: " + phpGuideAccountLogCount + ", type: "
					+ type);
		} else {
			logger.warn(" ### success: checkTotalAccountCount . 导游账号流水类型数量证成功, javaGuideAccountLogCount: "
					+ javaGuideAccountLogCount + ", phpGuideAccountLogCount: " + phpGuideAccountLogCount + ", type: "
					+ type);
		}

		example = new FundAccountLogExample();
		criteria = example.createCriteria();
		criteria.andLogNoLike("system-%");
		criteria.andBizTypeEqualTo(type);

		int javaSysAccountLogCount = fundAccountLogMapper.countByExample(example);

		int phpSysAccountLogCount = finalSysAccountService.countSysAccountLogByType(type);

		// Assert.assertEquals(javaSysAccountLogCount, phpSysAccountLogCount);

		if (javaSysAccountLogCount != phpSysAccountLogCount) {
			logger.warn(" .... fail: checkAccountLogCountByStatus . 系统账号流水类型数量证失败, javaSysAccountLogCount: "
					+ javaSysAccountLogCount + ", phpSysAccountLogCount: " + phpSysAccountLogCount + ", type: "
					+ type);
		} else {
			logger.warn(" ### success: checkAccountLogCountByStatus . 系统账号流水类型数量证成功, javaSysAccountLogCount: "
					+ javaSysAccountLogCount + ", phpSysAccountLogCount: " + phpSysAccountLogCount + ", type: "
					+ type);
		}
	}
}
