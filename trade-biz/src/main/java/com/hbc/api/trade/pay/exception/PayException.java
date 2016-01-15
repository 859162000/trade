package com.hbc.api.trade.pay.exception;

import com.hbc.api.trade.bdata.common.exception.BaseException;
import com.hbc.api.trade.bdata.common.exception.ReturnCode;


public class PayException extends BaseException {

	private static final long serialVersionUID = -4315017134342708285L;

	public PayException(ReturnCode returnCode, Object... params) {
		super(returnCode, params);
	}
}
