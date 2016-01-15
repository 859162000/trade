/**
 * @Author lukangle
 * @2015年11月23日@下午12:21:49
 */
package com.hbc.api.trade.order.enums.third;

public enum GuideJobTypeEnum {
	
	//1-兼职(学生)；2-兼职(工作)；3-专职(个体）；4-专职（地接社）
	student(1, "兼职(学生)"), jianzhi(2, "兼职(工作)"), zhuanzhi(3, "专职(个体）"), zhuanzhidijieshe(4, "专职（地接社）"),
	;
	public Integer value;
	public String name;
	GuideJobTypeEnum(int value,String name){
		this.value = value;
		this.name = name;
	}
	
	public static GuideJobTypeEnum getType(Integer value){
		GuideJobTypeEnum[] carTypeEnums = GuideJobTypeEnum.values();
		for(GuideJobTypeEnum coupStatus : carTypeEnums){
			if(coupStatus.value.equals(value)){
				return coupStatus;
			}
		}
		return null;
	}
}
