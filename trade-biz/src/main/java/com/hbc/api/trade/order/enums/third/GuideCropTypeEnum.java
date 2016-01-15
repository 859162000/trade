package com.hbc.api.trade.order.enums.third;

/**
 * @author colin
 *
 */
public enum GuideCropTypeEnum {
	CROPTYPE_RECEIVE(1, "接机"), CROPTYPE_SEND(2, "送机"), CROPTYPE_BAO_INCITY(3, "市内包车"), CROPTYPE_BYTIME(4, "次租"), CROPTYPE_ROUTE(5, "固定线路产品"), CROPTYPE_BAO_IN3(6, "三日内包车"), CARCLASS_BAO_OUT3(7,
			"三日外包车");
	public Integer value;
	public String name;

	GuideCropTypeEnum(int value, String name) {
		this.value = value;
		this.name = name;
	}

	public static GuideCropTypeEnum getType(Integer value) {
		GuideCropTypeEnum[] carTypeEnums = GuideCropTypeEnum.values();
		for (GuideCropTypeEnum coupStatus : carTypeEnums) {
			if (coupStatus.value.equals(value)) {
				return coupStatus;
			}
		}
		return null;
	}
}
