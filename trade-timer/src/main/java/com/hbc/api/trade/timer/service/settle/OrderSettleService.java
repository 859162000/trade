/**
 * @Author lukangle
 * @2015年11月21日@下午2:46:02
 */
package com.hbc.api.trade.timer.service.settle;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.hbc.api.fund.account.enums.BizType;
import com.hbc.api.fund.account.service.FundAccountService;
import com.hbc.api.trade.TradeFinalStr;
import com.hbc.api.trade.bdata.common.util.DoubleUtil;
import com.hbc.api.trade.order.enums.order.OrderSource;
import com.hbc.api.trade.order.enums.order.OrderStatus;
import com.hbc.api.trade.order.enums.order.OrderType;
import com.hbc.api.trade.order.exception.TradeOtaSettleException;
import com.hbc.api.trade.order.mapping.gen.OrderBeanMapper;
import com.hbc.api.trade.order.mapping.gen.bean.OrderBean;
import com.hbc.api.trade.order.mapping.gen.bean.OrderBeanExample;
import com.hbc.api.trade.order.service.OrderService;
import com.hbc.api.trade.order.service.OrderTrackService;
import com.hbc.api.trade.settle.enums.AccountEnums;
import com.hbc.api.trade.settle.enums.FundBizEnumConvter;
import com.hbc.api.trade.timer.service.ota.OtaOrderService;

@Component
public class OrderSettleService {
	Logger log = LoggerFactory.getLogger(OrderSettleService.class);
	@Autowired
	private OrderBeanMapper orderBeanMapper;
	@Autowired
	FundAccountService fundAccountService;
	@Autowired
	OrderService orderService;
	@Autowired
	OtaOrderService otaOrderService;

	@Autowired private OrderTrackService 	orderTrackService;
	
	@Transactional
	public void settleAcount(OrderBean orderBean){
		
		double guidePrice = orderBean.getPriceGuide();
		String accountNo = orderBean.getGuideAccountNo();
		if(accountNo==null){
			log.error(orderBean.getOrderNo()+"   "+orderBean.getGuideId()+" 无资金账户");
		}
		OrderType orderType = OrderType.getType(orderBean.getOrderType());
		BizType bizType = FundBizEnumConvter.getFundBizType(orderType);
		String orderNo = orderBean.getOrderNo();
		fundAccountService.pay(accountNo, guidePrice, bizType, orderNo);
		
		//HBC收益账户
		double priceChannel = orderBean.getPriceChannel();
		double profitAmount = DoubleUtil.subtractionDouble(priceChannel, guidePrice);
		if(profitAmount>0){
			fundAccountService.pay(AccountEnums.HBC_Profit.value, profitAmount, bizType, orderNo);
		}else if(profitAmount<0){
			//HBC 补贴账户
			double butieAmount = DoubleUtil.subtractionDouble(guidePrice,priceChannel);
			fundAccountService.pay(AccountEnums.HBC_BUTIE.value, butieAmount, bizType, orderNo);
		}
		//HBC担保账户
		fundAccountService.recharge(AccountEnums.HBC_Guarantee.value, priceChannel, bizType, orderNo);
		
		orderService.updateOrderStatus(orderNo, OrderStatus.CONFIRMED_COST, OrderStatus.SETTLEMENT);
		if(profitAmount>0){
			log.info(orderNo+" 结算完成  ，导游  ["+accountNo+" : "+guidePrice+"] HBC收益账户 ["+profitAmount+"]  担保账户 减掉["+priceChannel+"] ");
		}else{
			log.info(orderNo+" 结算完成  ，导游  ["+accountNo+" : "+guidePrice+"] HBC补贴账户 ["+profitAmount+"]  担保账户 减掉["+priceChannel+"] ");
		}
		//结算完成
		if(OrderSource.OTA.value.equals(orderBean.getOrderSource())){
			try{
				otaOrderService.settleOrderBean(orderBean);
			}catch(TradeOtaSettleException error){
				log.error("携程回调结算失败"+orderBean.getOrderNo());
				OrderBean record = new OrderBean();
				record.setOrderNo(orderNo);
				record.setIsNewTrack(TradeFinalStr.nosettle);
				orderBeanMapper.updateByPrimaryKeySelective(record);
				throw error;
			}
		}
		orderTrackService.settlementCompleted(orderNo);
		
		OrderBean record = new OrderBean();
		record.setOrderNo(orderNo);
		record.setCompleteTime(new Date());
		orderService.updateOrder(record);
	}
	public List<OrderBean> getAllSettleOrders(){
		OrderBeanExample orderBeanExample = new OrderBeanExample();
		OrderBeanExample.Criteria criteria = orderBeanExample.createCriteria();
		criteria.andOrderStatusEqualTo(OrderStatus.CONFIRMED_COST.value);
//		criteria.andIsNewTrackNotEqualTo(TradeFinalStr.nosettle);
		criteria.andIsNewTrackIsNull();
		List<OrderBean> settleOrders = orderBeanMapper.selectByExample(orderBeanExample);
		return settleOrders;
	}
}
