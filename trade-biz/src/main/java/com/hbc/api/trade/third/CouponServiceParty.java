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
package com.hbc.api.trade.third;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hbc.api.trade.bdata.common.HttpClientService;
import com.hbc.api.trade.bdata.common.rsp.ReturnResult;
import com.hbc.api.trade.bdata.common.util.DoubleUtil;
import com.hbc.api.trade.bdata.mapper.coup.gen.CouponBeanMapper;
import com.hbc.api.trade.bdata.mapper.coup.gen.bean.CouponBean;
import com.hbc.api.trade.bdata.mapper.coup.gen.bean.CouponBeanExample;
import com.hbc.api.trade.bdata.mapper.coup.genx.WtCouponBeanMapper;
import com.hbc.api.trade.coupon.enums.CouponReturnCodeEnum;
import com.hbc.api.trade.coupon.exception.CouponException;
import com.hbc.api.trade.order.enums.third.CoupStatus;
import com.hbc.api.trade.order.mapping.gen.bean.OrderBean;
import com.hbc.api.trade.pay.exception.PayException;
import com.hbc.api.trade.pay.exception.PayReturnCodeEnum;
import com.hbc.api.trade.pay.mapping.gen.bean.TradePayment;
import com.hbc.api.trade.pay.service.PaymentService;
import com.hbc.api.trade.pay.service.refund.InnerRefundService;
import com.hbc.api.trade.settle.enums.AccountEnums;
import com.hbc.api.trade.third.restful.ThirdRESTful;
import com.hbc.api.trade.third.vo.CoupParm;
import com.hbc.api.trade.third.vo.CouponVo;

/**
 * @author Jongly Ran
 */
@Component
public class CouponServiceParty {
	@Autowired
	private HttpClientService httpClientService;
	@Autowired
	private ThirdRESTful thirdRESTful;
	@Autowired
	private WtCouponBeanMapper wtCouponBeanMapper;
	@Autowired
	private CouponBeanMapper couponBeanMapper;

	private final static Logger logger = LoggerFactory.getLogger(CouponServiceParty.class);

	@Value("${trade.pycoupUrl}")
	public String pyCoupUrl;

	@Value("${trade.coupReqUrl}")
	String coupReqUrl;

	public Integer getNewCouponSize(String userId) {
		try {
			String responseText = httpClientService.sendReq(thirdRESTful.TRADE_ORDER_MARKETING_COUPONS + "?userId=" + userId);
			logger.info(responseText);
			ReturnResult result = JSON.parseObject(responseText, ReturnResult.class); // not
																						// null
			if (result.getStatus() == 200) {
				return Integer.parseInt(result.getData().toString());
			}
		} catch (Exception e) {
			logger.error("用户获取新增优惠券失败，容错。", e);
		}
		return null;
	}

	public CouponVo getPYCoupByOrderDetail(String orderNo, double orderPrice, String userId) {
		String reqUrl = pyCoupUrl + "?useOrderNo=" + orderNo + "&useOrderPrice=" + orderPrice + "&userId=" + userId;
		try {
			String rspstr = httpClientService.sendReqNoEncode(reqUrl);
			JSONObject jobj = JSON.parseObject(rspstr);
			if (200 == jobj.getInteger("status")) {
				JSONObject datajobj = jobj.getJSONObject("data");
				if (datajobj != null && datajobj.containsKey("couponId")) {
					CouponVo couponVo = JSON.parseObject(datajobj.toJSONString(), CouponVo.class);
					return couponVo;
				}
			} else {
				logger.error("req is [" + reqUrl + "] rsp is [" + rspstr + "]");
			}
		} catch (Exception e) {
			logger.error("", e);
		}
		return null;
	}

	public CouponBean getCoup(String couponId) {
		return couponBeanMapper.selectByPrimaryKey(couponId);
	}

	@Autowired
	PaymentService paymentService;

	/**
	 * 解锁所有该订单 支付的优惠券
	 * 
	 * @param orderNo
	 */
	@Transactional
	public void unAlllockCoup(String orderNo, String coupId) {
//		List<TradePayment> tradePaymentList = paymentService.queryPaymentByOrderNoCoups(orderNo, PayStatus.INITED);
//		List<String> coupIds = new ArrayList<String>();
//		for (TradePayment tradePayment : tradePaymentList) {
//			if (tradePayment.getCoupId() != null && tradePayment.getCoupId().length() > 0) {
//				coupIds.add(tradePayment.getCoupId());
//			}
//		}

		CouponBean couponBean = new CouponBean();
		couponBean.setOrderNo("");
		couponBean.setStatus(CoupStatus.STATUS_BINDED.value);

		CouponBeanExample couponBeanExample = new CouponBeanExample();
		CouponBeanExample.Criteria criteria = couponBeanExample.createCriteria();

		// criteria.andCouponIdIn(coupIds);
		criteria.andStatusEqualTo(CoupStatus.STATUS_LOCKED.value);
		criteria.andOrderNoEqualTo(orderNo);
		criteria.andCouponIdNotEqualTo(coupId);
		int optnum = couponBeanMapper.updateByExampleSelective(couponBean, couponBeanExample);

		logger.info("解锁券 [" + coupId + "]" + optnum + " ");
	}

	/**
	 * 
	 * @param coupId
	 */
	@Transactional
	public void lockCoup(String orderNo, String coupId) {

		CouponBean ucoup = couponBeanMapper.selectByPrimaryKey(coupId);
		// 券 若是本订单锁的 则直接返回不做操作
		if (CoupStatus.STATUS_LOCKED.value.equals(ucoup.getStatus()) && orderNo.equals(ucoup.getOrderNo())) {
			return;
		} else if (CoupStatus.STATUS_LOCKED.value.equals(ucoup.getStatus()) && 
				(ucoup.getOrderNo() != null && ucoup.getOrderNo().length() > 0 && !orderNo.equals(ucoup.getOrderNo()))) {
			throw new CouponException(CouponReturnCodeEnum.FAILED_COUP_LOCKED, coupId+"已经被订单 "+ucoup.getOrderNo()+" 使用");
		}
		
		if(CoupStatus.STATUS_BINDED.value.equals(ucoup.getStatus()) && StringUtils.isEmpty(ucoup.getOrderNo())){
			unAlllockCoup(orderNo, coupId);

			CouponBean couponBeanSts = new CouponBean();
			couponBeanSts.setStatus(CoupStatus.STATUS_LOCKED.value);
			couponBeanSts.setOrderNo(orderNo);

			CouponBeanExample couponBeanExample = new CouponBeanExample();
			CouponBeanExample.Criteria criteria = couponBeanExample.createCriteria();

			criteria.andCouponIdEqualTo(coupId);
			criteria.andStatusEqualTo(CoupStatus.STATUS_BINDED.value);
			int optnum = couponBeanMapper.updateByExampleSelective(couponBeanSts, couponBeanExample);

			logger.info("锁订单 [" + coupId + "] 操作数：" + optnum + " ");
			if (optnum != 1) {
				throw new CouponException(CouponReturnCodeEnum.FAILED_COUP_LOCKED, coupId);
			}
		}else{
			throw new CouponException(CouponReturnCodeEnum.FAILED_COUP_LOCKED, coupId);
		}
	}

	@Transactional
	public void useCoup(String coupId, String orderNo) {
		CouponBean ucoup = couponBeanMapper.selectByPrimaryKey(coupId);
		if(CoupStatus.STATUS_LOCKED.value.equals(ucoup.getStatus()) && orderNo.equals(ucoup.getOrderNo())){
			unAlllockCoup(orderNo, coupId);

			CouponBean couponBeanSts = new CouponBean();
			couponBeanSts.setStatus(CoupStatus.STATUS_USED.value);

			CouponBeanExample couponBeanExample = new CouponBeanExample();
			CouponBeanExample.Criteria criteria = couponBeanExample.createCriteria();

			criteria.andCouponIdEqualTo(coupId);
			int optnum = couponBeanMapper.updateByExampleSelective(couponBeanSts, couponBeanExample);

			if (optnum != 1) {
				throw new CouponException(CouponReturnCodeEnum.FAILED_COUP_USE, coupId);
			}
		}else{
			throw new CouponException(CouponReturnCodeEnum.FAILED_COUP_USE, coupId);
		}
	}
	@Autowired
	InnerRefundService innerRefundService;
	public void refundCoup(String coupId,OrderBean orderBean,TradePayment tradePayment) {
		CouponBean couponBeanSts = new CouponBean();
		couponBeanSts.setStatus(CoupStatus.STATUS_BINDED.value);
		couponBeanSts.setOrderNo("");
		CouponBeanExample couponBeanExample = new CouponBeanExample();
		CouponBeanExample.Criteria criteria = couponBeanExample.createCriteria();

		criteria.andCouponIdEqualTo(coupId);
		criteria.andStatusEqualTo(CoupStatus.STATUS_USED.value);
		int optnum = couponBeanMapper.updateByExampleSelective(couponBeanSts, couponBeanExample);
		innerRefundService.refundToHBCAccount(AccountEnums.HBC_COUP, orderBean, tradePayment.getCoupPay());
		if (optnum != 1) {
			throw new CouponException(CouponReturnCodeEnum.FAILED_COUP_USE, coupId);
		}
		
//		int optnum = wtCouponBeanMapper.updateStatusByPrimaryKey(coupId, CoupStatus.STATUS_USED.value, CoupStatus.STATUS_BINDED.value);
//		if (optnum != 1) {
//			throw new CouponException(CouponReturnCodeEnum.FAILED_COUP_USE, coupId);
//		}
	}

	/**
	 * 
	 * @param couponId
	 * @param orderNo
	 * @param userId
	 * @param priceChannel
	 * @return
	 */
	public CoupParm getCoupPriceInfo(String couponId, String orderNo, String userId, double priceChannel) {
		String reqUrl = coupReqUrl + "?couponId=" + couponId + "&userId=" + userId + "&useOrderNo=" + orderNo + "&useOrderPrice=" + priceChannel;
		try {
			String coupInfo = httpClientService.sendReq(reqUrl);
			JSONObject jobject = JSON.parseObject(coupInfo);
			int status = jobject.getInteger("status");
			if (200 == status) {
				JSONObject dataobj = jobject.getJSONObject("data");
				JSONArray listData = dataobj.getJSONArray("listData");

				if (listData.size() == 0) {
					throw new PayException(PayReturnCodeEnum.PAY_COUP_FAILED, couponId);
				}
				double actualPrice = listData.getJSONObject(0).getDouble("actualPrice");
				String priceInfo = listData.getJSONObject(0).getString("priceInfo");

				actualPrice = DoubleUtil.getDoubleByTwo(actualPrice);
				CoupParm coupParm = new CoupParm();
				coupParm.setCoupId(couponId);
				coupParm.setCoupInfo(priceInfo);
				coupParm.setcActualPrice(actualPrice);
				return coupParm;
			} else {
				throw new PayException(PayReturnCodeEnum.PAY_COUP_BANGDCH_FAILED, couponId);
			}
		} catch (Exception e) {
			throw new PayException(PayReturnCodeEnum.PAY_COUP_FAILED, couponId);
		}
	}

}
