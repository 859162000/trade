/**
 * Copyrights  2015  HuangBaoChe
 *
 * All rights reserved.
 *
 * Created on 2015年9月11日 下午5:08:44
 * 
 * @author HanZhaozhan
 *
 */
package com.hbc.api.trade.bdata.common.exception;

import com.hbc.api.trade.bdata.common.exception.BaseException;
import com.hbc.api.trade.bdata.common.exception.ReturnCode;


public class ParamValidateException extends BaseException {

	private static final long serialVersionUID = -4315017134342708285L;

	public ParamValidateException(ReturnCode returnCode, Object... params) {
		super(returnCode, params);
	}
}
