package com.hbc.api.fund.account.mapping.gen.bean;

import java.util.Date;

public class FundAccount {
    /**
     *  账户ID
     *  所属表字段为`fund_account`.account_no
     */
    private String accountNo;

    /**
     *  账户名称
     *  所属表字段为`fund_account`.account_name
     */
    private String accountName;

    /**
     *  账户类型 1 导游账户 2C端账户 3为黄包车 4为代理商账户
     *  所属表字段为`fund_account`.account_type
     */
    private Integer accountType;

    /**
     *  余额
     *  所属表字段为`fund_account`.amount
     */
    private Double amount;

    /**
     *  总收入，只加不减
     *  所属表字段为`fund_account`.total_amount
     */
    private Double totalAmount;

    /**
     *  创建账户时的用户ID
     *  所属表字段为`fund_account`.user_id
     */
    private String userId;

    /**
     *  冻结金额
     *  所属表字段为`fund_account`.frozen_amount
     */
    private Double frozenAmount;

    /**
     *  账户状态 1为正常 2为冻结
     *  所属表字段为`fund_account`.account_status
     */
    private Integer accountStatus;

    /**
     *  
     *  所属表字段为`fund_account`.update_time
     */
    private Date updateTime;

    /**
     *  
     *  所属表字段为`fund_account`.create_time
     */
    private Date createTime;

    /**
     *账户ID
     *`fund_account`.account_no
     *
     * @return the value of `fund_account`.account_no
     *
     * @mbggenerated
     */
    public String getAccountNo() {
        return accountNo;
    }

    /**
     *账户ID
     *`fund_account`.account_no
     *
     * @param accountNo the value for `fund_account`.account_no
     *
     * @mbggenerated
     */
    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo == null ? null : accountNo.trim();
    }

    /**
     *账户名称
     *`fund_account`.account_name
     *
     * @return the value of `fund_account`.account_name
     *
     * @mbggenerated
     */
    public String getAccountName() {
        return accountName;
    }

    /**
     *账户名称
     *`fund_account`.account_name
     *
     * @param accountName the value for `fund_account`.account_name
     *
     * @mbggenerated
     */
    public void setAccountName(String accountName) {
        this.accountName = accountName == null ? null : accountName.trim();
    }

    /**
     *账户类型 1 导游账户 2C端账户 3为黄包车 4为代理商账户
     *`fund_account`.account_type
     *
     * @return the value of `fund_account`.account_type
     *
     * @mbggenerated
     */
    public Integer getAccountType() {
        return accountType;
    }

    /**
     *账户类型 1 导游账户 2C端账户 3为黄包车 4为代理商账户
     *`fund_account`.account_type
     *
     * @param accountType the value for `fund_account`.account_type
     *
     * @mbggenerated
     */
    public void setAccountType(Integer accountType) {
        this.accountType = accountType;
    }

    /**
     *余额
     *`fund_account`.amount
     *
     * @return the value of `fund_account`.amount
     *
     * @mbggenerated
     */
    public Double getAmount() {
        return amount;
    }

    /**
     *余额
     *`fund_account`.amount
     *
     * @param amount the value for `fund_account`.amount
     *
     * @mbggenerated
     */
    public void setAmount(Double amount) {
        this.amount = amount;
    }

    /**
     *总收入，只加不减
     *`fund_account`.total_amount
     *
     * @return the value of `fund_account`.total_amount
     *
     * @mbggenerated
     */
    public Double getTotalAmount() {
        return totalAmount;
    }

    /**
     *总收入，只加不减
     *`fund_account`.total_amount
     *
     * @param totalAmount the value for `fund_account`.total_amount
     *
     * @mbggenerated
     */
    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    /**
     *创建账户时的用户ID
     *`fund_account`.user_id
     *
     * @return the value of `fund_account`.user_id
     *
     * @mbggenerated
     */
    public String getUserId() {
        return userId;
    }

    /**
     *创建账户时的用户ID
     *`fund_account`.user_id
     *
     * @param userId the value for `fund_account`.user_id
     *
     * @mbggenerated
     */
    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    /**
     *冻结金额
     *`fund_account`.frozen_amount
     *
     * @return the value of `fund_account`.frozen_amount
     *
     * @mbggenerated
     */
    public Double getFrozenAmount() {
        return frozenAmount;
    }

    /**
     *冻结金额
     *`fund_account`.frozen_amount
     *
     * @param frozenAmount the value for `fund_account`.frozen_amount
     *
     * @mbggenerated
     */
    public void setFrozenAmount(Double frozenAmount) {
        this.frozenAmount = frozenAmount;
    }

    /**
     *账户状态 1为正常 2为冻结
     *`fund_account`.account_status
     *
     * @return the value of `fund_account`.account_status
     *
     * @mbggenerated
     */
    public Integer getAccountStatus() {
        return accountStatus;
    }

    /**
     *账户状态 1为正常 2为冻结
     *`fund_account`.account_status
     *
     * @param accountStatus the value for `fund_account`.account_status
     *
     * @mbggenerated
     */
    public void setAccountStatus(Integer accountStatus) {
        this.accountStatus = accountStatus;
    }

    /**
     *
     *`fund_account`.update_time
     *
     * @return the value of `fund_account`.update_time
     *
     * @mbggenerated
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     *
     *`fund_account`.update_time
     *
     * @param updateTime the value for `fund_account`.update_time
     *
     * @mbggenerated
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     *
     *`fund_account`.create_time
     *
     * @return the value of `fund_account`.create_time
     *
     * @mbggenerated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     *
     *`fund_account`.create_time
     *
     * @param createTime the value for `fund_account`.create_time
     *
     * @mbggenerated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `fund_account`
     *
     * @mbggenerated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", accountNo=").append(accountNo);
        sb.append(", accountName=").append(accountName);
        sb.append(", accountType=").append(accountType);
        sb.append(", amount=").append(amount);
        sb.append(", totalAmount=").append(totalAmount);
        sb.append(", userId=").append(userId);
        sb.append(", frozenAmount=").append(frozenAmount);
        sb.append(", accountStatus=").append(accountStatus);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", createTime=").append(createTime);
        sb.append("]");
        return sb.toString();
    }
}