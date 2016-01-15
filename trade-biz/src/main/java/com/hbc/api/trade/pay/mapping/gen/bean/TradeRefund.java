package com.hbc.api.trade.pay.mapping.gen.bean;

import java.util.Date;

public class TradeRefund {
    /**
     *  退款ID
     *  所属表字段为`trade_refund`.refund_no
     */
    private String refundNo;

    /**
     *  
     *  所属表字段为`trade_refund`.order_no
     */
    private String orderNo;

    /**
     *  批次号
     *  所属表字段为`trade_refund`.batch_no
     */
    private String batchNo;

    /**
     *  退款笔数
     *  所属表字段为`trade_refund`.batch_num
     */
    private Integer batchNum;

    /**
     *  退款状态  1退款进行中 2 退款成功 
     *  所属表字段为`trade_refund`.refund_status
     */
    private Integer refundStatus;

    /**
     *  1 系统自动退款 2人工退款处理
     *  所属表字段为`trade_refund`.refund_type
     */
    private Integer refundType;

    /**
     *  退款发生时 订单状态
     *  所属表字段为`trade_refund`.order_status
     */
    private Integer orderStatus;

    /**
     *  退款发生时 订单状态
     *  所属表字段为`trade_refund`.order_status_name
     */
    private String orderStatusName;

    /**
     *  该笔退款产生的支付订单号
     *  所属表字段为`trade_refund`.pay_no
     */
    private String payNo;

    /**
     *  退款到的账户
     *  所属表字段为`trade_refund`.refund_account
     */
    private String refundAccount;

    /**
     *  退给用户的金额
     *  所属表字段为`trade_refund`.refund_money
     */
    private Double refundMoney;

    /**
     *  退款给导游的金额
     *  所属表字段为`trade_refund`.refund_money_guide
     */
    private Double refundMoneyGuide;

    /**
     *  退款给系统的金额
     *  所属表字段为`trade_refund`.refund_money_system
     */
    private Double refundMoneySystem;

    /**
     *  退款名称
     *  所属表字段为`trade_refund`.refund_subject
     */
    private String refundSubject;

    /**
     *  退款描述
     *  所属表字段为`trade_refund`.refund_desc
     */
    private String refundDesc;

    /**
     *  异常信息记录
     *  所属表字段为`trade_refund`.refund_error
     */
    private String refundError;

    /**
     *  退款详细信息
     *  所属表字段为`trade_refund`.refund_detail
     */
    private String refundDetail;

    /**
     *  退款请求直接返回报文 非异步通知
     *  所属表字段为`trade_refund`.refund_request
     */
    private String refundRequest;

    /**
     *  退款ipn信息
     *  所属表字段为`trade_refund`.refund_response
     */
    private String refundResponse;

    /**
     *  退款请求时间
     *  所属表字段为`trade_refund`.refund_time
     */
    private Date refundTime;

    /**
     *  
     *  所属表字段为`trade_refund`.user_id
     */
    private String userId;

    /**
     *  
     *  所属表字段为`trade_refund`.user_mobile
     */
    private String userMobile;

    /**
     *  支付方式。1-网银；2-信用卡；5-支付宝；6-微信； 7内部账 101-招商银行；102-工商银行；103-农业银行；104-中国银行；
     *  所属表字段为`trade_refund`.pay_getway
     */
    private Integer payGetway;

    /**
     *  
     *  所属表字段为`trade_refund`.pay_gateway_name
     */
    private String payGatewayName;

    /**
     *  退款getway id
     *  所属表字段为`trade_refund`.refund_getway
     */
    private Integer refundGetway;

    /**
     *  退款getway名称
     *  所属表字段为`trade_refund`.refund_getway_name
     */
    private String refundGetwayName;

    /**
     *  
     *  所属表字段为`trade_refund`.create_time
     */
    private Date createTime;

    /**
     *  
     *  所属表字段为`trade_refund`.update_time
     */
    private Date updateTime;

    /**
     *退款ID
     *`trade_refund`.refund_no
     *
     * @return the value of `trade_refund`.refund_no
     *
     * @mbggenerated
     */
    public String getRefundNo() {
        return refundNo;
    }

    /**
     *退款ID
     *`trade_refund`.refund_no
     *
     * @param refundNo the value for `trade_refund`.refund_no
     *
     * @mbggenerated
     */
    public void setRefundNo(String refundNo) {
        this.refundNo = refundNo == null ? null : refundNo.trim();
    }

    /**
     *
     *`trade_refund`.order_no
     *
     * @return the value of `trade_refund`.order_no
     *
     * @mbggenerated
     */
    public String getOrderNo() {
        return orderNo;
    }

    /**
     *
     *`trade_refund`.order_no
     *
     * @param orderNo the value for `trade_refund`.order_no
     *
     * @mbggenerated
     */
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    /**
     *批次号
     *`trade_refund`.batch_no
     *
     * @return the value of `trade_refund`.batch_no
     *
     * @mbggenerated
     */
    public String getBatchNo() {
        return batchNo;
    }

    /**
     *批次号
     *`trade_refund`.batch_no
     *
     * @param batchNo the value for `trade_refund`.batch_no
     *
     * @mbggenerated
     */
    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo == null ? null : batchNo.trim();
    }

    /**
     *退款笔数
     *`trade_refund`.batch_num
     *
     * @return the value of `trade_refund`.batch_num
     *
     * @mbggenerated
     */
    public Integer getBatchNum() {
        return batchNum;
    }

    /**
     *退款笔数
     *`trade_refund`.batch_num
     *
     * @param batchNum the value for `trade_refund`.batch_num
     *
     * @mbggenerated
     */
    public void setBatchNum(Integer batchNum) {
        this.batchNum = batchNum;
    }

    /**
     *退款状态  1退款进行中 2 退款成功 
     *`trade_refund`.refund_status
     *
     * @return the value of `trade_refund`.refund_status
     *
     * @mbggenerated
     */
    public Integer getRefundStatus() {
        return refundStatus;
    }

    /**
     *退款状态  1退款进行中 2 退款成功 
     *`trade_refund`.refund_status
     *
     * @param refundStatus the value for `trade_refund`.refund_status
     *
     * @mbggenerated
     */
    public void setRefundStatus(Integer refundStatus) {
        this.refundStatus = refundStatus;
    }

    /**
     *1 系统自动退款 2人工退款处理
     *`trade_refund`.refund_type
     *
     * @return the value of `trade_refund`.refund_type
     *
     * @mbggenerated
     */
    public Integer getRefundType() {
        return refundType;
    }

    /**
     *1 系统自动退款 2人工退款处理
     *`trade_refund`.refund_type
     *
     * @param refundType the value for `trade_refund`.refund_type
     *
     * @mbggenerated
     */
    public void setRefundType(Integer refundType) {
        this.refundType = refundType;
    }

    /**
     *退款发生时 订单状态
     *`trade_refund`.order_status
     *
     * @return the value of `trade_refund`.order_status
     *
     * @mbggenerated
     */
    public Integer getOrderStatus() {
        return orderStatus;
    }

    /**
     *退款发生时 订单状态
     *`trade_refund`.order_status
     *
     * @param orderStatus the value for `trade_refund`.order_status
     *
     * @mbggenerated
     */
    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    /**
     *退款发生时 订单状态
     *`trade_refund`.order_status_name
     *
     * @return the value of `trade_refund`.order_status_name
     *
     * @mbggenerated
     */
    public String getOrderStatusName() {
        return orderStatusName;
    }

    /**
     *退款发生时 订单状态
     *`trade_refund`.order_status_name
     *
     * @param orderStatusName the value for `trade_refund`.order_status_name
     *
     * @mbggenerated
     */
    public void setOrderStatusName(String orderStatusName) {
        this.orderStatusName = orderStatusName == null ? null : orderStatusName.trim();
    }

    /**
     *该笔退款产生的支付订单号
     *`trade_refund`.pay_no
     *
     * @return the value of `trade_refund`.pay_no
     *
     * @mbggenerated
     */
    public String getPayNo() {
        return payNo;
    }

    /**
     *该笔退款产生的支付订单号
     *`trade_refund`.pay_no
     *
     * @param payNo the value for `trade_refund`.pay_no
     *
     * @mbggenerated
     */
    public void setPayNo(String payNo) {
        this.payNo = payNo == null ? null : payNo.trim();
    }

    /**
     *退款到的账户
     *`trade_refund`.refund_account
     *
     * @return the value of `trade_refund`.refund_account
     *
     * @mbggenerated
     */
    public String getRefundAccount() {
        return refundAccount;
    }

    /**
     *退款到的账户
     *`trade_refund`.refund_account
     *
     * @param refundAccount the value for `trade_refund`.refund_account
     *
     * @mbggenerated
     */
    public void setRefundAccount(String refundAccount) {
        this.refundAccount = refundAccount == null ? null : refundAccount.trim();
    }

    /**
     *退给用户的金额
     *`trade_refund`.refund_money
     *
     * @return the value of `trade_refund`.refund_money
     *
     * @mbggenerated
     */
    public Double getRefundMoney() {
        return refundMoney;
    }

    /**
     *退给用户的金额
     *`trade_refund`.refund_money
     *
     * @param refundMoney the value for `trade_refund`.refund_money
     *
     * @mbggenerated
     */
    public void setRefundMoney(Double refundMoney) {
        this.refundMoney = refundMoney;
    }

    /**
     *退款给导游的金额
     *`trade_refund`.refund_money_guide
     *
     * @return the value of `trade_refund`.refund_money_guide
     *
     * @mbggenerated
     */
    public Double getRefundMoneyGuide() {
        return refundMoneyGuide;
    }

    /**
     *退款给导游的金额
     *`trade_refund`.refund_money_guide
     *
     * @param refundMoneyGuide the value for `trade_refund`.refund_money_guide
     *
     * @mbggenerated
     */
    public void setRefundMoneyGuide(Double refundMoneyGuide) {
        this.refundMoneyGuide = refundMoneyGuide;
    }

    /**
     *退款给系统的金额
     *`trade_refund`.refund_money_system
     *
     * @return the value of `trade_refund`.refund_money_system
     *
     * @mbggenerated
     */
    public Double getRefundMoneySystem() {
        return refundMoneySystem;
    }

    /**
     *退款给系统的金额
     *`trade_refund`.refund_money_system
     *
     * @param refundMoneySystem the value for `trade_refund`.refund_money_system
     *
     * @mbggenerated
     */
    public void setRefundMoneySystem(Double refundMoneySystem) {
        this.refundMoneySystem = refundMoneySystem;
    }

    /**
     *退款名称
     *`trade_refund`.refund_subject
     *
     * @return the value of `trade_refund`.refund_subject
     *
     * @mbggenerated
     */
    public String getRefundSubject() {
        return refundSubject;
    }

    /**
     *退款名称
     *`trade_refund`.refund_subject
     *
     * @param refundSubject the value for `trade_refund`.refund_subject
     *
     * @mbggenerated
     */
    public void setRefundSubject(String refundSubject) {
        this.refundSubject = refundSubject == null ? null : refundSubject.trim();
    }

    /**
     *退款描述
     *`trade_refund`.refund_desc
     *
     * @return the value of `trade_refund`.refund_desc
     *
     * @mbggenerated
     */
    public String getRefundDesc() {
        return refundDesc;
    }

    /**
     *退款描述
     *`trade_refund`.refund_desc
     *
     * @param refundDesc the value for `trade_refund`.refund_desc
     *
     * @mbggenerated
     */
    public void setRefundDesc(String refundDesc) {
        this.refundDesc = refundDesc == null ? null : refundDesc.trim();
    }

    /**
     *异常信息记录
     *`trade_refund`.refund_error
     *
     * @return the value of `trade_refund`.refund_error
     *
     * @mbggenerated
     */
    public String getRefundError() {
        return refundError;
    }

    /**
     *异常信息记录
     *`trade_refund`.refund_error
     *
     * @param refundError the value for `trade_refund`.refund_error
     *
     * @mbggenerated
     */
    public void setRefundError(String refundError) {
        this.refundError = refundError == null ? null : refundError.trim();
    }

    /**
     *退款详细信息
     *`trade_refund`.refund_detail
     *
     * @return the value of `trade_refund`.refund_detail
     *
     * @mbggenerated
     */
    public String getRefundDetail() {
        return refundDetail;
    }

    /**
     *退款详细信息
     *`trade_refund`.refund_detail
     *
     * @param refundDetail the value for `trade_refund`.refund_detail
     *
     * @mbggenerated
     */
    public void setRefundDetail(String refundDetail) {
        this.refundDetail = refundDetail == null ? null : refundDetail.trim();
    }

    /**
     *退款请求直接返回报文 非异步通知
     *`trade_refund`.refund_request
     *
     * @return the value of `trade_refund`.refund_request
     *
     * @mbggenerated
     */
    public String getRefundRequest() {
        return refundRequest;
    }

    /**
     *退款请求直接返回报文 非异步通知
     *`trade_refund`.refund_request
     *
     * @param refundRequest the value for `trade_refund`.refund_request
     *
     * @mbggenerated
     */
    public void setRefundRequest(String refundRequest) {
        this.refundRequest = refundRequest == null ? null : refundRequest.trim();
    }

    /**
     *退款ipn信息
     *`trade_refund`.refund_response
     *
     * @return the value of `trade_refund`.refund_response
     *
     * @mbggenerated
     */
    public String getRefundResponse() {
        return refundResponse;
    }

    /**
     *退款ipn信息
     *`trade_refund`.refund_response
     *
     * @param refundResponse the value for `trade_refund`.refund_response
     *
     * @mbggenerated
     */
    public void setRefundResponse(String refundResponse) {
        this.refundResponse = refundResponse == null ? null : refundResponse.trim();
    }

    /**
     *退款请求时间
     *`trade_refund`.refund_time
     *
     * @return the value of `trade_refund`.refund_time
     *
     * @mbggenerated
     */
    public Date getRefundTime() {
        return refundTime;
    }

    /**
     *退款请求时间
     *`trade_refund`.refund_time
     *
     * @param refundTime the value for `trade_refund`.refund_time
     *
     * @mbggenerated
     */
    public void setRefundTime(Date refundTime) {
        this.refundTime = refundTime;
    }

    /**
     *
     *`trade_refund`.user_id
     *
     * @return the value of `trade_refund`.user_id
     *
     * @mbggenerated
     */
    public String getUserId() {
        return userId;
    }

    /**
     *
     *`trade_refund`.user_id
     *
     * @param userId the value for `trade_refund`.user_id
     *
     * @mbggenerated
     */
    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    /**
     *
     *`trade_refund`.user_mobile
     *
     * @return the value of `trade_refund`.user_mobile
     *
     * @mbggenerated
     */
    public String getUserMobile() {
        return userMobile;
    }

    /**
     *
     *`trade_refund`.user_mobile
     *
     * @param userMobile the value for `trade_refund`.user_mobile
     *
     * @mbggenerated
     */
    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile == null ? null : userMobile.trim();
    }

    /**
     *支付方式。1-网银；2-信用卡；5-支付宝；6-微信； 7内部账 101-招商银行；102-工商银行；103-农业银行；104-中国银行；
     *`trade_refund`.pay_getway
     *
     * @return the value of `trade_refund`.pay_getway
     *
     * @mbggenerated
     */
    public Integer getPayGetway() {
        return payGetway;
    }

    /**
     *支付方式。1-网银；2-信用卡；5-支付宝；6-微信； 7内部账 101-招商银行；102-工商银行；103-农业银行；104-中国银行；
     *`trade_refund`.pay_getway
     *
     * @param payGetway the value for `trade_refund`.pay_getway
     *
     * @mbggenerated
     */
    public void setPayGetway(Integer payGetway) {
        this.payGetway = payGetway;
    }

    /**
     *
     *`trade_refund`.pay_gateway_name
     *
     * @return the value of `trade_refund`.pay_gateway_name
     *
     * @mbggenerated
     */
    public String getPayGatewayName() {
        return payGatewayName;
    }

    /**
     *
     *`trade_refund`.pay_gateway_name
     *
     * @param payGatewayName the value for `trade_refund`.pay_gateway_name
     *
     * @mbggenerated
     */
    public void setPayGatewayName(String payGatewayName) {
        this.payGatewayName = payGatewayName == null ? null : payGatewayName.trim();
    }

    /**
     *退款getway id
     *`trade_refund`.refund_getway
     *
     * @return the value of `trade_refund`.refund_getway
     *
     * @mbggenerated
     */
    public Integer getRefundGetway() {
        return refundGetway;
    }

    /**
     *退款getway id
     *`trade_refund`.refund_getway
     *
     * @param refundGetway the value for `trade_refund`.refund_getway
     *
     * @mbggenerated
     */
    public void setRefundGetway(Integer refundGetway) {
        this.refundGetway = refundGetway;
    }

    /**
     *退款getway名称
     *`trade_refund`.refund_getway_name
     *
     * @return the value of `trade_refund`.refund_getway_name
     *
     * @mbggenerated
     */
    public String getRefundGetwayName() {
        return refundGetwayName;
    }

    /**
     *退款getway名称
     *`trade_refund`.refund_getway_name
     *
     * @param refundGetwayName the value for `trade_refund`.refund_getway_name
     *
     * @mbggenerated
     */
    public void setRefundGetwayName(String refundGetwayName) {
        this.refundGetwayName = refundGetwayName == null ? null : refundGetwayName.trim();
    }

    /**
     *
     *`trade_refund`.create_time
     *
     * @return the value of `trade_refund`.create_time
     *
     * @mbggenerated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     *
     *`trade_refund`.create_time
     *
     * @param createTime the value for `trade_refund`.create_time
     *
     * @mbggenerated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     *
     *`trade_refund`.update_time
     *
     * @return the value of `trade_refund`.update_time
     *
     * @mbggenerated
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     *
     *`trade_refund`.update_time
     *
     * @param updateTime the value for `trade_refund`.update_time
     *
     * @mbggenerated
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `trade_refund`
     *
     * @mbggenerated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", refundNo=").append(refundNo);
        sb.append(", orderNo=").append(orderNo);
        sb.append(", batchNo=").append(batchNo);
        sb.append(", batchNum=").append(batchNum);
        sb.append(", refundStatus=").append(refundStatus);
        sb.append(", refundType=").append(refundType);
        sb.append(", orderStatus=").append(orderStatus);
        sb.append(", orderStatusName=").append(orderStatusName);
        sb.append(", payNo=").append(payNo);
        sb.append(", refundAccount=").append(refundAccount);
        sb.append(", refundMoney=").append(refundMoney);
        sb.append(", refundMoneyGuide=").append(refundMoneyGuide);
        sb.append(", refundMoneySystem=").append(refundMoneySystem);
        sb.append(", refundSubject=").append(refundSubject);
        sb.append(", refundDesc=").append(refundDesc);
        sb.append(", refundError=").append(refundError);
        sb.append(", refundDetail=").append(refundDetail);
        sb.append(", refundRequest=").append(refundRequest);
        sb.append(", refundResponse=").append(refundResponse);
        sb.append(", refundTime=").append(refundTime);
        sb.append(", userId=").append(userId);
        sb.append(", userMobile=").append(userMobile);
        sb.append(", payGetway=").append(payGetway);
        sb.append(", payGatewayName=").append(payGatewayName);
        sb.append(", refundGetway=").append(refundGetway);
        sb.append(", refundGetwayName=").append(refundGetwayName);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append("]");
        return sb.toString();
    }
}