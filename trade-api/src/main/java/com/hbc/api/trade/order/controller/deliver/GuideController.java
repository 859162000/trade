/**
 * @Author lukangle
 * @2015年11月5日@下午4:14:44
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

import com.hbc.api.trade.bdata.common.rsp.ReturnResult;
import com.hbc.api.trade.order.controller.validator.OrderValidator;
import com.hbc.api.trade.order.enums.third.CarClassEnum;
import com.hbc.api.trade.order.enums.third.CarTypeEnum;
import com.hbc.api.trade.order.mapping.gen.bean.OrderBean;
import com.hbc.api.trade.order.service.OrderQueryService;
import com.hbc.api.trade.third.GuideQueryService;
import com.hbc.api.trade.third.MisGuideService;
import com.hbc.api.trade.third.rsp.GuideRsp;

@RestController
@RequestMapping("trade")
public class GuideController {
	private final static Logger log = LoggerFactory.getLogger(GuideController.class);
	@Autowired
	GuideQueryService guideQueryService;
	@Autowired
	OrderQueryService orderQueryService;
	@Autowired
	MisGuideService misGuideService;

	@RequestMapping(value = "v1.0/e/order/guides", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public ReturnResult guides(@RequestParam(required = true) String orderNo, @RequestParam(required = true) Integer limit, @RequestParam(required = true) Integer offset, HttpServletRequest request,String guideNo,String guideName) {

		OrderValidator.validateOrderNo(orderNo);
		OrderBean orderBean = orderQueryService.getOrderByNo(orderNo);

		CarTypeEnum carTypeEnum = CarTypeEnum.getType(orderBean.getCarTypeId());
		CarClassEnum carClassEnum = CarClassEnum.getType(orderBean.getCarSeatNum());

		ReturnResult returnResult = new ReturnResult();
		int tnum = misGuideService.countGuidByCityGuideLevel(orderBean, carTypeEnum, carClassEnum,guideNo,guideName);
		List<GuideRsp> guides = misGuideService.getGuidByCityGuideLevel(orderBean, carTypeEnum, carClassEnum, limit, offset,guideNo,guideName);
		returnResult.setData(guides, tnum);
		return returnResult;
	}

}
