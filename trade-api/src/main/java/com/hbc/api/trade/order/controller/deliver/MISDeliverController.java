/**
 * @Author lukangle
 * @2015年11月26日@下午5:24:55
 */
package com.hbc.api.trade.order.controller.deliver;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hbc.api.trade.bdata.common.rsp.ListServiceRsp;
import com.hbc.api.trade.bdata.common.rsp.ReturnResult;
import com.hbc.api.trade.order.controller.validator.OrderValidator;
import com.hbc.api.trade.order.enums.deliver.DeliverType;
import com.hbc.api.trade.order.mapping.gen.bean.OrderBean;
import com.hbc.api.trade.order.mapping.gen.bean.TradeOrderDeliver;
import com.hbc.api.trade.order.service.OrderQueryService;
import com.hbc.api.trade.order.service.deliver.OrderDeliverService;
import com.hbc.api.trade.order.service.mis.MisDeliverService;
import com.hbc.api.trade.order.service.mis.rsp.MisDeliverGuideVo;

@RestController
@RequestMapping("trade")
public class MISDeliverController {
	private final static Logger log = LoggerFactory.getLogger(GuideController.class);

	@Autowired
	MisDeliverService misDeliverService;

	@RequestMapping(value = "v1.0/e/deliver/guides", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public ReturnResult guides(@RequestParam(required = true) String orderNo, @RequestParam(required = true) Integer deliverType, @RequestParam(required = true) Integer limit, @RequestParam(required = true) Integer offset,
			String searchInfo) {
		OrderValidator.validateOrderNo(orderNo);
		ListServiceRsp<MisDeliverGuideVo> lrsp = misDeliverService.getDeliverOrderGuidesByType(orderNo, deliverType, searchInfo, limit, offset);
		ReturnResult returnResult = new ReturnResult();
		returnResult.setData(lrsp.getDatalist(), lrsp.getTsize());
		return returnResult;
	}
	
	@RequestMapping(value = "v1.0/e/deliver/dtabs", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public ReturnResult guides(@RequestParam(required = true) String orderNo, HttpServletRequest request){
		OrderValidator.validateOrderNo(orderNo);
		
		List<TradeOrderDeliver> delivers = misDeliverService.getOrderDelivers(orderNo);

		ReturnResult returnResult = new ReturnResult();
		returnResult.setData(delivers);
		return returnResult;
	}
	@Autowired
	OrderDeliverService orderDeliverService;
	@Autowired
	OrderQueryService orderQueryService;
	/**
	 * 取消重发
	 * @param orderNo
	 * @param deliverType
	 * @param optId
	 * @param optname
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "v1.0/e/deliver/msend", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public ReturnResult immediate(@RequestParam(required = true) String orderNo,@RequestParam(required = true) Integer deliverType,@RequestParam(required = true)String optId,
			@RequestParam(required = true)String optname, HttpServletRequest request){
		OrderValidator.validateOrderNo(orderNo);
		DeliverType deliverTypeEnum = DeliverType.getType(deliverType);
		OrderBean orderBean = orderQueryService.getOrderByNo(orderNo);
		orderDeliverService.addOrderDeliver(orderBean, deliverTypeEnum, optId, optname);
		ReturnResult returnResult = new ReturnResult();
		return returnResult;
	}
	
}
