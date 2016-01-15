/**
 * @Author lukangle
 * @2015年11月18日@下午3:35:13
 */
package com.hbc.api.fund.account.enums;

/**
 * 对应表fund_account_log
 * @author Jongly Ran
 */
public enum BizStatus {
	SUCCESS(1, "成功"), FAILED(2, "失败 "), HIDDEN(-10, "隐藏");
	
	public int value;
	public String name;

	BizStatus(int value, String name) {
		this.value = value;
		this.name = name;
	}

	public static BizStatus getType(int value) {
		BizStatus[] accountStatus = BizStatus.values();
		for (BizStatus accountStatu : accountStatus) {
			if (accountStatu.value == value) {
				return accountStatu;
			}
		}
		
		return null;
	}
}
