/**
 * @Author lukangle
 * @2015年10月26日@下午2:47:05
 */
package com.hbc.api.trade.timer.service.deliver.order.nomal;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hbc.api.trade.bdata.mapper.guide.gen.bean.GuideBean;
import com.hbc.api.trade.order.enums.deliver.DeliverType;
import com.hbc.api.trade.order.enums.deliver.OrderDeliverStatus;
import com.hbc.api.trade.order.enums.deliver.OrderSerialFlag;
import com.hbc.api.trade.order.enums.deliver.TradeDeliverStatus;
import com.hbc.api.trade.order.enums.deliver.UrgentFlag;
import com.hbc.api.trade.order.enums.order.OrderStatus;
import com.hbc.api.trade.order.mapping.gen.bean.OrderBean;
import com.hbc.api.trade.order.mapping.gen.bean.TradeOrderDeliver;
import com.hbc.api.trade.order.service.OrderServiceTime;
import com.hbc.api.trade.order.service.deliver.GuideDeliverService;
import com.hbc.api.trade.order.service.deliver.OrderDeliverService;
import com.hbc.api.trade.order.service.deliver.TradeDeliverService;
import com.hbc.api.trade.order.service.deliver.conf.TradeCitySolrConf;
import com.hbc.api.trade.order.service.solr.TradeConfCollectionService;
import com.hbc.api.trade.timer.service.deliver.order.serial.OrderSerialService;
/**
 * 					支付前 导游价 修改 不能批量重发，若要批量发则需要 修改成成原有价格  
 */
@Component
public class NomalOrderDeliverSender {
	private final static Logger log = LoggerFactory.getLogger(NomalOrderDeliverSender.class);
	@Autowired
	NomalTradeDeliverService nomalTradeDeliverService;
	@Autowired
	OrderServiceTime orderServiceTime;
	@Autowired
	OrderDeliverService orderDeliverService;
	@Autowired
	OrderSerialService orderSerialService;
	/**
	 * 普通发单，  急单会立即发送
	 */
	public void preSendOrder(){
		int cnum = nomalTradeDeliverService.countAllDeliverOrders();
		//每次处理500条
		int limit = 500;
		int caltime = cnum/limit;
		int offset = 0;
		for(int i=0;i<=caltime;i++){
			List<OrderBean> orderBeans = nomalTradeDeliverService.queryDeliverOrderBeans(limit, offset);
			for(OrderBean orderBean : orderBeans){
				if( OrderStatus.PAYSUCCESS.value.equals(orderBean.getOrderStatus().intValue())){
					try{
						Date serviceTime = orderBean.getServiceTime();
						Date curtime = orderServiceTime.getServiceCityCurTime(orderBean.getServiceCityId());
						if(serviceTime.after(curtime)){
							/**
							 * 急单  会立即发送
							 */
							preSend(orderBean);
						}else{
							orderDeliverService.updateOrderDeliverStatus(orderBean.getOrderNo(), OrderStatus.PAYSUCCESS, OrderDeliverStatus.getType(orderBean.getDeliverStatus()), OrderDeliverStatus.deliverfailed);
							log.warn(orderBean.getOrderNo()+" 已经过期  更新状态为 发单失败");
						}
					}catch(Exception e){
						log.error(orderBean.getOrderNo()+" 预发单发生错误");
						log.error("", e);
					}
				}else{
					log.error(orderBean.getOrderNo()+" 订单状态不对 状态或者发单装填["+ orderBean.getOrderStatus()+"] ["+orderBean.getDeliverStatus()+"]");
				}
			}
		}
	}
	@Autowired
	GuideDeliverService guideDeliverService;
	@Autowired
	TradeConfCollectionService tradeConfCollectionService;
	public void preSend(OrderBean orderBean) {
		// solr 配置查询
		TradeCitySolrConf tradeCitySolrConf = tradeConfCollectionService.queryCityConf(orderBean);
		UrgentFlag urgentFlagDb = UrgentFlag.getType(orderBean.getUrgentFlag());
		
		
		// 可发单导游获取
		List<GuideBean> guideList = guideDeliverService.getDeliverGuidByCity(orderBean);
		//可串单导游
		Map<String,OrderBean> smap = orderSerialService.checkOrderSerial(orderBean,guideList);
//		Map<String,OrderBean> smap = new HashMap<String,OrderBean>();
		if(!smap.isEmpty()){
			//串单逻辑
			orderBean.setSerialFlag(OrderSerialFlag.SERIAL.value);
			if(UrgentFlag.urgent.equals(urgentFlagDb)){
				nomalTradeDeliverService.sendSearilUrgentOrder(orderBean,smap,tradeCitySolrConf);
			}else{
				nomalTradeDeliverService.sendSeriNomalOrder(orderBean,smap,tradeCitySolrConf);
			}
		}else{
			
			if(UrgentFlag.urgent.equals(urgentFlagDb)){
				nomalTradeDeliverService.sendUrgentOrder(orderBean,guideList,tradeCitySolrConf);
			}else{
				nomalTradeDeliverService.sendNomalOrder(orderBean,guideList,tradeCitySolrConf);
			}
		}
	}
	@Autowired
	TradeDeliverService tradeDeliverService;
	@Autowired
	NomalSendService nomalSendService;
	/**
	 * 预处理完成后 发单启动
	 */
	public void nomalSend() {
		List<TradeOrderDeliver> tradeDelivers = tradeDeliverService.getTradeDelivers(TradeDeliverStatus.predeliver, DeliverType.Ordinary);
		for (TradeOrderDeliver tradeOrderDeliver : tradeDelivers) {
			try{
				Date curdate = orderServiceTime.getServiceCityCurTime(tradeOrderDeliver.getCityId());
				int hour = curdate.getHours();
//				if(true){
				if (hour <= 22 && hour >= 8) {
					nomalSendService.sendOrderToGuide(tradeOrderDeliver);
				}
			}catch(Exception e){
				log.error("", e);
			}
		}
	}

	
}
