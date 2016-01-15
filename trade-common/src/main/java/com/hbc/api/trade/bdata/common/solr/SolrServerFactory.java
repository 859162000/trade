package com.hbc.api.trade.bdata.common.solr;

import java.io.IOException;

import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.apache.log4j.Logger;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.CloudSolrClient;
import org.apache.solr.client.solrj.impl.HttpClientUtil;
import org.apache.solr.client.solrj.response.SolrPingResponse;
import org.apache.solr.common.params.ModifiableSolrParams;

import com.alibaba.fastjson.JSON;

public class SolrServerFactory implements PooledObjectFactory<CloudSolrClient> {
	private Logger log = Logger.getLogger(SolrServerFactory.class);

	private String collectionName;

	private int socketTimeout;
	private int connTimeout;
	private String zkHost;
	private int zkTimeout;
	private int zkConnTimeout;

	public SolrServerFactory(String collectionName, int socketTimeout, int connTimeout, String zkHost, int zkTimeout, int zkConnTimeout) {
		this.collectionName = collectionName;
		this.socketTimeout = socketTimeout;
		this.connTimeout = connTimeout;
		this.zkHost = zkHost;
		this.zkTimeout = zkTimeout;
		this.zkConnTimeout = zkConnTimeout;
	}

	@Override
	public PooledObject<CloudSolrClient> makeObject() throws Exception {
		CloudSolrClient csolrserver = getNewCloudSolrServer(collectionName);
		PooledObject<CloudSolrClient> pobj = new DefaultPooledObject<CloudSolrClient>(csolrserver);
		return pobj;
	}

	@Override
	public void destroyObject(PooledObject<CloudSolrClient> p) throws Exception {
		p = null;
	}

	@Override
	public boolean validateObject(PooledObject<CloudSolrClient> p) {
		CloudSolrClient cserver = p.getObject();
		try {
			SolrPingResponse rresose = cserver.ping();
			int rstate = rresose.getStatus();
			if (rstate == 0) {
				return true;
			} else {
				log.error("cloudsolrserver :[" + JSON.toJSONString(cserver) + "] is not alive will remove it ");
			}
		} catch (SolrServerException e) {
			log.error("", e);
			return false;
		} catch (IOException e) {
			log.error("", e);
			return false;
		}
		return true;
	}

	@Override
	public void activateObject(PooledObject<CloudSolrClient> p) throws Exception {

	}

	@Override
	public void passivateObject(PooledObject<CloudSolrClient> p) throws Exception {

	}

	private CloudSolrClient getNewCloudSolrServer(String collection) {
		ModifiableSolrParams params = new ModifiableSolrParams();
		params.set(HttpClientUtil.PROP_SO_TIMEOUT, socketTimeout);
		params.set(HttpClientUtil.PROP_CONNECTION_TIMEOUT, connTimeout);
		params.set(HttpClientUtil.PROP_MAX_CONNECTIONS, Integer.MAX_VALUE);
		params.set(HttpClientUtil.PROP_MAX_CONNECTIONS_PER_HOST, Integer.MAX_VALUE);

		// params.set(HttpClientUtil.PROP_ALLOW_COMPRESSION,
		// TConfigLoader.getProperty(HttpClientUtil.PROP_ALLOW_COMPRESSION));

		CloudSolrClient cloudSolrClient = new CloudSolrClient(zkHost);

		cloudSolrClient.setZkClientTimeout(zkTimeout);
		cloudSolrClient.setZkConnectTimeout(zkConnTimeout);
		if (collection != null) {
			cloudSolrClient.setDefaultCollection(collection);
		}

		cloudSolrClient.connect();

		return cloudSolrClient;
	}

}
