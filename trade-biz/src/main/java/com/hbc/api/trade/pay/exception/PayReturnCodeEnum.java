package com.hbc.api.trade.pay.exception;

import com.hbc.api.trade.bdata.common.exception.ReturnCode;


public enum PayReturnCodeEnum implements ReturnCode{
	
	PAY_PARM_NOT_VALID(91001, "获取支付地址参数错误"),
	PAY_PRICECHANNEL_CHANGED(91002,"%S 订单付款价格已变更，请刷新页面"),
	PAY_NOT_EXIST(91003,"%S 支付信息不存在"),
	PAY_DOUBLE(91004,"%S 订单重复付款"),
	PAY_BIGGER_PRICE(91005,"%S 订单实付金额异常"),
	PAY_COUP_FAILED(91007,"%S 券信息异常"),
	PAY_COUP_BANGDCH_FAILED(91009,"%S 券已改配其他用户"),
	PAY_INSERT_FAILED(91006, "%S DB插入信息错误"),
	REFUND_FAILED(92006,"%S 订单退款失败"),
	REFUND_NOTIFY_FAILED(92007,"%S 订单退款 支付宝通知失败 "),
	REFUND_UPDATEDB_FAILED(92008,"%S 更新DB异常"),
	REFUND_ORDERSTATUS_EXCEPTION(92009,"%S 该笔订单对应订单状态异常"),
	
	PAYSUCCESS_NOPAY_CHANGED(91010,"%S 订单已经支付 请勿重复支付"),
	;
	
	private int code;
	private String message;

	private PayReturnCodeEnum(int code, String message){
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
