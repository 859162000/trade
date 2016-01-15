package com.hbc.api.trade.timer.service.deliver.order.pk.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hbc.api.trade.bdata.mapper.guide.gen.bean.GuideBean;
import com.hbc.api.trade.bdata.mapper.guide.gen.bean.GuideProhibit;
import com.hbc.api.trade.order.enums.deliver.DeliverPKFailType;
import com.hbc.api.trade.order.enums.deliver.OrderSerialFlag;
import com.hbc.api.trade.order.enums.order.GoodType;
import com.hbc.api.trade.order.enums.order.OrderType;
import com.hbc.api.trade.order.enums.third.GuideProhibitEnum;
import com.hbc.api.trade.order.mapping.gen.OrderBeanMapper;
import com.hbc.api.trade.order.mapping.gen.TradeOrderSerialMapper;
import com.hbc.api.trade.order.mapping.gen.bean.OrderBean;
import com.hbc.api.trade.order.mapping.gen.bean.TradeDeliverGuide;
import com.hbc.api.trade.order.mapping.gen.bean.TradeOrderSerial;
import com.hbc.api.trade.order.mapping.gen.bean.TradeOrderSerialExample;
import com.hbc.api.trade.order.service.deliver.ConflictOrderService;
import com.hbc.api.trade.order.service.deliver.GuidDeliverOrderService;
import com.hbc.api.trade.order.service.deliver.OrderPkService;
import com.hbc.api.trade.order.service.deliver.conf.TradeCitySolrConf;
import com.hbc.api.trade.third.LControllerService;
import com.hbc.api.trade.timer.service.deliver.order.pk.IPkGuideService;

@Component
public class SerialPkService implements IPkGuideService {
	private final static Logger log = LoggerFactory.getLogger(SerialPkService.class);
	@Autowired
	private GuidDeliverOrderService guidDeliverOrderService;
	@Autowired
	OrderPkService orderPkService;
	@Autowired
	ConflictOrderService conflictOrderService;
	@Autowired
	LControllerService controllerService;
	@Autowired
	OrderBeanMapper orderBeanMapper;
	@Autowired
	LControllerService lControllerService;
	@Autowired
	TradeOrderSerialMapper tradeOrderSerialMapper;

	@Override
	public TradeDeliverGuide getMostFitGuide(OrderBean orderBean, List<TradeDeliverGuide> tradeDeliverGuides, TradeCitySolrConf tradeCitySolrConf) {
		// TODO Auto-generated method stub
		if (tradeDeliverGuides == null || tradeDeliverGuides.size() == 0) {
			return null;
		}
		// TradeCitySolrConf tradeCitySolrConf= new TradeCitySolrConf();
		// OrderBean orderBean=new OrderBean();

		// OrderBean orderBean =
		// orderBeanMapper.selectByPrimaryKey(tradeDeliverGuides.get(0).getOrderNo());
		// 检查冲突
		List<TradeDeliverGuide> guideTarge = new ArrayList<TradeDeliverGuide>();
		guideTarge.addAll(tradeDeliverGuides);
		List<TradeDeliverGuide> guideConflict = new ArrayList<TradeDeliverGuide>();
		// 开始地导游列表
		List<TradeDeliverGuide> guideLocal = new ArrayList<TradeDeliverGuide>();
		guideLocal.addAll(tradeDeliverGuides);
		// 结束地导游列表
		List<TradeDeliverGuide> guideEndLocal = new ArrayList<TradeDeliverGuide>();
		guideEndLocal.addAll(tradeDeliverGuides);
		// 途径导游列表
		List<TradeDeliverGuide> guidePassLocal = new ArrayList<TradeDeliverGuide>();
		guidePassLocal.addAll(tradeDeliverGuides);
		log.info("订单"+orderBean.getOrderNo()+" 已按照串单进行PK");
		for (TradeDeliverGuide tradeDeliverGuide : tradeDeliverGuides) {
			try {
				GuideBean guideBean = controllerService.getGuidByGuideId(tradeDeliverGuide.getGuideId());
				if (guideBean == null) {
					log.error(tradeDeliverGuide.getGuideId() + " 已经被删除PK 跳过该导游");
					guideConflict.add(tradeDeliverGuide);
					guideLocal.remove(tradeDeliverGuide);
					guideEndLocal.remove(tradeDeliverGuide);
					guidePassLocal.remove(tradeDeliverGuide);
					tradeDeliverGuide.setFailType(DeliverPKFailType.forbidpick.value);
				} else {
					if (OrderType.DAILY.value.equals(orderBean.getOrderType())
							|| (OrderType.COMMENDATION.value.equals(orderBean.getOrderType()) && !GoodType.PICKUPORDER.value.equals(orderBean.getOrderGoodsType()) && !GoodType.TRANSFER.value.equals(orderBean.getOrderGoodsType()) && !GoodType.PERUSE.value.equals(orderBean.getOrderGoodsType()))) {
						// 日租开始地导游优先
						if (!orderBean.getServiceCityId().equals(guideBean.getCityId())) {
							guideLocal.remove(tradeDeliverGuide);
						}
						// 日租结束地导游优先
						if (!orderBean.getServiceEndCityid().equals(guideBean.getCityId())) {
							guideEndLocal.remove(tradeDeliverGuide);
						}
						// 日租途径地导游优先
						if (!orderBean.getServicePassCity().contains(guideBean.getCityId() + "_")) {
							guidePassLocal.remove(tradeDeliverGuide);
						}
					}
				}
			} catch (Exception e) {
				log.error("", e);
			}
		}

		// 获取最优导游
		// String guideId = orderPkService.getPKGuideByServiceGrade(guideTarge);
		// 获取最优导游
		String guideId = "";
		if (OrderType.DAILY.value.equals(orderBean.getOrderType())
				|| (OrderType.COMMENDATION.value.equals(orderBean.getOrderType()) && !GoodType.PICKUPORDER.value.equals(orderBean.getOrderGoodsType()) && !GoodType.TRANSFER.value.equals(orderBean.getOrderGoodsType()) && !GoodType.PERUSE.value.equals(orderBean.getOrderGoodsType()))) {
			if (guideLocal.size() > 0) {
				guideId = orderPkService.getPKGuideByServiceGrade(guideLocal);
			} else if (guideEndLocal.size() > 0) {
				guideId = orderPkService.getPKGuideByServiceGrade(guideEndLocal);
			} else if (guidePassLocal.size() > 0) {
				guideId = orderPkService.getPKGuideByServiceGrade(guidePassLocal);
			} else {
				guideId = orderPkService.getPKGuideByServiceGrade(guideTarge);
			}
		} else {
			guideId = orderPkService.getPKGuideByServiceGrade(guideTarge);
		}
		if (guideId == null || guideId.trim().length() == 0 || guideId.trim().equals("0")) {
			return null;
		}
		TradeDeliverGuide result = new TradeDeliverGuide();
		for (TradeDeliverGuide tradeDeliverGuide : tradeDeliverGuides) {
			if (tradeDeliverGuide.getGuideId().equals(guideId)) {
				result = tradeDeliverGuide;
			} else {
				if (tradeDeliverGuide.getFailType() == null) {
					tradeDeliverGuide.setFailType(DeliverPKFailType.grade.value);
				}
				
//				guideConflict.add(tradeDeliverGuide);
			}

		}
		return result;

	}

	@Override
	public List<TradeDeliverGuide> getAllFitGuides(String orderNo) {
		// TODO Auto-generated method stub
		List<TradeDeliverGuide> deliverGuides = guidDeliverOrderService.getAllAcceptGuideOrders(orderNo);
		List<TradeDeliverGuide> result = new ArrayList<TradeDeliverGuide>();
		if (deliverGuides == null || deliverGuides.size() == 0) {
			return result;
		}
		// result.addAll(deliverGuides);

		// 获取被串单订单Id
		TradeOrderSerialExample tradeOrderSerialExample = new TradeOrderSerialExample();
		tradeOrderSerialExample.createCriteria().andOrderNoEqualTo(orderNo);
		List<TradeOrderSerial> tradeOrderSerials = tradeOrderSerialMapper.selectByExample(tradeOrderSerialExample);
		for (TradeDeliverGuide tradeDeliverGuide : deliverGuides) {
			for (TradeOrderSerial tradeOrderSerial : tradeOrderSerials) {
				if (tradeDeliverGuide.getGuideId().equals(tradeOrderSerial.getMeetGuideId())) {
					OrderBean orderSerial = orderBeanMapper.selectByPrimaryKey(tradeOrderSerial.getMeetOrderNo());
					if (orderSerial != null && (orderSerial.getSerialFlag() == null || OrderSerialFlag.NORMAL.value.equals(orderSerial.getSerialFlag()))) {
						result.add(tradeDeliverGuide);
					}
					break;
				}
			}
		}
		List<TradeDeliverGuide> resultTemp = new ArrayList<TradeDeliverGuide>();
		resultTemp.addAll(result);
		// check guide
		// 检查 是否禁止接单
		List<GuideProhibit> guideProhibits = lControllerService.getGuideProhibitPushByDeliverGuide(result, GuideProhibitEnum.PROHIBIT_TYPE_RECEIVE);
		if (guideProhibits != null && guideProhibits.size() > 0) {
			for (GuideProhibit guideProhibit : guideProhibits) {
				for (TradeDeliverGuide deliverGuide : resultTemp) {
					if (guideProhibit.getGuideId().equals(deliverGuide.getGuideId())) {
						result.remove(deliverGuide);
						break;
					}
				}
			}

		}
		return result;
	}

}
