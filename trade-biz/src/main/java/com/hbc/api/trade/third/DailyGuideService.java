/**
 * @Author lukangle
 * @2015年11月20日@下午4:24:48
 */
package com.hbc.api.trade.third;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hbc.api.trade.order.enums.third.CarClassEnum;
import com.hbc.api.trade.order.enums.third.CarTypeEnum;
import com.hbc.api.trade.order.enums.third.GuideCropTypeEnum;
import com.hbc.api.trade.order.mapping.gen.bean.OrderBean;
import com.hbc.api.trade.order.service.ServicePassCityService;
@Component
public class DailyGuideService {
	@Autowired
	ServicePassCityService servicePassCityService;
	@Autowired
	private GuideQueryService guideQueryService;
	public List<String> getDailyGuideList(OrderBean orderBean, CarTypeEnum carTypeEnum, CarClassEnum carClassEnum, GuideCropTypeEnum guideCropTypeEnum) {
		List<String> guideIdsList = new ArrayList<String>();
		List<Integer> citys = servicePassCityService.getPassCitys(orderBean);

		Set<String> guideIds = guideQueryService.getGuideCoupsByCityIds(citys, guideCropTypeEnum);
		guideIdsList.addAll(guideIds);

		return guideIdsList;
	}
}
