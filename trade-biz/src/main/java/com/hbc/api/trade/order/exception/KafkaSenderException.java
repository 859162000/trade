package com.hbc.api.trade.order.exception;

import com.hbc.api.trade.bdata.common.exception.BaseException;
import com.hbc.api.trade.bdata.common.exception.ReturnCode;

/**
 * 
 * @author Jongly Ran
 */
public class KafkaSenderException extends BaseException {

	private static final long serialVersionUID = -4315017134342708285L;

	public KafkaSenderException(ReturnCode returnCode, Object... params) {
		super(returnCode, params);
	}
}
