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
package com.hbc.api.trade.coupon.enums;

import com.hbc.api.trade.bdata.common.exception.ReturnCode;


public enum CouponReturnCodeEnum implements ReturnCode{
	
	ONLY_ONE_FOR_ONCE(80012, "每个订单只能使用一张优惠券"),
	
	COUPON_NOT_FOUND(80013, "优惠券不存在"),
	
	BIND_USER_TYPE_NOT_FOUND(80014, "用户绑定类型不存在"),
	
	COUPON_STATUS_NOT_FOUND(80015, "代金券状态不存在"),
	
	COUPON_TYPE_NOT_FOUND(80016, "优惠券类型不存在"),
	
	FROM_USER_TYPE_NOT_FOUND(80017, "转赠人类型不存在"),
	
	/** 82005: %S 参数错误 */
	ORDER_PARAM_FAILED(82005, "%S 参数错误"),
	
	/** 83001: %S 更新数据异常 */
	FAILED_FOR_UPDATE(83001,"%S 更新数据异常"),
	
	/** 83002: %S 添加数据异常 */
	FAILED_FOR_INSERT(83002,"%S 添加数据异常"),
	/** 83003: 更新数据0行受影响，请确认传入的where条件对应的数据存在 */
	AFFECTED_ROWS_0(83003,"更新数据0行受影响，请确认传入的where条件对应的数据存在"),
	FAILED_GETCOUPINFO(83007,"%S 获取优惠券失败"),
	/** 83004: kafka操作异常 */
	FAILED_FOR_KAFKA(83004,"kafka操作异常"),
	FAILED_COUP_LOCKED(83005,"%S 锁定券失败"),
	FAILED_COUP_USE(83006,"%S 使用券失败"),
	;
	private int code;
	private String message;

	private CouponReturnCodeEnum(int code, String message){
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
