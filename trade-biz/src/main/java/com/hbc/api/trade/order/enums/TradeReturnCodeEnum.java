/**
 * Copyrights  2015  HuangBaoChe
 *
 * All rights reserved.
 *
 * Created on 2015年9月11日 下午5:02:35
 * 
 * @author HanZhaozhan
 *
 */
package com.hbc.api.trade.order.enums;

import com.hbc.api.trade.bdata.common.exception.ReturnCode;


public enum TradeReturnCodeEnum implements ReturnCode{
	
	ORDER_ID_NOT_FOUND(80001, "订单号不存在"),
	ORDER_ADD_LOST(80002, "订单添加数据库失败"),
	PRICE_MARK_NOEXT(800019,"pricemark %S"),
	ORDER_TYPE(80003, "订单类型不存在"),
	ORDER_PRICE_FAILED(80005, "订单价格错误"),
	ORDER_NOTEXIST(80006, "%S 订单不存在"),
	ORDER_IMTOKEN_EXCEPTION(80106, "%S 下单失败"),
	ORDER_UPDATE_FAILED(80007, "订单状态DB更新失败"),
	ORDER_NONEED_UPDATE(80008, "订单状态已经变更无须更新"),
	ORDER_LOG_TYPE(80009, "订单日志类型不存在"),
	ORDER_SOURCE_TYPE(80010, "订单来源类型不存在"),
	ORDER_STATUS_TYPE(80011, "%S 订单状态类型不存在"),
	ORDER_GUIDE_EXP(800111, "%S 对应的发单信息不存在"),
	ADDITIONAL_COST_TYPE(80012, "增项费用类型不存在"),
	ORDER_TRACK_TYPE(80013, "行程动态类型不存在"),
	ORDER_DELIVER_TYPE(80014, "发单类状态不存在"),
	IS_READABLE_TYPE(80015, "订单是否可见状态不存在"),
	DEMAND_DEAL_TYPE(80016, "是否愿意接单状态不存在"),
	IS_READ_TYPE(80017, "订单是否已读状态不存在"),
	DELIVER_STATUS(80018,"发单状态不存在"),
	OPERATION_TYPE(80019,"订单日志状态不存在"),
	ORDER_STATUS_QUERY_TYPE(80020,"查询条件中的订单状态不存在"),
	ORDER_ISEXISTS(80021, "%S 订单已存在"),
	ORDER_NEED_GUIDE(80022,"必须预先指定导游才能添加订单奖金"),
	ORDER_SERVICE(80023,"订单必须在支付完成 或者 服务中才能添加订单奖金"),
	ORDER_DELIVER_STATUSEXP(81009, "发单状态异常"),
	DELIVER_STATUSEXP(81010, "发单状态异常"),
	DELIVER_TYPEEXP(81011, "发单类型异常"),
	DELIVER_DeliverDemandDealExp(81012, "导游发单类型异常"),
	DELIVER_ORDERSTATUS_FAILED(81013, "%s 发单订单主状态异常,只有在支付成功后才能进行此操作"),
	USER_FUND_ACCOUNT_NOT_FOUND(81014, "%s 用户资金账户不存在"),
	CANCEL_C_ORDER(81015, "您仅有权限取消自己下的订单，无权取消GDS的订单，如需取消请拨打客服电话。"),
	DELIVER_CONFICT(81016,"%S 发单冲突"),
	/** 82005: %S 参数错误 */
	ORDER_PARAM_FAILED(82005, "%S 参数错误"),
	ORDER_PAY_TIMEOUT(820013, "订单已经过期，请重新下单"),
	ORDER_AIRPORTPARAM_FAILED(82006, "机场不存在"),
	ORDER_SERVICETIMEPARAM_FAILED(82007, "服务时间不可用,只容许%S小时内下单"),
	ORDER_CACLE_FAILED(82008, "%S 不容许取消"),
	CONFIRM_FAILED(82009, "%S 不容许确认订单"),
	ORDER_PAY_FAILED(82010, "%S 不容许支付"),
	ORDER_AGREE_FAILED(82015, "%S 您已经表态，请进入列表刷新重试"),
	ORDER_CHANEL_NOEXIST(82011, "%S 不支持的渠道"),
	ORDER_CACLE_FAILED_BYOUTMON(82012, "支付金额为 %S 实际退款金额累计为 %S   "),
	ORDER_EDIT_FAILED(82019, "%S 不容许修改"),
	ORDER_GUID_ARRIVE(82020,"请确认已支付且已接单后再重试。"),
	ORDER_GUID_RECEIVECUST(82021,"请确认导游已到达后再继续。"),
	ORDER_GUID_STROCKEND(82022, "请确认已接到客人后再继续。"),
	ORDER_ASSIGN_FAILED(82023, "%S 订单派单失败  请联系客服"),
	ORDER_DISPUTE_FAILED(82024, "%S 订单必须支付完成才能冻结"),
	ORDER_ALREADY_DISPUTED(82024, "%S 订单已经处于冻结状态"),
	NO_GUIDE(82024, "%S 带有不存在"),
	ORDER_CITY_EXIST(82015,"%S 城市 不存在"),
	ORDER_COUNTRY_EXIST(82016,"%S 国家 不存在"),
	ORDER_Continent_EXIST(82017,"%S 州 不存在"),
	ORDER_CARCLASS_NOSUPPORT(82018,"%S 不支持的车座位"),
	ORDER_CARTYPE_NOSUPPORT(82019,"%S 不支持的车型"),
	PASS_CITY_NOT_FOUND(82020,"途径城市不存在"),
	URAGENT_EXIST(82021,"%S 急单类型不存在"),
	ORDER_HEAD_COUNT_ERR(82022, "提交失败，乘客人数超出限制！"),
	CANNOT_CANCEL_GDS_ORDER(82023, "此订单是通过代理商预订的，请联系您的代理商取消。"),
	
	/** 83001: %S 更新数据异常 */
	FAILED_FOR_UPDATE(83001,"%S 更新数据异常"),
	ORDER_MIS_CACLE_FAILED(83022,"%S 不容许mis退款,必须先冻结该订单"),
	ORDER_MIS_CACLE_NOGUIDE(83023,"%S 无导游 不容许退款给导游"),
	ORDER_MIS_TOSYS_ERROR(83024,"退还系统金额错误 金额应为      %S" ),
	ALREADY_HANDLED(83025,"您已表态过，不可重复表态" ),
	THIRD_ORDER_UPDATE_FAILED(83026, "三方订单状态更新失败"),
	
	/** 83002: %S 添加数据异常 */
	FAILED_FOR_INSERT(83002,"%S 添加数据异常"),
	/** 83003: 更新数据0行受影响，请确认传入的where条件对应的数据存在 */
	AFFECTED_ROWS_0(83003,"更新数据0行受影响，请确认传入的where条件对应的数据存在"),
	
	ORDER_DELIVER_INSERT_DB_FAILED(84001, "发单预处理插入db失败"),
	ORDER_DELIVER_UPDATE_DB_FAILED(84002, "%S 发单处理回写主订单失败"),
	ORDER_GUIDE_DELIVER_UPDATE_DB_FAILED(84003, "%S 发单GUIDE状态异常"),
	DELIVER_UPDATE_DB_FAILED(84004, "%S 发单处理回写预发单表失败"),
	TradeDeliverGuid_NOT_EXIST(84005, "%S 发单表不存在"),
	DELIVER_ACCEPTEXP(84006, "%S 该订单已接单"),
	PRE_DELIVER_FAILED(84007, "%S 导游预设失败"),
	DELIVER_GUIDE_ALIDYACCEPT(84008, "已被%S接单 导游预设失败 "),
	GUIDE_ISNOT_SING(84009,"您还未参加皇包车的司导培训分级，暂时无法接单"),
	GUIDE_LEVEL_NOT(84010,"您还未参加皇包车的司导培训分级，暂时无法接单"),
	UNTRAINED_CANNOT_OPERATION(82024, "您还未参加皇包车的司导培训等级，暂时无法接单"),
	GUIDE_FORBIT_ACC(84018,"您已经被禁止接单"),
	GUIDE_FORBIT_WITHDRAW(84019,"您已经被禁止提现"),
	PASS_CITY_LENGH_ERR(84011,"途径城市数据异常"),
	ORDER_CANNOT_BE_APPRAISED(84012, "订单当前状态不可评价"),
	
	TRADE_SOLR_QUERYEXP(85001,"SOLR 配置信息读取失败"),
	TRADE_SOLR_URGENT(85002,"SOLR 车型号出错"),
	
	/** 85003 : 增项费用计算错误 */
	ADDITIONAL_COST_OUT_OF_SIZE(85003, "增项费用计算错误"),
	
	/** 85004 : 当前订单不支持增项费用，仅日租、接机和精品线路支持该费用 */
	ADDITIONAL_COST_NOT_SUPPORT(85004, "当前订单不支持增项费用，仅日租、接机和精品线路支持该费用"),
	
	/** 85006 : 超时费的单价输入错误 */
	ADDITIONAL_COST_ERR_UNIT_OVER_TIME(85006, "超时费的单价输入错误"),
	
	/** 85007 : 超公里费的单价输入错误 */
	ADDITIONAL_COST_ERR_UNIT_OVER_DISTANCE(85007, "超公里费的单价输入错误"),
	
	/** 85008 : 超天费的单价输入错误 */
	ADDITIONAL_COST_ERR_UNIT_OVER_DAY(85008, "超天费的单价输入错误"),
	
	/** 85009 : 增项费用APP输入的金额与后台校验不一致，请确认输入正确 */
	ADDITIONAL_COST_CALCULATE_ERR(85009, "%S 增项费用APP输入的金额与后台校验不一致，请确认输入正确 "),
	
	/** 85010 : 增项费用当天小计输入错误 */
	ADDITIONAL_COST_APPLYFEE_ERR(85010, "增项费用当天小计输入错误"),

	TRADE_PRICE_ERROR(86001, "订单报价错误"),
	TRADE_PRICE_EMPTY(86002, "没有查询到报价"),
	
	TRADE_THIRD_REQUEST_FAIL(87001, "渠道请求错误"), 
	TRADE_THIRD_QUEUE_FAIL(87002, "渠道队列写入错误"),
	TRADE_ADDORDER_FAILED(87003, "订单添加失败"),
	TRADE_ORDER_IS_CANCEL(87004, "订单已经取消"),
	TRADE_ORDER_SIGN_ERROR(87005, "第三方订单加密错误"),
	THIRD_ORDER_ERROR(87006, "第三方订单状态错误"),
	
	GUIDE_AGENCY_TYPE_NOT_FOUND(88001,"导游地接社类型不存在"),
	GUIDE_DELIVER_STATUS_NOT_FOUND(88002,"导游发单状态不存在"),
	ORDER_DELIVER_STATUS_NOT_FOUND(88003,"订单发单状态不存在"),
	ORDER_SERIAL_FLAG_NOT_FOUND(88004,"订单串单标记不存在"),
	ORDER_SERIAL_SECONDE_ERROR(88005,"订单%S已经被串单了，不允许被订单%S二次串单"),
	ORDER_SERIAL_FLAG_ERROR(88006,"订单 %S 串单标记失败"),
	
	RESTFULL_ERROR_UCENTER(89001, "用户中心服务错误"),
	RESTFULL_ERROR_FLIGHT(89002, "航班服务错误"),
	RESTFULL_ERROR_GUIDE(89003, "导游服务错误"),
	RESTFULL_ERROR_PRICE(89004, "查价服务错误"),
	CTRIP_CALLBACK_FAILED(89005, "回调携程API失败"),
	QUNAR_CALLBACK_FAILED(89006, "回调去哪儿API失败"),
	QUA_CALLBACK_FAILED(89006, "回调去啊API失败"),
	GENERATE_SIGN_ERR(89007, "生产签名错误"),
	CHANNEL_FUND_ACCOUNT_NOT_SUPPORT(89008, "渠道的资金账户不存在"),
	
	PUSH_EXP(88005,"%S push消息错误"),
	;
	private int code;
	private String message;

	private TradeReturnCodeEnum(int code, String message){
		this.code = code;
		this.message = message;
	};

	@Override
	public int getCode() {
		return this.code;
	}
	
	@Override
	public String getMessage() {
		return this.message;
	}

}
