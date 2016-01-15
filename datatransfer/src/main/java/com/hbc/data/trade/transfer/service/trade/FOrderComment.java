package com.hbc.data.trade.transfer.service.trade;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hbc.api.trade.order.mapping.gen.OrderBeanMapper;
import com.hbc.api.trade.order.mapping.gen.TradeMoveInfoMapper;
import com.hbc.api.trade.order.mapping.gen.bean.OrderBean;
import com.hbc.api.trade.order.mapping.gen.bean.TradeMoveInfo;
import com.hbc.api.trade.order.mapping.gen.bean.TradeMoveInfoExample;

@Component
public class FOrderComment {
	@Autowired
	OrderBeanMapper orderBeanMapper;

	public int updateOrderReward(String orderSN, Double reWardPrice) {

		OrderBean orderBean = orderBeanMapper.selectByPrimaryKey(orderSN);
		if (null == orderBean) {
			return -1;
		}

		if (null == orderBean.getPriceGuide()) {
			orderBean.setPriceGuide(reWardPrice);
		} else {
			orderBean.setPriceGuide(orderBean.getPriceGuide() + reWardPrice);
		}

		if (null == orderBean.getPriceReward()) {
			orderBean.setPriceReward(reWardPrice);
		} else {
			orderBean.setPriceReward(orderBean.getPriceReward() + reWardPrice);
		}

		return orderBeanMapper.updateByPrimaryKeySelective(orderBean);

	}
	@Autowired
	TradeMoveInfoMapper tradeMoveInfoMapper;
	public int updateOrderRewardByOrderId(String orderId, Double reWardPrice) {

		TradeMoveInfoExample example =new TradeMoveInfoExample();
		TradeMoveInfoExample.Criteria criteria =example.createCriteria();
		criteria.andOrderIdEqualTo(orderId);
		List<TradeMoveInfo> tradeMoveInfos =tradeMoveInfoMapper.selectByExample(example);
		if(tradeMoveInfos == null || tradeMoveInfos.size()==0){
			return 0;
		}
		String orderSN=tradeMoveInfos.get(0).getOrderSn();
		OrderBean orderBean = orderBeanMapper.selectByPrimaryKey(orderSN);
		if (null == orderBean) {
			return 0;
		}

		if (null == orderBean.getPriceGuide()) {
			orderBean.setPriceGuide(reWardPrice);
		} else {
			orderBean.setPriceGuide(orderBean.getPriceGuide() + reWardPrice);
		}

		if (null == orderBean.getPriceReward()) {
			orderBean.setPriceReward(reWardPrice);
		} else {
			orderBean.setPriceReward(orderBean.getPriceReward() + reWardPrice);
		}

		return orderBeanMapper.updateByPrimaryKeySelective(orderBean);

	}
}
