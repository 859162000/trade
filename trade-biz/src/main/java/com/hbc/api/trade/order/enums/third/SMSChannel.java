/**
 * @Author lukangle
 * @2015年12月3日@下午9:56:29
 */
package com.hbc.api.trade.order.enums.third;

public enum SMSChannel {
	APP(1, "APP"), zhixiao(2, "直销"), fenxiao(3, "分销"), INVALID(Integer.MIN_VALUE, "无效类型"), FORBIDDEN(Integer.MAX_VALUE, "禁止发送");

	public Integer value;
	public String name;

	SMSChannel(int value, String name) {
		this.value = value;
		this.name = name;
	}

	public static SMSChannel getType(Integer value) {
		SMSChannel[] carTypeEnums = SMSChannel.values();
		for (SMSChannel carTypeEnum : carTypeEnums) {
			if (carTypeEnum.value.equals(value)) {
				return carTypeEnum;
			}
		}
		return null;
	}
}
