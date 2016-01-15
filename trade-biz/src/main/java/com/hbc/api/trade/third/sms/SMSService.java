/**
 * @Author lukangle
 * @2015年12月3日@下午8:41:09
 */
package com.hbc.api.trade.third.sms;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.hbc.api.trade.bdata.common.HttpClientService;
import com.hbc.api.trade.bdata.common.util.TimeConverter;
import com.hbc.api.trade.bdata.mapper.channel.gen.bean.ChannelAgent;
import com.hbc.api.trade.order.enums.alarm.AlarmStatus;
import com.hbc.api.trade.order.enums.alarm.AlarmType;
import com.hbc.api.trade.order.enums.order.AgentCategory;
import com.hbc.api.trade.order.enums.order.OrderSource;
import com.hbc.api.trade.order.enums.order.OrderType;
import com.hbc.api.trade.order.enums.third.SMSChannel;
import com.hbc.api.trade.order.enums.third.SmsTemplate;
import com.hbc.api.trade.order.mapping.gen.bean.OrderBean;
import com.hbc.api.trade.order.service.OrderServiceTime;
import com.hbc.api.trade.order.service.alarm.OrderAlarmService;
import com.hbc.api.trade.third.ChannelAgentService;

@Component
public class SMSService {
	private Logger log = LoggerFactory.getLogger(SMSService.class);
	@Autowired
	HttpClientService httpClientService;
	@Autowired
	ChannelAgentService channelAgentService;
	@Value("${trade.smsUrl}")
	private String smsUrl;

	private static String smsTrade = "trade";

	/**
	 * 支付成功
	 * 
	 * @param orderBean
	 */
	public boolean paySuccessSms(OrderBean orderBean) {
		SMSChannel smsChannel = this.getSmsChannel(orderBean);
		SmsParam smsMessageVo = new SmsParam();

		smsMessageVo.setSys_name(smsTrade);
		smsMessageVo.setArea_code(orderBean.getUserAreaCode1());
		smsMessageVo.setMobile(orderBean.getUserMobile1());
		OrderType orderType = OrderType.getType(orderBean.getOrderType());

		if (SMSChannel.APP.equals(smsChannel)) {
			smsMessageVo.setTemplate_id(SmsTemplate.PAYSUCCESS_APP.getTemplateId());
			smsMessageVo.setParams(new String[] { orderType.name, orderBean.getOrderNo()});
		} else if (SMSChannel.fenxiao.equals(smsChannel)) {
			smsMessageVo.setTemplate_id(SmsTemplate.PAYSUCCESS_FENXIAO.getTemplateId());
			smsMessageVo.setParams(new String[] { orderBean.getAgentName(),orderType.name, orderBean.getOrderNo() });
		} else if (SMSChannel.zhixiao.equals(smsChannel)) {
			smsMessageVo.setTemplate_id(SmsTemplate.PAYSUCCESS_ZHIXIAO.getTemplateId());
			smsMessageVo.setParams(new String[] { orderBean.getAgentName(), orderType.name, orderBean.getOrderNo()});
		} else {
			log.error(orderBean.getOrderNo() + " 既不属于 分销 也不属于直销 或者app，检查[" + orderBean.getAgentId() + "] [" + orderBean.getOrderChannel() + "]");
			return Boolean.FALSE;
		}
		return noticeSmsCenter(orderBean, smsMessageVo, AlarmType.SMSPAYSUCCESS);
	}

	public boolean guideSMSConfirm(OrderBean orderBean) {
		SMSChannel smsChannel = getSmsChannel(orderBean);

		SmsParam smsMessageVo = new SmsParam();

		smsMessageVo.setSys_name(smsTrade);
		smsMessageVo.setArea_code(orderBean.getUserAreaCode1());
		smsMessageVo.setMobile(orderBean.getUserMobile1());
		OrderType orderType = OrderType.getType(orderBean.getOrderType());
		if (SMSChannel.APP.equals(smsChannel)) {
			smsMessageVo.setTemplate_id(SmsTemplate.GUIDECONFIRM_APP.getTemplateId());
			smsMessageVo.setParams(new String[] { orderType.name, orderBean.getOrderNo(), orderBean.getGuideName(), orderBean.getGuideAreaCode() + orderBean.getGuideMobile() });
		} else if (SMSChannel.fenxiao.equals(smsChannel)) {
			smsMessageVo.setTemplate_id(SmsTemplate.GUIDECONFIRM_FENXIAO.getTemplateId());
			smsMessageVo.setParams(new String[] { orderType.name, orderBean.getOrderNo(), orderBean.getGuideName(), orderBean.getGuideAreaCode() + orderBean.getGuideMobile() });
		} else if (SMSChannel.zhixiao.equals(smsChannel)) {
			smsMessageVo.setTemplate_id(SmsTemplate.GUIDECONFIRM_ZHIXIAO.getTemplateId());
			smsMessageVo.setParams(new String[] { orderType.name, orderBean.getOrderNo(), orderBean.getGuideName(), orderBean.getGuideAreaCode() + orderBean.getGuideMobile() });
		} else {
			log.error(orderBean.getOrderNo() + " 既不属于 分销 也不属于直销 或者app，检查[" + orderBean.getAgentId() + "] [" + orderBean.getOrderChannel() + "]");
			return Boolean.FALSE;
		}
		return noticeSmsCenter(orderBean, smsMessageVo, AlarmType.guideSMSConfirm);
	}

	@Autowired
	OrderServiceTime orderServiceTime;
	@Autowired
	OrderAlarmService orderAlarmService;

	/**
	 * 临行前给G端发送短信
	 * 
	 * @param orderBean
	 * @return
	 */
	public boolean gleaverStartSMS(OrderBean orderBean) {
		SmsParam smsMessageVo = new SmsParam();

		smsMessageVo.setSys_name(smsTrade);
		smsMessageVo.setArea_code(orderBean.getGuideAreaCode());
		smsMessageVo.setMobile(orderBean.getGuideMobile());

		smsMessageVo.setTemplate_id(SmsTemplate.GLEAVE_SMS.getTemplateId());

		Date serviceTime = orderBean.getServiceTime();
		OrderType orderType = OrderType.getType(orderBean.getOrderType());
		smsMessageVo.setParams(new String[] { TimeConverter.formatDate(serviceTime), orderType.name, orderBean.getUserName() });
		return noticeSmsCenter(orderBean, smsMessageVo, AlarmType.gleaverStartSMS);
	}

	/**
	 * 开始服务提醒
	 * 
	 * @param orderBean
	 * @return
	 */
	public boolean serviceStartSMS(OrderBean orderBean) {
		SmsParam smsMessageVo = new SmsParam();

		smsMessageVo.setSys_name(smsTrade);
		smsMessageVo.setArea_code(orderBean.getGuideAreaCode());
		smsMessageVo.setMobile(orderBean.getGuideMobile());

		smsMessageVo.setTemplate_id(SmsTemplate.GSERVICE_START_SMS.getTemplateId());

		OrderType orderType = OrderType.getType(orderBean.getOrderType());
		smsMessageVo.setParams(new String[] { orderType.name });
		return noticeSmsCenter(orderBean, smsMessageVo, AlarmType.gleaverStartSMS);
	}

	/**
	 * 临行前 C端发送短信
	 * 
	 * @param orderBean
	 */
	public boolean leaveStartSMSConfirm(OrderBean orderBean) {
		SMSChannel smsChannel = getSmsChannel(orderBean);
		SmsParam smsMessageVo = new SmsParam();

		smsMessageVo.setSys_name(smsTrade);
		smsMessageVo.setArea_code(orderBean.getUserAreaCode1());
		smsMessageVo.setMobile(orderBean.getUserMobile1());
		OrderType orderType = OrderType.getType(orderBean.getOrderType());
		Date serviceTime = orderBean.getServiceTime();

		Date curTime = orderServiceTime.getServiceCityCurTime(orderBean.getServiceCityId());
		long hour = (serviceTime.getTime() - curTime.getTime()) / (60 * 60 * 1000);

		if (hour >= 23 && hour <= 24) {
			if ("泰国".equalsIgnoreCase(orderBean.getServiceCountryName()) && OrderType.PICKUPORDER.equals(orderType)) {
				// 待完成
				// 素万那普国际机场4号门内侧铁栏杆处/廊曼机场2号门/普吉国际机场出口左转TMB旁，taxi接机牌候客区/清迈国际机场8号门
				String msgStr = taiguoGetMsgInfo(orderBean.getFlightDestCode());
				smsMessageVo.setParams(new String[] { orderType.name, hour + "", orderBean.getGuideName(), msgStr });
				if (SMSChannel.APP.equals(smsChannel)) {
					smsMessageVo.setTemplate_id(SmsTemplate.LININ_MORE_TAIGUO_APP.getTemplateId());
					return noticeSmsCenter(orderBean, smsMessageVo, AlarmType.SMSLOGKEAVING);
				} else if (SMSChannel.fenxiao.equals(smsChannel)) {
					smsMessageVo.setTemplate_id(SmsTemplate.LININ_MORE_TAIGUO_FENXIAO.getTemplateId());
					return noticeSmsCenter(orderBean, smsMessageVo, AlarmType.SMSLOGKEAVING);
				} else if (SMSChannel.zhixiao.equals(smsChannel)) {
					smsMessageVo.setTemplate_id(SmsTemplate.LININ_MORE_TAIGUO_ZHIXIAO.getTemplateId());
					return noticeSmsCenter(orderBean, smsMessageVo, AlarmType.SMSLOGKEAVING);
				} else {
					log.error(orderBean.getOrderNo() + " 既不属于 分销 也不属于直销 或者app，检查[" + orderBean.getAgentId() + "] [" + orderBean.getOrderChannel() + "]");
				}
			} else {
				if (SMSChannel.APP.equals(smsChannel)) {
					smsMessageVo.setTemplate_id(SmsTemplate.LINXIN_MORE_APP.getTemplateId());
					smsMessageVo.setParams(new String[] { orderType.name, hour + ""});
					return noticeSmsCenter(orderBean, smsMessageVo, AlarmType.SMSLOGKEAVING);
				} else if (SMSChannel.fenxiao.equals(smsChannel)) {
					smsMessageVo.setTemplate_id(SmsTemplate.LINXIN_MORE_FENXIAO.getTemplateId());
					smsMessageVo.setParams(new String[] { orderType.name, hour + "", orderBean.getGuideAreaCode() + orderBean.getGuideMobile()});
					return noticeSmsCenter(orderBean, smsMessageVo, AlarmType.SMSLOGKEAVING);
				} else if (SMSChannel.zhixiao.equals(smsChannel)) {
					smsMessageVo.setTemplate_id(SmsTemplate.LINXIN_MORE_ZHIXIAO.getTemplateId());
					smsMessageVo.setParams(new String[] { orderType.name, hour + ""});
					return noticeSmsCenter(orderBean, smsMessageVo, AlarmType.SMSLOGKEAVING);
				} else {
					log.error(orderBean.getOrderNo() + " 既不属于 分销 也不属于直销 或者app，检查[" + orderBean.getAgentId() + "] [" + orderBean.getOrderChannel() + "]");
				}
			}
		} else if (hour < 23 && hour > 0) {
			if ("泰国".equalsIgnoreCase(orderBean.getServiceCountryName()) && OrderType.PICKUPORDER.equals(orderType)) {
				String msgStr = taiguoGetMsgInfo(orderBean.getFlightDestCode());
				smsMessageVo.setParams(new String[] { orderType.name, hour + "", orderBean.getGuideName(), msgStr });

				if (SMSChannel.APP.equals(smsChannel)) {
					//11
					smsMessageVo.setTemplate_id(SmsTemplate.LININ_LESS_TAIGUO_APP.getTemplateId());
					return noticeSmsCenter(orderBean, smsMessageVo, AlarmType.SMSLOGKEAVING);
				} else if (SMSChannel.fenxiao.equals(smsChannel)) {
					smsMessageVo.setTemplate_id(SmsTemplate.LININ_LESS_TAIGUO_FENXIAO.getTemplateId());
					return noticeSmsCenter(orderBean, smsMessageVo, AlarmType.SMSLOGKEAVING);
				} else if (SMSChannel.zhixiao.equals(smsChannel)) {
					smsMessageVo.setTemplate_id(SmsTemplate.LININ_LESS_TAIGUO_ZHIXIAO.getTemplateId());
					return noticeSmsCenter(orderBean, smsMessageVo, AlarmType.SMSLOGKEAVING);
				} else {
					log.error(orderBean.getOrderNo() + " 既不属于 分销 也不属于直销 或者app，检查[" + orderBean.getAgentId() + "] [" + orderBean.getOrderChannel() + "]");
				}
			} else {
				if (SMSChannel.APP.equals(smsChannel)) {
					smsMessageVo.setTemplate_id(SmsTemplate.LINXIN_LESS_APP.getTemplateId());
					smsMessageVo.setParams(new String[] { orderType.name, hour + "" });
					return noticeSmsCenter(orderBean, smsMessageVo, AlarmType.SMSLOGKEAVING);
				} else if (SMSChannel.fenxiao.equals(smsChannel)) {
					smsMessageVo.setTemplate_id(SmsTemplate.LINXIN_LESS_FENXIAO.getTemplateId());
					smsMessageVo.setParams(new String[] { orderType.name, hour + "", orderBean.getAgentName() });
					return noticeSmsCenter(orderBean, smsMessageVo, AlarmType.SMSLOGKEAVING);
				} else if (SMSChannel.zhixiao.equals(smsChannel)) {
					smsMessageVo.setTemplate_id(SmsTemplate.LINXIN_LESS_ZHIXIAO.getTemplateId());
					smsMessageVo.setParams(new String[] { orderType.name, hour + "" });
					return noticeSmsCenter(orderBean, smsMessageVo, AlarmType.SMSLOGKEAVING);
				} else {
					log.error(orderBean.getOrderNo() + " 既不属于 分销 也不属于直销 或者app，检查[" + orderBean.getAgentId() + "] [" + orderBean.getOrderChannel() + "]");
				}
			}
		} else {
			log.warn("订单[" + orderBean.getOrderNo() + "] 没到发送时间");
		}

		return false;
	}

	private String taiguoGetMsgInfo(String airportCode) {
		String msgStr = "";
		//
		if ("BKK".equals(airportCode)) {
			// 素万那普国际机场4号门内侧铁栏杆处
			msgStr = "素万那普国际机场3号门内侧铁栏杆处";
		} else if ("DMK".equals(airportCode)) {
			// (DMK)廊曼机场3号门
			msgStr = "廊曼机场3号门";
		} else if ("HKT".equals(airportCode)) {
			// (HKT)普吉国际机场出口左转TMB旁，taxi接机牌候客区
			msgStr = "普吉国际机场出口左转TMB旁，taxi接机牌候客区";
		} else if ("CNX".equals(airportCode)) {
			// (CNX)清迈国际机场8号门
			msgStr = "清迈国际机场8号门";
		}
		return msgStr;
	}

	/**
	 * 取消成功
	 * 
	 * @param orderBean
	 */
	public void cancleSMSConfirm(OrderBean orderBean) {
		SMSChannel smsChannel = getSmsChannel(orderBean);
		//亲爱的，您从<皇包车天猫店>预订的<接机/送机/包车/次租>服务（订单号<J1506247268>）已取消成功，退款办理中，登陆APP查看退款进度及详情
		SmsParam smsMessageVo = new SmsParam();

		smsMessageVo.setSys_name(smsTrade);
		smsMessageVo.setArea_code(orderBean.getUserAreaCode1());
		smsMessageVo.setMobile(orderBean.getUserMobile1());

		OrderType orderType = OrderType.getType(orderBean.getOrderType());

		if (SMSChannel.APP.equals(smsChannel)) {
		} else if (SMSChannel.fenxiao.equals(smsChannel)) {
			smsMessageVo.setTemplate_id(SmsTemplate.CANCLE_ORDER_FENXIAO.getTemplateId());
			smsMessageVo.setParams(new String[] { orderBean.getAgentName(), orderType.name, orderBean.getOrderNo() });
			noticeSmsCenter(orderBean, smsMessageVo, AlarmType.cancleSMSConfirm);
		} else if (SMSChannel.zhixiao.equals(smsChannel)) {
			smsMessageVo.setTemplate_id(SmsTemplate.CANCLE_ORDER_ZHIXIAO.getTemplateId());
			smsMessageVo.setParams(new String[] { orderBean.getAgentName(), orderType.name, orderBean.getOrderNo() });
			noticeSmsCenter(orderBean, smsMessageVo, AlarmType.cancleSMSConfirm);
		} else {
			log.error(orderBean.getOrderNo() + " 既不属于 分销 也不属于直销 或者app，检查[" + orderBean.getAgentId() + "] [" + orderBean.getOrderChannel() + "]");
		}
	}

	public void editOrderSMS(OrderBean orderBean) {
		SMSChannel smsChannel = getSmsChannel(orderBean);
		//亲爱的，您从<皇包车天猫店>预订的<接机/送机/包车/次租>服务（订单号<J1506247268>）已取消成功，退款办理中，登陆APP查看退款进度及详情
		SmsParam smsMessageVo = new SmsParam();

		smsMessageVo.setSys_name(smsTrade);
		smsMessageVo.setArea_code(orderBean.getUserAreaCode1());
		smsMessageVo.setMobile(orderBean.getUserMobile1());

		OrderType orderType = OrderType.getType(orderBean.getOrderType());

		if (SMSChannel.APP.equals(smsChannel)) {

		} else if (SMSChannel.fenxiao.equals(smsChannel)) {
			smsMessageVo.setTemplate_id(SmsTemplate.EDIT_ORDER_FENXIAO.getTemplateId());
			smsMessageVo.setParams(new String[] { orderBean.getAgentName(), orderType.name, orderBean.getOrderNo() });
			noticeSmsCenter(orderBean, smsMessageVo, AlarmType.editOrderSMS);
		} else if (SMSChannel.zhixiao.equals(smsChannel)) {
			smsMessageVo.setTemplate_id(SmsTemplate.EDIT_ORDER_ZHIXIAO.getTemplateId());
			smsMessageVo.setParams(new String[] { orderBean.getAgentName(), orderType.name, orderBean.getOrderNo() });
			noticeSmsCenter(orderBean, smsMessageVo, AlarmType.editOrderSMS);
		} else {
			log.error(orderBean.getOrderNo() + " 既不属于 分销 也不属于直销 或者app，检查[" + orderBean.getAgentId() + "] [" + orderBean.getOrderChannel() + "]");
		}
	}

	/**
	 * 重新指派导游SMS forc
	 * 
	 * @param orderBean
	 */
	public void reGuideSMS(OrderBean orderBean) {
		SMSChannel smsChannel = getSmsChannel(orderBean);
		//亲爱的，您从<皇包车天猫店>预订的<接机/送机/包车/次租>服务（订单号<J1506247268>）已取消成功，退款办理中，登陆APP查看退款进度及详情
		SmsParam smsMessageVo = new SmsParam();

		smsMessageVo.setSys_name(smsTrade);
		smsMessageVo.setArea_code(orderBean.getUserAreaCode1());
		smsMessageVo.setMobile(orderBean.getUserMobile1());

		OrderType orderType = OrderType.getType(orderBean.getOrderType());

		if (SMSChannel.APP.equals(smsChannel)) {
			smsMessageVo.setTemplate_id(SmsTemplate.REGUIDE_ORDER_APP.getTemplateId());
			smsMessageVo.setParams(new String[] { orderType.name, orderBean.getOrderNo(), orderBean.getGuideName(), orderBean.getGuideAreaCode() + "" + orderBean.getGuideMobile() });
			noticeSmsCenter(orderBean, smsMessageVo, AlarmType.reGuideSMS);
		} else if (SMSChannel.fenxiao.equals(smsChannel)) {
			smsMessageVo.setTemplate_id(SmsTemplate.REGUIDE_ORDER_FENXIAO.getTemplateId());
			smsMessageVo.setParams(new String[] { orderBean.getAgentName(), orderType.name, orderBean.getOrderNo(), orderBean.getGuideName(), orderBean.getGuideAreaCode() + "" + orderBean.getGuideMobile() });
			noticeSmsCenter(orderBean, smsMessageVo, AlarmType.reGuideSMS);
		} else if (SMSChannel.zhixiao.equals(smsChannel)) {
			smsMessageVo.setTemplate_id(SmsTemplate.REGUIDE_ORDER_ZHIXIAO.getTemplateId());
			smsMessageVo.setParams(new String[] { orderBean.getAgentName(), orderType.name, orderBean.getOrderNo(), orderBean.getGuideName(), orderBean.getGuideAreaCode() + "" + orderBean.getGuideMobile() });
			noticeSmsCenter(orderBean, smsMessageVo, AlarmType.reGuideSMS);
		} else {
			log.error(orderBean.getOrderNo() + " 既不属于 分销 也不属于直销 或者app，检查[" + orderBean.getAgentId() + "] [" + orderBean.getOrderChannel() + "]");
		}
	}

	/**
	 * 导游重新指派
	 * 
	 * @param orderBean
	 */
	public void guideResignSMS(OrderBean orderBean) {
		SMSChannel smsChannel = getSmsChannel(orderBean);
		if (SMSChannel.APP.equals(smsChannel)) {

		} else if (SMSChannel.fenxiao.equals(smsChannel)) {

		} else if (SMSChannel.fenxiao.equals(smsChannel)) {

		} else {
			log.error(orderBean.getOrderNo() + " 既不属于 分销 也不属于直销 或者app，检查[" + orderBean.getAgentId() + "] [" + orderBean.getOrderChannel() + "]");
		}
	}

	private SMSChannel getSmsChannel(OrderBean orderBean) {
		int orderChannel = orderBean.getOrderChannel();
		ChannelAgent channelAgent = channelAgentService.getChannelType(orderChannel);
		channelAgent.getAgentName();
		OrderSource orderSource = OrderSource.getType(orderBean.getOrderSource());
		String agentName = channelAgent.getAgentName();

		if (OrderSource.C.equals(orderSource)) {
			return SMSChannel.APP;
		} else if (OrderSource.OTA.equals(orderSource)) {

		} else {
			SMSChannel channel = AgentCategory.getChannelCategory(agentName);
			log.debug("channel category information ->{} angentName ->{}", channel, agentName);
			return channel;
		}
		return null;
	}

	/**
	 * 通知SMS中心
	 * 
	 * @param smsParam
	 * @return
	 */
	private boolean noticeSmsCenter(OrderBean orderBean, SmsParam smsParam, AlarmType alarmType) {
		boolean result = false;
		int cnum = orderAlarmService.countOrderAlarmByOrderNo(orderBean.getOrderNo(), alarmType,AlarmStatus.SUCCESS);
		if(cnum==0){
			String json = JSON.toJSONString(smsParam);
			try {
				String rsp = httpClientService.sendReqPost(smsUrl, json);
				if (!rsp.contains("200")) {
					log.error("短信接口返回 [" + rsp + "]");
					orderAlarmService.insertAlarmLog(orderBean, alarmType, AlarmStatus.FAIL, json, rsp);
				} else {
					orderAlarmService.insertAlarmLog(orderBean, alarmType, AlarmStatus.SUCCESS, json, rsp);
					result = true;
				}
			} catch (Exception e) {
				log.error("", e);
			}
		}else{
			log.error("订单["+orderBean.getOrderNo()+"]已经存在发送短信[alarmtype:"+alarmType.value+"  value:"+AlarmStatus.SUCCESS.value+"]");
		}
		return result;
	}
}
