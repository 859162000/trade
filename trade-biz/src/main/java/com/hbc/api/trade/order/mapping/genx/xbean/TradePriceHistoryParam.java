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

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

/**
 * @author Jongly Ran
 */
public class TradePriceHistoryParam {
	/**
     *  订单编号
     *  所属表字段为trade_price_history.order_no
     */
    @NotBlank private String orderNo;

    /**
     *  修改序号 第一次 修改 第二次修改
     *  所属表字段为trade_price_history.price_seq
     */
    @NotNull private Integer priceSeq;

    /**
     *  价格 原始价
     *  所属表字段为trade_price_history.source_price
     */
    @NotNull private Double sourcePrice;

    /**
     *  价格 变动后的价格
     *  所属表字段为trade_price_history.target_price
     */
    @NotNull private Double targetPrice;

    /**
     *  价格改动类型：
            1.首单奖励
            2.串单
            3.二次发单
            4.管理员价格
     *  所属表字段为trade_price_history.op_type
     */
    @NotNull private Integer opType;

    /**
     *  操作员ID
     *  所属表字段为trade_price_history.op_uid
     */
    @NotBlank private String opUid;

    /**
     *  操作员姓名
     *  所属表字段为trade_price_history.op_uname
     */
    @NotBlank private String opUname;

    /**
     *  改价原因
     *  所属表字段为trade_price_history.op_comment
     */
    private String opComment;
    
    /**
     *  1 导游价 修改 2 渠道价修改
     *  所属表字段为`trade_price_history`.price_type
     */
    private Integer priceType;

    /**
	 * @return the priceType
	 */
	public Integer getPriceType() {
		return priceType;
	}

	/**
	 * @param priceType the priceType to set
	 */
	public void setPriceType(Integer priceType) {
		this.priceType = priceType;
	}

	/**
     *订单编号
     *trade_price_history.order_no
     *
     * @return the value of trade_price_history.order_no
     *
     * @mbggenerated Tue Nov 24 12:02:20 CST 2015
     */
    public String getOrderNo() {
        return orderNo;
    }

    /**
     *订单编号
     *trade_price_history.order_no
     *
     * @param orderNo the value for trade_price_history.order_no
     *
     * @mbggenerated Tue Nov 24 12:02:20 CST 2015
     */
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    /**
     *修改序号 第一次 修改 第二次修改
     *trade_price_history.price_seq
     *
     * @return the value of trade_price_history.price_seq
     *
     * @mbggenerated Tue Nov 24 12:02:20 CST 2015
     */
    public Integer getPriceSeq() {
        return priceSeq;
    }

    /**
     *修改序号 第一次 修改 第二次修改
     *trade_price_history.price_seq
     *
     * @param priceSeq the value for trade_price_history.price_seq
     *
     * @mbggenerated Tue Nov 24 12:02:20 CST 2015
     */
    public void setPriceSeq(Integer priceSeq) {
        this.priceSeq = priceSeq;
    }

    /**
     *价格 原始价
     *trade_price_history.source_price
     *
     * @return the value of trade_price_history.source_price
     *
     * @mbggenerated Tue Nov 24 12:02:20 CST 2015
     */
    public Double getSourcePrice() {
        return sourcePrice;
    }

    /**
     *价格 原始价
     *trade_price_history.source_price
     *
     * @param sourcePrice the value for trade_price_history.source_price
     *
     * @mbggenerated Tue Nov 24 12:02:20 CST 2015
     */
    public void setSourcePrice(Double sourcePrice) {
        this.sourcePrice = sourcePrice;
    }

    /**
     *价格 变动后的价格
     *trade_price_history.target_price
     *
     * @return the value of trade_price_history.target_price
     *
     * @mbggenerated Tue Nov 24 12:02:20 CST 2015
     */
    public Double getTargetPrice() {
        return targetPrice;
    }

    /**
     *价格 变动后的价格
     *trade_price_history.target_price
     *
     * @param targetPrice the value for trade_price_history.target_price
     *
     * @mbggenerated Tue Nov 24 12:02:20 CST 2015
     */
    public void setTargetPrice(Double targetPrice) {
        this.targetPrice = targetPrice;
    }

    /**
     *价格改动类型：
            1.首单奖励
            2.串单
            3.二次发单
            4.管理员价格
     *trade_price_history.op_type
     *
     * @return the value of trade_price_history.op_type
     *
     * @mbggenerated Tue Nov 24 12:02:20 CST 2015
     */
    public Integer getOpType() {
        return opType;
    }

    /**
     *价格改动类型：
            1.首单奖励
            2.串单
            3.二次发单
            4.管理员价格
     *trade_price_history.op_type
     *
     * @param opType the value for trade_price_history.op_type
     *
     * @mbggenerated Tue Nov 24 12:02:20 CST 2015
     */
    public void setOpType(Integer opType) {
        this.opType = opType;
    }

    /**
     *
     *trade_price_history.op_uid
     *
     * @return the value of trade_price_history.op_uid
     *
     * @mbggenerated Tue Nov 24 12:02:20 CST 2015
     */
    public String getOpUid() {
        return opUid;
    }

    /**
     *
     *trade_price_history.op_uid
     *
     * @param opUid the value for trade_price_history.op_uid
     *
     * @mbggenerated Tue Nov 24 12:02:20 CST 2015
     */
    public void setOpUid(String opUid) {
        this.opUid = opUid == null ? null : opUid.trim();
    }

    /**
     *
     *trade_price_history.op_uname
     *
     * @return the value of trade_price_history.op_uname
     *
     * @mbggenerated Tue Nov 24 12:02:20 CST 2015
     */
    public String getOpUname() {
        return opUname;
    }

    /**
     *
     *trade_price_history.op_uname
     *
     * @param opUname the value for trade_price_history.op_uname
     *
     * @mbggenerated Tue Nov 24 12:02:20 CST 2015
     */
    public void setOpUname(String opUname) {
        this.opUname = opUname == null ? null : opUname.trim();
    }

    /**
     *改价原因
     *trade_price_history.op_comment
     *
     * @return the value of trade_price_history.op_comment
     *
     * @mbggenerated Tue Nov 24 12:02:20 CST 2015
     */
    public String getOpComment() {
        return opComment;
    }

    /**
     *改价原因
     *trade_price_history.op_comment
     *
     * @param opComment the value for trade_price_history.op_comment
     *
     * @mbggenerated Tue Nov 24 12:02:20 CST 2015
     */
    public void setOpComment(String opComment) {
        this.opComment = opComment == null ? null : opComment.trim();
    }
}
