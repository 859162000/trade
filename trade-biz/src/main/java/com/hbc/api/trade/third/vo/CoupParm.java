/**
 * @Author lukangle
 * @2015年11月21日@下午4:31:29
 */
package com.hbc.api.trade.third.vo;

public class CoupParm {
	private String coupId;
	private String coupInfo;
	private double cActualPrice;
	public String getCoupId() {
		return coupId;
	}
	public void setCoupId(String coupId) {
		this.coupId = coupId;
	}
	public String getCoupInfo() {
		return coupInfo;
	}
	public void setCoupInfo(String coupInfo) {
		this.coupInfo = coupInfo;
	}
	public double getcActualPrice() {
		return cActualPrice;
	}
	public void setcActualPrice(double cActualPrice) {
		this.cActualPrice = cActualPrice;
	}
}
