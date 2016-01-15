/**
 * @Author lukangle
 * @2015年12月4日@下午8:30:49
 */
package com.hbc.api.trade.timer.thread.deliver;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hbc.api.trade.bdata.common.util.TConfigLoader;
import com.hbc.api.trade.order.enums.deliver.DeliverType;
import com.hbc.api.trade.order.enums.deliver.TradeDeliverStatus;
import com.hbc.api.trade.order.mapping.gen.bean.TradeOrderDeliver;
import com.hbc.api.trade.order.service.deliver.TradeDeliverService;
import com.hbc.api.trade.timer.service.deliver.order.redeliver.ReDeliverService;
@Component
public class ReDeliverThread implements Runnable {
	private final static Logger log = LoggerFactory.getLogger(ReDeliverThread.class);
	@Autowired
	ReDeliverService reDeliverService;
	@Autowired
	TradeDeliverService tradeDeliverService;
	@Override
	public void run() {
		log.info("重发服务启动.......");
		while (true) {
			try {
				int urgentHour = TConfigLoader.getInt("urgenthour", 24);
				int span = TConfigLoader.getInt("redelivertime", 10);
				String pushUrl =TConfigLoader.getProperty("pushurl");
				
				List<Integer> deliverStatuss = new ArrayList<Integer>();
				deliverStatuss.add(TradeDeliverStatus.delivered.value);
				deliverStatuss.add(TradeDeliverStatus.predeliver.value);

				List<TradeOrderDeliver> tradeDelivers = tradeDeliverService.getTradeDelivers(deliverStatuss, DeliverType.Cancle_Send);
				for(TradeOrderDeliver tradeOrderDeliver : tradeDelivers){
					reDeliverService.startToRedeliver(tradeOrderDeliver,span,urgentHour,pushUrl);
				}
				TimeUnit.SECONDS.sleep(1);
			} catch (Exception e) {
				log.error("", e);
			}
		}
	}

}