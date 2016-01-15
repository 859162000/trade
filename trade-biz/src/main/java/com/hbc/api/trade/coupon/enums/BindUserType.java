/**
 * @Author lukangle
 * @2015年10月6日@下午7:50:53
 */
package com.hbc.api.trade.coupon.enums;

import com.hbc.api.trade.coupon.exception.CouponException;

/**
 * 用户绑定类型：1-B端代理；2-C端用户
 * @author Jongly Ran
 */
public enum BindUserType {
	/** 1: B端代理 */
	B(1, "B端代理"), 
	
	/** 2: C端用户 */
	C(2,"C端用户"); 	
	
	public int value;
	public String name;
	BindUserType(int value,String name){
		this.value = value;
		this.name = name;
	}
	
	public static BindUserType getType(int value){
		BindUserType[] otypes = BindUserType.values();
		for(BindUserType orderType : otypes){
			if(orderType.value==value){
				return orderType;
			}
		}
		
		throw new CouponException(CouponReturnCodeEnum.BIND_USER_TYPE_NOT_FOUND);
	}

	/**
	 * 如果不存在，抛出TradeException
	 */
	public static void isValidate(Integer orderType) {
		getType(orderType);
	}
}
