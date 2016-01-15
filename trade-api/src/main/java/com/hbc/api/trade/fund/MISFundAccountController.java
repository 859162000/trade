/**
 * @Author lukangle
 * @2015年11月14日@下午5:16:52
 */
package com.hbc.api.trade.fund;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.hbc.api.fund.account.enums.AccountChangeType;
import com.hbc.api.fund.account.enums.AccountType;
import com.hbc.api.fund.account.enums.BalanceReturnCodeEnum;
import com.hbc.api.fund.account.enums.BizType;
import com.hbc.api.fund.account.mapping.gen.bean.FundAccount;
import com.hbc.api.fund.account.parm.AccountParam;
import com.hbc.api.fund.account.parm.PayParm;
import com.hbc.api.fund.account.service.FundAccountService;
import com.hbc.api.fund.account.service.RechargeService;
import com.hbc.api.trade.bdata.common.rsp.ReturnResult;
import com.hbc.api.trade.fund.req.MisGuideRePunish;
import com.hbc.api.trade.order.service.FundLogService;
import com.hbc.api.trade.sec.TradeAccountContext;

@RestController
@RequestMapping("fund")
public class MISFundAccountController {

	@Autowired
	private FundAccountService fundAccountService;

	@RequestMapping(value = "v1.0/e/createAccount", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public ReturnResult dailyOrder(@Valid AccountParam accountParam, BindingResult result, HttpServletRequest request) {
		String areacode = accountParam.getAreacode();
		String mobile = accountParam.getMobile();
		String accountName = accountParam.getAccountName();
		String userId = accountParam.getUserId();
		AccountType accountType = AccountType.getType(accountParam.getAccountType());
		ReturnResult returnResult = new ReturnResult();
		JSONObject jobj = new JSONObject();
		jobj.put("accountNo", fundAccountService.addAccount(areacode, mobile, accountName, userId, accountType));
		returnResult.setData(jobj);
		return returnResult;
	}

	@Autowired
	FundLogService fundLogService;
	@Autowired
	private TradeAccountContext tradeAccountContext;

	@RequestMapping(value = "v1.0/e/account/balances", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public ReturnResult getAccountBalances(String fundAccount, HttpServletRequest request) {
		FundAccount fundAccountBean = fundAccountService.getFundAccount(fundAccount);
		Map<String, Double> data = new HashMap<>(2);
		data.put("totalAmount", fundLogService.getFutureAmount(fundAccountBean.getAccountNo()));
		double useableAmount = fundAccountBean.getAmount();
		data.put("useableAmount", useableAmount < 0.0 ? 0.0 : useableAmount);
		ReturnResult returnResult = new ReturnResult();
		returnResult.setData(data);
		return returnResult;
	}

	@RequestMapping(value = "v1.0/e/account/created/flowing/user/register", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public ReturnResult createAccount(@Valid AccountParam accountParam, BindingResult result, HttpServletRequest request) {
		String accountNo = accountParam.getAccountNo();
		String areacode = accountParam.getAreacode();
		String mobile = accountParam.getMobile();
		String accountName = accountParam.getAccountName();
		String userId = accountParam.getUserId();
		AccountType accountType = AccountType.getType(accountParam.getAccountType());
		fundAccountService.addAccount(accountNo, areacode, mobile, accountName, userId, accountType);
		return new ReturnResult();
	}

	@RequestMapping(value = "v1.0/e/account/detail", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public ReturnResult findAccount(@RequestParam(required = true) String accountNo, BindingResult bindResult, HttpServletRequest request) {
		FundAccount result = fundAccountService.getFundAccount(accountNo);
		ReturnResult returnResult = new ReturnResult();
		returnResult.setData(result);
		return returnResult;
	}

	@RequestMapping(value = "v1.0/e/account/pay", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public ReturnResult payAccount(@Valid PayParm payParm, BindingResult result, HttpServletRequest request) {
		fundAccountService.pay(payParm);
		ReturnResult returnResult = new ReturnResult();
		returnResult.setStatus(200);
		return returnResult;
	}

	@RequestMapping(value = "v1.0/e/account/enable", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public ReturnResult fund_account_enable(@RequestParam(required = true) String accountNo, @RequestParam(required = true) String optId, @RequestParam(required = true) String optName,
			HttpServletRequest request) {
		boolean result = fundAccountService.enable(accountNo, optId, optName);
		ReturnResult returnResult = new ReturnResult();
		if (result) {
			returnResult.setMessage(BalanceReturnCodeEnum.ACCOUNT_ENABLE_SUCCESS.getMessage());
		} else {
			returnResult.setStatus(BalanceReturnCodeEnum.ACCOUNT_ENABLE_FAILED.getCode());
			returnResult.setMessage(BalanceReturnCodeEnum.ACCOUNT_ENABLE_FAILED.getMessage());
		}
		return returnResult;
	}

	@RequestMapping(value = "v1.0/e/account/disable", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public ReturnResult fund_account_disable(@RequestParam(required = true) String accountNo, @RequestParam(required = true) String optId, @RequestParam(required = true) String optName,
			HttpServletRequest request) {
		boolean result = fundAccountService.disable(accountNo, optId, optName);
		ReturnResult returnResult = new ReturnResult();
		if (result) {
			returnResult.setMessage(BalanceReturnCodeEnum.ACCOUNT_DISABLE_SUCCESS.getMessage());
		} else {
			returnResult.setStatus(BalanceReturnCodeEnum.ACCOUNT_DISABLE_FAILED.getCode());
			returnResult.setMessage(BalanceReturnCodeEnum.ACCOUNT_DISABLE_FAILED.getMessage());
		}
		return returnResult;
	}

	@RequestMapping(value = "v1.0/e/account/reword", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public ReturnResult guideReword(@RequestParam(required = true) String fundAccount, @RequestParam(required = true) Double amount, @RequestParam(required = true) String optId,
			@RequestParam(required = true) String optName, HttpServletRequest request) {
		ReturnResult returnResult = new ReturnResult();
		if (amount < 100 && amount > 0 && fundAccount != null && fundAccount.length() > 0) {
			fundAccountService.pay(fundAccount, amount, BizType.REWARD_BY_YINGDAOYOU, optId, optName);
		} else {
			returnResult.setStatus(400);
		}
		return returnResult;
	}
	@Autowired
	RechargeService rechargeService;
	@RequestMapping(value = "v1.0/e/account/rewordPunished", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public ReturnResult guidePunished(@Valid MisGuideRePunish misGuideRePunish, BindingResult result, HttpServletRequest request) {
		ReturnResult returnResult = new ReturnResult();

		Integer rtype = misGuideRePunish.getRtype();
		if (1 == rtype) {
			// 导游奖励
			fundAccountService.pay(misGuideRePunish.getFundAccount(), misGuideRePunish.getAmount(), BizType.REWARD_BY_MIS, 
					AccountChangeType.MISCG,misGuideRePunish.getOrderNo(),misGuideRePunish.getOptId(), misGuideRePunish.getOptName(),
					misGuideRePunish.getRemark());
		} else if (2 == rtype) {
			rechargeService.punished(misGuideRePunish.getFundAccount(), misGuideRePunish.getAmount(), BizType.PUNISH_BY_MIS,
					AccountChangeType.MISCG,misGuideRePunish.getOrderNo(),misGuideRePunish.getOptId(), misGuideRePunish.getOptName(),
					misGuideRePunish.getRemark());
		}else {
			returnResult.setStatus(400);
		}
		return returnResult;
	}

}
