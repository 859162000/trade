package com.hbc.api.fund.biz.enums;

import com.hbc.api.fund.biz.exception.FundException;

/**
 * @author Jongly Ran
 */
public enum FundDrawStatus {
	
	/** -2: 删除 */
	DELETE_TO_HIDDEN(-2, "删除"),
	
	/** -1: 删除申请提现；*/
	DELETE_APPLICATION(-1,"删除申请提现"), 
	
	DELETE(-2, "删除"),

	/** 0: 申请提现；*/
	APPLY(0,"申请提现"), 

	/** 1: 已转账*/
	HAVE_TRANSFERED(1,"已转账"),
	
	/** 2: 自动提现失败*/
	AUTO_WITHDRAW_FAILED(2,"自动提现失败"),
	
	/** 3: 自动提现已申请*/
	AUTO_WITHDRAW_APPLIED(3,"自动提现已申请 支付宝");


	public int value;
	public String name;

	FundDrawStatus(int value,String name){
		this.value = value;
		this.name = name;
	}
	
	public static FundDrawStatus getStatus(int value){
		FundDrawStatus[] otypes = FundDrawStatus.values();
		for(FundDrawStatus orderStatus : otypes){
			if(orderStatus.value==value){
				return orderStatus;
			}
		}
		
		throw new FundException(FundReturnCodeEnum.ERR_ENUM_TYPE,value, "银行卡状态");
	}
}
