package com.hbc.api.trade.timer.service.pay;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.hbc.api.gateway.alizhifu.kafka.KafPayParam;
import com.hbc.api.trade.order.service.OrderQueryService;
import com.hbc.api.trade.pay.enums.PayStatus;
import com.hbc.api.trade.pay.exception.PayException;
import com.hbc.api.trade.pay.exception.PayReturnCodeEnum;
import com.hbc.api.trade.pay.mapping.gen.TradePaymentMapper;
import com.hbc.api.trade.pay.mapping.gen.bean.TradePayment;
import com.hbc.api.trade.pay.service.PaymentService;
import com.hbc.api.trade.timer.kafka.KafkaMessageReceiver;
@Component
public class AliPayKafkaCusmer extends KafkaMessageReceiver{
	private Logger log = LoggerFactory.getLogger(AliPayKafkaCusmer.class);
	@Autowired
	OrderQueryService orderQueryService;
	@Autowired
	TradePaymentMapper tradePaymentMapper;
	@Autowired
	PaymentService paymentService;
	@Override
	public void handleMessage(String msg) {
		try{
			log.info("receive r msg : "+msg);
			KafPayParam payParam = JSON.parseObject(msg, KafPayParam.class);
			String payNo = payParam.getOut_trade_no();
			
			TradePayment tradePaymentDb = paymentService.queryTradePaymentByPayNo(payNo);
			if(tradePaymentDb==null){
				log.error(payNo+" 通知 不存在支付信息");
				throw new PayException(PayReturnCodeEnum.PAY_NOT_EXIST,payNo);
			}
			
			PayStatus payStatus = PayStatus.getType(tradePaymentDb.getPayStatus());
			if(!payStatus.equals(PayStatus.SUCCESS)){
				log.info("将 "+tradePaymentDb.getOrderNo()+" 支付状态变成成功");
				paymentService.payNotifyPayment(payParam, msg);
			}
		}catch(Exception e){
			log.error("", e);
		}
	}
	
}