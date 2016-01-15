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
package com.hbc.api.trade.coupon.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hbc.api.trade.bdata.mapper.coup.gen.CouponBeanMapper;
import com.hbc.api.trade.bdata.mapper.coup.gen.bean.CouponBean;
import com.hbc.api.trade.bdata.mapper.coup.gen.bean.CouponBeanExample;

/**
 * @author Jongly Ran
 */
@Service
public class CouponService {
	private final static Logger logger = LoggerFactory.getLogger(CouponService.class);
	
	@Autowired private CouponBeanMapper couponBeanMapper;
	
	/**
	 * 
	 * @param orderNo
	 * @return 如果没有代金券，返回null
	 */
	public CouponBean getCouponByOrderNo(String orderNo) {
		// orderNo 外围已校验
		CouponBeanExample example = new CouponBeanExample();
		example.createCriteria().andOrderNoEqualTo(orderNo);
		List<CouponBean> couponList = couponBeanMapper.selectByExample(example );
		if(couponList != null) {
			if( couponList.size() >= 1) return couponList.get(0);
//			logger.error("每个订单仅可以使用一张优惠券，而此订单:" + orderNo + "的优惠券数为" + couponList.size());
//			throw new CouponException(CouponReturnCodeEnum.ONLY_ONE_FOR_ONCE);
		}
		return null;
	}
	
}
