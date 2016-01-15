package com.hbc.api.trade.order.controller.daily;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
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
import com.hbc.api.trade.third.restful.UCenterService;

@RestController
@RequestMapping("trade")
public class GDSDailyController {

	private static final Logger logger = LoggerFactory.getLogger(GDSDailyController.class);

	@Autowired
	private COptOrderService orderService;
	@Autowired
	private UCenterService ucenterService;

	@RequestMapping(value = "v1.0/ca/order/daily", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public ReturnResult dailyOrder(@Valid @RequestBody DailyOrderParam param, BindingResult result, HttpServletRequest request) {
		logger.info("GDS下单原始请求 ->{} | {}", param.getStartAddress(), param.getStartAddressDetail());
		ServicePassCityService.validateServicePassCities(param.getServicePassCitys());
		OrderValidator.validateParamNumberGreaterThan0(param.getPriceChannel(), "订单单价");
		OrderValidator.validateParamNumberGreaterThan0(param.getAdultNum(), "成人座位数");
		OrderValidator.validateParamNumberGreaterThan0(param.getChildNum(), "儿童座位数");
		OrderValidator.validateParamNumberGreaterThan0(param.getCarSeatNum(), "座位数");
		OrderValidator.validateParamNumberGreaterThan0(param.getTotalDays(), "总天数");
		OrderValidator.validateParamString(param.getAgentId(), "代理商ID");
		OrderValidator.validateParamString(param.getAgentName(), "代理商名称");
		OrderValidator.validateParamString(param.getAgentOpid(), "代理商操作员id");
		OrderValidator.validateParamString(param.getAgentOpname(), "代理商操作员姓名");
		OrderValidator.validateParamNumberGreaterThan0(param.getPriceTicket(), "票面价");
		if (OrderType.COMMENDATION.value.equals(param.getOrderType())) {
			OrderValidator.validateParamString(param.getGoodNo(), "商品ID");
			OrderValidator.validateParamNumber(param.getOrderGoodsType(), "商品类别");
			OrderValidator.validateParamString(param.getLineDescription(), "线路描述");
			OrderValidator.validateParamString(param.getLineSubject(), "线路主题");
		} else {
			param.clearCommendationParam();
			param.setOrderType(OrderType.DAILY.value);
		}

		String startAddress = param.getStartAddress();
		String startAddressDetail = param.getStartAddressDetail();
		if (StringUtils.isBlank(startAddress) || startAddress == "null") {
			param.setStartAddress("未填写");
		}

		if (StringUtils.isBlank(startAddressDetail) || startAddressDetail == "null") {
			param.setStartAddressDetail("未填写");
		}

		logger.info("GDS下单-AFTER-SET ->{} | {}", param.getStartAddress(), param.getStartAddressDetail());

		OrderBean orderBean = new OrderBean();
		BeanUtilsEnhance.copyProperties(orderBean, param);
		orderBean.setAgentOpid(param.getAgentOpid());
		orderBean.setAgentOpname(param.getAgentOpname());
		orderBean.setUserId(ucenterService.obtainOrGenerateUserIdForGDS(orderBean.getUserAreaCode1(), orderBean.getUserMobile1(),
				orderBean.getAgentId(), orderBean.getAgentName()));
		orderBean.setJourneyComment(param.getJourneyComment());
		orderBean.setOrderSource(OrderSource.GDS.value);
		// default: 早上8点
		String stimestr = param.getServiceRecTime() == null ? "08:00:00" : param.getServiceRecTime();
		String servicetimel = param.getServiceDate() + " " + stimestr;
		orderBean.setServiceTime(TimeConverter.converTime(servicetimel, "serviceRecTime"));
		orderBean.setServiceEndTime(TimeConverter.converTime(param.getServiceEndDate() + " 23:59:59", "serviceEndDate"));
		orderBean.setServicePassCity(param.getServicePassCitys());
		orderBean.setStartAddress(StringUtils.isBlank(param.getStartAddress()) ? "未填写" : param.getStartAddress()); //startAddress
		orderBean.setStartAddressDetail(StringUtils.isBlank(param.getStartAddressDetail()) ? "未填写" : param.getStartAddressDetail());
		logger.info("GDS下单请求->{}", orderBean);
		String orderNo = orderService.addOrder(orderBean);
		ReturnResult returnResult = new ReturnResult();
		JSONObject jobj = new JSONObject();
		jobj.put("serviceRecTime", stimestr);
		jobj.put("orderno", orderNo);
		returnResult.setData(jobj);
		return returnResult;
	}
}
