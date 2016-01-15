/*
 * Copyright (c) 2015-2016, CCLX.COM. All rights reserved.
 * WANDA GROUP PROPRIETARY/CONFIDENTIAL. 
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is private property; you can't redistribute it and/or modify it
 * under the terms of the LICENSE you obtained from
 *
 *    http://www.cclx.com/
 * 
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. 
 *
 * Author: Jongly Ran
 * Revision: 1.0
 */
package com.hbc.api.trade.order.mapping.genx.xbean;

import java.util.List;

import com.hbc.api.trade.order.mapping.gen.bean.TradeAdditionalDetail;

/**
 * @author Jongly Ran
 */
public class PaymentDetail {
	private Double priceTicket; 		// 票面价 （来自C-app）
	private Double couponPrice; 		// 代金券 (可以是现金券和折扣券)
	private Double actualPay; 			// 已付款
	private Double priceChannel; 		// 渠道价 (已包含登机checkIn费用，如果有)
	private String priceChannelCPlus; 	// c-app 渠道加价
	private Double priceBase; 			// 系统价
	private Double priceReward; 		// 接单奖励 
	private String urgentOrderPlus; 	// 急单导游加价
	private Double priceGuide; 			// 导游价
	private String priceGuideDiscount; 	// A级导游降价
	private String serialDiscount; 		// 串单降价
	
	/* 取消退款 */
	private Double refundToCustomer; 	// 退给客人
	private String saleProfit; 			// 销售利润
	private Double refundToGuide; 		// 退给导游
	
	/* 增项费用 */
    private Double	overPrice;			// 增项费用总额
    private List<TradeAdditionalDetail> additionalCostDetails; // 增项费用明细
	
	
	/**
	 * @return the overPrice
	 */
	public Double getOverPrice() {
		return overPrice;
	}
	/**
	 * @param overPrice the overPrice to set
	 */
	public void setOverPrice(Double overPrice) {
		this.overPrice = overPrice;
	}
	/**
	 * @return the additionalCostDetails
	 */
	public List<TradeAdditionalDetail> getAdditionalCostDetails() {
		return additionalCostDetails;
	}
	/**
	 * @param additionalCostDetails the additionalCostDetails to set
	 */
	public void setAdditionalCostDetails(List<TradeAdditionalDetail> additionalCostDetails) {
		this.additionalCostDetails = additionalCostDetails;
	}
	/**
	 * @return the priceChannelCPlus
	 */
	public String getPriceChannelCPlus() {
		return priceChannelCPlus;
	}
	/**
	 * @param priceChannelCPlus the priceChannelCPlus to set
	 */
	public void setPriceChannelCPlus(String priceChannelCPlus) {
		this.priceChannelCPlus = priceChannelCPlus;
	}
	/**
	 * @return the priceTicket
	 */
	public Double getPriceTicket() {
		return priceTicket;
	}
	/**
	 * @param priceTicket the priceTicket to set
	 */
	public void setPriceTicket(Double priceTicket) {
		this.priceTicket = priceTicket;
	}
	/**
	 * @return the couponPrice
	 */
	/**
	 * @return the actualPay
	 */
	public Double getActualPay() {
		return actualPay;
	}
	public Double getCouponPrice() {
		return couponPrice;
	}
	public void setCouponPrice(Double couponPrice) {
		this.couponPrice = couponPrice;
	}
	/**
	 * @param actualPay the actualPay to set
	 */
	public void setActualPay(Double actualPay) {
		this.actualPay = actualPay;
	}
	/**
	 * @return the priceChannel
	 */
	public Double getPriceChannel() {
		return priceChannel;
	}
	/**
	 * @param priceChannel the priceChannel to set
	 */
	public void setPriceChannel(Double priceChannel) {
		this.priceChannel = priceChannel;
	}
	/**
	 * @return the priceBase
	 */
	public Double getPriceBase() {
		return priceBase;
	}
	/**
	 * @param priceBase the priceBase to set
	 */
	public void setPriceBase(Double priceBase) {
		this.priceBase = priceBase;
	}
	/**
	 * @return the priceReward
	 */
	public Double getPriceReward() {
		return priceReward;
	}
	/**
	 * @param priceReward the priceReward to set
	 */
	public void setPriceReward(Double priceReward) {
		this.priceReward = priceReward;
	}
	/**
	 * @return the urgentOrderPlus
	 */
	public String getUrgentOrderPlus() {
		return urgentOrderPlus;
	}
	/**
	 * @param urgentOrderPlus the urgentOrderPlus to set
	 */
	public void setUrgentOrderPlus(String urgentOrderPlus) {
		this.urgentOrderPlus = urgentOrderPlus;
	}
	/**
	 * @return the priceGuide
	 */
	public Double getPriceGuide() {
		return priceGuide;
	}
	/**
	 * @param priceGuide the priceGuide to set
	 */
	public void setPriceGuide(Double priceGuide) {
		this.priceGuide = priceGuide;
	}
	/**
	 * @return the priceGuideDiscount
	 */
	public String getPriceGuideDiscount() {
		return priceGuideDiscount;
	}
	/**
	 * @param priceGuideDiscount the priceGuideDiscount to set
	 */
	public void setPriceGuideDiscount(String priceGuideDiscount) {
		this.priceGuideDiscount = priceGuideDiscount;
	}
	/**
	 * @return the serialDiscount
	 */
	public String getSerialDiscount() {
		return serialDiscount;
	}
	/**
	 * @param serialDiscount the serialDiscount to set
	 */
	public void setSerialDiscount(String serialDiscount) {
		this.serialDiscount = serialDiscount;
	}
	/**
	 * @return the refundToCustomer
	 */
	public Double getRefundToCustomer() {
		return refundToCustomer;
	}
	/**
	 * @param refundToCustomer the refundToCustomer to set
	 */
	public void setRefundToCustomer(Double refundToCustomer) {
		this.refundToCustomer = refundToCustomer;
	}
	/**
	 * @return the saleProfit
	 */
	public String getSaleProfit() {
		return saleProfit;
	}
	/**
	 * @param saleProfit the saleProfit to set
	 */
	public void setSaleProfit(String saleProfit) {
		this.saleProfit = saleProfit;
	}
	/**
	 * @return the refundToGuide
	 */
	public Double getRefundToGuide() {
		return refundToGuide;
	}
	/**
	 * @param refundToGuide the refundToGuide to set
	 */
	public void setRefundToGuide(Double refundToGuide) {
		this.refundToGuide = refundToGuide;
	}
	
}
