/**
 * @Author lukangle
 * @2015年12月2日@下午3:13:28
 */
package com.hbc.api.trade.third.push;

import java.io.IOException;
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
import com.hbc.api.trade.bdata.common.HttpClientService;
import com.hbc.api.trade.bdata.common.util.TimeConverter;
import com.hbc.api.trade.order.enums.order.OrderType;
import com.hbc.api.trade.order.enums.third.NoticeTarget;
import com.hbc.api.trade.order.mapping.gen.bean.OrderBean;
import com.hbc.api.trade.order.mapping.gen.bean.TradeOrderSerial;

@Service
public class SeriPushService {
	private Logger log = LoggerFactory.getLogger(SeriPushService.class);
	@Value("${trade.pushSerUrl}")
	private String pushSerUrl;
	@Autowired
	private HttpClientService httpClientService;

	public void pushSerialNotis(OrderBean orderBean, Map<String, OrderBean> smap) {
		// 根据您的行程安排，皇包车为您优选了一个2015.09.07 12:40 的接机订单，与您2015.09.07 09:00
		// 的送机订单正好顺路，赶快去接单吧。

		try {
			for (String gid : smap.keySet()) {
				TemplatePushIn templatePushIn = new TemplatePushIn();
				templatePushIn.setApplyType(NoticeTarget.GUIDE.value);
				templatePushIn.setApplyIds(gid);
				templatePushIn.setTemplateId(71);
				String otime = TimeConverter.formatDate(orderBean.getServiceTime());

				OrderType orderType = OrderType.getType(orderBean.getOrderType());
				OrderBean serviceedOrderBean = smap.get(gid);
				OrderType serviceedOrderType = OrderType.getType(serviceedOrderBean.getOrderType());
				String serviceedtime = TimeConverter.formatDate(serviceedOrderBean.getServiceTime());
				if (OrderType.PICKUPORDER.equals(orderType)) {
					templatePushIn.setParams(new String[] { otime, orderBean.getFlightDestName(), orderType.name, serviceedtime, serviceedOrderType.name });
				} else if (OrderType.TRANSFER.equals(orderType)) {
					templatePushIn.setParams(new String[] { otime, orderBean.getFlightAirportName(), orderType.name, serviceedtime, serviceedOrderType.name });
				}

				Map<String, String> extras = new HashMap<>();
				extras.put("orderNo", orderBean.getOrderNo());
				extras.put("orderType", orderBean.getOrderType()+"");
				extras.put("type", "M5");
				templatePushIn.setExtras(extras);

				String json = JSONObject.toJSONString(templatePushIn);

				sendPush(pushSerUrl, json);
			}
		} catch (Exception e) {
			log.error("发送push [" + orderBean.getOrderNo() + "] [" + JSON.toJSONString(smap) + "] 失败");
		}

	}
	
	public void pushSerialNotis(OrderBean orderBean,List<TradeOrderSerial>  tlist) {
		// 根据您的行程安排，皇包车为您优选了一个2015.09.07 12:40 的接机订单，与您2015.09.07 09:00
		// 的送机订单正好顺路，赶快去接单吧。

		try {
			Map<String,TradeOrderSerial> tmap = new HashMap<String,TradeOrderSerial>();
			for(TradeOrderSerial tradeOrderSerial : tlist){
				tmap.put(tradeOrderSerial.getMeetOrderNo(), tradeOrderSerial);
			}
			for (String sid : tmap.keySet()) {
				TradeOrderSerial tradeOrderSerial = tmap.get(sid);
				TemplatePushIn templatePushIn = new TemplatePushIn();
				templatePushIn.setApplyType(NoticeTarget.GUIDE.value);
				templatePushIn.setApplyIds(tradeOrderSerial.getMeetGuideId());
				templatePushIn.setTemplateId(71);
				String otime = TimeConverter.formatDate(orderBean.getServiceTime());

				OrderType orderType = OrderType.getType(orderBean.getOrderType());
				OrderType serviceedOrderType = OrderType.getType(tradeOrderSerial.getMeetOrderType());
				String serviceedtime = TimeConverter.formatDate(tradeOrderSerial.getMeetOrderServiceTime());
				if (OrderType.PICKUPORDER.equals(orderType)) {
					templatePushIn.setParams(new String[] { otime, orderBean.getFlightDestName(), orderType.name, serviceedtime, serviceedOrderType.name });
				} else if (OrderType.TRANSFER.equals(orderType)) {
					templatePushIn.setParams(new String[] { otime, orderBean.getFlightAirportName(), orderType.name, serviceedtime, serviceedOrderType.name });
				}

				Map<String, String> extras = new HashMap<>();
				extras.put("orderNo", orderBean.getOrderNo());
				extras.put("orderType", orderBean.getOrderType()+"");
				extras.put("type", "M5");
				templatePushIn.setExtras(extras);

				String json = JSONObject.toJSONString(templatePushIn);

				sendPush(pushSerUrl, json);
			}
		} catch (Exception e) {
			log.error("发送push [" + orderBean.getOrderNo() + "] [" + JSON.toJSONString(tlist) + "] 失败");
		}

	}

	public void sendPush(String pushUrl, String data) throws IOException {
		try {
			String rspData = httpClientService.sendReqPost(pushSerUrl, data);

			JSONObject jobj = JSON.parseObject(rspData);
			if (200 == jobj.getInteger("status")) {
				log.info("发送push给 [" + pushUrl + "] [" + data + "]");
			} else {
				log.error("发送push给 [" + pushUrl + "] [" + data + "] 失败，失败信息[" + rspData + "]");
			}
		} catch (Exception e) {
			log.error("发送push给 [" + pushUrl + "] [" + data + "] 失败，失败信息[" + e.toString() + "]");
		}
	}
}
