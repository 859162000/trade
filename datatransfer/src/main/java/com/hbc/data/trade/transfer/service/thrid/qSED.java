/**
 * @Author lukangle
 * @2015年12月25日@上午8:11:49
 */
package com.hbc.data.trade.transfer.service.thrid;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hbc.api.trade.order.mapping.gen.OrderBeanMapper;
import com.hbc.api.trade.order.mapping.gen.TradeMoveInfoMapper;
import com.hbc.api.trade.order.mapping.gen.bean.OrderBean;
import com.hbc.api.trade.order.mapping.gen.bean.TradeMoveInfo;
import com.hbc.api.trade.order.mapping.gen.bean.TradeMoveInfoExample;
import com.hbc.api.trade.ota.mapping.gen.TradeThirdOrderMapper;
import com.hbc.data.trade.transfer.mapping.hbcfinal.gen.FinalOrderBeanMapper;
import com.hbc.data.trade.transfer.mapping.hbcfinal.gen.bean.FinalOrderBean;
import com.hbc.data.trade.transfer.mapping.hbcfinal.gen.bean.FinalOrderBeanCriteria;
import com.hbc.data.trade.transfer.mapping.thirdparty.gen.QunaOrderMapper;
import com.hbc.data.trade.transfer.mapping.thirdparty.gen.bean.QunaOrder;
import com.hbc.data.trade.transfer.mapping.thirdparty.gen.bean.QunaOrderCriteria;

@Component
public class qSED {
	private final static Logger log = LoggerFactory.getLogger(qSED.class);
	@Autowired
	QunaOrderMapper qunaOrderMapper;
	@Autowired
	TradeThirdOrderMapper tradeThirdOrderMapper;
	@Autowired
	OrderBeanMapper orderBeanMapper;
	@Autowired
	TradeMoveInfoMapper tradeMoveInfoMapper;
	@Autowired
	QunaOrderService qunaOrderService;
	@Autowired
	FinalOrderBeanMapper finalOrderBeanMapper;

	public void getAllXiechenOrder() {
		QunaOrderCriteria ctripOrderCriteria = new QunaOrderCriteria();

		List<QunaOrder> qlist = qunaOrderMapper.selectByExample(ctripOrderCriteria);

		for (QunaOrder ctripOrder : qlist) {
			try {
				log.info("开始迁移订单[" + ctripOrder.getVorderid() + "]");
				FinalOrderBeanCriteria finalOrderBeanCriteria = new FinalOrderBeanCriteria();
				finalOrderBeanCriteria.createCriteria().andOrdersnEqualTo(ctripOrder.getVorderid());
				List<FinalOrderBean> flist = finalOrderBeanMapper.selectByExample(finalOrderBeanCriteria);
				if (flist.size() == 1) {
					FinalOrderBean finalOrderBean = flist.get(0);

					TradeMoveInfoExample tradeMoveInfoExample = new TradeMoveInfoExample();
					tradeMoveInfoExample.createCriteria().andOrderIdEqualTo(finalOrderBean.getOrderid());
					List<TradeMoveInfo> mlist = tradeMoveInfoMapper.selectByExample(tradeMoveInfoExample);
					if (mlist.size() > 0) {
						OrderBean orderBean = orderBeanMapper.selectByPrimaryKey(mlist.get(0).getOrderSn());
						qunaOrderService.moveQunaOrder(ctripOrder, orderBean);
					}else{
						log.error(ctripOrder.getVorderid() + "@@ffff@@");
					}
				} else {
					log.error(ctripOrder.getVorderid() + "@@@@");
				}
			} catch (Exception e) {
				log.error("", e);
			}
		}
	}
}
