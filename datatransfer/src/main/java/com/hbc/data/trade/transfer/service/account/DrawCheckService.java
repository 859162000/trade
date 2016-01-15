package com.hbc.data.trade.transfer.service.account;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hbc.api.fund.biz.mapping.gen.FundWithdrawalMapper;
import com.hbc.api.fund.biz.mapping.gen.bean.FundWithdrawalExample;
import com.hbc.data.trade.transfer.mapping.hbcfund.gen.FinalGuidedrawMapper;
import com.hbc.data.trade.transfer.mapping.hbcfund.gen.bean.FinalGuidedrawCriteria;
import com.hbc.datamove.base.enums.UserType;
import com.hbc.datamove.base.util.DateUtil;
import com.hbc.datamove.base.util.UserIdConvertUtil;

@Service
public class DrawCheckService {

	private final static Logger logger = LoggerFactory.getLogger(BankCardCheckService.class);

	@Autowired
	private FundWithdrawalMapper fundWithdrawalMapper;

	@Autowired
	private FinalGuidedrawMapper finalGuidedrawMapper;

	/**
	 * 取现记录校验
	 */
	public void checkWithdrawal() {

		// 总数量验证
		checkTotalCount();

		logger.info("");
		logger.info("");

		// 银行
		checkFinTypeCount(1);
		logger.info("");
		// 支付宝
		checkFinTypeCount(2);
		logger.info("");
		// payPal
		checkFinTypeCount(3);

		logger.info("");
		logger.info("");

		checkGuideIdCount(459);
		logger.info("");
		checkGuideIdCount(1109);
		logger.info("");
		checkGuideIdCount(185);
		logger.info("");
		checkGuideIdCount(3127);
		logger.info("");
		checkGuideIdCount(100006092);
		logger.info("");
		checkGuideIdCount(100014378);
		logger.info("");
		checkGuideIdCount(100014274);
		logger.info("");
		checkGuideIdCount(100014542);
		logger.info("");
		logger.info("");

		// 申请提现
		checkStatusCount(0);
		logger.info("");
		// 已转账
		checkStatusCount(1);
		logger.info("");
		// 自动提现失败
		checkStatusCount(2);

		logger.info("");
		logger.info("");

		checkTransferTimeCount(DateUtil.stringToDate("2015-08-01 10:00:00", DateUtil.DATETIME_PATTERN),
				DateUtil.stringToDate("2015-09-01 10:00:00", DateUtil.DATETIME_PATTERN));

		logger.info("");
		checkTransferTimeCount(DateUtil.stringToDate("2015-06-01 00:00:00", DateUtil.DATETIME_PATTERN),
				DateUtil.stringToDate("2015-07-01 10:00:00", DateUtil.DATETIME_PATTERN));

		logger.info("");
		logger.info("");

		checkIsAutoCount(0);
		logger.info("");
		checkIsAutoCount(1);

		logger.info("");
		logger.info("");
	}

	// 总数量验证
	private void checkTotalCount() {

		FundWithdrawalExample fundWithdrawalExample = new FundWithdrawalExample();
		int javaTotalCount = fundWithdrawalMapper.countByExample(fundWithdrawalExample);

		FinalGuidedrawCriteria finalGuidedrawCriteria = new FinalGuidedrawCriteria();
		int phpTotalCount = finalGuidedrawMapper.countByExample(finalGuidedrawCriteria);

		// Assert.assertEquals(javaTotalCount, phpTotalCount);

		if (javaTotalCount != phpTotalCount) {
			logger.warn(" .... fail: checkTotalCount . 取现总数量证失败, javaTotalCount: "
					+ javaTotalCount + ", phpTotalCount: " + phpTotalCount);
		} else {
			logger.info(" ### success: checkTotalCount . 取现总数量证成功, javaTotalCount: "
					+ javaTotalCount + ", phpTotalCount: " + phpTotalCount);
		}
	}

	// 收款账号验证
	private void checkFinTypeCount(int fintype) {
		FundWithdrawalExample fundWithdrawalExample = new FundWithdrawalExample();

		FundWithdrawalExample.Criteria javaCriteria = fundWithdrawalExample.createCriteria();
		javaCriteria.andFinTypeEqualTo(fintype);
		int javaCount = fundWithdrawalMapper.countByExample(fundWithdrawalExample);

		FinalGuidedrawCriteria finalGuidedrawCriteria = new FinalGuidedrawCriteria();
		FinalGuidedrawCriteria.Criteria phpCriteria = finalGuidedrawCriteria.createCriteria();
		phpCriteria.andFintypeEqualTo(fintype);
		int phpCount = finalGuidedrawMapper.countByExample(finalGuidedrawCriteria);

		// Assert.assertEquals(javaCount, phpCount);

		if (javaCount != phpCount) {
			logger.warn(" .... fail: checkTotalCount . 取现fintype数量证失败, javaCount: "
					+ javaCount + ", phpCount: " + phpCount + ", finType: " + fintype);
		} else {
			logger.info(" ### success: checkTotalCount . 取现fintype数量证成功, javaCount: "
					+ javaCount + ", phpCount: " + phpCount + ", finType: " + fintype);
		}
	}

	// 根据导游IDcheck
	private void checkGuideIdCount(int guideId) {
		FundWithdrawalExample fundWithdrawalExample = new FundWithdrawalExample();

		FundWithdrawalExample.Criteria javaCriteria = fundWithdrawalExample.createCriteria();
		javaCriteria.andGuideIdEqualTo(UserIdConvertUtil.getNew(guideId + "", UserType.g.name()));
		int javaCount = fundWithdrawalMapper.countByExample(fundWithdrawalExample);

		FinalGuidedrawCriteria finalGuidedrawCriteria = new FinalGuidedrawCriteria();
		FinalGuidedrawCriteria.Criteria phpCriteria = finalGuidedrawCriteria.createCriteria();
		phpCriteria.andGuideidEqualTo(guideId);
		int phpCount = finalGuidedrawMapper.countByExample(finalGuidedrawCriteria);

		// Assert.assertEquals(javaCount, phpCount);
		if (javaCount != phpCount) {
			logger.warn(" .... fail: checkTotalCount . 取现guideId数量证失败, javaCount: "
					+ javaCount + ", phpCount: " + phpCount + ", guideId: " + guideId);
		} else {
			logger.info(" ### success: checkTotalCount . 取现guideId数量证成功, javaCount: "
					+ javaCount + ", phpCount: " + phpCount + ", guideId: " + guideId);
		}
	}

	// 根据导游IDcheck
	private void checkStatusCount(int status) {
		FundWithdrawalExample fundWithdrawalExample = new FundWithdrawalExample();

		FundWithdrawalExample.Criteria javaCriteria = fundWithdrawalExample.createCriteria();
		javaCriteria.andDrawStatusEqualTo(status);
		int javaCount = fundWithdrawalMapper.countByExample(fundWithdrawalExample);

		FinalGuidedrawCriteria finalGuidedrawCriteria = new FinalGuidedrawCriteria();
		FinalGuidedrawCriteria.Criteria phpCriteria = finalGuidedrawCriteria.createCriteria();
		phpCriteria.andStatusEqualTo(status);
		int phpCount = finalGuidedrawMapper.countByExample(finalGuidedrawCriteria);

		// Assert.assertEquals(javaCount, phpCount);

		if (javaCount != phpCount) {
			logger.warn(" .... fail: checkTotalCount . 取现status数量证失败, javaCount: "
					+ javaCount + ", phpCount: " + phpCount + ", status: " + status);
		} else {
			logger.info(" ### success: checkTotalCount . 取现status数量证成功, javaCount: "
					+ javaCount + ", phpCount: " + phpCount + ", status: " + status);
		}
	}

	// 根据导游IDcheck
	private void checkTransferTimeCount(Date startTime, Date endTime) {
		FundWithdrawalExample fundWithdrawalExample = new FundWithdrawalExample();

		FundWithdrawalExample.Criteria javaCriteria = fundWithdrawalExample.createCriteria();
		javaCriteria.andTransferTimeBetween(startTime, endTime);
		int javaCount = fundWithdrawalMapper.countByExample(fundWithdrawalExample);

		FinalGuidedrawCriteria finalGuidedrawCriteria = new FinalGuidedrawCriteria();
		FinalGuidedrawCriteria.Criteria phpCriteria = finalGuidedrawCriteria.createCriteria();
		phpCriteria.andTransfertimeBetween(startTime, endTime);
		int phpCount = finalGuidedrawMapper.countByExample(finalGuidedrawCriteria);

		// Assert.assertEquals(javaCount, phpCount);

		if (javaCount != phpCount) {
			logger.warn(" .... fail: checkTotalCount . 取现time数量证失败, javaCount: "
					+ javaCount + ", phpCount: " + phpCount + ", startTime: " + startTime + ", endTime: " + endTime);
		} else {
			logger.info(" ### success: checkTotalCount . 取现time数量证成功, javaCount: "
					+ javaCount + ", phpCount: " + phpCount + ", startTime: " + startTime + ", endTime: " + endTime);
		}
	}

	// 根据导游IDcheck
	private void checkIsAutoCount(int isAuto) {
		FundWithdrawalExample fundWithdrawalExample = new FundWithdrawalExample();

		FundWithdrawalExample.Criteria javaCriteria = fundWithdrawalExample.createCriteria();
		javaCriteria.andIsAutoEqualTo(isAuto);
		int javaCount = fundWithdrawalMapper.countByExample(fundWithdrawalExample);

		FinalGuidedrawCriteria finalGuidedrawCriteria = new FinalGuidedrawCriteria();
		FinalGuidedrawCriteria.Criteria phpCriteria = finalGuidedrawCriteria.createCriteria();
		phpCriteria.andIsAutoEqualTo(isAuto);
		int phpCount = finalGuidedrawMapper.countByExample(finalGuidedrawCriteria);

		// Assert.assertEquals(javaCount, phpCount);

		if (javaCount != phpCount) {
			logger.warn(" .... fail: checkTotalCount . 取现isAuto数量证失败, javaCount: "
					+ javaCount + ", phpCount: " + phpCount + ", isAuto: " + isAuto);
		} else {
			logger.info(" ### success: checkTotalCount . 取现isAuto数量证成功, javaCount: "
					+ javaCount + ", phpCount: " + phpCount + ", isAuto: " + isAuto);
		}
	}
}
