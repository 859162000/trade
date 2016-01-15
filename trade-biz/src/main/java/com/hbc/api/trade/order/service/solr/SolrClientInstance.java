package com.hbc.api.trade.order.service.solr;

import org.apache.solr.client.solrj.impl.CloudSolrClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SolrClientInstance {
	private final static Logger log = LoggerFactory.getLogger(SolrClientInstance.class);
	public String zkhost;
	private int zktimeout;
	private CloudSolrClient cloudSolrClient;

	public SolrClientInstance(String zkhost, int zktimeout) {
		this.zkhost = zkhost;
		this.zktimeout = zktimeout;
		initSolrClient();
	}

	private void initSolrClient() {
		cloudSolrClient = new CloudSolrClient(zkhost);
		cloudSolrClient.setZkClientTimeout(zktimeout);
		cloudSolrClient.setZkConnectTimeout(zktimeout);
		cloudSolrClient.connect();
	}

	public CloudSolrClient getSolrClient() {
		return cloudSolrClient;
	}
}
