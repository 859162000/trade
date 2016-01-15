/**
 * @Author lukangle
 * @2015年12月8日@下午7:54:46
 */
package com.hbc.data.trade.transfer.service.hfinal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hbc.data.trade.transfer.exceptions.TradeTransferException;
import com.hbc.data.trade.transfer.exceptions.TradeTransferReturnCodeEnum;
import com.hbc.data.trade.transfer.mapping.hbcfinal.gen.bean.FinalOrderPayDetail;
import com.hbc.data.trade.transfer.mapping.pay.gen.PayRefundOrderMapper;
import com.hbc.data.trade.transfer.mapping.pay.gen.bean.PayRefundOrder;
import com.hbc.data.trade.transfer.mapping.pay.gen.bean.PayRefundOrderCriteria;

@Component
public class FRefundService {
	@Autowired
	FPayService fpayService;
	@Autowired
	PayRefundOrderMapper payRefundOrderMapper;

	public PayRefundOrder getAliRefund(String orderId) {
		FinalOrderPayDetail finalOrderPayDetail = fpayService.getOrderPayDetail(orderId);
		if (finalOrderPayDetail != null) {
			String detailId = finalOrderPayDetail.getOrderpaydetailid();

			PayRefundOrderCriteria payRefundOrderCriteria = new PayRefundOrderCriteria();
			PayRefundOrderCriteria.Criteria criteria = payRefundOrderCriteria.createCriteria();

			criteria.andBusiRefundNoEqualTo(detailId);
			List<PayRefundOrder> refundList = payRefundOrderMapper.selectByExample(payRefundOrderCriteria);
			
			if(refundList.size()>1){
				throw new TradeTransferException(TradeTransferReturnCodeEnum.FORDER_muti_refund,orderId);
			}else if(refundList.size()==1){
				return refundList.get(0);
			}
		}
		return null;
	}

}
