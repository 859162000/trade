/**
 * @Author lukangle
 * @2015年11月27日@下午12:04:25
 */
package com.hbc.api.trade.third;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.hbc.api.trade.order.mapping.gen.bean.OrderBean;
@Component
public class TestPayVal {
	private static Logger logger = LoggerFactory.getLogger(TestPayVal.class);
	
	@Value("${trade.pay.env}")
	private String env;
	public boolean isTestByBeijing(OrderBean orderBean){
		if(env.equals("test")){
			logger.warn(orderBean.getOrderNo()+" 是测试订单");
			return true;
		}else{
			return false;
		}
	}
}
