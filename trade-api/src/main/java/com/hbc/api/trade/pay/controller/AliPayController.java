/**
 * @Author lukangle
 * @2015年10月14日@下午4:27:58
 */
package com.hbc.api.trade.pay.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.hbc.api.trade.bdata.common.rsp.ReturnResult;
import com.hbc.api.trade.order.controller.validator.OrderValidator;
import com.hbc.api.trade.pay.controller.req.AliMobilePayReqUrlParam;
import com.hbc.api.trade.pay.controller.req.AliWebPayReqUrlParam;
import com.hbc.api.trade.pay.getway.alibaba.AliPayService;
import com.hbc.api.trade.sec.TradeAccountContext;

@RestController
@RequestMapping("trade")
public class AliPayController {

	@Autowired
	AliPayService aliWebPayService;
	@Autowired
	TradeAccountContext tradeAccountContext;
	@RequestMapping(value = "v1.0/ca/pay/getwebpayurl", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public ReturnResult getAlipayUrl(@Valid AliWebPayReqUrlParam aliWebPayReqUrlParam, BindingResult result, HttpServletRequest request) {

		String mobilePayUrl =  aliWebPayService.getWebPayUrl(aliWebPayReqUrlParam.getOrderNo(), aliWebPayReqUrlParam.getUserId(),aliWebPayReqUrlParam.getActualPrice(),aliWebPayReqUrlParam.getCoupId());
		ReturnResult returnResult = new ReturnResult();
		JSONObject jobj = new JSONObject();
		jobj.put("payurl", mobilePayUrl);
		returnResult.setData(jobj);
		return returnResult;
	}

	@RequestMapping(value = "v1.0/c/pay/getmobilepayurl", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public ReturnResult getMobileAlipayUrl(@Valid AliMobilePayReqUrlParam payParam, BindingResult result, HttpServletRequest request) {
		String userId = tradeAccountContext.getUserId();
		OrderValidator.validateParamString(userId, "用户ID");
		String mobilePayUrl = aliWebPayService.getMobilePayUrl(payParam.getOrderNo(), payParam.getActualPrice(),userId, payParam.getAppId(), payParam.getAppEnv(),payParam.getCoupId());
		ReturnResult returnResult = new ReturnResult();
		JSONObject jobj = new JSONObject();
		jobj.put("payurl", mobilePayUrl);
		returnResult.setData(jobj);
		return returnResult;
	}

}
