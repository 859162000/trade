package com.hbc.api.gateway.alizhifu.req;

import java.io.Serializable;

public class TransInfo implements Serializable {

	private static final long serialVersionUID = -5174224525052592658L;

	private String transNo;
	private String payeeAccount;
	private String payeeName;
	private String guideId;
	private Double transAmount;
	private Double actualAmount;
	private String transSubject;
	private String actualAccount;
	private String actualRemark;

	public TransInfo() {
	}

	/**
	 * @param transNo
	 * @param payeeAccount
	 * @param payeeName
	 * @param guideId
	 * @param transAmount
	 * @param actualAmount
	 * @param transSubject
	 * @param actualAccount
	 * @param actualRemark
	 */
	public TransInfo(String transNo, String payeeAccount, String payeeName, String guideId, Double transAmount, Double actualAmount, String transSubject, String actualAccount, String actualRemark) {
		super();
		this.transNo = transNo;
		this.payeeAccount = payeeAccount;
		this.payeeName = payeeName;
		this.guideId = guideId;
		this.transAmount = transAmount;
		this.actualAmount = actualAmount;
		this.transSubject = transSubject;
		this.actualAccount = actualAccount;
		this.actualRemark = actualRemark;
	}

	public String getTransNo() {
		return transNo;
	}

	public void setTransNo(String transNo) {
		this.transNo = transNo;
	}

	public String getPayeeAccount() {
		return payeeAccount;
	}

	public void setPayeeAccount(String payeeAccount) {
		this.payeeAccount = payeeAccount;
	}

	public String getPayeeName() {
		return payeeName;
	}

	public void setPayeeName(String payeeName) {
		this.payeeName = payeeName;
	}

	public String getGuideId() {
		return guideId;
	}

	public void setGuideId(String guideId) {
		this.guideId = guideId;
	}

	public Double getTransAmount() {
		return transAmount;
	}

	public void setTransAmount(Double transAmount) {
		this.transAmount = transAmount;
	}

	public Double getActualAmount() {
		return actualAmount;
	}

	public void setActualAmount(Double actualAmount) {
		this.actualAmount = actualAmount;
	}

	public String getTransSubject() {
		return transSubject;
	}

	public void setTransSubject(String transSubject) {
		this.transSubject = transSubject;
	}

	public String getActualAccount() {
		return actualAccount;
	}

	public void setActualAccount(String actualAccount) {
		this.actualAccount = actualAccount;
	}

	public String getActualRemark() {
		return actualRemark;
	}

	public void setActualRemark(String actualRemark) {
		this.actualRemark = actualRemark;
	}

	@Override
	public String toString() {
		return "TransInfo [transNo=" + transNo + ", payeeAccount=" + payeeAccount + ", payeeName=" + payeeName + ", guideId=" + guideId + ", transAmount=" + transAmount + ", actualAmount=" + actualAmount + ", transSubject=" + transSubject
				+ ", actualAccount=" + actualAccount + ", actualRemark=" + actualRemark + "]";
	}

}
