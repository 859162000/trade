/**
 * @Author lukangle
 * @2015年11月11日@上午9:05:40
 */
package com.hbc.api.trade.timer.core;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.CloudSolrClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hbc.api.trade.bdata.common.util.TConfigLoader;
import com.hbc.api.trade.bdata.mapper.basedata.gen.bean.CityBean;
import com.hbc.api.trade.order.service.deliver.conf.ConfType;
import com.hbc.api.trade.order.service.deliver.conf.ConflictFlag;
import com.hbc.api.trade.order.service.deliver.conf.TradeCitySolrConf;
import com.hbc.api.trade.order.service.solr.SolrClientInstance;
import com.hbc.api.trade.third.LControllerService;
import com.hbc.api.trade.timer.util.LogBackInit;

public class SolrDataTest {
	public static void main(String[] args) throws SolrServerException, IOException {
		String baseConfigDir = "";
		if (args != null && args.length > 0) {
			baseConfigDir = args[0];
		} else {
			baseConfigDir = Class.class.getClass().getResource("/").getPath();
		}

		LogBackInit.initLogBack(baseConfigDir + File.separator + "/logback.xml");

		Logger log = LoggerFactory.getLogger(RedisQueueStarter.class);
		log.info("baseConfigDir:" + baseConfigDir + File.separator + "cfiles");
		TConfigLoader.loadProperties(baseConfigDir + File.separator + "cfiles");
		
		String springFileXml = "classpath:conf/trade-timer.xml";
		log.info(springFileXml);
		String pkftime = TConfigLoader.getProperty("pkftime");
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(springFileXml);
		LControllerService controllerService = applicationContext.getBean(LControllerService.class);
		List<CityBean>  citys = controllerService.getAllCitys();
		List<TradeCitySolrConf>  confs = new ArrayList<TradeCitySolrConf>();
		for(CityBean cityBean : citys){
			TradeCitySolrConf tradeCitySolrConf = new TradeCitySolrConf();
			tradeCitySolrConf.setCity_id(cityBean.getCityId());
			tradeCitySolrConf.setCity_name(cityBean.getCityName());
			tradeCitySolrConf.setConf_type(ConfType.DAILY.value);
			tradeCitySolrConf.setConflict_sing_airport(ConflictFlag.OPEN.value);
			tradeCitySolrConf.setConflict_time_gap(5*60);
			tradeCitySolrConf.setGuid_a(-7);
			tradeCitySolrConf.setGuid_b(-1);
			tradeCitySolrConf.setGuid_c(0);
			tradeCitySolrConf.setGuid_d(0);
			tradeCitySolrConf.setGuid_e(0);
			tradeCitySolrConf.setContinent_id(cityBean.getContinentId());
			tradeCitySolrConf.setCountry_id(cityBean.getCountryId());
			tradeCitySolrConf.setId(cityBean.getCityId()+""+ConfType.DAILY.value);
			confs.add(tradeCitySolrConf);
			
			TradeCitySolrConf tradeCitySolrConf2 = new TradeCitySolrConf();
			tradeCitySolrConf2.setCity_id(cityBean.getCityId());
			tradeCitySolrConf2.setCity_name(cityBean.getCityName());
			tradeCitySolrConf2.setConf_type(ConfType.PICKUPORDER.value);
			
			tradeCitySolrConf2.setConflict_sing_airport(ConflictFlag.OPEN.value);
			tradeCitySolrConf2.setConflict_time_gap(5*60);
			tradeCitySolrConf2.setGuid_a(-7);
			tradeCitySolrConf2.setGuid_b(-1);
			tradeCitySolrConf2.setGuid_c(0);
			tradeCitySolrConf2.setGuid_d(0);
			tradeCitySolrConf2.setGuid_e(0);
			tradeCitySolrConf2.setContinent_id(cityBean.getContinentId());
			tradeCitySolrConf2.setCountry_id(cityBean.getCountryId());
			tradeCitySolrConf2.setId(cityBean.getCityId()+""+ConfType.PICKUPORDER.value);
			confs.add(tradeCitySolrConf2);
			
		}
		String zkhost = "192.168.1.13:12181/solr";
		CloudSolrClient cloudSolrClient = new CloudSolrClient(zkhost);
		cloudSolrClient.setZkClientTimeout(10000);
		cloudSolrClient.setZkConnectTimeout(10000);
		
		cloudSolrClient.addBeans(TradeCitySolrConf.collectionName, confs);
		cloudSolrClient.commit(TradeCitySolrConf.collectionName);
	}

}
