/**
 * @Author lukangle
 * @2015年12月16日@下午2:05:34
 */
package com.hbc.api.trade.timer.service.ota;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.hbc.api.trade.bdata.mapper.guide.gen.bean.GuideBean;
import com.hbc.api.trade.order.enums.TradeReturnCodeEnum;
import com.hbc.api.trade.order.enums.ota.AgentChannelEnum;
import com.hbc.api.trade.order.exception.TradeException;
import com.hbc.api.trade.order.mapping.gen.bean.OrderBean;
import com.hbc.api.trade.order.service.OrderQueryService;
import com.hbc.api.trade.ota.mapping.gen.TradeThirdOrderMapper;
import com.hbc.api.trade.ota.mapping.gen.bean.TradeThirdOrder;
import com.hbc.api.trade.ota.mapping.gen.bean.TradeThirdOrderExample;
import com.hbc.api.trade.timer.service.ota.req.CallbackBean;

@Component
public class OtaOrderService {
	private final static Logger log = LoggerFactory.getLogger(OtaOrderService.class);
	@Autowired private TradeThirdOrderMapper tradeThirdOrderMapper;
	@Autowired private OrderQueryService qrderQueryService;
	
	@Autowired private CTripCallbackService 	cTripCallbackService;
	@Autowired private QuaCallbackService 		quaCallbackService;
	@Autowired private QunarCallbackService 	qunarCallbackService;
	
	public List<TradeThirdOrder> getAllGuideConfirmOrders(){
		TradeThirdOrderExample tradeThirdOrderExample = new TradeThirdOrderExample();
		TradeThirdOrderExample.Criteria criteria  = tradeThirdOrderExample.createCriteria();
		criteria.andGuideConfirmTimeIsNotNull();
		return tradeThirdOrderMapper.selectByExample(tradeThirdOrderExample);
	}
	/**
	 * OTA导游确认
	 * @param tradeThirdOrder
	 * @param guideBean
	 * @param orderBean
	 */
	@Transactional
	public void confirmGuide(TradeThirdOrder tradeThirdOrder,GuideBean guideBean,OrderBean orderBean){
		log.info("开始确认导游");
		if(tradeThirdOrder == null) {
			log.error("更新订单表失败，确认导游失败。");
			throw new TradeException(TradeReturnCodeEnum.ORDER_NOTEXIST, "OTA");
		}
		
		// push司机信息（车导）
		OTACallbackService callbackService = null;
		AgentChannelEnum channel = AgentChannelEnum.getType(orderBean.getOrderChannel());
		switch(channel) {
		case CTRIP_CHANNEL:
			log.info("【携程】已经确认导游 "+guideBean.getGuideId());
			callbackService = cTripCallbackService;
			break;
		case QUA_CHANNEL:
			log.info("【去啊】已经确认导游 "+guideBean.getGuideId());
			callbackService = quaCallbackService;
			break;
		case QUNAR_CHANNEL:
			log.info("【去哪儿】已经确认导游 "+guideBean.getGuideId());
			callbackService = qunarCallbackService;
			break;
		default:
			log.error("渠道不支持");
			throw new TradeException(TradeReturnCodeEnum.ORDER_CHANEL_NOEXIST, channel.value);
		}
		
		if(callbackService != null) {
			callbackService.pushDriverInfo(new CallbackBean(tradeThirdOrder, guideBean, orderBean) );
			log.info("第三方订单【"+tradeThirdOrder.getOrderNo()+"】已确认导游");
		}
	}
	/**
	 * 订单确认
	 * @param tradeThirdOrder
	 */
	@Transactional
	public void confirmOrder(TradeThirdOrder tradeThirdOrder){
		log.info("第三方订单【"+tradeThirdOrder.getOrderNo()+"】开始确认订单");
		// 确认订单 
		OrderBean orderBean = qrderQueryService.getOrderByNo(tradeThirdOrder.getOrderNo());
		AgentChannelEnum channel = AgentChannelEnum.getType(orderBean.getOrderChannel());
		OTACallbackService callbackService = null;
		switch(channel) {
		case CTRIP_CHANNEL:
			log.info("【携程】开始确认订单");
			callbackService = cTripCallbackService;
			break;
		case QUA_CHANNEL:
			log.info("【去啊】开始确认订单");
			callbackService = quaCallbackService;
			break;
		case QUNAR_CHANNEL:
			log.info("【去哪儿】开始确认订单");
			callbackService = qunarCallbackService;
			break;
		default:
			log.error("渠道不支持");
			throw new TradeException(TradeReturnCodeEnum.ORDER_CHANEL_NOEXIST, channel.value);
		}
		if(callbackService != null) {
			callbackService.confrimOrder(new CallbackBean(tradeThirdOrder, null, orderBean) );
			log.info("第三方订单【"+tradeThirdOrder.getOrderNo()+"】已确认订单");
		}
	}
	public List<TradeThirdOrder> getAllConfirmOrders(){
		TradeThirdOrderExample tradeThirdOrderExample = new TradeThirdOrderExample();
		TradeThirdOrderExample.Criteria criteria  = tradeThirdOrderExample.createCriteria();
		criteria.andOrderConfirmTimeIsNotNull();
		return tradeThirdOrderMapper.selectByExample(tradeThirdOrderExample);
	}
	
	public TradeThirdOrder getByOrderNo(String orderNo){
		return tradeThirdOrderMapper.selectByPrimaryKey(orderNo);
	}
	/**
	 * 结算完成
	 * @param orderBean
	 */

	@Transactional
	public void settleOrderBean(OrderBean orderBean){
		log.info("第三方订单【"+orderBean.getOrderNo()+"】开始结算");
		TradeThirdOrder tradeDThirdOrder = new TradeThirdOrder();
		tradeDThirdOrder.setOrderNo(orderBean.getOrderNo());
		tradeDThirdOrder.setThirdTradeNo(orderBean.getThirdTradeNo());

		// 结算完成push
		OTACallbackService callbackService = null;
		AgentChannelEnum channel = AgentChannelEnum.getType(orderBean.getOrderChannel());
		switch(channel) {
		case CTRIP_CHANNEL:
			log.info("【携程】开始结算");
			callbackService = cTripCallbackService;
			break;
		case QUA_CHANNEL:
			log.info("【去啊】开始结算");
			callbackService = quaCallbackService;
			break;
		case QUNAR_CHANNEL:
			log.info("【去哪儿】开始结算");
			callbackService = qunarCallbackService;
			break;
		default:
			log.error("渠道不支持");
			throw new TradeException(TradeReturnCodeEnum.ORDER_CHANEL_NOEXIST, channel.value);
		}
		if(callbackService != null) {
			tradeDThirdOrder.setThirdTradeNo(orderBean.getThirdTradeNo());
			callbackService.pushWhenOrderCompleted(new CallbackBean(tradeDThirdOrder, null, orderBean) );
			log.info("第三方订单【"+orderBean.getOrderNo()+"】已结算完成");
		}
	}
}
