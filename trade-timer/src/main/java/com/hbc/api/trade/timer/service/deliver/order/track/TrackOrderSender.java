package com.hbc.api.trade.timer.service.deliver.order.track;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hbc.api.trade.order.enums.alarm.AlarmStatus;
import com.hbc.api.trade.order.enums.alarm.AlarmTargetType;
import com.hbc.api.trade.order.enums.alarm.AlarmType;
import com.hbc.api.trade.order.enums.order.OrderStatus;
import com.hbc.api.trade.order.enums.third.NoticeTarget;
import com.hbc.api.trade.order.enums.third.NoticeType;
import com.hbc.api.trade.order.mapping.gen.bean.OrderBean;
import com.hbc.api.trade.order.mapping.gen.bean.TradeAlarm;
import com.hbc.api.trade.order.mapping.genx.UpdateOrderBeanMapper;
import com.hbc.api.trade.order.service.OrderServiceTime;
import com.hbc.api.trade.order.service.alarm.OrderAlarmService;
import com.hbc.api.trade.order.service.deliver.TradeDeliverService;
import com.hbc.api.trade.third.push.GPushService;

@Component
public class TrackOrderSender {
	private final static Logger log = LoggerFactory.getLogger(TrackOrderSender.class);
	@Autowired
	TradeDeliverService tradeDeliverService;
	@Autowired
	UpdateOrderBeanMapper updateOrderBeanMapper;
	@Autowired
	OrderServiceTime orderServiceTime;
	@Autowired
	OrderAlarmService orderAlarmService;
	@Autowired
	GPushService pushService;

	public void startToTracking() {
		log.info("订单跟踪服务 开始启动");
		// 获取所有未完成的订单
		List<OrderBean> orders = getAllOrder();

	}

	private void tracking(List<OrderBean> orders) {
		if (orders == null || orders.size() == 0) {
			return;
		}
		for (OrderBean order : orders) {
			OrderStatus orderStatus = OrderStatus.getStatus(order.getOrderStatus().intValue());
			switch (orderStatus) {
			case INITSTATE:// 未支付
				noPay(order);
				break;
			case GUIDE_ARRIVED:

				break;
			case PICK_CUSTOMER:

				break;

			}
		}

	}

	private List<OrderBean> getAllOrder() {
		List<Integer> orderStatus = new ArrayList<Integer>();

		orderStatus.add(OrderStatus.INITSTATE.value);
		orderStatus.add(OrderStatus.PAYSUCCESS.value);
		orderStatus.add(OrderStatus.GUIDE_ARRIVED.value);
		orderStatus.add(OrderStatus.PICK_CUSTOMER.value);
		orderStatus.add(OrderStatus.STROKE_END.value);

		return tradeDeliverService.getTrackOrders(orderStatus);

	}

	/**
	 * 下单一小时 无支付，系统自动取消（65分钟）
	 * 
	 * @param order
	 */
	private void noPay(OrderBean order) {
		Date now = new Date();
		long cancelTime = order.getCreateTime().getTime() + 65 * 60 * 1000;
		if (now.getTime() > cancelTime) {// 一小时未付款 自动取消
			// 更新订单状态
			int num = updateOrderBeanMapper.updateOrderStatus(order.getOrderNo(), OrderStatus.INITSTATE.value, OrderStatus.CANCLE_CLOSE_NOPAY.value, OrderStatus.CANCLE_CLOSE_NOPAY.name);
			if (num == 0) {
				log.info("noPay 取消订单 失败，订单Id：" + order.getOrderNo());
			} else {
				log.info("noPay 取消订单 成功，订单Id：" + order.getOrderNo());
			}
		}

	}


}
