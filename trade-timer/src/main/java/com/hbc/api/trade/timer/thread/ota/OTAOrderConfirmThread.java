/**
 * @Author lukangle
 * @2015年12月18日@下午6:36:12
 */
package com.hbc.api.trade.timer.thread.ota;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.hbc.api.trade.TradeFinalStr;
import com.hbc.api.trade.bdata.common.redis.RedisDao;
import com.hbc.api.trade.order.service.OrderQueryService;
import com.hbc.api.trade.ota.mapping.gen.bean.TradeThirdOrder;
import com.hbc.api.trade.third.LControllerService;
import com.hbc.api.trade.timer.service.ota.OtaOrderService;

@Component
public class OTAOrderConfirmThread  implements Runnable {
	private final static Logger log = LoggerFactory.getLogger(OTAOrderConfirmThread.class);
	@Autowired
	OtaOrderService otaOrderService;
	@Autowired
	OrderQueryService qrderQueryService;
	@Autowired
	LControllerService controllerService;
	@Autowired
	RedisDao redisDao;
	@Override
	public void run() {
		log.info("OTA 订单确认  服务启动 .......");
		while (true) {
			try {
				String orderInfo = redisDao.brpop(TradeFinalStr.orderConfirmQname);
				log.info("获取到 指派导游第三方 ["+orderInfo+"]");
				TimeUnit.SECONDS.sleep(5);
				TradeThirdOrder tradeThirdOrder  = JSON.parseObject(orderInfo,TradeThirdOrder.class);
				otaOrderService.confirmOrder(tradeThirdOrder);
			} catch (Exception e) {
				log.error("", e);
			}
		}
	}
}
