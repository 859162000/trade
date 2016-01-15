package com.hbc.api.trade.bdata.common.exception.enums;

import com.hbc.api.trade.bdata.common.exception.ReturnCode;

public enum CommonReturnCodeEnum implements ReturnCode {

	PARAM_ERROR(101, "参数错误 "), 
	PARAM_ERROR_WITHARG(103, "参数错误, %s"), 
	PARAM_RESOLVER_ERROR(102, "参数错误解码错误/非法请求");

	private int code;
	private String message;

	private CommonReturnCodeEnum(int code, String message) {
		this.code = code;
		this.message = message;
	};

	@Override
	public int getCode() {
		return code;
	}

	@Override
	public String getMessage() {
		return message;
	}

}
