/**
 * @Author lukangle
 * @2016年1月13日@下午10:34:08
 */
package com.hbc.data.trade.transfer.service;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hbc.api.trade.order.mapping.gen.OrderBeanMapper;
import com.hbc.api.trade.pay.mapping.gen.TradePaymentMapper;
import com.hbc.api.trade.pay.mapping.gen.TradeRefundMapper;
import com.hbc.api.trade.pay.mapping.gen.bean.TradePayment;
import com.hbc.api.trade.pay.mapping.gen.bean.TradePaymentExample;
import com.hbc.data.trade.transfer.mapping.hbcbasedata.gen.FinalCouponMapper;
import com.hbc.data.trade.transfer.mapping.hbcfinal.gen.FinalOrderBeanMapper;
import com.hbc.data.trade.transfer.mapping.hbcfinal.gen.FinalOrderPayDetailMapper;
import com.hbc.data.trade.transfer.mapping.hbcfinal.gen.FinalTmporderpayMapper;
import com.hbc.data.trade.transfer.mapping.hbcfinal.gen.bean.FinalOrderBean;
import com.hbc.data.trade.transfer.mapping.hbcfinal.gen.bean.FinalOrderPayDetail;
import com.hbc.data.trade.transfer.mapping.pay.gen.PayConsumeOrderMapper;
import com.hbc.data.trade.transfer.mapping.pay.gen.PayRechargeOrderMapper;
import com.hbc.data.trade.transfer.mapping.pay.gen.bean.PayConsumeOrder;
import com.hbc.data.trade.transfer.mapping.pay.gen.bean.PayConsumeOrderCriteria;
import com.hbc.data.trade.transfer.mapping.pay.gen.bean.PayRechargeOrder;
import com.hbc.data.trade.transfer.mapping.pay.gen.bean.PayRechargeOrderCriteria;
import com.hbc.data.trade.transfer.service.hfinal.FDailyOrderService;
import com.hbc.data.trade.transfer.service.hfinal.FOrderService;
import com.hbc.data.trade.transfer.service.hfinal.FPayService;
import com.hbc.data.trade.transfer.service.hfinal.FRefundService;
import com.hbc.data.trade.transfer.service.thrid.QunaOrderService;
import com.hbc.data.trade.transfer.service.thrid.XiechengOrderService;
import com.hbc.data.trade.transfer.service.trade.DPayService;

@Component
public class CheckRefundService {
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

	@Autowired
	PayConsumeOrderMapper payConsumeOrderMapper;

	@Autowired
	PayRechargeOrderMapper payRechargeOrderMapper;
	@Autowired
	FinalOrderPayDetailMapper finalOrderPayDetailMapper;

	@Autowired
	FinalOrderBeanMapper finalOrderMapper;
	public void check() {
		PayRechargeOrderCriteria payRechargeOrderCriteria = new PayRechargeOrderCriteria();
		List<PayRechargeOrder> prlist = payRechargeOrderMapper.selectByExample(payRechargeOrderCriteria);

		for (PayRechargeOrder payRechargeOrder : prlist) {
			String rid = payRechargeOrder.getId();

			PayConsumeOrderCriteria payConsumeOrderCriteria = new PayConsumeOrderCriteria();
			payConsumeOrderCriteria.createCriteria().andRechargeIdEqualTo(rid);
			List<PayConsumeOrder> plist = payConsumeOrderMapper.selectByExample(payConsumeOrderCriteria);

			PayConsumeOrder payConsumeOrder = plist.get(0);

			if (payRechargeOrder.getGateway() == 1) {
				FinalOrderPayDetail finalOrderPayDetail = finalOrderPayDetailMapper.selectByPrimaryKey(payConsumeOrder.getBusiConsumeNo());
				if(finalOrderPayDetail!=null){
					String orderId = finalOrderPayDetail.getOrderid();
					FinalOrderBean finalOrderBean = finalOrderMapper.selectByPrimaryKey(orderId);

					String newOrderSN = "";
					if (finalOrderBean.getOrderid().length() == 18) {
						newOrderSN = finalOrderBean.getOrderid().substring(15, 16);
					}
					String orderNo = finalOrderBean.getOrdersn() + newOrderSN;

//					log.info("开始check 订单【" + orderNo + "】");

					TradePaymentExample tradePaymentExample = new TradePaymentExample();
					tradePaymentExample.createCriteria().andOrderNoEqualTo(orderNo);
					List<TradePayment> payMentList = tradePaymentMapper.selectByExample(tradePaymentExample);
					
					if(payMentList.size()>0){
						TradePayment tradePayment = payMentList.get(0);
						
						if(tradePayment.getPayGetway()==1 && (tradePayment.getThirdPayNo()==null || tradePayment.getThirdPayNo().length()<2)){
							log.info("订单【" + orderNo + "】 支付信息异常 需要修复 第三方订单号修改成【"+payRechargeOrder.getSerRechargeNo()+"】");
//							tradePayment.setPayGetway(GetWayEnum.ALI.value);
//							tradePayment.setPayGatewayName(GetWayEnum.ALI.name);
							tradePayment.setThirdNotifyLog(payRechargeOrder.getSerNotifyLog());
							tradePayment.setThirdPayNo(payRechargeOrder.getSerRechargeNo());
							if(payRechargeOrder.getPayTime()>0){
								tradePayment.setPayTime(new Date(payRechargeOrder.getPayTime()*1000l));
							}
							
							tradePaymentMapper.updateByPrimaryKeySelective(tradePayment);
							
//							TradeRefundExample tradeRefundExample = new TradeRefundExample();
//							tradeRefundExample.createCriteria().andOrderNoEqualTo(orderNo);
//							List<TradeRefund> refundList = tradeRefundMapper.selectByExample(tradeRefundExample);
//							if(refundList.size()>0){
//								log.info("订单【" + orderNo + "】 退款信息异常 需要修复");
//								TradeRefund tradeRefund = refundList.get(0);
//								
//								tradeRefund.setPayGetway(GetWayEnum.ALI.value);
//								tradeRefund.setPayGatewayName(GetWayEnum.ALI.name);
//								tradeRefundMapper.updateByPrimaryKeySelective(tradeRefund);
//							}
						}
					}
				}else{
					log.error("detail id is :"+payConsumeOrder.getBusiConsumeNo()+" 无详细支付信息");
				}
			}
		}
	}
}
