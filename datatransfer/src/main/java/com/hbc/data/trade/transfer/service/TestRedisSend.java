package com.hbc.data.trade.transfer.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.hbc.api.trade.TradeFinalStr;
import com.hbc.api.trade.bdata.common.Page;
import com.hbc.api.trade.bdata.common.redis.RedisDao;
import com.hbc.api.trade.bdata.mapper.guide.gen.GuideBeanMapper;
import com.hbc.api.trade.bdata.mapper.guide.gen.bean.GuideBean;
import com.hbc.api.trade.order.mapping.gen.OrderBeanMapper;
import com.hbc.api.trade.order.mapping.gen.bean.OrderBean;
import com.hbc.api.trade.order.mapping.gen.bean.OrderBeanExample;
import com.hbc.api.trade.order.service.OrderQueryService;
@Component
public class TestRedisSend {
	Logger log = Logger.getLogger(TestRedisSend.class);
	@Autowired
	RedisDao redisDao;
	@Autowired
	OrderQueryService orderQueryService;
	// @Autowired
	// StandardOrderService otaOrderServiceImpl;
	@Autowired
	GuideBeanMapper guideBeanMapper;

	public void start(String orderNo, String guideNo, String ste) throws InterruptedException {
		// S110972333061'
		OrderBean orderBean = orderQueryService.getOrderByNo(orderNo);

		GuideBean guideBean = guideBeanMapper.selectByPrimaryKey(guideNo);
		if ("1".equals(ste)) {
			orderBean.setGuideId(guideNo);

			redisDao.lpush(TradeFinalStr.guideAssignQname, JSON.toJSONString(orderBean));
		} else {
			orderBean.setGuideId(guideBean.getGuideId());
			orderBean.setGuideMobile(guideBean.getMobile());
			orderBean.setGuideAreaCode(guideBean.getAreaCode());
			// otaOrderServiceImpl.settleOrderBean(orderBean);
		}

	}
	
	public void testLog() throws InterruptedException {
		log.info("info info  info  ");
		log.error("error error  error  ");
	}
	@Autowired private OrderBeanMapper 			orderBeanMapper;
	public void pushGuide() throws InterruptedException {
		// S110972333061'
		List<String> olist = new ArrayList<>();
		
//		olist.add("S110972435163");
		
		olist.add("S15122409123");
		olist.add("J15122501445");
		olist.add("J15122404376");
		olist.add("J15122404039");
//		OrderBeanExample orderBeanExample = new OrderBeanExample();
//		orderBeanExample.setPage(new Page(0, 10));
//		List<OrderBean> s = orderBeanMapper.selectByExample(orderBeanExample);
//		System.out.println(JSON.toJSONString(s));
		for(String torderNo : olist){
			System.out.println(torderNo);
			OrderBean orderBean = orderBeanMapper.selectByPrimaryKey(torderNo);
			if(orderBean!=null && !TradeFinalStr.defaultGuideId.equals(orderBean.getGuideId())){
				redisDao.lpush(TradeFinalStr.guideAssignQname, JSON.toJSONString(orderBean));
				System.out.println(orderBean.getOrderNo()+"确认导游"+orderBean.getGuideId());
				log.info(orderBean.getOrderNo()+"确认导游"+orderBean.getGuideId());
			}else{
				System.out.println(torderNo+"唔该数据");
			}
		}


	}
}
