package com.hbc.api.trade.ota.req ;

import org.hibernate.validator.constraints.NotBlank;

import com.hbc.api.trade.order.mapping.gen.bean.OrderBean;
import com.hbc.api.trade.ota.mapping.gen.bean.TradeThirdOrder;

/**
 * 
 * @author Jongly Ran
 */
public class OrderSubmitParam {
	private OrderBean 		orderBean;
	private TradeThirdOrder thirdOrderBean;
	
    @NotBlank 	
    private String 	sign ;						// 
	
	/**
	 * @return the sign
	 */
	public String getSign() {
		return sign;
	}
	/**
	 * @param sign the sign to set
	 */
	public void setSign(String sign) {
		this.sign = sign;
	}
	/**
	 * @return the orderBean
	 */
	public OrderBean getOrderBean() {
		return orderBean;
	}
	/**
	 * @param orderBean the orderBean to set
	 */
	public void setOrderBean(OrderBean orderBean) {
		this.orderBean = orderBean;
	}
	/**
	 * @return the thirdOrderBean
	 */
	public TradeThirdOrder getThirdOrderBean() {
		return thirdOrderBean;
	}
	/**
	 * @param thirdOrderBean the thirdOrderBean to set
	 */
	public void setThirdOrderBean(TradeThirdOrder thirdOrderBean) {
		this.thirdOrderBean = thirdOrderBean;
	}

}