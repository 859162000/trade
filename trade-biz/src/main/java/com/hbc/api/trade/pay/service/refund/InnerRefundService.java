/**
 * @Author lukangle
 * @2015年11月19日@下午8:35:27
 */
package com.hbc.api.trade.pay.service.refund;

import java.sql.Timestamp;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hbc.api.fund.account.enums.BizType;
import com.hbc.api.fund.account.service.FundAccountService;
import com.hbc.api.trade.bdata.common.IDGenerotor;
import com.hbc.api.trade.bdata.common.util.TimeConverter;
import com.hbc.api.trade.order.enums.bean.OperationType;
import com.hbc.api.trade.order.enums.order.OrderLogType;
import com.hbc.api.trade.order.enums.order.OrderStatus;
import com.hbc.api.trade.order.enums.order.OrderType;
import com.hbc.api.trade.order.mapping.gen.bean.OrderBean;
import com.hbc.api.trade.order.mapping.genx.xbean.OrderLogParamBean;
import com.hbc.api.trade.order.service.OrderLogService;
import com.hbc.api.trade.pay.enums.GetWayEnum;
import com.hbc.api.trade.pay.mapping.gen.TradeRefundMapper;
import com.hbc.api.trade.pay.mapping.gen.bean.TradePayment;
import com.hbc.api.trade.pay.mapping.gen.bean.TradeRefund;
import com.hbc.api.trade.settle.enums.AccountEnums;
import com.hbc.api.trade.settle.enums.FundBizEnumConvter;

@Component
public class InnerRefundService {
	private final static Logger log = LoggerFactory.getLogger(InnerRefundService.class);
	
	@Autowired private FundAccountService 	fundAccountService;
	@Autowired private TradeRefundMapper 	tradeRefundMapper;
	@Autowired private OrderLogService 		orderLogService;

	/**
	 * 退款给用户 内部账户
	 * @param orderBean
	 * @param tradePayment
	 * @param bizType
	 * @param ruserPrice
	 * @param reason
	 */
	public void refundToUserAccount(OrderBean orderBean, TradePayment tradePayment, BizType bizType, double ruserPrice,String reason) {
		OrderStatus ostatus = OrderStatus.getStatus(orderBean.getOrderStatus());
		String orderNo = orderBean.getOrderNo();

		if (ruserPrice > 0) {
			fundAccountService.pay(tradePayment.getUserAccountNo(), ruserPrice, bizType, orderNo);
			TradeRefund tradeRefund = buildTradefund(tradePayment, ruserPrice, orderBean, reason, ostatus);
			tradeRefundMapper.insert(tradeRefund);
			
			OrderLogParamBean orderLogParamBean = new OrderLogParamBean();
			orderLogParamBean.setContent(OrderLogType.REFUND_CONTENT(orderBean.getUserName(), tradePayment.getUserAccountNo(), 
					TimeConverter.formatDate(new Date()), orderNo, ruserPrice));
			orderLogParamBean.setOpUserId(orderBean.getUserId());
			orderLogParamBean.setLogType(OrderLogType.REFUND.type);
			orderLogParamBean.setOpType(OperationType.CUSTOMER_SERVICE.value);
			orderLogParamBean.setOpUserId(orderBean.getUserId());
			orderLogParamBean.setOpUserName(orderBean.getUserName());
			orderLogParamBean.setOrderNo(orderNo);
			orderLogService.insertOrderLog(orderLogParamBean );
		}

		log.info("退款完成，订单[" + orderNo + "]  退给客户 " + orderBean.getUserName() + "[" + orderBean.getUserAccount() +" " +ruserPrice+"]");
	}

	/**
	 * C端自动 退款 退款给导游
	 * @param orderBean
	 * @param tradePayment
	 */
	public void refundToGuideAccount(OrderBean orderBean,  double rguidePrice) {
		String orderNo = orderBean.getOrderNo();
		// 内部账户支付 退款
		OrderType orderType = OrderType.getType(orderBean.getOrderType());
		BizType bizType = FundBizEnumConvter.getRefundBizType(orderType);

		if (rguidePrice > 0) {
			if(orderBean.getGuideAccountNo()==null){
				log.error(orderBean.getGuideId()+"无资金账户");
			}
			fundAccountService.pay(orderBean.getGuideAccountNo(), rguidePrice, bizType, orderNo);
		}

		log.info("退款完成，订单[" + orderNo + "] 退给导游" + orderBean.getGuideName() + " [" + orderBean.getGuideAccountNo() + "] ");
	}
	
	
	public void refundToHBCAccount(AccountEnums accountEnums,OrderBean orderBean,  double rsysamount) {
		String orderNo = orderBean.getOrderNo();
		// 内部账户支付 退款
		OrderType orderType = OrderType.getType(orderBean.getOrderType());
		BizType bizType = FundBizEnumConvter.getRefundBizType(orderType);

		if (rsysamount > 0) {
			fundAccountService.pay(accountEnums.value, rsysamount, bizType, orderNo);
		}

		log.info("退款完成，订单[" + orderNo + "] 退给"+accountEnums.getDesc() +"  "+accountEnums.getCode()+" [" +  rsysamount + "] ");
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
