/**
 * @Author lukangle
 * @2015年10月21日@下午7:19:23
 */
package com.hbc.api.trade.pay.enums;

public enum GetWayEnum {
	ALI(1,"支付宝"), 
	WEIXIN(2,"微信"),
	INNER(3,"内部账户支付"),
	COUP(4,"券支付"),
	
	/** 17，去哪儿 */
	QUNAR(17, "QUNA渠道"),
	
	/** 20，携程 */
	CTRIP(20, "携程渠道"),
	
	/** 19，去啊 */
	QUA(19, "去啊渠道")
	;
	
	public int value;
	public String name;
	GetWayEnum(int value,String name){
		this.value = value;
		this.name = name;
	}
	
	public static GetWayEnum getType(int value){
		GetWayEnum[] getWayEnums = GetWayEnum.values();
		for(GetWayEnum getWayEnum : getWayEnums){
			if(getWayEnum.value==value){
				return getWayEnum;
			}
		}
		return null;
	}
}
