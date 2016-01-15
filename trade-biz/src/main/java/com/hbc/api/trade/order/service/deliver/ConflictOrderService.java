/**
 * @Author lukangle
 * @2015年11月10日@下午2:10:51
 */
package com.hbc.api.trade.order.service.deliver;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.hbc.api.trade.order.enums.deliver.OrderSerialFlag;
import com.hbc.api.trade.order.enums.order.OrderType;
import com.hbc.api.trade.order.mapping.gen.bean.OrderBean;
import com.hbc.api.trade.order.mapping.gen.bean.TradeDeliverGuide;
import com.hbc.api.trade.order.service.deliver.conf.ConflictFlag;
import com.hbc.api.trade.order.service.deliver.conf.TradeCitySolrConf;
/**
 * 冲突规则判断服务
 */
@Component
public class ConflictOrderService {
	private final static Logger log = LoggerFactory.getLogger(ConflictOrderService.class);
	@Autowired
	QueryDeliverOrderService queryDeliverOrderService;
	/**
	 * 只用于PK
	 * @return
	 */
	public synchronized boolean synConflict(OrderBean orderBean, String guideId, TradeCitySolrConf tradeCitySolrConf){
		return isConflict(orderBean,guideId,tradeCitySolrConf);
	}
	@SuppressWarnings("deprecation")
	public boolean isConflict(OrderBean orderBean, String guideId, TradeCitySolrConf tradeCitySolrConf) {
		//串单不应该有冲突规则
		if(tradeCitySolrConf==null || OrderSerialFlag.SERIAL.value.equals(orderBean.getSerialFlag())){
			return false;
		}
		OrderType orderType = OrderType.getType(orderBean.getOrderType());
		boolean isConflict = false;

		Date serviceStartTime = new Date(orderBean.getServiceTime().getTime());
		serviceStartTime.setHours(0);
		serviceStartTime.setMinutes(0);
		serviceStartTime.setSeconds(0);
		Date serviceEndTime =new Date( orderBean.getServiceEndTime().getTime());
		serviceEndTime.setHours(23);
		serviceEndTime.setMinutes(59);
		serviceEndTime.setSeconds(59);

		if (OrderType.DAILY.equals(orderType)) {
			// 日租 对日租冲突
			List<OrderBean> olist = queryDeliverOrderService.getMayConfictGuideOrders(guideId, serviceStartTime, serviceEndTime);
			if (olist.size() > 0) {
				isConflict = true;
				
				log.debug("日租 冲突 规则 ： ["+orderBean.getOrderNo()+"] 导游 ["+guideId+"]订单冲突 "+JSON.toJSONString(olist));
			}
		} else {
			// 寻找日租
			List<OrderBean> olist = queryDeliverOrderService.getMayConfictGuideDailyOrders(guideId, serviceStartTime, serviceEndTime);
			if (olist.size() > 0) {
				isConflict = true;
				log.debug("日租 冲突 规则 ： ["+orderBean.getOrderNo()+"] 导游 ["+guideId+"]订单冲突 "+JSON.toJSONString(olist));
			} else {
				// 接送次是否冲突
				int openFlag = tradeCitySolrConf.getConflict_sing_airport();
				if (ConflictFlag.OPEN.value==openFlag) {
					int gapMin = tradeCitySolrConf.getConflict_time_gap();
					Date gapStartTime = new Date(orderBean.getServiceTime().getTime() - gapMin * 60 * 1000);
					Date gapEndTime = new Date(orderBean.getServiceEndTime().getTime() + gapMin * 60 * 1000);

					olist = queryDeliverOrderService.getMayConfictGuideOrders(guideId, gapStartTime, gapEndTime);
					if (olist.size() > 0) {
						isConflict = true;
						log.debug("接送机 冲突 规则 ： ["+orderBean.getOrderNo()+"] 导游 ["+guideId+"]订单冲突 "+JSON.toJSONString(olist));
					}
				}
			}
		}

		return isConflict;
	}
	
	
	
	public List<TradeDeliverGuide> getNoConflictG(OrderBean orderBean, List<TradeDeliverGuide> tradeDeliverGuides, TradeCitySolrConf tradeCitySolrConf) {
		List<TradeDeliverGuide> glist = new ArrayList<TradeDeliverGuide>();
		for(TradeDeliverGuide tradeDeliverGuide : tradeDeliverGuides){
			boolean isC = isConflict(orderBean,tradeDeliverGuide.getGuideId(),tradeCitySolrConf);
			if(isC){
				glist.add(tradeDeliverGuide);
			}
		}

		return glist;
	}
}
