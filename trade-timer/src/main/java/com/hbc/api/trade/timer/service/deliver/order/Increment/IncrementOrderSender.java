package com.hbc.api.trade.timer.service.deliver.order.Increment;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hbc.api.trade.TradeFinalStr;
import com.hbc.api.trade.bdata.common.HttpClientService;
import com.hbc.api.trade.bdata.mapper.guide.gen.bean.GuideBean;
import com.hbc.api.trade.order.enums.deliver.DeliverDemandDeal;
import com.hbc.api.trade.order.enums.deliver.DeliverType;
import com.hbc.api.trade.order.enums.deliver.GuidDeliverStatus;
import com.hbc.api.trade.order.enums.deliver.IsReadable;
import com.hbc.api.trade.order.enums.deliver.OrderSerialFlag;
import com.hbc.api.trade.order.enums.deliver.TradeDeliverStatus;
import com.hbc.api.trade.order.mapping.gen.OrderBeanMapper;
import com.hbc.api.trade.order.mapping.gen.bean.OrderBean;
import com.hbc.api.trade.order.mapping.gen.bean.TradeDeliverGuide;
import com.hbc.api.trade.order.mapping.gen.bean.TradeOrderDeliver;
import com.hbc.api.trade.order.mapping.genx.deliver.GxGuideDeliverMapper;
import com.hbc.api.trade.order.service.OrderService;
import com.hbc.api.trade.order.service.OrderServiceTime;
import com.hbc.api.trade.order.service.deliver.ConflictOrderService;
import com.hbc.api.trade.order.service.deliver.GuidDeliverOrderService;
import com.hbc.api.trade.order.service.deliver.GuideDeliverService;
import com.hbc.api.trade.order.service.deliver.TradeDeliverService;
import com.hbc.api.trade.order.service.deliver.conf.TradeCitySolrConf;
import com.hbc.api.trade.order.service.solr.TradeConfCollectionService;
import com.hbc.api.trade.third.push.GPushService;

@Component
public class IncrementOrderSender {
	@Autowired
	TradeDeliverService tradeDeliverService;

	@Autowired
	OrderBeanMapper orderBeanMapper;

	@Autowired
	OrderService orderService;
	@Autowired
	GuideDeliverService guideDeliverService;

	@Autowired
	GuidDeliverOrderService guidDeliverOrderService;

	@Autowired
	HttpClientService httpClientService;
	@Autowired
	OrderServiceTime orderServiceTime;
	@Autowired
	GPushService pushService;
	@Autowired
	TradeConfCollectionService tradeConfCollectionService;
	@Autowired
	ConflictOrderService conflictOrderService;
	@Autowired
	GxGuideDeliverMapper gxGuideDeliverMapper;

	private final static Logger log = LoggerFactory.getLogger(IncrementOrderSender.class);

	public void startToIncrement() {

		List<TradeOrderDeliver> tradeDelivers = tradeDeliverService.getTradeDelivers(TradeDeliverStatus.predeliver, DeliverType.Incremental_Send);
		if (tradeDelivers != null && tradeDelivers.size() > 0) {
			for (TradeOrderDeliver tradeDeliver : tradeDelivers) {
				try {
					incrementSend(tradeDeliver);
				} catch (Exception e) {
					log.error("", e);
				}
			}
		}
	}

	public void incrementSend(TradeOrderDeliver tradeOrderDeliver) {
		OrderBean orderBean = orderBeanMapper.selectByPrimaryKey(tradeOrderDeliver.getOrderNo());
		incrementSend(tradeOrderDeliver, orderBean);
	}

	public void incrementSend(TradeOrderDeliver tradeOrderDeliver, OrderBean orderBean) {
		if (orderBean == null || (orderBean.getGuideId().length() > 0 && !orderBean.getGuideId().trim().equals("0"))) {
			return;
		}

		Date curdate = orderServiceTime.getServiceCityCurTime(orderBean.getServiceCityId());
		int hour = curdate.getHours();
		Date serviceDate = orderBean.getServiceTime();
		Date urgentDate = new Date(serviceDate.getTime() - 24 * 60 * 60 * 1000);

		log.info("增量补发开始| 当前时间:{} 服务时间:{} 紧急时间:{}", curdate, serviceDate, urgentDate);
		// 当地时间在 8-22点之间 或者24小时之内的订单
		if (hour < 22 && hour >= 8 || urgentDate.after(curdate)) {
			log.info("订单Id：" + orderBean.getOrderNo() + "开始增量补发");
			// 目前为止可发导游
			List<GuideBean> guideList = guideDeliverService.getDeliverGuidByCityGuideLevel(orderBean);
			log.info("增量补发| 目前为止可发导游:{}", guideList);

			// 获取所有已发导游
			List<TradeDeliverGuide> guidesource = guidDeliverOrderService.getGuideOrdersByOrderNo(orderBean);
			log.info("增量补发| 获取所有已发导游:{}", guidesource);

			// 目标导游
			List<TradeDeliverGuide> guideTarget = new ArrayList<TradeDeliverGuide>();
			Map<TradeDeliverGuide, GuideBean> guideMap = new HashMap<TradeDeliverGuide, GuideBean>();

			for (GuideBean guideBean : guideList) {
				boolean send =false;
				for(TradeDeliverGuide tradeDeliverGuide:guidesource){
					if(tradeDeliverGuide.getGuideId().equals(guideBean.getGuideId())){
						send = true;
						break;
					}
				}
				if(!send){
					TradeDeliverGuide newDeliverGuide = new TradeDeliverGuide();
					newDeliverGuide.setGuidePrice(guideDeliverService.getGuideFloatPrice(orderBean, guideBean));
					newDeliverGuide.setGuideId(guideBean.getGuideId() + "");
					guideTarget.add(newDeliverGuide);
					// 对象作为Key 放入MAP 可能存在风险（保证对象不修改）
					guideMap.put(newDeliverGuide, guideBean);
				}
			}
			// 是否串单
			if (OrderSerialFlag.SERIAL.value.equals(orderBean.getSerialFlag())) {// 串单
				orderService.updateOrderSerialFlag(0, "", orderBean.getOrderNo());
				if(orderBean.getSerialOrderNo() != null && orderBean.getSerialOrderNo().length()>0){
					orderService.updateOrderSerialFlag(0, "", orderBean.getSerialOrderNo());
				}
				//将原先导游全部置为不可见
				gxGuideDeliverMapper.updateByOrderNoReadable(orderBean.getOrderNo(), IsReadable.VISIABLE.value, IsReadable.HIDDEN.value);

			} 
//			else {
//				for (GuideBean guideBean : guideList) {
//					boolean isSend = false;
//					for (TradeDeliverGuide tradeDeliverGuide : guidesource) {
//						if (tradeDeliverGuide.getGuideId().equals(guideBean.getGuideId())) {
//							isSend = true;
//							break;
//						}
//
//					}
//					if (!isSend) {
//						TradeDeliverGuide newDeliverGuide = new TradeDeliverGuide();
//						newDeliverGuide.setGuidePrice(guideDeliverService.getGuideFloatPrice(orderBean, guideBean));
//						newDeliverGuide.setGuideId(guideBean.getGuideId() + "");
//						guideTarget.add(newDeliverGuide);
//						// 对象作为Key 放入MAP 可能存在风险（保证对象不修改）
//						guideMap.put(newDeliverGuide, guideBean);
//					}
//
//				}
//			}
			List<String> pushGuideIds = new ArrayList<String>();
			String pushGuideIdStr = "";
			int deliverCount = 0;

			log.info("增量补发| 目标导游:{} | {}", guideTarget, guideTarget.size());
			// solr 配置查询
			TradeCitySolrConf tradeCitySolrConf = tradeConfCollectionService.queryCityConf(orderBean);
			if (guideTarget.size() > 0) {
				for (TradeDeliverGuide deliverGuide : guideTarget) {
					GuideBean guideBean = guideMap.get(deliverGuide);
					//检查是否冲突
					boolean isConflict = conflictOrderService.isConflict(orderBean, guideBean.getGuideId(), tradeCitySolrConf);
					if (!isConflict && !guideBean.getGuideId().equals(TradeFinalStr.defaultGuideId)) {
						deliverCount++;
						pushGuideIds.add(String.valueOf(guideBean.getGuideId()));
						pushGuideIdStr += guideBean.getGuideId() + ",";
						guidDeliverOrderService.insertToGuide(tradeOrderDeliver, guideBean, orderBean, deliverGuide.getGuidePrice(), DeliverDemandDeal.init, GuidDeliverStatus.sendpush, DeliverType.Incremental_Send);
					}
				}
			}

			// 更新发包时间 发包状态
			Timestamp deliverTime = new Timestamp(System.currentTimeMillis());
			tradeDeliverService.updateIncrementDeliver(tradeOrderDeliver.getDeliverNo(), deliverTime, deliverCount, TradeDeliverStatus.delivered);
			log.info("增量补发| 目标导游:{}  更新发包时间:{} 发包状态: 发包数量:{} 发包状态:{}  deliverNo:{}", guideTarget, deliverTime, deliverCount, TradeDeliverStatus.delivered, tradeOrderDeliver.getDeliverNo());
			// 发送PUSH
			if (pushGuideIds.size() > 0) {
				pushService.pushNewOrder(pushGuideIds, orderBean);
			}
			log.info("订单Id：" + orderBean.getOrderNo() + "增量补发结束，补发导游Id" + pushGuideIdStr);
		}

	}

}
