package com.hbc.api.trade.order.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.hbc.api.trade.bdata.common.util.TimeConverter;
import com.hbc.api.trade.order.OrderPal;
import com.hbc.api.trade.order.enums.TradeReturnCodeEnum;
import com.hbc.api.trade.order.enums.bean.OperationType;
import com.hbc.api.trade.order.enums.order.OrderLogType;
import com.hbc.api.trade.order.enums.order.OrderStatus;
import com.hbc.api.trade.order.enums.third.CommentTypeEnum;
import com.hbc.api.trade.order.exception.TradeException;
import com.hbc.api.trade.order.mapping.gen.bean.OrderBean;
import com.hbc.api.trade.order.mapping.genx.xbean.AppraiseGuideParam;
import com.hbc.api.trade.order.mapping.genx.xbean.OrderLogParamBean;
import com.hbc.api.trade.third.restful.GuideRESTfulService;

@Service
public class COptOrderService extends OrderService {
	@Autowired private OrderQueryService 	orderQueryService;
	@Autowired private UpdateOrderService	updateOrderService;
	@Autowired private GuideRESTfulService	guideRESTfulService;
	@Autowired private OrderTrackService 	orderTrackService;

	/* (non-Javadoc)
	 * @see com.hbc.api.trade.order.service.OrderService#addOrder(com.hbc.api.trade.order.mapping.gen.bean.OrderBean)
	 */
	@Override
	public String addOrder(OrderBean orderBean) {
		return super.addOrder(orderBean);
	}

	@Transactional
	public void updateDailyOrder(OrderBean orderBean,String serviceRecTime) {
		updateOrderService.updateDailyOrder(orderBean, serviceRecTime);
	}
	
	@Transactional
	public void updatePickUpOrder(OrderBean orderBean) {
		updateOrderService.updatePickUpOrder(orderBean);
	}
	
	
	@Transactional
	public void updateSingleOrder(OrderBean orderBean) {
		updateOrderService.updateSingleOrder(orderBean);
	}
	
	@Transactional
	public void updateTransferOrder(OrderBean orderBean) {
		updateOrderService.updateTransferOrder(orderBean);
	}

	@Transactional
	public void cancelOrderWithoutMoney(String orderNo, String userId, String userName) {
		updateOrderStatus(orderNo,OrderStatus.INITSTATE, OrderStatus.CANCLE_CLOSE_NOPAY);
		
		updateOrderCancleTime(orderNo);
		OrderLogParamBean orderLogParamBean = new OrderLogParamBean();
		orderLogParamBean.setContent(OrderLogType.CANCEL_CONTENT(userName, TimeConverter.formatDate(new Date())));
		orderLogParamBean.setOpUserId(userId);
		orderLogParamBean.setLogType(OrderLogType.CANCEL.type);
		orderLogParamBean.setOpType(OperationType.CUSTOMER.value);
		orderLogParamBean.setOpUserId(userId);
		orderLogParamBean.setOpUserName(userName);
		orderLogParamBean.setOrderNo(orderNo);
		orderLogService.insertOrderLog(orderLogParamBean );
		orderTrackService.cancelled(orderNo);
	}
	
	@Transactional
	public void appraiseGuide(AppraiseGuideParam param) {
		OrderBean orderBean = orderQueryService.getOrderByNo(param.getOrderNo());
		if(OrderPal.appraisableByUser(orderBean) ) {
			updateOrderBeanMapper.updateToAlreadyUserAppraised(orderBean.getOrderNo());
			param.setCommentType(CommentTypeEnum.FROM_CUSTOMER.value);
			param.setFromUname(orderBean.getUserName());
			param.setGuideName(orderBean.getGuideName());
			guideRESTfulService.saveAppraiseContent(param, orderBean.getOrderType());
		} else {
			log.error("订单当前状态不能被评价, :" + JSON.toJSONString(orderBean));
			throw new TradeException(TradeReturnCodeEnum.ORDER_CANNOT_BE_APPRAISED);
		}
	}
}
