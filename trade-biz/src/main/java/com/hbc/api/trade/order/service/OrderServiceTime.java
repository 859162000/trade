/**
 * @Author lukangle
 * @2015年10月7日@下午2:13:14
 */
package com.hbc.api.trade.order.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.SimpleTimeZone;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.hbc.api.trade.bdata.mapper.basedata.gen.bean.CityBean;
import com.hbc.api.trade.order.enums.TradeReturnCodeEnum;
import com.hbc.api.trade.order.exception.TradeException;
import com.hbc.api.trade.order.mapping.gen.bean.OrderBean;
import com.hbc.api.trade.third.LControllerService;
@Component
public class OrderServiceTime {
	@Value("${trade.placeOrderTimeBefore}")
	private int placeOrderTimeBefore;//白天多少时间可以下单 3小时
	@Value("${trade.pick.starttime}")
	private int pickStarttime;//10 - 24
	@Value("${trade.pick.endtime}")
	private int pickEndtime;
	/**
	 * 判断订单是否有效
	 * @param orderBean
	 * @param city
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public boolean isValidServiceTime(OrderBean orderBean, CityBean city) {
		try {
			Date date = getFormatedDateString(city.getTimezone().intValue());
			Date serviceDate = orderBean.getServiceTime();
			
			int hour = serviceDate.getHours();
			
			if(hour<=pickEndtime && hour>=pickStarttime){
				Date dateAdd = new Date(date.getTime()+placeOrderTimeBefore*60*1000);
				if(serviceDate == null || !serviceDate.after(date) || !serviceDate.after(dateAdd)){
					throw new TradeException(TradeReturnCodeEnum.ORDER_SERVICETIMEPARAM_FAILED,placeOrderTimeBefore);
				}
			}else{
				Date dateAdd = new Date(serviceDate.getTime()-24*60*60*1000);
				dateAdd.setHours(21);
				dateAdd.setMinutes(0);
				dateAdd.setSeconds(0);
				if(serviceDate == null || !serviceDate.after(date) || !date.before(dateAdd)){
					throw new TradeException(TradeReturnCodeEnum.ORDER_SERVICETIMEPARAM_FAILED,placeOrderTimeBefore);
				}
			}
		} catch (ParseException e) {
			throw new TradeException(TradeReturnCodeEnum.ORDER_SERVICETIMEPARAM_FAILED,e);
		}
		return true;
	}

	
	public TimeZone getTimeZone(int timeZoneOffset) {
		if (timeZoneOffset > 13 || timeZoneOffset < -12) {
			timeZoneOffset = 0;
		}
		TimeZone timeZone;
		String[] ids = TimeZone.getAvailableIDs(timeZoneOffset * 60 * 60 * 1000);
		if (ids.length == 0) {
			// if no ids were returned, something is wrong. use default TimeZone
			timeZone = TimeZone.getDefault();
		} else {
			timeZone = new SimpleTimeZone(timeZoneOffset * 60 * 60 * 1000, ids[0]);
		}
		return timeZone;
	}
	public Date getFormatedDateString(int timeZoneOffset) throws ParseException {
		if (timeZoneOffset > 13 || timeZoneOffset < -12) {
			timeZoneOffset = 0;
		}
		TimeZone timeZone;
		String[] ids = TimeZone.getAvailableIDs(timeZoneOffset * 60 * 60 * 1000);
		if (ids.length == 0) {
			throw new TradeException(TradeReturnCodeEnum.ORDER_SERVICETIMEPARAM_FAILED,timeZoneOffset+"不存在");
		} else {
			timeZone = new SimpleTimeZone(timeZoneOffset * 60 * 60 * 1000, ids[0]);
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		sdf.setTimeZone(timeZone);
		String offsetStr = sdf.format(new Date());
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		Date date = sdf2.parse(offsetStr);
		return date;
	}
	@Autowired
	LControllerService controllerService;
	public Date getServiceCityCurTime(OrderBean orderBean){
		try {
			CityBean cityBean = controllerService.getCityById(orderBean.getServiceCityId());
			Date date = getFormatedDateString(cityBean.getTimezone().intValue());
			return date;
		} catch (ParseException e) {
			throw new TradeException(TradeReturnCodeEnum.ORDER_SERVICETIMEPARAM_FAILED,e);
		}
	}
	
	public Date getServiceCityCurTime(int cityId){
		try {
			CityBean cityBean = controllerService.getCityById(cityId);
			Date date = getFormatedDateString(cityBean.getTimezone().intValue());
			return date;
		} catch (ParseException e) {
			throw new TradeException(TradeReturnCodeEnum.ORDER_SERVICETIMEPARAM_FAILED,e);
		}
	}

	public Date getTimeZoneCurTime(int timeZoneOffset) throws ParseException {
		if (timeZoneOffset > 13 || timeZoneOffset < -12) {
			timeZoneOffset = 0;
		}
		TimeZone timeZone;
		String[] ids = TimeZone.getAvailableIDs(timeZoneOffset * 60 * 60 * 1000);
		if (ids.length == 0) {
			throw new TradeException(TradeReturnCodeEnum.ORDER_SERVICETIMEPARAM_FAILED,timeZoneOffset+"不存在");
		} else {
			timeZone = new SimpleTimeZone(timeZoneOffset * 60 * 60 * 1000, ids[0]);
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		sdf.setTimeZone(timeZone);
		String offsetStr = sdf.format(new Date());
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		Date date = sdf2.parse(offsetStr);
		return date;
	}
	
	
	public Date getTimeZoneServiceTime(int cityId,Date serviceTime) throws ParseException {
		CityBean cityBean = controllerService.getCityById(cityId);
		cityBean.getTimezone().intValue();
		int timeZoneOffset =cityBean.getTimezone().intValue();
		String[] ids = TimeZone.getAvailableIDs(timeZoneOffset * 60 * 60 * 1000);
		
		TimeZone timeZone;
		
		if (ids.length == 0) {
			throw new TradeException(TradeReturnCodeEnum.ORDER_SERVICETIMEPARAM_FAILED,timeZoneOffset+"不存在");
		} else {
			timeZone = new SimpleTimeZone(timeZoneOffset * 60 * 60 * 1000, ids[0]);
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		
		String offsetStr = sdf.format(serviceTime);
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		sdf2.setTimeZone(timeZone);
		Date date = sdf2.parse(offsetStr);
		return date;
	}
	
}
