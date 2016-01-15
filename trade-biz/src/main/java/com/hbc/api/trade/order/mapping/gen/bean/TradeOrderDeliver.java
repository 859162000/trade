package com.hbc.api.trade.order.mapping.gen.bean;

import java.util.Date;

public class TradeOrderDeliver {
    /**
     *  派单号
     *  所属表字段为`trade_deliver`.deliver_no
     */
    private String deliverNo;

    /**
     *  订单号
     *  所属表字段为`trade_deliver`.order_no
     */
    private String orderNo;

    /**
     *  1-正常发单；2-立即发单；3-增量补发；4-取消重发 5 指派导游 6 支付前预指派导游
     *  所属表字段为`trade_deliver`.deliver_type
     */
    private Integer deliverType;

    /**
     *  
     *  所属表字段为`trade_deliver`.deliver_type_name
     */
    private String deliverTypeName;

    /**
     *  发单状态 (0, "未发单"), (1,"已发送导游"),(2,"接单成功 pk成功"),(3,"接单成功并且消息已经发送"),
     *  所属表字段为`trade_deliver`.deliver_status
     */
    private Integer deliverStatus;

    /**
     *  
     *  所属表字段为`trade_deliver`.deliver_status_name
     */
    private String deliverStatusName;

    /**
     *  (0,"初始态"),(1,"PK中"),(2, "PK失败"),(3, "PK成功")
     *  所属表字段为`trade_deliver`.pk_status
     */
    private Integer pkStatus;

    /**
     *  
     *  所属表字段为`trade_deliver`.pk_status_name
     */
    private String pkStatusName;

    /**
     *  发送导游条数（取消重发累加）
     *  所属表字段为`trade_deliver`.deliver_count
     */
    private Integer deliverCount;

    /**
     *  导游接单数
     *  所属表字段为`trade_deliver`.guide_receive_count
     */
    private Integer guideReceiveCount;

    /**
     *  
     *  所属表字段为`trade_deliver`.city_id
     */
    private Integer cityId;

    /**
     *  发单时间
     *  所属表字段为`trade_deliver`.deliver_time
     */
    private Date deliverTime;

    /**
     *  pk时间最近一次
     *  所属表字段为`trade_deliver`.pk_time
     */
    private Date pkTime;

    /**
     *  更新时间
     *  所属表字段为`trade_deliver`.update_time
     */
    private Date updateTime;

    /**
     *  创建时间
     *  所属表字段为`trade_deliver`.create_time
     */
    private Date createTime;

    /**
     *派单号
     *`trade_deliver`.deliver_no
     *
     * @return the value of `trade_deliver`.deliver_no
     *
     * @mbggenerated
     */
    public String getDeliverNo() {
        return deliverNo;
    }

    /**
     *派单号
     *`trade_deliver`.deliver_no
     *
     * @param deliverNo the value for `trade_deliver`.deliver_no
     *
     * @mbggenerated
     */
    public void setDeliverNo(String deliverNo) {
        this.deliverNo = deliverNo == null ? null : deliverNo.trim();
    }

    /**
     *订单号
     *`trade_deliver`.order_no
     *
     * @return the value of `trade_deliver`.order_no
     *
     * @mbggenerated
     */
    public String getOrderNo() {
        return orderNo;
    }

    /**
     *订单号
     *`trade_deliver`.order_no
     *
     * @param orderNo the value for `trade_deliver`.order_no
     *
     * @mbggenerated
     */
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    /**
     *1-正常发单；2-立即发单；3-增量补发；4-取消重发 5 指派导游 6 支付前预指派导游
     *`trade_deliver`.deliver_type
     *
     * @return the value of `trade_deliver`.deliver_type
     *
     * @mbggenerated
     */
    public Integer getDeliverType() {
        return deliverType;
    }

    /**
     *1-正常发单；2-立即发单；3-增量补发；4-取消重发 5 指派导游 6 支付前预指派导游
     *`trade_deliver`.deliver_type
     *
     * @param deliverType the value for `trade_deliver`.deliver_type
     *
     * @mbggenerated
     */
    public void setDeliverType(Integer deliverType) {
        this.deliverType = deliverType;
    }

    /**
     *
     *`trade_deliver`.deliver_type_name
     *
     * @return the value of `trade_deliver`.deliver_type_name
     *
     * @mbggenerated
     */
    public String getDeliverTypeName() {
        return deliverTypeName;
    }

    /**
     *
     *`trade_deliver`.deliver_type_name
     *
     * @param deliverTypeName the value for `trade_deliver`.deliver_type_name
     *
     * @mbggenerated
     */
    public void setDeliverTypeName(String deliverTypeName) {
        this.deliverTypeName = deliverTypeName == null ? null : deliverTypeName.trim();
    }

    /**
     *发单状态 (0, "未发单"), (1,"已发送导游"),(2,"接单成功 pk成功"),(3,"接单成功并且消息已经发送"),
     *`trade_deliver`.deliver_status
     *
     * @return the value of `trade_deliver`.deliver_status
     *
     * @mbggenerated
     */
    public Integer getDeliverStatus() {
        return deliverStatus;
    }

    /**
     *发单状态 (0, "未发单"), (1,"已发送导游"),(2,"接单成功 pk成功"),(3,"接单成功并且消息已经发送"),
     *`trade_deliver`.deliver_status
     *
     * @param deliverStatus the value for `trade_deliver`.deliver_status
     *
     * @mbggenerated
     */
    public void setDeliverStatus(Integer deliverStatus) {
        this.deliverStatus = deliverStatus;
    }

    /**
     *
     *`trade_deliver`.deliver_status_name
     *
     * @return the value of `trade_deliver`.deliver_status_name
     *
     * @mbggenerated
     */
    public String getDeliverStatusName() {
        return deliverStatusName;
    }

    /**
     *
     *`trade_deliver`.deliver_status_name
     *
     * @param deliverStatusName the value for `trade_deliver`.deliver_status_name
     *
     * @mbggenerated
     */
    public void setDeliverStatusName(String deliverStatusName) {
        this.deliverStatusName = deliverStatusName == null ? null : deliverStatusName.trim();
    }

    /**
     *(0,"初始态"),(1,"PK中"),(2, "PK失败"),(3, "PK成功")
     *`trade_deliver`.pk_status
     *
     * @return the value of `trade_deliver`.pk_status
     *
     * @mbggenerated
     */
    public Integer getPkStatus() {
        return pkStatus;
    }

    /**
     *(0,"初始态"),(1,"PK中"),(2, "PK失败"),(3, "PK成功")
     *`trade_deliver`.pk_status
     *
     * @param pkStatus the value for `trade_deliver`.pk_status
     *
     * @mbggenerated
     */
    public void setPkStatus(Integer pkStatus) {
        this.pkStatus = pkStatus;
    }

    /**
     *
     *`trade_deliver`.pk_status_name
     *
     * @return the value of `trade_deliver`.pk_status_name
     *
     * @mbggenerated
     */
    public String getPkStatusName() {
        return pkStatusName;
    }

    /**
     *
     *`trade_deliver`.pk_status_name
     *
     * @param pkStatusName the value for `trade_deliver`.pk_status_name
     *
     * @mbggenerated
     */
    public void setPkStatusName(String pkStatusName) {
        this.pkStatusName = pkStatusName == null ? null : pkStatusName.trim();
    }

    /**
     *发送导游条数（取消重发累加）
     *`trade_deliver`.deliver_count
     *
     * @return the value of `trade_deliver`.deliver_count
     *
     * @mbggenerated
     */
    public Integer getDeliverCount() {
        return deliverCount;
    }

    /**
     *发送导游条数（取消重发累加）
     *`trade_deliver`.deliver_count
     *
     * @param deliverCount the value for `trade_deliver`.deliver_count
     *
     * @mbggenerated
     */
    public void setDeliverCount(Integer deliverCount) {
        this.deliverCount = deliverCount;
    }

    /**
     *导游接单数
     *`trade_deliver`.guide_receive_count
     *
     * @return the value of `trade_deliver`.guide_receive_count
     *
     * @mbggenerated
     */
    public Integer getGuideReceiveCount() {
        return guideReceiveCount;
    }

    /**
     *导游接单数
     *`trade_deliver`.guide_receive_count
     *
     * @param guideReceiveCount the value for `trade_deliver`.guide_receive_count
     *
     * @mbggenerated
     */
    public void setGuideReceiveCount(Integer guideReceiveCount) {
        this.guideReceiveCount = guideReceiveCount;
    }

    /**
     *
     *`trade_deliver`.city_id
     *
     * @return the value of `trade_deliver`.city_id
     *
     * @mbggenerated
     */
    public Integer getCityId() {
        return cityId;
    }

    /**
     *
     *`trade_deliver`.city_id
     *
     * @param cityId the value for `trade_deliver`.city_id
     *
     * @mbggenerated
     */
    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    /**
     *发单时间
     *`trade_deliver`.deliver_time
     *
     * @return the value of `trade_deliver`.deliver_time
     *
     * @mbggenerated
     */
    public Date getDeliverTime() {
        return deliverTime;
    }

    /**
     *发单时间
     *`trade_deliver`.deliver_time
     *
     * @param deliverTime the value for `trade_deliver`.deliver_time
     *
     * @mbggenerated
     */
    public void setDeliverTime(Date deliverTime) {
        this.deliverTime = deliverTime;
    }

    /**
     *pk时间最近一次
     *`trade_deliver`.pk_time
     *
     * @return the value of `trade_deliver`.pk_time
     *
     * @mbggenerated
     */
    public Date getPkTime() {
        return pkTime;
    }

    /**
     *pk时间最近一次
     *`trade_deliver`.pk_time
     *
     * @param pkTime the value for `trade_deliver`.pk_time
     *
     * @mbggenerated
     */
    public void setPkTime(Date pkTime) {
        this.pkTime = pkTime;
    }

    /**
     *更新时间
     *`trade_deliver`.update_time
     *
     * @return the value of `trade_deliver`.update_time
     *
     * @mbggenerated
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     *更新时间
     *`trade_deliver`.update_time
     *
     * @param updateTime the value for `trade_deliver`.update_time
     *
     * @mbggenerated
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     *创建时间
     *`trade_deliver`.create_time
     *
     * @return the value of `trade_deliver`.create_time
     *
     * @mbggenerated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     *创建时间
     *`trade_deliver`.create_time
     *
     * @param createTime the value for `trade_deliver`.create_time
     *
     * @mbggenerated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table `trade_deliver`
     *
     * @mbggenerated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", deliverNo=").append(deliverNo);
        sb.append(", orderNo=").append(orderNo);
        sb.append(", deliverType=").append(deliverType);
        sb.append(", deliverTypeName=").append(deliverTypeName);
        sb.append(", deliverStatus=").append(deliverStatus);
        sb.append(", deliverStatusName=").append(deliverStatusName);
        sb.append(", pkStatus=").append(pkStatus);
        sb.append(", pkStatusName=").append(pkStatusName);
        sb.append(", deliverCount=").append(deliverCount);
        sb.append(", guideReceiveCount=").append(guideReceiveCount);
        sb.append(", cityId=").append(cityId);
        sb.append(", deliverTime=").append(deliverTime);
        sb.append(", pkTime=").append(pkTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", createTime=").append(createTime);
        sb.append("]");
        return sb.toString();
    }
}