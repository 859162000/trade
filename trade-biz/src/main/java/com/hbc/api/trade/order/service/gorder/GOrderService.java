/**
 * @Author lukangle
 * @2015年11月28日@上午11:23:06
 */
package com.hbc.api.trade.order.service.gorder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hbc.api.trade.bdata.common.DownloadConfigService;
import com.hbc.api.trade.bdata.common.TradeConstant;
import com.hbc.api.trade.bdata.common.rsp.ReturnResult;
import com.hbc.api.trade.bdata.common.util.BeanUtilsEnhance;
import com.hbc.api.trade.bdata.common.util.TimeConverter;
import com.hbc.api.trade.bdata.mapper.basedata.gen.bean.CityBean;
import com.hbc.api.trade.order.OrderPal;
import com.hbc.api.trade.order.enums.TradeReturnCodeEnum;
import com.hbc.api.trade.order.enums.deliver.GuidDeliverStatus;
import com.hbc.api.trade.order.enums.deliver.OrderSerialFlag;
import com.hbc.api.trade.order.enums.order.AdditionalCostType;
import com.hbc.api.trade.order.enums.order.GOrderStatus;
import com.hbc.api.trade.order.enums.order.OrderStatus;
import com.hbc.api.trade.order.enums.order.OrderType;
import com.hbc.api.trade.order.enums.order.VisaType;
import com.hbc.api.trade.order.enums.third.CarTypeEnum;
import com.hbc.api.trade.order.exception.TradeException;
import com.hbc.api.trade.order.mapping.gen.TradeOrderSerialMapper;
import com.hbc.api.trade.order.mapping.gen.bean.OrderBean;
import com.hbc.api.trade.order.mapping.gen.bean.TradeAdditionalCost;
import com.hbc.api.trade.order.mapping.gen.bean.TradeAdditionalDetail;
import com.hbc.api.trade.order.mapping.gen.bean.TradeDeliverGuide;
import com.hbc.api.trade.order.mapping.gen.bean.TradeOrderSerial;
import com.hbc.api.trade.order.mapping.gen.bean.TradeOrderSerialExample;
import com.hbc.api.trade.order.mapping.gen.bean.TradeOrderTrack;
import com.hbc.api.trade.order.service.AdditionalCostService;
import com.hbc.api.trade.order.service.OrderQueryService;
import com.hbc.api.trade.order.service.OrderServiceTime;
import com.hbc.api.trade.order.service.OrderTrackService;
import com.hbc.api.trade.order.service.gorder.rsp.GOrderBean;
import com.hbc.api.trade.order.service.gorder.rsp.GOrderDetailBean;
import com.hbc.api.trade.order.service.rsp.ChildSeat;
import com.hbc.api.trade.order.service.rsp.Coupon;
import com.hbc.api.trade.order.service.rsp.OrderTrack;
import com.hbc.api.trade.order.service.rsp.PassCity;
import com.hbc.api.trade.order.service.rsp.PriceInfo;
import com.hbc.api.trade.order.service.rsp.UserMobile;
import com.hbc.api.trade.pay.enums.PayStatus;
import com.hbc.api.trade.pay.mapping.gen.bean.TradePayment;
import com.hbc.api.trade.pay.service.PaymentService;
import com.hbc.api.trade.third.CityQueryService;
import com.hbc.api.trade.third.CouponServiceParty;
import com.hbc.api.trade.third.UserInfoService;
import com.hbc.api.trade.third.restful.GuideRESTfulService;
import com.hbc.api.trade.third.restful.IMService;
import com.hbc.api.trade.third.vo.Appraisement;
import com.hbc.api.trade.third.vo.CouponVo;
@Component
public class GOrderService {
	private final static Logger logger = LoggerFactory.getLogger(GOrderService.class);
	
	@Autowired private IMService 				imService;
	@Autowired private OrderQueryService 		orderQueryService;
	@Autowired private CityQueryService 		cityQueryService;
	@Autowired private GuideRESTfulService 		guideRESTfulService;
	@Autowired private OrderTrackService 		orderTrackService;
	@Autowired private AdditionalCostService 	additionalCost;
	@Autowired private PaymentService 			paymentService;
	@Autowired private CouponServiceParty 		couponServiceParty;
	@Autowired private UserInfoService			userInfoService;
	@Autowired private DownloadConfigService	downloadConfigService;
	@Autowired private OrderServiceTime			orderServiceTime;
	
	public List<GOrderBean> convertToGorderBean(List<OrderBean> listOrder,List<TradeDeliverGuide> deliverGuideList, String guideId, List<String> orderNoList,boolean isNewOrder) {
		List<GOrderBean> gOrderBeanList = new LinkedList<GOrderBean>();
		if (listOrder == null || deliverGuideList == null) return null;

		// 获取未读消息数列表
		ReturnResult result = imService.imGUnreadSizeList(guideId);
		JSONObject jsonData = (JSONObject) result.getData();
		JSONArray jsonArrayOfUnread = null;
		if(jsonData != null) {
			jsonArrayOfUnread = (JSONArray) jsonData.get("list");
		}
		
		// 行程记录
		List<TradeOrderTrack> orderTrackList = orderTrackService.getOrderTrackRecords(orderNoList);
		
		// 获取接单成功的发单信息
		Map<String,TradeDeliverGuide> gmap = new HashMap<String,TradeDeliverGuide>();
		for(TradeDeliverGuide tradeDeliverGuide :deliverGuideList ){
			gmap.put(tradeDeliverGuide.getOrderNo(), tradeDeliverGuide);
		}
		
		for (OrderBean orderBean : listOrder) {
			try{
				GOrderBean gOrderBean = new GOrderBean();
				BeanUtilsEnhance.copyProperties(gOrderBean, orderBean);
				setCustomerNum(orderBean, gOrderBean);
				
				// 适配G端订单状态
				GOrderStatus gorderStatus;
				if(isNewOrder){
					TradeDeliverGuide tradeDeliverGuide = gmap.get(orderBean.getOrderNo());
					 gorderStatus = OrderStatusAdaptor.toNewGOrderStatus(orderBean, tradeDeliverGuide);
					 gOrderBean.setPriceGuide(tradeDeliverGuide.getGuidePrice());
					 gOrderBean.setGuideDealTime(orderBean.getGuideAssignTime());
					 setIsRead(tradeDeliverGuide, gOrderBean);
				}else{
					TradeDeliverGuide tradeDeliverGuide = gmap.get(orderBean.getOrderNo());
					gorderStatus = OrderStatusAdaptor.toGOrderStatus(orderBean,tradeDeliverGuide,guideId);
				}
				gOrderBean.setOrderStatus(gorderStatus.value);
				gOrderBean.setCanChat(OrderPal.chatable(orderBean)); // 是否能聊天和未读消息数
				setImcount(jsonArrayOfUnread, orderBean, gOrderBean);
				setJourneyNum(orderTrackList, gOrderBean);
				gOrderBean.setServiceTimeDown(getServiceTimeDown(orderBean));
				gOrderBeanList.add(gOrderBean);
			}catch(Exception e){
				logger.error("",e);
			}
		}
		logger.info("GAPP订单列表：" + JSON.toJSONString(gOrderBeanList));
		return gOrderBeanList;
	}
	
	
	public List<GOrderBean> convertToMissBean(List<OrderBean> listOrder,List<TradeDeliverGuide> deliverGuideList, String guideId, List<String> orderNoList,boolean isNewOrder) {
		List<GOrderBean> gOrderBeanList = convertToGorderBean(listOrder,deliverGuideList,guideId,orderNoList,isNewOrder);

		Map<String,GOrderBean> gorderBeanMap = new HashMap<>();
		for(GOrderBean gorderBean : gOrderBeanList){
			gorderBeanMap.put(gorderBean.getOrderNo(), gorderBean);
		}
		List<GOrderBean> gOrderBeanListOrderBy = new ArrayList<GOrderBean>();
		for(TradeDeliverGuide tradeDeliverGuide: deliverGuideList){
			gOrderBeanListOrderBy.add(gorderBeanMap.get(tradeDeliverGuide.getOrderNo()));
		}
		logger.info("错过订单对应列表：" + JSON.toJSONString(gOrderBeanListOrderBy));
		return gOrderBeanListOrderBy;
	}
	
	public List<GOrderBean> convertToCompletedBean(List<OrderBean> listOrder,List<TradeDeliverGuide> deliverGuideList, String guideId, List<String> orderNoList,boolean isNewOrder) {
		List<GOrderBean> gOrderBeanList = convertToGorderBean(listOrder,deliverGuideList,guideId,orderNoList,isNewOrder);
		List<Appraisement> appraisementList = guideRESTfulService.obtainAppraisementList(orderNoList);
		if(appraisementList == null || appraisementList.isEmpty())  {
			logger.info("已完成的订单列表：" + JSON.toJSONString(gOrderBeanList));
			return gOrderBeanList;
		} else {
			List<GOrderBean> gOrderBeanListResult = new LinkedList<>(); 
			for(GOrderBean gOrderBean : gOrderBeanList) {
				for(Appraisement appraisement : appraisementList) {
					if(gOrderBean.getOrderNo().equals(appraisement.getOrderNo())) {
						gOrderBean.setUserCommentTime(appraisement.getUserCommentTime());
						break;
					}
				}
				gOrderBeanListResult.add(gOrderBean);
			}
			logger.info("已完成的订单列表：" + JSON.toJSONString(gOrderBeanListResult));
			return gOrderBeanListResult;
		}
	}
	
	

	/**
	 * 客人数
	 * @param orderBean
	 * @param gOrderBean
	 */
	private void setCustomerNum(OrderBean orderBean, GOrderBean gOrderBean) {
		int customNum = 0;
		if (orderBean.getChildNum() != null)
			customNum += orderBean.getChildNum();
		if (orderBean.getAdultNum() != null)
			customNum += orderBean.getAdultNum();
		gOrderBean.setCustomNum(customNum);
	}

	/**
	 * 是否已读
	 * @param tradeDeliverGuide
	 * @param gOrderBean
	 */
	private void setIsRead(TradeDeliverGuide tradeDeliverGuide, GOrderBean gOrderBean) {
		// 接单平台才有该值，服务平台无此值
		Integer deliverStatus = tradeDeliverGuide.getDeliverStatus();
		if(GuidDeliverStatus.resend.value.equals(deliverStatus) || GuidDeliverStatus.sendpush.value.equals(deliverStatus)) {
			gOrderBean.setIsRead(tradeDeliverGuide.getIsRead() == null ? 0 : tradeDeliverGuide.getIsRead());
		}
	}

	/**
	 * 新行程动态数
	 * @param orderTrackList
	 * @param gOrderBean
	 * @return
	 */
	private void setJourneyNum(List<TradeOrderTrack> orderTrackList, GOrderBean gOrderBean) {
		int journeySize = 0;
		if(orderTrackList != null) {
			for (TradeOrderTrack orderTrack : orderTrackList) {
				if (gOrderBean.getOrderNo().equals(orderTrack.getOrderNo()) && orderTrack.getIsRead() == 0) {
					journeySize += 1;
				}
			}
		}
		gOrderBean.setJourneyNum(journeySize);
	}

	/**
	 * 获取未读消息列表
	 * @param jsonArrayOfUnread
	 * @param orderBean
	 * @param gOrderBean
	 */
	private void setImcount(JSONArray jsonArrayOfUnread, OrderBean orderBean, GOrderBean gOrderBean) {
		int j = 0;
		boolean chatable = OrderPal.chatable(orderBean);
		boolean hasIMCount = chatable && jsonArrayOfUnread != null && ( j = jsonArrayOfUnread.size()) > 0;
		logger.info("IMCount: chatable=" + chatable + ", hasIMCount=" + hasIMCount);
		if(hasIMCount ) {
			for(int i = 0; i < j; i++ ) {
				JSONObject jsonObject = (JSONObject) jsonArrayOfUnread.get(i);
				if( orderBean.getOrderNo().equals(jsonObject.get("name")) ) {
					gOrderBean.setImCount(jsonObject.get("value") != null ? Integer.valueOf(jsonObject.get("value").toString()) : 0);
					return;
				}
			}
		}
		gOrderBean.setImCount(0);
	}

	/**
	 * @param orderBean
	 * @param tradeDeliverGuide
	 * @return
	 */
	public GOrderDetailBean convertToGorderDetailBean(OrderBean orderBean, TradeDeliverGuide tradeDeliverGuide){
		return convertToGorderDetailBean(orderBean, tradeDeliverGuide, tradeDeliverGuide.getGuideId(),false);
	}
	
	public GOrderDetailBean convertToNewGorderDetailBean(OrderBean orderBean, TradeDeliverGuide tradeDeliverGuide){
		return convertToGorderDetailBean(orderBean, tradeDeliverGuide, tradeDeliverGuide.getGuideId(),true);
	}
			
	/**
	 * 
	 * @param orderBean
	 * @param tradeDeliverGuide
	 * @param guideIdSelf 当为错过订单时，orderBean的guideId一定不是当前导游的ID，因此需要从外围额外传递
	 * @return
	 */
	public GOrderDetailBean convertToGorderDetailBean(OrderBean orderBean, TradeDeliverGuide tradeDeliverGuide, String guideIdSelf,boolean isNew) {
		logger.info("GAPP转换前：guideId=" + guideIdSelf + "，OrderBean:" + JSON.toJSONString(orderBean) + ", tradeDeliverGuide:" + JSON.toJSONString(tradeDeliverGuide));
		
		GOrderDetailBean gOrderDetailBean = new GOrderDetailBean();
		BeanUtilsEnhance.copyProperties(gOrderDetailBean, orderBean);
		gOrderDetailBean.setServiceCityName(orderBean.getServiceCityName());
		gOrderDetailBean.setServiceEndCityname(orderBean.getServiceEndCityname());
		gOrderDetailBean.setUserGetOnDate(orderBean.getServiceTime());
		gOrderDetailBean.setCarTypeAndSeatInfo(CarTypeEnum.getType(orderBean.getCarTypeId()).name + orderBean.getCarSeatNum() + "座");
		gOrderDetailBean.setTotalDays(orderBean.getTotalDays() != null ? (int) orderBean.getTotalDays() : null);
		GOrderStatus gorderStatus ;
		if(isNew){
			gorderStatus = OrderStatusAdaptor.toNewGOrderStatus(orderBean, tradeDeliverGuide);
			gOrderDetailBean.setPriceGuide(tradeDeliverGuide.getGuidePrice());
			GuidDeliverStatus guidDeliverStatus = GuidDeliverStatus.getType(tradeDeliverGuide.getDeliverStatus());
			if(GuidDeliverStatus.pkfailed.equals(guidDeliverStatus) || GuidDeliverStatus.pkfailedPush.equals(guidDeliverStatus)){
				gOrderDetailBean.setIsMissed(true);
			}else{
				gOrderDetailBean.setIsMissed(false);
			}
			if(tradeDeliverGuide != null) {
				gOrderDetailBean.setAllocatGno(tradeDeliverGuide.getAllocatGno());
			}
		}else{
			gorderStatus = OrderStatusAdaptor.toGOrderStatus(orderBean,tradeDeliverGuide,guideIdSelf);
			setAdditionalCost(orderBean, gOrderDetailBean);
			appendOrderTracks(orderBean.getOrderNo(), gOrderDetailBean);
			try{
				gOrderDetailBean.setAppraisement(guideRESTfulService.obtainAppraiseContent(orderBean.getOrderNo(), orderBean.getUserCommentStatus()));
			} catch(Exception e) {
				// 容错
				logger.error("【GAPP】获取导游评价出错", e);
			}
		}
		gOrderDetailBean.setOrderStatus(gorderStatus.value);
		setSerialOrderTip(orderBean,tradeDeliverGuide, gOrderDetailBean);
		try {
			String userAvatar = userInfoService.getUserInfo(orderBean.getUserId()).getAvatar();
			gOrderDetailBean.setUserAvatar(downloadConfigService.getUserSmallAvatar(userAvatar) );
		} catch(Exception e) {
			// 容错
			logger.error("【GAPP】获取用户头像出错", e);
		}
		setMobiles(orderBean, gOrderDetailBean);
		setChildSeats(orderBean, gOrderDetailBean);
		setServicePassCity(orderBean, gOrderDetailBean);
		setPriceInformation(orderBean, gOrderDetailBean);
		setCoupon(orderBean, gOrderDetailBean);
		setUserRemark(orderBean, gOrderDetailBean);
		gOrderDetailBean.setServiceTimeDown(getServiceTimeDown(orderBean));
		logger.info("GAPP订单详情：" + JSON.toJSONString(gOrderDetailBean));
		return gOrderDetailBean;
	}
	
	/**
	 * 获取距离服务时间多少秒
	 * @param orderBean
	 * @return
	 */
	private Integer getServiceTimeDown(OrderBean orderBean) {
		long serviceCityCurrentTime = orderServiceTime.getServiceCityCurTime(orderBean.getServiceCityId()).getTime();
		long serviceTime = orderBean.getServiceTime().getTime();
		long gap = serviceTime - serviceCityCurrentTime;
		gap = gap <= 0 ? 0 :  gap / 1000; 
		return (int)gap;
	}


	/**
	 * @param order
	 * @param target
	 */
	private void setCoupon(OrderBean order, GOrderDetailBean target) {
		OrderStatus orderStatus = OrderStatus.getStatus(order.getOrderStatus());
		if(OrderStatus.INITSTATE.equals(orderStatus)){
			CouponVo couponVo = couponServiceParty.getPYCoupByOrderDetail(order.getOrderNo(),order.getPriceChannel(), order.getUserId());
			logger.info("未付款，获取优惠券信息：" + JSON.toJSONString(couponVo));
			if(couponVo != null) {
				Coupon coupon = new Coupon();
				coupon.setCouponId(couponVo.getCouponId());
				coupon.setActualPrice(couponVo.getActualPrice());
				coupon.setPriceInfo(couponVo.getPriceInfo());
				target.setCoupon(coupon);
			}
		}else if(StringUtils.isNoneBlank(order.getCoupId())){
			Coupon coupon = new Coupon();
			coupon.setCouponId(order.getCoupId());
			coupon.setPriceInfo(order.getCoupPriceInfo());
			TradePayment payment = paymentService.queryTradePaymentByOrderNoNoexp(order.getOrderNo(),PayStatus.SUCCESS);
			if(payment != null) {
				coupon.setActualPrice(payment.getPayActual());
			}
			logger.info("已付款，获取优惠券信息：" + JSON.toJSONString(target.getCoupon()));
			target.setCoupon(coupon);
		}
	}

	/**
	 * 给用户备注追加签证情况
	 * @param orderBean
	 * @param gOrderDetailBean
	 */
	private void setUserRemark(OrderBean orderBean, GOrderDetailBean gOrderDetailBean) {
		String userRemark = gOrderDetailBean.getUserRemark();
		if(userRemark != null) {
			String visa = null;
			Integer visaStatus = orderBean.getIsArrivalVisa();
			if(visaStatus != null) {
				VisaType visaType = VisaType.getType(visaStatus);
				switch(visaType) {
				case UNDEFINED:
					visa = "未确定";
					break;
				case INLAND:
					visa = "国内签";
					break;
				case FOREIGN:
					visa = "落地签";
					break;
				default:
					visa = "";
				}
			}
			gOrderDetailBean.setUserRemark(visa + " " + userRemark);
		}
	}
	

	private void setPriceInformation(OrderBean order, GOrderDetailBean target) {
		PriceInfo priceInfo = new PriceInfo();
		priceInfo.setCheckInPrice(order.getCheckInPrice());
		if(OrderPal.hasPaymentInfo(order.getOrderStatus())) {
			TradePayment tradePayment = paymentService.queryTradePaymentByOrderNoNoexp(order.getOrderNo(),PayStatus.SUCCESS);
			if(tradePayment != null) {
				priceInfo.setActualPay(tradePayment.getPayActual());
				priceInfo.setShouldPay(tradePayment.getPayShould());
			}
		} else {
			priceInfo.setShouldPay(order.getPriceChannel());
		}
		priceInfo.setOrderPrice(order.getPriceGuide());
		target.setPriceInfo(priceInfo);
	}
	
	private void setAdditionalCost(OrderBean orderBean, GOrderDetailBean target) {
		TradeAdditionalCost additionalCostBean = additionalCost.getAdditionalCost(orderBean.getOrderNo());
		if(additionalCostBean != null) {
			target.setAlreadyPay(AdditionalCostType.CONFIRMED.value.intValue() == additionalCostBean.getAdditionStatus() ? 1 : 0);
			target.setOverPrice(additionalCostBean.getApplyPrice());
			 List<TradeAdditionalDetail> additionalCostDetail = additionalCost.getAdditionalCostDetails(orderBean.getOrderNo());
			 target.setAdditionalCostDetails(additionalCostDetail);
		} 
	}
	
	
	private void appendOrderTracks(String orderNo, GOrderDetailBean gOrderDetailBean) {
		List<TradeOrderTrack> orderTracks = orderTrackService.getOrderTrackRecords(orderNo);
		if (orderTracks != null && orderTracks.size() > 0) {
			List<OrderTrack> orderTrackList = new LinkedList<>();
			for (TradeOrderTrack orderTrackParam : orderTracks) {
				OrderTrack orderTrackBean = new OrderTrack();
				orderTrackBean.setCreateTime(orderTrackParam.getCreateTime());
				orderTrackBean.setTrackDesc(orderTrackParam.getTrackDesc());
				orderTrackList.add(orderTrackBean);
			}
			if(orderTrackList != null && orderTrackList.size() > 0) {
				gOrderDetailBean.setOrderTracks(orderTrackList);
			}
		}

	}

	@Autowired
	TradeOrderSerialMapper tradeOrderSerialMapper;
	/**
	 * 串单
	 * @param orderBean
	 * @param gOrderDetailBean 
	 * @return 半角分号隔开，前段截取后加样式
	 */
	private void setSerialOrderTip(OrderBean orderBean, TradeDeliverGuide tradeDeliverGuide,GOrderDetailBean gOrderDetailBean) {
		OrderType orderType = OrderType.getType(orderBean.getOrderType());
		if (orderType.equals(OrderType.PICKUPORDER) || orderType.equals(OrderType.TRANSFER)) {
			if(orderBean.getSerialFlag() != null && orderBean.getSerialFlag().equals(OrderSerialFlag.SERIAL.value)) {
				// 后面接的单在详情上显示前面的单概要
				if (OrderSerialFlag.SERIAL.value.equals(orderBean.getSerialFlag()) && tradeDeliverGuide != null) {
					TradeOrderSerialExample tradeOrderSerialExample = new TradeOrderSerialExample();
					tradeOrderSerialExample.createCriteria().andMeetGuideIdEqualTo(tradeDeliverGuide.getGuideId()).andOrderNoEqualTo(orderBean.getOrderNo());
					List<TradeOrderSerial> slist = tradeOrderSerialMapper.selectByExample(tradeOrderSerialExample);
				
					if (slist.size() > 0) {
						OrderBean serialOrder = orderQueryService.getSerialOrder(slist.get(0).getMeetOrderNo());
						if(serialOrder!=null){
							OrderType orderType2 = OrderType.getType(serialOrder.getOrderType());
							gOrderDetailBean.setSerialOrderTip(serialOrder != null ? "您好，此订单与您已接的" + TimeConverter.formatDate(serialOrder.getServiceTime()) + "前往;" + serialOrder.getDestAddress() + ";" + orderType2.name + "订单顺路，优先派送给你。" : null);
						}
					}
				}
				OrderBean serialOrder = orderQueryService.getSerialOrder(orderBean.getSerialOrderNo());
				if(serialOrder!=null){
					OrderType orderType2 = OrderType.getType(serialOrder.getOrderType());
					gOrderDetailBean.setSerialOrderTip(serialOrder != null ? "您好，此订单与您已接的" + TimeConverter.formatDate(serialOrder.getServiceTime()) + "前往;" + serialOrder.getDestAddress() + ";" + orderType2.name + "订单顺路，优先派送给你。" : null);
				}
			}
		}
	}

	/**
	 * @param orderBean
	 * @param gOrderDetail
	 */
	private void setChildSeats(OrderBean orderBean, GOrderDetailBean gOrderDetail) {
		List<ChildSeat> childSeats = null;
		if (StringUtils.isNotBlank(orderBean.getChildSeat())) {
			childSeats = new LinkedList<ChildSeat>();
			for (String childSeatString : orderBean.getChildSeat().split(TradeConstant.SPLITER_COMMA)) {
				if (childSeatString != null) {
					String[] ones = childSeatString.split(TradeConstant.SPLITER_LINE);
					ChildSeat childSeat = new ChildSeat();
					if (ones.length > 0)
						childSeat.setSeatType(ones[0]);
					if (ones.length > 1)
						childSeat.setSeatSum(ones[1]);
					childSeats.add(childSeat);
				}
			}
			gOrderDetail.setChildSeats(childSeats);
		}
	}

	private void setServicePassCity(OrderBean order, GOrderDetailBean target) {
		String servicePassCity = order.getServicePassCity();
		if (!StringUtils.isBlank(servicePassCity)) {
			List<PassCity> passCityList = new LinkedList<>();
			List<Integer> cityIdList = new LinkedList<>();
			for (String cityInfo : servicePassCity.split(TradeConstant.SPLITER_COMMA)) {
				String[] info = cityInfo.split(TradeConstant.SPLITER_LINE);
				PassCity passCity = new PassCity();
				passCity.setCityId(Integer.parseInt(info[0]));
				passCity.setStayDay(Integer.parseInt(info[1]));
				passCityList.add(passCity);
				cityIdList.add(passCity.getCityId());
			}

			List<CityBean> citiesList = cityQueryService.getCities(cityIdList);
			if (citiesList == null || citiesList.isEmpty()) {
				logger.error("途径城市数据异常，存入trade_order表的service_pass_citys数据在basedata_cities表里无对应的cityName");
				throw new TradeException(TradeReturnCodeEnum.PASS_CITY_NOT_FOUND);
			}

			List<PassCity> passCityListTart = new LinkedList<>();
			for (PassCity passCityTemp : passCityList) {
				PassCity passCity = new PassCity();
				passCity.setCityId(passCityTemp.getCityId());
				passCity.setStayDay(passCityTemp.getStayDay());
				for (CityBean city : citiesList) {
					if (city.getCityId().equals(passCityTemp.getCityId())) {
						passCity.setName(city.getCityName());
						break;
					}
				}
				passCityListTart.add(passCity);
			}
			target.setPassCities(passCityListTart);
		}
	}

	/**
	 * @param orderBean
	 * @param gOrderDetail
	 */
	private void setMobiles(OrderBean orderBean, GOrderDetailBean gOrderDetail) {
		List<UserMobile> userMobiles = new LinkedList<UserMobile>();
		if (StringUtils.isNotEmpty(orderBean.getUserMobile1())) {
			UserMobile userMobile = new UserMobile();
			userMobile.setUserAreaCode(orderBean.getUserAreaCode1());
			userMobile.setUserMobile(orderBean.getUserMobile1());
			userMobiles.add(userMobile);
		}

		if (StringUtils.isNotEmpty(orderBean.getUserMobile2())) {
			UserMobile userMobile = new UserMobile();
			userMobile.setUserAreaCode(orderBean.getUserAreaCode2());
			userMobile.setUserMobile(orderBean.getUserMobile2());
			userMobiles.add(userMobile);
		}

		if (StringUtils.isNotEmpty(orderBean.getUserMobile3())) {
			UserMobile userMobile = new UserMobile();
			userMobile.setUserAreaCode(orderBean.getUserAreaCode3());
			userMobile.setUserMobile(orderBean.getUserMobile3());
			userMobiles.add(userMobile);
		}
		if (userMobiles.size() > 0)
			gOrderDetail.setUserMobiles(userMobiles);
	}
}
