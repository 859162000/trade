package com.hbc.api.trade.order.controller.daily;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.hbc.api.trade.bdata.common.rsp.ReturnResult;
import com.hbc.api.trade.bdata.common.util.BeanUtilsEnhance;
import com.hbc.api.trade.bdata.common.util.TimeConverter;
import com.hbc.api.trade.order.controller.daily.req.DailyOrderParam;
import com.hbc.api.trade.order.controller.validator.OrderValidator;
import com.hbc.api.trade.order.enums.order.OrderSource;
import com.hbc.api.trade.order.enums.order.OrderType;
import com.hbc.api.trade.order.mapping.gen.bean.OrderBean;
import com.hbc.api.trade.order.service.COptOrderService;
import com.hbc.api.trade.order.service.ServicePassCityService;
import com.hbc.api.trade.sec.TradeAccountContext;

@RestController
@RequestMapping("trade")
public class DailyController {
	
	@Autowired private COptOrderService orderService;
	@Autowired private TradeAccountContext tradeAccountContext;
	
	@RequestMapping(value = "v1.0/c/order/daily", 
			method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public ReturnResult dailyOrder(@Valid DailyOrderParam param, BindingResult result, HttpServletRequest request) {
		ServicePassCityService.validateServicePassCities(param.getServicePassCitys());
		OrderValidator.validateParamNumberGreaterThan0(param.getPriceChannel(), "订单单价");
		OrderValidator.validateParamNumberGreaterThan0(param.getAdultNum(), "成人座位数");
		OrderValidator.validateParamNumberGreaterThan0(param.getChildNum(), "儿童座位数");
		OrderValidator.validateParamNumberGreaterThan0(param.getCarSeatNum(), "座位数");
		OrderValidator.validateParamNumberGreaterThan0(param.getTotalDays(), "总天数");
		param.clearGDSParam();
		
		OrderBean orderBean = new OrderBean();
		BeanUtilsEnhance.copyProperties(orderBean, param);
		String userId = tradeAccountContext.getUserId();
		OrderValidator.validateParamString(userId, "用户ID");
		orderBean.setUserId(userId);
		orderBean.setOrderType(OrderType.DAILY.value);
		orderBean.setJourneyComment(param.getJourneyComment());
		orderBean.setOrderSource(OrderSource.C.value);
		// default: 早上8点
		String stimestr = param.getServiceRecTime() == null ? "08:00:00" : param.getServiceRecTime();
		String servicetimel = param.getServiceDate()+" "+stimestr;
		orderBean.setServiceTime(TimeConverter.converTime(servicetimel, "serviceRecTime"));
		orderBean.setServiceEndTime(TimeConverter.converTime(param.getServiceEndDate()+" 23:59:59", "serviceEndDate"));
		orderBean.setServicePassCity(param.getServicePassCitys());
		String orderNo = orderService.addOrder(orderBean);
		
		ReturnResult returnResult = new ReturnResult();
		JSONObject jobj = new JSONObject();
		jobj.put("serviceRecTime", stimestr);
		jobj.put("orderno", orderNo);
		returnResult.setData(jobj);
		return returnResult;
	}
}
