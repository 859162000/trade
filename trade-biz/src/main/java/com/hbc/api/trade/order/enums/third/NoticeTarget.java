package com.hbc.api.trade.order.enums.third;

public enum NoticeTarget {
	
	GUIDE(1,"导游"),
	USER(2,"用户");
	
	public Integer value;
	public String name;
	NoticeTarget(int value,String name){
		this.value = value;
		this.name = name;
	}
	
	public static NoticeTarget getType(Integer value){
		NoticeTarget[] pushTargets = NoticeTarget.values();
		for(NoticeTarget pushTarget : pushTargets){
			if(pushTarget.value.equals(value)){
				return pushTarget;
			}
		}
		return null;
	}
}
