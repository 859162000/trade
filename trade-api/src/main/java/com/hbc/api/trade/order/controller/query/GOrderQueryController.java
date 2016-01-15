/**
 * @Author lukangle
 * @2015年10月12日@上午10:28:45
 */
package com.hbc.api.trade.order.controller.query;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hbc.api.trade.bdata.common.rsp.ListServiceRsp;
import com.hbc.api.trade.bdata.common.rsp.ReturnResult;
import com.hbc.api.trade.order.controller.validator.OrderValidator;
import com.hbc.api.trade.order.mapping.genx.xbean.GOrderQueryBean;
import com.hbc.api.trade.order.mapping.genx.xbean.TradeDeliverGuideQueryBean;
import com.hbc.api.trade.order.service.TradeDeliverGuideService;
import com.hbc.api.trade.order.service.gorder.GOrderQueryService;
import com.hbc.api.trade.order.service.gorder.enums.SearchType;
import com.hbc.api.trade.order.service.gorder.rsp.GOrderBean;
import com.hbc.api.trade.order.service.gorder.rsp.GOrderDetailBean;
import com.hbc.api.trade.sec.TradeAccountContext;

@RestController
@RequestMapping("trade")
public class GOrderQueryController {

	@Autowired private TradeDeliverGuideService deliverGuideService;
	@Autowired private GOrderQueryService 		gorderQueryService;
	
	@Autowired
	TradeAccountContext tradeAccountContext;
	@RequestMapping(value = "v1.0/g/order/list", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public ReturnResult getOrders(Integer limit, Integer offset, Integer searchType, HttpServletRequest request) {

		OrderValidator.validateLimitAndOffset(limit, offset);
		SearchType searchTypeE = SearchType.getType(searchType);
		GOrderQueryBean gOrderQueryBean = new GOrderQueryBean();
		String guideId = tradeAccountContext.getUserId();
		OrderValidator.validateParamString(guideId, "导游ID");
		gOrderQueryBean.setGuideId(guideId);
		gOrderQueryBean.setLimit(limit);
		gOrderQueryBean.setOffset(offset);
		ListServiceRsp<GOrderBean> lrsp = gorderQueryService.getGOrdersByGuidIdStatus(gOrderQueryBean,searchTypeE);
		ReturnResult returnResult = new ReturnResult();
		returnResult.setData(lrsp.getDatalist(), lrsp.getTsize());
		return returnResult;
	}

	/**
	 * 
	 * @param orderNo
	 * @param onlyMissedOrder  废弃
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "v1.0/g/order/detail", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public ReturnResult getOrder(String orderNo, Boolean onlyMissedOrder, HttpServletRequest request) {
		OrderValidator.validateOrderNo(orderNo);
		String guideId = tradeAccountContext.getUserId();
		OrderValidator.validateParamString(guideId, "导游ID");
		GOrderDetailBean gOrderDetailBean = gorderQueryService.getOrder(guideId,orderNo);
		ReturnResult returnResult = new ReturnResult();
		returnResult.setData(gOrderDetailBean);
		return returnResult;
	}

	@RequestMapping(value = "v1.0/g/order/list/missed", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public ReturnResult getMissedOrders(TradeDeliverGuideQueryBean queryBean, HttpServletRequest request) {
		OrderValidator.validateLimitAndOffset(queryBean.getLimit(), queryBean.getOffset());
		String guideId = tradeAccountContext.getUserId();
		OrderValidator.validateParamString(guideId, "导游ID");
		queryBean.setGuideId(guideId);
		Map<String, Object> resultData = deliverGuideService.getGMissedOrders(queryBean);
		ReturnResult returnResult = new ReturnResult();
		returnResult.setData(resultData);
		return returnResult;
	}

	/**
	 * 推送到导游的新订单列表（发单列表）
	 * 
	 * @param queryBean
	 * @return
	 */
	@RequestMapping(value = "v1.0/g/order/list/purpose", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public ReturnResult getPurposefulOrders(TradeDeliverGuideQueryBean queryBean, HttpServletRequest request) {
		OrderValidator.validateLimitAndOffset(queryBean.getLimit(), queryBean.getOffset());
		String guideId = tradeAccountContext.getUserId();
		OrderValidator.validateParamString(guideId, "导游ID");
		queryBean.setGuideId(guideId);
		Map<String, Object> resultData = deliverGuideService.getPurposefulOrders(queryBean);
		ReturnResult returnResult = new ReturnResult();
		returnResult.setData(resultData);
		return returnResult;
	}

	/**
	 * 用getPurposefulOrder(orderNo)代替
	 * @param allocateGid
	 * @param orderNo
	 * @param request
	 * @return
	 */
	@Deprecated
	@RequestMapping(value = "v1.0/g/order/detail/purpose", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public ReturnResult getPurposefulOrder(String allocateGid, String orderNo, HttpServletRequest request) {
		String guideId = tradeAccountContext.getUserId();
		OrderValidator.validateDeliverGuide(allocateGid, orderNo, guideId);
		GOrderDetailBean gOrderDetailBean = gorderQueryService.getPurposefulOrder(allocateGid, orderNo, guideId);
		ReturnResult returnResult = new ReturnResult();
		returnResult.setData(gOrderDetailBean);
		return returnResult;
	}
	
	/**
	 * 所有G端新订单 详情 都使用该接口
	 * @param orderNo
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "v1.0/g/neworder/detail", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public ReturnResult getPurposefulOrder(String orderNo, HttpServletRequest request) {
		String guideId = tradeAccountContext.getUserId();
		OrderValidator.validateGuideId(guideId);
		OrderValidator.validateOrderNo(orderNo);
		GOrderDetailBean gOrderDetailBean = gorderQueryService.getPurposefulOrder(guideId, orderNo);
		ReturnResult returnResult = new ReturnResult();
		returnResult.setData(gOrderDetailBean);
		return returnResult;
	}

}
