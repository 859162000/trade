/**
 * @Author lukangle
 * @2015年10月16日@上午11:33:20
 */
package com.hbc.api.trade.pay.getway.alibaba;

import java.util.List;

import kafka.log.Log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.hbc.api.gateway.alizhifu.GAliPayService;
import com.hbc.api.trade.bdata.common.HttpClientService;
import com.hbc.api.trade.bdata.common.util.DoubleUtil;
import com.hbc.api.trade.order.enums.order.OrderStatus;
import com.hbc.api.trade.order.mapping.gen.bean.OrderBean;
import com.hbc.api.trade.order.service.OrderQueryService;
import com.hbc.api.trade.order.service.OrderService;
import com.hbc.api.trade.pay.enums.GetWayEnum;
import com.hbc.api.trade.pay.enums.PayStatus;
import com.hbc.api.trade.pay.exception.PayException;
import com.hbc.api.trade.pay.exception.PayReturnCodeEnum;
import com.hbc.api.trade.pay.mapping.gen.bean.TradePayment;
import com.hbc.api.trade.pay.service.CoupPayService;
import com.hbc.api.trade.pay.service.PayTimeService;
import com.hbc.api.trade.pay.service.PaymentService;
import com.hbc.api.trade.third.CouponServiceParty;
import com.hbc.api.trade.third.TestPayVal;
import com.hbc.api.trade.third.vo.CoupParm;
/**
 * 阿里 直接到账 支付 支付接口
 */
@Component
public class AliPayService {
	@Autowired
	OrderQueryService orderQueryService;
	
	private final static Logger logger = LoggerFactory.getLogger(OrderService.class);
	@Autowired
	PaymentService paymentService;
	
	private String subjectStr = "皇包车订单支付";
	@Autowired
	GAliPayService aliPayService;
	@Autowired
	PayTimeService payTimeService;
	@Autowired
	TestPayVal testPayVal;
	@Transactional
	public String getWebPayUrl(String orderNo,String userId,double actualPrice,String coupId) {
		OrderBean orderBean = orderQueryService.getOrderByNo(orderNo);
		payTimeService.payTimeValide(orderBean);
		OrderStatus orderStatus = OrderStatus.getStatus(orderBean.getOrderStatus());
		if(!OrderStatus.INITSTATE.equals(orderStatus)){
			throw new PayException(PayReturnCodeEnum.PAYSUCCESS_NOPAY_CHANGED,orderNo);
		}
		boolean isR = false;
		boolean isLockCoup = false;
		CoupParm coupParm = null;
		if(!StringUtils.isEmpty(coupId)){
			coupParm = couponServiceParty.getCoupPriceInfo(coupId,orderNo,userId,orderBean.getPriceChannel());
			isR = actualPrice>0 && orderBean.getPriceChannel() != null && coupParm.getcActualPrice()==actualPrice;
			if(isR){
				couponServiceParty.lockCoup(coupId,orderNo);
				isLockCoup = true;
			}
		}else{
			isR = actualPrice>0 && orderBean.getPriceChannel() == actualPrice;
		}
		
		if(isR){
			TradePayment tradePayment = paymentService.addTradePayment(orderBean, GetWayEnum.ALI, actualPrice, subjectStr, orderBean.getUserAccount(),PayStatus.INITED);
			if(isLockCoup){
				tradePayment.setCoupId(coupId);
				Double cp = DoubleUtil.subtractionDouble(orderBean.getPriceChannel(), actualPrice);
				tradePayment.setCoupPay(cp);
				tradePayment.setCouponInfo(coupParm.getCoupInfo());
			}
			if(testPayVal.isTestByBeijing(orderBean)){
				logger.info(orderBean.getOrderNo()+" 测试订单  价格强制修改成 0.01");
				actualPrice = 0.01d;
			}
			String rurl = aliPayService.getWebPayUrl(tradePayment.getPayNo(), actualPrice);
			return rurl;
		}else{
			logger.error("priceChannel > 0 && orderBean.getPriceChannel() != null && orderBean.getPriceChannel().equals(priceChannel) = false");
			logger.error("priceChannel="+actualPrice+",orderBean.getPriceChannel()=" + orderBean.getPriceChannel());
			throw new PayException(PayReturnCodeEnum.PAY_PRICECHANNEL_CHANGED,orderBean.getPriceChannel());
		}
	}
	@Autowired
	HttpClientService  httpClientService;
	@Autowired
	CouponServiceParty couponServiceParty;
	@Autowired
	CoupPayService coupPayService;
	@Transactional
	public String getMobilePayUrl(String orderNo,double actualPrice,String userId,String appid,String appenv,String coupId){
		OrderBean orderBean = orderQueryService.getOrderByNo(orderNo);
		payTimeService.payTimeValide(orderBean);
		OrderStatus orderStatus = OrderStatus.getStatus(orderBean.getOrderStatus());
		if(!OrderStatus.INITSTATE.equals(orderStatus)){
			throw new PayException(PayReturnCodeEnum.PAYSUCCESS_NOPAY_CHANGED,orderNo);
		}
		boolean isR = false;
		boolean isLockCoup = false;
		CoupParm coupParm = null;
		if(!StringUtils.isEmpty(coupId)){
			coupParm = couponServiceParty.getCoupPriceInfo(coupId,orderNo,userId,orderBean.getPriceChannel());
			isR = actualPrice>0 && orderBean.getPriceChannel() != null && coupParm.getcActualPrice()==actualPrice;
			
			if(actualPrice==0 && coupParm.getcActualPrice()==actualPrice){
				coupPayService.payByOnlyCoup(orderBean,coupParm);
				return "couppay";
			}
			
			if(isR){
				couponServiceParty.lockCoup(orderNo,coupId);
				isLockCoup = true;
			}
		}else{
			isR = actualPrice>0 && orderBean.getPriceChannel() == actualPrice;
		}
		if(isR){
			TradePayment tradePayment = paymentService.addTradePayment(orderBean, GetWayEnum.ALI, actualPrice, subjectStr, orderBean.getUserAccount(),PayStatus.INITED);
			if(isLockCoup){
				tradePayment.setCoupId(coupId);
				Double cp = DoubleUtil.subtractionDouble(orderBean.getPriceChannel(), actualPrice);
				tradePayment.setCoupPay(cp);
				tradePayment.setCouponInfo(coupParm.getCoupInfo());
				paymentService.updCoupInfo(tradePayment.getPayNo(),coupId,cp,coupParm.getCoupInfo());
			}
			
			if(testPayVal.isTestByBeijing(orderBean)){
				logger.info(orderBean.getOrderNo()+" 测试订单  价格强制修改成 0.01");
				actualPrice = 0.01d;
			}
			String rurl = aliPayService.getMobilePayUrl(tradePayment.getPayNo(), actualPrice, appid, appenv);
			logger.info(orderNo+" get payurl is ["+rurl+"]");
			return rurl;
		}else{
			logger.error("priceChannel>0 && orderBean.getPriceChannel() != null && orderBean.getPriceChannel().equals(priceChannel) && orderBean.getOrderStatus()==OrderStatus.INITSTATE.value = false");
			logger.error("priceChannel="+actualPrice+",orderBean.getPriceChannel()=" + orderBean.getPriceChannel());
			throw new PayException(PayReturnCodeEnum.PAY_PRICECHANNEL_CHANGED,actualPrice);
		}
	}
	
}
