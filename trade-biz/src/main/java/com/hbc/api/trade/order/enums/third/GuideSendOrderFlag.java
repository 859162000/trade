package com.hbc.api.trade.order.enums.third;

public enum GuideSendOrderFlag {

	/* 0=不发送； 1=发送（默认）*/
	FORBID(0,"不发送"),
	PERMIT(1,"允许");
	
	
	public Integer value;
	public String name;
	GuideSendOrderFlag(int value, String name) {
		this.value = value;
		this.name = name;
	}

	public static GuideSendOrderFlag getType(Integer value) {
		GuideSendOrderFlag[] guideSendOrderFlags = GuideSendOrderFlag.values();
		for (GuideSendOrderFlag guideSendOrderFlag : guideSendOrderFlags) {
			if (guideSendOrderFlag.value.equals(value)) {
				return guideSendOrderFlag;
			}
		}
		return null;
	}
}
