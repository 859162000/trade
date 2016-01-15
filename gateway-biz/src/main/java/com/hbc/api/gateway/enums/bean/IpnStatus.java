/**
 * @Author lukangle
 * @2015年10月16日@下午3:54:01
 */
package com.hbc.api.gateway.enums.bean;

import com.hbc.api.gateway.enums.GatewayReturnCodeEnum;
import com.hbc.api.gateway.exception.GatewayException;

public enum IpnStatus {
	SUCCESS(1, "成功"), 
	FAILED(2,"失败"), 
	PARTSUCCESS(3,"部分成功"),
	;
	
	public int value;
	public String name;
	IpnStatus(int value,String name){
		this.value = value;
		this.name = name;
	}
	
	public static IpnStatus getType(int value){
		IpnStatus[] orderKafkaOpts = IpnStatus.values();
		for(IpnStatus fundKafkaOpt : orderKafkaOpts){
			if(fundKafkaOpt.value==value){
				return fundKafkaOpt;
			}
		}
		
		throw new GatewayException(GatewayReturnCodeEnum.GATEWAY_IpnStatus_NOT_EXIST,value);
	}
}
