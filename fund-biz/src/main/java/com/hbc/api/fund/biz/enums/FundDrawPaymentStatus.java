package com.hbc.api.fund.biz.enums;

import com.hbc.api.fund.biz.exception.FundException;

/**
 * @author Luoshuai
 */
public enum FundDrawPaymentStatus {

	//0：初始 | 1：已提交 | 2：支付成功 | 3：支付失败

	PAYMENT_INIT(0, "初始状态"),
	PAYMENT_SUBMITTED(1, "已提交"),
	PAYMENT_SUCCESS(2, "支付成功"),
	PAYMENT_FAILED(3, "支付失败");

	public int value;
	public String name;

	FundDrawPaymentStatus(int value, String name) {
		this.value = value;
		this.name = name;
	}

	public static FundDrawPaymentStatus getStatus(int value) {
		FundDrawPaymentStatus[] otypes = FundDrawPaymentStatus.values();
		for (FundDrawPaymentStatus orderStatus : otypes) {
			if (orderStatus.value == value) {
				return orderStatus;
			}
		}

		throw new FundException(FundReturnCodeEnum.ERR_ENUM_TYPE, value, "支付状态");
	}
}
