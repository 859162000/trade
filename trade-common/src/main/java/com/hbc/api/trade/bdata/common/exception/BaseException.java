/**
 * Copyrights  2015  HuangBaoChe
 *
 * All rights reserved.
 *
 * Created on 2015年9月11日 下午4:51:11
 * 
 * @author HanZhaozhan
 *
 */
package com.hbc.api.trade.bdata.common.exception;

import com.hbc.common.exception.ReturnCode;

public class BaseException extends com.hbc.common.exception.BaseException {

	private static final long serialVersionUID = -6371381429248690866L;
	
	/**
	 * @param returnCode
	 * @param args
	 */
	public BaseException(ReturnCode returnCode, Object[] args) {
		super(returnCode, args);
	}

}
