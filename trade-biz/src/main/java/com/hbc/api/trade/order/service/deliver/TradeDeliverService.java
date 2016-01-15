/**
 * @Author lukangle
 * @2015年11月9日@下午8:38:14
 */
package com.hbc.api.trade.order.service.deliver;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.hbc.api.trade.bdata.common.IDGenerotor;
import com.hbc.api.trade.order.enums.TradeReturnCodeEnum;
import com.hbc.api.trade.order.enums.deliver.DeliverPkStatus;
import com.hbc.api.trade.order.enums.deliver.DeliverType;
import com.hbc.api.trade.order.enums.deliver.OrderDeliverStatus;
import com.hbc.api.trade.order.enums.deliver.OrderSerialFlag;
import com.hbc.api.trade.order.enums.deliver.TradeDeliverStatus;
import com.hbc.api.trade.order.enums.order.OrderStatus;
import com.hbc.api.trade.order.enums.order.OrderType;
import com.hbc.api.trade.order.exception.TradeException;
import com.hbc.api.trade.order.mapping.gen.OrderBeanMapper;
import com.hbc.api.trade.order.mapping.gen.TradeOrderDeliverMapper;
import com.hbc.api.trade.order.mapping.gen.bean.OrderBean;
import com.hbc.api.trade.order.mapping.gen.bean.OrderBeanExample;
import com.hbc.api.trade.order.mapping.gen.bean.TradeOrderDeliver;
import com.hbc.api.trade.order.mapping.gen.bean.TradeOrderDeliverExample;
import com.hbc.api.trade.order.mapping.genx.deliver.GxDeliverOrderMapper;
import com.hbc.api.trade.order.mapping.genx.deliver.WDOrderMapper;
import com.hbc.api.trade.order.service.OrderServiceTime;
@Component
public class TradeDeliverService {
	private final static Logger log = LoggerFactory.getLogger(TradeDeliverService.class);
	@Autowired
	TradeOrderDeliverMapper tradeOrderDeliverMapper;
	
	@Autowired
	OrderServiceTime orderServiceTime;
	@Autowired
	WDOrderMapper woOrderDeliverMapper;
	@Autowired
	GxDeliverOrderMapper gxDeliverOrderMapper;
	/**
	 * 
	 * @param orderBean
	 * @param tradeDeliverStatus deliver order 表 发单状态
	 * @param deliverType  发单类型
	 * @return
	 */
	@Transactional
	public TradeOrderDeliver insertDeliverOrder(OrderBean orderBean,TradeDeliverStatus tradeDeliverStatus,DeliverType deliverType,Timestamp deliverTime){
		TradeOrderDeliver tradeOrderDeliver = convertToDeliver(orderBean,tradeDeliverStatus,deliverType,deliverTime);
		log.info(tradeOrderDeliver.getOrderNo()+" success to insert ["+JSON.toJSONString(tradeOrderDeliver)+"]");
		int optnum = tradeOrderDeliverMapper.insert(tradeOrderDeliver);
		if(optnum!=1){
			throw new TradeException(TradeReturnCodeEnum.ORDER_DELIVER_INSERT_DB_FAILED,orderBean.getOrderNo());
		}
		return tradeOrderDeliver;
	}
	private TradeOrderDeliver convertToDeliver(OrderBean orderBean,TradeDeliverStatus tradeDeliverStatus,DeliverType deliverType,Timestamp deliverTime){
		TradeOrderDeliver tradeOrderDeliver = new TradeOrderDeliver();
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		tradeOrderDeliver.setCreateTime(timestamp);
		tradeOrderDeliver.setDeliverNo("DLI"+IDGenerotor.generateDeliverNo());
		tradeOrderDeliver.setDeliverStatus(tradeDeliverStatus.value);
		tradeOrderDeliver.setDeliverStatusName(tradeDeliverStatus.name);
		tradeOrderDeliver.setDeliverType(deliverType.value);
		tradeOrderDeliver.setDeliverTypeName(deliverType.name);
		tradeOrderDeliver.setDeliverTime(deliverTime);
		tradeOrderDeliver.setOrderNo(orderBean.getOrderNo());
		tradeOrderDeliver.setCityId(orderBean.getServiceCityId());
		tradeOrderDeliver.setPkStatus(DeliverPkStatus.INIT.value);
		return tradeOrderDeliver;
	}
	
	/**
	 * 获取发单批次
	 * @param tradeDeliverStatus
	 * @param deliverType
	 * @return
	 */
	public List<TradeOrderDeliver> getTradeDelivers(TradeDeliverStatus tradeDeliverStatus,DeliverType deliverType){
		TradeOrderDeliverExample tradeOrderDeliverExample = new TradeOrderDeliverExample();
		TradeOrderDeliverExample.Criteria criteria = tradeOrderDeliverExample.createCriteria();
		
		criteria.andDeliverStatusEqualTo(tradeDeliverStatus.value);
		criteria.andDeliverTypeEqualTo(deliverType.value);
		
		return tradeOrderDeliverMapper.selectByExample(tradeOrderDeliverExample);
	}
	/**
	 * pk查找
	 * @param tradeDeliverStatus
	 * @param deliverTypes
	 * @return
	 */
	public List<TradeOrderDeliver> getTradeDeliversForPk(List<Integer> deliverTypes){
		

		StringBuffer dtypes = new StringBuffer();
		for(int i=0;i<deliverTypes.size();i++){
			if(i==0){
				dtypes.append(deliverTypes.get(i));
			}else{
				dtypes.append(","+deliverTypes.get(i));
			}
		}
		List<TradeOrderDeliver> dlist = gxDeliverOrderMapper.getForPkDelivers(dtypes.toString());
		Map orderMap = new HashMap();
		for (TradeOrderDeliver tradeOrderDeliver : dlist) {
			if (tradeOrderDeliver != null) {
				if (orderMap.containsKey(tradeOrderDeliver.getOrderNo())) {
					TradeOrderDeliver temp = (TradeOrderDeliver) orderMap.get(tradeOrderDeliver.getOrderNo());
					if (temp != null) {
						if (tradeOrderDeliver.getDeliverTime() != null && temp.getDeliverTime() != null && tradeOrderDeliver.getDeliverTime().after(temp.getDeliverTime())) {
							orderMap.put(tradeOrderDeliver.getOrderNo(), tradeOrderDeliver);
						} else if (temp.getDeliverTime() == null) {
							orderMap.put(tradeOrderDeliver.getOrderNo(), tradeOrderDeliver);
						}
					}
				} else {
					orderMap.put(tradeOrderDeliver.getOrderNo(), tradeOrderDeliver);
				}
			}
		}

		List<TradeOrderDeliver> tlist = new ArrayList<TradeOrderDeliver>();
		for (Object obj : orderMap.keySet()) {

			TradeOrderDeliver tradeOrderDeliver = (TradeOrderDeliver) orderMap.get(obj);
			TradeDeliverStatus tradeDeliverStatus = TradeDeliverStatus.getType(tradeOrderDeliver.getDeliverStatus());
			DeliverType deliverType = DeliverType.getType(tradeOrderDeliver.getDeliverType());
			if (TradeDeliverStatus.delivered.equals(tradeDeliverStatus)) {
				tlist.add(tradeOrderDeliver);
			} else if (TradeDeliverStatus.pkend.equals(tradeDeliverStatus) && DeliverType.Cancle_Send.equals(deliverType)) {
				tlist.add(tradeOrderDeliver);
				// log.info("发单批次"+tradeOrderDeliver.getDeliverNo()+" 被兼容，参与pk行列");
			}
		}
//		List<TradeOrderDeliver> tlist = new ArrayList<TradeOrderDeliver>();
//		for(TradeOrderDeliver tradeOrderDeliver : dlist){
//			TradeDeliverStatus tradeDeliverStatus = TradeDeliverStatus.getType(tradeOrderDeliver.getDeliverStatus());
//			DeliverType deliverType = DeliverType.getType(tradeOrderDeliver.getDeliverType());
//			if(TradeDeliverStatus.delivered.equals(tradeDeliverStatus)){
//				tlist.add(tradeOrderDeliver);
//			}else if(TradeDeliverStatus.pkend.equals(tradeDeliverStatus) && DeliverType.Cancle_Send.equals(deliverType)){
//				tlist.add(tradeOrderDeliver);
////				log.info("发单批次"+tradeOrderDeliver.getDeliverNo()+" 被兼容，参与pk行列");
//			}
//		}
		return tlist;
	}
	public List<TradeOrderDeliver> getTradeDelivers(List<Integer> tradeDeliverStatuss,DeliverType deliverType){
		TradeOrderDeliverExample tradeOrderDeliverExample = new TradeOrderDeliverExample();
		TradeOrderDeliverExample.Criteria criteria = tradeOrderDeliverExample.createCriteria();
		if(tradeDeliverStatuss != null && tradeDeliverStatuss.size() > 0) {
			criteria.andDeliverStatusIn(tradeDeliverStatuss);
		}
		criteria.andDeliverTypeEqualTo(deliverType.value);		
		return tradeOrderDeliverMapper.selectByExample(tradeOrderDeliverExample);
	}
	
	public void updateStatus(String deliverNo,TradeDeliverStatus dbDeliverStatus,TradeDeliverStatus targetDeliverStatus,Timestamp deliverTime){
		int optNum =gxDeliverOrderMapper.updateDeliverStatusDtime(deliverNo, dbDeliverStatus.value, targetDeliverStatus.value,targetDeliverStatus.name,deliverTime);
		if(optNum!=1){
			throw new TradeException(TradeReturnCodeEnum.FAILED_FOR_UPDATE,deliverNo);
		}
	}
	
	public void updateStatus(String deliverNo,TradeDeliverStatus dbDeliverStatus,TradeDeliverStatus targetDeliverStatus){
		int optNum =gxDeliverOrderMapper.updateDeliverStatus(deliverNo, dbDeliverStatus.value, targetDeliverStatus.value,targetDeliverStatus.name);
		if(optNum!=1){
			throw new TradeException(TradeReturnCodeEnum.FAILED_FOR_UPDATE,deliverNo);
		}
	}
	/**
	 * 更新发包时间
	 * @param deliverNo
	 * @param deliverTime
	 */
	public void updateDeliverTimeAndCount(String deliverNo,Timestamp deliverTime,int dcount){
		int optNum =gxDeliverOrderMapper.updateDeliverTimeAndCount(deliverNo, deliverTime,dcount);
		if(optNum!=1){
			throw new TradeException(TradeReturnCodeEnum.FAILED_FOR_UPDATE,deliverNo);
		}
	}
	/**
	 * 更新PK时间
	 * @param deliverNo
	 */
	public void updatePkTime(String deliverNo){
		Timestamp pktime = new Timestamp(System.currentTimeMillis());
		int optNum =gxDeliverOrderMapper.updatePktime(deliverNo, pktime);
		if(optNum!=1){
			throw new TradeException(TradeReturnCodeEnum.FAILED_FOR_UPDATE,deliverNo);
		}
	}
	
	public void updatePkStatus(String deliverNo,DeliverPkStatus deliverPkStatus){
		int optNum = gxDeliverOrderMapper.updatePkStatus(deliverNo, deliverPkStatus.value,deliverPkStatus.name);
		if(optNum!=1){
			throw new TradeException(TradeReturnCodeEnum.FAILED_FOR_UPDATE,deliverNo);
		}
	}
	
	public void updateDeliverTime(String deliverNo,Timestamp deliverTime,int deliverCount){
		int optNum = gxDeliverOrderMapper.updateDeliverTime(deliverNo, deliverTime,deliverCount);
		if(optNum!=1){
			throw new TradeException(TradeReturnCodeEnum.FAILED_FOR_UPDATE,deliverNo);
		}
	}
	
	public void updateIncrementDeliver(String deliverNo,Timestamp deliverTime,int deliverCount,TradeDeliverStatus deliverStatus){
		int optNum = gxDeliverOrderMapper.updateIncrementDeliver(deliverNo, deliverTime,deliverCount,deliverStatus.value,deliverStatus.name);
		if(optNum!=1){
			throw new TradeException(TradeReturnCodeEnum.FAILED_FOR_UPDATE,deliverNo);
		}
	}
	
	
	@Autowired
	OrderBeanMapper orderBeanMapper;
	/**
	 * 获取有可能串单的订单
	 * @param placeCityId
	 * @param orderType
	 * @param startServiceTime
	 * @param endServiceTime
	 * @return
	 */
	public List<OrderBean> getOrders(int placeCityId,OrderType orderType,Date startServiceTime,Date endServiceTime){
		
		OrderBeanExample orderBeanExample = new OrderBeanExample();
		OrderBeanExample.Criteria criteria = orderBeanExample.createCriteria();
		
		criteria.andOrderTypeEqualTo(orderType.value);
		criteria.andServiceCityIdEqualTo(placeCityId);
		criteria.andOrderStatusEqualTo(OrderStatus.PAYSUCCESS.value);
		criteria.andDeliverStatusEqualTo(OrderDeliverStatus.deliversuccess.value);
		criteria.andServiceTimeBetween(startServiceTime, endServiceTime);
		criteria.andGuideIdIsNotNull();
		criteria.andGuideIdNotEqualTo("0");
		return orderBeanMapper.selectByExample(orderBeanExample);
	}
	
	/**
	 * 获取指定导游可能串单的订单
	 * @param placeCityId
	 * @param orderType
	 * @param startServiceTime
	 * @param endServiceTime
	 * @param guideids
	 * @return
	 */
	public List<OrderBean> getOrders(int placeCityId,OrderType orderType,Date startServiceTime,Date endServiceTime,List<String> guideids,String airCode){
		
		OrderBeanExample orderBeanExample = new OrderBeanExample();
		OrderBeanExample.Criteria criteria = orderBeanExample.createCriteria();
		
		criteria.andOrderTypeEqualTo(orderType.value);
		criteria.andServiceCityIdEqualTo(placeCityId);
		criteria.andOrderStatusEqualTo(OrderStatus.PAYSUCCESS.value);
		criteria.andDeliverStatusEqualTo(OrderDeliverStatus.deliversuccess.value);
		criteria.andServiceTimeBetween(startServiceTime, endServiceTime);
		if(OrderType.PICKUPORDER.value.equals(orderType.value)){
			criteria.andFlightDestCodeEqualTo(airCode);
		}else if(OrderType.TRANSFER.value.equals(orderType.value)){
			criteria.andFlightAirportCodeEqualTo(airCode);
		}
//		criteria.andGuideIdIsNotNull();
//		criteria.andGuideIdNotEqualTo("0");
		criteria.andGuideIdIn(guideids);
		criteria.andSerialFlagEqualTo(OrderSerialFlag.NORMAL.value);
		return orderBeanMapper.selectByExample(orderBeanExample);
	}
	
	/**
	 * 获取订单跟踪
	 * @param orderStatus
	 * @return
	 */
	public List<OrderBean> getTrackOrders(List<Integer> orderStatus){
		OrderBeanExample orderBeanExample = new OrderBeanExample();
		OrderBeanExample.Criteria criteria = orderBeanExample.createCriteria();
		if(orderStatus != null && orderStatus.size() > 0) {
			criteria.andOrderStatusIn(orderStatus);
		}
		return orderBeanMapper.selectByExample(orderBeanExample);
	}
	
	/**
	 * PK 成功后更新trade_deliver
	 * @param deliverNo
	 * @param pk_status
	 * @param pkTime
	 * @param deliver_status
	 * @param target_deliver_status
	 * @return
	 */
	public int updatePkDeliverSuccess(TradeOrderDeliver tradeOrderDeliver,Timestamp pkTime){
		//更新其他发单批次为失败
		TradeOrderDeliverExample tradeOrderDeliverExample = new TradeOrderDeliverExample();
		TradeOrderDeliverExample.Criteria criteria  = tradeOrderDeliverExample.createCriteria();
		criteria.andOrderNoEqualTo(tradeOrderDeliver.getOrderNo());
		criteria.andDeliverNoNotEqualTo(tradeOrderDeliver.getDeliverNo());
		TradeOrderDeliver tagTradeOrderDeliver = new TradeOrderDeliver();
		tagTradeOrderDeliver.setDeliverStatus(TradeDeliverStatus.deliverfailed.value);
		tagTradeOrderDeliver.setDeliverStatusName(TradeDeliverStatus.deliverfailed.name);
		tagTradeOrderDeliver.setPkStatus(DeliverPkStatus.PK_FAILED.value);
		tagTradeOrderDeliver.setPkStatusName(DeliverPkStatus.PK_FAILED.name);
		tradeOrderDeliverMapper.updateByExampleSelective(tagTradeOrderDeliver, tradeOrderDeliverExample);
		
		
		//更新本批次订单为成功
		
		TradeOrderDeliverExample tradeOrderDeliverExampleLa = new TradeOrderDeliverExample();
		TradeOrderDeliverExample.Criteria lacriteria  = tradeOrderDeliverExampleLa.createCriteria();
		lacriteria.andDeliverNoEqualTo(tradeOrderDeliver.getDeliverNo());
		TradeOrderDeliver successTradeOrderDeliver = new TradeOrderDeliver();
		successTradeOrderDeliver.setDeliverStatus(TradeDeliverStatus.pkend.value);
		successTradeOrderDeliver.setDeliverStatusName(TradeDeliverStatus.pkend.name);
		successTradeOrderDeliver.setPkStatus(DeliverPkStatus.PK_SUCCESS.value);
		successTradeOrderDeliver.setPkStatusName(DeliverPkStatus.PK_SUCCESS.name);
		successTradeOrderDeliver.setPkTime(pkTime);
		int optNum = tradeOrderDeliverMapper.updateByExampleSelective(successTradeOrderDeliver, tradeOrderDeliverExampleLa);
		
		if(optNum!=1){
			throw new TradeException(TradeReturnCodeEnum.FAILED_FOR_UPDATE,tradeOrderDeliver.getDeliverNo());
		}
		return optNum;
	}
	
}
