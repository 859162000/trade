/**
 * @Author lukangle
 * @2015年12月18日@上午11:26:02
 */
package com.hbc.data.trade.transfer.service.trade;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hbc.api.trade.order.mapping.gen.TradeMoveInfoMapper;
import com.hbc.api.trade.order.mapping.gen.bean.TradeMoveInfo;
import com.hbc.api.trade.order.mapping.gen.bean.TradeMoveInfoExample;
import com.hbc.api.trade.pay.exception.PayException;
import com.hbc.api.trade.pay.exception.PayReturnCodeEnum;

@Component
public class TradeMoveInfoService {
	@Autowired
	TradeMoveInfoMapper tradeMoveInfoMapper;
	
	public void insertMoveInfo(String orderId,String orderSn,boolean flag,String reason,String exp){
		TradeMoveInfo record = new TradeMoveInfo();
		record.setExp(exp);
		if(flag){
			record.setFlag(1);
		}else{
			record.setFlag(-1);
		}
		record.setOrderId(orderId);
		record.setOrderSn(orderSn);
		record.setReason(reason);
		record.setCreateTime(new Date());
		tradeMoveInfoMapper.insert(record);
	}
	
	public boolean isSuccess(String orderId){
		TradeMoveInfoExample tradeMoveInfoExample = new TradeMoveInfoExample();
		tradeMoveInfoExample.createCriteria().andOrderIdEqualTo(orderId).andFlagEqualTo(1);
		List<TradeMoveInfo> records = tradeMoveInfoMapper.selectByExample(tradeMoveInfoExample);
		if(records.size()==0){
			return false;
		}else if(records.size()==1){
			if(records.get(0).getFlag()==1){
				return true;
			}else{
				return false;
			}
		}else{
			throw new PayException(PayReturnCodeEnum.PAY_INSERT_FAILED, orderId+" 日志异常");
		}
	}
	
	public List<TradeMoveInfo> queryByOrderId(String orderId){
		TradeMoveInfoExample tradeMoveInfoExample = new TradeMoveInfoExample();
		tradeMoveInfoExample.createCriteria().andOrderIdEqualTo(orderId);
		return tradeMoveInfoMapper.selectByExample(tradeMoveInfoExample);
	}
}
