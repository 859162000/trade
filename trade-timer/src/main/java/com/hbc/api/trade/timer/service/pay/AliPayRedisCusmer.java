/**
 * @Author lukangle
 * @2015年11月14日@上午11:08:11
 */
package com.hbc.api.trade.timer.service.pay;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.hbc.api.gateway.alizhifu.kafka.KafPayParam;
import com.hbc.api.trade.bdata.common.redis.RedisDao;
import com.hbc.api.trade.bdata.common.util.TConfigLoader;
import com.hbc.api.trade.order.service.OrderQueryService;
import com.hbc.api.trade.pay.enums.PayStatus;
import com.hbc.api.trade.pay.exception.PayException;
import com.hbc.api.trade.pay.exception.PayReturnCodeEnum;
import com.hbc.api.trade.pay.mapping.gen.TradePaymentMapper;
import com.hbc.api.trade.pay.mapping.gen.bean.TradePayment;
import com.hbc.api.trade.pay.service.PaymentService;
@Component
public class AliPayRedisCusmer {
	private Logger log = LoggerFactory.getLogger(AliPayRedisCusmer.class);
	@Autowired
	OrderQueryService orderQueryService;
	@Autowired
	TradePaymentMapper tradePaymentMapper;
	@Autowired
	PaymentService paymentService;
	@Autowired
	RedisDao redisDao;
	
	public void startToCustomer(){
		String payReisKey = TConfigLoader.getProperty("ali.pay.redistopic", "alipaytopic");
		String msg = redisDao.blpop(payReisKey);
		log.info("start alipay redis cusomer .......");
		while(true){
			try{
				
				log.info("receive redis msg : "+msg);
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
				log.error("",e);
				redisDao.rpush(payReisKey+"error", msg);
			}
			
			msg = redisDao.blpop(payReisKey);
		}
	}
}
