
package com.hbc.api.trade.order.controller.opt;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hbc.api.trade.bdata.common.rsp.ReturnResult;
import com.hbc.api.trade.order.controller.validator.OrderValidator;
import com.hbc.api.trade.order.service.TradeDeliverGuideService;
import com.hbc.api.trade.sec.TradeAccountContext;

/**
 * 
 * @author Jongly Ran
 */
@RestController
@RequestMapping("trade")
public class GDeliveryOrderController {
	
	@Autowired private TradeDeliverGuideService deliverGuideService;
	@Autowired
	TradeAccountContext tradeAccountContext;
	@RequestMapping(value = "v1.0/g/deliveryorder/agree", 
			method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public ReturnResult agree(String allocateGid, String orderNo, HttpServletRequest request) {

		String guideId = tradeAccountContext.getUserId();
		OrderValidator.validateDeliverGuide(allocateGid, orderNo, guideId);
		deliverGuideService.agree(allocateGid, orderNo, guideId);
		return new ReturnResult();
	}
	
	@RequestMapping(value = "v1.0/g/deliveryorder/refuse", 
			method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public ReturnResult refuse(String allocateGid, String orderNo, String refuseReason, HttpServletRequest request) {

		String guideId = tradeAccountContext.getUserId();
		OrderValidator.validateDeliverGuide(allocateGid, orderNo, refuseReason, guideId);
		deliverGuideService.refuse(allocateGid, orderNo, refuseReason, guideId);
		return new ReturnResult();
	}
	
	@RequestMapping(value = "v1.0/g/deliveryorder/remove", 
			method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public ReturnResult removeMissedOrder(String allocateGid, String orderNo, HttpServletRequest request) {

		String guideId = tradeAccountContext.getUserId();
		OrderValidator.validateDeliverGuide(allocateGid, orderNo, guideId);
		deliverGuideService.removeMissedOrder(allocateGid, orderNo, guideId);
		return new ReturnResult();
	}
}
