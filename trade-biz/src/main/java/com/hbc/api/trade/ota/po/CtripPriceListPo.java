package com.hbc.api.trade.ota.po;

public class CtripPriceListPo {

	private Integer VehicleType ;
	public static Boolean IsSupportPickup = true ;
	public static Double PickupFee = (double) 0 ;
	public static Boolean IsSupportChildSeat = true ;
	public static Double ChildSeatFee = (double) 0;
	public static Boolean isMustChildSeat = true ;
	private Double Price;

	public Integer getVehicleType() {
		return VehicleType;
	}
	public void setVehicleType(Integer vehicleType) {
		VehicleType = vehicleType;
	}
	public Double getPrice() {
		return Price;
	}
	public void setPrice(Double price) {
		Price = price;
	}

}
