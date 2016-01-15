/*
 * Copyright (c) 2015-2016, CCLX.COM. All rights reserved.
 * WANDA GROUP PROPRIETARY/CONFIDENTIAL. 
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is private property; you can't redistribute it and/or modify it
 * under the terms of the LICENSE you obtained from
 *
 *    http://www.cclx.com/
 * 
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. 
 *
 * Author: Jongly Ran
 * Revision: 1.0
 */
package com.hbc.api.trade.order.service;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.hbc.api.trade.bdata.common.Page;
import com.hbc.api.trade.bdata.mapper.guide.gen.bean.GuideBean;
import com.hbc.api.trade.bdata.mapper.guide.gen.bean.GuideProhibit;
import com.hbc.api.trade.order.enums.TradeReturnCodeEnum;
import com.hbc.api.trade.order.enums.deliver.DemandDeal;
import com.hbc.api.trade.order.enums.deliver.GuidDeliverStatus;
import com.hbc.api.trade.order.enums.deliver.IsRead;
import com.hbc.api.trade.order.enums.deliver.IsReadable;
import com.hbc.api.trade.order.enums.third.GuideLevelEnums;
import com.hbc.api.trade.order.enums.third.GuideProhibitEnum;
import com.hbc.api.trade.order.enums.third.GuideSignStatus;
import com.hbc.api.trade.order.exception.TradeException;
import com.hbc.api.trade.order.mapping.gen.TradeDeliverGuideMapper;
import com.hbc.api.trade.order.mapping.gen.bean.OrderBean;
import com.hbc.api.trade.order.mapping.gen.bean.TradeDeliverGuide;
import com.hbc.api.trade.order.mapping.gen.bean.TradeDeliverGuideExample;
import com.hbc.api.trade.order.mapping.genx.TradeDeliverGuideMapperEnhance;
import com.hbc.api.trade.order.mapping.genx.xbean.TradeDeliverGuideParamBean;
import com.hbc.api.trade.order.mapping.genx.xbean.TradeDeliverGuideQueryBean;
import com.hbc.api.trade.order.service.gorder.GOrderService;
import com.hbc.api.trade.order.service.gorder.rsp.GOrderBean;
import com.hbc.api.trade.third.GuideLevelService;
import com.hbc.api.trade.third.GuideQueryService;
import com.hbc.api.trade.third.LControllerService;

/**
 * @author Jongly Ran
 */
@Service
public class TradeDeliverGuideService {
	private final static Logger log = LoggerFactory.getLogger(TradeDeliverGuideService.class);
	
	@Autowired private OrderQueryService 				orderQueryService;
	@Autowired private TradeDeliverGuideMapperEnhance 	tradeDeliverGuideMapperEnhance;
	@Autowired private GuideLevelService 				guideLevelService;
	@Autowired private GuideQueryService 				guideQueryService;
	@Autowired private GOrderService 					gorderService;
	public TradeDeliverGuide selectOne(String allocateGuideId) {
		return tradeDeliverGuideMapperEnhance.selectOne(allocateGuideId);
	}
	@Autowired
	LControllerService lControllerService;
	public void agree(String allocateGid, String orderNo, String guideId) {
		List<GuideProhibit> guideProhibits = lControllerService.getGuideProhibit(guideId,GuideProhibitEnum.PROHIBIT_TYPE_RECEIVE);
		if(guideProhibits.size()>0){
			log.error("我要接单：导游未培训不能接单。导游id=" + guideId );
			throw new TradeException(TradeReturnCodeEnum.GUIDE_FORBIT_ACC);
		}
		TradeDeliverGuideParamBean inputs = new TradeDeliverGuideParamBean(); 
		Date currentTime = new Date();
		inputs.setAcceptTime(currentTime);
		inputs.setUpdateTime(currentTime);
		inputs.setAllocateGid(allocateGid);
		inputs.setOrderNo(orderNo);
		inputs.setDemandDeal(DemandDeal.AGREE.value);
		inputs.setGuideId(guideId);
		
		
		OrderBean orderBean = orderQueryService.getOrderByNo(orderNo);
		
		TradeDeliverGuide tradeDeliverGuide = tradeDeliverGuideMapper.selectByPrimaryKey(allocateGid);
		DemandDeal demandDeal = DemandDeal.getType(tradeDeliverGuide.getDemandDeal());
		
		if(DemandDeal.INITIAL.equals(demandDeal)){
			GuideLevelEnums guideLevelEnums = guideLevelService.getGuideLevByOrder(orderBean);
			
			GuideBean guideBean = guideQueryService.getGuideBeanById(guideId);
			if(GuideSignStatus.SIGNSTATUS_TRAINED.value.equals(guideBean.getSignStatus())){
				if(guideBean.getGuideLevel()!=null && ((int)guideBean.getGuideLevel())<=guideLevelEnums.value){
					TradeDeliverGuide record = new TradeDeliverGuide();
					record.setAllocatGno(tradeDeliverGuide.getAllocatGno());
					record.setDemandDeal(DemandDeal.AGREE.value);
					record.setAcceptTime(new Date());
					int optnum = tradeDeliverGuideMapper.updateByPrimaryKeySelective(record);
					if(optnum == 0) {
						log.error("trade_deliver_guide表更新为1（我要接单）时失败，参数为：" + JSON.toJSONString(inputs));
						throw new TradeException(TradeReturnCodeEnum.FAILED_FOR_UPDATE, "发单时");
					}
				}else{
					log.error("导游ID："+guideBean.getGuideId()+"	导游编号："+guideBean.getGuideNo()+" 等级 "+guideBean.getGuideLevel());
					throw new TradeException(TradeReturnCodeEnum.GUIDE_LEVEL_NOT);
				}
			}else{
				log.error("导游ID："+guideBean.getGuideId()+"	导游编号："+guideBean.getGuideNo()+" getSignStatus "+guideBean.getSignStatus());
				throw new TradeException(TradeReturnCodeEnum.GUIDE_ISNOT_SING);
			}
		}else{
			throw new TradeException(TradeReturnCodeEnum.ORDER_AGREE_FAILED,orderNo);
		}
	}
	
	public void refuse(String allocateGid, String orderNo, String refuseReason, String guideId) {
		List<GuideProhibit> guideProhibits = lControllerService.getGuideProhibit(guideId,GuideProhibitEnum.PROHIBIT_TYPE_RECEIVE);
		if(guideProhibits.size()>0){log.error("拒绝接单：导游未培训不能接单。导游id=" + guideId );
			throw new TradeException(TradeReturnCodeEnum.UNTRAINED_CANNOT_OPERATION);
		}
		
		TradeDeliverGuideParamBean inputs = new TradeDeliverGuideParamBean(); 
		inputs.setUpdateTime(new Date());
		inputs.setAllocateGid(allocateGid);
		inputs.setOrderNo(orderNo);
		inputs.setRefuseReason(refuseReason);
		inputs.setDemandDeal(DemandDeal.DISAGREE.value);
		inputs.setGuideId(guideId);
		
		TradeDeliverGuide tradeDeliverGuide = tradeDeliverGuideMapper.selectByPrimaryKey(allocateGid);
		DemandDeal demandDeal = DemandDeal.getType(tradeDeliverGuide.getDemandDeal());
		OrderBean orderBean = orderQueryService.getOrderByNo(orderNo);
		
		if(DemandDeal.INITIAL.equals(demandDeal)){
			GuideLevelEnums guideLevelEnums = guideLevelService.getGuideLevByOrder(orderBean);
			
			GuideBean guideBean = guideQueryService.getGuideBeanById(guideId);
			if(GuideSignStatus.SIGNSTATUS_TRAINED.value.equals(guideBean.getSignStatus())){
				if(guideBean.getGuideLevel()!=null && ((int)guideBean.getGuideLevel())<=guideLevelEnums.value){
					if(tradeDeliverGuideMapperEnhance.refuse(inputs) == 0) {
						log.error("trade_deliver_guide表更新为2（拒绝接单）时失败，可能已经表态了，不可重复表态。参数为：" + JSON.toJSONString(inputs));
						throw new TradeException(TradeReturnCodeEnum.ALREADY_HANDLED);
					}
				}else{
					log.error("导游ID："+guideBean.getGuideId()+"	导游编号："+guideBean.getGuideNo()+" 等级 "+guideBean.getGuideLevel());
					throw new TradeException(TradeReturnCodeEnum.GUIDE_LEVEL_NOT);
				}
			}else{
				log.error("导游ID："+guideBean.getGuideId()+"	导游编号："+guideBean.getGuideNo()+" getSignStatus "+guideBean.getSignStatus());
				throw new TradeException(TradeReturnCodeEnum.GUIDE_ISNOT_SING);
			}
		}else{
			throw new TradeException(TradeReturnCodeEnum.ORDER_AGREE_FAILED,orderNo);
		}
		
	}
	
	public void hasRead(TradeDeliverGuide tradeDeliverGuide) {
		if(!IsRead.READ.value.equals(tradeDeliverGuide.getIsRead())){
			TradeDeliverGuide tradeDeliverGuideUpd = new TradeDeliverGuide();
			tradeDeliverGuideUpd.setIsRead(IsRead.READ.value);
			tradeDeliverGuideUpd.setAllocatGno(tradeDeliverGuide.getAllocatGno());
			tradeDeliverGuideUpd.setFirstReadTime(new Date());
			int optnum = tradeDeliverGuideMapper.updateByPrimaryKeySelective(tradeDeliverGuideUpd);

			if(optnum == 0) {
				log.error("trade_deliver_guide表更新为1（已读）时失败，参数为：" + JSON.toJSONString(tradeDeliverGuideUpd));
				throw new TradeException(TradeReturnCodeEnum.FAILED_FOR_UPDATE, "发单时");
			}
		}
	}

	/**
	 * @param allocateGid
	 * @param orderNo
	 */
	public void removeMissedOrder(String allocateGid, String orderNo, String guideId) {
		TradeDeliverGuideParamBean inputs = new TradeDeliverGuideParamBean(); 
		inputs.setAllocateGid(allocateGid);
		inputs.setOrderNo(orderNo);
		inputs.setGuideId(guideId);
		if(tradeDeliverGuideMapperEnhance.delete(inputs) == 0 ) {
			log.error("trade_deliver_guide表逻辑删除数据失败，参数为：" + JSON.toJSONString(inputs));
			throw new TradeException(TradeReturnCodeEnum.FAILED_FOR_UPDATE, "发单时");
		}
	}
	
	public List<TradeDeliverGuide> getTradeDeliverGuideList(TradeDeliverGuideQueryBean queryBean) {
		return tradeDeliverGuideMapperEnhance.selectDeliverGuides(queryBean);
	}
	
	public int getTradeDeliverGuideListTotalSize(TradeDeliverGuideQueryBean queryBean) {
		return tradeDeliverGuideMapperEnhance.selectDeliverGuidesTotalSize(queryBean);
	}
	@Autowired
	TradeDeliverGuideMapper tradeDeliverGuideMapper;
	public TradeDeliverGuide getTradeDeliverGuideByAllocateGid(String allocateGid, String orderNo, String guideId){
		TradeDeliverGuideExample tradeDeliverGuideExample = new TradeDeliverGuideExample();
		TradeDeliverGuideExample.Criteria criteria = tradeDeliverGuideExample.createCriteria();
		criteria.andAllocatGnoEqualTo(allocateGid);
		criteria.andIsReadableEqualTo(IsReadable.VISIABLE.value);
		List<TradeDeliverGuide> orderGuiides = tradeDeliverGuideMapper.selectByExample(tradeDeliverGuideExample);
		if(orderGuiides.size()==1){
			TradeDeliverGuide tradeDeliverGuide = orderGuiides.get(0);
			hasRead(tradeDeliverGuide);
			return tradeDeliverGuide;
		}else{
			log.error("allocateGid ["+allocateGid+"]  发单guide表数据异常");
			return null;
		}
	}
	
	public TradeDeliverGuide getTradeDeliverGuide(String guideId,String orderNo){
		TradeDeliverGuideExample tradeDeliverGuideExample = new TradeDeliverGuideExample();
		tradeDeliverGuideExample.setOrderByClause("create_time desc");
		TradeDeliverGuideExample.Criteria criteria = tradeDeliverGuideExample.createCriteria();
		criteria.andOrderNoEqualTo(orderNo);
		criteria.andGuideIdEqualTo(guideId);
		criteria.andIsReadableEqualTo(IsReadable.VISIABLE.value);
		List<TradeDeliverGuide> orderGuiides = tradeDeliverGuideMapper.selectByExample(tradeDeliverGuideExample);
		if(orderGuiides.size()==1){
			TradeDeliverGuide tradeDeliverGuide = orderGuiides.get(0);
			hasRead(tradeDeliverGuide);
			return tradeDeliverGuide;
		}else{
			log.error("guideId ["+guideId+"] orderNo ["+orderNo+"]  发单guide表数据异常");
			return null;
		}
	}

	/**
	 * @param queryBean
	 * @return
	 */
	public Map<String, Object> getGMissedOrders(TradeDeliverGuideQueryBean queryBean) {
		List<Integer> deliverStatusList = new LinkedList<>();
		deliverStatusList.add(GuidDeliverStatus.pkfailed.value);
		deliverStatusList.add(GuidDeliverStatus.pkfailedPush.value);
		queryBean.setDeliverStatusList(deliverStatusList);
		
		TradeDeliverGuideExample example = new TradeDeliverGuideExample();
		example.createCriteria().andGuideIdEqualTo(queryBean.getGuideId()).andDeliverStatusIn(deliverStatusList).andIsReadableEqualTo(IsReadable.VISIABLE.value);
		Integer totalSize = tradeDeliverGuideMapper.countByExample(example);

		Page page = new Page(queryBean.getOffset(), queryBean.getLimit());
		example.setOrderByClause("create_time desc");
		example.setPage(page );
		List<TradeDeliverGuide> deliverGuideList = tradeDeliverGuideMapper.selectByExample(example);
		
		if (deliverGuideList != null && deliverGuideList.size() > 0) {
			List<String> orderNoList = new LinkedList<>();
			for (TradeDeliverGuide bean : deliverGuideList) {
				orderNoList.add(bean.getOrderNo());
			}

			if (orderNoList.size() > 0) {
				List<OrderBean> orderBeans = orderQueryService.getMissOrdersByOrderNOs(orderNoList);
				List<GOrderBean> resultBean = gorderService.convertToMissBean(orderBeans,deliverGuideList, queryBean.getGuideId(), orderNoList,true);
				Map<String, Object> resultData = new HashMap<String, Object>(2);
				resultData.put("totalSize", totalSize);
				resultData.put("resultBean", resultBean);
				return resultData;
			}
		}
		return null;
	}

	/**
	 * @param queryBean
	 * @return
	 */
	public Map<String, Object> getPurposefulOrders(TradeDeliverGuideQueryBean queryBean) {
		List<Integer> deliverStatusList = new LinkedList<>();
		deliverStatusList.add(GuidDeliverStatus.resend.value);
		deliverStatusList.add(GuidDeliverStatus.sendpush.value);
		queryBean.setDeliverStatusList(deliverStatusList);
		log.info("GAPP查新订单列表，inputs：" + JSON.toJSONString(queryBean));
		List<TradeDeliverGuide> deliverGuideList = tradeDeliverGuideMapperEnhance.selectPurposeOrders(queryBean);
		Integer totalSize = tradeDeliverGuideMapperEnhance.selectPurposeOrdersTotalSize(queryBean);
		if (deliverGuideList != null && deliverGuideList.size() > 0) {
			List<String> orderNoList = new LinkedList<>();
			Map<String, String> allocateGidMap = new HashMap<>();
			for (TradeDeliverGuide bean : deliverGuideList) {
				orderNoList.add(bean.getOrderNo());
				allocateGidMap.put(bean.getOrderNo(), bean.getAllocatGno());
			}

			if (orderNoList.size() > 0) {
				List<OrderBean> orderBeans = orderQueryService.getMissOrdersByOrderNOs(orderNoList);
				List<GOrderBean> orderBeanList = gorderService.convertToGorderBean(orderBeans,deliverGuideList,queryBean.getGuideId(), orderNoList,true);
				List<GOrderBean> orderBeanListTarget = new LinkedList<>();
				for (GOrderBean gOrder : orderBeanList) {
					gOrder.setAllocateGid(allocateGidMap.get(gOrder.getOrderNo()));
					orderBeanListTarget.add(gOrder);
				}
				Map<String, Object> resultData = new HashMap<String, Object>(3);
				resultData.put("hasMissedOrder",hasMissOrder(queryBean.getGuideId()));
				resultData.put("totalSize", totalSize);
				resultData.put("resultBean", orderBeanListTarget);
				log.info("GAPP查新订单列表，outputs：" + JSON.toJSONString(resultData));
				return resultData;
			}
		}
		Map<String, Object> resultData = new HashMap<String, Object>(3);
		resultData.put("hasMissedOrder",hasMissOrder(queryBean.getGuideId()));
		resultData.put("totalSize", 0);
		resultData.put("resultBean", new LinkedList<>());
		log.info("GAPP查新订单列表，outputs：" + JSON.toJSONString(resultData));
		return resultData;
	}
	
	public boolean hasMissOrder(String guideId){
		TradeDeliverGuideExample tradeDeliverGuideExample = new TradeDeliverGuideExample();
		TradeDeliverGuideExample.Criteria criteria = tradeDeliverGuideExample.createCriteria();
		criteria.andGuideIdEqualTo(guideId);
		criteria.andIsReadableEqualTo(IsReadable.VISIABLE.value);
		
		List<Integer> deliverStatusList = new LinkedList<>();
		deliverStatusList.add(GuidDeliverStatus.pkfailed.value);
		deliverStatusList.add(GuidDeliverStatus.pkfailedPush.value);
		criteria.andDeliverStatusIn(deliverStatusList);
		return tradeDeliverGuideMapper.countByExample(tradeDeliverGuideExample) > 0;
	}
}
