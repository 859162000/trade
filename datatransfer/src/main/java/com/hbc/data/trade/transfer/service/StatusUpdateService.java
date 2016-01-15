/**
 * @Author lukangle
 * @2015年12月28日@下午10:26:09
 */
package com.hbc.data.trade.transfer.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hbc.api.trade.order.enums.order.OrderStatus;
import com.hbc.api.trade.order.mapping.gen.OrderBeanMapper;
import com.hbc.api.trade.order.mapping.gen.bean.OrderBean;
import com.hbc.data.trade.transfer.mapping.hbcfinal.gen.FinalOrderBeanMapper;
import com.hbc.data.trade.transfer.mapping.hbcfinal.gen.bean.FinalOrderBean;
import com.hbc.data.trade.transfer.mapping.hbcfinal.gen.bean.FinalOrderBeanCriteria;

@Component
public class StatusUpdateService {
	private final static Logger log = LoggerFactory.getLogger(StatusUpdateService.class);
	@Autowired
	OrderBeanMapper orderBeanMapper;
	@Autowired
	FinalOrderBeanMapper finalOrderBeanMapper;
	public void update() {
		FinalOrderBeanCriteria finalOrderBeanCriteria = new FinalOrderBeanCriteria();
		finalOrderBeanCriteria.createCriteria().andStatusEqualTo(11);
		List<FinalOrderBean> flist = finalOrderBeanMapper.selectByExample(finalOrderBeanCriteria);
		
		for(FinalOrderBean finalOrderBean : flist){
			String newOrderSN = "";
			if (finalOrderBean.getOrderid().length() == 18) {
				newOrderSN = finalOrderBean.getOrderid().substring(15, 16);
			}
			String orderNo=	finalOrderBean.getOrdersn()+newOrderSN;
			
			OrderBean orderBean =  orderBeanMapper.selectByPrimaryKey(orderNo);
			if(orderBean==null){
				System.out.println(orderNo+"无对应的 订单");
				continue;
			}
			OrderStatus orderStatus = OrderStatus.getStatus(orderBean.getOrderStatus());
			if(OrderStatus.STROKE_END.equals(orderStatus)){
				OrderBean record = new OrderBean();
				record.setOrderNo(orderBean.getOrderNo());
				record.setOrderStatus(OrderStatus.SETTLEMENT.value);
				orderBeanMapper.updateByPrimaryKeySelective(record);
				
				System.out.println(orderBean.getOrderNo()+"已经由 【"+orderBean.getOrderStatus()+"】变更成【"+OrderStatus.SETTLEMENT.value+"】");
				log.info(orderBean.getOrderNo()+"已经由 【"+orderBean.getOrderStatus()+"】变更成【"+OrderStatus.SETTLEMENT.value+"】");
			}else{
				System.out.println(orderBean.getOrderNo()+"已经由 被导游 【"+orderBean.getGuideId()+"】提现【"+orderBean.getPriceGuide()+"】");
			}
		}
	}
}
