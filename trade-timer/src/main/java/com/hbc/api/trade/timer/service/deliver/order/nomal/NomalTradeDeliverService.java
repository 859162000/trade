package com.hbc.api.trade.timer.service.deliver.order.nomal;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.hbc.api.trade.TradeFinalStr;
import com.hbc.api.trade.bdata.mapper.guide.gen.bean.GuideBean;
import com.hbc.api.trade.order.enums.deliver.DeliverDemandDeal;
import com.hbc.api.trade.order.enums.deliver.DeliverType;
import com.hbc.api.trade.order.enums.deliver.GuidDeliverStatus;
import com.hbc.api.trade.order.enums.deliver.OrderDeliverStatus;
import com.hbc.api.trade.order.enums.deliver.OrderSerialFlag;
import com.hbc.api.trade.order.enums.deliver.TradeDeliverStatus;
import com.hbc.api.trade.order.enums.order.OrderStatus;
import com.hbc.api.trade.order.mapping.gen.TradeOrderDeliverMapper;
import com.hbc.api.trade.order.mapping.gen.bean.OrderBean;
import com.hbc.api.trade.order.mapping.gen.bean.TradeOrderDeliver;
import com.hbc.api.trade.order.mapping.genx.deliver.WDOrderMapper;
import com.hbc.api.trade.order.service.OrderQueryService;
import com.hbc.api.trade.order.service.OrderServiceTime;
import com.hbc.api.trade.order.service.UrgentFlagService;
import com.hbc.api.trade.order.service.deliver.ConflictOrderService;
import com.hbc.api.trade.order.service.deliver.GuidDeliverOrderService;
import com.hbc.api.trade.order.service.deliver.GuideDeliverService;
import com.hbc.api.trade.order.service.deliver.OrderDeliverService;
import com.hbc.api.trade.order.service.deliver.TradeDeliverService;
import com.hbc.api.trade.order.service.deliver.conf.TradeCitySolrConf;
import com.hbc.api.trade.order.service.solr.TradeConfCollectionService;
import com.hbc.api.trade.third.LControllerService;
import com.hbc.api.trade.third.push.GPushService;
import com.hbc.api.trade.third.push.SeriPushService;
import com.hbc.api.trade.timer.service.deliver.order.serial.TradeOrderSerialService;

/**
 * 普通发单服务类
 */
@Component
public class NomalTradeDeliverService {
	private final static Logger log = LoggerFactory.getLogger(NomalTradeDeliverService.class);
	@Autowired
	WDOrderMapper worderDeliverMapper;
	@Autowired
	TradeOrderDeliverMapper tradeOrderDeliverMapper;
	@Autowired
	OrderServiceTime orderServiceTime;
	@Autowired
	WDOrderMapper woOrderDeliverMapper;
	private String defaultGuidId = TradeFinalStr.defaultGuideId;
	@Autowired
	GuideDeliverService guideDeliverService;
	@Autowired
	TradeConfCollectionService tradeConfCollectionService;
	@Autowired
	UrgentFlagService urgentFlagService;
	@Autowired
	OrderQueryService orderQueryService;
	@Autowired
	ConflictOrderService conflictOrderService;

	@Autowired
	private TradeDeliverService tradeDeliverService;
	@Autowired
	private GuidDeliverOrderService guidDeliverOrderService;
	@Autowired
	private OrderDeliverService orderDeliverService;
	@Autowired
	private LControllerService controllerService;
	public List<OrderBean> queryDeliverOrderBeans(int limit, int offset) {
		List<OrderBean> orderBeans = worderDeliverMapper.queryByGuideIdAndOrderStatus(defaultGuidId, OrderStatus.PAYSUCCESS.value, OrderDeliverStatus.init.value, limit, offset);
		return orderBeans;
	}

	public int countAllDeliverOrders() {
		int cnum = worderDeliverMapper.countByGuideIdAndOrderStatus(defaultGuidId, OrderStatus.PAYSUCCESS.value, OrderDeliverStatus.init.value);
		return cnum;
	}

	@Autowired
	GPushService pushService;
	@Autowired
	SeriPushService seriPushService;
	@Autowired
	TradeOrderSerialService tradeOrderSerialService;
	/**
	 * 串单发单
	 * @param orderBean
	 * @param smap
	 * @param tradeCitySolrConf
	 */
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void sendSearilUrgentOrder(OrderBean orderBean, Map<String,OrderBean> smap, TradeCitySolrConf tradeCitySolrConf){
		Timestamp dtime = new Timestamp(System.currentTimeMillis());
		TradeOrderDeliver tradeOrderDeliver = tradeDeliverService.insertDeliverOrder(orderBean, TradeDeliverStatus.delivered, DeliverType.Immediately, dtime);
		int sendCount = 0;
		
		List<String> pushGuideIds = new ArrayList<String>(smap.keySet());
		for (String guideId : pushGuideIds) {
			GuideBean guideBean = controllerService.getGuidByGuideId(guideId);
			double guidePrice = orderBean.getPriceGuide();
			if (guideBean.getGuideLevel() != null) {
				guidePrice = guideDeliverService.getGuideFloatPrice(orderBean, guideBean, tradeCitySolrConf);
			}
			if (guidePrice > 0) {
				guidDeliverOrderService.insertToGuide(tradeOrderDeliver, guideBean, orderBean, guidePrice, DeliverDemandDeal.init, GuidDeliverStatus.sendpush, DeliverType.Immediately);
				sendCount++;
				OrderBean meetOrderBean = smap.get(guideId);
				tradeOrderSerialService.saveTradeSerial(orderBean, meetOrderBean);
				log.info("串单 ： start to send order to guide [" + guideBean.getGuideId() + "] name is [" + guideBean.getGuideName() + "]");
			}
		}
		
		tradeDeliverService.updateDeliverTimeAndCount(tradeOrderDeliver.getDeliverNo(), dtime, sendCount);
		// 更新订单主状态
		orderDeliverService.updateOrderDeliverStatus(orderBean.getOrderNo(), OrderStatus.PAYSUCCESS, OrderDeliverStatus.init, OrderDeliverStatus.deliving, OrderSerialFlag.getType(orderBean.getSerialFlag()));

		seriPushService.pushSerialNotis(orderBean, smap);

		log.info("success to send order by urgent [" + orderBean.getOrderNo() + "]");
	}
	
	@Transactional
	public void sendSeriNomalOrder(OrderBean orderBean, Map<String,OrderBean> smap, TradeCitySolrConf tradeCitySolrConf) {
		// 非急单处理
		Timestamp dtime = new Timestamp(System.currentTimeMillis());
		TradeOrderDeliver tradeOrderDeliver = tradeDeliverService.insertDeliverOrder(orderBean, TradeDeliverStatus.predeliver, DeliverType.Ordinary, dtime);
		List<String> pushGuideIds = new ArrayList<String>(smap.keySet());
		for (String guideId : pushGuideIds) {
			GuideBean guideBean = controllerService.getGuidByGuideId(guideId);
			double guidePrice = orderBean.getPriceGuide();
			if (guideBean.getGuideLevel() != null) {
				guidePrice = guideDeliverService.getGuideFloatPrice(orderBean, guideBean, tradeCitySolrConf);
			}
			if (guidePrice > 0) {
				guidDeliverOrderService.insertToGuide(tradeOrderDeliver, guideBean, orderBean, guidePrice, DeliverDemandDeal.init, GuidDeliverStatus.nosend, DeliverType.Ordinary);
				OrderBean meetOrderBean = smap.get(guideId);
				tradeOrderSerialService.saveTradeSerial(orderBean, meetOrderBean);
				log.info("start to presend order [" + orderBean.getOrderNo() + "] to guide [" + guideBean.getGuideId() + "] name is [" + guideBean.getGuideName() + "]");
			}
		}
		// 更新订单主状态(预发单)
		orderDeliverService.updateOrderDeliverStatus(orderBean.getOrderNo(), OrderStatus.PAYSUCCESS, OrderDeliverStatus.init, OrderDeliverStatus.predelive, OrderSerialFlag.getType(orderBean.getSerialFlag()));
	}
	/**
	 * 发急单
	 * 
	 * @param orderBean
	 * @param guideBean
	 * @param guidePrice
	 */
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void sendUrgentOrder(OrderBean orderBean, List<GuideBean> guideList, TradeCitySolrConf tradeCitySolrConf) {
		// 急单处理
		Timestamp dtime = new Timestamp(System.currentTimeMillis());
		TradeOrderDeliver tradeOrderDeliver = tradeDeliverService.insertDeliverOrder(orderBean, TradeDeliverStatus.delivered, DeliverType.Immediately, dtime);
		int sendCount = 0;
		List<String> pushGuideIds = new ArrayList<String>();
		for (GuideBean guideBean : guideList) {
			// 串单 不计算冲突
			boolean isConflict = conflictOrderService.isConflict(orderBean, guideBean.getGuideId(), tradeCitySolrConf);

			if (!isConflict) {
				double guidePrice = orderBean.getPriceGuide();
				if (guideBean.getGuideLevel() != null) {
					guidePrice = guideDeliverService.getGuideFloatPrice(orderBean, guideBean, tradeCitySolrConf);
				}
				if (guidePrice > 0) {
					guidDeliverOrderService.insertToGuide(tradeOrderDeliver, guideBean, orderBean, guidePrice, DeliverDemandDeal.init, GuidDeliverStatus.sendpush, DeliverType.Immediately);
					pushGuideIds.add(guideBean.getGuideId());
					sendCount++;
				}
				log.info("start to send order to guide [" + guideBean.getGuideId() + "] name is [" + guideBean.getGuideName() + "]");
			} else {
				log.info("发单冲突 orderBean[" + orderBean.getOrderNo() + "] guideBean [" + JSON.toJSONString(guideBean) + "] tradeCitySolrConf [" + JSON.toJSONString(tradeCitySolrConf) + "] ");
			}
		}

		tradeDeliverService.updateDeliverTimeAndCount(tradeOrderDeliver.getDeliverNo(), dtime, sendCount);
		// 更新订单主状态
		orderDeliverService.updateOrderDeliverStatus(orderBean.getOrderNo(), OrderStatus.PAYSUCCESS, OrderDeliverStatus.init, OrderDeliverStatus.deliving, OrderSerialFlag.getType(orderBean.getSerialFlag()));

		// 发送PUSH
		if (pushGuideIds.size() > 0) {
			pushService.pushNewOrder(pushGuideIds, orderBean);
		}

		log.info("success to send order by urgent [" + orderBean.getOrderNo() + "]");
	}

	/**
	 * 发普通单
	 * 
	 * @param orderBean
	 * @param guideBean
	 * @param guidePrice
	 */
	@Transactional
	public void sendNomalOrder(OrderBean orderBean, List<GuideBean> guideList, TradeCitySolrConf tradeCitySolrConf) {
		// 非急单处理
		Timestamp dtime = new Timestamp(System.currentTimeMillis());
		TradeOrderDeliver tradeOrderDeliver = tradeDeliverService.insertDeliverOrder(orderBean, TradeDeliverStatus.predeliver, DeliverType.Ordinary, dtime);
		List<String> pushGuideIds = new ArrayList<String>();
		for (GuideBean guideBean : guideList) {
			boolean isConflict = conflictOrderService.isConflict(orderBean, guideBean.getGuideId(), tradeCitySolrConf);
			if (!isConflict) {
				double guidePrice = orderBean.getPriceGuide();
				if (guideBean.getGuideLevel() != null) {
					guidePrice = guideDeliverService.getGuideFloatPrice(orderBean, guideBean, tradeCitySolrConf);
				}
				if (guidePrice > 0) {
					pushGuideIds.add(guideBean.getGuideId());
					guidDeliverOrderService.insertToGuide(tradeOrderDeliver, guideBean, orderBean, guidePrice, DeliverDemandDeal.init, GuidDeliverStatus.nosend, DeliverType.Ordinary);
					log.info("start to presend order [" + orderBean.getOrderNo() + "] to guide [" + guideBean.getGuideId() + "] name is [" + guideBean.getGuideName() + "]");
				}
			} else {
				log.info("发单冲突 orderBean[" + orderBean.getOrderNo() + "] guideBean [" + JSON.toJSONString(guideBean) + "] tradeCitySolrConf [" + JSON.toJSONString(tradeCitySolrConf) + "] ");
			}
		}
		// 更新订单主状态(预发单)
		orderDeliverService.updateOrderDeliverStatus(orderBean.getOrderNo(), OrderStatus.PAYSUCCESS, OrderDeliverStatus.init, OrderDeliverStatus.predelive, OrderSerialFlag.getType(orderBean.getSerialFlag()));
		
	}
}
