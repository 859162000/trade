/**
 * @Author lukangle
 * @2015年11月15日@下午5:49:16
 */
package com.hbc.api.trade.timer.service.deliver.order.pk;

import java.util.List;

import com.hbc.api.trade.order.mapping.gen.bean.OrderBean;
import com.hbc.api.trade.order.mapping.gen.bean.TradeDeliverGuide;
import com.hbc.api.trade.order.service.deliver.conf.TradeCitySolrConf;

public interface IPkGuideService {
	public TradeDeliverGuide getMostFitGuide(OrderBean orderBean,List<TradeDeliverGuide> tradeDeliverGuides,TradeCitySolrConf tradeCitySolrConf);
	
	public List<TradeDeliverGuide> getAllFitGuides(String orderNo);
}
