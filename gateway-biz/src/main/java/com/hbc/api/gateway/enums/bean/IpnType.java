/**
 * @Author lukangle
 * @2015年10月16日@下午3:55:55
 */
package com.hbc.api.gateway.enums.bean;

public enum IpnType {
	ALI_PAY_NOTISFY(1, "支付宝支付 通知"),
	ALI_REFUND_NOTISFY(2, "支付宝退款 通知"),
	ALI_WITHDRAW_NOTISFY(3, "支付宝提现通知"), ;

	public int value;
	public String name;

	IpnType(int value, String name) {
		this.value = value;
		this.name = name;
	}

	public static IpnType getType(int value) {
		IpnType[] ipnTypes = IpnType.values();
		for (IpnType ipnType : ipnTypes) {
			if (ipnType.value == value) {
				return ipnType;
			}
		}

		return null;
	}
}
