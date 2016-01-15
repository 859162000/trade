/**
 * @Author lukangle
 * @2015年10月6日@下午7:50:53
 */
package com.hbc.api.trade.coupon.enums;

import com.hbc.api.trade.coupon.exception.CouponException;

/**
 * 转赠人类型：1-B端用户；2-C端用户
 * @author Jongly Ran
 */
public enum FromUserType {
	/** 1: B端用户 */
	B(1, "B端用户"), 
	
	/** 2: C端用户 */
	C(2,"C端用户"); 	
	
	public int value;
	public String name;
	FromUserType(int value,String name){
		this.value = value;
		this.name = name;
	}
	
	public static FromUserType getType(int value){
		FromUserType[] otypes = FromUserType.values();
		for(FromUserType orderType : otypes){
			if(orderType.value==value){
				return orderType;
			}
		}
		
		throw new CouponException(CouponReturnCodeEnum.FROM_USER_TYPE_NOT_FOUND);
	}

	/**
	 * 如果不存在，抛出TradeException
	 */
	public static void isValidate(Integer orderType) {
		getType(orderType);
	}
}
