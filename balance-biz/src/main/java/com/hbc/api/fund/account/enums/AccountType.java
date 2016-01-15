/**
 * @Author lukangle
 * @2015年11月14日@下午5:47:30
 */
package com.hbc.api.fund.account.enums;
/**
 * 账户类型 1 导游账户 2C端账户 3为黄包车 4为代理商账户
 */
public enum AccountType {
	G_ACCOUNT(1,"导游账户"), 
	C_ACCOUNT(2, "C端账户 "), 
	HBC_ACCOUNT(3, "黄包车 "), 
	AGENT_ACCOUNT(4, "代理商账户 "), 
	SYSTEM_ACCOUNT(5, "系统账户 "), 
	;
	
	public int value;
	public String name;
	AccountType(int value,String name){
		this.value = value;
		this.name = name;
	}
	
	public static AccountType getType(int value){
		AccountType[] accountStatus = AccountType.values();
		for(AccountType accountStatu : accountStatus){
			if(accountStatu.value==value){
				return accountStatu;
			}
		}
		return null;
	}
}
