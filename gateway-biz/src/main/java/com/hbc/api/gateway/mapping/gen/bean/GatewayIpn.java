package com.hbc.api.gateway.mapping.gen.bean;

import java.util.Date;

public class GatewayIpn {
    /**
     *  本次通知IPN号 若为notify 为notify_id
     *  所属表字段为`gateway_ipn`.ipn_no
     */
    private String ipnNo;

    /**
     *  支付方式。1-网银；2-信用卡；5-支付宝；6-微信；101-招商银行；102-工商银行；103-农业银行；104-中国银行；
     *  所属表字段为`gateway_ipn`.gateway_id
     */
    private Integer gatewayId;

    /**
     *  getway中文名
     *  所属表字段为`gateway_ipn`.gateway_name
     */
    private String gatewayName;

    /**
     *  订单号
     *  所属表字段为`gateway_ipn`.order_no
     */
    private String orderNo;

    /**
     *  第三方交易号
     *  所属表字段为`gateway_ipn`.trade_no
     */
    private String tradeNo;

    /**
     *  支付号
     *  所属表字段为`gateway_ipn`.pay_no
     */
    private String payNo;

    /**
     *  ipn 1 支付宝付款 2支付宝退款 3微信支付 4微信退款  5支付宝转账
     *  所属表字段为`gateway_ipn`.ipn_type
     */
    private Integer ipnType;

    /**
     *  通知类型名称
     *  所属表字段为`gateway_ipn`.ipn_type_name
     */
    private String ipnTypeName;

    /**
     *  1 成功 2失败 3部分成功
     *  所属表字段为`gateway_ipn`.ipn_status
     */
    private Integer ipnStatus;

    /**
     *  退款批次号
     *  所属表字段为`gateway_ipn`.ipn_batch_no
     */
    private String ipnBatchNo;

    /**
     *  1 成功 其他失败
     *  所属表字段为`gateway_ipn`.send_kafka_flag
     */
    private Integer sendKafkaFlag;

    /**
     *  
     *  所属表字段为`gateway_ipn`.create_time
     */
    private Date createTime;

    /**
     *  
     *  所属表字段为`gateway_ipn`.update_time
     */
    private Date updateTime;

    /**
     *  返回信息 全部post原始信息
     *  所属表字段为`gateway_ipn`.ipn_content
     */
    private String ipnContent;

    /**
     *本次通知IPN号 若为notify 为notify_id
     *`gateway_ipn`.ipn_no
     *
     * @return the value of `gateway_ipn`.ipn_no
     *
     * @mbggenerated
     */
    public String getIpnNo() {
        return ipnNo;
    }

    /**
     *本次通知IPN号 若为notify 为notify_id
     *`gateway_ipn`.ipn_no
     *
     * @param ipnNo the value for `gateway_ipn`.ipn_no
     *
     * @mbggenerated
     */
    public void setIpnNo(String ipnNo) {
        this.ipnNo = ipnNo == null ? null : ipnNo.trim();
    }

    /**
     *支付方式。1-网银；2-信用卡；5-支付宝；6-微信；101-招商银行；102-工商银行；103-农业银行；104-中国银行；
     *`gateway_ipn`.gateway_id
     *
     * @return the value of `gateway_ipn`.gateway_id
     *
     * @mbggenerated
     */
    public Integer getGatewayId() {
        return gatewayId;
    }

    /**
     *支付方式。1-网银；2-信用卡；5-支付宝；6-微信；101-招商银行；102-工商银行；103-农业银行；104-中国银行；
     *`gateway_ipn`.gateway_id
     *
     * @param gatewayId the value for `gateway_ipn`.gateway_id
     *
     * @mbggenerated
     */
    public void setGatewayId(Integer gatewayId) {
        this.gatewayId = gatewayId;
    }

    /**
     *getway中文名
     *`gateway_ipn`.gateway_name
     *
     * @return the value of `gateway_ipn`.gateway_name
     *
     * @mbggenerated
     */
    public String getGatewayName() {
        return gatewayName;
    }

    /**
     *getway中文名
     *`gateway_ipn`.gateway_name
     *
     * @param gatewayName the value for `gateway_ipn`.gateway_name
     *
     * @mbggenerated
     */
    public void setGatewayName(String gatewayName) {
        this.gatewayName = gatewayName == null ? null : gatewayName.trim();
    }

    /**
     *订单号
     *`gateway_ipn`.order_no
     *
     * @return the value of `gateway_ipn`.order_no
     *
     * @mbggenerated
     */
    public String getOrderNo() {
        return orderNo;
    }

    /**
     *订单号
     *`gateway_ipn`.order_no
     *
     * @param orderNo the value for `gateway_ipn`.order_no
     *
     * @mbggenerated
     */
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    /**
     *第三方交易号
     *`gateway_ipn`.trade_no
     *
     * @return the value of `gateway_ipn`.trade_no
     *
     * @mbggenerated
     */
    public String getTradeNo() {
        return tradeNo;
    }

    /**
     *第三方交易号
     *`gateway_ipn`.trade_no
     *
     * @param tradeNo the value for `gateway_ipn`.trade_no
     *
     * @mbggenerated
     */
    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo == null ? null : tradeNo.trim();
    }

    /**
     *支付号
     *`gateway_ipn`.pay_no
     *
     * @return the value of `gateway_ipn`.pay_no
     *
     * @mbggenerated
     */
    public String getPayNo() {
        return payNo;
    }

    /**
     *支付号
     *`gateway_ipn`.pay_no
     *
     * @param payNo the value for `gateway_ipn`.pay_no
     *
     * @mbggenerated
     */
    public void setPayNo(String payNo) {
        this.payNo = payNo == null ? null : payNo.trim();
    }

    /**
     *ipn 1 支付宝付款 2支付宝退款 3微信支付 4微信退款  5支付宝转账
     *`gateway_ipn`.ipn_type
     *
     * @return the value of `gateway_ipn`.ipn_type
     *
     * @mbggenerated
     */
    public Integer getIpnType() {
        return ipnType;
    }

    /**
     *ipn 1 支付宝付款 2支付宝退款 3微信支付 4微信退款  5支付宝转账
     *`gateway_ipn`.ipn_type
     *
     * @param ipnType the value for `gateway_ipn`.ipn_type
     *
     * @mbggenerated
     */
    public void setIpnType(Integer ipnType) {
        this.ipnType = ipnType;
    }

    /**
     *通知类型名称
     *`gateway_ipn`.ipn_type_name
     *
     * @return the value of `gateway_ipn`.ipn_type_name
     *
     * @mbggenerated
     */
    public String getIpnTypeName() {
        return ipnTypeName;
    }

    /**
     *通知类型名称
     *`gateway_ipn`.ipn_type_name
     *
     * @param ipnTypeName the value for `gateway_ipn`.ipn_type_name
     *
     * @mbggenerated
     */
    public void setIpnTypeName(String ipnTypeName) {
        this.ipnTypeName = ipnTypeName == null ? null : ipnTypeName.trim();
    }

    /**
     *1 成功 2失败 3部分成功
     *`gateway_ipn`.ipn_status
     *
     * @return the value of `gateway_ipn`.ipn_status
     *
     * @mbggenerated
     */
    public Integer getIpnStatus() {
        return ipnStatus;
    }

    /**
     *1 成功 2失败 3部分成功
     *`gateway_ipn`.ipn_status
     *
     * @param ipnStatus the value for `gateway_ipn`.ipn_status
     *
     * @mbggenerated
     */
    public void setIpnStatus(Integer ipnStatus) {
        this.ipnStatus = ipnStatus;
    }

    /**
     *退款批次号
     *`gateway_ipn`.ipn_batch_no
     *
     * @return the value of `gateway_ipn`.ipn_batch_no
     *
     * @mbggenerated
     */
    public String getIpnBatchNo() {
        return ipnBatchNo;
    }

    /**
     *退款批次号
     *`gateway_ipn`.ipn_batch_no
     *
     * @param ipnBatchNo the value for `gateway_ipn`.ipn_batch_no
     *
     * @mbggenerated
     */
    public void setIpnBatchNo(String ipnBatchNo) {
        this.ipnBatchNo = ipnBatchNo == null ? null : ipnBatchNo.trim();
    }

    /**
     *1 成功 其他失败
     *`gateway_ipn`.send_kafka_flag
     *
     * @return the value of `gateway_ipn`.send_kafka_flag
     *
     * @mbggenerated
     */
    public Integer getSendKafkaFlag() {
        return sendKafkaFlag;
    }

    /**
     *1 成功 其他失败
     *`gateway_ipn`.send_kafka_flag
     *
     * @param sendKafkaFlag the value for `gateway_ipn`.send_kafka_flag
     *
     * @mbggenerated
     */
    public void setSendKafkaFlag(Integer sendKafkaFlag) {
        this.sendKafkaFlag = sendKafkaFlag;
    }

    /**
     *
     *`gateway_ipn`.create_time
     *
     * @return the value of `gateway_ipn`.create_time
     *
     * @mbggenerated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     *
     *`gateway_ipn`.create_time
     *
     * @param createTime the value for `gateway_ipn`.create_time
     *
     * @mbggenerated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     *
     *`gateway_ipn`.update_time
     *
     * @return the value of `gateway_ipn`.update_time
     *
     * @mbggenerated
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     *
     *`gateway_ipn`.update_time
     *
     * @param updateTime the value for `gateway_ipn`.update_time
     *
     * @mbggenerated
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     *返回信息 全部post原始信息
     *`gateway_ipn`.ipn_content
     *
     * @return the value of `gateway_ipn`.ipn_content
     *
     * @mbggenerated
     */
    public String getIpnContent() {
        return ipnContent;
    }

    /**
     *返回信息 全部post原始信息
     *`gateway_ipn`.ipn_content
     *
     * @param ipnContent the value for `gateway_ipn`.ipn_content
     *
     * @mbggenerated
     */
    public void setIpnContent(String ipnContent) {
        this.ipnContent = ipnContent == null ? null : ipnContent.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `gateway_ipn`
     *
     * @mbggenerated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", ipnNo=").append(ipnNo);
        sb.append(", gatewayId=").append(gatewayId);
        sb.append(", gatewayName=").append(gatewayName);
        sb.append(", orderNo=").append(orderNo);
        sb.append(", tradeNo=").append(tradeNo);
        sb.append(", payNo=").append(payNo);
        sb.append(", ipnType=").append(ipnType);
        sb.append(", ipnTypeName=").append(ipnTypeName);
        sb.append(", ipnStatus=").append(ipnStatus);
        sb.append(", ipnBatchNo=").append(ipnBatchNo);
        sb.append(", sendKafkaFlag=").append(sendKafkaFlag);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", ipnContent=").append(ipnContent);
        sb.append("]");
        return sb.toString();
    }
}