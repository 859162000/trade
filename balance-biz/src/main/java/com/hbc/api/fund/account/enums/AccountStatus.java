/**
 * @Author lukangle
 * @2015年11月4日@下午6:29:45
 */
package com.hbc.api.fund.account.enums;

public enum AccountStatus {
	NOMAL(1, "正常"), Frozen(2, "冻结"), DELETED(-1, "删除"),;

	public int value;
	public String name;

	AccountStatus(int value, String name) {
		this.value = value;
		this.name = name;
	}

	public static AccountStatus getType(int value) {
		AccountStatus[] accountStatus = AccountStatus.values();
		for (AccountStatus accountStatu : accountStatus) {
			if (accountStatu.value == value) {
				return accountStatu;
			}
		}
		return null;
	}
}
