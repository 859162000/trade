/*
 * Copyright (c) 2015-2016, CCLX.COM. All rights reserved.
 * WANDA GROUP PROPRIETARY/CONFIDENTIAL. 
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is private property; you can't redistribute it and/or modify it
 * under the terms of the LICENSE you obtained from
 *
 *    http://www.cclx.com/
 * 
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. 
 *
 * Author: Jongly Ran
 * Revision: 1.0
 */
package com.hbc.api.fund.biz.validator;

import com.hbc.api.fund.biz.mapping.genx.xbean.FundWithDrawQueryBean;
import com.hbc.api.fund.biz.mapping.genx.xbean.FundWithdrawDenyQueryBean;
import com.hbc.api.trade.bdata.common.exception.ParamValidateException;
import com.hbc.api.trade.bdata.common.exception.enums.CommonReturnCodeEnum;
import com.hbc.api.trade.bdata.common.util.ParameterValidator;

/**
 * @author Jongly Ran
 */
public class FundValidator extends ParameterValidator {

	public static void validateBankNo(String bankNo) {
		validateParamString(bankNo, "银行卡号");
	}

	public static void validateFundWithDrawQueryBean(FundWithDrawQueryBean queryBean) {
		//validateParamString(queryBean.getGuideId(), "导游ID");
		//validateParamString(queryBean.getGuideNo(), "导游编号");

		if (queryBean.getProcessStatus() < FundWithDrawQueryBean.UNPROCESS || queryBean.getProcessStatus() > FundWithDrawQueryBean.AUTO_PROC) {
			//throw new ParamValidateException(CommonReturnCodeEnum.PARAM_ERROR_WITHARG, "处理状态参数异常，提现处理状态只能为：[0=初始态  | 1=已处理  | 2=自动提现]");
			throw new ParamValidateException(CommonReturnCodeEnum.PARAM_ERROR_WITHARG, "处理状态参数异常!");
		}

		//		if (queryBean.getProcessStatus() == FundWithDrawQueryBean.AUTO_PROC && queryBean.getDrawStatus() == null) {
		//			//throw new ParamValidateException(CommonReturnCodeEnum.PARAM_ERROR_WITHARG, "自动提款状态不能为空，状态有效值：[-2=删除 | -1=删除申请提现 | 0=申请提现默认 | 1=已转账 | 2-自动提现失败 | 3-自动提现已申请]");
		//			throw new ParamValidateException(CommonReturnCodeEnum.PARAM_ERROR_WITHARG, "自动提款状态不能为空！");
		//		}
	}

	public static void validateFundWithdrawDenyQueryBean(FundWithdrawDenyQueryBean queryBean) {
		validateParamString(queryBean.getOptId(), "操作员ID");
		validateParamString(queryBean.getOptName(), "操作员姓名");
		validateParamString(queryBean.getDrawNo(), "流水号");
		validateParamString(queryBean.getAccountNo(), "导游资金账号");
		validateParamString(queryBean.getGuideId(), "导游ID");
		validateParamString(queryBean.getReason(), "取消原因");

		if (queryBean.getAppliedAmount() == null || queryBean.getAppliedAmount() <= 0) {
			throw new ParamValidateException(CommonReturnCodeEnum.PARAM_ERROR_WITHARG, "取消提现金额无效！");
		}

	}
}
