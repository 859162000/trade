package com.hbc.api.trade.order.controller.transfer;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.hbc.api.trade.bdata.common.rsp.ReturnResult;
import com.hbc.api.trade.bdata.common.util.BeanUtilsEnhance;
import com.hbc.api.trade.bdata.common.util.TimeConverter;
import com.hbc.api.trade.order.controller.transfer.req.TransferOrderParam;
import com.hbc.api.trade.order.controller.validator.OrderValidator;
import com.hbc.api.trade.order.enums.TradeReturnCodeEnum;
import com.hbc.api.trade.order.enums.order.FlightIsCustom;
import com.hbc.api.trade.order.enums.order.OrderSource;
import com.hbc.api.trade.order.enums.order.OrderType;
import com.hbc.api.trade.order.exception.TradeException;
import com.hbc.api.trade.order.mapping.gen.bean.OrderBean;
import com.hbc.api.trade.order.service.COptOrderService;
import com.hbc.api.trade.sec.TradeAccountContext;
/**
 * 送机
 */
@RestController
@RequestMapping("trade")
public class TransferController {
	private static final Logger log = LoggerFactory.getLogger(TransferController.class);
	
	@Autowired private COptOrderService orderService;
	@Autowired private TradeAccountContext tradeAccountContext;
	@RequestMapping(value = "v1.0/c/order/transfer", 
			method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public ReturnResult placePickupBill(@Valid TransferOrderParam param, BindingResult result, HttpServletRequest request) {
		OrderValidator.validateParamNumberGreaterThan0(param.getPriceChannel(), "订单单价");
		OrderValidator.validateParamNumberGreaterThan0(param.getAdultNum(), "成人座位数");
		OrderValidator.validateParamNumberGreaterThan0(param.getChildNum(), "儿童座位数");
		OrderValidator.validateParamNumberGreaterThan0(param.getCarSeatNum(), "座位数");
		param.clearGDSParam();
		
		if(param.getCheckInPrice() != null && param.getPriceChannel() < param.getCheckInPrice()) {
			log.error("送机时，订单价不能小于checkIn价");
			throw new TradeException(TradeReturnCodeEnum.ORDER_PARAM_FAILED, "订单价");
		}
		OrderBean orderBean = new OrderBean();
		BeanUtilsEnhance.copyProperties(orderBean, param);
		String userId = tradeAccountContext.getUserId();
		OrderValidator.validateParamString(userId, "用户ID");
		orderBean.setUserId(userId);
		orderBean.setOrderType(OrderType.TRANSFER.value);
		orderBean.setServiceTime(TimeConverter.toDate(param.getServiceTimeL()));
		orderBean.setFlightFlyTime(TimeConverter.toDate(param.getFlightFlyTimeL()));
		orderBean.setFlightArriveTime(TimeConverter.toDate(param.getFlightArriveTimeL()));
		orderBean.setFlightIsCustom(FlightIsCustom.NORMAL.value); // 非必须
		orderBean.setOrderSource(OrderSource.C.value);
		String billId = orderService.addOrder(orderBean);
		ReturnResult returnResult = new ReturnResult();
		JSONObject jobj = new JSONObject();
		jobj.put("orderno", billId);
		returnResult.setData(jobj);
		return returnResult;
	}
}
