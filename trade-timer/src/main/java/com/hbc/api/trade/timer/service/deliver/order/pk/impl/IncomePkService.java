/**
 * @Author lukangle
 * @2015年11月13日@下午6:01:09
 */
package com.hbc.api.trade.timer.service.deliver.order.pk.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hbc.api.trade.bdata.mapper.guide.gen.bean.GuideBean;
import com.hbc.api.trade.bdata.mapper.guide.gen.bean.GuideProhibit;
import com.hbc.api.trade.order.enums.deliver.DeliverPKFailType;
import com.hbc.api.trade.order.enums.deliver.OrderSerialFlag;
import com.hbc.api.trade.order.enums.third.GuideProhibitEnum;
import com.hbc.api.trade.order.mapping.gen.OrderBeanMapper;
import com.hbc.api.trade.order.mapping.gen.bean.OrderBean;
import com.hbc.api.trade.order.mapping.gen.bean.TradeDeliverGuide;
import com.hbc.api.trade.order.service.deliver.ConflictOrderService;
import com.hbc.api.trade.order.service.deliver.GuidDeliverOrderService;
import com.hbc.api.trade.order.service.deliver.OrderPkService;
import com.hbc.api.trade.order.service.deliver.conf.TradeCitySolrConf;
import com.hbc.api.trade.third.LControllerService;
import com.hbc.api.trade.timer.service.deliver.order.pk.IPkGuideService;

@Service
public class IncomePkService implements IPkGuideService {

	private final static Logger log = LoggerFactory.getLogger(IncomePkService.class);
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hbc.api.trade.timer.service.deliver.order.pk.IPkGuideService#
	 * getMostFitGuide(java.util.List)
	 */
	@Override
	public TradeDeliverGuide getMostFitGuide(OrderBean orderBean, List<TradeDeliverGuide> tradeDeliverGuides, TradeCitySolrConf tradeCitySolrConf) {
		// 检查冲突
		List<TradeDeliverGuide> guideTarge = new ArrayList<TradeDeliverGuide>();
		guideTarge.addAll(tradeDeliverGuides);

		for (TradeDeliverGuide tradeDeliverGuide : tradeDeliverGuides) {
			try {
				GuideBean guideBean = controllerService.getGuidByGuideId(tradeDeliverGuide.getGuideId());
				if (guideBean == null) {
					log.error(tradeDeliverGuide.getGuideId() + " 已经被删除PK 跳过该导游");
					// guideConflict.add(tradeDeliverGuide);
					guideTarge.remove(tradeDeliverGuide);
					tradeDeliverGuide.setFailType(DeliverPKFailType.forbidpick.value);

				} else {
					if (OrderSerialFlag.SERIAL.value.equals(orderBean.getSerialFlag())) {// 串单
																				// 不校验
																				// 冲突

					} else {
						boolean isConflict = conflictOrderService.isConflict(orderBean, guideBean.getGuideId(), tradeCitySolrConf);
						if (isConflict) {							
							guideTarge.remove(tradeDeliverGuide);
							tradeDeliverGuide.setFailType(DeliverPKFailType.conflict.value);

						} 
					}

				}
			} catch (Exception e) {
				log.error("", e);
			}
		}

		// 获取价格最优导游
		 String guideId = orderPkService.getPKGuideByIncome(guideTarge,orderBean);
		
		TradeDeliverGuide result = new TradeDeliverGuide();
		for (TradeDeliverGuide tradeDeliverGuide : tradeDeliverGuides) {
			if (tradeDeliverGuide.getGuideId().trim().equals(guideId)) {
				result = tradeDeliverGuide;				
			}else{
				if (tradeDeliverGuide.getFailType() == null) {
					tradeDeliverGuide.setFailType(DeliverPKFailType.price.value);
				}
				
			}
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hbc.api.trade.timer.service.deliver.order.pk.IPkGuideService#
	 * getAllFitGuides(java.lang.String)
	 */
	@Override
	public List<TradeDeliverGuide> getAllFitGuides(String orderNo) {
		List<TradeDeliverGuide> deliverGuides = guidDeliverOrderService.getAllAcceptGuideOrders(orderNo);
		List<TradeDeliverGuide> result = new ArrayList<TradeDeliverGuide>();
		if (deliverGuides == null || deliverGuides.size() == 0) {
			return result;
		}
		result.addAll(deliverGuides);

		// check guide
		// 检查 是否禁止接单
		List<GuideProhibit> guideProhibits = lControllerService.getGuideProhibitPushByDeliverGuide(deliverGuides, GuideProhibitEnum.PROHIBIT_TYPE_RECEIVE);
		if (guideProhibits != null && guideProhibits.size() > 0) {
			for (GuideProhibit guideProhibit : guideProhibits) {
				for (TradeDeliverGuide deliverGuide : deliverGuides) {
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
