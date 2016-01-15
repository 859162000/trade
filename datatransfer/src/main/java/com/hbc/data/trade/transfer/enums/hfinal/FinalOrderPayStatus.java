/**
 * @Author lukangle
 * @2015年12月8日@上午10:21:14
 */
package com.hbc.data.trade.transfer.enums.hfinal;

public enum FinalOrderPayStatus  {
	/**
	 * 订单支付状态。0-未支付（默认初始）；1-已支付；11-有异动；100-已结算
	 */
	INITSTATE(0, "初始（默认）"),
	PAYSUCCESS(1, "已支付"),
	ADDITION_COST(11, "有异动"),
	SETTLE_END(100, "已结算"),
	;
	public Integer value;
	public String name;

	FinalOrderPayStatus(int value, String name) {
		this.value = value;
		this.name = name;
	}

	public static FinalOrderPayStatus getStatus(Integer value) {
		FinalOrderPayStatus[] alarmTypes = FinalOrderPayStatus.values();
		for (FinalOrderPayStatus alarmType : alarmTypes) {
			if (alarmType.value.equals(value)) {
				return alarmType;
			}
		}
		return null;
	}
}
