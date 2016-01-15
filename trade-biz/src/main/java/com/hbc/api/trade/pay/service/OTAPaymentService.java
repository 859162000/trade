/**
 * @Author lukangle
 * @2015年12月17日@下午7:46:48
 */
package com.hbc.api.trade.pay.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hbc.api.fund.account.enums.BizType;
import com.hbc.api.fund.account.service.FundAccountService;
import com.hbc.api.trade.bdata.common.enums.Sysuser;
import com.hbc.api.trade.bdata.common.util.TimeConverter;
import com.hbc.api.trade.order.enums.bean.OperationType;
import com.hbc.api.trade.order.enums.order.OrderLogType;
import com.hbc.api.trade.order.enums.order.OrderStatus;
import com.hbc.api.trade.order.enums.order.OrderType;
import com.hbc.api.trade.order.enums.ota.AgentChannelEnum;
import com.hbc.api.trade.order.mapping.gen.bean.OrderBean;
import com.hbc.api.trade.order.mapping.genx.xbean.OrderLogParamBean;
import com.hbc.api.trade.order.service.OrderLogService;
import com.hbc.api.trade.order.service.OrderQueryService;
import com.hbc.api.trade.order.service.OrderService;
import com.hbc.api.trade.pay.enums.GetWayEnum;
import com.hbc.api.trade.pay.enums.PayStatus;
import com.hbc.api.trade.pay.exception.PayException;
import com.hbc.api.trade.pay.exception.PayReturnCodeEnum;
import com.hbc.api.trade.settle.enums.AccountEnums;
import com.hbc.api.trade.settle.enums.FundBizEnumConvter;
import com.hbc.api.trade.third.sms.SMSService;
@Service
public class OTAPaymentService {
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
	public void payOTA(OrderBean orderBean,Double priceChannel, String subject, AgentChannelEnum agentChannelEnum){
		String accountNo = null;
		if(AgentChannelEnum.CTRIP_CHANNEL.equals(agentChannelEnum)){
			accountNo = AccountEnums.XIECHEN_ACCOUNT.value;
		}else if(AgentChannelEnum.QUA_CHANNEL.equals(agentChannelEnum)){
			accountNo = AccountEnums.QUA_ACCOUNT.value;
		}else if(AgentChannelEnum.QUNAR_CHANNEL.equals(agentChannelEnum)){
			accountNo = AccountEnums.QUNA_ACCOUNT.value;
		}else{
			throw new PayException(PayReturnCodeEnum.PAY_PRICECHANNEL_CHANGED,priceChannel);
		}
		paymentService.addTradePayment(orderBean, GetWayEnum.INNER, priceChannel, subject, accountNo,PayStatus.SUCCESS);
		OrderType orderType = OrderType.getType(orderBean.getOrderType());
		BizType bizType =	FundBizEnumConvter.getFundBizType(orderType);
		fundAccountService.recharge(accountNo, priceChannel,bizType,orderBean.getOrderNo());
		//担保账户加钱
		fundAccountService.pay(AccountEnums.HBC_Guarantee.value, priceChannel, bizType, orderBean.getOrderNo());
		
		orderService.updateOrderStatus(orderBean.getOrderNo(), OrderStatus.INITSTATE,OrderStatus.PAYSUCCESS);
		
		OrderLogParamBean orderLogParamBean = new OrderLogParamBean();
		orderLogParamBean.setContent(OrderLogType.PAY_CONTENT(orderBean.getUserName(), TimeConverter.formatDate(new Date()),priceChannel));
		orderLogParamBean.setOpUserId(agentChannelEnum.desc);
		orderLogParamBean.setLogType(OrderLogType.PAY.type);
		orderLogParamBean.setOpType(OperationType.SYSTEM.value);
		orderLogParamBean.setOpUserId(Sysuser.SYSUSER.id);
		orderLogParamBean.setOpUserName(Sysuser.SYSUSER.name);
		orderLogParamBean.setOrderNo(orderBean.getOrderNo());
		orderLogService.insertOrderLog(orderLogParamBean );
	}
	
}
