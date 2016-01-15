package com.hbc.api.fund.biz.enums;

import com.hbc.api.fund.biz.exception.FundException;

/**
 * 状态；0 = 删除（解除绑定）；1=正常（绑定）
 * @author Jongly Ran
 */
public enum FundBankStatus {
	
	/** 0: 删除（解除绑定）；*/
	UNBIND(0,"删除（解除绑定）"), 

	/** 1: 正常（绑定）*/
	BIND(1,"正常（绑定）");


	public int value;
	public String name;
	FundBankStatus(int value,String name){
		this.value = value;
		this.name = name;
	}
	
	public static FundBankStatus getStatus(int value){
		FundBankStatus[] otypes = FundBankStatus.values();
		for(FundBankStatus orderStatus : otypes){
			if(orderStatus.value==value){
				return orderStatus;
			}
		}
		
		throw new FundException(FundReturnCodeEnum.ERR_ENUM_TYPE,value, "银行卡状态");
	}
}
