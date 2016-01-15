/**
 * @Author lukangle
 * @2015年12月29日@下午5:46:40
 */
package com.hbc.data.trade.transfer.service;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import kafka.log.Log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hbc.api.trade.bdata.common.util.TimeConverter;
import com.hbc.api.trade.order.enums.deliver.GuidDeliverStatus;
import com.hbc.api.trade.order.enums.deliver.IsReadable;
import com.hbc.api.trade.order.enums.order.OrderStatus;
import com.hbc.api.trade.order.mapping.gen.TradeDeliverGuideMapper;
import com.hbc.api.trade.order.mapping.gen.TradeOrderDeliverMapper;
import com.hbc.api.trade.order.mapping.gen.bean.OrderBean;
import com.hbc.api.trade.order.mapping.gen.bean.TradeDeliverGuide;
import com.hbc.api.trade.order.mapping.gen.bean.TradeDeliverGuideExample;
import com.hbc.api.trade.order.mapping.gen.bean.TradeOrderDeliver;
import com.hbc.api.trade.order.mapping.gen.bean.TradeOrderDeliverExample;
import com.hbc.api.trade.order.service.OrderQueryService;
@Component
public class DeliverPkstatusToInit {
	@Autowired
	TradeDeliverGuideMapper tradeDeliverGuideMapper;
	@Autowired
	TradeOrderDeliverMapper tradeOrderDeliverMapper;
	@Autowired
	OrderQueryService orderQueryService;
	private final static Logger logger = LoggerFactory.getLogger(DeliverPkstatusToInit.class);
	public void checkPkStatus() {
		TradeOrderDeliverExample tradeOrderDeliverExample = new TradeOrderDeliverExample();
		TradeOrderDeliverExample.Criteria criteria = tradeOrderDeliverExample.createCriteria();

		Date date = TimeConverter.toDate("2015-12-20", TimeConverter.END_WITH_DATE);
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
//					System.out.println(orderNo+" GUIDENO【"+tradeDeliverGuide.getAllocatGno()+"】 已经成功 ，无须更新");
//					logger.info(orderNo+" GUIDENO【"+tradeDeliverGuide.getAllocatGno()+"】 已经成功 ，无须更新");
					break;
				}
			}
			
			//全部是失败 更新成  已发送导游
			if(!isUpdate){
				System.out.println();
				logger.info("可以开始更新 订单 ：【"+orderNo+"】");
//				OrderBean orderBean = orderQueryService.getOrderByNo(orderNo);
//				OrderStatus orderStatus = OrderStatus.getStatus(orderBean.getOrderStatus());
//				for(TradeDeliverGuide tradeDeliverGuide : glist){
//					GuidDeliverStatus guidDeliverStatus = GuidDeliverStatus.getType(tradeDeliverGuide.getDeliverStatus());
//					
//					if(tradeDeliverGuide.getGuideId().equals(orderBean.getGuideId())){
//						TradeDeliverGuide tradeDeliverGuidDbOther = new TradeDeliverGuide();
//						tradeDeliverGuidDbOther.setAllocatGno(tradeDeliverGuide.getAllocatGno());
//						
//						tradeDeliverGuidDbOther.setDeliverStatus(GuidDeliverStatus.successsend.value);
//						tradeDeliverGuidDbOther.setDeliverStatusName(GuidDeliverStatus.successsend.name);
//						int updNum = tradeDeliverGuideMapper.updateByPrimaryKeySelective(tradeDeliverGuidDbOther);
//						if (updNum >=1) {
//							System.out.println("发单记录【"+tradeDeliverGuidDbOther.getAllocatGno()+"】 订单号【"+tradeDeliverGuide.getOrderNo()+"】 更新【"+updNum+"】条已发送导游订单为 "+GuidDeliverStatus.sendpush.name+"");
//							logger.info("发单记录【"+tradeDeliverGuidDbOther.getAllocatGno()+"】 订单号【"+tradeDeliverGuide.getOrderNo()+"】 更新【"+updNum+"】条已发送导游订单为 "+GuidDeliverStatus.sendpush.name+"");
//						}else{
//							System.out.println("发单记录【"+tradeDeliverGuidDbOther.getAllocatGno()+"】 订单号【"+tradeDeliverGuide.getOrderNo()+"】 更新【"+updNum+"】条已发送导游订单为 pk失败");
//							logger.info("失败  发单记录【"+tradeDeliverGuidDbOther.getAllocatGno()+"】 订单号【"+tradeDeliverGuide.getOrderNo()+"】 更新【"+updNum+"】条已发送导游订单为 pk失败");
//						}
//					}else if(OrderStatus.PAYSUCCESS.equals(orderStatus) && GuidDeliverStatus.pkfailedPush.equals(guidDeliverStatus) && !tradeDeliverGuide.getGuideId().equals(orderBean.getGuideId())){
//						TradeDeliverGuide tradeDeliverGuidDbOther = new TradeDeliverGuide();
//						tradeDeliverGuidDbOther.setAllocatGno(tradeDeliverGuide.getAllocatGno());
//						
//						tradeDeliverGuidDbOther.setDeliverStatus(GuidDeliverStatus.sendpush.value);
//						tradeDeliverGuidDbOther.setDeliverStatusName(GuidDeliverStatus.sendpush.name);
//						int updNum = tradeDeliverGuideMapper.updateByPrimaryKeySelective(tradeDeliverGuidDbOther);
//						if (updNum >=1) {
//							System.out.println("发单记录【"+tradeDeliverGuidDbOther.getAllocatGno()+"】 订单号【"+tradeDeliverGuide.getOrderNo()+"】 更新【"+updNum+"】条已发送导游订单为 "+GuidDeliverStatus.sendpush.name+"");
//							logger.info("发单记录【"+tradeDeliverGuidDbOther.getAllocatGno()+"】 订单号【"+tradeDeliverGuide.getOrderNo()+"】 更新【"+updNum+"】条已发送导游订单为 "+GuidDeliverStatus.sendpush.name+"");
//						}else{
//							System.out.println("发单记录【"+tradeDeliverGuidDbOther.getAllocatGno()+"】 订单号【"+tradeDeliverGuide.getOrderNo()+"】 更新【"+updNum+"】条已发送导游订单为 pk失败");
//							logger.info("失败  发单记录【"+tradeDeliverGuidDbOther.getAllocatGno()+"】 订单号【"+tradeDeliverGuide.getOrderNo()+"】 更新【"+updNum+"】条已发送导游订单为 pk失败");
//						}
//					}else{
//						logger.error(orderNo+"错误数据 无法处理");
//					}
//				}
			}
			
		}
	}
}
