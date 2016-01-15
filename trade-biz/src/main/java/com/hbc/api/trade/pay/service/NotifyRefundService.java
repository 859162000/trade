/**
 * @Author lukangle
 * @2015年10月22日@上午11:24:18
 */
package com.hbc.api.trade.pay.service;

import java.util.Date;
import java.util.List;
/**
 * 																						奖金需要分开 处理 不需要
																			退款 时分细 系统补贴需要将价钱 退还给。 --- 退款需要单独处理
																			实付-已退金额 = 当前订单的可退金额  
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.hbc.api.fund.account.enums.BizType;
import com.hbc.api.fund.account.mapping.gen.bean.FundAccount;
import com.hbc.api.fund.account.service.FundAccountService;
import com.hbc.api.fund.account.service.FundFrozenAccountService;
import com.hbc.api.trade.bdata.common.enums.Sysuser;
import com.hbc.api.trade.bdata.common.util.TimeConverter;
import com.hbc.api.trade.order.enums.bean.OperationType;
import com.hbc.api.trade.order.enums.order.OrderLogType;
import com.hbc.api.trade.order.enums.order.OrderStatus;
import com.hbc.api.trade.order.enums.order.OrderType;
import com.hbc.api.trade.order.mapping.gen.bean.OrderBean;
import com.hbc.api.trade.order.mapping.genx.xbean.OrderLogParamBean;
import com.hbc.api.trade.order.service.OrderLogService;
import com.hbc.api.trade.order.service.OrderQueryService;
import com.hbc.api.trade.order.service.OrderService;
import com.hbc.api.trade.pay.exception.PayException;
import com.hbc.api.trade.pay.exception.PayReturnCodeEnum;
import com.hbc.api.trade.pay.mapping.gen.TradeRefundMapper;
import com.hbc.api.trade.pay.mapping.gen.bean.TradePayment;
import com.hbc.api.trade.pay.mapping.gen.bean.TradeRefund;
import com.hbc.api.trade.pay.mapping.gen.bean.TradeRefundExample;
import com.hbc.api.trade.pay.mapping.gen.bean.TradeRefundExample.Criteria;
import com.hbc.api.trade.pay.mapping.genx.WtRefundMapper;
import com.hbc.api.trade.settle.enums.FundBizEnumConvter;
@Component
public class NotifyRefundService {
	private static final Logger log = LoggerFactory.getLogger(NotifyRefundService.class);
	@Autowired
	TradeRefundMapper tradeRefundMapper;
	@Autowired
	WtRefundMapper wtRefundMapper;
	@Autowired
	OrderService orderService;
	@Autowired
	OrderQueryService orderQueryService;
	@Autowired
	FundAccountService fundAccountService;
	/**
	 * 
	 * @param batchNo
	 * @param orderNo
	 * @return
	 */
	public TradeRefund queryRefundByBathNOOrderNo(String payNo,String orderNo){
		TradeRefundExample tradeRefundExample = new TradeRefundExample();
		Criteria criteria = tradeRefundExample.createCriteria();
		criteria.andOrderNoEqualTo(orderNo);
		criteria.andPayNoEqualTo(payNo);
		log.info("退款 orderNo【"+orderNo+"】 支付号【"+payNo+"】");
		List<TradeRefund> tradeRefunds = tradeRefundMapper.selectByExample(tradeRefundExample);
		if(tradeRefunds.size()!=1){
			throw new PayException(PayReturnCodeEnum.REFUND_NOTIFY_FAILED,orderNo);
		}
		return tradeRefunds.get(0);
	}
	@Autowired
	FundFrozenAccountService fundFrozenAccountService;
	@Autowired
	PaymentService paymentService;
	@Autowired
	OrderLogService orderLogService;
	@Transactional
	public void refundSuccess(TradeRefund tradeRefund,double refundMoney){
		log.info(tradeRefund.getOrderNo()+" start to refund ");
		int optNum = wtRefundMapper.updateNotifyByPrimaryKey(tradeRefund);
		
		OrderBean orderBean = orderQueryService.getOrderByNo(tradeRefund.getOrderNo());
		OrderType orderType = OrderType.getType(orderBean.getOrderType());
		BizType bizType =	FundBizEnumConvter.getRefundBizType(orderType);
		
		String payNo = tradeRefund.getPayNo();
		TradePayment tradePayment = paymentService.queryTradePaymentByPayNo(payNo);
		String userAccount = tradePayment.getUserAccountNo();
		if(userAccount==null){
			userAccount = orderBean.getUserAccount();
		}
		//减去用户冻结账户金额
		FundAccount fundAccount = fundAccountService.getFundAccount(userAccount);
		if(fundAccount.getFrozenAmount()==null || fundAccount.getFrozenAmount()<=refundMoney ){
			Date cdate = TimeConverter.toDate("2015-12-24",TimeConverter.END_WITH_DATE);
			if(orderBean.getCreateTime().after(cdate)){
				fundFrozenAccountService.rechargeFrozen(userAccount, refundMoney, bizType, orderBean.getOrderNo());
			}
		}
		if(optNum!=1){
			throw new PayException(PayReturnCodeEnum.REFUND_UPDATEDB_FAILED,JSON.toJSON(tradeRefund));
		}else{
			OrderStatus orderStatus = OrderStatus.getStatus(orderBean.getOrderStatus());
			if(orderStatus.equals(OrderStatus.CANCELING)){
				OrderStatus sourceStatus = OrderStatus.getStatus(tradeRefund.getOrderStatus());
				if(OrderStatus.PAYSUCCESS.equals(sourceStatus)){
					orderService.updateOrderStatus(tradeRefund.getOrderNo(),sourceStatus, OrderStatus.CANCEL_CLOSE);
				}else{
					orderService.updateOrderStatus(tradeRefund.getOrderNo(),sourceStatus, OrderStatus.CANCLE_CLOSE_PAY_SERVICE);
				}
				
				OrderLogParamBean orderLogParamBean = new OrderLogParamBean();
				orderLogParamBean.setContent(OrderLogType.REFUND_CONTENT(orderBean.getUserName(), tradePayment.getUserAccountNo(),TimeConverter.formatDate(new Date()), tradeRefund.getOrderNo(), refundMoney));
				orderLogParamBean.setOpUserId(Sysuser.SYSUSER.id);
				orderLogParamBean.setLogType(OrderLogType.REFUND.type);
				orderLogParamBean.setOpType(OperationType.SYSTEM.value);
				orderLogParamBean.setOpUserId(Sysuser.SYSUSER.id);
				orderLogParamBean.setOpUserName(Sysuser.SYSUSER.name);
				orderLogParamBean.setOrderNo(tradeRefund.getOrderNo());
				orderLogService.insertOrderLog(orderLogParamBean );
			}else{
				throw new PayException(PayReturnCodeEnum.REFUND_ORDERSTATUS_EXCEPTION,tradeRefund.getOrderNo()+" "+orderStatus.name);
			}
		}
	}
	public void updateRefundByRefunNo(TradeRefund tradeRefund){
		int optNum = wtRefundMapper.updateNotifyByPrimaryKey(tradeRefund);
		if(optNum!=1){
			throw new PayException(PayReturnCodeEnum.REFUND_UPDATEDB_FAILED,JSON.toJSON(tradeRefund));
		}
	}
}
