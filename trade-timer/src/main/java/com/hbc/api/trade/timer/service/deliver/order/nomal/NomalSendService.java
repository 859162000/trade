/**
 * @Author lukangle
 * @2015年11月10日@下午7:31:39
 */
package com.hbc.api.trade.timer.service.deliver.order.nomal;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.hbc.api.trade.TradeFinalStr;
import com.hbc.api.trade.bdata.mapper.guide.gen.bean.GuideBean;
import com.hbc.api.trade.order.enums.deliver.DeliverDemandDeal;
import com.hbc.api.trade.order.enums.deliver.DeliverType;
import com.hbc.api.trade.order.enums.deliver.GuidDeliverStatus;
import com.hbc.api.trade.order.enums.deliver.IsReadable;
import com.hbc.api.trade.order.enums.deliver.OrderDeliverStatus;
import com.hbc.api.trade.order.enums.deliver.OrderSerialFlag;
import com.hbc.api.trade.order.enums.deliver.TradeDeliverStatus;
import com.hbc.api.trade.order.enums.order.OrderStatus;
import com.hbc.api.trade.order.mapping.gen.bean.OrderBean;
import com.hbc.api.trade.order.mapping.gen.bean.TradeDeliverGuide;
import com.hbc.api.trade.order.mapping.gen.bean.TradeOrderDeliver;
import com.hbc.api.trade.order.mapping.gen.bean.TradeOrderSerial;
import com.hbc.api.trade.order.service.OrderQueryService;
import com.hbc.api.trade.order.service.OrderServiceTime;
import com.hbc.api.trade.order.service.deliver.ConflictOrderService;
import com.hbc.api.trade.order.service.deliver.GuidDeliverOrderService;
import com.hbc.api.trade.order.service.deliver.GuideDeliverService;
import com.hbc.api.trade.order.service.deliver.OrderDeliverService;
import com.hbc.api.trade.order.service.deliver.TradeDeliverService;
import com.hbc.api.trade.order.service.deliver.conf.TradeCitySolrConf;
import com.hbc.api.trade.order.service.solr.TradeConfCollectionService;
import com.hbc.api.trade.third.GuideQueryService;
import com.hbc.api.trade.third.push.GPushService;
import com.hbc.api.trade.third.push.SeriPushService;
import com.hbc.api.trade.timer.service.deliver.order.serial.TradeOrderSerialService;

/**
 * 普通发单 根据服务城市 时区发起
 */
@Component
public class NomalSendService {
	private final static Logger log = LoggerFactory.getLogger(NomalSendService.class);
	@Autowired
	TradeDeliverService tradeDeliverService;
	@Autowired
	OrderServiceTime orderServiceTime;
	@Autowired
	GuidDeliverOrderService guidDeliverOrderService;
	@Autowired
	OrderQueryService orderQueryService;
	@Autowired
	OrderDeliverService orderDeliverService;

	@Autowired
	GuideDeliverService guideDeliverService;
	@Autowired
	TradeConfCollectionService tradeConfCollectionService;
	@Autowired
	ConflictOrderService conflictOrderService;
	@Autowired
	GuideQueryService guideQueryService;
	@Autowired
	GPushService pushService;
	@Autowired
	SeriPushService seriPushService;
	@Autowired
	TradeOrderSerialService tradeOrderSerialService;
	/**
	 * 在 8-22点 发送订单给导游
	 * 
	 * @param tradeOrderDeliver
	 */
	@Transactional
	public void sendOrderToGuide(TradeOrderDeliver tradeOrderDeliver) {
		log.info("开始发送订单["+tradeOrderDeliver.getDeliverNo()+"]");
		TradeDeliverStatus tradeDeliverStatus = TradeDeliverStatus.getType(tradeOrderDeliver.getDeliverStatus());
		if (TradeDeliverStatus.predeliver.equals(tradeDeliverStatus)) {
			Timestamp dtime = new Timestamp(System.currentTimeMillis());
			OrderBean orderBean = orderQueryService.getOrderByNo(tradeOrderDeliver.getOrderNo());
			
			OrderStatus dbStatus = OrderStatus.getStatus(orderBean.getOrderStatus());
			OrderDeliverStatus ostatus = OrderDeliverStatus.getType(orderBean.getDeliverStatus());
			
			if(OrderStatus.PAYSUCCESS.equals(dbStatus) && TradeFinalStr.defaultGuideId.equals(orderBean.getGuideId())){
				TradeCitySolrConf tradeCitySolrConf = tradeConfCollectionService.queryCityConf(orderBean);
				List<TradeDeliverGuide> guideDelvers = guidDeliverOrderService.getNoPushGuideOrders(orderBean, tradeOrderDeliver);

				if(!OrderSerialFlag.SERIAL.value.equals(orderBean.getSerialFlag())){
					compensateGuide(orderBean,tradeOrderDeliver,guideDelvers,tradeCitySolrConf);
				}
				
				List<String> guideList = new ArrayList<String>();
				int sendCount = 0;
				for (TradeDeliverGuide tradeDeliverGuide : guideDelvers) {
					String guideId = tradeDeliverGuide.getGuideId();
					GuideBean guideBean = guideQueryService.getGuideBeanById(guideId);
					boolean isConflict = conflictOrderService.isConflict(orderBean, guideBean.getGuideId(), tradeCitySolrConf);
					if (!isConflict) {
						guidDeliverOrderService.updateGuideDeliverStatus(tradeDeliverGuide, GuidDeliverStatus.nosend, GuidDeliverStatus.sendpush);
						guideList.add(guideId);

						sendCount++;
					} else {
						// 冲突则 强行关闭该条记录
						guidDeliverOrderService.disableGuideOrder(tradeDeliverGuide.getAllocatGno(), IsReadable.HIDDEN);
					}
				}
				tradeDeliverService.updateStatus(tradeOrderDeliver.getDeliverNo(), TradeDeliverStatus.getType(tradeOrderDeliver.getDeliverStatus()), TradeDeliverStatus.delivered, dtime);
				// 更新订单主状态
				tradeDeliverService.updateDeliverTimeAndCount(tradeOrderDeliver.getDeliverNo(), dtime, sendCount);
				orderDeliverService.updateOrderDeliverStatus(orderBean.getOrderNo(), dbStatus, ostatus, OrderDeliverStatus.deliving);

				// 发送push
				if (guideList.size() > 0) {
					if(!OrderSerialFlag.SERIAL.value.equals(orderBean.getSerialFlag())){
						pushService.pushNewOrder(guideList, orderBean);
					}else{
						List<TradeOrderSerial>  tlist = tradeOrderSerialService.getAllTradeSeri(orderBean.getOrderNo());
						if(tlist.size()>0){
							seriPushService.pushSerialNotis(orderBean, tlist);
						}
					}
					
				}
			}else{
				tradeDeliverService.updateStatus(tradeOrderDeliver.getDeliverNo(), TradeDeliverStatus.getType(tradeOrderDeliver.getDeliverStatus()), TradeDeliverStatus.deliverfailed, dtime);
			}
		} else {
			log.error("["+JSON.toJSON(tradeOrderDeliver)+"] 无法发单，存在错误");
		}
	}

	/**
	 * 预发单补偿机制
	 * @param orderBean
	 * @param tradeOrderDeliver
	 * @param guideDelvers
	 */
	public void compensateGuide(OrderBean orderBean, TradeOrderDeliver tradeOrderDeliver, List<TradeDeliverGuide> guideDelvers,TradeCitySolrConf tradeCitySolrConf) {
		// 当前可发单导游
		List<GuideBean> guideNewList = guideDeliverService.getDeliverGuidByCity(orderBean);
		List<String> pushGuideIds = new ArrayList<String>();
		for(GuideBean guideBean:guideNewList){
			if(!isContainGuide(guideDelvers,guideBean)){
				double priceGuide = guideDeliverService.getGuideFloatPrice(orderBean, guideBean);
				if(priceGuide>0){
					boolean isConflict = conflictOrderService.isConflict(orderBean, guideBean.getGuideId(), tradeCitySolrConf);
					if (!isConflict) {
						guidDeliverOrderService.insertToGuide(tradeOrderDeliver, guideBean, orderBean, priceGuide, DeliverDemandDeal.init, GuidDeliverStatus.sendpush, DeliverType.Ordinary);
						pushGuideIds.add(guideBean.getGuideId()+"");
					}
				}
			}
		}
		if(pushGuideIds.size()>0){
			pushService.pushNewOrder(pushGuideIds, orderBean);
		}
		
		
	}
	
	private boolean isContainGuide(List<TradeDeliverGuide> guideDelvers,GuideBean guideBean){
		for(TradeDeliverGuide tradeDeliverGuide : guideDelvers){
			if(tradeDeliverGuide.getGuideId().equalsIgnoreCase(guideBean.getGuideId()+"")){
				return true;
			}
		}
		return false;
	}
}
