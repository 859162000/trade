/**
 * @Author lukangle
 * @2015年11月29日@上午11:50:30
 */
package com.hbc.api.trade.order.service.mis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.hbc.api.trade.bdata.common.Page;
import com.hbc.api.trade.order.mapping.gen.TradeDeliverGuideMapper;
import com.hbc.api.trade.order.mapping.gen.bean.OrderBean;
import com.hbc.api.trade.order.mapping.gen.bean.TradeDeliverGuide;
import com.hbc.api.trade.order.mapping.gen.bean.TradeDeliverGuideExample;
import com.hbc.api.trade.order.mapping.gen.bean.TradeOrderDeliver;
import com.hbc.api.trade.order.mapping.genx.TradeDeliverGuideMapperEnhance;
import com.hbc.api.trade.order.mapping.genx.xbean.TradeDeliverGuideQueryBean;
import com.hbc.api.trade.order.mapping.genx.xbean.TradeDeliverGuideVoBean;

@Component
public class MisGuideDeliverService {

	@Autowired
	TradeDeliverGuideMapper tradeDeliverGuidMapper;

	@Autowired
	private TradeDeliverGuideMapperEnhance tradeDeliverGuideMapperEnhance;

	/**
	 * 获取已发导游
	 * 
	 * @param orderBean
	 * @param tradeOrderDeliver
	 * @return
	 */
	public List<TradeDeliverGuide> getGuideOrdersByDeliverNoPage(OrderBean orderBean, TradeOrderDeliver tradeOrderDeliver, String guideNo, String guideName, int limit, int offset) {
		TradeDeliverGuideExample tradeDeliverGuideExample = new TradeDeliverGuideExample();
		TradeDeliverGuideExample.Criteria criteria = tradeDeliverGuideExample.createCriteria();
		criteria.andDeliverNoEqualTo(tradeOrderDeliver.getDeliverNo());
		criteria.andOrderNoEqualTo(orderBean.getOrderNo());
		if (!StringUtils.isEmpty(guideName)) {
			criteria.andGuideNameLike(guideName);
		}
		if (!StringUtils.isEmpty(guideNo)) {
			criteria.andGuideNoLike(guideNo);
		}
		tradeDeliverGuideExample.setPage(new Page(offset, limit));

		return tradeDeliverGuidMapper.selectByExample(tradeDeliverGuideExample);
	}

	public List<TradeDeliverGuideVoBean> getGuideOrdersByDeliverTypeNoPage(OrderBean orderBean, Integer deliverType, String searchInfo, int limit, int offset) {
		TradeDeliverGuideQueryBean queryBean = new TradeDeliverGuideQueryBean();
		queryBean.setOrderNo(orderBean.getOrderNo());
		queryBean.setDeliverType(deliverType);
		queryBean.setTradeDeliverStatus(0); //发单状态 (0, "未发单"), (1,"已发送导游"),(2,"接单成功 pk成功"),(3,"接单成功并且消息已经发送"),
		queryBean.setTradeDeliverGuideStatus(1); //1:未发单 2:已发送给导游 3:接单成功4:接单成功并且消息已经发送5:pk失败 6:取消重发
		queryBean.setLimit(limit);
		queryBean.setOffset(offset);
		queryBean.setSearchInfo(searchInfo);
		return tradeDeliverGuideMapperEnhance.selectTradeDeliverGuidesByType(queryBean);
	}

	public int countGuideOrdersByDeliverNo(OrderBean orderBean, TradeOrderDeliver tradeOrderDeliver, String guideNo, String guideName) {
		TradeDeliverGuideExample tradeDeliverGuideExample = new TradeDeliverGuideExample();
		TradeDeliverGuideExample.Criteria criteria = tradeDeliverGuideExample.createCriteria();
		criteria.andDeliverNoEqualTo(tradeOrderDeliver.getDeliverNo());
		criteria.andOrderNoEqualTo(orderBean.getOrderNo());
		if (!StringUtils.isEmpty(guideName)) {
			criteria.andGuideNameLike(guideName);
		}
		if (!StringUtils.isEmpty(guideNo)) {
			criteria.andGuideNoLike(guideNo);
		}
		return tradeDeliverGuidMapper.countByExample(tradeDeliverGuideExample);
	}

	public int countGuideOrdersByDeliverTypeNo(OrderBean orderBean, Integer deliverType, String searchInfo) {
		TradeDeliverGuideQueryBean queryBean = new TradeDeliverGuideQueryBean();
		queryBean.setOrderNo(orderBean.getOrderNo());
		queryBean.setDeliverType(deliverType);
		queryBean.setSearchInfo(searchInfo);
		queryBean.setTradeDeliverStatus(0); //发单状态 (0, "未发单"), (1,"已发送导游"),(2,"接单成功 pk成功"),(3,"接单成功并且消息已经发送"),
		queryBean.setTradeDeliverGuideStatus(1); //1:未发单 2:已发送给导游 3:接单成功4:接单成功并且消息已经发送5:pk失败 6:取消重发
		return tradeDeliverGuideMapperEnhance.countTradeDeliverGuidesByType(queryBean);
	}
}
