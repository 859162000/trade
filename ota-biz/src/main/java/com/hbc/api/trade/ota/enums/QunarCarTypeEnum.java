package com.hbc.api.trade.ota.enums;

public enum QunarCarTypeEnum {

	Car1(1, 1, 5),
	Car3(3, 2, 5),
	Car4(4, 3, 5),
	Car5(5, 2, 7),
	Car10(10, 2, 9),
	Car14(14, 2, 12),
	Car103(103, 1, 7),
	Car119(119, 4, 5),
	Car121(121, 4, 9),
	Car122(122, 1, 12),
	Car125(125, 3, 7),
	Car126(126, 1, 9);

	public int thirdCarType;
	public int carTypeId;
	public int carSeatNum;

	QunarCarTypeEnum(int thirdCarType, int carTypeId, int carSeatNum) {
		this.thirdCarType = thirdCarType;
		this.carTypeId = carTypeId;
		this.carSeatNum = carSeatNum;
	}

	public static QunarCarTypeEnum getCar(int thirdCarType) {
		QunarCarTypeEnum[] otypes = QunarCarTypeEnum.values();
		for (QunarCarTypeEnum qunarCarTypeEnum : otypes) {
			if (qunarCarTypeEnum.thirdCarType == thirdCarType) {
				return qunarCarTypeEnum;
			}
		}
		// 三方不支持该类型车辆
		return null;
	}

	public static QunarCarTypeEnum getCar(int carType, int carNum) {
		QunarCarTypeEnum[] otypes = QunarCarTypeEnum.values();
		for (QunarCarTypeEnum qunarCarTypeEnum : otypes) {
			if (qunarCarTypeEnum.carTypeId == carType && qunarCarTypeEnum.carSeatNum == carNum) {
				return qunarCarTypeEnum;
			}
		}
		// 三方不支持该类型车辆
		return null;
	}
}
