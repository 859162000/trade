/**
 * @Author lukangle
 * @2015年12月16日@下午3:27:22
 */
package com.hbc.api.trade.timer.thread.ota;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.hbc.api.trade.TradeFinalStr;
import com.hbc.api.trade.bdata.common.redis.RedisDao;
import com.hbc.api.trade.bdata.common.util.TimeConverter;
import com.hbc.api.trade.bdata.mapper.guide.gen.bean.GuideBean;
import com.hbc.api.trade.order.mapping.gen.TradeMoveInfoMapper;
import com.hbc.api.trade.order.mapping.gen.bean.OrderBean;
import com.hbc.api.trade.order.mapping.gen.bean.TradeMoveInfo;
import com.hbc.api.trade.order.mapping.gen.bean.TradeMoveInfoExample;
import com.hbc.api.trade.order.service.OrderQueryService;
import com.hbc.api.trade.ota.mapping.gen.bean.TradeThirdOrder;
import com.hbc.api.trade.third.LControllerService;
import com.hbc.api.trade.timer.service.ota.OtaOrderService;

@Component
public class OTAGuidConfirmThread implements Runnable {
	private final static Logger log = LoggerFactory.getLogger(OTAGuidConfirmThread.class);
	@Autowired
	OtaOrderService otaOrderService;
	@Autowired
	OrderQueryService qrderQueryService;
	@Autowired
	LControllerService controllerService;
	@Autowired
	RedisDao redisDao;
	@Autowired
	TradeMoveInfoMapper tradeMoveInfoMapper;
	@Override
	public void run() {
		log.info("OTA 导游确认 服务启动 .......");
		while (true) {
			try {
				String orderInfo = redisDao.brpop(TradeFinalStr.guideAssignQname);
				log.info("获取到 指派导游第三方 ["+orderInfo+"]");
				OrderBean orderBean = JSON.parseObject(orderInfo,OrderBean.class);
				
				TradeThirdOrder tradeThirdOrder = otaOrderService.getByOrderNo( orderBean.getOrderNo());
				GuideBean guideBean = controllerService.getGuidByGuideId(orderBean.getGuideId());
				
				log.info(orderBean.getOrderNo()+":"+JSON.toJSONString(tradeThirdOrder));
				otaOrderService.confirmGuide(tradeThirdOrder, guideBean, orderBean);
				
			} catch (Exception e) {
				log.error("", e);
			}
		}
	}
}
