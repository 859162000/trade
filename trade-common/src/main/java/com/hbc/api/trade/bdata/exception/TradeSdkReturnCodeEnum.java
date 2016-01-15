package com.hbc.api.trade.bdata.exception;

import com.hbc.api.trade.bdata.common.exception.ReturnCode;



public enum TradeSdkReturnCodeEnum implements ReturnCode{
	
	HTTPCLIENT_NOT_VALID(99001, "网络发送失败"),
	;
	
	private int code;
	private String message;

	private TradeSdkReturnCodeEnum(int code, String message){
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
