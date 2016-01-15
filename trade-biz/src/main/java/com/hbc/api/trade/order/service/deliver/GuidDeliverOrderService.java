/**
 * @Author lukangle
 * @2015年10月26日@下午4:03:36
 */
package com.hbc.api.trade.order.service.deliver;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.hbc.api.trade.TradeFinalStr;
import com.hbc.api.trade.bdata.common.IDGenerotor;
import com.hbc.api.trade.bdata.mapper.guide.gen.bean.GuideBean;
import com.hbc.api.trade.order.enums.TradeReturnCodeEnum;
import com.hbc.api.trade.order.enums.deliver.DeliverDemandDeal;
import com.hbc.api.trade.order.enums.deliver.DeliverPKFailType;
import com.hbc.api.trade.order.enums.deliver.DeliverType;
import com.hbc.api.trade.order.enums.deliver.DemandDeal;
import com.hbc.api.trade.order.enums.deliver.GuidDeliverStatus;
import com.hbc.api.trade.order.enums.deliver.IsRead;
import com.hbc.api.trade.order.enums.deliver.IsReadable;
import com.hbc.api.trade.order.enums.order.OrderStatus;
import com.hbc.api.trade.order.exception.TradeException;
import com.hbc.api.trade.order.mapping.gen.TradeDeliverGuideMapper;
import com.hbc.api.trade.order.mapping.gen.TradeOrderDeliverMapper;
import com.hbc.api.trade.order.mapping.gen.bean.OrderBean;
import com.hbc.api.trade.order.mapping.gen.bean.TradeDeliverGuide;
import com.hbc.api.trade.order.mapping.gen.bean.TradeDeliverGuideExample;
import com.hbc.api.trade.order.mapping.gen.bean.TradeOrderDeliver;
import com.hbc.api.trade.order.mapping.gen.bean.TradeOrderDeliverExample;
import com.hbc.api.trade.order.mapping.genx.deliver.GxDeliverOrderMapper;
import com.hbc.api.trade.order.mapping.genx.deliver.GxGuideDeliverMapper;
import com.hbc.api.trade.order.mapping.genx.deliver.WDOrderMapper;
import com.hbc.api.trade.order.service.OrderQueryService;
import com.hbc.api.trade.order.service.UrgentFlagService;
/**
 * 对guide order 表操作
 */
@Component
public class GuidDeliverOrderService {
	private final static Logger log = LoggerFactory.getLogger(GuidDeliverOrderService.class);
	@Autowired
	TradeDeliverGuideMapper tradeDeliverGuidMapper;
	
	@Autowired
	TradeOrderDeliverMapper tradeOrderDeliverMapper;
	@Autowired
	UrgentFlagService urgentFlagService;
	@Autowired
	WDOrderMapper wDOrderMapper;
	@Autowired
	GxDeliverOrderMapper gxDeliverOrderMapper;
	@Autowired
	OrderQueryService orderQueryService;
	@Autowired
	GxGuideDeliverMapper gxGuideDeliverMapper;
	@Autowired
	TradeDeliverGuideMapper tradeDeliverGuideMapper;
	/**
	 * 
	 * @param tradeOrderDeliver
	 * @param guideBean
	 * @param batchNo
	 * @param orderBean
	 * @throws ParseException
	 */
	@Transactional
	public TradeDeliverGuide insertToGuide(TradeOrderDeliver tradeOrderDeliver, GuideBean guideBean, OrderBean orderBean, double guidePrice,DeliverDemandDeal deliverDemandDeal, GuidDeliverStatus guidDeliverStatus, DeliverType deliverType) {

		TradeDeliverGuide tradeDeliverGuid = new TradeDeliverGuide();
		Timestamp curtime = new Timestamp(System.currentTimeMillis());
		tradeDeliverGuid.setCreateTime(curtime);
		tradeDeliverGuid.setDeliverNo(tradeOrderDeliver.getDeliverNo());
		tradeDeliverGuid.setOrderNo(orderBean.getOrderNo());

		tradeDeliverGuid.setGuideId(guideBean.getGuideId() + "");
		tradeDeliverGuid.setGuideName(guideBean.getGuideName());
		tradeDeliverGuid.setGuideNo(guideBean.getGuideNo());
		tradeDeliverGuid.setDeliverType(deliverType.value);
		tradeDeliverGuid.setDeliverTypeName(deliverType.name);
		tradeDeliverGuid.setDemandDeal(deliverDemandDeal.value);

		tradeDeliverGuid.setDeliverStatus(guidDeliverStatus.value);
		tradeDeliverGuid.setDeliverStatusName(guidDeliverStatus.name);
		tradeDeliverGuid.setAllocatGno("DLG" + IDGenerotor.genDeliverGuidNo());
		tradeDeliverGuid.setGuidePrice(guidePrice);
		tradeDeliverGuid.setIsReadable(IsReadable.VISIABLE.value);
		tradeDeliverGuid.setIsRead(IsRead.INITIAL.value);
		
		tradeDeliverGuid.setServiceTime(orderBean.getServiceTime());
		/**
		 * 将所有的 该导游的新订单 重置成hidden
		 */
		int optnum = gxGuideDeliverMapper.updatePreviousDelivers(guideBean.getGuideId(), orderBean.getOrderNo(), IsReadable.VISIABLE.value,IsReadable.HIDDEN.value);
		if(optnum>0){
			log.info("guide id ["+guideBean.getGuideId()+"] orderNo ["+orderBean.getOrderNo()+"] hidden ["+optnum+"] 条旧历史信息");
		}
		int allocatGid = tradeDeliverGuideMapper.insert(tradeDeliverGuid);
		if (allocatGid == 1) {
			return tradeDeliverGuid;
		} else {
			throw new TradeException(TradeReturnCodeEnum.ORDER_DELIVER_INSERT_DB_FAILED, orderBean.getOrderNo());
		}
	}
	@Transactional
	public void updateOthers(TradeDeliverGuide tradeDeliverGuid){
		TradeDeliverGuideExample tradeDeliverGuideExample = new TradeDeliverGuideExample();
		TradeDeliverGuideExample.Criteria cdriteria = tradeDeliverGuideExample.createCriteria();
		String orderNo = tradeDeliverGuid.getOrderNo();
		
		cdriteria.andOrderNoEqualTo(orderNo);
		cdriteria.andIsReadableEqualTo(IsReadable.VISIABLE.value);
		List<TradeDeliverGuide> glist = tradeDeliverGuideMapper.selectByExample(tradeDeliverGuideExample);
		
		boolean isUpdate = false;
		
		for(TradeDeliverGuide tradeDeliverGuide : glist){
			GuidDeliverStatus guidDeliverStatus = GuidDeliverStatus.getType(tradeDeliverGuide.getDeliverStatus());
			if(GuidDeliverStatus.success.equals(guidDeliverStatus) || GuidDeliverStatus.successsend.equals(guidDeliverStatus)){
				isUpdate = true;
				log.info(orderNo+" GUIDENO【"+tradeDeliverGuide.getAllocatGno()+"】 已经成功 ，可以更新");
				break;
			}
		}
		
		if(isUpdate){
			for(TradeDeliverGuide tradeDeliverGuide : glist){
				GuidDeliverStatus guidDeliverStatus = GuidDeliverStatus.getType(tradeDeliverGuide.getDeliverStatus());
				
				if(GuidDeliverStatus.nosend.equals(guidDeliverStatus) || GuidDeliverStatus.sendpush.equals(guidDeliverStatus)){
					DemandDeal demandDeal = DemandDeal.INITIAL ;
					if(tradeDeliverGuide.getDemandDeal()!=null){
						demandDeal = DemandDeal.getType(tradeDeliverGuide.getDemandDeal());
					}
					
					TradeDeliverGuide tradeDeliverGuidDbOther = new TradeDeliverGuide();
					tradeDeliverGuidDbOther.setAllocatGno(tradeDeliverGuide.getAllocatGno());
					
					if(DemandDeal.AGREE.equals(demandDeal)){
						tradeDeliverGuidDbOther.setDeliverStatus(GuidDeliverStatus.pkfailed.value);
						tradeDeliverGuidDbOther.setDeliverStatusName(GuidDeliverStatus.pkfailed.name);
					}else{
						tradeDeliverGuidDbOther.setDeliverStatus(GuidDeliverStatus.pkfailedPush.value);
						tradeDeliverGuidDbOther.setDeliverStatusName(GuidDeliverStatus.pkfailedPush.name);
					}
					int updNum = tradeDeliverGuideMapper.updateByPrimaryKeySelective(tradeDeliverGuidDbOther);
					if (updNum ==1) {
						log.info("发单记录【"+tradeDeliverGuidDbOther.getAllocatGno()+"】 订单号【"+tradeDeliverGuide.getOrderNo()+"】 更新【"+updNum+"】条已发送导游订单为 pk失败");
					}else{
						log.info("失败  发单记录【"+tradeDeliverGuidDbOther.getAllocatGno()+"】 订单号【"+tradeDeliverGuide.getOrderNo()+"】 更新【"+updNum+"】条已发送导游订单为 pk失败");
						throw new TradeException(TradeReturnCodeEnum.ORDER_GUIDE_DELIVER_UPDATE_DB_FAILED, tradeDeliverGuid.getAllocatGno());
					}
				}
			}
		}
		
	}
	@Transactional
	public void pkSuccess(TradeDeliverGuide tradeDeliverGuid){
		TradeDeliverGuide tradeDeliverGuidDb = new TradeDeliverGuide();
		tradeDeliverGuidDb.setDeliverStatus(GuidDeliverStatus.success.value);
		tradeDeliverGuidDb.setDeliverStatusName(GuidDeliverStatus.success.name);
		
		TradeDeliverGuideExample tradeDeliverGuideExample = new TradeDeliverGuideExample();
		tradeDeliverGuideExample.createCriteria().andAllocatGnoEqualTo(tradeDeliverGuid.getAllocatGno());
		int updNum = tradeDeliverGuidMapper.updateByExampleSelective(tradeDeliverGuidDb, tradeDeliverGuideExample);
		
		if (updNum != 1) {
			throw new TradeException(TradeReturnCodeEnum.ORDER_GUIDE_DELIVER_UPDATE_DB_FAILED, tradeDeliverGuid.getAllocatGno());
		}
		updateOthers(tradeDeliverGuid);
	}
	@Transactional
	public void updateOtherFailed(OrderBean orderBean){
		OrderStatus orderStatus = OrderStatus.getStatus(orderBean.getOrderStatus());
		
		if(!TradeFinalStr.defaultGuideId.equals(orderBean.getGuideId()) || !OrderStatus.PAYSUCCESS.equals(orderStatus)){
			TradeDeliverGuideExample successExamp = new TradeDeliverGuideExample();
			successExamp.createCriteria().andOrderNoEqualTo(orderBean.getOrderNo());
			List<TradeDeliverGuide> tlist = tradeDeliverGuidMapper.selectByExample(successExamp);
			
			for(TradeDeliverGuide tradeDeliverGuide : tlist){
				GuidDeliverStatus guidDeliverStatus = GuidDeliverStatus.getType(tradeDeliverGuide.getDeliverStatus());
				if(GuidDeliverStatus.nosend.equals(guidDeliverStatus) || GuidDeliverStatus.sendpush.equals(guidDeliverStatus)){
					DemandDeal demandDeal = DemandDeal.INITIAL ;
					if(tradeDeliverGuide.getDemandDeal()!=null){
						demandDeal = DemandDeal.getType(tradeDeliverGuide.getDemandDeal());
					}
					
					TradeDeliverGuide tradeDeliverGuidDbOther = new TradeDeliverGuide();
					tradeDeliverGuidDbOther.setAllocatGno(tradeDeliverGuide.getAllocatGno());
					
					if(DemandDeal.AGREE.equals(demandDeal)){
						tradeDeliverGuidDbOther.setDeliverStatus(GuidDeliverStatus.pkfailed.value);
						tradeDeliverGuidDbOther.setDeliverStatusName(GuidDeliverStatus.pkfailed.name);
					}else{
						tradeDeliverGuidDbOther.setDeliverStatus(GuidDeliverStatus.pkfailedPush.value);
						tradeDeliverGuidDbOther.setDeliverStatusName(GuidDeliverStatus.pkfailedPush.name);
					}
					int updNum = tradeDeliverGuidMapper.updateByPrimaryKeySelective(tradeDeliverGuidDbOther);
					if (updNum >=1) {
						log.info("发单记录【"+tradeDeliverGuidDbOther.getAllocatGno()+"】 订单号【"+tradeDeliverGuide.getOrderNo()+"】 更新【"+updNum+"】条已发送导游订单为 pk失败");
					}else{
						throw new TradeException(TradeReturnCodeEnum.ORDER_UPDATE_FAILED, "PK更新DB错误");
					}
				}else{
					log.info("发单记录【"+tradeDeliverGuide.getAllocatGno()+"】 订单号【"+tradeDeliverGuide.getOrderNo()+"】 无须更新，状态为【"+guidDeliverStatus.name+"】");
				}
			}
			
			
//			TradeDeliverGuide tradeDeliverGuidDbOther = new TradeDeliverGuide();
//			tradeDeliverGuidDbOther.setDeliverStatus(GuidDeliverStatus.pkfailed.value);
//			tradeDeliverGuidDbOther.setDeliverStatusName(GuidDeliverStatus.pkfailed.name);
//			
//			List<Integer> destatus = new ArrayList<Integer>();
//			destatus.add(GuidDeliverStatus.success.value);
//			destatus.add(GuidDeliverStatus.successsend.value);
//			destatus.add(GuidDeliverStatus.pkfailed.value);
//			destatus.add(GuidDeliverStatus.pkfailedPush.value);
//			TradeDeliverGuideExample tradeDeliverGuideExampleOther = new TradeDeliverGuideExample();
//			tradeDeliverGuideExampleOther.createCriteria().andOrderNoEqualTo(orderBean.getOrderNo())
//			.andGuideIdNotEqualTo(orderBean.getGuideId())
//			.andDeliverStatusNotIn(destatus);
//			int updNum = tradeDeliverGuidMapper.updateByExampleSelective(tradeDeliverGuidDbOther, tradeDeliverGuideExampleOther);
//			if (updNum >=1) {
//				log.info("订单["+orderBean.getOrderNo()+"] 更新["+updNum+"]条已发送导游订单为 pk失败");
//			}
			
		}else{
			log.info("无指派导游无须 对其他订单进行 失败操作");
		}
	}
	/**
	 * @param tradeDeliverGuid
	 */
	public void updateGuideDeliverStatus(TradeDeliverGuide tradeDeliverGuid, GuidDeliverStatus dbguidDeliverStatus,GuidDeliverStatus guidDeliverStatus) {

		int opnum = gxGuideDeliverMapper.updateDeliverStatus(tradeDeliverGuid.getAllocatGno(), dbguidDeliverStatus.value, guidDeliverStatus.value,guidDeliverStatus.name);
		if (opnum != 1) {
			throw new TradeException(TradeReturnCodeEnum.ORDER_GUIDE_DELIVER_UPDATE_DB_FAILED, tradeDeliverGuid.getAllocatGno());
		}
	}
	
	/**
	 * 
	 * @param allocatGno 不更新的id
	 * @param deliverNo 发单批次号
	 * @param dbGuidDeliverStatus db中的发单状态
	 * @param guidDeliverStatus 要更新成的发单状态
	 */
	public int updateAllBachNoDeliverStatus(String deliverNo,GuidDeliverStatus dbGuidDeliverStatus,GuidDeliverStatus guidDeliverStatus){
		int opnum = gxGuideDeliverMapper.updateDeliverStatusByDeliverNo(deliverNo, dbGuidDeliverStatus.value, guidDeliverStatus.value,guidDeliverStatus.name);
		return opnum;
	}
	/**
	 * 获取 所有已经表态的导游 用于PK
	 * @param orderBean
	 * @param tradeOrderDeliver
	 * @return
	 */
	public List<TradeDeliverGuide> getAllAcceptGuideOrders(String orderNo){
		TradeDeliverGuideExample tradeDeliverGuideExample = new TradeDeliverGuideExample();
		TradeDeliverGuideExample.Criteria criteria = tradeDeliverGuideExample.createCriteria();
		criteria.andOrderNoEqualTo(orderNo);
		criteria.andDemandDealEqualTo(DeliverDemandDeal.accept.value);
//		criteria.andDeliverStatusEqualTo(GuidDeliverStatus.sendpush.value);
		List<TradeDeliverGuide> glist =  tradeDeliverGuidMapper.selectByExample(tradeDeliverGuideExample);
		
		List<TradeDeliverGuide> dlist = new ArrayList<TradeDeliverGuide>();
		
		TradeOrderDeliverExample tradeOrderDeliverExample = new TradeOrderDeliverExample();
		tradeOrderDeliverExample.createCriteria().andOrderNoEqualTo(orderNo);
		List<TradeOrderDeliver> orderDeliveList = tradeOrderDeliverMapper.selectByExample(tradeOrderDeliverExample);
		
		boolean ischeck = false;
		if(orderDeliveList.size()==1 && orderDeliveList.get(0).getPkStatus()==0){
			ischeck = true;
		}
		for(TradeDeliverGuide tradeDeliverGuide : glist){
			
			GuidDeliverStatus guidDeliverStatus = GuidDeliverStatus.getType(tradeDeliverGuide.getDeliverStatus());
			if(guidDeliverStatus.equals(GuidDeliverStatus.pkfailedPush)&& ischeck){
				
				tradeDeliverGuide.setDeliverStatus(GuidDeliverStatus.sendpush.value);
				tradeDeliverGuide.setDeliverStatusName(GuidDeliverStatus.sendpush.name);
				
				TradeDeliverGuide dguide = new TradeDeliverGuide();
				dguide.setAllocatGno(tradeDeliverGuide.getAllocatGno());
				dguide.setDeliverStatus(GuidDeliverStatus.sendpush.value);
				dguide.setDeliverStatusName(GuidDeliverStatus.sendpush.name);
				
				int optnum = tradeDeliverGuidMapper.updateByPrimaryKeySelective(dguide);
				if(optnum==1){
					log.info(tradeDeliverGuide.getAllocatGno()+" 已经修改DB成 pk中");
				}
				dlist.add(tradeDeliverGuide);
				
				log.info(tradeDeliverGuide.getAllocatGno()+" 已经修改成 pk中");
				
			}else if(guidDeliverStatus.equals(GuidDeliverStatus.sendpush)){
				dlist.add(tradeDeliverGuide);
			}
		}
		
		return dlist;
	}
	/**
	 * 获取响应时间最快的 导游
	 * @param orderBean
	 * @param tradeOrderDeliver
	 * @return
	 */
	public List<TradeDeliverGuide> getRspTimeAcceptGuideOrder(String orderNo){
		TradeDeliverGuideExample tradeDeliverGuideExample = new TradeDeliverGuideExample();
		TradeDeliverGuideExample.Criteria criteria = tradeDeliverGuideExample.createCriteria();
		criteria.andOrderNoEqualTo(orderNo);
		criteria.andDemandDealEqualTo(DeliverDemandDeal.accept.value);
		tradeDeliverGuideExample.setOrderByClause(" accept_time asc ");
		return tradeDeliverGuidMapper.selectByExample(tradeDeliverGuideExample);
	}
	/**
	 * 获取未发 push的所有guide order
	 * @param orderBean
	 * @param tradeOrderDeliver
	 * @return
	 */
	public List<TradeDeliverGuide> getNoPushGuideOrders(OrderBean orderBean,TradeOrderDeliver tradeOrderDeliver){
		TradeDeliverGuideExample tradeDeliverGuideExample = new TradeDeliverGuideExample();
		TradeDeliverGuideExample.Criteria criteria = tradeDeliverGuideExample.createCriteria();
		criteria.andDeliverNoEqualTo(tradeOrderDeliver.getDeliverNo());
		criteria.andOrderNoEqualTo(orderBean.getOrderNo());
		criteria.andDemandDealEqualTo(DeliverDemandDeal.init.value);
		criteria.andDeliverStatusEqualTo(GuidDeliverStatus.nosend.value);
		return tradeDeliverGuidMapper.selectByExample(tradeDeliverGuideExample);
	}
	
	/**
	 * 获取已发导游
	 * @param orderBean
	 * @param tradeOrderDeliver
	 * @return
	 */
	public List<TradeDeliverGuide> getGuideOrdersByDeliverNo(OrderBean orderBean,TradeOrderDeliver tradeOrderDeliver){
		TradeDeliverGuideExample tradeDeliverGuideExample = new TradeDeliverGuideExample();
		TradeDeliverGuideExample.Criteria criteria = tradeDeliverGuideExample.createCriteria();
		criteria.andDeliverNoEqualTo(tradeOrderDeliver.getDeliverNo());
		criteria.andOrderNoEqualTo(orderBean.getOrderNo());		
		return tradeDeliverGuidMapper.selectByExample(tradeDeliverGuideExample);
	}
	
	/**
	 * 根据订单号查找发单导游列表
	 * @param orderBean
	 * @return
	 */
	public List<TradeDeliverGuide> getGuideOrdersByOrderNo(OrderBean orderBean){
		TradeDeliverGuideExample tradeDeliverGuideExample = new TradeDeliverGuideExample();
		TradeDeliverGuideExample.Criteria criteria = tradeDeliverGuideExample.createCriteria();		
		criteria.andOrderNoEqualTo(orderBean.getOrderNo());		
		return tradeDeliverGuidMapper.selectByExample(tradeDeliverGuideExample);
	}
	
	/**
	 * 获取非本批次发送的所有导游
	 * @param orderBean
	 * @param tradeOrderDeliver
	 * @return
	 */
	public List<TradeDeliverGuide> getGuideOrdersByExcludeDeliverNo(OrderBean orderBean,TradeOrderDeliver tradeOrderDeliver){
		TradeDeliverGuideExample tradeDeliverGuideExample = new TradeDeliverGuideExample();
		TradeDeliverGuideExample.Criteria criteria = tradeDeliverGuideExample.createCriteria();
		criteria.andDeliverNoNotEqualTo(tradeOrderDeliver.getDeliverNo());
		criteria.andOrderNoEqualTo(orderBean.getOrderNo());		
		return tradeDeliverGuidMapper.selectByExample(tradeDeliverGuideExample);
	}
	
	
	
	
	
	/**
	 * 废弃guide order
	 * @param allocatGno
	 * @param isReadable
	 */
	public void disableGuideOrder(String allocatGno,IsReadable isReadable){
		int opnum = gxGuideDeliverMapper.disableGuideOrder(allocatGno, isReadable.value);
		if (opnum != 1) {
			throw new TradeException(TradeReturnCodeEnum.ORDER_GUIDE_DELIVER_UPDATE_DB_FAILED, allocatGno);
		}
	}
	
	/**
	 * 根据批次号以及是否可读，修改可读状态
	 * @param deliverNo
	 * @param originalReadable 原先可读状态
	 * @param newReadable 新的可读状态
	 * @return
	 */
	public int updateByBachNoReadable(String deliverNo,int originalReadable,int newReadable){
		int opnum = gxGuideDeliverMapper.updateAllBachNoReadable( deliverNo, originalReadable,newReadable);
		return opnum;
	}
	
	/**
	 * 根据订单号以及是否可读，修改可读状态
	 * @param orderNo
	 * @param originalReadable
	 * @param newReadable
	 * @return
	 */
	public int updateByOrderNoReadable(String orderNo,IsReadable originalReadable,IsReadable newReadable){
		int opnum = gxGuideDeliverMapper.updateByOrderNoReadable(orderNo, originalReadable.value,newReadable.value);
		return opnum;
	}
	
	public int updateDeliverStatusByGuideId(String orderNo,String guideId,IsReadable newReadable,GuidDeliverStatus guideDeliverStatus){
		int opnum = gxGuideDeliverMapper.updateDeliverStatusByGuideId(orderNo,guideId,newReadable.value,guideDeliverStatus.value,guideDeliverStatus.name);
		return opnum;
	}
	
	/**
	 * 更新PK失败
	 * 
	 * @param orderNo
	 * @param guideId
	 * @param deliverPKFailType
	 * @param guideDeliverStatus
	 * @return
	 */
	public int updatePKFailDeliverGuide(String orderNo,String guideId,DeliverPKFailType deliverPKFailType,GuidDeliverStatus guideDeliverStatus){
		int opnum = gxGuideDeliverMapper.updatePKFailDeliverGuide(orderNo,guideId,deliverPKFailType.value,guideDeliverStatus.value,guideDeliverStatus.name);
		return opnum;
	}
	
	/**
	 * 根据DeliverStatus获取数据
	 * @param deliverGuideStatus
	 * @return
	 */
	public List<TradeDeliverGuide> getDeliverGuideByDeliverStatus(List<Integer> deliverGuideStatus){
		TradeDeliverGuideExample tradeDeliverGuideExample = new TradeDeliverGuideExample();
		TradeDeliverGuideExample.Criteria criteria = tradeDeliverGuideExample.createCriteria();		
		if(deliverGuideStatus != null && deliverGuideStatus.size() > 0) {
			criteria.andDeliverStatusIn(deliverGuideStatus);	
		}else{
			return new ArrayList<TradeDeliverGuide>();
		}
		return tradeDeliverGuidMapper.selectByExample(tradeDeliverGuideExample);
	}
	
	public List<TradeDeliverGuide> getSuccessedList(List<String> orderNoList, String guideId) {
		TradeDeliverGuideExample tradeDeliverGuideExample = new TradeDeliverGuideExample();
		TradeDeliverGuideExample.Criteria criteria = tradeDeliverGuideExample.createCriteria();
		criteria.andGuideIdEqualTo(guideId);
		
		List<Integer> gstatus = new ArrayList<Integer>();
		gstatus.add(GuidDeliverStatus.success.value);
		gstatus.add(GuidDeliverStatus.successsend.value);
		if(gstatus != null && gstatus.size() > 0) {
			criteria.andDeliverStatusIn(gstatus);
		}
		if(orderNoList != null && orderNoList.size() > 0) {
			criteria.andOrderNoIn(orderNoList);
		}
		return tradeDeliverGuideMapper.selectByExample(tradeDeliverGuideExample);
	}
	
	public List<TradeDeliverGuide> getDeliverGuideList(List<String> orderNoList, String guideId) {
		TradeDeliverGuideExample tradeDeliverGuideExample = new TradeDeliverGuideExample();
		TradeDeliverGuideExample.Criteria criteria = tradeDeliverGuideExample.createCriteria();
		criteria.andGuideIdEqualTo(guideId).andIsReadableEqualTo(IsReadable.VISIABLE.value);
		if(orderNoList != null && orderNoList.size() > 0) {
			criteria.andOrderNoIn(orderNoList);
		}
		return tradeDeliverGuideMapper.selectByExample(tradeDeliverGuideExample);
	}

}
