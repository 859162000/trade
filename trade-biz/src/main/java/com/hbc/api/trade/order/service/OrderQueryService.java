/**
 * @Author lukangle
 * @2015年10月7日@下午3:35:21
 */
package com.hbc.api.trade.order.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.hbc.api.trade.TradeFinalStr;
import com.hbc.api.trade.bdata.common.Page;
import com.hbc.api.trade.bdata.common.rsp.ListServiceRsp;
import com.hbc.api.trade.bdata.common.util.DoubleUtil;
import com.hbc.api.trade.order.enums.TradeReturnCodeEnum;
import com.hbc.api.trade.order.enums.order.OrderSource;
import com.hbc.api.trade.order.enums.order.OrderStatus;
import com.hbc.api.trade.order.enums.order.PriceHistoryOpType;
import com.hbc.api.trade.order.enums.order.SystemCommentStatus;
import com.hbc.api.trade.order.enums.order.UserCommentStatus;
import com.hbc.api.trade.order.exception.TradeException;
import com.hbc.api.trade.order.mapping.gen.OrderBeanMapper;
import com.hbc.api.trade.order.mapping.gen.bean.OrderBean;
import com.hbc.api.trade.order.mapping.gen.bean.OrderBeanExample;
import com.hbc.api.trade.order.mapping.gen.bean.OrderBeanExample.Criteria;
import com.hbc.api.trade.order.mapping.gen.bean.TradeAdditionalCost;
import com.hbc.api.trade.order.mapping.gen.bean.TradeAdditionalDetail;
import com.hbc.api.trade.order.mapping.genx.OrderBeanMapperEnhance;
import com.hbc.api.trade.order.mapping.genx.xbean.GOrderQueryBean;
import com.hbc.api.trade.order.mapping.genx.xbean.PaymentDetail;
import com.hbc.api.trade.order.service.gorder.enums.SearchType;
import com.hbc.api.trade.pay.enums.PayStatus;
import com.hbc.api.trade.pay.mapping.gen.bean.TradePayment;
import com.hbc.api.trade.pay.mapping.gen.bean.TradeRefund;
import com.hbc.api.trade.pay.service.PaymentService;
import com.hbc.api.trade.pay.service.RefundService;
import com.hbc.api.trade.third.CouponServiceParty;
import com.hbc.api.trade.third.restful.PriceRESTfulService;
import com.hbc.api.trade.third.vo.CouponVo;
@Component
public class OrderQueryService {
	@Autowired private OrderBeanMapper 			orderBeanMapper;
	@Autowired private OrderBeanMapperEnhance 	orderBeanMapperEnhance;
	
	@Autowired private CouponServiceParty 		couponServiceParty;
	@Autowired private RefundService			refundService;
	@Autowired private PriceRESTfulService		priceRESTfulService;
	@Autowired private PaymentService			paymentService;
	@Autowired private AdditionalCostService 	additionalCost;
	@Autowired private TradePriceHistoryService tradePriceHistoryService;
	
	
	public OrderBean getOrderByNo(String orderNo) {
		OrderBean orderBean = orderBeanMapper.selectByPrimaryKey(orderNo);
		if(orderBean==null){
			throw new TradeException(TradeReturnCodeEnum.ORDER_NOTEXIST, orderNo);
		}
		return orderBean;
	}
	
	public List<OrderBean> getMissOrdersByOrderNOs(List<String> orderNoList) {
		OrderBeanExample example = new OrderBeanExample();
		Criteria criteria = example.createCriteria();
		if(orderNoList != null && orderNoList.size() > 0) {
			criteria.andOrderNoIn(orderNoList);
		}
		example.setOrderByClause("service_time");
		return orderBeanMapper.selectByExample(example);
	}
	
	
	public List<OrderBean> getOrderByFilght(String flightRegistId) {
		OrderBeanExample example = new OrderBeanExample();
		OrderBeanExample.Criteria criteria = example.createCriteria();
		criteria.andFlightRegisterIdEqualTo(flightRegistId);
		criteria.andOrderStatusEqualTo(OrderStatus.PAYSUCCESS.value);
		criteria.andGuideIdNotEqualTo(TradeFinalStr.defaultGuideId);
		criteria.andGuideIdIsNotNull();
		return orderBeanMapper.selectByExample(example);
	}
	
	public int getOrdersByOrderNOsTotalSize(List<String> orderNoList) {
		OrderBeanExample example = new OrderBeanExample();
		if(orderNoList != null && orderNoList.size() > 0) {
			example.createCriteria().andOrderNoIn(orderNoList);
		}
		return orderBeanMapper.countByExample(example);
	}
	
	public List<OrderBean> getOrdersByUserId(String uid){
		//校验用户存在
		OrderBeanExample orderBeanExample = new OrderBeanExample();
		Criteria criteria = orderBeanExample.createCriteria();
		
		criteria.andUserIdEqualTo(uid);
		return orderBeanMapper.selectByExample(orderBeanExample);
	}
	
	public ListServiceRsp<OrderBean> getOrdersByUserIdStatus(String uid,List<Integer> status, Boolean onlyForCAPP, SearchType searchType, int limit, int offset){
		ListServiceRsp<OrderBean> listServiceRsp = new ListServiceRsp<OrderBean>();
		List<String> excludeOrderNoList = new LinkedList<>();
		if(onlyForCAPP) {
			OrderBeanExample excludeExample = new OrderBeanExample();
			Criteria excludeCondition = excludeExample.createCriteria();
			excludeCondition.andUserIdEqualTo(uid).andOrderSourceEqualTo(OrderSource.GDS.value).andOrderStatusEqualTo(OrderStatus.INITSTATE.value);
			List<OrderBean> excludeList = orderBeanMapper.selectByExample(excludeExample);
			
			for(OrderBean order : excludeList) {
				excludeOrderNoList.add(order.getOrderNo());
			}
		}
		
		OrderBeanExample example = new OrderBeanExample();
		Criteria condition = example.createCriteria();
		condition.andOrderStatusIn(status).andUserIdEqualTo(uid);
		if(onlyForCAPP && excludeOrderNoList.size() > 0) {
			condition.andOrderNoNotIn(excludeOrderNoList);
		}
		switch(searchType) {
		case CANCLED:
			example.setOrderByClause("cancel_time desc");
			break;
		case SUCCESSCOMPLETE:
			example.setOrderByClause("service_time desc");
			break;
		case TOBEDONE:
			example.setOrderByClause("service_time asc");
			break;
		}
		
		listServiceRsp.setTsize(orderBeanMapper.countByExample(example));
		Page page = new Page(offset, limit);
		example.setPage(page);
		listServiceRsp.setDatalist(orderBeanMapper.selectByExample(example));
		
		return listServiceRsp;
	}
	
	public OrderBean getSerialOrder(String serialOrderNo) {
		return orderBeanMapper.selectByPrimaryKey(serialOrderNo);
	}
	
	/**
	 * @param guideId
	 * @param orderStatus
	 * @return
	 */
	public int selectByGuideIdAndOrderStatusTotalSize(GOrderQueryBean gOrderQueryBean) {
		return orderBeanMapperEnhance.selectByGuideIdAndOrderStatusTotalSize(gOrderQueryBean);
	}
	
	/**
	 * 获取已获用户评论的订单数量
	 * @param orderBean
	 * @return
	 */
	public int getAppraisedSizeByUser(OrderBean orderBean) {
		OrderBeanExample example = new OrderBeanExample();
		example.createCriteria().andGuideIdEqualTo(orderBean.getGuideId()).andUserCommentStatusEqualTo(UserCommentStatus.SCORED.value);
		return orderBeanMapper.countByExample(example );
	}
	
	/**
	 * 获取已获系统评论的订单数量
	 * @param orderBean
	 * @return
	 */
	public int getAppraisedSizeBySystem(OrderBean orderBean) {
		OrderBeanExample example = new OrderBeanExample();
		example.createCriteria().andGuideIdEqualTo(orderBean.getGuideId())
			.andUserCommentStatusEqualTo(SystemCommentStatus.SCORED.value);
		return orderBeanMapper.countByExample(example );
	}
	
	/**
	 * 获取未评价的订单数量
	 * @param orderBean
	 * @return
	 */
	public int getUnvaluedSizeByUser(OrderBean orderBean) {
		OrderBeanExample example = new OrderBeanExample();
		example.createCriteria().andGuideIdEqualTo(orderBean.getGuideId())
			.andUserCommentStatusEqualTo(UserCommentStatus.UNSCORED.value)
			.andUserCommentStatusEqualTo(SystemCommentStatus.UNSCORED.value);
		return orderBeanMapper.countByExample(example );
	}
	
	/**
	 * 获取用户订单中已有导游接单的数量
	 * @param userId
	 * @return
	 */
	public int getSizeOfOrderWhichHasGuideId(String userId) {
		OrderBeanExample example = new OrderBeanExample();
		example.createCriteria().andUserIdEqualTo(userId).andGuideIdNotEqualTo(TradeFinalStr.defaultGuideId);
		return orderBeanMapper.countByExample(example);
	}
	
	/**
	 * @param orderNo
	 * @param order
	 * @return
	 */
	public PaymentDetail getOrderPaymentDetail(String orderNo) {
		OrderBean order = getOrderByNo(orderNo);
		if(order == null) return null;
		
		PaymentDetail paymentDetail = new PaymentDetail();
//		paymentDetail.setCouponPrice(order.getCoupPriceInfo());
		paymentDetail.setPriceBase(order.getPriceBase());
		paymentDetail.setPriceChannel(order.getPriceChannel());
		paymentDetail.setPriceGuide(order.getPriceGuide());
		paymentDetail.setPriceReward(order.getPriceReward());
		paymentDetail.setPriceTicket(order.getPriceTicket());
		
		setActualPayment(orderNo, paymentDetail);
		setCouponInfo(order, paymentDetail);
		setRefundInfo(order, paymentDetail);
		setPriceChangeInfo(orderNo, paymentDetail);
		setDiscountInfo(order, paymentDetail);
		setAdditionalCost(order, paymentDetail);
		return paymentDetail;
	}
	

	/**
	 * @param orderNo
	 * @param target
	 */
	private void setAdditionalCost(OrderBean orderBean, PaymentDetail target) {
		TradeAdditionalCost additionalCostBean = additionalCost.getAdditionalCost(orderBean.getOrderNo());
		if(additionalCostBean != null) {
			target.setOverPrice(additionalCostBean.getApplyPrice());
			 List<TradeAdditionalDetail> additionalCostDetail = additionalCost.getAdditionalCostDetails(orderBean.getOrderNo());
			 target.setAdditionalCostDetails(additionalCostDetail);
		} 
	}
	
	/**
	 * @param order
	 * @param paymentDetail
	 */
	private void setDiscountInfo(OrderBean order, PaymentDetail paymentDetail) {
		JSONObject discountInfo = priceRESTfulService.getPaymentDiscount(order.getOrderNo());
		if(discountInfo != null) {
			Object priceChannelCPlus 	= discountInfo.get("channelFloat");
			Object saleProfit 			= discountInfo.get("saleFloat");
			Object urgentOrderPlus 		= discountInfo.get("gurgentFloat");
			paymentDetail.setPriceChannelCPlus(priceChannelCPlus != null ? priceChannelCPlus + "%" : null);
			paymentDetail.setSaleProfit(saleProfit != null ? saleProfit + "%" : null);
			paymentDetail.setUrgentOrderPlus(urgentOrderPlus != null ? urgentOrderPlus + "%" : null);
		}
	}
	
	/**
	 * @param paymentDetail
	 */
	private void setPriceChangeInfo( String orderNo, PaymentDetail paymentDetail) {
		String priceGuideDiscount = tradePriceHistoryService.getPercentForPriceChanges(PriceHistoryOpType.DELIVER_GUIDE_LEVEL, orderNo);
		String serialDiscount = tradePriceHistoryService.getPercentForPriceChanges(PriceHistoryOpType.SER_ORDER, orderNo);
		paymentDetail.setPriceGuideDiscount(priceGuideDiscount);
		paymentDetail.setSerialDiscount(serialDiscount);
	}
	
	/**
	 * @param order
	 * @param paymentDetail
	 */
	private void setRefundInfo(OrderBean order, PaymentDetail paymentDetail) {
		List<TradeRefund> refundList = refundService.getAllRefunds(order.getOrderNo());
		if(refundList != null && refundList.size() > 0 ) {
			Double refundPrice = 0.0;
			for(TradeRefund refund : refundList) {
				refundPrice += ( refund.getRefundMoney() != null ? refund.getRefundMoney() : 0.0);
			}
			paymentDetail.setRefundToCustomer(refundPrice);
			paymentDetail.setRefundToGuide(refundPrice);
		}
	}
	
	/**
	 * @param order
	 * @param paymentDetail
	 */
	private void setCouponInfo(OrderBean order, PaymentDetail paymentDetail) {
		if(OrderStatus.INITSTATE.equals(order.getOrderStatus())){
			CouponVo couponVo = couponServiceParty.getPYCoupByOrderDetail(order.getOrderNo(),order.getPriceChannel(), order.getUserId());
			if(couponVo != null) {
				/**couponVo.getActualPrice() 券抵用后 实付多少钱  2015 1213 和券接口人确认已经**/
				Double coupPrice = DoubleUtil.subtractionDouble(order.getPriceChannel(), couponVo.getActualPrice());
				paymentDetail.setCouponPrice(coupPrice);
			}
		}
	}
	
	/**
	 * @param orderNo
	 * @param paymentDetail
	 */
	private void setActualPayment(String orderNo, PaymentDetail paymentDetail) {
		TradePayment payment = paymentService.queryTradePaymentByOrderNoNoexp(orderNo, PayStatus.SUCCESS);
		if(payment != null) {
			paymentDetail.setActualPay(payment.getPayActual());
			paymentDetail.setCouponPrice(payment.getCoupPay());
		}
	}
	
	public int getServiceCount(String guideId, List<Integer> orderStatusList) {
		OrderBeanExample example = new OrderBeanExample();
		Criteria criteria = example.createCriteria().andGuideIdEqualTo(guideId);
		if(orderStatusList != null && orderStatusList.size() > 0) {
			criteria.andOrderStatusIn(orderStatusList );
		}
		return orderBeanMapper.countByExample(example );
	}
}
