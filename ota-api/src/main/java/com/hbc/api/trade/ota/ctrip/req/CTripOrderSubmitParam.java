package com.hbc.api.trade.ota.ctrip.req ;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.NotBlank;

import com.hbc.api.trade.bdata.common.TradeConstant;
import com.hbc.api.trade.bdata.common.util.TimeConverter;
import com.hbc.api.trade.order.enums.order.OrderType;
import com.hbc.api.trade.order.enums.ota.AgentChannelEnum;
import com.hbc.api.trade.order.mapping.gen.bean.OrderBean;
import com.hbc.api.trade.ota.mapping.gen.bean.TradeThirdOrder;
import com.hbc.api.trade.ota.req.OrderSubmitParam;

/**
 * CTrip API.
 * @author Jongly Ran
 */
public class CTripOrderSubmitParam {
	
	public Integer UseType ;					// 服务类型：3-海外接送机
	
	@NotNull 	
	public Integer PatternType ;				// 用车形态（1-接机，2-送机）
	
	@NotNull 	
	public String 	TotalPrice ;				// 订单总价
	
	public String 	PriceMark ;					// 价格标示符号，用来标明本次约定的价格（下单时使用，值从查价来）
	
	@NotNull 	
	public Integer VehicleType ;				// 车型ID列表（如不指定则返回该城市提供的所有车型信息）

	public String 	AreaCode ;					// 国家区号
	
	public String 	Mobile ;					// 手机号
	
	public String  Name ;						// 姓名
	
    @NotBlank 	
    public String 	FixedCode ;					// 机场三字码/火车站ID
    
	public String  FlightNumber ;	 			// 航班号(接机必须，送机非必须)
    
	public String 	DuseLocationAddress;		// 出发地址
	
	public String 	DuseLocationDetailAddress ; // 出发详细地址
	
    @NotNull 	
    public String 	DuseLocationLongitude ; 	// 出发经度
    
    @NotNull 	
    public String 	DuseLocationLatitude ; 		// 出发纬度
    
    public String 	AuseLocationAddress ;		// 到达地址
    
    public String 	AuseLocationDetailAddress ;	// 到达详细地址
    
    @NotNull 	
    public String 	AuseLocationLongitude ; 	// 到达经度
    
    @NotNull 	
    public String 	AuseLocationLatitude ; 		// 到达纬度
    
    @NotBlank 	
    public String 	UseTime ; 					// 用车时间（当地时间）
    public Integer AdultNum;

	public Integer ChildSeatCount =0;			// 婴儿车辆数
	public String  ChildSeat ;	 				// 1岁对应1，4岁对应 2，7岁对应 3 ，12岁对应 4
	public Boolean IsNeedPickUp ;	 			// 是否需要举牌
	public String  PickupCardMsg ;	 			// 如果举机牌存在此参数为必须	举牌接机文字描述

	public String  SaleCode ;	 				// 代表优惠编码
	public String  SalePrice ;	 				// 代表优惠价格50元


	public String CtripPurchaseOrderID;	// 	携程订单号
	
	public String sign;		// 签名

    public Long timestamp ;	// 时间戳
    
    public String 	noncestr ;	// 随机字符串
	
	/* TODO 第二版, 来自查价*/
	public Integer urgentFlag;					
	public String	distance;
	public Integer estime;
	
    @Deprecated
    /**
     * 老版本无，确认新版本是否有
     */ 
	public Integer ChildNum ;			//

    @Deprecated
    /**
     * 老版本无，确认新版本是否有
     */
	public Integer expectedCompTime ;
    
	public OrderSubmitParam toStandardSubmitParam() {
		OrderBean orderBean = new OrderBean();
		orderBean.setAgentId(AgentChannelEnum.CTRIP_CHANNEL.value+"");
		orderBean.setAgentName(AgentChannelEnum.CTRIP_CHANNEL.desc);
		orderBean.setAgentOpid(AgentChannelEnum.CTRIP_CHANNEL.value+"");
		orderBean.setAgentOpname(AgentChannelEnum.CTRIP_CHANNEL.desc);
		orderBean.setOrderChannel(AgentChannelEnum.CTRIP_CHANNEL.value);
		orderBean.setAdultNum(AdultNum);
		orderBean.setChildNum(ChildSeatCount);
		orderBean.setServiceTime(TimeConverter.toDate(UseTime) );
		orderBean.setUserAreaCode1(AreaCode);
		orderBean.setUserMobile1(Mobile);
		orderBean.setExpectedCompTime(expectedCompTime);
		orderBean.setThirdTradeNo(CtripPurchaseOrderID);
		orderBean.setStartAddress(DuseLocationAddress);
		orderBean.setStartAddressDetail(DuseLocationDetailAddress);
		orderBean.setStartAddressPoi(DuseLocationLatitude + "," + DuseLocationLongitude);
		orderBean.setDestAddress(AuseLocationAddress);
		orderBean.setDestAddressDetail(AuseLocationDetailAddress);
		orderBean.setDestAddressPoi(AuseLocationLatitude + "," + AuseLocationLongitude);
		orderBean.setUserName(Name);
		orderBean.setPriceMark(PriceMark);
		orderBean.setFlightNo(FlightNumber);
		orderBean.setUrgentFlag(urgentFlag);
		orderBean.setDistance(distance == null ? null : Double.valueOf(distance));
		orderBean.setExpectedCompTime(estime);
		orderBean.setCarTypeId(VehicleType);
		orderBean.setPriceChannel(Double.valueOf(TotalPrice));
		// 订单类型适配
		if(PatternType == 1) {
			orderBean.setFlightDestCode(FixedCode);
			orderBean.setOrderType(OrderType.PICKUPORDER.value);
			
			// 接机牌适配
			if(IsNeedPickUp != null && IsNeedPickUp) {
				orderBean.setFlightBrandSign(PickupCardMsg);
			}
		} else {
			orderBean.setFlightAirportCode(FixedCode);
			orderBean.setOrderType(OrderType.TRANSFER.value);
		}
		
		// 儿童座椅适配 TODO 再次跟携程
		if(ChildSeatCount != null && StringUtils.isNotBlank(ChildSeat)) {
			orderBean.setChildNum(ChildSeatCount);
			Integer number = 1;
			Map<Integer, Integer> childSeatMap = new HashMap<>();
			for(String seatOfAgeType : ChildSeat.split(TradeConstant.SPLITER_COMMA)) {
				if(childSeatMap.containsKey(seatOfAgeType)) {
					childSeatMap.put(Integer.valueOf(seatOfAgeType), ++number);
				} else {
					childSeatMap.put(Integer.valueOf(seatOfAgeType), number);
				}
			}
			Set<Integer> keySet = childSeatMap.keySet();
			if(keySet != null && keySet.size() > 0) {
				StringBuilder childSeatString = new StringBuilder();
				for(Integer seatOfAgeType : keySet) {
					childSeatString.append(TradeConstant.SPLITER_COMMA).append(seatOfAgeType).append(TradeConstant.SPLITER_LINE).append(childSeatMap.get(seatOfAgeType));
				}
				orderBean.setChildSeat(childSeatString.toString().substring(1));
			}
		}
		
		TradeThirdOrder thirdOrderBean = new TradeThirdOrder();
		thirdOrderBean.setThirdPartner(AgentChannelEnum.CTRIP_CHANNEL.value);
		thirdOrderBean.setPrice(Double.valueOf(TotalPrice));
		thirdOrderBean.setPriceMark(PriceMark);
		thirdOrderBean.setThirdTradeNo(CtripPurchaseOrderID);
		thirdOrderBean.setThirdCarType(VehicleType);
		thirdOrderBean.setCouponAmount(SalePrice == null ? 0d : Double.valueOf(SalePrice));
		
		OrderSubmitParam submitParam = new OrderSubmitParam();
		submitParam.setOrderBean(orderBean);
		submitParam.setThirdOrderBean(thirdOrderBean);
		submitParam.setSign(sign);
		return submitParam;
	}
}