/**
 * @Author lukangle
 * @2015年12月26日@下午8:31:53
 */
package com.hbc.api.trade.order.exception;

import com.hbc.api.trade.bdata.common.exception.BaseException;
import com.hbc.api.trade.bdata.common.exception.ReturnCode;

public class TradeOtaSettleException extends BaseException {

	private static final long serialVersionUID = -4315017134342708285L;

	public TradeOtaSettleException(ReturnCode returnCode, Object... params) {
		super(returnCode, params);
	}
}
