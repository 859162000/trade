/**
 * @Author lukangle
 * @2015年12月27日@下午6:36:02
 */
package com.hbc.data.trade.transfer.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hbc.api.trade.bdata.mapper.basedata.gen.UserFundAccountMapper;
import com.hbc.api.trade.bdata.mapper.basedata.gen.bean.UserFundAccount;
import com.hbc.api.trade.bdata.mapper.basedata.gen.bean.UserFundAccountExample;
import com.hbc.api.trade.order.mapping.gen.OrderBeanMapper;
import com.hbc.api.trade.order.mapping.gen.bean.OrderBean;
import com.hbc.api.trade.order.mapping.gen.bean.OrderBeanExample;

@Component
public class OrderFundAccountEdit {
	Logger log = Logger.getLogger(OrderFundAccountEdit.class);
	@Autowired
	OrderBeanMapper orderBeanMapper;
	@Autowired
	UserFundAccountMapper userFundAccountMapper;
	public void dd(){
		OrderBeanExample orderBeanExample = new OrderBeanExample();
		List<Integer> ilist = new ArrayList<>();
		ilist.add(2001);
		ilist.add(2030);
		ilist.add(2050);
		ilist.add(2070);
		ilist.add(2080);
		orderBeanExample.createCriteria().andUserAccountIsNull().andOrderStatusIn(ilist);
		List<OrderBean> olist = orderBeanMapper.selectByExample(orderBeanExample);
		
		for(OrderBean orderBean : olist){
			UserFundAccountExample userFundAccountExample = new UserFundAccountExample();
			userFundAccountExample.createCriteria().andUserIdEqualTo(orderBean.getUserId());
			List<UserFundAccount> alist = userFundAccountMapper.selectByExample(userFundAccountExample);
			
			if(alist.size()==1){
				log.info("【"+orderBean.getOrderNo()+"】更新资金账户 【"+alist.get(0).getAccountNo()+"】");
				OrderBean record = new OrderBean();
				record.setOrderNo(orderBean.getOrderNo());;
				record.setUserAccount(alist.get(0).getAccountNo());
				orderBeanMapper.updateByPrimaryKeySelective(record);
			}
		}
	}
}
