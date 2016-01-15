/**
 * @Author lukangle
 * @2015年12月3日@下午12:04:47
 */
package com.hbc.api.trade.timer.service.deliver.order.track;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hbc.api.trade.TradeFinalStr;
import com.hbc.api.trade.order.enums.alarm.AlarmStatus;
import com.hbc.api.trade.order.enums.alarm.AlarmType;
import com.hbc.api.trade.order.enums.order.OrderStatus;
import com.hbc.api.trade.order.mapping.gen.bean.OrderBean;
import com.hbc.api.trade.order.mapping.gen.bean.TradeAlarm;
import com.hbc.api.trade.order.service.OrderQueryService;
import com.hbc.api.trade.order.service.alarm.OrderAlarmService;
import com.hbc.api.trade.third.push.CPushService;
import com.hbc.api.trade.third.push.GPushService;
@Component
public class NoServiceSender {
	private Logger log = LoggerFactory.getLogger(LeaverTrackSender.class);
	@Autowired
	CPushService cpushService;
	@Autowired
	OrderQueryService orderQueryService;
	@Autowired
	OrderAlarmService orderAlarmService;
	@Autowired
	GPushService gpushService;
	public void sendLeaveOrder(){
		List<TradeAlarm> talrmlist = orderAlarmService.getOrderAlarm(AlarmType.LEAVING);
		 for(TradeAlarm tradeAlarm : talrmlist){
			 try{
				 OrderBean orderBean = orderQueryService.getOrderByNo(tradeAlarm.getOrderNo());
				 OrderStatus orderStatus = OrderStatus.getStatus(orderBean.getOrderStatus());
				 if(!TradeFinalStr.defaultGuideId.equals(orderBean.getGuideId()) && !OrderStatus.GUIDE_ARRIVED.equals(orderStatus)){
					 cpushService.leavePushOrder(tradeAlarm,orderBean);
					 gpushService.pushLeaving(orderBean,tradeAlarm);
					 orderAlarmService.updAlarmSuccess(tradeAlarm, AlarmStatus.SUCCESS, "", "SUCCESS");
				 }else{
					 orderAlarmService.updAlarmSuccess(tradeAlarm, AlarmStatus.FAIL, "导游已经开始服务，无提醒必要", "FAIL");
				 }
			 }catch(Exception e){
				 log.error("",e);
			 }
		 }
	}
}
