/**
 * @Author lukangle
 * @2015年12月15日@下午8:12:01
 */
package com.hbc.data.trade.transfer.service.trade;

import java.sql.Timestamp;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.hbc.api.trade.bdata.common.IDGenerotor;
import com.hbc.api.trade.bdata.common.util.TimeConverter;
import com.hbc.api.trade.order.enums.order.OrderStatus;
import com.hbc.api.trade.order.mapping.gen.OrderBeanMapper;
import com.hbc.api.trade.order.mapping.gen.bean.OrderBean;
import com.hbc.api.trade.pay.enums.GetWayEnum;
import com.hbc.api.trade.pay.enums.PayStatus;
import com.hbc.api.trade.pay.enums.RefundStatus;
import com.hbc.api.trade.pay.exception.PayException;
import com.hbc.api.trade.pay.exception.PayReturnCodeEnum;
import com.hbc.api.trade.pay.mapping.gen.TradePaymentMapper;
import com.hbc.api.trade.pay.mapping.gen.TradeRefundMapper;
import com.hbc.api.trade.pay.mapping.gen.bean.TradePayment;
import com.hbc.api.trade.pay.mapping.gen.bean.TradeRefund;
import com.hbc.data.trade.transfer.enums.hfinal.FOrderPayStatus;
import com.hbc.data.trade.transfer.enums.pay.ConsumerPayStatus;
import com.hbc.data.trade.transfer.mapping.hbcbasedata.gen.FinalCouponMapper;
import com.hbc.data.trade.transfer.mapping.hbcbasedata.gen.bean.FinalCoupon;
import com.hbc.data.trade.transfer.mapping.hbcfinal.gen.FinalTmporderpayMapper;
import com.hbc.data.trade.transfer.mapping.hbcfinal.gen.bean.FinalOrderBean;
import com.hbc.data.trade.transfer.mapping.hbcfinal.gen.bean.FinalOrderPay;
import com.hbc.data.trade.transfer.mapping.hbcfinal.gen.bean.FinalOrderPayDetail;
import com.hbc.data.trade.transfer.mapping.hbcfinal.gen.bean.FinalTmporderpay;
import com.hbc.data.trade.transfer.mapping.pay.gen.bean.PayConsumeOrder;
import com.hbc.data.trade.transfer.mapping.pay.gen.bean.PayRechargeOrder;
import com.hbc.data.trade.transfer.mapping.pay.gen.bean.PayRefundOrder;
import com.hbc.data.trade.transfer.service.hfinal.FDailyOrderService;
import com.hbc.data.trade.transfer.service.hfinal.FOrderService;
import com.hbc.data.trade.transfer.service.hfinal.FPayService;
import com.hbc.data.trade.transfer.service.hfinal.FRefundService;
import com.hbc.data.trade.transfer.service.thrid.QunaOrderService;
import com.hbc.data.trade.transfer.service.thrid.XiechengOrderService;

@Component
public class DPayService {
	private final static Logger log = LoggerFactory.getLogger(DPayService.class);
	@Autowired
	FDailyOrderService fdailyOrderService;
	@Autowired
	FOrderService forderService;
	@Autowired
	FPayService fpayService;
	@Autowired
	FRefundService frefundService;
	@Autowired
	QunaOrderService qunaOrderService;
	@Autowired
	XiechengOrderService xiechengOrderService;
	@Autowired
	TradePaymentMapper tradePaymentMapper;
	@Autowired
	TradeRefundMapper tradeRefundMapper;
	@Autowired
	OrderBeanMapper orderBeanMapper;
	@Autowired
	FinalTmporderpayMapper finalTmporderpayMapper;
	@Autowired
	FinalCouponMapper finalCouponMapper;
	public String getCouponPriceInfo(Integer couponType,Integer couponPrice,Integer couponDiscount){
		if(couponType==null){
			return null;
		}
		switch (couponType) {
			case 1:
				if(couponPrice==null){
					return null;
				}
				return couponPrice + "元";
			case 2:
			case 3:
				if(couponDiscount==null){
					return null;
				}
				float rate = (float) couponDiscount / 10;
				if (rate <= 0) {
					return "免费券";
				} else {
					return rate + "折";
				}
			default:
				return null;
		}
	}

	
	public void updateCoupInfo(FinalOrderPay finalOrderPay,String orderNo){
		OrderBean orderBean = orderBeanMapper.selectByPrimaryKey(orderNo);
		
		OrderBean record = new OrderBean();
		record.setOrderNo(orderNo);
		record.setCoupId(finalOrderPay.getCouponid());
		if(!StringUtils.isEmpty(finalOrderPay.getCouponid())){
			FinalCoupon finalCoupon = finalCouponMapper.selectByPrimaryKey(finalOrderPay.getCouponid());
			
			if(finalCoupon.getCoupontype()!=null){
				String cinfo = getCouponPriceInfo(finalCoupon.getCoupontype(),finalCoupon.getCouponprice(),finalCoupon.getCoupondiscount());
				record.setCoupPriceInfo(cinfo);
			}
		}
		int inum = orderBeanMapper.updateByPrimaryKeySelective(record);
		if (inum == 1) {
			log.info("更新coup 信息成功 [" + orderBean.getOrderNo() + "]");
		}else{
			throw new PayException(PayReturnCodeEnum.PAY_INSERT_FAILED, " 更新券信息异常");
		}
	}
	public void updatePriceTicket(FinalTmporderpay finalTmporderpay,String orderNo){
		
		OrderBean record = new OrderBean();
		record.setOrderNo(orderNo);
		record.setPriceTicket(finalTmporderpay.getAgentprice()/100d);
		int inum = orderBeanMapper.updateByPrimaryKeySelective(record);
		if (inum == 1) {
			log.info("更新天猫票面价  信息成功 [" + record.getOrderNo() + "]");
		}else{
			throw new PayException(PayReturnCodeEnum.PAY_INSERT_FAILED, " 更新券信息异常");
		}
	}
	@Transactional
	public void setPayInfo(FinalOrderBean finalOrderBean,OrderBean orderBean) {
//		OrderBean orderBean = testInsertOrderBean(finalOrderBean);
//		if (orderBean == null) {
//			return;
//		}
		Integer payStatus = finalOrderBean.getPaystatus();
		if(payStatus==null || payStatus==0){
			return ;
		}
		FinalOrderPay finalOrderPay = fpayService.getPayInfosByOrderNo(finalOrderBean.getOrderid());
		FinalTmporderpay finalTmporderpay = finalTmporderpayMapper.selectByPrimaryKey(finalOrderBean.getOrderid());
		if(finalTmporderpay!=null){
			updatePriceTicket(finalTmporderpay,orderBean.getOrderNo());
		}
		// 支付信息
		FinalOrderPayDetail finalOrderPayDetail = fpayService.getOrderPayDetail(finalOrderBean.getOrderid());
		OrderStatus orderStatus = OrderStatus.getStatus(orderBean.getOrderStatus());// 该处将遗失退款时的
																					// 订单的状态
		if (finalOrderPay!=null  && finalOrderPayDetail == null) {
			// 内部账户支付
			// 直接产生一笔支付 信息
			// TODO lkl 添加用户资金账户
			TradePayment tradePayment = addPayment(orderBean, finalOrderPay);
			if(finalOrderPay.getCouponid()!=null && finalOrderPay.getCouponid().length()>0){
				updateCoupInfo(finalOrderPay,orderBean.getOrderNo());
			}
			// 退款信息
			if (finalOrderPay.getRefundprice() != null && finalOrderPay.getRefundprice() > 0) {
				TradeRefund tradeRefund = buildTradefund(tradePayment, finalOrderPay.getRefundprice(), orderBean, "黄包车协议退款", orderStatus);
				int optnum = tradeRefundMapper.insert(tradeRefund);
				if (optnum == 1) {
				} else {
					throw new PayException(PayReturnCodeEnum.PAY_INSERT_FAILED, tradeRefund.getOrderNo() + " 退款信息插入失败");
				}
			}
		} else if(finalOrderPay!=null) {
			// 支付宝支付
			int gateway = finalOrderPayDetail.getGateway();
			if (1 == gateway) {
				FOrderPayStatus forderPayStatus = FOrderPayStatus.getStatus(finalOrderPayDetail.getStatus());
				PayConsumeOrder payConsumeOrder = fpayService.getPayConsume(finalOrderPayDetail.getOrderpaydetailid());
				PayRechargeOrder payRechargeOrder = null;
				ConsumerPayStatus consumerPayStatus = null;
				if(payConsumeOrder!=null){
					payRechargeOrder = fpayService.getPayRecharge(payConsumeOrder.getRechargeId());
					consumerPayStatus = ConsumerPayStatus.getStatus(payConsumeOrder.getStatus());
				}
				if (FOrderPayStatus.PAYSUCCESS.equals(forderPayStatus)) {

					if (ConsumerPayStatus.PAYSUCCESS.equals(consumerPayStatus)) {
						addAliPayment(orderBean, finalOrderPay, finalOrderPayDetail, payConsumeOrder, payRechargeOrder, PayStatus.SUCCESS);
					}
					// else
					// if(ConsumerPayStatus.INITSTATE.equals(consumerPayStatus)){
					// addAliPayment(orderBean,finalOrderPay,payConsumeOrder,payRechargeOrder,PayStatus.INITED);
					// }else
					// if(ConsumerPayStatus.PAYFAILED.equals(consumerPayStatus)){
					// addAliPayment(orderBean,finalOrderPay,payConsumeOrder,payRechargeOrder,PayStatus.FAILED);
					// }
					else {
						throw new PayException(PayReturnCodeEnum.PAY_INSERT_FAILED, " 支付信息异常");
					}
				} else if (FOrderPayStatus.CANCLED.equals(forderPayStatus)) {
					if (ConsumerPayStatus.PAYSUCCESS.equals(consumerPayStatus)) {
						TradePayment tradePayment = addAliPayment(orderBean, finalOrderPay, finalOrderPayDetail, payConsumeOrder, payRechargeOrder, PayStatus.SUCCESS);
						PayRefundOrder payRefundOrder = frefundService.getAliRefund(finalOrderBean.getOrderid());
						TradeRefund tradeRefund = buildAliTradefund(tradePayment, payRefundOrder, orderBean, orderStatus);

						int optnum = tradeRefundMapper.insert(tradeRefund);
						if (optnum == 1) {
						} else {
							throw new PayException(PayReturnCodeEnum.PAY_INSERT_FAILED, tradeRefund.getOrderNo() + " 退款信息插入失败");
						}
					} else {
						TradePayment tradePayment = addPayment(orderBean, finalOrderPay);
						if(finalOrderPay.getCouponid()!=null && finalOrderPay.getCouponid().length()>0){
							updateCoupInfo(finalOrderPay,orderBean.getOrderNo());
						}
						// 退款信息
						if (finalOrderPay.getRefundprice() != null && finalOrderPay.getRefundprice() > 0) {
							TradeRefund tradeRefund = buildTradefund(tradePayment, finalOrderPay.getRefundprice(), orderBean, "黄包车协议退款", orderStatus);
							int optnum = tradeRefundMapper.insert(tradeRefund);
							if (optnum == 1) {
							} else {
								throw new PayException(PayReturnCodeEnum.PAY_INSERT_FAILED, tradeRefund.getOrderNo() + " 退款信息插入失败");
							}
						}
					}
				} else if (FOrderPayStatus.FAILED.equals(forderPayStatus)) {
					if (ConsumerPayStatus.PAYFAILED.equals(consumerPayStatus)) {
						addAliPayment(orderBean, finalOrderPay, finalOrderPayDetail, payConsumeOrder, payRechargeOrder, PayStatus.FAILED);
					}
					// else
					// if(ConsumerPayStatus.INITSTATE.equals(consumerPayStatus)){
					// addAliPayment(orderBean,finalOrderPay,payConsumeOrder,payRechargeOrder,PayStatus.INITED);
					// }else
					// if(ConsumerPayStatus.PAYFAILED.equals(consumerPayStatus)){
					// addAliPayment(orderBean,finalOrderPay,payConsumeOrder,payRechargeOrder,PayStatus.FAILED);
					// }
					else {
						throw new PayException(PayReturnCodeEnum.PAY_INSERT_FAILED, " 支付信息异常");
					}
				} else if (FOrderPayStatus.INITSTATE.equals(forderPayStatus)) {
					addAliPayment(orderBean, finalOrderPay, finalOrderPayDetail, payConsumeOrder, payRechargeOrder, PayStatus.INITED);
				} else {
					TradePayment tradePayment = addPayment(orderBean, finalOrderPay);
					if(finalOrderPay.getCouponid()!=null && finalOrderPay.getCouponid().length()>0){
						updateCoupInfo(finalOrderPay,orderBean.getOrderNo());
					}
					// 退款信息
					if (finalOrderPay.getRefundprice() != null && finalOrderPay.getRefundprice() > 0) {
						TradeRefund tradeRefund = buildTradefund(tradePayment, finalOrderPay.getRefundprice(), orderBean, "黄包车协议退款", orderStatus);
						int optnum = tradeRefundMapper.insert(tradeRefund);
						if (optnum == 1) {
						} else {
							throw new PayException(PayReturnCodeEnum.PAY_INSERT_FAILED, tradeRefund.getOrderNo() + " 退款信息插入失败");
						}
					}
				}
			} else {
				log.error(finalOrderPayDetail.getOrderpaydetailid() + " finalOrderPayDetail 是支付宝以外的支付形式 直接忽略");
			}
		}
	}

	public TradePayment addPayment(OrderBean orderBean, FinalOrderPay finalOrderPay) {
		TradePayment tradePayment = new TradePayment();
		tradePayment.setOrderNo(orderBean.getOrderNo());
		tradePayment.setOrderPrice(orderBean.getPriceChannel());
		Integer actualPay = finalOrderPay.getActualpay();
		if(actualPay==null){
			actualPay = 0;
		}
		
		if(finalOrderPay.getRefundprice()!=null && finalOrderPay.getRefundprice()>0){
			tradePayment.setPayActual((double)finalOrderPay.getRefundprice());
		}else{
			tradePayment.setPayActual((double)actualPay);
		}
		tradePayment.setPayFee(0.00);
		tradePayment.setPayNo(IDGenerotor.generatePayNo());
		tradePayment.setPaySubject("黄包车订单支付");
		tradePayment.setPayShould((double) finalOrderPay.getOrderprice());
		tradePayment.setPayGetway(GetWayEnum.INNER.value);
		tradePayment.setPayGatewayName(GetWayEnum.INNER.name);
		tradePayment.setUserAccountNo(orderBean.getUserAccount());

		tradePayment.setPayStatus(PayStatus.SUCCESS.value);
		tradePayment.setCreateTime(finalOrderPay.getCreatedAt());
		tradePayment.setUpdateTime(finalOrderPay.getUpdatedAt());

		// TODO coup 信息添加
		tradePayment.setCoupId(finalOrderPay.getCouponid());
		
		if(finalOrderPay.getCouponpay()!=null){
			tradePayment.setCoupPay((double)finalOrderPay.getCouponpay());
		}
		int optnum = tradePaymentMapper.insert(tradePayment);
		if (optnum == 1) {
		} else {
			throw new PayException(PayReturnCodeEnum.PAY_INSERT_FAILED, orderBean.getOrderNo());
		}

		return tradePayment;
	}

	public TradePayment addAliPayment(OrderBean orderBean, FinalOrderPay finalOrderPay, FinalOrderPayDetail finalOrderPayDetail, PayConsumeOrder payConsumeOrder, PayRechargeOrder payRechargeOrder,
			PayStatus payStatus) {
		TradePayment tradePayment = new TradePayment();
		tradePayment.setOrderNo(orderBean.getOrderNo());
		tradePayment.setOrderPrice(orderBean.getPriceChannel());
		Integer actualPay = finalOrderPay.getActualpay();
		if(actualPay==null){
			actualPay = 0;
		}
		
		if(finalOrderPay.getRefundprice()!=null && finalOrderPay.getRefundprice()>0){
			tradePayment.setPayActual((double)finalOrderPay.getRefundprice());
		}else{
			tradePayment.setPayActual((double)actualPay);
		}
		tradePayment.setPayFee(0.00);
		tradePayment.setPayNo(finalOrderPayDetail.getOrderpaydetailid());
		tradePayment.setPayShould((double) finalOrderPay.getOrderprice());
		tradePayment.setPayGetway(GetWayEnum.ALI.value);
		tradePayment.setPayGatewayName(GetWayEnum.ALI.name);

		// tradePayment.setUserAccountNo(11111 + "");

		tradePayment.setPayStatus(payStatus.value);
		tradePayment.setCreateTime(finalOrderPay.getCreatedAt());
		tradePayment.setUpdateTime(finalOrderPay.getUpdatedAt());
		
		if(!PayStatus.INITED.equals(payStatus)){
			tradePayment.setPaySubject(payConsumeOrder.getSubject());
			tradePayment.setPayTime(new Date(payConsumeOrder.getPayTime()));
			tradePayment.setThirdNotifyStatus(payRechargeOrder.getCallbackStatus() + "");
			tradePayment.setThirdNotifyLog(payRechargeOrder.getSerNotifyLog());
			tradePayment.setThirdPayNo(payRechargeOrder.getSerRechargeNo());
		}
		// TODO coup 信息添加
		if (finalOrderPay.getCouponid() != null) {
			tradePayment.setCoupId(finalOrderPay.getCouponid());
			tradePayment.setCoupPay((double) finalOrderPay.getCouponpay());
			// tradePayment.setCouponInfo(finalOrderPay.getC);
		}
		
		if(finalOrderPay.getCouponpay()!=null){
			tradePayment.setCoupPay((double)finalOrderPay.getCouponpay());
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
		tradeRefund.setRefundStatus(RefundStatus.SUCCESS.value);
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

	/**
	 * 退款所有数据全部迁移
	 * 
	 * @param tradePayment
	 * @param payRefundOrder
	 * @param orderBean
	 * @param orderStatus
	 * @return
	 */
	public TradeRefund buildAliTradefund(TradePayment tradePayment, PayRefundOrder payRefundOrder, OrderBean orderBean, OrderStatus orderStatus) {
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

		if (payRefundOrder.getSerNotifyStatus() == 1) {
			tradeRefund.setRefundStatus(RefundStatus.UNKNOWN.value);
		} else if (payRefundOrder.getSerNotifyStatus() == 2) {
			tradeRefund.setRefundStatus(RefundStatus.SUCCESS.value);
		} else if (payRefundOrder.getSerNotifyStatus() == 3) {
			tradeRefund.setRefundStatus(RefundStatus.FAILED.value);
		} else {
			tradeRefund.setRefundStatus(RefundStatus.UNKNOWN.value);
		}

		tradeRefund.setRefundRequest(payRefundOrder.getCallbackUrl());
		tradeRefund.setRefundTime(new Date(payRefundOrder.getRefundTime() * 1000L));
		tradeRefund.setRefundResponse(payRefundOrder.getSerNotifyLog());
		String detailData = tradePayment.getThirdPayNo() + "^" + tradeRefund.getRefundMoney() + "^" + payRefundOrder.getSubject();
		tradeRefund.setRefundDetail(detailData);

		tradeRefund.setRefundDetail(payRefundOrder.getSubject());
		return tradeRefund;
	}
}
