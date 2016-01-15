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
package com.hbc.api.trade.pay.service;

import java.sql.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.helper.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hbc.api.fund.account.enums.BizType;
import com.hbc.api.fund.account.service.FundAccountService;
import com.hbc.api.gateway.alizhifu.kafka.KafPayParam;
import com.hbc.api.trade.bdata.common.IDGenerotor;
import com.hbc.api.trade.bdata.common.util.TimeConverter;
import com.hbc.api.trade.order.enums.bean.OperationType;
import com.hbc.api.trade.order.enums.order.OrderLogType;
import com.hbc.api.trade.order.enums.order.OrderStatus;
import com.hbc.api.trade.order.enums.order.OrderType;
import com.hbc.api.trade.order.mapping.gen.bean.OrderBean;
import com.hbc.api.trade.order.mapping.genx.xbean.OrderLogParamBean;
import com.hbc.api.trade.order.service.OrderLogService;
import com.hbc.api.trade.order.service.OrderQueryService;
import com.hbc.api.trade.order.service.OrderService;
import com.hbc.api.trade.pay.enums.GetWayEnum;
import com.hbc.api.trade.pay.enums.PayStatus;
import com.hbc.api.trade.pay.exception.PayException;
import com.hbc.api.trade.pay.exception.PayReturnCodeEnum;
import com.hbc.api.trade.pay.mapping.gen.TradePaymentMapper;
import com.hbc.api.trade.pay.mapping.gen.bean.TradePayment;
import com.hbc.api.trade.pay.mapping.gen.bean.TradePaymentExample;
import com.hbc.api.trade.pay.mapping.gen.bean.TradePaymentExample.Criteria;
import com.hbc.api.trade.pay.mapping.genx.WtTradePaymentMapper;
import com.hbc.api.trade.settle.enums.AccountEnums;
import com.hbc.api.trade.settle.enums.FundBizEnumConvter;
import com.hbc.api.trade.third.CouponServiceParty;
import com.hbc.api.trade.third.TestPayVal;
import com.hbc.api.trade.third.sms.SMSService;

/**
 * @author Jongly Ran
 */
@Service
public class PaymentService {
	private static final Logger logger = LoggerFactory.getLogger(PaymentService.class);

	@Autowired private TradePaymentMapper 	tradePaymentMapper;
	@Autowired private WtTradePaymentMapper wtTradePaymentMapper;
	@Autowired private OrderService 		orderService;
	@Autowired private OrderQueryService 	orderQueryService;
	@Autowired private FundAccountService 	fundAccountService;
	@Autowired private OrderLogService 		orderLogService; 
	
	public TradePayment querySuccessTradePaymentByOrderNo(String orderNo) {
		TradePaymentExample tradePaymentExample = new TradePaymentExample();
		Criteria criteria = tradePaymentExample.createCriteria();
		criteria.andOrderNoEqualTo(orderNo);
		criteria.andPayStatusEqualTo(PayStatus.SUCCESS.value);
		List<TradePayment> tradePayments = tradePaymentMapper.selectByExample(tradePaymentExample);
		if(tradePayments==null || tradePayments.size()==0){
			logger.error("订单号：" + orderNo + "，在TradePayment表里无对应的支付信息");
			throw new PayException(PayReturnCodeEnum.PAY_NOT_EXIST,orderNo);
		}else if(tradePayments.size()>1){
			logger.error("订单号：" + orderNo + "，在TradePayment表里存在多个支付信息");
			throw new PayException(PayReturnCodeEnum.PAY_DOUBLE,orderNo);
		}
		return tradePayments.get(0);
	}
	
	public TradePayment queryTradePaymentByOrderNo(String orderNo,PayStatus payStatus) {
		TradePaymentExample tradePaymentExample = new TradePaymentExample();
		Criteria criteria = tradePaymentExample.createCriteria();
		criteria.andOrderNoEqualTo(orderNo);
		criteria.andPayStatusEqualTo(payStatus.value);
		List<TradePayment> tradePayments = tradePaymentMapper.selectByExample(tradePaymentExample);
		if(tradePayments==null || tradePayments.size()==0){
			logger.error("订单号：" + orderNo + "，在TradePayment表里无对应的支付信息");
			throw new PayException(PayReturnCodeEnum.PAY_NOT_EXIST,orderNo);
		}else if(tradePayments.size()>1){
			logger.error("订单号：" + orderNo + "，在TradePayment表里存在多个支付信息");
			throw new PayException(PayReturnCodeEnum.PAY_DOUBLE,orderNo);
		}
		return tradePayments.get(0);
	}
	
	
	public TradePayment queryTradePaymentByOrderNoNoexp(String orderNo,PayStatus payStatus) {
		TradePaymentExample tradePaymentExample = new TradePaymentExample();
		Criteria criteria = tradePaymentExample.createCriteria();
		criteria.andOrderNoEqualTo(orderNo);
		criteria.andPayStatusEqualTo(payStatus.value);
		List<TradePayment> tradePayments = tradePaymentMapper.selectByExample(tradePaymentExample);
		if(tradePayments==null || tradePayments.size()==0){
			return null;
		}else if(tradePayments.size()>=1){
			return tradePayments.get(0);
		}
		return null;
	}
	
	

	public List<TradePayment> queryPaymentByOrderNoCoups(String orderNo,PayStatus payStatus) {
		TradePaymentExample tradePaymentExample = new TradePaymentExample();
		Criteria criteria = tradePaymentExample.createCriteria();
		criteria.andOrderNoEqualTo(orderNo);
		criteria.andPayStatusEqualTo(payStatus.value);
		criteria.andCoupIdIsNotNull();
		List<TradePayment> tradePayments = tradePaymentMapper.selectByExample(tradePaymentExample);
		return tradePayments;
	}
	
	public TradePayment queryTradePaymentByPayNo(String payNo) {
		TradePayment tradePayment = tradePaymentMapper.selectByPrimaryKey(payNo);
		return tradePayment;
	}
	
	
	public TradePayment queryByTradeNo(String tradeNo) {
		TradePaymentExample tradePaymentExample = new TradePaymentExample();
		Criteria criteria = tradePaymentExample.createCriteria();
		criteria.andThirdPayNoEqualTo(tradeNo);
		List<TradePayment> tradePayments = tradePaymentMapper.selectByExample(tradePaymentExample);
		if(tradePayments==null || tradePayments.size()==0){
			logger.error("第三方交易号：" + tradeNo + "，在TradePayment表里无对应的支付信息");
			throw new PayException(PayReturnCodeEnum.PAY_NOT_EXIST,"第三方交易号：" + tradeNo + "，在TradePayment表里无对应的支付信息");
		}else if(tradePayments.size()>1){
			logger.error("订单号：" + tradeNo + "，在TradePayment表里存在多个支付信息");
			throw new PayException(PayReturnCodeEnum.PAY_DOUBLE,"订单号：" + tradeNo + "，在TradePayment表里存在多个支付信息");
		}
		return tradePayments.get(0);
	}
	/**
	 * 根据第三方交易号查询支付信息，在不存在情况下 不会抛出异常
	 * @param tradeNo
	 * @return
	 */
	public TradePayment queryByTradeNoNoException(String tradeNo) {
		TradePaymentExample tradePaymentExample = new TradePaymentExample();
		Criteria criteria = tradePaymentExample.createCriteria();
		criteria.andThirdPayNoEqualTo(tradeNo);
		List<TradePayment> tradePayments = tradePaymentMapper.selectByExample(tradePaymentExample);
		if(tradePayments==null || tradePayments.size()==0){
			return null;
		}
		return tradePayments.get(0);
	}
	@Transactional
	public void updatePaymentStatus(String tradeNo,PayStatus payStatus) {
		TradePaymentExample tradePaymentExample = new TradePaymentExample();
		Criteria criteria = tradePaymentExample.createCriteria();
		criteria.andThirdPayNoEqualTo(tradeNo);
		List<TradePayment> tradePayments = tradePaymentMapper.selectByExample(tradePaymentExample);
		if(tradePayments==null || tradePayments.size()==0){
			logger.error("第三方交易号：" + tradeNo + "，在TradePayment表里无对应的支付信息");
			throw new PayException(PayReturnCodeEnum.PAY_NOT_EXIST,"第三方交易号：" + tradeNo + "，在TradePayment表里无对应的支付信息");
		}else if(tradePayments.size()>1){
			logger.error("订单号：" + tradeNo + "，在TradePayment表里存在多个支付信息");
			throw new PayException(PayReturnCodeEnum.PAY_DOUBLE,"订单号：" + tradeNo + "，在TradePayment表里存在多个支付信息");
		}
		
		TradePayment tradePayment = tradePayments.get(0);
		OrderBean orderBean = orderQueryService.getOrderByNo(tradePayment.getOrderNo());
		tradePayment.setPayStatus(payStatus.value);
		wtTradePaymentMapper.updatePayStatus(tradePayment);
		if(payStatus.equals(PayStatus.SUCCESS)){
			OrderStatus orderStatus = OrderStatus.getStatus(orderBean.getOrderStatus());
			if(orderStatus.equals(OrderStatus.INITSTATE)){
				orderService.updateOrderStatus(tradePayment.getOrderNo(),OrderStatus.INITSTATE, OrderStatus.PAYSUCCESS);
			}
		}
	}
	@Autowired
	TestPayVal testPayVal;
	@Autowired
	SMSService smsService;
	@Autowired
	CouponServiceParty couponServiceParty;
	/**
	 * 支付回调函数
	 * @param payParam
	 * @param msg
	 */
	@Transactional
	public void payNotifyPayment(KafPayParam payParam,String msg){
		String payNo = payParam.getOut_trade_no();
		TradePayment tradePayment = queryTradePaymentByPayNo(payNo);
		OrderBean orderBean = orderQueryService.getOrderByNo(tradePayment.getOrderNo());
		
//		tradePayment.setPayActual(Double.valueOf(payParam.getPrice()));
		tradePayment.setPayDesc(payParam.getBody());
//		tradePayment.setPayFee(0.00);
		tradePayment.setPayTime(TimeConverter.toDate(payParam.getGmt_create()));
		tradePayment.setThirdNotifyLog(msg);
		tradePayment.setThirdNotifyStatus(payParam.getTrade_status());
		tradePayment.setThirdPayNo(payParam.getTrade_no());
		tradePayment.setUserPayAccount(payParam.getBuyer_email());
		
		if("TRADE_SUCCESS".equalsIgnoreCase(payParam.getTrade_status())){
			// TODO 先放开，不校验。对接优惠券
			//if(orderBean.getPriceChannel().equals(Double.valueOf(payParam.getPrice())) && OrderStatus.INITSTATE.value==orderBean.getOrderStatus()){
			if(OrderStatus.INITSTATE.value.equals(orderBean.getOrderStatus())){
				boolean isVal = tradePayment.getPayActual().equals(Double.valueOf(payParam.getPrice()));
				if(testPayVal.isTestByBeijing(orderBean)){
					isVal = true;
				}
				if(isVal){
					tradePayment.setPayStatus(PayStatus.SUCCESS.value);
					wtTradePaymentMapper.updateByPrimaryKeyWithBLOBs(tradePayment);
					fundAccountService.pay(orderBean.getUserAccount(), Double.valueOf(tradePayment.getPayActual()));
					fundAccountService.recharge(orderBean.getUserAccount(), Double.valueOf(tradePayment.getPayActual()));
					
					OrderType orderType = OrderType.getType(orderBean.getOrderType());
					BizType bizType =	FundBizEnumConvter.getFundBizType(orderType);
					
					String coupId = tradePayment.getCoupId();
					orderService.updateOrderStatus(tradePayment.getOrderNo(),OrderStatus.INITSTATE, OrderStatus.PAYSUCCESS);
					if(!StringUtils.isEmpty(coupId)){
						orderService.updateCoupInfos(orderBean, coupId, tradePayment);
						couponServiceParty.useCoup(coupId, orderBean.getOrderNo());
						//券账户加钱
						fundAccountService.pay(AccountEnums.HBC_COUP.value, tradePayment.getCoupPay(), bizType, orderBean.getOrderNo());
					}
					//担保账户加钱
					fundAccountService.pay(AccountEnums.HBC_Guarantee.value, orderBean.getPriceChannel(), bizType, orderBean.getOrderNo());
					
					OrderLogParamBean orderLogParamBean = new OrderLogParamBean();
					orderLogParamBean.setContent(OrderLogType.PAYMENT_RESULT_CONTENT(orderBean.getAgentName(), orderBean.getUserName(), payParam.getGmt_create(), "支付宝支付 ："+tradePayment.getPayActual()));
					orderLogParamBean.setLogType(OrderLogType.PAYMENT_RESULT.type);
					orderLogParamBean.setOpType(OperationType.CUSTOMER.value);
					orderLogParamBean.setOpUserId(orderBean.getUserId());
					orderLogParamBean.setOpUserName(orderBean.getUserName());
					orderLogParamBean.setOrderNo(orderBean.getOrderNo());
					orderLogService.insertOrderLog(orderLogParamBean );
					
					smsService.paySuccessSms(orderBean);
				}else{
					logger.error(tradePayment.getOrderNo()+" 支付金额异常，请迅速联系客户");
				}
			}else{
				logger.error(tradePayment.getOrderNo()+" 支付金额异常，请迅速联系客户");
			}
		}else{
			if("TRADE_CLOSED".equalsIgnoreCase(payParam.getTrade_status())){
//				tradePayment.setPayStatus(PayStatus.FAILED.value);
//				tradePaymentMapper.updateByPrimaryKey(tradePayment);
				logger.error(tradePayment.getOrderNo()+" 支付宝 TRADE_CLOSED 通知");
			}else{
				logger.info(tradePayment.getOrderNo()+" 等待用户付款");
			}
		}
	}
	
	
	public TradePayment addTradePayment(OrderBean orderBean,GetWayEnum getWayNo,double payActual,String paySubject,String userAccountNo,PayStatus payStatus){
		
//		TradePayment tradePaymentDb = queryTradePaymentByOrderNo(orderBean.getOrderNo());
//		if(tradePaymentDb){
//			
//		}
		TradePayment tradePayment = new TradePayment();
		Date curtime = new Date(System.currentTimeMillis());
		tradePayment.setCreateTime(curtime);
		tradePayment.setOrderNo(orderBean.getOrderNo());
		tradePayment.setOrderPrice(orderBean.getPriceChannel());
		tradePayment.setPayActual(payActual);
		tradePayment.setPayFee(0.00);
		tradePayment.setPayNo(IDGenerotor.generatePayNo());
		tradePayment.setPaySubject(paySubject);
		tradePayment.setPayShould(orderBean.getPriceChannel());
		tradePayment.setPayGetway(getWayNo.value);
		tradePayment.setPayGatewayName(getWayNo.name);
		tradePayment.setUserAccountNo(userAccountNo);
		
		tradePayment.setPayStatus(payStatus.value);
		
		int optnum = tradePaymentMapper.insert(tradePayment);
		if(optnum==1){
			return tradePayment;
		}else{
			throw new PayException(PayReturnCodeEnum.PAY_INSERT_FAILED,orderBean.getOrderNo());
		}
	}
	
	public void updCoupInfo(String payNo,String coupId,Double cpay,String cinfo){
		TradePayment tradePayment = new TradePayment();
		tradePayment.setPayNo(payNo);
		tradePayment.setCoupId(coupId);
		tradePayment.setCoupPay(cpay);
		tradePayment.setCouponInfo(cinfo);
		int optnum = tradePaymentMapper.updateByPrimaryKeySelective(tradePayment);
		if(optnum==1){
		}else{
			throw new PayException(PayReturnCodeEnum.PAY_COUP_FAILED,coupId);
		}
	}
	
	
}
