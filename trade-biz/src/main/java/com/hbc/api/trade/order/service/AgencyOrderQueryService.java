/**
 * @Author lukangle
 * @2015年10月8日@下午5:39:09
 */
package com.hbc.api.trade.order.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.hbc.api.trade.bdata.common.Page;
import com.hbc.api.trade.order.mapping.gen.OrderBeanMapper;
import com.hbc.api.trade.order.mapping.gen.bean.OrderBean;
import com.hbc.api.trade.order.mapping.gen.bean.OrderBeanExample;
import com.hbc.api.trade.order.mapping.gen.bean.OrderBeanExample.Criteria;
import com.hbc.api.trade.order.mapping.genx.xbean.AgencyOrderQueryBean;
import com.hbc.api.trade.third.AgencyGuideService;
@Component
public class AgencyOrderQueryService {
	private final static Logger logger = LoggerFactory.getLogger(AgencyOrderQueryService.class);
	
	@Autowired private OrderBeanMapper 		orderBeanMapper;
	@Autowired private AgencyGuideService 	agencyGuideService;
	
	public Map<String, Object> selectOrderForAgency(AgencyOrderQueryBean  queryBean) {
		logger.info("gBoss多条件查询参数：" + JSON.toJSONString(queryBean));
		OrderBeanExample example = new OrderBeanExample();
		Criteria criteria = example.createCriteria();
		List<String> guideIdList = agencyGuideService.selectGuideIdList(Integer.valueOf(queryBean.getAgencyId()));
		logger.info("gBoss多条件查询,guideId列表：" + JSON.toJSONString(guideIdList));
		criteria.andGuideIdIn(guideIdList);
		
		// Tab页查询
		if(queryBean.getOrderStatus() != null && queryBean.getOrderStatus().size() > 0) {
			criteria.andOrderStatusIn(queryBean.getOrderStatus());
		}
		
		// Tab页内部，除高级查询外的条件
		if(queryBean.getUserMobile() != null) {
			criteria.andUserMobile1EqualTo(queryBean.getUserMobile());
		}
		if(queryBean.getOrderType() != null) {
			criteria.andOrderTypeEqualTo(Integer.valueOf(queryBean.getOrderType()));
		}
		if(queryBean.getOrderNo() != null) {
			criteria.andOrderNoEqualTo(queryBean.getOrderNo());
		}
		
		// 高级查询
		if(queryBean.getServiceCityName() != null) {
			criteria.andServiceCityNameLike("%" +queryBean.getServiceCityName()+ "%");
		}
		if(queryBean.getUserName() != null) {
			criteria.andUserNameLike("%" +queryBean.getUserName()+ "%");
		}
		
		
		int totalSize = orderBeanMapper.countByExample(example);
		
		example.setOrderByClause("service_time");
		Page page = new Page(queryBean.getOffset(), queryBean.getLimit());
		example.setPage(page);
		List<OrderBean>  orderBeans = orderBeanMapper.selectByExample(example );
		
		// 拼装返回结果
		Map<String, Object> result = new HashMap<>(2);
		result.put("totalSize", totalSize);
		result.put("resultBean", orderBeans);
		logger.info("gBoss多条件查询结果：" + JSON.toJSONString(result));
		return result;
	}

	/**
	 * @param queryBean
	 * @return
	 */
	public int selectOrderForAgencyTotalSize(AgencyOrderQueryBean queryBean) {
		OrderBeanExample example = new OrderBeanExample();
		Criteria criteria = example.createCriteria();
		List<String> guideIdList = agencyGuideService.selectGuideIdList(Integer.valueOf(queryBean.getAgencyId()));
		criteria.andGuideIdIn(guideIdList);
		
		// Tab页查询
		if(queryBean.getOrderStatus() != null && queryBean.getOrderStatus().size() > 0) {
			criteria.andOrderStatusIn(queryBean.getOrderStatus());
		}
		
		// Tab页内部，除高级查询外的条件
		if(queryBean.getUserMobile() != null) {
			criteria.andUserMobile1EqualTo(queryBean.getUserMobile());
		}
		if(queryBean.getOrderType() != null) {
			criteria.andOrderTypeEqualTo(Integer.valueOf(queryBean.getOrderType()));
		}
		if(queryBean.getOrderNo() != null) {
			criteria.andOrderNoEqualTo(queryBean.getOrderNo());
		}
		
		// 高级查询
		if(queryBean.getServiceCityName() != null) {
			criteria.andServiceCityNameLike("%" +queryBean.getServiceCityName()+ "%");
		}
		if(queryBean.getUserName() != null) {
			criteria.andUserNameLike("%" +queryBean.getUserName()+ "%");
		}
		
		return orderBeanMapper.countByExample(example);
	}
}
