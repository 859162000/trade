package com.hbc.data.trade.transfer.service.account;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hbc.api.fund.biz.mapping.gen.FundBankCardMapper;
import com.hbc.api.fund.biz.mapping.gen.bean.FundBankCardExample;
import com.hbc.data.trade.transfer.mapping.hbcfund.gen.FinalGuidefinanceMapper;
import com.hbc.data.trade.transfer.mapping.hbcfund.gen.bean.FinalGuidefinanceCriteria;
import com.hbc.datamove.base.enums.UserType;
import com.hbc.datamove.base.util.UserIdConvertUtil;

@Service
public class BankCardCheckService {

	private final static Logger logger = LoggerFactory.getLogger(BankCardCheckService.class);

	@Autowired
	private FundBankCardMapper fundBankCardMapper;

	@Autowired
	private FinalGuidefinanceMapper finalGuidefinanceMapper;

	public void checkCount() {
		checkTotalCount();

		logger.info("");
		logger.info("");

		checkByType(1);
		logger.info("");
		checkByType(2);
		logger.info("");
		checkByType(3);

		logger.info("");
		logger.info("");

		checkByStatus(0);
		logger.info("");
		checkByStatus(1);

		logger.info("");
		logger.info("");

		checkByGuideId(637);
		logger.info("");
		checkByGuideId(1258);
		logger.info("");
		checkByGuideId(2530);
		logger.info("");
		checkByGuideId(100003040);
		logger.info("");
		checkByGuideId(100009524);

		checkByGuideId(100014754);
		logger.info("");
		checkByGuideId(100006182);
		logger.info("");
		checkByGuideId(100014874);
		logger.info("");
		checkByGuideId(100013656);
		logger.info("");
		checkByGuideId(100000154);

		logger.info("");
		logger.info("");
	}

	private void checkTotalCount() {
		FundBankCardExample javaExample = new FundBankCardExample();
		int javaTotalBankCardCount = fundBankCardMapper.countByExample(javaExample);

		FinalGuidefinanceCriteria phpExample = new FinalGuidefinanceCriteria();
		int phpTotalBankCardCount = finalGuidefinanceMapper.countByExample(phpExample);

		// Assert.assertEquals(javaTotalBankCardCount, phpTotalBankCardCount);

		if (javaTotalBankCardCount != phpTotalBankCardCount) {
			logger.warn(" .... fail: checkTotalCount . 银行卡总数量证失败, javaTotalBankCardCount: "
					+ javaTotalBankCardCount + ", phpTotalBankCardCount: " + phpTotalBankCardCount);
		} else {
			logger.info(" ### success: checkTotalCount . 银行卡总数量证成功, javaTotalBankCardCount: "
					+ javaTotalBankCardCount + ", phpTotalBankCardCount: " + phpTotalBankCardCount);
		}
	}

	private void checkByType(int type) {
		FundBankCardExample javaExample = new FundBankCardExample();
		FundBankCardExample.Criteria criteria = javaExample.createCriteria();
		criteria.andTypeEqualTo(type);
		int javaTotalBankCardCount = fundBankCardMapper.countByExample(javaExample);

		FinalGuidefinanceCriteria phpExample = new FinalGuidefinanceCriteria();
		FinalGuidefinanceCriteria.Criteria phpCriteria = phpExample.createCriteria();
		phpCriteria.andTypeEqualTo(type);

		int phpTotalBankCardCount = finalGuidefinanceMapper.countByExample(phpExample);

		// Assert.assertEquals(javaTotalBankCardCount, phpTotalBankCardCount);

		if (javaTotalBankCardCount != phpTotalBankCardCount) {
			logger.warn(" .... fail: checkByType . 银行卡类型数量证失败, javaTotalBankCardCount: "
					+ javaTotalBankCardCount + ", phpTotalBankCardCount: " + phpTotalBankCardCount + ", type: " + type);
		} else {
			logger.info(" ### success: checkByType . 银行卡类型数量证成功, javaTotalBankCardCount: "
					+ javaTotalBankCardCount + ", phpTotalBankCardCount: " + phpTotalBankCardCount + ", type: " + type);
		}
	}

	private void checkByStatus(int status) {
		FundBankCardExample javaExample = new FundBankCardExample();
		FundBankCardExample.Criteria criteria = javaExample.createCriteria();
		criteria.andStatusEqualTo(status);
		int javaTotalBankCardCount = fundBankCardMapper.countByExample(javaExample);

		FinalGuidefinanceCriteria phpExample = new FinalGuidefinanceCriteria();
		FinalGuidefinanceCriteria.Criteria phpCriteria = phpExample.createCriteria();
		phpCriteria.andStatusEqualTo(status);

		int phpTotalBankCardCount = finalGuidefinanceMapper.countByExample(phpExample);

		// Assert.assertEquals(javaTotalBankCardCount, phpTotalBankCardCount);

		if (javaTotalBankCardCount != phpTotalBankCardCount) {
			logger.warn(" .... fail: checkByStatus . 银行卡状态数量证失败, javaTotalBankCardCount: "
					+ javaTotalBankCardCount + ", phpTotalBankCardCount: " + phpTotalBankCardCount + ", status: "
					+ status);
		} else {
			logger.info(" ### success: checkByStatus . 银行卡状态数量证成功, javaTotalBankCardCount: "
					+ javaTotalBankCardCount + ", phpTotalBankCardCount: " + phpTotalBankCardCount + ", status: "
					+ status);
		}
	}

	private void checkByGuideId(int guideId) {
		FundBankCardExample javaExample = new FundBankCardExample();
		FundBankCardExample.Criteria criteria = javaExample.createCriteria();
		criteria.andGuideIdEqualTo(UserIdConvertUtil.getNew(guideId + "", UserType.g.name()));
		int javaTotalBankCardCount = fundBankCardMapper.countByExample(javaExample);

		FinalGuidefinanceCriteria phpExample = new FinalGuidefinanceCriteria();
		FinalGuidefinanceCriteria.Criteria phpCriteria = phpExample.createCriteria();
		phpCriteria.andGuideidEqualTo(guideId);

		int phpTotalBankCardCount = finalGuidefinanceMapper.countByExample(phpExample);

		// Assert.assertEquals(javaTotalBankCardCount, phpTotalBankCardCount);

		if (javaTotalBankCardCount != phpTotalBankCardCount) {
			logger.warn(" .... fail: checkByGuideId . 银行卡导游数量证失败, javaTotalBankCardCount: "
					+ javaTotalBankCardCount + ", phpTotalBankCardCount: " + phpTotalBankCardCount + ", guideId: "
					+ guideId);
		} else {
			logger.info(" ### success: checkByGuideId . 银行卡导游数量证成功, javaTotalBankCardCount: "
					+ javaTotalBankCardCount + ", phpTotalBankCardCount: " + phpTotalBankCardCount + ", guideId: "
					+ guideId);
		}
	}
}
