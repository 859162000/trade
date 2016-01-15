/**
 * @Author lukangle
 * @2015年12月8日@下午8:49:08
 */
package com.hbc.data.trade.transfer.service.trade;

import java.sql.Date;
import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hbc.api.trade.bdata.common.IDGenerotor;
import com.hbc.api.trade.bdata.common.util.TimeConverter;
import com.hbc.api.trade.order.enums.order.OrderStatus;
import com.hbc.api.trade.order.mapping.gen.bean.OrderBean;
import com.hbc.api.trade.pay.enums.GetWayEnum;
import com.hbc.api.trade.pay.enums.PayStatus;
import com.hbc.api.trade.pay.exception.PayException;
import com.hbc.api.trade.pay.exception.PayReturnCodeEnum;
import com.hbc.api.trade.pay.mapping.gen.TradePaymentMapper;
import com.hbc.api.trade.pay.mapping.gen.TradeRefundMapper;
import com.hbc.api.trade.pay.mapping.gen.bean.TradePayment;
import com.hbc.api.trade.pay.mapping.gen.bean.TradeRefund;
import com.hbc.data.trade.transfer.mapping.hbcfinal.gen.bean.FinalOrderBean;
import com.hbc.data.trade.transfer.mapping.hbcfinal.gen.bean.FinalOrderPay;
import com.hbc.data.trade.transfer.mapping.hbcfinal.gen.bean.FinalOrderPayDetail;
import com.hbc.data.trade.transfer.mapping.pay.gen.bean.PayConsumeOrder;
import com.hbc.data.trade.transfer.mapping.pay.gen.bean.PayRechargeOrder;
import com.hbc.data.trade.transfer.mapping.pay.gen.bean.PayRefundOrder;
import com.hbc.data.trade.transfer.service.hfinal.FDailyOrderService;
import com.hbc.data.trade.transfer.service.hfinal.FPayService;

@Component
public class PayServiceBack {
	@Autowired
	FPayService fpayService;
	@Autowired
	FDailyOrderService fdailyOrderService;
	@Autowired
	TradePaymentMapper tradePaymentMapper;
//	public void insertPayInfos(FinalOrderBean finalOrderBean){
//		FinalOrderPay finalOrderPay = fpayService.getPayInfosByOrderNo(finalOrderBean.getOrderid());
//		
//		finalOrderPay.getActualpay();
//		finalOrderPay.getCostprice();
//		finalOrderPay.getCouponid();
//		finalOrderPay.getCouponpay();
//		finalOrderPay.getOrderprice();
//		finalOrderPay.getPaytype();
//		finalOrderPay.getUpdatedAt();
//		
//		FinalOrderPayDetail finalOrderPayDetail = fpayService.getOrderPayDetail(finalOrderBean.getOrderid());
//		
//		
//		
//		TradePayment tradePayment = new TradePayment();
//		Date curtime = new Date(System.currentTimeMillis());
//		tradePayment.setCreateTime(curtime);
//		tradePayment.setOrderNo(finalOrderBean.getOrdersn());
//		tradePayment.setOrderPrice((double)finalOrderBean.getClientprice());
//		tradePayment.setPayActual((double)finalOrderPay.getActualpay());
//		tradePayment.setPayFee(0.00);
//		tradePayment.setPayNo(finalOrderPay.getOrderid());
////		tradePayment.setPaySubject();
//		tradePayment.setPayShould((double)finalOrderBean.getClientprice());
//		
//		
//		tradePayment.setPayGetway(GetWayEnum.ALI.value);
//		tradePayment.setPayGatewayName(GetWayEnum.ALI.name);
//		
//		tradePayment.setUserAccountNo(userAccountNo);
//		
//		tradePayment.setPayStatus(payStatus.value);
//		
//		int optnum = tradePaymentMapper.insert(tradePayment);
//	}
	
	
	@Autowired
	TradeRefundMapper tradeRefundMapper;

	public TradePayment addPayment(OrderBean orderBean, FinalOrderPay finalOrderPay) {
		TradePayment tradePayment = new TradePayment();
		tradePayment.setOrderNo(orderBean.getOrderNo());
		tradePayment.setOrderPrice(orderBean.getPriceChannel());
		tradePayment.setPayActual((double) finalOrderPay.getActualpay());
		tradePayment.setPayFee(0.00);
		tradePayment.setPayNo(IDGenerotor.generatePayNo());
		tradePayment.setPaySubject("黄包车订单支付");
		tradePayment.setPayShould((double) finalOrderPay.getOrderprice());
		tradePayment.setPayGetway(GetWayEnum.INNER.value);
		tradePayment.setPayGatewayName(GetWayEnum.INNER.name);
		tradePayment.setUserAccountNo(11111 + "");

		tradePayment.setPayStatus(PayStatus.SUCCESS.value);
		tradePayment.setCreateTime(finalOrderPay.getCreatedAt());
		tradePayment.setUpdateTime(finalOrderPay.getUpdatedAt());

		// TODO coup 信息添加
		tradePayment.setCoupId(finalOrderPay.getCouponid());
		int optnum = tradePaymentMapper.insert(tradePayment);
		if (optnum == 1) {
		} else {
			throw new PayException(PayReturnCodeEnum.PAY_INSERT_FAILED, orderBean.getOrderNo());
		}

		return tradePayment;
	}

	public TradePayment addAliPayment(OrderBean orderBean, FinalOrderPay finalOrderPay, PayConsumeOrder payConsumeOrder, PayRechargeOrder payRechargeOrder, PayStatus payStatus) {
		TradePayment tradePayment = new TradePayment();
		tradePayment.setOrderNo(orderBean.getOrderNo());
		tradePayment.setOrderPrice(orderBean.getPriceChannel());
		tradePayment.setPayActual((double) finalOrderPay.getActualpay());
		tradePayment.setPayFee(0.00);
		tradePayment.setPayNo(IDGenerotor.generatePayNo());
		tradePayment.setPaySubject(payConsumeOrder.getSubject());
		tradePayment.setPayShould((double) finalOrderPay.getOrderprice());
		tradePayment.setPayGetway(GetWayEnum.ALI.value);
		tradePayment.setPayGatewayName(GetWayEnum.ALI.name);
		tradePayment.setUserAccountNo(11111 + "");

		tradePayment.setPayStatus(payStatus.value);
		tradePayment.setCreateTime(finalOrderPay.getCreatedAt());
		tradePayment.setUpdateTime(finalOrderPay.getUpdatedAt());

		tradePayment.setPayTime(new Date(payConsumeOrder.getPayTime()));
		tradePayment.setThirdNotifyStatus(payRechargeOrder.getCallbackStatus() + "");
		tradePayment.setThirdNotifyLog(payRechargeOrder.getSerNotifyLog());
		tradePayment.setThirdPayNo(payRechargeOrder.getSerRechargeNo());
		// TODO coup 信息添加
		if (finalOrderPay.getCouponid() != null) {
			tradePayment.setCoupId(finalOrderPay.getCouponid());
			tradePayment.setCoupPay((double) finalOrderPay.getCouponpay());
			// tradePayment.setCouponInfo(finalOrderPay.getC);
		}
		int optnum = tradePaymentMapper.insert(tradePayment);
		if (optnum == 1) {
		} else {
			throw new PayException(PayReturnCodeEnum.PAY_INSERT_FAILED, orderBean.getOrderNo());
		}

		return tradePayment;
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
		tradeRefund.setPayNo(tradePayment.getPayNo());
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

	public TradeRefund buildAliTradefund(TradePayment tradePayment, PayRefundOrder payRefundOrder, OrderBean orderBean,  OrderStatus orderStatus) {
		TradeRefund tradeRefund = new TradeRefund();
		tradeRefund.setRefundNo(payRefundOrder.getId());
		tradeRefund.setCreateTime(new Date(payRefundOrder.getCreateTime() * 1000L));
		tradeRefund.setBatchNum(1);
		tradeRefund.setBatchNo(payRefundOrder.getSerRefundNo());

		tradeRefund.setOrderNo(tradePayment.getOrderNo());
		tradeRefund.setPayGatewayName(GetWayEnum.ALI.name);
		tradeRefund.setPayGetway(GetWayEnum.ALI.value);
		tradeRefund.setPayNo(tradePayment.getPayNo());
		tradeRefund.setRefundMoney(payRefundOrder.getAmount() / 100.00d);
		tradeRefund.setUserId(orderBean.getUserId());
		tradeRefund.setUserMobile(orderBean.getUserMobile1());
		tradeRefund.setRefundAccount(tradePayment.getUserAccountNo());
		tradeRefund.setOrderStatus(orderStatus.value);
		tradeRefund.setOrderStatusName(orderStatus.name);

		tradeRefund.setRefundRequest(payRefundOrder.getCallbackUrl());
		tradeRefund.setRefundTime(new Date(payRefundOrder.getRefundTime() * 1000L));
		tradeRefund.setRefundResponse(payRefundOrder.getSerNotifyLog());


		String detailData = "黄包车订单协商退款";
		tradeRefund.setRefundDetail(detailData);
		return tradeRefund;
	}
}
