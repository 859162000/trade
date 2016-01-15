package com.hbc.api.trade.order.enums.order;

import com.hbc.api.trade.order.enums.TradeReturnCodeEnum;
import com.hbc.api.trade.order.exception.TradeException;

/**
 * @author Jongly Ran
 */
public enum OrderLogType {
	/** 
	 * 1: (${GDS-agentName}) ${userName}于${createTime}提交${orderType}订单 
	 * <p>call: SUBMIT_ORDER_CONTENT()</p>
	 */
	SUBMIT_ORDER(1), 
	
	/** 
	 * 2: (${GDS-agentName}) ${userName}于${createTime}修改订单 内容：${JSON} 
	 * <p>call: UPDATE_ORDER_CONTENT()</p>
	 */
	UPDATE_ORDER(2), 
	
	/** 
	 * 3: (${GDS-agentName}) ${userName}于${createTime}付款 详情：${priceInfoJSON} 
	 * <p>priceInfoJSON: 包含各种价格，例如订单价，优惠券抵消金额，票面价等</p>
	 * <p>call: PAYMENT_RESULT_CONTENT()</p>
	 */
	PAYMENT_RESULT(3),
	
	/** 
	 * 4: 导游 [${guideName}] 于${createTime} 接下此单 
	 * <p>call: ORDER_STATUS_CONTENT()</p>
	 */
	GUIDE_AGREE(4),
	
	/** 
	 * 5: 导游 [${guideName}] 于${createTime} 到达位置
	 * <p>call: ORDER_STATUS_CONTENT()</p>
	 */
	GUIDE_ARRIVE(5),
	
	/** 
	 * 6: 导游 [${guideName}] 于${createTime} 接到客人 
	 * <p>call: ORDER_STATUS_CONTENT()</p>
	 */
	GUIDE_GET_CUSTOMER(6),
	
	/** 
	 * 7: 导游 [${guideName}] 于${createTime} 已完成服务 
	 * <p>call: ORDER_STATUS_CONTENT()</p>
	 */
	GUIDE_COMPLETE(7),
	
	/** 
	 * 8: 导游 [${guideName}] 于${createTime} 完成评价 
	 * <p>call: ORDER_STATUS_CONTENT()</p>
	 */
	SCORED(8),
	
	/** 
	 * 10: 导游 [${guideName}] 于${createTime} 确认费用 
	 * <p>call: ORDER_STATUS_CONTENT()</p>
	 */
	CONFIRMED_COST(10),
	
	/** 
	 * 11: 导游 [${guideName}] 于${createTime} 结算完成
	 * <p>call: ORDER_STATUS_CONTENT()</p>
	 */
	SETTLEMENT(11),
	
	/** 
	 * 98: 重新分发订单共通知(${guideTotalNumber})名导游 付给导游金额为：${guidePrice}
	 * <p>call: RESEND_CONTENT()</p>
	 */
	RESEND(98),
	
	/** 
	 * 101: ${userName} 于${createTime} 取消订单
	 * <p>call: CANCEL_CONTENT()</p>
	 */
	CANCEL(101),
	
	/** 
	 * 102: 导游 [${userName}] 于${createTime} 取消订单
	 * <p>2015.11.02最新流程中导游不能取消订单</p>
	 */
	@Deprecated
	CANCELLED_GUIDE(102),
	
	/** 
	 * 103: (${GDS-agentName}) ${userName}于${createTime}写入留言日志
	 * <p>call: LEAVE_MESSAGE_CONTENT()</p>
	 */
	LEAVE_MESSAGE(103),
	
	/** 
	 * 107: 系统结算费用
	 * <p>call: SYSTEM_SETTLEMENT_CONTENT()</p>
	 */
	SYSTEM_SETTLEMENT(107),

	/** 
	 * 201: 管理员付给导游[(${licenseNumber})${guideName}]奖金 金额：￥${bonus}
	 * <p/>call: BONUS_CONTENT()</p>
	 */
	BONUS(201),
	
	/** 
	 * 202: 管理员派单(指定 订单号${orderNo})给导游：(${licenseNumber})${guideName}
	 * <p/>call: SPECIFY_CONTENT()</p>
	 */
	SPECIFY(202),
	
	/** 
	 * 203: 管理员于${createTime}输入投诉记录
	 * <p/>call: COMPLAINT_CONTENT()</p>
	 */
	COMPLAINT(203),
	
	/** 
	 * 204: 管理员于15年08月20日 14:53:13操作 立即发点
	 * <p>2015.11.02最新流程中无立即发点call:IMMEDIATELY_CONTENT（）</p>
	 */
	IMMEDIATELY(204),
	
	/** 
	 * 205: 管理员于${createTime}运营审批增项费用[￥${additionalCost}]
	 * <p>call: ADDITIONAL_COST_CONTENT()</p>
	 * 2015-12-12 23:23:09 确认取消
	 */
	@Deprecated
	ADDITIONAL_COST(205),
	
	/** 
	 * 206: 管理员于${createTime}增量补发
	 * <p>call: INCREMENTAL_SEND_CONTENT()</p>
	 * 2015-12-12 23:23:09 确认取消
	 */
	@Deprecated
	INCREMENTAL_SEND(206),
	
	/** 
	 * 207: 管理员运营重新指派 新导游:(${licenseNumber})${guideName}
	 * <p>2015.11.02最新流程中无重新指派，只能关闭订单后重新开订单</p>
	 */
	REASSIGN_GUIDE(207),
	
	/** 
	 * 208: (${GDS-agentName}) ${userName}于${createTime}修改（支付前）订单金额 金额为：￥${nowPrice} 原始金额：￥${originPrice}
	 * <p>call: UPDATE_PRICE_ALREADY_PAYED_CONTENT</p>
	 */
	UPDATE_PRICE_ALREADY_PAYED(208),
	
	/** 
	 * 209: 运营[${userName]将订单从未评分改为${starLevel}星
	 * <p>2015.11.02最新流程中给导游评星级</p>
	 */
	@Deprecated
	ORDER_STAR_LEVEL(209),
	
	/** 
	 * 210: 管理员于${createTime}执行注册航班操作！
	 * <p>call: REGISTER_FLIGHT_NO_CONTENT</p>
	 */
	REGISTER_FLIGHT_NO(210),
	
	/** 
	 * 211: 管理员${opUserName}取消 原导游:(${licenseNumber})${guideName}
	 * <p>call: CANCEL_ORIGIN_GUIDE_CONTENT</p>
	 */
	CANCEL_ORIGIN_GUIDE(211),
	
	/** 
	 * 212: 管理员于${createTime}帮助导游提交增项费用[￥${additionalCost}]
	 * <p>call: HELP_SUBMIT_ADDITIONAL_COST_CONTENT</p>
	 */
	HELP_SUBMIT_ADDITIONAL_COST(212),
	
	/** 
	 * 213: 运营处罚导游
	 * <p>call: PUNISHMENT_CONTENT</p>
	 */
	PUNISHMENT(213),
	
	/**
	 * 操作人员指派导游
	 */
	ASSIGN_GUIDE(214),

//	/** 
//	 * 214: 增量补发  [MIS]${agentOpname}在${createTime}操作了${deliverType}
//	 * <p>call: MIS_ORDER_CONTENT</p>
//	 */
//	INCREATMENT_ORDER(214),
//	
//	/** 
//	 * 214: 立即发单 [MIS]${agentOpname}在${createTime}操作了${deliverType}
//	 * <p>call: MIS_ORDER_CONTENT</p>
//	 */
//	IMMEDIATELY_ORDER(215),
//	
//	/** 
//	 * 214: 取消重发 [MIS]${agentOpname}在${createTime}操作了${deliverType}
//	 * <p>call: MIS_ORDER_CONTENT</p>
//	 */
//	CANCEL_GUIDE_RESEND(216),
	
	/** 
	 * 301: 导游 [${guideName}]于${createTime}提交增项费用
	 * <p>call: CHANGE_ORDER_CONTENT</p>
	 */
	CHANGE_ORDER(301),
	
	/** 
	 * 4003: 用户[${userName}]的资金账户 [${accountNo}]于${createTime}获得订单${orderNo}的退款${price}元
	 * <p>call: REFUND_CONTENT</p>
	 */
	REFUND(4003),
	
	/** 
	 * 400: 客人于${createTime}使用${paymentType}支付异动费用 金额:￥${account}元
	 * <p>2015.11.02最新流程中无异动申请</p>
	 */
	@Deprecated
	PAY_FOR_CHANGES(400),
	
	/** 
	 * 601: 地接社老板[(${userId})${userName}]重新指派 新导游:(${guideId})${guideName}
	 * <p>call: REASSIGN_GUIDE_BY_BOSS_CONTENT</p>
	 */
	REASSIGN_GUIDE_BY_BOSS(601),	
	
	DISPUTE(801),
	PAY(802),
	; 
	public Integer type;
	
	OrderLogType(Integer type){
		this.type = type;
	}
	

	/**
	 * 提交${orderType}订单
	 * @param gdsAgentName 为Null时是客户
	 * @param userName
	 * @param createTime
	 * @param orderType
	 * @return  (${GDS-agentName}) ${userName}于${createTime}提交${orderType}订单 
	 */
	public static String SUBMIT_ORDER_CONTENT(String gdsAgentName, String userName, 
			String createTime, OrderType orderType) {
		String prefix = "";
		if(gdsAgentName != null) prefix = "("+gdsAgentName+") ";
		return prefix+userName+"于"+createTime+"提交"+orderType.name+"订单";
	}
	
	/**
	 * C端提交${orderType}订单 
	 * @param userName
	 * @param createTime
	 * @param orderType
	 * @return ${userName}于${createTime}提交${orderType}订单 
	 */
	public static String SUBMIT_ORDER_CONTENT(String userName, String createTime, OrderType orderType) {
		return SUBMIT_ORDER_CONTENT(null, userName, createTime, orderType);
	}
	
	/**
	 * 
	 * @param gdsAgentName 为Null时是客户
	 * @param opUserName
	 * @param createTime
	 * @param orderBeanAsJSONString
	 * @return (${GDS-agentName}) ${userName}于${createTime}修改订单 内容：${JSON} 
	 */
	public static String UPDATE_ORDER_CONTENT(String gdsAgentName, String opUserName, 
			String createTime, String orderBeanAsJSONString) {
		String prefix = "";
		if(gdsAgentName != null) prefix = "("+gdsAgentName+") ";
		return prefix+opUserName+"于"+createTime+"修改订单 内容：" + orderBeanAsJSONString;
	}
	
	/**
	 * C端修改订单
	 * @param opUserName
	 * @param createTime
	 * @param orderBeanAsJSONString
	 * @return ${userName}于${createTime}修改订单 内容：${JSON} 
	 */
	public static String UPDATE_ORDER_CONTENT(String opUserName,String createTime, String orderBeanAsJSONString) {
		return UPDATE_ORDER_CONTENT(null, opUserName, createTime, orderBeanAsJSONString);
	}
	
	/**
	 * 
	 * @param gdsAgentName 为Null时是客户
	 * @param opUserName
	 * @param createTime
	 * @param priceInfoJSON
	 * @return (${GDS-agentName}) ${userName}于${createTime}付款 详情：${priceInfoJSON} 
	 */
	public static String PAYMENT_RESULT_CONTENT(String gdsAgentName, String opUserName, 
			String createTime, String priceInfoJSON) {
		String prefix = "";
		if(gdsAgentName != null) prefix = "("+gdsAgentName+") ";
		return prefix+opUserName+"于"+createTime+"付款 详情：" + priceInfoJSON;
	}
	
	/**
	 * C端付款
	 * @param opUserName
	 * @param createTime
	 * @param priceInfoJSON
	 * @return (${GDS-agentName}) ${userName}于${createTime}付款 详情：${priceInfoJSON} 
	 */
	public static String PAYMENT_RESULT_CONTENT(String opUserName,  String createTime, String priceInfoJSON) {
		return PAYMENT_RESULT_CONTENT(null, opUserName, createTime, priceInfoJSON);
	}
	
	/**
	 * 
	 * @param guideName
	 * @param createTime
	 * @param orderStatus
	 * @return 导游 [${guideName}] 于${createTime} ${OrderStatus.name} 
	 */
	public static String ORDER_STATUS_CONTENT(String guideName, String createTime, OrderStatus orderStatus) {
		return "导游 ["+guideName+"] 于"+createTime+" " + orderStatus.name;
	}
	
	/**
	 * 
	 * @param guideTotalNumber
	 * @param guidePrice
	 * @return 重新分发订单共通知(${guideTotalNumber})名导游 付给导游金额为：${guidePrice}
	 */
	public static String RESEND_CONTENT(String guideTotalNumber, String guidePrice) {
		return "重新分发订单共通知("+guideTotalNumber+")名导游 付给导游金额为：" + guidePrice;
	}
	
	/**
	 * 
	 * @param userName
	 * @param createTime
	 * @return ${userName} 于${createTime} 取消订单
	 */
	public static String CANCEL_CONTENT(String userName, String createTime) {
		return userName+" 于"+createTime+" 取消订单";
	}
	
	
	public static String DISPUTE_CONTENT(String userName, String createTime) {
		return userName+" 于"+createTime+" 冻结订单";
	}
	
	public static String PAY_CONTENT(String userName, String createTime,Double money) {
		return userName+" 于"+createTime+" 支付订单 "+money+" 元";
	}
	
	/**
	 * 
	 * @param gdsAgentName
	 * @param userName
	 * @param createTime
	 * @return (${GDS-agentName}) ${userName}于${createTime}写入留言日志
	 */
	public static String LEAVE_MESSAGE_CONTENT(String gdsAgentName, String userName, String createTime) {
		String prefix = "";
		if(gdsAgentName != null) prefix = "("+gdsAgentName+") ";
		return prefix+userName+"于"+createTime+"写入留言日志";
	}
	
	/**
	 * @return 系统结算费用
	 */
	public static String SYSTEM_SETTLEMENT_CONTENT() {
		return "系统结算费用";
	}
	
	/**
	 * @return 管理员付给导游[(${licenseNumber})${guideName}]奖金 金额：￥${bonus}
	 */
	public static String BONUS_CONTENT(String licenseNumber, String guideName, Double bonus) {
		return "管理员付给导游[("+licenseNumber+")"+guideName+"]奖金 金额：￥"+bonus;
	}
	
	/**
	 * 
	 * @param orderNo
	 * @param licenseNumber
	 * @param guideName
	 * @return 管理员派单(指定 订单号${orderNo})给导游：(${licenseNumber})${guideName}
	 */
	public static String SPECIFY_CONTENT(String orderNo, String licenseNumber, String guideName) {
		return "管理员派单(指定 订单号"+orderNo+")给导游：("+licenseNumber+")"+guideName;
	}
	
	/**
	 * 
	 * @param createTime
	 * @return 管理员于${createTime}输入投诉记录
	 */
	public static String COMPLAINT_CONTENT(String createTime) {
		return "管理员于"+createTime+"输入投诉记录";
	}
	
	public static String IMMEDIATELY_CONTENT(String opUserName,String createTime){
		return "管理员["+opUserName+"]于"+createTime+"立即发单";
	}
	
	
	/**
	 * 
	 * @param createTime
	 * @param additionalCost
	 * @return 管理员于${createTime}运营审批增项费用[￥${additionalCost}]
	 */
	public static String COMPLAINT_CONTENT(String createTime, Double additionalCost) {
		return "管理员于"+createTime+"运营审批增项费用[￥"+additionalCost+"]";
	}
	
	/**
	 * 
	 * @param createTime
	 * @return 管理员${opUserName}于${createTime}增量补发
	 */
	public static String INCREATAL_SEND_CONTENT(String opUserName,String createTime) {
		return "管理员["+opUserName+"]于"+createTime+"增量补发";
	}
	
	/**
	 * 
	 * @param gdsAgentName
	 * @param userName
	 * @param createTime
	 * @param nowPrice
	 * @param originPrice
	 * @return (${GDS-agentName}) ${userName}于${createTime}修改（支付前）订单金额 金额为：￥${nowPrice} 原始金额：￥${originPrice}
	 */
	public static String UPDATE_PRICE_ALREADY_PAYED_CONTENT(String gdsAgentName, String userName, String createTime, Double nowPrice, Double originPrice ) {
		return "("+gdsAgentName+") "+userName+"于"+createTime+"修改（支付前）订单金额 金额为：￥"+nowPrice+" 原始金额：￥"+originPrice;
	}
	
	/**
	 * 
	 * @param createTime
	 * @return 管理员于${createTime}执行注册航班操作！
	 */
	public static String REGISTER_FLIGHT_NO_CONTENT(String createTime ) {
		return "管理员于"+createTime+"执行注册航班操作！";
	}
	
	/**
	 * 
	 * @param licenseNumber
	 * @param guideName
	 * @return 管理员${opUserName}取消 原导游:(${licenseNumber})${guideName}
	 */
	public static String CANCEL_ORIGIN_GUIDE_CONTENT(String opUserName,String licenseNumber, String guideName ) {
		return "管理员["+opUserName+"]取消 原导游:("+licenseNumber+")" + guideName;
	}
	
	/**
	 * 
	 * @param createTime
	 * @param additionalCost
	 * @return 管理员于${createTime}帮助导游提交增项费用[￥${additionalCost}]
	 */
	public static String HELP_SUBMIT_ADDITIONAL_COST_CONTENT(String createTime, Double additionalCost ) {
		return "管理员于"+createTime+"帮助导游提交增项费用[￥"+additionalCost+"]";
	}
	
	/**
	 * @return 运营处罚导游
	 */
	public static String PUNISH_CONTENT( ) {
		return "运营处罚导游";
	}
	
	/**
	 * 
	 * @param userId
	 * @param userName
	 * @param guideId
	 * @param guideName
	 * @return 地接社老板[(${userId})${userName}]重新指派 新导游:(${guideId})${guideName}
	 */
	public static String REASSIGN_GUIDE_BY_BOSS_CONTENT(String userId, String userName, String guideId, String guideName ) {
		return "地接社老板[("+userId+")"+userName+"]重新指派 新导游:("+guideId+")"+guideName;
	}
	
	/**
	 * 
	 * @param guideName
	 * @param createTime
	 * @return 导游 [${guideName}]于${createTime}提交增项费用
	 */
	public static String CHANGE_ORDER_CONTENT(String guideName, String createTime ) {
		return "导游 ["+guideName+"]于"+createTime+"提交增项费用";
	}
	
	/**
	 * 
	 * @param agentOpname
	 * @param createTime
	 * @param deliverType
	 * @return  [MIS]${agentOpname}在${createTime}操作了${deliverType}
	 */
	public static String MIS_ORDER_CONTENT(String agentOpname, String createTime,String deliverType ) {
		return "[MIS]["+agentOpname+"]于"+createTime+"操作了"+deliverType;
	}
	
	/**
	 * 
	 * @param accountNo
	 * @param createTime
	 * @param orderNo
	 * @param price
	 * @return 用户[${userName}]的资金账户 [${accountNo}]于${createTime}获得订单${orderNo}的退款${price}元
	 */
	public static String REFUND_CONTENT(String userName, String accountNo, String createTime, String orderNo, Double price) {
		return "用户["+userName+"]的资金账户 [" + accountNo + "]于" + createTime + "获得订单"+orderNo+"的退款" + price+"元";
	}
	
	public static OrderLogType getType(Integer type){
		OrderLogType[] otypes = OrderLogType.values();
		for(OrderLogType orderType : otypes){
			if(orderType.type.equals(type)){
				return orderType;
			}
		}
		
		throw new TradeException(TradeReturnCodeEnum.ORDER_LOG_TYPE);
	}

	/**
	 * 如果不存在，抛出TradeException
	 */
	public static void isValidate(Integer orderType) {
		getType(orderType);
	}
}
