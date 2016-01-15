/**
 * @Author lukangle
 * @2015年12月8日@下午3:33:40
 */
package com.hbc.data.trade.transfer.service.thrid;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.hbc.api.trade.order.enums.ota.AgentChannelEnum;
import com.hbc.api.trade.order.mapping.gen.OrderBeanMapper;
import com.hbc.api.trade.order.mapping.gen.TradeMoveInfoMapper;
import com.hbc.api.trade.order.mapping.gen.bean.OrderBean;
import com.hbc.api.trade.order.mapping.gen.bean.TradeMoveInfo;
import com.hbc.api.trade.order.mapping.gen.bean.TradeMoveInfoExample;
import com.hbc.api.trade.ota.mapping.gen.TradeThirdOrderMapper;
import com.hbc.api.trade.ota.mapping.gen.bean.TradeThirdOrder;
import com.hbc.data.trade.transfer.mapping.thirdparty.gen.QunaOrderMapper;
import com.hbc.data.trade.transfer.mapping.thirdparty.gen.bean.QunaOrder;
import com.hbc.data.trade.transfer.mapping.thirdparty.gen.bean.QunaOrderCriteria;
import com.hbc.data.trade.transfer.service.trade.DOrderService;

@Component
public class QunaOrderService {
	@Autowired
	QunaOrderMapper qunaOrderMapper;

	public QunaOrder getQunaOrder(String orderSn) {
		QunaOrderCriteria qunaOrderCriteria = new QunaOrderCriteria();
		QunaOrderCriteria.Criteria criteria = qunaOrderCriteria.createCriteria();

		criteria.andVorderidEqualTo(orderSn);
		List<QunaOrder> qlist = qunaOrderMapper.selectByExample(qunaOrderCriteria);
		if (qlist.size() == 1) {
			return qlist.get(0);
		}
		return null;
	}
	@Autowired
	TradeThirdOrderMapper tradeThirdOrderMapper;
	@Transactional
	public void moveQunaOrder(QunaOrder qunaOrder,OrderBean orderBean){
		TradeThirdOrder tradeThirdOrder = new TradeThirdOrder();
		tradeThirdOrder.setThirdTradeNo(qunaOrder.getQorderid());
		tradeThirdOrder.setOrderNo(orderBean.getOrderNo());
		
		tradeThirdOrderMapper.updateByPrimaryKeySelective(tradeThirdOrder);
		
		log.info("成功更新"+orderBean.getOrderNo());
	}
	
	
	@Autowired
	OrderBeanMapper orderBeanMapper;
	@Autowired
	TradeMoveInfoMapper tradeMoveInfoMapper;
	private final static Logger log = LoggerFactory.getLogger(DOrderService.class);

	
	
}
