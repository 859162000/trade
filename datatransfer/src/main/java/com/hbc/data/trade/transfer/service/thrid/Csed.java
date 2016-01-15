/**
 * @Author lukangle
 * @2015年12月25日@上午8:13:54
 */
package com.hbc.data.trade.transfer.service.thrid;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.hbc.api.trade.order.mapping.gen.OrderBeanMapper;
import com.hbc.api.trade.order.mapping.gen.TradeMoveInfoMapper;
import com.hbc.api.trade.order.mapping.gen.bean.OrderBean;
import com.hbc.api.trade.order.mapping.gen.bean.TradeMoveInfo;
import com.hbc.api.trade.order.mapping.gen.bean.TradeMoveInfoExample;
import com.hbc.api.trade.ota.mapping.gen.TradeThirdOrderMapper;
import com.hbc.data.trade.transfer.mapping.thirdparty.gen.CtripOrderMapper;
import com.hbc.data.trade.transfer.mapping.thirdparty.gen.bean.CtripOrder;
import com.hbc.data.trade.transfer.mapping.thirdparty.gen.bean.CtripOrderCriteria;
@Component
public class Csed {
	private final static Logger log = LoggerFactory.getLogger(Csed.class);
	@Autowired
	CtripOrderMapper ctripOrderMapper;
	@Autowired
	TradeThirdOrderMapper tradeThirdOrderMapper;
	@Autowired
	OrderBeanMapper orderBeanMapper;
	@Autowired
	TradeMoveInfoMapper tradeMoveInfoMapper;
	@Autowired
	XiechengOrderService xiechengOrderService;
	public void getAllXiechenOrder() {
		CtripOrderCriteria ctripOrderCriteria = new CtripOrderCriteria();

		List<CtripOrder> qlist = ctripOrderMapper.selectByExample(ctripOrderCriteria);
		
		for(CtripOrder ctripOrder:qlist){
			try{
				TradeMoveInfoExample tradeMoveInfoExample = new TradeMoveInfoExample();
				tradeMoveInfoExample.createCriteria().andOrderIdEqualTo(ctripOrder.getVorderid());
				List<TradeMoveInfo>  mlist = tradeMoveInfoMapper.selectByExample(tradeMoveInfoExample);
				if(mlist.size()>0){
					OrderBean orderBean = orderBeanMapper.selectByPrimaryKey(mlist.get(0).getOrderSn());
					if(orderBean!=null){
						xiechengOrderService.moveXiechenOrder(ctripOrder,orderBean);
					}
				}
			}catch(Exception e){
				log.error("", e);
			}
		}
	}
}
