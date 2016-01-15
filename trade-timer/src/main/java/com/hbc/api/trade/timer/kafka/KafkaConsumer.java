package com.hbc.api.trade.timer.kafka;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import kafka.consumer.Consumer;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class KafkaConsumer implements Runnable {
	private String zkhost;
	private String groupId;
	private String topic;
	private KafkaMessageReceiver receiver;
	private Logger log = LoggerFactory.getLogger(KafkaConsumer.class);
	public KafkaConsumer(String zkhost, String groupId, String topic,KafkaMessageReceiver kafkaMessageReceiver) {
		this.zkhost = zkhost;
		this.groupId = groupId;
		this.topic = topic;
		this.receiver = kafkaMessageReceiver;
		executor = Executors.newFixedThreadPool(1);
	}

	private ConsumerConnector consumer;
	private ExecutorService executor;

	public void shutdown() {
		if (consumer != null)
			consumer.shutdown();
		if (executor != null)
			executor.shutdown();
	}


	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		log.info("start to zkhost ["+zkhost+"] topic ["+topic+"] ");
		consumer = Consumer.createJavaConsumerConnector(KafkaZkUtil.createConsumerConfig(false, zkhost, groupId));

		Map<String, Integer> topicCountMap = new HashMap<String, Integer>();
		topicCountMap.put(topic, new Integer(1));
		Map<String, List<KafkaStream<byte[], byte[]>>> consumerMap = consumer.createMessageStreams(topicCountMap);
		List<KafkaStream<byte[], byte[]>> streams = consumerMap.get(topic);

		for (KafkaStream<byte[], byte[]> stream : streams) {
			receiver.setM_stream(stream);
			executor.execute(receiver);
		}
	}

	
}
