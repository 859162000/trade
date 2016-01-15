package com.hbc.data.trade.transfer.enums.hfinal;

import com.hbc.api.trade.order.enums.order.OrderStatus;
import com.hbc.data.trade.transfer.exceptions.TradeTransferException;
import com.hbc.data.trade.transfer.exceptions.TradeTransferReturnCodeEnum;
import com.hbc.data.trade.transfer.mapping.hbcfinal.gen.bean.FinalOrderBean;
import com.hbc.data.trade.transfer.mapping.hbcfinal.gen.bean.FinalOrderPay;

public enum FOrderStatus {
	/**
	 * 订单状态。0-初始（默认）；1-已支付；2-已确定导游（已有导游接单）；3-导游已到达位置；4-导游已接到客人（开始出发）；5-已完成服务；-1-
	 * 用户取消订单
	 * ；-2-导游取消订单（客服取消）；-3-系统取消（60分钟未支付）；-4-删除订单；11-价格有异动；12-取消重发；99-无导游（不能支付
	 * ，可能是没有相关城市或没有导游);98-已分发97-未分发；100-订单已完成（已结算）
	 */

	/** 0-初始（默认） */
	INITSTATE(0, "初始（默认）"),
	/** 1-已支付 */
	PAYSUCCESS(1, "已支付"),
	/** 2-已确定导游（已有导游接单） */
	GUIDE_QUEDING(2, "已确定导游（已有导游接单）"),
	/** 3-导游已到达位置 */
	GUIDE_ARRIVED(3, "3-导游已到达位置"),

	/** 4-导游已接到客人（开始出发） */
	PICK_CUSTOMER(4, "4-导游已接到客人（开始出发）"),

	/** 5-已完成服务 */
	STROKE_END(5, "5-已完成服务"),

	/** 用户取消订单 */
	CANCEL_BYUSER(-1, " 用户取消订单"),

	/** 系统取消（60分钟未支付） */
	CANCEL_BYGUIDE(-3, "系统取消（60分钟未支付）"),

	/** -4-删除订单 */
	CANCEL_BYSYSTEM(-4, "删除订单"),
	/** 11-价格有异动 **/
	PRICE_ADDTIONAL(11, "价格有异动"),
	/** 12: 取消重发 */
	CANCALE_DELIVER(12, "取消重发"),
	/** 无导游（不能支付 可能是没有相关城市或没有导游) */
	NO_GUIDE(99, "无导游（不能支付 可能是没有相关城市或没有导游)"),
	/** -已分发 */
	YIFENFA(98, "-已分发"),
	/** -未分发 */
	WEIFENFA(97, "-未分发"),
	/** 订单已完成（已结算） */
	SUCCESS(100, "订单已完成（已结算）"), ;

	public Integer value;
	public String name;

	FOrderStatus(int value, String name) {
		this.value = value;
		this.name = name;
	}

	public static FOrderStatus getStatus(Integer value) {
		FOrderStatus[] alarmTypes = FOrderStatus.values();
		for (FOrderStatus alarmType : alarmTypes) {
			if (alarmType.value.equals(value)) {
				return alarmType;
			}
		}
		return null;
	}

	public static OrderStatus getOrderStatus(FinalOrderBean finalOrderBean,FinalOrderPay finalOrderPay) {
		FOrderStatus forderStatus = FOrderStatus.getStatus(finalOrderBean.getStatus());
		FinalOrderPayStatus finalOrderPayStatus = FinalOrderPayStatus.getStatus(finalOrderBean.getPaystatus());
		switch (forderStatus) {
		case INITSTATE:
			return OrderStatus.INITSTATE;
		case PAYSUCCESS:
			return OrderStatus.PAYSUCCESS;
		case GUIDE_QUEDING:
			return OrderStatus.PAYSUCCESS;
		case GUIDE_ARRIVED:
			return OrderStatus.GUIDE_ARRIVED;
		case PICK_CUSTOMER:
			return OrderStatus.PICK_CUSTOMER;
		case STROKE_END:
			return OrderStatus.STROKE_END;
		case CANCEL_BYUSER:
			if(finalOrderPay==null || FinalOrderPayStatus.INITSTATE.equals(finalOrderPayStatus)){
				return OrderStatus.CANCLE_CLOSE_NOPAY;
			}else{
				return OrderStatus.CANCEL_CLOSE;
			}
		case CANCEL_BYGUIDE:
			if(finalOrderPay==null || FinalOrderPayStatus.INITSTATE.equals(finalOrderPayStatus)){
				return OrderStatus.CANCLE_CLOSE_NOPAY;
			}else{
				return OrderStatus.CANCEL_CLOSE;
			}
		case CANCEL_BYSYSTEM:
			if(finalOrderPay==null || FinalOrderPayStatus.INITSTATE.equals(finalOrderPayStatus)){
				return OrderStatus.CANCLE_CLOSE_NOPAY;
			}else{
				return OrderStatus.CANCEL_SERVICEED;
			}
		case PRICE_ADDTIONAL:
			return OrderStatus.CONFIRMED_COST;
		case CANCALE_DELIVER:
			return OrderStatus.PAYSUCCESS;
		case NO_GUIDE:
			return OrderStatus.PAYSUCCESS;
		case YIFENFA:
			return OrderStatus.PAYSUCCESS;
		case WEIFENFA:
			return OrderStatus.PAYSUCCESS;
		case SUCCESS:
			return OrderStatus.SETTLEMENT;
		default:
				throw new TradeTransferException(TradeTransferReturnCodeEnum.ORDER_ID_NOT_FOUND,forderStatus.name+" 无法转换 ");
		}
	}

}
