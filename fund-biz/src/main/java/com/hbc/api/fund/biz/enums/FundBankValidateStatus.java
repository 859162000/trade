package com.hbc.api.fund.biz.enums;

import com.hbc.api.fund.biz.exception.FundException;

/**
 * 是否已验证。0-否（默认）；1-是
 * @author Jongly Ran
 */
public enum FundBankValidateStatus {
	
	/** 0: 否（默认）*/
	NO(0,"否"), 

	/** 1: 是*/
	YES(1,"是");


	public int value;
	public String name;
	FundBankValidateStatus(int value,String name){
		this.value = value;
		this.name = name;
	}
	
	public static FundBankValidateStatus getStatus(int value){
		FundBankValidateStatus[] otypes = FundBankValidateStatus.values();
		for(FundBankValidateStatus orderStatus : otypes){
			if(orderStatus.value==value){
				return orderStatus;
			}
		}
		
		throw new FundException(FundReturnCodeEnum.ERR_ENUM_TYPE,value, "银行卡校验状态");
	}
}
