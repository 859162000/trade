/**
 * @Author lukangle
 * @2015年10月6日@下午7:50:53
 */
package com.hbc.api.trade.coupon.enums;

import com.hbc.api.trade.coupon.exception.CouponException;

/**
 * 代金券状态：0-初始化（默认）；1-绑定；2-使用；-1-废弃
 * @author Jongly Ran
 */
public enum CouponStatus {
	/** -1: 初始化（默认）*/
	DISABLE(-1, "废弃"), 
	
	/** 0: 初始化（默认）*/
	initial(0, "初始化（默认）"), 
	
	/** 1: 绑定 */
	BIND(1,"绑定"), 
	
	/** 2: 使用*/
	USED(2, "使用"); 	
	
	public int value;
	public String name;
	CouponStatus(int value,String name){
		this.value = value;
		this.name = name;
	}
	
	public static CouponStatus getType(int value){
		CouponStatus[] otypes = CouponStatus.values();
		for(CouponStatus orderType : otypes){
			if(orderType.value==value){
				return orderType;
			}
		}
		
		throw new CouponException(CouponReturnCodeEnum.COUPON_STATUS_NOT_FOUND);
	}

	/**
	 * 如果不存在，抛出TradeException
	 */
	public static void isValidate(Integer orderType) {
		getType(orderType);
	}
}
