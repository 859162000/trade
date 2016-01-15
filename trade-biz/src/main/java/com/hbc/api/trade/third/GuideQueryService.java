/**
 * @Author lukangle
 * @2015年11月5日@下午3:06:59
 */
package com.hbc.api.trade.third;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hbc.api.trade.bdata.mapper.guide.gen.GuideBeanMapper;
import com.hbc.api.trade.bdata.mapper.guide.gen.GuideCropMapper;
import com.hbc.api.trade.bdata.mapper.guide.gen.bean.GuideBean;
import com.hbc.api.trade.bdata.mapper.guide.gen.bean.GuideBeanExample;
import com.hbc.api.trade.bdata.mapper.guide.gen.bean.GuideCrop;
import com.hbc.api.trade.bdata.mapper.guide.gen.bean.GuideCropExample;
import com.hbc.api.trade.order.enums.third.GuideCropTypeEnum;

@Component
public class GuideQueryService {
	@Autowired private GuideBeanMapper 			guideBeanMapper;
	@Autowired private GuideCropMapper 			guideCropMapper;
	
	
	public GuideBean getGuideBeanById(String guideId){
		return guideBeanMapper.selectByPrimaryKey(guideId);
	}
	
	public List<GuideBean> getGuideBeansByCity(int cityId){
		GuideBeanExample guideBeanExample = new GuideBeanExample();
		GuideBeanExample.Criteria criteria = guideBeanExample.createCriteria();
		criteria.andCityIdEqualTo(cityId);
		return guideBeanMapper.selectByExample(guideBeanExample);
	}
	
	/**
	 * 获取客服务城市
	 * @param guideId
	 * @return
	 */
	public Set<String> getGuideCoupsByCityIds(List<Integer> cityIds,GuideCropTypeEnum cropType){
		GuideCropExample guideCropExample = new GuideCropExample();
		GuideCropExample.Criteria criteria= guideCropExample.createCriteria();
		criteria.andCropTypeEqualTo(cropType.value);
		if(cityIds != null && cityIds.size() > 0) {
			criteria.andCityIdIn(cityIds);
		}
		List<GuideCrop> gcorps = guideCropMapper.selectByExample(guideCropExample);
		Set<String> guideIds = new HashSet<String>();
		for(GuideCrop guideCrop : gcorps){
			guideIds.add(guideCrop.getGuideId());
		}
		return guideIds;
	}
	public List<GuideBean> getGuideByGids(List<String> gidList) {
		GuideBeanExample guideBeanExample = new GuideBeanExample();
		GuideBeanExample.Criteria criteria = guideBeanExample.createCriteria();
		criteria.andGuideIdIn(gidList);
		return guideBeanMapper.selectByExample(guideBeanExample);
	}
}
