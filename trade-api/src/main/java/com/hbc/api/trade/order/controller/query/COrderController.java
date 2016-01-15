/**
 * @Author lukangle
 * @2015年10月7日@下午2:56:07
 */
package com.hbc.api.trade.order.controller.query;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hbc.api.trade.bdata.common.rsp.ReturnResult;
import com.hbc.api.trade.bdata.common.util.ParameterValidator;
import com.hbc.api.trade.bdata.common.util.TimeConverter;
import com.hbc.api.trade.order.controller.query.vo.AdditionalCostDetailBean;
import com.hbc.api.trade.order.controller.validator.OrderValidator;
import com.hbc.api.trade.order.enums.TradeReturnCodeEnum;
import com.hbc.api.trade.order.enums.order.GoodType;
import com.hbc.api.trade.order.enums.order.OrderType;
import com.hbc.api.trade.order.exception.TradeException;
import com.hbc.api.trade.order.mapping.gen.bean.OrderBean;
import com.hbc.api.trade.order.mapping.gen.bean.TradeAdditionalCost;
import com.hbc.api.trade.order.mapping.gen.bean.TradeAdditionalDetail;
import com.hbc.api.trade.order.service.AdditionalCostService;
import com.hbc.api.trade.order.service.OrderQueryService;
import com.hbc.api.trade.order.service.corder.COrderQueryService;
import com.hbc.api.trade.order.service.corder.rsp.COrderDetailBean;
import com.hbc.api.trade.sec.TradeAccountContext;
import com.hbc.api.trade.third.CouponServiceParty;
import com.hbc.api.trade.third.restful.IMService;

@RestController
@RequestMapping("trade")
public class COrderController {

	@Autowired
	private OrderQueryService orderQueryService;
	@Autowired
	private CouponServiceParty couponServiceParty;
	@Autowired
	private AdditionalCostService additionalCost;
	@Autowired
	private IMService imService;
	@Autowired
	private COrderQueryService cOrderQueryService;
	
	private final static Logger logger = LoggerFactory.getLogger(COrderController.class);

	@RequestMapping(value = "v1.0/c/order/cost/additional", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public ReturnResult findCostDetail(String orderNo) {
		String userId = tradeAccountContext.getUserId();
		OrderValidator.validateParamString(userId, "用户ID");
		OrderBean orderBean = cOrderQueryService.getOrderBean(orderNo);
		List<TradeAdditionalDetail> costDetailsList = additionalCost.getAdditionalCostDetails(orderNo);
		ReturnResult returnResult = new ReturnResult();

		if (costDetailsList == null || costDetailsList.size() == 0)
			return returnResult;

		List<AdditionalCostDetailBean> cAdditionalCostDetailList = new LinkedList<>();
		String unitForDay = "天", unitForMoney = "元", unitForDistance = "公里";
		String unitForTime = getTimeUnit(orderBean);
		for (TradeAdditionalDetail detail : costDetailsList) {
			AdditionalCostDetailBean cAdditionalCostDetail = new AdditionalCostDetailBean();
			cAdditionalCostDetail.setDailyDate(TimeConverter.formatDates(detail.getDailyDate()));
			cAdditionalCostDetail.setOverDay(detail.getOverDay() == null ? null : detail.getOverDay() + unitForDay);
			cAdditionalCostDetail.setOverDayPrice(detail.getOverDayPrice() == null ? null : detail.getOverDayPrice() + unitForMoney);
			cAdditionalCostDetail.setPrePaymentPrice(detail.getOtherFee1() == null ? null : detail.getOtherFee1() + unitForMoney);
			cAdditionalCostDetail.setOverDistance(detail.getOverDistance() == null ? null : detail.getOverDistance() + unitForDistance);
			cAdditionalCostDetail.setOverDistancePrice(detail.getOverDistancePrice() == null ? null : detail.getOverDistancePrice() + unitForMoney);
			cAdditionalCostDetail.setOverTime(detail.getOverTime() == null ? null : detail.getOverTime() + unitForTime);
			cAdditionalCostDetail.setOverTimePrice(detail.getOverTimePrice() == null ? null : detail.getOverTimePrice() + unitForMoney);
			cAdditionalCostDetail.setOverWaitTimeCost(detail.getOverWaitTimeCost() == null ? null : detail.getOverWaitTimeCost() + unitForMoney);
			cAdditionalCostDetail.setApplyfee(detail.getApplyfee() == null ? null : detail.getApplyfee() + unitForMoney);
			cAdditionalCostDetailList.add(cAdditionalCostDetail);
		}

		TradeAdditionalCost additionCostTotalBean = additionalCost.getAdditionalCost(orderNo);
		String applyfee = additionCostTotalBean.getApplyPrice() == null ? null : additionCostTotalBean.getApplyPrice() + unitForMoney;
		JSONObject json = new JSONObject();
		json.put("applyfee", applyfee);
		json.put("resultBean", cAdditionalCostDetailList);
		returnResult.setData(json);
		additionalCost.setAdditionalCostRead(orderNo);

		return returnResult;
	}

	/**
	 * @param orderBean
	 */
	private String getTimeUnit(OrderBean orderBean) {
		String timeUnit = "分钟";
		OrderType orderType = OrderType.getType(orderBean.getOrderType());
		switch(orderType) {
		case DAILY:
			timeUnit = "小时";
			break;
		case COMMENDATION:
			GoodType goodType = GoodType.getType(orderBean.getOrderGoodsType());
			switch(goodType) {
			case BigLongDistance:
			case CityVehiclesCar:
			case LessLongDistance:
				timeUnit = "小时";
				break;
			default:
				break;
			}
			break;
		default:
			break;
		}
		return timeUnit;
	}

	@Autowired
	TradeAccountContext tradeAccountContext;

	@RequestMapping(value = "v1.0/c/order/detail",
			method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public ReturnResult getOrder(String orderNo) {
		String userId = tradeAccountContext.getUserId();
		OrderValidator.validateParamString(userId, "用户ID");
		COrderDetailBean target = cOrderQueryService.getOrderDetailBean(userId, orderNo);
		ReturnResult returnResult = new ReturnResult();
		returnResult.setData(target);
		return returnResult;
	}

	@RequestMapping(value = "v1.0/c/order/list",
			method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public ReturnResult getOrders(@RequestParam(required = true) Integer searchType,
			@RequestParam(required = true) Integer limit,
			@RequestParam(required = true) Integer offset,
			HttpServletRequest request) {

		ParameterValidator.validateLimitAndOffset(limit, offset);
		String userId = tradeAccountContext.getUserId();
		OrderValidator.validateParamString(userId, "用户ID");
		
		Map<String, Object> data = cOrderQueryService.getOrders(userId, searchType, limit, offset);
		ReturnResult returnResult = new ReturnResult();
		returnResult.setData(data);
		logger.info("[Jongly] CAPP order list: " + JSON.toJSONString(returnResult));
		return returnResult;
	}

	/**
	 * 
	 * @param userId
	 * @return {data:{total, hasGuide, hasNewCoupon}, status:200 }
	 */
	@RequestMapping(value = "v1.0/c/order/home",
			method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public ReturnResult goToHomePage() {
		String userId = tradeAccountContext.getUserId();
		OrderValidator.validateParamString(userId, "用户ID");
		ReturnResult result = imService.imCustomerUnreadTotalSize(userId);
		JSONObject object = (JSONObject) result.getData();
		object.put("hasGuide", orderQueryService.getSizeOfOrderWhichHasGuideId(userId) > 0);
		object.put("newCouponSize", couponServiceParty.getNewCouponSize(userId));
		logger.info("[Jongly] go to CAPP home page: " + JSON.toJSONString(result));
		return result;
	}

	@RequestMapping(value = "v1.0/c/order/im/token/update", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public ReturnResult imTokenUpdate(@RequestParam String orderNo, HttpServletRequest request) {
		String token = imService.obtainIMToken(orderNo);
		if(token==null || token.length()<=0){
			throw new TradeException(TradeReturnCodeEnum.ORDER_IMTOKEN_EXCEPTION);
		}
		ReturnResult result = new ReturnResult();
		result.setData(token);
		return result;
	}
}
