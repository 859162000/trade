/**
 * @Author lukangle
 * @2015年11月28日@上午11:52:08
 */
package com.hbc.api.trade.order.service.gorder;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.hbc.api.trade.order.mapping.gen.bean.OrderBean;

@Component
public class OrderIdsService {
	public List<String> getOrderIdList(List<OrderBean> orderBeans){
		if(orderBeans != null) {
			List<String> oids = new ArrayList<String>();
			for(OrderBean orderBean : orderBeans){
				oids.add(orderBean.getOrderNo());
			}
			return oids;
		}
		return null;
	}
}
