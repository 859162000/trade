/**
 * @Author lukangle
 * @2016年1月4日@下午3:32:26
 */
package com.hbc.api.trade.fund.req;

import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;


public class MisGuideRePunish {
	@Range(min=1, max=2,message="类型1为奖励 2为处罚")
	Integer rtype;//1 为奖励 2为处罚
	@NotBlank(message="订单号不能为空") 
	String orderNo;
	@NotBlank(message="资金帐号不能为空") 
	String fundAccount;
	@Min(value=0, message="金额必须大于0")
	Double amount;
	@NotBlank(message="操作员ID不能为空")
	String optId;
	@NotBlank(message="操作员名称不能为空") 
	String optName;
	
	String remark;
	
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Integer getRtype() {
		return rtype;
	}
	public void setRtype(Integer rtype) {
		this.rtype = rtype;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getFundAccount() {
		return fundAccount;
	}
	public void setFundAccount(String fundAccount) {
		this.fundAccount = fundAccount;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public String getOptId() {
		return optId;
	}
	public void setOptId(String optId) {
		this.optId = optId;
	}
	public String getOptName() {
		return optName;
	}
	public void setOptName(String optName) {
		this.optName = optName;
	}
	
	
}
