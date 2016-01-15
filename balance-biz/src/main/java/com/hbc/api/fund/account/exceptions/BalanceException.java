package com.hbc.api.fund.account.exceptions;

import com.hbc.api.trade.bdata.common.exception.BaseException;
import com.hbc.api.trade.bdata.common.exception.ReturnCode;

public class BalanceException extends BaseException {

	private static final long serialVersionUID = -4315017134342708285L;

	public BalanceException(ReturnCode returnCode, Object... params) {
		super(returnCode, params);
	}
}
