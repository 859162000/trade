package com.hbc.data.trade.transfer.service.account;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.hbc.api.fund.account.enums.AccountStatus;
import com.hbc.api.fund.account.enums.AccountType;
import com.hbc.api.fund.account.enums.BizStatus;
import com.hbc.api.fund.account.mapping.gen.FundAccountLogMapper;
import com.hbc.api.fund.account.mapping.gen.FundAccountMapper;
import com.hbc.api.fund.account.mapping.gen.bean.FundAccount;
import com.hbc.api.fund.account.mapping.gen.bean.FundAccountLog;
import com.hbc.api.fund.biz.enums.FundBankCardType;
import com.hbc.api.fund.biz.mapping.gen.FundBankCardMapper;
import com.hbc.api.fund.biz.mapping.gen.FundWithdrawalMapper;
import com.hbc.api.fund.biz.mapping.gen.bean.FundBankCard;
import com.hbc.api.fund.biz.mapping.gen.bean.FundWithdrawal;
import com.hbc.api.trade.bdata.common.enums.Sysuser;
import com.hbc.api.trade.order.mapping.gen.TradeMoveInfoMapper;
import com.hbc.api.trade.order.mapping.gen.bean.TradeMoveInfo;
import com.hbc.api.trade.order.mapping.gen.bean.TradeMoveInfoExample;
import com.hbc.api.trade.settle.enums.AccountEnums;
import com.hbc.data.trade.transfer.mapping.hbcbasedata.gen.FinalGuideinagencyMapper;
import com.hbc.data.trade.transfer.mapping.hbcbasedata.gen.bean.FinalAgency;
import com.hbc.data.trade.transfer.mapping.hbcbasedata.gen.bean.FinalAgent;
import com.hbc.data.trade.transfer.mapping.hbcbasedata.gen.bean.FinalGuide;
import com.hbc.data.trade.transfer.mapping.hbcbasedata.gen.bean.FinalGuideinagency;
import com.hbc.data.trade.transfer.mapping.hbcbasedata.gen.bean.FinalGuideinagencyCriteria;
import com.hbc.data.trade.transfer.mapping.hbcfinal.gen.FinalOrderBeanMapper;
import com.hbc.data.trade.transfer.mapping.hbcfinal.gen.bean.FinalGuideaccountdetail;
import com.hbc.data.trade.transfer.mapping.hbcfund.gen.FinalGuidedrawMapper;
import com.hbc.data.trade.transfer.mapping.hbcfund.gen.bean.FinalAgencyAccount;
import com.hbc.data.trade.transfer.mapping.hbcfund.gen.bean.FinalAgencyAccountlog;
import com.hbc.data.trade.transfer.mapping.hbcfund.gen.bean.FinalAgentAccount;
import com.hbc.data.trade.transfer.mapping.hbcfund.gen.bean.FinalAgentaccountLog;
import com.hbc.data.trade.transfer.mapping.hbcfund.gen.bean.FinalGuideAccount;
import com.hbc.data.trade.transfer.mapping.hbcfund.gen.bean.FinalGuidedraw;
import com.hbc.data.trade.transfer.mapping.hbcfund.gen.bean.FinalGuidedrawCriteria;
import com.hbc.data.trade.transfer.mapping.hbcfund.gen.bean.FinalGuidefinance;
import com.hbc.data.trade.transfer.mapping.hbcfund.gen.bean.FinalSysAccount;
import com.hbc.data.trade.transfer.mapping.hbcfund.gen.bean.FinalSysAccountLog;
import com.hbc.data.trade.transfer.service.trade.FOrderComment;
import com.hbc.data.trade.transfer.util.Page;
import com.hbc.datamove.base.enums.UserType;
import com.hbc.datamove.base.util.AccountIdUtil;
import com.hbc.datamove.base.util.AgentIdConvertUtil;
import com.hbc.datamove.base.util.UserIdConvertUtil;

@Service
public class AccountService {

	private final static Logger logger = LoggerFactory.getLogger(AccountService.class);

	@Autowired
	private FundAccountMapper fundAccountMapper;

	@Autowired
	private FundAccountLogMapper fundAccountLogMapper;

	@Autowired
	private FundWithdrawalMapper fundWithdrawalMapper;

	@Autowired
	private FundBankCardMapper fundBankCardMapper;

	@Autowired
	private FinalAgencyAccountService finalAgencyAccountService;

	@Autowired
	private FinalAgentAccountService finalAgentAccountService;

	@Autowired
	private FinalGuideAccountService finalGuideAccountService;

	@Autowired
	private FinalGuidefinanceService finalGuidefinanceService;

	@Autowired
	private FinalSysAccountService finalSysAccountService;

	@Autowired
	private FinalOrderBeanMapper finalOrderBeanMapper;

	@Autowired
	private FinalGuidedrawMapper finalGuidedrawMapper;

	@Autowired
	private FinalGuideinagencyMapper finalGuideinagencyMapper;

	@Autowired
	private FOrderComment fOrderComment;

	@Autowired
	private TradeMoveInfoMapper tradeMoveInfoMapper;

	private String toNewOrderSn(String orderId) {
		TradeMoveInfoExample example = new TradeMoveInfoExample();
		TradeMoveInfoExample.Criteria criteria = example.createCriteria();

		criteria.andOrderIdEqualTo(orderId + "");

		List<TradeMoveInfo> list = tradeMoveInfoMapper.selectByExample(example);

		if (CollectionUtils.isEmpty(list)) {
			return null;
		}

		TradeMoveInfo tradeMoveInfo = list.get(0);

		return tradeMoveInfo.getOrderSn();
	}

	/**
	 * 地接社账户
	 */
	private void agencyAccountTrarnsfer() {

		int startIndex = 0;
		int totalCount = 0;
		List<FinalAgencyAccount> finalAgencyAccounts = finalAgencyAccountService.pageQueryByAgencyId(startIndex);

		AccountType accountType = AccountType.G_ACCOUNT;

		while (!CollectionUtils.isEmpty(finalAgencyAccounts)) {

			logger.info("agencyAccountTrarnsfer startIndex: " + startIndex);

			for (FinalAgencyAccount finalAgencyAccount : finalAgencyAccounts) {

				FundAccount fundAccount = new FundAccount();

				FinalAgency finalAgency = finalAgencyAccountService.getByIAgencyId(finalAgencyAccount.getAgencyid());
				if (null == finalAgency) {
					logger.error("No finalAgency found for " + finalAgencyAccount.getAgencyid());
					continue;
				}

				fundAccount.setAccountNo(AccountIdUtil.getAgencyAccountNo(finalAgencyAccount.getAgencyid()));
				fundAccount.setAccountName(finalAgency.getName());
				fundAccount.setAccountType(accountType.value);

				fundAccount.setAmount(Double.valueOf(finalAgencyAccount.getActiveprice() + ""));
				fundAccount.setTotalAmount(Double.valueOf(finalAgencyAccount.getTotal() + ""));
				fundAccount.setFrozenAmount(Double.valueOf(finalAgencyAccount.getDepositprice() + ""));

				fundAccount.setUserId(UserIdConvertUtil.getNew(finalAgency.getGuideid() + "", UserType.g.name()));
				int status = finalAgency.getStatus();
				if (status == 1) {
					fundAccount.setAccountStatus(AccountStatus.NOMAL.value);
				} else if (status == 0) {
					fundAccount.setAccountStatus(AccountStatus.Frozen.value);
				} else {
					fundAccount.setAccountStatus(AccountStatus.DELETED.value);
				}

				fundAccount.setCreateTime(finalAgencyAccount.getCreatedAt());
				fundAccount.setUpdateTime(finalAgencyAccount.getUpdatedAt());

				fundAccountMapper.insert(fundAccount);

				startIndex = finalAgencyAccount.getAgencyid();
			}

			totalCount += finalAgencyAccounts.size();

			finalAgencyAccounts = finalAgencyAccountService.pageQueryByAgencyId(startIndex);
		}

		logger.info("agencyAccountTrarnsfer final startIndex: " + startIndex);
		logger.info("agencyAccountTrarnsfer totalCount: " + totalCount);
	}

	/**
	 * 供应商账户
	 */
	@Transactional
	public void agentAccountTransfer() {

		int startIndex = 0;
		int totalCount = 0;
		List<FinalAgentAccount> finalAgentAccounts = finalAgentAccountService.pageQueryByAgentId(startIndex);

		AccountType accountType = AccountType.AGENT_ACCOUNT;

		while (!CollectionUtils.isEmpty(finalAgentAccounts)) {

			logger.info("agentAccountTransfer startIndex: " + startIndex);

			for (FinalAgentAccount finalAgentAccount : finalAgentAccounts) {

				FundAccount fundAccount = new FundAccount();

				FinalAgent finalAgent = finalAgentAccountService.getFinalAgent(finalAgentAccount.getAgentid());
				if (null == finalAgent) {
					logger.error("No finalAgent found for " + finalAgentAccount.getAgentid());
					continue;
				}

				if (17 == finalAgentAccount.getAgentid()) {
					fundAccount.setAccountNo(AccountEnums.QUNA_ACCOUNT.getCode());
				} else if (100000059 == finalAgentAccount.getAgentid()) {
					fundAccount.setAccountNo(AccountEnums.XIECHEN_ACCOUNT.getCode());
				} else {
					fundAccount.setAccountNo(AccountIdUtil.getAgentAccountNo(finalAgentAccount.getAgentid()));
				}
				fundAccount.setAccountNo(AccountIdUtil.getAgentAccountNo(finalAgentAccount.getAgentid()));
				fundAccount.setAccountName(finalAgent.getAgentname());
				fundAccount.setAccountType(accountType.value);

				fundAccount.setAmount(Double.valueOf(finalAgentAccount.getCurrentprice() + ""));
				fundAccount.setTotalAmount(Double.valueOf(finalAgentAccount.getTotal() + ""));
				fundAccount.setFrozenAmount(Double.valueOf("0"));
				fundAccount.setUserId(AgentIdConvertUtil.getNewAgentId(finalAgentAccount.getAgentid() + ""));
				int status = finalAgent.getStatus();
				if (status == 1) {
					fundAccount.setAccountStatus(AccountStatus.NOMAL.value);
				} else if (status == 0) {
					fundAccount.setAccountStatus(AccountStatus.Frozen.value);
				} else {
					fundAccount.setAccountStatus(AccountStatus.DELETED.value);
				}

				fundAccount.setCreateTime(finalAgentAccount.getCreatedAt());
				fundAccount.setUpdateTime(finalAgentAccount.getUpdatedAt());

				fundAccountMapper.insert(fundAccount);

				startIndex = finalAgentAccount.getAgentid();
			}

			totalCount += finalAgentAccounts.size();

			finalAgentAccounts = finalAgentAccountService.pageQueryByAgentId(startIndex);
		}

		logger.info("agentAccountTransfer final startIndex: " + startIndex);
		logger.info("agentAccountTransfer totalCount: " + totalCount);
	}

	/**
	 * 导游账户
	 */
	@Transactional
	public void guideAccountTransfer() {
		int startIndex = 0;
		int totalCount = 0;
		List<FinalGuideAccount> finalGuideAccounts = finalGuideAccountService.pageQueryByGuideId(startIndex);

		AccountType accountType = AccountType.G_ACCOUNT;

		while (!CollectionUtils.isEmpty(finalGuideAccounts)) {

			logger.info("guideAccountTransfer startIndex: " + startIndex);

			for (FinalGuideAccount finalGuideAccount : finalGuideAccounts) {

				FundAccount fundAccount = new FundAccount();

				FinalGuide finalGuide = finalGuideAccountService.getFinalGuide(finalGuideAccount.getGuideid());
				if (null == finalGuide) {
					logger.warn("invalid guide id: " + finalGuideAccount.getGuideid());
					continue;
				}

				fundAccount.setAccountNo(AccountIdUtil.getGuideAccountNo(finalGuideAccount.getGuideid()));
				fundAccount.setAccountName(finalGuide.getName());
				fundAccount.setAccountType(accountType.value);

				fundAccount.setAmount(Double.valueOf(finalGuideAccount.getActiveprice() + ""));
				fundAccount.setTotalAmount(Double.valueOf(finalGuideAccount.getTotal() + ""));
				fundAccount.setFrozenAmount(Double.valueOf("0"));
				fundAccount.setUserId(UserIdConvertUtil.getNew(finalGuideAccount.getGuideid() + "", UserType.g.name()));
				int status = finalGuide.getStatus();
				if (status == 1) {
					fundAccount.setAccountStatus(AccountStatus.NOMAL.value);
				} else if (status == 0) {
					fundAccount.setAccountStatus(AccountStatus.Frozen.value);
				} else {
					fundAccount.setAccountStatus(AccountStatus.DELETED.value);
				}

				fundAccount.setCreateTime(finalGuideAccount.getCreatedAt());
				fundAccount.setUpdateTime(finalGuideAccount.getUpdatedAt());

				fundAccountMapper.insert(fundAccount);

				startIndex = finalGuideAccount.getGuideid();
			}

			totalCount += finalGuideAccounts.size();

			finalGuideAccounts = finalGuideAccountService.pageQueryByGuideId(startIndex);
		}

		logger.info("guideAccountTransfer final startIndex: " + startIndex);
		logger.info("guideAccountTransfer totalCount: " + totalCount);
	}

	/**
	 * 系统账户
	 */
	@Transactional
	public void sysAccountTransfer() {
		FinalSysAccount finalSysAccount = finalSysAccountService.getFinalSysAccount(1);

		FundAccount fundAccount = new FundAccount();

		fundAccount.setAccountNo(AccountEnums.OLD_HBC_Profit.value);
		fundAccount.setAccountName(AccountEnums.OLD_HBC_Profit.desc);
		fundAccount.setAccountType(AccountType.SYSTEM_ACCOUNT.value);
		// fundAccount.setAccountArea("");
		// fundAccount.setAccountMobile("");

		fundAccount.setAmount(finalSysAccount.getIncome().doubleValue());
		fundAccount.setTotalAmount(finalSysAccount.getIncome().doubleValue());
		fundAccount.setFrozenAmount(0d);
		// fundAccount.setUserId(Sysuser.SYSUSER.id);

		fundAccount.setAccountStatus(AccountStatus.NOMAL.value);

		fundAccount.setCreateTime(finalSysAccount.getCreatedAt());
		fundAccount.setUpdateTime(finalSysAccount.getUpdatedAt());

		fundAccountMapper.insert(fundAccount);

		FundAccount oldFundAccount = new FundAccount();

		oldFundAccount.setAccountNo(AccountEnums.OLD_HBC_BUTIE.value);
		oldFundAccount.setAccountName(AccountEnums.OLD_HBC_BUTIE.desc);
		oldFundAccount.setAccountType(AccountType.SYSTEM_ACCOUNT.value);
		// fundAccount.setAccountArea("");
		// fundAccount.setAccountMobile("");

		oldFundAccount.setAmount(finalSysAccount.getExpend().doubleValue());
		oldFundAccount.setTotalAmount(finalSysAccount.getExpend().doubleValue());
		oldFundAccount.setFrozenAmount(0d);
		// fundAccount.setUserId(Sysuser.SYSUSER.id);

		oldFundAccount.setAccountStatus(AccountStatus.NOMAL.value);

		oldFundAccount.setCreateTime(finalSysAccount.getCreatedAt());
		oldFundAccount.setUpdateTime(finalSysAccount.getUpdatedAt());

		fundAccountMapper.insert(oldFundAccount);
	}

	/**
	 * 地接社账户log
	 */
	private void agencyAccountLogTransfer() {
		int startIndex = 0;
		int totalCount = 0;
		List<FinalAgencyAccountlog> finalAgencyAccountlogs = finalAgencyAccountService
				.pageQueryByAgencyLogId(startIndex + "");

		int changeType = 2;
		while (!CollectionUtils.isEmpty(finalAgencyAccountlogs)) {

			logger.info("agencyAccountLogTransfer startIndex: " + startIndex);

			for (FinalAgencyAccountlog finalAgencyAccountlog : finalAgencyAccountlogs) {

				FundAccountLog fundAccountLog = new FundAccountLog();

				FinalAgency finalAgency = finalAgencyAccountService.getByIAgencyId(finalAgencyAccountlog.getAgencyid());
				if (null == finalAgency) {
					continue;
				}

				fundAccountLog.setLogNo("agency-" + finalAgencyAccountlog.getAgencyaccountlogid());
				fundAccountLog.setAccountNo(AccountIdUtil.getAgencyAccountNo(finalAgencyAccountlog.getAgencyid()));
				// fundAccountLog.setAmountCur(0d);
				// fundAccountLog.setAmountCg(0d);
				fundAccountLog.setChangAmount(finalAgencyAccountlog.getPrice().doubleValue());

				fundAccountLog.setChangeType(changeType);

				if (null != finalAgencyAccountlog.getOrderid()) {
					fundAccountLog.setOrderNo(toNewOrderSn(finalAgencyAccountlog.getOrderid()));
				}

				// fundAccountLog.setPayNo("");
				// fundAccountLog.setRefundNo("");

				FinalGuide finalGuide = finalGuideAccountService.getFinalGuide(finalAgencyAccountlog.getGuideid());
				if (null != finalGuide) {
					fundAccountLog.setOpUserId(
							UserIdConvertUtil.getNew(finalAgencyAccountlog.getGuideid() + "", UserType.g.name()));
					fundAccountLog.setOpUserName(finalGuide.getName());
				}
				// TODO
				fundAccountLog.setUserId(finalAgency.getAgencyid() + "");
				fundAccountLog.setUserName(finalAgency.getName());

				fundAccountLog.setBizType(finalAgencyAccountlog.getBiztype());
				int bizStatus = finalAgencyAccountlog.getBizstatus();
				if (bizStatus == 1) {
					fundAccountLog.setBizStatus(BizStatus.SUCCESS.value);
				} else {
					fundAccountLog.setBizStatus(BizStatus.FAILED.value);
				}
				fundAccountLog.setBizMessage(finalAgencyAccountlog.getBizcomment());
				fundAccountLog.setBizTime(finalAgencyAccountlog.getCreatedAt());
				fundAccountLog.setComment(finalAgencyAccountlog.getContent());

				// fundAccountLog.setRemark1("");
				// fundAccountLog.setRemark2("");
				// fundAccountLog.setRemark3("");
				// fundAccountLog.setRemark4("");
				// fundAccountLog.setRemark5("");

				fundAccountLog.setUpdateTime(finalAgencyAccountlog.getUpdatedAt());
				fundAccountLog.setCreateTime(finalAgencyAccountlog.getCreatedAt());

				fundAccountLogMapper.insert(fundAccountLog);

				startIndex = finalAgencyAccountlog.getAgencyfinanceid();
			}

			totalCount += finalAgencyAccountlogs.size();

			finalAgencyAccountlogs = finalAgencyAccountService
					.pageQueryByAgencyLogId(startIndex + "");
		}

		logger.info("agencyAccountLogTransfer final startIndex: " + startIndex);
		logger.info("agencyAccountLogTransfer totalCount: " + totalCount);
	}

	/**
	 * 供应商账户log
	 */
	@Transactional
	public void agentAccountLogTransfer() throws Exception {

		int index = 0;
		String startIndex = "150511122104000000";
		int totalCount = 0;
		List<FinalAgentaccountLog> finalAgentaccountLogs = finalAgentAccountService
				.pageQueryByAgentLogId(startIndex);

		int changeType = 2;
		while (!CollectionUtils.isEmpty(finalAgentaccountLogs)) {

			logger.info("agentAccountLogTransfer startIndex: " + startIndex);

			for (FinalAgentaccountLog finalAgentaccountLog : finalAgentaccountLogs) {

				FundAccountLog fundAccountLog = new FundAccountLog();

				FinalAgent finalAgent = finalAgentAccountService.getFinalAgent(finalAgentaccountLog.getAgentid());
				if (null == finalAgent) {
					logger.warn("no agent for agentId: " + finalAgentaccountLog.getAgentid());
					continue;
				}

				String agentAccountLogId = finalAgentaccountLog.getAgentaccountlogid();

				if (agentAccountLogId.equals("150924010514753135")) {

					if (index == 0) {
						agentAccountLogId = "150924010514753134";
					}

					if (index == 1) {
						agentAccountLogId = "150924010514753135";
					}

					index++;
				}

				fundAccountLog.setLogNo("agent-" + agentAccountLogId);
				fundAccountLog.setAccountNo(AccountIdUtil.getAgentAccountNo(finalAgentaccountLog.getAgentid()));
				// fundAccountLog.setAmountCur(0d);
				// fundAccountLog.setAmountCg(0d);
				fundAccountLog.setChangAmount(finalAgentaccountLog.getPrice().doubleValue());

				fundAccountLog.setChangeType(changeType);
				if (null != finalAgentaccountLog.getOrderid()) {
					fundAccountLog.setOrderNo(toNewOrderSn(finalAgentaccountLog.getOrderid()));
				}
				// fundAccountLog.setPayNo("");
				// fundAccountLog.setRefundNo("");

				fundAccountLog.setOpUserId(
						UserIdConvertUtil.getNew(finalAgentaccountLog.getAgentuserid() + "", UserType.g.name()));
				fundAccountLog.setOpUserName(finalAgentaccountLog.getAgentusername());
				fundAccountLog.setUserId(AgentIdConvertUtil.getNewAgentId(finalAgent.getAgentid() + ""));
				fundAccountLog.setUserName(finalAgent.getAgentname());

				fundAccountLog.setBizType(finalAgentaccountLog.getBiztype());
				int bizStatus = finalAgentaccountLog.getBizstatus();
				if (bizStatus == 1) {
					fundAccountLog.setBizStatus(BizStatus.SUCCESS.value);
				} else {
					fundAccountLog.setBizStatus(BizStatus.FAILED.value);
				}
				fundAccountLog.setBizMessage(finalAgentaccountLog.getBizcomment());
				fundAccountLog.setBizTime(finalAgentaccountLog.getCreatedAt());
				fundAccountLog.setComment(finalAgentaccountLog.getContent());

				fundAccountLog.setRemark1(finalAgentaccountLog.getComment());
				// fundAccountLog.setRemark2("");
				// fundAccountLog.setRemark3("");
				// fundAccountLog.setRemark4("");
				// fundAccountLog.setRemark5("");

				fundAccountLog.setUpdateTime(finalAgentaccountLog.getUpdatedAt());
				fundAccountLog.setCreateTime(finalAgentaccountLog.getCreatedAt());

				fundAccountLogMapper.insert(fundAccountLog);

				startIndex = finalAgentaccountLog.getAgentaccountlogid();
			}

			totalCount += finalAgentaccountLogs.size();

			finalAgentaccountLogs = finalAgentAccountService
					.pageQueryByAgentLogId(startIndex);

			Thread.sleep(50);
		}

		logger.info("agentAccountLogTransfer final startIndex: " + startIndex);
		logger.info("agentAccountLogTransfer totalCount: " + totalCount);
	}

	/**
	 * 导游账户log
	 */
	@Transactional
	public void guideAccountLogTransfer() throws Exception {
		String startIndex = "150511121911000000";
		int totalCount = 0;
		List<FinalGuideaccountdetail> finalGuideaccountdetails = finalGuideAccountService
				.pageQueryByGuideLogId(startIndex);

		int changeType = 2;
		while (!CollectionUtils.isEmpty(finalGuideaccountdetails)) {

			logger.info("guideAccountLogTransfer startIndex: " + startIndex);

			for (FinalGuideaccountdetail finalGuideaccountdetail : finalGuideaccountdetails) {

				FundAccountLog fundAccountLog = new FundAccountLog();

				FinalGuide finalGuide = finalGuideAccountService.getFinalGuide(finalGuideaccountdetail.getGuideid());
				if (null == finalGuide) {
					continue;
				}

				fundAccountLog.setLogNo("guide-" + finalGuideaccountdetail.getGuideaccountdetailid());
				fundAccountLog.setAccountNo(AccountIdUtil.getGuideAccountNo(finalGuideaccountdetail.getGuideid()));
				// fundAccountLog.setAmountCur(0d);
				// fundAccountLog.setAmountCg(0d);
				fundAccountLog.setChangAmount(finalGuideaccountdetail.getPrice().doubleValue());

				fundAccountLog.setChangeType(changeType);

				if (null != finalGuideaccountdetail.getOrderid()) {
					fundAccountLog.setOrderNo(toNewOrderSn(finalGuideaccountdetail.getOrderid()));
				}

				// fundAccountLog.setPayNo("");
				// fundAccountLog.setRefundNo("");

				fundAccountLog.setOpUserId(UserIdConvertUtil.getNew(finalGuide.getGuideid() + "", UserType.g.name()));
				fundAccountLog.setOpUserName(finalGuide.getName());
				fundAccountLog.setUserId(UserIdConvertUtil.getNew(finalGuide.getGuideid() + "", UserType.g.name()));
				fundAccountLog.setUserName(finalGuide.getName());

				fundAccountLog.setBizType(finalGuideaccountdetail.getBiztype());
				int bizStatus = finalGuideaccountdetail.getBizstatus();
				if (bizStatus == 1) {
					fundAccountLog.setBizStatus(BizStatus.SUCCESS.value);
				} else {
					fundAccountLog.setBizStatus(BizStatus.FAILED.value);
				}
				fundAccountLog.setBizMessage(finalGuideaccountdetail.getBizcomment());
				fundAccountLog.setBizTime(finalGuideaccountdetail.getCreatedAt());
				fundAccountLog.setComment(finalGuideaccountdetail.getContent());

				// fundAccountLog.setRemark1("");
				// fundAccountLog.setRemark2("");
				// fundAccountLog.setRemark3("");
				// fundAccountLog.setRemark4("");
				// fundAccountLog.setRemark5("");

				fundAccountLog.setUpdateTime(finalGuideaccountdetail.getUpdatedAt());
				fundAccountLog.setCreateTime(finalGuideaccountdetail.getCreatedAt());

				int bizType = finalGuideaccountdetail.getBiztype();
				if (bizType == 6 || bizType == 10 || bizType == 11 || bizType == 21 || bizType == 31 || bizType == 41) {
					if (null != finalGuideaccountdetail.getPrice()
							&& StringUtils.isNoneBlank(finalGuideaccountdetail.getOrderid())) {
						int result = fOrderComment.updateOrderRewardByOrderId(finalGuideaccountdetail.getOrderid(),
								finalGuideaccountdetail.getPrice().doubleValue());

						if (result != 1) {
							logger.error("fOrderComment.updateOrderRewardByOrderId error, orderId : "
									+ finalGuideaccountdetail.getOrderid() + ", doubleValue: "
									+ finalGuideaccountdetail.getPrice().doubleValue() + ", result : " + result);
						}
					}
				}

				fundAccountLogMapper.insert(fundAccountLog);

				startIndex = finalGuideaccountdetail.getGuideaccountdetailid();
			}

			totalCount += finalGuideaccountdetails.size();

			finalGuideaccountdetails = finalGuideAccountService
					.pageQueryByGuideLogId(startIndex);

			Thread.sleep(50);
		}

		logger.info("guideAccountLogTransfer final startIndex: " + startIndex);
		logger.info("guideAccountLogTransfer totalCount: " + totalCount);
	}

	/**
	 * 系统账户log
	 */
	@Transactional
	public void sysAccountLogTransfer() throws Exception {
		String startIndex = "150528113034880094";
		int totalCount = 0;
		List<FinalSysAccountLog> finalSysAccountLogs = finalSysAccountService
				.pageQueryBySysAccountLogId(startIndex);

		int changeType = 1;
		while (!CollectionUtils.isEmpty(finalSysAccountLogs)) {

			logger.info("sysAccountLogTransfer startIndex: " + startIndex);

			for (FinalSysAccountLog finalSysAccountLog : finalSysAccountLogs) {

				FundAccountLog fundAccountLog = new FundAccountLog();

				fundAccountLog.setLogNo("system-" + finalSysAccountLog.getSysaccountlogid());
				if (finalSysAccountLog.getPrice() > 0) {
					fundAccountLog.setAccountNo(AccountEnums.OLD_HBC_Profit.value);
				} else {
					fundAccountLog.setAccountNo(AccountEnums.OLD_HBC_BUTIE.value);
				}

				// fundAccountLog.setAmountCur(0d);
				// fundAccountLog.setAmountCg(0d);
				fundAccountLog.setChangAmount(finalSysAccountLog.getPrice().doubleValue());

				fundAccountLog.setChangeType(changeType);

				if (null != finalSysAccountLog.getOrderid()) {
					fundAccountLog.setOrderNo(toNewOrderSn(finalSysAccountLog.getOrderid()));
				}

				// fundAccountLog.setPayNo("");
				// fundAccountLog.setRefundNo("");

				fundAccountLog.setOpUserId(Sysuser.SYSUSER.id);
				fundAccountLog.setOpUserName(Sysuser.SYSUSER.name);
				// fundAccountLog.setUserId(finalGuide.getGuideid() + "");
				// fundAccountLog.setUserName("system");

				fundAccountLog.setBizType(finalSysAccountLog.getBiztype());
				int bizStatus = 1;
				if (bizStatus == 1) {
					fundAccountLog.setBizStatus(BizStatus.SUCCESS.value);
				} else {
					fundAccountLog.setBizStatus(BizStatus.FAILED.value);
				}
				// fundAccountLog.setBizMessage("");
				fundAccountLog.setBizTime(finalSysAccountLog.getCreatedAt());
				fundAccountLog.setComment(finalSysAccountLog.getBizcomment());

				// fundAccountLog.setRemark1("");
				// fundAccountLog.setRemark2("");
				// fundAccountLog.setRemark3("");
				// fundAccountLog.setRemark4("");
				// fundAccountLog.setRemark5("");

				fundAccountLog.setUpdateTime(finalSysAccountLog.getUpdatedAt());
				fundAccountLog.setCreateTime(finalSysAccountLog.getCreatedAt());

				fundAccountLogMapper.insert(fundAccountLog);

				startIndex = finalSysAccountLog.getSysaccountlogid();
			}

			totalCount += finalSysAccountLogs.size();

			finalSysAccountLogs = finalSysAccountService
					.pageQueryBySysAccountLogId(startIndex);

			Thread.sleep(50);
		}

		logger.info("sysAccountLogTransfer final startIndex: " + startIndex);
		logger.info("sysAccountLogTransfer totalCount: " + totalCount);
	}

	/**
	 * 导游提现
	 */
	@Transactional
	public void guideDrawTransfer() {

		int startIndex = 0;
		int totalCount = 0;

		List<FinalGuidedraw> finalGuidedraws = pageQueryFinalGuidedraws(startIndex);

		while (!CollectionUtils.isEmpty(finalGuidedraws)) {

			logger.info("guideDrawTransfer startIndex: " + startIndex);
			for (FinalGuidedraw finalGuidedraw : finalGuidedraws) {

				FundWithdrawal fundWithdrawal = new FundWithdrawal();

				fundWithdrawal.setDrawNo(finalGuidedraw.getGuidedrawno());
				fundWithdrawal
						.setGuideId(UserIdConvertUtil.getNew(finalGuidedraw.getGuideid() + "", UserType.g.name()));

				fundWithdrawal.setGuideName(finalGuidedraw.getGuidename());
				FinalGuideinagency finalGuideinagency = getFinalGuideinagency(finalGuidedraw.getGuideid());

				if (null != finalGuideinagency) {
					fundWithdrawal.setGuideAgencyId(finalGuideinagency.getAgencyid() + "");
					FinalAgency finalAgency = finalAgencyAccountService
							.getByIAgencyId(finalGuideinagency.getAgencyid());
					if (null != finalAgency) {
						fundWithdrawal.setGuideAgencyName(finalAgency.getName());
					}
				}

				fundWithdrawal.setApplyTime(finalGuidedraw.getApplytime());
				fundWithdrawal.setPrice(finalGuidedraw.getPrice().doubleValue());
				fundWithdrawal.setFinBankNo(finalGuidedraw.getFinaccount());
				fundWithdrawal.setFinName(finalGuidedraw.getFinname());
				fundWithdrawal.setFinAccount(AccountIdUtil.getGuideAccountNo(finalGuidedraw.getGuideid()));

				fundWithdrawal.setFinBank(finalGuidedraw.getFinbank());
				fundWithdrawal.setFinCurrency(finalGuidedraw.getFincurrency());
				fundWithdrawal.setFinType(finalGuidedraw.getFintype());
				fundWithdrawal.setAdminId(finalGuidedraw.getAdminid() + "");
				if (null != finalGuidedraw.getActualprice()) {
					fundWithdrawal.setActualPrice(finalGuidedraw.getActualprice().doubleValue());
				}

				fundWithdrawal.setTransferTime(finalGuidedraw.getTransfertime());
				fundWithdrawal.setAccount(finalGuidedraw.getAccount());
				fundWithdrawal.setDrawComment(finalGuidedraw.getComment());
				fundWithdrawal.setDrawStatus(finalGuidedraw.getStatus());
				Object object = finalGuidedraw.getIsAuto();
				if (null != object) {
					if ("1".equalsIgnoreCase(finalGuidedraw.getIsAuto().toString().trim())
							|| "true".equalsIgnoreCase(finalGuidedraw.getIsAuto().toString().trim())) {
						fundWithdrawal.setIsAuto(1);
					} else {
						fundWithdrawal.setIsAuto(0);
					}

				} else {
					fundWithdrawal.setIsAuto(0);
				}

				fundWithdrawal.setUpdateTime(finalGuidedraw.getUpdatedAt());
				fundWithdrawal.setCreateTime(finalGuidedraw.getCreatedAt());

				int finType = finalGuidedraw.getFintype();
				int status = finalGuidedraw.getStatus();
				int processStatus;
				if (finType == 2 && status == 0 && fundWithdrawal.getIsAuto() == 1) {
					processStatus = 2;
				} else if (status == 0) {
					processStatus = 0;
				} else {
					processStatus = 1;
				}

				fundWithdrawal.setProcessStatus(processStatus);

				fundWithdrawalMapper.insert(fundWithdrawal);

				startIndex = finalGuidedraw.getGuidedrawid();
			}

			totalCount += finalGuidedraws.size();
			finalGuidedraws = pageQueryFinalGuidedraws(startIndex);
		}

		logger.info("guideDrawTransfer final startIndex: " + startIndex);
		logger.info("guideDrawTransfer totalCount: " + totalCount);
	}

	/**
	 * 银行卡迁移
	 */
	@Transactional
	public void bankCardTransfer() {
		int startIndex = 0;
		int totalCount = 0;

		List<FinalGuidefinance> finalGuidefinances = finalGuidefinanceService.pageQueryGuidefinance(startIndex);

		while (!CollectionUtils.isEmpty(finalGuidefinances)) {

			logger.info("bankCardTransfer startIndex: " + startIndex);
			for (FinalGuidefinance finalGuidefinance : finalGuidefinances) {
				FundBankCard fundBankCard = new FundBankCard();

				fundBankCard.setBankNo(finalGuidefinance.getGuidefinanceid() + "");
				fundBankCard
						.setGuideId(UserIdConvertUtil.getNew(finalGuidefinance.getGuideid() + "", UserType.g.name()));

				FinalGuide finalGuide = finalGuideAccountService.getFinalGuide(finalGuidefinance.getGuideid());
				if (null != finalGuide) {
					fundBankCard.setGuideName(finalGuide.getName());
				}
				FinalGuideinagency finalGuideinagency = getFinalGuideinagency(finalGuidefinance.getGuideid());
				if (null != finalGuideinagency) {
					fundBankCard.setGuideAgencyId(finalGuideinagency.getAgencyid() + "");
					FinalAgency finalAgency = finalAgencyAccountService
							.getByIAgencyId(finalGuideinagency.getAgencyid());
					if (null != finalAgency) {
						fundBankCard.setGuideAgencyName(finalAgency.getName());
					}
				}

				fundBankCard.setAccountHolderName(finalGuidefinance.getName());
				fundBankCard.setAccount(finalGuidefinance.getAccount());
				if (finalGuidefinance.getType() == FundBankCardType.ALIPAY.value) {
					fundBankCard.setBank("支付宝");
				} else if (finalGuidefinance.getType() == FundBankCardType.PAYPAL.value) {
					fundBankCard.setBank("Paypal");
				} else {
					fundBankCard.setBank(finalGuidefinance.getBank());
				}

				fundBankCard.setCityId(finalGuidefinance.getCity());
				fundBankCard.setCityName(finalGuidefinance.getCityname());

				fundBankCard.setCurrency(finalGuidefinance.getCurrency());
				fundBankCard.setSwift(finalGuidefinance.getSwift());
				fundBankCard.setIsValid(finalGuidefinance.getIsvalidated());
				fundBankCard.setType(finalGuidefinance.getType());
				fundBankCard.setStatus(finalGuidefinance.getStatus());

				fundBankCard.setUpdateTime(finalGuidefinance.getUpdatedAt());
				fundBankCard.setCreateTime(fundBankCard.getCreateTime());

				fundBankCardMapper.insert(fundBankCard);
				startIndex = finalGuidefinance.getGuidefinanceid();
			}

			totalCount += finalGuidefinances.size();
			finalGuidefinances = finalGuidefinanceService.pageQueryGuidefinance(startIndex);
		}

		logger.info("bankCardTransfer final startIndex: " + startIndex);
		logger.info("bankCardTransfer totalCount: " + totalCount);
	}

	private List<FinalGuidedraw> pageQueryFinalGuidedraws(int startIndex) {
		int limit = 100;
		FinalGuidedrawCriteria example = new FinalGuidedrawCriteria();
		FinalGuidedrawCriteria.Criteria criteria = example.createCriteria();

		criteria.andGuidedrawidGreaterThan(startIndex);

		Page page = new Page(0, limit);
		example.setPage(page);

		example.setOrderByClause("guidedrawid asc");

		return finalGuidedrawMapper.selectByExample(example);
	}

	private FinalGuideinagency getFinalGuideinagency(int guideId) {
		FinalGuideinagencyCriteria example = new FinalGuideinagencyCriteria();
		FinalGuideinagencyCriteria.Criteria criteria = example.createCriteria();

		criteria.andGuideidEqualTo(guideId);

		List<FinalGuideinagency> finalGuideinagencys = finalGuideinagencyMapper.selectByExample(example);

		if (CollectionUtils.isEmpty(finalGuideinagencys)) {
			return null;
		}
		return finalGuideinagencys.get(0);
	}

}
