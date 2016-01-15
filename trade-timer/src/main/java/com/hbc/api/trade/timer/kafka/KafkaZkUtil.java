package com.hbc.api.trade.timer.kafka;

import java.util.Properties;

import kafka.consumer.ConsumerConfig;
import kafka.utils.ZkUtils;

public class KafkaZkUtil {
	public static ConsumerConfig createConsumerConfig(boolean isRestart,String a_zookeeper, String a_groupId) {
		Properties props = new Properties();

		props.put("zookeeper.connect", a_zookeeper);
		props.put("group.id", a_groupId);
		props.put("zookeeper.session.timeout.ms", ""+10000);
		props.put("zookeeper.sync.time.ms", ""+10000);
		props.put("auto.commit.interval.ms", "1000");
		if(isRestart){
			props.put("auto.offset.reset", "smallest");
			ZkUtils.maybeDeletePath(props.getProperty("zookeeper.connect"), "/consumers/" + props.getProperty("group.id"));
		}
		
		return new ConsumerConfig(props);
	}
}
