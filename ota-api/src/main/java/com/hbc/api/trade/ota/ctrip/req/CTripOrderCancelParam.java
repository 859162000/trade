/*
 * Copyright (c) 2015-2016, CCLX.COM. All rights reserved.
 * WANDA GROUP PROPRIETARY/CONFIDENTIAL. 
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is public property; you can't redistribute it and/or modify it
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
package com.hbc.api.trade.ota.ctrip.req;

import com.hbc.api.trade.order.enums.ota.AgentChannelEnum;
import com.hbc.api.trade.ota.req.OrderCancelParam;

/**
 * @author Jongly Ran
 */
public class CTripOrderCancelParam{


	public String CtripPurchaseOrderID;	// 	携程订单号
	
	public String sign;		// 签名

    public Long timestamp ;	// 时间戳
    
    public String 	noncestr ;	// 随机字符串
    
	/**
	 * @return
	 */
	public OrderCancelParam toStandardOrderDetailBean() {
		OrderCancelParam param = new OrderCancelParam();
    	param.setServicePartner(AgentChannelEnum.CTRIP_CHANNEL.value);
    	param.setSign(sign);
    	param.setThirdTradeNo(CtripPurchaseOrderID);
		return param;
	}
}
