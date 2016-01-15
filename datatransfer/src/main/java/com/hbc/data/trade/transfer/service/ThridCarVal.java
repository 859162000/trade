/**
 * @Author lukangle
 * @2015年12月27日@下午2:49:47
 */
package com.hbc.data.trade.transfer.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.hbc.api.trade.bdata.common.util.TimeConverter;
import com.hbc.api.trade.order.mapping.gen.OrderBeanMapper;
import com.hbc.api.trade.order.mapping.gen.bean.OrderBean;
import com.hbc.api.trade.order.mapping.gen.bean.OrderBeanExample;
import com.hbc.api.trade.order.service.OrderServiceTime;
import com.hbc.api.trade.ota.adaptor.CTripCarAdaptor;
import com.hbc.api.trade.ota.adaptor.CarTypeAdaptor;
import com.hbc.api.trade.ota.adaptor.CarTypeContext;
import com.hbc.api.trade.ota.adaptor.QunarCarAdaptor;
import com.hbc.api.trade.ota.mapping.gen.TradeThirdOrderMapper;
import com.hbc.api.trade.ota.mapping.gen.bean.TradeThirdOrder;

@Component
public class ThridCarVal {
	@Autowired
	OrderBeanMapper orderBeanMapper;
	@Autowired
	TradeThirdOrderMapper tradeThirdOrderMapper;

	/**
	 * QUNAR(1948212164, "QUNA渠道"),
	 * 
	 * CTRIP(1918029805, "携程渠道"),
	 * 
	 * @return
	 */
	public List<OrderBean> geXiechentAllOrder() {
		OrderBeanExample orderBeanExample = new OrderBeanExample();
		Date date = TimeConverter.toDate("2015-12-27", TimeConverter.END_WITH_DATE);
		List<Integer> ilist = new ArrayList<>();
		ilist.add(2001);
		ilist.add(2030);
		ilist.add(2050);
		ilist.add(2070);
		ilist.add(2080);
		orderBeanExample.createCriteria().andOrderChannelEqualTo(1918029805).andCreateTimeLessThan(date).andOrderStatusIn(ilist);
		return orderBeanMapper.selectByExample(orderBeanExample);
	}

	public List<OrderBean> getQunaAllOrder() {
		OrderBeanExample orderBeanExample = new OrderBeanExample();
		Date date = TimeConverter.toDate("2015-12-27", TimeConverter.END_WITH_DATE);
		List<Integer> ilist = new ArrayList<>();
		ilist.add(2001);
		ilist.add(2030);
		ilist.add(2050);
		ilist.add(2070);
		ilist.add(2080);
		orderBeanExample.createCriteria().andOrderChannelEqualTo(1948212164).andCreateTimeLessThan(date);
		orderBeanExample.setOrderByClause(" service_time asc ");
		return orderBeanMapper.selectByExample(orderBeanExample);
	}
	@Autowired
	OrderServiceTime orderServiceTime;
	public void print() {
		List<OrderBean> xlist = geXiechentAllOrder();
		for (OrderBean orderBean : xlist) {
			try {
				String otyp = orderBean.getCarTypeId() + "_" + orderBean.getCarSeatNum();

				TradeThirdOrder tradeThirdOrder = tradeThirdOrderMapper.selectByPrimaryKey(orderBean.getOrderNo());
				int otype = tradeThirdOrder.getThirdCarType();
				CarTypeAdaptor carTypeAdaptor = CarTypeContext.getInstance().parseThirdCarType(otype, CTripCarAdaptor.getInstance());
				Date cdate = orderServiceTime.getTimeZoneServiceTime(orderBean.getServiceCityId(), orderBean.getServiceTime());
				
				
				String zhque = carTypeAdaptor.getCarType() + "_" + carTypeAdaptor.getSeatCategory();
				if (!zhque.equals(otyp)) {
					System.out.println("订单号：【" + tradeThirdOrder.getOrderNo() + "】 服务时间 【"+TimeConverter.formatDate(cdate)+"】  携程价格【" + orderBean.getPriceChannel() + "】    导游价格【" + orderBean.getPriceGuide() + "】        导游编号【"
							+ orderBean.getGuideNo() + "】" + "导游姓名【" + orderBean.getGuideName() + "】 导游手机号：【" + orderBean.getGuideMobile() + "】" + "   错误的车型【" + otyp + "】 对的车型【" + zhque
							+ "】  第三方的车型【" + otype + "】 ");
					
					OrderBean record = new OrderBean();
					record.setOrderNo(orderBean.getOrderNo());
					record.setCarTypeId(carTypeAdaptor.getCarType());
					record.setCarSeatNum(carTypeAdaptor.getSeatCategory());
					orderBeanMapper.updateByPrimaryKeySelective(record);
					
					System.out.println(JSON.toJSONString(record) +"  更新成功");
				}
			} catch (Exception e) {
				System.out.println(orderBean.getOrderNo() + "发生错误，" + e.toString());
			}
		}

		List<OrderBean> xdlist = getQunaAllOrder();
		for (OrderBean orderBean : xdlist) {
			String otyp = orderBean.getCarTypeId() + "_" + orderBean.getCarSeatNum();

			TradeThirdOrder tradeThirdOrder = tradeThirdOrderMapper.selectByPrimaryKey(orderBean.getOrderNo());
			int otype = tradeThirdOrder.getThirdCarType();
			CarTypeAdaptor carTypeAdaptor = CarTypeContext.getInstance().parseThirdCarType(otype, QunarCarAdaptor.getInstance());

			String zhque = carTypeAdaptor.getCarType() + "_" + carTypeAdaptor.getSeatCategory();

			if (!zhque.equals(otyp)) {
				System.out.println("订单号：【" + tradeThirdOrder.getOrderNo() + "】   服务时间 【"+orderBean.getServiceTime()+"】   去哪【" + orderBean.getPriceChannel() + "】    导游价格【" + orderBean.getPriceGuide() + "】        导游编号【" + orderBean.getGuideNo()
						+ "】" + "导游姓名【" + orderBean.getGuideName() + "】 导游手机号：【" + orderBean.getGuideMobile() + "】" + "   错误的车型【" + otyp + "】 对的车型【" + zhque + "】  第三方的车型【" + otype + "】 ");
			}
		}
	}
}
