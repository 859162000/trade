/**
 * @Author lukangle
 * @2015年12月14日@下午8:30:25
 */
package com.hbc.api.trade.order.service.deliver;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.hbc.api.trade.TradeFinalStr;
import com.hbc.api.trade.bdata.common.redis.RedisDao;
import com.hbc.api.trade.bdata.mapper.guide.gen.AgencyMapper;
import com.hbc.api.trade.bdata.mapper.guide.gen.bean.Agency;
import com.hbc.api.trade.bdata.mapper.guide.gen.bean.GuideBean;
import com.hbc.api.trade.order.enums.TradeReturnCodeEnum;
import com.hbc.api.trade.order.enums.deliver.OrderDeliverStatus;
import com.hbc.api.trade.order.enums.deliver.OrderSerialFlag;
import com.hbc.api.trade.order.enums.order.OrderSource;
import com.hbc.api.trade.order.enums.order.OrderStatus;
import com.hbc.api.trade.order.enums.third.GuideAgencyType;
import com.hbc.api.trade.order.exception.TradeException;
import com.hbc.api.trade.order.mapping.gen.OrderBeanMapper;
import com.hbc.api.trade.order.mapping.gen.TradeOrderSerialMapper;
import com.hbc.api.trade.order.mapping.gen.bean.OrderBean;
import com.hbc.api.trade.order.mapping.gen.bean.OrderBeanExample;
import com.hbc.api.trade.order.mapping.gen.bean.TradeOrderSerial;
import com.hbc.api.trade.order.mapping.gen.bean.TradeOrderSerialExample;
import com.hbc.api.trade.order.mapping.genx.UpdateOrderBeanMapper;
import com.hbc.api.trade.order.service.OrderLogService;
import com.hbc.api.trade.order.service.OrderTrackService;
import com.hbc.api.trade.third.LControllerService;

@Component
public class GuideAssignService {
	private final static Logger log = LoggerFactory.getLogger(GuideAssignService.class);
	
	@Autowired
	OrderBeanMapper orderBeanMapper;
	@Autowired
	UpdateOrderBeanMapper updateOrderBeanMapper;
	@Autowired
	LControllerService controllerService;
	@Autowired
	AgencyMapper agencyMapper;
	@Autowired
	OrderLogService orderLogService;
	@Autowired
	private OrderTrackService orderTrackService;
	public void setGuideOrderInfo(String guideId,OrderBean orderBean,Double guidePrice){
		GuideBean guideBean = controllerService.getGuidByGuideId(guideId);
		if (guideBean == null || guideBean.getFundAccountId().trim().length() == 0) {
			throw new TradeException(TradeReturnCodeEnum.NO_GUIDE, guideId);
		}
		orderBean.setGuideId(guideBean.getGuideId() + "");
		orderBean.setGuideMobile(guideBean.getMobile());
		orderBean.setGuideAreaCode(guideBean.getAreaCode());
		orderBean.setGuideName(guideBean.getGuideName());
		orderBean.setDeliverStatus(OrderDeliverStatus.deliversuccess.value);
		orderBean.setGuideAssignTime(new Date());
		orderBean.setDeliverAcpTime(orderBean.getGuideAssignTime());
		orderBean.setPriceGuide(guidePrice);
		orderBean.setGuideNo(guideBean.getGuideNo());
		orderBean.setGuideAreaCode(guideBean.getAreaCode());
		GuideAgencyType guideAgencyType = GuideAgencyType.getType(guideBean.getAgencyType());
		if (guideAgencyType == null) {
			log.error("导游地接社类型不存在,GuideId:" + guideBean.getGuideId());
			throw new TradeException(TradeReturnCodeEnum.GUIDE_AGENCY_TYPE_NOT_FOUND, "导游地接社类型不存在");
		} else if (GuideAgencyType.NORMAL.equals(guideAgencyType)) {
			orderBean.setGuideAgencyBossId("");
			orderBean.setGuideAgencyBossName("");
			orderBean.setGuideAgencyId("");
			orderBean.setGuideAgencyName("");
			orderBean.setGuideAgencyType(0);
		} else if (GuideAgencyType.BOOS.equals(guideAgencyType)) {
			Agency agency = agencyMapper.selectByPrimaryKey(guideBean.getAgencyId());
			if (agency != null && agency.getAgencyId() != null && agency.getAgencyId().intValue() > 0) {
				orderBean.setGuideAgencyName(agency.getAgencyName());
				orderBean.setGuideAgencyType(agency.getSendFlag());
			}
			orderBean.setGuideAgencyBossId(guideBean.getGuideId());
			orderBean.setGuideAgencyBossName(guideBean.getGuideName());
			orderBean.setGuideAgencyId(guideBean.getAgencyId() + "");
		} else if (GuideAgencyType.STAFF.equals(guideAgencyType)) {
			GuideBean boos = controllerService.getGuidByGuideId(guideBean.getAgencyBossId());
			if (boos == null || guideBean.getFundAccountId().trim().length() == 0) {
				throw new TradeException(TradeReturnCodeEnum.NO_GUIDE,"guideId:"+guideBean.getGuideId()+" "+ guideBean.getAgencyBossId());
			}
			orderBean.setGuideAgencyBossId(guideBean.getAgencyBossId());
			orderBean.setGuideAgencyBossName(boos.getGuideName());
			orderBean.setGuideAgencyId(guideBean.getAgencyId() + "");
			Agency agency = agencyMapper.selectByPrimaryKey(guideBean.getAgencyId());
			if (agency != null && agency.getAgencyId() != null && agency.getAgencyId().intValue() > 0) {
				orderBean.setGuideAgencyName(agency.getAgencyName());
				orderBean.setGuideAgencyType(agency.getSendFlag());
			}
		}
		orderBean.setGuideAccountNo(guideBean.getFundAccountId());
	    
	}
	@Autowired
	RedisDao redisDao;
	@Autowired
	TradeOrderSerialMapper tradeOrderSerialMapper;
	/**
	 * 指派导游 并且锁表
	 * @param guideId
	 * @param orderBean
	 * @param guidePrice
	 * @param orderStatus
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void assignGuide(String guideId,OrderBean orderBean,Double guidePrice,OrderStatus orderStatus){
		OrderBean record = new OrderBean();
		record.setOrderNo(orderBean.getOrderNo());
		record.setFlightRegisterId(orderBean.getFlightRegisterId());
		if (OrderSerialFlag.SERIAL.value.equals(orderBean.getSerialFlag())) {
			TradeOrderSerialExample tradeOrderSerialExample = new TradeOrderSerialExample();
			tradeOrderSerialExample.createCriteria().andMeetGuideIdEqualTo(guideId).andOrderNoEqualTo(orderBean.getOrderNo());
			List<TradeOrderSerial> slist = tradeOrderSerialMapper.selectByExample(tradeOrderSerialExample);
			if (slist.size() > 0) {
				record.setSerialFlag(orderBean.getSerialFlag());
				record.setSerialOrderNo(slist.get(0).getMeetOrderNo());
			}else{
				record.setSerialFlag(OrderSerialFlag.NORMAL.value);
				record.setSerialOrderNo("");
			}
		}
		setGuideOrderInfo(guideId,record,guidePrice);
		
		/**用于上层使用**/
		setGuideOrderInfo(guideId,orderBean,guidePrice);
		
		OrderBeanExample orderBeanExample = new OrderBeanExample();
		orderBeanExample.createCriteria().andOrderNoEqualTo(orderBean.getOrderNo()).andOrderStatusEqualTo(orderStatus.value);
		int optnum = orderBeanMapper.updateByExampleSelective(record, orderBeanExample);
		if(optnum!=1){
			throw new TradeException(TradeReturnCodeEnum.ORDER_UPDATE_FAILED, "指定导游错误");
		}
		
		if(OrderSource.getType(orderBean.getOrderSource()).equals(OrderSource.OTA)) {
			redisDao.lpush(TradeFinalStr.guideAssignQname, JSON.toJSONString(orderBean));
		}
		orderTrackService.obtainedOrder(orderBean.getOrderNo());
	}
}
