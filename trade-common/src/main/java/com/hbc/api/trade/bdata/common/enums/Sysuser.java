/**
 * @Author lukangle
 * @2015年11月18日@下午1:56:03
 */
package com.hbc.api.trade.bdata.common.enums;


public enum Sysuser {
	SYSUSER("700010", "系统"), 
	;


	public String id;
	public String name;

	private Sysuser(String id, String name) {
		this.id = id;
		this.name = name;
	}

	
	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public static Sysuser getType(int id){
		Sysuser[] carClassEnums = Sysuser.values();
		for(Sysuser carClassEnum : carClassEnums){
			if(carClassEnum.id.equals(id)){
				return carClassEnum;
			}
		}
		return null;
	}
}
