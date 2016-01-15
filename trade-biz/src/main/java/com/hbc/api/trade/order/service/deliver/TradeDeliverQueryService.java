/**
 * @Author lukangle
 * @2015年11月29日@上午10:52:39
 */
package com.hbc.api.trade.order.service.deliver;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hbc.api.trade.order.mapping.gen.TradeOrderDeliverMapper;
import com.hbc.api.trade.order.mapping.gen.bean.TradeOrderDeliver;
import com.hbc.api.trade.order.mapping.gen.bean.TradeOrderDeliverExample;

@Component
public class TradeDeliverQueryService {
	@Autowired
	TradeOrderDeliverMapper tradeDeliverMapper;

	public List<TradeOrderDeliver> getDeliversByOrderNo(String orderNo){
		TradeOrderDeliverExample tradeDeliverExample = new TradeOrderDeliverExample();
		TradeOrderDeliverExample.Criteria criteria = tradeDeliverExample.createCriteria();
		criteria.andOrderNoEqualTo(orderNo);
		return tradeDeliverMapper.selectByExample(tradeDeliverExample);
	}
	
	public TradeOrderDeliver getDeliverByDeliverNo(String deliverNo){
		return tradeDeliverMapper.selectByPrimaryKey(deliverNo);
	}

	public List<TradeOrderDeliver> getDeliverByDeliverType(Integer deliverType) {
		TradeOrderDeliverExample tradeDeliverExample = new TradeOrderDeliverExample();
		TradeOrderDeliverExample.Criteria criteria = tradeDeliverExample.createCriteria();
		criteria.andDeliverTypeEqualTo(deliverType);
		return tradeDeliverMapper.selectByExample(tradeDeliverExample);
	}
}
