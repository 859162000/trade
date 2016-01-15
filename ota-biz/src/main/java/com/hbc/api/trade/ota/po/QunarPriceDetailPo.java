package com.hbc.api.trade.ota.po;

public class QunarPriceDetailPo {
	
	private Double basePrice ;
	public static Integer baseTime = 0;
	public static Integer overTimeCost = 0;
	public static Integer baseDist = 0;
	public static Integer overDistCost = 0;
	public static Integer freeDriverCost = 0;
	public static Integer nightServiceCost = 0;
	public static Integer vatCost = 0;
	public static Integer shortPreBookCost = 0;
	public static Integer meetCost = 0;
	public static Integer otherCost = 0;
	public static Integer tip = 0;
	public static Integer childrenChairCost = 0;
	public static Integer pickupCardCost = 0;
	public static String priceDescription = "";

	public Double getBasePrice() {
		return basePrice;
	}
	public void setBasePrice(Double basePrice) {
		this.basePrice = basePrice;
	}
}
