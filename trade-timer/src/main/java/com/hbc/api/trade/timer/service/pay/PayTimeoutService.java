/**
 * @Author lukangle
 * @2015年12月11日@下午11:17:14
 */
package com.hbc.api.trade.timer.service.pay;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.hbc.api.trade.bdata.mapper.basedata.gen.bean.CityBean;
import com.hbc.api.trade.order.enums.order.OrderStatus;
import com.hbc.api.trade.order.mapping.gen.OrderBeanMapper;
import com.hbc.api.trade.order.mapping.gen.bean.OrderBean;
import com.hbc.api.trade.order.mapping.gen.bean.OrderBeanExample;
import com.hbc.api.trade.order.service.OrderServiceTime;
import com.hbc.api.trade.pay.service.PayTimeService;
import com.hbc.api.trade.third.LControllerService;

@Component
public class PayTimeoutService {
	private static final Logger logger = LoggerFactory.getLogger(PayTimeoutService.class);
	@Autowired 
	private OrderBeanMapper 	orderBeanMapper;
	@Autowired
	PayTimeService payTimeService;
	
	@Autowired
	OrderServiceTime orderServiceTime;
	
	@Autowired
	LControllerService controllerService;
	public List<OrderBean> getAllNoPayOrders(){
		OrderBeanExample orderBeanExample = new OrderBeanExample();
		OrderBeanExample.Criteria criteria = orderBeanExample.createCriteria();
		criteria.andOrderStatusEqualTo(OrderStatus.INITSTATE.value);
		return orderBeanMapper.selectByExample(orderBeanExample);
	}
	
	@Transactional
	public void payOutCancle(OrderBean orderBean){
		try {
			CityBean cityBean = controllerService.getCityById(orderBean.getServiceCityId());
			Date date = orderServiceTime.getFormatedDateString(cityBean.getTimezone().intValue());
			Date endDate = payTimeService.getPayEndDate(orderBean);
			
			if(date.after(endDate)){
				
				OrderBean orderBeanDb = new OrderBean();
				orderBeanDb.setOrderStatus(OrderStatus.CANCLE_CLOSE_NOPAY.value);
				orderBeanDb.setOrderStatusName(OrderStatus.CANCLE_CLOSE_NOPAY.name);
				OrderBeanExample orderBeanExample = new OrderBeanExample();
				OrderBeanExample.Criteria criteria = orderBeanExample.createCriteria();
				criteria.andOrderStatusEqualTo(OrderStatus.INITSTATE.value);
				criteria.andOrderNoEqualTo(orderBean.getOrderNo());
				int optnum = orderBeanMapper.updateByExampleSelective(orderBeanDb, orderBeanExample);
				
				if(optnum!=1){
					logger.error(orderBean.getOrderNo()+" 取消订单失败");
				}else{
					logger.info(orderBean.getOrderNo()+" 取消订单成功");
				}
			}
		} catch (Exception e) {
			logger.error("", e);
		}
	}
}
