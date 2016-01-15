/**
 * @Author lukangle
 * @2015年12月17日@下午8:16:49
 */
package com.hbc.api.trade.pay.service.refund;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hbc.api.fund.account.enums.BizType;
import com.hbc.api.fund.account.service.FundAccountService;
import com.hbc.api.trade.TradeFinalStr;
import com.hbc.api.trade.bdata.common.IDGenerotor;
import com.hbc.api.trade.bdata.common.util.DoubleUtil;
import com.hbc.api.trade.bdata.common.util.TimeConverter;
import com.hbc.api.trade.order.enums.bean.OperationType;
import com.hbc.api.trade.order.enums.order.OrderLogType;
import com.hbc.api.trade.order.enums.order.OrderStatus;
import com.hbc.api.trade.order.enums.order.OrderType;
import com.hbc.api.trade.order.enums.ota.AgentChannelEnum;
import com.hbc.api.trade.order.mapping.gen.bean.OrderBean;
import com.hbc.api.trade.order.mapping.genx.xbean.OrderLogParamBean;
import com.hbc.api.trade.order.service.OrderLogService;
import com.hbc.api.trade.pay.enums.GetWayEnum;
import com.hbc.api.trade.pay.exception.PayException;
import com.hbc.api.trade.pay.exception.PayReturnCodeEnum;
import com.hbc.api.trade.pay.mapping.gen.TradeRefundMapper;
import com.hbc.api.trade.pay.mapping.gen.bean.TradePayment;
import com.hbc.api.trade.pay.mapping.gen.bean.TradeRefund;
import com.hbc.api.trade.pay.mapping.gen.bean.TradeRefundConf;
import com.hbc.api.trade.pay.service.RefundConfService;
import com.hbc.api.trade.settle.enums.AccountEnums;
import com.hbc.api.trade.settle.enums.FundBizEnumConvter;

@Component
public class OTARefundService {
	private final static Logger log = LoggerFactory.getLogger(OTARefundService.class);
	@Autowired private FundAccountService 	fundAccountService;
	@Autowired private TradeRefundMapper 	tradeRefundMapper;
	@Autowired private OrderLogService 		orderLogService;
	@Autowired
	RefundConfService refundConfService;
	@Autowired
	InnerRefundService innerRefundService;
	/**
	 * 退款给用户 内部账户
	 * @param orderBean
	 * @param tradePayment
	 * @param bizType
	 * @param ruserPrice
	 * @param reason
	 */
	public void refundToUserAccount(OrderBean orderBean, TradePayment tradePayment, double ruserPrice,String reason,AgentChannelEnum agentChannelEnum){
		String accountNo = null;
		if(AgentChannelEnum.CTRIP_CHANNEL.equals(agentChannelEnum)){
			accountNo = AccountEnums.XIECHEN_ACCOUNT.value;
		}else if(AgentChannelEnum.QUA_CHANNEL.equals(agentChannelEnum)){
			accountNo = AccountEnums.QUA_ACCOUNT.value;
		}else if(AgentChannelEnum.QUNAR_CHANNEL.equals(agentChannelEnum)){
			accountNo = AccountEnums.QUNA_ACCOUNT.value;
		}else{
			throw new PayException(PayReturnCodeEnum.PAY_PRICECHANNEL_CHANGED,orderBean.getOrderNo());
		}
		OrderStatus ostatus = OrderStatus.getStatus(orderBean.getOrderStatus());
		List<TradeRefundConf> refundConfs = refundConfService.getRefunConfs();

		double rguidePrice = refundConfService.getRefundGuidePrice(orderBean, refundConfs);
		
		double toProfit = DoubleUtil.subtractionDouble(orderBean.getPriceChannel(), DoubleUtil.addDouble(ruserPrice, rguidePrice));
		double butie = DoubleUtil.subtractionDouble(DoubleUtil.addDouble(ruserPrice, rguidePrice),orderBean.getPriceChannel());
		
		double toSystem = 0.0d;
		if(toProfit>0){
			toSystem = toProfit;
		}else if(butie>0){
			toSystem = butie;
		}
		//担保账户减钱
		if(toProfit>0){
			innerRefundService.refundToHBCAccount(AccountEnums.HBC_Profit,orderBean, toProfit);
		}else if(butie>0){
			innerRefundService.refundToHBCAccount(AccountEnums.HBC_BUTIE,orderBean, butie);
		}
		if(TradeFinalStr.defaultGuideId.equals(orderBean.getGuideId())){
			//无导游情况 全额退款给用户
			ruserPrice = tradePayment.getPayActual();
			rguidePrice = 0.0d;
		}
		
		
		OrderType orderType = OrderType.getType(orderBean.getOrderType());
		BizType bizType = FundBizEnumConvter.getRefundBizType(orderType);
		
		String orderNo = orderBean.getOrderNo();
		
		fundAccountService.recharge(AccountEnums.HBC_Guarantee.value, orderBean.getPriceChannel(), bizType, orderBean.getOrderNo());
		
		if (ruserPrice > 0 && accountNo.equals(tradePayment.getUserAccountNo())) {
			fundAccountService.pay(tradePayment.getUserAccountNo(), ruserPrice, bizType, orderNo);
		}
		
		if(orderBean.getGuideId()!=null && !orderBean.getGuideId().equals(TradeFinalStr.defaultGuideId) ){
			innerRefundService.refundToGuideAccount(orderBean, rguidePrice);
		}
		TradeRefund tradeRefund = buildTradefund(tradePayment, ruserPrice, orderBean, reason, ostatus);
		tradeRefund.setRefundMoneyGuide(rguidePrice);
		tradeRefund.setRefundMoneySystem(toSystem);
		tradeRefundMapper.insert(tradeRefund);
		
		OrderLogParamBean orderLogParamBean = new OrderLogParamBean();
		orderLogParamBean.setContent(OrderLogType.REFUND_CONTENT(orderBean.getUserName(), tradePayment.getUserAccountNo(), 
				TimeConverter.formatDate(new Date()), orderNo, ruserPrice));
		orderLogParamBean.setOpUserId(orderBean.getUserId());
		orderLogParamBean.setLogType(OrderLogType.REFUND.type);
		orderLogParamBean.setOpType(OperationType.SYSTEM.value);
		orderLogParamBean.setOpUserId(agentChannelEnum.value+"");
		orderLogParamBean.setOpUserName(agentChannelEnum.name());
		orderLogParamBean.setOrderNo(orderNo);
		orderLogService.insertOrderLog(orderLogParamBean);

		log.info("OTA 退款完成，订单[" + orderNo + "]  退给客户 " + orderBean.getUserName() + "[" + orderBean.getUserAccount() +" " +ruserPrice+"] 给导游 【"+rguidePrice+"】");
	}
	
	public TradeRefund buildTradefund(TradePayment tradePayment, double refundAmout, OrderBean orderBean, String refundReason, OrderStatus orderStatus) {
		TradeRefund tradeRefund = new TradeRefund();
		tradeRefund.setRefundNo(IDGenerotor.generateRefundNo());
		Timestamp curtime = new Timestamp(System.currentTimeMillis());
		String batchNo3End = orderBean.getOrderNo().substring(0, 3);

		String batchNo = TimeConverter.formatDate(curtime, "yyyyMMddHHmmss") + batchNo3End;
		tradeRefund.setCreateTime(curtime);
		tradeRefund.setBatchNum(1);
		tradeRefund.setRefundTime(curtime);
		tradeRefund.setBatchNo(batchNo);

		tradeRefund.setOrderNo(tradePayment.getOrderNo());
		tradeRefund.setPayGatewayName(GetWayEnum.INNER.name);
		tradeRefund.setPayGetway(GetWayEnum.INNER.value);
		tradeRefund.setPayNo(tradePayment.getOrderNo());
		tradeRefund.setRefundMoney(refundAmout);
		tradeRefund.setUserId(orderBean.getUserId());
		tradeRefund.setUserMobile(orderBean.getUserMobile1());
		tradeRefund.setRefundAccount(tradePayment.getUserAccountNo());
		tradeRefund.setOrderStatus(orderStatus.value);
		tradeRefund.setOrderStatusName(orderStatus.name);

		if (refundReason == null || refundReason.length() < 1) {
			refundReason = "黄包车订单协商退款";
		}

		String detailData = "内部账户退款";
		tradeRefund.setRefundDetail(detailData);
		return tradeRefund;
	}
}
