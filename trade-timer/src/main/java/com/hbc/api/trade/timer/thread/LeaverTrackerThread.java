/**
 * @Author lukangle
 * @2015年12月2日@下午8:15:30
 */
package com.hbc.api.trade.timer.thread;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hbc.api.trade.timer.service.deliver.order.track.LeaverTrackSender;
@Component
public class LeaverTrackerThread extends Thread {
	private final static Logger log = LoggerFactory.getLogger(LeaverTrackerThread.class);
	@Autowired
	LeaverTrackSender leaverTrackSender;
	@Override
	public void run() {
		log.info("启动push 临行前24小时  导游无服务 .......");
		while (true) {
			try {
				leaverTrackSender.sendLeaveOrder();
				leaverTrackSender.sendNoServiceOrder();
				TimeUnit.SECONDS.sleep(1);
			} catch (Exception e) {
				log.error("", e);
			}
		}
	}
}
