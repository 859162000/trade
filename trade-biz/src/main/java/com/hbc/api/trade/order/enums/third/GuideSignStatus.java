/**
 * @Author lukangle
 * @2015年11月12日@下午7:29:56
 */
package com.hbc.api.trade.order.enums.third;

public enum GuideSignStatus {
	// 1-待审核;2-已审核待培训;3-已培训;4-审核不通过
	SIGNSTATUS_TOEXAM(1, "待审核"), SIGNSTATUS_EXAMED(2, "待培训"), SIGNSTATUS_TRAINED(3, "已培训"), SIGNSTATUS_NOT_PASS(4, "审核不通过"), ;

	public Integer value;
	public String desc;

	private GuideSignStatus(Integer value, String desc) {
		this.value = value;
		this.desc = desc;
	}

	public Integer getCode() {
		return value;
	}

	public String getDesc() {
		return desc;
	}

	public static GuideSignStatus getType(Integer value) {
		GuideSignStatus[] guideSignStatus = GuideSignStatus.values();
		for (GuideSignStatus guideSignStatu : guideSignStatus) {
			if (guideSignStatu.value.equals(value)) {
				return guideSignStatu;
			}
		}
		return null;
	}
}
