/**
 * @Author lukangle
 * @2015年12月10日@下午8:54:42
 */
package com.hbc.data.trade.transfer.enums.hfinal;
/**
 * orderpay 支付状态
 * 状态 0=初始（默认）；1=成功；2=失败；4=取消；
 */
public enum FOrderPayStatus {
	INITSTATE(0, "初始（默认）"),
	PAYSUCCESS(1, "成功"),
	FAILED(2, "失败"),
	CANCLED(4, "取消"),
	;
	public Integer value;
	public String name;

	FOrderPayStatus(int value, String name) {
		this.value = value;
		this.name = name;
	}

	public static FOrderPayStatus getStatus(Integer value) {
		FOrderPayStatus[] alarmTypes = FOrderPayStatus.values();
		for (FOrderPayStatus alarmType : alarmTypes) {
			if (alarmType.value.equals(value)) {
				return alarmType;
			}
		}
		return null;
	}
}
