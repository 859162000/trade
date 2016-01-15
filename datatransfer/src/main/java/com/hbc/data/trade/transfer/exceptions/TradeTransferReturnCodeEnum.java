package com.hbc.data.trade.transfer.exceptions;

import com.hbc.api.trade.bdata.common.exception.ReturnCode;


public enum TradeTransferReturnCodeEnum implements ReturnCode{
	
	ORDER_ID_NOT_FOUND(100001, "订单 %S 状态转换错误"),
	FORDER_CLIENT_TYPE_NOTFOUND(100002,"老订单[%S] client type不对"),
	FORDER_muti_refund(100003,"老订单[%S] 多比退款信息"),
	;
	private int code;
	private String message;

	private TradeTransferReturnCodeEnum(int code, String message){
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
