/**
 * @Author lukangle
 * @2015年11月17日@下午4:58:56
 */
package com.hbc.api.trade.order.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.hbc.api.trade.bdata.common.TradeConstant;
import com.hbc.api.trade.order.enums.TradeReturnCodeEnum;
import com.hbc.api.trade.order.exception.TradeException;
import com.hbc.api.trade.order.mapping.gen.bean.OrderBean;

@Component
public class ServicePassCityService {
	private final static Logger log = LoggerFactory.getLogger(ServicePassCityService.class);
	public List<Integer> getPassCitys(OrderBean orderBean){
		Set<Integer> citysSet = new HashSet<Integer>();
		List<Integer> citys = new ArrayList<Integer>();
		String passCityStr = orderBean.getServicePassCity();
		if (passCityStr != null && passCityStr.length() > 0) {
			String[] pstrs = passCityStr.split(TradeConstant.SPLITER_COMMA);
			for (String servicePassCity : pstrs) {
				citysSet.add(Integer.parseInt(servicePassCity.split(TradeConstant.SPLITER_LINE)[0]));
			}
		}
		citysSet.add(orderBean.getServiceCityId());
		if(orderBean.getServiceEndCityid()!=null){
			citysSet.add(orderBean.getServiceEndCityid());
		}
		
		citys.addAll(citysSet);
		return citys;
		
	}
	

	/**
	 * 校验途径城市值格式是否为"cityId_stayDay,[cityId2_stayDay2,...]"
	 * @param param
	 * @return
	 */
	public static void validateServicePassCities(String servicePassCities) {
		if(servicePassCities != null) {
			if(!servicePassCities.contains(TradeConstant.SPLITER_LINE)) {
				throw new TradeException(TradeReturnCodeEnum.ORDER_PARAM_FAILED, "途径城市");
			}
			if(servicePassCities.contains(TradeConstant.SPLITER_COMMA)) {
				String[] cities = servicePassCities.split(TradeConstant.SPLITER_COMMA);
				for(String city : cities) {
					if(!city.contains(TradeConstant.SPLITER_LINE) || city.split(TradeConstant.SPLITER_LINE).length != 2) {
						throw new TradeException(TradeReturnCodeEnum.ORDER_PARAM_FAILED, "途径城市");
					}
				}
			} else if(servicePassCities.split(TradeConstant.SPLITER_LINE).length != 2) {
				throw new TradeException(TradeReturnCodeEnum.ORDER_PARAM_FAILED, "途径城市");
			}
		}
	}
}
