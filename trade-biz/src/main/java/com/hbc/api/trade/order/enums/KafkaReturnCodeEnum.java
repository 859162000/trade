
package com.hbc.api.trade.order.enums;

import com.hbc.api.trade.bdata.common.exception.ReturnCode;

/**
 * 
 * @author Jongly Ran
 */
public enum KafkaReturnCodeEnum implements ReturnCode{
	
	ORDER_SYCMSG_FAILED(80001, "订单异步消息失败"),
	ORDER_TRACK_SYCMSG_FAILED(80002, "订单动态异步消息失败");
	
	private int code;
	private String message;

	private KafkaReturnCodeEnum(Integer code, String message){
		this.code = code;
		this.message = message;
	};

	@Override
	public int getCode() {
		return this.code;
	}
	
	@Override
	public String getMessage() {
		return this.message;
	}

}
