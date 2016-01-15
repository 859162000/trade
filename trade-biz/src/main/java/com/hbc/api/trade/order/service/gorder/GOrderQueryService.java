/**
 * @Author lukangle
 * @2015年11月28日@上午11:31:01
 */
package com.hbc.api.trade.order.service.gorder;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.hbc.api.trade.bdata.common.Page;
import com.hbc.api.trade.bdata.common.rsp.ListServiceRsp;
import com.hbc.api.trade.order.enums.TradeReturnCodeEnum;
import com.hbc.api.trade.order.exception.TradeException;
import com.hbc.api.trade.order.mapping.gen.OrderBeanMapper;
import com.hbc.api.trade.order.mapping.gen.bean.OrderBean;
import com.hbc.api.trade.order.mapping.gen.bean.OrderBeanExample;
import com.hbc.api.trade.order.mapping.gen.bean.OrderBeanExample.Criteria;
import com.hbc.api.trade.order.mapping.gen.bean.TradeDeliverGuide;
import com.hbc.api.trade.order.mapping.genx.xbean.GOrderQueryBean;
import com.hbc.api.trade.order.service.OrderQueryService;
import com.hbc.api.trade.order.service.TradeDeliverGuideService;
import com.hbc.api.trade.order.service.deliver.GuidDeliverOrderService;
import com.hbc.api.trade.order.service.gorder.enums.SearchType;
import com.hbc.api.trade.order.service.gorder.rsp.GOrderBean;
import com.hbc.api.trade.order.service.gorder.rsp.GOrderDetailBean;
@Component
public class GOrderQueryService {
	private static Logger logger = LoggerFactory.getLogger(GOrderQueryService.class);
	
	@Autowired private OrderBeanMapper 			orderBeanMapper;
	@Autowired private OrderQueryService 		orderQueryService;
	@Autowired private GOrderService 			gorderService;
	@Autowired private OrderIdsService 			orderIdsService;
	@Autowired private TradeDeliverGuideService tradeDeliverGuideService;
	@Autowired private GuidDeliverOrderService	guidDeliverOrderService;
	
	public  ListServiceRsp<GOrderBean> getGOrdersByGuidIdStatus(GOrderQueryBean queryBean,SearchType searchTypeE) {
		logger.info("GAPP查订单列表,inputs：searchTypeE=" + searchTypeE.value + "=" + searchTypeE.desc + " || " + JSON.toJSONString(queryBean));
		if(SearchType.TOBEDONE.equals(searchTypeE)){ 							// 待完成
			return getServicingList(queryBean);
		}else if(SearchType.SUCCESSCOMPLETE.equals(searchTypeE)){ 				// 已完成
			return getCompletedList(queryBean);
		}else if(SearchType.CANCLED.equals(searchTypeE)){ 						// 被取消
			return getCancelledList(queryBean);
		}else{
			throw new TradeException(TradeReturnCodeEnum.ORDER_PARAM_FAILED, "查询类型");
		}
	}
	
	public ListServiceRsp<GOrderBean> getServicingList(GOrderQueryBean queryBean) {
		OrderBeanExample orderBeanExample = new OrderBeanExample();
		Criteria searchCriteria = orderBeanExample.createCriteria();
		searchCriteria.andGuideIdEqualTo(queryBean.getGuideId());
		searchCriteria.andOrderStatusIn(GorderStatusService.unfinishedOrders());
		int totalSize = orderBeanMapper.countByExample(orderBeanExample);
		
		orderBeanExample.setPage(new Page(queryBean.getOffset(),queryBean.getLimit()));
		orderBeanExample.setOrderByClause(" service_time ");
		List<OrderBean> orderBeans = orderBeanMapper.selectByExample(orderBeanExample);
		List<String> orderNoList = orderIdsService.getOrderIdList(orderBeans);
		List<TradeDeliverGuide> deliverGuideList = guidDeliverOrderService.getDeliverGuideList(orderNoList, queryBean.getGuideId());
		
		List<GOrderBean> gorders =  gorderService.convertToGorderBean(orderBeans,deliverGuideList,queryBean.getGuideId(), orderNoList,false);
		ListServiceRsp<GOrderBean> listServiceRsp = new ListServiceRsp<GOrderBean>();
		listServiceRsp.setDatalist(gorders);
		listServiceRsp.setTsize(totalSize);
		logger.info("GAPP查进行中的列表：" + JSON.toJSONString(listServiceRsp));
		return listServiceRsp;
	}
	
	public ListServiceRsp<GOrderBean> getCancelledList(GOrderQueryBean queryBean) {
		OrderBeanExample orderBeanExample = new OrderBeanExample();
		Criteria searchCriteria = orderBeanExample.createCriteria();
		searchCriteria.andGuideIdEqualTo(queryBean.getGuideId());
		searchCriteria.andOrderStatusIn(GorderStatusService.cancelledOrders());
		int totalSize = orderBeanMapper.countByExample(orderBeanExample);
		
		orderBeanExample.setPage(new Page(queryBean.getOffset(),queryBean.getLimit()));
		orderBeanExample.setOrderByClause(" cancel_time desc ");
		List<OrderBean> orderBeans = orderBeanMapper.selectByExample(orderBeanExample);
		List<String> orderNoList = orderIdsService.getOrderIdList(orderBeans);
		List<TradeDeliverGuide> deliverGuideList = guidDeliverOrderService.getDeliverGuideList(orderNoList, queryBean.getGuideId());
		List<GOrderBean> gorders =  gorderService.convertToGorderBean(orderBeans,deliverGuideList,queryBean.getGuideId(), orderNoList,false);
		ListServiceRsp<GOrderBean> listServiceRsp = new ListServiceRsp<GOrderBean>();
		listServiceRsp.setDatalist(gorders);
		listServiceRsp.setTsize(totalSize);
		logger.info("GAPP查取消列表：" + JSON.toJSONString(listServiceRsp));
		return listServiceRsp;
	}
	
	public ListServiceRsp<GOrderBean> getCompletedList(GOrderQueryBean queryBean) {
		OrderBeanExample orderBeanExample = new OrderBeanExample();
		Criteria searchCriteria = orderBeanExample.createCriteria();
		searchCriteria.andGuideIdEqualTo(queryBean.getGuideId());
		searchCriteria.andOrderStatusIn(GorderStatusService.finishedOrders());
		int totalSize = orderBeanMapper.countByExample(orderBeanExample);
		
		orderBeanExample.setPage(new Page(queryBean.getOffset(),queryBean.getLimit()));
		orderBeanExample.setOrderByClause(" complete_time desc ");
		List<OrderBean> orderBeans = orderBeanMapper.selectByExample(orderBeanExample);
		List<String> orderNoList = orderIdsService.getOrderIdList(orderBeans);
		List<TradeDeliverGuide> deliverGuideList = guidDeliverOrderService.getDeliverGuideList(orderNoList, queryBean.getGuideId());
		List<GOrderBean> gorders =  gorderService.convertToCompletedBean(orderBeans,deliverGuideList,queryBean.getGuideId(), orderNoList,false);
		ListServiceRsp<GOrderBean> listServiceRsp = new ListServiceRsp<GOrderBean>();
		listServiceRsp.setDatalist(gorders);
		listServiceRsp.setTsize(totalSize);
		logger.debug("GAPP查已完成列表：" + JSON.toJSONString(listServiceRsp));
		return listServiceRsp;
	}

	public GOrderDetailBean getOrder(String guideId, String orderNo) {
		OrderBean orderBean = orderQueryService.getOrderByNo(orderNo);
		TradeDeliverGuide tradeDeliverGuide = tradeDeliverGuideService.getTradeDeliverGuide(guideId,orderNo);
		return gorderService.convertToGorderDetailBean(orderBean, tradeDeliverGuide, guideId,false);
	}

	/**
	 * 用getPurposefulOrder(String guideId, String orderNo)代替
	 * @param allocateGid
	 * @param orderNo
	 * @param guideId
	 * @return
	 */
	@Deprecated
	public GOrderDetailBean getPurposefulOrder(String allocateGid, String orderNo, String guideId) {
		OrderBean orderBean = orderQueryService.getOrderByNo(orderNo);
		TradeDeliverGuide tradeDeliverGuide = tradeDeliverGuideService.getTradeDeliverGuideByAllocateGid(allocateGid, orderNo, guideId);
		return gorderService.convertToGorderDetailBean(orderBean, tradeDeliverGuide);
	}

	/**
	 * @param guideId
	 * @param orderNo
	 * @return
	 */
	public GOrderDetailBean getPurposefulOrder(String guideId, String orderNo) {
		OrderBean orderBean = orderQueryService.getOrderByNo(orderNo);
		TradeDeliverGuide tradeDeliverGuide = tradeDeliverGuideService.getTradeDeliverGuide(guideId,orderNo);
		return gorderService.convertToNewGorderDetailBean(orderBean, tradeDeliverGuide);
	}
	
	
}
