package com.hbc.data.trade.transfer.mapping.pay.gen.bean;

public class PayRechargeOrder {
    /**
     *  主键id
     *  所属表字段为`pay_recharge_order`.id
     */
    private String id;

    /**
     *  对应的用户Id
     *  所属表字段为`pay_recharge_order`.user_id
     */
    private String userId;

    /**
     *  账户id
     *  所属表字段为`pay_recharge_order`.account_id
     */
    private String accountId;

    /**
     *  频道
     *  所属表字段为`pay_recharge_order`.channel
     */
    private Integer channel;

    /**
     *  支付网关
     *  所属表字段为`pay_recharge_order`.gateway
     */
    private Integer gateway;

    /**
     *  支付网关其他信息
     *  所属表字段为`pay_recharge_order`.gateway_ext
     */
    private String gatewayExt;

    /**
     *  商户充值订单号
     *  所属表字段为`pay_recharge_order`.mer_recharge_no
     */
    private String merRechargeNo;

    /**
     *  第三方支付平台订单号
     *  所属表字段为`pay_recharge_order`.ser_recharge_no
     */
    private String serRechargeNo;

    /**
     *  业务线订单号
     *  所属表字段为`pay_recharge_order`.busi_recharge_no
     */
    private String busiRechargeNo;

    /**
     *  充值金额
     *  所属表字段为`pay_recharge_order`.recharge_amount
     */
    private Integer rechargeAmount;

    /**
     *  对应payConsumeOrder表中的id
     *  所属表字段为`pay_recharge_order`.consume_id
     */
    private String consumeId;

    /**
     *  订单状态
     *  所属表字段为`pay_recharge_order`.status
     */
    private Integer status;

    /**
     *  订单创建时间
     *  所属表字段为`pay_recharge_order`.create_time
     */
    private Integer createTime;

    /**
     *  支付时间
     *  所属表字段为`pay_recharge_order`.pay_time
     */
    private Integer payTime;

    /**
     *  服务名称
     *  所属表字段为`pay_recharge_order`.subject
     */
    private String subject;

    /**
     *  服务描述
     *  所属表字段为`pay_recharge_order`.body
     */
    private String body;

    /**
     *  支付帐号
     *  所属表字段为`pay_recharge_order`.gateway_account
     */
    private String gatewayAccount;

    /**
     *  商户partner
     *  所属表字段为`pay_recharge_order`.seller_partner
     */
    private String sellerPartner;

    /**
     *  城市id
     *  所属表字段为`pay_recharge_order`.city_id
     */
    private Integer cityId;

    /**
     *  外部退款次数
     *  所属表字段为`pay_recharge_order`.refund_count
     */
    private Integer refundCount;

    /**
     *  订单过期时间
     *  所属表字段为`pay_recharge_order`.expire_time
     */
    private Integer expireTime;

    /**
     *  外部退款总的现金金额
     *  所属表字段为`pay_recharge_order`.refund_amount
     */
    private Integer refundAmount;

    /**
     *  外部退款时间
     *  所属表字段为`pay_recharge_order`.refund_time
     */
    private Integer refundTime;

    /**
     *  订单最后更新时间
     *  所属表字段为`pay_recharge_order`.update_time
     */
    private Integer updateTime;

    /**
     *  支付用的手机号
     *  所属表字段为`pay_recharge_order`.mobile_no
     */
    private String mobileNo;

    /**
     *  银行编码
     *  所属表字段为`pay_recharge_order`.bank_code
     */
    private String bankCode;

    /**
     *  订单平台，1为PC，2表示wap 3客户端app
     *  所属表字段为`pay_recharge_order`.plat
     */
    private Integer plat;

    /**
     *  平台扩展,有web,android,iphone等
     *  所属表字段为`pay_recharge_order`.plat_ext
     */
    private String platExt;

    /**
     *  返回URL
     *  所属表字段为`pay_recharge_order`.return_url
     */
    private String returnUrl;

    /**
     *  回调URL
     *  所属表字段为`pay_recharge_order`.callback_url
     */
    private String callbackUrl;

    /**
     *  回调状态
     *  所属表字段为`pay_recharge_order`.callback_status
     */
    private Integer callbackStatus;

    /**
     *  回调次数
     *  所属表字段为`pay_recharge_order`.callback_count
     */
    private Integer callbackCount;

    /**
     *  最后回调时间
     *  所属表字段为`pay_recharge_order`.callback_time
     */
    private Integer callbackTime;

    /**
     *  第三方回调时间
     *  所属表字段为`pay_recharge_order`.ser_notify_time
     */
    private Integer serNotifyTime;

    /**
     *  第三方回调结果
     *  所属表字段为`pay_recharge_order`.ser_notify_log
     */
    private String serNotifyLog;

    /**
     *主键id
     *`pay_recharge_order`.id
     *
     * @return the value of `pay_recharge_order`.id
     *
     * @mbggenerated
     */
    public String getId() {
        return id;
    }

    /**
     *主键id
     *`pay_recharge_order`.id
     *
     * @param id the value for `pay_recharge_order`.id
     *
     * @mbggenerated
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     *对应的用户Id
     *`pay_recharge_order`.user_id
     *
     * @return the value of `pay_recharge_order`.user_id
     *
     * @mbggenerated
     */
    public String getUserId() {
        return userId;
    }

    /**
     *对应的用户Id
     *`pay_recharge_order`.user_id
     *
     * @param userId the value for `pay_recharge_order`.user_id
     *
     * @mbggenerated
     */
    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    /**
     *账户id
     *`pay_recharge_order`.account_id
     *
     * @return the value of `pay_recharge_order`.account_id
     *
     * @mbggenerated
     */
    public String getAccountId() {
        return accountId;
    }

    /**
     *账户id
     *`pay_recharge_order`.account_id
     *
     * @param accountId the value for `pay_recharge_order`.account_id
     *
     * @mbggenerated
     */
    public void setAccountId(String accountId) {
        this.accountId = accountId == null ? null : accountId.trim();
    }

    /**
     *频道
     *`pay_recharge_order`.channel
     *
     * @return the value of `pay_recharge_order`.channel
     *
     * @mbggenerated
     */
    public Integer getChannel() {
        return channel;
    }

    /**
     *频道
     *`pay_recharge_order`.channel
     *
     * @param channel the value for `pay_recharge_order`.channel
     *
     * @mbggenerated
     */
    public void setChannel(Integer channel) {
        this.channel = channel;
    }

    /**
     *支付网关
     *`pay_recharge_order`.gateway
     *
     * @return the value of `pay_recharge_order`.gateway
     *
     * @mbggenerated
     */
    public Integer getGateway() {
        return gateway;
    }

    /**
     *支付网关
     *`pay_recharge_order`.gateway
     *
     * @param gateway the value for `pay_recharge_order`.gateway
     *
     * @mbggenerated
     */
    public void setGateway(Integer gateway) {
        this.gateway = gateway;
    }

    /**
     *支付网关其他信息
     *`pay_recharge_order`.gateway_ext
     *
     * @return the value of `pay_recharge_order`.gateway_ext
     *
     * @mbggenerated
     */
    public String getGatewayExt() {
        return gatewayExt;
    }

    /**
     *支付网关其他信息
     *`pay_recharge_order`.gateway_ext
     *
     * @param gatewayExt the value for `pay_recharge_order`.gateway_ext
     *
     * @mbggenerated
     */
    public void setGatewayExt(String gatewayExt) {
        this.gatewayExt = gatewayExt == null ? null : gatewayExt.trim();
    }

    /**
     *商户充值订单号
     *`pay_recharge_order`.mer_recharge_no
     *
     * @return the value of `pay_recharge_order`.mer_recharge_no
     *
     * @mbggenerated
     */
    public String getMerRechargeNo() {
        return merRechargeNo;
    }

    /**
     *商户充值订单号
     *`pay_recharge_order`.mer_recharge_no
     *
     * @param merRechargeNo the value for `pay_recharge_order`.mer_recharge_no
     *
     * @mbggenerated
     */
    public void setMerRechargeNo(String merRechargeNo) {
        this.merRechargeNo = merRechargeNo == null ? null : merRechargeNo.trim();
    }

    /**
     *第三方支付平台订单号
     *`pay_recharge_order`.ser_recharge_no
     *
     * @return the value of `pay_recharge_order`.ser_recharge_no
     *
     * @mbggenerated
     */
    public String getSerRechargeNo() {
        return serRechargeNo;
    }

    /**
     *第三方支付平台订单号
     *`pay_recharge_order`.ser_recharge_no
     *
     * @param serRechargeNo the value for `pay_recharge_order`.ser_recharge_no
     *
     * @mbggenerated
     */
    public void setSerRechargeNo(String serRechargeNo) {
        this.serRechargeNo = serRechargeNo == null ? null : serRechargeNo.trim();
    }

    /**
     *业务线订单号
     *`pay_recharge_order`.busi_recharge_no
     *
     * @return the value of `pay_recharge_order`.busi_recharge_no
     *
     * @mbggenerated
     */
    public String getBusiRechargeNo() {
        return busiRechargeNo;
    }

    /**
     *业务线订单号
     *`pay_recharge_order`.busi_recharge_no
     *
     * @param busiRechargeNo the value for `pay_recharge_order`.busi_recharge_no
     *
     * @mbggenerated
     */
    public void setBusiRechargeNo(String busiRechargeNo) {
        this.busiRechargeNo = busiRechargeNo == null ? null : busiRechargeNo.trim();
    }

    /**
     *充值金额
     *`pay_recharge_order`.recharge_amount
     *
     * @return the value of `pay_recharge_order`.recharge_amount
     *
     * @mbggenerated
     */
    public Integer getRechargeAmount() {
        return rechargeAmount;
    }

    /**
     *充值金额
     *`pay_recharge_order`.recharge_amount
     *
     * @param rechargeAmount the value for `pay_recharge_order`.recharge_amount
     *
     * @mbggenerated
     */
    public void setRechargeAmount(Integer rechargeAmount) {
        this.rechargeAmount = rechargeAmount;
    }

    /**
     *对应payConsumeOrder表中的id
     *`pay_recharge_order`.consume_id
     *
     * @return the value of `pay_recharge_order`.consume_id
     *
     * @mbggenerated
     */
    public String getConsumeId() {
        return consumeId;
    }

    /**
     *对应payConsumeOrder表中的id
     *`pay_recharge_order`.consume_id
     *
     * @param consumeId the value for `pay_recharge_order`.consume_id
     *
     * @mbggenerated
     */
    public void setConsumeId(String consumeId) {
        this.consumeId = consumeId == null ? null : consumeId.trim();
    }

    /**
     *订单状态
     *`pay_recharge_order`.status
     *
     * @return the value of `pay_recharge_order`.status
     *
     * @mbggenerated
     */
    public Integer getStatus() {
        return status;
    }

    /**
     *订单状态
     *`pay_recharge_order`.status
     *
     * @param status the value for `pay_recharge_order`.status
     *
     * @mbggenerated
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     *订单创建时间
     *`pay_recharge_order`.create_time
     *
     * @return the value of `pay_recharge_order`.create_time
     *
     * @mbggenerated
     */
    public Integer getCreateTime() {
        return createTime;
    }

    /**
     *订单创建时间
     *`pay_recharge_order`.create_time
     *
     * @param createTime the value for `pay_recharge_order`.create_time
     *
     * @mbggenerated
     */
    public void setCreateTime(Integer createTime) {
        this.createTime = createTime;
    }

    /**
     *支付时间
     *`pay_recharge_order`.pay_time
     *
     * @return the value of `pay_recharge_order`.pay_time
     *
     * @mbggenerated
     */
    public Integer getPayTime() {
        return payTime;
    }

    /**
     *支付时间
     *`pay_recharge_order`.pay_time
     *
     * @param payTime the value for `pay_recharge_order`.pay_time
     *
     * @mbggenerated
     */
    public void setPayTime(Integer payTime) {
        this.payTime = payTime;
    }

    /**
     *服务名称
     *`pay_recharge_order`.subject
     *
     * @return the value of `pay_recharge_order`.subject
     *
     * @mbggenerated
     */
    public String getSubject() {
        return subject;
    }

    /**
     *服务名称
     *`pay_recharge_order`.subject
     *
     * @param subject the value for `pay_recharge_order`.subject
     *
     * @mbggenerated
     */
    public void setSubject(String subject) {
        this.subject = subject == null ? null : subject.trim();
    }

    /**
     *服务描述
     *`pay_recharge_order`.body
     *
     * @return the value of `pay_recharge_order`.body
     *
     * @mbggenerated
     */
    public String getBody() {
        return body;
    }

    /**
     *服务描述
     *`pay_recharge_order`.body
     *
     * @param body the value for `pay_recharge_order`.body
     *
     * @mbggenerated
     */
    public void setBody(String body) {
        this.body = body == null ? null : body.trim();
    }

    /**
     *支付帐号
     *`pay_recharge_order`.gateway_account
     *
     * @return the value of `pay_recharge_order`.gateway_account
     *
     * @mbggenerated
     */
    public String getGatewayAccount() {
        return gatewayAccount;
    }

    /**
     *支付帐号
     *`pay_recharge_order`.gateway_account
     *
     * @param gatewayAccount the value for `pay_recharge_order`.gateway_account
     *
     * @mbggenerated
     */
    public void setGatewayAccount(String gatewayAccount) {
        this.gatewayAccount = gatewayAccount == null ? null : gatewayAccount.trim();
    }

    /**
     *商户partner
     *`pay_recharge_order`.seller_partner
     *
     * @return the value of `pay_recharge_order`.seller_partner
     *
     * @mbggenerated
     */
    public String getSellerPartner() {
        return sellerPartner;
    }

    /**
     *商户partner
     *`pay_recharge_order`.seller_partner
     *
     * @param sellerPartner the value for `pay_recharge_order`.seller_partner
     *
     * @mbggenerated
     */
    public void setSellerPartner(String sellerPartner) {
        this.sellerPartner = sellerPartner == null ? null : sellerPartner.trim();
    }

    /**
     *城市id
     *`pay_recharge_order`.city_id
     *
     * @return the value of `pay_recharge_order`.city_id
     *
     * @mbggenerated
     */
    public Integer getCityId() {
        return cityId;
    }

    /**
     *城市id
     *`pay_recharge_order`.city_id
     *
     * @param cityId the value for `pay_recharge_order`.city_id
     *
     * @mbggenerated
     */
    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    /**
     *外部退款次数
     *`pay_recharge_order`.refund_count
     *
     * @return the value of `pay_recharge_order`.refund_count
     *
     * @mbggenerated
     */
    public Integer getRefundCount() {
        return refundCount;
    }

    /**
     *外部退款次数
     *`pay_recharge_order`.refund_count
     *
     * @param refundCount the value for `pay_recharge_order`.refund_count
     *
     * @mbggenerated
     */
    public void setRefundCount(Integer refundCount) {
        this.refundCount = refundCount;
    }

    /**
     *订单过期时间
     *`pay_recharge_order`.expire_time
     *
     * @return the value of `pay_recharge_order`.expire_time
     *
     * @mbggenerated
     */
    public Integer getExpireTime() {
        return expireTime;
    }

    /**
     *订单过期时间
     *`pay_recharge_order`.expire_time
     *
     * @param expireTime the value for `pay_recharge_order`.expire_time
     *
     * @mbggenerated
     */
    public void setExpireTime(Integer expireTime) {
        this.expireTime = expireTime;
    }

    /**
     *外部退款总的现金金额
     *`pay_recharge_order`.refund_amount
     *
     * @return the value of `pay_recharge_order`.refund_amount
     *
     * @mbggenerated
     */
    public Integer getRefundAmount() {
        return refundAmount;
    }

    /**
     *外部退款总的现金金额
     *`pay_recharge_order`.refund_amount
     *
     * @param refundAmount the value for `pay_recharge_order`.refund_amount
     *
     * @mbggenerated
     */
    public void setRefundAmount(Integer refundAmount) {
        this.refundAmount = refundAmount;
    }

    /**
     *外部退款时间
     *`pay_recharge_order`.refund_time
     *
     * @return the value of `pay_recharge_order`.refund_time
     *
     * @mbggenerated
     */
    public Integer getRefundTime() {
        return refundTime;
    }

    /**
     *外部退款时间
     *`pay_recharge_order`.refund_time
     *
     * @param refundTime the value for `pay_recharge_order`.refund_time
     *
     * @mbggenerated
     */
    public void setRefundTime(Integer refundTime) {
        this.refundTime = refundTime;
    }

    /**
     *订单最后更新时间
     *`pay_recharge_order`.update_time
     *
     * @return the value of `pay_recharge_order`.update_time
     *
     * @mbggenerated
     */
    public Integer getUpdateTime() {
        return updateTime;
    }

    /**
     *订单最后更新时间
     *`pay_recharge_order`.update_time
     *
     * @param updateTime the value for `pay_recharge_order`.update_time
     *
     * @mbggenerated
     */
    public void setUpdateTime(Integer updateTime) {
        this.updateTime = updateTime;
    }

    /**
     *支付用的手机号
     *`pay_recharge_order`.mobile_no
     *
     * @return the value of `pay_recharge_order`.mobile_no
     *
     * @mbggenerated
     */
    public String getMobileNo() {
        return mobileNo;
    }

    /**
     *支付用的手机号
     *`pay_recharge_order`.mobile_no
     *
     * @param mobileNo the value for `pay_recharge_order`.mobile_no
     *
     * @mbggenerated
     */
    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo == null ? null : mobileNo.trim();
    }

    /**
     *银行编码
     *`pay_recharge_order`.bank_code
     *
     * @return the value of `pay_recharge_order`.bank_code
     *
     * @mbggenerated
     */
    public String getBankCode() {
        return bankCode;
    }

    /**
     *银行编码
     *`pay_recharge_order`.bank_code
     *
     * @param bankCode the value for `pay_recharge_order`.bank_code
     *
     * @mbggenerated
     */
    public void setBankCode(String bankCode) {
        this.bankCode = bankCode == null ? null : bankCode.trim();
    }

    /**
     *订单平台，1为PC，2表示wap 3客户端app
     *`pay_recharge_order`.plat
     *
     * @return the value of `pay_recharge_order`.plat
     *
     * @mbggenerated
     */
    public Integer getPlat() {
        return plat;
    }

    /**
     *订单平台，1为PC，2表示wap 3客户端app
     *`pay_recharge_order`.plat
     *
     * @param plat the value for `pay_recharge_order`.plat
     *
     * @mbggenerated
     */
    public void setPlat(Integer plat) {
        this.plat = plat;
    }

    /**
     *平台扩展,有web,android,iphone等
     *`pay_recharge_order`.plat_ext
     *
     * @return the value of `pay_recharge_order`.plat_ext
     *
     * @mbggenerated
     */
    public String getPlatExt() {
        return platExt;
    }

    /**
     *平台扩展,有web,android,iphone等
     *`pay_recharge_order`.plat_ext
     *
     * @param platExt the value for `pay_recharge_order`.plat_ext
     *
     * @mbggenerated
     */
    public void setPlatExt(String platExt) {
        this.platExt = platExt == null ? null : platExt.trim();
    }

    /**
     *返回URL
     *`pay_recharge_order`.return_url
     *
     * @return the value of `pay_recharge_order`.return_url
     *
     * @mbggenerated
     */
    public String getReturnUrl() {
        return returnUrl;
    }

    /**
     *返回URL
     *`pay_recharge_order`.return_url
     *
     * @param returnUrl the value for `pay_recharge_order`.return_url
     *
     * @mbggenerated
     */
    public void setReturnUrl(String returnUrl) {
        this.returnUrl = returnUrl == null ? null : returnUrl.trim();
    }

    /**
     *回调URL
     *`pay_recharge_order`.callback_url
     *
     * @return the value of `pay_recharge_order`.callback_url
     *
     * @mbggenerated
     */
    public String getCallbackUrl() {
        return callbackUrl;
    }

    /**
     *回调URL
     *`pay_recharge_order`.callback_url
     *
     * @param callbackUrl the value for `pay_recharge_order`.callback_url
     *
     * @mbggenerated
     */
    public void setCallbackUrl(String callbackUrl) {
        this.callbackUrl = callbackUrl == null ? null : callbackUrl.trim();
    }

    /**
     *回调状态
     *`pay_recharge_order`.callback_status
     *
     * @return the value of `pay_recharge_order`.callback_status
     *
     * @mbggenerated
     */
    public Integer getCallbackStatus() {
        return callbackStatus;
    }

    /**
     *回调状态
     *`pay_recharge_order`.callback_status
     *
     * @param callbackStatus the value for `pay_recharge_order`.callback_status
     *
     * @mbggenerated
     */
    public void setCallbackStatus(Integer callbackStatus) {
        this.callbackStatus = callbackStatus;
    }

    /**
     *回调次数
     *`pay_recharge_order`.callback_count
     *
     * @return the value of `pay_recharge_order`.callback_count
     *
     * @mbggenerated
     */
    public Integer getCallbackCount() {
        return callbackCount;
    }

    /**
     *回调次数
     *`pay_recharge_order`.callback_count
     *
     * @param callbackCount the value for `pay_recharge_order`.callback_count
     *
     * @mbggenerated
     */
    public void setCallbackCount(Integer callbackCount) {
        this.callbackCount = callbackCount;
    }

    /**
     *最后回调时间
     *`pay_recharge_order`.callback_time
     *
     * @return the value of `pay_recharge_order`.callback_time
     *
     * @mbggenerated
     */
    public Integer getCallbackTime() {
        return callbackTime;
    }

    /**
     *最后回调时间
     *`pay_recharge_order`.callback_time
     *
     * @param callbackTime the value for `pay_recharge_order`.callback_time
     *
     * @mbggenerated
     */
    public void setCallbackTime(Integer callbackTime) {
        this.callbackTime = callbackTime;
    }

    /**
     *第三方回调时间
     *`pay_recharge_order`.ser_notify_time
     *
     * @return the value of `pay_recharge_order`.ser_notify_time
     *
     * @mbggenerated
     */
    public Integer getSerNotifyTime() {
        return serNotifyTime;
    }

    /**
     *第三方回调时间
     *`pay_recharge_order`.ser_notify_time
     *
     * @param serNotifyTime the value for `pay_recharge_order`.ser_notify_time
     *
     * @mbggenerated
     */
    public void setSerNotifyTime(Integer serNotifyTime) {
        this.serNotifyTime = serNotifyTime;
    }

    /**
     *第三方回调结果
     *`pay_recharge_order`.ser_notify_log
     *
     * @return the value of `pay_recharge_order`.ser_notify_log
     *
     * @mbggenerated
     */
    public String getSerNotifyLog() {
        return serNotifyLog;
    }

    /**
     *第三方回调结果
     *`pay_recharge_order`.ser_notify_log
     *
     * @param serNotifyLog the value for `pay_recharge_order`.ser_notify_log
     *
     * @mbggenerated
     */
    public void setSerNotifyLog(String serNotifyLog) {
        this.serNotifyLog = serNotifyLog == null ? null : serNotifyLog.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `pay_recharge_order`
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
        sb.append(", gatewayExt=").append(gatewayExt);
        sb.append(", merRechargeNo=").append(merRechargeNo);
        sb.append(", serRechargeNo=").append(serRechargeNo);
        sb.append(", busiRechargeNo=").append(busiRechargeNo);
        sb.append(", rechargeAmount=").append(rechargeAmount);
        sb.append(", consumeId=").append(consumeId);
        sb.append(", status=").append(status);
        sb.append(", createTime=").append(createTime);
        sb.append(", payTime=").append(payTime);
        sb.append(", subject=").append(subject);
        sb.append(", body=").append(body);
        sb.append(", gatewayAccount=").append(gatewayAccount);
        sb.append(", sellerPartner=").append(sellerPartner);
        sb.append(", cityId=").append(cityId);
        sb.append(", refundCount=").append(refundCount);
        sb.append(", expireTime=").append(expireTime);
        sb.append(", refundAmount=").append(refundAmount);
        sb.append(", refundTime=").append(refundTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", mobileNo=").append(mobileNo);
        sb.append(", bankCode=").append(bankCode);
        sb.append(", plat=").append(plat);
        sb.append(", platExt=").append(platExt);
        sb.append(", returnUrl=").append(returnUrl);
        sb.append(", callbackUrl=").append(callbackUrl);
        sb.append(", callbackStatus=").append(callbackStatus);
        sb.append(", callbackCount=").append(callbackCount);
        sb.append(", callbackTime=").append(callbackTime);
        sb.append(", serNotifyTime=").append(serNotifyTime);
        sb.append(", serNotifyLog=").append(serNotifyLog);
        sb.append("]");
        return sb.toString();
    }
}