/**
 * @Author lukangle
 * @2015年10月12日@上午10:28:45
 */
package com.hbc.api.trade.order.controller.query;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.hbc.api.trade.bdata.common.DownloadConfigService;
import com.hbc.api.trade.bdata.common.rsp.ReturnResult;
import com.hbc.api.trade.bdata.common.util.BeanUtilsEnhance;
import com.hbc.api.trade.bdata.common.util.TimeConverter;
import com.hbc.api.trade.bdata.mapper.guide.gen.bean.GuideBean;
import com.hbc.api.trade.bdata.mapper.guide.gen.bean.GuideCar;
import com.hbc.api.trade.bdata.mapper.guide.gen.bean.GuideGrade;
import com.hbc.api.trade.order.OrderPal;
import com.hbc.api.trade.order.controller.query.vo.MISCancelOrderDetailBean;
import com.hbc.api.trade.order.controller.query.vo.MISOrderDetail;
import com.hbc.api.trade.order.controller.validator.OrderValidator;
import com.hbc.api.trade.order.enums.deliver.OrderDeliverStatus;
import com.hbc.api.trade.order.enums.order.OrderSource;
import com.hbc.api.trade.order.enums.order.OrderStatus;
import com.hbc.api.trade.order.enums.order.OrderType;
import com.hbc.api.trade.order.enums.order.UserCommentStatus;
import com.hbc.api.trade.order.enums.ota.AgentChannelEnum;
import com.hbc.api.trade.order.enums.third.CarTypeEnum;
import com.hbc.api.trade.order.mapping.gen.bean.OrderBean;
import com.hbc.api.trade.order.mapping.gen.bean.TradeDeliverGuide;
import com.hbc.api.trade.order.mapping.gen.bean.TradeOrderTrack;
import com.hbc.api.trade.order.mapping.genx.xbean.MISOrderQueryBean;
import com.hbc.api.trade.order.mapping.genx.xbean.MISOrderResultBean;
import com.hbc.api.trade.order.mapping.genx.xbean.PaymentDetail;
import com.hbc.api.trade.order.mapping.genx.xbean.TradeDeliverGuideQueryBean;
import com.hbc.api.trade.order.service.MISOrderQueryService;
import com.hbc.api.trade.order.service.OrderQueryService;
import com.hbc.api.trade.order.service.OrderTrackService;
import com.hbc.api.trade.order.service.TradeDeliverGuideService;
import com.hbc.api.trade.order.service.gorder.OrderStatusAdaptor;
import com.hbc.api.trade.order.service.rsp.GuideInfo;
import com.hbc.api.trade.order.service.rsp.OrderTrack;
import com.hbc.api.trade.pay.enums.PayStatus;
import com.hbc.api.trade.pay.mapping.gen.bean.TradePayment;
import com.hbc.api.trade.pay.service.PaymentService;
import com.hbc.api.trade.third.GuideCarService;
import com.hbc.api.trade.third.GuideGradeService;
import com.hbc.api.trade.third.GuideQueryService;
import com.hbc.api.trade.third.restful.GuideRESTfulService;
import com.hbc.api.trade.third.vo.Appraisement;
import com.hbc.api.trade.third.vo.MISAppraisement;

@RestController
@RequestMapping("trade")
public class MISOrderQueryController {
	private final static Logger logger = LoggerFactory.getLogger(COrderController.class);

	@Autowired
	private OrderQueryService orderQueryService;
	@Autowired
	private MISOrderQueryService misOrderQueryService;
	@Autowired
	private OrderTrackService orderTrackService;
	@Autowired
	private PaymentService paymentService;

	@Autowired
	private GuideQueryService guideQueryService;
	@Autowired
	private GuideCarService guideCarService;
	@Autowired
	private GuideGradeService guideGradeService;
	@Autowired
	private TradeDeliverGuideService tradeDeliverGuideService;
	@Autowired
	private GuideRESTfulService guideRESTfulService;
	@Autowired
	private DownloadConfigService downloadConfigService;

	/**
	 * MIS 复杂多条件查询
	 * 
	 * @param orderBeanParam
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "v1.0/e/order/list",
			method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public ReturnResult getOrders(MISOrderQueryBean orderBeanParam, HttpServletRequest request) {
		logger.info("【MIS条件查询】request：" + JSON.toJSONString(orderBeanParam));
		validateForGetOrders(orderBeanParam);
		buildCommonConditions(orderBeanParam);
		adaptOrderChannelInfo(orderBeanParam);

		List<MISOrderResultBean> orderBeans = null;
		Integer totalSize = null;
		if (StringUtils.isNotBlank(orderBeanParam.getUserId())) {
			// 根据userId查询
			orderBeans = misOrderQueryService.queryOrdersByUserId(orderBeanParam);
			totalSize = misOrderQueryService.queryOrdersByUserIdTotalSize(orderBeanParam);
		} else if (orderBeanParam.getHasAdditionalCost() != null && orderBeanParam.getHasAdditionalCost()) {
			// 查询有增项费用的订单
			orderBeans = misOrderQueryService.queryAdditionalCostOrders(orderBeanParam);
			totalSize = misOrderQueryService.queryAdditionalCostOrdersTotalSize(orderBeanParam);
		} else {
			if (StringUtils.isNoneBlank(orderBeanParam.getOrderNo())) {
				List<MISOrderResultBean> misOrderQueryBean = misOrderQueryService.queryOrders(orderBeanParam); //BUGFIX(http://bug.hbc.tech/mantis/view.php?id=1086)
				orderBeans = new ArrayList<MISOrderResultBean>();
				if (misOrderQueryBean != null && misOrderQueryBean.size() == 1) //BUGFIX(修复根据订单号查询订单，每查询到返回 {"data":{"totalSize":1,"resultBean":[null]},"status":200} 的BUG)
				{
					orderBeans.add(misOrderQueryBean.get(0));
					totalSize = orderBeans.size();
				} else {
					orderBeans = misOrderQueryBean;
					totalSize = orderBeans.size();
				}
			} else {
				orderBeans = misOrderQueryService.queryOrders(orderBeanParam);
				totalSize = misOrderQueryService.queryOrdersTotalSize(orderBeanParam);
			}
		}


		ReturnResult returnResult = new ReturnResult();
		returnResult.setData(orderBeans, totalSize);
		logger.info("【MIS条件查询】response：" + JSON.toJSONString(returnResult));
		return returnResult;
	}

	@RequestMapping(value = "v1.0/e/order/unbalanced/list",
			method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public ReturnResult getUnbalancedOrders(@RequestParam(required = true) String guideId, Integer orderType, HttpServletRequest request) {
		MISOrderQueryBean orderBeanParam = new MISOrderQueryBean();
		orderBeanParam.setOrderStatusList(OrderPal.getNotSettlementOrderStatus());
		orderBeanParam.setGuideId(guideId);
		orderBeanParam.setOrderType(orderType);
		logger.info("【MIS未结算订单列表】request：" + JSON.toJSONString(orderBeanParam));
		List<MISOrderResultBean> orderBeans = misOrderQueryService.getUnbalancedOrders(orderBeanParam);
		ReturnResult returnResult = new ReturnResult();
		returnResult.setData(orderBeans);
		logger.info("【MIS未结算订单列表】response：" + JSON.toJSONString(returnResult));
		return returnResult;
	}

	/**
	 * @param orderBeanParam
	 */
	private void buildCommonConditions(MISOrderQueryBean orderBeanParam) {
		Integer searchType = orderBeanParam.getSearchType();
		if (searchType != null) {
			if (searchType == 1) {
				// 急待处理，尽包含未注册航班和没有导游的订单
				// 相关bug：0000474: 急待处理的tab下，应该只有航班未注册的订单和没有导游接单的订单，其他的订单不在急待处理中
				orderBeanParam.setHasMoreThanOneHour(true);
				orderBeanParam.setHasNotRegistered(true);
				return;
			} else if (searchType == 3) {
				orderBeanParam.setDeliverStatus(OrderDeliverStatus.deliving.value);
			}
		}
		orderBeanParam.setOrderStatusList(OrderStatusAdaptor.getMISOrderStatusQueryType(searchType));
	}

	private void adaptOrderChannelInfo(MISOrderQueryBean orderBeanParam)
	{
		if (orderBeanParam.getOrderSource() != null && orderBeanParam.getOrderSource() > 0) {
			if (OrderSource.CTRIP.value.equals(orderBeanParam.getOrderSource())) {
				orderBeanParam.setOrderChannel(AgentChannelEnum.CTRIP_CHANNEL.value); //CTRIP(1918029805, "携程渠道")
				orderBeanParam.setOrderSource(OrderSource.OTA.value);
			} else if (OrderSource.QUNAR.value.equals(orderBeanParam.getOrderSource())) {
				orderBeanParam.setOrderChannel(AgentChannelEnum.QUNAR_CHANNEL.value); //QUNAR(1948212164, "QUNA渠道")
				orderBeanParam.setOrderSource(OrderSource.OTA.value);
			} else if (OrderSource.ALITRIP.value.equals(orderBeanParam.getOrderSource())) {
				orderBeanParam.setOrderChannel(AgentChannelEnum.QUA_CHANNEL.value); //QUA(1909280330, "去啊渠道")
				orderBeanParam.setOrderSource(OrderSource.OTA.value);
			}
			logger.info("final source->{}|channel->{} (CTRIP-1918029805 QUNAR-1948212164 QUA-1909280330)", orderBeanParam.getOrderSource(), orderBeanParam.getOrderChannel());
		}
	}

	@RequestMapping(value = "v1.0/e/order/payment/detail",
			method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public ReturnResult getOrderPaymentDetail(@RequestParam(required = true) String orderNo, HttpServletRequest request) {
		PaymentDetail paymentDetail = orderQueryService.getOrderPaymentDetail(orderNo);
		ReturnResult returnResult = new ReturnResult();
		returnResult.setData(paymentDetail);
		return returnResult;
	}

	@RequestMapping(value = "v1.0/e/order/confirmation",
			method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public ReturnResult getOrderConfirmation(@RequestParam(required = true) String orderNo, HttpServletRequest request) {
		OrderBean orderDetail = orderQueryService.getOrderByNo(orderNo);
		ReturnResult returnResult = new ReturnResult();
		returnResult.setData(orderDetail);
		return returnResult;
	}

	@RequestMapping(value = "v1.0/e/order/detail",
			method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public ReturnResult getOrder(@RequestParam(required = true) String orderNo, HttpServletRequest request) {
		OrderBean orderDetail = orderQueryService.getOrderByNo(orderNo);
		MISOrderDetail misOrderDetail = new MISOrderDetail();
		BeanUtilsEnhance.copyProperties(misOrderDetail, orderDetail);
		setOrderTrack(orderNo, misOrderDetail);
		setPayGatewayName(orderDetail, misOrderDetail);
		setGuideInformation(orderDetail, misOrderDetail);
		ReturnResult returnResult = new ReturnResult();
		returnResult.setData(misOrderDetail);
		return returnResult;
	}

	/**
	 * 获取支付方式名称
	 * 
	 * @param orderNo
	 * @param orderDetail
	 * @param misOrderDetail
	 */
	private void setPayGatewayName(OrderBean orderDetail, MISOrderDetail misOrderDetail) {
		if (!orderDetail.getOrderStatus().equals(OrderStatus.INITSTATE.value)) {
			try {
				TradePayment payment = paymentService.queryTradePaymentByOrderNoNoexp(orderDetail.getOrderNo(), PayStatus.SUCCESS);
				misOrderDetail.setPayGatewayName(payment.getPayGatewayName());
			} catch (Exception e) {
				logger.error(orderDetail.getOrderNo() + "测试时，出现一个订单对应多个支付信息的情况，忽略", e);
			}
		}
	}

	/**
	 * 获取订单动态
	 * 
	 * @param orderNo
	 * @param misOrderDetail
	 */
	private void setOrderTrack(String orderNo, MISOrderDetail misOrderDetail) {
		List<TradeOrderTrack> orderTracks = orderTrackService.getOrderTrackRecords(orderNo);
		if (orderTracks != null && orderTracks.size() > 0) {
			List<OrderTrack> orderTrackList = new LinkedList<>();
			for (TradeOrderTrack orderTrackParam : orderTracks) {
				OrderTrack orderTrackBean = new OrderTrack();
				orderTrackBean.setCreateTime(orderTrackParam.getCreateTime());
				orderTrackBean.setTrackDesc(orderTrackParam.getTrackDesc());
				orderTrackList.add(orderTrackBean);
			}
			misOrderDetail.setOrderTracks(orderTrackList);
		}
	}

	private void setGuideInformation(OrderBean order, MISOrderDetail target) {
		if (OrderStatus.getStatus(order.getOrderStatus()).value != OrderStatus.INITSTATE.value) {
			GuideBean guideBean = guideQueryService.getGuideBeanById(order.getGuideId());
			if (guideBean != null) {
				GuideInfo guideInfo = new GuideInfo();
				guideInfo.setGuideAvatar(downloadConfigService.getGuideAvatar(guideBean.getAvatar()));
				guideInfo.setGuideId(order.getGuideId());
				guideInfo.setGuideName(order.getGuideName());
				guideInfo.setGuideTel("+"+guideBean.getAreaCode()+guideBean.getMobile());
				guideInfo.setPriceGuide(order.getPriceGuide());
				guideInfo.setGuideNo(guideBean.getGuideNo());
				guideInfo.setGuideLevel(guideBean.getGuideLevel());
				guideInfo.setUgrContactName(guideBean.getContactName());
				guideInfo.setUgrContactAreaCode(guideBean.getContactAreaCode());
				guideInfo.setUgrContactMobile(guideBean.getContactMobile());

				List<GuideCar> guideList = guideCarService.getGuideCars(order.getGuideId());
				if (guideList != null && guideList.size() > 0) {
					GuideCar guideCar = guideList.get(0); // 现阶段只取一个
					String brandName = guideCar.getCarBrandName() == null ? "" : guideCar.getCarBrandName();
					String carName = guideCar.getCarName() == null ? "" : guideCar.getCarName();
					String seatNumber = order.getCarSeatNum() != null ? order.getCarSeatNum() + "座" : "";
					String carType = null;
					switch (CarTypeEnum.getType(order.getCarTypeId())) {
					case CARTYPE_COMFOTABLE:
						carType = CarTypeEnum.CARTYPE_COMFOTABLE.name;
						break;
					case CARTYPE_ECONOMIC:
						carType = CarTypeEnum.CARTYPE_ECONOMIC.name;
						break;
					case CARTYPE_EXTRA_LUXURY:
						carType = CarTypeEnum.CARTYPE_EXTRA_LUXURY.name;
						break;
					case CARTYPE_LUXURY:
						carType = CarTypeEnum.CARTYPE_LUXURY.name;
						break;
					default:
						carType = CarTypeEnum.CARTYPE_ECONOMIC.name;
					}
					guideInfo.setGuideCar(brandName + " " + carName + " " + carType + " " + seatNumber);
				}

				List<GuideGrade> guideGradeList = guideGradeService.getGuideGrades(order.getGuideId());
				if (guideGradeList != null && guideGradeList.size() > 0) {
					GuideGrade guideGrade = guideGradeList.get(0); // 只会有一个
					guideInfo.setGuideStarLevel(guideGrade.getSysAssessment());
				}
				target.setGuideInfo(guideInfo);
			}
		}
	}

	/**
	 * @param orderBeanParam
	 */
	private void validateForGetOrders(MISOrderQueryBean orderBeanParam) {
		OrderValidator.validateLimitAndOffset(orderBeanParam.getLimit(), orderBeanParam.getOffset());
		//boolean hasNotUserId = StringUtils.isBlank(orderBeanParam.getUserId());
		//boolean hasNotOrderNo = StringUtils.isBlank(orderBeanParam.getOrderNo());
		//boolean hasNotServiceTime = StringUtils.isBlank(orderBeanParam.getServiceTimeBegin()) || StringUtils.isBlank(orderBeanParam.getServiceTimeEnd());
		//		if (hasNotUserId) {
		//			logger.error("用户ID不能为空");
		//			throw new TradeException(TradeReturnCodeEnum.ORDER_PARAM_FAILED, "MIS订单查询条件:用户ID不能为空。");
		//		}
	}

	@RequestMapping(value = "v1.0/e/order/cancel/detail",
			method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public ReturnResult toCancelOrderPage(@RequestParam(required = true) String orderNo, HttpServletRequest request) {
		OrderValidator.validateOrderNo(orderNo);
		OrderBean orderBean = orderQueryService.getOrderByNo(orderNo); // already validated.
		TradePayment tradePayment = paymentService.querySuccessTradePaymentByOrderNo(orderNo); // already validated.
		MISCancelOrderDetailBean detailBean = new MISCancelOrderDetailBean();
		detailBean.setActualPay(tradePayment.getPayActual());
		detailBean.setCouponPrice(tradePayment.getCoupPay());
		detailBean.setGuidePrice(orderBean.getPriceGuide());
		detailBean.setOrderPrice(tradePayment.getOrderPrice());
		detailBean.setPriceChannel(orderBean.getPriceChannel());
		ReturnResult result = new ReturnResult();
		result.setData(detailBean);
		return result;
	}

	/**
	 * 已接订单
	 * 
	 * @param orderBeanParam
	 * @return
	 */
	@RequestMapping(value = "v1.0/e/order/list/obtained",
			method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public ReturnResult getAlreadyAgreedOrders(MISOrderQueryBean orderBeanParam, HttpServletRequest request) {

		OrderValidator.validateGuideId(orderBeanParam.getGuideId());
		OrderValidator.validateLimitAndOffset(orderBeanParam.getLimit(), orderBeanParam.getOffset());
		List<MISOrderResultBean> orderBeans = misOrderQueryService.queryAlreayAgrredOrder(orderBeanParam);
		int totalSize = misOrderQueryService.queryAlreayAgrredOrderTotalSize(orderBeanParam);
		ReturnResult returnResult = new ReturnResult();
		returnResult.setData(orderBeans, totalSize);
		return returnResult;
	}

	@RequestMapping(value = "v1.0/e/order/statistics/appraisement",
			method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public ReturnResult obtainStatistics(@RequestParam(required = true) String guideId, HttpServletRequest request) {
		OrderBean orderBean = new OrderBean();
		orderBean.setGuideId(guideId);
		Integer appraisedSizeBySystem = orderQueryService.getAppraisedSizeBySystem(orderBean);
		Integer appraisedSizeByUser = orderQueryService.getAppraisedSizeByUser(orderBean);
		Integer unvaluedSizeByUser = orderQueryService.getUnvaluedSizeByUser(orderBean);

		Map<String, Integer> data = new HashMap<>(3);
		data.put("appraisedSize", appraisedSizeBySystem + appraisedSizeByUser);
		data.put("appraisedSizeByUser", appraisedSizeByUser);
		data.put("unvaluedSizeByUser", unvaluedSizeByUser);
		ReturnResult result = new ReturnResult();
		result.setData(data);
		return result;
	}

	@RequestMapping(value = "v1.0/e/order/deliver/guide/list",
			method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public ReturnResult getDeliveredList(TradeDeliverGuideQueryBean queryBean, HttpServletRequest request) {
		OrderValidator.validateOrderNo(queryBean.getOrderNo());
		OrderValidator.validateLimitAndOffset(queryBean.getLimit(), queryBean.getOffset());
		List<TradeDeliverGuide> list = tradeDeliverGuideService.getTradeDeliverGuideList(queryBean);
		int totalSize = tradeDeliverGuideService.getTradeDeliverGuideListTotalSize(queryBean);
		ReturnResult result = new ReturnResult();
		result.setData(list, totalSize);
		return result;
	}

	@RequestMapping(value = "v1.0/e/order/guide/appraisement",
			method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public ReturnResult getAppraisedContent(@RequestParam(required = true) String orderNo, HttpServletRequest request) {
		OrderBean orderBean = orderQueryService.getOrderByNo(orderNo);
		Appraisement appraisedContent = guideRESTfulService.obtainAppraiseContent(orderNo, UserCommentStatus.SCORED.value);
		MISAppraisement resultBean = new MISAppraisement();
		BeanUtilsEnhance.copyProperties(resultBean, appraisedContent);
		resultBean.setOrderNo(orderNo);
		resultBean.setGuideName(orderBean.getGuideName());
		resultBean.setOrderType(OrderType.getType(orderBean.getOrderType()).name);
		resultBean.setServiceCompleteTime(TimeConverter.formatDate(orderBean.getCompleteTime()));
		resultBean.setServiceCityName(orderBean.getServiceCityName());
		resultBean.setServiceCountryName(orderBean.getServiceCountryName());
		ReturnResult result = new ReturnResult();
		result.setData(resultBean);
		return result;
	}

	@RequestMapping(value = "v1.0/e/order/user",
			method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public ReturnResult getUserInfo(@RequestParam(required = true) String orderNo, HttpServletRequest request) {
		OrderBean orderBean = orderQueryService.getOrderByNo(orderNo); // 已处理null
		ReturnResult returnResult = new ReturnResult();
		Map<String, String> resultBean = new HashMap<>(2);
		resultBean.put("userId", orderBean.getUserId());
		resultBean.put("userName", orderBean.getUserName());
		returnResult.setData(resultBean);
		return returnResult;
	}
}
