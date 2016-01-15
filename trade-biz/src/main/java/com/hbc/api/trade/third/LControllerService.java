/**
 * @Author lukangle
 * @2015年10月7日@下午1:56:05
 */
package com.hbc.api.trade.third;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import com.hbc.api.trade.bdata.common.cache.CacheFinal;
import com.hbc.api.trade.bdata.mapper.basedata.gen.AirportBeanMapper;
import com.hbc.api.trade.bdata.mapper.basedata.gen.CityBeanMapper;
import com.hbc.api.trade.bdata.mapper.basedata.gen.ContinentBeanMapper;
import com.hbc.api.trade.bdata.mapper.basedata.gen.CountryBeanMapper;
import com.hbc.api.trade.bdata.mapper.basedata.gen.bean.AirportBean;
import com.hbc.api.trade.bdata.mapper.basedata.gen.bean.AirportBeanExample;
import com.hbc.api.trade.bdata.mapper.basedata.gen.bean.CityBean;
import com.hbc.api.trade.bdata.mapper.basedata.gen.bean.ContinentBean;
import com.hbc.api.trade.bdata.mapper.basedata.gen.bean.CountryBean;
import com.hbc.api.trade.bdata.mapper.basedata.genx.GxCityBeanMapper;
import com.hbc.api.trade.bdata.mapper.guide.gen.GuideBeanMapper;
import com.hbc.api.trade.bdata.mapper.guide.gen.GuideGradeMapper;
import com.hbc.api.trade.bdata.mapper.guide.gen.GuideProhibitMapper;
import com.hbc.api.trade.bdata.mapper.guide.gen.bean.GuideBean;
import com.hbc.api.trade.bdata.mapper.guide.gen.bean.GuideBeanExample;
import com.hbc.api.trade.bdata.mapper.guide.gen.bean.GuideGrade;
import com.hbc.api.trade.bdata.mapper.guide.gen.bean.GuideGradeExample;
import com.hbc.api.trade.bdata.mapper.guide.gen.bean.GuideProhibit;
import com.hbc.api.trade.bdata.mapper.guide.gen.bean.GuideProhibitExample;
import com.hbc.api.trade.bdata.mapper.guide.genx.GxGuideMapper;
import com.hbc.api.trade.order.enums.TradeReturnCodeEnum;
import com.hbc.api.trade.order.enums.third.GuideProhibitEnum;
import com.hbc.api.trade.order.enums.third.GuideStatus;
import com.hbc.api.trade.order.exception.TradeException;
import com.hbc.api.trade.order.mapping.gen.bean.TradeDeliverGuide;

@Component
public class LControllerService {
	@Autowired
	CityBeanMapper cityBeanMapper;
	@Autowired
	AirportBeanMapper airportBeanMapper;
	@Autowired
	GxGuideMapper gxGuideMapper;
	@Autowired
	ContinentBeanMapper continentBeanMapper;
	@Autowired
	CountryBeanMapper countryBeanMapper;
	@Autowired
	GxCityBeanMapper gxCityBeanMapper;
	
	@Cacheable(value=CacheFinal.airportCache,key="#root.targetClass+#root.methodName"+"+#code")
	public AirportBean getAirportByCode(String code) {
		AirportBeanExample airportBeanExample = new AirportBeanExample();
		AirportBeanExample.Criteria criteria = airportBeanExample.createCriteria();
		criteria.andCodeEqualTo(code);
		List<AirportBean> airports = airportBeanMapper.selectByExample(airportBeanExample);
		if (airports == null || airports.size() != 1) {
			throw new TradeException(TradeReturnCodeEnum.ORDER_AIRPORTPARAM_FAILED, code);
		}
		return airports.get(0);
	}

	@Cacheable(value=CacheFinal.cityCacheName,key="#root.targetClass+#root.methodName"+"+#cityId")
	public CityBean getCityById(int cityId) {
		CityBean city = cityBeanMapper.selectByPrimaryKey(cityId);
		return city;
	}

	@Cacheable(value=CacheFinal.cityCacheName,key="#root.targetClass+#root.methodName"+"+'cid'")
	public List<CityBean> getAllCitys() {
		return gxCityBeanMapper.getAllCitys();
	}

	@Cacheable(value=CacheFinal.countryCacheName,key="#root.targetClass+#root.methodName"+"+#countryId")
	public CountryBean getCountryById(int countryId) {
		return countryBeanMapper.selectByPrimaryKey(countryId);
	}

	@Cacheable(value=CacheFinal.countryCacheName,key="#root.targetClass+#root.methodName"+"+#continentId")
	public ContinentBean geContinentById(int continentId) {
		return continentBeanMapper.selectByPrimaryKey(continentId);
	}

	@Autowired
	private GuideBeanMapper guideBeanMapper;
	public GuideBean getGuidByGuideId(String guideId) {
		return guideBeanMapper.selectByPrimaryKey(guideId);
	}

	@Autowired
	private GuideGradeMapper guideGradeMapper;

	public List<GuideGrade> getGuideGradeByGuideIds(List<String> guideIds) {
		GuideGradeExample guideGradeExample = new GuideGradeExample();
		GuideGradeExample.Criteria criteria = guideGradeExample.createCriteria();
		if(guideIds != null && guideIds.size() > 0) {
			criteria.andGuideIdIn(guideIds);
		}
		List<GuideGrade> guideGrades = guideGradeMapper.selectByExample(guideGradeExample);
		return guideGrades;
	}
	
	public GuideGrade getGuideGradeByGuideId(String guideId) {
		GuideGradeExample guideGradeExample = new GuideGradeExample();
		GuideGradeExample.Criteria criteria = guideGradeExample.createCriteria();
		criteria.andGuideIdEqualTo(guideId);
		List<GuideGrade> guideGrades = guideGradeMapper.selectByExample(guideGradeExample);
		if(guideGrades.size()>=1){
			return guideGrades.get(0);
		}else{
			return null;
		}
	}
	@Autowired
	private GuideProhibitMapper guideProhibitMapper;
	/**
	 * 
	 * @param guides
	 * @param guideProhibitEnum
	 * @return
	 */
	public List<GuideProhibit> getGuideProhibitPush(List<GuideBean> guides,GuideProhibitEnum guideProhibitEnum){
		GuideProhibitExample guideProhibitExample = new GuideProhibitExample();
		GuideProhibitExample.Criteria criteria = guideProhibitExample.createCriteria();
		List<String> guideIds=new ArrayList<String>();
		for(GuideBean guide:guides){
			guideIds.add(guide.getGuideId());
		}
		if(guideIds.size() > 0 ) {
			criteria.andGuideIdIn(guideIds);
		}
		criteria.andTypeEqualTo(guideProhibitEnum.value);
		Date now=new Date();		
		criteria.andStartDateLessThanOrEqualTo(now);
		criteria.andEndDateGreaterThan(now);
		criteria.andStatusEqualTo(1);
		List<GuideProhibit> guideProhibits = guideProhibitMapper.selectByExample(guideProhibitExample);		
		
		GuideProhibitExample guideProhibitExample2 = new GuideProhibitExample();
		GuideProhibitExample.Criteria criteria2 = guideProhibitExample2.createCriteria();
		if(guideIds.size() > 0 ) {
			criteria2.andGuideIdIn(guideIds);
		}
		criteria2.andTypeEqualTo(guideProhibitEnum.value);
		criteria2.andStartDateLessThanOrEqualTo(now);
		criteria2.andEndDateIsNull();
		criteria2.andStatusEqualTo(1);
		guideProhibits.addAll(guideProhibitMapper.selectByExample(guideProhibitExample2));		
		return guideProhibits;
	}
	
	public List<GuideProhibit> getGuideProhibit(String guideId,GuideProhibitEnum guideProhibitEnum){
		GuideBean guideBean = getGuidByGuideId(guideId);
		List<GuideBean> guides = new ArrayList<GuideBean>();
		guides.add(guideBean);
		return getGuideProhibitPush(guides,guideProhibitEnum);
	}
	/**
	 * 
	 * @param tradeDeliverGuides
	 * @param guideProhibitEnum
	 * @return
	 */
	public List<GuideProhibit> getGuideProhibitPushByDeliverGuide(List<TradeDeliverGuide> tradeDeliverGuides,GuideProhibitEnum guideProhibitEnum){
		GuideProhibitExample guideProhibitExample = new GuideProhibitExample();
		GuideProhibitExample.Criteria criteria = guideProhibitExample.createCriteria();
		List<String> guideIds=new ArrayList<String>();
		for(TradeDeliverGuide tradeDeliverGuide:tradeDeliverGuides){
			guideIds.add(tradeDeliverGuide.getGuideId());
		}
		if(guideIds.size() > 0) {
			criteria.andGuideIdIn(guideIds);
		}
		criteria.andTypeEqualTo(guideProhibitEnum.value);
		Date now=new Date();
		criteria.andStartDateLessThanOrEqualTo(now);
		criteria.andEndDateGreaterThan(now);
		List<GuideProhibit> guideProhibits = guideProhibitMapper.selectByExample(guideProhibitExample);		
		return guideProhibits;
	}
	
	/**
	 * 根据导游Id列表获取导游列表
	 * @param guideIds
	 * @return
	 */
	public List<GuideBean> getGuides(List<String> guideIds){
		GuideBeanExample guideBeanExample = new GuideBeanExample();
		GuideBeanExample.Criteria criteria = guideBeanExample.createCriteria();
		if(guideIds != null && guideIds.size() > 0) {
			criteria.andGuideIdIn(guideIds);
		}
		criteria.andStatusEqualTo(GuideStatus.NOMAL.value);
		List<GuideBean> guides = guideBeanMapper.selectByExample(guideBeanExample);

		return guides;
	}
	
	
}
