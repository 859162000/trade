/**
 * @Author lukangle
 * @2015年11月10日@下午8:36:50
 */
package com.hbc.api.trade.order.service.deliver;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hbc.api.trade.TradeFinalStr;
import com.hbc.api.trade.bdata.common.util.TimeConverter;
import com.hbc.api.trade.order.enums.TradeReturnCodeEnum;
import com.hbc.api.trade.order.enums.bean.OperationType;
import com.hbc.api.trade.order.enums.deliver.DeliverType;
import com.hbc.api.trade.order.enums.deliver.GuidDeliverStatus;
import com.hbc.api.trade.order.enums.deliver.IsReadable;
import com.hbc.api.trade.order.enums.deliver.OrderDeliverStatus;
import com.hbc.api.trade.order.enums.deliver.OrderSerialFlag;
import com.hbc.api.trade.order.enums.deliver.TradeDeliverStatus;
import com.hbc.api.trade.order.enums.order.OrderLogType;
import com.hbc.api.trade.order.enums.order.OrderStatus;
import com.hbc.api.trade.order.exception.TradeException;
import com.hbc.api.trade.order.mapping.gen.OrderBeanMapper;
import com.hbc.api.trade.order.mapping.gen.TradeOrderDeliverMapper;
import com.hbc.api.trade.order.mapping.gen.bean.OrderBean;
import com.hbc.api.trade.order.mapping.gen.bean.TradeOrderDeliver;
import com.hbc.api.trade.order.mapping.gen.bean.TradeOrderDeliverExample;
import com.hbc.api.trade.order.mapping.genx.UpdateOrderBeanMapper;
import com.hbc.api.trade.order.mapping.genx.deliver.WDOrderMapper;
import com.hbc.api.trade.order.mapping.genx.xbean.OrderLogParamBean;
import com.hbc.api.trade.order.service.OrderLogService;
import com.hbc.api.trade.third.LControllerService;

@Component
public class OrderDeliverService {
	private final static Logger log = LoggerFactory.getLogger(OrderDeliverService.class);
	@Autowired
	WDOrderMapper worderDeliverMapper;
	@Autowired
	TradeDeliverService tradeDeliverService;
	@Autowired
	UpdateOrderBeanMapper updateOrderBeanMapper;
	@Autowired
	GuidDeliverOrderService guidDeliverOrderService;
	@Autowired
	LControllerService controllerService;
	@Autowired
	OrderLogService orderLogService;
	@Autowired
	GuideAssignService guideAssignService;

	/**
	 * 更新主订单发单状态
	 * 
	 * @param orderNo
	 * @param dbOrderStatus
	 * @param dbDeliverStatus
	 * @param targetDeliverStatus
	 */
	@Transactional
	public void updateOrderDeliverStatus(String orderNo, OrderStatus dbOrderStatus, OrderDeliverStatus dbDeliverStatus, OrderDeliverStatus targetDeliverStatus) {
		int optNum = worderDeliverMapper.updateSOrderDeliverStatus(orderNo, dbOrderStatus.value, dbDeliverStatus.value, targetDeliverStatus.value, TradeFinalStr.defaultGuideId);
		if (optNum != 1) {
			throw new TradeException(TradeReturnCodeEnum.FAILED_FOR_UPDATE, orderNo + "	dbDeliverStatus " + dbDeliverStatus.name);
		}
	}

	/**
	 * 更新主订单发单状态以及串单标记
	 * 
	 * @param orderNo
	 * @param dbOrderStatus
	 * @param dbDeliverStatus
	 * @param targetDeliverStatus
	 * @param serialFlag
	 */
	@Transactional
	public void updateOrderDeliverStatus(String orderNo, OrderStatus dbOrderStatus, OrderDeliverStatus dbDeliverStatus, OrderDeliverStatus targetDeliverStatus, OrderSerialFlag serialFlag) {
		int optNum = worderDeliverMapper.updateOrderDeliverStatus(orderNo, dbOrderStatus.value, dbDeliverStatus.value, targetDeliverStatus.value, TradeFinalStr.defaultGuideId, serialFlag.value);
		if (optNum != 1) {
			throw new TradeException(TradeReturnCodeEnum.FAILED_FOR_UPDATE, orderNo + "	dbDeliverStatus " + dbDeliverStatus.name);
		}
	}

	@Transactional
	public boolean addOrderDeliver(OrderBean orderBean, DeliverType deliverType, String opUserId, String opUserName) {
		OrderStatus orderStatus = OrderStatus.getStatus(orderBean.getOrderStatus());

		if (OrderStatus.PAYSUCCESS.equals(orderStatus)) {
			switch (deliverType) {
			case Immediately:// 立即发单
				return immediately(orderBean, opUserId, opUserName);
			case Incremental_Send:// 增量补发
				return incrementalSend(orderBean, opUserId, opUserName);
			case Cancle_Send:// 取消重发
				return cancleSend(orderBean, opUserId, opUserName);
			default:
				return false;
			}
		} else {
			throw new TradeException(TradeReturnCodeEnum.DELIVER_STATUSEXP, orderBean.getOrderNo());
		}
	}

	@Transactional(propagation = Propagation.REQUIRED)
	private boolean immediately(OrderBean orderBean, String opUserId, String opUserName) {
		boolean result = true;
		tradeDeliverService.insertDeliverOrder(orderBean, TradeDeliverStatus.predeliver, DeliverType.Immediately, null);
		// 更新Order
		orderBean.setDeliverType(DeliverType.Immediately.value);
		orderBean.setDeliverStatus(OrderDeliverStatus.deliving.value);
		orderBean.setOrderStatus(OrderStatus.PAYSUCCESS.value);
		updateOrderBeanMapper.updateOrderDeliverStatus(orderBean);

		insertOrderLog(orderBean, OrderLogType.IMMEDIATELY, OrderLogType.IMMEDIATELY_CONTENT(opUserName, TimeConverter.formatDate(new Date())), opUserId, opUserName);
		return result;
	}

	@Autowired
	TradeOrderDeliverMapper tradeOrderDeliverMapper;
	@Transactional(propagation = Propagation.REQUIRED)
	private boolean incrementalSend(OrderBean orderBean, String opUserId, String opUserName) {
		boolean result = true;
		if (orderBean.getDeliverType().equals(DeliverType.Cancle_Send.value)) {
			// return false;
		}
		//判断当前订单是否有取消重发的操作
		TradeOrderDeliverExample  tradeOrderDeliverExample = new TradeOrderDeliverExample();
		List<Integer> deliverStatus= new ArrayList<Integer>();
		deliverStatus.add(TradeDeliverStatus.predeliver.value);
		deliverStatus.add(TradeDeliverStatus.delivered.value);
		tradeOrderDeliverExample.createCriteria().andOrderNoEqualTo(orderBean.getOrderNo()).andDeliverTypeEqualTo(DeliverType.Cancle_Send.value).andDeliverStatusIn(deliverStatus);
		List<TradeOrderDeliver> tradeOrderDelivers= tradeOrderDeliverMapper.selectByExample(tradeOrderDeliverExample);
	
		if(tradeOrderDelivers == null || tradeOrderDelivers.size()==0){
			log.info("增量补发 | 订单信息:{} |预发包，增量补发.", orderBean);
			tradeDeliverService.insertDeliverOrder(orderBean, TradeDeliverStatus.predeliver, DeliverType.Incremental_Send, null);

			// 更新Order
			orderBean.setDeliverType(DeliverType.Incremental_Send.value);
			orderBean.setDeliverStatus(OrderDeliverStatus.deliving.value);
			orderBean.setOrderStatus(OrderStatus.PAYSUCCESS.value);
			updateOrderBeanMapper.updateOrderDeliverStatus(orderBean);
			//		OrderBean orderBean,OrderLogType logType,String content
			insertOrderLog(orderBean, OrderLogType.INCREMENTAL_SEND, OrderLogType.INCREATAL_SEND_CONTENT(opUserName, TimeConverter.formatDate(new Date())), opUserId, opUserName);
			
		}else{
			result=false;
		}		
		return result;

		
	}

	@Autowired
	OrderBeanMapper orderBeanMapper;

	@Transactional(propagation = Propagation.REQUIRED)
	private boolean cancleSend(OrderBean orderBean, String opUserId, String opUserName) {
		boolean result = true;

		if (orderBean.getDeliverType().equals(DeliverType.Cancle_Send.value)) {
			return false;
		}

		// 更新取消重发导游
		int optnum = guidDeliverOrderService.updateDeliverStatusByGuideId(orderBean.getOrderNo(), orderBean.getGuideId(), IsReadable.VISIABLE, GuidDeliverStatus.resend);

		if (optnum != 1) {
			throw new TradeException(TradeReturnCodeEnum.ORDER_UPDATE_FAILED, "指定导游错误");
		}// 更新deliver_guide

		guidDeliverOrderService.updateByOrderNoReadable(orderBean.getOrderNo(), IsReadable.VISIABLE, IsReadable.HIDDEN);

		String oldGuideName = orderBean.getGuideName();
		String oldGuideId = orderBean.getGuideId();

		OrderBean record = new OrderBean();
		record.setOrderNo(orderBean.getOrderNo());
		// 更新Order
		record.setGuideId(TradeFinalStr.defaultGuideId);
		record.setGuideAreaCode("");
		record.setGuideMobile("");
		record.setGuideName("");
		record.setGuideAssignTime(null);
		record.setGuideNo("");
		record.setGuideAccountNo("");
		record.setDeliverType(DeliverType.Cancle_Send.value);
		record.setDeliverStatus(OrderDeliverStatus.deliving.value);
		record.setGuideAreaCode("");

		optnum = orderBeanMapper.updateByPrimaryKeySelective(record);

		if (optnum != 1) {
			throw new TradeException(TradeReturnCodeEnum.ORDER_UPDATE_FAILED, "指定导游错误");
		}
		tradeDeliverService.insertDeliverOrder(orderBean, TradeDeliverStatus.predeliver, DeliverType.Cancle_Send, null);
		insertOrderLog(orderBean, OrderLogType.CANCEL_ORIGIN_GUIDE, OrderLogType.CANCEL_ORIGIN_GUIDE_CONTENT(opUserName, oldGuideId, oldGuideName), opUserId, opUserName);
		return result;
	}

	@Autowired
	GuideDeliverService guideDeliverService;

	@Transactional(propagation = Propagation.REQUIRED)
	private void insertOrderLog(OrderBean orderBean, OrderLogType logType, String content, String opUserId, String opUserName) {
		OrderLogParamBean orderLogParamBean = new OrderLogParamBean();
		orderLogParamBean.setContent(content);
		orderLogParamBean.setLogType(logType.type);
		orderLogParamBean.setOpType(OperationType.CUSTOMER_SERVICE.value);
		orderLogParamBean.setOpUserId(opUserId);
		orderLogParamBean.setOpUserName(opUserName);

		orderLogParamBean.setOrderNo(orderBean.getOrderNo());
		orderLogService.insertOrderLog(orderLogParamBean);
	}
}
