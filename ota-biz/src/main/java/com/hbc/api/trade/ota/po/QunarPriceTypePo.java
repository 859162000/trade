package com.hbc.api.trade.ota.po;

public class QunarPriceTypePo {
	
	private Integer carType ;
	public static Integer priceType = 1 ; 
	private Double price;
	private QunarPriceDetailPo detail ;

	public Integer getCarType() {
		return carType;
	}
	public void setCarType(Integer carType) {
		this.carType = carType;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public QunarPriceDetailPo getDetail() {
		return detail;
	}
	public void setDetail(QunarPriceDetailPo detail) {
		this.detail = detail;
	}
}
