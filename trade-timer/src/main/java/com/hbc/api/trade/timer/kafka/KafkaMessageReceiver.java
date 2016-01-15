package com.hbc.api.trade.timer.kafka;

import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public abstract class KafkaMessageReceiver implements Runnable {
	private KafkaStream<byte[], byte[]> m_stream;
	
	
	private Logger log = LoggerFactory.getLogger(KafkaMessageReceiver.class);
	public void run() {
		log.info("@@@@ start to customer ");
		ConsumerIterator<byte[], byte[]> it = m_stream.iterator();

		while (it.hasNext()) {
			try {
				String message = new String(it.next().message());
				handleMessage(message);
				log.info(message);
			} catch (Exception e) {
				log.error("", e);
			}
		}
	}
	
	public KafkaStream<byte[], byte[]> getM_stream() {
		return m_stream;
	}

	public void setM_stream(KafkaStream<byte[], byte[]> m_stream) {
		this.m_stream = m_stream;
	}

	public abstract void handleMessage(String msg);
}
