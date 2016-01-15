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
package com.hbc.api.trade.ota.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.hbc.api.trade.TradeFinalStr;
import com.hbc.api.trade.bdata.common.TradeConstant;
import com.hbc.api.trade.bdata.common.exception.ReturnCode;
import com.hbc.api.trade.bdata.common.redis.RedisDao;
import com.hbc.api.trade.bdata.mapper.basedata.gen.CityBeanMapper;
import com.hbc.api.trade.bdata.mapper.basedata.gen.bean.AirportBean;
import com.hbc.api.trade.bdata.mapper.basedata.gen.bean.CityBean;
import com.hbc.api.trade.bdata.mapper.basedata.gen.bean.CityBeanExample;
import com.hbc.api.trade.order.enums.TradeReturnCodeEnum;
import com.hbc.api.trade.order.enums.order.OrderSource;
import com.hbc.api.trade.order.enums.order.OrderStatus;
import com.hbc.api.trade.order.enums.order.OrderType;
import com.hbc.api.trade.order.enums.ota.AgentChannelEnum;
import com.hbc.api.trade.order.exception.TradeException;
import com.hbc.api.trade.order.mapping.gen.OrderBeanMapper;
import com.hbc.api.trade.order.mapping.gen.bean.OrderBean;
import com.hbc.api.trade.order.mapping.gen.bean.OrderBeanExample;
import com.hbc.api.trade.order.mapping.genx.UpdateOrderBeanMapper;
import com.hbc.api.trade.order.service.OrderQueryService;
import com.hbc.api.trade.order.service.OrderService;
import com.hbc.api.trade.order.service.OrderTrackService;
import com.hbc.api.trade.order.service.UpdateOrderService;
import com.hbc.api.trade.ota.enums.QunarErrorCode;
import com.hbc.api.trade.ota.enums.ThirdOrderStatus;
import com.hbc.api.trade.ota.mapping.gen.TradeThirdOrderMapper;
import com.hbc.api.trade.ota.mapping.gen.bean.TradeThirdOrder;
import com.hbc.api.trade.ota.mapping.gen.bean.TradeThirdOrderExample;
import com.hbc.api.trade.ota.mapping.gen.bean.TradeThirdOrderLogWithBLOBs;
import com.hbc.api.trade.ota.mapping.genx.UpdateTradeThirdOrderMapper;
import com.hbc.api.trade.ota.po.CityPo;
import com.hbc.api.trade.ota.req.CalculatePriceParam;
import com.hbc.api.trade.ota.req.OrderCancelParam;
import com.hbc.api.trade.ota.req.OrderDetailParam;
import com.hbc.api.trade.ota.req.OrderModifyParam;
import com.hbc.api.trade.ota.req.OrderSubmitParam;
import com.hbc.api.trade.ota.resp.OrderCancelResult;
import com.hbc.api.trade.ota.resp.OrderPriceInfo;
import com.hbc.api.trade.pay.enums.GetWayEnum;
import com.hbc.api.trade.pay.mapping.gen.bean.TradePayment;
import com.hbc.api.trade.pay.service.OTAPaymentService;
import com.hbc.api.trade.pay.service.PaymentService;
import com.hbc.api.trade.pay.service.RefundService;
import com.hbc.api.trade.pay.service.refund.OTARefundService;
import com.hbc.api.trade.third.LControllerService;

/**
 * 
 * Qua API. 
 * Standard interface 
 * 
 * @author Jongly Ran
 */
@Service
public class StandardOrderService implements OTAOrderService{
	private final static Logger log = LoggerFactory.getLogger(StandardOrderService.class);
	
	@Autowired private ThirdAPIService 			thirdAPIService;
	@Autowired private TradeThirdOrderMapper 	tradeThirdOrderMapper;
    @Autowired private OrderService 			orderService ;
    @Autowired private OrderBeanMapper 			orderBeanMapper;
	@Autowired private UpdateOrderBeanMapper 	updateOrderBeanMapper;
    @Autowired private OrderQueryService 		orderQueryService ;
    @Autowired private ThirdOrderLogService 	thirdOrderLogService ;
    @Autowired private OTARefundService 		otaRefundService;
    @Autowired private RefundService			refundService;
    @Autowired private OTAPaymentService 		otaPaymentService;
	@Autowired private CityBeanMapper 			cityBeanMapper ;
	@Autowired private UpdateOrderService 		updateOrderService;
	@Autowired private PaymentService			paymentService;
	@Autowired private RedisDao					redisDao;
    @Autowired private LControllerService		controllerService;
	@Autowired private UpdateTradeThirdOrderMapper updateTradeThirdOrderMapper;
	@Autowired private OrderTrackService		orderTrackService;
	
	public OrderPriceInfo getPrice(CalculatePriceParam param) {
		Map<String, String > params = buildQueryParam(param);
		try {
			OrderPriceInfo result = thirdAPIService.getPriceFromAPI(params, OrderType.getType(param.getOrderType()));
			return result;
		} catch(Exception e) {
			log.error("查价失败。", e);
		}
		return null;
	}

	private Map<String, String> buildQueryParam(CalculatePriceParam param) {
		log.info("查价参数处理前：" + JSON.toJSONString(param));
		String startAddressPoi = param.getStartAddressPoi();
		String destAddressPoi = param.getDestAddressPoi();
		//去掉末尾的0
		String[] startLocations = startAddressPoi.split(TradeConstant.SPLITER_COMMA);
		String[] endLocations = destAddressPoi.split(TradeConstant.SPLITER_COMMA);
		startAddressPoi = Double.valueOf(startLocations[0]) + TradeConstant.SPLITER_COMMA +  Double.valueOf(startLocations[1]);
		destAddressPoi = Double.valueOf(endLocations[0]) + TradeConstant.SPLITER_COMMA +  Double.valueOf(endLocations[1]);
		
		Map<String, String> params = new HashMap<String, String>();
		params.put("channelId", String.valueOf(param.getServicePartner())) ;
		params.put("airportCode", param.getFlightAirportCode());
		params.put("serviceDate", param.getServiceTime());
		params.put("startLocation", startAddressPoi);
		params.put("endLocation", destAddressPoi);
		params.put("serviceType", param.getOrderType().toString());
		params.put("assitCheckIn", "0"); // TODO 第二版 是否要checkIn
		params.put("sign", param.getSign());
		if(param.getDistance() != null) {
			params.put("distance", param.getDistance()+"");
		}
		if(param.getCarTypeId() != null) {
			params.put("carIds", param.getCarTypeId());
		}
		log.info("标准查价参数：" + JSON.toJSONString(params));
		return params;
	}
	public TradeThirdOrder getThirdOrderBythirdTradeNo(Integer partner, String thirdTradeNo){
		TradeThirdOrderExample tradeThirdOrderExample = new TradeThirdOrderExample();
		TradeThirdOrderExample.Criteria criteria = tradeThirdOrderExample.createCriteria();
		criteria.andThirdPartnerEqualTo(partner);
		criteria.andThirdTradeNoEqualTo(thirdTradeNo);
		List<TradeThirdOrder> tOrders = tradeThirdOrderMapper.selectByExample(tradeThirdOrderExample);
		if(tOrders == null || tOrders.size()==0){
			OrderBeanExample orderBeanExample = new OrderBeanExample();
			orderBeanExample.createCriteria().andThirdTradeNoEqualTo(thirdTradeNo);
			List<OrderBean> olist = orderBeanMapper.selectByExample(orderBeanExample);
			if(olist.size()==1){
				TradeThirdOrder tradeThirdOrder = tradeThirdOrderMapper.selectByPrimaryKey(olist.get(0).getOrderNo());
				return tradeThirdOrder;
			}
			return null;
		}else{
			return tOrders.get(0);
		}
	}
	

	/**
	 * @param param
	 * @return
	 */
	public OrderBean getOrderDetail(OrderDetailParam param) {
		TradeThirdOrder tradeThirdOrder = getThirdOrderBythirdTradeNo(param.getServicePartner(), param.getThirdTradeNo());
		String orderNo = tradeThirdOrder.getOrderNo();
		OrderBean orderBean = orderQueryService.getOrderByNo(orderNo) ;
        if (orderBean == null) {
        	log.error("订单不存在，查询条件：" + JSON.toJSONString(param) + ", HBC订单号：orderNo=" + orderNo);
			throw new TradeException(new ReturnCode() {
				@Override public String getMessage() { return QunarErrorCode.ORDER_NOT_FOUND.name(); }
				@Override public int getCode() { return QunarErrorCode.ORDER_NOT_FOUND.code(); }
			});
        }
        return orderBean;
	}
	
	/**
	 * @param param
	 * @return
	 */
	@Transactional
	public OrderBean createOrder(OrderSubmitParam param) {
		if(param.getOrderBean()!=null && param.getOrderBean().getThirdTradeNo()!=null
				&& param.getOrderBean().getOrderChannel()!=null){
			String tno = param.getOrderBean().getThirdTradeNo();
			OrderBeanExample orderBeanExample = new OrderBeanExample();
			orderBeanExample.createCriteria().andThirdTradeNoEqualTo(tno).andOrderChannelEqualTo(param.getOrderBean().getOrderChannel());
			List<OrderBean> olist = orderBeanMapper.selectByExample(orderBeanExample);
			
			if(olist!=null && olist.size()>0){
				log.info(tno+"已经成功下单，兼容直接返回");
				return olist.get(0);
			}
		}
		Timestamp curtime = new Timestamp(System.currentTimeMillis());
		OrderBean orderBean = addTradeOrder(param, curtime);
		addThirdOrder(param, orderBean, curtime);
		insertPayment(param, curtime);
		copyToRedis(param.getThirdOrderBean());
	    return orderBean;
	}
	
	/**
	 * 加入Redis Queue
	 * @param tradeThirdOrder
	 */
	private void copyToRedis(TradeThirdOrder tradeThirdOrder) {
		try {
			// TODO 优化
			redisDao.lpush(TradeFinalStr.orderConfirmQname, JSON.toJSONString(tradeThirdOrder));
		} catch(Exception e) {
			log.error("Redis server异常。", e);
			throw new TradeException(new ReturnCode() {
				@Override public String getMessage() { return QunarErrorCode.SERVER_ERR.value(); }
				@Override public int getCode() { return QunarErrorCode.SERVER_ERR.code(); }
			});
		}
	}

	@Transactional
	private void insertPayment(OrderSubmitParam param, Timestamp curtime) {
		OrderBean orderBean = param.getOrderBean();
		String subject	 = null;
		String postfix   = "订单支付";
		AgentChannelEnum channel = AgentChannelEnum.getType(orderBean.getOrderChannel());
		switch(channel) {
		case CTRIP_CHANNEL:
			subject  = GetWayEnum.CTRIP.name + postfix;
			break;
		case QUA_CHANNEL:
			subject  = GetWayEnum.QUA.name + postfix;
			break;
		case QUNAR_CHANNEL:
			subject  = GetWayEnum.QUNAR.name + postfix;
			break;
		default:
			log.error("渠道下单的支付方式不支持：channel=" + channel);
			throw new TradeException(new ReturnCode() {
				@Override public String getMessage() { return QunarErrorCode.ERR_PARAM.name(); }
				@Override public int getCode() { return QunarErrorCode.ERR_PARAM.code(); }
			});
		}
		
		try {
			otaPaymentService.payOTA(orderBean, orderBean.getPriceChannel(),subject, AgentChannelEnum.getType(orderBean.getOrderChannel()));
		} catch(Exception e) {
			log.error("内部账户支付异常。", e);
			throw new TradeException(new ReturnCode() {
				@Override public String getMessage() { return QunarErrorCode.SERVER_ERR.value(); }
				@Override public int getCode() { return QunarErrorCode.SERVER_ERR.code(); }
			});
		}
	}

	@Transactional
	private OrderBean addTradeOrder(OrderSubmitParam param, Timestamp curtime) {
		log.info("OTA下单，StandardService参数适配结果：" + JSON.toJSONString(param));

		OrderBean orderBean = param.getOrderBean();
		OrderBeanExample example = new OrderBeanExample();
		example.createCriteria().andThirdTradeNoEqualTo(orderBean.getThirdTradeNo()).andOrderChannelEqualTo(orderBean.getOrderChannel());
		if(orderBeanMapper.countByExample(example ) > 0) {
			log.error("订单已存在，不可重复下单。");
			throw new TradeException(new ReturnCode() {
				@Override public String getMessage() { return QunarErrorCode.CANNOT_CREATE_THE_SAME_ORDER.name(); }
				@Override public int getCode() { return QunarErrorCode.CANNOT_CREATE_THE_SAME_ORDER.code(); }
			});
		}
		
		TradeThirdOrder thirdOrderBean = param.getThirdOrderBean();
		orderBean.setPriceBase(thirdOrderBean.getPrice());
		orderBean.setPriceChannel(thirdOrderBean.getPrice());
		orderBean.setPriceGuide(thirdOrderBean.getPrice());
		orderBean.setPriceGuideBase(thirdOrderBean.getPrice());
		orderBean.setPriceTicket(thirdOrderBean.getPrice());
		orderBean.setCreateTime(curtime);
		orderBean.setUpdateTime(curtime);
		orderBean.setCheckInPrice(orderBean.getCheckInPrice() == null ? 0.0 : orderBean.getCheckInPrice());
		String flightCode = orderBean.getFlightAirportCode() == null ? orderBean.getFlightDestCode() : orderBean.getFlightAirportCode();
		AirportBean airportBean = controllerService.getAirportByCode(flightCode); // validated
		orderBean.setServiceCityId(airportBean.getCityId());
		orderBean.setOrderSource(OrderSource.OTA.value);
		String orderNo = orderService.addOrderProccess(orderBean); // validated
		orderBean.setOrderNo(orderNo);
		log.info("OTA新订单下单成功，结果OrderBean：" + JSON.toJSONString(orderBean));
		return orderBean;
	}

	@Transactional
	private void addThirdOrder(OrderSubmitParam inputs, OrderBean orderBean, Timestamp curtime) {
		TradeThirdOrder tradeThirdOrder = inputs.getThirdOrderBean();
		tradeThirdOrder.setPriceMark(orderBean.getPriceMark());
		tradeThirdOrder.setCreateTime(curtime);
		tradeThirdOrder.setOrderNo(orderBean.getOrderNo());
		tradeThirdOrder.setOrderStatus(ThirdOrderStatus.PAYSUCCESS.value);
		tradeThirdOrderMapper.insert(tradeThirdOrder);
		log.info("insert trade_trird_order [" + tradeThirdOrder + "]");
	}

	/**
	 * 2015-12-29，废弃，by 贺伟。用log文件代替。
	 * @param param
	 * @param orderNo
	 * @param curtime
	 */
	@Deprecated
	@Transactional
	private void addThirdOrderLogs(OrderSubmitParam param, String orderNo, Timestamp curtime) {
		OrderBean orderBean = param.getOrderBean();
		TradeThirdOrderLogWithBLOBs tradeThirdOrderLogWithBLOBs = new TradeThirdOrderLogWithBLOBs();
		tradeThirdOrderLogWithBLOBs.setCreateTime(curtime);
		tradeThirdOrderLogWithBLOBs.setThirdPartner(Integer.parseInt(orderBean.getAgentId()));
		tradeThirdOrderLogWithBLOBs.setThirdTradeNo(orderBean.getThirdTradeNo());
		tradeThirdOrderLogWithBLOBs.setLogType("orderSubmit");
		tradeThirdOrderLogWithBLOBs.setRequestUrl("v1.0/orderSubmit");
		tradeThirdOrderLogWithBLOBs.setRequestData(JSON.toJSONString(param));
		tradeThirdOrderLogWithBLOBs.setOrderNo(orderNo);
		tradeThirdOrderLogWithBLOBs.setResponseData(JSON.toJSONString(orderBean));
		
		try{
			thirdOrderLogService.addOrderLog(tradeThirdOrderLogWithBLOBs);
		} catch(Exception e) {
			log.error("OTA下单LOG【TradeThirdOrderLogWithBLOBs-"+orderNo+"】入库失败，容错。参数：" + JSON.toJSONString(tradeThirdOrderLogWithBLOBs), e);
		}
	}
	
	@Transactional
    public OrderCancelResult cancelThirdOrder(OrderCancelParam param) {
		TradeThirdOrder tradeThirdOrder = getThirdOrderBythirdTradeNo(param.getServicePartner(), param.getThirdTradeNo()) ;
		if(tradeThirdOrder == null) {
			log.info("没下单成功的，无取消操作。");
			throw new TradeException(new ReturnCode() {
				@Override public String getMessage() { return QunarErrorCode.SUCCESS.name(); }
				@Override public int getCode() { return QunarErrorCode.SUCCESS.code(); }
			});
		}
        String orderNo = tradeThirdOrder.getOrderNo();
        OrderBean orderBean = updateOrderBeanMapper.forupdateByOrderNo(orderNo);
        try{
        	updateOrderStatus(orderNo, orderBean);
        } catch(Exception e) {
        	log.error("", e);
        }
        
        try {
			updateThirdOrderStatus(orderNo, ThirdOrderStatus.ORDER_CANCEL);
			
	    	TradePayment tradePayment = paymentService.querySuccessTradePaymentByOrderNo(orderNo);
	    	Double ruserPrice = refundService.getRefundAmount(orderNo);
	    	Double cancelFee = refundService.getCancelFee(orderNo);
	    	otaRefundService.refundToUserAccount(orderBean, tradePayment, ruserPrice, param.getReason(), AgentChannelEnum.getType(orderBean.getOrderChannel()));
	    	OrderCancelResult result = new OrderCancelResult();
	    	result.setOrderNo(orderNo);
	    	result.setThirdTradeNo(orderBean.getThirdTradeNo());
	    	result.setRefundable(ruserPrice);
	    	result.setCancelFee(cancelFee);
	    	
	    	try {
	    		orderTrackService.cancelled(orderNo);
	    	}catch(Exception e) {
	    		log.error("记录取消订单DB级日志(订单动态)失败，容错。orderNo="+orderNo, e);
	    	}
	    	
	    	return result;
        } catch(Exception e) {
        	log.error("OTA["+ orderBean.getOrderChannel() + "]取消订单失败，输入：" + JSON.toJSONString(orderBean), e);
        	throw new TradeException(TradeReturnCodeEnum.FAILED_FOR_UPDATE, "取消订单");
        }
    }

	@Transactional
	private void updateThirdOrderStatus(String orderNo, ThirdOrderStatus orderStatus) {
		TradeThirdOrder tradeThirdOrder = updateTradeThirdOrderMapper.forupdateById(orderNo);
		if (tradeThirdOrder == null) {
			log.error("订单不存在，订单orderNo：" + orderNo);
			throw new TradeException(new ReturnCode() {
				@Override public String getMessage() { return QunarErrorCode.ORDER_NOT_FOUND.name(); }
				@Override public int getCode() { return QunarErrorCode.ORDER_NOT_FOUND.code(); }
			});
		}
		tradeThirdOrder.setOrderStatus(orderStatus.value);
		int ors = updateTradeThirdOrderMapper.updateStatusByPrimaryKey(tradeThirdOrder);
		if (ors > 0) {
			log.info("订单更新成功，where条件：orderNo：" + orderNo + ", oldOrderStatus: " + tradeThirdOrder.getOrderStatus() + ", newOrderStatus:" + orderStatus.value);
		} else {
			log.error("订单未更新，where条件：orderNo：" + orderNo + ", oldOrderStatus: " + tradeThirdOrder.getOrderStatus() + ", newOrderStatus:" + orderStatus.value);
			throw new TradeException(new ReturnCode() {
				@Override public String getMessage() { return QunarErrorCode.NOT_RESPONSED.name(); }
				@Override public int getCode() { return QunarErrorCode.NOT_RESPONSED.code(); }
			});
		}
		log.info(orderNo + " end to update orderstatus to [" + orderStatus.value + "]");
    }

	/**
	 * @param orderNo
	 * @param orderBean
	 */
	private void updateOrderStatus(String orderNo, OrderBean orderBean) {
		if(orderBean == null) {
			log.error("订单不存在，订单orderNo：" + orderNo);
			throw new TradeException(new ReturnCode() {
				@Override public String getMessage() { return QunarErrorCode.ORDER_NOT_FOUND.name(); }
				@Override public int getCode() { return QunarErrorCode.ORDER_NOT_FOUND.code(); }
			});
		}
		
		OrderStatus orderMainStatus = OrderStatus.getStatus(orderBean.getOrderStatus());
		switch(orderMainStatus) {
		case CANCEL_CLOSE:
		case CANCEL_NOSERVICE:
		case CANCEL_SERVICEED:
		case CANCELING:
		case CANCLE_CLOSE_NOPAY:
		case CANCLE_CLOSE_PAY_SERVICE:
			return;
		default:
			OrderBean record = new OrderBean();
			record.setOrderStatus(OrderStatus.CANCELING.value);
			record.setOrderStatusName(OrderStatus.CANCELING.name);
			record.setOrderNo(orderNo);
			if (updateOrderBeanMapper.updateStatusByPrimaryKey(record ) > 0) {
				log.info("取消订单成功:" + JSON.toJSONString(record));
			} else {
				log.error("取消订单失败");
				throw new TradeException(new ReturnCode() {
					@Override public String getMessage() { return QunarErrorCode.NOT_RESPONSED.name(); }
					@Override public int getCode() { return QunarErrorCode.NOT_RESPONSED.code(); }
				});
			}
		}
	}
	
    public List<CityPo> getServiceCitys() {
        CityBeanExample cityBeanExample = new CityBeanExample();
        List<CityBean> cityBeanList = cityBeanMapper.selectByExample(cityBeanExample);
        List<CityPo> cityList = new ArrayList<CityPo>();
        for (int i=0;i<cityBeanList.size();i++){
        	CityBean cityBean = cityBeanList.get(i);
        	
        	CityPo cityPo = new CityPo();
        	cityPo.setServiceCityId(cityBean.getCityId().toString()) ;
        	cityPo.setServiceCityName(cityBean.getCityName()) ;
        	cityPo.setServiceCountryId(cityBean.getCountryId().toString());
        	cityPo.setServiceCountryName(cityBean.getCountryName());
        	cityList.add(cityPo);
        }
        return cityList ;
    }

	/**
	 * @param param
	 */
    @Transactional
	public void updateOrder(OrderModifyParam param) {
        TradeThirdOrder tradeThirdOrder = getThirdOrderBythirdTradeNo(param.getServicePartner(), param.getThirdTradeNo()) ;
        if(tradeThirdOrder == null) {
        	throw new TradeException(new ReturnCode() {
				@Override public String getMessage() { return QunarErrorCode.ORDER_NOT_FOUND.name(); }
				@Override public int getCode() { return QunarErrorCode.ORDER_NOT_FOUND.code(); }
			});
        }
		OrderBean orderBean = orderQueryService.getOrderByNo(tradeThirdOrder.getOrderNo()) ;
        if (orderBean == null) {
			log.error("订单不存在。订单号为：" + tradeThirdOrder.getOrderNo() + "，三方订单号是：" + param.getThirdTradeNo());
			throw new TradeException(new ReturnCode() {
				@Override public String getMessage() { return QunarErrorCode.ORDER_NOT_FOUND.name(); }
				@Override public int getCode() { return QunarErrorCode.ORDER_NOT_FOUND.code(); }
			});
        }
		orderBean.setUserName(param.getUserName());
		orderBean.setUserAreaCode1(param.getUserAreaCode1());
		orderBean.setUserMobile1(param.getUserMobile1());
		orderBean.setUserAreaCode2(param.getUserAreaCode2());
		orderBean.setUserMobile2(param.getUserMobile2());
		orderBean.setUserAreaCode3(param.getUserAreaCode3());
		orderBean.setUserMobile3(param.getUserMobile3());
		orderBean.setUserRemark(param.getUserRemark());
		orderBean.setIsArrivalVisa(param.getIsArrivalVisa());
		switch (OrderType.getType(orderBean.getOrderType())) {
			case PICKUPORDER :
				updateOrderService.updatePickUpOrder(orderBean);
				break ;
			case TRANSFER :
				updateOrderService.updateTransferOrder(orderBean);
				break ;
			default :
				log.error("订单类型不支持。参数：" + JSON.toJSONString(orderBean));
				throw new TradeException(new ReturnCode() {
					@Override public String getMessage() { return QunarErrorCode.ERR_PARAM.name(); }
					@Override public int getCode() { return QunarErrorCode.ERR_PARAM.code(); }
				});
		}
	}
}
