/**
 * @Author lukangle
 * @2015年12月22日@下午9:44:25
 */
package com.hbc.data.trade.transfer.service.trade;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hbc.api.trade.bdata.common.HttpClientService;
import com.hbc.api.trade.bdata.common.rsp.ReturnResult;
import com.hbc.api.trade.order.mapping.gen.OrderBeanMapper;
import com.hbc.api.trade.order.mapping.gen.TradeMoveImtokenMapper;
import com.hbc.api.trade.order.mapping.gen.bean.OrderBean;
import com.hbc.api.trade.order.mapping.gen.bean.OrderBeanExample;
import com.hbc.api.trade.order.mapping.gen.bean.TradeMoveImtoken;
import com.hbc.api.trade.third.restful.ThirdRESTful;
import com.hbc.data.trade.transfer.mapping.hbcfinal.gen.bean.FinalOrderBean;
import com.hbc.data.trade.transfer.mapping.hbcfinal.gen.bean.FinalOrderBeanCriteria;
import com.hbc.data.trade.transfer.util.Page;

@Component
public class ImServiceMove {
	@Autowired
	TradeMoveImtokenMapper tradeMoveImtokenMapper;
	private final static Logger log = LoggerFactory.getLogger(ImServiceMove.class);

	public void setImToken(String orderSn) {
		try {
			if (tradeMoveImtokenMapper.selectByPrimaryKey(orderSn) == null) {
				String token = obtainIMToken(orderSn);
				TradeMoveImtoken tradeMoveImtoken = new TradeMoveImtoken();
				tradeMoveImtoken.setCreateTime(new Date());
				tradeMoveImtoken.setImtoken(token);
				tradeMoveImtoken.setOrderSn(orderSn);
				tradeMoveImtokenMapper.insert(tradeMoveImtoken);
			}
		} catch (Exception e) {
			log.error("获取token失败 插入DB失败");
		}
	}

	public TradeMoveImtoken getImToken(String orderSn) {
		return tradeMoveImtokenMapper.selectByPrimaryKey(orderSn);
	}

	@Autowired
	private HttpClientService httpClientService;
	@Autowired
	private ThirdRESTful thirdRESTful;

	public String obtainIMToken(String orderNo) {
		try {
			String responseText = httpClientService.sendReq("http://api2.huangbaoche.com/communication/v1.0/e/im/token?apply_type=2&apply_id=" + orderNo);
			log.info("获取订单[" + orderNo + "]评价导游信息：" + responseText);
			ReturnResult result = JSON.parseObject(responseText, ReturnResult.class); // not
																						// null
			if (result != null && result.getStatus() == 200) {
				JSONObject json = (JSONObject) result.getData(); // not null
				return json.getString("token");
			} else {
				log.error("获取[" + orderNo + "]IMToken失败，为了不影响主流程，容错。result: " + JSON.toJSONString(result));
			}
		} catch (Exception e) {
			log.error("获取[" + orderNo + "]IMToken失败，为了不影响主流程，容错。", e);
		}
		return null;
	}

	@Autowired
	OrderBeanMapper orderBeanMapper;

	public void setImOrder(String orderNo) {
		String imtok = obtainIMToken(orderNo);

		OrderBean orderBeanD = new OrderBean();
		orderBeanD.setOrderNo(orderNo);
		orderBeanD.setImToken(imtok);
		int optnum = orderBeanMapper.updateByPrimaryKeySelective(orderBeanD);
		if (optnum == 1) {
			log.info("成功 更新IMTOKEN " + orderBeanD.getOrderNo() + "   " + imtok);
		}
	}

	public int countAllFinalOrders() {
		OrderBeanExample finalOrderBeanCriteria = new OrderBeanExample();
		return orderBeanMapper.countByExample(finalOrderBeanCriteria);
	}

	public List<OrderBean> getAllFinalOrderBean(int limit, int offset) {
		OrderBeanExample finalOrderBeanCriteria = new OrderBeanExample();
		com.hbc.api.trade.bdata.common.Page page = new com.hbc.api.trade.bdata.common.Page(offset, limit);
		finalOrderBeanCriteria.setPage(page);
		List<OrderBean> orders = orderBeanMapper.selectByExample(finalOrderBeanCriteria);
		return orders;
	}

}
