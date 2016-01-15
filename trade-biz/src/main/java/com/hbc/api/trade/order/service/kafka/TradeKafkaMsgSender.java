/**
 * @Author lukangle
 * @2015年10月7日@下午5:06:06
 */
package com.hbc.api.trade.order.service.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.hbc.api.trade.bdata.common.kafka.producer.KafkaProducerService;
import com.hbc.api.trade.order.enums.order.OrderKafkaOpt;
import com.hbc.api.trade.order.mapping.gen.bean.OrderBean;

@Component
public class TradeKafkaMsgSender {
	private final static Logger log = LoggerFactory.getLogger(TradeKafkaMsgSender.class);
	
	@Autowired KafkaProducerService kafkaProducerService;
	
	public void sendToKafka(OrderBean orderBean,OrderKafkaOpt orderKafkaOpt){
		try{
			kafkaProducerService.sendMsg(orderKafkaOpt.value+"\t"+JSON.toJSONString(orderBean), "ordermessage");
		}catch(Exception e){
			log.error("kafka异步消息失败", e);
			// ignore
		}
	}
}
