/**
 * @Author lukangle
 * @2015年11月18日@下午2:36:35
 */
package com.hbc.api.fund.account.parm;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

public class PayParm {
	@NotBlank
	private String accountNo;
	@NotNull
	private Double amount;
	@NotBlank
	private String optid;
	@NotBlank
	private String optnam;
	@NotBlank
	private String userId;
	@NotBlank
	private String userName;
	@NotNull
	private Integer bizType;
	private String comment;
	private String remark;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getOptid() {
		return optid;
	}

	public void setOptid(String optid) {
		this.optid = optid;
	}

	public String getOptnam() {
		return optnam;
	}

	public void setOptnam(String optnam) {
		this.optnam = optnam;
	}

	public Integer getBizType() {
		return bizType;
	}

	public void setBizType(Integer bizType) {
		this.bizType = bizType;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}
