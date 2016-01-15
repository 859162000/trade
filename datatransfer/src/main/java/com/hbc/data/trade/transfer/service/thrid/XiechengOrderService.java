/**
 * @Author lukangle
 * @2015年12月8日@下午4:11:40
 */
package com.hbc.data.trade.transfer.service.thrid;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hbc.api.trade.order.enums.ota.AgentChannelEnum;
import com.hbc.api.trade.order.mapping.gen.OrderBeanMapper;
import com.hbc.api.trade.order.mapping.gen.TradeMoveInfoMapper;
import com.hbc.api.trade.order.mapping.gen.bean.OrderBean;
import com.hbc.api.trade.ota.mapping.gen.TradeThirdOrderMapper;
import com.hbc.api.trade.ota.mapping.gen.bean.TradeThirdOrder;
import com.hbc.data.trade.transfer.mapping.thirdparty.gen.CtripOrderMapper;
import com.hbc.data.trade.transfer.mapping.thirdparty.gen.bean.CtripOrder;
import com.hbc.data.trade.transfer.mapping.thirdparty.gen.bean.CtripOrderCriteria;
import com.hbc.data.trade.transfer.service.trade.DOrderService;

@Component
public class XiechengOrderService {
	@Autowired
	CtripOrderMapper ctripOrderMapper;
	
	public CtripOrder getXiechenOrder(String orderSn) {
		CtripOrderCriteria ctripOrderCriteria = new CtripOrderCriteria();
		CtripOrderCriteria.Criteria criteria = ctripOrderCriteria.createCriteria();

		criteria.andVorderidEqualTo(orderSn);
		List<CtripOrder> qlist = ctripOrderMapper.selectByExample(ctripOrderCriteria);
		if (qlist.size() == 1) {
			return qlist.get(0);
		}
		return null;
	}
	@Autowired
	OrderBeanMapper orderBeanMapper;
	@Autowired
	TradeMoveInfoMapper tradeMoveInfoMapper;
	private final static Logger log = LoggerFactory.getLogger(DOrderService.class);

	
	@Autowired
	TradeThirdOrderMapper tradeThirdOrderMapper;
	public void moveXiechenOrder(CtripOrder qunaOrder,OrderBean orderBean){
		if(tradeThirdOrderMapper.selectByPrimaryKey(orderBean.getOrderNo())==null){
			TradeThirdOrder tradeThirdOrder = new TradeThirdOrder();
			tradeThirdOrder.setCreateTime(qunaOrder.getCreatedAt());
			tradeThirdOrder.setGuideConfirmTime(orderBean.getGuideAssignTime());
			tradeThirdOrder.setOrderConfirmTime(orderBean.getCreateTime());
			tradeThirdOrder.setOrderNo(orderBean.getOrderNo());
//			tradeThirdOrder.setOrderStatus(qunaOrder.getStatus());
			tradeThirdOrder.setPrice(orderBean.getPriceChannel());
//			tradeThirdOrder.setPriceMark(priceMark);
//			tradeThirdOrder.setSaleCode(saleCode);
			tradeThirdOrder.setSalePrice(orderBean.getPriceChannel());
			tradeThirdOrder.setThirdCarType(qunaOrder.getCartype());
			tradeThirdOrder.setThirdPartner(AgentChannelEnum.CTRIP_CHANNEL.value);
			tradeThirdOrder.setThirdTradeNo(qunaOrder.getVorderid());
			tradeThirdOrder.setUpdateTime(qunaOrder.getUpdatedAt());
			tradeThirdOrderMapper.insert(tradeThirdOrder);
			
			OrderBean record = new OrderBean();
			record.setOrderNo(orderBean.getOrderNo());
			record.setThirdTradeNo(qunaOrder.getCtriporderid());
			orderBeanMapper.updateByPrimaryKeySelective(record);
			
			log.info("成功插入"+orderBean.getOrderNo());
		}
	}
}
