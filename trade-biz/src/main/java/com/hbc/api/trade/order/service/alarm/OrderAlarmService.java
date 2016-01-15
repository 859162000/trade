package com.hbc.api.trade.order.service.alarm;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.hbc.api.trade.order.enums.TradeReturnCodeEnum;
import com.hbc.api.trade.order.enums.alarm.AlarmFlag;
import com.hbc.api.trade.order.enums.alarm.AlarmStatus;
import com.hbc.api.trade.order.enums.alarm.AlarmType;
import com.hbc.api.trade.order.exception.TradeException;
import com.hbc.api.trade.order.mapping.gen.TradeAlarmMapper;
import com.hbc.api.trade.order.mapping.gen.bean.OrderBean;
import com.hbc.api.trade.order.mapping.gen.bean.TradeAlarm;
import com.hbc.api.trade.order.mapping.gen.bean.TradeAlarmExample;
import com.hbc.api.trade.order.service.OrderServiceTime;

@Service
public class OrderAlarmService {
	private Logger log = LoggerFactory.getLogger(OrderAlarmService.class);
	@Autowired
	TradeAlarmMapper tradeAlarmMapper;
	@Autowired
	OrderServiceTime orderServiceTime;
	
	public void insertBeforeLeave(OrderBean orderBean) {
		try{
			TradeAlarm tradeAlarm = new TradeAlarm();
			Date sserverTime = orderServiceTime.getTimeZoneServiceTime(orderBean.getServiceCityId(), orderBean.getServiceTime());
			Timestamp sendPreTime = new Timestamp(sserverTime.getTime()-24*60*60*1000);
			tradeAlarm.setCreateTime(new Date());
			tradeAlarm.setFlag(AlarmFlag.NOSEND.value);
			tradeAlarm.setOrderNo(orderBean.getOrderNo());
			tradeAlarm.setOrderServiceTime(orderBean.getServiceTime());
			
			tradeAlarm.setSendPreTime(sendPreTime);
			
			tradeAlarm.setStatus(AlarmStatus.INIT.value);
			tradeAlarm.setType(AlarmType.LEAVING.value);
			tradeAlarm.setAlarmTypeName(AlarmType.LEAVING.name);
			tradeAlarm.setAlarmType(AlarmType.LEAVING.value);
			int optnum = tradeAlarmMapper.insert(tradeAlarm);
			if(optnum<=0){
				throw new TradeException(TradeReturnCodeEnum.FAILED_FOR_INSERT,orderBean.getOrderNo()+ "临行前24小时");
			}
		}catch(Exception e){
			log.error("通知失败", e);
			throw new TradeException(TradeReturnCodeEnum.FAILED_FOR_INSERT,e);
		}
	}

	/**
	 * 下单时调用
	 * @param orderBean
	 */
	public void insertNoService(OrderBean orderBean) {
		try{
			TradeAlarm tradeAlarm = new TradeAlarm();
			Date sserverTime = orderServiceTime.getTimeZoneServiceTime(orderBean.getServiceCityId(), orderBean.getServiceTime());
			
			tradeAlarm.setCreateTime(new Date());
			tradeAlarm.setFlag(AlarmFlag.NOSEND.value);
			tradeAlarm.setOrderNo(orderBean.getOrderNo());
			tradeAlarm.setOrderServiceTime(orderBean.getServiceTime());
			
			tradeAlarm.setSendPreTime(sserverTime);
			
			tradeAlarm.setStatus(AlarmStatus.INIT.value);
			tradeAlarm.setType(AlarmType.GUIDENOOPREATION.value);
			
			tradeAlarm.setAlarmTypeName(AlarmType.GUIDENOOPREATION.name);
			tradeAlarm.setAlarmType(AlarmType.GUIDENOOPREATION.value);
			
			int optnum = tradeAlarmMapper.insert(tradeAlarm);
			if(optnum<=0){
				throw new TradeException(TradeReturnCodeEnum.FAILED_FOR_INSERT,orderBean.getOrderNo()+ "已经到服务时间");
			}
		}catch(Exception e){
			throw new TradeException(TradeReturnCodeEnum.FAILED_FOR_INSERT,e);
		}
	}
	
	/**
	 * alarm 日志
	 * @param orderBean
	 * @param alarmType
	 * @param alarmStatus
	 * @param req
	 * @param rsp
	 */
	public void insertAlarmLog(OrderBean orderBean,AlarmType alarmType,AlarmStatus alarmStatus,String req,String rsp) {
		try{
			TradeAlarm tradeAlarm = new TradeAlarm();
			
			tradeAlarm.setCreateTime(new Date());
			tradeAlarm.setFlag(AlarmFlag.SENDED.value);
			tradeAlarm.setOrderNo(orderBean.getOrderNo());
			tradeAlarm.setOrderServiceTime(orderBean.getServiceTime());
			
			tradeAlarm.setSendTime(new Date());
			tradeAlarm.setStatus(alarmStatus.value);
			tradeAlarm.setType(alarmType.value);
			tradeAlarm.setAlarmTypeName(alarmType.name);
			tradeAlarm.setAlarmType(alarmType.value);
			
//			tradeAlarm.setReqInfo(req);
//			tradeAlarm.setRspInfo(rsp);
			int optnum = tradeAlarmMapper.insert(tradeAlarm);
			if(optnum<=0){
				throw new TradeException(TradeReturnCodeEnum.FAILED_FOR_INSERT,orderBean.getOrderNo()+ "已经到服务时间");
			}
		}catch(Exception e){
			throw new TradeException(TradeReturnCodeEnum.FAILED_FOR_INSERT,e);
		}
	}
	
	public List<TradeAlarm> getOrderAlarm(AlarmType alarmType) {
		TradeAlarmExample tradeAlarmExample = new TradeAlarmExample();
		TradeAlarmExample.Criteria criteria = tradeAlarmExample.createCriteria();
		criteria.andTypeEqualTo(alarmType.value);
		criteria.andFlagEqualTo(AlarmFlag.NOSEND.value);
		criteria.andStatusEqualTo(AlarmStatus.INIT.value);
		
		criteria.andSendPreTimeLessThan(new Date());
		return tradeAlarmMapper.selectByExample(tradeAlarmExample);
	}
	
	public int countOrderAlarmByOrderNo(String orderNo,AlarmType alarmType,AlarmStatus alarmStatus) {
		TradeAlarmExample tradeAlarmExample = new TradeAlarmExample();
		TradeAlarmExample.Criteria criteria = tradeAlarmExample.createCriteria();
		
		criteria.andStatusEqualTo(alarmStatus.value);
		criteria.andTypeEqualTo(alarmType.value);
		criteria.andOrderNoEqualTo(orderNo);
		
		return tradeAlarmMapper.countByExample(tradeAlarmExample);
	}
	
	public void updAlarmSuccess(TradeAlarm tradeAlarm,AlarmStatus alarmStatus,String req,String rsp){
		TradeAlarm tradeAlarmd = new TradeAlarm();
		tradeAlarmd.setFlag(AlarmFlag.SENDED.value);
		tradeAlarmd.setSendTime(new Date());
		tradeAlarmd.setStatus(alarmStatus.value);
		tradeAlarmd.setAlarmId(tradeAlarm.getAlarmId());
		tradeAlarmd.setSendTime(new Date());
//		tradeAlarmd.setReqInfo(req);
//		tradeAlarmd.setRspInfo(rsp);
		int optnum = tradeAlarmMapper.updateByPrimaryKeySelective(tradeAlarmd);
		if(optnum<=0){
			throw new TradeException(TradeReturnCodeEnum.FAILED_FOR_INSERT,tradeAlarm.getOrderNo()+"  "+ tradeAlarm.getAlarmId()+" DB更新失败");
		}else{
			log.info("更新提醒["+JSON.toJSONString(tradeAlarm)+"] to ["+alarmStatus.name+"]");
		}
	}
}
