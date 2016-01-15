package com.hbc.api.trade.bdata.common.kafka.producer;

import java.util.Properties;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;
public class KafkaProducerService {
	private String brokenListStr;
	public KafkaProducerService(String brokenListStr){
		this.brokenListStr = brokenListStr;
	}
	
	public void sendMsg(String msg,String topic){
//		Properties props = new Properties();
//        props.put("metadata.broker.list", brokenListStr);//"192.168.1.226:9092"
//        props.put("serializer.class", "kafka.serializer.StringEncoder");
////        props.put("partitioner.class", "com.hbc.api.trade.common.kafka.producer.SimplePartitioner");
//        props.put("request.required.acks", "1");
// 
//        ProducerConfig config = new ProducerConfig(props);
// 
//        Producer<String, String> producer = new Producer<String, String>(config);
// 
//        KeyedMessage<String, String> data = new KeyedMessage<String, String>(topic, msg);
//        producer.send(data);
//        producer.close();
	}
}
