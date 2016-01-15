package com.hbc.api.trade.order.enums.third;

public enum GuideAgencyType {
	
	//1=地接社员工；2=地接社管理员；0=不属于地接社（默认）
	NORMAL(0,"不属于地接社"),
	STAFF(1,"地接社员工"),
	BOOS(2,"地接社管理员");
	
	public Integer value;
	public String name;
	GuideAgencyType(int value, String name) {
		this.value = value;
		this.name = name;
	}

	public static GuideAgencyType getType(Integer value) {
		GuideAgencyType[] guideAgencyTypes = GuideAgencyType.values();
		for (GuideAgencyType guideAgencyType : guideAgencyTypes) {
			if (guideAgencyType.value.equals(value)) {
				return guideAgencyType;
			}
		}
		return null;
	}
}
