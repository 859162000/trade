/**
 * @Author lukangle
 * @2015年12月3日@下午8:53:14
 */
package com.hbc.api.trade.order.enums.third;

public enum SmsTemplate {
	PAYSUCCESS_ZHIXIAO("1", 1, "下载皇包车APP，使用下单时的手机号注册，即可查询订单详情并与您的车导取得联系"),
	PAYSUCCESS_FENXIAO("12", 12, "亲爱的，您从{0}预订的{1}服务已预订成功（订单号{2}），皇家车导正在激烈争抢订单中，请稍后。"),
	PAYSUCCESS_APP("22", 22, "【皇包车】您的{0}服务已预订成功（订单号{1}），订单详情及行程动态请进入APP及时查看；24小时全球客服电话：4006-1000-66（境内） +861056295713（境外）"),
	
	
	GUIDECONFIRM_ZHIXIAO("2",2,"亲爱的，您的{0}服务（订单号{1}）由皇家车导{2}提供，拨打+{3}呼叫他。想与车导联系了解当地好吃好玩的？请点击t.cn/RLBpPZx下载皇包车APP，使用下单时的手机号注册，与车导进行互动吧"),
	GUIDECONFIRM_FENXIAO("13",13,""),
	GUIDECONFIRM_APP("23",23,""),
	
	LINXIN_MORE_ZHIXIAO("3",3,""),
	LININ_MORE_TAIGUO_ZHIXIAO("4",4,""),
	LINXIN_LESS_ZHIXIAO("5",5,""),
	LININ_LESS_TAIGUO_ZHIXIAO("6",6,""),
	LININ_M_TAIGUO_ZHIXIAO("7",7,""),
	
	CANCLE_ORDER_ZHIXIAO("8",8,"亲爱的，您从{0}预订的{1}服务（订单号{2}）已取消成功，退款办理中，登陆APP查看退款进度及详情"),
	EDIT_ORDER_ZHIXIAO("9",9,"亲爱的，您从{0}预订的{1}服务（订单号{2}）,订单信息修改成功，登陆APP查看详情"),
	REGUIDE_ORDER_ZHIXIAO("10",10,"亲爱的，您从{0}预订的{1}服务（订单号{2}），重新分配了与您气场更匹配的皇家车导{3}，拨打+{4}呼叫他或登陆APP与车导直接对话，记得有困难要找守(jing)护(cha)神，我在APP上等着服侍您！"),
	
	LINXIN_MORE_FENXIAO("14",14,""),
	LININ_MORE_TAIGUO_FENXIAO("15",15,""),
	LINXIN_LESS_FENXIAO("16",16,""),
	LININ_LESS_TAIGUO_FENXIAO("17",17,""),
	LININ_M_TAIGUO_FENXIAO("18",18,""),
	CANCLE_ORDER_FENXIAO("19",19,"亲爱的，您从{0}预订的{1}服务（订单号{2}）已取消成功，退款办理中"),
	EDIT_ORDER_FENXIAO("20",20,"亲爱的，您从{0}预订的{1}服务（订单号{2}）,订单信息修改成功"),
	REGUIDE_ORDER_FENXIAO("21",21,"亲爱的，您从{0}预订的{1}服务（订单号{2}），重新分配了与您气场更匹配的皇家车导{3}，拨打+{4}即可呼叫他。"),
	
	LINXIN_MORE_APP("24",24,""),
	LININ_MORE_TAIGUO_APP("25",25,""),
	LINXIN_LESS_APP("26",26,""),
	LININ_LESS_TAIGUO_APP("27",27,""),
	LININ_M_TAIGUO_APP("28",28,""),
	REGUIDE_ORDER_APP("29",29,"【皇包车-更换导游】您预订的{0}服务（订单号{1}），重新分配了与您气场更匹配的皇家车导{2}，拨打+{3}呼叫他；出国旅行不方便，我在电话这头儿守护你，记得有困难要找守(jing)护(cha)神：4006-1000-66（境内） +861056295713（境外）"),
	
	
	GLEAVE_SMS("36",36,"当地时间{0}您有要服务的{1}订单，请您准时去接您的客人{2}，详情请登录APP查看【皇包车】"),
	GSERVICE_START_SMS("37",37,"注意！您的{0}服务已到服务时间，请尽快开始服务。并马上进入APP进行相关操作。【皇包车】"),

	;
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

	SmsTemplate(String type, int templateId, String message) {
		this.type = type;
		this.templateId = templateId;
		this.message = message;
	}
}
