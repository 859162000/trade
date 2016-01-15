package com.hbc.api.trade.order.mapping.gen.bean;

import java.util.Date;

public class TradeLoan {
    /**
     *  结算号
     *  所属表字段为`trade_loan`.settle_no
     */
    private String settleNo;

    /**
     *  订单号
     *  所属表字段为`trade_loan`.order_no
     */
    private String orderNo;

    /**
     *  账户类型 1 导游帐号 2代理商帐号 3皇包车账户
     *  所属表字段为`trade_loan`.account_type
     */
    private Integer accountType;

    /**
     *  帐号ID
     *  所属表字段为`trade_loan`.account_no
     */
    private String accountNo;

    /**
     *  放款金额
     *  所属表字段为`trade_loan`.price
     */
    private Double price;

    /**
     *  结算时间
     *  所属表字段为`trade_loan`.settle_time
     */
    private Date settleTime;

    /**
     *  备注
     *  所属表字段为`trade_loan`.comment
     */
    private String comment;

    /**
     *  操作员类型  1系统结算  2管理员结算
     *  所属表字段为`trade_loan`.op_user_type
     */
    private Integer opUserType;

    /**
     *  操作员ID 系统 为1008
     *  所属表字段为`trade_loan`.op_user_id
     */
    private String opUserId;

    /**
     *  操作员ID 系统默认为system
     *  所属表字段为`trade_loan`.op_user_name
     */
    private String opUserName;

    /**
     *  
     *  所属表字段为`trade_loan`.create_time
     */
    private Date createTime;

    /**
     *  
     *  所属表字段为`trade_loan`.update_time
     */
    private Date updateTime;

    /**
     *结算号
     *`trade_loan`.settle_no
     *
     * @return the value of `trade_loan`.settle_no
     *
     * @mbggenerated
     */
    public String getSettleNo() {
        return settleNo;
    }

    /**
     *结算号
     *`trade_loan`.settle_no
     *
     * @param settleNo the value for `trade_loan`.settle_no
     *
     * @mbggenerated
     */
    public void setSettleNo(String settleNo) {
        this.settleNo = settleNo == null ? null : settleNo.trim();
    }

    /**
     *订单号
     *`trade_loan`.order_no
     *
     * @return the value of `trade_loan`.order_no
     *
     * @mbggenerated
     */
    public String getOrderNo() {
        return orderNo;
    }

    /**
     *订单号
     *`trade_loan`.order_no
     *
     * @param orderNo the value for `trade_loan`.order_no
     *
     * @mbggenerated
     */
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    /**
     *账户类型 1 导游帐号 2代理商帐号 3皇包车账户
     *`trade_loan`.account_type
     *
     * @return the value of `trade_loan`.account_type
     *
     * @mbggenerated
     */
    public Integer getAccountType() {
        return accountType;
    }

    /**
     *账户类型 1 导游帐号 2代理商帐号 3皇包车账户
     *`trade_loan`.account_type
     *
     * @param accountType the value for `trade_loan`.account_type
     *
     * @mbggenerated
     */
    public void setAccountType(Integer accountType) {
        this.accountType = accountType;
    }

    /**
     *帐号ID
     *`trade_loan`.account_no
     *
     * @return the value of `trade_loan`.account_no
     *
     * @mbggenerated
     */
    public String getAccountNo() {
        return accountNo;
    }

    /**
     *帐号ID
     *`trade_loan`.account_no
     *
     * @param accountNo the value for `trade_loan`.account_no
     *
     * @mbggenerated
     */
    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo == null ? null : accountNo.trim();
    }

    /**
     *放款金额
     *`trade_loan`.price
     *
     * @return the value of `trade_loan`.price
     *
     * @mbggenerated
     */
    public Double getPrice() {
        return price;
    }

    /**
     *放款金额
     *`trade_loan`.price
     *
     * @param price the value for `trade_loan`.price
     *
     * @mbggenerated
     */
    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     *结算时间
     *`trade_loan`.settle_time
     *
     * @return the value of `trade_loan`.settle_time
     *
     * @mbggenerated
     */
    public Date getSettleTime() {
        return settleTime;
    }

    /**
     *结算时间
     *`trade_loan`.settle_time
     *
     * @param settleTime the value for `trade_loan`.settle_time
     *
     * @mbggenerated
     */
    public void setSettleTime(Date settleTime) {
        this.settleTime = settleTime;
    }

    /**
     *备注
     *`trade_loan`.comment
     *
     * @return the value of `trade_loan`.comment
     *
     * @mbggenerated
     */
    public String getComment() {
        return comment;
    }

    /**
     *备注
     *`trade_loan`.comment
     *
     * @param comment the value for `trade_loan`.comment
     *
     * @mbggenerated
     */
    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }

    /**
     *操作员类型  1系统结算  2管理员结算
     *`trade_loan`.op_user_type
     *
     * @return the value of `trade_loan`.op_user_type
     *
     * @mbggenerated
     */
    public Integer getOpUserType() {
        return opUserType;
    }

    /**
     *操作员类型  1系统结算  2管理员结算
     *`trade_loan`.op_user_type
     *
     * @param opUserType the value for `trade_loan`.op_user_type
     *
     * @mbggenerated
     */
    public void setOpUserType(Integer opUserType) {
        this.opUserType = opUserType;
    }

    /**
     *操作员ID 系统 为1008
     *`trade_loan`.op_user_id
     *
     * @return the value of `trade_loan`.op_user_id
     *
     * @mbggenerated
     */
    public String getOpUserId() {
        return opUserId;
    }

    /**
     *操作员ID 系统 为1008
     *`trade_loan`.op_user_id
     *
     * @param opUserId the value for `trade_loan`.op_user_id
     *
     * @mbggenerated
     */
    public void setOpUserId(String opUserId) {
        this.opUserId = opUserId == null ? null : opUserId.trim();
    }

    /**
     *操作员ID 系统默认为system
     *`trade_loan`.op_user_name
     *
     * @return the value of `trade_loan`.op_user_name
     *
     * @mbggenerated
     */
    public String getOpUserName() {
        return opUserName;
    }

    /**
     *操作员ID 系统默认为system
     *`trade_loan`.op_user_name
     *
     * @param opUserName the value for `trade_loan`.op_user_name
     *
     * @mbggenerated
     */
    public void setOpUserName(String opUserName) {
        this.opUserName = opUserName == null ? null : opUserName.trim();
    }

    /**
     *
     *`trade_loan`.create_time
     *
     * @return the value of `trade_loan`.create_time
     *
     * @mbggenerated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     *
     *`trade_loan`.create_time
     *
     * @param createTime the value for `trade_loan`.create_time
     *
     * @mbggenerated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     *
     *`trade_loan`.update_time
     *
     * @return the value of `trade_loan`.update_time
     *
     * @mbggenerated
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     *
     *`trade_loan`.update_time
     *
     * @param updateTime the value for `trade_loan`.update_time
     *
     * @mbggenerated
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `trade_loan`
     *
     * @mbggenerated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", settleNo=").append(settleNo);
        sb.append(", orderNo=").append(orderNo);
        sb.append(", accountType=").append(accountType);
        sb.append(", accountNo=").append(accountNo);
        sb.append(", price=").append(price);
        sb.append(", settleTime=").append(settleTime);
        sb.append(", comment=").append(comment);
        sb.append(", opUserType=").append(opUserType);
        sb.append(", opUserId=").append(opUserId);
        sb.append(", opUserName=").append(opUserName);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append("]");
        return sb.toString();
    }
}