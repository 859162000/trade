/**
 * @Author lukangle
 * @2015年11月12日@下午8:42:24
 */
package com.hbc.api.trade.third;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import com.hbc.api.trade.bdata.common.cache.CacheFinal;
import com.hbc.api.trade.bdata.common.cache.TradeCacheManager;
import com.hbc.api.trade.bdata.mapper.guide.gen.GuideCarMapper;
import com.hbc.api.trade.bdata.mapper.guide.gen.bean.GuideBean;
import com.hbc.api.trade.bdata.mapper.guide.gen.bean.GuideCar;
import com.hbc.api.trade.bdata.mapper.guide.gen.bean.GuideCarExample;
import com.hbc.api.trade.bdata.mapper.guide.gen.bean.GuideProhibit;
import com.hbc.api.trade.bdata.mapper.guide.genx.GxGuideMapper;
import com.hbc.api.trade.bdata.mapper.guide.genx.param.QGuideLimitParam;
import com.hbc.api.trade.order.enums.order.OrderType;
import com.hbc.api.trade.order.enums.third.CarClassEnum;
import com.hbc.api.trade.order.enums.third.CarTypeEnum;
import com.hbc.api.trade.order.enums.third.GuideCropTypeEnum;
import com.hbc.api.trade.order.enums.third.GuideJobTypeEnum;
import com.hbc.api.trade.order.enums.third.GuideLevelEnums;
import com.hbc.api.trade.order.enums.third.GuideProhibitEnum;
import com.hbc.api.trade.order.enums.third.GuideSendOrderFlag;
import com.hbc.api.trade.order.enums.third.GuideSignStatus;
import com.hbc.api.trade.order.mapping.gen.bean.OrderBean;
import com.hbc.api.trade.order.service.deliver.GuideDeliverService;
import com.hbc.api.trade.third.rsp.GuideRsp;

@Component
public class MisGuideService {
	private final static Logger log = LoggerFactory.getLogger(MisGuideService.class);

	@Autowired
	private GxGuideMapper gxGuideMapper;
	@Autowired
	private GuideQueryService guideQueryService;
	@Autowired
	GuideLevelService guideLevelService;
	@Autowired
	private TradeCacheManager tradeCacheManager;
	@Autowired
	DailyGuideService dailyGuideService;
	@Autowired
	GuideCarMapper guideCarMapper;
	@Autowired
	GuideDeliverService guideDeliverService;
	@Autowired
	LControllerService lControllerService;
	/**
	 * mis端调用 查询该订单 匹配的导游 若订单为日租 则会根据订单服务天数 匹配导游
	 * 
	 * @param orderBean
	 * @param carTypeEnum
	 * @param carClassEnum
	 * @param limit
	 * @param offset
	 * @return
	 */
	public List<GuideRsp> getGuidByCityGuideLevel(OrderBean orderBean, CarTypeEnum carTypeEnum, CarClassEnum carClassEnum, int limit, int offset,String guideNo,String guideName) {

		List<GuideRsp> rsps = new ArrayList<GuideRsp>();
		GuideLevelEnums guideLevelEnum = guideLevelService.getGuideLevByOrder(orderBean);
		OrderType orderType = OrderType.getTypeByGoodeType(orderBean);
		GuideCropTypeEnum guideCropTypeEnum = guideLevelService.getGuideCropLevelByOrder(orderBean);

		QGuideLimitParam qguideLimitParam = new QGuideLimitParam();
		qguideLimitParam.setCarClass(carClassEnum.value);
		qguideLimitParam.setCarType(carTypeEnum.value);
		qguideLimitParam.setCropType(guideCropTypeEnum.value);
		qguideLimitParam.setGuideLevel(guideLevelEnum.value);
		qguideLimitParam.setLimit(limit);
		qguideLimitParam.setOffset(offset);
		qguideLimitParam.setGuideName(guideName);
		qguideLimitParam.setGuideNo(guideNo);
		
		List<Integer> signStatus = new ArrayList<Integer>();
		signStatus.add(GuideSignStatus.SIGNSTATUS_TRAINED.value);
		qguideLimitParam.setSignStatus(signStatus);
		
		if (OrderType.DAILY.equals(orderType)) {
			List<String> guideIdsList = dailyGuideService.getDailyGuideList(orderBean, carTypeEnum, carClassEnum, guideCropTypeEnum);
			if(guideIdsList.size()==0){
				//若大范围没有相关导游 则直接退出
				return rsps;
			}
			qguideLimitParam.setGuideIds(guideIdsList);
		} else {
			qguideLimitParam.setCityId(orderBean.getServiceCityId());
		}

		List<GuideBean> guideList = gxGuideMapper.queryByOrderGuideLimit(qguideLimitParam);
		//TODO lkl 删除
//		guideList = gxGuideMapper.queryAllByOrderGuideLimit(qguideLimitParam);
		
		if (guideList.size() > 0) {
			// 检查 是否禁止听单
			List<GuideProhibit> guideProhibits = lControllerService.getGuideProhibitPush(guideList,GuideProhibitEnum.PROHIBIT_TYPE_PUSH);
			if (guideProhibits.size() > 0) {
				List<GuideBean> guidesClone = new ArrayList<GuideBean>();
				guidesClone.addAll(guideList);
				for (GuideBean guideBean : guidesClone) {
//					if (GuideSendOrderFlag.FORBID.value.equals(guideBean.getSendOrderFlag())){
//						guideList.remove(guideBean);
//						continue;
//					}
					for (GuideProhibit guideProhibit : guideProhibits) {
						if (guideBean.getGuideId() .equals( guideProhibit.getGuideId())) {
							guideList.remove(guideBean);
							break;
						}
					}
				}
			}
		}
		
		for (GuideBean guideBean : guideList) {
			GuideRsp guideRsp = new GuideRsp();
			GuideLevelEnums guideLeve = GuideLevelEnums.getType(guideBean.getGuideLevel());
			if(guideLeve!=null){
				guideRsp.setGuideLevel(guideLeve.value);
				guideRsp.setGuideLevelName(guideLeve.name);
			}
			
			if(guideBean.getJobType()!=null){
				GuideJobTypeEnum guideJobTypeEnum = GuideJobTypeEnum.getType(guideBean.getJobType().intValue());
				if(guideJobTypeEnum!=null){
					guideRsp.setJobTypeName(guideJobTypeEnum.name);
					guideRsp.setJobType(guideBean.getJobType().intValue());
				}
			}
			guideRsp.setGuideId(guideBean.getGuideId());
			guideRsp.setGuideName(guideBean.getGuideName());
			guideRsp.setGuideNo(guideBean.getGuideNo());
			guideRsp.setLatestLoginTime(guideBean.getLatestLoginTime());

			GuideCarExample guideCarExample = new GuideCarExample();
			GuideCarExample.Criteria crc = guideCarExample.createCriteria();
			crc.andGuideIdEqualTo(guideBean.getGuideId());
			List<GuideCar> cars = guideCarMapper.selectByExample(guideCarExample);
			if(cars.size()==1){
				GuideCar guideCar = cars.get(0);
				CarClassEnum carClassEnumD = CarClassEnum.getType(guideCar.getCarClass());
				CarTypeEnum carTypeEnumD = CarTypeEnum.getType(guideCar.getCarType());
				if(carClassEnumD!=null){
					guideRsp.setCarClass(carClassEnumD.value);
					guideRsp.setCatClassName(carClassEnumD.name());
				}
				
				if(carTypeEnumD!=null){
					guideRsp.setCarType(carTypeEnumD.value);
					guideRsp.setCatTypeName(carTypeEnumD.name);
				}
				guideRsp.setCarId(guideCar.getCarId()+"");
				guideRsp.setCarName(guideCar.getCarName());
				double guidePrice = orderBean.getPriceGuide();
				if(guideBean.getGuideLevel()!=null){
					guidePrice = guideDeliverService.getGuideFloatPrice(orderBean, guideBean);
				}
				guideRsp.setPriceGuide(guidePrice);
				
			}
			rsps.add(guideRsp);
		}

		return rsps;
	}

	/**
	 * mis端调用
	 * 
	 * @param orderBean
	 * @param carTypeEnum
	 * @param carClassEnum
	 * @param cityId
	 * @return
	 */
	public int countGuidByCityGuideLevel(OrderBean orderBean, CarTypeEnum carTypeEnum, CarClassEnum carClassEnum,String guideNo,String guideName) {

		GuideLevelEnums guideLevelEnum = guideLevelService.getGuideLevByOrder(orderBean);
		OrderType orderType = OrderType.getTypeByGoodeType(orderBean);
		GuideCropTypeEnum guideCropTypeEnum = guideLevelService.getGuideCropLevelByOrder(orderBean);

		QGuideLimitParam qguideLimitParam = new QGuideLimitParam();
		qguideLimitParam.setCarClass(carClassEnum.value);
		qguideLimitParam.setCarType(carTypeEnum.value);
		qguideLimitParam.setCropType(guideCropTypeEnum.value);
		qguideLimitParam.setGuideLevel(guideLevelEnum.value);
		qguideLimitParam.setGuideName(guideName);
		qguideLimitParam.setGuideNo(guideNo);
		List<Integer> signStatus = new ArrayList<Integer>();
		signStatus.add(GuideSignStatus.SIGNSTATUS_TRAINED.value);
		qguideLimitParam.setSignStatus(signStatus);
		if (OrderType.DAILY.equals(orderType)) {
			List<String> guideIdsList = dailyGuideService.getDailyGuideList(orderBean, carTypeEnum, carClassEnum, guideCropTypeEnum);
			
			if(guideIdsList.size()==0){
				return 0;
			}
			qguideLimitParam.setGuideIds(guideIdsList);
		} else {
			qguideLimitParam.setCityId(orderBean.getServiceCityId());
		}

		return gxGuideMapper.countByOrderGuideIds(qguideLimitParam);
	}

}
