/**
 * @Author lukangle
 * @2015年11月21日@下午3:22:55
 */
package com.hbc.api.trade.pay.service;

import java.text.ParseException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.hbc.api.trade.bdata.mapper.basedata.gen.bean.CityBean;
import com.hbc.api.trade.order.enums.TradeReturnCodeEnum;
import com.hbc.api.trade.order.exception.TradeException;
import com.hbc.api.trade.order.mapping.gen.bean.OrderBean;
import com.hbc.api.trade.order.service.OrderServiceTime;
import com.hbc.api.trade.third.LControllerService;

@Component
public class PayTimeService {
	@Value(value = "${trade.pay.timeout}")
	private int paytimeout;
	
	/**
	 * 付款的截止时间
	 * @param orderBean
	 * @return
	 */
	public Date payDeadline(OrderBean orderBean) {
		return getPayEndDate(orderBean);
	}
	
	public String getPayDeadline(OrderBean orderBean) {
		String ctimestr = "";
		Date date = getPayEndDate(orderBean);
		Date serviceCur = orderServiceTime.getServiceCityCurTime(orderBean.getServiceCityId());
		long ctime = date.getTime()-serviceCur.getTime();
		long minit = ctime/(1000*60);
		if(ctime>0 && minit<60){
			ctimestr = minit+"分钟";
		}else if(ctime>0 && minit>=60){
			long hour= ctime/(1000*60*60);
			ctimestr = hour+"小时";
		}else{
			ctimestr = 0+"分钟";
		}
		return ctimestr;
	}
	
	/**
	 * 
	 * @param orderBean
	 * @return true,付款已超时；反之，false
	 */
	public boolean isTimeout(OrderBean orderBean) {
		return new Date().after(payDeadline(orderBean));
	}
	@Autowired
	OrderServiceTime orderServiceTime;
	
	@Autowired
	LControllerService controllerService;
	
	@Value("${trade.pick.starttime}")
	private int pickStarttime;
	@Value("${trade.pick.endtime}")
	private int pickEndtime;
	private int placeOrderTimeBefore = 2;//白天多少时间可以下单 2小时
	
	@SuppressWarnings("deprecation")
	public Date getPayEndDate(OrderBean orderBean){
		try {
			CityBean cityBean = controllerService.getCityById(orderBean.getServiceCityId());
			Date date = orderServiceTime.getFormatedDateString(cityBean.getTimezone().intValue());
			Date serviceDate = orderBean.getServiceTime();
			
			Date pickTime = orderServiceTime.getTimeZoneServiceTime(orderBean.getServiceCityId(),orderBean.getCreateTime());
			
			int hour = serviceDate.getHours();
			
			Date dateAdd = date;
			if(hour<=pickEndtime && hour>=pickStarttime){
				dateAdd = new Date(serviceDate.getTime()-placeOrderTimeBefore*60*60*1000);
//				return dateAdd;
			}else{
				dateAdd = new Date(serviceDate.getTime()-24*60*60*1000);
				dateAdd.setHours(22);
				dateAdd.setMinutes(0);
				dateAdd.setSeconds(0);
//				return dateAdd;
			}
			
			Date twoDayDate = new Date(pickTime.getTime()+48*60*60*1000);
			
			int urgentHour = 0;
			if(orderBean.getUrgentHour()!=null){
				urgentHour = orderBean.getUrgentHour();
			}
			Date urgentTime =  new Date(serviceDate.getTime()-urgentHour*60*60*1000);
			//48小时
			if(twoDayDate.before(dateAdd)){
				dateAdd = twoDayDate;
			}
			
			if(urgentTime.after(pickTime) && urgentTime.before(dateAdd)){
				dateAdd = urgentTime;
			}
			return dateAdd;
		} catch (ParseException e) {
			throw new TradeException(TradeReturnCodeEnum.ORDER_SERVICETIMEPARAM_FAILED,e);
		}
	}
	public void payTimeValide(OrderBean orderBean) {
		Date dateAdd = getPayEndDate(orderBean);
		Date serviceDate = orderBean.getServiceTime();
		
		if(serviceDate == null  || !serviceDate.after(dateAdd)){
			throw new TradeException(TradeReturnCodeEnum.ORDER_SERVICETIMEPARAM_FAILED,placeOrderTimeBefore);
		}
	}
}
