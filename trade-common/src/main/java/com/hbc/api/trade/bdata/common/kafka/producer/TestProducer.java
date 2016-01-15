package com.hbc.api.trade.bdata.common.kafka.producer;

import java.util.Properties;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

import org.apache.log4j.PropertyConfigurator;


public class TestProducer {
    public static void main(String[] args) {
    	PropertyConfigurator.configure("D:\\dev\\wd\\search-platform\\search-cquery\\src\\main\\resources\\log4j.properties");  
    	Properties props = new Properties();
        props.put("metadata.broker.list", "192.168.1.7:9092");
        props.put("serializer.class", "kafka.serializer.StringEncoder");
        props.put("partitioner.class", "com.hbc.api.trade.bdata.common.kafka.producer.SimplePartitioner");
        props.put("request.required.acks", "1");
 
        ProducerConfig config = new ProducerConfig(props);
 
        Producer<String, String> producer = new Producer<String, String>(config);
 
        KeyedMessage<String, String> data = new KeyedMessage<String, String>("message", "2","v2fffffff");
        producer.send(data);
        producer.close();
    }
}