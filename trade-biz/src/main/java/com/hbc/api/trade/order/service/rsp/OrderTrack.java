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
package com.hbc.api.trade.order.service.rsp;

import java.util.Date;

/**
 * @author Jongly Ran
 */
public class OrderTrack {

    /**
     *  行程动态文字内容
     *  所属表字段为trade_order_track.track_desc
     */
    private String trackDesc;

    /**
     *  
     *  所属表字段为trade_order_track.create_time
     */
    private Date createTime;

    /**
     *行程动态文字内容
     *trade_order_track.track_desc
     *
     * @return the value of trade_order_track.track_desc
     *
     * @mbggenerated Sat Nov 07 10:21:58 CST 2015
     */
    public String getTrackDesc() {
        return trackDesc;
    }

    /**
     *行程动态文字内容
     *trade_order_track.track_desc
     *
     * @param trackDesc the value for trade_order_track.track_desc
     *
     * @mbggenerated Sat Nov 07 10:21:58 CST 2015
     */
    public void setTrackDesc(String trackDesc) {
        this.trackDesc = trackDesc == null ? null : trackDesc.trim();
    }

    /**
     *
     *trade_order_track.create_time
     *
     * @return the value of trade_order_track.create_time
     *
     * @mbggenerated Sat Nov 07 10:21:58 CST 2015
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     *
     *trade_order_track.create_time
     *
     * @param createTime the value for trade_order_track.create_time
     *
     * @mbggenerated Sat Nov 07 10:21:58 CST 2015
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
