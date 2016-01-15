/**
 * @Author lukangle
 * @2015年11月13日@下午3:23:04
 */
package com.hbc.api.trade.third.push;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hbc.api.trade.TradeFinalStr;
import com.hbc.api.trade.bdata.common.HttpClientService;
import com.hbc.api.trade.bdata.common.util.TimeConverter;
import com.hbc.api.trade.order.enums.alarm.AlarmStatus;
import com.hbc.api.trade.order.enums.alarm.AlarmType;
import com.hbc.api.trade.order.enums.order.OrderType;
import com.hbc.api.trade.order.enums.third.NoticeTarget;
import com.hbc.api.trade.order.enums.third.PushTemplate;
import com.hbc.api.trade.order.mapping.gen.bean.OrderBean;
import com.hbc.api.trade.order.mapping.gen.bean.TradeAlarm;
import com.hbc.api.trade.order.service.OrderServiceTime;
import com.hbc.api.trade.order.service.alarm.OrderAlarmService;
import com.hbc.api.trade.third.PushParam;

@Service
public class GPushService {
	private Logger log = LoggerFactory.getLogger(GPushService.class);
	@Autowired
	HttpClientService httpClientService;
	@Value("${trade.pushUrl}")
	private String pushUrl;
	@Value("${trade.registerUrl}")
	private String registerUrl;
	@Autowired
	OrderServiceTime orderServiceTime;

	public void pushFlightFly(OrderBean orderBean) {
		PushParam pushParam = new PushParam();
		pushParam.setPush_type(NoticeTarget.GUIDE.value);
		pushParam.setPush_objects(new String[] { orderBean.getGuideId() });
		pushParam.setTemplate_id(PushTemplate.USER_FLY_PUSH.getTemplateId());

		SimpleDateFormat format = new SimpleDateFormat("M月d日");
		Date date = orderServiceTime.getServiceCityCurTime(orderBean.getServiceCityId());

		pushParam.setParams(new String[] { orderBean.getUserName(), orderBean.getFlightNo(), format.format(date) });

		Map<String, String> map = new HashMap<>();
		map.put("orderNo", orderBean.getOrderNo());
		map.put("orderType", String.valueOf(orderBean.getOrderType()));
		map.put("type", PushTemplate.USER_FLY_PUSH.getType());
		pushParam.setExtras(map);
		noticePushCenter(orderBean, pushParam, AlarmType.PUSH_LOG_PAYSUCCESS);
	}

	/**
	 * 航班到达通知
	 * 
	 * @param orderBean
	 */
	public void pushFlightArrive(OrderBean orderBean) {
		PushParam pushParam = new PushParam();
		pushParam.setPush_type(NoticeTarget.GUIDE.value);
		pushParam.setPush_objects(new String[] { orderBean.getGuideId() });
		pushParam.setTemplate_id(PushTemplate.FLIGHT_ARRIVER_PUSH.getTemplateId());

		SimpleDateFormat format = new SimpleDateFormat("M月d日");
		Date date = orderServiceTime.getServiceCityCurTime(orderBean.getServiceCityId());

		pushParam.setParams(new String[] { orderBean.getUserName(), orderBean.getFlightNo(), format.format(date) });

		Map<String, String> map = new HashMap<>();
		map.put("orderNo", orderBean.getOrderNo());
		map.put("orderType", String.valueOf(orderBean.getOrderType()));
		map.put("type", PushTemplate.FLIGHT_ARRIVER_PUSH.getType());
		pushParam.setExtras(map);
		noticePushCenter(orderBean, pushParam, AlarmType.pushFlightArrive);
	}

	/**
	 * 延误
	 * 
	 * @param orderBean
	 */
	public void pushFlightDelay(OrderBean orderBean) {
		PushParam pushParam = new PushParam();
		pushParam.setPush_type(NoticeTarget.GUIDE.value);
		pushParam.setPush_objects(new String[] { orderBean.getGuideId() });
		pushParam.setTemplate_id(PushTemplate.USER_DELAY_PUSH.getTemplateId());

		pushParam.setParams(new String[] { orderBean.getUserName(), orderBean.getFlightNo() });

		Map<String, String> map = new HashMap<>();
		map.put("orderNo", orderBean.getOrderNo());
		map.put("orderType", String.valueOf(orderBean.getOrderType()));
		map.put("type", PushTemplate.USER_DELAY_PUSH.getType());
		pushParam.setExtras(map);
		noticePushCenter(orderBean, pushParam, AlarmType.pushFlightDelay);
	}

	public void pushFlightCancle(OrderBean orderBean) {
		PushParam pushParam = new PushParam();
		pushParam.setPush_type(NoticeTarget.GUIDE.value);
		pushParam.setPush_objects(new String[] { orderBean.getGuideId() });
		pushParam.setTemplate_id(PushTemplate.USER_FLIGHT_CACLE.getTemplateId());

		pushParam.setParams(new String[] { orderBean.getUserName(), orderBean.getFlightNo() });

		Map<String, String> map = new HashMap<>();
		map.put("orderNo", orderBean.getOrderNo());
		map.put("orderType", String.valueOf(orderBean.getOrderType()));
		map.put("type", PushTemplate.USER_FLIGHT_CACLE.getType());
		pushParam.setExtras(map);
		noticePushCenter(orderBean, pushParam, AlarmType.pushFlightCancle);
	}

	public void pushFlightALTERNATEArrive(OrderBean orderBean) {
		PushParam pushParam = new PushParam();
		pushParam.setPush_type(NoticeTarget.GUIDE.value);
		pushParam.setPush_objects(new String[] { orderBean.getGuideId() });
		pushParam.setTemplate_id(PushTemplate.POJIANG_FLIGHT_USER.getTemplateId());

		pushParam.setParams(new String[] { orderBean.getUserName(), orderBean.getFlightNo() });

		Map<String, String> map = new HashMap<>();
		map.put("orderNo", orderBean.getOrderNo());
		map.put("orderType", String.valueOf(orderBean.getOrderType()));
		map.put("type", PushTemplate.POJIANG_FLIGHT_USER.getType());
		pushParam.setExtras(map);
		noticePushCenter(orderBean, pushParam, AlarmType.pushFlightCancle);
	}

	public void pushFlightStatusBack(OrderBean orderBean) {
		PushParam pushParam = new PushParam();
		pushParam.setPush_type(NoticeTarget.GUIDE.value);
		pushParam.setPush_objects(new String[] { orderBean.getGuideId() });
		pushParam.setTemplate_id(PushTemplate.USER_FANHAGN_FLIGHT.getTemplateId());

		pushParam.setParams(new String[] { orderBean.getUserName(), orderBean.getFlightNo() });

		Map<String, String> map = new HashMap<>();
		map.put("orderNo", orderBean.getOrderNo());
		map.put("orderType", String.valueOf(orderBean.getOrderType()));
		map.put("type", PushTemplate.USER_FANHAGN_FLIGHT.getType());
		pushParam.setExtras(map);
		noticePushCenter(orderBean, pushParam, AlarmType.pushFlightStatusBack);
	}

	/**
	 * 新订单通知
	 * 
	 * @param userIds
	 * @param orderBean
	 * @param pushTarget
	 */
	public boolean pushNewOrder(List<String> targetIds, OrderBean orderBean) {
		PushParam pushParam = new PushParam();
		pushParam.setPush_type(NoticeTarget.GUIDE.value);
		pushParam.setPush_objects(targetIds.toArray(new String[targetIds.size()]));
		pushParam.setTitle("您有1个新订单");
		pushParam.setTemplate_id(PushTemplate.NEW_ORDER_PUSH.getTemplateId());

		SimpleDateFormat format = new SimpleDateFormat("M月d日");
		String serviceName = format.format(orderBean.getServiceTime());
		serviceName += OrderType.getType(orderBean.getOrderType()).name;

		pushParam.setParams(new String[] { serviceName });

		Map<String, String> map = new HashMap<>();
		map.put("orderNo", orderBean.getOrderNo());
		map.put("orderType", String.valueOf(orderBean.getOrderType()));
		map.put("type", PushTemplate.NEW_ORDER_PUSH.getType());
		pushParam.setExtras(map);
		return noticePushCenter(orderBean, pushParam, AlarmType.pushNewOrder);
	}

	/**
	 * PK 成功
	 * 
	 * @param guideId
	 * @param orderBean
	 * @param pushTarget
	 * @return
	 */
	public boolean pushPkSuccess(String guideId, OrderBean orderBean) {
		PushParam pushParam = new PushParam();
		pushParam.setPush_type(NoticeTarget.GUIDE.value);
		pushParam.setPush_objects(new String[] { guideId });
		pushParam.setTitle("接单成功");
		pushParam.setTemplate_id(PushTemplate.SUCCESS_ORDER_PUSH.getTemplateId());

		pushParam.setParams(new String[] {});

		Map<String, String> map = new HashMap<>();
		map.put("orderNo", orderBean.getOrderNo());
		map.put("orderType", String.valueOf(orderBean.getOrderType()));
		map.put("type", PushTemplate.SUCCESS_ORDER_PUSH.getType());
		pushParam.setExtras(map);
		
		log.info("发送接单成功PUSH 【"+JSON.toJSONString(pushParam)+"】");
		return noticePushCenter(orderBean, pushParam, AlarmType.pushNewOrder);
	}
	
	public void cancleOrderPush(OrderBean orderBean) {
		if(!TradeFinalStr.defaultGuideId.equals(orderBean.getGuideId())){
			PushParam pushParam = new PushParam();
			pushParam.setPush_type(NoticeTarget.GUIDE.value);
			pushParam.setPush_objects(new String[] { orderBean.getGuideId() });
			pushParam.setTemplate_id(PushTemplate.CANSEL_ORDER_PUSH.getTemplateId());

			String timestr = TimeConverter.formatDate(orderBean.getCancelTime());
			if(timestr == null){
				timestr="";
			}
			pushParam.setParams(new String[] { timestr,orderBean.getUserName() });

			Map<String, String> map = new HashMap<>();
			map.put("orderNo", orderBean.getOrderNo());
			map.put("orderType", String.valueOf(orderBean.getOrderType()));
			map.put("type", PushTemplate.CANSEL_ORDER_PUSH.getType());
			pushParam.setExtras(map);
			noticePushCenter(orderBean, pushParam, AlarmType.cancleGOrder);
		}
	}

	/**
	 * PK 失败
	 * 
	 * @param guideIds
	 * @param orderBean
	 * @param pushTarget
	 * @return
	 */
	public boolean pushPkFail(String guideId, OrderBean orderBean) {
		PushParam pushParam = new PushParam();
		pushParam.setPush_type(NoticeTarget.GUIDE.value);
		pushParam.setPush_objects(new String[] { guideId });
		pushParam.setTitle("接单失败");
		pushParam.setTemplate_id(PushTemplate.PKFAILED_PUSH.getTemplateId());

		pushParam.setParams(new String[] {});
		Map<String, String> map = new HashMap<>();
		map.put("orderNo", orderBean.getOrderNo());
		map.put("orderType", String.valueOf(orderBean.getOrderType()));
		map.put("type", PushTemplate.PKFAILED_PUSH.getType());
		pushParam.setExtras(map);
		return noticePushCenter(orderBean, pushParam, AlarmType.pushPkFail);
	}

	@Autowired
	OrderAlarmService orderAlarmService;

	/**
	 * 临行前提醒
	 * 
	 * @param guideIds
	 * @param orderBean
	 * @param pushTarget
	 * @return
	 */
	public void pushLeaving(OrderBean orderBean, TradeAlarm tradeAlarm) {
		PushParam pushParam = new PushParam();
		pushParam.setPush_type(NoticeTarget.GUIDE.value);
		pushParam.setPush_objects(new String[] { orderBean.getGuideId() });
		pushParam.setTitle("临行前提醒");
		pushParam.setTemplate_id(PushTemplate.GUIDE_PICKUP_USER.getTemplateId());

		SimpleDateFormat format = new SimpleDateFormat("M月d日 HH:mm");
		String serviceTime = format.format(orderBean.getServiceTime());

		pushParam.setParams(new String[] { serviceTime, OrderType.getType(orderBean.getOrderType()).name, orderBean.getUserName() });

		Map<String, String> map = new HashMap<>();
		map.put("orderNo", orderBean.getOrderNo());
		map.put("orderType", String.valueOf(orderBean.getOrderType()));
		map.put("type", PushTemplate.GUIDE_PICKUP_USER.getType());
		pushParam.setExtras(map);
		noticePushCenter(orderBean, pushParam, AlarmType.pushLeaving);
	}

	
	public void pushNoService(OrderBean orderBean, TradeAlarm tradeAlarm) {
		PushParam pushParam = new PushParam();
		pushParam.setPush_type(NoticeTarget.GUIDE.value);
		pushParam.setPush_objects(new String[] { orderBean.getGuideId() });
		pushParam.setTemplate_id(PushTemplate.SERVICE_TIME_ARRICE.getTemplateId());

		pushParam.setParams(new String[] { OrderType.getType(orderBean.getOrderType()).name });

		Map<String, String> map = new HashMap<>();
		map.put("orderNo", orderBean.getOrderNo());
		map.put("orderType", String.valueOf(orderBean.getOrderType()));
		map.put("type", PushTemplate.SERVICE_TIME_ARRICE.getType());
		pushParam.setExtras(map);
		boolean isSucc = noticePushCenter(pushParam);
		if (isSucc) {
			orderAlarmService.updAlarmSuccess(tradeAlarm, AlarmStatus.SUCCESS, JSON.toJSONString(pushParam), "FAIL");
		} else {
			orderAlarmService.updAlarmSuccess(tradeAlarm, AlarmStatus.FAIL, JSON.toJSONString(pushParam), "FAIL");
		}
	}

	public void pushRewardServce(OrderBean orderBean) {
		if (orderBean.getGuideId() != null && !TradeFinalStr.defaultGuideId.equals(orderBean.getGuideId())) {
			PushParam pushParam = new PushParam();
			pushParam.setPush_type(NoticeTarget.GUIDE.value);
			pushParam.setPush_objects(new String[] { orderBean.getGuideId() });
			pushParam.setTemplate_id(PushTemplate.REWORD_PUSH.getTemplateId());

			pushParam.setParams(new String[] { orderBean.getOrderNo() });

			Map<String, String> map = new HashMap<>();
			map.put("orderNo", orderBean.getOrderNo());
			map.put("orderType", String.valueOf(orderBean.getOrderType()));
			map.put("type", PushTemplate.REWORD_PUSH.getType());
			pushParam.setExtras(map);
			noticePushCenter(orderBean, pushParam, AlarmType.pushRewardServce);
		}
	}

	public void pushEditOrder(OrderBean orderBean) {
		PushParam pushParam = new PushParam();
		pushParam.setPush_type(NoticeTarget.GUIDE.value);
		pushParam.setPush_objects(new String[] { orderBean.getGuideId() });
		pushParam.setTemplate_id(PushTemplate.ORDER_UPDATE_PUSH.getTemplateId());

		SimpleDateFormat format = new SimpleDateFormat("M月d日 HH:mm");
		String serviceTime = format.format(orderBean.getServiceTime());
		pushParam.setParams(new String[] { serviceTime, OrderType.getType(orderBean.getOrderType()).name });
		Map<String, String> map = new HashMap<>();
		map.put("orderNo", orderBean.getOrderNo());
		map.put("orderType", String.valueOf(orderBean.getOrderType()));
		map.put("type", PushTemplate.ORDER_UPDATE_PUSH.getType());
		pushParam.setExtras(map);
		noticePushCenter(orderBean, pushParam, AlarmType.pushEditOrder);
	}

	/**
	 * 通知PUSH中心
	 * 
	 * @param pushParam
	 */
	private boolean noticePushCenter(PushParam pushParam) {
		boolean result = false;
		String json = JSON.toJSONString(pushParam);
		try {
			String rsp = httpClientService.sendReqPost(pushUrl, json);
			if (!rsp.contains("200")) {
				log.error("push message failed rsp is [" + rsp + "]");
				rsp = httpClientService.sendReqPost(pushUrl, json);
				if (!rsp.contains("200")) {
					log.error("push message failed rsp is [" + rsp + "]");
				}
			}
			result = true;
		} catch (Exception e) {
			log.error("", e);
		}
		return result;
	}

	private boolean noticePushCenter(OrderBean orderBean, PushParam pushParam, AlarmType alarmType) {
		int count = orderAlarmService.countOrderAlarmByOrderNo(orderBean.getOrderNo(), alarmType, AlarmStatus.SUCCESS);
		boolean result = false;
		if (count <= 100) {
			String json = JSON.toJSONString(pushParam);
			try {
				String rsp = httpClientService.sendReqPost(pushUrl, json);
				if (!rsp.contains("200")) {
					log.error("push message failed rsp is [" + rsp + "]");
					rsp = httpClientService.sendReqPost(pushUrl, json);
					if (!rsp.contains("200")) {
						log.error("push message failed rsp is [" + rsp + "]");
						orderAlarmService.insertAlarmLog(orderBean, alarmType, AlarmStatus.FAIL, json, rsp);
					} else {
						orderAlarmService.insertAlarmLog(orderBean, alarmType, AlarmStatus.SUCCESS, json, rsp);
						result = true;
					}
				}else{
					result = true;
					orderAlarmService.insertAlarmLog(orderBean, alarmType, AlarmStatus.SUCCESS, json, rsp);
				}
			} catch (Exception e) {
				orderAlarmService.insertAlarmLog(orderBean, alarmType, AlarmStatus.FAIL, json, "");
				log.error("", e);
			}
		} else {
			orderAlarmService.insertAlarmLog(orderBean, alarmType, AlarmStatus.FAIL, "push 已经发送", "push 已经发送");
			log.error(orderBean.getOrderNo() + " " + alarmType.name + " push 已经发送");
		}
		return result;
	}

	/**
	 * 注册航班
	 * 
	 * @param orderBean
	 * @return
	 */
	public String registerFlight(OrderBean orderBean) {
		String fid = "";
		if (OrderType.PICKUPORDER.value != orderBean.getOrderType().intValue()) {
			return fid;
		}

		if (orderBean.getFlightAirportCode() == null || orderBean.getFlightAirportCode().trim().length() == 0 || orderBean.getFlightDestCode() == null
				|| orderBean.getFlightDestCode().trim().length() == 0 || orderBean.getFlightFlyTime() == null || orderBean.getFlightNo() == null || orderBean.getFlightNo().trim().length() == 0) {
			return fid;
		}
		// RegistFlightParam param = new RegistFlightParam();
		// param.setDep(orderBean.getFlightAirportCode().trim());
		// param.setArr(orderBean.getFlightDestCode().trim());
		// SimpleDateFormat sFormat = new SimpleDateFormat("yyyy-MM-dd");
		// param.setDate(sFormat.format(orderBean.getFlightFlyTime()));
		// param.setFnum(orderBean.getFlightNo());

		Map<String, String> paramMap = new HashMap<String, String>();

		paramMap.put("fnum", orderBean.getFlightNo());
		paramMap.put("dep", orderBean.getFlightAirportCode().trim());
		paramMap.put("arr", orderBean.getFlightDestCode().trim());
		SimpleDateFormat sFormat = new SimpleDateFormat("yyyy-MM-dd");
		paramMap.put("date", sFormat.format(orderBean.getFlightFlyTime()));

		// String json = JSON.toJSONString(param);
		try {
			String rsp = httpClientService.sendPostMapReq(registerUrl, paramMap);
			JSONObject object = JSON.parseObject(rsp);
			int status = object.getInteger("status");
			if (status != 200) {
				rsp = httpClientService.sendPostMapReq(registerUrl, paramMap);
				object = JSON.parseObject(rsp);
				status = object.getInteger("status");
				if (status != 200) {
					log.error("register message failed rsp is [" + rsp + "]");
				} else {
					JSONObject data = object.getJSONObject("data");
					fid = data.getString("flightId");
					if (fid == null) {
						fid = "";
					}
				}
			} else {
				JSONObject data = object.getJSONObject("data");
				fid = data.getString("flightId");
				if (fid == null) {
					fid = "";
				}
			}
		} catch (Exception e) {
			log.error("", e);
		}

		return fid;
	}
}
