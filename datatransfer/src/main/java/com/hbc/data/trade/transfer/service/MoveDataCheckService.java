/**
 * @Author lukangle
 * @2015年12月20日@上午10:10:10
 */
package com.hbc.data.trade.transfer.service;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hbc.api.trade.order.mapping.gen.OrderBeanMapper;
import com.hbc.api.trade.order.mapping.gen.TradeMoveCheckMapper;
import com.hbc.api.trade.order.mapping.gen.bean.OrderBean;
import com.hbc.api.trade.order.mapping.gen.bean.TradeMoveCheck;
import com.hbc.api.trade.order.mapping.gen.bean.TradeMoveInfo;
import com.hbc.api.trade.order.service.OrderQueryService;
import com.hbc.data.trade.transfer.mapping.hbcfinal.gen.FinalOrderBeanMapper;
import com.hbc.data.trade.transfer.mapping.hbcfinal.gen.bean.FinalOrderBean;
import com.hbc.data.trade.transfer.service.hfinal.FOrderService;
import com.hbc.data.trade.transfer.service.trade.TradeMoveInfoService;

@Component
public class MoveDataCheckService {
	@Autowired
	TradeMoveCheckMapper tradeMoveCheckMapper;

	Logger log = Logger.getLogger(MoveDataCheckService.class);
	@Autowired
	FinalOrderBeanMapper finalOrderBeanMapper;
	@Autowired
	FOrderService fOrderService;
	@Autowired
	TradeMoveInfoService tradeMoveInfoService;
	@Autowired
	OrderQueryService orderQueryService;
	@Autowired
	OrderBeanMapper orderBeanMapper;
	public void startToCheck(List<FinalOrderBean> finalOrderBeans) {
		for (FinalOrderBean finalOrderBean : finalOrderBeans) {
			try{
//				if(finalOrderBean.getOrderid().equals("150511193126521661")){
//					log.info("测试");
//				}
				List<TradeMoveInfo> tradeMoveInfos = tradeMoveInfoService.queryByOrderId(finalOrderBean.getOrderid());
				OrderBean orderBean = orderBeanMapper.selectByPrimaryKey(finalOrderBean.getOrdersn());
				TradeMoveCheck tradeMoveCheck = new TradeMoveCheck();
				tradeMoveCheck.setOrderId(finalOrderBean.getOrderid());
				tradeMoveCheck.setOrderSn(finalOrderBean.getOrdersn());
				
				if(tradeMoveInfos.size()>1){
					log.warn("订单["+finalOrderBean.getOrderid()+"] 在moveinfo中存在多条记录");
				}else if(tradeMoveInfos.size()==0){
					tradeMoveCheck.setIsInInfo(-1);
					log.warn("订单["+finalOrderBean.getOrderid()+"] 在moveinfo中没有记录");
				}else{
					tradeMoveCheck.setIsInInfo(1);
					String msginfo = "";
					for(TradeMoveInfo tradeMoveInfo : tradeMoveInfos){
						msginfo = msginfo+tradeMoveInfo.getReason();
						tradeMoveCheck.setInfoFlag(tradeMoveInfo.getFlag());
					}
					tradeMoveCheck.setInfoDesc(msginfo);
				}
				if(orderBean!=null){
					tradeMoveCheck.setOrderNo(orderBean.getOrderNo());
					tradeMoveCheck.setIsMoveSuccess(1);
				}else{
					tradeMoveCheck.setIsMoveSuccess(-1);
				}
				
				tradeMoveCheck.setCreateTime(new Date());
				
				tradeMoveCheckMapper.insert(tradeMoveCheck);
				
				log.info("校验订单["+tradeMoveCheck.getOrderId()+"]成功");
			}catch(Exception e){
				log.error("校验订单失败，订单号为["+finalOrderBean.getOrderid()+"]",e);
			}
		}
	}
}
