package com.hbc.api.trade.ota.po;

public class PriceListPo {
	private String priceMark;
	private int CarType;
	private double Price;
	
	private String carDesc;
	private String models;
	
	
	public String getCarDesc() {
		return carDesc;
	}
	public void setCarDesc(String carDesc) {
		this.carDesc = carDesc;
	}
	public String getModels() {
		return models;
	}
	public void setModels(String models) {
		this.models = models;
	}
	public String getPriceMark() {
		return priceMark;
	}
	public void setPriceMark(String priceMark) {
		this.priceMark = priceMark;
	}
	public int getCarType() {
		return CarType;
	}
	public void setCarType(int carType) {
		CarType = carType;
	}
	public double getPrice() {
		return Price;
	}
	public void setPrice(double price) {
		Price = price;
	}
}
