/**
 * @Author lukangle
 * @2015年12月4日@下午8:23:27
 */
package com.hbc.api.trade.timer.thread.deliver;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hbc.api.trade.order.enums.deliver.DeliverType;
import com.hbc.api.trade.order.enums.deliver.OrderSerialFlag;
import com.hbc.api.trade.order.enums.deliver.PkChannelType;
import com.hbc.api.trade.order.mapping.gen.bean.OrderBean;
import com.hbc.api.trade.order.mapping.gen.bean.TradeOrderDeliver;
import com.hbc.api.trade.order.service.OrderQueryService;
import com.hbc.api.trade.order.service.deliver.GuidDeliverOrderService;
import com.hbc.api.trade.order.service.deliver.OrderDeliverService;
import com.hbc.api.trade.order.service.deliver.TradeDeliverService;
import com.hbc.api.trade.third.ChannelService;
import com.hbc.api.trade.timer.service.deliver.order.pk.PkCommonService;
import com.hbc.api.trade.timer.service.deliver.order.pk.impl.FWPrioryServiceImpl;
import com.hbc.api.trade.timer.service.deliver.order.pk.impl.IncomePkService;
import com.hbc.api.trade.timer.service.deliver.order.pk.impl.RsptimePrioryService;
import com.hbc.api.trade.timer.service.deliver.order.pk.impl.SerialPkService;
@Component
public class PkThread implements Runnable {
	private final static Logger log = LoggerFactory.getLogger(PkThread.class);
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
	RsptimePrioryService rsptimePrioryService;
	@Autowired
	FWPrioryServiceImpl fWPrioryServiceImpl;
	@Autowired
	IncomePkService incomePkService;
	@Autowired
	PkCommonService pkCommonService;
	@Autowired
	SerialPkService serialPkService;
	
	ExecutorService executor;
	
	@Override
	public void run() {
		while (true) {
			try {
				try{
//					executor = Executors.newFixedThreadPool(10);
					long cutime = System.currentTimeMillis();
					List<Integer> deliverTypes = new ArrayList<Integer>();
					deliverTypes.add(DeliverType.Cancle_Send.value);
					deliverTypes.add(DeliverType.Incremental_Send.value);
					deliverTypes.add(DeliverType.Ordinary.value);
					deliverTypes.add(DeliverType.Immediately.value);
					
					//获取最近的deliver time
					List<TradeOrderDeliver> tradeOrderDelivers = tradeDeliverService.getTradeDeliversForPk(deliverTypes);
					for(TradeOrderDeliver tradeOrderDeliver : tradeOrderDelivers){
//						PkOrderThread pkOrderThread = new PkOrderThread(tradeOrderDeliver);
//						executor.submit(pkOrderThread);
						
						OrderBean orderBean = orderQueryService.getOrderByNo(tradeOrderDeliver.getOrderNo());
						int channelId = orderBean.getOrderChannel();
						
						//串单模式
						if(OrderSerialFlag.SERIAL.value.equals(orderBean.getSerialFlag())){
							pkCommonService.pkPriory(orderBean, tradeOrderDeliver, serialPkService);
						}else{
							PkChannelType pkChannelType = channelService.getPkType(channelId+"");						
							
							if(PkChannelType.RspPriory.equals(pkChannelType)){
								pkCommonService.pkPriory(orderBean, tradeOrderDeliver, rsptimePrioryService);
							}else if(PkChannelType.IncomePriory.equals(pkChannelType)){
								pkCommonService.pkPriory(orderBean, tradeOrderDeliver, incomePkService);
							}else if(PkChannelType.ServicePriory.equals(pkChannelType)){
								pkCommonService.pkPriory(orderBean, tradeOrderDeliver, fWPrioryServiceImpl);
							}else{
								log.error(channelId+" 发单策略配置错误 [%S]",channelId);
							}
						}
						
						
//						log.info(tradeOrderDeliver.getOrderNo()+" pk完成");
					}
//					executor.shutdown();
//					executor.awaitTermination(15, TimeUnit.MINUTES);
//					log.info("冲击波完成 耗时  ["+(System.currentTimeMillis()-cutime)+"]");
				}catch(Exception e){
					log.error("", e);
				}
				TimeUnit.SECONDS.sleep(1);
			} catch (Exception e) {
				log.error("", e);
			}
		}
	}
	
	public class PkOrderThread implements Runnable{
		private TradeOrderDeliver tradeOrderDeliver;
		public PkOrderThread(TradeOrderDeliver tradeOrderDeliver){
			this.tradeOrderDeliver = tradeOrderDeliver;
		}
		@Override
		public void run() {
			try{
				OrderBean orderBean = orderQueryService.getOrderByNo(tradeOrderDeliver.getOrderNo());
				int channelId = orderBean.getOrderChannel();
				
				PkChannelType pkChannelType = channelService.getPkType(channelId+"");
				
				if(PkChannelType.RspPriory.equals(pkChannelType)){
					pkCommonService.pkPriory(orderBean, tradeOrderDeliver, rsptimePrioryService);
				}else if(PkChannelType.IncomePriory.equals(pkChannelType)){
					pkCommonService.pkPriory(orderBean, tradeOrderDeliver, incomePkService);
				}else if(PkChannelType.ServicePriory.equals(pkChannelType)){
					pkCommonService.pkPriory(orderBean, tradeOrderDeliver, fWPrioryServiceImpl);
				}else{
					log.error(channelId+" 发单策略配置错误 [%S]",channelId);
				}
				log.info(tradeOrderDeliver.getOrderNo()+" pk完成");
			}catch(Exception e){
				log.error("", e);
			}
		}
		
	}

}
