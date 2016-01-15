/**
 * @Author lukangle
 * @2015年11月5日@下午3:13:52
 */
package com.hbc.api.trade.order.enums.third;


public enum GuideLevelEnums {
	A(1, "A"), B(2, "B"), C(3, "C"), D(4, "D"), E(5, "E");
	public Integer value;
	public String name;
	GuideLevelEnums(int value,String name){
		this.value = value;
		this.name = name;
	}
	
	public static GuideLevelEnums getType(Integer value){
		GuideLevelEnums[] guideGrades = GuideLevelEnums.values();
		for(GuideLevelEnums guideGrade : guideGrades){
			if(guideGrade.value.equals(value)){
				return guideGrade;
			}
		}
		return null;
		
	}
	
	public static GuideLevelEnums getType(String name){
		GuideLevelEnums[] guideGrades = GuideLevelEnums.values();
		for(GuideLevelEnums guideGrade : guideGrades){
			if(guideGrade.name.equals(name)){
				return guideGrade;
			}
		}
		return null;
		
	}

}
