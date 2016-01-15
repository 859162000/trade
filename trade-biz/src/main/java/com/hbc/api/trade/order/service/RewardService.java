/**
 * @Author lukangle
 * @2015年11月22日@下午6:57:00
 */
package com.hbc.api.trade.order.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hbc.api.trade.order.enums.TradeReturnCodeEnum;
import com.hbc.api.trade.order.enums.order.OrderStatus;
import com.hbc.api.trade.order.exception.TradeException;
import com.hbc.api.trade.order.mapping.gen.bean.OrderBean;
import com.hbc.api.trade.order.mapping.genx.UpdateOrderBeanMapper;
import com.hbc.api.trade.third.push.GPushService;

@Component
public class RewardService {
	@Autowired private UpdateOrderBeanMapper 	updateOrderBeanMapper;
	@Autowired private GPushService 			gpushService;
	@Autowired private OrderLogService			orderLogService;
	@Autowired private TradePriceHistoryService tradePriceHistoryService;
	
	public void reword(String orderNo, Double rewordprice,String remark,String optId,String optName){
		OrderBean orderBean = updateOrderBeanMapper.forupdateByOrderNo(orderNo);
//		if(TradeFinalStr.defaultGuideId.equals(orderBean.getGuideId())){
//			throw new TradeException(TradeReturnCodeEnum.ORDER_NEED_GUIDE);
//		}
//		
		OrderStatus ostatus = OrderStatus.getStatus(orderBean.getOrderStatus());
		if (ostatus.equals(OrderStatus.PAYSUCCESS) || OrderStatus.GUIDE_ARRIVED.equals(ostatus) || OrderStatus.PICK_CUSTOMER.equals(ostatus) || OrderStatus.STROKE_END.equals(ostatus)) {
			updateOrderBeanMapper.updateRewordGuidePrice(orderNo, rewordprice);
		}else{
			throw new TradeException(TradeReturnCodeEnum.ORDER_SERVICE);
		}
		
		// TODO 卢员外：记录修改记录
		// tradePriceHistoryService.recordPriceHistory(priceHistoryBeanParam);
		
		/*
		// TODO 卢员外：记录log
		OrderLogParamBean orderLogParamBean = new OrderLogParamBean();
		orderLogParamBean.setContent(OrderLogType.UPDATE_PRICE_ALREADY_PAYED_CONTENT("MIS", priceHistoryBeanParam.getOpUname(),
				TimeConverter.formatDate(new Date()), priceHistoryBeanParam.getTargetPrice(), priceHistoryBeanParam.getSourcePrice()));
		orderLogParamBean.setLogType(OrderLogType.UPDATE_PRICE_ALREADY_PAYED.type);
		orderLogParamBean.setOpType(OperationType.CUSTOMER_SERVICE.value);
		orderLogParamBean.setOpUserId(priceHistoryBeanParam.getOpUid());
		orderLogParamBean.setOpUserName(priceHistoryBeanParam.getOpUname());
		orderLogParamBean.setOrderNo(priceHistoryBeanParam.getOrderNo());
		orderLogService.insertOrderLog(orderLogParamBean );
		*/
	}
	
}
