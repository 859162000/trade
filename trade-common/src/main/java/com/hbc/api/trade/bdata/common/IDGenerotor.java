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
package com.hbc.api.trade.bdata.common;

import com.hbc.seqgen.CountCycle;
import com.hbc.seqgen.SeqGenerator;

/**
 * 用户Id序列号生产器
 * 
 * @author Jongly Ran
 *
 */
public class IDGenerotor {

	private static SeqGenerator tradeOrderSeq = new SeqGenerator("tradeOrder", CountCycle.DAY, 5);
	private static SeqGenerator tradePaymentSeq = new SeqGenerator("tradePayment", CountCycle.DAY, 5);
	private static SeqGenerator additionalCostSeq = new SeqGenerator("additionalCost", CountCycle.DAY, 5);
	private static SeqGenerator additionalCostDetailSeq = new SeqGenerator("additionalCostDetail", CountCycle.DAY, 5);
	private static SeqGenerator couponSeq = new SeqGenerator("coupon", CountCycle.DAY, 5);
	private static SeqGenerator orderTrackSeq = new SeqGenerator("TradeOrderTrack", CountCycle.DAY, 5);
	private static SeqGenerator accountTrackSeq = new SeqGenerator("accountTrackSeq", CountCycle.DAY, 5);
	private static SeqGenerator fundBankCardSeq = new SeqGenerator("fundBankCard", CountCycle.DAY, 5);
	private static SeqGenerator fundAccountLogSeq = new SeqGenerator("fundAccountLogSeq", CountCycle.DAY, 5);
	private static SeqGenerator tradePriceHistorySeq = new SeqGenerator("tradePriceHistory", CountCycle.DAY, 5);
	private static SeqGenerator fundSeqGenerator = new SeqGenerator("fundSeqGenerator", CountCycle.DAY, 5);

	public static String generateOrderNo() {
		return tradeOrderSeq.nextSeq("1", true);
	}

	public static String generatePayNo() {
		return tradePaymentSeq.nextSeq("2", true);
	}

	public static String generateRefundNo() {
		return tradePaymentSeq.nextSeq("3", true);
	}

	public static String generateDeliverNo() {
		return tradePaymentSeq.nextSeq("4", true);
	}

	public static String genDeliverGuidNo() {
		return tradePaymentSeq.nextSeq("5", true);
	}

	public static String genDeliverBatchNo() {
		return tradePaymentSeq.nextSeq("6", true);
	}

	public static String generateAdditionalCostId() {
		return additionalCostSeq.nextSeq("7", true);
	}

	public static String generateAdditionalCostDetailId() {
		return additionalCostDetailSeq.nextSeq("8", true);
	}

	public static String generateCouponId() {
		return couponSeq.nextSeq("1", true);
	}

	public static String generateOrderTrackId() {
		return orderTrackSeq.nextSeq("2", true);
	}

	public static String generateAccountNo() {
		return accountTrackSeq.nextSeq("9", true);
	}

	public static String generateBankNo() {
		return fundBankCardSeq.nextSeq("8", true);
	}

	public static String generateAccountLogNo() {
		return fundAccountLogSeq.nextSeq("1", true);
	}

	public static String generateTradePriceHistoryId() {
		return tradePriceHistorySeq.nextSeq("2", true);
	}

	public static String generateFundWithDrawNo()
	{
		return fundSeqGenerator.nextSeq("WD", true);
	}

	public static String generatePaymentId()
	{
		return fundSeqGenerator.nextSeq("PMID", true);
	}
}
