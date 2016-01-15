package com.hbc.api.trade.order.controller.opt;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.mortbay.log.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hbc.api.trade.bdata.common.rsp.ReturnResult;
import com.hbc.api.trade.bdata.common.util.BeanUtilsEnhance;
import com.hbc.api.trade.order.controller.opt.req.CEditOrderParam;
import com.hbc.api.trade.order.controller.validator.OrderValidator;
import com.hbc.api.trade.order.enums.TradeReturnCodeEnum;
import com.hbc.api.trade.order.enums.order.OrderSource;
import com.hbc.api.trade.order.enums.order.OrderStatus;
import com.hbc.api.trade.order.enums.order.OrderType;
import com.hbc.api.trade.order.enums.order.VisaType;
import com.hbc.api.trade.order.exception.TradeException;
import com.hbc.api.trade.order.mapping.gen.bean.OrderBean;
import com.hbc.api.trade.order.mapping.genx.xbean.AppraiseGuideParam;
import com.hbc.api.trade.order.service.COptOrderService;
import com.hbc.api.trade.order.service.OrderQueryService;
import com.hbc.api.trade.pay.service.RefundService;
import com.hbc.api.trade.sec.TradeAccountContext;
import com.hbc.api.trade.third.push.GPushService;
import com.hbc.api.trade.third.sms.SMSService;

@RestController
@RequestMapping("trade")
public class COptOrderController {
	@Autowired private COptOrderService 	cOptOrderService;
	@Autowired private RefundService 		refundService;
	@Autowired private TradeAccountContext 	tradeAccountContext;
	@Autowired private GPushService 		gpushService;
	@Autowired private SMSService 			smsservice;
	@Autowired private OrderQueryService 	orderQueryService;
	
	@RequestMapping(value = "v1.0/c/order/edit", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public ReturnResult editOrder(@Valid CEditOrderParam orderParam, BindingResult result, HttpServletRequest request) {
		OrderType orderType = OrderType.getType(orderParam.getOrderType());
		if (orderType.equals(OrderType.PICKUPORDER)) {
			VisaType.isValidate(orderParam.getIsArrivalVisa());
		}
		OrderBean orderBean = orderQueryService.getOrderByNo(orderParam.getOrderNo());
		OrderStatus orderStatus = OrderStatus.getStatus(orderBean.getOrderStatus());
		
		if(OrderStatus.INITSTATE.equals(orderStatus)||OrderStatus.PAYSUCCESS.equals(orderStatus)||OrderStatus.GUIDE_ARRIVED.equals(orderStatus)||
				OrderStatus.PICK_CUSTOMER.equals(orderStatus)||OrderStatus.STROKE_END.equals(orderStatus)){
			BeanUtilsEnhance.copyProperties(orderBean, orderParam);
			String userId = tradeAccountContext.getUserId();
			OrderValidator.validateParamString(userId, "用户ID");
			orderBean.setUserId(userId);
			orderBean.setServicePassCity(orderParam.getServicePassCitys());
			switch (orderType) {
			case PICKUPORDER:
				cOptOrderService.updatePickUpOrder(orderBean);
				break;
			case TRANSFER:
				cOptOrderService.updateTransferOrder(orderBean);
				break;
			case DAILY:
			case COMMENDATION:
				cOptOrderService.updateDailyOrder(orderBean, orderParam.getServiceRecTime());
				break;
			case PERUSE:
				cOptOrderService.updateSingleOrder(orderBean);
				break;
			default:
				throw new TradeException(TradeReturnCodeEnum.ORDER_PARAM_FAILED, "订单类别错误");
			}
			gpushService.pushEditOrder(orderBean);
			smsservice.editOrderSMS(orderBean);
		}else{
			throw new TradeException(TradeReturnCodeEnum.ORDER_EDIT_FAILED, orderParam.getOrderNo()+" "+orderStatus.name);
		}
		return new ReturnResult();
		
	}


	@RequestMapping(value = "v1.0/c/order/cancel", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public ReturnResult cancelorder(String orderNo, String reason, HttpServletRequest request) {

		OrderValidator.validateOrderNo(orderNo);
		OrderBean orderBean =orderQueryService.getOrderByNo(orderNo);
		ReturnResult returnResult = new ReturnResult();
		if(OrderSource.C.value.equals(orderBean.getOrderSource())) {
			refundService.refundOrder(orderNo, reason);
		} else {
			Log.info("CAPP无权取消非C端的订单，订单号：" + orderBean.getOrderNo() + ", orderSorce:" + OrderSource.getType(orderBean.getOrderSource()).name);
			returnResult.setMessage(TradeReturnCodeEnum.CANNOT_CANCEL_GDS_ORDER.getMessage());
			returnResult.setStatus(TradeReturnCodeEnum.CANCEL_C_ORDER.getCode());
		}
		return returnResult;
	}
	

	@RequestMapping(value = "v1.0/c/order/evaluate", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public ReturnResult appraiseGuide(@Valid AppraiseGuideParam param, BindingResult result, HttpServletRequest request) {
		cOptOrderService.appraiseGuide(param);
		return new ReturnResult();
	}

}
