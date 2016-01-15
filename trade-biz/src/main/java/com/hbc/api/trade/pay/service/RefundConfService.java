/**
 * @Author lukangle
 * @2015楠烇拷11閺堬拷19閺冾檰娑撳宕�7:36:15
 */
package com.hbc.api.trade.pay.service;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import com.hbc.api.trade.TradeFinalStr;
import com.hbc.api.trade.bdata.common.cache.CacheFinal;
import com.hbc.api.trade.bdata.common.util.DoubleUtil;
import com.hbc.api.trade.order.enums.order.OrderType;
import com.hbc.api.trade.order.enums.order.RefundConfType;
import com.hbc.api.trade.order.mapping.gen.bean.OrderBean;
import com.hbc.api.trade.order.service.OrderServiceTime;
import com.hbc.api.trade.pay.mapping.gen.TradeRefundConfMapper;
import com.hbc.api.trade.pay.mapping.gen.bean.TradePayment;
import com.hbc.api.trade.pay.mapping.gen.bean.TradeRefundConf;
import com.hbc.api.trade.pay.mapping.gen.bean.TradeRefundConfExample;
@Component
public class RefundConfService {
	private Logger log = LoggerFactory.getLogger(RefundConfService.class);
	@Autowired
	TradeRefundConfMapper tradeRefundConfMapper;
	@Autowired
	OrderServiceTime orderServiceTime;
	@Cacheable(value=CacheFinal.confCache,key="#root.targetClass+#root.methodName"+"+#root.args")
	public List<TradeRefundConf> getRefunConfs(){
		TradeRefundConfExample tradeRefundConfExample = new TradeRefundConfExample();
		return tradeRefundConfMapper.selectByExample(tradeRefundConfExample);
	}
	/**
	 * 閼惧嘲褰囩�靛吋鐖堕崣顖烇拷锟藉▎楣冨櫨妫帮拷
	 * @param orderBean
	 * @param tradeRefunds
	 * @return
	 */
	public double getRefundGuidePrice(OrderBean orderBean,List<TradeRefundConf> tradeRefunds){
		double priceGuide = orderBean.getPriceGuide();
		TradeRefundConf tradeRefundConf = getSRconf(orderBean,tradeRefunds);
		
		double guideRatio = tradeRefundConf.getGuideRatio();
		double priceGuided = priceGuide*guideRatio*0.01d;
		if(priceGuided<0){
			priceGuided = 0;
		}
		return priceGuided;
	}
	/**
	 * 闁拷濞嗘崘骞忛崣鏍暏閹村嘲褰查柅锟介柌鎴︻杺
	 * @param orderBean
	 * @param tradeRefunds
	 * @return
	 */
	public double getRefundUserPrice(OrderBean orderBean,TradePayment tradePayment,List<TradeRefundConf> tradeRefunds){
		double refundAblePrice = tradePayment.getPayShould();
		if(tradeRefunds==null || tradeRefunds.size()==0){
			return refundAblePrice;
		}
		TradeRefundConf tradeRefundConf = getSRconf(orderBean,tradeRefunds);
		if(tradeRefundConf == null) return 0.0;
		double userRatio = 100 - tradeRefundConf.getUserRatio();
		double refundAmount =  DoubleUtil.multiplicationDouble(refundAblePrice, userRatio*0.01d);
		if(refundAmount<0){
			refundAmount = 0;
		}
		
		if(orderBean.getGuideId() == null || TradeFinalStr.defaultGuideId.equals(orderBean.getGuideId())){
			refundAmount = refundAblePrice;
		}
		return DoubleUtil.getNoFloat(refundAmount);
	}
	
	
	public double getRefundUserAblePrice(OrderBean orderBean,TradePayment tradePayment,List<TradeRefundConf> tradeRefunds){
		double refundAblePrice =  getRefundUserPrice(orderBean,tradePayment,tradeRefunds);
		
		Double couppay = tradePayment.getCoupPay();
		if(couppay!=null && refundAblePrice>=couppay){
			return DoubleUtil.subtractionDouble(refundAblePrice, couppay);
		}else if(couppay!=null && refundAblePrice<couppay){
			return 0d;
		}
		return refundAblePrice;
	}
	private TradeRefundConf getSRconf(OrderBean orderBean,List<TradeRefundConf> tradeRefunds){
		OrderType orderType = OrderType.getTypeByGoodeType(orderBean);
		Date curtime = orderServiceTime.getServiceCityCurTime(orderBean.getServiceCityId());
		
		long stime = orderBean.getServiceTime().getTime()-curtime.getTime();
		if(stime<=0){
			stime = 1;
		}
		if(OrderType.DAILY.equals(orderType)){
			
			for(TradeRefundConf tradeRefundConf : tradeRefunds){
				if(RefundConfType.DC.equals(RefundConfType.getType(tradeRefundConf.getRefundType()))){
					//(24-48] 閸栨椽妫跨拋锛勭暬
					if(isContain(stime,tradeRefundConf)){
						return tradeRefundConf;
					}
					
				}
			}
		}else{
			for(TradeRefundConf tradeRefundConf : tradeRefunds){
				if(RefundConfType.SPT.equals(RefundConfType.getType(tradeRefundConf.getRefundType()))){
					//(24-48] 閸栨椽妫跨拋锛勭暬
					if(isContain(stime,tradeRefundConf)){
						return tradeRefundConf;
					}
					
				}
			}
		}
		log.error("鑾峰彇 閫�娆鹃厤缃俊鎭け璐�");
		return null;
	}
	
	
	/**
	 * 閺冨爼妫块弰顖氭儊閸︺劏顔曠純顔芥闂傜瀵栭崶鏉戝敶
	 * @param serviceTime
	 * @param tradeRefundConf
	 * @return
	 */
	private boolean isContain(long stime,TradeRefundConf tradeRefundConf){
		//(24-48] 閸栨椽妫跨拋锛勭暬
		boolean isContain = false;
		if(tradeRefundConf.getIsContainStart()==1){
			isContain = stime>=tradeRefundConf.getStartHour()*(60*60*1000);
		}else{
			isContain = stime>tradeRefundConf.getStartHour()*(60*60*1000);
		}
		
		if(tradeRefundConf.getIsContainEnd()!=null && tradeRefundConf.getIsContainEnd()==1){
			if(tradeRefundConf.getEndHour()!=null){
				isContain = isContain && stime<=tradeRefundConf.getEndHour()*(60*60*1000);
			}
		}else{
			if(tradeRefundConf.getEndHour()!=null){
				isContain = isContain && stime<tradeRefundConf.getEndHour()*(60*60*1000);
			}
		}
		return isContain;
	}
}
