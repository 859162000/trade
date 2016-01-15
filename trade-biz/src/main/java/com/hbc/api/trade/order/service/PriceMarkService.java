/**
 * @Author lukangle
 * @2015年11月29日@下午5:42:57
 */
package com.hbc.api.trade.order.service;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hbc.api.trade.bdata.common.HttpClientService;
import com.hbc.api.trade.bdata.common.TradeConstant;
import com.hbc.api.trade.bdata.common.exception.ReturnCode;
import com.hbc.api.trade.bdata.common.util.TimeConverter;
import com.hbc.api.trade.order.enums.TradeReturnCodeEnum;
import com.hbc.api.trade.order.enums.order.OrderSource;
import com.hbc.api.trade.order.enums.order.OrderType;
import com.hbc.api.trade.order.enums.ota.AgentChannelEnum;
import com.hbc.api.trade.order.enums.third.ServiceType;
import com.hbc.api.trade.order.exception.TradeException;
import com.hbc.api.trade.order.mapping.gen.bean.OrderBean;

@Component
public class PriceMarkService {
	// http://api.dev.hbc.tech/price/v1.0/p/pricemarkauth?serviceType=1&pricemark=f36190c7-c29d-4b8d-8e71-573edce05fbd1447938352026&price=1151&carType=1&seatCategory=5&startLocation=37.574102,127.020007
	// &endLocation=37.460191,126.440696&channelId=25&serviceDate=2015-11-12
	// 19:30:22&airportCode=ICN
	protected final static Logger log = LoggerFactory.getLogger(PriceMarkService.class);
	@Value("${trade.priceMarkUrl}")
	private String priceMarkUrl;
	@Autowired
	private HttpClientService httpClientService;

	public void verify(OrderBean orderBean) {
		String verisyUrl = priceMarkUrl;
		OrderType orderType = OrderType.getType(orderBean.getOrderType());
		ServiceType serviceType = ServiceType.getType(orderType);

		double price = orderBean.getPriceChannel();
		String pricemark = orderBean.getPriceMark();

		int carType = orderBean.getCarTypeId();
		int seatNum = orderBean.getCarSeatNum();

		String startLocation = orderBean.getStartAddressPoi();
		String endLocation = orderBean.getDestAddressPoi();
		// 	去掉末尾的0
		String[] startLocations = startLocation.split(TradeConstant.SPLITER_COMMA);
		String[] endLocations = endLocation.split(TradeConstant.SPLITER_COMMA);
		startLocation = Double.valueOf(startLocations[0]) + TradeConstant.SPLITER_COMMA +  Double.valueOf(startLocations[1]);
		endLocation = Double.valueOf(endLocations[0]) + TradeConstant.SPLITER_COMMA +  Double.valueOf(endLocations[1]);

		String serviceDate = TimeConverter.formatDate(orderBean.getServiceTime());
		Integer channelId = orderBean.getOrderChannel();

		if (OrderType.PICKUPORDER.equals(orderType)) {
			String airportCode = orderBean.getFlightDestCode();
			verisyUrl = verisyUrl + "?serviceType=" + serviceType.value + "&pricemark=" + pricemark + "&price=" + price + "&carType=" + carType + "&seatCategory=" + seatNum + "&startLocation="
					+ startLocation + "&endLocation=" + endLocation + "&channelId=" + channelId + "&serviceDate=" + serviceDate + "&airportCode=" + airportCode;
		} else if (OrderType.TRANSFER.equals(orderType)) {
			// http://api.dev.hbc.tech/price/v1.0/p/pricemarkauth?serviceType=2&pricemark=b9bfafb1-bd35-4217-8869-2965ff05d9e81447937078853&price=1164&carType=1&seatCategory=5&startLocation=37.460191,126.440696&endLocation=37.574102,127.020007&airportCode=ICN&channelId=25&serviceDate=2015-11-12
			// 19:30:22&assitCheckIn=1&checkinPrice=54
			String airportCode = orderBean.getFlightAirportCode();
			Double checkInPrice = orderBean.getCheckInPrice();
			int ischeckIn = 0;
			if (checkInPrice != null && checkInPrice > 0) {
				ischeckIn = 1;
			} else {
				checkInPrice = 0d;
			}
			verisyUrl = verisyUrl + "?serviceType=" + serviceType.value + "&pricemark=" + pricemark + "&price=" + price + "&carType=" + carType + "&seatCategory=" + seatNum + "&startLocation="
					+ startLocation + "&endLocation=" + endLocation + "&channelId=" + channelId + "&serviceDate=" + serviceDate + "&airportCode=" + airportCode + "&assitCheckIn=" + ischeckIn
					+ "&checkinPrice=" + checkInPrice;
		} else if (OrderType.PERUSE.equals(orderType)) {
			// http://api.dev.hbc.tech/price/v1.0/p/pricemarkauth?serviceType=4&pricemark=f0c5e257-37c0-43c9-8d22-7c0df84524331447938754277&price=2&carType=2&seatCategory=5&startLocation=37.460191,126.440696&endLocation=37.574102,127.020007&channelId=25&serviceDate=2015-11-12
			// 19:30:22&cityId=204
			Integer cityId = orderBean.getServiceCityId();
			verisyUrl = verisyUrl + "?serviceType=" + serviceType.value + "&pricemark=" + pricemark + "&price=" + price + "&carType=" + carType + "&seatCategory=" + seatNum + "&startLocation="
					+ startLocation + "&endLocation=" + endLocation + "&channelId=" + channelId + "&serviceDate=" + serviceDate + "&cityId=" + cityId;

		} else if (OrderType.DAILY.equals(orderType)) {

			// http://api.dev.hbc.tech/price/v1.0/p/pricemarkauth?serviceType=3&pricemark=34c80e93-d839-41a5-ad66-523aecb3702e1447939033115&price=3&
			// endCityId=43&startCityId=204&channelId=25&startLocation=37.460191,126.440696&endLocation=37.574102,127.020007&startDate=2015-11-12
			// 19:30:22&endDate=2015-11-14
			// 00:00:00&stayCities=204-1,43-2&carType=1&seatCategory=9
			Integer cityId = orderBean.getServiceCityId();
			Integer cityEndId = orderBean.getServiceEndCityid();

			String etime = TimeConverter.formatDates(orderBean.getServiceEndTime())+" 00:00:00";
			verisyUrl = verisyUrl + "?serviceType=" + serviceType.value + "&pricemark=" + pricemark + "&price=" + price + "&carType=" + carType + "&seatCategory=" + seatNum + "&channelId="
					+ channelId + "&startDate=" + serviceDate + "&startCityId=" + cityId + "&endCityId=" + cityEndId + "&startLocation=" + startLocation + "&endLocation=" + endLocation
					+ "&stayCities=" + orderBean.getServicePassCity()+"&endDate="+etime;
		} else if (OrderType.COMMENDATION.equals(orderType)) {
			Integer cityId = orderBean.getServiceCityId();
			Integer cityEndId = orderBean.getServiceEndCityid();
			verisyUrl = verisyUrl + "?serviceType=" + serviceType.value + "&pricemark=" + pricemark + "&price=" + price + "&carType=" + carType + "&seatCategory=" + seatNum + "&channelId="
					+ channelId + "&serviceDate=" + serviceDate + "&startCityId=" + cityId + "&endCityId=" + cityEndId + "&startLocation=" + startLocation + "&endLocation=" + endLocation
					+ "&stayCities=" + orderBean.getServicePassCity()+"&goodsNo=" + orderBean.getGoodNo() + "&goodsType=" + orderBean.getOrderGoodsType();
		}

		try {
			log.info("下单价格校验RequestURL：" + verisyUrl);
			String vstr = httpClientService.sendReq(verisyUrl);
			log.info("服务返回结果：" + vstr);
			/*
			 DEMO:
			 {"data":{"channelNightPrice":0,"channelPrice":8935,"distance":47.55,"estTime":48,"guideNightPrice":0,"guidePrice":7521,
			 "pricemark":"13899ce6-c818-40aa-92e1-3a0fa2eb2cb51450698686065","sysNightPrice":0,"sysPrice":7521,"ticketNightPrice":0,
			 "ticketPrice":8935,"urgentFlat":0,"urgentHour":24},"status":200}
			 */
			JSONObject jobject = JSON.parseObject(vstr);

			Integer status = jobject.getInteger("status");
			if (200 == status) {
				JSONObject data = (JSONObject) jobject.get("data");
				if(AgentChannelEnum.QUNAR_CHANNEL.value.equals(orderBean.getOrderChannel())) {
					orderBean.setPriceMark(data.getString("pricemark"));
				}
				if(orderBean.getDistance()==null || orderBean.getDistance()==0){
					orderBean.setDistance(data.getDouble("distance"));
				}
				if(orderBean.getExpectedCompTime() == null || orderBean.getExpectedCompTime() <= 0) {
					orderBean.setExpectedCompTime(data.getInteger("estTime"));
				}
				if(data.getDouble("sysPrice") == null || data.getDouble("guidePrice") == null || data.getDouble("ticketPrice") == null ){
					throw new TradeException(TradeReturnCodeEnum.PRICE_MARK_NOEXT, vstr);
				}
				orderBean.setPriceBase(data.getDouble("sysPrice"));
				orderBean.setPriceGuide(data.getDouble("guidePrice"));
				orderBean.setPriceGuideBase(data.getDouble("guidePrice"));
				
				orderBean.setUrgentHour(data.getInteger("urgentHour"));
				
				if(orderBean.getPriceTicket()==null || orderBean.getPriceTicket()<=0d || OrderSource.GDS.value.equals(orderBean.getOrderSource())){
					
				}else{
					orderBean.setPriceTicket(data.getDouble("ticketPrice"));
				}

			} else {
				throw new TradeException(new ReturnCode() {
					@Override public String getMessage() { return jobject.getString("message"); }
					@Override public int getCode() { return status; }
				});
			}
		} catch (IOException e) {
			throw new TradeException(TradeReturnCodeEnum.PRICE_MARK_NOEXT, e.getMessage());
		}

	}
}
