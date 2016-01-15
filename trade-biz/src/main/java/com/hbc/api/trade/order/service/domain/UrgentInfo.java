package com.hbc.api.trade.order.service.domain;

import com.hbc.api.trade.order.enums.CarClassTypeEnum;

public class UrgentInfo {
	private CarClassTypeEnum carClassTypeEnum;
	private double hour;
	private double userprice;
	private double guideprice;
	public double getHour() {
		return hour;
	}
	public void setHour(double hour) {
		this.hour = hour;
	}
	public double getUserprice() {
		return userprice;
	}
	public void setUserprice(double userprice) {
		this.userprice = userprice;
	}
	public double getGuideprice() {
		return guideprice;
	}
	public void setGuideprice(double guideprice) {
		this.guideprice = guideprice;
	}
	public CarClassTypeEnum getCarClassTypeEnum() {
		return carClassTypeEnum;
	}
	public void setCarClassTypeEnum(CarClassTypeEnum carClassTypeEnum) {
		this.carClassTypeEnum = carClassTypeEnum;
	}
}
