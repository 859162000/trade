/**
 * @Author lukangle
 * @2015年10月16日@下午4:10:38
 */
package com.hbc.api.gateway.enums.bean;

public enum SendKafkaFlag {
	SEND_KAFKA_SUCCESS(1, "发送kafka成功"), 
	SEND_KAFKA_FAILED(2,"发送kafka失败"), 
	;
	
	public int value;
	public String name;
	SendKafkaFlag(int value,String name){
		this.value = value;
		this.name = name;
	}
	
	public static SendKafkaFlag getType(int value){
		SendKafkaFlag[] sendKafkaFlags = SendKafkaFlag.values();
		for(SendKafkaFlag sendKafkaFlag : sendKafkaFlags){
			if(sendKafkaFlag.value==value){
				return sendKafkaFlag;
			}
		}
		
		return null;
	}
}
