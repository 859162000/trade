package com.hbc.api.trade.timer.service.deliver.order.redeliver;

import java.sql.Timestamp;
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
import com.hbc.api.trade.bdata.common.HttpClientService;
import com.hbc.api.trade.bdata.mapper.guide.gen.bean.GuideBean;
import com.hbc.api.trade.order.enums.TradeReturnCodeEnum;
import com.hbc.api.trade.order.enums.deliver.DeliverDemandDeal;
import com.hbc.api.trade.order.enums.deliver.DeliverType;
import com.hbc.api.trade.order.enums.deliver.GuidDeliverStatus;
import com.hbc.api.trade.order.enums.deliver.OrderSerialFlag;
import com.hbc.api.trade.order.enums.deliver.TradeDeliverStatus;
import com.hbc.api.trade.order.enums.order.OrderStatus;
import com.hbc.api.trade.order.exception.TradeException;
import com.hbc.api.trade.order.mapping.gen.OrderBeanMapper;
import com.hbc.api.trade.order.mapping.gen.TradeDeliverGuideMapper;
import com.hbc.api.trade.order.mapping.gen.bean.OrderBean;
import com.hbc.api.trade.order.mapping.gen.bean.TradeDeliverGuide;
import com.hbc.api.trade.order.mapping.gen.bean.TradeDeliverGuideExample;
import com.hbc.api.trade.order.mapping.gen.bean.TradeOrderDeliver;
import com.hbc.api.trade.order.mapping.genx.UpdateOrderBeanMapper;
import com.hbc.api.trade.order.service.OrderServiceTime;
import com.hbc.api.trade.order.service.deliver.GuidDeliverOrderService;
import com.hbc.api.trade.order.service.deliver.GuideDeliverService;
import com.hbc.api.trade.order.service.deliver.OrderDeliverService;
import com.hbc.api.trade.order.service.deliver.OrderPkService;
import com.hbc.api.trade.order.service.deliver.TradeDeliverService;
import com.hbc.api.trade.third.LControllerService;
import com.hbc.api.trade.third.push.GPushService;

@Component
public class ReDeliverService {
	private final static Logger log = LoggerFactory.getLogger(ReDeliverService.class);
	@Autowired
	OrderBeanMapper orderBeanMapper;
	@Autowired
	TradeDeliverService tradeDeliverService;
	@Autowired
	OrderServiceTime orderServiceTime;
	@Autowired
	GuidDeliverOrderService guidDeliverOrderService;
	@Autowired
	OrderPkService orderPkService;
	@Autowired
	LControllerService controllerService;

	@Autowired
	HttpClientService httpClientService;
	@Autowired
	GuideDeliverService guideDeliverService;
	@Autowired
	OrderDeliverService orderDeliverService;
	@Autowired
	UpdateOrderBeanMapper updateOrderBeanMapper;
	@Autowired
	GPushService pushService;
	
	@Autowired
	TradeDeliverGuideMapper tradeDeliverGuideMapper;

	@Transactional
	public void startToRedeliver(TradeOrderDeliver tradeOrderDeliver,int span, int urgentHour, String pushUrl){
		try{
			//允许取消重发的订单状态
			List<OrderStatus> underWay=new ArrayList<OrderStatus>();
			underWay.add(OrderStatus.PAYSUCCESS);
			underWay.add(OrderStatus.GUIDE_ARRIVED);
			underWay.add(OrderStatus.PICK_CUSTOMER);
			OrderBean orderBean = orderBeanMapper.selectByPrimaryKey(tradeOrderDeliver.getOrderNo());
			
			OrderStatus orderStatus=OrderStatus.getStatus(orderBean.getOrderStatus());
			if ((orderBean.getGuideId() != null && orderBean.getGuideId().length() > 0 && !TradeFinalStr.defaultGuideId.equals(orderBean.getGuideId())) ||!underWay.contains(orderStatus)) {// 订单不存在  已确定导游  订单已经完成
				//若有导游 直接退出
			}else{
				TradeDeliverStatus tradeDeliverStatus = TradeDeliverStatus.getType(tradeOrderDeliver.getDeliverStatus());
				if(tradeDeliverStatus == null){
					log.error("订单发单状态不存在");
					throw new TradeException(TradeReturnCodeEnum.ORDER_DELIVER_STATUS_NOT_FOUND, "订单发单状态不存在");
				}
				
				// 第一次处理
				if (TradeDeliverStatus.predeliver.equals(tradeDeliverStatus)) {
					log.info("startToReDeliver orderNO :" + orderBean.getOrderNo() + "第一次处理");
					initReDeliver(tradeOrderDeliver, orderBean, urgentHour, span, pushUrl);
					log.info("startToReDeliver orderNO :" + orderBean.getOrderNo() + "第一次处理结束");

				} else if (TradeDeliverStatus.delivered.equals(tradeDeliverStatus)) {
					deliver(tradeOrderDeliver, orderBean, span, pushUrl);
				}
			}
		}catch(Exception e){
			log.error("", e);
		}
	}
//	public void startToReDeliver(int span, int urgentHour, String pushUrl) {
//		// int span=10;
//		// int urgentHour=24;
//		// String pushUrl="";
////		log.info("startToReDeliver start");
//		List<Integer> deliverStatuss = new ArrayList<Integer>();
//		deliverStatuss.add(TradeDeliverStatus.delivered.value);
//		deliverStatuss.add(TradeDeliverStatus.predeliver.value);
//
//		List<TradeOrderDeliver> tradeDelivers = tradeDeliverService.getTradeDelivers(deliverStatuss, DeliverType.Cancle_Send);
//		
//		//允许取消重发的订单状态
//		List<OrderStatus> underWay=new ArrayList<OrderStatus>();
//		underWay.add(OrderStatus.PAYSUCCESS);
//		underWay.add(OrderStatus.GUIDE_ARRIVED);
//		underWay.add(OrderStatus.PICK_CUSTOMER);
//		
//		for (TradeOrderDeliver tradeOrderDeliver : tradeDelivers) {
//			try{
//				OrderBean orderBean = orderBeanMapper.selectByPrimaryKey(tradeOrderDeliver.getOrderNo());
//				
//				OrderStatus orderStatus=OrderStatus.getStatus(orderBean.getOrderStatus());
//				if ((orderBean.getGuideId() != null && orderBean.getGuideId().length() > 0 && !TradeFinalStr.defaultGuideId.equals(orderBean.getGuideId())) ||!underWay.contains(orderStatus)) {// 订单不存在  已确定导游  订单已经完成
//					continue;
//				}				
//				TradeDeliverStatus tradeDeliverStatus = TradeDeliverStatus.getType(tradeOrderDeliver.getDeliverStatus());
//				if(tradeDeliverStatus == null){
//					log.error("订单发单状态不存在");
//					throw new TradeException(TradeReturnCodeEnum.ORDER_DELIVER_STATUS_NOT_FOUND, "订单发单状态不存在");
//				}
//				
//				
//				// 第一次处理
//				if (TradeDeliverStatus.predeliver.equals(tradeDeliverStatus)) {
//					log.info("startToReDeliver orderNO :" + orderBean.getOrderNo() + "第一次处理");
//					initReDeliver(tradeOrderDeliver, orderBean, urgentHour, span, pushUrl);
//					log.info("startToReDeliver orderNO :" + orderBean.getOrderNo() + "第一次处理结束");
//
//				} else if (TradeDeliverStatus.delivered.equals(tradeDeliverStatus)) {
////					log.info("startToReDeliver orderNO :" + orderBean.getOrderNo() + "再次处理");
//					deliver(tradeOrderDeliver, orderBean, span, pushUrl);
////					log.info("startToReDeliver orderNO :" + orderBean.getOrderNo() + "再次结束");
//				}
//				// 更新trade_deliver表中deliver_status=TradeDeliverStatus.delivered.value,deliver
//				// 获取下一个应发导游
////				log.info("startToReDeliver orderNO :" + orderBean.getOrderNo() + "处理结束");
//			}catch(Exception e){
//				log.error("", e);
//			}
//		}
//	}

	@Transactional(propagation = Propagation.REQUIRED)
	private void initReDeliver(TradeOrderDeliver tradeOrderDeliver, OrderBean orderBean, int urgentHour, int span, String pushUrl) {

		Date curdate = orderServiceTime.getServiceCityCurTime(tradeOrderDeliver.getCityId());
		int hour = curdate.getHours();

		Date serviceDate = orderBean.getServiceTime();

		Date urgentDate = new Date(serviceDate.getTime() - urgentHour * 60 * 60 * 1000);

		// 当地时间在 8-22点之间 或者24小时之内的订单
		if (hour < 22 && hour >= 8 || urgentDate.after(curdate)) {
			//

			// 更新trade_deliver_guide表中除被取消的导游其他 is_readable=0(MIS)

			// 更新trade_order表中deliver_status=1（发单中）

//			orderDeliverService.updateOrderDeliverStatus(orderBean.getOrderNo(), OrderStatus.getStatus(orderBean.getOrderStatus()), OrderDeliverStatus.getType(orderBean.getDeliverStatus()), OrderDeliverStatus.deliving);
			tradeDeliverService.updateStatus(tradeOrderDeliver.getDeliverNo(), TradeDeliverStatus.predeliver, TradeDeliverStatus.delivered);
			deliver(tradeOrderDeliver, orderBean, span, pushUrl);
		}
	}

	@Transactional(propagation = Propagation.REQUIRED)
	private void deliver(TradeOrderDeliver tradeOrderDeliver, OrderBean orderBean, int span, String pushUrl) {

		OrderSerialFlag orderSerialFlag =OrderSerialFlag.getType(orderBean.getSerialFlag());
		if (OrderSerialFlag.SERIAL.equals(orderSerialFlag)) {
			log.info("deliver orderNO :" + orderBean.getOrderNo() + "串单 取消重发");
			tradeDeliverService.updateStatus(tradeOrderDeliver.getDeliverNo(), TradeDeliverStatus.getType(tradeOrderDeliver.getDeliverStatus()), TradeDeliverStatus.resenddelivered);
			// 执行增量补发
			tradeDeliverService.insertDeliverOrder(orderBean, TradeDeliverStatus.predeliver, DeliverType.Incremental_Send, null);
		}else{
			Date now = new Date();
			// 获取已发导游
			List<TradeDeliverGuide> guideDelivers = guidDeliverOrderService.getGuideOrdersByDeliverNo(orderBean, tradeOrderDeliver);
			boolean allReject = true;
			boolean demandDeal = false;
			for (TradeDeliverGuide temp : guideDelivers) {
				DeliverDemandDeal deliverDemandDeal=DeliverDemandDeal.getType(temp.getDemandDeal());
				GuidDeliverStatus guidDeliverStatus=GuidDeliverStatus.getType(temp.getDeliverStatus());
				
				if(GuidDeliverStatus.pkfailed.equals(guidDeliverStatus)||GuidDeliverStatus.pkfailedPush.equals(guidDeliverStatus)){
					continue;
				}
				
				if (!DeliverDemandDeal.noaccept.equals(deliverDemandDeal) && GuidDeliverStatus.sendpush.equals(guidDeliverStatus)) {
					allReject = false;
				}
				if (DeliverDemandDeal.accept.equals(deliverDemandDeal)) {
					demandDeal = true;
				}
			}
			TradeDeliverStatus tradeDeliverStatus =TradeDeliverStatus.getType(tradeOrderDeliver.getDeliverStatus());
			// 不是全部拒绝需要判断是否到间隔时间
			if (!allReject) {
				Date deliverTime = tradeOrderDeliver.getDeliverTime();
				
				if (TradeDeliverStatus.delivered.equals(tradeDeliverStatus)) {
					long ctime = deliverTime.getTime() + span * 60 * 1000;
					if (demandDeal) {
						ctime = ctime + 2 * 60 * 1000;
					}
					Date newDeliverTime = new Date(ctime);
					// 未到重发时间
					if (newDeliverTime.after(now)) {
						return;
					}
				}
			}
			//更新以前批次 全为pk失败
			log.info("startToReDeliver orderNO :" + orderBean.getOrderNo() + "处理开始");
			if (guideDelivers != null && guideDelivers.size() > 0) {
				// 更新已发导游的状态
				int opnum = guidDeliverOrderService.updateAllBachNoDeliverStatus(tradeOrderDeliver.getDeliverNo(), GuidDeliverStatus.sendpush, GuidDeliverStatus.pkfailedPush);
				log.info("订单号：【"+tradeOrderDeliver.getOrderNo()+"】发单批次号：【"+tradeOrderDeliver.getDeliverNo()+"】修改导游发单状态个数为【"+opnum+"】");
			}

			// 1.查找下一个应发导游
			// 获取所有导游
			List<TradeDeliverGuide> guidesource = guidDeliverOrderService.getGuideOrdersByExcludeDeliverNo(orderBean, tradeOrderDeliver);

			List<TradeDeliverGuide> guidetarget = new ArrayList<TradeDeliverGuide>();
			guidetarget.addAll(guidesource);
			List<TradeDeliverGuide> guideDemand = new ArrayList<TradeDeliverGuide>();

			// 移除已经发送的导游,以及拒接和取消重发的导游

			for (TradeDeliverGuide tradeDeliverGuide : guidesource) {
				GuidDeliverStatus guidDeliverStatus =GuidDeliverStatus.getType(tradeDeliverGuide.getDeliverStatus());
				
				DeliverDemandDeal deliverDemandDeal=DeliverDemandDeal.getType(tradeDeliverGuide.getDemandDeal());
				if ( GuidDeliverStatus.resend.equals(guidDeliverStatus) || DeliverDemandDeal.noaccept.equals(deliverDemandDeal)) {
					guidetarget.remove(tradeDeliverGuide);
					continue;
				}

				for (TradeDeliverGuide guideDeliver : guideDelivers) {
					if (tradeDeliverGuide.getGuideId().equals(guideDeliver.getGuideId())) {
						guidetarget.remove(tradeDeliverGuide);
					}

				}

			}

			// 获取所有已表态导游
			for (TradeDeliverGuide guideDeliver : guidetarget) {
				DeliverDemandDeal deliverDemandDeal=DeliverDemandDeal.getType(guideDeliver.getDemandDeal());
				if (DeliverDemandDeal.accept.equals(deliverDemandDeal)) {
					// 后续工具类
					guideDemand.add(guideDeliver);
				}
			}

			Integer deliverCountObj = tradeOrderDeliver.getDeliverCount();
			int deliverCount = 0;
			if (deliverCountObj != null) {
				deliverCount = deliverCountObj.intValue();
			}
			// 应发导游列表
			List<TradeDeliverGuide> tradeDeliverGuides = new ArrayList<TradeDeliverGuide>();
			if (deliverCount < 4 && guideDemand.size() > 0) {
				log.info("deliver orderNO :" + orderBean.getOrderNo() + "发送一个导游");
				// 获取下一个应发导游
				String guideId = orderPkService.getPKGuideByServiceGrade(guideDemand);

				if (guideId.length() > 0) {
					TradeDeliverGuide originalDeliverGuide = new TradeDeliverGuide();

					for (TradeDeliverGuide guideDeliver : guidetarget) {
						if (guideDeliver.getGuideId().equalsIgnoreCase(guideId)) {
							originalDeliverGuide = guideDeliver;
							break;
						}
					}
					tradeDeliverGuides.add(originalDeliverGuide);

				}

			} else if (guidetarget.size() > 0) {// 剩下导游一批发送
				log.info("deliver orderNO :" + orderBean.getOrderNo() + "发送最后一批导游");
				tradeDeliverGuides.addAll(guidetarget);
				// 查找新增导游
				// 获取非本批次发送的所有导游
				// 目前为止可发导游
				List<GuideBean> guideList = guideDeliverService.getDeliverGuidByCityGuideLevel(orderBean);
				
				//获取取消的导游
				TradeDeliverGuideExample tradeDeliverGuideExample= new TradeDeliverGuideExample();
				tradeDeliverGuideExample.createCriteria().andOrderNoEqualTo(orderBean.getOrderNo()).andDeliverTypeEqualTo(GuidDeliverStatus.resend.value);
				List<TradeDeliverGuide> tradeDeliverGuideResend = tradeDeliverGuideMapper.selectByExample(tradeDeliverGuideExample);
				
				
				for (GuideBean guideItem : guideList) {
					boolean isDeliver = false;	
					if(tradeDeliverGuideResend != null && tradeDeliverGuideResend.size()>0){
						for(TradeDeliverGuide resend:tradeDeliverGuideResend){
							if(guideItem.getGuideId().equals(resend.getGuideId())){
								isDeliver = true;
							}
						}
						
					}
					if(!isDeliver){
						for (TradeDeliverGuide guideDeliver : guideDelivers) {
							if (guideDeliver.getGuideId().equals(String.valueOf(guideItem.getGuideId()))) {
								isDeliver = true;
								break;
							}
						}
					}
					
					if (!isDeliver) {// 未发送，加入到发送队列
						TradeDeliverGuide newDeliverGuide = new TradeDeliverGuide();
						newDeliverGuide.setGuidePrice(guideDeliverService.getGuideFloatPrice(orderBean, guideItem));
						newDeliverGuide.setGuideId(guideItem.getGuideId() + "");

						tradeDeliverGuides.add(newDeliverGuide);
					}
				}

			} else {

			}

			// 无导游可发
			if (tradeDeliverGuides.size() == 0) {
				log.info("deliver orderNO :" + orderBean.getOrderNo() + "所有导游发送完毕");
				tradeDeliverService.updateStatus(tradeOrderDeliver.getDeliverNo(), TradeDeliverStatus.delivered, TradeDeliverStatus.resenddelivered);
				return;
			}
			List<String> pushGuideIds = new ArrayList<String>();
			String pushGuideStr="";
			int newDeliverCount = 0;
			for (TradeDeliverGuide deliverGuide : tradeDeliverGuides) {
				GuideBean guideBean = controllerService.getGuidByGuideId(deliverGuide.getGuideId());
				if (!guideBean.getGuideId().equals(TradeFinalStr.defaultGuideId)) {
					newDeliverCount++;
					pushGuideIds.add(String.valueOf(guideBean.getGuideId()));
					pushGuideStr+=guideBean.getGuideId()+",";
					guidDeliverOrderService.insertToGuide(tradeOrderDeliver, guideBean, orderBean, deliverGuide.getGuidePrice(), DeliverDemandDeal.init, GuidDeliverStatus.sendpush, DeliverType.Cancle_Send);
				}
			}
			if (newDeliverCount == 0) {
				return;
			}
			// 更新发包时间
			Timestamp deliverTime = new Timestamp(System.currentTimeMillis());

			tradeDeliverService.updateDeliverTime(tradeOrderDeliver.getDeliverNo(), deliverTime, deliverCount + newDeliverCount);
			// 发送PUSH
			if (pushGuideIds.size() > 0) {
				pushService.pushNewOrder(pushGuideIds, orderBean);
			}
			log.info("deliver orderNO :" + orderBean.getOrderNo() + "重发导游Id:"+pushGuideStr);

		}


	}

}
