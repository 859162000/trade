/**
 * @Author lukangle
 * @2015年12月29日@下午4:15:54
 */
package com.hbc.data.trade.transfer.service;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hbc.api.trade.bdata.common.util.TimeConverter;
import com.hbc.api.trade.order.enums.deliver.DemandDeal;
import com.hbc.api.trade.order.enums.deliver.GuidDeliverStatus;
import com.hbc.api.trade.order.enums.deliver.IsReadable;
import com.hbc.api.trade.order.mapping.gen.TradeDeliverGuideMapper;
import com.hbc.api.trade.order.mapping.gen.TradeOrderDeliverMapper;
import com.hbc.api.trade.order.mapping.gen.bean.TradeDeliverGuide;
import com.hbc.api.trade.order.mapping.gen.bean.TradeDeliverGuideExample;
import com.hbc.api.trade.order.mapping.gen.bean.TradeOrderDeliver;
import com.hbc.api.trade.order.mapping.gen.bean.TradeOrderDeliverExample;

@Component
public class DeliverDateCheck {
	@Autowired
	TradeDeliverGuideMapper tradeDeliverGuideMapper;
	@Autowired
	TradeOrderDeliverMapper tradeOrderDeliverMapper;
	private final static Logger logger = LoggerFactory.getLogger(DeliverDateCheck.class);
	public void checkPkStatus() {
		TradeOrderDeliverExample tradeOrderDeliverExample = new TradeOrderDeliverExample();
		TradeOrderDeliverExample.Criteria criteria = tradeOrderDeliverExample.createCriteria();

		Date date = TimeConverter.toDate("2015-12-25", TimeConverter.END_WITH_DATE);
		criteria.andUpdateTimeGreaterThan(date);

		List<TradeOrderDeliver> olist = tradeOrderDeliverMapper.selectByExample(tradeOrderDeliverExample);
		
		Set<String> set = new HashSet<String>();
		for(TradeOrderDeliver tradeOrderDeliver : olist){
			set.add(tradeOrderDeliver.getOrderNo());
		}
		
		for(String orderNo : set){
			TradeDeliverGuideExample tradeDeliverGuideExample = new TradeDeliverGuideExample();
			TradeDeliverGuideExample.Criteria cdriteria = tradeDeliverGuideExample.createCriteria();
			
			cdriteria.andOrderNoEqualTo(orderNo);
			cdriteria.andIsReadableEqualTo(IsReadable.VISIABLE.value);
			List<TradeDeliverGuide> glist = tradeDeliverGuideMapper.selectByExample(tradeDeliverGuideExample);
			
			boolean isUpdate = false;
			
			for(TradeDeliverGuide tradeDeliverGuide : glist){
				GuidDeliverStatus guidDeliverStatus = GuidDeliverStatus.getType(tradeDeliverGuide.getDeliverStatus());
				if(GuidDeliverStatus.success.equals(guidDeliverStatus) || GuidDeliverStatus.successsend.equals(guidDeliverStatus)){
					isUpdate = true;
					break;
				}
			}
			
			if(isUpdate){
				System.out.println(orderNo+" 开始更新 数据");
				logger.info(orderNo+" 开始更新 数据");
				for(TradeDeliverGuide tradeDeliverGuide : glist){
					GuidDeliverStatus guidDeliverStatus = GuidDeliverStatus.getType(tradeDeliverGuide.getDeliverStatus());
					
					if(GuidDeliverStatus.nosend.equals(guidDeliverStatus) || GuidDeliverStatus.sendpush.equals(guidDeliverStatus)){
						DemandDeal demandDeal = DemandDeal.INITIAL ;
						if(tradeDeliverGuide.getDemandDeal()!=null){
							demandDeal = DemandDeal.getType(tradeDeliverGuide.getDemandDeal());
						}
						
						TradeDeliverGuide tradeDeliverGuidDbOther = new TradeDeliverGuide();
						tradeDeliverGuidDbOther.setAllocatGno(tradeDeliverGuide.getAllocatGno());
						
//						if(DemandDeal.AGREE.equals(demandDeal)){
//							tradeDeliverGuidDbOther.setDeliverStatus(GuidDeliverStatus.pkfailed.value);
//							tradeDeliverGuidDbOther.setDeliverStatusName(GuidDeliverStatus.pkfailed.name);
//						}else{
//							tradeDeliverGuidDbOther.setDeliverStatus(GuidDeliverStatus.pkfailedPush.value);
//							tradeDeliverGuidDbOther.setDeliverStatusName(GuidDeliverStatus.pkfailedPush.name);
//						}
						tradeDeliverGuidDbOther.setDeliverStatus(GuidDeliverStatus.pkfailedPush.value);
						tradeDeliverGuidDbOther.setDeliverStatusName(GuidDeliverStatus.pkfailedPush.name);
						int updNum = tradeDeliverGuideMapper.updateByPrimaryKeySelective(tradeDeliverGuidDbOther);
						if (updNum >=1) {
							System.out.println("发单记录【"+tradeDeliverGuidDbOther.getAllocatGno()+"】 订单号【"+tradeDeliverGuidDbOther.getOrderNo()+"】 更新【"+updNum+"】条已发送导游订单为 pk失败");
							logger.info("发单记录【"+tradeDeliverGuidDbOther.getAllocatGno()+"】 订单号【"+tradeDeliverGuidDbOther.getOrderNo()+"】 更新【"+updNum+"】条已发送导游订单为 pk失败");
						}else{
							System.out.println("发单记录【"+tradeDeliverGuidDbOther.getAllocatGno()+"】 订单号【"+tradeDeliverGuidDbOther.getOrderNo()+"】 更新【"+updNum+"】条已发送导游订单为 pk失败");
							logger.info("失败  发单记录【"+tradeDeliverGuidDbOther.getAllocatGno()+"】 订单号【"+tradeDeliverGuidDbOther.getOrderNo()+"】 更新【"+updNum+"】条已发送导游订单为 pk失败");
						}
					}
				}
			}
			
		}
	}
}
