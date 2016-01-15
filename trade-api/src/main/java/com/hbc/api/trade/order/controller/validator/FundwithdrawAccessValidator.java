/*
 * COPYRIGHT (C) 2015-2016,LUOSHUAI. ALL RIGHTS RESERVED.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files
 * (the “Software”), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, 
 * publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, 
 * subject to the following conditions: 
 *
 *   a).The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software!
 *   b).Any individual or entity would be granted by LUOSHUAI before using this Software!
 *  
 * Please contact through email luoshuai@live.com if you need additional informations OR have any questions.
 *
 * THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT
 * LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. 
 * IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, 
 * WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE 
 * SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 * Author: Luoshuai 
 * Revision: 1.0
 * 
 *  
 */
package com.hbc.api.trade.order.controller.validator;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hbc.api.fund.biz.enums.FundDrawStatus;
import com.hbc.api.fund.biz.enums.FundReturnCodeEnum;
import com.hbc.api.fund.biz.exception.FundException;
import com.hbc.api.fund.biz.mapping.gen.bean.FundWithdrawal;
import com.hbc.api.fund.biz.service.FundWithdrawService;

/**
 * @author LuoShuai
 *
 */
@Component
public class FundwithdrawAccessValidator {

	private static final Logger logger = LoggerFactory.getLogger(FundwithdrawAccessValidator.class);

	private static final int SEVEN_DAYS_IN_MS = 7 * 24 * 60 * 60 * 1000;

	@Autowired
	private FundWithdrawService fundWithdrawService;

	public void validateGAPFundwithdrawRight(String finAccountOrAccountNo)
	{
		doValidate(finAccountOrAccountNo);
	}

	public void validateMISFundwithdrawRight(String finAccountOrAccountNo)
	{
		doValidate(finAccountOrAccountNo);
	}

	/**
	 * 或者finAccount Fundwithdraw表 的finAccount和FundAccount表的accountNo对应
	 * 
	 * @param accountNo
	 */
	private void doValidate(String accountNo)
	{
		//ADD-FOR-BUGFIX(http://bug.hbc.tech/mantis/view.php?id=1096)
		List<FundWithdrawal> latestWithDrawList = fundWithdrawService.getLatestFundwithdraw(accountNo);
		if (latestWithDrawList != null && latestWithDrawList.size() > 0) {
			Date now = new Date();
			FundWithdrawal latestWithDraw0 = latestWithDrawList.get(0);
			if (latestWithDraw0 != null && latestWithDraw0.getApplyTime() != null && latestWithDraw0.getDrawStatus() != null)
			{
				logger.info("导游提现申请|上次提现申请信息:NO->{} | 日期->{} | 当前日期->{} | 间隙时间->{} | 提现状态->{} | {}", latestWithDraw0.getDrawNo(), latestWithDraw0.getApplyTime(), now, SEVEN_DAYS_IN_MS, latestWithDraw0.getDrawStatus(),
						FundDrawStatus.getStatus(latestWithDraw0.getDrawStatus()));
				if ((now.getTime() - latestWithDraw0.getApplyTime().getTime()) < SEVEN_DAYS_IN_MS) { //提现日期小于7天
					logger.error("导游提现申请|NO:->{} 上次提现状态->{} | {}", latestWithDraw0.getDrawNo(), latestWithDraw0.getDrawStatus(), FundDrawStatus.getStatus(latestWithDraw0.getDrawStatus()));
					if (!latestWithDraw0.getDrawStatus().equals(FundDrawStatus.AUTO_WITHDRAW_FAILED.value)
							&& !latestWithDraw0.getDrawStatus().equals(FundDrawStatus.DELETE_TO_HIDDEN.value)
							&& !latestWithDraw0.getDrawStatus().equals(FundDrawStatus.DELETE_APPLICATION.value)) {
						throw new FundException(FundReturnCodeEnum.ERR_NOT_ALLOW, "提现过于频繁(一个自然周一次)");
					}
				}
			} else {
				logger.info("导游提现申请|未查询到上次提现申请信息:FinAccount->{}", accountNo);
			}
		}
		//ADD-END
	}

}
