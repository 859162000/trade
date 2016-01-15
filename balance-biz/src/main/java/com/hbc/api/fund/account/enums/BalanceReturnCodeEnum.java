package com.hbc.api.fund.account.enums;

import com.hbc.api.trade.bdata.common.exception.ReturnCode;



public enum BalanceReturnCodeEnum implements ReturnCode{
	
	ACCOUNT_FROZEN(89001, "%S 帐号已冻结"),
	ACCOUNT_NOT_EXSIT(89002, "%S 帐号不存在"),
	BIZ_NOT_EXSIT(89003, "%S 不支持的业务类型"),
	ACCOUNT_MONEY_NOTE(89004, "%S 帐号余额不足"),

	ACCOUNT_ENABLE_FAILED(89005, "账号启用失败"),
	ACCOUNT_DISABLE_FAILED(89006, "账号冻结失败"),

	ACCOUNT_ENABLE_SUCCESS(89007, "账号启用成功"),
	ACCOUNT_DISABLE_SUCCESS(89008, "账号冻结成功"),
	WITHDRAW_CANCELED_FAILED(89009, "取消提现申请"),
	ACCOUNT_SYSTEM_ERROR(89010, "系统异常"),
	ACCOUNT_ABNORMAL(89011, "账号状态异常"),
	WITHDRAW_NONE_AUTO_TRANSFER_FAILED(89011, "转账申请"),
	WITHDRAW_DENY_FROZEN_AMOUNT_ABNORMAL(89012, "取消提现金额大于冻结金额"),
	WITHDRAW_FAILED(89013, "申请提现金额大于可提现金额"),
	WITHDRAW_CONFIRM_FROZEN_AMOUNT_ABNORMAL(89014, "提现金额大于冻结金额"),
	WITHDRAW_DENY_DUPLICATE_SUBMITED(89015, "%S 取消提现重复提交"),
	ACCOUNT_ILLEGAL_AMOUNT_SUBMITED(89016, "入参金额不能小于0"),

	;
	
	private int code;
	private String message;

	private BalanceReturnCodeEnum(int code, String message){
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
