/**
 * @Author lukangle
 * @2015年11月15日@下午4:26:57
 */
package com.hbc.api.trade.timer.service.deliver.order.pk.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hbc.api.trade.bdata.mapper.guide.gen.bean.GuideProhibit;
import com.hbc.api.trade.order.enums.deliver.DeliverPKFailType;
import com.hbc.api.trade.order.enums.third.GuideProhibitEnum;
import com.hbc.api.trade.order.mapping.gen.bean.OrderBean;
import com.hbc.api.trade.order.mapping.gen.bean.TradeDeliverGuide;
import com.hbc.api.trade.order.service.deliver.ConflictOrderService;
import com.hbc.api.trade.order.service.deliver.GuidDeliverOrderService;
import com.hbc.api.trade.order.service.deliver.conf.TradeCitySolrConf;
import com.hbc.api.trade.third.LControllerService;
import com.hbc.api.trade.timer.service.deliver.order.pk.IPkGuideService;
import com.hbc.api.trade.timer.service.deliver.order.pk.bean.PkEndBean;

@Component
public class RsptimePrioryService implements IPkGuideService {
	private final static Logger log = LoggerFactory.getLogger(RsptimePrioryService.class);
	@Autowired
	private GuidDeliverOrderService guidDeliverOrderService;
	@Autowired
	LControllerService lControllerService;

	@Override
	public List<TradeDeliverGuide> getAllFitGuides(String orderNo) {
		List<TradeDeliverGuide> deliverGuides = guidDeliverOrderService.getAllAcceptGuideOrders(orderNo);
		List<TradeDeliverGuide> result = new ArrayList<TradeDeliverGuide>();
		if(deliverGuides == null ||deliverGuides.size()==0){
			return result;
		}
		result.addAll(deliverGuides);		
		// check guide
		// 检查 是否禁止接单
		List<GuideProhibit> guideProhibits = lControllerService.getGuideProhibitPushByDeliverGuide(deliverGuides, GuideProhibitEnum.PROHIBIT_TYPE_RECEIVE);
		if(guideProhibits != null && guideProhibits.size()>0){
			for(GuideProhibit guideProhibit:guideProhibits){
				for (TradeDeliverGuide deliverGuide : deliverGuides) {
					if(guideProhibit.getGuideId().equals(deliverGuide.getGuideId())){
						result.remove(deliverGuide);
						break;
					}
				}
			}
			
		}	
		return result;
	}

	@Autowired
	ConflictOrderService conflictOrderService;

	@Override
	public TradeDeliverGuide getMostFitGuide(OrderBean orderBean, List<TradeDeliverGuide> tradeDeliverGuides, TradeCitySolrConf tradeCitySolrConf) {

		List<TradeDeliverGuide> trdeNoDguides = conflictOrderService.getNoConflictG(orderBean, tradeDeliverGuides, tradeCitySolrConf);
		TradeDeliverGuide tradeDeliverGuideR = null;
		for (TradeDeliverGuide tradeDeliverGuide : trdeNoDguides) {
			Date atime = tradeDeliverGuide.getAcceptTime();
			if (tradeDeliverGuideR == null) {
				tradeDeliverGuideR = tradeDeliverGuide;
			} else {
				Date tatime = tradeDeliverGuideR.getAcceptTime();
				if ((atime != null && tatime == null) || (atime != null && tatime != null && atime.before(tatime))) {
					tradeDeliverGuideR.setFailType(DeliverPKFailType.accepttime.value);
					tradeDeliverGuideR = tradeDeliverGuide;
				} else{
					tradeDeliverGuide.setFailType(DeliverPKFailType.accepttime.value);
				}
			}
		}
		return tradeDeliverGuideR;
	}
}
