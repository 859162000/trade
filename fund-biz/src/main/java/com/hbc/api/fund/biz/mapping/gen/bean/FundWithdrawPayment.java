package com.hbc.api.fund.biz.mapping.gen.bean;

import java.util.Date;

public class FundWithdrawPayment {
    /**
     *  
     *  所属表字段为`fund_withdraw_payment`.payment_id
     */
    private String paymentId;

    /**
     *  流水号
     *  所属表字段为`fund_withdraw_payment`.draw_no
     */
    private String drawNo;

    /**
     *  批次号
     *  所属表字段为`fund_withdraw_payment`.batch_no
     */
    private String batchNo;

    /**
     *  支付方式 
     *  所属表字段为`fund_withdraw_payment`.payment_method
     */
    private Integer paymentMethod;

    /**
     *  导游编号
     *  所属表字段为`fund_withdraw_payment`.guide_id
     */
    private String guideId;

    /**
     *  收款账号
     *  所属表字段为`fund_withdraw_payment`.payee_account
     */
    private String payeeAccount;

    /**
     *  收款人姓名
     *  所属表字段为`fund_withdraw_payment`.payee_name
     */
    private String payeeName;

    /**
     *  转账金额
     *  所属表字段为`fund_withdraw_payment`.transfer_amount
     */
    private Double transferAmount;

    /**
     *  实际支付金额
     *  所属表字段为`fund_withdraw_payment`.actual_amount
     */
    private Double actualAmount;

    /**
     *  转账时间
     *  所属表字段为`fund_withdraw_payment`.transfer_datetime
     */
    private Date transferDatetime;

    /**
     *  支付账号
     *  所属表字段为`fund_withdraw_payment`.payer_account
     */
    private String payerAccount;

    /**
     *  操作人ID
     *  所属表字段为`fund_withdraw_payment`.operator_id
     */
    private String operatorId;

    /**
     *  操作人姓名
     *  所属表字段为`fund_withdraw_payment`.operator_name
     */
    private String operatorName;

    /**
     *  支付状态 0：初始 | 1：已提交 | 2：支付成功 | 3：支付失败
     *  所属表字段为`fund_withdraw_payment`.transfer_status
     */
    private Integer transferStatus;

    /**
     *  创建时间
     *  所属表字段为`fund_withdraw_payment`.create_time
     */
    private Date createTime;

    /**
     *  更新时间
     *  所属表字段为`fund_withdraw_payment`.update_time
     */
    private Date updateTime;

    /**
     *  备注
     *  所属表字段为`fund_withdraw_payment`.remark
     */
    private String remark;

    /**
     *
     *`fund_withdraw_payment`.payment_id
     *
     * @return the value of `fund_withdraw_payment`.payment_id
     *
     * @mbggenerated
     */
    public String getPaymentId() {
        return paymentId;
    }

    /**
     *
     *`fund_withdraw_payment`.payment_id
     *
     * @param paymentId the value for `fund_withdraw_payment`.payment_id
     *
     * @mbggenerated
     */
    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId == null ? null : paymentId.trim();
    }

    /**
     *流水号
     *`fund_withdraw_payment`.draw_no
     *
     * @return the value of `fund_withdraw_payment`.draw_no
     *
     * @mbggenerated
     */
    public String getDrawNo() {
        return drawNo;
    }

    /**
     *流水号
     *`fund_withdraw_payment`.draw_no
     *
     * @param drawNo the value for `fund_withdraw_payment`.draw_no
     *
     * @mbggenerated
     */
    public void setDrawNo(String drawNo) {
        this.drawNo = drawNo == null ? null : drawNo.trim();
    }

    /**
     *批次号
     *`fund_withdraw_payment`.batch_no
     *
     * @return the value of `fund_withdraw_payment`.batch_no
     *
     * @mbggenerated
     */
    public String getBatchNo() {
        return batchNo;
    }

    /**
     *批次号
     *`fund_withdraw_payment`.batch_no
     *
     * @param batchNo the value for `fund_withdraw_payment`.batch_no
     *
     * @mbggenerated
     */
    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo == null ? null : batchNo.trim();
    }

    /**
     *支付方式 
     *`fund_withdraw_payment`.payment_method
     *
     * @return the value of `fund_withdraw_payment`.payment_method
     *
     * @mbggenerated
     */
    public Integer getPaymentMethod() {
        return paymentMethod;
    }

    /**
     *支付方式 
     *`fund_withdraw_payment`.payment_method
     *
     * @param paymentMethod the value for `fund_withdraw_payment`.payment_method
     *
     * @mbggenerated
     */
    public void setPaymentMethod(Integer paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    /**
     *导游编号
     *`fund_withdraw_payment`.guide_id
     *
     * @return the value of `fund_withdraw_payment`.guide_id
     *
     * @mbggenerated
     */
    public String getGuideId() {
        return guideId;
    }

    /**
     *导游编号
     *`fund_withdraw_payment`.guide_id
     *
     * @param guideId the value for `fund_withdraw_payment`.guide_id
     *
     * @mbggenerated
     */
    public void setGuideId(String guideId) {
        this.guideId = guideId == null ? null : guideId.trim();
    }

    /**
     *收款账号
     *`fund_withdraw_payment`.payee_account
     *
     * @return the value of `fund_withdraw_payment`.payee_account
     *
     * @mbggenerated
     */
    public String getPayeeAccount() {
        return payeeAccount;
    }

    /**
     *收款账号
     *`fund_withdraw_payment`.payee_account
     *
     * @param payeeAccount the value for `fund_withdraw_payment`.payee_account
     *
     * @mbggenerated
     */
    public void setPayeeAccount(String payeeAccount) {
        this.payeeAccount = payeeAccount == null ? null : payeeAccount.trim();
    }

    /**
     *收款人姓名
     *`fund_withdraw_payment`.payee_name
     *
     * @return the value of `fund_withdraw_payment`.payee_name
     *
     * @mbggenerated
     */
    public String getPayeeName() {
        return payeeName;
    }

    /**
     *收款人姓名
     *`fund_withdraw_payment`.payee_name
     *
     * @param payeeName the value for `fund_withdraw_payment`.payee_name
     *
     * @mbggenerated
     */
    public void setPayeeName(String payeeName) {
        this.payeeName = payeeName == null ? null : payeeName.trim();
    }

    /**
     *转账金额
     *`fund_withdraw_payment`.transfer_amount
     *
     * @return the value of `fund_withdraw_payment`.transfer_amount
     *
     * @mbggenerated
     */
    public Double getTransferAmount() {
        return transferAmount;
    }

    /**
     *转账金额
     *`fund_withdraw_payment`.transfer_amount
     *
     * @param transferAmount the value for `fund_withdraw_payment`.transfer_amount
     *
     * @mbggenerated
     */
    public void setTransferAmount(Double transferAmount) {
        this.transferAmount = transferAmount;
    }

    /**
     *实际支付金额
     *`fund_withdraw_payment`.actual_amount
     *
     * @return the value of `fund_withdraw_payment`.actual_amount
     *
     * @mbggenerated
     */
    public Double getActualAmount() {
        return actualAmount;
    }

    /**
     *实际支付金额
     *`fund_withdraw_payment`.actual_amount
     *
     * @param actualAmount the value for `fund_withdraw_payment`.actual_amount
     *
     * @mbggenerated
     */
    public void setActualAmount(Double actualAmount) {
        this.actualAmount = actualAmount;
    }

    /**
     *转账时间
     *`fund_withdraw_payment`.transfer_datetime
     *
     * @return the value of `fund_withdraw_payment`.transfer_datetime
     *
     * @mbggenerated
     */
    public Date getTransferDatetime() {
        return transferDatetime;
    }

    /**
     *转账时间
     *`fund_withdraw_payment`.transfer_datetime
     *
     * @param transferDatetime the value for `fund_withdraw_payment`.transfer_datetime
     *
     * @mbggenerated
     */
    public void setTransferDatetime(Date transferDatetime) {
        this.transferDatetime = transferDatetime;
    }

    /**
     *支付账号
     *`fund_withdraw_payment`.payer_account
     *
     * @return the value of `fund_withdraw_payment`.payer_account
     *
     * @mbggenerated
     */
    public String getPayerAccount() {
        return payerAccount;
    }

    /**
     *支付账号
     *`fund_withdraw_payment`.payer_account
     *
     * @param payerAccount the value for `fund_withdraw_payment`.payer_account
     *
     * @mbggenerated
     */
    public void setPayerAccount(String payerAccount) {
        this.payerAccount = payerAccount == null ? null : payerAccount.trim();
    }

    /**
     *操作人ID
     *`fund_withdraw_payment`.operator_id
     *
     * @return the value of `fund_withdraw_payment`.operator_id
     *
     * @mbggenerated
     */
    public String getOperatorId() {
        return operatorId;
    }

    /**
     *操作人ID
     *`fund_withdraw_payment`.operator_id
     *
     * @param operatorId the value for `fund_withdraw_payment`.operator_id
     *
     * @mbggenerated
     */
    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId == null ? null : operatorId.trim();
    }

    /**
     *操作人姓名
     *`fund_withdraw_payment`.operator_name
     *
     * @return the value of `fund_withdraw_payment`.operator_name
     *
     * @mbggenerated
     */
    public String getOperatorName() {
        return operatorName;
    }

    /**
     *操作人姓名
     *`fund_withdraw_payment`.operator_name
     *
     * @param operatorName the value for `fund_withdraw_payment`.operator_name
     *
     * @mbggenerated
     */
    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName == null ? null : operatorName.trim();
    }

    /**
     *支付状态 0：初始 | 1：已提交 | 2：支付成功 | 3：支付失败
     *`fund_withdraw_payment`.transfer_status
     *
     * @return the value of `fund_withdraw_payment`.transfer_status
     *
     * @mbggenerated
     */
    public Integer getTransferStatus() {
        return transferStatus;
    }

    /**
     *支付状态 0：初始 | 1：已提交 | 2：支付成功 | 3：支付失败
     *`fund_withdraw_payment`.transfer_status
     *
     * @param transferStatus the value for `fund_withdraw_payment`.transfer_status
     *
     * @mbggenerated
     */
    public void setTransferStatus(Integer transferStatus) {
        this.transferStatus = transferStatus;
    }

    /**
     *创建时间
     *`fund_withdraw_payment`.create_time
     *
     * @return the value of `fund_withdraw_payment`.create_time
     *
     * @mbggenerated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     *创建时间
     *`fund_withdraw_payment`.create_time
     *
     * @param createTime the value for `fund_withdraw_payment`.create_time
     *
     * @mbggenerated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     *更新时间
     *`fund_withdraw_payment`.update_time
     *
     * @return the value of `fund_withdraw_payment`.update_time
     *
     * @mbggenerated
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     *更新时间
     *`fund_withdraw_payment`.update_time
     *
     * @param updateTime the value for `fund_withdraw_payment`.update_time
     *
     * @mbggenerated
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     *备注
     *`fund_withdraw_payment`.remark
     *
     * @return the value of `fund_withdraw_payment`.remark
     *
     * @mbggenerated
     */
    public String getRemark() {
        return remark;
    }

    /**
     *备注
     *`fund_withdraw_payment`.remark
     *
     * @param remark the value for `fund_withdraw_payment`.remark
     *
     * @mbggenerated
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `fund_withdraw_payment`
     *
     * @mbggenerated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", paymentId=").append(paymentId);
        sb.append(", drawNo=").append(drawNo);
        sb.append(", batchNo=").append(batchNo);
        sb.append(", paymentMethod=").append(paymentMethod);
        sb.append(", guideId=").append(guideId);
        sb.append(", payeeAccount=").append(payeeAccount);
        sb.append(", payeeName=").append(payeeName);
        sb.append(", transferAmount=").append(transferAmount);
        sb.append(", actualAmount=").append(actualAmount);
        sb.append(", transferDatetime=").append(transferDatetime);
        sb.append(", payerAccount=").append(payerAccount);
        sb.append(", operatorId=").append(operatorId);
        sb.append(", operatorName=").append(operatorName);
        sb.append(", transferStatus=").append(transferStatus);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", remark=").append(remark);
        sb.append("]");
        return sb.toString();
    }
}