package com.hbc.api.trade.ota.po;

public class QunarPriceListPo {
	
	private Double lowPrice ;
	private QunarPriceTypePo types;

	public Double getLowPrice() {
		return lowPrice;
	}
	public void setLowPrice(Double lowPrice) {
		this.lowPrice = lowPrice;
	}
	public QunarPriceTypePo getTypes() {
		return types;
	}
	public void setTypes(QunarPriceTypePo types) {
		this.types = types;
	}

}
