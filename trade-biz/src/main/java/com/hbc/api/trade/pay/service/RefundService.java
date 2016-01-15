/**
 * @Author lukangle
 * @2015骞�11鏈�11鏃涓嬪崍3:45:25
 */
package com.hbc.api.trade.pay.service;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.hbc.api.fund.account.enums.BizType;
import com.hbc.api.fund.account.service.FundAccountService;
import com.hbc.api.fund.account.service.FundFrozenAccountService;
import com.hbc.api.trade.TradeFinalStr;
import com.hbc.api.trade.bdata.common.util.DoubleUtil;
import com.hbc.api.trade.bdata.common.util.TimeConverter;
import com.hbc.api.trade.order.enums.TradeReturnCodeEnum;
import com.hbc.api.trade.order.enums.bean.OperationType;
import com.hbc.api.trade.order.enums.order.OrderLogType;
import com.hbc.api.trade.order.enums.order.OrderStatus;
import com.hbc.api.trade.order.enums.order.OrderType;
import com.hbc.api.trade.order.exception.TradeException;
import com.hbc.api.trade.order.mapping.gen.bean.OrderBean;
import com.hbc.api.trade.order.mapping.genx.xbean.OrderLogParamBean;
import com.hbc.api.trade.order.service.COptOrderService;
import com.hbc.api.trade.order.service.OrderLogService;
import com.hbc.api.trade.order.service.OrderQueryService;
import com.hbc.api.trade.order.service.OrderService;
import com.hbc.api.trade.order.service.OrderTrackService;
import com.hbc.api.trade.pay.enums.GetWayEnum;
import com.hbc.api.trade.pay.enums.PayStatus;
import com.hbc.api.trade.pay.getway.alibaba.AliRefundPayservice;
import com.hbc.api.trade.pay.mapping.gen.TradeRefundMapper;
import com.hbc.api.trade.pay.mapping.gen.bean.TradePayment;
import com.hbc.api.trade.pay.mapping.gen.bean.TradeRefund;
import com.hbc.api.trade.pay.mapping.gen.bean.TradeRefundConf;
import com.hbc.api.trade.pay.mapping.gen.bean.TradeRefundExample;
import com.hbc.api.trade.pay.param.MISCancelOrderParam;
import com.hbc.api.trade.pay.service.refund.InnerRefundService;
import com.hbc.api.trade.settle.enums.AccountEnums;
import com.hbc.api.trade.settle.enums.FundBizEnumConvter;
import com.hbc.api.trade.third.CouponServiceParty;
import com.hbc.api.trade.third.TestPayVal;
import com.hbc.api.trade.third.push.CPushService;
import com.hbc.api.trade.third.push.GPushService;
import com.hbc.api.trade.third.sms.SMSService;

@Component
public class RefundService {
	private Logger log = LoggerFactory.getLogger(RefundService.class);
	@Autowired
	private COptOrderService cOrderService;
	@Autowired
	AliRefundPayservice aliRefundPayservice;
	@Autowired
	private PaymentService paymentService;
	@Autowired
	RefundConfService refundConfService;
	@Autowired
	InnerRefundService innerRefundService;
	@Autowired
	TradeRefundMapper tradeRefundMapper;
	@Autowired
	OrderQueryService orderQueryService;
	@Autowired
	OrderService orderService;
	@Autowired
	CouponServiceParty couponServiceParty;
	@Autowired
	FundAccountService fundAccountService;
	@Autowired
	FundFrozenAccountService fundFrozenAccountService;
	@Autowired
	CPushService cpushService;
	@Autowired
	SMSService smsservice;
	@Autowired
	GPushService gpushService;

	/**
	 *C端 自动退款
	 * 
	 * @param orderBean
	 */
	@Transactional
	public void refundOrder(String orderNo, String reason) {
		OrderBean orderBean = orderQueryService.getOrderByNo(orderNo);
		OrderStatus ostatus = OrderStatus.getStatus(orderBean.getOrderStatus());

		if (ostatus.equals(OrderStatus.INITSTATE)) {
			cOrderService.cancelOrderWithoutMoney(orderBean.getOrderNo(), orderBean.getUserId(), orderBean.getUserName());
		} else if (ostatus.equals(OrderStatus.PAYSUCCESS) || OrderStatus.GUIDE_ARRIVED.equals(ostatus) || OrderStatus.PICK_CUSTOMER.equals(ostatus) || OrderStatus.STROKE_END.equals(ostatus)) {
			TradePayment tradePayment = paymentService.queryTradePaymentByOrderNo(orderNo, PayStatus.SUCCESS);

			GetWayEnum getWayEnum = GetWayEnum.getType(tradePayment.getPayGetway());
			List<TradeRefundConf> refundConfs = refundConfService.getRefunConfs();
			Double ruserPrice = refundConfService.getRefundUserPrice(orderBean, tradePayment, refundConfs);
			Double rguidePrice = refundConfService.getRefundGuidePrice(orderBean, refundConfs);

			Double toProfit = DoubleUtil.subtractionDouble(orderBean.getPriceChannel(), DoubleUtil.addDouble(ruserPrice, rguidePrice));
			Double butie = DoubleUtil.subtractionDouble(DoubleUtil.addDouble(ruserPrice, rguidePrice), orderBean.getPriceChannel());

			double toSystem = 0.0d;
			if (toProfit > 0) {
				toSystem = toProfit;
			} else if (butie > 0) {
				toSystem = butie;
			}
			// 担保账户减钱
			if (toProfit > 0) {
				innerRefundService.refundToHBCAccount(AccountEnums.HBC_Profit, orderBean, toProfit);
			} else if (butie > 0) {
				innerRefundService.refundToHBCAccount(AccountEnums.HBC_BUTIE, orderBean, butie);
			}

			if (TradeFinalStr.defaultGuideId.equals(orderBean.getGuideId())) {
				//无导游情况 全额退款给用户
				ruserPrice = tradePayment.getPayShould();
				rguidePrice = 0.0d;
			}

			isCanRefund(tradePayment, orderNo, ruserPrice);
			OrderType orderType = OrderType.getType(orderBean.getOrderType());
			BizType bizType = FundBizEnumConvter.getRefundBizType(orderType);

			fundAccountService.recharge(AccountEnums.HBC_Guarantee.value, orderBean.getPriceChannel(), bizType, orderBean.getOrderNo());

			//  退款给导游
			if (orderBean.getGuideId() != null && !orderBean.getGuideId().equals(TradeFinalStr.defaultGuideId)) {
				innerRefundService.refundToGuideAccount(orderBean, rguidePrice);
			}

			if (GetWayEnum.ALI.equals(getWayEnum)) {
				if (ostatus.equals(OrderStatus.PAYSUCCESS) || OrderStatus.GUIDE_ARRIVED.equals(ostatus) || OrderStatus.PICK_CUSTOMER.equals(ostatus) || OrderStatus.STROKE_END.equals(ostatus)) {
					refundAliToUser(ruserPrice, toSystem, rguidePrice, tradePayment, orderBean, reason, bizType);
					orderService.updateOrderStatus(orderBean.getOrderNo(), ostatus, OrderStatus.CANCELING);
				} else {
					throw new TradeException(TradeReturnCodeEnum.ORDER_CACLE_FAILED, orderBean.getOrderNo() + " " + ostatus.name);
				}
			} else if (GetWayEnum.INNER.equals(getWayEnum)) {
				// 内部账户支付 退款
				innerRefundService.refundToUserAccount(orderBean, tradePayment, bizType, ruserPrice, reason);

				if (ostatus.equals(OrderStatus.PAYSUCCESS)) {
					orderService.updateOrderStatus(orderBean.getOrderNo(), ostatus, OrderStatus.CANCEL_CLOSE);
				} else if (OrderStatus.GUIDE_ARRIVED.equals(ostatus) || OrderStatus.PICK_CUSTOMER.equals(ostatus) || OrderStatus.STROKE_END.equals(ostatus)) {
					orderService.updateOrderStatus(orderBean.getOrderNo(), ostatus, OrderStatus.CANCLE_CLOSE_PAY_SERVICE);
				} else {
					throw new TradeException(TradeReturnCodeEnum.ORDER_CACLE_FAILED, orderBean.getOrderNo() + " " + ostatus.name);
				}
			} else if (GetWayEnum.COUP.equals(getWayEnum)) {
				double couppay = tradePayment.getCoupPay();
				if (ruserPrice >= couppay) {
					couponServiceParty.refundCoup(tradePayment.getCoupId(), orderBean, tradePayment);
				}
				if (ostatus.equals(OrderStatus.PAYSUCCESS)) {
					orderService.updateOrderStatus(orderBean.getOrderNo(), ostatus, OrderStatus.CANCEL_CLOSE);
				} else if (OrderStatus.GUIDE_ARRIVED.equals(ostatus) || OrderStatus.PICK_CUSTOMER.equals(ostatus) || OrderStatus.STROKE_END.equals(ostatus)) {
					orderService.updateOrderStatus(orderBean.getOrderNo(), ostatus, OrderStatus.CANCLE_CLOSE_PAY_SERVICE);
				} else {
					throw new TradeException(TradeReturnCodeEnum.ORDER_CACLE_FAILED, orderBean.getOrderNo() + " " + ostatus.name);
				}
			} else {
				throw new TradeException(TradeReturnCodeEnum.ORDER_CACLE_FAILED, orderBean.getOrderNo() + " " + ostatus.name);
			}
			orderService.updateOrderCancleTime(orderBean.getOrderNo());

			cpushService.canclePushOrder(orderBean);
			gpushService.cancleOrderPush(orderBean);
			smsservice.cancleSMSConfirm(orderBean);
			orderService.resetSerOrder(orderBean);
			insertCacleLog(orderBean,null,null,ruserPrice,toProfit,rguidePrice);
		} else {
			throw new TradeException(TradeReturnCodeEnum.ORDER_CACLE_FAILED, orderBean.getOrderNo() + " " + ostatus.name);
		}
	}

	private void insertCacleLog(OrderBean orderBean,String optId,String optName,Double toUser,Double toSys,Double toGuide){
		OrderLogParamBean orderLogParamBean = new OrderLogParamBean();
		orderLogParamBean.setContent(OrderLogType.CANCEL_CONTENT(orderBean.getUserName(), TimeConverter.formatDate(new Date()))+
				" 退款给用户"+orderBean.getUserName()+"【"+toUser+"】退款给导游"+orderBean.getGuideName()+"【"+toGuide+"】退款给系统【"+toSys+"】");
		orderLogParamBean.setLogType(OrderLogType.CANCEL.type);
		orderLogParamBean.setOpType(OperationType.CUSTOMER.value);
		orderLogParamBean.setOpUserId(optId==null?orderBean.getUserId():optId);
		orderLogParamBean.setOpUserName(optName==null?orderBean.getUserName():optName);
		orderLogParamBean.setOrderNo(orderBean.getOrderNo());
		orderLogService.insertOrderLog(orderLogParamBean);
		orderTrackService.cancelled(orderBean.getOrderNo());
	}
	@Autowired
	protected OrderLogService orderLogService;
	@Autowired
	private OrderTrackService orderTrackService;
	@Autowired
	TestPayVal testPayVal;

	private void refundAliToUser(Double ruserPrice, Double toSystem, Double toGuide, TradePayment tradePayment, OrderBean orderBean, String reason, BizType bizType) {
		double couppay = 0;

		if (tradePayment.getCoupId() == null) {
			if (testPayVal.isTestByBeijing(orderBean) && ruserPrice > 0) {
				log.info(orderBean.getOrderNo() + " 测试订单  退款价格强制修改成 0.01");
				ruserPrice = 0.01d;
			}
			aliRefundPayservice.refundOrder(orderBean, ruserPrice, toSystem, toGuide, reason, tradePayment);
			// 内部账户先加钱 通知成功时在减钱
			fundFrozenAccountService.payFrozen(orderBean.getUserAccount(), ruserPrice, bizType, orderBean.getOrderNo());
		} else {
			couppay = tradePayment.getCoupPay();
			if (ruserPrice >= couppay) {
				// 当退款金额大于 优惠券支付时 优先退款优惠券
				couponServiceParty.refundCoup(tradePayment.getCoupId(), orderBean, tradePayment);

				Double toali = DoubleUtil.subtractionDouble(ruserPrice, tradePayment.getCoupPay());
				if (toali > tradePayment.getPayActual()) {
					throw new TradeException(TradeReturnCodeEnum.ORDER_CACLE_FAILED, orderBean.getOrderNo() + " " + toali + " " + tradePayment.getPayActual());
				}
				if (testPayVal.isTestByBeijing(orderBean) && ruserPrice > 0) {
					log.info(orderBean.getOrderNo() + " 娴嬭瘯璁㈠崟  閫�娆句环鏍煎己鍒朵慨鏀规垚 0.01");
					toali = 0.01d;
				}
				aliRefundPayservice.refundOrder(orderBean, toali, toSystem, toGuide, reason, tradePayment);
				// 内部账户先加钱 通知成功时在减钱
				fundFrozenAccountService.payFrozen(orderBean.getUserAccount(), toali, bizType, orderBean.getOrderNo());
			} else if (ruserPrice <= tradePayment.getPayActual()) {
				aliRefundPayservice.refundOrder(orderBean, ruserPrice, toSystem, toGuide, reason, tradePayment);
				// 内部账户先加钱 通知成功时在减钱
				fundFrozenAccountService.payFrozen(orderBean.getUserAccount(), ruserPrice, bizType, orderBean.getOrderNo());
			} else {
				// 给用户退款金额 小于券金额 并且大于 用户实际金额 则不退款 直接将该部分金额 给 系统收益
				fundAccountService.pay(AccountEnums.HBC_Profit.value, ruserPrice, BizType.REFUND_TOUSER_LESSTHANCOUP, orderBean.getOrderNo());
			}
		}

	}

	/**
	 * 查询可退款金额
	 * 
	 * @param orderNo
	 * @return
	 */
	public double getRefundAmount(String orderNo) {
		OrderBean orderBean = orderQueryService.getOrderByNo(orderNo);
		OrderStatus ostatus = OrderStatus.getStatus(orderBean.getOrderStatus());

		TradePayment tradePayment = paymentService.queryTradePaymentByOrderNoNoexp(orderNo, PayStatus.SUCCESS);

		if (ostatus.equals(OrderStatus.INITSTATE) || tradePayment == null) {
			return 0.0d;
		} else {
			List<TradeRefundConf> refundConfs = refundConfService.getRefunConfs();
			double ruserPrice = refundConfService.getRefundUserAblePrice(orderBean, tradePayment, refundConfs);
			return ruserPrice;
		}
	}

	/**
	 * 查询退款时被扣金额，例如实付100，可退款金额80，那么已服务被扣费用为20
	 * 
	 * @param orderNo
	 * @return
	 */
	public double getCancelFee(String orderNo) {
		OrderBean orderBean = orderQueryService.getOrderByNo(orderNo);
		OrderStatus ostatus = OrderStatus.getStatus(orderBean.getOrderStatus());

		TradePayment tradePayment = paymentService.queryTradePaymentByOrderNoNoexp(orderNo, PayStatus.SUCCESS);

		if (ostatus.equals(OrderStatus.INITSTATE) || tradePayment == null) {
			return 0.0d;
		} else {
			List<TradeRefundConf> refundConfs = refundConfService.getRefunConfs();
			double ruserPrice = refundConfService.getRefundUserAblePrice(orderBean, tradePayment, refundConfs);
			//在有优惠券的情况下
			
			return DoubleUtil.subtractionDouble(tradePayment.getPayActual(), ruserPrice);
		}
	}

	public void isCanRefund(TradePayment tradePayment, String orderNo, double refundMoney) {
		List<TradeRefund> refunds = getAllRefunds(tradePayment.getPayNo(), orderNo);
		double payActual = tradePayment.getPayShould();
		double refundedAmout = refundMoney;
		for (TradeRefund tradeRefund : refunds) {
			refundedAmout = refundedAmout + tradeRefund.getRefundMoney();
		}
		if (refundedAmout > payActual) {
			throw new TradeException(TradeReturnCodeEnum.ORDER_CACLE_FAILED_BYOUTMON, payActual, refundedAmout);
		}
	}

	public List<TradeRefund> getAllRefunds(String payNo, String orderNo) {
		TradeRefundExample tradeRefundExample = new TradeRefundExample();
		TradeRefundExample.Criteria criteria = tradeRefundExample.createCriteria();
		criteria.andPayNoEqualTo(payNo);
		criteria.andOrderNoEqualTo(orderNo);
		return tradeRefundMapper.selectByExample(tradeRefundExample);
	}

	public List<TradeRefund> getAllRefunds(String orderNo) {
		TradeRefundExample tradeRefundExample = new TradeRefundExample();
		tradeRefundExample.createCriteria().andOrderNoEqualTo(orderNo);
		return tradeRefundMapper.selectByExample(tradeRefundExample);
	}

	/**
	 * MIS 退款 涉及 三方分钱
	 * 
	 * @param param
	 */
	@Transactional
	public void misRefundProccess(MISCancelOrderParam param) {
		String orderNo = param.getOrderNo();
		OrderBean orderBean = orderQueryService.getOrderByNo(orderNo);
		OrderStatus ostatus = OrderStatus.getStatus(orderBean.getOrderStatus());
		OrderType orderType = OrderType.getType(orderBean.getOrderType());
		BizType bizType = FundBizEnumConvter.getRefundBizType(orderType);

		double toUser = param.getBackToCustomer();
		double toGuide = param.getGuideIncome();
		double toSystem = param.getSystemPrice();

		if (toGuide > orderBean.getPriceGuide()) {
			throw new TradeException(TradeReturnCodeEnum.ORDER_CACLE_FAILED, "导游退款金额大于支付金额");
		}

		if (toUser > orderBean.getPriceChannel()) {
			throw new TradeException(TradeReturnCodeEnum.ORDER_CACLE_FAILED, "客户退款金额大于支付金额");
		}

		if (OrderStatus.INITSTATE.equals(ostatus)) {
			cOrderService.cancelOrderWithoutMoney(orderBean.getOrderNo(), orderBean.getUserId(), orderBean.getUserName());
		} else if (OrderStatus.DISPUTING.equals(ostatus)) {

			if (toUser > 0) {
				TradePayment tradePayment = paymentService.queryTradePaymentByOrderNo(orderNo, PayStatus.SUCCESS);
				GetWayEnum getWayEnum = GetWayEnum.getType(tradePayment.getPayGetway());

				isCanRefund(tradePayment, orderNo, toUser);
				if (GetWayEnum.ALI.equals(getWayEnum)) {
					refundAliToUser(toUser, toSystem, toGuide, tradePayment, orderBean, param.getContent(), bizType);
				} else if (GetWayEnum.INNER.equals(getWayEnum)) {
					// 内部账户支付 退款
					innerRefundService.refundToUserAccount(orderBean, tradePayment, bizType, toUser, param.getContent());
				} else {
					throw new TradeException(TradeReturnCodeEnum.ORDER_CACLE_FAILED, orderBean.getOrderNo() + " " + ostatus.name);
				}
			}

			if (toGuide > 0 && TradeFinalStr.defaultGuideId.equals(orderBean.getGuideId())) {
				// 无导游情况 全额退款给用户
				throw new TradeException(TradeReturnCodeEnum.ORDER_MIS_CACLE_NOGUIDE, orderNo);
			} else if (toGuide > 0 && !TradeFinalStr.defaultGuideId.equals(orderBean.getGuideId())) {
				innerRefundService.refundToGuideAccount(orderBean, toGuide);
			}

			double toProfit = DoubleUtil.subtractionDouble(orderBean.getPriceChannel(), DoubleUtil.addDouble(toUser, toGuide));
			double butie = DoubleUtil.subtractionDouble(DoubleUtil.addDouble(toUser, toGuide), orderBean.getPriceChannel());

			double toS = 0.0d;
			if (toProfit > 0) {
				toS = toProfit;
			} else if (butie > 0) {
				toS = butie;
			}

			toSystem = toS;
			// 担保账户减钱
			if (toProfit > 0) {
				innerRefundService.refundToHBCAccount(AccountEnums.HBC_Profit, orderBean, toProfit);
			} else if (butie > 0) {
				innerRefundService.refundToHBCAccount(AccountEnums.HBC_BUTIE, orderBean, butie);
			}

			if (TradeFinalStr.defaultGuideId.equals(orderBean.getGuideId())) {
				orderService.updateOrderStatus(orderBean.getOrderNo(), ostatus, OrderStatus.CANCEL_NOSERVICE);
			} else {
				orderService.updateOrderStatus(orderBean.getOrderNo(), ostatus, OrderStatus.CANCEL_SERVICEED);
			}

		} else {
			throw new TradeException(TradeReturnCodeEnum.ORDER_MIS_CACLE_FAILED, ostatus.name);
		}
		orderService.updateOrderCancleTime(orderBean.getOrderNo());
		cpushService.canclePushOrder(orderBean);
		smsservice.cancleSMSConfirm(orderBean);
		orderService.resetSerOrder(orderBean);
		insertCacleLog(orderBean,param.getOpUserId(),param.getOpUserName(),toUser,toSystem,toGuide);
		log.info("订单 [" + orderNo + "] 成功退款给 用户 " + orderBean.getUserAccount() + "[ " + toUser + " ]  退款给导游 " + orderBean.getGuideAccountNo() + " [" + toGuide + "] 退还给 系统 [" + toSystem + "]");

	}
}
