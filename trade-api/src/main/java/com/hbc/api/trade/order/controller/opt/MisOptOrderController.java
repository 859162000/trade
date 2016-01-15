/**
 * @Author lukangle
 * @2015年10月12日@下午1:57:46
 */
package com.hbc.api.trade.order.controller.opt;

import java.text.ParseException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.hbc.api.trade.bdata.common.rsp.ReturnResult;
import com.hbc.api.trade.bdata.common.util.BeanUtilsEnhance;
import com.hbc.api.trade.order.controller.daily.DailyController;
import com.hbc.api.trade.order.controller.opt.req.MISEditOrderParam;
import com.hbc.api.trade.order.controller.validator.OrderValidator;
import com.hbc.api.trade.order.enums.TradeReturnCodeEnum;
import com.hbc.api.trade.order.enums.order.OrderType;
import com.hbc.api.trade.order.exception.TradeException;
import com.hbc.api.trade.order.mapping.gen.bean.OrderBean;
import com.hbc.api.trade.order.mapping.genx.xbean.AppraiseGuideParam;
import com.hbc.api.trade.order.mapping.genx.xbean.TradePriceHistoryParam;
import com.hbc.api.trade.order.service.MISOptOrderService;
import com.hbc.api.trade.order.service.OrderLogService;
import com.hbc.api.trade.order.service.OrderQueryService;
import com.hbc.api.trade.order.service.OrderTrackService;
import com.hbc.api.trade.order.service.RewardService;
import com.hbc.api.trade.order.service.TradePriceHistoryService;
import com.hbc.api.trade.order.service.deliver.OptTradeDeliverService;
import com.hbc.api.trade.pay.param.MISCancelOrderParam;
import com.hbc.api.trade.pay.service.RefundService;
import com.hbc.api.trade.third.push.GPushService;
import com.hbc.api.trade.third.restful.FlightRESTfulService;
import com.hbc.api.trade.third.sms.SMSService;

@RestController
@RequestMapping("trade")
public class MisOptOrderController {
	private static final Logger logger = LoggerFactory.getLogger(DailyController.class);

	@Autowired
	private OrderTrackService orderTrackService;

	@Autowired
	private MISOptOrderService misOptOrderService;

	@Autowired
	private OrderQueryService orderQueryService;
	@Autowired
	private OptTradeDeliverService optTradeDeliverService;
	@Autowired
	private RefundService refundService;
	@Autowired
	private FlightRESTfulService flightRESTfulService;
	@Autowired
	private RewardService rewardService;
	@Autowired
	private GPushService gpushService;
	@Autowired
	private SMSService smsservice;

	@Autowired
	private TradePriceHistoryService tradePriceHistoryService;

	/**
	 * 派单必须 要配给 符合条件的 导游
	 * 
	 * @param orderNo
	 * @param guideId
	 * @param result
	 * @param request
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping(value = "v1.0/e/order/assign", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public ReturnResult assignorder(String orderNo, @RequestParam(required = true) String guideId, @RequestParam(required = true) String remark, @RequestParam(required = true) String opUserId,
			@RequestParam(required = true) String opUserName) {
		OrderValidator.validateOrderNo(orderNo);
		optTradeDeliverService.assignGuide(orderNo, guideId, opUserId, opUserName);
		ReturnResult returnResult = new ReturnResult();
		returnResult.setStatus(200);
		return returnResult;
	}

	@Autowired
	OrderLogService orderLogService;

	@RequestMapping(value = "v1.0/e/order/disputing", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public ReturnResult disputing(String orderNo, String opUserId, String opUserName, HttpServletRequest request) {

		OrderValidator.validateOrderNo(orderNo);
		OrderValidator.validateParamString(opUserId, "操作员ID");
		OrderValidator.validateParamString(opUserName, "操作员名称");

		misOptOrderService.dispute(orderNo, opUserName, opUserId);
		return new ReturnResult();
	}

	@RequestMapping(value = "v1.0/e/order/cancel/do", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public ReturnResult cancel(@Valid MISCancelOrderParam param, BindingResult result, HttpServletRequest request) {
		refundService.misRefundProccess(param);
		return new ReturnResult();
	}

	@RequestMapping(value = "v1.0/e/order/edit", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public ReturnResult editOrder(@Valid MISEditOrderParam orderParam, BindingResult result, HttpServletRequest request) throws ParseException {
		OrderType orderType = OrderType.getType(orderParam.getOrderType());

		//		if (orderType.equals(OrderType.PICKUPORDER)) {   //FIXME 添加验证
		//			VisaType.isValidate(orderParam.getIsArrivalVisa());
		//		}

		OrderBean orderBean = new OrderBean();
		Date serviceTime = DateUtils.parseDate(orderParam.getServiceDate(), "yyyy-MM-dd HH:mm:ss");
		orderBean.setServiceTime(serviceTime);
		orderBean.setOrderSource(orderParam.getOrderSource());
		orderBean.setOrderChannel(orderParam.getOrderChannel());
		BeanUtilsEnhance.copyProperties(orderBean, orderParam);
		orderBean.setServicePassCity(orderParam.getServicePassCitys());

		switch (orderType) {
		case PICKUPORDER:
			misOptOrderService.updatePickUpOrder(orderBean);
			break;
		case TRANSFER:
			misOptOrderService.updateTransferOrder(orderBean);
			break;
		case DAILY:
		case COMMENDATION:
			misOptOrderService.updateDailyOrder(orderBean, orderParam.getServiceRecTime());
			break;
		case PERUSE:
			misOptOrderService.updateSingleOrder(orderBean);
			break;
		default:
			throw new TradeException(TradeReturnCodeEnum.ORDER_PARAM_FAILED, "订单类别错误");
		}
		gpushService.pushEditOrder(orderBean);
		smsservice.editOrderSMS(orderBean);
		return new ReturnResult();
	}

	@RequestMapping(value = "v1.0/e/order/edit/price", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public ReturnResult updateGuidePrice(@Valid TradePriceHistoryParam param, BindingResult result, HttpServletRequest request) {
		if (param.getTargetPrice() <= 0) {
			logger.error("导游价不能修改为 <= 0的值，输入参数：" + JSON.toJSONString(param));
			throw new TradeException(TradeReturnCodeEnum.ORDER_PARAM_FAILED, "导游价");
		}
		misOptOrderService.updateGuidePrice(param);
		return new ReturnResult();
	}

	@RequestMapping(value = "v1.0/e/order/pre/edit/seq", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public ReturnResult getPriceSeq(@RequestParam("orderNumber") String orderNumber, HttpServletRequest request) {
		Integer tph = tradePriceHistoryService.getMaxTradePriceByOrderNumber(orderNumber);
		ReturnResult returnResult = new ReturnResult();
		returnResult.setData(tph);
		return returnResult;
	}

	@RequestMapping(value = "v1.0/e/order/reword", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public ReturnResult orderReward(@RequestParam("orderNo") String orderNo, @RequestParam("rewordprice") Double rewordprice, @RequestParam("opUserId") String opUserId,
			@RequestParam("opUserName") String opUserName, String remark, HttpServletRequest request) {
		rewardService.reword(orderNo, rewordprice, opUserId, opUserName, remark);
		return new ReturnResult();
	}

	@RequestMapping(value = "v1.0/e/order/flight/register", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public ReturnResult registerFlightNo(@RequestParam(required = true) String flightNo, @RequestParam(required = true) String flightCode, @RequestParam(required = true) String flightDestCode,
			@RequestParam(required = true) String flightDate, @RequestParam(required = true) String opUserId, @RequestParam(required = true) String opUserName,
			@RequestParam(required = true) String orderNo, HttpServletRequest request) {

		return flightRESTfulService.registerFlightNo(flightNo, flightCode, flightDestCode, flightDate, opUserId, opUserName, orderNo);
	}

	@RequestMapping(value = "v1.0/e/order/guide/appraise", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public ReturnResult appraiseGuide(@Valid AppraiseGuideParam param, BindingResult result, HttpServletRequest request) {
		misOptOrderService.appraiseGuide(param);
		return new ReturnResult();
	}
}
