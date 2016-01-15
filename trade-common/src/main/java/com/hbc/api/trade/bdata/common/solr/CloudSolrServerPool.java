package com.hbc.api.trade.bdata.common.solr;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.pool2.ObjectPool;
import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.apache.solr.client.solrj.impl.CloudSolrClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 
 * @author lkl
 *
 */
public class CloudSolrServerPool {

	Logger log = LoggerFactory.getLogger(CloudSolrServerPool.class);
	@Value("${trade.solr.zkhost}")
	String zkHost;
	@Value("${trade.solr.zkClientTimeout}")
	int zkTimeout;
	@Value("${trade.solr.zkConnectTimeout}")
	int zkConnTimeout;
	@Value("${trade.solr.solrpoonum}")
	int solrpoonum;
	@Value("${trade.solr.minidle}")
	int minidle;
	@Value("${trade.solr.maxConnections}")
	int maxConnections;
	@Value("${trade.solr.socketTimeout}")
	int socketTimeout;
	@Value("${trade.solr.connTimeout}")
	int connTimeout;
	@Value("${trade.solr.maxConnectionsPerHost}")
	int maxConnectionsPerHost;
	@Value("${trade.solr.retry}")
	int retry;
	@Value("${trade.solr.grows}")
	int grows;
	@Value("${trade.solr.cnames}")
	String cnames;
	@Value("${trade.solr.pool.maxwaitTimeout}")
	int maxwaitTimeout;

	private Map<String, ObjectPool<CloudSolrClient>> poolMap = new HashMap<>();

	public CloudSolrServerPool() throws Exception {
		initSolrServers();
	}

	private void initSolrServers() throws Exception {

		log.info("start to init solrserver pool solrpoonum [" + solrpoonum + "]");

//		GenericObjectPoolConfig config = new GenericObjectPoolConfig();
//
//		config.setMaxIdle(solrpoonum);
//		config.setMinIdle(minidle);
//		config.setMaxWaitMillis(maxwaitTimeout);
//
//		String[] collectionNames = cnames.split(",");
//		log.info("put " + cnames + " to pool");
//
//		poolMap = new HashMap<>();
//		for (String coname : collectionNames) {
//			PooledObjectFactory<CloudSolrClient> factory = new SolrServerFactory(coname);
//			ObjectPool<CloudSolrClient> solrserverpool = new GenericObjectPool<CloudSolrClient>(factory, config);
//			poolMap.put(coname, solrserverpool);
//			log.info("end to init solrserver pool solrpoonum [" + solrpoonum + "] zkhost is [" + zkHost + "] collection name is [" + coname + "]");
//		}

	}

	public ObjectPool<CloudSolrClient> getCloudServerPool(String collectionName) {
		ObjectPool<CloudSolrClient> solrserverpool = poolMap.get(collectionName);
		if (solrserverpool == null) {
			GenericObjectPoolConfig config = new GenericObjectPoolConfig();
			config.setMaxIdle(solrpoonum);
			config.setMinIdle(minidle);
			config.setMaxWaitMillis(connTimeout);
			config.setMaxTotal(solrpoonum);
			
			
			PooledObjectFactory<CloudSolrClient> factory = new SolrServerFactory(collectionName, socketTimeout, connTimeout, zkHost, zkTimeout, zkConnTimeout);
			solrserverpool = new GenericObjectPool<CloudSolrClient>(factory, config);
			log.info("create " + collectionName + " pool");
		}
		return solrserverpool;
	}

}
