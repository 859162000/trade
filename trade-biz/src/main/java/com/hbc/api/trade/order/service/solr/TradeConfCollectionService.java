package com.hbc.api.trade.order.service.solr;

import java.util.List;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;

import com.hbc.api.trade.bdata.common.cache.CacheFinal;
import com.hbc.api.trade.order.enums.TradeReturnCodeEnum;
import com.hbc.api.trade.order.enums.order.OrderType;
import com.hbc.api.trade.order.exception.TradeException;
import com.hbc.api.trade.order.mapping.gen.bean.OrderBean;
import com.hbc.api.trade.order.service.deliver.conf.ConfType;
import com.hbc.api.trade.order.service.deliver.conf.TradeCitySolrConf;
public class TradeConfCollectionService {
	private final static Logger log = LoggerFactory.getLogger(TradeConfCollectionService.class);
	@Autowired
	SolrClientInstance solrClientInstance;
	@Cacheable(value=CacheFinal.confCache,key="#root.targetClass+#root.methodName"+"+#orderBean.getOrderNo()")
	public TradeCitySolrConf queryCityConf(OrderBean orderBean){
		OrderType orderType = OrderType.getType(orderBean.getOrderType());
		SolrClient solrClient = null;
		try {
			solrClient = solrClientInstance.getSolrClient();
			SolrQuery solrQuery = new SolrQuery();
			solrQuery.setQuery("*:*");
			if(OrderType.DAILY.equals(orderType)){
				solrQuery.setFilterQueries(new String[]{"city_id:"+orderBean.getServiceCityId(),"conf_type:"+ConfType.DAILY.value});
			}else{
				solrQuery.setFilterQueries(new String[]{"city_id:"+orderBean.getServiceCityId(),"conf_type:"+ConfType.PICKUPORDER.value});
			}
			QueryResponse queryResponse = solrClient.query(TradeCitySolrConf.collectionName, solrQuery);
			List<TradeCitySolrConf> tradeConfs = queryResponse.getBeans(TradeCitySolrConf.class);
			if(tradeConfs.size()>=1){
				return tradeConfs.get(0);
			}else{
				return null;
			}
		} catch (Exception e) {
			log.error("", e);
			throw new TradeException(TradeReturnCodeEnum.TRADE_SOLR_QUERYEXP,e);
		}
	}
}
