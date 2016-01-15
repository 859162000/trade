/**
 * @Author lukangle
 * @2015年12月2日@下午5:39:17
 */
package com.hbc.api.trade.order.enums.third;
/**
 * 61	2	服务呱呱棒的皇家车导{0}为您提供{1}服务【皇包车】	2	1	order	2			C1 - 导游已确认
62	2	距离您出行还有24小时，带好护照、开通漫游、充好电喔【皇包车】	0	1	order	2			C2 - 出发前24小时
63	2	调整为与您气场更匹配的皇家车导{0}为您服务，嘻嘻不谢！【皇包车】	1	1	order	2			C3 - 更换导游
64	2	您预定的{0}服务已退款成功【皇包车】	1	1	order	2			C4 - 取消订单（退款完成）
65	2	您的{0}，请点击查看详情【皇包车】	1	1	order	2			C5 - 增项费用待支付
75	2	您的增项费用为{0}元，请点击查看详情【皇包车】	1	1	order	2			C6-增项费用确认
 *
 */
public enum CPushTemplate {
	GUIDEASSIGN(61,"C1","导游已确认"),
	BEFORE24(62,"C2","出发前24小时"),
	GUIDERESIGN(63,"C3","更换导游"),
	CANCLEORDER(64,"C4","取消订单（退款完成）"),
	ADDITIONORDER(65,"C5","增项费用待支付"),
	ADDITIONAL_FEE_CONFIRM(75, "C6", "增项费用确认");
	
	public Integer value;
	public String name;
	public String code;
	CPushTemplate(int value,String code, String name) {
		this.value = value;
		this.name = name;
		this.code = code;
	}

	public static CPushTemplate getType(Integer value) {
		CPushTemplate[] guideAgencyTypes = CPushTemplate.values();
		for (CPushTemplate guideAgencyType : guideAgencyTypes) {
			if (guideAgencyType.value.equals(value)) {
				return guideAgencyType;
			}
		}
		return null;
	}
}
