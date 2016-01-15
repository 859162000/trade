package com.hbc.api.trade.pay.mapping.gen.bean;

import java.util.Date;

public class TradePayment {
    /**
     *  支付号
     *  所属表字段为`trade_payment`.pay_no
     */
    private String payNo;

    /**
     *  订单号
     *  所属表字段为`trade_payment`.order_no
     */
    private String orderNo;

    /**
     *  支付方式。1-网银；2-信用卡；5-支付宝；6-微信；7-内部账 101-招商银行；102-工商银行；103-农业银行；104-中国银行；
     *  所属表字段为`trade_payment`.pay_getway
     */
    private Integer payGetway;

    /**
     *  支付渠道名称
     *  所属表字段为`trade_payment`.pay_gateway_name
     */
    private String payGatewayName;

    /**
     *  支付 状态 0未支付 1 支付中 2 支付完成 3 支付失败
     *  所属表字段为`trade_payment`.pay_status
     */
    private Integer payStatus;

    /**
     *  订单价
     *  所属表字段为`trade_payment`.order_price
     */
    private Double orderPrice;

    /**
     *  支付券ID
     *  所属表字段为`trade_payment`.coup_id
     */
    private String coupId;

    /**
     *  券抵扣
     *  所属表字段为`trade_payment`.coup_pay
     */
    private Double coupPay;

    /**
     *  
     *  所属表字段为`trade_payment`.coupon_info
     */
    private String couponInfo;

    /**
     *  应付
     *  所属表字段为`trade_payment`.pay_should
     */
    private Double payShould;

    /**
     *  实付order_price=coup_pay+pay_should
                        pay_should=pay_actual+pay_fee
     *  所属表字段为`trade_payment`.pay_actual
     */
    private Double payActual;

    /**
     *  支付手续费
     *  所属表字段为`trade_payment`.pay_fee
     */
    private Double payFee;

    /**
     *  支付来源，1为PC，2表示wap 3客户端app
     *  所属表字段为`trade_payment`.pay_source
     */
    private Integer paySource;

    /**
     *  支付时间
     *  所属表字段为`trade_payment`.pay_time
     */
    private Date payTime;

    /**
     *  支付过期时间
     *  所属表字段为`trade_payment`.order_expire_time
     */
    private Date orderExpireTime;

    /**
     *  支付创建时间
     *  所属表字段为`trade_payment`.order_create_time
     */
    private Date orderCreateTime;

    /**
     *  用户资金账户no
     *  所属表字段为`trade_payment`.user_account_no
     */
    private String userAccountNo;

    /**
     *  用户支付帐号（支付宝号）
     *  所属表字段为`trade_payment`.user_pay_account
     */
    private String userPayAccount;

    /**
     *  支付用的手机号
     *  所属表字段为`trade_payment`.user_mobile
     */
    private String userMobile;

    /**
     *  支付用户ID
     *  所属表字段为`trade_payment`.user_id
     */
    private String userId;

    /**
     *  
     *  所属表字段为`trade_payment`.user_name
     */
    private String userName;

    /**
     *  支付名称
     *  所属表字段为`trade_payment`.pay_subject
     */
    private String paySubject;

    /**
     *  支付描述
     *  所属表字段为`trade_payment`.pay_desc
     */
    private String payDesc;

    /**
     *  第三方支付号
     *  所属表字段为`trade_payment`.third_pay_no
     */
    private String thirdPayNo;

    /**
     *  1:未通知;2,支付成功;3,支付失败
     *  所属表字段为`trade_payment`.third_notify_status
     */
    private String thirdNotifyStatus;

    /**
     *  
     *  所属表字段为`trade_payment`.update_time
     */
    private Date updateTime;

    /**
     *  
     *  所属表字段为`trade_payment`.create_time
     */
    private Date createTime;

    /**
     *  
     *  所属表字段为`trade_payment`.third_notify_log
     */
    private String thirdNotifyLog;

    /**
     *支付号
     *`trade_payment`.pay_no
     *
     * @return the value of `trade_payment`.pay_no
     *
     * @mbggenerated
     */
    public String getPayNo() {
        return payNo;
    }

    /**
     *支付号
     *`trade_payment`.pay_no
     *
     * @param payNo the value for `trade_payment`.pay_no
     *
     * @mbggenerated
     */
    public void setPayNo(String payNo) {
        this.payNo = payNo == null ? null : payNo.trim();
    }

    /**
     *订单号
     *`trade_payment`.order_no
     *
     * @return the value of `trade_payment`.order_no
     *
     * @mbggenerated
     */
    public String getOrderNo() {
        return orderNo;
    }

    /**
     *订单号
     *`trade_payment`.order_no
     *
     * @param orderNo the value for `trade_payment`.order_no
     *
     * @mbggenerated
     */
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    /**
     *支付方式。1-网银；2-信用卡；5-支付宝；6-微信；7-内部账 101-招商银行；102-工商银行；103-农业银行；104-中国银行；
     *`trade_payment`.pay_getway
     *
     * @return the value of `trade_payment`.pay_getway
     *
     * @mbggenerated
     */
    public Integer getPayGetway() {
        return payGetway;
    }

    /**
     *支付方式。1-网银；2-信用卡；5-支付宝；6-微信；7-内部账 101-招商银行；102-工商银行；103-农业银行；104-中国银行；
     *`trade_payment`.pay_getway
     *
     * @param payGetway the value for `trade_payment`.pay_getway
     *
     * @mbggenerated
     */
    public void setPayGetway(Integer payGetway) {
        this.payGetway = payGetway;
    }

    /**
     *支付渠道名称
     *`trade_payment`.pay_gateway_name
     *
     * @return the value of `trade_payment`.pay_gateway_name
     *
     * @mbggenerated
     */
    public String getPayGatewayName() {
        return payGatewayName;
    }

    /**
     *支付渠道名称
     *`trade_payment`.pay_gateway_name
     *
     * @param payGatewayName the value for `trade_payment`.pay_gateway_name
     *
     * @mbggenerated
     */
    public void setPayGatewayName(String payGatewayName) {
        this.payGatewayName = payGatewayName == null ? null : payGatewayName.trim();
    }

    /**
     *支付 状态 0未支付 1 支付中 2 支付完成 3 支付失败
     *`trade_payment`.pay_status
     *
     * @return the value of `trade_payment`.pay_status
     *
     * @mbggenerated
     */
    public Integer getPayStatus() {
        return payStatus;
    }

    /**
     *支付 状态 0未支付 1 支付中 2 支付完成 3 支付失败
     *`trade_payment`.pay_status
     *
     * @param payStatus the value for `trade_payment`.pay_status
     *
     * @mbggenerated
     */
    public void setPayStatus(Integer payStatus) {
        this.payStatus = payStatus;
    }

    /**
     *订单价
     *`trade_payment`.order_price
     *
     * @return the value of `trade_payment`.order_price
     *
     * @mbggenerated
     */
    public Double getOrderPrice() {
        return orderPrice;
    }

    /**
     *订单价
     *`trade_payment`.order_price
     *
     * @param orderPrice the value for `trade_payment`.order_price
     *
     * @mbggenerated
     */
    public void setOrderPrice(Double orderPrice) {
        this.orderPrice = orderPrice;
    }

    /**
     *支付券ID
     *`trade_payment`.coup_id
     *
     * @return the value of `trade_payment`.coup_id
     *
     * @mbggenerated
     */
    public String getCoupId() {
        return coupId;
    }

    /**
     *支付券ID
     *`trade_payment`.coup_id
     *
     * @param coupId the value for `trade_payment`.coup_id
     *
     * @mbggenerated
     */
    public void setCoupId(String coupId) {
        this.coupId = coupId == null ? null : coupId.trim();
    }

    /**
     *券抵扣
     *`trade_payment`.coup_pay
     *
     * @return the value of `trade_payment`.coup_pay
     *
     * @mbggenerated
     */
    public Double getCoupPay() {
        return coupPay;
    }

    /**
     *券抵扣
     *`trade_payment`.coup_pay
     *
     * @param coupPay the value for `trade_payment`.coup_pay
     *
     * @mbggenerated
     */
    public void setCoupPay(Double coupPay) {
        this.coupPay = coupPay;
    }

    /**
     *
     *`trade_payment`.coupon_info
     *
     * @return the value of `trade_payment`.coupon_info
     *
     * @mbggenerated
     */
    public String getCouponInfo() {
        return couponInfo;
    }

    /**
     *
     *`trade_payment`.coupon_info
     *
     * @param couponInfo the value for `trade_payment`.coupon_info
     *
     * @mbggenerated
     */
    public void setCouponInfo(String couponInfo) {
        this.couponInfo = couponInfo == null ? null : couponInfo.trim();
    }

    /**
     *应付
     *`trade_payment`.pay_should
     *
     * @return the value of `trade_payment`.pay_should
     *
     * @mbggenerated
     */
    public Double getPayShould() {
        return payShould;
    }

    /**
     *应付
     *`trade_payment`.pay_should
     *
     * @param payShould the value for `trade_payment`.pay_should
     *
     * @mbggenerated
     */
    public void setPayShould(Double payShould) {
        this.payShould = payShould;
    }

    /**
     *实付order_price=coup_pay+pay_should
                        pay_should=pay_actual+pay_fee
     *`trade_payment`.pay_actual
     *
     * @return the value of `trade_payment`.pay_actual
     *
     * @mbggenerated
     */
    public Double getPayActual() {
        return payActual;
    }

    /**
     *实付order_price=coup_pay+pay_should
                        pay_should=pay_actual+pay_fee
     *`trade_payment`.pay_actual
     *
     * @param payActual the value for `trade_payment`.pay_actual
     *
     * @mbggenerated
     */
    public void setPayActual(Double payActual) {
        this.payActual = payActual;
    }

    /**
     *支付手续费
     *`trade_payment`.pay_fee
     *
     * @return the value of `trade_payment`.pay_fee
     *
     * @mbggenerated
     */
    public Double getPayFee() {
        return payFee;
    }

    /**
     *支付手续费
     *`trade_payment`.pay_fee
     *
     * @param payFee the value for `trade_payment`.pay_fee
     *
     * @mbggenerated
     */
    public void setPayFee(Double payFee) {
        this.payFee = payFee;
    }

    /**
     *支付来源，1为PC，2表示wap 3客户端app
     *`trade_payment`.pay_source
     *
     * @return the value of `trade_payment`.pay_source
     *
     * @mbggenerated
     */
    public Integer getPaySource() {
        return paySource;
    }

    /**
     *支付来源，1为PC，2表示wap 3客户端app
     *`trade_payment`.pay_source
     *
     * @param paySource the value for `trade_payment`.pay_source
     *
     * @mbggenerated
     */
    public void setPaySource(Integer paySource) {
        this.paySource = paySource;
    }

    /**
     *支付时间
     *`trade_payment`.pay_time
     *
     * @return the value of `trade_payment`.pay_time
     *
     * @mbggenerated
     */
    public Date getPayTime() {
        return payTime;
    }

    /**
     *支付时间
     *`trade_payment`.pay_time
     *
     * @param payTime the value for `trade_payment`.pay_time
     *
     * @mbggenerated
     */
    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    /**
     *支付过期时间
     *`trade_payment`.order_expire_time
     *
     * @return the value of `trade_payment`.order_expire_time
     *
     * @mbggenerated
     */
    public Date getOrderExpireTime() {
        return orderExpireTime;
    }

    /**
     *支付过期时间
     *`trade_payment`.order_expire_time
     *
     * @param orderExpireTime the value for `trade_payment`.order_expire_time
     *
     * @mbggenerated
     */
    public void setOrderExpireTime(Date orderExpireTime) {
        this.orderExpireTime = orderExpireTime;
    }

    /**
     *支付创建时间
     *`trade_payment`.order_create_time
     *
     * @return the value of `trade_payment`.order_create_time
     *
     * @mbggenerated
     */
    public Date getOrderCreateTime() {
        return orderCreateTime;
    }

    /**
     *支付创建时间
     *`trade_payment`.order_create_time
     *
     * @param orderCreateTime the value for `trade_payment`.order_create_time
     *
     * @mbggenerated
     */
    public void setOrderCreateTime(Date orderCreateTime) {
        this.orderCreateTime = orderCreateTime;
    }

    /**
     *用户资金账户no
     *`trade_payment`.user_account_no
     *
     * @return the value of `trade_payment`.user_account_no
     *
     * @mbggenerated
     */
    public String getUserAccountNo() {
        return userAccountNo;
    }

    /**
     *用户资金账户no
     *`trade_payment`.user_account_no
     *
     * @param userAccountNo the value for `trade_payment`.user_account_no
     *
     * @mbggenerated
     */
    public void setUserAccountNo(String userAccountNo) {
        this.userAccountNo = userAccountNo == null ? null : userAccountNo.trim();
    }

    /**
     *用户支付帐号（支付宝号）
     *`trade_payment`.user_pay_account
     *
     * @return the value of `trade_payment`.user_pay_account
     *
     * @mbggenerated
     */
    public String getUserPayAccount() {
        return userPayAccount;
    }

    /**
     *用户支付帐号（支付宝号）
     *`trade_payment`.user_pay_account
     *
     * @param userPayAccount the value for `trade_payment`.user_pay_account
     *
     * @mbggenerated
     */
    public void setUserPayAccount(String userPayAccount) {
        this.userPayAccount = userPayAccount == null ? null : userPayAccount.trim();
    }

    /**
     *支付用的手机号
     *`trade_payment`.user_mobile
     *
     * @return the value of `trade_payment`.user_mobile
     *
     * @mbggenerated
     */
    public String getUserMobile() {
        return userMobile;
    }

    /**
     *支付用的手机号
     *`trade_payment`.user_mobile
     *
     * @param userMobile the value for `trade_payment`.user_mobile
     *
     * @mbggenerated
     */
    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile == null ? null : userMobile.trim();
    }

    /**
     *支付用户ID
     *`trade_payment`.user_id
     *
     * @return the value of `trade_payment`.user_id
     *
     * @mbggenerated
     */
    public String getUserId() {
        return userId;
    }

    /**
     *支付用户ID
     *`trade_payment`.user_id
     *
     * @param userId the value for `trade_payment`.user_id
     *
     * @mbggenerated
     */
    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    /**
     *
     *`trade_payment`.user_name
     *
     * @return the value of `trade_payment`.user_name
     *
     * @mbggenerated
     */
    public String getUserName() {
        return userName;
    }

    /**
     *
     *`trade_payment`.user_name
     *
     * @param userName the value for `trade_payment`.user_name
     *
     * @mbggenerated
     */
    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    /**
     *支付名称
     *`trade_payment`.pay_subject
     *
     * @return the value of `trade_payment`.pay_subject
     *
     * @mbggenerated
     */
    public String getPaySubject() {
        return paySubject;
    }

    /**
     *支付名称
     *`trade_payment`.pay_subject
     *
     * @param paySubject the value for `trade_payment`.pay_subject
     *
     * @mbggenerated
     */
    public void setPaySubject(String paySubject) {
        this.paySubject = paySubject == null ? null : paySubject.trim();
    }

    /**
     *支付描述
     *`trade_payment`.pay_desc
     *
     * @return the value of `trade_payment`.pay_desc
     *
     * @mbggenerated
     */
    public String getPayDesc() {
        return payDesc;
    }

    /**
     *支付描述
     *`trade_payment`.pay_desc
     *
     * @param payDesc the value for `trade_payment`.pay_desc
     *
     * @mbggenerated
     */
    public void setPayDesc(String payDesc) {
        this.payDesc = payDesc == null ? null : payDesc.trim();
    }

    /**
     *第三方支付号
     *`trade_payment`.third_pay_no
     *
     * @return the value of `trade_payment`.third_pay_no
     *
     * @mbggenerated
     */
    public String getThirdPayNo() {
        return thirdPayNo;
    }

    /**
     *第三方支付号
     *`trade_payment`.third_pay_no
     *
     * @param thirdPayNo the value for `trade_payment`.third_pay_no
     *
     * @mbggenerated
     */
    public void setThirdPayNo(String thirdPayNo) {
        this.thirdPayNo = thirdPayNo == null ? null : thirdPayNo.trim();
    }

    /**
     *1:未通知;2,支付成功;3,支付失败
     *`trade_payment`.third_notify_status
     *
     * @return the value of `trade_payment`.third_notify_status
     *
     * @mbggenerated
     */
    public String getThirdNotifyStatus() {
        return thirdNotifyStatus;
    }

    /**
     *1:未通知;2,支付成功;3,支付失败
     *`trade_payment`.third_notify_status
     *
     * @param thirdNotifyStatus the value for `trade_payment`.third_notify_status
     *
     * @mbggenerated
     */
    public void setThirdNotifyStatus(String thirdNotifyStatus) {
        this.thirdNotifyStatus = thirdNotifyStatus == null ? null : thirdNotifyStatus.trim();
    }

    /**
     *
     *`trade_payment`.update_time
     *
     * @return the value of `trade_payment`.update_time
     *
     * @mbggenerated
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     *
     *`trade_payment`.update_time
     *
     * @param updateTime the value for `trade_payment`.update_time
     *
     * @mbggenerated
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     *
     *`trade_payment`.create_time
     *
     * @return the value of `trade_payment`.create_time
     *
     * @mbggenerated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     *
     *`trade_payment`.create_time
     *
     * @param createTime the value for `trade_payment`.create_time
     *
     * @mbggenerated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     *
     *`trade_payment`.third_notify_log
     *
     * @return the value of `trade_payment`.third_notify_log
     *
     * @mbggenerated
     */
    public String getThirdNotifyLog() {
        return thirdNotifyLog;
    }

    /**
     *
     *`trade_payment`.third_notify_log
     *
     * @param thirdNotifyLog the value for `trade_payment`.third_notify_log
     *
     * @mbggenerated
     */
    public void setThirdNotifyLog(String thirdNotifyLog) {
        this.thirdNotifyLog = thirdNotifyLog == null ? null : thirdNotifyLog.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `trade_payment`
     *
     * @mbggenerated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", payNo=").append(payNo);
        sb.append(", orderNo=").append(orderNo);
        sb.append(", payGetway=").append(payGetway);
        sb.append(", payGatewayName=").append(payGatewayName);
        sb.append(", payStatus=").append(payStatus);
        sb.append(", orderPrice=").append(orderPrice);
        sb.append(", coupId=").append(coupId);
        sb.append(", coupPay=").append(coupPay);
        sb.append(", couponInfo=").append(couponInfo);
        sb.append(", payShould=").append(payShould);
        sb.append(", payActual=").append(payActual);
        sb.append(", payFee=").append(payFee);
        sb.append(", paySource=").append(paySource);
        sb.append(", payTime=").append(payTime);
        sb.append(", orderExpireTime=").append(orderExpireTime);
        sb.append(", orderCreateTime=").append(orderCreateTime);
        sb.append(", userAccountNo=").append(userAccountNo);
        sb.append(", userPayAccount=").append(userPayAccount);
        sb.append(", userMobile=").append(userMobile);
        sb.append(", userId=").append(userId);
        sb.append(", userName=").append(userName);
        sb.append(", paySubject=").append(paySubject);
        sb.append(", payDesc=").append(payDesc);
        sb.append(", thirdPayNo=").append(thirdPayNo);
        sb.append(", thirdNotifyStatus=").append(thirdNotifyStatus);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", createTime=").append(createTime);
        sb.append(", thirdNotifyLog=").append(thirdNotifyLog);
        sb.append("]");
        return sb.toString();
    }
}