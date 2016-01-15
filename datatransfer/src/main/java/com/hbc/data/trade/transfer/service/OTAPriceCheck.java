/**
 * @Author lukangle
 * @2016年1月4日@下午6:47:57
 */
package com.hbc.data.trade.transfer.service;

import java.math.BigDecimal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hbc.api.trade.order.mapping.gen.OrderBeanMapper;
import com.hbc.api.trade.order.mapping.gen.bean.OrderBean;
import com.hbc.api.trade.ota.mapping.gen.TradeThirdOrderMapper;
import com.hbc.api.trade.ota.mapping.gen.bean.TradeThirdOrder;
import com.hbc.data.trade.transfer.mapping.hbcfinal.gen.FinalOrderBeanMapper;
import com.hbc.data.trade.transfer.mapping.hbcfinal.gen.bean.FinalOrderBean;
import com.hbc.data.trade.transfer.mapping.hbcfinal.gen.bean.FinalOrderBeanCriteria;
import com.hbc.data.trade.transfer.mapping.thirdparty.gen.CtripOrderMapper;
import com.hbc.data.trade.transfer.mapping.thirdparty.gen.QunaOrderMapper;
import com.hbc.data.trade.transfer.mapping.thirdparty.gen.bean.CtripOrder;
import com.hbc.data.trade.transfer.mapping.thirdparty.gen.bean.CtripOrderCriteria;
import com.hbc.data.trade.transfer.mapping.thirdparty.gen.bean.QunaOrder;
import com.hbc.data.trade.transfer.mapping.thirdparty.gen.bean.QunaOrderCriteria;
import com.hbc.data.trade.transfer.util.Page;

@Component
public class OTAPriceCheck {
	private final static Logger log = LoggerFactory.getLogger(OTAPriceCheck.class);
	@Autowired
	CtripOrderMapper ctripOrderMapper;
	@Autowired
	QunaOrderMapper qunaOrderMapper;
	@Autowired
	FinalOrderBeanMapper finalOrderBeanMapper;
	@Autowired
	OrderBeanMapper orderBeanMapper;
	@Autowired
	TradeThirdOrderMapper tradeThirdOrderMapper;
	public void startToSycQuna() {
		QunaOrderCriteria qunaOrderCriteria = new QunaOrderCriteria();
//		qunaOrderCriteria.createCriteria().andVorderidEqualTo("J1506118941");
		int cnum = qunaOrderMapper.countByExample(qunaOrderCriteria);
		int fnum = 1000;
		int tnum = (cnum / fnum) + 1;
		for (int i = 0; i < tnum; i++) {
			Page page = new Page(tnum * i, fnum);
			qunaOrderCriteria.setPage(page);
			log.info("开始同步去哪第【"+tnum * i+"】到【"+(tnum * i+fnum)+"】..............................");
			List<QunaOrder> qlist = qunaOrderMapper.selectByExample(qunaOrderCriteria);
			
			for(QunaOrder qunaOrder : qlist){
				try{
					Double price = qunaOrder.getTotalprice().doubleValue();
					String vorderId = qunaOrder.getVorderid();
					
					FinalOrderBeanCriteria finalOrderBeanCriteria = new FinalOrderBeanCriteria();
					finalOrderBeanCriteria.createCriteria().andOrdersnEqualTo(vorderId);
					FinalOrderBean finalOrderBean = finalOrderBeanMapper.selectByExample(finalOrderBeanCriteria).get(0);
					
					String newOrderSN = "";
					if (finalOrderBean.getOrderid().length() == 18) {
						newOrderSN = finalOrderBean.getOrderid().substring(15, 16);
					}
					String orderNo = finalOrderBean.getOrdersn() + newOrderSN;
					
					updatePriceChannel(orderNo,price);
				}catch(Exception e){
					log.error("",e);
				}
			}
		}
	}
	
	
	public void startToSycCtrl() {
		CtripOrderCriteria ctripOrderCriteria = new CtripOrderCriteria();
//		151224001325717169
//		ctripOrderCriteria.createCriteria().andVorderidEqualTo("151224001325717169");
		int cnum = ctripOrderMapper.countByExample(ctripOrderCriteria);
		int fnum = 1000;
		int tnum = (cnum / fnum) + 1;
		for (int i = 0; i < tnum; i++) {
			Page page = new Page(tnum * i, fnum);
			ctripOrderCriteria.setPage(page);
			log.info("开始同步第【"+tnum * i+"】到【"+(tnum * i+fnum)+"】..............................");
			List<CtripOrder> qlist = ctripOrderMapper.selectByExample(ctripOrderCriteria);
			////151224001325717169
			for(CtripOrder ctripOrder : qlist){
				try{
					Double price = ctripOrder.getTotalprice().doubleValue();
					String corderId = ctripOrder.getCtriporderid();
					String vorderId = ctripOrder.getVorderid();
					
					FinalOrderBean finalOrderBean = finalOrderBeanMapper.selectByPrimaryKey(vorderId);
					
					String newOrderSN = "";
					if (finalOrderBean.getOrderid().length() == 18) {
						newOrderSN = finalOrderBean.getOrderid().substring(15, 16);
					}
					String orderNo = finalOrderBean.getOrdersn() + newOrderSN;
					BigDecimal dd = ctripOrder.getSaleprice();
					if(dd==null){
						updateTrird(orderNo,corderId,null,0d);
					}else{
						updateTrird(orderNo,corderId,ctripOrder.getSalecode(),dd.doubleValue());
					}
					updatePriceChannel(orderNo,price);
				}catch(Exception e){
					log.error("",e);
				}
			}
		}
	}
	public void updateTrird(String orderNo,String torderNo,String saleCode,Double salePrice){
		TradeThirdOrder tradeThirdOrder = new TradeThirdOrder();
		tradeThirdOrder.setOrderNo(orderNo);
		tradeThirdOrder.setThirdTradeNo(torderNo);
		tradeThirdOrder.setSaleCode(saleCode);
		tradeThirdOrder.setSalePrice(salePrice);
		int iptnum = tradeThirdOrderMapper.updateByPrimaryKeySelective(tradeThirdOrder);
		if(iptnum==1){
			log.info("成功迁移"+orderNo+"将第三方订单号改成【"+torderNo+"】");
		}else{
			log.info("失败迁移"+orderNo+"");
		}
	}
	public void updatePriceChannel(String orderNo, Double priceChannel) {
		OrderBean record = new OrderBean();
		record.setOrderNo(orderNo);
		record.setPriceChannel(priceChannel);
		int iptnum = orderBeanMapper.updateByPrimaryKeySelective(record);
		if(iptnum==1){
			log.info("成功迁移"+orderNo+"将价格换成【"+priceChannel+"】");
		}else{
			log.info("失败迁移"+orderNo+"");
		}
	}
}
