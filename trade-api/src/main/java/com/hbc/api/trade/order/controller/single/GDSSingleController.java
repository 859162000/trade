package com.hbc.api.trade.order.controller.single;

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
import com.hbc.api.trade.order.controller.single.req.PeruseOrderParam;
import com.hbc.api.trade.order.controller.validator.OrderValidator;
import com.hbc.api.trade.order.enums.order.OrderSource;
import com.hbc.api.trade.order.enums.order.OrderType;
import com.hbc.api.trade.order.mapping.gen.bean.OrderBean;
import com.hbc.api.trade.order.service.COptOrderService;
import com.hbc.api.trade.third.restful.UCenterService;

@RestController
@RequestMapping("trade")
public class GDSSingleController {
	@Autowired private COptOrderService 	orderService;
	@Autowired private UCenterService 		ucenterService;
	
	@RequestMapping(value = "v1.0/ca/order/single",
			method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public ReturnResult placePeruseBill(@Valid PeruseOrderParam param, BindingResult result, HttpServletRequest request) {

		OrderValidator.validateParamNumberGreaterThan0(param.getPriceChannel(), "订单单价");
		OrderValidator.validateParamNumberGreaterThan0(param.getPriceTicket(), "票面价");
		OrderValidator.validateParamNumberGreaterThan0(param.getAdultNum(), "成人座位数");
		OrderValidator.validateParamNumberGreaterThan0(param.getChildNum(), "儿童座位数");
		OrderValidator.validateParamNumberGreaterThan0(param.getCarSeatNum(), "座位数");
		OrderValidator.validateParamString(param.getAgentId(), "代理商ID");
		OrderValidator.validateParamString(param.getAgentName(), "代理商名称");
		OrderValidator.validateParamString(param.getAgentOpid(), "代理商操作员id");
		OrderValidator.validateParamString(param.getAgentOpname(), "代理商操作员姓名");

		if(OrderType.COMMENDATION.value.equals(param.getOrderType())) {
			OrderValidator.validateParamString(param.getGoodNo(), "商品ID");
			OrderValidator.validateParamNumber(param.getOrderGoodsType(), "商品类别");
			OrderValidator.validateParamString(param.getLineDescription(), "线路描述");
			OrderValidator.validateParamString(param.getLineSubject(), "线路主题");
		} else {
			param.clearCommendationParam();
			param.setOrderType(OrderType.PERUSE.value);
		}
		
		OrderBean orderBean = new OrderBean();
		BeanUtilsEnhance.copyProperties(orderBean, param);
		orderBean.setAgentOpid(param.getAgentOpid());
		orderBean.setAgentOpname(param.getAgentOpname());
		orderBean.setUserId(ucenterService.obtainOrGenerateUserIdForGDS(orderBean.getUserAreaCode1(), orderBean.getUserMobile1(),
				orderBean.getAgentId(), orderBean.getAgentName()));
		orderBean.setServiceTime(TimeConverter.toDate(param.getServiceTimeL()));
		orderBean.setOrderSource(OrderSource.GDS.value);
		String billId = orderService.addOrder(orderBean);
		ReturnResult returnResult = new ReturnResult();
		JSONObject jobj = new JSONObject();
		jobj.put("orderno", billId);
		returnResult.setData(jobj);
		return returnResult;
	}

}
