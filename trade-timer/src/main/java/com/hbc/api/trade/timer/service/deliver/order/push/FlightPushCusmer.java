/**
 * @Author lukangle
 * @2015年12月3日@下午5:33:19
 */
package com.hbc.api.trade.timer.service.deliver.order.push;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hbc.api.trade.order.enums.FlightStatus;
import com.hbc.api.trade.order.mapping.gen.bean.OrderBean;
import com.hbc.api.trade.order.service.OrderQueryService;
import com.hbc.api.trade.order.service.OrderTrackService;
import com.hbc.api.trade.third.push.GPushService;
import com.hbc.api.trade.timer.kafka.KafkaMessageReceiver;

public class FlightPushCusmer extends KafkaMessageReceiver {
	private Logger log = LoggerFactory.getLogger(FlightPushCusmer.class);
	@Autowired
	OrderQueryService orderQueryService;
	@Autowired
	GPushService gpushService;
	@Autowired
	OrderTrackService orderTrackService;
	@Override
	public void handleMessage(String msg) {
		// {"fromFlightStatus":"备降","toFlightStatus":"返航","flightId":"11532800000002"}
		//
		log.info("开始处理航班注册信息：[" + msg + "]");
		JSONObject jsonobject = JSON.parseObject(msg);
		FlightStatus tostatus = FlightStatus.getType(jsonobject.getString("toFlightStatus"));
		String flightRegistId = jsonobject.getString("flightId");

		List<OrderBean> orderList = orderQueryService.getOrderByFilght(flightRegistId);

		for (OrderBean orderBean : orderList) {
			try{
				if (FlightStatus.STATUS_TAKE_OFF.equals(tostatus)) {
					gpushService.pushFlightFly(orderBean);
					orderTrackService.flightFly(orderBean.getOrderNo());
				} else if (FlightStatus.STATUS_ARRIVE.equals(tostatus)) {
					gpushService.pushFlightArrive(orderBean);
					orderTrackService.flightArrive(orderBean.getOrderNo());
				} else if (FlightStatus.STATUS_DELAY.equals(tostatus)) {
					gpushService.pushFlightDelay(orderBean);
					orderTrackService.flightDelay(orderBean.getOrderNo());
				} else if (FlightStatus.STATUS_CANCEL.equals(tostatus)) {
					gpushService.pushFlightCancle(orderBean);
					orderTrackService.flightCancle(orderBean.getOrderNo());
				} else if (FlightStatus.STATUS_ALTERNATE_ARRIVE.equals(tostatus)) {
					gpushService.pushFlightALTERNATEArrive(orderBean);
					orderTrackService.flightALTERNATEArrive(orderBean.getOrderNo());
				} else if (FlightStatus.STATUS_BACK.equals(tostatus)) {
					gpushService.pushFlightStatusBack(orderBean);
					orderTrackService.flightStatusBack(orderBean.getOrderNo());
				} else {
					log.error(msg);
				}
			}catch(Exception e){
				log.error("", e);
			}
		}

		/**
		 * STATUS_TAKE_OFF("起飞"), STATUS_ARRIVE("到达"), STATUS_DELAY("延误"),
		 * STATUS_CANCEL("取消"), STATUS_ALTERNATE_ARRIVE("备降"),
		 * STATUS_BACK("返航"),
		 */
	}

}
