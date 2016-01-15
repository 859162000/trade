/**
 * @Author lukangle
 * @2015年12月2日@下午2:37:09
 */
package com.hbc.api.trade.timer.service.deliver.order.serial;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hbc.api.trade.order.mapping.gen.TradeOrderSerialMapper;
import com.hbc.api.trade.order.mapping.gen.bean.OrderBean;
import com.hbc.api.trade.order.mapping.gen.bean.TradeOrderSerial;
import com.hbc.api.trade.order.mapping.gen.bean.TradeOrderSerialExample;

@Component
public class TradeOrderSerialService {
	@Autowired
	TradeOrderSerialMapper tradeOrderSerialMapper;
	public void saveTradeSerial(OrderBean orderBean,OrderBean meetOrder){
		TradeOrderSerial tradeOrderSerial = new TradeOrderSerial();
		tradeOrderSerial.setCreateTime(new Date());
		tradeOrderSerial.setIsMatch(1);
		tradeOrderSerial.setMeetGuideId(meetOrder.getGuideId());
		tradeOrderSerial.setMeetOrderAddress(meetOrder.getStartAddress());
		tradeOrderSerial.setMeetOrderNo(meetOrder.getOrderNo());
		tradeOrderSerial.setMeetOrderServiceTime(meetOrder.getServiceTime());
		tradeOrderSerial.setMeetOrderType(meetOrder.getOrderType());
		tradeOrderSerial.setOrderNo(orderBean.getOrderNo());
		
		tradeOrderSerialMapper.insert(tradeOrderSerial);
	}
	
	
	public List<TradeOrderSerial> getAllTradeSeri(String orderNo){
		TradeOrderSerialExample tradeOrderSerialExample = new TradeOrderSerialExample();
		TradeOrderSerialExample.Criteria criteria = tradeOrderSerialExample.createCriteria();
		criteria.andOrderNoEqualTo(orderNo);
		List<TradeOrderSerial> tlist = tradeOrderSerialMapper.selectByExample(tradeOrderSerialExample);
		return tlist;
	}
}
