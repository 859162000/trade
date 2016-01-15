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
package com.hbc.api.gateway.enums;

import com.hbc.api.trade.bdata.common.exception.ReturnCode;


public enum GatewayReturnCodeEnum implements ReturnCode{
	
	GATEWAY_IpnStatus_NOT_EXIST(100002, "资金IpnStatus 枚举异常"),
	GATEWAY_REFUND_FAILED(100003, "退款失败 %S"),
	
	ERR_ALIPAY_SIGN(100004, "用URLEncoder.encode处理AliPay sign时异常"),
	ERR_ALIPAY_URL(100005, "URLEncoder.encode处理AliPay URL参数发生异常"),
	ERR_REQUEST_URL(100006, "无法解析请求地址"),
	ERR_BUFFEREDREADER_CANNOT_BE_CLOSED(100007, "BufferedReader无法关闭"),
	SIGN_EXP(100009, "签名错误"),
	ERR_TRANS_PRICE_NOT_MATCH(100008, "批次累加总金额和付款总金额不匹配"),
	ERR_TRANS_LIST_PARM(100009, "批量转账列表参数非法,不能为空,最多1000笔"),
	ERR_TRANS_PARM_DATE_ILLEGAL(100010, "转账日期格式异常"),
	ERR_TRANS_PARM_ILLEGAL(100011, "流水号 %S 导游ID不存在"),
	ERR_TRANS_GUIDE_FROZEN(100012, "流水号 %S 导游处于冻结状态"),
	
	;
	
	private int code;
	private String message;

	private GatewayReturnCodeEnum(int code, String message){
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
