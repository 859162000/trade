package com.hbc.api.trade.timer.service.deliver.order.push;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hbc.api.trade.order.enums.TradeReturnCodeEnum;
import com.hbc.api.trade.order.enums.deliver.DeliverType;
import com.hbc.api.trade.order.enums.deliver.DemandDeal;
import com.hbc.api.trade.order.enums.deliver.GuidDeliverStatus;
import com.hbc.api.trade.order.exception.TradeException;
import com.hbc.api.trade.order.mapping.gen.OrderBeanMapper;
import com.hbc.api.trade.order.mapping.gen.bean.OrderBean;
import com.hbc.api.trade.order.mapping.gen.bean.TradeDeliverGuide;
import com.hbc.api.trade.order.service.OrderServiceTime;
import com.hbc.api.trade.order.service.deliver.GuidDeliverOrderService;
import com.hbc.api.trade.third.push.CPushService;
import com.hbc.api.trade.third.push.GPushService;
import com.hbc.api.trade.third.sms.SMSService;

@Component
public class OrderPushSender {
	
	private final static Logger log = LoggerFactory.getLogger(OrderPushSender.class);
	@Autowired
	GuidDeliverOrderService guidDeliverOrderService;
	@Autowired
	OrderBeanMapper orderBeanMapper;
	@Autowired
	OrderServiceTime orderServiceTime;
	@Autowired
	GPushService gpushService;
	
	@Autowired
	CPushService cpushService;
	@Autowired
	SMSService smsservice;
	public void startToOrderPush(){
		List<TradeDeliverGuide> tradeDeliverGuides =getNeedPushGuide();
		for(TradeDeliverGuide tradeDeliverGuide:tradeDeliverGuides){
			try{
				OrderBean orderBean=orderBeanMapper.selectByPrimaryKey(tradeDeliverGuide.getOrderNo());
				DeliverType deliverType = DeliverType.getType(tradeDeliverGuide.getDeliverType());
				Date curtime = orderServiceTime.getServiceCityCurTime(orderBean.getServiceCityId());
				if(tradeDeliverGuide.getDemandDeal() == null || !DemandDeal.AGREE.equals(DemandDeal.getType(tradeDeliverGuide.getDemandDeal()))){
					continue;
				}
				int hour = curtime.getHours();
				if(hour>=8 && hour<22){
					//发送PUSH
					GuidDeliverStatus guidDeliverStatus =GuidDeliverStatus.getType(tradeDeliverGuide.getDeliverStatus());
					if(guidDeliverStatus == null){
						log.error("导游发单状态不存在,AllocatGno:"+tradeDeliverGuide.getAllocatGno());
						throw new TradeException(TradeReturnCodeEnum.GUIDE_DELIVER_STATUS_NOT_FOUND, "导游发单状态不存在");
					}
					if(GuidDeliverStatus.success.equals(guidDeliverStatus)){
						gpushService.pushPkSuccess(tradeDeliverGuide.getGuideId(), orderBean);
						guidDeliverOrderService.updateGuideDeliverStatus(tradeDeliverGuide, GuidDeliverStatus.success, GuidDeliverStatus.successsend);
						
						if(!DeliverType.assign_GUIDE.equals(deliverType)){
							//指派导游在C端发送
							cpushService.cGuideConfirm(orderBean, false);
							smsservice.guideSMSConfirm(orderBean);
						}
					}else if(GuidDeliverStatus.pkfailed.equals(guidDeliverStatus)){
						DemandDeal demandDeal = DemandDeal.getType(tradeDeliverGuide.getDemandDeal());
						if(DemandDeal.AGREE.equals(demandDeal)){
							gpushService.pushPkFail(tradeDeliverGuide.getGuideId(), orderBean);
						}
						guidDeliverOrderService.updateGuideDeliverStatus(tradeDeliverGuide, GuidDeliverStatus.pkfailed, GuidDeliverStatus.pkfailedPush);
					}
					
				}
			}catch(Exception e){
				log.error("", e);
			}
		}
	}
	
	private List<TradeDeliverGuide> getNeedPushGuide(){
		List<Integer> deliverStatus=new ArrayList<Integer>();
		deliverStatus.add(GuidDeliverStatus.success.value);
		deliverStatus.add(GuidDeliverStatus.pkfailed.value);
		return guidDeliverOrderService.getDeliverGuideByDeliverStatus(deliverStatus);
		
	}

}
