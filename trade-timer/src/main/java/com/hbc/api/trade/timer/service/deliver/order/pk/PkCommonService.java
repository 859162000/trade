/**
 * @Author lukangle
 * @2015年11月15日@下午4:28:56
 */
package com.hbc.api.trade.timer.service.deliver.order.pk;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.hbc.api.trade.TradeFinalStr;
import com.hbc.api.trade.bdata.common.HttpClientService;
import com.hbc.api.trade.bdata.common.util.TConfigLoader;
import com.hbc.api.trade.bdata.common.util.TimeConverter;
import com.hbc.api.trade.bdata.mapper.guide.gen.AgencyMapper;
import com.hbc.api.trade.bdata.mapper.guide.gen.bean.GuideBean;
import com.hbc.api.trade.order.enums.TradeReturnCodeEnum;
import com.hbc.api.trade.order.enums.bean.LogType;
import com.hbc.api.trade.order.enums.bean.OperationType;
import com.hbc.api.trade.order.enums.deliver.DeliverPKFailType;
import com.hbc.api.trade.order.enums.deliver.DeliverPkStatus;
import com.hbc.api.trade.order.enums.deliver.GuidDeliverStatus;
import com.hbc.api.trade.order.enums.deliver.OrderSerialFlag;
import com.hbc.api.trade.order.enums.deliver.TradeDeliverStatus;
import com.hbc.api.trade.order.enums.order.OrderStatus;
import com.hbc.api.trade.order.enums.order.PriceHistoryOpType;
import com.hbc.api.trade.order.exception.TradeException;
import com.hbc.api.trade.order.mapping.gen.OrderBeanMapper;
import com.hbc.api.trade.order.mapping.gen.TradeGuideAttitudinalMapper;
import com.hbc.api.trade.order.mapping.gen.TradeOrderSerialMapper;
import com.hbc.api.trade.order.mapping.gen.bean.OrderBean;
import com.hbc.api.trade.order.mapping.gen.bean.TradeDeliverGuide;
import com.hbc.api.trade.order.mapping.gen.bean.TradeGuideAttitudinal;
import com.hbc.api.trade.order.mapping.gen.bean.TradeOrderDeliver;
import com.hbc.api.trade.order.mapping.gen.bean.TradeOrderSerial;
import com.hbc.api.trade.order.mapping.gen.bean.TradeOrderSerialExample;
import com.hbc.api.trade.order.mapping.genx.UpdateOrderBeanMapper;
import com.hbc.api.trade.order.mapping.genx.deliver.GxGuideAttitudinalMapper;
import com.hbc.api.trade.order.mapping.genx.xbean.OrderLogParamBean;
import com.hbc.api.trade.order.service.OrderLogService;
import com.hbc.api.trade.order.service.OrderQueryService;
import com.hbc.api.trade.order.service.TradePriceHistoryService;
import com.hbc.api.trade.order.service.deliver.ConflictOrderService;
import com.hbc.api.trade.order.service.deliver.GuidDeliverOrderService;
import com.hbc.api.trade.order.service.deliver.GuideAssignService;
import com.hbc.api.trade.order.service.deliver.OrderDeliverService;
import com.hbc.api.trade.order.service.deliver.TradeDeliverService;
import com.hbc.api.trade.order.service.deliver.conf.TradeCitySolrConf;
import com.hbc.api.trade.order.service.solr.TradeConfCollectionService;
import com.hbc.api.trade.third.ChannelService;
import com.hbc.api.trade.third.LControllerService;
import com.hbc.api.trade.third.push.CPushService;
import com.hbc.api.trade.third.push.GPushService;

@Component
public class PkCommonService {
	private final static Logger log = LoggerFactory.getLogger(PkCommonService.class);
	@Autowired
	private TradeDeliverService tradeDeliverService;
	@Autowired
	private GuidDeliverOrderService guidDeliverOrderService;
	@Autowired
	private OrderQueryService orderQueryService;
	@Autowired
	private ChannelService channelService;
	@Autowired
	private OrderDeliverService orderDeliverService;
	@Autowired
	LControllerService controllerService;
	@Autowired
	UpdateOrderBeanMapper updateOrderBeanMapper;
	@Autowired
	TradeConfCollectionService tradeConfCollectionService;
	@Autowired
	HttpClientService httpClientService;
	@Autowired
	TradeGuideAttitudinalMapper tradeGuideAttitudinalMapper;
	@Autowired
	GxGuideAttitudinalMapper gxGuideAttitudinalMapper;
	@Autowired
	AgencyMapper agencyMapper;
	@Autowired
	GPushService gPushService;
	@Autowired
	OrderBeanMapper orderBeanMapper;

	private int pkPeriod = TConfigLoader.getInt("trade.deliver.pkPeriod", 10 * 60);
	private int pkDelayPeriod = TConfigLoader.getInt("trade.deliver.pkDelayPeriod", 1 * 60);

	@Value("${trade.registerUrl}")
	private String registerUrl;

	@Transactional
	public void pkPriory(OrderBean orderBean, TradeOrderDeliver tradeOrderDeliver, IPkGuideService pkGuideService) {

		if (TradeFinalStr.defaultGuideId.equals(orderBean.getGuideId()) || orderBean.getGuideId() == null) {
			Date pktime = tradeOrderDeliver.getPkTime();
			Date deliverTime = tradeOrderDeliver.getDeliverTime();
			
			//做兼容  下个版本直接删除
			if(deliverTime==null){
				deliverTime = tradeOrderDeliver.getUpdateTime();
			}
			DeliverPkStatus deliverPkStatus = DeliverPkStatus.INIT;
			if (tradeOrderDeliver.getPkStatus() != null) {
				deliverPkStatus = DeliverPkStatus.getType(tradeOrderDeliver.getPkStatus());
			}
			TradeCitySolrConf tradeCitySolrConf = tradeConfCollectionService.queryCityConf(orderBean);
			if (DeliverPkStatus.PKING.equals(deliverPkStatus)) {
				if (((System.currentTimeMillis() - pktime.getTime()) / (1000)) >= pkDelayPeriod) {
					if (beforecheck(orderBean, tradeOrderDeliver)) {
						List<TradeDeliverGuide> deliverGuides = pkGuideService.getAllFitGuides(orderBean.getOrderNo());
						if (deliverGuides.size() > 0) {
							TradeDeliverGuide tradeDeliverGuide = pkGuideService.getMostFitGuide(orderBean, deliverGuides, tradeCitySolrConf);
							if (tradeDeliverGuide == null || tradeDeliverGuide.getGuideId() == null || TradeFinalStr.defaultGuideId.equals(tradeDeliverGuide.getGuideId())) {
								return;
							}
							pkEnd(orderBean, tradeOrderDeliver, tradeDeliverGuide, deliverGuides, GuidDeliverStatus.sendpush);
						} else {
							log.error("pk无发获取对应的发单导游信息orderno为【" + orderBean.getOrderNo() + "】");
						}
						tradeDeliverService.updatePkTime(tradeOrderDeliver.getDeliverNo());
					}
				}
			} else if (DeliverPkStatus.INIT.equals(deliverPkStatus)) {
				if (((System.currentTimeMillis() - deliverTime.getTime()) / (1000)) >= pkPeriod) {
					if (beforecheck(orderBean, tradeOrderDeliver)) {
						List<TradeDeliverGuide> deliverGuides = pkGuideService.getAllFitGuides(orderBean.getOrderNo());
						if (deliverGuides.size() > 0) {
							TradeDeliverGuide tradeDeliverGuide = pkGuideService.getMostFitGuide(orderBean, deliverGuides, tradeCitySolrConf);
							if (tradeDeliverGuide == null || tradeDeliverGuide.getGuideId() == null || TradeFinalStr.defaultGuideId.equals(tradeDeliverGuide.getGuideId())) {
								return;
							}
							pkEnd(orderBean, tradeOrderDeliver, tradeDeliverGuide, deliverGuides, GuidDeliverStatus.sendpush);
						}
						tradeDeliverService.updatePkTime(tradeOrderDeliver.getDeliverNo());
					}
				}
			} else {
				log.error(tradeOrderDeliver.getDeliverNo() + " 错误的状态 [" + deliverPkStatus.name + "]tradeOrderDeliver");
			}
		} else {
			log.info(orderBean.getOrderNo() + " 已经指派导游  更新其他所有 新订单为PK失败");
			pkFailed(orderBean, tradeOrderDeliver);
		}

	}

	public boolean beforecheck(OrderBean orderBean, TradeOrderDeliver tradeOrderDeliver) {
		OrderStatus orderStatus = OrderStatus.getStatus(orderBean.getOrderStatus());
		if (!OrderStatus.PAYSUCCESS.equals(orderStatus)) {
			log.info(orderBean.getOrderNo() +"订单状态已经变成" + orderStatus.name + " 发单直接结束");
			pkFailed(orderBean, tradeOrderDeliver);
			return false;
		}
		if (!TradeFinalStr.defaultGuideId.equals(orderBean.getGuideId())) {
			log.info(orderBean.getOrderNo() +"订单已经被强制指派给 " + orderBean.getGuideId() + " 发单直接结束");
			pkFailed(orderBean, tradeOrderDeliver);
			return false;
		}
		return true;
	}

	public void pkFailed(OrderBean orderBean, TradeOrderDeliver tradeOrderDeliver) {
		guidDeliverOrderService.updateOtherFailed(orderBean);
		tradeDeliverService.updatePkStatus(tradeOrderDeliver.getDeliverNo(), DeliverPkStatus.PK_FAILED);
		tradeDeliverService.updateStatus(tradeOrderDeliver.getDeliverNo(), TradeDeliverStatus.delivered, TradeDeliverStatus.deliverfailed);
	}

	@Autowired
	TradeOrderSerialMapper tradeOrderSerialMapper;
	@Autowired
	CPushService cpushService;
	@Autowired
	TradePriceHistoryService tradePriceHistoryService;
	@Autowired
	GuideAssignService guideAssignService;
	@Autowired
	ConflictOrderService conflictOrderService;
	@Autowired
	OrderLogService orderLogService;

	@Transactional
	public void pkEnd(OrderBean orderBean, TradeOrderDeliver tradeOrderDeliver, TradeDeliverGuide tradeDeliverGuide, List<TradeDeliverGuide> allTradeDeliverGuides, GuidDeliverStatus guidDeliverStatus) {
		if (tradeDeliverGuide == null) {
			// 当所有导游冲突时 直接返回
			return;
		}
		// PK 成功更新deliver_guide
		guidDeliverOrderService.pkSuccess(tradeDeliverGuide);
		boolean isSuccess = calculateGuideDemand(tradeDeliverGuide, true);
		if (isSuccess) {
			log.info("PK成功 更新 导游平衡表成功,OrderNo:" + tradeDeliverGuide.getOrderNo() + " GuideId:+" + tradeDeliverGuide.getGuideId());
		} else {
			log.info("PK成功 更新 导游平衡表失败,OrderNo:" + tradeDeliverGuide.getOrderNo() + " GuideId:+" + tradeDeliverGuide.getGuideId());
		}

		// PK 失败更新deliver_guide
		for (TradeDeliverGuide failTradeDeliverGuide : allTradeDeliverGuides) {
			if(failTradeDeliverGuide.getFailType() == null){
				failTradeDeliverGuide.setFailType(DeliverPKFailType.rp.value);
			}
			// 对失败的 deliver guide操作
			if (!failTradeDeliverGuide.getAllocatGno().equals(tradeDeliverGuide.getAllocatGno())) {
				guidDeliverOrderService.updatePKFailDeliverGuide(failTradeDeliverGuide.getOrderNo(), failTradeDeliverGuide.getGuideId(),
						DeliverPKFailType.getType(failTradeDeliverGuide.getFailType()), GuidDeliverStatus.pkfailed);

				isSuccess = calculateGuideDemand(failTradeDeliverGuide, false);
				if (isSuccess) {
					log.info("PK失败 更新 导游平衡表成功,OrderNo:" + tradeDeliverGuide.getOrderNo() + " GuideId:+" + failTradeDeliverGuide.getGuideId());
				} else {
					log.info("PK失败 更新 导游平衡表失败,OrderNo:" + tradeDeliverGuide.getOrderNo() + " GuideId:+" + failTradeDeliverGuide.getGuideId());
				}
			}
		}

		// 更新所有deliver为PK失败
		Timestamp pktime = new Timestamp(System.currentTimeMillis());
		tradeDeliverService.updatePkDeliverSuccess(tradeOrderDeliver, pktime);

		GuideBean guideBean = controllerService.getGuidByGuideId(tradeDeliverGuide.getGuideId());
		if (guideBean == null || guideBean.getFundAccountId().trim().length() == 0) {
			throw new TradeException(TradeReturnCodeEnum.NO_GUIDE, tradeDeliverGuide.getGuideId());
		}

		if (OrderSerialFlag.SERIAL.value.equals(orderBean.getSerialFlag())) {
			TradeOrderSerialExample tradeOrderSerialExample = new TradeOrderSerialExample();
			tradeOrderSerialExample.createCriteria().andMeetGuideIdEqualTo(guideBean.getGuideId()).andOrderNoEqualTo(orderBean.getOrderNo());
			List<TradeOrderSerial> slist = tradeOrderSerialMapper.selectByExample(tradeOrderSerialExample);

			if (slist.size() > 0) {
				TradeOrderSerial tradeOrderSerial = slist.get(0);
				orderBean.setSerialOrderNo(tradeOrderSerial.getMeetOrderNo());

				TradeOrderSerial priceTradeOrderSerial = new TradeOrderSerial();
				priceTradeOrderSerial.setGuidePrice(tradeDeliverGuide.getGuidePrice());
				tradeOrderSerialMapper.updateByExampleSelective(priceTradeOrderSerial, tradeOrderSerialExample);
				
				//被传单标记
//				OrderBeanExample orderBeanExample=new OrderBeanExample();
//				orderBeanExample.createCriteria().andOrderNoEqualTo(tradeOrderSerial.getMeetOrderNo())
				
				OrderBean orderBeanSerial = new OrderBean();
				orderBeanSerial.setOrderNo(tradeOrderSerial.getMeetOrderNo());
				orderBeanSerial.setSerialFlag(OrderSerialFlag.SERIALED.value);
				orderBeanSerial.setSerialOrderNo(orderBean.getOrderNo());
				int opNum = orderBeanMapper.updateByPrimaryKeySelective(orderBeanSerial);
				if(opNum !=1){
					log.error("更新订单NO：【"+orderBean.getOrderNo()+"】被串单的对应订单号：【"+tradeOrderSerial.getMeetOrderNo()+"】的被传单标记 失败");
					throw new TradeException(TradeReturnCodeEnum.ORDER_SERIAL_FLAG_ERROR, tradeOrderSerial.getMeetOrderNo());
					
				}				
			}
		}
		if (!tradeDeliverGuide.getGuidePrice().equals(orderBean.getPriceGuide())) {
			tradePriceHistoryService.deliverGuidePriceChange(orderBean.getOrderNo(), orderBean.getPriceGuide(), tradeDeliverGuide.getGuidePrice(), PriceHistoryOpType.DELIVER_GUIDE_LEVEL);
		}
		TradeCitySolrConf tradeCitySolrConf = tradeConfCollectionService.queryCityConf(orderBean);
		boolean isConflict = conflictOrderService.synConflict(orderBean, guideBean.getGuideId(), tradeCitySolrConf);
		if (isConflict) {
			throw new TradeException(TradeReturnCodeEnum.DELIVER_CONFICT, guideBean.getGuideId());
		}
		

		// 注册航班
		String fid = gPushService.registerFlight(orderBean);
		if (fid.trim().length() > 0) {
			orderBean.setFlightRegisterId(fid);
		}
		guideAssignService.assignGuide(tradeDeliverGuide.getGuideId(), orderBean, tradeDeliverGuide.getGuidePrice(), OrderStatus.PAYSUCCESS);

		OrderLogParamBean orderLogParamBean = new OrderLogParamBean();
				
		orderLogParamBean.setContent("导游 ["+guideBean.getGuideName()+"] 于 "+TimeConverter.formatDate(new Date())+" 接下此单");
		orderLogParamBean.setGuideId(guideBean.getGuideId());
		orderLogParamBean.setGuideName(guideBean.getGuideName());
		orderLogParamBean.setLogType(LogType.CONFIRMED_BY_GUIDE.value);
		orderLogParamBean.setOpType(OperationType.SYSTEM.value);
		orderLogParamBean.setOpUserName("PK 服务");
		orderLogParamBean.setOrderNo(orderBean.getOrderNo());		
		orderLogService.insertOrderLog(orderLogParamBean);
//		 cpushService.cGuideConfirm(orderBean, false);
		log.info("订单 [" + orderBean.getOrderNo() + "] 最终由 导游 [" + guideBean.getGuideId() + " ] [" + guideBean.getGuideName() + "] 服务");
	}

	/**
	 * 更新导游PK 平衡记录表
	 * 
	 * @param tradeDeliverGuide
	 * @param pkSuccess
	 * @return
	 */
	@Transactional
	private synchronized boolean calculateGuideDemand(TradeDeliverGuide tradeDeliverGuide, boolean pkSuccess) {
		boolean result = true;
		Date now = new Date();
		TradeGuideAttitudinal tradeGuideAttitudinal = tradeGuideAttitudinalMapper.selectByPrimaryKey(tradeDeliverGuide.getGuideId());
		if (tradeGuideAttitudinal == null || tradeGuideAttitudinal.getGuideId().equals(TradeFinalStr.defaultGuideId)) {
			tradeGuideAttitudinal = new TradeGuideAttitudinal();
			if (pkSuccess) {// 接单成功
				tradeGuideAttitudinal.setAccumulativeDealOrders(1);
				tradeGuideAttitudinal.setAccumulativeDemandTimes(1);
				tradeGuideAttitudinal.setAccumulativeExpectTimes(1);
				tradeGuideAttitudinal.setAccumulativeFailDays(0);
				tradeGuideAttitudinal.setAccumulativeFailTimes(0);
				tradeGuideAttitudinal.setCreateTime(now);
				tradeGuideAttitudinal.setGuideId(tradeDeliverGuide.getGuideId());
				tradeGuideAttitudinal.setReptdt(tradeGuideAttitudinal.getCreateTime());
				tradeGuideAttitudinal.setSerialFailDays(0);
				tradeGuideAttitudinal.setSerialFailTimes(0);
				tradeGuideAttitudinal.setTodayDealTimes(1);
				tradeGuideAttitudinal.setTodayFailTimes(0);
				int num = tradeGuideAttitudinalMapper.insert(tradeGuideAttitudinal);
				if (num == 0) {
					result = false;
				}
			} else {
				tradeGuideAttitudinal.setAccumulativeDealOrders(0);
				tradeGuideAttitudinal.setAccumulativeDemandTimes(1);
				tradeGuideAttitudinal.setAccumulativeExpectTimes(1);
				tradeGuideAttitudinal.setAccumulativeFailDays(1);
				tradeGuideAttitudinal.setAccumulativeFailTimes(1);
				tradeGuideAttitudinal.setCreateTime(now);
				tradeGuideAttitudinal.setGuideId(tradeDeliverGuide.getGuideId());
				tradeGuideAttitudinal.setReptdt(tradeGuideAttitudinal.getCreateTime());
				tradeGuideAttitudinal.setSerialFailDays(1);
				tradeGuideAttitudinal.setSerialFailTimes(1);
				tradeGuideAttitudinal.setTodayDealTimes(0);
				tradeGuideAttitudinal.setTodayFailTimes(1);
				int num = tradeGuideAttitudinalMapper.insert(tradeGuideAttitudinal);
				if (num == 0) {
					result = false;
				}
			}
		} else {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String nowStr = sdf.format(now);
			String rept = sdf.format(tradeGuideAttitudinal.getReptdt());
			tradeGuideAttitudinal.setAccumulativeDemandTimes(tradeGuideAttitudinal.getAccumulativeDemandTimes() + 1);
			tradeGuideAttitudinal.setAccumulativeExpectTimes(tradeGuideAttitudinal.getAccumulativeExpectTimes() + 1);
			if (pkSuccess) {// 接单成功

				tradeGuideAttitudinal.setAccumulativeDealOrders(tradeGuideAttitudinal.getAccumulativeDealOrders() + 1);

				tradeGuideAttitudinal.setSerialFailDays(0);
				tradeGuideAttitudinal.setSerialFailTimes(0);

				if (nowStr.equals(rept)) {// 今天表态过
					if (tradeGuideAttitudinal.getTodayDealTimes().intValue() == 0 && tradeGuideAttitudinal.getTodayFailTimes().intValue() > 0) {// 今天接单未成功过
						tradeGuideAttitudinal.setAccumulativeFailDays(tradeGuideAttitudinal.getAccumulativeFailDays() - 1);
					}
					tradeGuideAttitudinal.setTodayDealTimes(tradeGuideAttitudinal.getTodayDealTimes() + 1);

				} else {// 第一次表态
					tradeGuideAttitudinal.setReptdt(now);
					tradeGuideAttitudinal.setTodayFailTimes(0);
					tradeGuideAttitudinal.setTodayDealTimes(1);
				}

			} else {// 接单失败
				tradeGuideAttitudinal.setAccumulativeFailTimes(tradeGuideAttitudinal.getAccumulativeFailTimes() + 1);
				tradeGuideAttitudinal.setSerialFailTimes(tradeGuideAttitudinal.getSerialFailTimes() + 1);

				if (nowStr.equals(rept)) {// 今天表态过
					tradeGuideAttitudinal.setTodayFailTimes(tradeGuideAttitudinal.getTodayFailTimes() + 1);

				} else {// 今天第一次表态
					tradeGuideAttitudinal.setReptdt(now);
					tradeGuideAttitudinal.setSerialFailDays(tradeGuideAttitudinal.getSerialFailDays() + 1);
					tradeGuideAttitudinal.setTodayFailTimes(1);
					tradeGuideAttitudinal.setTodayDealTimes(0);
					tradeGuideAttitudinal.setAccumulativeFailDays(tradeGuideAttitudinal.getAccumulativeFailDays() + 1);
				}

			}
			// tradeGuideAttitudinalMapper.updateByPrimaryKey(tradeGuideAttitudinal);
			int num = gxGuideAttitudinalMapper.updateByPrimaryKey(tradeGuideAttitudinal);
			if (num == 0) {
				result = false;
			}

		}

		return result;

	}

}
