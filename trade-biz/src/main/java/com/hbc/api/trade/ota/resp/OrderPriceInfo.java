/**
 * Copyrights  2015  HuangBaoChe
 *
 * All rights reserved.
 *
 * Created on 2015年11月7日 下午4:42:33
 * 
 * @author HanZhaozhan
 *
 */
package com.hbc.api.trade.ota.resp;

import java.util.List;

public class OrderPriceInfo {
	private Double distance;
	private Integer estTime;
	private Boolean supportChildseat; // 是否支持儿童座椅
	private Boolean supportBanner; // 是否支持 接机举牌开关
	
	private List<OrderPriceInfoQuoteVo> cars;

	public Boolean getSupportChildseat() {
		return supportChildseat;
	}

	public void setSupportChildseat(Boolean supportChildseat) {
		this.supportChildseat = supportChildseat;
	}

	public Boolean getSupportBanner() {
		return supportBanner;
	}

	public void setSupportBanner(Boolean supportBanner) {
		this.supportBanner = supportBanner;
	}

	public Double getDistance() {
		return distance;
	}

	public void setDistance(Double distance) {
		this.distance = distance;
	}

	public Integer getEstTime() {
		return estTime;
	}

	public void setEstTime(Integer estTime) {
		this.estTime = estTime;
	}

	public List<OrderPriceInfoQuoteVo> getCars() {
		return cars;
	}

	public void setCars(List<OrderPriceInfoQuoteVo> cars) {
		this.cars = cars;
	}
}
