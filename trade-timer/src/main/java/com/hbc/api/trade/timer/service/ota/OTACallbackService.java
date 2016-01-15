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
package com.hbc.api.trade.timer.service.ota;

import com.hbc.api.trade.timer.service.ota.req.CallbackBean;

/**
 * @author Jongly Ran
 */
public interface OTACallbackService {

	/**
	 * 携程确认订单回调函数
	 * @param ctripConfirmOrder
	 */
	void confrimOrder(CallbackBean callbackBean);

	/**
	 * 司机应答，推送司机信息到携程
	 * @param driverAgree
	 */
	void pushDriverInfo(CallbackBean callbackBean);

	/**
	 * 订单完成时，推送消息给携程
	 * @param pushOrderComplete
	 */
	void pushWhenOrderCompleted(CallbackBean callbackBean);

}