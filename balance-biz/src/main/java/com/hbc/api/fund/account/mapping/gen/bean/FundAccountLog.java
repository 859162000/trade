package com.hbc.api.fund.account.mapping.gen.bean;

import java.util.Date;

public class FundAccountLog {
    /**
     *  
     *  所属表字段为`fund_account_log`.log_no
     */
    private String logNo;

    /**
     *  帐号编码 全系统唯一
     *  所属表字段为`fund_account_log`.account_no
     */
    private String accountNo;

    /**
     *  地接社老板提现目标账户
     *  所属表字段为`fund_account_log`.target_account_no
     */
    private String targetAccountNo;

    /**
     *  款项发生时 该账户金额
     *  所属表字段为`fund_account_log`.amount_cur
     */
    private Double amountCur;

    /**
     *  款项记入账户后金额
     *  所属表字段为`fund_account_log`.amount_cg
     */
    private Double amountCg;

    /**
     *  发生变化的金额 比如打款 8块钱 
     *  所属表字段为`fund_account_log`.chang_amount
     */
    private Double changAmount;

    /**
     *  1 系统发生 2 企业账户
     *  所属表字段为`fund_account_log`.change_type
     */
    private Integer changeType;

    /**
     *  
     *  所属表字段为`fund_account_log`.order_no
     */
    private String orderNo;

    /**
     *  
     *  所属表字段为`fund_account_log`.pay_no
     */
    private String payNo;

    /**
     *  
     *  所属表字段为`fund_account_log`.refund_no
     */
    private String refundNo;

    /**
     *  操作员ID  系统时默认为SYSTEM
     *  所属表字段为`fund_account_log`.op_user_id
     */
    private String opUserId;

    /**
     *  操作员ID 系统时默认为SYSTEM
     *  所属表字段为`fund_account_log`.op_user_name
     */
    private String opUserName;

    /**
     *  账户对应用户ID 或者 代理商ID
     *  所属表字段为`fund_account_log`.user_id
     */
    private String userId;

    /**
     *  账户对应用户名称 或者 代理商名称
     *  所属表字段为`fund_account_log`.user_name
     */
    private String userName;

    /**
     *  业务类型。1-接机；2-送机；3-日租；4-次租；5-提现；6-首单奖励；8-处罚；10-导游邀请奖励； 11-接机（接单奖励）；12-接机（取消补偿）；13-接机（改派订单）；14-接机（取消退款）；21-送机（接单奖励）；22-送机（取消补偿）；23-送机（改派订单）；24-送机（取消退款）；31-日租（接单奖励）；32-日租（取消补偿）；33-日租（改派订单）；34-包车（取消退款）；41-次租（接单奖励）；42-次租（取消补偿）；43-次租（改派订单）；44-次租（取消退款）；10-充值；5-提现；101-增项费用；102-返点；51-提现（公司提现）；201-市场活动补贴（对C端）202-代金劵（对C端）。对于订单流水只有一条，后续的操作做update。
     *  所属表字段为`fund_account_log`.biz_type
     */
    private Integer bizType;

    /**
     *  业务状态 1成功 2失败
     *  所属表字段为`fund_account_log`.biz_status
     */
    private Integer bizStatus;

    /**
     *  失败说明
     *  所属表字段为`fund_account_log`.biz_message
     */
    private String bizMessage;

    /**
     *  交易时间
     *  所属表字段为`fund_account_log`.biz_time
     */
    private Date bizTime;

    /**
     *  说明
     *  所属表字段为`fund_account_log`.comment
     */
    private String comment;

    /**
     *  修改备注
     *  所属表字段为`fund_account_log`.remark1
     */
    private String remark1;

    /**
     *  
     *  所属表字段为`fund_account_log`.remark2
     */
    private String remark2;

    /**
     *  
     *  所属表字段为`fund_account_log`.remark3
     */
    private String remark3;

    /**
     *  
     *  所属表字段为`fund_account_log`.remark4
     */
    private String remark4;

    /**
     *  
     *  所属表字段为`fund_account_log`.remark5
     */
    private String remark5;

    /**
     *  
     *  所属表字段为`fund_account_log`.update_time
     */
    private Date updateTime;

    /**
     *  
     *  所属表字段为`fund_account_log`.create_time
     */
    private Date createTime;

    /**
     *
     *`fund_account_log`.log_no
     *
     * @return the value of `fund_account_log`.log_no
     *
     * @mbggenerated
     */
    public String getLogNo() {
        return logNo;
    }

    /**
     *
     *`fund_account_log`.log_no
     *
     * @param logNo the value for `fund_account_log`.log_no
     *
     * @mbggenerated
     */
    public void setLogNo(String logNo) {
        this.logNo = logNo == null ? null : logNo.trim();
    }

    /**
     *帐号编码 全系统唯一
     *`fund_account_log`.account_no
     *
     * @return the value of `fund_account_log`.account_no
     *
     * @mbggenerated
     */
    public String getAccountNo() {
        return accountNo;
    }

    /**
     *帐号编码 全系统唯一
     *`fund_account_log`.account_no
     *
     * @param accountNo the value for `fund_account_log`.account_no
     *
     * @mbggenerated
     */
    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo == null ? null : accountNo.trim();
    }

    /**
     *地接社老板提现目标账户
     *`fund_account_log`.target_account_no
     *
     * @return the value of `fund_account_log`.target_account_no
     *
     * @mbggenerated
     */
    public String getTargetAccountNo() {
        return targetAccountNo;
    }

    /**
     *地接社老板提现目标账户
     *`fund_account_log`.target_account_no
     *
     * @param targetAccountNo the value for `fund_account_log`.target_account_no
     *
     * @mbggenerated
     */
    public void setTargetAccountNo(String targetAccountNo) {
        this.targetAccountNo = targetAccountNo == null ? null : targetAccountNo.trim();
    }

    /**
     *款项发生时 该账户金额
     *`fund_account_log`.amount_cur
     *
     * @return the value of `fund_account_log`.amount_cur
     *
     * @mbggenerated
     */
    public Double getAmountCur() {
        return amountCur;
    }

    /**
     *款项发生时 该账户金额
     *`fund_account_log`.amount_cur
     *
     * @param amountCur the value for `fund_account_log`.amount_cur
     *
     * @mbggenerated
     */
    public void setAmountCur(Double amountCur) {
        this.amountCur = amountCur;
    }

    /**
     *款项记入账户后金额
     *`fund_account_log`.amount_cg
     *
     * @return the value of `fund_account_log`.amount_cg
     *
     * @mbggenerated
     */
    public Double getAmountCg() {
        return amountCg;
    }

    /**
     *款项记入账户后金额
     *`fund_account_log`.amount_cg
     *
     * @param amountCg the value for `fund_account_log`.amount_cg
     *
     * @mbggenerated
     */
    public void setAmountCg(Double amountCg) {
        this.amountCg = amountCg;
    }

    /**
     *发生变化的金额 比如打款 8块钱 
     *`fund_account_log`.chang_amount
     *
     * @return the value of `fund_account_log`.chang_amount
     *
     * @mbggenerated
     */
    public Double getChangAmount() {
        return changAmount;
    }

    /**
     *发生变化的金额 比如打款 8块钱 
     *`fund_account_log`.chang_amount
     *
     * @param changAmount the value for `fund_account_log`.chang_amount
     *
     * @mbggenerated
     */
    public void setChangAmount(Double changAmount) {
        this.changAmount = changAmount;
    }

    /**
     *1 系统发生 2 企业账户
     *`fund_account_log`.change_type
     *
     * @return the value of `fund_account_log`.change_type
     *
     * @mbggenerated
     */
    public Integer getChangeType() {
        return changeType;
    }

    /**
     *1 系统发生 2 企业账户
     *`fund_account_log`.change_type
     *
     * @param changeType the value for `fund_account_log`.change_type
     *
     * @mbggenerated
     */
    public void setChangeType(Integer changeType) {
        this.changeType = changeType;
    }

    /**
     *
     *`fund_account_log`.order_no
     *
     * @return the value of `fund_account_log`.order_no
     *
     * @mbggenerated
     */
    public String getOrderNo() {
        return orderNo;
    }

    /**
     *
     *`fund_account_log`.order_no
     *
     * @param orderNo the value for `fund_account_log`.order_no
     *
     * @mbggenerated
     */
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    /**
     *
     *`fund_account_log`.pay_no
     *
     * @return the value of `fund_account_log`.pay_no
     *
     * @mbggenerated
     */
    public String getPayNo() {
        return payNo;
    }

    /**
     *
     *`fund_account_log`.pay_no
     *
     * @param payNo the value for `fund_account_log`.pay_no
     *
     * @mbggenerated
     */
    public void setPayNo(String payNo) {
        this.payNo = payNo == null ? null : payNo.trim();
    }

    /**
     *
     *`fund_account_log`.refund_no
     *
     * @return the value of `fund_account_log`.refund_no
     *
     * @mbggenerated
     */
    public String getRefundNo() {
        return refundNo;
    }

    /**
     *
     *`fund_account_log`.refund_no
     *
     * @param refundNo the value for `fund_account_log`.refund_no
     *
     * @mbggenerated
     */
    public void setRefundNo(String refundNo) {
        this.refundNo = refundNo == null ? null : refundNo.trim();
    }

    /**
     *操作员ID  系统时默认为SYSTEM
     *`fund_account_log`.op_user_id
     *
     * @return the value of `fund_account_log`.op_user_id
     *
     * @mbggenerated
     */
    public String getOpUserId() {
        return opUserId;
    }

    /**
     *操作员ID  系统时默认为SYSTEM
     *`fund_account_log`.op_user_id
     *
     * @param opUserId the value for `fund_account_log`.op_user_id
     *
     * @mbggenerated
     */
    public void setOpUserId(String opUserId) {
        this.opUserId = opUserId == null ? null : opUserId.trim();
    }

    /**
     *操作员ID 系统时默认为SYSTEM
     *`fund_account_log`.op_user_name
     *
     * @return the value of `fund_account_log`.op_user_name
     *
     * @mbggenerated
     */
    public String getOpUserName() {
        return opUserName;
    }

    /**
     *操作员ID 系统时默认为SYSTEM
     *`fund_account_log`.op_user_name
     *
     * @param opUserName the value for `fund_account_log`.op_user_name
     *
     * @mbggenerated
     */
    public void setOpUserName(String opUserName) {
        this.opUserName = opUserName == null ? null : opUserName.trim();
    }

    /**
     *账户对应用户ID 或者 代理商ID
     *`fund_account_log`.user_id
     *
     * @return the value of `fund_account_log`.user_id
     *
     * @mbggenerated
     */
    public String getUserId() {
        return userId;
    }

    /**
     *账户对应用户ID 或者 代理商ID
     *`fund_account_log`.user_id
     *
     * @param userId the value for `fund_account_log`.user_id
     *
     * @mbggenerated
     */
    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    /**
     *账户对应用户名称 或者 代理商名称
     *`fund_account_log`.user_name
     *
     * @return the value of `fund_account_log`.user_name
     *
     * @mbggenerated
     */
    public String getUserName() {
        return userName;
    }

    /**
     *账户对应用户名称 或者 代理商名称
     *`fund_account_log`.user_name
     *
     * @param userName the value for `fund_account_log`.user_name
     *
     * @mbggenerated
     */
    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    /**
     *业务类型。1-接机；2-送机；3-日租；4-次租；5-提现；6-首单奖励；8-处罚；10-导游邀请奖励； 11-接机（接单奖励）；12-接机（取消补偿）；13-接机（改派订单）；14-接机（取消退款）；21-送机（接单奖励）；22-送机（取消补偿）；23-送机（改派订单）；24-送机（取消退款）；31-日租（接单奖励）；32-日租（取消补偿）；33-日租（改派订单）；34-包车（取消退款）；41-次租（接单奖励）；42-次租（取消补偿）；43-次租（改派订单）；44-次租（取消退款）；10-充值；5-提现；101-增项费用；102-返点；51-提现（公司提现）；201-市场活动补贴（对C端）202-代金劵（对C端）。对于订单流水只有一条，后续的操作做update。
     *`fund_account_log`.biz_type
     *
     * @return the value of `fund_account_log`.biz_type
     *
     * @mbggenerated
     */
    public Integer getBizType() {
        return bizType;
    }

    /**
     *业务类型。1-接机；2-送机；3-日租；4-次租；5-提现；6-首单奖励；8-处罚；10-导游邀请奖励； 11-接机（接单奖励）；12-接机（取消补偿）；13-接机（改派订单）；14-接机（取消退款）；21-送机（接单奖励）；22-送机（取消补偿）；23-送机（改派订单）；24-送机（取消退款）；31-日租（接单奖励）；32-日租（取消补偿）；33-日租（改派订单）；34-包车（取消退款）；41-次租（接单奖励）；42-次租（取消补偿）；43-次租（改派订单）；44-次租（取消退款）；10-充值；5-提现；101-增项费用；102-返点；51-提现（公司提现）；201-市场活动补贴（对C端）202-代金劵（对C端）。对于订单流水只有一条，后续的操作做update。
     *`fund_account_log`.biz_type
     *
     * @param bizType the value for `fund_account_log`.biz_type
     *
     * @mbggenerated
     */
    public void setBizType(Integer bizType) {
        this.bizType = bizType;
    }

    /**
     *业务状态 1成功 2失败
     *`fund_account_log`.biz_status
     *
     * @return the value of `fund_account_log`.biz_status
     *
     * @mbggenerated
     */
    public Integer getBizStatus() {
        return bizStatus;
    }

    /**
     *业务状态 1成功 2失败
     *`fund_account_log`.biz_status
     *
     * @param bizStatus the value for `fund_account_log`.biz_status
     *
     * @mbggenerated
     */
    public void setBizStatus(Integer bizStatus) {
        this.bizStatus = bizStatus;
    }

    /**
     *失败说明
     *`fund_account_log`.biz_message
     *
     * @return the value of `fund_account_log`.biz_message
     *
     * @mbggenerated
     */
    public String getBizMessage() {
        return bizMessage;
    }

    /**
     *失败说明
     *`fund_account_log`.biz_message
     *
     * @param bizMessage the value for `fund_account_log`.biz_message
     *
     * @mbggenerated
     */
    public void setBizMessage(String bizMessage) {
        this.bizMessage = bizMessage == null ? null : bizMessage.trim();
    }

    /**
     *交易时间
     *`fund_account_log`.biz_time
     *
     * @return the value of `fund_account_log`.biz_time
     *
     * @mbggenerated
     */
    public Date getBizTime() {
        return bizTime;
    }

    /**
     *交易时间
     *`fund_account_log`.biz_time
     *
     * @param bizTime the value for `fund_account_log`.biz_time
     *
     * @mbggenerated
     */
    public void setBizTime(Date bizTime) {
        this.bizTime = bizTime;
    }

    /**
     *说明
     *`fund_account_log`.comment
     *
     * @return the value of `fund_account_log`.comment
     *
     * @mbggenerated
     */
    public String getComment() {
        return comment;
    }

    /**
     *说明
     *`fund_account_log`.comment
     *
     * @param comment the value for `fund_account_log`.comment
     *
     * @mbggenerated
     */
    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }

    /**
     *修改备注
     *`fund_account_log`.remark1
     *
     * @return the value of `fund_account_log`.remark1
     *
     * @mbggenerated
     */
    public String getRemark1() {
        return remark1;
    }

    /**
     *修改备注
     *`fund_account_log`.remark1
     *
     * @param remark1 the value for `fund_account_log`.remark1
     *
     * @mbggenerated
     */
    public void setRemark1(String remark1) {
        this.remark1 = remark1 == null ? null : remark1.trim();
    }

    /**
     *
     *`fund_account_log`.remark2
     *
     * @return the value of `fund_account_log`.remark2
     *
     * @mbggenerated
     */
    public String getRemark2() {
        return remark2;
    }

    /**
     *
     *`fund_account_log`.remark2
     *
     * @param remark2 the value for `fund_account_log`.remark2
     *
     * @mbggenerated
     */
    public void setRemark2(String remark2) {
        this.remark2 = remark2 == null ? null : remark2.trim();
    }

    /**
     *
     *`fund_account_log`.remark3
     *
     * @return the value of `fund_account_log`.remark3
     *
     * @mbggenerated
     */
    public String getRemark3() {
        return remark3;
    }

    /**
     *
     *`fund_account_log`.remark3
     *
     * @param remark3 the value for `fund_account_log`.remark3
     *
     * @mbggenerated
     */
    public void setRemark3(String remark3) {
        this.remark3 = remark3 == null ? null : remark3.trim();
    }

    /**
     *
     *`fund_account_log`.remark4
     *
     * @return the value of `fund_account_log`.remark4
     *
     * @mbggenerated
     */
    public String getRemark4() {
        return remark4;
    }

    /**
     *
     *`fund_account_log`.remark4
     *
     * @param remark4 the value for `fund_account_log`.remark4
     *
     * @mbggenerated
     */
    public void setRemark4(String remark4) {
        this.remark4 = remark4 == null ? null : remark4.trim();
    }

    /**
     *
     *`fund_account_log`.remark5
     *
     * @return the value of `fund_account_log`.remark5
     *
     * @mbggenerated
     */
    public String getRemark5() {
        return remark5;
    }

    /**
     *
     *`fund_account_log`.remark5
     *
     * @param remark5 the value for `fund_account_log`.remark5
     *
     * @mbggenerated
     */
    public void setRemark5(String remark5) {
        this.remark5 = remark5 == null ? null : remark5.trim();
    }

    /**
     *
     *`fund_account_log`.update_time
     *
     * @return the value of `fund_account_log`.update_time
     *
     * @mbggenerated
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     *
     *`fund_account_log`.update_time
     *
     * @param updateTime the value for `fund_account_log`.update_time
     *
     * @mbggenerated
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     *
     *`fund_account_log`.create_time
     *
     * @return the value of `fund_account_log`.create_time
     *
     * @mbggenerated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     *
     *`fund_account_log`.create_time
     *
     * @param createTime the value for `fund_account_log`.create_time
     *
     * @mbggenerated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `fund_account_log`
     *
     * @mbggenerated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", logNo=").append(logNo);
        sb.append(", accountNo=").append(accountNo);
        sb.append(", targetAccountNo=").append(targetAccountNo);
        sb.append(", amountCur=").append(amountCur);
        sb.append(", amountCg=").append(amountCg);
        sb.append(", changAmount=").append(changAmount);
        sb.append(", changeType=").append(changeType);
        sb.append(", orderNo=").append(orderNo);
        sb.append(", payNo=").append(payNo);
        sb.append(", refundNo=").append(refundNo);
        sb.append(", opUserId=").append(opUserId);
        sb.append(", opUserName=").append(opUserName);
        sb.append(", userId=").append(userId);
        sb.append(", userName=").append(userName);
        sb.append(", bizType=").append(bizType);
        sb.append(", bizStatus=").append(bizStatus);
        sb.append(", bizMessage=").append(bizMessage);
        sb.append(", bizTime=").append(bizTime);
        sb.append(", comment=").append(comment);
        sb.append(", remark1=").append(remark1);
        sb.append(", remark2=").append(remark2);
        sb.append(", remark3=").append(remark3);
        sb.append(", remark4=").append(remark4);
        sb.append(", remark5=").append(remark5);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", createTime=").append(createTime);
        sb.append("]");
        return sb.toString();
    }
}