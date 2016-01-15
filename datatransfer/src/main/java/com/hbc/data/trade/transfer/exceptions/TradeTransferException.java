package com.hbc.data.trade.transfer.exceptions;

import com.hbc.common.exception.BaseException;
import com.hbc.common.exception.ReturnCode;

public class TradeTransferException extends BaseException {

	private static final long serialVersionUID = -4315017134342708285L;

	public TradeTransferException(ReturnCode returnCode, Object... params) {
		super(returnCode, params);
	}
}
