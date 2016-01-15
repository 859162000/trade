/**
 * @Author lukangle
 * @2015年11月19日@下午4:17:48
 */
package com.hbc.api.trade.pay.service;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.hbc.api.fund.account.enums.BizType;
import com.hbc.api.fund.account.service.FundAccountService;
import com.hbc.api.trade.bdata.common.util.TimeConverter;
import com.hbc.api.trade.order.enums.bean.OperationType;
import com.hbc.api.trade.order.enums.order.OrderLogType;
import com.hbc.api.trade.order.enums.order.OrderStatus;
import com.hbc.api.trade.order.enums.order.OrderType;
import com.hbc.api.trade.order.mapping.gen.bean.OrderBean;
import com.hbc.api.trade.order.mapping.genx.xbean.OrderLogParamBean;
import com.hbc.api.trade.order.service.OrderLogService;
import com.hbc.api.trade.order.service.OrderQueryService;
import com.hbc.api.trade.order.service.OrderService;
import com.hbc.api.trade.pay.enums.GetWayEnum;
import com.hbc.api.trade.pay.enums.PayStatus;
import com.hbc.api.trade.pay.exception.PayException;
import com.hbc.api.trade.pay.exception.PayReturnCodeEnum;
import com.hbc.api.trade.pay.param.InnerPayparm;
import com.hbc.api.trade.settle.enums.AccountEnums;
import com.hbc.api.trade.settle.enums.FundBizEnumConvter;
import com.hbc.api.trade.third.sms.SMSService;

@Component
public class InnerPaymentService {
	private static final Logger log = LoggerFactory.getLogger(InnerPaymentService.class);
	@Autowired
	FundAccountService fundAccountService;
	@Autowired
	OrderQueryService orderQueryService;
	@Autowired
	PaymentService paymentService;
	@Autowired
	OrderService orderService;
	@Autowired
	PayTimeService payTimeService;
	@Autowired
	SMSService smsService;
	@Autowired
	OrderLogService orderLogService;
	@Transactional
	public void payByInnerAccount(InnerPayparm innerPayparm){
		String orderNo = innerPayparm.getOrderNo();
		OrderBean orderBean = orderQueryService.getOrderByNo(orderNo);
		payTimeService.payTimeValide(orderBean);
		OrderStatus orderStatus = OrderStatus.getStatus(orderBean.getOrderStatus());
		if(!OrderStatus.INITSTATE.equals(orderStatus)){
			throw new PayException(PayReturnCodeEnum.PAYSUCCESS_NOPAY_CHANGED,orderNo);
		}
		Double priceChannel = orderBean.getPriceChannel();
		if(priceChannel.equals(innerPayparm.getPriceChannel())){
			paymentService.addTradePayment(orderBean, GetWayEnum.INNER, priceChannel, "", innerPayparm.getAccountNo(),PayStatus.SUCCESS);
			OrderType orderType = OrderType.getType(orderBean.getOrderType());
			BizType bizType =	FundBizEnumConvter.getFundBizType(orderType);
			fundAccountService.recharge(innerPayparm.getAccountNo(), priceChannel,bizType,orderBean.getOrderNo());
			//担保账户加钱
			fundAccountService.pay(AccountEnums.HBC_Guarantee.value, priceChannel, bizType, orderNo);
			
			orderService.updateOrderStatus(orderNo, OrderStatus.INITSTATE,OrderStatus.PAYSUCCESS);
			
			OrderLogParamBean orderLogParamBean = new OrderLogParamBean();
			orderLogParamBean.setContent(OrderLogType.PAY_CONTENT(innerPayparm.getUserName(), TimeConverter.formatDate(new Date()),priceChannel));
			orderLogParamBean.setOpUserId(innerPayparm.getUserId());
			orderLogParamBean.setLogType(OrderLogType.PAY.type);
			orderLogParamBean.setOpType(OperationType.CUSTOMER_SERVICE.value);
			orderLogParamBean.setOpUserId(innerPayparm.getUserId());
			orderLogParamBean.setOpUserName(innerPayparm.getUserName());
			orderLogParamBean.setOrderNo(orderNo);
			orderLogService.insertOrderLog(orderLogParamBean );
		}else{
			log.error("priceChannel="+innerPayparm.getPriceChannel()+",orderBean.getPriceChannel()=" + priceChannel);
			throw new PayException(PayReturnCodeEnum.PAY_PRICECHANNEL_CHANGED,priceChannel);
		}
		
		smsService.paySuccessSms(orderBean);
	}
}
