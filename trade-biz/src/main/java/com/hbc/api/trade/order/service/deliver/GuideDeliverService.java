package com.hbc.api.trade.order.service.deliver;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.hbc.api.trade.bdata.common.util.DoubleUtil;
import com.hbc.api.trade.bdata.mapper.guide.gen.bean.GuideBean;
import com.hbc.api.trade.bdata.mapper.guide.gen.bean.GuideProhibit;
import com.hbc.api.trade.bdata.mapper.guide.genx.GxGuideMapper;
import com.hbc.api.trade.bdata.mapper.guide.genx.param.QGuideLimitParam;
import com.hbc.api.trade.order.enums.TradeReturnCodeEnum;
import com.hbc.api.trade.order.enums.deliver.OrderSerialFlag;
import com.hbc.api.trade.order.enums.order.OrderType;
import com.hbc.api.trade.order.enums.third.CarClassEnum;
import com.hbc.api.trade.order.enums.third.CarTypeEnum;
import com.hbc.api.trade.order.enums.third.GuideCropTypeEnum;
import com.hbc.api.trade.order.enums.third.GuideLevelEnums;
import com.hbc.api.trade.order.enums.third.GuideProhibitEnum;
import com.hbc.api.trade.order.enums.third.GuideSignStatus;
import com.hbc.api.trade.order.exception.TradeException;
import com.hbc.api.trade.order.mapping.gen.bean.OrderBean;
import com.hbc.api.trade.order.service.deliver.conf.TradeCitySolrConf;
import com.hbc.api.trade.order.service.solr.TradeConfCollectionService;
import com.hbc.api.trade.third.DailyGuideService;
import com.hbc.api.trade.third.GuideLevelService;
import com.hbc.api.trade.third.GuideQueryService;
import com.hbc.api.trade.third.LControllerService;

@Component
public class GuideDeliverService {
	private final static Logger log = LoggerFactory.getLogger(GuideDeliverService.class);

	@Autowired
	private GxGuideMapper gxGuideMapper;
	@Autowired
	private GuideQueryService guideQueryService;
	@Autowired
	GuideLevelService guideLevelService;
	@Autowired
	LControllerService lControllerService;
	@Autowired
	DailyGuideService dailyGuideService;
	/**
	 * 获取关联城市 导游 等级向上兼容
	 * 
	 * @param guideId
	 * @return
	 */
	public List<GuideBean> getDeliverGuidByCityGuideLevel(OrderBean orderBean) {
		int limit = 5000;
		int offset = 0;
		CarTypeEnum carTypeEnum = CarTypeEnum.getType(orderBean.getCarTypeId());
		CarClassEnum carClassEnum = CarClassEnum.getType(orderBean.getCarSeatNum());
		List<GuideBean> guideBeans = getGuidByCityGuideLevel(orderBean, carTypeEnum, carClassEnum, limit, offset);
		return guideBeans;
	}

	public void isVal(String guideId){
		GuideBean guideBean  = lControllerService.getGuidByGuideId(guideId);
		List<GuideProhibit> guideProhibits = lControllerService.getGuideProhibit(guideBean.getGuideId(),GuideProhibitEnum.PROHIBIT_TYPE_PUSH);
		if(guideProhibits.size()>0){
			throw new TradeException(TradeReturnCodeEnum.ORDER_AGREE_FAILED, guideBean.getGuideName());
		}
		List<GuideProhibit> guideProhibits2 = lControllerService.getGuideProhibit(guideBean.getGuideId(),GuideProhibitEnum.PROHIBIT_TYPE_RECEIVE);
		if(guideProhibits2.size()>0){
			throw new TradeException(TradeReturnCodeEnum.ORDER_AGREE_FAILED, guideBean.getGuideName());
		}
		
//		if(new Integer(0).equals(guideBean.getSendOrderFlag())){
//			throw new TradeException(TradeReturnCodeEnum.ORDER_AGREE_FAILED, guideBean.getGuideName());
//		}
	}
	/**
	 * 查询该订单 匹配的导游 若订单为日租 则会根据订单服务天数 匹配导游
	 * 
	 * @param orderBean
	 * @param carTypeEnum
	 * @param carClassEnum
	 * @param limit
	 * @param offset
	 * @return
	 */
	public List<GuideBean> getGuidByCityGuideLevel(OrderBean orderBean, CarTypeEnum carTypeEnum, CarClassEnum carClassEnum, int limit, int offset) {

		GuideLevelEnums guideLevelEnum = guideLevelService.getGuideLevByOrder(orderBean);
		OrderType orderType = OrderType.getTypeByGoodeType(orderBean);
		// 获取导游服务类型
		GuideCropTypeEnum guideCropTypeEnum = guideLevelService.getGuideCropLevelByOrder(orderBean);
		QGuideLimitParam qguideLimitParam = new QGuideLimitParam();
		qguideLimitParam.setCarClass(carClassEnum.value);
		qguideLimitParam.setCarType(carTypeEnum.value);
		qguideLimitParam.setCropType(guideCropTypeEnum.value);
		qguideLimitParam.setGuideLevel(guideLevelEnum.value);
		qguideLimitParam.setLimit(limit);
		qguideLimitParam.setOffset(offset);
		List<GuideBean> guides = new ArrayList<GuideBean>();
		if (OrderType.DAILY.equals(orderType)) {
			List<String> guidesList = dailyGuideService.getDailyGuideList(orderBean, carTypeEnum, carClassEnum, guideCropTypeEnum);
			qguideLimitParam.setGuideIds(guidesList);

			//若大范围没有相关导游 则直接退出
			if(guidesList.size()==0){
				return new ArrayList<GuideBean>();
			}
			 guides = gxGuideMapper.queryByOrderGuideLimit(qguideLimitParam);
			
		} else {
			qguideLimitParam.setCityId(orderBean.getServiceCityId());
			log.info("获取包车以外订单的可用导游范围,queryparam:" + JSON.toJSONString(qguideLimitParam));
			guides = gxGuideMapper.queryByOrderGuideLimit(qguideLimitParam);
		}
		
		if (guides.size() > 0) {
			// 检查 是否禁止听单
			List<GuideProhibit> guideProhibits = lControllerService.getGuideProhibitPush(guides,GuideProhibitEnum.PROHIBIT_TYPE_PUSH);
			if (guideProhibits.size() > 0) {
				List<GuideBean> guidesClone = new ArrayList<GuideBean>();
				guidesClone.addAll(guides);
				for (GuideBean guideBean : guidesClone) {
//					if (GuideSendOrderFlag.FORBID.value.equals(guideBean.getSendOrderFlag())){
//						guides.remove(guideBean);
//						continue;
//					}
					for (GuideProhibit guideProhibit : guideProhibits) {
						if (guideBean.getGuideId() .equals( guideProhibit.getGuideId())) {
							guides.remove(guideBean);
							break;
						}
					}
				}
			}
		}
		
		return guides;
	}


	/**
	 * 发单获取导游列表 主要用于 不限制导游的条件下 导游为 除通过考核 未删除的所有导游
	 * 
	 * @param orderBean
	 * @return
	 */
	public List<GuideBean> getDeliverGuidByCity(OrderBean orderBean) {
		CarTypeEnum carTypeEnum = CarTypeEnum.getType(orderBean.getCarTypeId());
		CarClassEnum carClassEnum = CarClassEnum.getType(orderBean.getCarSeatNum());

		OrderType orderType = OrderType.getTypeByGoodeType(orderBean);
		// 获取导游服务类型
		GuideCropTypeEnum guideCropTypeEnum = guideLevelService.getGuideCropLevelByOrder(orderBean);
		
		QGuideLimitParam qguideLimitParam = new QGuideLimitParam();
		qguideLimitParam.setCarClass(carClassEnum.value);
		qguideLimitParam.setCarType(carTypeEnum.value);
		qguideLimitParam.setCropType(guideCropTypeEnum.value);
		
		List<Integer> signStatus = new ArrayList<Integer>();
		signStatus.add(GuideSignStatus.SIGNSTATUS_EXAMED.value);
		signStatus.add(GuideSignStatus.SIGNSTATUS_TRAINED.value);
		qguideLimitParam.setSignStatus(signStatus);
		
		/**2015 1218 应测试要求 添加发单导游级别限制**/
		GuideLevelEnums guideLevelEnum = guideLevelService.getGuideLevByOrder(orderBean);
		qguideLimitParam.setGuideLevel(guideLevelEnum.value);
		
		List<GuideBean> guides = new ArrayList<GuideBean>();
		if (OrderType.DAILY.equals(orderType)) {
			List<String> guideList = dailyGuideService.getDailyGuideList(orderBean, carTypeEnum, carClassEnum, guideCropTypeEnum);
			qguideLimitParam.setGuideIds(guideList);
			//若大范围没有相关导游 则直接退出
			if(guideList.size()==0){
				return guides;
			}
		} else {
			qguideLimitParam.setCityId(orderBean.getServiceCityId());
		}
		guides =  gxGuideMapper.queryByOrderGuideLimit(qguideLimitParam);
		if (guides.size() > 0) {
			// 检查 是否禁止听单
			List<GuideProhibit> guideProhibits = lControllerService.getGuideProhibitPush(guides,GuideProhibitEnum.PROHIBIT_TYPE_PUSH);
			if (guideProhibits.size() > 0) {
				List<GuideBean> guidesClone = new ArrayList<GuideBean>();
				guidesClone.addAll(guides);
				for (GuideBean guideBean : guidesClone) {
//					if (GuideSendOrderFlag.FORBID.value.equals(guideBean.getSendOrderFlag())){
//						guides.remove(guideBean);
//						continue;
//					}
					for (GuideProhibit guideProhibit : guideProhibits) {
						if (guideBean.getGuideId() .equals( guideProhibit.getGuideId())) {
							guides.remove(guideBean);
							break;
						}
					}
				}
			}
		}

		return guides;
	}

	@Autowired
	TradeConfCollectionService tradeConfCollectionService;

	/**
	 * 获取导游对应的 价格
	 * 
	 * @param orderBean
	 * @param guideBean
	 * @return
	 */
	public double getGuideFloatPrice(OrderBean orderBean, GuideBean guideBean, TradeCitySolrConf tradeCitySolrConf) {
		if(tradeCitySolrConf==null){
			return orderBean.getPriceGuideBase() ;
		}
		
		if (OrderSerialFlag.SERIAL.value.equals(orderBean.getSerialFlag())) {
			double guidePriceFloat = DoubleUtil.multiplicationDouble(0.01d,tradeCitySolrConf.getSer_reduce_price());

			double guidePriceBase = orderBean.getPriceGuideBase();
			double fpriceguide = DoubleUtil.multiplicationDouble(orderBean.getPriceGuideBase() , guidePriceFloat);
			return  DoubleUtil.getNoFloat(DoubleUtil.addDouble(guidePriceBase, fpriceguide));
		}else{
			GuideLevelEnums guideLevelEnums = GuideLevelEnums.getType(guideBean.getGuideLevel());
			double guidePriceFloat = tradeCitySolrConf.guidePriceFloat(guideLevelEnums);

			double guidePriceBase = orderBean.getPriceGuideBase();
			
			double fpriceguide = DoubleUtil.multiplicationDouble(orderBean.getPriceGuideBase() , guidePriceFloat);
			double guidePrice = DoubleUtil.addDouble(guidePriceBase, fpriceguide);
			return DoubleUtil.getNoFloat(guidePrice);
		}
	}

	public double getGuideFloatPrice(OrderBean orderBean, GuideBean guideBean) {
		if (guideBean.getGuideLevel() == null) {
			return orderBean.getPriceGuideBase() ;
		}
		GuideLevelEnums guideLevelEnums = GuideLevelEnums.getType(guideBean.getGuideLevel());
		TradeCitySolrConf tradeCitySolrConf = tradeConfCollectionService.queryCityConf(orderBean);
		if(tradeCitySolrConf==null){
			return orderBean.getPriceGuideBase() ;
		}
		double guidePriceFloat = tradeCitySolrConf.guidePriceFloat(guideLevelEnums);

		double guidePrice = orderBean.getPriceGuideBase() + orderBean.getPriceGuideBase() * guidePriceFloat;
		return DoubleUtil.getNoFloat(guidePrice);
	}

}
