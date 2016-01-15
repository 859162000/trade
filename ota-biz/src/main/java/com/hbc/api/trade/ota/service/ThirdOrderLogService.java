package com.hbc.api.trade.ota.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.hbc.api.trade.ota.mapping.gen.TradeThirdOrderLogMapper;
import com.hbc.api.trade.ota.mapping.gen.bean.TradeThirdOrderLogWithBLOBs;
@Component
public class ThirdOrderLogService {

	private final static Logger log = LoggerFactory.getLogger(ThirdOrderLogService.class);

	@Autowired TradeThirdOrderLogMapper tradeThirdOrderLogMapper;
	
	@Transactional
	public int addOrderLog(TradeThirdOrderLogWithBLOBs tradeThirdOrderLogWithBLOBs) {
		log.info("insert order [" + tradeThirdOrderLogWithBLOBs + "] orderNo is [" + tradeThirdOrderLogWithBLOBs.getOrderNo() + "]");
		return tradeThirdOrderLogMapper.insert(tradeThirdOrderLogWithBLOBs);
	}
	

}
