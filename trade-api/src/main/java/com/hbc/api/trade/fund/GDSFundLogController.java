/**
 * @Author lukangle
 * @2015年11月18日@下午2:56:51
 */
package com.hbc.api.trade.fund;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hbc.api.fund.account.parm.FundAccountLogParam;
import com.hbc.api.trade.bdata.common.rsp.ReturnResult;
import com.hbc.api.trade.order.controller.validator.OrderValidator;
import com.hbc.api.trade.order.service.FundLogService;
@RestController
@RequestMapping("fund")
public class GDSFundLogController {
	@Autowired private FundLogService 	fundAccountLogService;

	@RequestMapping(value = "v1.0/ca/account/logs", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public ReturnResult payAccount(FundAccountLogParam param, HttpServletRequest request) {
		OrderValidator.validateParamString(param.getAccountNo(), "资金账户");
		Map<String, Object> data = fundAccountLogService.getFundAccountLogForGDS(param);
		ReturnResult  returnResult = new ReturnResult ();
		returnResult.setData(data);
		return returnResult;
	}
}
