/**
 * @Author lukangle
 * @2015年10月8日@下午5:39:09
 */
package com.hbc.api.trade.order.service;

import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hbc.api.trade.order.enums.TradeReturnCodeEnum;
import com.hbc.api.trade.order.enums.order.OrderStatus;
import com.hbc.api.trade.order.exception.TradeException;
import com.hbc.api.trade.order.mapping.gen.OrderBeanMapper;
import com.hbc.api.trade.order.mapping.gen.bean.OrderBean;
import com.hbc.api.trade.order.mapping.gen.bean.OrderBeanExample;
import com.hbc.api.trade.order.mapping.gen.bean.OrderBeanExample.Criteria;
import com.hbc.api.trade.order.mapping.genx.OrderBeanMapperEnhance;
import com.hbc.api.trade.order.mapping.genx.xbean.GDSOrderQueryBean;

@Component
public class GDSOrderQueryService {

	@Autowired
	private OrderBeanMapper orderBeanMapper;
	@Autowired
	private OrderBeanMapperEnhance orderBeanMapperEnhance;

	public OrderBean getOrderByNo(String orderNo) {
		OrderBean orderBean = orderBeanMapper.selectByPrimaryKey(orderNo);
		if (orderBean == null) {
			throw new TradeException(TradeReturnCodeEnum.ORDER_NOTEXIST, orderNo);
		}
		return orderBean;
	}

	public List<OrderBean> getOrdersAgentOpid(String agentOpid) {
		//校验用户存在
		OrderBeanExample orderBeanExample = new OrderBeanExample();
		Criteria criteria = orderBeanExample.createCriteria();
		criteria.andAgentOpidEqualTo(agentOpid);
		List<OrderBean> orderbeans = orderBeanMapper.selectByExample(orderBeanExample);
		return orderbeans;
	}

	public List<OrderBean> selectOrderForGDS(GDSOrderQueryBean queryBean) {
		return orderBeanMapperEnhance.selectOrderForGDS(queryBean);
	}

	public int selectOrderForGDSTotalSize(GDSOrderQueryBean queryBean) {
		return orderBeanMapperEnhance.selectOrderForGDSTotalSize(queryBean);
	}

	public Integer getDoneSize(GDSOrderQueryBean queryBean) {
		OrderBeanExample example = new OrderBeanExample();
		List<Integer> orderStatusList = new LinkedList<>();
		orderStatusList.add(OrderStatus.CONFIRMED_COST.value);
		orderStatusList.add(OrderStatus.SETTLEMENT.value);
		Criteria crieria = example.createCriteria();

		if (!StringUtils.isEmpty(queryBean.getAgentId())) {
			String agentId = queryBean.getAgentId();
			crieria.andAgentIdEqualTo(agentId);
		} 
		
		if (!StringUtils.isEmpty(queryBean.getAgentOpid())) {
			String agentOpId = queryBean.getAgentOpid();
			crieria.andAgentOpidEqualTo(agentOpId);
		}

		crieria.andOrderStatusIn(orderStatusList);
		return orderBeanMapper.countByExample(example);
	}

	public Integer getInitalSize(GDSOrderQueryBean queryBean) {
		OrderBeanExample example = new OrderBeanExample();
		Criteria crieria = example.createCriteria();

		if (StringUtils.isNoneBlank(queryBean.getAgentId())) {
			String agentId = queryBean.getAgentId();
			crieria.andAgentIdEqualTo(agentId);
		} else {
			String agentOpId = queryBean.getAgentOpid();
			crieria.andAgentOpidEqualTo(agentOpId);
		}

		crieria.andOrderStatusEqualTo(OrderStatus.INITSTATE.value);
		return orderBeanMapper.countByExample(example);
	}
}
