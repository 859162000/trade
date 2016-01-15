/**
 * @Author lukangle
 * @2015年10月27日@上午11:02:36
 */
package com.hbc.api.trade.order.service.deliver;

import java.sql.Timestamp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.hbc.api.trade.TradeFinalStr;
import com.hbc.api.trade.bdata.common.util.DoubleUtil;
import com.hbc.api.trade.bdata.mapper.guide.gen.bean.GuideBean;
import com.hbc.api.trade.order.enums.TradeReturnCodeEnum;
import com.hbc.api.trade.order.enums.deliver.DeliverDemandDeal;
import com.hbc.api.trade.order.enums.deliver.DeliverType;
import com.hbc.api.trade.order.enums.deliver.GuidDeliverStatus;
import com.hbc.api.trade.order.enums.deliver.OrderDeliverStatus;
import com.hbc.api.trade.order.enums.deliver.OrderSerialFlag;
import com.hbc.api.trade.order.enums.deliver.TradeDeliverStatus;
import com.hbc.api.trade.order.enums.order.OrderLogType;
import com.hbc.api.trade.order.enums.order.OrderStatus;
import com.hbc.api.trade.order.enums.order.PriceHistoryOpType;
import com.hbc.api.trade.order.exception.TradeException;
import com.hbc.api.trade.order.mapping.gen.bean.OrderBean;
import com.hbc.api.trade.order.mapping.gen.bean.TradeOrderDeliver;
import com.hbc.api.trade.order.mapping.genx.UpdateOrderBeanMapper;
import com.hbc.api.trade.order.mapping.genx.xbean.OrderLogParamBean;
import com.hbc.api.trade.order.service.OrderLogService;
import com.hbc.api.trade.order.service.OrderQueryService;
import com.hbc.api.trade.order.service.OrderService;
import com.hbc.api.trade.order.service.TradePriceHistoryService;
import com.hbc.api.trade.order.service.kafka.TradeKafkaMsgSender;
import com.hbc.api.trade.third.LControllerService;
import com.hbc.api.trade.third.push.CPushService;
import com.hbc.api.trade.third.sms.SMSService;

@Component
public class OptTradeDeliverService {
	private final static Logger log = LoggerFactory.getLogger(OptTradeDeliverService.class);
	@Autowired
	private TradeDeliverService tradeDeliverService;
	@Autowired
	private GuidDeliverOrderService guidDeliverOrderService;
	@Autowired
	OrderQueryService orderQueryService;
	@Autowired
	UpdateOrderBeanMapper updateOrderBeanMapper;
	@Autowired
	LControllerService controllerService;
	@Autowired
	TradeKafkaMsgSender tradeKafkaMsgSender;
	@Autowired
	SMSService smsservice;

	@Autowired
	private OrderLogService orderLogService;

	/**
	 * 
	 * @param orderNo
	 * @param guideId
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void assignGuide(String orderNo, String guideId, String opId, String opName) {
		OrderBean orderBeanDb = orderQueryService.getOrderByNo(orderNo);
		if (orderBeanDb != null) {
			guideDeliverService.isVal(guideId);
			OrderStatus orderStatus = OrderStatus.getStatus(orderBeanDb.getOrderStatus());
			if (orderStatus.equals(OrderStatus.INITSTATE)) {
				preToGuide(orderNo, guideId, opId, opName);
			} else {
				forceToGuide(orderNo, guideId, DeliverType.assign_GUIDE, opId, opName);
			}
		} else {
			throw new TradeException(TradeReturnCodeEnum.ORDER_NOTEXIST, orderNo);
		}
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void preToGuide(String orderNo, String guideId, String opId, String opName) {
		OrderBean orderBeanDb = updateOrderBeanMapper.forupdateByOrderNo(orderNo);
		GuideBean guideBean = controllerService.getGuidByGuideId(guideId);
		if (guideBean == null) {
			throw new TradeException(TradeReturnCodeEnum.NO_GUIDE, guideId);
		}
		if (TradeFinalStr.defaultGuideId.equals(orderBeanDb.getGuideId()) || orderBeanDb.getGuideId() == null) {
			int ors = updateOrderBeanMapper.preOrderGuid(guideId, DeliverType.PRE_ASSIGN_GUIDE.value, OrderDeliverStatus.init.value, orderNo);
			if (ors == 1) {
				logGuideAssign(orderNo, guideBean, opId, opName, "预指派导游成功");
				log.info("success [" + orderBeanDb.getOrderNo() + "] pre to guide [" + JSON.toJSONString(guideBean) + "] ");
			} else {
				logGuideAssign(orderNo, guideBean, opId, opName, "预指派导游失败");
				throw new TradeException(TradeReturnCodeEnum.PRE_DELIVER_FAILED, orderNo);
			}
		} else {
			logGuideAssign(orderNo, guideBean, opId, opName, "已接单，预指派失败");
			throw new TradeException(TradeReturnCodeEnum.DELIVER_GUIDE_ALIDYACCEPT, orderBeanDb.getGuideId());
		}
	}

	private void logGuideAssign(String orderNo, GuideBean guideBean, String opId, String opName, String value) {
		OrderLogParamBean orderLogParamBean = new OrderLogParamBean();
		orderLogParamBean.setLogType(OrderLogType.ASSIGN_GUIDE.type);
		orderLogParamBean.setGuideId(guideBean.getGuideId());
		orderLogParamBean.setOrderNo(orderNo);
		orderLogParamBean.setOpUserId(opId);
		orderLogParamBean.setOpUserName(opName);
		orderLogParamBean.setOpType(4);
		orderLogParamBean.setValue(value);
		orderLogParamBean.setContent(opName+ "	"+ value+"【" + guideBean.getGuideName() + "】成功");
		orderLogService.insertOrderLog(orderLogParamBean);
	}

	@Autowired
	GuideDeliverService guideDeliverService;
	@Autowired
	CPushService cpushService;
	@Autowired
	TradePriceHistoryService tradePriceHistoryService;
	@Autowired
	OrderDeliverService orderDeliverService;
	@Autowired
	GuideAssignService guideAssignService;
	@Autowired
	OrderService orderService;

	/**
	 * 支付完成后 强制 指派导游
	 * 
	 * @param orderNo
	 * @param guideId
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void forceToGuide(String orderNo, String guideId, DeliverType deliverType, String opId, String opName) {
		OrderBean orderBeanDb = updateOrderBeanMapper.forupdateByOrderNo(orderNo);
		GuideBean guideBean = controllerService.getGuidByGuideId(guideId);
		if (guideBean == null) {
			throw new TradeException(TradeReturnCodeEnum.NO_GUIDE, guideId);
		}
		Timestamp dtime = new Timestamp(System.currentTimeMillis());
		TradeOrderDeliver tradeOrderDeliver = tradeDeliverService.insertDeliverOrder(orderBeanDb, TradeDeliverStatus.pkend, deliverType, dtime);
		double guidePrice = guideDeliverService.getGuideFloatPrice(orderBeanDb, guideBean);
		if (guidePrice != orderBeanDb.getPriceGuide()) {
			tradePriceHistoryService.deliverGuidePriceChange(orderNo, orderBeanDb.getPriceGuide(), guidePrice, PriceHistoryOpType.DELIVER_GUIDE_LEVEL);
		}
		// 订单奖金 只针对 强制指派
		if (null != orderBeanDb.getPriceReward()) {
			double guidePrice2 = DoubleUtil.addDouble(guidePrice, orderBeanDb.getPriceReward());
			tradePriceHistoryService.deliverGuidePriceChange(orderNo, guidePrice, guidePrice2, PriceHistoryOpType.REWARD);

			guidePrice = guidePrice2;
		}

		guidDeliverOrderService.insertToGuide(tradeOrderDeliver, guideBean, orderBeanDb, guidePrice, DeliverDemandDeal.accept, GuidDeliverStatus.success, deliverType);

		boolean isNewGuide = orderBeanDb.getGuideId().equals(TradeFinalStr.defaultGuideId);

		OrderStatus orderStatus = OrderStatus.getStatus(orderBeanDb.getOrderStatus());
		guideAssignService.assignGuide(guideId, orderBeanDb, guidePrice, orderStatus);
		logGuideAssign(orderNo, guideBean, opId, opName, "派导游操作");

		// 重置串单标记
		if (OrderSerialFlag.SERIAL.value.equals(orderBeanDb.getSerialFlag())) {
			orderService.resetSerOrder(orderBeanDb);
		}

		log.info("update [" + orderBeanDb.getOrderNo() + "] [" + JSON.toJSONString(orderBeanDb) + "] to [" + JSON.toJSONString(orderBeanDb) + "] ");

		// 强制指派在该处发Push
		if (isNewGuide) {
			smsservice.guideSMSConfirm(orderBeanDb);
			cpushService.cGuideConfirm(orderBeanDb, false);
		} else {
			smsservice.reGuideSMS(orderBeanDb);
			cpushService.cGuideConfirm(orderBeanDb, true);
		}
	}
}
