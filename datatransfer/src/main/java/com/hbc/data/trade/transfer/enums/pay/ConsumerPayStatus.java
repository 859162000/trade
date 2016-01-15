/**
 * @Author lukangle
 * @2015年12月10日@下午8:48:38
 */
package com.hbc.data.trade.transfer.enums.pay;


public enum ConsumerPayStatus {
	INITSTATE(0, "初始（默认）"),
	PAYFAILED(1, "支付失败"),
	PAYSUCCESS(2, "支付成功"),
	;
	public Integer value;
	public String name;

	ConsumerPayStatus(int value, String name) {
		this.value = value;
		this.name = name;
	}

	public static ConsumerPayStatus getStatus(Integer value) {
		ConsumerPayStatus[] alarmTypes = ConsumerPayStatus.values();
		for (ConsumerPayStatus alarmType : alarmTypes) {
			if (alarmType.value.equals(value)) {
				return alarmType;
			}
		}
		return null;
	}
}
