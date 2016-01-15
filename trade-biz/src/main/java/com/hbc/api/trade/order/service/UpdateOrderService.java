/**
 * @Author lukangle
 * @2015骞�11鏈�10鏃涓嬪崍2:49:57
 */
package com.hbc.api.trade.order.service;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.hbc.api.trade.bdata.common.util.TimeConverter;
import com.hbc.api.trade.order.enums.TradeReturnCodeEnum;
import com.hbc.api.trade.order.enums.bean.OperationType;
import com.hbc.api.trade.order.enums.order.OrderKafkaOpt;
import com.hbc.api.trade.order.enums.order.OrderLogType;
import com.hbc.api.trade.order.exception.TradeException;
import com.hbc.api.trade.order.mapping.gen.bean.OrderBean;
import com.hbc.api.trade.order.mapping.genx.UpdateOrderBeanMapper;
import com.hbc.api.trade.order.mapping.genx.xbean.OrderLogParamBean;
import com.hbc.api.trade.order.service.kafka.TradeKafkaMsgSender;
import com.hbc.api.trade.order.validator.OrderValidator;
@Component
public class UpdateOrderService {
	@Autowired private UpdateOrderBeanMapper 		updateOrderBeanMapper;
	@Autowired private TradeKafkaMsgSender 			tradeKafkaMsgSender;
	@Autowired protected OrderLogService			orderLogService;
	@Autowired private OrderTrackService 			orderTrackService;

	private final static Logger log = LoggerFactory.getLogger(OrderService.class);
	
	public void updateDailyOrder(OrderBean orderBean,String serviceRecTime) {
		OrderValidator.validatePassCity(orderBean);
		OrderValidator.validateHeadCount(orderBean);
		
		OrderBean orderBeanNewest = updateOrderBeanMapper.forupdateByOrderNo(orderBean.getOrderNo());
		if (orderBeanNewest == null) {
			TradeException tradeException = new TradeException(TradeReturnCodeEnum.ORDER_NOTEXIST, "订单号:" + orderBean.getOrderNo());
			log.error(tradeException.getMessage());
			throw tradeException;
		}
		log.info("【更新日租订单】开始更新：update [" + orderBean.getOrderNo() + "] [" + JSON.toJSONString(orderBeanNewest) + "] to [" + JSON.toJSONString(orderBean) + "] ");

		if (serviceRecTime != null) {
			String serviceTime = TimeConverter.formatDate(orderBeanNewest.getServiceTime()).substring(0, 11) + serviceRecTime;
			orderBean.setServiceTime(TimeConverter.toDate(serviceTime));
		}
		
		int affectedRows = 0;
		try {
			affectedRows = updateOrderBeanMapper.updateDailyOrder(orderBean);
		} catch(Exception e) {
			log.error("【更新日租订单】失败，发生异常。参数：" + JSON.toJSONString(orderBean), e);
			throw new TradeException(TradeReturnCodeEnum.FAILED_FOR_UPDATE, "订单号:" + orderBean.getOrderNo());
		}

		if (affectedRows == 0) {
			log.error("【更新日租订单】失败，0行成功。参数：" + JSON.toJSONString(orderBean));
			throw new TradeException(TradeReturnCodeEnum.FAILED_FOR_UPDATE, "订单号:" + orderBean.getOrderNo());
		}
		
		try {
			tradeKafkaMsgSender.sendToKafka(orderBean, OrderKafkaOpt.edit);
			orderTrackService.updated(orderBean.getOrderNo());
			orderUpdatedLogger(orderBean);
		} catch(Exception e) {
			log.error("【更新日租订单】失败，发生异常。参数：" + JSON.toJSONString(orderBean), e);
			throw new TradeException(TradeReturnCodeEnum.FAILED_FOR_UPDATE, "订单号:" + orderBean.getOrderNo());
		}
	}
	
	
	@Transactional
	public void updatePickUpOrder(OrderBean orderBean) {
		OrderValidator.validateHeadCount(orderBean);
		OrderBean orderBeanNewest = updateOrderBeanMapper.forupdateByOrderNo(orderBean.getOrderNo());
		if (orderBeanNewest == null) {
			TradeException tradeException = new TradeException(TradeReturnCodeEnum.ORDER_NOTEXIST, "订单号:" + orderBean.getOrderNo());
			log.error(tradeException.getMessage());
			throw tradeException;
		}
		log.info("【更新接机订单】update [" + orderBean.getOrderNo() + "] [" + JSON.toJSONString(orderBean) + "] to [" + JSON.toJSONString(orderBean) + "] ");

		int affectedRows = 0;
		try {
			affectedRows = updateOrderBeanMapper.updatePickupOrder(orderBean);
		} catch(Exception e) {
			log.error("【更新接机订单】失败，发生异常。参数：" + JSON.toJSONString(orderBean), e);
			throw new TradeException(TradeReturnCodeEnum.FAILED_FOR_UPDATE, "订单号:" + orderBean.getOrderNo());
		}

		if (affectedRows == 0) {
			log.error("【更新接机订单】失败，0行成功。参数：" + JSON.toJSONString(orderBean));
			throw new TradeException(TradeReturnCodeEnum.FAILED_FOR_UPDATE, "订单号:" + orderBean.getOrderNo());
		}
		
		try {
			tradeKafkaMsgSender.sendToKafka(orderBean, OrderKafkaOpt.edit);
			orderTrackService.updated(orderBean.getOrderNo());
			orderUpdatedLogger(orderBean);
		} catch(Exception e) {
			log.error("【更新接机订单】失败，发生异常。参数：" + JSON.toJSONString(orderBean), e);
			throw new TradeException(TradeReturnCodeEnum.FAILED_FOR_UPDATE, "订单号:" + orderBean.getOrderNo());
		}
	}
	
	
	@Transactional
	public void updateSingleOrder(OrderBean orderBean) {
		OrderValidator.validatePassCity(orderBean);
		OrderValidator.validateHeadCount(orderBean);
		OrderBean orderBeanNewest = updateOrderBeanMapper.forupdateByOrderNo(orderBean.getOrderNo());
		if (orderBeanNewest == null) {
			TradeException tradeException = new TradeException(TradeReturnCodeEnum.ORDER_NOTEXIST, "订单号:" + orderBean.getOrderNo());
			log.error(tradeException.getMessage());
			throw tradeException;
		}

		log.info("【更新次租订单】开始 update [" + orderBean.getOrderNo() + "] [" + JSON.toJSONString(orderBean) + "] to [" + JSON.toJSONString(orderBean) + "] ");
		int affectedRows = 0;
		try {
			affectedRows = updateOrderBeanMapper.updateOnceOrder(orderBean);
		} catch(Exception e) {
			log.error("【更新次租订单】失败，发生异常。参数：" + JSON.toJSONString(orderBean), e);
			throw new TradeException(TradeReturnCodeEnum.FAILED_FOR_UPDATE, "订单号:" + orderBean.getOrderNo());
		}

		if (affectedRows == 0) {
			log.error("【更新次租订单】失败，0行成功。参数：" + JSON.toJSONString(orderBean));
			throw new TradeException(TradeReturnCodeEnum.FAILED_FOR_UPDATE, "订单号:" + orderBean.getOrderNo());
		}
		
		try{
			tradeKafkaMsgSender.sendToKafka(orderBean, OrderKafkaOpt.edit);
			orderTrackService.updated(orderBean.getOrderNo());
			orderUpdatedLogger(orderBean);
		} catch(Exception e) {
			log.error("【更新次租订单】失败，发生异常。参数：" + JSON.toJSONString(orderBean), e);
			throw new TradeException(TradeReturnCodeEnum.FAILED_FOR_UPDATE, "订单号:" + orderBean.getOrderNo());
		}
	}

	@Transactional
	public void updateTransferOrder(OrderBean orderBean) {
		OrderValidator.validateHeadCount(orderBean);
		OrderBean orderBeanNewest = updateOrderBeanMapper.forupdateByOrderNo(orderBean.getOrderNo());
		if (orderBeanNewest == null) {
			TradeException tradeException = new TradeException(TradeReturnCodeEnum.ORDER_NOTEXIST, "订单号:" + orderBean.getOrderNo());
			log.error(tradeException.getMessage());
			throw tradeException;
		}

		log.info("update [" + orderBean.getOrderNo() + "] [" + JSON.toJSONString(orderBean) + "] to [" + JSON.toJSONString(orderBean) + "] ");
		int affectedRows = 0;
		try{
			affectedRows = updateOrderBeanMapper.updateSendOrder(orderBean);
		} catch(Exception e) {
			log.error("【更新送机订单】失败，发生异常。参数：" + JSON.toJSONString(orderBean), e);
			throw new TradeException(TradeReturnCodeEnum.FAILED_FOR_UPDATE, "订单号:" + orderBean.getOrderNo());
		}

		if (affectedRows == 0) {
			log.error("【更新送机订单】失败，0行成功。参数：" + JSON.toJSONString(orderBean));
			throw new TradeException(TradeReturnCodeEnum.FAILED_FOR_UPDATE, "订单号:" + orderBean.getOrderNo());
		}
		
		try{
			tradeKafkaMsgSender.sendToKafka(orderBean, OrderKafkaOpt.edit);
			orderTrackService.updated(orderBean.getOrderNo());
			orderUpdatedLogger(orderBean);
		} catch(Exception e) {
			log.error("【更新送机订单】失败，发生异常。参数：" + JSON.toJSONString(orderBean), e);
			throw new TradeException(TradeReturnCodeEnum.FAILED_FOR_UPDATE, "订单号:" + orderBean.getOrderNo());
		}
	}
	
	/**
	 * @param orderBean
	 */
	private void orderUpdatedLogger(OrderBean orderBean) {
		OrderLogParamBean orderLogParamBean = new OrderLogParamBean();
		boolean isOperatedByAgent = orderBean.getAgentOpid() != null;
		String opUserName = isOperatedByAgent ? orderBean.getAgentOpname() : orderBean.getUserName();
		orderLogParamBean.setContent(OrderLogType.UPDATE_ORDER_CONTENT(opUserName, TimeConverter.formatDate(new Date()), JSON.toJSONString(orderBean)));
		orderLogParamBean.setLogType(OrderLogType.UPDATE_ORDER.type);
		orderLogParamBean.setOpType(isOperatedByAgent ? OperationType.ASSISTENT.value : OperationType.CUSTOMER.value);
		orderLogParamBean.setOpUserId(isOperatedByAgent ? orderBean.getAgentOpid() : orderBean.getUserId());
		orderLogParamBean.setOpUserName(opUserName);
		orderLogParamBean.setOrderNo(orderBean.getOrderNo());
		orderLogService.insertOrderLog(orderLogParamBean );
	}
}
