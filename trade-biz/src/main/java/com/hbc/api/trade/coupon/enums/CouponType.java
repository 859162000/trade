/**
 * @Author lukangle
 * @2015年10月6日@下午7:50:53
 */
package com.hbc.api.trade.coupon.enums;

import com.hbc.api.trade.coupon.exception.CouponException;

/**
 * 优惠券类型：1-现金券；2-折扣券； 3-包车折扣券
 * @author Jongly Ran
 */
public enum CouponType {
	/** 1: 现金券 */
	CASH(1, "现金券"), 
	
	/** 2: 折扣券 */
	SALE(2,"折扣券"), 
	
	/** 3: 包车折扣券 */
	SALE_CHARTERED_CAR_ONLY(3,"包车折扣券"); 	
	
	public int value;
	public String name;
	CouponType(int value,String name){
		this.value = value;
		this.name = name;
	}
	
	public static CouponType getType(int value){
		CouponType[] otypes = CouponType.values();
		for(CouponType orderType : otypes){
			if(orderType.value==value){
				return orderType;
			}
		}
		
		throw new CouponException(CouponReturnCodeEnum.COUPON_TYPE_NOT_FOUND);
	}

	/**
	 * 如果不存在，抛出TradeException
	 */
	public static void isValidate(Integer orderType) {
		getType(orderType);
	}
}
