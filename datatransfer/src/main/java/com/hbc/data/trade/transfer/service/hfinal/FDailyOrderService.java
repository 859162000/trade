package com.hbc.data.trade.transfer.service.hfinal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hbc.data.trade.transfer.mapping.hbcfinal.gen.FinalOrderDailyMapper;
import com.hbc.data.trade.transfer.mapping.hbcfinal.gen.bean.FinalOrderDaily;

@Component
public class FDailyOrderService {
	@Autowired
	FinalOrderDailyMapper finalOrderDailyMapper;
	public FinalOrderDaily getFinalOrderBean(String orderId) {
		return finalOrderDailyMapper.selectByPrimaryKey(orderId);
	}
}
