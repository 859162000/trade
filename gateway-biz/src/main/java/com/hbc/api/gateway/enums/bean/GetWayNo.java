/**
 * @Author lukangle
 * @2015年10月16日@下午4:04:26
 */
package com.hbc.api.gateway.enums.bean;


public enum GetWayNo {
	ALI(1, "支付宝"), 
	WEI_XIN(2,"微信"), 
	YI_BAO(3,"易宝");
	
	public int value;
	public String name;
	GetWayNo(int value,String name){
		this.value = value;
		this.name = name;
	}
	
	public static GetWayNo getType(int value){
		GetWayNo[] orderKafkaOpts = GetWayNo.values();
		for(GetWayNo getWayNo : orderKafkaOpts){
			if(getWayNo.value==value){
				return getWayNo;
			}
		}
		return null;
	}
}
