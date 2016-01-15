package com.hbc.data.trade.transfer.mapping.pay.gen.bean;

public class PayRefundOrder {
    /**
     *  主键id
     *  所属表字段为`pay_refund_order`.id
     */
    private String id;

    /**
     *  对应的用户Id
     *  所属表字段为`pay_refund_order`.user_id
     */
    private String userId;

    /**
     *  账户id
     *  所属表字段为`pay_refund_order`.account_id
     */
    private String accountId;

    /**
     *  频道
     *  所属表字段为`pay_refund_order`.channel
     */
    private Integer channel;

    /**
     *  支付网关
     *  所属表字段为`pay_refund_order`.gateway
     */
    private Object gateway;

    /**
     *  对应的充值id
     *  所属表字段为`pay_refund_order`.recharge_id
     */
    private String rechargeId;

    /**
     *  对应的冻结id
     *  所属表字段为`pay_refund_order`.freeze_id
     */
    private String freezeId;

    /**
     *  商户订单号
     *  所属表字段为`pay_refund_order`.mer_refund_no
     */
    private String merRefundNo;

    /**
     *  第三方支付平台订单号
     *  所属表字段为`pay_refund_order`.ser_refund_no
     */
    private String serRefundNo;

    /**
     *  业务线订单号
     *  所属表字段为`pay_refund_order`.busi_refund_no
     */
    private String busiRefundNo;

    /**
     *  退款金额,不能大于充值金额
     *  所属表字段为`pay_refund_order`.amount
     */
    private Integer amount;

    /**
     *  退款名称
     *  所属表字段为`pay_refund_order`.subject
     */
    private String subject;

    /**
     *  退款描述
     *  所属表字段为`pay_refund_order`.body
     */
    private String body;

    /**
     *  订单创建时间
     *  所属表字段为`pay_refund_order`.create_time
     */
    private Integer createTime;

    /**
     *  退款时间
     *  所属表字段为`pay_refund_order`.refund_time
     */
    private Integer refundTime;

    /**
     *  第三方回调时间
     *  所属表字段为`pay_refund_order`.ser_notify_time
     */
    private Integer serNotifyTime;

    /**
     *  1:未通知;2,退款成功;3,退款失败
     *  所属表字段为`pay_refund_order`.ser_notify_status
     */
    private Integer serNotifyStatus;

    /**
     *  第三方回调结果
     *  所属表字段为`pay_refund_order`.ser_notify_log
     */
    private String serNotifyLog;

    /**
     *  回调URL
     *  所属表字段为`pay_refund_order`.callback_url
     */
    private String callbackUrl;

    /**
     *  回调状态
     *  所属表字段为`pay_refund_order`.callback_status
     */
    private Integer callbackStatus;

    /**
     *  回调次数
     *  所属表字段为`pay_refund_order`.callback_count
     */
    private Integer callbackCount;

    /**
     *  最后回调时间
     *  所属表字段为`pay_refund_order`.callback_time
     */
    private Integer callbackTime;

    /**
     *  商户partner
     *  所属表字段为`pay_refund_order`.seller_partner
     */
    private String sellerPartner;

    /**
     *主键id
     *`pay_refund_order`.id
     *
     * @return the value of `pay_refund_order`.id
     *
     * @mbggenerated
     */
    public String getId() {
        return id;
    }

    /**
     *主键id
     *`pay_refund_order`.id
     *
     * @param id the value for `pay_refund_order`.id
     *
     * @mbggenerated
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     *对应的用户Id
     *`pay_refund_order`.user_id
     *
     * @return the value of `pay_refund_order`.user_id
     *
     * @mbggenerated
     */
    public String getUserId() {
        return userId;
    }

    /**
     *对应的用户Id
     *`pay_refund_order`.user_id
     *
     * @param userId the value for `pay_refund_order`.user_id
     *
     * @mbggenerated
     */
    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    /**
     *账户id
     *`pay_refund_order`.account_id
     *
     * @return the value of `pay_refund_order`.account_id
     *
     * @mbggenerated
     */
    public String getAccountId() {
        return accountId;
    }

    /**
     *账户id
     *`pay_refund_order`.account_id
     *
     * @param accountId the value for `pay_refund_order`.account_id
     *
     * @mbggenerated
     */
    public void setAccountId(String accountId) {
        this.accountId = accountId == null ? null : accountId.trim();
    }

    /**
     *频道
     *`pay_refund_order`.channel
     *
     * @return the value of `pay_refund_order`.channel
     *
     * @mbggenerated
     */
    public Integer getChannel() {
        return channel;
    }

    /**
     *频道
     *`pay_refund_order`.channel
     *
     * @param channel the value for `pay_refund_order`.channel
     *
     * @mbggenerated
     */
    public void setChannel(Integer channel) {
        this.channel = channel;
    }

    /**
     *支付网关
     *`pay_refund_order`.gateway
     *
     * @return the value of `pay_refund_order`.gateway
     *
     * @mbggenerated
     */
    public Object getGateway() {
        return gateway;
    }

    /**
     *支付网关
     *`pay_refund_order`.gateway
     *
     * @param gateway the value for `pay_refund_order`.gateway
     *
     * @mbggenerated
     */
    public void setGateway(Object gateway) {
        this.gateway = gateway;
    }

    /**
     *对应的充值id
     *`pay_refund_order`.recharge_id
     *
     * @return the value of `pay_refund_order`.recharge_id
     *
     * @mbggenerated
     */
    public String getRechargeId() {
        return rechargeId;
    }

    /**
     *对应的充值id
     *`pay_refund_order`.recharge_id
     *
     * @param rechargeId the value for `pay_refund_order`.recharge_id
     *
     * @mbggenerated
     */
    public void setRechargeId(String rechargeId) {
        this.rechargeId = rechargeId == null ? null : rechargeId.trim();
    }

    /**
     *对应的冻结id
     *`pay_refund_order`.freeze_id
     *
     * @return the value of `pay_refund_order`.freeze_id
     *
     * @mbggenerated
     */
    public String getFreezeId() {
        return freezeId;
    }

    /**
     *对应的冻结id
     *`pay_refund_order`.freeze_id
     *
     * @param freezeId the value for `pay_refund_order`.freeze_id
     *
     * @mbggenerated
     */
    public void setFreezeId(String freezeId) {
        this.freezeId = freezeId == null ? null : freezeId.trim();
    }

    /**
     *商户订单号
     *`pay_refund_order`.mer_refund_no
     *
     * @return the value of `pay_refund_order`.mer_refund_no
     *
     * @mbggenerated
     */
    public String getMerRefundNo() {
        return merRefundNo;
    }

    /**
     *商户订单号
     *`pay_refund_order`.mer_refund_no
     *
     * @param merRefundNo the value for `pay_refund_order`.mer_refund_no
     *
     * @mbggenerated
     */
    public void setMerRefundNo(String merRefundNo) {
        this.merRefundNo = merRefundNo == null ? null : merRefundNo.trim();
    }

    /**
     *第三方支付平台订单号
     *`pay_refund_order`.ser_refund_no
     *
     * @return the value of `pay_refund_order`.ser_refund_no
     *
     * @mbggenerated
     */
    public String getSerRefundNo() {
        return serRefundNo;
    }

    /**
     *第三方支付平台订单号
     *`pay_refund_order`.ser_refund_no
     *
     * @param serRefundNo the value for `pay_refund_order`.ser_refund_no
     *
     * @mbggenerated
     */
    public void setSerRefundNo(String serRefundNo) {
        this.serRefundNo = serRefundNo == null ? null : serRefundNo.trim();
    }

    /**
     *业务线订单号
     *`pay_refund_order`.busi_refund_no
     *
     * @return the value of `pay_refund_order`.busi_refund_no
     *
     * @mbggenerated
     */
    public String getBusiRefundNo() {
        return busiRefundNo;
    }

    /**
     *业务线订单号
     *`pay_refund_order`.busi_refund_no
     *
     * @param busiRefundNo the value for `pay_refund_order`.busi_refund_no
     *
     * @mbggenerated
     */
    public void setBusiRefundNo(String busiRefundNo) {
        this.busiRefundNo = busiRefundNo == null ? null : busiRefundNo.trim();
    }

    /**
     *退款金额,不能大于充值金额
     *`pay_refund_order`.amount
     *
     * @return the value of `pay_refund_order`.amount
     *
     * @mbggenerated
     */
    public Integer getAmount() {
        return amount;
    }

    /**
     *退款金额,不能大于充值金额
     *`pay_refund_order`.amount
     *
     * @param amount the value for `pay_refund_order`.amount
     *
     * @mbggenerated
     */
    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    /**
     *退款名称
     *`pay_refund_order`.subject
     *
     * @return the value of `pay_refund_order`.subject
     *
     * @mbggenerated
     */
    public String getSubject() {
        return subject;
    }

    /**
     *退款名称
     *`pay_refund_order`.subject
     *
     * @param subject the value for `pay_refund_order`.subject
     *
     * @mbggenerated
     */
    public void setSubject(String subject) {
        this.subject = subject == null ? null : subject.trim();
    }

    /**
     *退款描述
     *`pay_refund_order`.body
     *
     * @return the value of `pay_refund_order`.body
     *
     * @mbggenerated
     */
    public String getBody() {
        return body;
    }

    /**
     *退款描述
     *`pay_refund_order`.body
     *
     * @param body the value for `pay_refund_order`.body
     *
     * @mbggenerated
     */
    public void setBody(String body) {
        this.body = body == null ? null : body.trim();
    }

    /**
     *订单创建时间
     *`pay_refund_order`.create_time
     *
     * @return the value of `pay_refund_order`.create_time
     *
     * @mbggenerated
     */
    public Integer getCreateTime() {
        return createTime;
    }

    /**
     *订单创建时间
     *`pay_refund_order`.create_time
     *
     * @param createTime the value for `pay_refund_order`.create_time
     *
     * @mbggenerated
     */
    public void setCreateTime(Integer createTime) {
        this.createTime = createTime;
    }

    /**
     *退款时间
     *`pay_refund_order`.refund_time
     *
     * @return the value of `pay_refund_order`.refund_time
     *
     * @mbggenerated
     */
    public Integer getRefundTime() {
        return refundTime;
    }

    /**
     *退款时间
     *`pay_refund_order`.refund_time
     *
     * @param refundTime the value for `pay_refund_order`.refund_time
     *
     * @mbggenerated
     */
    public void setRefundTime(Integer refundTime) {
        this.refundTime = refundTime;
    }

    /**
     *第三方回调时间
     *`pay_refund_order`.ser_notify_time
     *
     * @return the value of `pay_refund_order`.ser_notify_time
     *
     * @mbggenerated
     */
    public Integer getSerNotifyTime() {
        return serNotifyTime;
    }

    /**
     *第三方回调时间
     *`pay_refund_order`.ser_notify_time
     *
     * @param serNotifyTime the value for `pay_refund_order`.ser_notify_time
     *
     * @mbggenerated
     */
    public void setSerNotifyTime(Integer serNotifyTime) {
        this.serNotifyTime = serNotifyTime;
    }

    /**
     *1:未通知;2,退款成功;3,退款失败
     *`pay_refund_order`.ser_notify_status
     *
     * @return the value of `pay_refund_order`.ser_notify_status
     *
     * @mbggenerated
     */
    public Integer getSerNotifyStatus() {
        return serNotifyStatus;
    }

    /**
     *1:未通知;2,退款成功;3,退款失败
     *`pay_refund_order`.ser_notify_status
     *
     * @param serNotifyStatus the value for `pay_refund_order`.ser_notify_status
     *
     * @mbggenerated
     */
    public void setSerNotifyStatus(Integer serNotifyStatus) {
        this.serNotifyStatus = serNotifyStatus;
    }

    /**
     *第三方回调结果
     *`pay_refund_order`.ser_notify_log
     *
     * @return the value of `pay_refund_order`.ser_notify_log
     *
     * @mbggenerated
     */
    public String getSerNotifyLog() {
        return serNotifyLog;
    }

    /**
     *第三方回调结果
     *`pay_refund_order`.ser_notify_log
     *
     * @param serNotifyLog the value for `pay_refund_order`.ser_notify_log
     *
     * @mbggenerated
     */
    public void setSerNotifyLog(String serNotifyLog) {
        this.serNotifyLog = serNotifyLog == null ? null : serNotifyLog.trim();
    }

    /**
     *回调URL
     *`pay_refund_order`.callback_url
     *
     * @return the value of `pay_refund_order`.callback_url
     *
     * @mbggenerated
     */
    public String getCallbackUrl() {
        return callbackUrl;
    }

    /**
     *回调URL
     *`pay_refund_order`.callback_url
     *
     * @param callbackUrl the value for `pay_refund_order`.callback_url
     *
     * @mbggenerated
     */
    public void setCallbackUrl(String callbackUrl) {
        this.callbackUrl = callbackUrl == null ? null : callbackUrl.trim();
    }

    /**
     *回调状态
     *`pay_refund_order`.callback_status
     *
     * @return the value of `pay_refund_order`.callback_status
     *
     * @mbggenerated
     */
    public Integer getCallbackStatus() {
        return callbackStatus;
    }

    /**
     *回调状态
     *`pay_refund_order`.callback_status
     *
     * @param callbackStatus the value for `pay_refund_order`.callback_status
     *
     * @mbggenerated
     */
    public void setCallbackStatus(Integer callbackStatus) {
        this.callbackStatus = callbackStatus;
    }

    /**
     *回调次数
     *`pay_refund_order`.callback_count
     *
     * @return the value of `pay_refund_order`.callback_count
     *
     * @mbggenerated
     */
    public Integer getCallbackCount() {
        return callbackCount;
    }

    /**
     *回调次数
     *`pay_refund_order`.callback_count
     *
     * @param callbackCount the value for `pay_refund_order`.callback_count
     *
     * @mbggenerated
     */
    public void setCallbackCount(Integer callbackCount) {
        this.callbackCount = callbackCount;
    }

    /**
     *最后回调时间
     *`pay_refund_order`.callback_time
     *
     * @return the value of `pay_refund_order`.callback_time
     *
     * @mbggenerated
     */
    public Integer getCallbackTime() {
        return callbackTime;
    }

    /**
     *最后回调时间
     *`pay_refund_order`.callback_time
     *
     * @param callbackTime the value for `pay_refund_order`.callback_time
     *
     * @mbggenerated
     */
    public void setCallbackTime(Integer callbackTime) {
        this.callbackTime = callbackTime;
    }

    /**
     *商户partner
     *`pay_refund_order`.seller_partner
     *
     * @return the value of `pay_refund_order`.seller_partner
     *
     * @mbggenerated
     */
    public String getSellerPartner() {
        return sellerPartner;
    }

    /**
     *商户partner
     *`pay_refund_order`.seller_partner
     *
     * @param sellerPartner the value for `pay_refund_order`.seller_partner
     *
     * @mbggenerated
     */
    public void setSellerPartner(String sellerPartner) {
        this.sellerPartner = sellerPartner == null ? null : sellerPartner.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `pay_refund_order`
     *
     * @mbggenerated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", userId=").append(userId);
        sb.append(", accountId=").append(accountId);
        sb.append(", channel=").append(channel);
        sb.append(", gateway=").append(gateway);
        sb.append(", rechargeId=").append(rechargeId);
        sb.append(", freezeId=").append(freezeId);
        sb.append(", merRefundNo=").append(merRefundNo);
        sb.append(", serRefundNo=").append(serRefundNo);
        sb.append(", busiRefundNo=").append(busiRefundNo);
        sb.append(", amount=").append(amount);
        sb.append(", subject=").append(subject);
        sb.append(", body=").append(body);
        sb.append(", createTime=").append(createTime);
        sb.append(", refundTime=").append(refundTime);
        sb.append(", serNotifyTime=").append(serNotifyTime);
        sb.append(", serNotifyStatus=").append(serNotifyStatus);
        sb.append(", serNotifyLog=").append(serNotifyLog);
        sb.append(", callbackUrl=").append(callbackUrl);
        sb.append(", callbackStatus=").append(callbackStatus);
        sb.append(", callbackCount=").append(callbackCount);
        sb.append(", callbackTime=").append(callbackTime);
        sb.append(", sellerPartner=").append(sellerPartner);
        sb.append("]");
        return sb.toString();
    }
}