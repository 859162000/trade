/**
 * @Author lukangle
 * @2015年12月8日@上午11:02:49
 */
package com.hbc.data.trade.transfer.service.hfinal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hbc.data.trade.transfer.mapping.hbcfinal.gen.FinalOrderPayDetailMapper;
import com.hbc.data.trade.transfer.mapping.hbcfinal.gen.FinalOrderPayMapper;
import com.hbc.data.trade.transfer.mapping.hbcfinal.gen.bean.FinalOrderPay;
import com.hbc.data.trade.transfer.mapping.hbcfinal.gen.bean.FinalOrderPayCriteria;
import com.hbc.data.trade.transfer.mapping.hbcfinal.gen.bean.FinalOrderPayDetail;
import com.hbc.data.trade.transfer.mapping.hbcfinal.gen.bean.FinalOrderPayDetailCriteria;
import com.hbc.data.trade.transfer.mapping.pay.gen.PayConsumeOrderMapper;
import com.hbc.data.trade.transfer.mapping.pay.gen.PayRechargeOrderMapper;
import com.hbc.data.trade.transfer.mapping.pay.gen.bean.PayConsumeOrder;
import com.hbc.data.trade.transfer.mapping.pay.gen.bean.PayConsumeOrderCriteria;
import com.hbc.data.trade.transfer.mapping.pay.gen.bean.PayRechargeOrder;

@Component
public class FPayService {
	@Autowired
	FinalOrderPayMapper finalOrderPayMapper;
	@Autowired
	FinalOrderPayDetailMapper finalOrderPayDetailMapper;
	@Autowired
	PayConsumeOrderMapper payConsumeOrderMapper;
	
	@Autowired
	PayRechargeOrderMapper payRechargeOrderMapper;
	/**所有订单 只有单独的个数**/
	public FinalOrderPay getPayInfosByOrderNo(String forderId){
		FinalOrderPayCriteria finalOrderPayCriteria = new FinalOrderPayCriteria();
		FinalOrderPayCriteria.Criteria criteria = finalOrderPayCriteria.createCriteria();
		criteria.andOrderidEqualTo(forderId);
		List<FinalOrderPay> finalOrderPayList = finalOrderPayMapper.selectByExample(finalOrderPayCriteria);
		if(finalOrderPayList.size()==1){
			return finalOrderPayList.get(0);
		}
		return null;
	}
	
	public PayConsumeOrder getPayConsume(String orderPayDetailId){
		PayConsumeOrderCriteria payConsumeOrderCriteria = new PayConsumeOrderCriteria();
		PayConsumeOrderCriteria.Criteria criteria = payConsumeOrderCriteria.createCriteria();
		criteria.andBusiConsumeNoEqualTo(orderPayDetailId);
		criteria.andStatusEqualTo(2);
		List<PayConsumeOrder> finalOrderPayList = payConsumeOrderMapper.selectByExample(payConsumeOrderCriteria);
		if(finalOrderPayList.size()==1){
			return finalOrderPayList.get(0);
		}
		return null;
	}
	
	public PayRechargeOrder getPayRecharge(String rid){
		return payRechargeOrderMapper.selectByPrimaryKey(rid);
	}
	
	public FinalOrderPayDetail getOrderPayDetail(String forderId){
		FinalOrderPayDetailCriteria finalOrderPayDetailCriteria = new FinalOrderPayDetailCriteria();
		FinalOrderPayDetailCriteria.Criteria criteria  = finalOrderPayDetailCriteria.createCriteria();
		criteria.andOrderidEqualTo(forderId);
		List<FinalOrderPayDetail> finalOrderPayDetailList = finalOrderPayDetailMapper.selectByExample(finalOrderPayDetailCriteria);
		if(finalOrderPayDetailList.size()==1){
			return finalOrderPayDetailList.get(0);
		}else{
			return null;
		}
	}
}
