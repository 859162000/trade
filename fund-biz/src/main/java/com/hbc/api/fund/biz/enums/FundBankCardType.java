package com.hbc.api.fund.biz.enums;

import com.hbc.api.fund.biz.exception.FundException;

/**
 * 财务信息分类。1-银行账号；2-支付宝账号；3-paypal账号
 * @author Jongly Ran
 */
public enum FundBankCardType {
	
	/** 1: 银行账号*/
	BANK(1,"银行账号"),
	
	/** 2: 支付宝账号*/
	ALIPAY(2,"支付宝账号"),
	
	/** 3: PAYPAL账号*/
	PAYPAL(3,"PAYPAL账号"),
	
	;


	public int value;
	public String name;
	FundBankCardType(int value,String name){
		this.value = value;
		this.name = name;
	}
	
	public static FundBankCardType getStatus(Integer value){
		if(value != null) {
			FundBankCardType[] otypes = FundBankCardType.values();
			for(FundBankCardType orderStatus : otypes){
				if(orderStatus.value==value){
					return orderStatus;
				}
			}
		}
		
		throw new FundException(FundReturnCodeEnum.ERR_ENUM_TYPE,value, "银行卡分类");
	}
}
