/**
 * @Author lukangle
 * @2015年11月20日@下午5:18:43
 */
package com.hbc.api.trade.settle.enums;

/**
 * HBC应收在途账户	HBC担保账户	HBC应付在途账户		HBC收益账户	HBC待收车导补贴账户
 */
public enum AccountEnums {
//	HBC_receivable_transit("80001", "HBC应收在途账户"),
	HBC_Guarantee("80002", "HBC担保账户"),
//	HBC_Payable("80003", "HBC应付在途账户"),
	HBC_Profit("80008", "HBC收益账户"),
	HBC_BUTIE("80005", "HBC待收车导补贴账户"),
	HBC_COUP("80006", "HBC券补贴账户"),	
	OLD_HBC_Profit("81009", "HBC收益账户"),
	OLD_HBC_BUTIE("81010", "HBC补贴账户"),
	
	QUNA_ACCOUNT("60001","去哪账户"),
	XIECHEN_ACCOUNT("60002","携程账户"),
	QUA_ACCOUNT("60003","去啊账户"),
	;
	

	public String value;
	public String desc;

	private AccountEnums(String value, String desc) {
		this.value = value;
		this.desc = desc;
	}

	public String getCode() {
		return value;
	}

	public String getDesc() {
		return desc;
	}
	
	public static AccountEnums getAccount(Integer value){
		AccountEnums[] carClassEnums = AccountEnums.values();
		for(AccountEnums carClassEnum : carClassEnums){
			if(carClassEnum.value.equals(value)){
				return carClassEnum;
			}
		}
		return null;
	}
}
