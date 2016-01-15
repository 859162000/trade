package com.hbc.data.trade.transfer.mapping.pay.gen.bean;

public class PayConsumeOrder {
    /**
     *  主键id
     *  所属表字段为`pay_consume_order`.id
     */
    private String id;

    /**
     *  对应的用户Id
     *  所属表字段为`pay_consume_order`.user_id
     */
    private String userId;

    /**
     *  账户id
     *  所属表字段为`pay_consume_order`.account_id
     */
    private String accountId;

    /**
     *  频道
     *  所属表字段为`pay_consume_order`.channel
     */
    private Integer channel;

    /**
     *  订单状态
     *  所属表字段为`pay_consume_order`.status
     */
    private Integer status;

    /**
     *  订单创建时间
     *  所属表字段为`pay_consume_order`.create_time
     */
    private Integer createTime;

    /**
     *  支付时间
     *  所属表字段为`pay_consume_order`.pay_time
     */
    private Integer payTime;

    /**
     *  对应的充值id
     *  所属表字段为`pay_consume_order`.recharge_id
     */
    private String rechargeId;

    /**
     *  消费订单金额
     *  所属表字段为`pay_consume_order`.consume_amount
     */
    private Integer consumeAmount;

    /**
     *  充值金额
     *  所属表字段为`pay_consume_order`.recharge_amount
     */
    private Integer rechargeAmount;

    /**
     *  服务名称
     *  所属表字段为`pay_consume_order`.subject
     */
    private String subject;

    /**
     *  服务描述
     *  所属表字段为`pay_consume_order`.body
     */
    private String body;

    /**
     *  业务线订单号
     *  所属表字段为`pay_consume_order`.busi_consume_no
     */
    private String busiConsumeNo;

    /**
     *  服务的展示地址
     *  所属表字段为`pay_consume_order`.busi_show_url
     */
    private String busiShowUrl;

    /**
     *  城市id
     *  所属表字段为`pay_consume_order`.city_id
     */
    private Integer cityId;

    /**
     *  退款次数
     *  所属表字段为`pay_consume_order`.refund_count
     */
    private Integer refundCount;

    /**
     *  订单过期时间
     *  所属表字段为`pay_consume_order`.expire_time
     */
    private Integer expireTime;

    /**
     *  退款总金额
     *  所属表字段为`pay_consume_order`.refund_amount
     */
    private Integer refundAmount;

    /**
     *  退款时间
     *  所属表字段为`pay_consume_order`.refund_time
     */
    private Integer refundTime;

    /**
     *  订单最后更新时间
     *  所属表字段为`pay_consume_order`.update_time
     */
    private Integer updateTime;

    /**
     *  平台，1为PC，2表示wap 3客户端app
     *  所属表字段为`pay_consume_order`.plat
     */
    private Integer plat;

    /**
     *  平台扩展,有web,android,iphone等
     *  所属表字段为`pay_consume_order`.plat_ext
     */
    private String platExt;

    /**
     *  返回URL
     *  所属表字段为`pay_consume_order`.return_url
     */
    private String returnUrl;

    /**
     *  回调URL
     *  所属表字段为`pay_consume_order`.callback_url
     */
    private String callbackUrl;

    /**
     *  回调状态
     *  所属表字段为`pay_consume_order`.callback_status
     */
    private Integer callbackStatus;

    /**
     *  回调次数
     *  所属表字段为`pay_consume_order`.callback_count
     */
    private Integer callbackCount;

    /**
     *  最后回调时间
     *  所属表字段为`pay_consume_order`.callback_time
     */
    private Integer callbackTime;

    /**
     *主键id
     *`pay_consume_order`.id
     *
     * @return the value of `pay_consume_order`.id
     *
     * @mbggenerated
     */
    public String getId() {
        return id;
    }

    /**
     *主键id
     *`pay_consume_order`.id
     *
     * @param id the value for `pay_consume_order`.id
     *
     * @mbggenerated
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     *对应的用户Id
     *`pay_consume_order`.user_id
     *
     * @return the value of `pay_consume_order`.user_id
     *
     * @mbggenerated
     */
    public String getUserId() {
        return userId;
    }

    /**
     *对应的用户Id
     *`pay_consume_order`.user_id
     *
     * @param userId the value for `pay_consume_order`.user_id
     *
     * @mbggenerated
     */
    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    /**
     *账户id
     *`pay_consume_order`.account_id
     *
     * @return the value of `pay_consume_order`.account_id
     *
     * @mbggenerated
     */
    public String getAccountId() {
        return accountId;
    }

    /**
     *账户id
     *`pay_consume_order`.account_id
     *
     * @param accountId the value for `pay_consume_order`.account_id
     *
     * @mbggenerated
     */
    public void setAccountId(String accountId) {
        this.accountId = accountId == null ? null : accountId.trim();
    }

    /**
     *频道
     *`pay_consume_order`.channel
     *
     * @return the value of `pay_consume_order`.channel
     *
     * @mbggenerated
     */
    public Integer getChannel() {
        return channel;
    }

    /**
     *频道
     *`pay_consume_order`.channel
     *
     * @param channel the value for `pay_consume_order`.channel
     *
     * @mbggenerated
     */
    public void setChannel(Integer channel) {
        this.channel = channel;
    }

    /**
     *订单状态
     *`pay_consume_order`.status
     *
     * @return the value of `pay_consume_order`.status
     *
     * @mbggenerated
     */
    public Integer getStatus() {
        return status;
    }

    /**
     *订单状态
     *`pay_consume_order`.status
     *
     * @param status the value for `pay_consume_order`.status
     *
     * @mbggenerated
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     *订单创建时间
     *`pay_consume_order`.create_time
     *
     * @return the value of `pay_consume_order`.create_time
     *
     * @mbggenerated
     */
    public Integer getCreateTime() {
        return createTime;
    }

    /**
     *订单创建时间
     *`pay_consume_order`.create_time
     *
     * @param createTime the value for `pay_consume_order`.create_time
     *
     * @mbggenerated
     */
    public void setCreateTime(Integer createTime) {
        this.createTime = createTime;
    }

    /**
     *支付时间
     *`pay_consume_order`.pay_time
     *
     * @return the value of `pay_consume_order`.pay_time
     *
     * @mbggenerated
     */
    public Integer getPayTime() {
        return payTime;
    }

    /**
     *支付时间
     *`pay_consume_order`.pay_time
     *
     * @param payTime the value for `pay_consume_order`.pay_time
     *
     * @mbggenerated
     */
    public void setPayTime(Integer payTime) {
        this.payTime = payTime;
    }

    /**
     *对应的充值id
     *`pay_consume_order`.recharge_id
     *
     * @return the value of `pay_consume_order`.recharge_id
     *
     * @mbggenerated
     */
    public String getRechargeId() {
        return rechargeId;
    }

    /**
     *对应的充值id
     *`pay_consume_order`.recharge_id
     *
     * @param rechargeId the value for `pay_consume_order`.recharge_id
     *
     * @mbggenerated
     */
    public void setRechargeId(String rechargeId) {
        this.rechargeId = rechargeId == null ? null : rechargeId.trim();
    }

    /**
     *消费订单金额
     *`pay_consume_order`.consume_amount
     *
     * @return the value of `pay_consume_order`.consume_amount
     *
     * @mbggenerated
     */
    public Integer getConsumeAmount() {
        return consumeAmount;
    }

    /**
     *消费订单金额
     *`pay_consume_order`.consume_amount
     *
     * @param consumeAmount the value for `pay_consume_order`.consume_amount
     *
     * @mbggenerated
     */
    public void setConsumeAmount(Integer consumeAmount) {
        this.consumeAmount = consumeAmount;
    }

    /**
     *充值金额
     *`pay_consume_order`.recharge_amount
     *
     * @return the value of `pay_consume_order`.recharge_amount
     *
     * @mbggenerated
     */
    public Integer getRechargeAmount() {
        return rechargeAmount;
    }

    /**
     *充值金额
     *`pay_consume_order`.recharge_amount
     *
     * @param rechargeAmount the value for `pay_consume_order`.recharge_amount
     *
     * @mbggenerated
     */
    public void setRechargeAmount(Integer rechargeAmount) {
        this.rechargeAmount = rechargeAmount;
    }

    /**
     *服务名称
     *`pay_consume_order`.subject
     *
     * @return the value of `pay_consume_order`.subject
     *
     * @mbggenerated
     */
    public String getSubject() {
        return subject;
    }

    /**
     *服务名称
     *`pay_consume_order`.subject
     *
     * @param subject the value for `pay_consume_order`.subject
     *
     * @mbggenerated
     */
    public void setSubject(String subject) {
        this.subject = subject == null ? null : subject.trim();
    }

    /**
     *服务描述
     *`pay_consume_order`.body
     *
     * @return the value of `pay_consume_order`.body
     *
     * @mbggenerated
     */
    public String getBody() {
        return body;
    }

    /**
     *服务描述
     *`pay_consume_order`.body
     *
     * @param body the value for `pay_consume_order`.body
     *
     * @mbggenerated
     */
    public void setBody(String body) {
        this.body = body == null ? null : body.trim();
    }

    /**
     *业务线订单号
     *`pay_consume_order`.busi_consume_no
     *
     * @return the value of `pay_consume_order`.busi_consume_no
     *
     * @mbggenerated
     */
    public String getBusiConsumeNo() {
        return busiConsumeNo;
    }

    /**
     *业务线订单号
     *`pay_consume_order`.busi_consume_no
     *
     * @param busiConsumeNo the value for `pay_consume_order`.busi_consume_no
     *
     * @mbggenerated
     */
    public void setBusiConsumeNo(String busiConsumeNo) {
        this.busiConsumeNo = busiConsumeNo == null ? null : busiConsumeNo.trim();
    }

    /**
     *服务的展示地址
     *`pay_consume_order`.busi_show_url
     *
     * @return the value of `pay_consume_order`.busi_show_url
     *
     * @mbggenerated
     */
    public String getBusiShowUrl() {
        return busiShowUrl;
    }

    /**
     *服务的展示地址
     *`pay_consume_order`.busi_show_url
     *
     * @param busiShowUrl the value for `pay_consume_order`.busi_show_url
     *
     * @mbggenerated
     */
    public void setBusiShowUrl(String busiShowUrl) {
        this.busiShowUrl = busiShowUrl == null ? null : busiShowUrl.trim();
    }

    /**
     *城市id
     *`pay_consume_order`.city_id
     *
     * @return the value of `pay_consume_order`.city_id
     *
     * @mbggenerated
     */
    public Integer getCityId() {
        return cityId;
    }

    /**
     *城市id
     *`pay_consume_order`.city_id
     *
     * @param cityId the value for `pay_consume_order`.city_id
     *
     * @mbggenerated
     */
    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    /**
     *退款次数
     *`pay_consume_order`.refund_count
     *
     * @return the value of `pay_consume_order`.refund_count
     *
     * @mbggenerated
     */
    public Integer getRefundCount() {
        return refundCount;
    }

    /**
     *退款次数
     *`pay_consume_order`.refund_count
     *
     * @param refundCount the value for `pay_consume_order`.refund_count
     *
     * @mbggenerated
     */
    public void setRefundCount(Integer refundCount) {
        this.refundCount = refundCount;
    }

    /**
     *订单过期时间
     *`pay_consume_order`.expire_time
     *
     * @return the value of `pay_consume_order`.expire_time
     *
     * @mbggenerated
     */
    public Integer getExpireTime() {
        return expireTime;
    }

    /**
     *订单过期时间
     *`pay_consume_order`.expire_time
     *
     * @param expireTime the value for `pay_consume_order`.expire_time
     *
     * @mbggenerated
     */
    public void setExpireTime(Integer expireTime) {
        this.expireTime = expireTime;
    }

    /**
     *退款总金额
     *`pay_consume_order`.refund_amount
     *
     * @return the value of `pay_consume_order`.refund_amount
     *
     * @mbggenerated
     */
    public Integer getRefundAmount() {
        return refundAmount;
    }

    /**
     *退款总金额
     *`pay_consume_order`.refund_amount
     *
     * @param refundAmount the value for `pay_consume_order`.refund_amount
     *
     * @mbggenerated
     */
    public void setRefundAmount(Integer refundAmount) {
        this.refundAmount = refundAmount;
    }

    /**
     *退款时间
     *`pay_consume_order`.refund_time
     *
     * @return the value of `pay_consume_order`.refund_time
     *
     * @mbggenerated
     */
    public Integer getRefundTime() {
        return refundTime;
    }

    /**
     *退款时间
     *`pay_consume_order`.refund_time
     *
     * @param refundTime the value for `pay_consume_order`.refund_time
     *
     * @mbggenerated
     */
    public void setRefundTime(Integer refundTime) {
        this.refundTime = refundTime;
    }

    /**
     *订单最后更新时间
     *`pay_consume_order`.update_time
     *
     * @return the value of `pay_consume_order`.update_time
     *
     * @mbggenerated
     */
    public Integer getUpdateTime() {
        return updateTime;
    }

    /**
     *订单最后更新时间
     *`pay_consume_order`.update_time
     *
     * @param updateTime the value for `pay_consume_order`.update_time
     *
     * @mbggenerated
     */
    public void setUpdateTime(Integer updateTime) {
        this.updateTime = updateTime;
    }

    /**
     *平台，1为PC，2表示wap 3客户端app
     *`pay_consume_order`.plat
     *
     * @return the value of `pay_consume_order`.plat
     *
     * @mbggenerated
     */
    public Integer getPlat() {
        return plat;
    }

    /**
     *平台，1为PC，2表示wap 3客户端app
     *`pay_consume_order`.plat
     *
     * @param plat the value for `pay_consume_order`.plat
     *
     * @mbggenerated
     */
    public void setPlat(Integer plat) {
        this.plat = plat;
    }

    /**
     *平台扩展,有web,android,iphone等
     *`pay_consume_order`.plat_ext
     *
     * @return the value of `pay_consume_order`.plat_ext
     *
     * @mbggenerated
     */
    public String getPlatExt() {
        return platExt;
    }

    /**
     *平台扩展,有web,android,iphone等
     *`pay_consume_order`.plat_ext
     *
     * @param platExt the value for `pay_consume_order`.plat_ext
     *
     * @mbggenerated
     */
    public void setPlatExt(String platExt) {
        this.platExt = platExt == null ? null : platExt.trim();
    }

    /**
     *返回URL
     *`pay_consume_order`.return_url
     *
     * @return the value of `pay_consume_order`.return_url
     *
     * @mbggenerated
     */
    public String getReturnUrl() {
        return returnUrl;
    }

    /**
     *返回URL
     *`pay_consume_order`.return_url
     *
     * @param returnUrl the value for `pay_consume_order`.return_url
     *
     * @mbggenerated
     */
    public void setReturnUrl(String returnUrl) {
        this.returnUrl = returnUrl == null ? null : returnUrl.trim();
    }

    /**
     *回调URL
     *`pay_consume_order`.callback_url
     *
     * @return the value of `pay_consume_order`.callback_url
     *
     * @mbggenerated
     */
    public String getCallbackUrl() {
        return callbackUrl;
    }

    /**
     *回调URL
     *`pay_consume_order`.callback_url
     *
     * @param callbackUrl the value for `pay_consume_order`.callback_url
     *
     * @mbggenerated
     */
    public void setCallbackUrl(String callbackUrl) {
        this.callbackUrl = callbackUrl == null ? null : callbackUrl.trim();
    }

    /**
     *回调状态
     *`pay_consume_order`.callback_status
     *
     * @return the value of `pay_consume_order`.callback_status
     *
     * @mbggenerated
     */
    public Integer getCallbackStatus() {
        return callbackStatus;
    }

    /**
     *回调状态
     *`pay_consume_order`.callback_status
     *
     * @param callbackStatus the value for `pay_consume_order`.callback_status
     *
     * @mbggenerated
     */
    public void setCallbackStatus(Integer callbackStatus) {
        this.callbackStatus = callbackStatus;
    }

    /**
     *回调次数
     *`pay_consume_order`.callback_count
     *
     * @return the value of `pay_consume_order`.callback_count
     *
     * @mbggenerated
     */
    public Integer getCallbackCount() {
        return callbackCount;
    }

    /**
     *回调次数
     *`pay_consume_order`.callback_count
     *
     * @param callbackCount the value for `pay_consume_order`.callback_count
     *
     * @mbggenerated
     */
    public void setCallbackCount(Integer callbackCount) {
        this.callbackCount = callbackCount;
    }

    /**
     *最后回调时间
     *`pay_consume_order`.callback_time
     *
     * @return the value of `pay_consume_order`.callback_time
     *
     * @mbggenerated
     */
    public Integer getCallbackTime() {
        return callbackTime;
    }

    /**
     *最后回调时间
     *`pay_consume_order`.callback_time
     *
     * @param callbackTime the value for `pay_consume_order`.callback_time
     *
     * @mbggenerated
     */
    public void setCallbackTime(Integer callbackTime) {
        this.callbackTime = callbackTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `pay_consume_order`
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
        sb.append(", status=").append(status);
        sb.append(", createTime=").append(createTime);
        sb.append(", payTime=").append(payTime);
        sb.append(", rechargeId=").append(rechargeId);
        sb.append(", consumeAmount=").append(consumeAmount);
        sb.append(", rechargeAmount=").append(rechargeAmount);
        sb.append(", subject=").append(subject);
        sb.append(", body=").append(body);
        sb.append(", busiConsumeNo=").append(busiConsumeNo);
        sb.append(", busiShowUrl=").append(busiShowUrl);
        sb.append(", cityId=").append(cityId);
        sb.append(", refundCount=").append(refundCount);
        sb.append(", expireTime=").append(expireTime);
        sb.append(", refundAmount=").append(refundAmount);
        sb.append(", refundTime=").append(refundTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", plat=").append(plat);
        sb.append(", platExt=").append(platExt);
        sb.append(", returnUrl=").append(returnUrl);
        sb.append(", callbackUrl=").append(callbackUrl);
        sb.append(", callbackStatus=").append(callbackStatus);
        sb.append(", callbackCount=").append(callbackCount);
        sb.append(", callbackTime=").append(callbackTime);
        sb.append("]");
        return sb.toString();
    }
}