/**
 * @Author lukangle
 * @2015年12月2日@下午5:38:02
 */
package com.hbc.api.trade.third.push;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hbc.api.trade.bdata.common.HttpClientService;
import com.hbc.api.trade.order.enums.alarm.AlarmStatus;
import com.hbc.api.trade.order.enums.alarm.AlarmType;
import com.hbc.api.trade.order.enums.order.OrderType;
import com.hbc.api.trade.order.enums.third.CPushTemplate;
import com.hbc.api.trade.order.enums.third.NoticeTarget;
import com.hbc.api.trade.order.mapping.gen.bean.OrderBean;
import com.hbc.api.trade.order.mapping.gen.bean.TradeAlarm;
import com.hbc.api.trade.order.service.alarm.OrderAlarmService;
import com.hbc.api.trade.third.PushParam;

@Component
public class CPushService {
	private Logger log = LoggerFactory.getLogger(CPushService.class);
	@Autowired
	HttpClientService httpClientService;
	@Value("${trade.pushUrl}")
	private String pushUrl;
	@Value("${trade.registerUrl}")
	private String registerUrl;

	public void additionalFeeConfirm(OrderBean orderBean, double additionalFee) {
		CPushTemplate pushTemplate = CPushTemplate.ADDITIONAL_FEE_CONFIRM;
		PushParam pushParam = new PushParam();
		try {
			pushParam.setPush_type(NoticeTarget.USER.value);
			pushParam.setPush_objects(new String[] { orderBean.getUserId() });
			pushParam.setTemplate_id(pushTemplate.value);
			pushParam.setParams(new String[] { String.valueOf(additionalFee) });

			Map<String, String> map = new HashMap<>();
			map.put("orderNo", orderBean.getOrderNo());
			map.put("orderType", String.valueOf(orderBean.getOrderType()));
			map.put("type", pushTemplate.code);
			pushParam.setExtras(map);

			String json = JSON.toJSONString(pushParam);
			sendPush(orderBean, json, AlarmType.ADDITIONAL_FEE_CONFIRM);
		} catch (Exception e) {
			log.error("发送push [" + orderBean.getOrderNo() + "] [" + JSON.toJSONString(pushParam) + "] 失败");
		}
	}

	public void cGuideConfirm(OrderBean orderBean, boolean isChangeGuide) {
		PushParam pushParam = new PushParam();
		CPushTemplate pushTemplate = CPushTemplate.GUIDEASSIGN;
		OrderType orderType = OrderType.getType(orderBean.getOrderType());
		pushParam.setParams(new String[] { orderBean.getGuideName(), orderType.name });
		if (isChangeGuide) {
			//更换导游
			pushTemplate = CPushTemplate.GUIDERESIGN;
			pushParam.setParams(new String[] { orderBean.getGuideName() });
		}
		//服务呱呱棒的皇家车导{0}为您提供{1}服务【皇包车】
		try {
			pushParam.setPush_type(NoticeTarget.USER.value);
			pushParam.setPush_objects(new String[] { orderBean.getUserId() });
			pushParam.setTemplate_id(pushTemplate.value);

			Map<String, String> map = new HashMap<>();
			map.put("orderNo", orderBean.getOrderNo());
			map.put("orderType", String.valueOf(orderBean.getOrderType()));
			map.put("type", pushTemplate.code);
			pushParam.setExtras(map);

			String json = JSON.toJSONString(pushParam);
			sendPush(orderBean, json, AlarmType.cGuideConfirm,isChangeGuide);
		} catch (Exception e) {
			log.error("发送push [" + orderBean.getOrderNo() + "] [" + JSON.toJSONString(pushParam) + "] 失败");
		}
	}

	public void canclePushOrder(OrderBean orderBean) {
		CPushTemplate pushTemplate = CPushTemplate.CANCLEORDER;
		PushParam pushParam = new PushParam();
		//服务呱呱棒的皇家车导{0}为您提供{1}服务【皇包车】
		try {
			pushParam.setPush_type(NoticeTarget.USER.value);
			pushParam.setPush_objects(new String[] { orderBean.getUserId() });
			pushParam.setTemplate_id(pushTemplate.value);

			OrderType orderType = OrderType.getType(orderBean.getOrderType());
			pushParam.setParams(new String[] { orderType.name });

			Map<String, String> map = new HashMap<>();
			map.put("orderNo", orderBean.getOrderNo());
			map.put("orderType", String.valueOf(orderBean.getOrderType()));
			map.put("type", pushTemplate.code);
			pushParam.setExtras(map);

			String json = JSON.toJSONString(pushParam);
			sendPush(orderBean, json, AlarmType.canclePushOrder);
		} catch (Exception e) {
			log.error("发送push [" + orderBean.getOrderNo() + "] [" + JSON.toJSONString(pushParam) + "] 失败");
		}
	}

	@Autowired
	OrderAlarmService orderAlarmService;

	public void leavePushOrder(TradeAlarm tradeAlarm, OrderBean orderBean) {
		CPushTemplate pushTemplate = CPushTemplate.BEFORE24;
		PushParam pushParam = new PushParam();
		//服务呱呱棒的皇家车导{0}为您提供{1}服务【皇包车】
		try {
			pushParam.setPush_type(NoticeTarget.USER.value);
			pushParam.setPush_objects(new String[] { orderBean.getUserId() });
			pushParam.setTemplate_id(pushTemplate.value);

			Map<String, String> map = new HashMap<>();
			map.put("orderNo", orderBean.getOrderNo());
			map.put("orderType", String.valueOf(orderBean.getOrderType()));
			map.put("type", pushTemplate.code);
			pushParam.setExtras(map);

			String json = JSON.toJSONString(pushParam);
			sendPush(orderBean, json, AlarmType.leavePushOrder);
		} catch (Exception e) {
			log.error("发送push [" + orderBean.getOrderNo() + "] [" + JSON.toJSONString(pushParam) + "] 失败", e);
		}
	}

	public boolean sendPush(String pushUrl, String data) throws IOException {
		try {
			String rspData = httpClientService.sendReqPost(pushUrl, data);

			JSONObject jobj = JSON.parseObject(rspData);
			if (200 == jobj.getInteger("status")) {
				log.info("发送push给 [" + pushUrl + "] [" + data + "]");
				return true;
			} else {
				log.error("发送push给 [" + pushUrl + "] [" + data + "] 失败，失败信息[" + rspData + "]");
			}
		} catch (Exception e) {
			log.error("发送push给 [" + pushUrl + "] [" + data + "] 失败，失败信息[" + e.toString() + "]");
		}
		return false;
	}

	private boolean sendPush(OrderBean orderBean, String data, AlarmType alarmType) {
		boolean result = sendPush(orderBean,data,alarmType,false);
		return result;
	}
	
	private boolean sendPush(OrderBean orderBean, String data, AlarmType alarmType,boolean isCount) {
		boolean result = false;
		log.info("sending push for order: {} data: {}", orderBean.getOrderNo(), data);
		int count = orderAlarmService.countOrderAlarmByOrderNo(orderBean.getOrderNo(), alarmType, AlarmStatus.SUCCESS);
		if (count <= 100 || isCount) {
			try {
				String rspData = httpClientService.sendReqPost(pushUrl, data);
				JSONObject jobj = JSON.parseObject(rspData);
				if (200 == jobj.getInteger("status")) {
					log.info("发送push给 [" + pushUrl + "] [" + data + "]");
					orderAlarmService.insertAlarmLog(orderBean, alarmType, AlarmStatus.SUCCESS, data, rspData);
					return true;
				} else {
					orderAlarmService.insertAlarmLog(orderBean, alarmType, AlarmStatus.FAIL, data, rspData);
					log.error("发送push给 [" + pushUrl + "] [" + data + "] 失败，失败信息[" + rspData + "]");
				}

			} catch (Exception e) {
				log.error("", e);
			}
		} else {
			log.error("订单号为:{} 的PUSH已发送过！", orderBean.getOrderNo());
		}
		return result;
	}
}
