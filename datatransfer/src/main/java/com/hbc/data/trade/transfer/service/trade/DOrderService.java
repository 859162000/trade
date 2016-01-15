package com.hbc.data.trade.transfer.service.trade;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.hbc.api.trade.TradeFinalStr;
import com.hbc.api.trade.bdata.common.IDGenerotor;
import com.hbc.api.trade.bdata.common.redis.RedisDao;
import com.hbc.api.trade.bdata.common.util.DoubleUtil;
import com.hbc.api.trade.bdata.common.util.TimeConverter;
import com.hbc.api.trade.bdata.mapper.basedata.gen.AirportBeanMapper;
import com.hbc.api.trade.bdata.mapper.basedata.gen.CityBeanMapper;
import com.hbc.api.trade.bdata.mapper.basedata.gen.bean.AirportBean;
import com.hbc.api.trade.bdata.mapper.basedata.gen.bean.AirportBeanExample;
import com.hbc.api.trade.bdata.mapper.basedata.gen.bean.CityBean;
import com.hbc.api.trade.bdata.mapper.channel.gen.ChannelAgentUserMapper;
import com.hbc.api.trade.bdata.mapper.channel.gen.bean.ChannelAgentUser;
import com.hbc.api.trade.bdata.mapper.guide.gen.GuideBeanMapper;
import com.hbc.api.trade.bdata.mapper.guide.gen.GuideCarMapper;
import com.hbc.api.trade.bdata.mapper.guide.gen.bean.GuideBean;
import com.hbc.api.trade.bdata.mapper.guide.gen.bean.GuideCar;
import com.hbc.api.trade.bdata.mapper.guide.gen.bean.GuideCarExample;
import com.hbc.api.trade.order.enums.TradeReturnCodeEnum;
import com.hbc.api.trade.order.enums.alarm.AlarmType;
import com.hbc.api.trade.order.enums.deliver.DeliverPkStatus;
import com.hbc.api.trade.order.enums.deliver.DeliverType;
import com.hbc.api.trade.order.enums.deliver.DemandDeal;
import com.hbc.api.trade.order.enums.deliver.GuidDeliverStatus;
import com.hbc.api.trade.order.enums.deliver.IsReadable;
import com.hbc.api.trade.order.enums.deliver.OrderDeliverStatus;
import com.hbc.api.trade.order.enums.deliver.OrderSerialFlag;
import com.hbc.api.trade.order.enums.deliver.TradeDeliverStatus;
import com.hbc.api.trade.order.enums.deliver.UrgentFlag;
import com.hbc.api.trade.order.enums.order.CarGroupFlag;
import com.hbc.api.trade.order.enums.order.FlightIsCustom;
import com.hbc.api.trade.order.enums.order.GuideCommentStatus;
import com.hbc.api.trade.order.enums.order.OrderSource;
import com.hbc.api.trade.order.enums.order.OrderStatus;
import com.hbc.api.trade.order.enums.order.OrderType;
import com.hbc.api.trade.order.enums.order.SystemCommentStatus;
import com.hbc.api.trade.order.enums.order.UserCommentStatus;
import com.hbc.api.trade.order.enums.order.VisaType;
import com.hbc.api.trade.order.enums.ota.AgentChannelEnum;
import com.hbc.api.trade.order.enums.third.CarClassEnum;
import com.hbc.api.trade.order.enums.third.CarTypeEnum;
import com.hbc.api.trade.order.exception.TradeException;
import com.hbc.api.trade.order.mapping.gen.OrderBeanMapper;
import com.hbc.api.trade.order.mapping.gen.OrderLogMapper;
import com.hbc.api.trade.order.mapping.gen.PriceSnapshotMapper;
import com.hbc.api.trade.order.mapping.gen.TradeAlarmMapper;
import com.hbc.api.trade.order.mapping.gen.TradeDeliverGuideMapper;
import com.hbc.api.trade.order.mapping.gen.TradeGuideAttitudinalMapper;
import com.hbc.api.trade.order.mapping.gen.TradeOrderDeliverMapper;
import com.hbc.api.trade.order.mapping.gen.TradeOrderSerialMapper;
import com.hbc.api.trade.order.mapping.gen.TradeOrderTrackMapper;
import com.hbc.api.trade.order.mapping.gen.TradeRouteMapper;
import com.hbc.api.trade.order.mapping.gen.bean.OrderBean;
import com.hbc.api.trade.order.mapping.gen.bean.OrderLog;
import com.hbc.api.trade.order.mapping.gen.bean.PriceSnapshotWithBLOBs;
import com.hbc.api.trade.order.mapping.gen.bean.TradeAlarm;
import com.hbc.api.trade.order.mapping.gen.bean.TradeDeliverGuide;
import com.hbc.api.trade.order.mapping.gen.bean.TradeGuideAttitudinal;
import com.hbc.api.trade.order.mapping.gen.bean.TradeMoveImtoken;
import com.hbc.api.trade.order.mapping.gen.bean.TradeOrderDeliver;
import com.hbc.api.trade.order.mapping.gen.bean.TradeOrderDeliverExample;
import com.hbc.api.trade.order.mapping.gen.bean.TradeOrderSerial;
import com.hbc.api.trade.order.mapping.gen.bean.TradeOrderTrack;
import com.hbc.api.trade.order.mapping.gen.bean.TradeRoute;
import com.hbc.api.trade.order.validator.OrderValidator;
import com.hbc.api.trade.settle.enums.AccountEnums;
import com.hbc.api.trade.third.LControllerService;
import com.hbc.data.trade.transfer.mapping.hbcbasedata.gen.FinalAgentuserMapper;
import com.hbc.data.trade.transfer.mapping.hbcbasedata.gen.bean.FinalAgentuser;
import com.hbc.data.trade.transfer.mapping.hbcfinal.gen.FinalGuideDemandOrderMapper;
import com.hbc.data.trade.transfer.mapping.hbcfinal.gen.FinalGuideorderMapper;
import com.hbc.data.trade.transfer.mapping.hbcfinal.gen.FinalOrderAlarmMapper;
import com.hbc.data.trade.transfer.mapping.hbcfinal.gen.FinalOrderBeanMapper;
import com.hbc.data.trade.transfer.mapping.hbcfinal.gen.FinalOrderDailyMapper;
import com.hbc.data.trade.transfer.mapping.hbcfinal.gen.FinalOrderJourneyMapper;
import com.hbc.data.trade.transfer.mapping.hbcfinal.gen.FinalOrderOptimizationMapper;
import com.hbc.data.trade.transfer.mapping.hbcfinal.gen.FinalOrderPeruseMapper;
import com.hbc.data.trade.transfer.mapping.hbcfinal.gen.FinalOrderPickupMapper;
import com.hbc.data.trade.transfer.mapping.hbcfinal.gen.FinalOrderRouteMapper;
import com.hbc.data.trade.transfer.mapping.hbcfinal.gen.FinalOrderTransferMapper;
import com.hbc.data.trade.transfer.mapping.hbcfinal.gen.FinalOrderlogMapper;
import com.hbc.data.trade.transfer.mapping.hbcfinal.gen.bean.FinalGuideDemandOrder;
import com.hbc.data.trade.transfer.mapping.hbcfinal.gen.bean.FinalGuideDemandOrderCriteria;
import com.hbc.data.trade.transfer.mapping.hbcfinal.gen.bean.FinalGuideorder;
import com.hbc.data.trade.transfer.mapping.hbcfinal.gen.bean.FinalGuideorderCriteria;
import com.hbc.data.trade.transfer.mapping.hbcfinal.gen.bean.FinalOrderAlarm;
import com.hbc.data.trade.transfer.mapping.hbcfinal.gen.bean.FinalOrderAlarmCriteria;
import com.hbc.data.trade.transfer.mapping.hbcfinal.gen.bean.FinalOrderBean;
import com.hbc.data.trade.transfer.mapping.hbcfinal.gen.bean.FinalOrderDaily;
import com.hbc.data.trade.transfer.mapping.hbcfinal.gen.bean.FinalOrderJourney;
import com.hbc.data.trade.transfer.mapping.hbcfinal.gen.bean.FinalOrderJourneyCriteria;
import com.hbc.data.trade.transfer.mapping.hbcfinal.gen.bean.FinalOrderOptimization;
import com.hbc.data.trade.transfer.mapping.hbcfinal.gen.bean.FinalOrderOptimizationCriteria;
import com.hbc.data.trade.transfer.mapping.hbcfinal.gen.bean.FinalOrderPeruse;
import com.hbc.data.trade.transfer.mapping.hbcfinal.gen.bean.FinalOrderPickup;
import com.hbc.data.trade.transfer.mapping.hbcfinal.gen.bean.FinalOrderRoute;
import com.hbc.data.trade.transfer.mapping.hbcfinal.gen.bean.FinalOrderRouteCriteria;
import com.hbc.data.trade.transfer.mapping.hbcfinal.gen.bean.FinalOrderTransfer;
import com.hbc.data.trade.transfer.mapping.hbcfinal.gen.bean.FinalOrderlog;
import com.hbc.data.trade.transfer.mapping.hbcfinal.gen.bean.FinalOrderlogCriteria;
import com.hbc.data.trade.transfer.mapping.thirdparty.gen.bean.CtripOrder;
import com.hbc.data.trade.transfer.mapping.thirdparty.gen.bean.QunaOrder;
import com.hbc.data.trade.transfer.service.hfinal.FDailyOrderService;
import com.hbc.data.trade.transfer.service.hfinal.FPayService;
import com.hbc.data.trade.transfer.service.hfinal.FRefundService;
import com.hbc.data.trade.transfer.service.thrid.QunaOrderService;
import com.hbc.data.trade.transfer.service.thrid.XiechengOrderService;
import com.hbc.data.trade.transfer.util.StringConvert;
import com.hbc.datamove.base.enums.UserType;
import com.hbc.datamove.base.util.AccountIdUtil;
import com.hbc.datamove.base.util.UserIdConvertUtil;

@Component
public class DOrderService {
	private final static Logger log = LoggerFactory.getLogger(DOrderService.class);

	private static List<AirportBean> alist = new ArrayList<AirportBean>();
	@Autowired
	FDailyOrderService fdailyOrderService;
	@Autowired
	FPayService fpayService;
	@Autowired
	FRefundService frefundService;
	@Autowired
	QunaOrderService qunaOrderService;
	@Autowired
	XiechengOrderService xiechengOrderService;
	@Autowired
	FinalOrderPickupMapper finalOrderPickupMapper;
	@Autowired
	FinalOrderDailyMapper finalOrderDailyMapper;
	@Autowired
	FinalOrderPeruseMapper finalOrderPeruseMapper;
	@Autowired
	FinalOrderTransferMapper finalOrderTransferMapper;
	@Autowired
	FinalOrderJourneyMapper finalOrderJourneyMapper;
	@Autowired
	TradeOrderDeliverMapper tradeOrderDeliverMapper;
	@Autowired
	TradeOrderTrackMapper tradeOrderTrackMapper;
	@Autowired
	FinalOrderlogMapper finalOrderlogMapper;
	@Autowired
	OrderLogMapper orderLogMapper;
	@Autowired
	FinalOrderAlarmMapper finalOrderAlarmMapper;
	@Autowired
	TradeAlarmMapper tradeAlarmMapper;
	@Autowired
	FinalOrderOptimizationMapper finalOrderOptimizationMapper;

	@Autowired
	FinalOrderBeanMapper finalOrderBeanMapper;
	@Autowired
	TradeOrderSerialMapper tradeOrderSerialMapper;
	@Autowired
	FinalGuideDemandOrderMapper finalGuideDemandOrderMapper;
	@Autowired
	TradeGuideAttitudinalMapper tradeGuideAttitudinalMapper;
	@Autowired
	FinalGuideorderMapper finalGuideorderMapper;
	@Autowired
	TradeDeliverGuideMapper tradeDeliverGuideMapper;
	@Autowired
	FinalOrderRouteMapper finalOrderRouteMapper;
	@Autowired
	TradeRouteMapper tradeRouteMapper;

	@Autowired
	OrderBeanMapper orderBeanMapper;
	@Autowired
	GuideBeanMapper guideBeanMapper;

	// public OrderBean convertFinalOrder(FinalOrderBean finalOrderBean) {
	// FinalOrderDaily finalOrderDaily =
	// fdailyOrderService.getFinalOrderBean(finalOrderBean.getOrderid());
	// FOrderStatus fstatus =
	// FOrderStatus.getStatus(finalOrderBean.getStatus());
	//
	// List<FinalOrderPay> fpayList =
	// fpayService.getPayInfosByOrderNo(finalOrderBean.getOrderid());
	// FinalOrderPay finalOrderPay = null;
	// if (fpayList.size() == 1) {
	// finalOrderPay = fpayList.get(0);
	// }
	//
	// if (FOrderStatus.INITSTATE.equals(fstatus)) {
	//
	// }
	// }
	@Autowired
	DPayService dpayService;
	@Autowired
	TradeMoveInfoService tradeMoveInfoService;
	@Autowired
	RedisDao redisDao;

	@Autowired
	CityBeanMapper cityBeanMapper;

	public int redisN = 5;

	@Transactional
	public OrderBean moveOrder(FinalOrderBean finalOrderBean) {
		OrderBean orderBean = new OrderBean();
		try {

			if (tradeMoveInfoService.isSuccess(finalOrderBean.getOrderid())) {
				log.info(finalOrderBean.getOrderid() + " 已经成功迁移 无须处理");
			} else {
				try {
					if (finalOrderBean.getStatus() == null || finalOrderBean.getStatus().intValue() == -2 || finalOrderBean.getStatus().intValue() == -3 || finalOrderBean.getStatus().intValue() == -99) {
						log.info(finalOrderBean.getOrderid() + " 迁移失败 状态为 [" + finalOrderBean.getStatus() + "]");
						String orderStatus = "无状态";
						if (null != finalOrderBean.getStatus()) {
							orderStatus = finalOrderBean.getStatus().toString();
						}
						tradeMoveInfoService.insertMoveInfo(finalOrderBean.getOrderid(), orderBean.getOrderNo(), false, orderStatus + " 状态不做迁移", orderStatus + " 状态不做迁移");
						return null;
					}

					// moveGuideDemandOrder();
					orderBean = moveInitOrder(finalOrderBean);
					if (orderBean != null) {
						dpayService.setPayInfo(finalOrderBean, orderBean);
					}

				} catch (TradeException e) {
					tradeMoveInfoService.insertMoveInfo(finalOrderBean.getOrderid(), orderBean.getOrderNo(), false, "程序异常", e.getMessage());
					log.error("orderId:" + finalOrderBean.getOrderid(), e);
					return null;
				} catch (Exception ex) {
					tradeMoveInfoService.insertMoveInfo(finalOrderBean.getOrderid(), orderBean.getOrderNo(), false, "程序异常", ex.toString());
					log.error("orderId:" + finalOrderBean.getOrderid() + ex.getMessage(), ex);
					return null;
				}
				if (orderBean != null) {
					tradeMoveInfoService.insertMoveInfo(finalOrderBean.getOrderid(), orderBean.getOrderNo(), true, "迁移成功", "");
				}
			}
			return orderBean;
		} catch (Exception e) {
			tradeMoveInfoService.insertMoveInfo(finalOrderBean.getOrderid(), orderBean.getOrderNo(), false, "迁移失败", e.toString());
		}
		return null;
	}

	@Autowired
	LControllerService controllerService;
	@Autowired
	ChannelAgentUserMapper channelAgentUserMapper;
	@Autowired
	GuideCarMapper guideCarMapper;
	@Autowired
	FinalAgentuserMapper finalAgentuserMapper;
	@Autowired
	ImServiceMove imServiceMove;
	@Autowired
	AirportBeanMapper airportBeanMapper;

	public AirportBean getAcode(String name, String airportName, Integer cityId) {
		AirportBean code = new AirportBean();
		if (alist.size() == 0) {
			alist = airportBeanMapper.selectByExample(new AirportBeanExample());
		}
		AirportBean cityCode = new AirportBean();

		for (AirportBean airportBean : alist) {

			if (airportBean.getCityId().equals(cityId)) {
				cityCode = airportBean;
			}
			if (name.contains(airportBean.getCode())) {
				code = airportBean;
				break;
			} else if (airportName.contains(airportBean.getCode())) {
				code = airportBean;
				break;
			} else if (name.contains(airportBean.getName())) {
				code = airportBean;
				break;
			} else if (airportName.contains(airportBean.getName())) {
				code = airportBean;
				break;
			}
		}
		if (code.getAirportId() == null) {
			code = cityCode;
			cityCode.setIsView(1000);
		}

		return code;
	}

	/**
	 * 初始态订单 迁移
	 * 
	 * @param finalOrderBean
	 */
	@Autowired
	PriceSnapshotMapper priceSnapshotMapper;

	@Transactional
	private String setPriceMark(OrderBean orderBean) {
		String priceMark = orderBean.getOrderNo() + "|" + System.currentTimeMillis();
		PriceSnapshotWithBLOBs priceSnapshotWithBLOBs = new PriceSnapshotWithBLOBs();
		priceSnapshotWithBLOBs.setAirportCode(orderBean.getFlightAirportCode());
		priceSnapshotWithBLOBs.setCurrencyRate(-1f);
		priceSnapshotWithBLOBs.setAirportId(1);
		priceSnapshotWithBLOBs.setCarType(orderBean.getCarTypeId());
		priceSnapshotWithBLOBs.setChannel(orderBean.getOrderChannel());
		priceSnapshotWithBLOBs.setChannelDetail("NAN");
		priceSnapshotWithBLOBs.setChannelPrice(orderBean.getPriceChannel());
		priceSnapshotWithBLOBs.setChannelSlices("NAN");

		OrderType orderType = OrderType.getType(orderBean.getOrderType());
		if (OrderType.DAILY.equals(orderType)) {
			Double a = DoubleUtil.getNoFloat(orderBean.getPriceChannel() / orderBean.getTotalDays());
			Double houra = DoubleUtil.getNoFloat(a / 10);
			priceSnapshotWithBLOBs.setChargePerDay(a);
			priceSnapshotWithBLOBs.setChargePerHour(houra);
			priceSnapshotWithBLOBs.setChargePerKm(2d);
			priceSnapshotWithBLOBs.setChargePerMinute(1.5d);
			priceSnapshotWithBLOBs.setCityId(orderBean.getServiceCityId());
			priceSnapshotWithBLOBs.setCreateTime(new Date());
			priceSnapshotWithBLOBs.setDistance(orderBean.getDistance());
			priceSnapshotWithBLOBs.setEndLocation(orderBean.getDestAddressPoi());
			priceSnapshotWithBLOBs.setEstTime(orderBean.getExpectedCompTime());
			priceSnapshotWithBLOBs.setExpiredTime(orderBean.getCompleteTime());
			priceSnapshotWithBLOBs.setGuideDetail("NAN");
			priceSnapshotWithBLOBs.setGuidePrice(orderBean.getPriceGuide());
			priceSnapshotWithBLOBs.setIsUrgent(orderBean.getUrgentFlag());
			priceSnapshotWithBLOBs.setPriceFactor("NAN");
			priceSnapshotWithBLOBs.setPricemark(priceMark);
			priceSnapshotWithBLOBs.setPriceVersion(new Date().getSeconds());
			priceSnapshotWithBLOBs.setRule("");
			priceSnapshotWithBLOBs.setSeatCategory(orderBean.getCarSeatNum());
			priceSnapshotWithBLOBs.setServiceDate("NAN");
			priceSnapshotWithBLOBs.setStatus(2);
			priceSnapshotWithBLOBs.setSysDetail("");
			priceSnapshotWithBLOBs.setSysPrice(orderBean.getPriceBase());
			priceSnapshotWithBLOBs.setTicketPrice(orderBean.getPriceTicket());
			priceSnapshotWithBLOBs.setTicketSlices("NAN");
		} else {
			priceSnapshotWithBLOBs.setChargePerDay(0d);
			priceSnapshotWithBLOBs.setChargePerHour(0d);
			priceSnapshotWithBLOBs.setChargePerKm(2d);
			priceSnapshotWithBLOBs.setChargePerMinute(1.5d);
			priceSnapshotWithBLOBs.setCityId(orderBean.getServiceCityId());
			priceSnapshotWithBLOBs.setCreateTime(new Date());
			priceSnapshotWithBLOBs.setDistance(orderBean.getDistance());
			priceSnapshotWithBLOBs.setEndLocation(orderBean.getDestAddressPoi());
			priceSnapshotWithBLOBs.setEstTime(orderBean.getExpectedCompTime());
			priceSnapshotWithBLOBs.setExpiredTime(orderBean.getCompleteTime());
			priceSnapshotWithBLOBs.setGuideDetail("NAN");
			priceSnapshotWithBLOBs.setGuidePrice(orderBean.getPriceGuide());
			priceSnapshotWithBLOBs.setIsUrgent(orderBean.getUrgentFlag());
			priceSnapshotWithBLOBs.setPriceFactor("NAN");
			priceSnapshotWithBLOBs.setPricemark(priceMark);
			priceSnapshotWithBLOBs.setPriceVersion(new Date().getSeconds());
			priceSnapshotWithBLOBs.setRule("");
			priceSnapshotWithBLOBs.setSeatCategory(orderBean.getCarSeatNum());
			priceSnapshotWithBLOBs.setServiceDate("NAN");
			priceSnapshotWithBLOBs.setStatus(2);
			priceSnapshotWithBLOBs.setSysDetail("");
			priceSnapshotWithBLOBs.setSysPrice(orderBean.getPriceBase());
			priceSnapshotWithBLOBs.setTicketPrice(orderBean.getPriceTicket());
			priceSnapshotWithBLOBs.setTicketSlices("NAN");
		}
		priceSnapshotWithBLOBs.setUpdateTime(new Date());
		priceSnapshotWithBLOBs.setServiceType(orderBean.getOrderType());
		priceSnapshotWithBLOBs.setExpiredTime(new Date());
		priceSnapshotMapper.insert(priceSnapshotWithBLOBs);
		return priceMark;
	}

	@Transactional
	public OrderBean moveInitOrder(FinalOrderBean finalOrderBean) {

		OrderBean orderBean = new OrderBean();
		QunaOrder qunaOrder = null;
		CtripOrder ctripOrder = null;

		if (finalOrderBean.getOrdersn() == null) {
			log.error("订单号" + finalOrderBean.getOrderid() + " 订单SN不存在");
			throw new TradeException(TradeReturnCodeEnum.ORDER_PARAM_FAILED, "订单号" + finalOrderBean.getOrderid() + " 订单SN不存在");
		}
		String newOrderSN = "";
		if (finalOrderBean.getOrderid().length() == 18) {
			newOrderSN = finalOrderBean.getOrderid().substring(15, 16);
		}
		orderBean.setOrderNo(finalOrderBean.getOrdersn() + newOrderSN);

		// FOrderType forderType =
		// FOrderType.getType(finalOrderBean.getOrdertype());

		// if (FOrderType.DAILY.equals(forderType)) {
		// FinalOrderDaily finalOrderDaily =
		// fdailyOrderService.getFinalOrderBean(finalOrderBean.getOrderid());
		// orderBean.setAdultNum(finalOrderDaily.getAdultnum());
		//
		// } else if (FOrderType.PERUSE.equals(forderType)) {
		//
		// } else if (FOrderType.PICKUPORDER.equals(forderType)) {
		//
		// } else if (FOrderType.TRANSFER.equals(forderType)) {
		//
		// }

		orderBean.setAdminId(finalOrderBean.getAdminid());
		String imtoken = imServiceMove.obtainIMToken(orderBean.getOrderNo());
		if (imtoken != null && !StringUtils.isEmpty(imtoken)) {
			orderBean.setImToken(imtoken);
		}
		// agent
		// 客户ID。clienttype=1时为agentuserid；clienttype=2时为userpassportid
		if (finalOrderBean.getClienttype().equals(1)) {
			// 代理商 去哪 携程
			orderBean.setOrderSource(OrderSource.GDS.value);
			// orderBean.setAgentId();
			// orderBean.setAgentName(agentName);
			String agentUserId = UserIdConvertUtil.getNew(finalOrderBean.getClientid(), UserType.a.name());
			orderBean.setAgentOpid(agentUserId);
			// orderBean.setAgentOpid(finalOrderBean.getClientid());
			// orderBean.setAgentOpname(agentOpname);
			// 代理上相关信息获取
			// orderBean.setOrderChannel(OrderChannelFinal.cchannelId);

			qunaOrder = qunaOrderService.getQunaOrder(orderBean.getOrderNo());
			ctripOrder = xiechengOrderService.getXiechenOrder(orderBean.getOrderNo());

			if (qunaOrder != null) {
				orderBean.setOrderChannel(AgentChannelEnum.QUNAR_CHANNEL.value);
				orderBean.setThirdTradeNo(qunaOrder.getQorderid());
				orderBean.setUserAccount(AccountEnums.QUNA_ACCOUNT.value);
			} else if (ctripOrder != null) {
				orderBean.setOrderChannel(AgentChannelEnum.CTRIP_CHANNEL.value);
				orderBean.setThirdTradeNo(ctripOrder.getCtriporderid());
				orderBean.setUserAccount(AccountEnums.XIECHEN_ACCOUNT.value);
			} else {
				// gds 其他商家 渠道号
				// TODO 韩雪 api接口

				ChannelAgentUser channelAgentUser = channelAgentUserMapper.selectByPrimaryKey(Long.parseLong(agentUserId));
				if (channelAgentUser != null) {
					orderBean.setAgentOpname(channelAgentUser.getAgentUserName());
					orderBean.setAgentId(channelAgentUser.getAgentId());
					orderBean.setAgentName(channelAgentUser.getAgentName());
					orderBean.setOrderChannel(Integer.parseInt(channelAgentUser.getAgentId()));

					FinalAgentuser finalAgentuser = finalAgentuserMapper.selectByPrimaryKey(Integer.parseInt(finalOrderBean.getClientid()));
					if (finalAgentuser != null) {
						orderBean.setUserAccount(AccountIdUtil.getAgentAccountNo(finalAgentuser.getAgentid()));
					} else {
						tradeMoveInfoService.insertMoveInfo(finalOrderBean.getOrderid(), orderBean.getOrderNo(), false, finalOrderBean.getClientid() + " 在老系统中无代理商用户信息，无法迁移", finalOrderBean.getClientid() + " 在新系统中无代理商用户信息，无法迁移");
						log.error(finalOrderBean.getClientid() + " 在老系统中无代理商用户信息，无法迁移", finalOrderBean.getClientid() + " 在新系统中无代理商用户信息，无法迁移");
						return null;
					}

				} else {
					tradeMoveInfoService.insertMoveInfo(finalOrderBean.getOrderid(), orderBean.getOrderNo(), false, finalOrderBean.getClientid() + " 在新系统中无代理商用户信息，无法迁移", finalOrderBean.getClientid() + " 在新系统中无代理商用户信息，无法迁移");
					log.error(finalOrderBean.getClientid() + " 在老系统中无代理商用户信息，无法迁移", finalOrderBean.getClientid() + " 在新系统中无代理商用户信息，无法迁移");
					return null;
				}

				// orderBean.setAgentId(agentId);

				// orderBean.setOrderChannel(UserIdConvertUtil.getNew(finalOrderBean.getC,
				// UserType.a.name()));
			}
		} else if (finalOrderBean.getClienttype().equals(2)) {
			orderBean.setOrderSource(OrderSource.C.value);
			orderBean.setOrderChannel(AgentChannelEnum.C.value);
		} else {
			tradeMoveInfoService.insertMoveInfo(finalOrderBean.getOrderid(), orderBean.getOrderNo(), false, " 在老系统中 下单类型 ClientType 不正确，无法迁移", finalOrderBean.getClientid() + " 在老系统中 下单类型 ClientType 不正确，无法迁移");
			return null;
		}

		orderBean.setUserId(UserIdConvertUtil.getNew(finalOrderBean.getUserpassportid(), UserType.c.name()));

		orderBean.setUserCommentStatus(finalOrderBean.getUsercommentstatus());

		// orderBean.setServiceContinentId(serviceContinentId);

		// 车型

		String carDesc = "";
		switch (finalOrderBean.getSeatcategory().intValue()) {
		case 1:
			carDesc = "5座";
			orderBean.setCarSeatNum(5);
			break;
		case 2:
			carDesc = "7座";
			orderBean.setCarSeatNum(7);
			break;
		case 3:
			carDesc = "9座";
			orderBean.setCarSeatNum(9);
			break;
		case 4:
			carDesc = "12座";
			orderBean.setCarSeatNum(12);
			break;
		default:
			log.error(finalOrderBean.getOrderid() + " 无匹配座位数");
			tradeMoveInfoService.insertMoveInfo(finalOrderBean.getOrderid(), orderBean.getOrderNo(), false, finalOrderBean.getSeatcategory() + " 在老系统中 无匹配座位数，无法迁移", finalOrderBean.getSeatcategory() + " 在老系统中无匹配座位数，无法迁移");
			return null;
		}

		if (finalOrderBean.getType() == null) {
			log.error(finalOrderBean.getOrderid() + " 无车座类型");
			tradeMoveInfoService.insertMoveInfo(finalOrderBean.getOrderid(), orderBean.getOrderNo(), false, orderBean.getOrderNo() + " 在老系统中 无车座类型，无法迁移", orderBean.getOrderNo() + " 在老系统中无车座类型，无法迁移");
			return null;
		}

		CarTypeEnum cartype = CarTypeEnum.getType(finalOrderBean.getType().intValue());
		orderBean.setCarTypeId(cartype.value);

		carDesc = CarTypeEnum.getType(finalOrderBean.getType().intValue()).name.replaceAll("型", "") + carDesc;

		orderBean.setCarDesc(carDesc);
		orderBean.setCargroupFlag(0);

		// orderBean.setCargroupId(0);
		// 导游冗余信息 用SQL更新
		if (finalOrderBean.getGuideid() != null && finalOrderBean.getGuideid().intValue() > 0) {
			String guideId = UserIdConvertUtil.getNew(StringConvert.convertStr(finalOrderBean.getGuideid()), UserType.g.name());
			GuideBean guideBean = controllerService.getGuidByGuideId(guideId);
			orderBean.setGuideAccountNo(guideBean.getFundAccountId());
			;
			orderBean.setGuideAgencyBossId(guideBean.getAgencyBossId());

			orderBean.setGuideAgencyId(StringConvert.convertStr(guideBean.getAgencyId()));

			orderBean.setGuideAgencyType(guideBean.getAgencyType());
			orderBean.setGuideAreaCode(guideBean.getAreaCode());
			orderBean.setGuideAssignTime(finalOrderBean.getDealordertime());
			orderBean.setGuideCommentStatus(finalOrderBean.getGuidecommentstatus());
			orderBean.setGuideId(guideId + "");
			orderBean.setGuideMobile(guideBean.getMobile());
			orderBean.setGuideName(guideBean.getGuideName());
			orderBean.setGuideNo(guideBean.getGuideNo());
			if (finalOrderBean.getPreguideid() != null) {
				orderBean.setGuidePreId(UserIdConvertUtil.getNew(finalOrderBean.getPreguideid() + "", UserType.g.name()) + "");
			}

			// 导游车信息 用SQL语句批量更新

			// 冗余信息 后续SQL 更新
			GuideCarExample guideCarExample = new GuideCarExample();
			GuideCarExample.Criteria criteria = guideCarExample.createCriteria();
			criteria.andGuideIdEqualTo(orderBean.getGuideId());

			List<GuideCar> guideCars = guideCarMapper.selectByExample(guideCarExample);
			if (guideCars != null && guideCars.size() > 0) {
				orderBean.setCarId(guideCars.get(0).getCarId() + "");
				orderBean.setCarLicenseNum(guideCars.get(0).getCarLicenceNo());
				orderBean.setCarName(guideCars.get(0).getCarName());
				// orderBean.setCarSeatNum(guideCars.get(0).getCarSeatNum());
				// orderBean.setCarTypeId(guideCars.get(0).getCarType());
			}

		}

		orderBean.setGoodNo("8");

		String time1 = TimeConverter.formatDate(finalOrderBean.getServicedate(), TimeConverter.END_WITH_DATE);
		String time2 = "08:00:00";
		if (finalOrderBean.getServicetime() != null) {
			time2 = TimeConverter.formatDate(finalOrderBean.getServicetime(), TimeConverter.hour_data);
		}

		Date serviceTime = TimeConverter.toDate(time1 + " " + time2);

		long time = serviceTime.getTime();
		orderBean.setServiceTime(serviceTime);

		// 获取订单详情
		if (1 == finalOrderBean.getOrdertype().intValue()) {// 接机
			FinalOrderPickup finalPickUpOrder = finalOrderPickupMapper.selectByPrimaryKey(finalOrderBean.getOrderid());
			if (finalPickUpOrder == null || finalPickUpOrder.getOrderid() == null || finalPickUpOrder.getOrderid().equals("")) {
				log.error("订单号" + finalPickUpOrder.getOrderid() + " 接机订单 未找到匹配的订单详情");
				tradeMoveInfoService.insertMoveInfo(finalOrderBean.getOrderid(), orderBean.getOrderNo(), false, "订单号" + finalOrderBean.getOrderid() + " 接机订单 未找到匹配的订单详情  不做迁移", "订单号" + finalOrderBean.getOrderid() + " 接机订单 未找到匹配的订单详情 不做迁移");
				return null;
			}

			if (finalPickUpOrder.getStatus().intValue() != finalOrderBean.getStatus().intValue()) {
				log.error("订单号" + finalPickUpOrder.getOrderid() + " 订单状态与详情表中订单状态不一致，不做迁移");
				tradeMoveInfoService.insertMoveInfo(finalOrderBean.getOrderid(), orderBean.getOrderNo(), false, "订单号" + finalPickUpOrder.getOrderid() + " 订单状态与详情表中订单状态不一致，不做迁移", "订单号" + finalPickUpOrder.getOrderid() + " 订单状态与详情表中订单状态不一致，不做迁移");
				return null;
			}

			if (finalPickUpOrder.getChildnum() == null) {
				orderBean.setChildNum(0);
			} else {
				orderBean.setChildNum(finalPickUpOrder.getChildnum());
			}

			if (finalPickUpOrder.getChildseat() != null && finalPickUpOrder.getChildseat().length() > 0) {
				String[] childSeats = finalPickUpOrder.getChildseat().split(",");
				int a = 0;
				int b = 0;
				int c = 0;
				int d = 0;
				for (int i = 0; i < childSeats.length; i++) {
					if (childSeats[i].equals("1")) {
						a++;
					} else if (childSeats[i].equals("2")) {
						b++;
					} else if (childSeats[i].equals("3")) {
						c++;
					} else if (childSeats[i].equals("4")) {
						d++;
					}
				}
				String childSeat = "";
				if (a > 0) {
					childSeat += "1-" + a + ",";
				}
				if (b > 0) {
					childSeat += "2-" + b + ",";
				}
				if (c > 0) {
					childSeat += "3-" + c + ",";
				}
				if (d > 0) {
					childSeat += "4-" + d + ",";
				}

				if (childSeat.length() > 0) {
					childSeat = childSeat.substring(0, childSeat.length() - 1);
					orderBean.setChildSeat(childSeat);
				}

			}

			orderBean.setDestAddressPoi(finalPickUpOrder.getDestlocation());
			orderBean.setDistance(finalPickUpOrder.getDistance().doubleValue());

			if (finalPickUpOrder.getDuration() != null) {
				orderBean.setExpectedCompTime(finalPickUpOrder.getDuration() / 60);
				time += finalPickUpOrder.getDuration().intValue() * 1000;
				Date serviceEndTime = new Date(time);
				orderBean.setServiceEndTime(serviceEndTime);
			}

			// orderBean.setFlightAirportBuiding(finalPickUpOrder.getTerminal());
			orderBean.setFlightAirportCode(finalPickUpOrder.getDep());
			// orderBean.setFlightAirportName(flightAirportName);

			orderBean.setFlightArriveTime(orderBean.getServiceTime());
			if (finalPickUpOrder.getBrandsign() == null) {
				orderBean.setFlightBrandSign(finalPickUpOrder.getUsername());
			} else {
				orderBean.setFlightBrandSign(finalPickUpOrder.getBrandsign());
			}

			orderBean.setFlightFlyTime(finalPickUpOrder.getFlightdeptimeplandate());
			orderBean.setFlightIsCustom(0);
			orderBean.setFlightNo(finalPickUpOrder.getFlightno());
			orderBean.setFlightRegisterId(finalPickUpOrder.getRegisterflightid());
			orderBean.setIsArrivalVisa(finalPickUpOrder.getIsarrivalvisa());
			if (finalPickUpOrder.getDestmobile() != null) {
				String[] mobile = finalPickUpOrder.getDestmobile().split("-");
				if (mobile != null && mobile.length > 1) {
					orderBean.setServiceAddressTel(mobile[1]);
					orderBean.setServiceAreaCode(mobile[0]);
				}
			}
			if (finalPickUpOrder.getArr() != null) {
				orderBean.setFlightDestCode(finalPickUpOrder.getArr());
				AirportBean result = this.getAcode(finalPickUpOrder.getArr(), finalPickUpOrder.getDeparture() == null ? finalPickUpOrder.getAirport() : finalPickUpOrder.getDeparture(), finalPickUpOrder.getPlacecityid());
				if (result != null) {
					if (result.getIsView() != null && result.getIsView().equals(1000)) {
						orderBean.setGoodNo("9");
					}
					orderBean.setFlightDestName(result.getName());
				} else {
					orderBean.setGoodNo("9");
				}
			} else {

				AirportBean result = this.getAcode(finalPickUpOrder.getDeparture() == null ? "" : finalPickUpOrder.getDeparture(), finalPickUpOrder.getAirport() == null ? "" : finalPickUpOrder.getAirport(), finalPickUpOrder.getPlacecityid());
				if (result != null) {
					if (result.getIsView() != null && result.getIsView().equals(1000)) {
						orderBean.setGoodNo("9");
					}
					orderBean.setFlightDestCode(result.getCode());
					orderBean.setFlightDestName(result.getName());
				} else {
					orderBean.setGoodNo("9");
				}

			}

			orderBean.setServiceCityName(finalPickUpOrder.getCityname());
			orderBean.setStartAddressPoi(finalPickUpOrder.getDeptlocation());
			if (finalPickUpOrder.getAdultnum() != null && finalPickUpOrder.getAdultnum().intValue() > 0) {
				orderBean.setAdultNum(finalPickUpOrder.getAdultnum());
			} else {
				orderBean.setAdultNum(1);
			}

			// 订单联系人信息

			orderBean.setUserName(finalPickUpOrder.getUsername());
			if (finalPickUpOrder.getAreacode() != null && finalPickUpOrder.getAreacode().length() > 0) {
				orderBean.setUserAreaCode1(finalPickUpOrder.getAreacode());
			} else {
				orderBean.setUserAreaCode1(" ");
			}
			if (finalPickUpOrder.getUsermobile() != null && finalPickUpOrder.getUsermobile().length() > 0) {
				orderBean.setUserMobile1(finalPickUpOrder.getUsermobile());
			} else {
				orderBean.setUserMobile1(" ");
			}
			if (finalPickUpOrder.getUsermobile1() != null && finalPickUpOrder.getUsermobile1().length() > 0) {
				String[] mobile = finalPickUpOrder.getUsermobile1().split("-");
				if (mobile != null && mobile.length > 1) {

					orderBean.setUserAreaCode2(mobile[0]);
					orderBean.setUserMobile2(mobile[1]);
				}
			}
			if (finalPickUpOrder.getUsermobile2() != null && finalPickUpOrder.getUsermobile2().length() > 0) {
				String[] mobile = finalPickUpOrder.getUsermobile2().split("-");
				if (mobile != null && mobile.length > 1) {

					orderBean.setUserAreaCode3(mobile[0]);
					orderBean.setUserMobile3(mobile[1]);
				}
			}

		} else if (2 == finalOrderBean.getOrdertype().intValue()) {
			FinalOrderTransfer finalOrderTransfer = finalOrderTransferMapper.selectByPrimaryKey(finalOrderBean.getOrderid());
			if (finalOrderTransfer == null || finalOrderTransfer.getOrderid() == null || finalOrderTransfer.getOrderid().equals("")) {
				log.error("订单号" + finalOrderTransfer.getOrderid() + " 送机订单 未找到匹配的订单详情");
				tradeMoveInfoService.insertMoveInfo(finalOrderBean.getOrderid(), orderBean.getOrderNo(), false, "订单号" + finalOrderBean.getOrderid() + " 接机订单 未找到匹配的订单详情  不做迁移", "订单号" + finalOrderBean.getOrderid() + " 接机订单 未找到匹配的订单详情 不做迁移");
				return null;
			}
			if (finalOrderTransfer.getStatus().intValue() != finalOrderBean.getStatus().intValue()) {
				log.error("订单号" + finalOrderTransfer.getOrderid() + " 订单状态与详情表中订单状态不一致，不做迁移");
				tradeMoveInfoService.insertMoveInfo(finalOrderBean.getOrderid(), orderBean.getOrderNo(), false, "订单号" + finalOrderTransfer.getOrderid() + " 订单状态与详情表中订单状态不一致，不做迁移", "订单号" + finalOrderTransfer.getOrderid() + " 订单状态与详情表中订单状态不一致，不做迁移");
				return null;
			}

			if (finalOrderTransfer.getChildnum() == null) {
				orderBean.setChildNum(0);
			} else {
				orderBean.setChildNum(finalOrderTransfer.getChildnum());
			}

			if (finalOrderTransfer.getChildseat() != null && finalOrderTransfer.getChildseat().length() > 0) {
				String[] childSeats = finalOrderTransfer.getChildseat().split(",");
				int a = 0;
				int b = 0;
				int c = 0;
				int d = 0;
				for (int i = 0; i < childSeats.length; i++) {
					if (childSeats[i].equals("1")) {
						a++;
					} else if (childSeats[i].equals("2")) {
						b++;
					} else if (childSeats[i].equals("3")) {
						c++;
					} else if (childSeats[i].equals("4")) {
						d++;
					}
				}
				String childSeat = "";
				if (a > 0) {
					childSeat += "1-" + a + ",";
				}
				if (b > 0) {
					childSeat += "2-" + b + ",";
				}
				if (c > 0) {
					childSeat += "3-" + c + ",";
				}
				if (d > 0) {
					childSeat += "4-" + d + ",";
				}

				if (childSeat.length() > 0) {
					childSeat = childSeat.substring(0, childSeat.length() - 1);
					orderBean.setChildSeat(childSeat);
				}

			}

			// orderBean.setChildSeat(finalOrderTransfer.getChildseat());
			orderBean.setDestAddressPoi(finalOrderTransfer.getDestlocation());
			orderBean.setDistance(finalOrderTransfer.getDistance().doubleValue());

			if (finalOrderTransfer.getDuration() != null) {
				orderBean.setExpectedCompTime(finalOrderTransfer.getDuration() / 60);
				time += finalOrderTransfer.getDuration().intValue() * 1000;
				Date serviceEndTime = new Date(time);
				orderBean.setServiceEndTime(serviceEndTime);
			}

			orderBean.setFlightNo(finalOrderTransfer.getFlightno());
			orderBean.setFlightFlyTime(finalOrderTransfer.getFlightdeptimeplandate());

			if (finalOrderTransfer.getDeptmobile() != null) {
				String[] mobile = finalOrderTransfer.getDeptmobile().split("-");
				if (mobile != null && mobile.length > 1) {
					orderBean.setServiceAddressTel(mobile[1]);
					orderBean.setServiceAreaCode(mobile[0]);
				}
			}

			if (finalOrderTransfer.getAirportcode() != null) {
				orderBean.setFlightAirportCode(finalOrderTransfer.getAirportcode());
				AirportBean code = this.getAcode(finalOrderTransfer.getAirportcode(), finalOrderTransfer.getDestination() == null ? finalOrderTransfer.getDetailaddress() : finalOrderTransfer.getDestination(), finalOrderTransfer.getPlacecityid());
				if (code != null) {
					if (code.getIsView() != null && code.getIsView().equals(1000)) {
						orderBean.setGoodNo("9");
					}
					orderBean.setFlightAirportName(code.getName());
				} else {
					orderBean.setGoodNo("9");
				}
			} else {

				AirportBean code = this.getAcode(finalOrderTransfer.getDestination() == null ? "" : finalOrderTransfer.getDestination(), finalOrderTransfer.getDetailaddress() == null ? "" : finalOrderTransfer.getDetailaddress(), finalOrderTransfer.getPlacecityid());
				if (code != null) {
					if (code.getIsView() != null && code.getIsView().equals(1000)) {
						orderBean.setGoodNo("9");
					}
					orderBean.setFlightAirportCode(code.getCode());
					orderBean.setFlightAirportName(code.getName());
				} else {
					orderBean.setGoodNo("9");
				}
			}

			orderBean.setServiceCityName(finalOrderTransfer.getCityname());
			orderBean.setStartAddressPoi(finalOrderTransfer.getDeptlocation());
			if (finalOrderTransfer.getAdultnum() != null && finalOrderTransfer.getAdultnum().intValue() > 0) {
				orderBean.setAdultNum(finalOrderTransfer.getAdultnum());
			} else {
				orderBean.setAdultNum(1);
			}

			// 订单联系人信息
			orderBean.setUserName(finalOrderTransfer.getUsername());
			if (finalOrderTransfer.getAreacode() != null && finalOrderTransfer.getAreacode().length() > 0) {
				orderBean.setUserAreaCode1(finalOrderTransfer.getAreacode());
			} else {
				orderBean.setUserAreaCode1(" ");
			}
			if (finalOrderTransfer.getUsermobile() != null && finalOrderTransfer.getUsermobile().length() > 0) {
				orderBean.setUserMobile1(finalOrderTransfer.getUsermobile());
			} else {
				orderBean.setUserMobile1(" ");
			}
			if (finalOrderTransfer.getUsermobile1() != null && finalOrderTransfer.getUsermobile1().length() > 0) {
				String[] mobile = finalOrderTransfer.getUsermobile1().split("-");
				if (mobile != null && mobile.length > 1) {

					orderBean.setUserAreaCode2(mobile[0]);
					orderBean.setUserMobile2(mobile[1]);
				}
			}
			if (finalOrderTransfer.getUsermobile2() != null && finalOrderTransfer.getUsermobile2().length() > 0) {
				String[] mobile = finalOrderTransfer.getUsermobile2().split("-");
				if (mobile != null && mobile.length > 1) {

					orderBean.setUserAreaCode3(mobile[0]);
					orderBean.setUserMobile3(mobile[1]);
				}
			}

		} else if (3 == finalOrderBean.getOrdertype().intValue()) {
			FinalOrderDaily finalOrderDaily = finalOrderDailyMapper.selectByPrimaryKey(finalOrderBean.getOrderid());
			if (finalOrderDaily == null || finalOrderDaily.getOrderid() == null || finalOrderDaily.getOrderid().equals("")) {
				log.error("订单号" + finalOrderDaily.getOrderid() + " 日租订单 未找到匹配的订单详情");
				tradeMoveInfoService.insertMoveInfo(finalOrderBean.getOrderid(), orderBean.getOrderNo(), false, "订单号" + finalOrderBean.getOrderid() + " 接机订单 未找到匹配的订单详情  不做迁移", "订单号" + finalOrderBean.getOrderid() + " 接机订单 未找到匹配的订单详情 不做迁移");
				return null;
			}
			if (finalOrderDaily.getStatus().intValue() != finalOrderBean.getStatus().intValue()) {
				log.error("订单号" + finalOrderBean.getOrderid() + " 订单状态与详情表中订单状态不一致，不做迁移");
				tradeMoveInfoService.insertMoveInfo(finalOrderBean.getOrderid(), orderBean.getOrderNo(), false, "订单号" + finalOrderBean.getOrderid() + " 订单状态与详情表中订单状态不一致，不做迁移", "订单号" + finalOrderBean.getOrderid() + " 订单状态与详情表中订单状态不一致，不做迁移");
				return null;
			}
			if (finalOrderDaily.getChildnum() == null) {
				orderBean.setChildNum(0);
			} else {
				orderBean.setChildNum(finalOrderDaily.getChildnum());
			}
			if (finalOrderDaily.getChildseat() != null && finalOrderDaily.getChildseat().length() > 0) {
				String[] childSeats = finalOrderDaily.getChildseat().split(",");
				int a = 0;
				int b = 0;
				int c = 0;
				int d = 0;
				for (int i = 0; i < childSeats.length; i++) {
					if (childSeats[i].equals("1")) {
						a++;
					} else if (childSeats[i].equals("2")) {
						b++;
					} else if (childSeats[i].equals("3")) {
						c++;
					} else if (childSeats[i].equals("4")) {
						d++;
					}
				}
				String childSeat = "";
				if (a > 0) {
					childSeat += "1-" + a + ",";
				}
				if (b > 0) {
					childSeat += "2-" + b + ",";
				}
				if (c > 0) {
					childSeat += "3-" + c + ",";
				}
				if (d > 0) {
					childSeat += "4-" + d + ",";
				}

				if (childSeat.length() > 0) {
					childSeat = childSeat.substring(0, childSeat.length() - 1);
					orderBean.setChildSeat(childSeat);
				}

			}
			// orderBean.setChildSeat(finalOrderDaily.getChildseat());
			if (finalOrderDaily.getDeptmobile() != null) {
				String[] mobile = finalOrderDaily.getDeptmobile().split("-");
				if (mobile != null && mobile.length > 1) {
					orderBean.setServiceAddressTel(mobile[1]);
					orderBean.setServiceAreaCode(mobile[0]);
				}
			}
			orderBean.setServiceCityName(finalOrderDaily.getCityname());

			orderBean.setServiceDailyType(finalOrderDaily.getServicetype());
			if (finalOrderDaily.getTerminalcityid() != null) {
				orderBean.setServiceEndCityid(finalOrderDaily.getTerminalcityid());
				orderBean.setServiceEndCityname(finalOrderDaily.getTerminalcityname());
			} else {
				orderBean.setServiceEndCityid(finalOrderDaily.getPlacecityid());
				orderBean.setServiceEndCityname(finalOrderDaily.getCityname());
			}

			// Date serviceEndTime = new
			// Date(finalOrderDaily.getServiceenddate()().getTime()+finalOrderBean.getServicetime().getTime());
			orderBean.setServiceEndTime(finalOrderDaily.getServiceenddate());
			orderBean.setServiceLocalDays(finalOrderDaily.getLocalday());
			orderBean.setServiceNonlocalDays(finalOrderDaily.getNonlocalday());
			int lday = 0;
			int oday = 0;
			if (orderBean.getServiceLocalDays() != null) {
				lday = orderBean.getServiceLocalDays();
			}

			if (orderBean.getServiceNonlocalDays() != null) {
				oday = orderBean.getServiceNonlocalDays();
			}
			orderBean.setTotalDays(oday + lday);
			if (finalOrderDaily.getPassbycityid() != null && finalOrderDaily.getPassbycityid().length() > 0) {
				String[] citys = finalOrderDaily.getPassbycityid().split(",");
				String passCity = "";
				if (citys != null && citys.length > 0) {
					for (int i = 0; i < citys.length; i++) {
						int index = citys[i].indexOf(":");
						String city = citys[i].substring(0, index);
						if (i == 0) {
							if (citys.length == 1) {
								int cityDay = orderBean.getTotalDays().intValue();
								if (orderBean.getTotalDays() > 1) {
									passCity += city + "-" + (cityDay - 1) + "," + city + "-1,";
								} else {
									passCity += city + "-1," + city + "-0,";
								}

							} else {
								passCity += city + "-" + orderBean.getTotalDays() + ",";
							}

						} else {
							passCity += city + "-0,";
						}
					}

				}
				if (passCity.length() > 0) {
					passCity = passCity.substring(0, passCity.length() - 1);
					orderBean.setServicePassCity(passCity);
				}
			} else {
				int cityDay = orderBean.getTotalDays().intValue();
				String passCity = "";

				if (orderBean.getTotalDays() > 1) {

					passCity += finalOrderBean.getPlacecityid() + "-" + (cityDay - 1) + "," + finalOrderBean.getPlacecityid() + "-1";
				} else {
					passCity += finalOrderBean.getPlacecityid() + "-1," + finalOrderBean.getPlacecityid() + "-0";
				}
				orderBean.setServicePassCity(passCity);
			}

			// orderBean.setServicePassCity(finalOrderDaily.getPassbycityid());
			orderBean.setJourneyComment(finalOrderDaily.getJourneycomment());
			if (finalOrderDaily.getAdultnum() != null && finalOrderDaily.getAdultnum().intValue() > 0) {
				orderBean.setAdultNum(finalOrderDaily.getAdultnum());
			} else {
				orderBean.setAdultNum(1);
			}

			// 订单联系人信息
			orderBean.setUserName(finalOrderDaily.getUsername());
			if (finalOrderDaily.getAreacode() != null && finalOrderDaily.getAreacode().length() > 0) {
				orderBean.setUserAreaCode1(finalOrderDaily.getAreacode());
			} else {
				orderBean.setUserAreaCode1(" ");
			}
			if (finalOrderDaily.getUsermobile() != null && finalOrderDaily.getUsermobile().length() > 0) {
				orderBean.setUserMobile1(finalOrderDaily.getUsermobile());
			} else {
				orderBean.setUserMobile1(" ");
			}
			if (finalOrderDaily.getUsermobile1() != null && finalOrderDaily.getUsermobile1().length() > 0) {
				String[] mobile = finalOrderDaily.getUsermobile1().split("-");
				if (mobile != null && mobile.length > 1) {

					orderBean.setUserAreaCode2(mobile[0]);
					orderBean.setUserMobile2(mobile[1]);
				}
			}
			if (finalOrderDaily.getUsermobile2() != null && finalOrderDaily.getUsermobile2().length() > 0) {
				String[] mobile = finalOrderDaily.getUsermobile2().split("-");
				if (mobile != null && mobile.length > 1) {

					orderBean.setUserAreaCode3(mobile[0]);
					orderBean.setUserMobile3(mobile[1]);
				}
			}
		} else if (4 == finalOrderBean.getOrdertype().intValue()) {
			FinalOrderPeruse finalOrderPeruse = finalOrderPeruseMapper.selectByPrimaryKey(finalOrderBean.getOrderid());
			if (finalOrderPeruse == null || finalOrderPeruse.getOrderid() == null || finalOrderPeruse.getOrderid().equals("")) {
				log.error("订单号" + finalOrderPeruse.getOrderid() + " 次租订单 未找到匹配的订单详情");
				tradeMoveInfoService.insertMoveInfo(finalOrderBean.getOrderid(), orderBean.getOrderNo(), false, "订单号" + finalOrderBean.getOrderid() + " 接机订单 未找到匹配的订单详情  不做迁移", "订单号" + finalOrderBean.getOrderid() + " 接机订单 未找到匹配的订单详情 不做迁移");
				return null;
			}
			if (finalOrderPeruse.getStatus().intValue() != finalOrderBean.getStatus().intValue()) {
				log.error("订单号" + finalOrderBean.getOrderid() + " 订单状态与详情表中订单状态不一致，不做迁移");
				tradeMoveInfoService.insertMoveInfo(finalOrderBean.getOrderid(), orderBean.getOrderNo(), false, "订单号" + finalOrderBean.getOrderid() + " 订单状态与详情表中订单状态不一致，不做迁移", "订单号" + finalOrderBean.getOrderid() + " 订单状态与详情表中订单状态不一致，不做迁移");
				return null;
			}
			if (finalOrderPeruse.getChildnum() == null) {
				orderBean.setChildNum(0);
			} else {
				orderBean.setChildNum(finalOrderPeruse.getChildnum());
			}
			if (finalOrderPeruse.getChildseat() != null && finalOrderPeruse.getChildseat().length() > 0) {
				String[] childSeats = finalOrderPeruse.getChildseat().split(",");
				int a = 0;
				int b = 0;
				int c = 0;
				int d = 0;
				for (int i = 0; i < childSeats.length; i++) {
					if (childSeats[i].equals("1")) {
						a++;
					} else if (childSeats[i].equals("2")) {
						b++;
					} else if (childSeats[i].equals("3")) {
						c++;
					} else if (childSeats[i].equals("4")) {
						d++;
					}
				}
				String childSeat = "";
				if (a > 0) {
					childSeat += "1-" + a + ",";
				}
				if (b > 0) {
					childSeat += "2-" + b + ",";
				}
				if (c > 0) {
					childSeat += "3-" + c + ",";
				}
				if (d > 0) {
					childSeat += "4-" + d + ",";
				}

				if (childSeat.length() > 0) {
					childSeat = childSeat.substring(0, childSeat.length() - 1);
				}
				orderBean.setChildSeat(childSeat);
			}
			// orderBean.setChildSeat(finalOrderPeruse.getChildseat());
			orderBean.setDestAddressPoi(finalOrderPeruse.getDestlocation());
			orderBean.setDistance(finalOrderPeruse.getDistance().doubleValue());

			if (finalOrderPeruse.getDuration() != null) {
				orderBean.setExpectedCompTime(finalOrderPeruse.getDuration() / 60);
				time += finalOrderPeruse.getDuration().intValue() * 1000;
				Date serviceEndTime = new Date(time);
				orderBean.setServiceEndTime(serviceEndTime);
			}
			orderBean.setServiceCityName(finalOrderPeruse.getCityname());
			orderBean.setStartAddressPoi(finalOrderPeruse.getDeptlocation());
			if (finalOrderPeruse.getAdultnum() != null && finalOrderPeruse.getAdultnum().intValue() > 0) {
				orderBean.setAdultNum(finalOrderPeruse.getAdultnum());
			} else {
				orderBean.setAdultNum(1);
			}

			// 订单联系人信息
			orderBean.setUserName(finalOrderPeruse.getUsername());
			if (finalOrderPeruse.getAreacode() != null && finalOrderPeruse.getAreacode().length() > 0) {
				orderBean.setUserAreaCode1(finalOrderPeruse.getAreacode());
			} else {
				orderBean.setUserAreaCode1(" ");
			}
			if (finalOrderPeruse.getUsermobile() != null && finalOrderPeruse.getUsermobile().length() > 0) {
				orderBean.setUserMobile1(finalOrderPeruse.getUsermobile());
			} else {
				orderBean.setUserMobile1(" ");
			}
			if (finalOrderPeruse.getUsermobile1() != null && finalOrderPeruse.getUsermobile1().length() > 0) {
				String[] mobile = finalOrderPeruse.getUsermobile1().split("-");
				if (mobile != null && mobile.length > 1) {

					orderBean.setUserAreaCode2(mobile[0]);
					orderBean.setUserMobile2(mobile[1]);
				}
			}
			if (finalOrderPeruse.getUsermobile2() != null && finalOrderPeruse.getUsermobile2().length() > 0) {
				String[] mobile = finalOrderPeruse.getUsermobile2().split("-");
				if (mobile != null && mobile.length > 1) {

					orderBean.setUserAreaCode3(mobile[0]);
					orderBean.setUserMobile3(mobile[1]);
				}
			}
		}

		// orderBean.setCheckInPrice(checkInPrice);
		// orderBean.setChildNum(childNum);
		// orderBean.setChildSeat(childSeat);

		// 乐乐回写
		// orderBean.setCoupId(coupId);
		// orderBean.setCoupLabel(coupLabel);
		// orderBean.setCoupPriceInfo(coupPriceInfo);
		// orderBean.setCoupType(coupType);
		orderBean.setCreateTime(finalOrderBean.getCreatedAt());
		orderBean.setDeliverAcpTime(finalOrderBean.getDealordertime());
		// orderBean.setDeliverStatus(deliverStatus);
		orderBean.setDeliverType(DeliverType.Ordinary.value);

		orderBean.setDestAddress(finalOrderBean.getDestination());
		orderBean.setDestAddressDetail(finalOrderBean.getDestdetailaddress());
		// orderBean.setDestAddressPoi(finalPickUpOrder.getDestlocation());
		// orderBean.setDistance(distance);
		// orderBean.setExpectedCompTime(expectedCompTime);
		// orderBean.setFlightAirportBuiding(flightAirportBuiding);
		// orderBean.setFlightAirportCode(flightAirportCode);
		// orderBean.setFlightAirportName(flightAirportName);
		// orderBean.setFlightArriveTime(flightArriveTime);
		// orderBean.setFlightBrandSign(flightBrandSign);
		// orderBean.setFlightFlyTime(flightFlyTime);
		// orderBean.setFlightIsCustom(flightIsCustom);
		// orderBean.setFlightNo(flightNo);
		// orderBean.setFlightRegisterId(flightRegisterId);

		//

		// orderBean.setImToken(finalOrderBean.getImtoken());
		// orderBean.setIsArrivalVisa(isArrivalVisa);
		// orderBean.setJourneyComment(journeyComment);

		// orderBean.setLineDescription(lineDescription);
		// orderBean.setLineSubject(lineSubject);

		// 获取下单渠道

		// orderBean.setOrderChannel(finalOrderBean);

		// orderBean.setOrderSource(orderSource);

		Date completeTime = new Date();
		OrderStatus orderStatus = OrderStatus.INITSTATE;
		OrderDeliverStatus orderDeliverStatus = OrderDeliverStatus.init;
		if (finalOrderBean.getStatus() == null) {
			log.error("订单号" + finalOrderBean.getOrderid() + " 订单状态不存在");
			throw new TradeException(TradeReturnCodeEnum.ORDER_PARAM_FAILED, "订单号" + finalOrderBean.getOrderid() + " 订单状态不存在");
		}
		if (0 == finalOrderBean.getStatus().intValue()) {// 初始化
			orderStatus = OrderStatus.INITSTATE;
			orderDeliverStatus = OrderDeliverStatus.init;
		} else if (1 == finalOrderBean.getStatus().intValue()) {//
			if (finalOrderBean.getDelivertime() != null) {
				orderDeliverStatus = OrderDeliverStatus.deliving;
			} else {
				orderDeliverStatus = OrderDeliverStatus.init;
			}
			orderStatus = OrderStatus.PAYSUCCESS;
		} else if (2 == finalOrderBean.getStatus().intValue()) {//
			orderStatus = OrderStatus.PAYSUCCESS;
			orderDeliverStatus = OrderDeliverStatus.deliversuccess;
		} else if (3 == finalOrderBean.getStatus().intValue()) {//
			orderDeliverStatus = OrderDeliverStatus.deliversuccess;
			orderStatus = OrderStatus.GUIDE_ARRIVED;
		} else if (4 == finalOrderBean.getStatus().intValue()) {//
			orderDeliverStatus = OrderDeliverStatus.deliversuccess;
			orderStatus = OrderStatus.PICK_CUSTOMER;
		} else if (5 == finalOrderBean.getStatus().intValue()) {//
			orderDeliverStatus = OrderDeliverStatus.deliversuccess;
			orderStatus = OrderStatus.STROKE_END;
		} else if (-1 == finalOrderBean.getStatus().intValue()) {//
			orderDeliverStatus = OrderDeliverStatus.init;
			if (finalOrderBean.getPaystatus() == null || 0 == finalOrderBean.getPaystatus().intValue()) {
				orderStatus = OrderStatus.CANCLE_CLOSE_NOPAY;
			} else {
				orderStatus = OrderStatus.CANCEL_CLOSE;
			}
		} else if (-3 == finalOrderBean.getStatus().intValue()) {//
			orderDeliverStatus = OrderDeliverStatus.init;
			orderStatus = OrderStatus.PAY_OUTTIME_CLOSE;
		} else if (-4 == finalOrderBean.getStatus().intValue()) {//
			orderDeliverStatus = OrderDeliverStatus.init;
			orderStatus = OrderStatus.CANCLE_CLOSE_NOPAY;
		} else if (11 == finalOrderBean.getStatus().intValue()) {//
			orderDeliverStatus = OrderDeliverStatus.deliversuccess;
			orderStatus = OrderStatus.STROKE_END;

			FinalOrderJourneyCriteria finalOrderJourneyExample = new FinalOrderJourneyCriteria();
			FinalOrderJourneyCriteria.Criteria criteria = finalOrderJourneyExample.createCriteria();
			criteria.andOrderidEqualTo(finalOrderBean.getOrderid());
			criteria.andTypeEqualTo(7);
			List<FinalOrderJourney> finalOrderJourneys = finalOrderJourneyMapper.selectByExample(finalOrderJourneyExample);
			if (finalOrderJourneys != null && finalOrderJourneys.size() == 1) {
				completeTime = finalOrderJourneys.get(0).getCreatedAt();
			}
			orderBean.setCompleteTime(completeTime);
		} else if (12 == finalOrderBean.getStatus().intValue()) {//
			orderDeliverStatus = OrderDeliverStatus.init;
			orderStatus = OrderStatus.PAYSUCCESS;
			// 需要插入 orderdeliver表

		} else if (97 == finalOrderBean.getStatus().intValue()) {//
			orderStatus = OrderStatus.PAYSUCCESS;
			orderDeliverStatus = OrderDeliverStatus.init;
			// orderBean.setDeliverStatus(OrderDeliverStatus.init.value);
		} else if (98 == finalOrderBean.getStatus().intValue()) {// 已发单
			orderDeliverStatus = OrderDeliverStatus.deliving;
			orderStatus = OrderStatus.PAYSUCCESS;

			// 需要插入 orderdeliver表
			TradeOrderDeliver tradeOrderDeliver = new TradeOrderDeliver();
			tradeOrderDeliver.setCityId(finalOrderBean.getPlacecityid());
			tradeOrderDeliver.setCreateTime(finalOrderBean.getDelivertime());
			tradeOrderDeliver.setDeliverNo("DLI" + IDGenerotor.generateDeliverNo());
			tradeOrderDeliver.setDeliverStatus(TradeDeliverStatus.delivered.value);
			tradeOrderDeliver.setDeliverTime(finalOrderBean.getDelivertime());
			tradeOrderDeliver.setDeliverType(DeliverType.Ordinary.value);
			tradeOrderDeliver.setDeliverTypeName(DeliverType.Ordinary.name);
			tradeOrderDeliver.setOrderNo(orderBean.getOrderNo());
			tradeOrderDeliver.setPkStatus(DeliverPkStatus.INIT.value);
			tradeOrderDeliver.setPkStatusName(DeliverPkStatus.INIT.name);
			tradeOrderDeliver.setUpdateTime(finalOrderBean.getDelivertime());

			int optnum = tradeOrderDeliverMapper.insert(tradeOrderDeliver);
			if (optnum != 1) {
				// tradeMoveInfoService.insertMoveInfo(finalOrderBean.getOrderid(),
				// orderBean.getOrderNo(), false, orderBean.getOrderNo() +
				// " 插入 发单表 失败，无法迁移", orderBean.getOrderNo() +
				// " 插入 发单表 失败，无法迁移");
				log.error("订单号" + finalOrderBean.getOrderid() + " 插入 发单表 失败");
				throw new TradeException(TradeReturnCodeEnum.ORDER_PARAM_FAILED, finalOrderBean.getOrderid() + " 插入 发单表 失败，无法迁移");
			}

		} else if (100 == finalOrderBean.getStatus().intValue()) {//
			orderDeliverStatus = OrderDeliverStatus.deliversuccess;
			orderStatus = OrderStatus.SETTLEMENT;
			FinalOrderJourneyCriteria finalOrderJourneyExample = new FinalOrderJourneyCriteria();
			FinalOrderJourneyCriteria.Criteria criteria = finalOrderJourneyExample.createCriteria();
			criteria.andOrderidEqualTo(finalOrderBean.getOrderid());
			criteria.andTypeEqualTo(7);
			List<FinalOrderJourney> finalOrderJourneys = finalOrderJourneyMapper.selectByExample(finalOrderJourneyExample);
			if (finalOrderJourneys != null && finalOrderJourneys.size() == 1) {
				completeTime = finalOrderJourneys.get(0).getCreatedAt();
			}
			orderBean.setCompleteTime(completeTime);
		}

		orderBean.setOrderStatus(orderStatus.value);
		orderBean.setOrderStatusName(orderStatus.name);
		orderBean.setDeliverStatus(orderDeliverStatus.value);

		orderBean.setOrderType(finalOrderBean.getOrdertype());

		if (finalOrderBean.getPrice() == null) {
			if (finalOrderBean.getGuideprice() != null) {
				orderBean.setPriceBase(finalOrderBean.getGuideprice().doubleValue());
			} else if (finalOrderBean.getClientprice() != null) {
				orderBean.setPriceBase(finalOrderBean.getClientprice().doubleValue());
			}
		} else {
			orderBean.setPriceBase(finalOrderBean.getPrice().doubleValue());
		}

		if (finalOrderBean.getClientprice() == null) {
			if (finalOrderBean.getGuideprice() != null) {
				orderBean.setPriceChannel(finalOrderBean.getGuideprice().doubleValue());
			} else if (finalOrderBean.getPrice() != null) {
				orderBean.setPriceChannel(finalOrderBean.getPrice().doubleValue());
			} else {
				throw new TradeException(TradeReturnCodeEnum.ORDER_PARAM_FAILED, "订单" + finalOrderBean.getOrderid() + " 所有价格均没有 不做迁移");
			}
		} else {
			orderBean.setPriceChannel(finalOrderBean.getClientprice().doubleValue());
		}

		orderBean.setPriceTicket(orderBean.getPriceChannel());

		if (finalOrderBean.getGuideprice() == null) {
			if (finalOrderBean.getPrice() != null) {
				orderBean.setPriceGuide(finalOrderBean.getPrice().doubleValue());
				orderBean.setPriceGuideBase(finalOrderBean.getPrice().doubleValue());
			} else if (finalOrderBean.getClientprice() != null) {
				orderBean.setPriceGuide(finalOrderBean.getClientprice().doubleValue());
				orderBean.setPriceGuideBase(finalOrderBean.getClientprice().doubleValue());
			}
		} else {
			orderBean.setPriceGuide(finalOrderBean.getGuideprice().doubleValue());
			orderBean.setPriceGuideBase(finalOrderBean.getGuideprice().doubleValue());
		}

		orderBean.setSerialFlag(finalOrderBean.getMode());
		orderBean.setSerialOrderNo(finalOrderBean.getRefid());
		// orderBean.setServiceAddressTel(finalOrderBean);
		// orderBean.setServiceAreaCode(serviceAreaCode);
		// orderBean.setServiceCityEnname(finalOrderBean);
		// 城市冗余信息 SQL更新
		orderBean.setServiceCityId(finalOrderBean.getPlacecityid());

		CityBean cityBean = cityBeanMapper.selectByPrimaryKey(finalOrderBean.getPlacecityid());
		if (cityBean != null) {
			orderBean.setServiceCityName(cityBean.getCityName());
			orderBean.setServiceCitySpell(cityBean.getSpell());
			orderBean.setServiceContinentId(cityBean.getContinentId());
			orderBean.setServiceContinentName(cityBean.getContinentName());
			orderBean.setServiceCountryId(cityBean.getCountryId());
			orderBean.setServiceCountryName(cityBean.getCountryName());
			orderBean.setServiceCityEnname(cityBean.getEnName());
		}

		// 日租信息
		// orderBean.setServiceDailyType(finalOrderBean);
		// orderBean.setServiceEndCityid(serviceEndCityid);
		// orderBean.setServiceEndCityname(serviceEndCityname);
		// orderBean.setServiceEndTime(serviceEndTime);
		// orderBean.setServiceLocalDays(serviceLocalDays);
		// orderBean.setServiceNonlocalDays(serviceNonlocalDays);
		// orderBean.setServicePassCity(servicePassCity);

		orderBean.setStartAddress(finalOrderBean.getDeparture());
		orderBean.setStartAddressDetail(finalOrderBean.getDeptdetailaddress());

		// orderBean.setGuideAgencyBossId(guideAgencyBossId);
		// orderBean.setStartAddressPoi(startAddressPoi);

		// 无法对应的
		// orderBean.setSystemCommentStatus(systemCommentStatus);
		// orderBean.setPriceMark(priceMark);
		// orderBean.setPriceReward(priceReward);
		// orderBean.setPriceTicket(priceTicket);
		// orderBean.setServicePassCity(servicePassCity);
		// orderBean.setOrderGoodsType(orderGoodsType);
		// orderBean.setGoodNo(goodNo);
		// orderBean.set

		// Integer customerNum = orderBean.getAdultNum() +
		// orderBean.getChildNum();
		// Integer useableCarSeatNum = orderBean.getCarSeatNum() - 1;
		// if(customerNum > useableCarSeatNum) {
		// if(orderBean.getAdultNum()>orderBean.getChildNum()){
		// if(orderBean.getAdultNum()>customerNum - useableCarSeatNum){
		// orderBean.setAdultNum(orderBean.getAdultNum()-customerNum +
		// useableCarSeatNum);
		// }else{
		// orderBean.setAdultNum(1);
		// orderBean.setChildNum(orderBean.getChildNum()-);
		// }
		// }
		//
		// }

		// orderBean.setDeliverStatus(deliverStatus);

		// 便于测试

		// orderBean.setOrderNo(orderNo);
		validateEnumType(orderBean);
		OrderValidator.validateChildSeat(orderBean);
		OrderValidator.validatePassCity(orderBean);
		if (orderBean.getAdultNum() == null || orderBean.getAdultNum() <= 0) {
			log.error("必须要有成年人，手机号：" + orderBean.getUserAreaCode1() + "-" + orderBean.getUserMobile1() + "，联系人：" + orderBean.getUserName());
			throw new TradeException(TradeReturnCodeEnum.ORDER_PARAM_FAILED, "成人数");
		}
		// OrderValidator.validateHeadCount(orderBean);
		// 添加默认值
		buildStaticOrderBody(orderBean);
		OrderStatus orderStatusd = OrderStatus.getStatus(orderBean.getOrderStatus());
		/**
		 * GUIDE_ARRIVED(2030,"导游到达"),
		 * 
		 * PICK_CUSTOMER(2050,"接到客人"),
		 * 
		 * STROKE_END(2070,"行程结束"),
		 * 
		 * CONFIRMED_COST(2080,"确认费用"),
		 */
		if (OrderStatus.INITSTATE.equals(orderStatusd) || OrderStatus.PAYSUCCESS.equals(orderStatusd) || OrderStatus.CONFIRMED_COST.equals(orderStatusd) || OrderStatus.GUIDE_ARRIVED.equals(orderStatusd) || OrderStatus.PICK_CUSTOMER.equals(orderStatusd)
				|| OrderStatus.STROKE_END.equals(orderStatusd)) {
			String priceMark = setPriceMark(orderBean);
			orderBean.setPriceMark(priceMark);
		}
		int optnum = orderBeanMapper.insert(orderBean);
		if (optnum != 1) {
			log.error("订单" + finalOrderBean.getOrderid() + " 插入 orderBean表 失败");
			throw new TradeException(TradeReturnCodeEnum.ORDER_PARAM_FAILED, "订单" + finalOrderBean.getOrderid() + " 插入 orderBean表 失败");
		}
		moveOrderOther(finalOrderBean, orderBean);

//		if (qunaOrder != null) {
//			qunaOrderService.moveQunaOrder(finalOrderBean, qunaOrder, orderBean);
//		} else if (ctripOrder != null) {
//			xiechengOrderService.moveXiechenOrder(finalOrderBean, ctripOrder, orderBean);
//		}

		return orderBean;
	}

	private void validateEnumType(OrderBean orderBean) {
		if (orderBean.getPriceChannel() == null || orderBean.getPriceBase() == null || orderBean.getPriceGuide() == null || orderBean.getPriceGuideBase() == null) {
			throw new TradeException(TradeReturnCodeEnum.ORDER_PARAM_FAILED, "订单" + orderBean.getOrderNo() + " 价格为空");
		}
		OrderType.getType(orderBean.getOrderType());
		CarClassEnum.getType(orderBean.getCarSeatNum());
	}

	/**
	 * 迁移导游平衡表
	 */
	@Transactional
	public void moveGuideDemandOrder() {
		log.info("开始迁移平衡表");
		FinalGuideDemandOrderCriteria finalGuideDemandOrderCriteria = new FinalGuideDemandOrderCriteria();
		FinalGuideDemandOrderCriteria.Criteria criteria = finalGuideDemandOrderCriteria.createCriteria();

		criteria.andGuideidIsNotNull();

		List<FinalGuideDemandOrder> finalGuideDemandOrders = finalGuideDemandOrderMapper.selectByExample(finalGuideDemandOrderCriteria);
		if (finalGuideDemandOrders != null && finalGuideDemandOrders.size() > 0) {
			for (FinalGuideDemandOrder finalGuideDemandOrder : finalGuideDemandOrders) {
				TradeGuideAttitudinal tradeGuideAttitudinal = new TradeGuideAttitudinal();
				tradeGuideAttitudinal.setAccumulativeDealOrders(finalGuideDemandOrder.getAccumulativedealorders());
				tradeGuideAttitudinal.setAccumulativeDemandTimes(finalGuideDemandOrder.getAccumulativedemandtimes());
				tradeGuideAttitudinal.setAccumulativeExpectTimes(finalGuideDemandOrder.getAccumulativeexpecttimes());
				tradeGuideAttitudinal.setAccumulativeFailDays(finalGuideDemandOrder.getAccumulativefaildays());
				tradeGuideAttitudinal.setAccumulativeFailTimes(finalGuideDemandOrder.getAccumulativefailtimes());
				tradeGuideAttitudinal.setCreateTime(finalGuideDemandOrder.getCreatedAt());

				tradeGuideAttitudinal.setGuideId(UserIdConvertUtil.getNew(StringConvert.convertStr(finalGuideDemandOrder.getGuideid()), UserType.g.name()));
				tradeGuideAttitudinal.setReptdt(finalGuideDemandOrder.getReptdt());
				tradeGuideAttitudinal.setSerialFailDays(finalGuideDemandOrder.getSerialfaildays());
				tradeGuideAttitudinal.setSerialFailTimes(finalGuideDemandOrder.getSerialfailtimes());
				tradeGuideAttitudinal.setTodayDealTimes(finalGuideDemandOrder.getTodaydealtimes());
				tradeGuideAttitudinal.setTodayFailTimes(finalGuideDemandOrder.getTodayfailtimes());
				tradeGuideAttitudinal.setUpdateTime(finalGuideDemandOrder.getUpdatedAt());
				int optnum = tradeGuideAttitudinalMapper.insert(tradeGuideAttitudinal);
				if (optnum != 1) {
					log.error("导游" + finalGuideDemandOrder.getGuideid() + " 插入 tradeGuideAttitudinal表 失败");
					throw new TradeException(TradeReturnCodeEnum.ORDER_PARAM_FAILED, "导游" + finalGuideDemandOrder.getGuideid() + " 插入 tradeGuideAttitudinal表 失败");
				} else {
					log.info(tradeGuideAttitudinal.getGuideId() + "平衡表迁移成功");
				}
			}
		}

		log.info("迁移平衡表完成");
	}

	/**
	 * 迁移订单其他表
	 * 
	 * @param orderBean
	 */
	@Transactional
	private void moveOrderOther(FinalOrderBean finalOrderBean, OrderBean orderBean) {
		moveOrderTrack(finalOrderBean, orderBean);
		moveOrderLog(finalOrderBean, orderBean);
		moveOrderAlarm(finalOrderBean, orderBean);
		moveOrderOptimization(finalOrderBean, orderBean);
		moveOrderGuide(finalOrderBean, orderBean);
		moveOrderRoute(finalOrderBean, orderBean);
	}

	/**
	 * 迁移订单行程动态表
	 * 
	 * @param orderBean
	 */
	@Transactional
	private void moveOrderTrack(FinalOrderBean finalOrderBean, OrderBean orderBean) {
		FinalOrderJourneyCriteria finalOrderJourneyExample = new FinalOrderJourneyCriteria();
		FinalOrderJourneyCriteria.Criteria criteria = finalOrderJourneyExample.createCriteria();
		criteria.andOrderidEqualTo(finalOrderBean.getOrderid());

		List<FinalOrderJourney> finalOrderJourneys = finalOrderJourneyMapper.selectByExample(finalOrderJourneyExample);
		if (finalOrderJourneys != null && finalOrderJourneys.size() > 0) {
			for (FinalOrderJourney finalOrderJourney : finalOrderJourneys) {
				TradeOrderTrack tradeOrderTrack = new TradeOrderTrack();
				tradeOrderTrack.setCreateTime(finalOrderJourney.getCreatedAt());
				tradeOrderTrack.setIsRead(finalOrderJourney.getIsread());
				tradeOrderTrack.setOrderNo(orderBean.getOrderNo());
				if (finalOrderJourney.getStatus().equals(1)) {
					tradeOrderTrack.setStatus(1);
				} else {
					tradeOrderTrack.setStatus(2);
				}
				tradeOrderTrack.setTrackContent(finalOrderJourney.getContent());
				tradeOrderTrack.setTrackDesc(finalOrderJourney.getContent());
				tradeOrderTrack.setTrackId(finalOrderJourney.getOrderjourneyid() + "");
				tradeOrderTrack.setTrackType(finalOrderJourney.getType());
				tradeOrderTrack.setUpdateTime(finalOrderJourney.getUpdatedAt());
				int optnum = tradeOrderTrackMapper.insert(tradeOrderTrack);
				if (optnum != 1) {
					log.error("订单号" + finalOrderBean.getOrderid() + " 插入 tradeOrderTrack表 失败");
					throw new TradeException(TradeReturnCodeEnum.ORDER_PARAM_FAILED, "订单号" + finalOrderBean.getOrderid() + " 插入 tradeOrderTrack表 失败");
				}
			}
		}
	}

	/**
	 * 迁移订单日志表
	 * 
	 * @param finalOrderBean
	 */
	@Transactional
	private void moveOrderLog(FinalOrderBean finalOrderBean, OrderBean orderBean) {

		FinalOrderlogCriteria finalOrderlogCriteria = new FinalOrderlogCriteria();
		FinalOrderlogCriteria.Criteria criteria = finalOrderlogCriteria.createCriteria();
		criteria.andOrderidEqualTo(finalOrderBean.getOrderid());

		List<FinalOrderlog> finalOrderlogs = finalOrderlogMapper.selectByExample(finalOrderlogCriteria);

		if (finalOrderlogs != null && finalOrderlogs.size() > 0) {
			for (FinalOrderlog finalOrderlog : finalOrderlogs) {
				OrderLog orderLog = new OrderLog();
				orderLog.setComment(finalOrderlog.getComment());
				orderLog.setContent(finalOrderlog.getContent());
				orderLog.setCreateTime(finalOrderlog.getCreatedAt());
				if (finalOrderlog.getGuideid() != null && finalOrderlog.getGuideid() > 0) {
					orderLog.setGuideId(UserIdConvertUtil.getNew(StringConvert.convertStr(finalOrderlog.getGuideid()), UserType.g.name()));
					orderLog.setGuideName(finalOrderlog.getGuidename());
				}

				// orderLog.setLogId(finalOrderlog.get);
				orderLog.setLogType(finalOrderlog.getType());
				// switch(finalOrderlog.getType()){
				// case 1:
				// orderLog.setLogType(LogType.SUBMIT_ORDER.value);
				// break;
				// case 2:
				// orderLog.setLogType(LogType.SUPPLEMENT.value);
				// break;
				// case 3:
				// orderLog.setLogType(LogType.PAYMENT.value);
				// break;
				// case 4:
				// orderLog.setLogType(LogType.CONFIRMED_BY_GUIDE.value);
				// break;
				// case 5:
				// orderLog.setLogType(LogType.ARRIVED_APPOINTED_PLACE.value);
				// break;
				// case 6:
				// orderLog.setLogType(LogType.PICKED_UP.value);
				// break;
				// case 7:
				// orderLog.setLogType(LogType.COMPLETED_SERVICE.value);
				// break;
				// case 8:
				// orderLog.setLogType(LogType.GUIDE_EVALUATES_CUSTOMER.value);
				// break;
				// case 9:
				// orderLog.setLogType(LogType.CUSTOMER_EVALUATES_GUIDE.value);
				// break;
				// case 10:
				// orderLog.setLogType(LogType.CONFIRMS_COST.value);
				// break;
				// case 11:
				// orderLog.setLogType(LogType.PICKED_UP.value);
				// break;
				// case 6:
				// orderLog.setLogType(LogType.PICKED_UP.value);
				// break;
				// case 6:
				// orderLog.setLogType(LogType.PICKED_UP.value);
				// break;
				// case 6:
				// orderLog.setLogType(LogType.PICKED_UP.value);
				// break;
				// case 6:
				// orderLog.setLogType(LogType.PICKED_UP.value);
				// break;
				// case 6:
				// orderLog.setLogType(LogType.PICKED_UP.value);
				// break;
				// case 6:
				// orderLog.setLogType(LogType.PICKED_UP.value);
				// break;
				// }

				orderLog.setOpType(finalOrderlog.getSystype());
				orderLog.setOpUserId(finalOrderlog.getOpuserid());
				orderLog.setOpUserName(finalOrderlog.getOpusername());
				orderLog.setOrderNo(orderBean.getOrderNo());
				orderLog.setUpdateTime(finalOrderlog.getUpdatedAt());
				int optnum = orderLogMapper.insert(orderLog);
				if (optnum != 1) {
					log.error("订单号" + finalOrderBean.getOrderid() + " 插入 tradeOrderLog表 失败");
					throw new TradeException(TradeReturnCodeEnum.ORDER_PARAM_FAILED, "订单号" + finalOrderBean.getOrderid() + " 插入 tradeOrderLog表 失败");
				}

			}

		}

	}

	/**
	 * 迁移订单提醒表
	 * 
	 * @param finalOrderBean
	 */
	@Transactional
	private void moveOrderAlarm(FinalOrderBean finalOrderBean, OrderBean orderBean) {

		FinalOrderAlarmCriteria finalOrderAlarmCriteria = new FinalOrderAlarmCriteria();
		FinalOrderAlarmCriteria.Criteria criteria = finalOrderAlarmCriteria.createCriteria();
		criteria.andOrderidEqualTo(finalOrderBean.getOrderid());

		List<FinalOrderAlarm> finalOrderAlarms = finalOrderAlarmMapper.selectByExample(finalOrderAlarmCriteria);
		if (finalOrderAlarms != null && finalOrderAlarms.size() > 0) {
			for (FinalOrderAlarm finalOrderAlarm : finalOrderAlarms) {
				TradeAlarm tradeAlarm = new TradeAlarm();
				// tradeAlarm.setAlarmId(alarmId);
				tradeAlarm.setType(finalOrderAlarm.getType());
				AlarmType alarmType = AlarmType.getStatus(finalOrderAlarm.getType());
				if (alarmType != null) {

					tradeAlarm.setAlarmType(alarmType.value);
					tradeAlarm.setAlarmTypeName(alarmType.name);

				}

				tradeAlarm.setCreateTime(finalOrderAlarm.getCreatedAt());
				tradeAlarm.setFlag(finalOrderAlarm.getFlag());
				tradeAlarm.setOrderNo(orderBean.getOrderNo());
				tradeAlarm.setOrderServiceTime(orderBean.getServiceTime());
				// tradeAlarm.setSendPreTime(sendPreTime);
				tradeAlarm.setSendTime(finalOrderAlarm.getSendtime());
				tradeAlarm.setStatus(finalOrderAlarm.getStatus());
				tradeAlarm.setType(finalOrderAlarm.getType());
				tradeAlarm.setUpdateTime(finalOrderAlarm.getUpdatedAt());

				int optnum = tradeAlarmMapper.insert(tradeAlarm);
				if (optnum != 1) {
					log.error("订单号" + finalOrderBean.getOrderid() + " 插入 tradeAlarm表 失败");
					throw new TradeException(TradeReturnCodeEnum.ORDER_PARAM_FAILED, "订单号" + finalOrderBean.getOrderid() + " 插入 tradeAlarm表 失败");
				}
			}
		}
	}

	/**
	 * 迁移订单串单表
	 * 
	 * @param finalOrderBean
	 */
	@Transactional
	private void moveOrderOptimization(FinalOrderBean finalOrderBean, OrderBean orderBean) {

		FinalOrderOptimizationCriteria finalOrderOptimizationCriteria = new FinalOrderOptimizationCriteria();
		FinalOrderOptimizationCriteria.Criteria criteria = finalOrderOptimizationCriteria.createCriteria();
		criteria.andOptimizationorderidEqualTo(finalOrderBean.getOrderid());

		List<FinalOrderOptimization> finalOrderOptimizations = finalOrderOptimizationMapper.selectByExample(finalOrderOptimizationCriteria);
		if (finalOrderOptimizations != null && finalOrderOptimizations.size() > 0) {
			for (FinalOrderOptimization finalOrderOptimization : finalOrderOptimizations) {
				TradeOrderSerial tradeOrderSerial = new TradeOrderSerial();
				tradeOrderSerial.setCreateTime(finalOrderOptimization.getCreatedAt());
				tradeOrderSerial.setGuidePrice(orderBean.getPriceGuide());
				tradeOrderSerial.setIsMatch(finalOrderOptimization.getIsmatch());
				tradeOrderSerial.setMeetGuideId(finalOrderOptimization.getMatchguideid().toString());
				tradeOrderSerial.setMeetOrderAddress(finalOrderOptimization.getAddress());

				FinalOrderBean finalOrderBeanMatch = finalOrderBeanMapper.selectByPrimaryKey(finalOrderOptimization.getMatchorderid());
				if (finalOrderBeanMatch != null && finalOrderBeanMatch.getOrdersn().length() > 0) {
					tradeOrderSerial.setMeetOrderNo(finalOrderBeanMatch.getOrdersn());
				}

				try {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					Date date = sdf.parse(finalOrderOptimization.getMatchorderservicedate());
					tradeOrderSerial.setMeetOrderServiceTime(date);
				} catch (Exception ex) {

				}
				tradeOrderSerial.setMeetOrderType(finalOrderOptimization.getMatchordertype());
				tradeOrderSerial.setOrderNo(orderBean.getOrderNo());
				// tradeOrderSerial.setSerialId(serialId);
				tradeOrderSerial.setUpdateTime(finalOrderOptimization.getUpdatedAt());

				int optnum = tradeOrderSerialMapper.insert(tradeOrderSerial);
				if (optnum != 1) {
					log.error("订单号" + finalOrderBean.getOrderid() + " 插入 tradeOrderSerial表 失败");
					throw new TradeException(TradeReturnCodeEnum.ORDER_PARAM_FAILED, "订单号" + finalOrderBean.getOrderid() + " 插入 tradeOrderSerial表 失败");
				}

			}
		}

	}

	/**
	 * 迁移导游订单表
	 * 
	 * @param finalOrderBean
	 */
	@Transactional
	private void moveOrderGuide(FinalOrderBean finalOrderBean, OrderBean orderBean) {
		FinalGuideorderCriteria finalGuideorderCriteria = new FinalGuideorderCriteria();
		FinalGuideorderCriteria.Criteria criteria = finalGuideorderCriteria.createCriteria();
		criteria.andOrderidEqualTo(finalOrderBean.getOrderid());
		List<FinalGuideorder> finalGuideorders = finalGuideorderMapper.selectByExample(finalGuideorderCriteria);
		if (finalGuideorders != null && finalGuideorders.size() > 0) {
			TradeOrderDeliverExample tradeOrderDeliverExample = new TradeOrderDeliverExample();
			tradeOrderDeliverExample.createCriteria().andOrderNoEqualTo(orderBean.getOrderNo());
			List<TradeOrderDeliver> tlist = tradeOrderDeliverMapper.selectByExample(tradeOrderDeliverExample);
			TradeOrderDeliver tradeOrderDeliver = new TradeOrderDeliver();
			if (tlist.size() > 0) {
				tradeOrderDeliver = tlist.get(0);
			} else {
				tradeOrderDeliver.setCityId(finalOrderBean.getPlacecityid());
				tradeOrderDeliver.setCreateTime(finalOrderBean.getDelivertime());
				tradeOrderDeliver.setDeliverNo("DLI" + IDGenerotor.generateDeliverNo());
				tradeOrderDeliver.setDeliverStatus(TradeDeliverStatus.predeliver.value);
				tradeOrderDeliver.setDeliverTime(finalOrderBean.getDelivertime());
				tradeOrderDeliver.setDeliverType(DeliverType.Ordinary.value);
				tradeOrderDeliver.setDeliverTypeName(DeliverType.Ordinary.name);
				tradeOrderDeliver.setOrderNo(orderBean.getOrderNo());

				if (finalOrderBean.getStatus() != null) {
					if (finalOrderBean.getStatus().intValue() < 0) {
						if (finalOrderBean.getGuideid() != null && !"0".equals(finalOrderBean.getGuideid())) {
							tradeOrderDeliver.setPkStatus(DeliverPkStatus.PK_SUCCESS.value);
							tradeOrderDeliver.setPkStatusName(DeliverPkStatus.PK_SUCCESS.name);
						} else {
							tradeOrderDeliver.setPkStatus(DeliverPkStatus.PK_FAILED.value);
							tradeOrderDeliver.setPkStatusName(DeliverPkStatus.PK_FAILED.name);
						}
						tradeOrderDeliver.setDeliverStatus(TradeDeliverStatus.deliverfailed.value);

					} else if (finalOrderBean.getStatus().intValue() == 0) {
						tradeOrderDeliver.setPkStatus(DeliverPkStatus.INIT.value);
						tradeOrderDeliver.setPkStatusName(DeliverPkStatus.INIT.name);
						return;
					} else if (finalOrderBean.getStatus().intValue() == 1) {
						tradeOrderDeliver.setPkStatus(DeliverPkStatus.INIT.value);
						tradeOrderDeliver.setPkStatusName(DeliverPkStatus.INIT.name);
						tradeOrderDeliver.setDeliverStatus(TradeDeliverStatus.predeliver.value);
						return;
					} else if (finalOrderBean.getStatus().intValue() == 97) {
						tradeOrderDeliver.setPkStatus(DeliverPkStatus.INIT.value);
						tradeOrderDeliver.setPkStatusName(DeliverPkStatus.INIT.name);
						tradeOrderDeliver.setDeliverStatus(TradeDeliverStatus.predeliver.value);
						return;
					} else if (finalOrderBean.getStatus().intValue() == 98) {
						tradeOrderDeliver.setPkStatus(DeliverPkStatus.INIT.value);
						tradeOrderDeliver.setPkStatusName(DeliverPkStatus.INIT.name);
						tradeOrderDeliver.setDeliverStatus(TradeDeliverStatus.delivered.value);
					} else {
						tradeOrderDeliver.setPkStatus(DeliverPkStatus.PK_SUCCESS.value);
						tradeOrderDeliver.setPkStatusName(DeliverPkStatus.PK_SUCCESS.name);
						tradeOrderDeliver.setDeliverStatus(TradeDeliverStatus.pushSended.value);
					}
				} else {
					return;
				}

				// tradeOrderDeliver.setPkStatus(DeliverPkStatus.PK_SUCCESS.value);
				// tradeOrderDeliver.setPkStatusName(DeliverPkStatus.PK_SUCCESS.name);
				tradeOrderDeliver.setUpdateTime(finalOrderBean.getDelivertime());
				tradeOrderDeliverMapper.insert(tradeOrderDeliver);
			}

			for (FinalGuideorder finalGuideorder : finalGuideorders) {
				TradeDeliverGuide tradeDeliverGuide = new TradeDeliverGuide();
				// tradeDeliverGuide.setAcceptTime(acceptTime);
				tradeDeliverGuide.setAllocatGno(finalGuideorder.getGuideorderid());
				tradeDeliverGuide.setCreateTime(finalGuideorder.getCreatedAt());
				tradeDeliverGuide.setDeliverNo(tradeOrderDeliver.getDeliverNo());
				// tradeDeliverGuide.setDeliverNo(deliverNo);
				// if(finalGuideorder.getStatus()!= null &&
				// finalGuideorder.getStatus().intValue()==1){
				// tradeDeliverGuide.setDeliverStatus(GuidDeliverStatus.successsend.value);
				// tradeDeliverGuide.setDeliverStatusName(GuidDeliverStatus.successsend.name);
				// }else if(finalOrderBean.getStatus().intValue()>1 &&
				// finalOrderBean.getStatus().intValue()!=97 &&
				// finalOrderBean.getStatus().intValue()!=98){
				// tradeDeliverGuide.setDeliverStatus(GuidDeliverStatus.pkfailedPush.value);
				// tradeDeliverGuide.setDeliverStatusName(GuidDeliverStatus.pkfailedPush.name);
				// }else{
				// tradeDeliverGuide.setDeliverStatus(GuidDeliverStatus.sendpush.value);
				// tradeDeliverGuide.setDeliverStatusName(GuidDeliverStatus.sendpush.name);
				// }
				tradeDeliverGuide.setGuideId(UserIdConvertUtil.getNew(StringConvert.convertStr(finalGuideorder.getGuideid()), UserType.g.name()));
				if (finalOrderBean.getStatus() != null) {
					if (finalOrderBean.getStatus().intValue() < 0) {
						if (tradeDeliverGuide.getGuideId().equals(orderBean.getGuideId())) {
							tradeDeliverGuide.setDeliverStatus(GuidDeliverStatus.successsend.value);
							tradeDeliverGuide.setDeliverStatusName(GuidDeliverStatus.successsend.name);
						} else {
							tradeDeliverGuide.setDeliverStatus(GuidDeliverStatus.pkfailedPush.value);
							tradeDeliverGuide.setDeliverStatusName(GuidDeliverStatus.pkfailedPush.name);
						}

					} else if (finalOrderBean.getStatus().intValue() == 0) {
						tradeDeliverGuide.setDeliverStatus(GuidDeliverStatus.sendpush.value);
						tradeDeliverGuide.setDeliverStatusName(GuidDeliverStatus.sendpush.name);
					} else if (finalOrderBean.getStatus().intValue() == 1) {
						tradeDeliverGuide.setDeliverStatus(GuidDeliverStatus.sendpush.value);
						tradeDeliverGuide.setDeliverStatusName(GuidDeliverStatus.sendpush.name);
					} else if (finalOrderBean.getStatus().intValue() == 97) {
						tradeDeliverGuide.setDeliverStatus(GuidDeliverStatus.sendpush.value);
						tradeDeliverGuide.setDeliverStatusName(GuidDeliverStatus.sendpush.name);
					} else if (finalOrderBean.getStatus().intValue() == 98) {
						tradeDeliverGuide.setDeliverStatus(GuidDeliverStatus.sendpush.value);
						tradeDeliverGuide.setDeliverStatusName(GuidDeliverStatus.sendpush.name);
					} else {
						if (finalGuideorder.getGuideid().equals(finalOrderBean.getGuideid())) {
							tradeDeliverGuide.setDeliverStatus(GuidDeliverStatus.successsend.value);
							tradeDeliverGuide.setDeliverStatusName(GuidDeliverStatus.successsend.name);
						} else {
							tradeDeliverGuide.setDeliverStatus(GuidDeliverStatus.pkfailedPush.value);
							tradeDeliverGuide.setDeliverStatusName(GuidDeliverStatus.pkfailedPush.name);
						}

					}
				} else {
					return;
				}

				tradeDeliverGuide.setDeliverType(DeliverType.Ordinary.value);
				tradeDeliverGuide.setDeliverTypeName(DeliverType.Ordinary.name);
				if (finalGuideorder.getDemanddeal() == null) {
					tradeDeliverGuide.setDemandDeal(DemandDeal.INITIAL.value);
				} else if (finalGuideorder.getDemanddeal().intValue() == -1) {
					tradeDeliverGuide.setDemandDeal(DemandDeal.DISAGREE.value);
				} else if (finalGuideorder.getDemanddeal().intValue() == 1) {
					tradeDeliverGuide.setDemandDeal(DemandDeal.AGREE.value);
				} else {
					tradeDeliverGuide.setDemandDeal(DemandDeal.INITIAL.value);
				}

				// setPKStatus
				// if(OrderStatus.PAYSUCCESS.equals(orderBean.getOrderStatus())){
				//
				// }

				tradeDeliverGuide.setFailType(finalGuideorder.getFailtype());

				GuideBean guideBean = controllerService.getGuidByGuideId(tradeDeliverGuide.getGuideId());
				if (guideBean != null) {
					tradeDeliverGuide.setGuideName(guideBean.getGuideName());
					tradeDeliverGuide.setGuideNo(guideBean.getGuideNo());
				}

				tradeDeliverGuide.setGuidePrice(orderBean.getPriceGuide());
				tradeDeliverGuide.setIsReadable(1);
				if (finalOrderBean.getMode() == null) {
					tradeDeliverGuide.setIsOnWay(0);
				} else if (finalOrderBean.getMode().equals(1)) {
					tradeDeliverGuide.setIsOnWay(1);
				} else {
					tradeDeliverGuide.setIsOnWay(0);
				}
				if (finalGuideorder.getStatus() == null) {
					tradeDeliverGuide.setIsRead(0);
					tradeDeliverGuide.setIsReadMis(0);
				} else if (finalGuideorder.getStatus().equals(0)) {
					tradeDeliverGuide.setIsRead(0);
					tradeDeliverGuide.setIsReadMis(0);
				} else if (finalGuideorder.getStatus().equals(1)) {
					tradeDeliverGuide.setIsRead(1);
					tradeDeliverGuide.setIsReadMis(0);
				} else if (finalGuideorder.getStatus().equals(2)) {
					tradeDeliverGuide.setIsRead(1);
					tradeDeliverGuide.setIsReadMis(0);
				} else if (finalGuideorder.getStatus().equals(4)) {
					tradeDeliverGuide.setIsRead(1);
					tradeDeliverGuide.setIsReadMis(1);
				} else if (finalGuideorder.getStatus().equals(-4)) {
					tradeDeliverGuide.setIsRead(1);
					tradeDeliverGuide.setIsReadMis(1);
					tradeDeliverGuide.setIsReadable(0);
				}

				tradeDeliverGuide.setOrderNo(orderBean.getOrderNo());
				tradeDeliverGuide.setOther(finalGuideorder.getOther());
				tradeDeliverGuide.setRefuseReason(finalGuideorder.getRefusereson());
				tradeDeliverGuide.setServiceTime(orderBean.getServiceTime());
				tradeDeliverGuide.setUpdateTime(finalGuideorder.getUpdatedAt());
				tradeDeliverGuide.setViewResult(finalGuideorder.getViewresult());
				int optnum = tradeDeliverGuideMapper.insert(tradeDeliverGuide);
				if (optnum != 1) {
					log.error("订单" + finalOrderBean.getOrderid() + "导游" + finalOrderBean.getGuideid() + " 插入 tradeDeliverGuide表 失败");
					throw new TradeException(TradeReturnCodeEnum.ORDER_PARAM_FAILED, "订单" + finalOrderBean.getOrderid() + "导游" + finalOrderBean.getGuideid() + " 插入 tradeDeliverGuide表 失败");
				}

			}
		}
	}

	/**
	 * 迁移订单行程路径表
	 * 
	 * @param finalOrderBean
	 */
	@Transactional
	private void moveOrderRoute(FinalOrderBean finalOrderBean, OrderBean orderBean) {
		FinalOrderRouteCriteria finalOrderRouteCriteria = new FinalOrderRouteCriteria();
		FinalOrderRouteCriteria.Criteria criteria = finalOrderRouteCriteria.createCriteria();
		criteria.andOrderidEqualTo(finalOrderBean.getOrderid());
		List<FinalOrderRoute> finalOrderRoutes = finalOrderRouteMapper.selectByExample(finalOrderRouteCriteria);
		if (finalOrderRoutes != null && finalOrderRoutes.size() > 0) {
			for (FinalOrderRoute finalOrderRoute : finalOrderRoutes) {
				TradeRoute tradeRoute = new TradeRoute();
				tradeRoute.setCreateTime(finalOrderRoute.getCreatedAt());
				tradeRoute.setEndTime(finalOrderRoute.getEndtime());
				tradeRoute.setGpsPoints(finalOrderRoute.getValue());
				tradeRoute.setOrderNo(orderBean.getOrderNo());
				// tradeRoute.setRouteId(routeId);
				tradeRoute.setStartTime(finalOrderRoute.getStarttime());
				tradeRoute.setUpdateTime(finalOrderRoute.getUpdatedAt());
				int optnum = tradeRouteMapper.insert(tradeRoute);
				if (optnum != 1) {
					log.error("订单" + finalOrderBean.getOrderid() + " 插入 tradeRoute表 失败");
					throw new TradeException(TradeReturnCodeEnum.ORDER_PARAM_FAILED, "订单" + finalOrderBean.getOrderid() + " 插入 tradeRoute表 失败");
				}
			}
		}
	}

	private void buildStaticOrderBody(OrderBean orderBean) {
		orderBean.setPriceGuideBase(orderBean.getPriceGuideBase() == null ? orderBean.getPriceGuide() : orderBean.getPriceGuideBase());

		Date currentTime = new Date();
		orderBean.setCreateTime(orderBean.getCreateTime() == null ? currentTime : orderBean.getCreateTime());
		orderBean.setUpdateTime(orderBean.getUpdateTime() == null ? currentTime : orderBean.getUpdateTime());
		orderBean.setGuideId(orderBean.getGuideId() == null ? TradeFinalStr.defaultGuideId : orderBean.getGuideId());
		orderBean.setDeliverStatus(orderBean.getDeliverStatus() == null ? OrderDeliverStatus.init.value : orderBean.getDeliverStatus());
		orderBean.setDeliverType(orderBean.getDeliverType() == null ? DeliverType.Ordinary.value : orderBean.getDeliverType());
		orderBean.setPriceGuide(orderBean.getPriceGuide() == null ? orderBean.getPriceChannel() : orderBean.getPriceGuide());
		orderBean.setUserCommentStatus(orderBean.getUserCommentStatus() == null ? UserCommentStatus.UNSCORED.value : orderBean.getUserCommentStatus());
		orderBean.setGuideCommentStatus(orderBean.getGuideCommentStatus() == null ? GuideCommentStatus.UNSCORED.value : orderBean.getGuideCommentStatus());
		orderBean.setSystemCommentStatus(orderBean.getSystemCommentStatus() == null ? SystemCommentStatus.UNSCORED.value : orderBean.getSystemCommentStatus());
		orderBean.setFlightIsCustom(orderBean.getFlightIsCustom() == null ? FlightIsCustom.NORMAL.value : orderBean.getFlightIsCustom());
		orderBean.setSerialFlag(orderBean.getSerialFlag() == null ? OrderSerialFlag.NORMAL.value : orderBean.getSerialFlag());
		orderBean.setIsArrivalVisa(orderBean.getIsArrivalVisa() == null ? VisaType.UNDEFINED.value : orderBean.getIsArrivalVisa());
		orderBean.setCargroupFlag(orderBean.getCargroupFlag() == null ? CarGroupFlag.NORMAL.value : orderBean.getCargroupFlag());
		orderBean.setCheckInPrice(orderBean.getCheckInPrice() == null ? 0d : orderBean.getCheckInPrice());
		orderBean.setIsReadable(orderBean.getIsReadable() == null ? IsReadable.VISIABLE.value : orderBean.getIsReadable());
		if (orderBean.getUrgentFlag() == null) {
			orderBean.setUrgentFlag(UrgentFlag.nomal.value);
		}
		// BUGFIX
		if (orderBean.getStartAddress() != null && orderBean.getStartAddress().equals("null")) {
			orderBean.setStartAddress(null);
		}
		if (orderBean.getStartAddressDetail() != null && orderBean.getStartAddressDetail().equals("null")) {
			orderBean.setStartAddressDetail(null);
		}
	}

}
