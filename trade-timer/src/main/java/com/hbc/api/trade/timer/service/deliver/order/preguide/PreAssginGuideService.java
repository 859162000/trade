/**
 * @Author lukangle
 * @2015年11月10日@下午5:03:09
 */
package com.hbc.api.trade.timer.service.deliver.order.preguide;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hbc.api.trade.TradeFinalStr;
import com.hbc.api.trade.bdata.common.util.DoubleUtil;
import com.hbc.api.trade.bdata.common.util.TimeConverter;
import com.hbc.api.trade.bdata.mapper.guide.gen.AgencyMapper;
import com.hbc.api.trade.bdata.mapper.guide.gen.bean.GuideBean;
import com.hbc.api.trade.order.enums.TradeReturnCodeEnum;
import com.hbc.api.trade.order.enums.bean.LogType;
import com.hbc.api.trade.order.enums.bean.OperationType;
import com.hbc.api.trade.order.enums.deliver.DeliverDemandDeal;
import com.hbc.api.trade.order.enums.deliver.DeliverType;
import com.hbc.api.trade.order.enums.deliver.GuidDeliverStatus;
import com.hbc.api.trade.order.enums.deliver.OrderDeliverStatus;
import com.hbc.api.trade.order.enums.deliver.TradeDeliverStatus;
import com.hbc.api.trade.order.enums.order.OrderStatus;
import com.hbc.api.trade.order.enums.order.PriceHistoryOpType;
import com.hbc.api.trade.order.exception.TradeException;
import com.hbc.api.trade.order.mapping.gen.OrderBeanMapper;
import com.hbc.api.trade.order.mapping.gen.bean.OrderBean;
import com.hbc.api.trade.order.mapping.gen.bean.OrderBeanExample;
import com.hbc.api.trade.order.mapping.gen.bean.TradeOrderDeliver;
import com.hbc.api.trade.order.mapping.genx.UpdateOrderBeanMapper;
import com.hbc.api.trade.order.mapping.genx.xbean.OrderLogParamBean;
import com.hbc.api.trade.order.service.OrderLogService;
import com.hbc.api.trade.order.service.TradePriceHistoryService;
import com.hbc.api.trade.order.service.deliver.GuidDeliverOrderService;
import com.hbc.api.trade.order.service.deliver.GuideAssignService;
import com.hbc.api.trade.order.service.deliver.GuideDeliverService;
import com.hbc.api.trade.order.service.deliver.OrderDeliverService;
import com.hbc.api.trade.order.service.deliver.TradeDeliverService;
import com.hbc.api.trade.third.LControllerService;
import com.hbc.api.trade.third.push.CPushService;
import com.hbc.api.trade.third.push.GPushService;

@Component
public class PreAssginGuideService {
	private final static Logger log = LoggerFactory.getLogger(PreAssginGuideService.class);
	@Autowired
	OrderBeanMapper orderBeanMapper;
	@Autowired
	UpdateOrderBeanMapper updateOrderBeanMapper;
	@Autowired
	LControllerService controllerService;
	@Autowired
	private TradeDeliverService tradeDeliverService;
	@Autowired
	private GuidDeliverOrderService guidDeliverOrderService;

	@Autowired
	GuideDeliverService guideDeliverService;
	
	@Autowired	
	AgencyMapper agencyMapper;
	@Autowired
	CPushService cpushService;
	@Autowired
	TradePriceHistoryService tradePriceHistoryService;
	@Autowired
	OrderDeliverService orderDeliverService;
	@Autowired
	GuideAssignService guideAssignService;
	@Autowired
	GPushService gpushService;
	@Autowired
	OrderLogService orderLogService;
	@Transactional(propagation=Propagation.REQUIRED)
	public void assginPreToGuide(OrderBean orderBean ){
		OrderBean orderBeanDb = updateOrderBeanMapper.forupdateByOrderNo(orderBean.getOrderNo());
		boolean isChangeGuide = TradeFinalStr.defaultGuideId.equals(orderBean.getGuideId());
		GuideBean guideBean = controllerService.getGuidByGuideId(orderBean.getGuidePreId());
		if (guideBean == null) {
			throw new TradeException(TradeReturnCodeEnum.NO_GUIDE, orderBean.getGuidePreId());
		}
		Timestamp curtime = new Timestamp(System.currentTimeMillis());
		Double priceGuide = orderBean.getPriceGuide();
		
		if(priceGuide.equals(orderBean.getPriceGuideBase())){
			//预设导游 需要若价格没修改 需要根据导游减价
			priceGuide = guideDeliverService.getGuideFloatPrice(orderBeanDb, guideBean);
			tradePriceHistoryService.deliverGuidePriceChange(orderBean.getOrderNo(), orderBean.getPriceGuide(), priceGuide,PriceHistoryOpType.DELIVER_GUIDE_LEVEL);
		}
		
		boolean isReward = false;
		if(orderBeanDb.getPriceReward()!=null){
			//订单奖励
			priceGuide = DoubleUtil.addDouble(priceGuide, orderBeanDb.getPriceReward());
			tradePriceHistoryService.deliverGuidePriceChange(orderBean.getOrderNo(), orderBean.getPriceGuide(), priceGuide,PriceHistoryOpType.REWARD);
			isReward = true;
			
		}
		
		
		
		TradeOrderDeliver tradeOrderDeliver = tradeDeliverService.insertDeliverOrder(orderBeanDb,TradeDeliverStatus.pkend,DeliverType.PRE_ASSIGN_GUIDE,curtime);
		
		guidDeliverOrderService.insertToGuide(tradeOrderDeliver, guideBean, orderBeanDb,priceGuide,DeliverDemandDeal.accept,GuidDeliverStatus.success,DeliverType.PRE_ASSIGN_GUIDE);
		
		guideAssignService.assignGuide(orderBean.getGuidePreId(), orderBean,  priceGuide, OrderStatus.PAYSUCCESS);
		
		OrderLogParamBean orderLogParamBean = new OrderLogParamBean();
		
		orderLogParamBean.setContent("导游 ["+guideBean.getGuideName()+"] 于 "+TimeConverter.formatDate(new Date())+" 接下此单");
		orderLogParamBean.setGuideId(guideBean.getGuideId());
		orderLogParamBean.setGuideName(guideBean.getGuideName());
		orderLogParamBean.setLogType(LogType.CONFIRMED_BY_GUIDE.value);
		orderLogParamBean.setOpType(OperationType.SYSTEM.value);
		orderLogParamBean.setOpUserName("预设导游 服务");
		orderLogParamBean.setOrderNo(orderBean.getOrderNo());		
		orderLogService.insertOrderLog(orderLogParamBean);
		
		
		if(isReward){
			gpushService.pushRewardServce(orderBeanDb);
		}
		cpushService.cGuideConfirm(orderBeanDb,isChangeGuide);
	}
	public List<OrderBean> preAssginGuide(){
		OrderBeanExample orderBeanExample = new OrderBeanExample();
		OrderBeanExample.Criteria criteria = orderBeanExample.createCriteria();
		criteria.andOrderStatusEqualTo(OrderStatus.PAYSUCCESS.value);
		criteria.andDeliverStatusEqualTo(OrderDeliverStatus.init.value);
		criteria.andDeliverTypeEqualTo(DeliverType.PRE_ASSIGN_GUIDE.value);
		criteria.andGuidePreIdIsNotNull();
		criteria.andGuideIdEqualTo(TradeFinalStr.defaultGuideId);
		return orderBeanMapper.selectByExample(orderBeanExample);
	}
}
