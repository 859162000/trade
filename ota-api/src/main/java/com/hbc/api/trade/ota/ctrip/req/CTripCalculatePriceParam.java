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

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.hbc.api.trade.order.enums.order.OrderType;
import com.hbc.api.trade.order.enums.ota.AgentChannelEnum;
import com.hbc.api.trade.ota.req.CalculatePriceParam;

/**
 * CTrip API.
 * @author Jongly Ran
 */
public class CTripCalculatePriceParam {

    @NotBlank 
    public String 	sign;
    
	public Integer UseType ;						// 服务类型：3-海外接送机
	
	@NotNull
	public Integer PatternType ;					// 用车形态（1-接机，2-送机）
	
	public String 	VehicleTypeList ;				// carTypeId,车型ID列表（如不指定则返回该城市提供的所有车型信息）
	
	public String 	DuseLocationAddress;			// 出发地址
	public String 	DuseLocationDetailAddress ; 	// 出发详细地址

	@NotBlank 
	public String 	DuseLocationLongitude ; 		// 出发经度 startLocation end part
	
	@NotBlank 
	public String 	DuseLocationLatitude ; 			// 出发纬度 startLocation before part
	
    
    public String 	AuseLocationAddress ;			// 到达地址
    public String 	AuseLocationDetailAddress ;		// 到达详细地址

    @NotBlank 
    public String 	AuseLocationLatitude ; 			// 到达纬度 startLocation before part
    
    @NotBlank 
    public String 	AuseLocationLongitude ; 		// 到达经度 endLocation before part

    @NotBlank 
    public String 	UseTime ; 						// 用车时间（当地时间） serviceDate
    
    @NotBlank 
    public String 	FixedCode ;						// 机场三字码/火车站ID airportCode
    
    public Long timestamp ;

    public String 	noncestr ;
    
    
	/**
	 * @return the priceQueryBean
	 */
	public CalculatePriceParam toStandardCalculatePriceParam() {
		CalculatePriceParam priceQueryBean = new CalculatePriceParam();
		priceQueryBean.setFlightAirportCode(FixedCode);
		priceQueryBean.setServicePartner(AgentChannelEnum.CTRIP_CHANNEL.value);
		priceQueryBean.setServiceTime(UseTime);
		priceQueryBean.setStartAddressPoi(DuseLocationLatitude + "," + DuseLocationLongitude);
		priceQueryBean.setDestAddressPoi(AuseLocationLatitude + "," + AuseLocationLongitude);
		priceQueryBean.setCarTypeId(VehicleTypeList);
		priceQueryBean.setSign(sign);
		priceQueryBean.setOrderType(PatternType == 1 ? OrderType.PICKUPORDER.value : OrderType.TRANSFER.value);
		return priceQueryBean;
	}
    
    
}
