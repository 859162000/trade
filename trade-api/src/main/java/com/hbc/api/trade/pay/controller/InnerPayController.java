/**
 * @Author lukangle
 * @2015年11月19日@下午4:10:38
 */
package com.hbc.api.trade.pay.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hbc.api.trade.bdata.common.rsp.ReturnResult;
import com.hbc.api.trade.pay.param.InnerPayparm;
import com.hbc.api.trade.pay.service.InnerPaymentService;

@RestController
@RequestMapping("trade")
public class InnerPayController {
	private final static Logger log = LoggerFactory.getLogger(InnerPayController.class);
	@Autowired
	InnerPaymentService innerPaymentService;
	@RequestMapping(value = "v1.0/ca/pay/innerpay", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public ReturnResult payByInner(@Valid InnerPayparm innerPayparm, BindingResult result, HttpServletRequest request) {
		innerPaymentService.payByInnerAccount(innerPayparm);
		ReturnResult returnResult = new ReturnResult();
		returnResult.setStatus(200);
		return returnResult;
	}
}
