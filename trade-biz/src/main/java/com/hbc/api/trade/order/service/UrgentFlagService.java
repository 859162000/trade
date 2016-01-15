/**
 * @Author lukangle
 * @2015年10月26日@下午5:10:31
 */
package com.hbc.api.trade.order.service;

import java.text.ParseException;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hbc.api.gateway.util.TimeConverter;
import com.hbc.api.trade.bdata.mapper.basedata.gen.bean.CityBean;
import com.hbc.api.trade.order.enums.TradeReturnCodeEnum;
import com.hbc.api.trade.order.enums.deliver.UrgentFlag;
import com.hbc.api.trade.order.exception.TradeException;
import com.hbc.api.trade.order.mapping.gen.bean.OrderBean;
import com.hbc.api.trade.order.service.deliver.conf.TradeCitySolrConf;
import com.hbc.api.trade.third.LControllerService;

@Component
public class UrgentFlagService {
	private final static Logger log = LoggerFactory.getLogger(UrgentFlagService.class);
	@Autowired
	LControllerService controllerService;
	@Autowired
	OrderServiceTime orderServiceTime;
	/**
	 * 判断是否是急单 并且对订单价格做处理
	 * @param orderBean
	 * @return
	 * @throws ParseException
	 */
	public UrgentFlag isUrgent(OrderBean orderBean,TradeCitySolrConf.UrgentInfo urgentInfo){
		Integer serviceCityId = orderBean.getServiceCityId();
		CityBean city = controllerService.getCityById(serviceCityId);
		if(city == null) {
			log.error("city为null，查询条件是：serviceCityId=" + serviceCityId);
			throw new TradeException(TradeReturnCodeEnum.ORDER_CITY_EXIST, "cityId:"+serviceCityId);
		}
		if(city.getTimezone() == null) {
			log.error("city的timezone为null，查询条件是：serviceCityId=" + serviceCityId);
			throw new TradeException(TradeReturnCodeEnum.ORDER_PARAM_FAILED, "cityId:"+serviceCityId);
		}
		Date curtime = null;
		try {
			curtime = orderServiceTime.getFormatedDateString(city.getTimezone().intValue());
		} catch (ParseException e) {
			log.error("city的timeZone转化为date时异常");
			throw new TradeException(TradeReturnCodeEnum.ORDER_PARAM_FAILED, "city.getTimezone()");
		}
		
		long serviceTimeLong = orderBean.getServiceTime().getTime();
		long curtimeLong = curtime.getTime();
		long timepp = serviceTimeLong - curtimeLong; 
		log.info(orderBean.getOrderNo()+" 服务城市["+city.getCityName()+"] 服务时区 偏移量 ["+city.getTimezone()+"] 当前服务器时间["+TimeConverter.formatDate(curtime)+"]");
		if(timepp>0&& timepp<urgentInfo.getHour()*60*60*1000){
			return UrgentFlag.urgent;
		}else{
			return UrgentFlag.nomal;
		}
	}
}
