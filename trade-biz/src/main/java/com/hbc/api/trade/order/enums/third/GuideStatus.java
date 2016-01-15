/**
 * @Author lukangle
 * @2015年11月5日@下午7:25:04
 */
package com.hbc.api.trade.order.enums.third;

public enum GuideStatus {
	NOMAL(1, "正常"), CARTYPE_COMFOTABLE(0, "不可用");

	public Integer value;
	public String name;
	GuideStatus(int value,String name){
		this.value = value;
		this.name = name;
	}
	
	public static GuideStatus getType(Integer value){
		GuideStatus[] guideStatus = GuideStatus.values();
		for(GuideStatus guideStatu : guideStatus){
			if(guideStatu.value.equals(value)){
				return guideStatu;
			}
		}
		return null;
	}
}
