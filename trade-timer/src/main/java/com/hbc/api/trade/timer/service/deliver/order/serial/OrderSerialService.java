package com.hbc.api.trade.timer.service.deliver.order.serial;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.hbc.api.trade.bdata.mapper.guide.gen.GuideBeanMapper;
import com.hbc.api.trade.bdata.mapper.guide.gen.bean.GuideBean;
import com.hbc.api.trade.bdata.mapper.guide.gen.bean.GuideProhibit;
import com.hbc.api.trade.order.enums.conf.CitySerialOpen;
import com.hbc.api.trade.order.enums.order.OrderType;
import com.hbc.api.trade.order.enums.third.GuideProhibitEnum;
import com.hbc.api.trade.order.enums.third.GuideSendOrderFlag;
import com.hbc.api.trade.order.mapping.gen.bean.OrderBean;
import com.hbc.api.trade.order.service.deliver.ConflictOrderService;
import com.hbc.api.trade.order.service.deliver.TradeDeliverService;
import com.hbc.api.trade.order.service.deliver.conf.TradeCitySolrConf;
import com.hbc.api.trade.order.service.solr.TradeConfCollectionService;
import com.hbc.api.trade.third.LControllerService;

@Component
public class OrderSerialService {
	private final static Logger log = LoggerFactory.getLogger(OrderSerialService.class);
	@Autowired
	TradeConfCollectionService tradeConfCollectionService;
	@Autowired
	TradeDeliverService tradeDeliverService;
	@Autowired
	LControllerService lControllerService;
	@Autowired
	TradeOrderSerialService tradeOrderSerialService;
	
	@Autowired
	GuideBeanMapper guideBeanMapper;
	@Autowired
	ConflictOrderService conflictOrderService;
	
	@Transactional
	public Map<String,OrderBean> checkOrderSerial(OrderBean orderBean) {		
		TradeCitySolrConf tradeCitySolrConf = tradeConfCollectionService.queryCityConf(orderBean);
		Map<String,OrderBean> sermap = new HashMap<String,OrderBean>();
		if (tradeCitySolrConf!=null && tradeCitySolrConf.getSer_open() == CitySerialOpen.OPEN.value) {
			int startMin = tradeCitySolrConf.getSer_start_minute();
			int endMin = tradeCitySolrConf.getSer_end_minute();
			Date ServiceTime = orderBean.getServiceTime();
			List<GuideBean> guides= new ArrayList<GuideBean>();
			if (orderBean.getOrderType() == OrderType.PICKUPORDER.value.intValue()) {// 接机订单
																			// 需要查找匹配的送机订单
				// 计算送机订单 开始时间 和结束时间
				Date startTimeDate = new Date(ServiceTime.getTime() - (endMin * 60 * 1000 ));
				Date endTimeDate = new Date(ServiceTime.getTime() - (startMin * 60 * 1000 ));
				List<OrderBean> orders = tradeDeliverService.getOrders(orderBean.getServiceCityId(), OrderType.TRANSFER, startTimeDate, endTimeDate);
				if (orders != null && orders.size() > 0) {
					for (OrderBean order : orders) {
						long serviceEndTime = order.getServiceTime().getTime() + order.getExpectedCompTime() * 60 * 1000;
						// 符合串单规则
						if (ServiceTime.getTime() - serviceEndTime > startMin * 60 * 1000 && ServiceTime.getTime() - serviceEndTime < endMin * 60 * 1000) {
							if(sermap.get(order.getGuideId())!=null){
								log.warn("导游  ["+order.getGuideId()+"] 已经串单，又有新订单和"+orderBean.getOrderNo()+" 匹配到，所以丢弃");
							}else{
								GuideBean guideBean = guideBeanMapper.selectByPrimaryKey(order.getGuideId());
								if(guideBean != null && guideBean.getGuideId()!= null){
									guides.add(guideBean);
									sermap.put(order.getGuideId(), order);
								}
								
							}
						}
					}
				}

			} else if (orderBean.getOrderType() == OrderType.TRANSFER.value.intValue()) {// 送机订单
																				// 需要查找匹配的接机订单
				// 计算接机订单 开始时间 和结束时间

				Date startTimeDate = new Date(ServiceTime.getTime() + orderBean.getExpectedCompTime() * 60 * 1000 + startMin * 60 * 1000);
				Date endTimeDate = new Date(ServiceTime.getTime() + orderBean.getExpectedCompTime() * 60 * 1000 + endMin * 60 * 1000);

				List<OrderBean> orders = tradeDeliverService.getOrders(orderBean.getServiceCityId(), OrderType.PICKUPORDER, startTimeDate, endTimeDate);
				if (orders != null && orders.size() > 0) {
					for (OrderBean order : orders) {
						if(sermap.get(order.getGuideId())!=null){
							log.warn("导游  ["+order.getGuideId()+"] 已经串单，又有新订单和"+orderBean.getOrderNo()+" 匹配到，所以丢弃");
						}else{
							GuideBean guideBean = guideBeanMapper.selectByPrimaryKey(order.getGuideId());
							if(guideBean != null && guideBean.getGuideId()!= null&& !GuideSendOrderFlag.FORBID.value.equals(guideBean.getSendOrderFlag())){
								guides.add(guideBean);
								sermap.put(order.getGuideId(), order);
							}
						}
					}
				}

			}
			
			
	
			if (guides.size() > 0) {
				// 检查 是否禁止听单
				List<GuideProhibit> guideProhibits = lControllerService.getGuideProhibitPush(guides,GuideProhibitEnum.PROHIBIT_TYPE_PUSH);
				if (guideProhibits.size() > 0) {
					List<GuideBean> guidesClone = new ArrayList<GuideBean>();
					guidesClone.addAll(guides);
					for (GuideProhibit guideProhibit : guideProhibits) {
						for (GuideBean guideBean : guidesClone) {
							if (guideBean.getGuideId() .equals( guideProhibit.getGuideId())) {
								guides.remove(guideBean);
								break;
							}
							
						}
					}
				}
			}
			

		}
		return sermap;
	}
	
	public Map<String,OrderBean> checkOrderSerial(OrderBean orderBean,List<GuideBean> allGuides) {		
		TradeCitySolrConf tradeCitySolrConf = tradeConfCollectionService.queryCityConf(orderBean);
		Map<String,OrderBean> sermap = new HashMap<String,OrderBean>();
		if (tradeCitySolrConf!=null && tradeCitySolrConf.getSer_open() == CitySerialOpen.OPEN.value) {
			int startMin = tradeCitySolrConf.getSer_start_minute();
			int endMin = tradeCitySolrConf.getSer_end_minute();
			Date ServiceTime = orderBean.getServiceTime();
			List<GuideBean> guides= new ArrayList<GuideBean>();
			List<String> allGuideIdStr= new ArrayList<String>();
			for(GuideBean guideBean:allGuides){				
				if(guideBean.getGuideId() != null && !"0".equals(guideBean.getGuideId()) &&!guides.contains(guideBean.getGuideId())){
					allGuideIdStr.add(guideBean.getGuideId());
				}	
			}
			if(allGuideIdStr.size()==0){
				return sermap;
			}
			
			
			if (orderBean.getOrderType() == OrderType.PICKUPORDER.value.intValue()) {// 接机订单
																			// 需要查找匹配的送机订单
				// 计算送机订单 开始时间 和结束时间
				Date startTimeDate = new Date(ServiceTime.getTime() - (endMin * 60 * 1000 ));
				Date endTimeDate = new Date(ServiceTime.getTime() - (startMin * 60 * 1000 ));
//				List<OrderBean> orders = tradeDeliverService.getOrders(orderBean.getServiceCityId(), OrderType.TRANSFER, startTimeDate, endTimeDate);
				
				
				
				List<OrderBean> orders = tradeDeliverService.getOrders(orderBean.getServiceCityId(), OrderType.TRANSFER, startTimeDate, endTimeDate,allGuideIdStr,orderBean.getFlightDestCode());
				if (orders != null && orders.size() > 0) {
					for (OrderBean order : orders) {
						long serviceEndTime = order.getServiceTime().getTime() + order.getExpectedCompTime() * 60 * 1000;
						// 符合串单规则
						if (ServiceTime.getTime() - serviceEndTime > startMin * 60 * 1000 && ServiceTime.getTime() - serviceEndTime < endMin * 60 * 1000) {
							if(sermap.get(order.getGuideId())!=null){
								log.warn("导游  ["+order.getGuideId()+"] 已经串单，又有新订单和"+orderBean.getOrderNo()+" 匹配到，所以丢弃");
							}else{
								GuideBean guideBean = guideBeanMapper.selectByPrimaryKey(order.getGuideId());
								if(guideBean != null && guideBean.getGuideId()!= null){
									guides.add(guideBean);
									sermap.put(order.getGuideId(), order);
								}
								
							}
						}
					}
				}

			} else if (orderBean.getOrderType() == OrderType.TRANSFER.value.intValue()) {// 送机订单
																				// 需要查找匹配的接机订单
				// 计算接机订单 开始时间 和结束时间

				Date startTimeDate = new Date(ServiceTime.getTime() + orderBean.getExpectedCompTime() * 60 * 1000 + startMin * 60 * 1000);
				Date endTimeDate = new Date(ServiceTime.getTime() + orderBean.getExpectedCompTime() * 60 * 1000 + endMin * 60 * 1000);

				List<OrderBean> orders = tradeDeliverService.getOrders(orderBean.getServiceCityId(), OrderType.PICKUPORDER, startTimeDate, endTimeDate,allGuideIdStr,orderBean.getFlightAirportCode());
				if (orders != null && orders.size() > 0) {
					for (OrderBean order : orders) {
						if(sermap.get(order.getGuideId())!=null){
							log.warn("导游  ["+order.getGuideId()+"] 已经串单，又有新订单和"+orderBean.getOrderNo()+" 匹配到，所以丢弃");
						}else{
							GuideBean guideBean = guideBeanMapper.selectByPrimaryKey(order.getGuideId());
							if(guideBean != null && guideBean.getGuideId()!= null&& !GuideSendOrderFlag.FORBID.value.equals(guideBean.getSendOrderFlag())){
								guides.add(guideBean);
								sermap.put(order.getGuideId(), order);
							}
						}
					}
				}

			}
			
			
	
			if (guides.size() > 0) {
				// 检查 是否禁止听单
				List<GuideProhibit> guideProhibits = lControllerService.getGuideProhibitPush(guides,GuideProhibitEnum.PROHIBIT_TYPE_PUSH);
				if (guideProhibits.size() > 0) {
					List<GuideBean> guidesClone = new ArrayList<GuideBean>();
					guidesClone.addAll(guides);
					for (GuideProhibit guideProhibit : guideProhibits) {
						for (GuideBean guideBean : guidesClone) {
							if (guideBean.getGuideId() .equals( guideProhibit.getGuideId())) {
								guides.remove(guideBean);
								break;
							}
							
						}
					}
				}
			}
			

		}
		return sermap;
	}
}
