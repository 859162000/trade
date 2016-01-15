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
package com.hbc.api.trade.order.service.corder;

import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hbc.api.trade.bdata.common.AppNoticeService;
import com.hbc.api.trade.bdata.common.DownloadConfigService;
import com.hbc.api.trade.bdata.common.TradeConstant;
import com.hbc.api.trade.bdata.common.rsp.ReturnResult;
import com.hbc.api.trade.bdata.common.util.BeanUtilsEnhance;
import com.hbc.api.trade.bdata.common.util.DoubleUtil;
import com.hbc.api.trade.bdata.common.util.TimeConverter;
import com.hbc.api.trade.bdata.mapper.basedata.gen.bean.CityBean;
import com.hbc.api.trade.bdata.mapper.guide.gen.bean.GuideBean;
import com.hbc.api.trade.bdata.mapper.guide.gen.bean.GuideCar;
import com.hbc.api.trade.bdata.mapper.guide.gen.bean.GuideGrade;
import com.hbc.api.trade.order.OrderPal;
import com.hbc.api.trade.order.enums.TradeReturnCodeEnum;
import com.hbc.api.trade.order.enums.deliver.TradeDeliverStatus;
import com.hbc.api.trade.order.enums.order.OrderSource;
import com.hbc.api.trade.order.enums.order.OrderStatus;
import com.hbc.api.trade.order.enums.order.OrderType;
import com.hbc.api.trade.order.exception.TradeException;
import com.hbc.api.trade.order.mapping.gen.OrderBeanMapper;
import com.hbc.api.trade.order.mapping.gen.bean.OrderBean;
import com.hbc.api.trade.order.mapping.gen.bean.TradeAdditionalCost;
import com.hbc.api.trade.order.service.AdditionalCostService;
import com.hbc.api.trade.order.service.corder.rsp.COrderBean;
import com.hbc.api.trade.order.service.corder.rsp.COrderDetailBean;
import com.hbc.api.trade.order.service.gorder.OrderStatusAdaptor;
import com.hbc.api.trade.order.service.rsp.Coupon;
import com.hbc.api.trade.order.service.rsp.GuideInfo;
import com.hbc.api.trade.order.service.rsp.PassCity;
import com.hbc.api.trade.order.service.rsp.PriceInfo;
import com.hbc.api.trade.pay.enums.PayStatus;
import com.hbc.api.trade.pay.mapping.gen.bean.TradePayment;
import com.hbc.api.trade.pay.mapping.gen.bean.TradeRefund;
import com.hbc.api.trade.pay.service.PayTimeService;
import com.hbc.api.trade.pay.service.PaymentService;
import com.hbc.api.trade.pay.service.RefundService;
import com.hbc.api.trade.third.CityQueryService;
import com.hbc.api.trade.third.CouponServiceParty;
import com.hbc.api.trade.third.GuideCarService;
import com.hbc.api.trade.third.GuideGradeService;
import com.hbc.api.trade.third.GuideQueryService;
import com.hbc.api.trade.third.restful.GuideRESTfulService;
import com.hbc.api.trade.third.restful.IMService;
import com.hbc.api.trade.third.vo.CouponVo;

/**
 * @author Jongly Ran
 */
@Service
public class COrderService {
	private final static Logger logger = LoggerFactory.getLogger(COrderService.class);

	@Autowired
	private PaymentService paymentService;
	@Autowired
	private CouponServiceParty couponServiceParty;
	@Autowired
	private AdditionalCostService additionalCost;
	@Autowired
	private GuideQueryService guideQueryService;
	@Autowired
	private CityQueryService cityQueryService;
	@Autowired
	private GuideCarService guideCarService;
	@Autowired
	private GuideGradeService guideGradeService;
	@Autowired
	private RefundService refundService;
	@Autowired
	private GuideRESTfulService guideRESTfulService;
	@Autowired
	private IMService imService;
	@Autowired
	private PayTimeService payTimeService;
	@Autowired
	private AppNoticeService appNoticeService;
	
	@Autowired private DownloadConfigService downloadConfigService;

	@Autowired
	private OrderBeanMapper orderBeanMapper;

	public COrderDetailBean convertToCOrderDetailBean(OrderBean orderBean) {
		COrderDetailBean target = new COrderDetailBean();
		BeanUtilsEnhance.copyProperties(target, orderBean);
		target.setCanChat(OrderPal.chatable(orderBean));

		// Change for BUGFIX(http://bug.hbc.tech/mantis/view.php?id=385)
		// GDS订单用户不能取消
		// BUGFIX 用户不能取消GDS下的单
		if (orderBean.getOrderSource() != OrderSource.GDS.value) {
			target.setCancelable(OrderPal.cancelable(orderBean.getOrderStatus()));
			target.setCancelText(OrderPal.cancelText(orderBean.getOrderStatus()));
		} else {
			target.setCancelable(false);
			target.setCancelText(TradeReturnCodeEnum.CANNOT_CANCEL_GDS_ORDER.getMessage());
		}
		target.setOrderStatus(OrderStatusAdaptor.toCOrderStatus(OrderStatus.getStatus(orderBean.getOrderStatus()), TradeDeliverStatus.getType(orderBean.getDeliverStatus()),
				orderBean.getUserCommentStatus()).value);
		target.setRefundable(OrderPal.refundable(orderBean.getOrderStatus()));
		target.setAppraisement(guideRESTfulService.obtainAppraiseContent(orderBean.getOrderNo(), orderBean.getUserCommentStatus()));

		// BUGFIX 修改订单详情中未读消息数缺失。flightBrandSign
		JSONArray jsonArrayOfUnread = this.getIMMessages(orderBean.getUserId());
		target.setImCount(getImCount(jsonArrayOfUnread, orderBean));
		setAdditionalCost(orderBean, target);
		String[] serviceTimes = convertServiceTimes(orderBean);
		target.setServiceRecTime(serviceTimes[0]);
		target.setServiceTime(serviceTimes[1]);
		target.setServiceEndTime(serviceTimes[2]);
		if(target.getFlightBrandSign()==null){
			target.setFlightBrandSign(" ");
		}
		setTimeoutFlag(orderBean, target);
		setPriceInformation(orderBean, target);
		setCoupon(orderBean, target);
		setGuideInformation(orderBean, target);
		setServicePassCity(orderBean, target);
		setTips(orderBean, target);
		logger.info("CAPP订单详情：" + JSONObject.toJSONString(target));
		return target;
	}

	/**
	 * @param orderBean
	 * @param target
	 */
	private void setTips(OrderBean orderBean, COrderDetailBean target) {
		OrderType orderType = OrderType.getType(orderBean.getOrderType());
		switch (orderType) {
		case PICKUPORDER:
		case TRANSFER:
		case PERUSE:
			target.setCancelTip(appNoticeService.USER_CANCELS_ORDER_TIP);
			break;
		case DAILY:
		case COMMENDATION:
			target.setCancelTip(appNoticeService.NOT_USER_CANCELS_ORDER_TIP);
		}
	}

	/**
	 * @param orderNo
	 * @param target
	 */
	private void setAdditionalCost(OrderBean orderBean, COrderDetailBean target) {
		String orderNo = orderBean.getOrderNo();
		TradeAdditionalCost additionalCostBean = additionalCost.getAdditionalCost(orderNo);
		if (additionalCostBean != null) {
			target.setAdditionIsRead(additionalCostBean.getAdditionIsRead() == null ? 0 : additionalCostBean.getAdditionIsRead());
		} else {
			target.setAdditionIsRead(-1); // 无增项费用
		}
	}

	private void setServicePassCity(OrderBean order, COrderDetailBean target) {
		String servicePassCity = order.getServicePassCity();
		if (!StringUtils.isBlank(servicePassCity)) {
			List<PassCity> passCityList = new LinkedList<>();
			List<Integer> cityIdList = new LinkedList<>();
			for (String cityInfo : servicePassCity.split(TradeConstant.SPLITER_COMMA)) {
				String[] info = cityInfo.split(TradeConstant.SPLITER_LINE);
				PassCity passCity = new PassCity();
				passCity.setCityId(Integer.parseInt(info[0]));
				passCity.setStayDay(Integer.parseInt(info[1]));
				passCityList.add(passCity);
				cityIdList.add(passCity.getCityId());
			}

			List<CityBean> citiesList = cityQueryService.getCities(cityIdList);
			if (citiesList == null || citiesList.isEmpty()) {
				logger.error("途径城市数据异常，存入trade_order表的service_pass_citys数据在basedata_cities表里无对应的cityName");
				throw new TradeException(TradeReturnCodeEnum.PASS_CITY_NOT_FOUND);
			}

			List<PassCity> passCityListTart = new LinkedList<>();
			for (PassCity passCityTemp : passCityList) {
				PassCity passCity = new PassCity();
				passCity.setCityId(passCityTemp.getCityId());
				passCity.setStayDay(passCityTemp.getStayDay());
				for (CityBean city : citiesList) {
					if (city.getCityId().equals(passCityTemp.getCityId())) {
						passCity.setName(city.getCityName());
						break;
					}
				}
				passCityListTart.add(passCity);
			}
			target.setPassCities(passCityListTart);
		}
	}

	/**
	 * @param order
	 * @param target
	 */
	private void setGuideInformation(OrderBean order, COrderDetailBean target) {
		boolean hasGuideInfo = OrderPal.hasGuideInfo(order.getOrderStatus(), order.getGuideId());
		logger.info("查订单列表，hasGuideInfo=" + hasGuideInfo + ", orderNo: " + order.getOrderNo() + ",guideId: " + order.getGuideId());
		if (hasGuideInfo) {
			GuideBean guideBean = guideQueryService.getGuideBeanById(order.getGuideId());
			if (guideBean != null) {
				GuideInfo guideInfo = new GuideInfo();
				guideInfo.setGuideAvatar(downloadConfigService.getGuideAvatar(guideBean.getAvatar()));
				guideInfo.setGuideId(guideBean.getGuideId());
				guideInfo.setGuideName(guideBean.getGuideName());
				guideInfo.setGuideTel("+"+guideBean.getAreaCode()+guideBean.getMobile());

				List<GuideCar> guideList = guideCarService.getGuideCars(order.getGuideId());
				if (guideList != null && guideList.size() > 0) {
					GuideCar guideCar = guideList.get(0); // 现阶段只取一个
					String brandName = guideCar.getCarBrandName();
					if (brandName == null)
						brandName = "";
					String carName = guideCar.getCarName();
					if (carName == null)
						carName = "";
					guideInfo.setGuideCar(brandName + " " + carName);
				}

				// BUG-FIX 默认4.5
				List<GuideGrade> guideGradeList = guideGradeService.getGuideGrades(order.getGuideId());
				if (guideGradeList != null && guideGradeList.size() > 0) {
					GuideGrade guideGrade = guideGradeList.get(0); // 只会有一个
					guideInfo.setGuideStarLevel(guideGrade.getSysAssessment() == null ? 4.5f : guideGrade.getSysAssessment());
				} else {
					guideInfo.setGuideStarLevel(4.5f);
				}
				target.setGuideInfo(guideInfo);
			}
		}
	}

	/**
	 * @param order
	 * @param target
	 */
	private void setCoupon(OrderBean order, COrderDetailBean target) {
		OrderStatus orderStatus = OrderStatus.getStatus(order.getOrderStatus());
		if (OrderStatus.INITSTATE.equals(orderStatus)) {
			CouponVo couponVo = couponServiceParty.getPYCoupByOrderDetail(order.getOrderNo(), order.getPriceChannel(), order.getUserId());
			logger.info("未付款，获取优惠券信息：" + JSON.toJSONString(couponVo));
			if (couponVo != null) {
				Coupon coupon = new Coupon();
				coupon.setCouponId(couponVo.getCouponId());
				coupon.setActualPrice(couponVo.getActualPrice());
				coupon.setPriceInfo(couponVo.getPriceInfo());
				target.setCoupon(coupon);
			}
		} else {
			if(order.getCoupId() ==null ) return;
			TradePayment payment = paymentService.queryTradePaymentByOrderNoNoexp(order.getOrderNo(), PayStatus.SUCCESS);
			if(payment!=null && payment.getCoupPay()!=null && payment.getCoupPay()>0){
				Coupon coupon = new Coupon();
				coupon.setPriceInfo(order.getCoupPriceInfo());
				coupon.setCouponId(order.getCoupId());
				coupon.setActualPrice(payment.getPayActual());//实付金额
				logger.info("已付款，获取优惠券信息：" + JSON.toJSONString(target.getCoupon()));
				target.setCoupon(coupon);
			}
		}
	}

	/**
	 * @param order
	 * @param paymentService
	 * @param target
	 */
	private void setPriceInformation(OrderBean order, COrderDetailBean target) {
		PriceInfo priceInfo = new PriceInfo();
		OrderType orderType = OrderType.getType(order.getOrderType());
		if(OrderType.TRANSFER.equals(orderType)){
			priceInfo.setCheckInPrice(order.getCheckInPrice());
		}
		priceInfo.setOrderPrice(OrderPal.getOrderPrice(order));
		
		priceInfo.setRefundablePrice(refundService.getRefundAmount(order.getOrderNo()));
		TradePayment tradePayment = paymentService.queryTradePaymentByOrderNoNoexp(order.getOrderNo(), PayStatus.SUCCESS);
		if(tradePayment!=null){
			if (OrderSource.GDS.value.equals(order.getOrderSource())) {
				priceInfo.setActualPay(order.getPriceTicket());
				priceInfo.setShouldPay(order.getPriceTicket());
				Double rprice = refundService.getRefundAmount(order.getOrderNo());
				if (rprice > 0) {
					priceInfo.setRefundablePrice(order.getPriceTicket());
				}
			}else{
				priceInfo.setActualPay(tradePayment.getPayActual()==null?0:tradePayment.getPayActual());
				priceInfo.setShouldPay(tradePayment.getPayShould());
			}

			// 退款金额
			List<TradeRefund> refundList = refundService.getAllRefunds(order.getOrderNo());
			priceInfo.setRefundPrice(0.0d);
			priceInfo.setCancelFee(getRefundFee(tradePayment,priceInfo.getRefundablePrice()));
			if (refundList != null && refundList.size() > 0) {
				Double refundPrice = 0.0;
				for (TradeRefund refund : refundList) {
					refundPrice += (refund.getRefundMoney() != null ? refund.getRefundMoney() : 0.0);
				}
				if(refundPrice.equals(order.getPriceChannel())){
					refundPrice = order.getPriceTicket();
				}
				priceInfo.setRefundPrice(refundPrice);
				
				priceInfo.setCancelFee(getRefundFee(tradePayment,refundPrice));
			}
		}else{
			if (OrderSource.GDS.value.equals(order.getOrderSource())) {
				priceInfo.setShouldPay(order.getPriceTicket());
			} else {
				priceInfo.setShouldPay(order.getPriceChannel());
			}
		}
		target.setPriceInfo(priceInfo);
	}

	private Double getRefundFee(TradePayment tradePayment,Double refundPrice){
		Double couppay = tradePayment.getCoupPay()==null?0:tradePayment.getCoupPay();
		Double refPrice = DoubleUtil.subtractionDouble(tradePayment.getPayShould(), refundPrice+couppay);
		if(refPrice>tradePayment.getPayActual()){
			refPrice = tradePayment.getPayActual();
		}
		if(refPrice<=0){
			refPrice = 0d;
		}
		return refPrice;
	}
	/**
	 * 
	 * @param order
	 * @param target
	 * @return 数组三个值依次为：recTime, serviceTime, serviceEndTime
	 */
	private String[] convertServiceTimes(OrderBean order) {
		String[] times = new String[3];
		boolean usingShortDate = OrderPal.usingShortDate(order.getOrderType(), order.getOrderGoodsType());
		if (usingShortDate) {
			String originServiceTime = TimeConverter.formatDate(order.getServiceTime());
			if (StringUtils.isBlank(originServiceTime)) {
				originServiceTime = "0000-00-00 00-00-00"; // It only takes
															// place when
															// developing.
			}
			times[0] = originServiceTime.substring(10);
			times[1] = TimeConverter.formatDates(order.getServiceTime());
		} else {
			times[1] = TimeConverter.formatDate(order.getServiceTime());
		}
		times[2] = usingShortDate ? TimeConverter.formatDates(order.getServiceEndTime()) : TimeConverter.formatDate(order.getServiceEndTime());
		return times;
	}

	/**
	 * @param order
	 * @param target
	 */
	private void setTimeoutFlag(OrderBean order, COrderDetailBean target) {
		// 超时及是否已经超时
		OrderStatus orderStatus = OrderStatus.getStatus(order.getOrderStatus());
		if (orderStatus.equals(OrderStatus.INITSTATE)) {
			payTimeService.payDeadline(order);
			target.setPayDeadTime(payTimeService.getPayDeadline(order));
			target.setAlreadyTimeout(payTimeService.isTimeout(order));
		}
	}

	public List<COrderBean> convert(List<OrderBean> listBucket, String userId) {
		List<COrderBean> listTarget = new LinkedList<>();
		if (listBucket == null || listBucket.size() == 0)
			return listTarget;

		// 获取未读消息数列表
		JSONArray jsonArrayOfUnread = this.getIMMessages(userId);

		// 适配CAPP订单详情
		for (OrderBean order : listBucket) {
			COrderBean target = new COrderBean();
			BeanUtilsEnhance.copyProperties(target, order);
			// 适配C端订单状态
			OrderStatus status = OrderStatus.getStatus(order.getOrderStatus());
			TradeDeliverStatus type = TradeDeliverStatus.getType(order.getDeliverStatus());
			target.setOrderStatus(OrderStatusAdaptor.toCOrderStatus(status, type, order.getUserCommentStatus()).value);

			// 适配时间
			String[] serviceTimes = convertServiceTimes(order);
			target.setServiceTime(serviceTimes[1]);
			target.setServiceEndTime(serviceTimes[2]);
			target.setCanChat(OrderPal.chatable(order));
			target.setImCount(getImCount(jsonArrayOfUnread, order));
			logger.info("[Jongly]unread IM count filtered:" + JSON.toJSONString(jsonArrayOfUnread));
			logger.info("[Jongly]unread IM count has get:" + target.getImCount());
			setGuideInformation(order, target);
			setPriceInformation(order, target);
			setImToken(order, target);
			listTarget.add(target);
		}
		logger.info("CAPP订单列表：" + JSONObject.toJSONString(listTarget));
		return listTarget;
	}
	
	/**
	 * @param order
	 * @param target
	 */
	private void setImToken(OrderBean order, COrderBean target) {
		if(target.getImToken()==null || target.getImToken().length()==0){
			String orderNo = order.getOrderNo();
			String token = imService.obtainIMToken(orderNo);
			logger.info("[Jongly] orderNo="+orderNo+" reget IMTOKEN=" + token);
			try{
				OrderBean orderBean = new OrderBean();
				orderBean.setImToken(token);
				orderBean.setOrderNo(orderNo );
				int optnum = orderBeanMapper.updateByPrimaryKeySelective(orderBean);
				if(optnum==1){
					logger.info(orderNo+" 更新imtoken 成功");
				}
			}catch(Exception e){
				logger.error("", e);
			}
			target.setImToken(token);
		}
	}
	
	private JSONArray getIMMessages(String userId) {
		ReturnResult result = imService.imCUnreadSizeList(userId);
		JSONObject jsonData;
		JSONArray jsonArrayOfUnread = null;
		if (result != null && (jsonData = (JSONObject) result.getData()) != null) {
			jsonArrayOfUnread = (JSONArray) jsonData.get("list");
		}
		logger.info("[Jongly]unread IM count: " + JSON.toJSONString(result));
		return jsonArrayOfUnread;
	}

	/**
	 * 获取消息数
	 * 
	 * @param jsonArrayOfUnread
	 * @param order
	 * @param target
	 */
	private Integer getImCount(JSONArray jsonArrayOfUnread, OrderBean order) {
		int j = 0;
		boolean chatable = OrderPal.chatable(order);
		if (chatable && jsonArrayOfUnread != null && (j = jsonArrayOfUnread.size()) > 0) {
			for (int i = 0; i < j; i++) {
				JSONObject jsonObject = (JSONObject) jsonArrayOfUnread.get(i);
				if (order.getOrderNo().equals(jsonObject.get("name"))) {
					return jsonObject.get("value") != null ? Integer.valueOf(jsonObject.get("value").toString()) : null;
				}
			}
		}
		return null;
	}

	/**
	 * @param order
	 * @param target
	 */
	private void setGuideInformation(OrderBean order, COrderBean target) {
		boolean hasGuideInfo = OrderPal.hasGuideInfo(order.getOrderStatus(), order.getGuideId());
		logger.info("查订单列表，hasGuideInfo=" + hasGuideInfo + ", orderNo: " + order.getOrderNo() + ",guideId: " + order.getGuideId());
		if (hasGuideInfo) {
			GuideBean guideBean = guideQueryService.getGuideBeanById(order.getGuideId());
			if (guideBean != null) {
				GuideInfo guideInfo = new GuideInfo();
				guideInfo.setGuideId(guideBean.getGuideId());
				guideInfo.setGuideAvatar(downloadConfigService.getGuideAvatar(guideBean.getAvatar()));
				guideInfo.setGuideName(guideBean.getGuideName());
				target.setGuideInfo(guideInfo);
			}
		}
	}

	/**
	 * @param paymentService
	 * @param order
	 * @param target
	 */
	private void setPriceInformation(OrderBean order, COrderBean target) {
		PriceInfo priceInfo = new PriceInfo();
		priceInfo.setCheckInPrice(order.getCheckInPrice());
		priceInfo.setOrderPrice(OrderPal.getOrderPrice(order));
		if (OrderPal.hasPaymentInfo(order.getOrderStatus())) {
			TradePayment tradePayment = paymentService.queryTradePaymentByOrderNoNoexp(order.getOrderNo(), PayStatus.SUCCESS);
			if (tradePayment != null) {
				priceInfo.setActualPay(tradePayment.getPayActual());
				priceInfo.setShouldPay(tradePayment.getPayShould());
			}
		} else {
			priceInfo.setShouldPay(order.getPriceChannel());
		}
		target.setPriceInfo(priceInfo);
	}
}
