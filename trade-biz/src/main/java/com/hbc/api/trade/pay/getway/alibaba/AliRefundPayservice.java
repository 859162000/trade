/**
 * @Author lukangle
 * @2015年10月17日@上午11:46:31
 */
package com.hbc.api.trade.pay.getway.alibaba;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.hbc.api.fund.account.service.FundAccountService;
import com.hbc.api.gateway.alizhifu.GAliRefundPayservice;
import com.hbc.api.gateway.alizhifu.req.RefundInfo;
import com.hbc.api.trade.bdata.common.HttpClientService;
import com.hbc.api.trade.bdata.common.IDGenerotor;
import com.hbc.api.trade.bdata.common.util.TimeConverter;
import com.hbc.api.trade.order.enums.order.OrderStatus;
import com.hbc.api.trade.order.mapping.gen.bean.OrderBean;
import com.hbc.api.trade.order.service.OrderQueryService;
import com.hbc.api.trade.order.service.OrderService;
import com.hbc.api.trade.pay.enums.GetWayEnum;
import com.hbc.api.trade.pay.enums.RefundStatus;
import com.hbc.api.trade.pay.exception.PayException;
import com.hbc.api.trade.pay.exception.PayReturnCodeEnum;
import com.hbc.api.trade.pay.mapping.gen.TradeRefundMapper;
import com.hbc.api.trade.pay.mapping.gen.bean.TradePayment;
import com.hbc.api.trade.pay.mapping.gen.bean.TradeRefund;
import com.hbc.api.trade.pay.service.PaymentService;

@Component
public class AliRefundPayservice {
	private static final Logger log = LoggerFactory.getLogger(AliRefundPayservice.class);

	@Autowired
	private OrderQueryService orderQueryService;
	@Autowired
	private PaymentService paymentService;
	@Autowired
	protected HttpClientService httpClientService;
	@Autowired
	private TradeRefundMapper tradeRefundMapper;
	@Autowired
	private OrderService orderService;
	@Autowired
	private GAliRefundPayservice gAliRefundPayservice;
	@Autowired
	FundAccountService fundAccountService;
	@Transactional
	public void refundOrder(OrderBean orderBean, double refundAmout,Double toSystem,Double toGuide, String refundReason,TradePayment tradePayment) {
		OrderStatus orderStatus = OrderStatus.getStatus(orderBean.getOrderStatus());

		if (refundAmout > tradePayment.getPayActual() || tradePayment.getPayActual() > orderBean.getPriceChannel()) {
			log.error("PayActual大于PriceChannel");
			throw new PayException(PayReturnCodeEnum.PAY_BIGGER_PRICE, orderBean.getOrderNo() + "payActual[" + tradePayment.getPayActual() + "] priceChannel[" + orderBean.getPriceChannel() + "]");
		}
		TradeRefund tradeRefund = buildTradefund(tradePayment, refundAmout, orderBean, refundReason, orderStatus);
		RefundInfo refundInfo = new RefundInfo();
		refundInfo.setReason(refundReason);
		refundInfo.setRefundMoney(tradeRefund.getRefundMoney());
		refundInfo.setRefundNo(tradeRefund.getRefundNo());

		List<RefundInfo> refundList = new ArrayList<RefundInfo>();
		refundList.add(refundInfo);
		if(refundAmout>0){
			String refundRsp = gAliRefundPayservice.refundToAli(tradeRefund.getBatchNo(), 1, tradeRefund.getRefundDetail());
			tradeRefund.setRefundResponse(refundRsp);
			tradeRefund.setRefundStatus(RefundStatus.UNKNOWN.value);
		}else{
			tradeRefund.setRefundStatus(RefundStatus.SUCCESS.value);
		}
		tradeRefund.setRefundMoneyGuide(toGuide);
		tradeRefund.setRefundMoneySystem(toSystem);
		tradeRefundMapper.insert(tradeRefund);
	}

	private TradeRefund buildTradefund(TradePayment tradePayment, double refundAmout, OrderBean orderBean, String refundReason, OrderStatus orderStatus) {
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
		tradeRefund.setPayGatewayName(GetWayEnum.ALI.name);
		tradeRefund.setPayGetway(GetWayEnum.ALI.value);
		tradeRefund.setPayNo(tradePayment.getPayNo());
		tradeRefund.setRefundMoney(refundAmout);
		tradeRefund.setUserId(orderBean.getUserId());
		tradeRefund.setUserMobile(orderBean.getUserMobile1());

		tradeRefund.setOrderStatus(orderStatus.value);
		tradeRefund.setOrderStatusName(orderStatus.name);

		if (refundReason == null || refundReason.length() < 1) {
			refundReason = "黄包车订单协商退款 订单号："+orderBean.getOrderNo();
		}

		String detailData = tradePayment.getThirdPayNo() + "^" + refundAmout + "^" + refundReason;
		tradeRefund.setRefundDetail(detailData);
		return tradeRefund;
	}
}
