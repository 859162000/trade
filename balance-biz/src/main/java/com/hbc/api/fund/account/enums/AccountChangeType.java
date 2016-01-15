/**
 * @Author lukangle
 * @2015年11月4日@下午6:22:40
 */
package com.hbc.api.fund.account.enums;


public enum AccountChangeType {
	System(1, "系统"), 
	COMPAY(2,"企业支付 "), 
	AGENTPAY(3,"代理商付款"), 
	
	MISCG(4,"MIS操作"),
	;
	
	public int value;
	public String name;
	AccountChangeType(int value,String name){
		this.value = value;
		this.name = name;
	}
	
	public static AccountChangeType getType(int value){
		AccountChangeType[] accountChangeTypes = AccountChangeType.values();
		for(AccountChangeType accountChangeType : accountChangeTypes){
			if(accountChangeType.value==value){
				return accountChangeType;
			}
		}
		return null;
	}
}
