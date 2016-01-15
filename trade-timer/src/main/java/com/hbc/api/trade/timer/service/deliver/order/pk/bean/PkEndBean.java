package com.hbc.api.trade.timer.service.deliver.order.pk.bean;

import java.util.List;

import com.hbc.api.trade.order.mapping.gen.bean.TradeDeliverGuide;

public class PkEndBean {
	TradeDeliverGuide successTradeDeliverGuide;
	List<TradeDeliverGuide> failTradeDeliverGuide;
	public TradeDeliverGuide getSuccessTradeDeliverGuide() {
		return successTradeDeliverGuide;
	}
	public void setSuccessTradeDeliverGuide(TradeDeliverGuide successTradeDeliverGuide) {
		this.successTradeDeliverGuide = successTradeDeliverGuide;
	}
	public List<TradeDeliverGuide> getFailTradeDeliverGuide() {
		return failTradeDeliverGuide;
	}
	public void setFailTradeDeliverGuide(List<TradeDeliverGuide> failTradeDeliverGuide) {
		this.failTradeDeliverGuide = failTradeDeliverGuide;
	}
	
}
