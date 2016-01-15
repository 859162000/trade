/**
 * @Author lukangle
 * @2015年10月17日@上午11:33:49
 */
package com.hbc.api.trade.pay.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hbc.api.trade.bdata.common.rsp.ReturnResult;
import com.hbc.api.trade.pay.controller.req.AliRefundPayParam;
import com.hbc.api.trade.pay.service.RefundService;
@Controller
@RequestMapping("trade")
public class RefundController {
	@Autowired
	RefundService refundService;
	@RequestMapping(value = "v1.0/c/pay/refund", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public ReturnResult payRefund(@Valid AliRefundPayParam aliRefundPayParam,BindingResult result, HttpServletRequest request) {
		refundService.refundOrder(aliRefundPayParam.getOrderNo(),aliRefundPayParam.getReason());
		return new ReturnResult();
	}
	
	
	@RequestMapping(value = "v1.0/e/pay/refund", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public ReturnResult payERefund(@Valid AliRefundPayParam aliRefundPayParam,BindingResult result, HttpServletRequest request) {
		refundService.refundOrder(aliRefundPayParam.getOrderNo(),aliRefundPayParam.getReason());
		return new ReturnResult();
	}
	
	
	@RequestMapping(value = "v1.0/e/pay/refundamount", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	@ResponseBody
	public ReturnResult refundAmount(String orderNo) {
		double refundMoney = refundService.getRefundAmount(orderNo);
		ReturnResult returnResult = new ReturnResult();
		returnResult.setData("refundamount", refundMoney);
		return returnResult;
	}
}
