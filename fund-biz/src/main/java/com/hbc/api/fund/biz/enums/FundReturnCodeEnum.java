/**
 * Copyrights  2015  HuangBaoChe
 *
 * All rights reserved.
 *
 * Created on 2015年9月11日 下午5:02:35
 * 
 * @author HanZhaozhan
 *
 */
package com.hbc.api.fund.biz.enums;

import com.hbc.api.trade.bdata.common.exception.ReturnCode;


public enum FundReturnCodeEnum implements ReturnCode{

	/** %s */
	ERR_INSERT(89001, "%s"),
	
	/** %s */
	ERR_UPDATE(89002, "%s"),
	
	/** %s类型不存在 */
	ERR_ENUM_TYPE(89003, "%s类型不存在"),

	/** 参数%s错误 */
	ERR_PARAM(89004, "参数%s错误"),
	
	/** %已存在 */
	ERR_EXISTED(89005, "%S已存在"),
	
	/** %不存在 */
	ERR_NOT_FUND(89005, "%S不存在"),
	
	ERR_NOT_ALLOW(89006, "%S"),

	;
	private int code;
	private String message;

	private FundReturnCodeEnum(int code, String message){
		this.code = code;
		this.message = message;
	};

	@Override
	public int getCode() {
		return this.code;
	}
	
	@Override
	public String getMessage() {
		return this.message;
	}

}
