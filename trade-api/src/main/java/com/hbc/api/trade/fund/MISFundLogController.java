/**
 * @Author lukangle
 * @2015年11月18日@下午2:56:51
 */
package com.hbc.api.trade.fund;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hbc.api.fund.account.enums.BizType;
import com.hbc.api.fund.account.mapping.gen.bean.FundAccountLog;
import com.hbc.api.fund.account.parm.FundAccountLogParam;
import com.hbc.api.fund.account.parm.QAccountLogParam;
import com.hbc.api.fund.account.service.FundAccountLogService;
import com.hbc.api.trade.bdata.common.rsp.ReturnResult;
import com.hbc.api.trade.order.controller.validator.OrderValidator;
import com.hbc.api.trade.order.service.FundLogService;

/**
 * 给代理商充值
 */
@RestController
@RequestMapping("fund")
public class MISFundLogController {
	@Autowired private FundAccountLogService 	fundAccountLogService;
	@Autowired private FundLogService 			fundLogService;

	@RequestMapping(value = "v1.0/e/account/logs", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public ReturnResult payAccount(FundAccountLogParam param, HttpServletRequest request) {
		OrderValidator.validateParamString(param.getAccountNo(), "资金账户");
		OrderValidator.validateParamString(param.getGuideId(), "导游ID");
		Map<String, Object> data = fundLogService.getFundAccountLogForMIS(param);
		ReturnResult  returnResult = new ReturnResult ();
		returnResult.setData(data);
		return returnResult;
	}

	@RequestMapping(value = "v1.0/e/account/alllogs", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public ReturnResult accountLogs(@Valid QAccountLogParam accountLogParam, BindingResult result, HttpServletRequest request) {
		List<Integer> bizTypeList = new LinkedList<>();
		bizTypeList.add(BizType.INVITEREWORD.value);
		bizTypeList.add(BizType.RT.value);
		accountLogParam.setBizTypeList(bizTypeList);
		int totalSize = fundAccountLogService.getAccountAllLogsTotalSize(accountLogParam);
		List<FundAccountLog> accountLogs = fundAccountLogService.getAccountAllLogs(accountLogParam);
		ReturnResult  returnResult = new ReturnResult ();
		returnResult.setData(accountLogs, totalSize);
		return returnResult;
	}
	
	@RequestMapping(value = "v1.0/e/account/paylogs", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public ReturnResult accountPayLogs(@Valid QAccountLogParam accountLogParam, BindingResult result, HttpServletRequest request) {
		List<Integer> bizTypeList = new LinkedList<>();
		bizTypeList.add(BizType.PAY.value);
		accountLogParam.setBizTypeList(bizTypeList);
		int totalSize = fundAccountLogService.getAccountAllLogsTotalSize(accountLogParam);
		List<FundAccountLog> accountLogs = fundAccountLogService.getAccountAllLogs(accountLogParam);
		ReturnResult  returnResult = new ReturnResult ();
		returnResult.setData(accountLogs, totalSize);
		return returnResult;
	}
}
