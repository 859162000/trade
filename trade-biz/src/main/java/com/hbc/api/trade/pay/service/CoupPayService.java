/**
 * @Author lukangle
 * @2015年11月24日@下午3:30:38
 */
package com.hbc.api.trade.pay.service;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.hbc.api.fund.account.enums.BizType;
import com.hbc.api.fund.account.service.FundAccountService;
import com.hbc.api.trade.bdata.common.IDGenerotor;
import com.hbc.api.trade.bdata.common.util.DoubleUtil;
import com.hbc.api.trade.order.enums.bean.OperationType;
import com.hbc.api.trade.order.enums.order.OrderLogType;
import com.hbc.api.trade.order.enums.order.OrderStatus;
import com.hbc.api.trade.order.enums.order.OrderType;
import com.hbc.api.trade.order.mapping.gen.bean.OrderBean;
import com.hbc.api.trade.order.mapping.genx.xbean.OrderLogParamBean;
import com.hbc.api.trade.order.service.OrderLogService;
import com.hbc.api.trade.order.service.OrderService;
import com.hbc.api.trade.pay.enums.GetWayEnum;
import com.hbc.api.trade.pay.enums.PayStatus;
import com.hbc.api.trade.pay.exception.PayException;
import com.hbc.api.trade.pay.exception.PayReturnCodeEnum;
import com.hbc.api.trade.pay.mapping.gen.TradePaymentMapper;
import com.hbc.api.trade.pay.mapping.gen.bean.TradePayment;
import com.hbc.api.trade.settle.enums.AccountEnums;
import com.hbc.api.trade.settle.enums.FundBizEnumConvter;
import com.hbc.api.trade.third.CouponServiceParty;
import com.hbc.api.trade.third.vo.CoupParm;

@Component
public class CoupPayService {
	@Autowired
	CouponServiceParty couponServiceParty;
	@Autowired
	OrderService orderService;
	@Autowired
	OrderLogService orderLogService;
	@Autowired
	PaymentService paymentService;
	@Autowired
	TradePaymentMapper tradePaymentMapper;
	@Autowired private FundAccountService 	fundAccountService;
	@Transactional
	public void payByOnlyCoup(OrderBean orderBean,CoupParm coupParm){
		couponServiceParty.lockCoup(orderBean.getOrderNo(), coupParm.getCoupId());
		couponServiceParty.useCoup(coupParm.getCoupId(),orderBean.getOrderNo());
		orderService.updateOrderStatus(orderBean.getOrderNo(), OrderStatus.INITSTATE, OrderStatus.PAYSUCCESS);
		
		OrderType orderType = OrderType.getType(orderBean.getOrderType());
		BizType bizType =	FundBizEnumConvter.getFundBizType(orderType);
		
		TradePayment tradePayment = new TradePayment();
		Date curtime = new Date(System.currentTimeMillis());
		tradePayment.setCreateTime(curtime);
		tradePayment.setOrderNo(orderBean.getOrderNo());
		tradePayment.setOrderPrice(orderBean.getPriceChannel());
		tradePayment.setPayActual(0.00d);
		tradePayment.setPayFee(0.00d);
		tradePayment.setPayNo(IDGenerotor.generatePayNo());
		tradePayment.setPaySubject("HBC券支付");
		tradePayment.setPayShould(orderBean.getPriceChannel());
		tradePayment.setPayGetway(GetWayEnum.COUP.value);
		tradePayment.setPayGatewayName(GetWayEnum.COUP.name);
		tradePayment.setUserAccountNo(orderBean.getUserAccount());
		tradePayment.setCoupId(coupParm.getCoupId());
		tradePayment.setCoupPay(DoubleUtil.subtractionDouble(orderBean.getPriceChannel(), coupParm.getcActualPrice()));
		tradePayment.setCouponInfo(coupParm.getCoupInfo());
		tradePayment.setPayStatus(PayStatus.SUCCESS.value);
		if(tradePaymentMapper.insert(tradePayment) == 0){
			throw new PayException(PayReturnCodeEnum.PAY_INSERT_FAILED,orderBean.getOrderNo());
		}
		
		orderService.updateCoupInfos(orderBean, coupParm.getCoupId(), tradePayment);
		
		fundAccountService.pay(AccountEnums.HBC_COUP.value, tradePayment.getCoupPay(), bizType, orderBean.getOrderNo());
		fundAccountService.pay(AccountEnums.HBC_Guarantee.value, orderBean.getPriceChannel(), bizType, orderBean.getOrderNo());
		
		OrderLogParamBean orderLogParamBean = new OrderLogParamBean();
		orderLogParamBean.setContent(OrderLogType.PAYMENT_RESULT_CONTENT(orderBean.getAgentName(), orderBean.getUserName(), coupParm.getCoupId(), JSON.toJSONString(coupParm)));
		orderLogParamBean.setLogType(OrderLogType.PAYMENT_RESULT.type);
		orderLogParamBean.setOpType(OperationType.CUSTOMER.value);
		orderLogParamBean.setOpUserId(orderBean.getUserId());
		orderLogParamBean.setOpUserName(orderBean.getUserName());
		orderLogParamBean.setOrderNo(orderBean.getOrderNo());
		orderLogService.insertOrderLog(orderLogParamBean );
	}
}
