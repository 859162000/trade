/**
 * @Author lukangle
 * @2015年11月14日@下午12:08:08
 */
package com.hbc.api.trade.timer.service.pay;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.hbc.api.trade.bdata.common.redis.RedisDao;
import com.hbc.api.trade.bdata.common.util.TConfigLoader;
import com.hbc.api.trade.order.service.OrderQueryService;
import com.hbc.api.trade.pay.enums.RefundStatus;
import com.hbc.api.trade.pay.mapping.gen.bean.TradePayment;
import com.hbc.api.trade.pay.mapping.gen.bean.TradeRefund;
import com.hbc.api.trade.pay.service.NotifyRefundService;
import com.hbc.api.trade.pay.service.PaymentService;

@Component
public class AliRefundRedisCusmer {
	private Logger log = LoggerFactory.getLogger(AliRefundRedisCusmer.class);
	@Autowired
	OrderQueryService orderQueryService;
	@Autowired
	NotifyRefundService notifyRefundService;
	@Autowired
	PaymentService paymentService;
	@Autowired
	RedisDao redisDao;
	int redisN = 3;

	public void startToCustomer() {
		String payReisKey = TConfigLoader.getProperty("ali.refund.redistopic", "alirefundtopic");
		String msg = redisDao.blpop(payReisKey);
		log.info("start alirefund redis cusomer .......");
		while (true) {
			try {
				log.info("receive redis msg refund : " + msg);
				List<TradeRefund> refundList = msgHandle(msg);
				for (TradeRefund tradeRefund : refundList) {
					notifyRefundService.refundSuccess(tradeRefund, tradeRefund.getRefundMoney());
				}
			} catch (Exception e) {
				log.error("", e);
				redisDao.rpush(payReisKey + "error", msg);
			}

			msg = redisDao.blpop(payReisKey);
		}
	}

	private List<TradeRefund> msgHandle(String msg) {
		AliRefundNotifyParam refundNotifyParam = JSON.parseObject(msg, AliRefundNotifyParam.class);
		String batchNo = refundNotifyParam.getBatch_no();
		String redetail = refundNotifyParam.getResult_details();
		int succesNum = Integer.parseInt(refundNotifyParam.getSuccess_num());

		String[] reundStrs = redetail.split(";");
		List<TradeRefund> refundList = new ArrayList<TradeRefund>();
		for (String refundStr : reundStrs) {
			String[] refundInfos = refundStr.split("\\^");
			if (refundInfos.length == 3) {
				String tradeNo = refundInfos[0];
				double refundMoney = Double.parseDouble(refundInfos[1]);
				String refundResult = refundInfos[2];

				TradePayment tradePayment = paymentService.queryByTradeNo(tradeNo);
				TradeRefund tradeRefund = notifyRefundService.queryRefundByBathNOOrderNo(tradePayment.getPayNo(), tradePayment.getOrderNo());
				if ("SUCCESS".equalsIgnoreCase(refundResult)) {
					if (!tradeRefund.getRefundMoney().equals(refundMoney)) {
						tradeRefund.setRefundError("退款通知 金额 [" + refundMoney + "] 和 退款金额不等 ");
					}
					RefundStatus refundStatus = RefundStatus.getType(tradeRefund.getRefundStatus());
					if (refundStatus.equals(RefundStatus.UNKNOWN)) {
						tradeRefund.setRefundStatus(RefundStatus.SUCCESS.value);
					} else {
						log.warn(tradeRefund.getRefundNo() + " 退款状态可能异常" + refundStatus + " ");
						tradeRefund.setRefundStatus(RefundStatus.SUCCESS.value);
					}
					tradeRefund.setRefundResponse(msg);
					refundList.add(tradeRefund);
				} else {
					log.info(tradePayment.getOrderNo() + "支付宝 退款 失败 " + msg);
				}
			} else {
				log.error("支付宝 退款 格式错误 " + msg);
			}

		}

		return refundList;
	}
}
