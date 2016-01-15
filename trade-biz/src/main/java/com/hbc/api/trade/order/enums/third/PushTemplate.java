/**
 * @Author lukangle
 * @2015年11月13日@下午3:27:36
 */
package com.hbc.api.trade.order.enums.third;

public enum PushTemplate {
//	NEW_ORDER_PUSH(43, "您有1个新订单，{0},请赶快登录皇包车-导游端APP去接单吧。", "GP1 - 行前，新订单 - 语音，圣旨到"), 
//	CANSEL_ORDER_PUSH(46, "{0}客人（{1}）取消了此订单GP12 -", " 用户取消订单"), 
//	SUCCESS_ORDER_PUSH(44, "恭喜您接单成功，请赶快登录皇包车-车导端APP查看吧", "GP16 - 接单成功"),
//
//	DIJIESHE_SEND_PUSH(45, "您的老板{0}指派给您一个新订单，请登录皇包车-导游端APP查看", "GP14 - 地接社指派订单"), 
//	ORDER_UPDATE_PUSH(47, "您{0}的{1}订单，订单内容发生变更。详情请登录皇包车-导游端APP查看", "GP13 - 订单内容变更"), 
//	GUIDE_PICKUP_USER(48, "当地时间{0}您有要服务的{1}订单，请您准时去接您的客人{2}，详情请登录APP查看", "GP2 - 待服务订单提醒导游"),
//	USER_FLY_PUSH(49, "您的客人{0}乘坐的航班{1}已于您当地的时间{2}起飞,请合理安排接机时间", "GP3 - 客人起飞通知导游"),
//	USER_DELAY_PUSH(50, "您的客人{0}乘坐的航班{1}延误起飞，具体起飞时间请等待通知", "GP4 - 航班延误通知导游"),
//	USER_FLIGHT_CACLE(51, "您的客人{0}乘坐的航班{1}取消", "GP5 - 航班取消通知导游"), 
//	POJIANG_FLIGHT_USER(52, "您的客人{0}乘坐的航班{1}备降，具体服务时间请等待通知", "GP6 - 航班备降通知导游"), 
//	USER_FANHAGN_FLIGHT(53,"您的客人{0}乘坐的航班{1}返航，具体服务时间请等待通知", "GP7 - 航班返航通知导游"), 
//	SERVICE_TIME_ARRICE(54, "注意！您的{0}服务已到服务时间，请尽快开始服务。并马上进入APP进行相关操作。", "GP8 - 服务开始通知导游"),
//	FLIGHT_ARRIVER_PUSH(55, "您的客人{0}乘坐的航班{1}已于您当地的时间{2}落地", "GP9 - 航班落地通知导游"), 
//	ORDER_COMPLETE_PUSH(56, "您的{0}服务已经完成，订单号{1}，请您及时确认订单费用！！", "GP10 - 订单结算通知导游确认费用"), 
//	ADDITION_COMPLETE(57, "您提交的增项费用申请已处理完成，订单号{0}，请登录皇包车-导游端APP查看！", "GP11 - 增项费用处理完成通知导游"), 
//	REWORD_PUSH(58, "您收到一笔订单奖金，订单号{0}，请登录皇包车-车导端APP查看", "GP15 - 订单奖金通知导游"), 
//	PKFAILED_PUSH(59, "很遗憾您PK失败，请再接再厉", "GP19 - 导游PK失败"), 
//	GUI_AA(60, "服务呱呱棒的皇家车导{0}为您提供{1}服务【皇包车】", "C1 - 导游已确认"), 
//	CHUXING_GUIDE(61, "距离您出行还有24小时，带好护照、开通漫游、充好电喔【皇包车】", "C2 - 出发前24小时"), 
//	GUIDE_CAR_PIPEI(62, "调整为与您气场更匹配的皇家车导{0}为您服务，嘻嘻不谢！【皇包车】", "C3 - 更换导游"), 
//	REFUND_OK(63, "您预定的{0}服务已退款成功【皇包车】", "C4 - 取消订单（退款完成）"), 
//	ORDER_REDEAY_PAY(64, "您的{0}，请点击查看详情【皇包车】", "C5 - 增项费用待支付"), ;
//	public int value;
//	public String name;
//	public String name1;
//
//	PushTemplate(int value, String name, String name1) {
//		this.value = value;
//		this.name = name;
//		this.name1 = name1;
//	}
//
//	public static PushTemplate getType(int value) {
//		PushTemplate[] carTypeEnums = PushTemplate.values();
//		for (PushTemplate carTypeEnum : carTypeEnums) {
//			if (carTypeEnum.value == value) {
//				return carTypeEnum;
//			}
//		}
//		return null;
//	}
	
	NEW_ORDER_PUSH("G1", 41, "行前，新订单"),

	GUIDE_PICKUP_USER("G2", 42, "待服务订单提醒导游"),

	USER_FLY_PUSH("G3", 43, "客人起飞通知导游"),

	USER_DELAY_PUSH("G4", 44, "航班延误通知导游"),

	USER_FLIGHT_CACLE("G5", 45, "航班取消通知导游"),

	POJIANG_FLIGHT_USER("G6", 46, "航班备降通知导游"),

	USER_FANHAGN_FLIGHT("G7", 47, "航班返航通知导游"),
	/**48	2	注意！您的{0}服务已到服务时间，请尽快开始服务。并马上进入APP进行相关操作。	1	1	order	2			G8 - 服务开始通知导游**/
	SERVICE_TIME_ARRICE("G8", 48, "服务开始通知导游"),

	FLIGHT_ARRIVER_PUSH("G9", 49, "航班落地通知导游"),

	ORDER_COMPLETE_PUSH("G10", 50, "订单结算通知导游确认费用"),

	ADDITION_COMPLETE("G11", 51, "增项费用处理完成通知导游"),

	CANSEL_ORDER_PUSH("G12", 52, "用户取消订单"),

	ORDER_UPDATE_PUSH("G13", 53, "订单内容变更"),

	DIJIESHE_SEND_PUSH("G14", 54, "地接社指派订单"),

	REWORD_PUSH("G15", 55, "订单奖金通知导游"),

	SUCCESS_ORDER_PUSH("G16", 56, "接单成功"),

	G17("G17", 57, "导游报名成功"),

	G18("G18", 58, "导游报名失败"),

	PKFAILED_PUSH("G19", 59, "导游PK失败"),

	G20("G20", 60, "被邀请导游已报名"),

	GUI_AA("C1", 61, "导游已确认"),

	CHUXING_GUIDE("C2", 62, "出发前24小时"),

	GUIDE_CAR_PIPEI("C3", 63, "更换导游"),

	REFUND_OK("C4", 64, "取消订单（退款完成）"),

	ORDER_REDEAY_PAY("C5", 65, "增项费用待支付"),

	IM("IM", 66, "IM新消息"),

	M1("M1", 67, "公告、荣誉榜、培训PUSH"),

	M2("M2", 68, "串单，给导游发消息，自动push"),

	M3("M3", 69, "被邀请导游报名，给邀请人发消息，自动push"),

	M4("M4", 70, "导游申请提现失败，给导游发消息，自动push"),
	
	ORDER_SERIAL("M5", 71, "串单，给导游发消息，自动push"),

	M6("M6", 72, "被邀请导游报名，给邀请人发消息，自动push"),

	M7("M7", 73, "导游申请提现失败，给导游发消息，自动push");

	/**
	 * PUSH类型，在调用推送接口时，extras放入此参数标识，app识别该类型做对应处理
	 */
	private String type;
	/**
	 * PUSH调用模板ID，与PUSH类型一一对应
	 */
	private int templateId;
	/**
	 * PUSH应用场景
	 */
	private String message;

	public String getType() {
		return type;
	}

	public int getTemplateId() {
		return templateId;
	}

	public String getMessage() {
		return message;
	}

	PushTemplate(String type, int templateId, String message) {
		this.type = type;
		this.templateId = templateId;
		this.message = message;
	}
}
