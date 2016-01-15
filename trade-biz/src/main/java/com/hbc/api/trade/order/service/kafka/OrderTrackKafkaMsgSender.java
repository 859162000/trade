
/*
 * Copyright (c) 2015-2016, CCLX.COM. All rights reserved.
 * WANDA GROUP PROPRIETARY/CONFIDENTIAL. 
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is private property; you can't redistribute it and/or modify it
 * under the terms of the LICENSE you obtained from
 *
 *    http://www.cclx.com/
 * 
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. 
 *
 * Author: Jongly Ran
 * Revision: 1.0
 */
package com.hbc.api.trade.order.service.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.hbc.api.trade.bdata.common.kafka.producer.KafkaProducerService;
import com.hbc.api.trade.order.enums.order.KafkaOperation;
import com.hbc.api.trade.order.mapping.gen.bean.TradeOrderTrack;

/**
 * 
 * @author Jongly Ran
 */
@Service
public class OrderTrackKafkaMsgSender {
	private final static Logger log = LoggerFactory.getLogger(OrderTrackKafkaMsgSender.class);
	
	@Autowired KafkaProducerService kafkaProducerService;
	
	public void sendToKafka(TradeOrderTrack orderTrack, KafkaOperation kafkaOperation){
		try{
			kafkaProducerService.sendMsg(kafkaOperation.value+"\t"+JSON.toJSONString(orderTrack), "TradeOrderTrack message");
		}catch(Exception e){
			log.error("kafka异步消息失败", e);
			// ignore
		}
	}
}
