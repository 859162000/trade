/**
 * @Author lukangle
 * @2015年11月26日@下午6:26:45
 */
package com.hbc.api.trade.order.service.mis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hbc.api.trade.bdata.common.rsp.ListServiceRsp;
import com.hbc.api.trade.bdata.mapper.basedata.gen.bean.Car;
import com.hbc.api.trade.bdata.mapper.guide.gen.bean.GuideBean;
import com.hbc.api.trade.bdata.mapper.guide.gen.bean.GuideCar;
import com.hbc.api.trade.order.enums.deliver.DemandDeal;
import com.hbc.api.trade.order.enums.third.CarClassEnum;
import com.hbc.api.trade.order.enums.third.CarTypeEnum;
import com.hbc.api.trade.order.enums.third.GuideLevelEnums;
import com.hbc.api.trade.order.mapping.gen.bean.OrderBean;
import com.hbc.api.trade.order.mapping.gen.bean.TradeDeliverGuide;
import com.hbc.api.trade.order.mapping.gen.bean.TradeOrderDeliver;
import com.hbc.api.trade.order.mapping.genx.xbean.TradeDeliverGuideVoBean;
import com.hbc.api.trade.order.service.OrderQueryService;
import com.hbc.api.trade.order.service.deliver.TradeDeliverQueryService;
import com.hbc.api.trade.order.service.mis.rsp.MisDeliverGuideVo;
import com.hbc.api.trade.third.CarService;
import com.hbc.api.trade.third.GuideCarService;
import com.hbc.api.trade.third.GuideQueryService;

@Component
public class MisDeliverService {
	protected final static Logger log = LoggerFactory.getLogger(MisDeliverService.class);
	@Autowired
	OrderQueryService orderQueryService;
	@Autowired
	TradeDeliverQueryService tradeDeliverQueryService;
	@Autowired
	GuideQueryService guideQueryService;
	@Autowired
	GuideCarService guideCarService;
	@Autowired
	CarService carService;
	@Autowired
	MisGuideDeliverService misGuideDeliverService;

	/**
	 * MIS端发单列表
	 * 
	 * @return
	 */
	public ListServiceRsp<MisDeliverGuideVo> getDeliverOrderGuides(String orderNo, String dno, String guideNo, String guideName, int limit, int offset) {
		OrderBean orderBean = orderQueryService.getOrderByNo(orderNo);

		TradeOrderDeliver tradeOrderDeliver = tradeDeliverQueryService.getDeliverByDeliverNo(dno);
		List<TradeDeliverGuide> tradeDeliverGuides = misGuideDeliverService.getGuideOrdersByDeliverNoPage(orderBean, tradeOrderDeliver, guideNo, guideName, limit, offset);
		int count = misGuideDeliverService.countGuideOrdersByDeliverNo(orderBean, tradeOrderDeliver, guideNo, guideName);
		List<MisDeliverGuideVo> gvos = new ArrayList<MisDeliverGuideVo>();
		if (tradeDeliverGuides != null && tradeDeliverGuides.size() > 0) {
			gvos = convertToDguids(tradeOrderDeliver, tradeDeliverGuides);
		}
		ListServiceRsp<MisDeliverGuideVo> listServiceRsp = new ListServiceRsp<MisDeliverGuideVo>();
		listServiceRsp.setDatalist(gvos);
		listServiceRsp.setTsize(count);
		return listServiceRsp;
	}

	public ListServiceRsp<MisDeliverGuideVo> getDeliverOrderGuidesByType(String orderNo, Integer deliverType, String searchInfo, int limit, int offset) {
		OrderBean orderBean = orderQueryService.getOrderByNo(orderNo);
		List<TradeDeliverGuideVoBean> tradeDeliverGuides = misGuideDeliverService.getGuideOrdersByDeliverTypeNoPage(orderBean, deliverType, searchInfo, limit, offset);
		int count = misGuideDeliverService.countGuideOrdersByDeliverTypeNo(orderBean, deliverType, searchInfo);

		List<MisDeliverGuideVo> vos = null;
		if (tradeDeliverGuides != null && tradeDeliverGuides.size() > 0) {
			vos = this.convertToDguidsNew(tradeDeliverGuides);
		}

		ListServiceRsp<MisDeliverGuideVo> listServiceRsp = new ListServiceRsp<MisDeliverGuideVo>();
		listServiceRsp.setDatalist(vos == null ? new ArrayList<MisDeliverGuideVo>() : vos);
		listServiceRsp.setTsize(count);
		return listServiceRsp;
	}

	public List<TradeOrderDeliver> getOrderDelivers(String orderNo) {
		List<TradeOrderDeliver> delivers = tradeDeliverQueryService.getDeliversByOrderNo(orderNo);
		return delivers;
	}

	private List<MisDeliverGuideVo> convertToDguidsNew(List<TradeDeliverGuideVoBean> tradeDeliverGuides) {
		List<MisDeliverGuideVo> gvolist = new ArrayList<MisDeliverGuideVo>();
		List<String> guideIds = new ArrayList<String>();
		for (TradeDeliverGuideVoBean tradeDeliverGuide : tradeDeliverGuides) {
			guideIds.add(tradeDeliverGuide.getGuideId());
		}

		if (guideIds == null || guideIds.size() <= 0) {
			return gvolist;
		}

		List<GuideBean> glist = guideQueryService.getGuideByGids(guideIds);

		if (glist == null || glist.size() <= 0) {
			return gvolist;
		}

		Map<String, GuideBean> gmap = new HashMap<String, GuideBean>();
		for (GuideBean guideBean : glist) {
			gmap.put(guideBean.getGuideId(), guideBean);
		}

		for (TradeDeliverGuideVoBean tradeDeliverGuide : tradeDeliverGuides) {
			GuideBean guideBean = gmap.get(tradeDeliverGuide.getGuideId());
			if (guideBean != null) {
				GuideCar guideCar = guideCarService.getGuideCar(guideBean.getGuideId());
				MisDeliverGuideVo misDeliverGuideVo = new MisDeliverGuideVo();
				if (guideCar != null) {
					CarTypeEnum cartype = CarTypeEnum.getType(guideCar.getCarType());
					CarClassEnum carclass = CarClassEnum.getType(guideCar.getCarClass());

					misDeliverGuideVo.setCarId(guideCar.getCarId() + "");
					misDeliverGuideVo.setCarName(guideCar.getCarName());
					misDeliverGuideVo.setCarType(guideCar.getCarType());
					misDeliverGuideVo.setCarClass(guideCar.getCarClass());
					Car car = carService.getCar(guideCar.getCarId());
					misDeliverGuideVo.setCarModel(car == null ? "" : car.getCarModel());
					misDeliverGuideVo.setCarTypeName(cartype.name);
					misDeliverGuideVo.setCarClassName(carclass.desc);
				}

				misDeliverGuideVo.setAcptime(tradeDeliverGuide.getAcceptTime());
				GuideLevelEnums glevel = GuideLevelEnums.getType(guideBean.getGuideLevel());
				if (glevel != null) {
					misDeliverGuideVo.setGuideLevel(glevel.value);
					misDeliverGuideVo.setGuideLevelName(glevel.name);
				}

				DemandDeal demandDeal = DemandDeal.getType(tradeDeliverGuide.getDemandDeal());
				if (demandDeal != null) {
					misDeliverGuideVo.setDemandDeal(demandDeal.value);
					misDeliverGuideVo.setDemandDealName(demandDeal.name);
				}
				misDeliverGuideVo.setDeliverTime(tradeDeliverGuide.getDeliverTime());
				misDeliverGuideVo.setFirstSeeTime(tradeDeliverGuide.getFirstReadTime());
				misDeliverGuideVo.setGuideId(tradeDeliverGuide.getGuideId());
				misDeliverGuideVo.setGuideName(guideBean.getGuideName());
				misDeliverGuideVo.setGuideNo(guideBean.getGuideNo());
				misDeliverGuideVo.setLatestLoginTime(guideBean.getLatestLoginTime());
				misDeliverGuideVo.setPriceGuide(tradeDeliverGuide.getGuidePrice());
				misDeliverGuideVo.setRefusReason(tradeDeliverGuide.getRefuseReason());
				gvolist.add(misDeliverGuideVo);
			}
		}
		return gvolist;
	}

	private List<MisDeliverGuideVo> convertToDguids(TradeOrderDeliver tradeOrderDeliver, List<TradeDeliverGuide> tradeDeliverGuides) {
		List<MisDeliverGuideVo> gvolist = new ArrayList<MisDeliverGuideVo>();
		List<String> guideIds = new ArrayList<String>();
		for (TradeDeliverGuide tradeDeliverGuide : tradeDeliverGuides) {
			guideIds.add(tradeDeliverGuide.getGuideId());
		}

		List<GuideBean> glist = guideQueryService.getGuideByGids(guideIds);
		Map<String, GuideBean> gmap = new HashMap<String, GuideBean>();
		for (GuideBean guideBean : glist) {
			gmap.put(guideBean.getGuideId(), guideBean);
		}

		for (TradeDeliverGuide tradeDeliverGuide : tradeDeliverGuides) {
			GuideBean guideBean = gmap.get(tradeDeliverGuide.getGuideId());

			//			log.info(guideBean.getGuideId()+"@@@"+guideBean.getGuideId());
			GuideCar guideCar = guideCarService.getGuideCar(guideBean.getGuideId());

			Car car = carService.getCar(guideCar.getCarId());

			CarTypeEnum cartype = CarTypeEnum.getType(guideCar.getCarType());
			CarClassEnum carclass = CarClassEnum.getType(guideCar.getCarClass());
			MisDeliverGuideVo misDeliverGuideVo = new MisDeliverGuideVo();
			misDeliverGuideVo.setAcptime(tradeDeliverGuide.getAcceptTime());
			misDeliverGuideVo.setCarId(guideCar.getCarId() + "");
			misDeliverGuideVo.setCarModel(car.getCarModel());
			misDeliverGuideVo.setCarName(guideCar.getCarName());

			misDeliverGuideVo.setCarType(guideCar.getCarType());
			misDeliverGuideVo.setCarTypeName(cartype.name);
			misDeliverGuideVo.setCarClassName(carclass.desc);
			misDeliverGuideVo.setCarClass(guideCar.getCarClass());

			GuideLevelEnums glevel = GuideLevelEnums.getType(guideBean.getGuideLevel());
			DemandDeal demandDeal = DemandDeal.getType(tradeDeliverGuide.getDemandDeal());
			misDeliverGuideVo.setDeliverTime(tradeOrderDeliver.getDeliverTime());
			misDeliverGuideVo.setDemandDeal(demandDeal.value);
			misDeliverGuideVo.setDemandDealName(demandDeal.name);
			misDeliverGuideVo.setFirstSeeTime(tradeDeliverGuide.getFirstReadTime());
			misDeliverGuideVo.setGuideId(tradeDeliverGuide.getGuideId());
			misDeliverGuideVo.setGuideLevel(glevel.value);
			misDeliverGuideVo.setGuideLevelName(glevel.name);
			misDeliverGuideVo.setGuideName(guideBean.getGuideName());
			misDeliverGuideVo.setGuideNo(guideBean.getGuideNo());
			misDeliverGuideVo.setLatestLoginTime(guideBean.getLatestLoginTime());
			misDeliverGuideVo.setPriceGuide(tradeDeliverGuide.getGuidePrice());
			misDeliverGuideVo.setRefusReason(tradeDeliverGuide.getRefuseReason());

			gvolist.add(misDeliverGuideVo);
		}
		return gvolist;
	}
}
