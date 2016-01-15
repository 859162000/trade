package com.hbc.api.fund.biz.enums;

import com.hbc.api.fund.biz.exception.FundException;

public enum FundProcessStatus {

	//处理状态 0：待处理 ，1 已处理 ，2 自动提款

	INIT(0, "待处理"),
	PROCESSED(1, " 已处理"),
	AUTO_PROCESS(2, "自动提款");

	public int value;
	public String name;

	FundProcessStatus(int value, String name) {
		this.value = value;
		this.name = name;
	}

	public static FundProcessStatus getStatus(int value) {
		FundProcessStatus[] otypes = FundProcessStatus.values();
		for (FundProcessStatus orderStatus : otypes) {
			if (orderStatus.value == value) {
				return orderStatus;
			}
		}

		throw new FundException(FundReturnCodeEnum.ERR_ENUM_TYPE, value, "银行卡状态");
	}
}
