/**
 * @Author lukangle
 * @2015年12月2日@下午7:54:28
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
import com.hbc.api.trade.third.sms.SMSService;

@Component
public class LeaverTrackSender {
	private Logger log = LoggerFactory.getLogger(LeaverTrackSender.class);
	@Autowired
	CPushService cpushService;
	@Autowired
	OrderQueryService orderQueryService;
	@Autowired
	OrderAlarmService orderAlarmService;
	@Autowired
	GPushService gpushService;
	@Autowired
	SMSService smsservice;

	public void sendLeaveOrder() {
		List<TradeAlarm> talrmlist = orderAlarmService.getOrderAlarm(AlarmType.LEAVING);
		for (TradeAlarm tradeAlarm : talrmlist) {
			try {
				OrderBean orderBean = orderQueryService.getOrderByNo(tradeAlarm.getOrderNo());
				OrderStatus orderStatus = OrderStatus.getStatus(orderBean.getOrderStatus());
				if (!TradeFinalStr.defaultGuideId.equals(orderBean.getGuideId()) && OrderStatus.PAYSUCCESS.equals(orderStatus)) {
					cpushService.leavePushOrder(tradeAlarm, orderBean);
					gpushService.pushLeaving(orderBean, tradeAlarm);
					/** 临行前 给C端发短信提醒 **/
					smsservice.leaveStartSMSConfirm(orderBean);

					orderAlarmService.updAlarmSuccess(tradeAlarm, AlarmStatus.SUCCESS, "", "SUCCESS");
				}
			} catch (Exception e) {
				log.error("", e);
			}
		}
	}

	public void sendNoServiceOrder() {
		List<TradeAlarm> talrmlist = orderAlarmService.getOrderAlarm(AlarmType.GUIDENOOPREATION);
		for (TradeAlarm tradeAlarm : talrmlist) {
			try {
				OrderBean orderBean = orderQueryService.getOrderByNo(tradeAlarm.getOrderNo());
				if (!TradeFinalStr.defaultGuideId.equals(orderBean.getGuideId()) && OrderStatus.PAYSUCCESS.equals(orderBean.getOrderStatus())) {
					gpushService.pushNoService(orderBean, tradeAlarm);
					smsservice.serviceStartSMS(orderBean);
				}
			} catch (Exception e) {
				log.error("", e);
			}
		}
	}
}
