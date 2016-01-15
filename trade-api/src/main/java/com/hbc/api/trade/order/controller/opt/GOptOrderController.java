/**
 * @Author lukangle
 * @2015年10月12日@上午10:46:13
 */
package com.hbc.api.trade.order.controller.opt;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.hbc.api.fund.biz.third.GuideService;
import com.hbc.api.trade.bdata.common.rsp.ReturnResult;
import com.hbc.api.trade.order.controller.daily.DailyController;
import com.hbc.api.trade.order.controller.validator.OrderValidator;
import com.hbc.api.trade.order.enums.TradeReturnCodeEnum;
import com.hbc.api.trade.order.enums.deliver.OrderDeliverStatus;
import com.hbc.api.trade.order.enums.order.OrderStatus;
import com.hbc.api.trade.order.exception.TradeException;
import com.hbc.api.trade.order.mapping.gen.bean.OrderBean;
import com.hbc.api.trade.order.mapping.genx.xbean.GOrderAdditionalCostParamBean;
import com.hbc.api.trade.order.mapping.genx.xbean.GOrderRoadMapParamBean;
import com.hbc.api.trade.order.service.GOptOrderService;
import com.hbc.api.trade.order.service.OrderQueryService;
import com.hbc.api.trade.sec.TradeAccountContext;

@RestController
@RequestMapping("trade")
public class GOptOrderController {
	private final static Logger logger = LoggerFactory.getLogger(DailyController.class);

	@Autowired private GOptOrderService orderService;
	@Autowired private OrderQueryService orderQueryService;
	@Autowired private GuideService 	 guideService;

	@RequestMapping(value = "v1.0/g/order/guidearrive", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public ReturnResult guideArrive(String orderNo, HttpServletRequest request) {

		OrderValidator.validateOrderNo(orderNo);
		String guideId = tradeAccountContext.getUserId();
		OrderValidator.validateParamString(guideId, "导游ID");
		String guideName = guideService.getGuide(guideId).getGuideName(); // validated
		OrderBean orderBean = orderQueryService.getOrderByNo(orderNo);
		OrderStatus ostatus = OrderStatus.getStatus(orderBean.getOrderStatus());
		OrderDeliverStatus deliverStatus = OrderDeliverStatus.getType(orderBean.getDeliverStatus());
		if (ostatus.equals(OrderStatus.PAYSUCCESS) && deliverStatus.equals(OrderDeliverStatus.deliversuccess) && orderBean.getGuideId() != null) {
			orderService.guideArrive(orderNo, guideId, guideName);
			return new ReturnResult();
		}
		logger.error("订单状态不正确，此时订单状态为：" + ostatus.value + "(" + ostatus.name + ") 、" + "发单状态为：" + deliverStatus.value + "("
				+ deliverStatus.name + ")、导游ID为[" + orderBean.getGuideId() + "]");
		throw new TradeException(TradeReturnCodeEnum.ORDER_GUID_ARRIVE);
	}

	@RequestMapping(value = "v1.0/g/order/receivegust", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public ReturnResult receiveGuest(String orderNo, HttpServletRequest request) {

		OrderValidator.validateOrderNo(orderNo);
		OrderBean orderBean = orderQueryService.getOrderByNo(orderNo);
		OrderStatus ostatus = OrderStatus.getStatus(orderBean.getOrderStatus());
		if (!ostatus.equals(OrderStatus.GUIDE_ARRIVED)) {
			logger.error("订单【"+orderNo+"】状态错误，此时订单状态为：" + ostatus.value + "(" + ostatus.name + ")");
			throw new TradeException(TradeReturnCodeEnum.ORDER_GUID_RECEIVECUST);
		}
		orderService.receiveGuest(orderNo);
		return new ReturnResult();
	}

	@RequestMapping(value = "v1.0/g/order/complete", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public ReturnResult complete(String orderNo, HttpServletRequest request) {

		OrderValidator.validateOrderNo(orderNo);
		String guideId = tradeAccountContext.getUserId();
		OrderValidator.validateParamString(guideId, "导游ID");
		String guideName = guideService.getGuide(guideId).getGuideName(); // validated
		OrderBean orderBean = orderQueryService.getOrderByNo(orderNo);
		OrderStatus ostatus = OrderStatus.getStatus(orderBean.getOrderStatus());
		if (!ostatus.equals(OrderStatus.PICK_CUSTOMER)) {
			logger.error("订单【"+orderNo+"】此时订单状态为：" + ostatus.value + "(" + ostatus.name + ")");
			throw new TradeException(TradeReturnCodeEnum.ORDER_GUID_STROCKEND);
		}
		orderService.complete(orderNo, guideId, guideName);
		return new ReturnResult();
	}
	@Autowired
	TradeAccountContext tradeAccountContext;	
	/**
	 * 确认费用
	 * @param costApplyInfo 包含了所有付费项目及订单号，在service里已经对无增项费用进行处理
	 * @return
	 */
	@RequestMapping(value = "v1.0/g/order/confirmcost", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public ReturnResult confirmCost(@RequestParam(required=true) String costApplyInfo, HttpServletRequest request) {
		logger.info("确认费用请求：" + costApplyInfo);
		GOrderAdditionalCostParamBean additionalCostParam = null;
		try {
			additionalCostParam = JSON.parseObject(costApplyInfo, GOrderAdditionalCostParamBean.class);
		} catch (Exception e) {
			logger.error("输入参数非正确的JSON字符串，解析失败。JSON input：" + costApplyInfo, e);
			throw new TradeException(TradeReturnCodeEnum.ORDER_PARAM_FAILED, "输入");
		}
		if (additionalCostParam == null)
			throw new TradeException(TradeReturnCodeEnum.ORDER_PARAM_FAILED, "输入");
		String orderNo = additionalCostParam.getOrderNo();
		OrderValidator.validateOrderNo(orderNo);
		OrderBean orderBean = orderQueryService.getOrderByNo(orderNo);
		OrderStatus ostatus = OrderStatus.getStatus(orderBean.getOrderStatus());
		if (!ostatus.equals(OrderStatus.STROKE_END)) {
			logger.error("订单【"+orderNo+"】此时订单状态为：" + ostatus.value + "(" + ostatus.name + ")");
			throw new TradeException(TradeReturnCodeEnum.ORDER_GUID_STROCKEND);
		}
		String guideId = tradeAccountContext.getUserId();
		OrderValidator.validateParamString(guideId, "导游ID");
		String guideName = guideService.getGuide(guideId).getGuideName(); // validated
		orderService.confirmcost(orderBean, additionalCostParam, guideId, guideName);
		return new ReturnResult();
	}

	// TODO roadmap 待完成
	@RequestMapping(value = "v1.0/g/order/roadmap", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public ReturnResult roadmap(GOrderRoadMapParamBean roadMapParam, HttpServletRequest request) {
		System.out.println(roadMapParam.getEndTime());
		System.out.println(roadMapParam.getStartTime());
		System.out.println(roadMapParam.getGpsPoints());
		System.out.println(roadMapParam.getOrderNo());
		OrderValidator.validateOrderNo(roadMapParam.getOrderNo());
		return new ReturnResult();
	}
}
