/**
 * @Author lukangle
 * @2015年11月10日@上午11:48:46
 */
package com.hbc.api.trade.order.service.deliver;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hbc.api.trade.order.enums.deliver.OrderDeliverStatus;
import com.hbc.api.trade.order.enums.order.OrderStatus;
import com.hbc.api.trade.order.enums.order.OrderType;
import com.hbc.api.trade.order.mapping.gen.OrderBeanMapper;
import com.hbc.api.trade.order.mapping.gen.bean.OrderBean;
import com.hbc.api.trade.order.mapping.gen.bean.OrderBeanExample;
@Component
public class QueryDeliverOrderService {
	@Autowired
	OrderBeanMapper orderBeanMapper;
	/**
	 * 获取该时间段内导游的接送次订单
	 * 接送次 规则判断查询
	 * @param guideId
	 * @param orderStatus
	 * @param orderDeliverStatus
	 * @param serviceStartTime
	 * @param seviceEndTime
	 * @return
	 */
	public int getMayConfictGuidePSTOrders(String guideId,Date serviceStartTime,Date seviceEndTime){
		OrderBeanExample orderBeanExample = new OrderBeanExample();
		OrderBeanExample.Criteria criteria = orderBeanExample.createCriteria();
		criteria.andGuideIdEqualTo(guideId);
		setCompleteStatus(criteria);
		criteria.andDeliverStatusEqualTo(OrderDeliverStatus.deliversuccess.value);
		criteria.andServiceTimeGreaterThanOrEqualTo(serviceStartTime);
		criteria.andServiceEndTimeLessThanOrEqualTo(seviceEndTime);
		criteria.andOrderTypeNotEqualTo(OrderType.DAILY.value);
		return orderBeanMapper.countByExample(orderBeanExample);
	}
	
	private void setCompleteStatus(OrderBeanExample.Criteria criteria){
		criteria.andOrderStatusNotEqualTo(OrderStatus.INITSTATE.value);
		criteria.andOrderStatusNotEqualTo(OrderStatus.CANCEL_CLOSE.value);
		criteria.andOrderStatusNotEqualTo(OrderStatus.CANCEL_NOSERVICE.value);
		criteria.andOrderStatusNotEqualTo(OrderStatus.CANCEL_SERVICEED.value);
		criteria.andOrderStatusNotEqualTo(OrderStatus.CANCLE_CLOSE_PAY_SERVICE.value);
		criteria.andOrderStatusNotEqualTo(OrderStatus.SETTLEMENT.value);
		criteria.andOrderStatusNotEqualTo(OrderStatus.CONFIRMED_COST.value);
		criteria.andOrderStatusNotEqualTo(OrderStatus.CANCELING.value);
	}
	/**
	 * 获取可能冲突的订单
	 * @param guideId
	 * @param serviceStartTime
	 * @param seviceEndTime
	 * @return
	 */
	public List<OrderBean> getMayConfictGuideOrders(String guideId,Date serviceStartTime,Date seviceEndTime){
		OrderBeanExample orderBeanExample = new OrderBeanExample();
		OrderBeanExample.Criteria criteria = orderBeanExample.createCriteria();
		criteria.andGuideIdEqualTo(guideId);
		setCompleteStatus(criteria);
		
		criteria.andDeliverStatusEqualTo(OrderDeliverStatus.deliversuccess.value);
		criteria.andServiceTimeGreaterThanOrEqualTo(serviceStartTime);
		criteria.andServiceTimeLessThanOrEqualTo(seviceEndTime);
		
		criteria.andServiceEndTimeLessThanOrEqualTo(seviceEndTime);
		criteria.andServiceEndTimeGreaterThanOrEqualTo(serviceStartTime);
		
		List<Integer> cancelStatus =new ArrayList<Integer>();
		cancelStatus.add(OrderStatus.CANCEL_CLOSE.value);
		cancelStatus.add(OrderStatus.CANCEL_NOSERVICE.value);
		cancelStatus.add(OrderStatus.CANCEL_SERVICEED.value);
		cancelStatus.add(OrderStatus.CANCELING.value);
		cancelStatus.add(OrderStatus.CANCLE_CLOSE_NOPAY.value);
		cancelStatus.add(OrderStatus.CANCLE_CLOSE_NOPAY.value);
		cancelStatus.add(OrderStatus.CANCLE_CLOSE_PAY_SERVICE.value);
		criteria.andOrderStatusNotIn(cancelStatus);
//		criteria.andOrderTypeEqualTo(OrderType.DAILY.value);
		return orderBeanMapper.selectByExample(orderBeanExample);
	}
	/**
	 * 
	 * @param guideId
	 * @param serviceStartTime
	 * @param seviceEndTime
	 * @return
	 */
	public List<OrderBean> getMayConfictGuideDailyOrders(String guideId,Date serviceStartTime,Date seviceEndTime){
		OrderBeanExample orderBeanExample = new OrderBeanExample();
		OrderBeanExample.Criteria criteria = orderBeanExample.createCriteria();
		criteria.andGuideIdEqualTo(guideId);
		setCompleteStatus(criteria);
		criteria.andDeliverStatusEqualTo(OrderDeliverStatus.deliversuccess.value);
		criteria.andServiceTimeGreaterThanOrEqualTo(serviceStartTime);
		criteria.andServiceEndTimeLessThanOrEqualTo(seviceEndTime);
		criteria.andOrderTypeEqualTo(OrderType.DAILY.value);
		List<Integer> cancelStatus =new ArrayList<Integer>();
		cancelStatus.add(OrderStatus.CANCEL_CLOSE.value);
		cancelStatus.add(OrderStatus.CANCEL_NOSERVICE.value);
		cancelStatus.add(OrderStatus.CANCEL_SERVICEED.value);
		cancelStatus.add(OrderStatus.CANCELING.value);
		cancelStatus.add(OrderStatus.CANCLE_CLOSE_NOPAY.value);
		cancelStatus.add(OrderStatus.CANCLE_CLOSE_NOPAY.value);
		cancelStatus.add(OrderStatus.CANCLE_CLOSE_PAY_SERVICE.value);
		criteria.andOrderStatusNotIn(cancelStatus);
		return orderBeanMapper.selectByExample(orderBeanExample);
	}
	
}
